package com.fsc.external.portal.services.servicerequest;

import com.fsc.common.addon.db.DBNumberGeneratorService;
import com.fsc.common.addon.db.PrcProcessServiceRequestDBProcedure;
import com.fsc.common.addon.entity.User;
import com.fsc.common.addon.entity.catalog.service.ServiceRequest;
import com.fsc.common.addon.entity.catalog.service.ServiceRequestDocument;
import com.fsc.common.addon.entity.document.DocumentType;
import com.fsc.common.addon.entity.enums.ServiceRequestStatus;
import com.fsc.common.addon.service.uiconfigurator.dto.FormTemplateDataObject;
import com.fsc.common.addon.service.user.UserService;
import com.fsc.common.addon.util.DataConvensionUtils;
import com.fsc.exception.library.exception.FscRuntimeException;
import com.fsc.exception.library.exception.RecordNotFoundException;
import com.fsc.exception.library.exception.enums.ErrorDefinition;
import com.fsc.external.portal.dtos.ServiceRequestDocumentRequest;
import com.fsc.external.portal.dtos.ServiceRequestDocumentResponse;
import com.fsc.external.portal.dtos.SubmitFormResponse;
import com.fsc.external.portal.error.ServiceRequestErrorDefinition;
import com.fsc.external.portal.mappers.ServiceRequestDocumentMapper;
import com.fsc.external.portal.services.ExternalDynamicFormCommunicationService;
import com.fsc.external.portal.services.FileService;
import io.jmix.core.DataManager;
import io.jmix.core.FetchPlan;
import io.jmix.core.FetchPlans;
import io.jmix.core.FileRef;
import io.jmix.core.SaveContext;
import io.jmix.core.querycondition.LogicalCondition;
import io.jmix.core.querycondition.PropertyCondition;
import io.jmix.core.security.CurrentAuthentication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@Service
public class ServiceRequestServiceImpl implements ServiceRequestService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ServiceRequestServiceImpl.class);

    private static final String SUBMITTED_SR_FP = "submitted-service-request-fetch-plan";
    private static final String RESULT_FIELD = "result";
    private static final String DATA_FIELD = "data";
    private static final String ERROR_FIELD = "error";
    private static final String CATALOG_ELEMENT_ID_QUERY = "select o.catalogElement.id from ServiceRequest o"
                                                           + " where o.id = :serviceRequestId";
    private final DataManager dataManager;
    private final ServiceRequestDocumentMapper serviceRequestDocumentMapper;
    private final FetchPlans fetchPlans;
    private final FileService fileService;
    private final CurrentAuthentication currentAuthentication;
    private final DBNumberGeneratorService dbNumberGeneratorService;
    private final ExternalDynamicFormCommunicationService externalDynamicFormCommunicationService;
    private final UserService userService;
    private final DataSource dataSource;

    public ServiceRequestServiceImpl(
            DataManager dataManager,
            ServiceRequestDocumentMapper serviceRequestDocumentMapper,
            FetchPlans fetchPlans,
            FileService fileService,
            CurrentAuthentication currentAuthentication,
            DBNumberGeneratorService dbNumberGeneratorService,
            ExternalDynamicFormCommunicationService externalDynamicFormCommunicationService,
            UserService userService,
            DataSource dataSource) {
        this.dataManager = dataManager;
        this.serviceRequestDocumentMapper = serviceRequestDocumentMapper;
        this.fetchPlans = fetchPlans;
        this.fileService = fileService;
        this.currentAuthentication = currentAuthentication;
        this.dbNumberGeneratorService = dbNumberGeneratorService;
        this.externalDynamicFormCommunicationService = externalDynamicFormCommunicationService;
        this.userService = userService;
        this.dataSource = dataSource;
    }

    @Override
    public void updateSubmitData(ServiceRequest serviceRequest, String submitData) {
        SaveContext saveContext = new SaveContext();
        saveContext.setDiscardSaved(true);
        serviceRequest.setRequestData(submitData);
        saveContext.saving(serviceRequest);

        dataManager.save(saveContext);
    }

    @Override
    public void delete(Long serviceRequestId) {
        User user = (User) currentAuthentication.getUser();

        LogicalCondition logicalCondition = LogicalCondition.and();
        logicalCondition.add(PropertyCondition.equal("id", serviceRequestId));
        logicalCondition.add(PropertyCondition.equal("submittedBy.id", user.getId()));
        ServiceRequest serviceRequest = dataManager.load(ServiceRequest.class)
                .condition(logicalCondition)
                .fetchPlan(generateFetchPlan())
                .optional().orElseThrow(() -> new RecordNotFoundException(ErrorDefinition.RECORD_NOT_FOUND));

        dataManager.remove(serviceRequest);
    }

    @Override
    public ServiceRequestDocumentResponse attachServiceRequestDocument(
            Long serviceRequestId,
            ServiceRequestDocumentRequest serviceRequestDocumentRequest) {

        User user = (User) currentAuthentication.getUser();
        ServiceRequest serviceRequest = dataManager.load(ServiceRequest.class).id(serviceRequestId)
                                                   .fetchPlan(generateFetchPlan())
                                                   .one(); // TODO are we OK with IllegalStateException if no result is found ?
        if (!Objects.equals(user.getId(), serviceRequest.getSubmittedBy().getId())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
        // TODO there is no check is SR is still a DRAFT

        ServiceRequestDocument serviceRequestDocument = serviceRequestDocumentMapper.toEntity(
                serviceRequestDocumentRequest);
        serviceRequestDocument.setMain(false);
        serviceRequestDocument.setServiceRequest(serviceRequest);
        dataManager.save(serviceRequestDocument);

        return serviceRequestDocumentMapper.toResponse(serviceRequestDocument);
    }

    @Override
    public ServiceRequestDocument attachServiceRequestDocument(Long serviceRequestId, Long documentTypeId,
                                                               FileRef fileRef) {
        User user = (User) currentAuthentication.getUser();

        LogicalCondition logicalCondition = LogicalCondition.and();
        logicalCondition.add(PropertyCondition.equal("id", serviceRequestId));
        logicalCondition.add(PropertyCondition.equal("submittedBy.id", user.getId()));
        logicalCondition.add(PropertyCondition.equal("status", ServiceRequestStatus.DRAFT));

        ServiceRequest serviceRequest = dataManager.load(ServiceRequest.class)
                .condition(logicalCondition)
                .fetchPlan(generateFetchPlan()).one();  // TODO are we OK with IllegalStateException if no result is found ?


        ServiceRequestDocument serviceRequestDocument = dataManager.create(ServiceRequestDocument.class);
        serviceRequestDocument.setDocumentType(dataManager.getReference(DocumentType.class, documentTypeId));
        serviceRequestDocument.setFileReference(fileRef);
        serviceRequestDocument.setCreatedDate(LocalDateTime.now());
        serviceRequestDocument.setMain(false);
        serviceRequestDocument.setServiceRequest(serviceRequest);
        dataManager.save(serviceRequestDocument);

        return serviceRequestDocument;
    }

    @Override
    public ServiceRequestDocumentResponse attachServiceRequestDocumentByAnonymousUser(
            Long serviceRequestId,
            ServiceRequestDocumentRequest serviceRequestDocumentRequest) {

        ServiceRequest serviceRequest = dataManager.load(ServiceRequest.class).id(serviceRequestId)
                                                   .fetchPlan(generateFetchPlan())
                                                   .one();

        ServiceRequestDocument serviceRequestDocument = serviceRequestDocumentMapper.toEntity(
                serviceRequestDocumentRequest);
        serviceRequestDocument.setMain(false);
        serviceRequestDocument.setServiceRequest(serviceRequest);
        dataManager.save(serviceRequestDocument);

        return serviceRequestDocumentMapper.toResponse(serviceRequestDocument);
    }

    @Transactional
    @Override
    public String submitServiceRequest(Long serviceRequestId, String documentDescription, FileRef fileReference) {

        User user = (User) currentAuthentication.getUser();
        ServiceRequest serviceRequest = getByIdAndSubmitter(serviceRequestId, user.getId());

        if (!ServiceRequestStatus.DRAFT.equals(serviceRequest.getStatus())) {
            throw new RuntimeException("The status is not draft"); // TODO change message and exception
        }

        serviceRequest.setServiceRequestNumber(String.format("SR-%s", dbNumberGeneratorService.callServiceRequestNumSeq()));
        serviceRequest.setStatus(ServiceRequestStatus.SUBMITTED);

        serviceRequest = dataManager.save(serviceRequest);

        ServiceRequestDocument serviceRequestDocument = dataManager.create(ServiceRequestDocument.class);
        serviceRequestDocument.setMain(true);
        serviceRequestDocument.setServiceRequest(serviceRequest);

        DocumentType documentType = dataManager.getReference(DocumentType.class,
                serviceRequest.getCatalogElement().getDocumentType().getId());
        serviceRequestDocument.setDocumentType(documentType);
        serviceRequestDocument.setDocumentDescription(documentDescription);
        serviceRequestDocument.setCreatedDate(LocalDateTime.now());
        serviceRequestDocument.setFileReference(fileReference);

        dataManager.save(serviceRequestDocument);

        return serviceRequest.getServiceRequestNumber();
    }

    @Transactional
    @Override
    public String submitServiceRequest(ServiceRequest serviceRequest, ServiceRequestDocumentRequest serviceRequestDocumentRequest) {
        SaveContext saveContext = new SaveContext();
        saveContext.setDiscardSaved(true);

        serviceRequest = updateServiceRequest(serviceRequest);

        saveContext.saving(serviceRequest);

        if (serviceRequestDocumentRequest != null) {
            serviceRequestDocumentRequest.setDocumentTypeId(serviceRequest.getCatalogElement().getDocumentType().getId());
            ServiceRequestDocument serviceRequestDocument = serviceRequestDocumentMapper.toEntity(serviceRequestDocumentRequest);
            serviceRequestDocument.setMain(true);
            serviceRequestDocument.setServiceRequest(serviceRequest);
            saveContext.saving(serviceRequestDocument);
        }

        dataManager.save(saveContext);

        return serviceRequest.getServiceRequestNumber();
    }

    @Override
    public void deleteDocument(Long serviceRequestId, Long documentId) {
        User user = (User) currentAuthentication.getUser();

        ServiceRequest serviceRequest = dataManager.load(ServiceRequest.class).id(serviceRequestId).one();
        if (!ServiceRequestStatus.DRAFT.equals(serviceRequest.getStatus())) {
            throw new FscRuntimeException(HttpStatus.FORBIDDEN, ServiceRequestErrorDefinition.CODE_003);
        }

        LogicalCondition logicalCondition = LogicalCondition.and();
        logicalCondition.add(PropertyCondition.equal("id", documentId));
        if (!userService.isCurrentUserAnonymous()) {
            logicalCondition.add(PropertyCondition.equal("serviceRequest.submittedBy.id", user.getId()));
        }
        logicalCondition.add(PropertyCondition.equal("serviceRequest.id", serviceRequestId));

        ServiceRequestDocument doc = dataManager
                .load(ServiceRequestDocument.class)
                .condition(logicalCondition)
                .optional().orElseThrow(() -> new RecordNotFoundException(ErrorDefinition.RECORD_NOT_FOUND));
        this.fileService.remove(doc.getFileReference().toString());
        dataManager.remove(doc);
    }

    @Deprecated
    @Override
    public void deleteDocument(Long documentId) {
        User user = (User) currentAuthentication.getUser();

        LogicalCondition logicalCondition = LogicalCondition.and();
        logicalCondition.add(PropertyCondition.equal("id", documentId));
        logicalCondition.add(PropertyCondition.equal("serviceRequest.submittedBy.id", user.getId()));

        ServiceRequestDocument doc = dataManager
                .load(ServiceRequestDocument.class)
                .condition(logicalCondition)
                .optional().orElseThrow(() -> new RecordNotFoundException(ErrorDefinition.RECORD_NOT_FOUND));
        this.fileService.remove(doc.getFileReference().toString());
        dataManager.remove(doc);
    }

    @Override
    public Set<ServiceRequestDocumentResponse> getServiceRequestDocuments(Long serviceRequestId) {
        ServiceRequest serviceRequest = dataManager.load(ServiceRequest.class).id(serviceRequestId)
                .optional().orElseThrow(() -> new RecordNotFoundException(ErrorDefinition.RECORD_NOT_FOUND));

        if (serviceRequest.getSubmittedBy() != null && serviceRequest.getSubmittedBy().getId() != null) {
            User user = (User) currentAuthentication.getUser();
            if (!Objects.equals(user.getId(), serviceRequest.getSubmittedBy().getId())) {
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
            }
        }

        return serviceRequestDocumentMapper.toResponse(serviceRequest.getDocuments());
    }

    @Override
    public Optional<ServiceRequest> checkIfDraftServiceRequestExist(Long catalogElementId, Long pnlId, Long personId) {
        User user = (User) currentAuthentication.getUser();
        LogicalCondition logicalCondition = LogicalCondition.and();
        logicalCondition.add(PropertyCondition.equal("catalogElement.id", catalogElementId));
        logicalCondition.add(PropertyCondition.equal("status", ServiceRequestStatus.DRAFT));
        logicalCondition.add(PropertyCondition.equal("submittedBy.id", user.getId()));

        if (!Objects.isNull(pnlId)) {
            logicalCondition.add(PropertyCondition.equal("pnl.id", pnlId));
        }
        if (!Objects.isNull(personId)) {
            logicalCondition.add(PropertyCondition.equal("person.id", personId));
        }

        return dataManager.load(ServiceRequest.class)
                .condition(logicalCondition)
                .fetchPlan(generateFetchPlan())
                .optional();
    }

    @Override
    public Optional<ServiceRequest> getUnfinishedServiceRequest(Long serviceRequestId, User user) {
        LogicalCondition logicalCondition = LogicalCondition.and();
        logicalCondition.add(PropertyCondition.equal("id", serviceRequestId));
        logicalCondition.add(PropertyCondition.equal("status", ServiceRequestStatus.DRAFT));
        logicalCondition.add(PropertyCondition.equal("submittedBy.id", user.getId()));

        return dataManager.load(ServiceRequest.class)
                .condition(logicalCondition)
                .fetchPlan(generateFetchPlan())
                .optional();
    }

    @Override
    public Optional<ServiceRequest> getServiceRequest(Long serviceRequestId) {
        Optional<ServiceRequest> serviceRequestOptional = dataManager
                .load(ServiceRequest.class)
                .id(serviceRequestId)
                .fetchPlan(SUBMITTED_SR_FP)
                .optional();
        return serviceRequestOptional;
    }

    @Override
    public Long getStartedServiceRequest(Long catalogElementId, Long pnlId, Long personId) {
        Optional<ServiceRequest> serviceRequestOptional = checkIfDraftServiceRequestExist(catalogElementId, pnlId, personId);

        return serviceRequestOptional.isPresent() && serviceRequestOptional.get().getCatalogElement().getHasDraft()
                ? serviceRequestOptional.get().getId() : null;
    }

    @Override
    public ServiceRequest getByIdAndSubmitter(long id, Long submitterId) {
        LogicalCondition logicalCondition = LogicalCondition.and();

        logicalCondition.add(PropertyCondition.equal("id", id));
        logicalCondition.add(PropertyCondition.equal("submittedBy.id", submitterId));

        return dataManager
                .load(ServiceRequest.class)
                .condition(logicalCondition)
                .fetchPlan(generateFetchPlan())
                .optional().orElseThrow(() -> new RecordNotFoundException(ErrorDefinition.RECORD_NOT_FOUND));
    }

    @Override
    public SubmitFormResponse updateServiceRequest(ServiceRequest serviceRequest,
                                                                    Map<String, Object> submitData) {

        FormTemplateDataObject templateDataObject = new FormTemplateDataObject();
        templateDataObject.setSubmitData(DataConvensionUtils.convertMapToString(submitData));
        templateDataObject.setTemplateId(serviceRequest.getTemplateId());

        Map<String, Object> validationResult =
                externalDynamicFormCommunicationService.validate(templateDataObject);

        final SubmitFormResponse response = new SubmitFormResponse();

        if (!Boolean.parseBoolean(validationResult.get(RESULT_FIELD).toString())) {
            Map<String, Object> errors = (Map<String, Object>) validationResult.get(ERROR_FIELD);
            response.setErrors(errors);

            return response;
        }

        Map<String, Object> data = (Map<String, Object>) validationResult.get(DATA_FIELD);
        updateSubmitData(serviceRequest, DataConvensionUtils.convertMapToString(data));

        response.setServiceRequestId(serviceRequest.getId());

        return response;
    }

    @Override
    public ServiceRequest updateServiceRequest(ServiceRequest serviceRequest) {
        serviceRequest.setServiceRequestNumber(String.format("SR-%s", dbNumberGeneratorService.callServiceRequestNumSeq()));
        serviceRequest.setStatus(ServiceRequestStatus.SUBMITTED);

        return serviceRequest;
    }

    @Override
    public ServiceRequestDocument getRequestServiceDocumentById(Long documentId) {
        return dataManager.load(ServiceRequestDocument.class).id(documentId)
                       .optional()
                       .orElseThrow(() -> new RecordNotFoundException(ErrorDefinition.RECORD_NOT_FOUND));
    }

    @Override
    public Long getCatalogElementIdFromServiceRequest(Long serviceRequestId) {
        return dataManager.loadValue(CATALOG_ELEMENT_ID_QUERY, Long.class)
                          .parameter("serviceRequestId", serviceRequestId)
                          .optional()
                          .orElseThrow(() -> new RecordNotFoundException(ErrorDefinition.RECORD_NOT_FOUND));
    }

    @Override
    public void processServiceRequest(Long serviceRequestId) {
        PrcProcessServiceRequestDBProcedure processServiceRequestDBProcedure = new PrcProcessServiceRequestDBProcedure(dataSource);

        Map<String, Object> procedureResult = processServiceRequestDBProcedure.execute(serviceRequestId);

        if (procedureResult.get(PrcProcessServiceRequestDBProcedure.OUT_PARAM_ERROR_MSG) != null) {
            LOGGER.error(procedureResult.get(PrcProcessServiceRequestDBProcedure.OUT_PARAM_ERROR_MSG).toString());
            throw new RuntimeException("Execution of PCK_SERVICE_REQUESTS.PROCESS_SR stored procedure failed.");
        }
    }

    private FetchPlan generateFetchPlan() {
        return fetchPlans.builder(ServiceRequest.class)
                .addFetchPlan(FetchPlan.BASE)
                .add("documents")
                .add("documents.documentType")
                .add("documents.fileReference")
                .add("catalogElement")
                .add("catalogElement.parents")
                .add("catalogElement.children")
                .add("catalogElement.documentType")
                .add("catalogElement.catalogElementTexts")
                .add("submittedBy")
                .add("pnl")
                .add("person")
                .build();
    }
}