package com.fsc.external.portal.controllers;

import com.fsc.common.addon.entity.catalog.service.CatalogElementText;
import com.fsc.common.addon.entity.enums.CatalogStatus;
import com.fsc.exception.library.exception.enums.ErrorDefinition;
import com.fsc.external.portal.dtos.catalog.CatalogElementIdsResponse;
import com.fsc.external.portal.dtos.catalog.CatalogElementTextResponse;
import com.fsc.external.portal.mappers.CatalogElementTextMapper;
import com.fsc.external.portal.services.CatalogElementService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rest/entities")
@Tag(name = "Catalog API", description = "Catalog API used for generating the catalog services on the FE side")
public class CatalogElementController {

    private final CatalogElementService catalogElementService;
    private final CatalogElementTextMapper catalogElementTextMapper;

    public CatalogElementController(CatalogElementService catalogElementService, CatalogElementTextMapper catalogElementTextMapper) {
        this.catalogElementService = catalogElementService;
        this.catalogElementTextMapper = catalogElementTextMapper;
    }

    @Operation(
            summary = "Select the catalog of services based on locale",
            description = "Select the catalog of services based on locale. The result contains only "
                    + "active and marked as visible on external portal elements.",
            parameters = {
                @Parameter(name = "locale", in = ParameterIn.PATH, required = true,
                        description = "The locale for which language we want to select the catalog services")
            },
            responses = {
                @ApiResponse(responseCode = "200", description = "Standard response for successful HTTP requests"),
                @ApiResponse(responseCode = "400", description = "BAD REQUEST - locale not in correct format"),
                @ApiResponse(responseCode = "404", content = @Content(schema = @Schema(
                        name = "ErrorDefinition", type = "object", implementation = ErrorDefinition.class)))
            })
    @GetMapping("/catalog/locale/{locale}")
    public List<CatalogElementTextResponse> getCatalogElements(@PathVariable final String locale) {
        // Get only active and marked as visible on external portal elements.
        List<CatalogElementText> catalog = catalogElementService.getCatalogElementTree(locale).stream()
                .filter(element -> (CatalogStatus.ACTIVE.equals(element.getCatalogElement().getCatalogStatus())
                        && BooleanUtils.isTrue(element.getCatalogElement().getVisibleOnExternalPortal())))
                .collect(Collectors.toList());

        return catalogElementTextMapper.toResponse(catalog);
    }

    @Operation(
            summary = "Select the service based on catalog element id and locale",
            description = "Select the service based on catalog element id and locale. The result contains "
                    + "the name, description and catalog element response with status, types, etc.",
            parameters = {
                @Parameter(name = "locale", in = ParameterIn.PATH, required = true,
                        description = "The locale for which language we want to select the catalog services"),
                @Parameter(name = "catalogElementId", in = ParameterIn.PATH, required = true,
                        description = "The catalog element id used to fetch the service")
            },
            responses = {
                @ApiResponse(responseCode = "200", description = "Standard response for successful HTTP requests"),
                @ApiResponse(responseCode = "400", description = "BAD REQUEST - catalogElementId not in correct format"),
                @ApiResponse(responseCode = "404", description = "NOT FOUND - The requested resource could not be found",
                        content = @Content(schema = @Schema(
                                name = "ErrorDefinition", type = "object", implementation = ErrorDefinition.class)))
            })
    @GetMapping("/catalog/{catalogElementId}/locale/{locale}")
    public CatalogElementTextResponse getCatalogElements(@PathVariable final String locale, @PathVariable final Long catalogElementId) {
        CatalogElementText catalogElement = catalogElementService.getCatalogElement(catalogElementId, locale);

        return catalogElementTextMapper.toResponse(catalogElement);
    }

    @Operation(
            summary = "Select the catalog of services based on current context",
            description = "Select the catalog of services based on the current context and locale. The result contains "
                    + "only active and marked as visible on external portal elements, that the current user can access",
            parameters = {
                @Parameter(name = "locale", in = ParameterIn.QUERY, required = true,
                        description = "The locale for which language we want to select the catalog services"),
                @Parameter(name = "pnlId", in = ParameterIn.QUERY, required = false,
                        description = "The id of the selected pnl from the context"),
                @Parameter(name = "personId", in = ParameterIn.QUERY, required = false,
                        description = "The id of the selected person from the context")
            },
            responses = {
                @ApiResponse(responseCode = "200", description = "Standard response for successful HTTP requests"),
                @ApiResponse(responseCode = "400", description = "BAD REQUEST - locale not in correct format"),
                @ApiResponse(responseCode = "404", content = @Content(schema = @Schema(
                        name = "ErrorDefinition", type = "object", implementation = ErrorDefinition.class)))
            })
    @GetMapping("/catalog")
    public List<CatalogElementTextResponse> getCatalogElementTextByContext(
            @RequestParam(required = false) final Long pnlId,
            @RequestParam final Long personId,
            @RequestParam final String locale) {

        Set<CatalogElementText> catalogs = catalogElementService.getPermittedService(locale, pnlId, personId);

        return catalogElementTextMapper.toResponse(catalogs);
    }

    @Operation(
            summary = "Select the catalog id based on context",
            description = "Select the ids of the catalog of services based on the current context. The result contains "
                                  + "only active and marked as visible on external portal elements, that the current user can access",
            parameters = {
                @Parameter(name = "pnlId", in = ParameterIn.QUERY, required = false,
                            description = "The id of the selected pnl from the context"),
                @Parameter(name = "personId", in = ParameterIn.QUERY, required = false,
                            description = "The id of the selected person from the context")
            },
            responses = {
                @ApiResponse(responseCode = "200", description = "Standard response for successful HTTP requests"),
                @ApiResponse(responseCode = "400", description = "BAD REQUEST - locale not in correct format"),
                @ApiResponse(responseCode = "404", content = @Content(schema = @Schema(
                            name = "ErrorDefinition", type = "object", implementation = ErrorDefinition.class)))
            })
    @GetMapping("/catalog-id")
    public List<CatalogElementIdsResponse> getCatalogElementTextByPersonAndPnlId(
            @RequestParam(required = false) final Long pnlId,
            @RequestParam final Long personId) {

        List<Long> catalogIds = catalogElementService.getPermittedServiceIdsWithoutParentIds(pnlId, personId);

        return catalogElementTextMapper.toCatalogElementIdsResponseList(catalogIds);
    }
}