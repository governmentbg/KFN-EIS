package com.fsc.external.portal.controllers;

import com.fsc.common.addon.entity.catalog.service.ServiceCatalogElementDocumentTypes;
import com.fsc.common.addon.entity.catalog.service.ServiceRequest;
import com.fsc.common.addon.entity.catalog.service.ServiceRequestDocument;
import com.fsc.common.addon.entity.enums.ServiceRequestStatus;
import com.fsc.common.addon.service.CorrespondenceService;
import com.fsc.common.addon.service.document.type.DocumentTypeInCorrespondenceService;
import com.fsc.exception.library.exception.FscRuntimeException;
import com.fsc.exception.library.exception.RecordNotFoundException;
import com.fsc.exception.library.exception.enums.ErrorDefinition;
import com.fsc.external.portal.dtos.DocumentTypeByCatalogResponse;
import com.fsc.external.portal.dtos.ServiceRequestDocumentRequest;
import com.fsc.external.portal.dtos.ServiceRequestDocumentResponse;
import com.fsc.external.portal.dtos.ServiceRequestRequest;
import com.fsc.external.portal.dtos.SubmitFormResponse;
import com.fsc.external.portal.dtos.catalog.FormResponse;
import com.fsc.external.portal.error.ServiceRequestErrorDefinition;
import com.fsc.external.portal.mappers.DocumentTypeMapper;
import com.fsc.external.portal.services.CatalogElementService;
import com.fsc.external.portal.services.DynamicFormService;
import com.fsc.external.portal.services.ExternalDynamicFormCommunicationService;
import com.fsc.external.portal.services.FileService;
import com.fsc.external.portal.services.servicerequest.ServiceRequestCreator;
import com.fsc.external.portal.services.servicerequest.ServiceRequestCreatorFactory;
import com.fsc.external.portal.services.servicerequest.ServiceRequestService;
import io.jmix.core.DataManager;
import io.jmix.core.FetchPlan;
import io.jmix.core.FileInfoResponse;
import io.jmix.core.FileRef;
import io.jmix.core.querycondition.LogicalCondition;
import io.jmix.core.querycondition.PropertyCondition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Objects;

@Validated
@RestController
@RequestMapping("/rest/public")
public class PublicServicesController {

    private static final String PDF_FILE_NAME_FORMAT = "service_request_%s.pdf";
    private static final String FORMAT_REACT = "react";
    private static final String MINIO_FILE_STORAGE_DOCUMENT_TEMPLATES_PACKAGE_NAME = "documentTemplates";

    private final ServiceRequestService serviceRequestService;
    private final DynamicFormService dynamicFormService;
    private final DocumentTypeInCorrespondenceService documentTypeInCorrespondenceService;
    private final DocumentTypeMapper documentTypeMapper;
    private final ServiceRequestCreatorFactory serviceRequestCreatorFactory;
    private final FileService fileService;
    private final ExternalDynamicFormCommunicationService externalDynamicFormCommunicationService;
    private final DataManager dataManager;
    private final CatalogElementService catalogElementService;
    private final CorrespondenceService correspondenceService;

    public PublicServicesController(ServiceRequestService serviceRequestService,
                                    DynamicFormService dynamicFormService,
                                    DocumentTypeInCorrespondenceService documentTypeInCorrespondenceService,
                                    DocumentTypeMapper documentTypeMapper,
                                    ServiceRequestCreatorFactory serviceRequestCreatorFactory,
                                    FileService fileService,
                                    ExternalDynamicFormCommunicationService externalDynamicFormCommunicationService,
                                    DataManager dataManager,
                                    CatalogElementService catalogElementService,
                                    CorrespondenceService correspondenceService) {
        this.serviceRequestService = serviceRequestService;
        this.dynamicFormService = dynamicFormService;
        this.documentTypeInCorrespondenceService = documentTypeInCorrespondenceService;
        this.documentTypeMapper = documentTypeMapper;
        this.serviceRequestCreatorFactory = serviceRequestCreatorFactory;
        this.fileService = fileService;
        this.externalDynamicFormCommunicationService = externalDynamicFormCommunicationService;
        this.dataManager = dataManager;
        this.catalogElementService = catalogElementService;
        this.correspondenceService = correspondenceService;
    }

