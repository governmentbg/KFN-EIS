package com.fsc.external.portal.controllers;

import com.fsc.common.addon.entity.catalog.service.ServiceRequestDocument;
import com.fsc.common.addon.validation.ValidatorErrorMessages;
import com.fsc.external.portal.dtos.ServiceRequestRequest;
import com.fsc.external.portal.dtos.SubmitFormResponse;
import com.fsc.external.portal.services.DynamicFormService;
import com.fsc.external.portal.services.FileService;
import com.fsc.external.portal.services.servicerequest.ServiceRequestCreator;
import com.fsc.external.portal.services.servicerequest.ServiceRequestCreatorFactory;
import com.fsc.external.portal.services.servicerequest.ServiceRequestService;
import io.jmix.core.FileRef;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/rest/integration-service")
@Tag(name = "Integration Service API")
public class IntegrationServiceController {

    private final ServiceRequestCreatorFactory serviceRequestCreatorFactory;
    private final FileService fileService;
    private final ServiceRequestService serviceRequestService;
    private final DynamicFormService dynamicFormService;

    public IntegrationServiceController(
            ServiceRequestCreatorFactory serviceRequestCreatorFactory,
            FileService fileService,
            ServiceRequestService serviceRequestService,
            DynamicFormService dynamicFormService) {
        this.serviceRequestCreatorFactory = serviceRequestCreatorFactory;
        this.fileService = fileService;
        this.serviceRequestService = serviceRequestService;
        this.dynamicFormService = dynamicFormService;
    }

    @Operation(summary = "Create new service request for a given catalog")
    @PostMapping("/service-request")
    public ResponseEntity<SubmitFormResponse> createServiceRequest(
            @Valid @RequestBody ServiceRequestRequest serviceRequestRequest) {

        ServiceRequestCreator serviceRequestCreator = serviceRequestCreatorFactory.getServiceRequestCreator(
                ServiceRequestCreator.INTEGRATION_CREATOR
        );

        SubmitFormResponse response = serviceRequestCreator.create(serviceRequestRequest);

        if (Objects.nonNull(response.getErrors())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(summary = "Add file to a given service request")
    @PostMapping(
            path = "/service-request/{serviceRequestId}/file",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Transactional
    public ResponseEntity<Object> addFileToServiceRequest(
            @PathVariable(value = "serviceRequestId") final Long serviceRequestId,
            @RequestParam("file") final MultipartFile file,
            @RequestParam(value = "documentTypeId") Long documentTypeId,
            @RequestParam(required = false) final String fileName) {

        Long pnlId = getPnlIdBySystemUser(); // TODO replace when system users are ready

        // validate file
        List<ValidatorErrorMessages> errors = fileService.validateFile(file, null, pnlId, documentTypeId);

        if (CollectionUtils.isNotEmpty(errors)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
        }

        // upload file to storage
        FileRef fileRef;
        try {
            fileRef = fileService.multipartFileUpload(file, fileName);
        } catch (Exception e) {
            throw new RuntimeException(e);  // TODO add specific exception
        }

        // add file to ServiceRequest
        ServiceRequestDocument serviceRequestDocument = serviceRequestService.attachServiceRequestDocument(
                serviceRequestId, documentTypeId, fileRef);

        return ResponseEntity.ok().body(serviceRequestDocument.getId());
    }

    @Operation(summary = "Finish service request")
    @PutMapping("/service-request/{serviceRequestId}")
    @Transactional
    public ResponseEntity<String> finishServiceRequest(
            @PathVariable(value = "serviceRequestId") final Long serviceRequestId) {

        ByteArrayOutputStream baos = dynamicFormService.getPdfAsStream(serviceRequestId);

        FileRef fileRef = fileService.uploadFile(baos, "PDF.pdf"); // TODO change the file name

        String inboundNumber = serviceRequestService.submitServiceRequest(
                serviceRequestId,
                "Main document as PDF",  // TODO change the main document description
                fileRef
        );

        return ResponseEntity.status(HttpStatus.OK).body(inboundNumber);
    }

    private Long getPnlIdBySystemUser() {
        return 1L; // TODO implement when system users are ready
    }
}