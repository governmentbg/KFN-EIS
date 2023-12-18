package com.fsc.external.portal.controllers;

import com.fsc.common.addon.entity.PublicRegisterSectionType;
import com.fsc.common.addon.entity.enums.PersonType;
import com.fsc.common.addon.entity.view.publicregister.PublicRegistersRelatedPersonView;
import com.fsc.common.addon.entity.view.publicregister.PublicRegistersView;
import com.fsc.exception.library.exception.RecordNotFoundException;
import com.fsc.exception.library.exception.enums.ErrorDefinition;
import com.fsc.external.portal.dtos.PageResponse;
import com.fsc.external.portal.dtos.PublicRegisterRequest;
import com.fsc.external.portal.dtos.PublicRegisterSectionResponse;
import com.fsc.external.portal.dtos.PublicRegisterSectionWrapperResponse;
import com.fsc.external.portal.dtos.person.PersonRelationTypeEnum;
import com.fsc.external.portal.dtos.person.PersonTypeDto;
import com.fsc.external.portal.dtos.pnl.PublicRegisterResponse;
import com.fsc.external.portal.dtos.publicregister.PublicRegisterInsuranceRequest;
import com.fsc.external.portal.dtos.publicregister.PublicRegisterInsuranceResponse;
import com.fsc.external.portal.dtos.publicregisterdetailedinfo.CapitalInfoResponse;
import com.fsc.external.portal.dtos.publicregisterdetailedinfo.DetailedInfoResponse;
import com.fsc.external.portal.dtos.publicregisterdetailedinfo.PnlPublicRegisterDocumentDownloadRequest;
import com.fsc.external.portal.dtos.publicregisterdetailedinfo.PublicRegisterDocumentsDto;
import com.fsc.external.portal.dtos.publicregisterdetailedinfo.PublicRegisterInsurerCompaniesDto;
import com.fsc.external.portal.dtos.publicregisterdetailedinfo.PublicRegisterLegalAndIndividualPersonDto;
import com.fsc.external.portal.dtos.publicregisterdetailedinfo.PublicRegisterManagementResponseDto;
import com.fsc.external.portal.dtos.publicregisterdetailedinfo.PublicRegisterPersonInfoDto;
import com.fsc.external.portal.dtos.publicregisterdetailedinfo.PublicRegisterRelatedPersonAndDocumentsDto;
import com.fsc.external.portal.dtos.publicregisterdetailedinfo.PublicRegisterRelatedPersonDetailDto;
import com.fsc.external.portal.dtos.publicregisterdetailedinfo.PublicRegisterRelatedPersonDto;
import com.fsc.external.portal.dtos.publicregisterdetailedinfo.PublicRegisterRelatedPnlDto;
import com.fsc.external.portal.dtos.publicregisterdetailedinfo.PublicRegisterRelatedPnlWithRegisterDto;
import com.fsc.external.portal.dtos.publicregisterdetailedinfo.PublicRegisterStatusAndDocumentDto;
import com.fsc.external.portal.dtos.publicregisterdetailedinfo.PublicRegisterStatusDto;
import com.fsc.external.portal.mappers.PublicRegisterInsuranceMapper;
import com.fsc.external.portal.mappers.PublicRegisterMapper;
import com.fsc.external.portal.mappers.PublicRegisterSectionMapper;
import com.fsc.external.portal.services.CatalogElementService;
import com.fsc.external.portal.services.FileService;
import com.fsc.external.portal.services.PnlService;
import com.fsc.external.portal.services.PublicRegisterService;
import io.jmix.core.FileRef;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Validated
@RestController
@RequestMapping("/rest/public-register")
public class PublicRegisterController {

    private static final List<Long> SUPERVISORY_LIST =
            List.of(PersonRelationTypeEnum.SUPERVISORY_CHAIRMAIN.getId(), PersonRelationTypeEnum.SUPERVISORY_CHAIRMAIN_DEPUTY.getId(),
                    PersonRelationTypeEnum.SUPERVISORY_MEMBER.getId(), PersonRelationTypeEnum.SUPERVISORY_INDEPENDENT_MEMBER.getId());
    private static final List<Long> BORD_OF_DIRECTOR_LIST =
            List.of(PersonRelationTypeEnum.BORD_DIRECTOR_CHAIRMAIN.getId(), PersonRelationTypeEnum.BORD_DIRECTOR_MEMBER.getId(),
                    PersonRelationTypeEnum.BORD_DIRECTOR_CHAIRMAIN_DEPUTY.getId());

