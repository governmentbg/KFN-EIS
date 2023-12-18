package com.fsc.external.portal.mappers.services;

import com.fsc.common.addon.entity.view.PnlReportView;
import com.fsc.external.portal.controllers.myservices.MyReportsResponse;
import com.fsc.external.portal.mappers.BasePageResponseMapper;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class MyServicesReportsMapper extends BaseServicesMapper
        implements BasePageResponseMapper<PnlReportView, MyReportsResponse> {

    @Mapping(target = "serviceId", source = "serviceRequestId")
    @Mapping(target = "serviceName", source = "documentTypeName")
    @Mapping(target = "reportDateFrom", source = "reportFrom")
    @Mapping(target = "reportDateTo", source = "reportTo")
    @Mapping(target = "incomingNumber", source = "correspondenceNumber")
    @Mapping(target = "submissionDate", source = "reportSubmitDate")
    @Mapping(target = "status", source = "report", qualifiedByName = "mapReportStatusId")
    public abstract MyReportsResponse toResponse(PnlReportView report);
}