    @Operation(summary = "Get already started (DRAFT) service request id by anonymous user")
    @GetMapping("/service-request/catalog-element/{catalogElementId}")
    public ResponseEntity<Long> getStartedServiceRequestId(
            @PathVariable final Long catalogElementId) {

        boolean authenticationLevelPublic = catalogElementService.checkIsAuthenticationLevelPublic(catalogElementId);

        if (!authenticationLevelPublic) {
            throw new FscRuntimeException(HttpStatus.FORBIDDEN, ServiceRequestErrorDefinition.CODE_003);
        }

        Long serviceRequestId = serviceRequestService.getStartedServiceRequest(catalogElementId, null, null);

        return ResponseEntity.ok(serviceRequestId);
    }

    @Operation(summary = "Get dynamic form for a catalog by anonymous user")
    @GetMapping("/dynamic-forms/{catalogElementId}")
    public ResponseEntity<FormResponse> getFormByCatalog(@PathVariable final long catalogElementId) {

        boolean authenticationLevelPublic = catalogElementService.checkIsAuthenticationLevelPublic(catalogElementId);
        boolean documentTypeNull = catalogElementService.checkIsDocumentTypeNull(catalogElementId);
        boolean serviceTypePermitted = catalogElementService.checkIsServiceTypePermitted(catalogElementId);

        if (!authenticationLevelPublic || documentTypeNull || !serviceTypePermitted) {
            throw new FscRuntimeException(HttpStatus.FORBIDDEN, ServiceRequestErrorDefinition.CODE_003);
        }

        FormResponse formResponse = dynamicFormService.getFormByCatalogElementAnonymous(catalogElementId);

        return ResponseEntity.ok(formResponse);
    }

    @Operation(summary = "Select document types that are connected to a specific catalog element by anonymous user")
    @GetMapping("/entities/catalog-element/{catalogElementId}/document-types")
    public List<DocumentTypeByCatalogResponse> getDocumentTypesByCatalogElement(
            @PathVariable(value = "catalogElementId") final Long catalogElementId) {

        boolean authenticationLevelPublic = catalogElementService.checkIsAuthenticationLevelPublicWithSpecDocs(catalogElementId);
        boolean documentTypeNull = catalogElementService.checkIsDocumentTypeNull(catalogElementId);
        boolean serviceTypePermitted = catalogElementService.checkIsServiceTypePermitted(catalogElementId);

        if (!authenticationLevelPublic || documentTypeNull || !serviceTypePermitted) {
            throw new FscRuntimeException(HttpStatus.FORBIDDEN, ServiceRequestErrorDefinition.CODE_003);
        }

        final List<ServiceCatalogElementDocumentTypes> documentTypesCatalogElements = documentTypeInCorrespondenceService
                .getDocumentTypesByCatalogElementId(catalogElementId);

        return documentTypeMapper.toServiceCatalogElementDocumentTypes(documentTypesCatalogElements);
    }

