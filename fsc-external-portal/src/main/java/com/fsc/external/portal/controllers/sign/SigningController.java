package com.fsc.external.portal.controllers.sign;

import com.fsc.common.addon.entity.User;
import com.fsc.common.addon.entity.catalog.service.ServiceRequest;
import com.fsc.common.addon.entity.catalog.service.ServiceRequestDocument;
import com.fsc.common.addon.entity.enums.ServiceRequestStatus;
import com.fsc.common.addon.service.sign.BissSigningStructure;
import com.fsc.common.addon.service.sign.DocumentSigningException;
import com.fsc.common.addon.service.sign.DocumentSigningService;
import com.fsc.exception.library.exception.FscRuntimeException;
import com.fsc.exception.library.exception.RecordNotFoundException;
import com.fsc.exception.library.exception.enums.ErrorDefinition;
import com.fsc.external.portal.dtos.ServiceRequestDocumentRequest;
import com.fsc.external.portal.dtos.ServiceRequestDocumentResponse;
import com.fsc.external.portal.error.DocumentSigningErrorDefinition;
import com.fsc.external.portal.error.ServiceRequestErrorDefinition;
import com.fsc.external.portal.mappers.ServiceRequestDocumentMapper;
import com.fsc.external.portal.services.DynamicFormService;
import com.fsc.external.portal.services.servicerequest.ServiceRequestService;
import io.jmix.core.DataManager;
import io.jmix.core.FetchPlan;
import io.jmix.core.FetchPlans;
import io.jmix.core.FileRef;
import io.jmix.core.FileStorage;
import io.jmix.core.FileStorageLocator;
import io.jmix.core.security.CurrentAuthentication;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/rest/signing")
@Tag(name = "Document e-signing API")
public class SigningController {

    private static final Logger log = LoggerFactory.getLogger(SigningController.class);

    private static final String PDF_DOCUMENT_NAME_PREFIX = "service-request-";
    private static final String PDF_DOCUMENT_NAME_SUFFIX = ".pdf";

    private final ServiceRequestService serviceRequestService;
    private final DocumentSigningService documentSigningService;
    private final FileStorageLocator fileStorageLocator;
    private final DataManager dataManager;
    private final ServiceRequestDocumentMapper serviceRequestDocumentMapper;
    private final DynamicFormService dynamicFormService;
    private final CurrentAuthentication currentAuthentication;
    private final FetchPlans fetchPlans;

    public SigningController(ServiceRequestService serviceRequestService,
            DocumentSigningService documentSigningService, FileStorageLocator fileStorageLocator, DataManager dataManager,
            ServiceRequestDocumentMapper serviceRequestDocumentMapper,
            DynamicFormService dynamicFormService, CurrentAuthentication currentAuthentication,
            FetchPlans fetchPlans) {
        this.serviceRequestService = serviceRequestService;
        this.documentSigningService = documentSigningService;
        this.fileStorageLocator = fileStorageLocator;
        this.dataManager = dataManager;
        this.serviceRequestDocumentMapper = serviceRequestDocumentMapper;
        this.dynamicFormService = dynamicFormService;
        this.currentAuthentication = currentAuthentication;
        this.fetchPlans = fetchPlans;
    }

    @Operation(summary = "Prepare Service request for signing",
            description = "Creates pdf file from the populated service request, attach it as a document and prepare all attached "
                                  + "documents for signing",
            parameters = {
                @Parameter(name = "serviceRequestId", description = "Id of the service request to be prepared for signing")
            },
            responses = {
                @ApiResponse(responseCode = "200", description = "Standard response for successful HTTP requests"),
                @ApiResponse(responseCode = "400", description = "BAD REQUEST - CatalogElementId, page or size are not in correct format"),
                @ApiResponse(responseCode = "404", description = "NOT FOUND - The requested resource could not be found",
                        content = @Content(schema = @Schema(name = "ErrorDefinition",
                                type = "object", implementation = ErrorDefinition.class))),
                @ApiResponse(responseCode = "422", description = "Unprocessable Entity",
                        content = @Content(schema = @Schema(name = "Unprocessable Entity",
                                example = "Requested service request exists but is not ready to be prepared for signing.")))
            }
    )
    @PostMapping("/service-request/{serviceRequestId}/prepareForSigning")
    public Set<SigningPrepareResponse> prepareForSigning(
            @PathVariable final Long serviceRequestId,
            @RequestParam(required = false, defaultValue = "false") final boolean createPdf,
            @RequestBody SigningPrepareRequest params
    ) {

        ServiceRequest serviceRequest = getServiceRequest(serviceRequestId);

        // Create pdf file from the request and attach it.
        if (createPdf) {
            ByteArrayOutputStream out = dynamicFormService.getPdfAsStream(serviceRequestId);
            FileRef fileRef = fileStorageLocator.getDefault()
                    .saveStream(PDF_DOCUMENT_NAME_PREFIX + serviceRequestId + PDF_DOCUMENT_NAME_SUFFIX,
                            new ByteArrayInputStream(out.toByteArray()));

            ServiceRequestDocumentRequest serviceRequestDocumentRequest = new ServiceRequestDocumentRequest();
            String requestType = serviceRequest.getRequestType();
            serviceRequestDocumentRequest.setDocumentTypeId(Long.valueOf(requestType));
            serviceRequestDocumentRequest.setFileReferenceStr(fileRef.toString());

            ServiceRequestDocument serviceRequestDocument = serviceRequestDocumentMapper.toEntity(serviceRequestDocumentRequest);
            serviceRequestDocument.setMain(true);
            serviceRequestDocument.setServiceRequest(serviceRequest);
            dataManager.save(serviceRequestDocument);
        }

        Set<SigningPrepareResponse> documents = new HashSet<>();
        {
            Set<ServiceRequestDocumentResponse> serviceRequestDocuments =
                    serviceRequestService.getServiceRequestDocuments(serviceRequestId);
            serviceRequestDocuments.forEach(doc -> {
                if (documentSigningService.canSign(doc.getFileRef())) {
                    try {
                        BissSigningStructure preparedDocument =
                                documentSigningService.prepareForSigning(doc.getFileRef(), params.certificateChain());
                        documents.add(new SigningPrepareResponse(doc, preparedDocument.getFileBase64(), preparedDocument.getHashBase64(),
                                preparedDocument.getHashContentBase64(),
                                preparedDocument.getHashSignBase64(), preparedDocument.getHashCertBase64(),
                                preparedDocument.getCertificateChainBase64()));
                    } catch (DocumentSigningException e) {
                        log.error("Failed preparing document {} for signing. File type is {}. Not going to add it to the list for signing.",
                                doc.getId(), FilenameUtils.getExtension(doc.getFileRef().getFileName()), e);
                    }
                }
            });
        }
        return documents;
    }