    private final PnlService pnlService;
    private final CatalogElementService catalogElementService;
    private final PublicRegisterMapper publicRegisterMapper;
    private final PublicRegisterInsuranceMapper publicRegisterInsuranceMapper;

    private final PublicRegisterService publicRegisterService;
    private final PublicRegisterSectionMapper publicRegisterSectionMapper;
    private final FileService fileService;

    public PublicRegisterController(PnlService pnlService, CatalogElementService catalogElementService,
                                    PublicRegisterService publicRegisterService,
                                    FileService fileService,
                                    PublicRegisterMapper publicRegisterMapper,
                                    PublicRegisterSectionMapper publicRegisterSectionMapper,
                                    PublicRegisterInsuranceMapper publicRegisterInsuranceMapper) {
        this.pnlService = pnlService;
        this.catalogElementService = catalogElementService;
        this.publicRegisterService = publicRegisterService;
        this.fileService = fileService;
        this.publicRegisterMapper = publicRegisterMapper;
        this.publicRegisterInsuranceMapper = publicRegisterInsuranceMapper;
        this.publicRegisterSectionMapper = publicRegisterSectionMapper;
    }

    @Operation(
            summary = "Returns list of PNLs whose type is related to the passed catalog element id.",
            description = "Returns list of PNLs by catalogElementId, page, size and filter parameters.",
            parameters = {
                @Parameter(name = "publicRegisterRequest",
                            description = "PublicRegisterRequest containing catalogElementId, page, size and filter", required = true)
            },
            responses = {
                @ApiResponse(responseCode = "200", description = "Standard response for successful HTTP requests"),
                @ApiResponse(responseCode = "400", description = "BAD REQUEST - CatalogElementId, page or size are not in correct format"),
                @ApiResponse(responseCode = "404", description = "NOT FOUND - The requested resource could not be found",
                        content = @Content(schema = @Schema(name = "ErrorDefinition",
                                type = "object", implementation = ErrorDefinition.class))),
                @ApiResponse(responseCode = "422", description = "Unprocessable Entity",
                            content = @Content(schema = @Schema(name = "Unprocessable Entity",
                                    example = "Requested catalog element exists but is not of type public register")))
            })
    @PostMapping("/pnls")
    public PageResponse<PublicRegisterResponse> getPnlsByCatalogElementId(
            @Valid @RequestBody PublicRegisterRequest publicRegisterRequest) {
        Set<Long> pnlTypeIds = catalogElementService.getPnlTypeIdsByCatalogElementId(publicRegisterRequest.getCatalogElementId());

        Page<PublicRegistersView> pnlsByPnlTypeIds = pnlService.getPnlsByPnlTypeIds(pnlTypeIds, publicRegisterRequest);

        return publicRegisterMapper.toPageResponse((PageImpl<PublicRegistersView>) pnlsByPnlTypeIds);
    }

    @Operation(
            summary = "Returns list of PNLs whose type is related to the passed catalog element id and person type.",
            description = "Returns list of PNLs by catalogElementId, personType, page, size and filter parameters.",
            parameters = {
                @Parameter(name = "publicRegisterInsuranceRequest",
                            description = "PublicRegisterInsuranceRequest containing catalogElementId, "
                                             + "person type, page, size and filter", required = true)
            },
            responses = {
                @ApiResponse(responseCode = "200", description = "Standard response for successful HTTP requests"),
                @ApiResponse(responseCode = "400", description = "BAD REQUEST - data are not in correct format"),
                @ApiResponse(responseCode = "404", description = "NOT FOUND - The requested resource could not be found",
                    content = @Content(schema = @Schema(name = "ErrorDefinition",
                                 type = "object", implementation = ErrorDefinition.class))),
                @ApiResponse(responseCode = "422", description = "Unprocessable Entity",
                                 content = @Content(schema = @Schema(name = "Unprocessable Entity",
                                    example = "Requested catalog element exists but is not of type public register")))
            })
    @PostMapping("/pnls/insurance")
    public PageResponse<PublicRegisterInsuranceResponse> getInsurancePnlsByCatalogElementIdAndPersonType(
            @Valid @RequestBody PublicRegisterInsuranceRequest publicRegisterInsuranceRequest) {
        Set<Long> pnlTypeIds =
                catalogElementService.getPnlTypeIdsByCatalogElementId(publicRegisterInsuranceRequest.getCatalogElementId());

        Page<PublicRegistersView> pnlsByPnlTypeIds = pnlService.getPnlsByPnlTypeIds(pnlTypeIds, publicRegisterInsuranceRequest);

        return publicRegisterInsuranceMapper.toPageResponse((PageImpl<PublicRegistersView>) pnlsByPnlTypeIds);
    }

