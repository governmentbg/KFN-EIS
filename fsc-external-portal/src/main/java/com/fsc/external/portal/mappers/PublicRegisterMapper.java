package com.fsc.external.portal.mappers;

import com.fsc.common.addon.entity.view.PublicRegistersMainView;
import com.fsc.common.addon.entity.view.publicregister.PublicRegistersDocumentView;
import com.fsc.common.addon.entity.view.publicregister.PublicRegistersRelatedPersonView;
import com.fsc.common.addon.entity.view.publicregister.PublicRegistersRelatedPnlView;
import com.fsc.common.addon.entity.view.publicregister.PublicRegistersStatusView;
import com.fsc.common.addon.entity.view.publicregister.PublicRegistersView;
import com.fsc.external.portal.dtos.pnl.PublicRegisterResponse;
import com.fsc.external.portal.dtos.publicregisterdetailedinfo.CapitalInfoResponse;
import com.fsc.external.portal.dtos.publicregisterdetailedinfo.DetailedInfoResponse;
import com.fsc.external.portal.dtos.publicregisterdetailedinfo.PublicRegisterInsurerCompaniesDto;
import com.fsc.external.portal.dtos.publicregisterdetailedinfo.PublicRegisterRelatedPersonDetailDto;
import com.fsc.external.portal.dtos.publicregisterdetailedinfo.PublicRegisterDocumentsDto;
import com.fsc.external.portal.dtos.publicregisterdetailedinfo.PublicRegisterPersonInfoDto;
import com.fsc.external.portal.dtos.publicregisterdetailedinfo.PublicRegisterRelatedPersonDetailShortDto;
import com.fsc.external.portal.dtos.publicregisterdetailedinfo.PublicRegisterRelatedPersonDto;
import com.fsc.external.portal.dtos.publicregisterdetailedinfo.PublicRegisterRelatedPersonManagementDetailDto;
import com.fsc.external.portal.dtos.publicregisterdetailedinfo.PublicRegisterRelatedPnlDto;
import com.fsc.external.portal.dtos.publicregisterdetailedinfo.PublicRegisterRelatedPnlWithRegisterDto;
import com.fsc.external.portal.dtos.publicregisterdetailedinfo.PublicRegisterStatusDto;
import com.fsc.external.portal.utils.RoundingUtil;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = RoundingUtil.class
)
public abstract class PublicRegisterMapper implements BasePageResponseMapper<PublicRegistersView, PublicRegisterResponse> {

    @Mapping(target = "pnlName", source = "personName")
    public abstract PublicRegisterResponse toResponse(PublicRegistersView publicRegister);

    @Mapping(target = "pnlId", source = "publicRegistersView.pnlId")
    @Mapping(target = "personId", source = "publicRegistersView.personId")
    @Mapping(target = "pnlName", source = "publicRegistersView.personName")
    @Mapping(target = "personName", source = "publicRegistersView.personName")
    public abstract DetailedInfoResponse toResponse(PublicRegistersView publicRegistersView,
                                                    PublicRegistersMainView publicRegistersMainView);

    @Mapping(target = "pnlId", source = "relatedPnlId")
    @Named("toRelatedPnl")
    abstract PublicRegisterRelatedPnlDto toRelatedPnl(PublicRegistersRelatedPnlView publicRegisterRelatedPnlView);

    @Mapping(target = "pnlId", source = "relatedPnlId")
    @Named("toRelatedPnlWithRegister")
    abstract PublicRegisterRelatedPnlWithRegisterDto toRelatedPnlWithRegister(PublicRegistersRelatedPnlView publicRegisterRelatedPnlView);

    @IterableMapping(qualifiedByName = "toRelatedPnlWithRegister")
    public abstract List<PublicRegisterRelatedPnlWithRegisterDto> toRelatedPnlWithRegister(
            List<PublicRegistersRelatedPnlView> publicRegisterRelatedPnlView);

    @IterableMapping(qualifiedByName = "toRelatedPnl")
    public abstract List<PublicRegisterRelatedPnlDto> toRelatedPnlListDto(List<PublicRegistersRelatedPnlView> publicRegisterRelatedPnlView);

    public abstract List<PublicRegisterRelatedPersonDto> toRelatedPersons(
            List<PublicRegistersRelatedPersonView> publicRegistersRelatedPersonViews);

    public abstract List<PublicRegisterInsurerCompaniesDto> toInsurerCompanies(
            List<PublicRegistersRelatedPersonView> publicRegistersRelatedPersonViews);

    @Named("toDocumentDto")
    @Mapping(target = "fileRef", source = "fileReference")
    @Mapping(target = "fileReferenceStr", expression = "java(publicRegistersDocumentViews.getFileReference().toString())")
    public abstract PublicRegisterDocumentsDto toDocumentListDto(PublicRegistersDocumentView publicRegistersDocumentViews);

    @IterableMapping(qualifiedByName = "toDocumentDto")
    public abstract List<PublicRegisterDocumentsDto> toDocumentListDto(List<PublicRegistersDocumentView> publicRegistersDocumentViews);

    public abstract PublicRegisterPersonInfoDto toPersonInfo(PublicRegistersView publicRegistersView);

    public abstract List<PublicRegisterRelatedPersonDetailDto> toPersonDetailListInfo(
            List<PublicRegistersRelatedPersonView> publicRegistersRelatedPersonViews);

    public abstract List<PublicRegisterStatusDto> toStatusList(List<PublicRegistersStatusView> publicRegistersStatusViews);

    @Mapping(target = "capital", source = "capital", qualifiedBy = RoundingUtil.RoundingTwoAfterDecimalPoint.class)
    @Mapping(target = "emissionValues", source = "emissionValues", qualifiedBy = RoundingUtil.RoundingTwoAfterDecimalPoint.class)
    @Mapping(target = "assetCount", source = "assetCount", qualifiedBy = RoundingUtil.RoundingTwoAfterDecimalPoint.class)
    @Mapping(target = "assetNominal", source = "assetNominal", qualifiedBy = RoundingUtil.RoundingTwoAfterDecimalPoint.class)
    public abstract CapitalInfoResponse toCapitalResponse(PublicRegistersView publicRegister);

    @Mapping(target = "pnlName", source = "personName")
    @Named("toRelatedPersonShortDetail")
    abstract PublicRegisterRelatedPersonDetailShortDto toRelatedPersonShortDetail(
            PublicRegistersRelatedPersonView publicRegistersRelatedPersonView);

    @IterableMapping(qualifiedByName = "toRelatedPersonShortDetail")
    public abstract List<PublicRegisterRelatedPersonDetailShortDto> toRelatedPersonDetailShortList(
            List<PublicRegistersRelatedPersonView> publicRegistersRelatedPersonViews);

    public abstract List<PublicRegisterRelatedPersonManagementDetailDto> toManagementResponse(
            List<PublicRegistersRelatedPersonView> publicRegistersRelatedPersonViews);
}
