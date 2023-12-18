package com.fsc.external.portal.controllers.myservices;

import com.fsc.external.portal.dtos.ServiceRequestDocumentResponse;

import java.time.LocalDate;
import java.util.Set;

public class MyServicesDetailedInfoResponse {

    private Long serviceRequestId;
    private Long personId;
    private String serviceName;
    private String userWhoSubmitted;
    private String incomingNumber;
    private String submissionMethod;
    private LocalDate submissionDate;
    private String status;
    private Set<ServiceRequestDocumentResponse> mainDocument;
    private Set<ServiceRequestDocumentResponse> attachedDocuments;

    public Long getServiceRequestId() {
        return serviceRequestId;
    }

    public void setServiceRequestId(Long serviceRequestId) {
        this.serviceRequestId = serviceRequestId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public String getUserWhoSubmitted() {
        return userWhoSubmitted;
    }

    public void setUserWhoSubmitted(String userWhoSubmitted) {
        this.userWhoSubmitted = userWhoSubmitted;
    }

    public String getIncomingNumber() {
        return incomingNumber;
    }

    public void setIncomingNumber(String incomingNumber) {
        this.incomingNumber = incomingNumber;
    }

    public String getSubmissionMethod() {
        return submissionMethod;
    }

    public void setSubmissionMethod(String submissionMethod) {
        this.submissionMethod = submissionMethod;
    }

    public LocalDate getSubmissionDate() {
        return submissionDate;
    }

    public void setSubmissionDate(LocalDate submissionDate) {
        this.submissionDate = submissionDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Set<ServiceRequestDocumentResponse> getMainDocument() {
        return mainDocument;
    }

    public void setMainDocument(Set<ServiceRequestDocumentResponse> mainDocument) {
        this.mainDocument = mainDocument;
    }

    public Set<ServiceRequestDocumentResponse> getAttachedDocuments() {
        return attachedDocuments;
    }

    public void setAttachedDocuments(Set<ServiceRequestDocumentResponse> attachedDocuments) {
        this.attachedDocuments = attachedDocuments;
    }
}