    @Operation(
            summary = "Returns detailed info about particular passed pnl Id.",
            description = "Returns detailed info about pnl and its documents, relations and klonove.",
            parameters = {
                @Parameter(name = "pnlId",
                            description = "pnl id for which detailed info to be fetched", required = true)
            },
            responses = {
                @ApiResponse(responseCode = "200", description = "Standard response for successful HTTP requests"),
                @ApiResponse(responseCode = "400", description = "BAD REQUEST - pnlId not in correct format"),
                @ApiResponse(responseCode = "404", description = "NOT FOUND - The requested resource could not be found",
                            content = @Content(schema = @Schema(name = "ErrorDefinition",
                                    type = "object", implementation = ErrorDefinition.class)))
            })
    @GetMapping("/detailed-info/{pnlId}")
    public DetailedInfoResponse getPnlDetailsByPnlId(@PathVariable(value = "pnlId") @Min(value = 1) Long pnlId) {

        final PublicRegistersView additionalInfo = publicRegisterService.getPublicRegistersView(pnlId);
        if (additionalInfo == null) {
            throw new RecordNotFoundException(ErrorDefinition.RECORD_NOT_FOUND);
        }

        return publicRegisterMapper.toResponse(additionalInfo, publicRegisterService.getMainInfo(pnlId));
    }

    @Deprecated
    @Operation(
            summary = "Returns section id and name by passed catalog element id.",
            parameters = {
                @Parameter(name = "catalogElementId",
                            description = "pnl id for which section to be fetched", required = true)
            },
            responses = {
                @ApiResponse(responseCode = "200", description = "Standard response for successful HTTP requests"),
                @ApiResponse(responseCode = "400", description = "BAD REQUEST - catalogElementId not in number format"),
                @ApiResponse(responseCode = "404",
                            description = "NOT FOUND - The requested resource could not be found or catalog element id missing",
                            content = @Content(schema = @Schema(name = "ErrorDefinition",
                                    type = "object", implementation = ErrorDefinition.class)))
            })
    @GetMapping("/section-types/{catalogElementId}")
    public PageResponse<PublicRegisterSectionResponse> getSectionTypes(
            @PathVariable(value = "catalogElementId") @Min(value = 1) Long catalogElementId,
            @RequestParam(required = false) final Integer page,
            @RequestParam(required = false) final Integer size) {

        Page<PublicRegisterSectionType> sectionDetails = publicRegisterService.getSectionTypes(catalogElementId, page, size, null);

        return publicRegisterSectionMapper.toPageResponse((PageImpl<PublicRegisterSectionType>) sectionDetails);
    }

    @Operation(
            summary = "Returns section id and name by passed catalog element id.",
            parameters = {
                @Parameter(name = "catalogElementId",
                        description = "pnl id for which section to be fetched", required = true)
            },
            responses = {
                @ApiResponse(responseCode = "200", description = "Standard response for successful HTTP requests"),
                @ApiResponse(responseCode = "400", description = "BAD REQUEST - catalogElementId not in number format"),
                @ApiResponse(responseCode = "404",
                            description = "NOT FOUND - The requested resource could not be found or catalog element id missing",
                            content = @Content(schema = @Schema(name = "ErrorDefinition",
                                    type = "object", implementation = ErrorDefinition.class)))
            })
    @GetMapping("/v2/section-types/{catalogElementId}")
    public PublicRegisterSectionWrapperResponse getSectionTypesV2(
            @PathVariable(value = "catalogElementId") @Min(value = 1) Long catalogElementId,
            @RequestParam(required = false) final Long pnlId,
            @RequestParam(required = false) final Integer page,
            @RequestParam(required = false) final Integer size) {

        Page<PublicRegisterSectionType> sectionDetails;

        PublicRegisterSectionWrapperResponse response = new PublicRegisterSectionWrapperResponse();

        if (pnlId != null) {
            PublicRegistersView publicRegistersView = publicRegisterService.getPublicRegistersView(pnlId);

            String personName = publicRegistersView != null ? publicRegistersView.getPersonName() : null;
            response.setPersonName(personName);

            PersonType personTypeEnum = publicRegistersView.getPersonType();

            String personType = publicRegistersView != null && publicRegistersView.getPersonType() != null
                    ? personTypeEnum.getId() : null;
            response.setPersonType(personType);

            sectionDetails = publicRegisterService.getSectionTypes(catalogElementId, page, size, personTypeEnum);
        } else {
            sectionDetails = publicRegisterService.getSectionTypes(catalogElementId, page, size, null);
        }

        PageResponse<PublicRegisterSectionResponse> pageResponse =
                publicRegisterSectionMapper.toPageResponse((PageImpl<PublicRegisterSectionType>) sectionDetails);
        response.setPageResponse(pageResponse);

        return response;
    }