    private ServiceRequest getServiceRequest(Long serviceRequestId) {

        ServiceRequest serviceRequest;
        // TODO Remove this fetch plan after refactoring the hashCode and equals methods of CatalogElement entity
        Optional<ServiceRequest> serviceRequestOptional =
                dataManager.load(ServiceRequest.class).id(serviceRequestId)
                        .fetchPlan(fetchPlans.builder(ServiceRequest.class)
                                           .addFetchPlan(FetchPlan.BASE)
                                           .add("catalogElement")
                                           .add("catalogElement.parents")
                                           .add("catalogElement.children")
                                           .add("catalogElement.catalogElementTexts")
                                           .build()).optional();

        if (serviceRequestOptional.isEmpty()) {
            throw new RecordNotFoundException(ErrorDefinition.RECORD_NOT_FOUND);
        }
        serviceRequest = serviceRequestOptional.get();
        if (serviceRequest.getSubmittedBy() != null && serviceRequest.getSubmittedBy().getId() != null) {
            User user = (User) currentAuthentication.getUser();
            if (!Objects.equals(user.getId(), serviceRequest.getSubmittedBy().getId())) {
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
            }
        }

        if (!ServiceRequestStatus.DRAFT.equals(serviceRequest.getStatus())) {
            throw new FscRuntimeException(HttpStatus.UNPROCESSABLE_ENTITY, ServiceRequestErrorDefinition.CODE_002);
        }

        if (StringUtils.isBlank(serviceRequest.getRequestData())) {
            throw new FscRuntimeException(HttpStatus.UNPROCESSABLE_ENTITY, ServiceRequestErrorDefinition.CODE_004);
        }
        return serviceRequest;
    }

    @Operation(summary = "Sign a document",
        description = "Sign the given document and replace the file reference with the signed one",
        parameters = {
            @Parameter(name = "serviceRequestId", description = "Id of the service request containing the file."),
            @Parameter(name = "documentId", description = "Id of the document to be signed.")
        },
        responses = {
            @ApiResponse(responseCode = "200", description = "Standard response for successful HTTP requests"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST - CatalogElementId, page or size are not in correct format"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND - The requested resource could not be found",
                content = @Content(schema = @Schema(name = "ErrorDefinition",
                    type = "object", implementation = ErrorDefinition.class)))
        }
    )
    @PostMapping("/service-request/{serviceRequestId}/document/{documentId}/sign")
    public ServiceRequestDocumentResponse sign(@PathVariable final Long serviceRequestId, @PathVariable final Long documentId,
            @RequestBody SigningRequest params) {

        Optional<ServiceRequestDocument> serviceRequestDocumentOptional = dataManager.load(ServiceRequestDocument.class)
                .query("from ServiceRequestDocument e where e.id = :id and e.serviceRequest.id = :srId")
                .parameter("id", documentId)
                .parameter("srId", serviceRequestId).optional();
        if (serviceRequestDocumentOptional.isEmpty()) {
            throw new RecordNotFoundException(ErrorDefinition.RECORD_NOT_FOUND);
        }

        byte[] bytes;
        try {
            bytes = documentSigningService.sendForSigning(params);
        } catch (Exception e) {
            log.error("Could not sign document.", e);
            throw new FscRuntimeException(HttpStatus.INTERNAL_SERVER_ERROR, DocumentSigningErrorDefinition.CODE_001, e);
        }
        ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
        FileStorage fileStorage = fileStorageLocator.getDefault();
        FileRef fileRef = fileStorage.saveStream(params.getFilename(), inputStream);

        ServiceRequestDocument serviceRequestDocument = serviceRequestDocumentOptional.get();
        FileRef prevFileRef = serviceRequestDocument.getFileReference();
        serviceRequestDocument.setFileReference(fileRef);
        serviceRequestDocument.setSigned(true);
        ServiceRequestDocument saved = dataManager.save(serviceRequestDocument);

        fileStorageLocator.getDefault().removeFile(prevFileRef);

        return serviceRequestDocumentMapper.toResponse(saved);
    }

}
