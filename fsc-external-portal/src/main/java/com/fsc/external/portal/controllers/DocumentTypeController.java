package com.fsc.external.portal.controllers;

import com.fsc.common.addon.entity.catalog.service.ServiceCatalogElementDocumentTypes;
import com.fsc.common.addon.service.document.type.DocumentTypeInCorrespondenceService;
import com.fsc.exception.library.exception.FscRuntimeException;
import com.fsc.exception.library.exception.enums.ErrorDefinition;
import com.fsc.external.portal.dtos.DocumentTypeByCatalogResponse;
import com.fsc.external.portal.dtos.DocumentTypeResponse;
import com.fsc.external.portal.error.ServiceRequestErrorDefinition;
import com.fsc.external.portal.mappers.DocumentTypeMapper;
import com.fsc.external.portal.services.CatalogElementService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rest/entities")
@Tag(name = "Document type API")
public class DocumentTypeController {

    private static final String DOCUMENT_TYPE_BY_CATALOG_DESCRIPTION = "OK. Possible values for Mandatory: M-mandatory,"
                                                                       + " R-recommended, O-optional. Possible values for"
                                                                       + " Unique: 1-can be submitted only once,"
                                                                       + " 0-maybe more times";

    private final DocumentTypeMapper documentTypeMapper;
    private final DocumentTypeInCorrespondenceService documentTypeInCorrespondenceService;
    private final CatalogElementService catalogElementService;

    public DocumentTypeController(final DocumentTypeInCorrespondenceService documentTypeInCorrespondenceService,
                                  final DocumentTypeMapper documentTypeMapper,
                                  CatalogElementService catalogElementService) {
        this.documentTypeInCorrespondenceService = documentTypeInCorrespondenceService;
        this.documentTypeMapper = documentTypeMapper;
        this.catalogElementService = catalogElementService;
    }

    @Operation(summary = "Select document types that are connected to a specific correspondence type")
    @GetMapping("/correspondence-type/{correspondenceTypeId}/document-types")
    public List<DocumentTypeResponse> getDocumentTypes(
            @PathVariable(value = "correspondenceTypeId") final Long correspondenceTypeId) {

        return documentTypeMapper.toDocumentType(documentTypeInCorrespondenceService
                                                         .getDocumentTypesByCorrespondenceTypeId(correspondenceTypeId));
    }

    @Operation(
            summary = "Select document types that are connected to a specific catalog element",
            description = "Returns list of document types.",
            parameters = {
                @Parameter(name = "catalogElementId",
                               description = "catalog element id for which document types to be retrieved", required = true)
            },
            responses = {
                @ApiResponse(responseCode = "200", description = DOCUMENT_TYPE_BY_CATALOG_DESCRIPTION),
                @ApiResponse(responseCode = "400", description = "BAD REQUEST - catalogElementId passed not in correct format"),
                @ApiResponse(responseCode = "404", description = "NOT FOUND - The requested resource could not be found",
                                 content = @Content(schema = @Schema(name = "ErrorDefinition",
                                                                     type = "object", implementation = ErrorDefinition.class)))
            })
    @GetMapping("/catalog-element/{catalogElementId}/document-types")
    public List<DocumentTypeByCatalogResponse> getDocumentTypesByCatalogElement(
            @PathVariable(value = "catalogElementId") final Long catalogElementId) {

        boolean authenticationLevelPublic = catalogElementService.checkIsAuthenticationLevelPublic(catalogElementId);

        if (authenticationLevelPublic) {
            throw new FscRuntimeException(HttpStatus.FORBIDDEN, ServiceRequestErrorDefinition.CODE_003);
        }

        final List<ServiceCatalogElementDocumentTypes> documentTypesCatalogElements =
                documentTypeInCorrespondenceService.getDocumentTypesByCatalogElementId(catalogElementId);

        return documentTypeMapper.toServiceCatalogElementDocumentTypes(documentTypesCatalogElements);
    }
}