    @Operation(
            summary = "Returns detailed info about related pnl by pnl Id.",
            description = "Returns detailed info about related pnl by pnl Id.",
            parameters = {
                @Parameter(name = "sectionId", description = "section id for which detailed info to be fetched", required = true),
                @Parameter(name = "pnlId", description = "pnl id for which detailed info to be fetched", required = true)
            },
            responses = {
                @ApiResponse(responseCode = "200", description = "Standard response for successful HTTP requests"),
                @ApiResponse(responseCode = "400", description = "BAD REQUEST - pnlId not in correct format"),
                @ApiResponse(responseCode = "404", description = "NOT FOUND - The requested resource could not be found",
                      content = @Content(schema = @Schema(name = "ErrorDefinition", type = "object", implementation =
                                                                                                             ErrorDefinition.class)))
            })
    @GetMapping("/detailed-info/related-pnl/{sectionId}/{pnlId}")
    public List<PublicRegisterRelatedPnlDto> getPnlDetailsRelatedPnlByPnlId(
            @PathVariable(value = "sectionId") @Min(value = 1) Long sectionId,
            @PathVariable(value = "pnlId") @Min(value = 1) Long pnlId) {

        return publicRegisterMapper.toRelatedPnlListDto(publicRegisterService.getRelatedPnlInfo(sectionId, pnlId));
    }

    @GetMapping("/detailed-info/related-pnl/register/section/{sectionId}/pnl/{pnlId}")
    public List<PublicRegisterRelatedPnlWithRegisterDto> getPnlDetailsRelatedPnlWithRegisterIdByPnlId(
            @PathVariable(value = "sectionId") @Min(value = 1) Long sectionId,
            @PathVariable(value = "pnlId") @Min(value = 1) Long pnlId) {

        List<PublicRegisterRelatedPnlWithRegisterDto> result =
                publicRegisterMapper.toRelatedPnlWithRegister(publicRegisterService.getRelatedPnlInfo(sectionId, pnlId));

        if (CollectionUtils.isNotEmpty(result)) {
            result.forEach(x -> publicRegisterService.setRegisterIdForPnl(x));
        }

        return result;
    }

    @Operation(
            summary = "Returns detailed info about related persons by pnl Id.",
            description = "Returns detailed info about related persons by pnl Id.",
            parameters = {
                @Parameter(name = "sectionId", description = "section id for which detailed info to be fetched", required = true),
                @Parameter(name = "pnlId", description = "pnl id for which detailed info to be fetched", required = true)
            },
            responses = {
                @ApiResponse(responseCode = "200", description = "Standard response for successful HTTP requests"),
                @ApiResponse(responseCode = "400", description = "BAD REQUEST - pnlId not in correct format"),
                @ApiResponse(responseCode = "404", description = "NOT FOUND - The requested resource could not be found",
                    content = @Content(schema = @Schema(name = "ErrorDefinition", type = "object", implementation = ErrorDefinition.class)))
            })
    @GetMapping("/detailed-info/related-person/{sectionId}/{pnlId}")
    public List<PublicRegisterRelatedPersonDto> getPnlDetailsRelatedPersonByPnlId(
            @PathVariable(value = "sectionId") @Min(value = 1) Long sectionId,
            @PathVariable(value = "pnlId") @Min(value = 1) Long pnlId) {

        return publicRegisterMapper.toRelatedPersons(publicRegisterService.getRelatedPersonInfo(sectionId, pnlId));
    }

    @Operation(
            summary = "Returns detailed info about related offices by pnl Id.",
            description = "Returns detailed info about related offices by pnl Id.",
            parameters = {
                @Parameter(name = "sectionId", description = "section id for which detailed info to be fetched", required = true),
                @Parameter(name = "pnlId", description = "pnl id for which detailed info to be fetched", required = true)
            },
            responses = {
                @ApiResponse(responseCode = "200", description = "Standard response for successful HTTP requests"),
                @ApiResponse(responseCode = "400", description = "BAD REQUEST - pnlId not in correct format"),
                @ApiResponse(responseCode = "404", description = "NOT FOUND - The requested resource could not be found",
                    content = @Content(schema = @Schema(name = "ErrorDefinition", type = "object", implementation = ErrorDefinition.class)))
            })
    @GetMapping("/detailed-info/office/{sectionId}/{pnlId}")
    public List<PublicRegisterRelatedPersonDetailDto> getPnlDetailsRelatedOfficeByPnlId(
            @PathVariable(value = "sectionId") @Min(value = 1) Long sectionId,
            @PathVariable(value = "pnlId") @Min(value = 1) Long pnlId) {

        return publicRegisterMapper.toPersonDetailListInfo(publicRegisterService.getRelatedPersonInfo(sectionId, pnlId));
    }