    @Operation(summary = "Create new service request for a given catalog by anonymous user")
    @PostMapping(value = "/service-request")
    public ResponseEntity<SubmitFormResponse> submitForm(
            @Valid @RequestBody ServiceRequestRequest serviceRequestRequest) {

        boolean authenticationLevelPublic = catalogElementService.checkIsAuthenticationLevelPublic(
                serviceRequestRequest.getCatalogElementId());

        boolean documentTypeNull = catalogElementService.checkIsDocumentTypeNull(
                serviceRequestRequest.getCatalogElementId());

        boolean serviceTypePermitted = catalogElementService.checkIsServiceTypePermitted(
                serviceRequestRequest.getCatalogElementId());

        if (!authenticationLevelPublic || documentTypeNull || !serviceTypePermitted) {
            throw new FscRuntimeException(HttpStatus.FORBIDDEN, ServiceRequestErrorDefinition.CODE_003);
        }

        ServiceRequestCreator serviceRequestCreator = serviceRequestCreatorFactory.getServiceRequestCreator(
                ServiceRequestCreator.ANONYMOUS_CREATOR);

        SubmitFormResponse response = serviceRequestCreator.create(serviceRequestRequest);

        if (Objects.nonNull(response.getErrors())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(summary = "Map file refs to service request object by anonymous user")
    @PostMapping("/service-request/{serviceRequestId}/document")
    public ResponseEntity<ServiceRequestDocumentResponse> submitFile(
            @PathVariable final Long serviceRequestId,
            @Valid @RequestBody final ServiceRequestDocumentRequest serviceRequestDocumentRequest) {

        try {
            Long catalogElementId = serviceRequestService.getCatalogElementIdFromServiceRequest(serviceRequestId);

            if (!catalogElementService.checkIsAuthenticationLevelPublic(catalogElementId)) {
                throw new FscRuntimeException(HttpStatus.FORBIDDEN, ServiceRequestErrorDefinition.CODE_003);
            }

            ServiceRequestDocumentResponse response =
                    serviceRequestService.attachServiceRequestDocumentByAnonymousUser(serviceRequestId,
                                                                                      serviceRequestDocumentRequest);

            return ResponseEntity.ok(response);

        } catch (RecordNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Uploads a file to the file storage by anonymous user")
    @PostMapping(value = "/files",
                 consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
                 produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FileInfoResponse> uploadFile(@RequestParam("file") final MultipartFile file,
                                                       @RequestParam(required = false) final String name,
                                                       @RequestParam(required = false) final String storageName,
                                                       final HttpServletRequest request) {
        return fileService.multipartFileUpload(file, name, storageName, request);
    }

    @Operation(summary = "Get populated service request and attached files for preview by anonymous user")
    @GetMapping("/dynamic-forms/service-request/{serviceRequestId}")
    public ResponseEntity<FormResponse> getRequestCheckPage(@PathVariable final Long serviceRequestId) {
        Long catalogElementId = serviceRequestService.getCatalogElementIdFromServiceRequest(serviceRequestId);
        boolean authenticationLevelPublic = catalogElementService.checkIsAuthenticationLevelPublic(catalogElementId);

        if (!authenticationLevelPublic) {
            throw new FscRuntimeException(HttpStatus.FORBIDDEN, ServiceRequestErrorDefinition.CODE_003);
        }

        FormResponse response = dynamicFormService.getPopulatedForm(serviceRequestId, FORMAT_REACT);

        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Produces PDF with all input data and attached files by anonymous user")
    @GetMapping(value = "/dynamic-forms/{serviceRequestId}/format/pdf",
                produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<Resource> getPdf(@PathVariable final Long serviceRequestId) {

        Long catalogElementId = serviceRequestService.getCatalogElementIdFromServiceRequest(serviceRequestId);
        boolean authenticationLevelPublic = catalogElementService.checkIsAuthenticationLevelPublic(catalogElementId);

        if (!authenticationLevelPublic) {
            throw new FscRuntimeException(HttpStatus.FORBIDDEN, ServiceRequestErrorDefinition.CODE_003);
        }

        ByteArrayOutputStream out = dynamicFormService.getPdfAsStream(serviceRequestId);

        HttpHeaders header = new HttpHeaders();
        String fileName = String.format(PDF_FILE_NAME_FORMAT, serviceRequestId);
        header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName);

        ByteArrayResource resource = new ByteArrayResource(out.toByteArray());

        return ResponseEntity.ok().headers(header).body(resource);
    }

    @Operation(summary = "Delete this when the time is right or left or whatever used by anonymous user")
    @GetMapping("/nomenclature/{type}")
    public Object getNumenclatureFromPhPByName(
            @PathVariable final String type,
            @RequestParam final String search) {
        return externalDynamicFormCommunicationService.getNumenclatureFromPhPByName(type, search, null);
    }

    @Operation(summary = "Delete document from service request object for anonymous user")
    @DeleteMapping("/service-request/{serviceRequestId}/documents/{documentId}")
    public HttpStatus deleteDocument(@PathVariable final Long serviceRequestId,
                                     @PathVariable final Long documentId) {
        Long catalogElementId = serviceRequestService.getCatalogElementIdFromServiceRequest(serviceRequestId);
        boolean authenticationLevelPublic = catalogElementService.checkIsAuthenticationLevelPublic(catalogElementId);

        if (!authenticationLevelPublic) {
            throw new FscRuntimeException(HttpStatus.FORBIDDEN, ServiceRequestErrorDefinition.CODE_003);
        }

        serviceRequestService.deleteDocument(serviceRequestId, documentId);

        return HttpStatus.OK;
    }

    @Operation(summary = "Finish service request by anonymous user")
    @PutMapping("/service-request/{serviceRequestId}/status/submitted")
    public ResponseEntity<String> finishServiceRequest(
            @PathVariable final Long serviceRequestId,
            @Valid @RequestBody final ServiceRequestDocumentRequest serviceRequestDocumentRequest) {
        try {
            Long catalogElementId = serviceRequestService.getCatalogElementIdFromServiceRequest(serviceRequestId);

            if (!catalogElementService.checkIsAuthenticationLevelPublic(catalogElementId)) {
                throw new FscRuntimeException(HttpStatus.FORBIDDEN, ServiceRequestErrorDefinition.CODE_003);
            }

            LogicalCondition logicalCondition = LogicalCondition.and();
            logicalCondition.add(PropertyCondition.equal("id", serviceRequestId));

            ServiceRequest serviceRequest = dataManager
                    .load(ServiceRequest.class)
                    .condition(logicalCondition)
                    .fetchPlan(FetchPlan.BASE)
                    .optional().orElseThrow(() -> new RecordNotFoundException(ErrorDefinition.RECORD_NOT_FOUND));

            if (!ServiceRequestStatus.DRAFT.equals(serviceRequest.getStatus())) {
                throw new FscRuntimeException(HttpStatus.FORBIDDEN, ServiceRequestErrorDefinition.CODE_002);
            }

            serviceRequestService.submitServiceRequest(serviceRequest, serviceRequestDocumentRequest);

            serviceRequestService.processServiceRequest(serviceRequestId);

            return ResponseEntity.ok(correspondenceService.getCorrespondenceNumber(serviceRequestId));
        } catch (RecordNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Download a file by anonymous user")
    @GetMapping(value = "/download/{documentId}")
    public void downloadAndWriteResponse(@PathVariable final Long documentId, HttpServletResponse httpServletResponse) {
        ServiceRequestDocument serviceRequestDocument =  serviceRequestService.getRequestServiceDocumentById(documentId);

        fileService.downloadFile(serviceRequestDocument.getFileReference(), httpServletResponse);
    }

    @Operation(
            summary = "Download file by document file ref",
            description = "Public endpoint for downloading a file by passed file reference",
            parameters = {
                @Parameter(name = "fileRefStr", description = "String value of document file ref", required = true)
            },
            responses = {
                @ApiResponse(responseCode = "200", description = "SUCCESS - Downloads document"),
                @ApiResponse(responseCode = "400", description = "BAD REQUEST - File ref passed not in correct format"),
                @ApiResponse(responseCode = "403", description = "FORBIDDEN - Required document is not public"),
                @ApiResponse(responseCode = "404", description = "NOT FOUND - Document could not be found for this file ref",
                            content = @Content(schema = @Schema(name = "ErrorDefinition",
                                    type = "object", implementation = ErrorDefinition.class)))
            })
    @GetMapping(value = "/specific-document/download")
    public void downloadFile(@RequestParam final String fileRefStr, HttpServletResponse httpServletResponse) {
        FileRef fileRef = FileRef.fromString(fileRefStr);

        //When document is stored in documentTemplates package it is public.
        if (!fileRef.getPath().startsWith(MINIO_FILE_STORAGE_DOCUMENT_TEMPLATES_PACKAGE_NAME)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }

        fileService.downloadFile(fileRef, httpServletResponse);
    }
}
