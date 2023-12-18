package com.fsc.external.portal.controllers;

import com.fsc.common.addon.entity.User;
import com.fsc.common.addon.entity.catalog.service.ServiceRequest;
import com.fsc.common.addon.service.uiconfigurator.ExternalDynamicFormsApiService;
import com.fsc.common.addon.service.uiconfigurator.enums.DynamicFormsParamFormat;
import com.fsc.exception.library.exception.FscRuntimeException;
import com.fsc.exception.library.exception.RecordNotFoundException;
import com.fsc.exception.library.exception.enums.ErrorDefinition;
import com.fsc.external.portal.dtos.KeyDataObjectResponse;
import com.fsc.external.portal.dtos.ServiceRequestDocumentRequest;
import com.fsc.external.portal.dtos.ServiceRequestDocumentResponse;
import com.fsc.external.portal.dtos.ServiceRequestRequest;
import com.fsc.external.portal.dtos.SubmitFormResponse;
import com.fsc.external.portal.dtos.catalog.FormResponse;
import com.fsc.external.portal.error.ServiceRequestErrorDefinition;
import com.fsc.external.portal.services.CatalogElementService;
import com.fsc.external.portal.services.DynamicFormService;
import com.fsc.external.portal.services.servicerequest.ServiceRequestCreator;
import com.fsc.external.portal.services.servicerequest.ServiceRequestCreatorFactory;
import com.fsc.external.portal.services.servicerequest.ServiceRequestService;
import io.jmix.core.FileRef;
import io.jmix.core.security.CurrentAuthentication;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.io.ByteArrayOutputStream;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/rest/dynamic-forms")
@Tag(name = "Dynamic Forms API")
public class DynamicFormsController {

    private static final String PDF_FILE_NAME_FORMAT = "service_request_%s.pdf";

    private final DynamicFormService dynamicFormService;
    private final ServiceRequestService serviceRequestService;
    private final CurrentAuthentication currentAuthentication;
    private final ServiceRequestCreatorFactory serviceRequestCreatorFactory;
    private final CatalogElementService catalogElementService;
    private final ExternalDynamicFormsApiService externalDynamicFormsApiService;

    public DynamicFormsController(
            DynamicFormService dynamicFormService,
            ServiceRequestService serviceRequestService,
            CurrentAuthentication currentAuthentication,
            ServiceRequestCreatorFactory serviceRequestCreatorFactory,
            CatalogElementService catalogElementService,
            ExternalDynamicFormsApiService externalDynamicFormsApiService) {
        this.dynamicFormService = dynamicFormService;
        this.serviceRequestService = serviceRequestService;
        this.currentAuthentication = currentAuthentication;
        this.serviceRequestCreatorFactory = serviceRequestCreatorFactory;
        this.catalogElementService = catalogElementService;
        this.externalDynamicFormsApiService = externalDynamicFormsApiService;
    }