    @Operation(
            summary = "Returns detailed info about status by pnl Id.",
            description = "Returns detailed info about related persons by pnl Id.",
            parameters = {
                @Parameter(name = "pnlId", description = "pnl id for which detailed info to be fetched", required = true)
            },
            responses = {
                @ApiResponse(responseCode = "200", description = "Standard response for successful HTTP requests"),
                @ApiResponse(responseCode = "400", description = "BAD REQUEST - pnlId not in correct format"),
                @ApiResponse(responseCode = "404", description = "NOT FOUND - The requested resource could not be found",
                    content = @Content(schema = @Schema(name = "ErrorDefinition", type = "object", implementation = ErrorDefinition.class)))
            })
    @GetMapping("/detailed-info/register-status/{pnlId}")
    public List<PublicRegisterStatusDto> getPnlRegisterStatusByPnlId(
            @PathVariable(value = "pnlId") @Min(value = 1) Long pnlId) {

        return publicRegisterMapper.toStatusList(publicRegisterService.getRegisterStatusInfo(pnlId));
    }

    @Operation(
            summary = "Returns detailed info about person.",
            description = "Returns detailed info about person by pnl id.",
            parameters = {
                @Parameter(name = "pnlId",
                            description = "pnl id for which detailed info to be fetched", required = true)
            },
            responses = {
                @ApiResponse(responseCode = "200", description = "Standard response for successful HTTP requests"),
                @ApiResponse(responseCode = "400", description = "BAD REQUEST - pnlId not in correct format"),
                @ApiResponse(responseCode = "404", description = "NOT FOUND - The requested resource could not be found",
                    content = @Content(schema = @Schema(name = "ErrorDefinition", type = "object", implementation = ErrorDefinition.class)))
            })
    @GetMapping("/detailed-info/person/{pnlId}/{personId}")
    public PublicRegisterPersonInfoDto getPersonDetailsByPnlId(
            @PathVariable(value = "pnlId") @Min(value = 1) Long pnlId,
            @PathVariable(value = "personId") @Min(value = 1) Long personId) {

        return publicRegisterMapper.toPersonInfo(publicRegisterService.getDetailedPersonInfo(pnlId, personId));
    }

    @Operation(
            summary = "Returns documents about related pnl by pnl Id.",
            description = "Returns documents info about related pnl by pnl Id.",
            parameters = {
                @Parameter(name = "registerId", description = "register id", required = true),
                @Parameter(name = "sectionId", description = "section id", required = true),
                @Parameter(name = "pnlId", description = "pnl id", required = true)
            },
            responses = {
                @ApiResponse(responseCode = "200", description = "Standard response for successful HTTP requests"),
                @ApiResponse(responseCode = "400", description = "BAD REQUEST - pnlId not in correct format"),
                @ApiResponse(responseCode = "404", description = "NOT FOUND - The requested resource could not be found",
                    content = @Content(
                            schema = @Schema(name = "ErrorDefinition", type = "object", implementation = ErrorDefinition.class)))
            })
    @GetMapping("/detailed-info/documents/{registerId}/{sectionId}/{pnlId}")
    public List<PublicRegisterDocumentsDto> getPnlDetailsDocumentsByPnlId(
            @PathVariable(value = "registerId") @Min(value = 1) Long registerId,
            @PathVariable(value = "sectionId") @Min(value = 1) Long sectionId,
            @PathVariable(value = "pnlId") @Min(value = 1) Long pnlId) {

        return publicRegisterMapper.toDocumentListDto(publicRegisterService.getDocumentsInfo(registerId, sectionId, pnlId));
    }

