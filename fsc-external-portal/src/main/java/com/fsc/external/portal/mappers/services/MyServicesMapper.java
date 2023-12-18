package com.fsc.external.portal.mappers.services;

import com.fsc.external.portal.controllers.myservices.MyServicesResponse;
import com.fsc.external.portal.entity.servicerequest.ServiceRequestView;
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
public abstract class MyServicesMapper extends BaseServicesMapper
        implements BasePageResponseMapper<ServiceRequestView, MyServicesResponse> {

    @Mapping(target = "serviceId", source = "serviceRequestId")
    @Mapping(target = "serviceName", source = "catalogueName")
    @Mapping(target = "incomingNumber", source = "correspondenceNumber")
    @Mapping(target = "submissionDate", source = "serviceRequestDate")
    @Mapping(target = "status", source = "serviceRequestStatus", qualifiedByName = "mapServiceStatusId")
    public abstract MyServicesResponse toResponse(ServiceRequestView serviceRequest);
}
