package com.fsc.external.portal.mappers.services;

import com.fsc.common.addon.entity.catalog.service.ServiceRequest;
import com.fsc.common.addon.entity.catalog.service.ServiceRequestDocument;
import com.fsc.common.addon.entity.enums.CorrectionAllowedOperation;
import com.fsc.external.portal.controllers.myservices.MyReportDetailedInfoResponse;
import com.fsc.external.portal.controllers.myservices.MyServicesDetailedInfoResponse;
import com.fsc.external.portal.dtos.ServiceRequestDocumentResponse;
import com.fsc.external.portal.dtos.ServiceRequestWithPnlReportViewDTO;
import com.fsc.external.portal.mappers.BasePageResponseMapper;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.ReportingPolicy;

import java.util.HashSet;
import java.util.Set;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class MyServicesDetailedInfoMapper extends BaseServicesMapper implements
        BasePageResponseMapper<ServiceRequest, MyServicesDetailedInfoResponse> {

    private static final boolean IS_MAIN_TRUE = true;
    private static final boolean IS_MAIN_FALSE = false;

    @Mapping(target = "serviceRequestId", source = "id")
    @Mapping(target = "personId", source = "person.id")
    @Mapping(target = "serviceName", source = "serviceRequest", qualifiedByName = "mapServiceName")
    @Mapping(target = "userWhoSubmitted", source = "submittedBy.person.name")
    @Mapping(target = "incomingNumber", source = "correspondence.number")
    @Mapping(target = "submissionMethod", expression = "java(new java.lang.String(\"Подаване с КЕП\"))")
    @Mapping(target = "submissionDate", source = "requestDate")
    @Mapping(target = "status", source = "serviceRequest.status", qualifiedByName = "mapServiceStatusId")
    @Mapping(target = "mainDocument", source = "serviceRequest.documents", qualifiedByName = "mapMainDocument")
    @Mapping(target = "attachedDocuments", source = "serviceRequest.documents", qualifiedByName = "mapAttachedDocuments")
    public abstract MyServicesDetailedInfoResponse toResponse(ServiceRequest serviceRequest);

    @Mapping(target = "serviceRequestId", source = "serviceRequest.id")
    @Mapping(target = "personId", source = "serviceRequest.person.id")
    @Mapping(target = "serviceName", source = "serviceRequest", qualifiedByName = "mapServiceName")
    @Mapping(target = "userWhoSubmitted", source = "serviceRequest.submittedBy.person.name")
    @Mapping(target = "incomingNumber", source = "pnlReportView.correspondenceNumber")
    @Mapping(target = "submissionMethod", expression = "java(new java.lang.String(\"Подаване с КЕП\"))")
    @Mapping(target = "submissionDate", source = "serviceRequest.requestDate")
    @Mapping(target = "status", source = "dto.pnlReportView", qualifiedByName = "mapReportStatusId")
    @Mapping(target = "mainDocument", source = "serviceRequest.documents", qualifiedByName = "mapMainDocument")
    @Mapping(target = "attachedDocuments", source = "serviceRequest.documents", qualifiedByName = "mapAttachedDocuments")
    @Mapping(target = "reportDateFrom", source = "pnlReportView.reportFrom")
    @Mapping(target = "reportDateTo", source = "pnlReportView.reportTo")
    @Mapping(target = "statusDate", source = "pnlReportView.reportStatusDate")
    @Mapping(target = "allowedOperation", source = "pnlReportView.allowedOperation", qualifiedByName = "mapAllowedOperation")
    @Mapping(target = "versionId", source = "pnlReportView.versionId")
    public abstract MyReportDetailedInfoResponse toDetailedResponse(ServiceRequestWithPnlReportViewDTO dto);

    @Named("mapAllowedOperation")
    String mapAllowedOperation(CorrectionAllowedOperation allowedOperation) {
        switch (allowedOperation) {
            case CORRECTION_REQUEST -> {
                return CorrectionAllowedOperation.CORRECTION_REQUEST.getId();
            }
            case CORRECTION -> {
                return CorrectionAllowedOperation.CORRECTION.getId();
            }
            default -> {
                return null;
            }
        }
    }

    @Named("mapMainDocument")
    Set<ServiceRequestDocumentResponse> mapMainDocument(Set<ServiceRequestDocument> serviceRequestDocuments) {

        return getServiceRequestDocumentsResponse(serviceRequestDocuments, IS_MAIN_TRUE);
    }

    @Named("mapAttachedDocuments")
    Set<ServiceRequestDocumentResponse> mapAttachedDocuments(Set<ServiceRequestDocument> serviceRequestDocuments) {

        return getServiceRequestDocumentsResponse(serviceRequestDocuments, IS_MAIN_FALSE);
    }

    private Set<ServiceRequestDocumentResponse> getServiceRequestDocumentsResponse(Set<ServiceRequestDocument> serviceRequestDocuments,
            boolean isMain) {

        Set<ServiceRequestDocumentResponse> set = new HashSet<>();

        for (ServiceRequestDocument document : serviceRequestDocuments) {
            if (document.getMain().equals(isMain)) {
                ServiceRequestDocumentResponse serviceRequestDocumentResponse = new ServiceRequestDocumentResponse();
                serviceRequestDocumentResponse.setId(document.getId());
                serviceRequestDocumentResponse.setDocumentTypeId(String.valueOf(document.getDocumentType().getId()));
                serviceRequestDocumentResponse.setDocumentDescription(document.getDocumentDescription());
                serviceRequestDocumentResponse.setFileRef(document.getFileReference());
                serviceRequestDocumentResponse.setFileReferenceStr(document.getFileReference().toString());
                serviceRequestDocumentResponse.setCreatedDate(document.getCreatedDate().toLocalDate().atStartOfDay());

                set.add(serviceRequestDocumentResponse);
            }
        }
        return set;
    }
}