    @Operation(
            summary = "Returns status and documents about related pnl by pnl Id.",
            description = "Returns status and documents info about related pnl by pnl Id.",
            parameters = {
                @Parameter(name = "registerId", description = "register id", required = true),
                @Parameter(name = "sectionId", description = "section id", required = true),
                @Parameter(name = "pnlId", description = "pnl id", required = true)
            },
            responses = {
                @ApiResponse(responseCode = "200", description = "Standard response for successful HTTP requests"),
                @ApiResponse(responseCode = "400", description = "BAD REQUEST - pnlId not in correct format"),
                @ApiResponse(responseCode = "404", description = "NOT FOUND - The requested resource could not be found",
                    content = @Content(
                        schema = @Schema(name = "ErrorDefinition", type = "object", implementation = ErrorDefinition.class)))
            })
    @GetMapping("/detailed-info/status-documents/{registerId}/{sectionId}/{pnlId}")
    public PublicRegisterStatusAndDocumentDto getDetailsStatusAndDocuments(
            @PathVariable(value = "registerId") @Min(value = 1) Long registerId,
            @PathVariable(value = "sectionId") @Min(value = 1) Long sectionId,
            @PathVariable(value = "pnlId") @Min(value = 1) Long pnlId) {

        final PublicRegisterStatusAndDocumentDto publicRegisterStatusAndDocuments = new PublicRegisterStatusAndDocumentDto();
        publicRegisterStatusAndDocuments.setStatusList(
                publicRegisterMapper.toStatusList(publicRegisterService.getRegisterStatusInfo(pnlId)));
        publicRegisterStatusAndDocuments.setDocumentsList(
                publicRegisterMapper.toDocumentListDto(publicRegisterService.getDocumentsInfo(registerId, sectionId, pnlId)));

        return publicRegisterStatusAndDocuments;
    }

    @Operation(summary = "Returns all person types.", description = "Returns all person types.",
               responses = {
                   @ApiResponse(responseCode = "200", description = "Standard response for successful HTTP request."),
                   @ApiResponse(responseCode = "500", description = "External system exception",
                       content = @Content(schema = @Schema(name = "ErrorDefinition",
                                   type = "object", implementation = ErrorDefinition.class)))})
    @GetMapping("/person-types")
    public List<PersonTypeDto> getPersonTypes() {
        return Arrays.stream(PersonType.values()).map(x -> new PersonTypeDto(x.getId(), x.name())).toList();
    }

    @Operation(
            summary = "Returns detailed info about related person devide by person type.",
            description = "Returns legal and individual persons together.",
            parameters = {
                @Parameter(name = "sectionId", description = "section id for which detailed info to be fetched", required = true),
                @Parameter(name = "pnlId", description = "pnl id for which detailed info to be fetched", required = true)
            },
            responses = {
                @ApiResponse(responseCode = "200", description = "Standard response for successful HTTP requests"),
                @ApiResponse(responseCode = "400", description = "BAD REQUEST - pnlId not in correct format"),
                @ApiResponse(responseCode = "404", description = "NOT FOUND - The requested resource could not be found",
                                 content = @Content(schema = @Schema(name = "ErrorDefinition", type = "object",
                                                                     implementation = ErrorDefinition.class)))
            })
    @GetMapping("/detailed-info/person-union-list/{sectionId}/{pnlId}")
    public PublicRegisterLegalAndIndividualPersonDto getLegalAndIndividualPersons(
            @PathVariable(value = "sectionId") @Min(value = 1) Long sectionId,
            @PathVariable(value = "pnlId") @Min(value = 1) Long pnlId) {

        final List<PublicRegistersRelatedPersonView> publicRegistersRelatedPersonViews =
                publicRegisterService.getRelatedPersonInfo(sectionId, pnlId);

        final List<PublicRegistersRelatedPersonView> individualPerson =
                publicRegistersRelatedPersonViews.stream()
                                                 .filter(x -> PersonType.NATURAL.equals(x.getPersonType()))
                                                 .collect(Collectors.toList());

        final List<PublicRegistersRelatedPersonView> legalPerson =
                publicRegistersRelatedPersonViews.stream()
                                                 .filter(x -> PersonType.LEGAL.equals(x.getPersonType()))
                                                 .collect(Collectors.toList());

        return new PublicRegisterLegalAndIndividualPersonDto(
                publicRegisterMapper.toPersonDetailListInfo(legalPerson),
                publicRegisterMapper.toRelatedPersons(individualPerson));
    }

    @Operation(summary = "Download file.", description = "Download file.",
        responses = {
            @ApiResponse(responseCode = "200", description = "Standard response for successful HTTP request."),
            @ApiResponse(responseCode = "500", description = "External system exception",
                content = @Content(schema = @Schema(name = "ErrorDefinition",
                    type = "object", implementation = ErrorDefinition.class)))})
    @PostMapping("/download")
    public void downloadFile(@Valid @RequestBody PnlPublicRegisterDocumentDownloadRequest fileRequest,
                             HttpServletResponse httpServletResponse) {
        
        if (!publicRegisterService.isDocumentPublic(fileRequest.getDocumentId())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }

        final FileRef fileRef = FileRef.fromString(fileRequest.getFileReferenceStr());

        fileService.downloadFile(fileRef, httpServletResponse);
    }

