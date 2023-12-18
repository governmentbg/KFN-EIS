package com.fsc.external.portal.controllers.myservices;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Validated
public class MyReportsRequest {

    private static final String INCOMING_DATE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";

    @NotNull
    private Long personId;
    @NotNull
    private Long pnlId;
    @Min(value = 1, message = "Page number should not be less than 1")
    private Integer page;
    @Min(value = 1, message = "Page size should not be less than 1")
    private Integer size;
    private String serviceName;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = INCOMING_DATE_TIME_FORMAT)
    private LocalDateTime submissionDate;
    private String incomingNumber;
    private String[] status;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = INCOMING_DATE_TIME_FORMAT)
    private LocalDate reportDateFrom;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = INCOMING_DATE_TIME_FORMAT)
    private LocalDate reportDateTo;

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public LocalDateTime getSubmissionDate() {
        return submissionDate;
    }

    public void setSubmissionDate(LocalDateTime submissionDate) {
        this.submissionDate = submissionDate;
    }

    public String getIncomingNumber() {
        return incomingNumber;
    }

    public void setIncomingNumber(String incomingNumber) {
        this.incomingNumber = incomingNumber;
    }

    public String[] getStatuses() {
        return status;
    }

    public void setStatus(String[] status) {
        this.status = status;
    }

    public Long getPnlId() {
        return pnlId;
    }

    public void setPnlId(Long pnlId) {
        this.pnlId = pnlId;
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
}