    @Operation(summary = "Get populated service request and attached files for preview")
    @GetMapping("/service-request/{serviceRequestId}")
    public FormResponse getRequestCheckPage(@PathVariable final Long serviceRequestId) {
        try {
            Long catalogElementId = serviceRequestService.getCatalogElementIdFromServiceRequest(serviceRequestId);
            boolean authenticationLevelPublic = catalogElementService.checkIsAuthenticationLevelPublic(catalogElementId);

            if (authenticationLevelPublic) {
                throw new FscRuntimeException(HttpStatus.FORBIDDEN, ServiceRequestErrorDefinition.CODE_003);
            }

            return dynamicFormService.getPopulatedForm(serviceRequestId, DynamicFormsParamFormat.REACT.getId());
        } catch (RecordNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Produces PDF with all input data and attached files")
    @GetMapping(value = "/{serviceRequestId}/format/pdf", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<Resource> getPdf(@PathVariable final Long serviceRequestId) {
        try {
            Long catalogElementId = serviceRequestService.getCatalogElementIdFromServiceRequest(serviceRequestId);
            boolean authenticationLevelPublic = catalogElementService.checkIsAuthenticationLevelPublic(catalogElementId);

            if (authenticationLevelPublic) {
                throw new FscRuntimeException(HttpStatus.FORBIDDEN, ServiceRequestErrorDefinition.CODE_003);
            }

            ByteArrayOutputStream out = dynamicFormService.getPdfAsStream(serviceRequestId);

            HttpHeaders header = new HttpHeaders();
            String fileName = String.format(PDF_FILE_NAME_FORMAT, serviceRequestId);
            header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName);

            ByteArrayResource resource = new ByteArrayResource(out.toByteArray());

            return ResponseEntity.ok().headers(header).body(resource);
        } catch (RecordNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Get dynamic form for a catalog")
    @GetMapping("/{catalogElementId}")
    public FormResponse getFormByCatalog(
            @RequestParam(required = false) final Long pnlId,
            @RequestParam(required = false) final Long personId,
            @RequestParam(required = false) final Long serviceRequestId,
            @RequestParam(required = false) final String keyData,
            @PathVariable final long catalogElementId) {
        boolean authenticationLevelPublic = catalogElementService.checkIsAuthenticationLevelPublic(catalogElementId);

        if (authenticationLevelPublic) {
            throw new FscRuntimeException(HttpStatus.FORBIDDEN, ServiceRequestErrorDefinition.CODE_003);
        }

        return dynamicFormService.getFormByCatalogElement(pnlId, personId, catalogElementId, serviceRequestId, keyData);
    }

    @Operation(
            summary = "Generate keyData for particular service request.",
            description = "Generate keyData based on mandatory service request id and allowed operation variables. "
                    + "There are optional parameters pnl and document type ids.",
            parameters = {
                @Parameter(name = "serviceRequestId", in = ParameterIn.PATH, required = true,
                        description = "The service request id for which key date should be generated"),
                @Parameter(name = "allowedOperation", in = ParameterIn.QUERY, required = true,
                        description = "The allowed operation used to determine the operation type")
            },
            responses = {
                @ApiResponse(responseCode = "200", description = "Standard response for successful HTTP requests"),
                @ApiResponse(responseCode = "400", description = "BAD REQUEST - incorrect service request id"),
                @ApiResponse(responseCode = "404", description = "NOT FOUND - the requested resource could not be found",
                        content = @Content(schema = @Schema(
                                name = "ErrorDefinition", type = "object", implementation = ErrorDefinition.class)))
            })
    @GetMapping("/service-request/{serviceRequestId}/key-data")
    public KeyDataObjectResponse getKeyData(@PathVariable final Long serviceRequestId, @RequestParam final String allowedOperation) {
        // Generate key
        String keyData = externalDynamicFormsApiService.generateKeyData();
        // Get logged user
        User user = (User) currentAuthentication.getUser();
        // Get service request
        ServiceRequest serviceRequest = serviceRequestService.getByIdAndSubmitter(serviceRequestId, user.getId());

        Long catalogElementId = dynamicFormService.submitFormDataForCorrection(keyData, serviceRequest, allowedOperation);

        KeyDataObjectResponse dataObjectResponse = new KeyDataObjectResponse();
        dataObjectResponse.setKeyData(keyData);
        dataObjectResponse.setCatalogElementId(catalogElementId);

        return dataObjectResponse;
    }

    @Operation(summary = "Get dynamic form for a catalog by anonymous user")
    @GetMapping("/anonymous/{catalogElementId}")
    public FormResponse getFormByCatalogAnonymous(@PathVariable final long catalogElementId) {
        return dynamicFormService.getFormByCatalogElementAnonymous(catalogElementId);
    }

    @Operation(summary = "Get dynamic form for a document type")
    @GetMapping("/document-type/{documentTypeId}")
    public FormResponse getFormByDocumentType(@RequestParam(required = false) final Long pnlId,
                                              @RequestParam(required = false) final Long personId,
                                              @PathVariable final long documentTypeId) {
        return dynamicFormService.getFormByDocumentType(pnlId, personId, documentTypeId);
    }

    @Deprecated
    @Operation(summary = "Get populated service request and attached files for preview")
    @GetMapping("/form")
    public ResponseEntity<FormResponse> getRequestCheckPageDepricated(@RequestParam final Long serviceRequestId) {

        FormResponse response = dynamicFormService.getPopulatedForm(serviceRequestId, DynamicFormsParamFormat.REACT.getId());

        return ResponseEntity.ok(response);
    }

    @Deprecated
    @Operation(summary = "Produces PDF with all input data and attached files")
    @GetMapping(
            value = "/{serviceRequestId}/pdf",
            produces = MediaType.APPLICATION_OCTET_STREAM_VALUE
    )
    public ResponseEntity<Resource> getPdfDepricated(@PathVariable final Long serviceRequestId) {

        ByteArrayOutputStream out = dynamicFormService.getPdfAsStream(serviceRequestId);

        HttpHeaders header = new HttpHeaders();
        String fileName = String.format(PDF_FILE_NAME_FORMAT, serviceRequestId);
        header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName);

        ByteArrayResource resource = new ByteArrayResource(out.toByteArray());

        return ResponseEntity.ok().headers(header).body(resource);
    }

    @Deprecated
    @Operation(summary = "Get dynamic form for a catalog")
    @GetMapping("/form/{catalogId}")
    public ResponseEntity<FormResponse> getFormDepricated(
            @RequestParam(required = false) final Long pnlId,
            @RequestParam(required = false) final Long personId,
            @RequestParam(required = false) final Long serviceRequestId,
            @PathVariable final long catalogId) {

        FormResponse formResponse = dynamicFormService.getFormByCatalogElement(pnlId, personId, catalogId, serviceRequestId, null);

        return ResponseEntity.ok(formResponse);
    }

    @Deprecated
    @Operation(summary = "Finish service request")
    @PostMapping("/service-request/{serviceRequestId}/submit")
    public ResponseEntity<String> finishServiceRequestDepricated(
            @PathVariable final Long serviceRequestId,
            @RequestBody final ServiceRequestDocumentRequest serviceRequestDocumentRequest) {

        String serviceRequestNumber = serviceRequestService.submitServiceRequest(serviceRequestId,
                serviceRequestDocumentRequest.getDocumentDescription(),
                FileRef.fromString(serviceRequestDocumentRequest.getFileReferenceStr()));

        return ResponseEntity.ok(serviceRequestNumber);
    }

    @Deprecated
    @Operation(summary = "Create new service request for a given catalog")
    @PostMapping("/form/{catalogId}")
    public ResponseEntity<SubmitFormResponse> submitFormDepricated(
            @PathVariable final long catalogId,
            @RequestParam(required = false) final Long pnlId,
            @RequestParam(required = false) final Long personId,
            @RequestParam(required = false) final Long serviceRequestId,
            @RequestBody Map<String, Object> submitData) {

        User user = (User) currentAuthentication.getUser();

        Optional<ServiceRequest> draftInProgress = Optional.empty();

        if (Objects.nonNull(serviceRequestId)) {
            draftInProgress = serviceRequestService.getUnfinishedServiceRequest(serviceRequestId, user);
        }

        SubmitFormResponse response;
        if (draftInProgress.isEmpty()) {

            ServiceRequestRequest serviceRequestRequest = new ServiceRequestRequest();
            serviceRequestRequest.setServiceRequestId(serviceRequestId);
            serviceRequestRequest.setPersonId(personId);
            serviceRequestRequest.setPnlId(pnlId);
            serviceRequestRequest.setCatalogElementId(catalogId);
            serviceRequestRequest.setSubmitData(submitData);

            ServiceRequestCreator serviceRequestCreator = serviceRequestCreatorFactory.getServiceRequestCreator(
                    ServiceRequestCreator.DEFAULT_CREATOR);

            response = serviceRequestCreator.create(serviceRequestRequest);
        } else {
            response = serviceRequestService.updateServiceRequest(draftInProgress.get(), submitData);
        }

        if (Objects.nonNull(response.getErrors())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Deprecated
    @Operation(summary = "Map file refs to service request object")
    @PostMapping("/service-request/{serviceRequestId}/submit-file")
    public ResponseEntity<ServiceRequestDocumentResponse> submitFileDepricated(
            @PathVariable final Long serviceRequestId,
            @RequestBody final ServiceRequestDocumentRequest serviceRequestDocumentRequest) {
        ServiceRequestDocumentResponse response =
                serviceRequestService.attachServiceRequestDocument(serviceRequestId, serviceRequestDocumentRequest);

        return ResponseEntity.ok(response);
    }

    @Deprecated
    @Operation(summary = "Delete document from service request object")
    @DeleteMapping("/document/{documentId}")
    public ResponseEntity<HttpStatus> deleteDocumentDepricated(@PathVariable final Long documentId) {
        serviceRequestService.deleteDocument(documentId);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Deprecated
    @Operation(summary = "Get already started (DRAFT) service request id")
    @GetMapping("/service-request/catalog-element/{catalogElementId}")
    public ResponseEntity<Long> getStartedServiceRequestIdDepricated(
            @PathVariable final Long catalogElementId,
            @RequestParam(required = false) final Long pnlId,
            @RequestParam final Long personId) {
        Long serviceRequestId = serviceRequestService.getStartedServiceRequest(catalogElementId, pnlId, personId);
        return ResponseEntity.ok(serviceRequestId);
    }

    @Deprecated
    @Operation(summary = "Get service request documents")
    @GetMapping("/service-request/{serviceRequestId}/documents")
    public ResponseEntity<Set<ServiceRequestDocumentResponse>> getServiceRequestDocumentsDepricated(
            @PathVariable final Long serviceRequestId) {
        Set<ServiceRequestDocumentResponse> serviceRequestDocuments = serviceRequestService.getServiceRequestDocuments(serviceRequestId);
        return ResponseEntity.ok(serviceRequestDocuments);
    }

    @Deprecated
    @Operation(summary = "Delete service request with status DRAFT")
    @DeleteMapping("/service-request/{serviceRequestId}")
    public ResponseEntity<HttpStatus> deleteServiceRequestDepricated(@PathVariable final Long serviceRequestId) {
        serviceRequestService.delete(serviceRequestId);

        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