    @Operation(
            summary = "Returns detailed info about particular passed pnl Id.",
            description = "Returns detailed info about pnl and its documents, relations and klonove.",
            parameters = {
                @Parameter(name = "pnlId", description = "pnl id for which detailed info to be fetched", required = true)
            },
            responses = {
                @ApiResponse(responseCode = "200", description = "Standard response for successful HTTP requests"),
                @ApiResponse(responseCode = "400", description = "BAD REQUEST - pnlId not in correct format"),
                @ApiResponse(responseCode = "404", description = "NOT FOUND - The requested resource could not be found",
                    content = @Content(schema = @Schema(name = "ErrorDefinition",
                        type = "object", implementation = ErrorDefinition.class)))
            })
    @GetMapping("/detailed-info/capital/{pnlId}")
    public CapitalInfoResponse getPnlCapitalDetailsByPnlId(@PathVariable(value = "pnlId") @Min(value = 1) Long pnlId) {

        PublicRegistersView pnlInfo = publicRegisterService.getPublicRegistersView(pnlId);
        if (pnlInfo == null) {
            throw new RecordNotFoundException(ErrorDefinition.RECORD_NOT_FOUND);
        }

        return publicRegisterMapper.toCapitalResponse(publicRegisterService.getPublicRegistersView(pnlId));
    }

    @Operation(
            summary = "Returns person list and documents too.",
            description = "Returns person with EIK, name, regOfficeAddress and mailingAddress  and documents too.",
            parameters = {
                @Parameter(name = "registerId", description = "register id", required = true),
                @Parameter(name = "sectionId", description = "section id", required = true),
                @Parameter(name = "pnlId", description = "pnl id", required = true)
            },
            responses = {
                @ApiResponse(responseCode = "200", description = "Standard response for successful HTTP requests"),
                @ApiResponse(responseCode = "400", description = "BAD REQUEST - pnlId not in correct format"),
                @ApiResponse(responseCode = "404", description = "NOT FOUND - The requested resource could not be found",
                    content = @Content(schema = @Schema(name = "ErrorDefinition", type = "object",
                                                                     implementation = ErrorDefinition.class)))
            })
    @GetMapping("/detailed-info/person-document/register/{registerId}/section/{sectionId}/pnl/{pnlId}")
    public PublicRegisterRelatedPersonAndDocumentsDto getPnlRegisterPersonAndDocument(
            @PathVariable(value = "registerId") @Min(value = 1) Long registerId,
            @PathVariable(value = "sectionId") @Min(value = 1) Long sectionId,
            @PathVariable(value = "pnlId") @Min(value = 1) Long pnlId) {

        final PublicRegisterRelatedPersonAndDocumentsDto publicRegisterRelatedPersonAndDocumentsDto =
                new PublicRegisterRelatedPersonAndDocumentsDto();

        publicRegisterRelatedPersonAndDocumentsDto.setPersonList(
                publicRegisterMapper.toRelatedPersonDetailShortList(
                        publicRegisterService.getRelatedPersonInfo(sectionId, pnlId)));
        publicRegisterRelatedPersonAndDocumentsDto.setDocumentsList(
                publicRegisterMapper.toDocumentListDto(
                        publicRegisterService.getDocumentsInfo(registerId, sectionId, pnlId)));

        return publicRegisterRelatedPersonAndDocumentsDto;
    }

    @Operation(
            summary = "Returns related person list split by role type id",
            description = "Returns persons with management data split by role type id",
            parameters = {
                @Parameter(name = "sectionId", description = "section id", required = true),
                @Parameter(name = "pnlId", description = "pnl id", required = true)
            },
            responses = {
                @ApiResponse(responseCode = "200", description = "Standard response for successful HTTP requests"),
                @ApiResponse(responseCode = "400", description = "BAD REQUEST - pnlId not in correct format"),
                @ApiResponse(responseCode = "404", description = "NOT FOUND - The requested resource could not be found",
                    content = @Content(schema = @Schema(name = "ErrorDefinition", type = "object",
                                                                     implementation = ErrorDefinition.class)))
            })
    @GetMapping("/detailed-info/person-management/section/{sectionId}/pnl/{pnlId}")
    public PublicRegisterManagementResponseDto getPnlRegisterManagement(
            @PathVariable(value = "sectionId") @Min(value = 1) Long sectionId,
            @PathVariable(value = "pnlId") @Min(value = 1) Long pnlId) {

        final List<PublicRegistersRelatedPersonView> publicRegistersRelatedPersonViews =
                publicRegisterService.getRelatedPersonInfo(sectionId, pnlId);

        return applyRelatedPersonToManagementDto(publicRegistersRelatedPersonViews);
    }

