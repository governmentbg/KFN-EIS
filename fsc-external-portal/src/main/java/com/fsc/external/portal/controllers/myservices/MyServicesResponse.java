package com.fsc.external.portal.controllers.myservices;

import java.time.LocalDate;

public class MyServicesResponse {

    private Long serviceId;
    private String serviceName;
    private String incomingNumber;
    private LocalDate submissionDate;
    private MyServicesStatus status;

    public Long getServiceId() {
        return serviceId;
    }

    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getIncomingNumber() {
        return incomingNumber;
    }

    public void setIncomingNumber(String incomingNumber) {
        this.incomingNumber = incomingNumber;
    }

    public LocalDate getSubmissionDate() {
        return submissionDate;
    }

    public void setSubmissionDate(LocalDate submissionDate) {
        this.submissionDate = submissionDate;
    }

    public MyServicesStatus getStatus() {
        return status;
    }

    public void setStatus(MyServicesStatus status) {
        this.status = status;
    }
}
