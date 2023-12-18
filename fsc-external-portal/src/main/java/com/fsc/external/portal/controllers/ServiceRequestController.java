package com.fsc.external.portal.controllers;

import com.fsc.common.addon.entity.User;
import com.fsc.common.addon.entity.accounting.charges.Charges;
import com.fsc.common.addon.entity.catalog.service.CatalogElement;
import com.fsc.common.addon.entity.catalog.service.ServiceRequest;
import com.fsc.common.addon.entity.enums.EventType;
import com.fsc.common.addon.entity.enums.ServiceRequestStatus;
import com.fsc.common.addon.service.CorrespondenceService;
import com.fsc.common.addon.service.accounting.AccountingService;
import com.fsc.exception.library.exception.FscRuntimeException;
import com.fsc.exception.library.exception.RecordNotFoundException;
import com.fsc.exception.library.exception.enums.ErrorDefinition;
import com.fsc.external.portal.dtos.ServiceRequestDocumentRequest;
import com.fsc.external.portal.dtos.ServiceRequestDocumentResponse;
import com.fsc.external.portal.dtos.ServiceRequestRequest;
import com.fsc.external.portal.dtos.SubmitFormResponse;
import com.fsc.external.portal.dtos.SubmittedServiceRequestResponse;
import com.fsc.external.portal.error.ServiceRequestErrorDefinition;
import com.fsc.external.portal.services.CatalogElementService;
import com.fsc.external.portal.services.servicerequest.ServiceRequestCreator;
import com.fsc.external.portal.services.servicerequest.ServiceRequestCreatorFactory;
import com.fsc.external.portal.services.servicerequest.ServiceRequestService;
import io.jmix.core.DataManager;
import io.jmix.core.SaveContext;
import io.jmix.core.security.CurrentAuthentication;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/rest/service-request")
@Tag(name = "Service Request API")
public class ServiceRequestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ServiceRequestController.class);

    private final ServiceRequestService serviceRequestService;
    private final CurrentAuthentication currentAuthentication;
    private final ServiceRequestCreatorFactory serviceRequestCreatorFactory;
    private final DataManager dataManager;
    private final CatalogElementService catalogElementService;
    private final CorrespondenceService correspondenceService;
    private final AccountingService accountingService;

    public ServiceRequestController(
            ServiceRequestService serviceRequestService,
            CurrentAuthentication currentAuthentication,
            ServiceRequestCreatorFactory serviceRequestCreatorFactory,
            DataManager dataManager,
            CatalogElementService catalogElementService,
            CorrespondenceService correspondenceService,
            AccountingService accountingService) {
        this.serviceRequestService = serviceRequestService;
        this.currentAuthentication = currentAuthentication;
        this.serviceRequestCreatorFactory = serviceRequestCreatorFactory;
        this.dataManager = dataManager;
        this.catalogElementService = catalogElementService;
        this.correspondenceService = correspondenceService;
        this.accountingService = accountingService;
    }

    @Operation(
            summary = "Finish service request",
            parameters = {
                @Parameter(name = "serviceRequestId", in = ParameterIn.PATH, required = true,
                        description = "The service request id used to fetch the service request")
            }
    )
    @PutMapping("/{serviceRequestId}/status/submitted")
    public ResponseEntity<SubmittedServiceRequestResponse> finishServiceRequest(
            @PathVariable final Long serviceRequestId) {
        try {
            //TODO когато ФЕ започнат да изпращат правилния хедър Accept-language
            //request.getLocale().toLanguageTag().toUpperCase();
            final String locale = "BG";
            SaveContext saveContext = new SaveContext();
            saveContext.setDiscardSaved(true);

            SubmittedServiceRequestResponse submittedServiceRequestResponse = new SubmittedServiceRequestResponse();

            Long catalogElementId = serviceRequestService.getCatalogElementIdFromServiceRequest(serviceRequestId);
            if (catalogElementService.checkIsAuthenticationLevelPublic(catalogElementId)) {
                throw new FscRuntimeException(HttpStatus.FORBIDDEN, ServiceRequestErrorDefinition.CODE_003);
            }

            Optional<ServiceRequest> serviceRequestOptional = serviceRequestService.getServiceRequest(serviceRequestId);

            if (serviceRequestOptional.isEmpty()) {
                throw new RecordNotFoundException(ErrorDefinition.RECORD_NOT_FOUND);
            }
            ServiceRequest serviceRequest = serviceRequestOptional.get();
            if (!serviceRequest.getSubmittedBy().getId().equals(((User) currentAuthentication.getUser()).getId())) {
                throw new FscRuntimeException(HttpStatus.FORBIDDEN, ServiceRequestErrorDefinition.CODE_001);
            }
            if (!ServiceRequestStatus.DRAFT.equals(serviceRequest.getStatus())) {
                throw new FscRuntimeException(HttpStatus.FORBIDDEN, ServiceRequestErrorDefinition.CODE_002);
            }

            serviceRequest = serviceRequestService.updateServiceRequest(serviceRequest);
            saveContext.saving(serviceRequest);
            dataManager.save(saveContext);

            CatalogElement catalogElement = catalogElementService.getCatalogElement(catalogElementId);
            if (catalogElement.getChargesTariffId() != null) {
                Charges charges = accountingService.createCharge(catalogElement, null, locale, serviceRequest);
                submittedServiceRequestResponse.setChargeId(charges.getId());
                accountingService.sendChargeNotification(EventType.CHARGE_CREATED_REMINDER, charges, null);
            }

            serviceRequestService.processServiceRequest(serviceRequestId);
            submittedServiceRequestResponse.setCorrespondenceNumber(correspondenceService.getCorrespondenceNumber(serviceRequestId));

            return ResponseEntity.ok(submittedServiceRequestResponse);
        } catch (RecordNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Create new service request for a given catalog")
    @PostMapping
    public ResponseEntity<SubmitFormResponse> submitForm(
            @Valid @RequestBody ServiceRequestRequest serviceRequestRequest) {

        boolean authenticationLevelPublic = catalogElementService.checkIsAuthenticationLevelPublic(
                serviceRequestRequest.getCatalogElementId());

        if (authenticationLevelPublic) {
            throw new FscRuntimeException(HttpStatus.FORBIDDEN, ServiceRequestErrorDefinition.CODE_003);
        }

        User user = (User) currentAuthentication.getUser();

        Optional<ServiceRequest> draftInProgress = Optional.empty();
        final Long serviceRequestId = serviceRequestRequest.getServiceRequestId();

        if (Objects.nonNull(serviceRequestId)) {
            draftInProgress = serviceRequestService.getUnfinishedServiceRequest(serviceRequestId, user);
        }

        SubmitFormResponse response;
        if (draftInProgress.isEmpty()) {
            ServiceRequestCreator serviceRequestCreator = serviceRequestCreatorFactory.getServiceRequestCreator(
                    ServiceRequestCreator.DEFAULT_CREATOR);
            response = serviceRequestCreator.create(serviceRequestRequest);
        } else {
            response = serviceRequestService
                    .updateServiceRequest(draftInProgress.get(), serviceRequestRequest.getSubmitData());
        }

        if (Objects.nonNull(response.getErrors())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(summary = "Get service request documents")
    @GetMapping("/{serviceRequestId}/documents")
    public ResponseEntity<Set<ServiceRequestDocumentResponse>> getServiceRequestDocuments(
            @PathVariable final Long serviceRequestId) {
        Set<ServiceRequestDocumentResponse> serviceRequestDocuments = serviceRequestService.getServiceRequestDocuments(
                serviceRequestId);

        return ResponseEntity.ok(serviceRequestDocuments);
    }

    @Operation(summary = "Map file refs to service request object")
    @PostMapping("/{serviceRequestId}/document")
    public ResponseEntity<ServiceRequestDocumentResponse> submitFile(
            @PathVariable final Long serviceRequestId,
            @RequestBody final ServiceRequestDocumentRequest serviceRequestDocumentRequest) {

        try {
            Long catalogElementId = serviceRequestService.getCatalogElementIdFromServiceRequest(serviceRequestId);
            boolean authenticationLevelPublic = catalogElementService.checkIsAuthenticationLevelPublic(catalogElementId);

            if (authenticationLevelPublic) {
                throw new FscRuntimeException(HttpStatus.FORBIDDEN, ServiceRequestErrorDefinition.CODE_003);
            }

            ServiceRequestDocumentResponse response =
                    serviceRequestService.attachServiceRequestDocument(serviceRequestId, serviceRequestDocumentRequest);

            return ResponseEntity.ok(response);

        } catch (RecordNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Delete document from service request object")
    @DeleteMapping("/{serviceRequestId}/documents/{documentId}")
    public ResponseEntity<HttpStatus> deleteDocument(@PathVariable final Long serviceRequestId,
                                                     @PathVariable final Long documentId) {

        try {
            Long catalogElementId = serviceRequestService.getCatalogElementIdFromServiceRequest(serviceRequestId);
            boolean authenticationLevelPublic = catalogElementService.checkIsAuthenticationLevelPublic(catalogElementId);

            if (authenticationLevelPublic) {
                throw new FscRuntimeException(HttpStatus.FORBIDDEN, ServiceRequestErrorDefinition.CODE_003);
            }

            serviceRequestService.deleteDocument(serviceRequestId, documentId);

            return ResponseEntity.status(HttpStatus.OK).build();

        } catch (RecordNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Get already started (DRAFT) service request id")
    @GetMapping("/catalog-element/{catalogElementId}")
    public ResponseEntity<Long> getStartedServiceRequestId(
            @PathVariable final Long catalogElementId,
            @RequestParam(required = false) final Long pnlId,
            @RequestParam final Long personId) {

        try {
            boolean authenticationLevelPublic = catalogElementService.checkIsAuthenticationLevelPublic(catalogElementId);

            if (authenticationLevelPublic) {
                throw new FscRuntimeException(HttpStatus.FORBIDDEN, ServiceRequestErrorDefinition.CODE_003);
            }

            Long serviceRequestId = serviceRequestService.getStartedServiceRequest(catalogElementId, pnlId, personId);

            return ResponseEntity.ok(serviceRequestId);

        } catch (RecordNotFoundException ex) {
            throw new RecordNotFoundException(ErrorDefinition.RECORD_NOT_FOUND);
        }
    }

    @Operation(summary = "Delete service request with status DRAFT")
    @DeleteMapping("/{serviceRequestId}")
    public ResponseEntity<HttpStatus> deleteServiceRequest(@PathVariable final Long serviceRequestId) {
        serviceRequestService.delete(serviceRequestId);

        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
