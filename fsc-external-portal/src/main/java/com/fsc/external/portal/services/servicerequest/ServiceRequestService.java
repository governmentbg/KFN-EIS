package com.fsc.external.portal.services.servicerequest;

import com.fsc.common.addon.entity.User;
import com.fsc.common.addon.entity.catalog.service.ServiceRequest;
import com.fsc.common.addon.entity.catalog.service.ServiceRequestDocument;
import com.fsc.external.portal.dtos.ServiceRequestDocumentRequest;
import com.fsc.external.portal.dtos.ServiceRequestDocumentResponse;
import com.fsc.external.portal.dtos.SubmitFormResponse;
import io.jmix.core.FileRef;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

public interface ServiceRequestService {

    void delete(Long serviceRequestId);

    ServiceRequestDocumentResponse attachServiceRequestDocument(Long serviceRequestId,
                                                                ServiceRequestDocumentRequest serviceRequestDocumentRequest);

    ServiceRequestDocument attachServiceRequestDocument(Long serviceRequestId, Long documentTypeId,
                                                        FileRef fileRef);

    ServiceRequestDocumentResponse attachServiceRequestDocumentByAnonymousUser(Long serviceRequestId,
                                                                               ServiceRequestDocumentRequest serviceRequestDocumentRequest);

    @Deprecated
    void deleteDocument(Long documentId);

    void deleteDocument(Long serviceRequestId, Long documentId);

    ServiceRequest getByIdAndSubmitter(long id, Long submitterId);

    Optional<ServiceRequest> getUnfinishedServiceRequest(Long serviceRequestId, User user);

    Optional<ServiceRequest> getServiceRequest(Long serviceRequestId);

    Long getStartedServiceRequest(Long catalogElementId, Long pnlId, Long personId);

    Optional<ServiceRequest> checkIfDraftServiceRequestExist(Long catalogElementId, Long pnlId, Long personId);

    Set<ServiceRequestDocumentResponse> getServiceRequestDocuments(Long serviceRequestId);

    String submitServiceRequest(Long serviceRequestId, String documentDescription, FileRef fileReference);

    String submitServiceRequest(ServiceRequest serviceRequest, ServiceRequestDocumentRequest serviceRequestDocumentRequest);

    void updateSubmitData(ServiceRequest serviceRequest, String submitData);

    ServiceRequest updateServiceRequest(ServiceRequest serviceRequest);

    SubmitFormResponse updateServiceRequest(ServiceRequest serviceRequest, Map<String, Object> submitData);

    ServiceRequestDocument getRequestServiceDocumentById(Long documentId);

    Long getCatalogElementIdFromServiceRequest(Long serviceRequestId);

    void processServiceRequest(Long serviceRequestId);
}
