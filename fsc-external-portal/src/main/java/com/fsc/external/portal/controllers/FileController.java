package com.fsc.external.portal.controllers;

import com.fsc.common.addon.validation.FileValidationType;
import com.fsc.common.addon.validation.ValidatorErrorMessages;
import com.fsc.external.portal.services.FileService;
import io.jmix.core.FileInfoResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/rest/files")
@Tag(name = "File API")
public class FileController {

    private final FileService fileService;

    public FileController(final FileService fileService) {
        this.fileService = fileService;
    }

    @Operation(summary = "Uploads a file to the file storage")
    @PostMapping(
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FileInfoResponse> uploadFile(@RequestParam("file") final MultipartFile file,
                                                       @RequestParam(required = false) final String name,
                                                       @RequestParam(required = false) final String storageName,
                                                       final HttpServletRequest request) {
        return fileService.multipartFileUpload(file, name, storageName, request);
    }

    @Operation(summary = "Checks if the file is valid for upload.")
    @PostMapping(value = "/validateFile",
                 consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
                 produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ValidatorErrorMessages> validateFile(
            @RequestParam("file") final MultipartFile file,
            @RequestParam(value = "fileType", required = false) FileValidationType fileType,
            @RequestParam(value = "pnlId", required = false) Long pnlId,
            @RequestParam(value = "documentTypeId", required = false) Long documentTypeId,
            @RequestParam(value = "personId", required = false) Long personId) { // TODO for future use
        return fileService.validateFile(file, fileType, pnlId, documentTypeId);
    }

    @Operation(summary = "Removes a file from the file storage")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Filed removed successfully", content = @Content),
        @ApiResponse(responseCode = "500",
                         description = "Unable to delete the file",
                         content = @Content)})
    @DeleteMapping
    public ResponseEntity<HttpStatus> deleteFile(@RequestParam final String fileRef) {
        fileService.remove(fileRef);
        return ResponseEntity.ok().build();
    }
}