    @Operation(
            summary = "Returns section insurer companies by pnlId and sectionId",
            description = "Returns section insurer companies by pnlId and sectionId",
            parameters = {
                @Parameter(name = "sectionId", description = "section id", required = true),
                @Parameter(name = "pnlId", description = "pnl id", required = true)
            },
            responses = {
                @ApiResponse(responseCode = "200", description = "Standard response for successful HTTP requests"),
                @ApiResponse(responseCode = "400", description = "BAD REQUEST - pnlId not in correct format"),
                @ApiResponse(responseCode = "404", description = "NOT FOUND - The requested resource could not be found",
                            content = @Content(schema = @Schema(name = "ErrorDefinition", type = "object",
                                    implementation = ErrorDefinition.class)))
            })
    @GetMapping("/detailed-info/insurer-companies/{sectionId}/{pnlId}")
    public List<PublicRegisterInsurerCompaniesDto> getInsurerCompanies(
            @PathVariable(value = "sectionId") @Min(value = 1) Long sectionId,
            @PathVariable(value = "pnlId") @Min(value = 1) Long pnlId) {

        final List<PublicRegistersRelatedPersonView> publicRegistersRelatedPersonViews =
                publicRegisterService.getRelatedPersonInfo(sectionId, pnlId);

        return publicRegisterMapper.toInsurerCompanies(publicRegistersRelatedPersonViews);
    }

    private PublicRegisterManagementResponseDto applyRelatedPersonToManagementDto(
            List<PublicRegistersRelatedPersonView> publicRegistersRelatedPersonViews) {

        final PublicRegisterManagementResponseDto publicRegisterManagementDto = new PublicRegisterManagementResponseDto();

        if (!publicRegistersRelatedPersonViews.isEmpty()) {

            //filter data depend on type
            final String wayOfRepresentative = publicRegistersRelatedPersonViews.stream().findFirst()
                                                                                .orElse(new PublicRegistersRelatedPersonView())
                                                                                .getWayOfRepresentative();

            final List<PublicRegistersRelatedPersonView> representativeList =
                    filterRelatedPersons(PersonRelationTypeEnum.REPRESENTATIVE.getId(), publicRegistersRelatedPersonViews);

            final List<PublicRegistersRelatedPersonView>
                    supervisoryList = filterRelatedPersons(SUPERVISORY_LIST, publicRegistersRelatedPersonViews);

            final List<PublicRegistersRelatedPersonView> bordOfDirectorList =
                    filterRelatedPersons(BORD_OF_DIRECTOR_LIST, publicRegistersRelatedPersonViews);

            final List<PublicRegistersRelatedPersonView> directorList =
                    filterRelatedPersons(PersonRelationTypeEnum.DIRECTOR_MEMBER.getId(), publicRegistersRelatedPersonViews);

            //set response data
            publicRegisterManagementDto.setWayOfRepresentative(wayOfRepresentative);
            publicRegisterManagementDto.setRespresentativeList(publicRegisterMapper.toRelatedPersons(representativeList));
            publicRegisterManagementDto.setSupervisoryList(publicRegisterMapper.toManagementResponse(supervisoryList));
            publicRegisterManagementDto.setBordOfDirectorList(publicRegisterMapper.toManagementResponse(bordOfDirectorList));
            publicRegisterManagementDto.setDirectorList(publicRegisterMapper.toManagementResponse(directorList));
        }

        return publicRegisterManagementDto;
    }

    private List<PublicRegistersRelatedPersonView> filterRelatedPersons(
            List<Long> relationTypeList, List<PublicRegistersRelatedPersonView> publicRegistersRelatedPersonViews) {

        return publicRegistersRelatedPersonViews.stream()
                                                .filter(x -> relationTypeList.contains(x.getRoleTypeId()))
                                                .collect(Collectors.toList());
    }

    private List<PublicRegistersRelatedPersonView> filterRelatedPersons(
            Long relationTypeId, List<PublicRegistersRelatedPersonView> publicRegistersRelatedPersonViews) {

        return publicRegistersRelatedPersonViews.stream()
                                                .filter(x -> relationTypeId.equals(x.getRoleTypeId()))
                                                .collect(Collectors.toList());
    }
}