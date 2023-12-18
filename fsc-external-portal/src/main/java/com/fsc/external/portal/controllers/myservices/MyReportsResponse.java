package com.fsc.external.portal.controllers.myservices;

import java.time.LocalDate;

public class MyReportsResponse {

    private Long serviceId;
    private String serviceName;
    private LocalDate reportDateFrom;
    private LocalDate reportDateTo;
    private String incomingNumber;
    private LocalDate submissionDate;
    private MyReportsStatus status;

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

    public LocalDate getReportDateFrom() {
        return reportDateFrom;
    }

    public void setReportDateFrom(LocalDate reportDateFrom) {
        this.reportDateFrom = reportDateFrom;
    }

    public LocalDate getReportDateTo() {
        return reportDateTo;
    }

    public void setReportDateTo(LocalDate reportDateTo) {
        this.reportDateTo = reportDateTo;
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

    public MyReportsStatus getStatus() {
        return status;
    }

    public void setStatus(MyReportsStatus status) {
        this.status = status;
    }
}
