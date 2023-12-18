package com.fsc.external.portal.mappers;

import com.fsc.common.addon.entity.view.PnlReportOnePensionShareView;
import com.fsc.external.portal.dtos.ReportPensionShareResponse;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public abstract class ReportPensionShareMapper implements BasePageResponseMapper<PnlReportOnePensionShareView, ReportPensionShareResponse> {

    public abstract List<ReportPensionShareResponse> toResponse(List<PnlReportOnePensionShareView> reportPensionShare);
}
