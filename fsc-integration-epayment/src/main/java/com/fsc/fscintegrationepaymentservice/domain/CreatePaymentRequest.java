package com.fsc.fscintegrationepaymentservice.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.validation.constraints.NotNull;

public class CreatePaymentRequest {

    private String pnlName;

    private String eik;
    private BigDecimal totalAmount;
    private String currency;
    private String reason;
    private int personType;

    private String referenceNumber;

    @NotNull
    private LocalDateTime paymentReferenceDate;

    @NotNull
    private LocalDateTime expirationDate;

    public String getPnlName() {
        return pnlName;
    }

    public void setPnlName(String pnlName) {
        this.pnlName = pnlName;
    }

    public String getEik() {
        return eik;
    }

    public void setEik(String eik) {
        this.eik = eik;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getPersonType() {
        return personType;
    }

    public void setPersonType(int personType) {
        this.personType = personType;
    }

    public LocalDateTime getPaymentReferenceDate() {
        return paymentReferenceDate;
    }

    public void setPaymentReferenceDate(LocalDateTime paymentReferenceDate) {
        this.paymentReferenceDate = paymentReferenceDate;
    }

    public LocalDateTime getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDateTime expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getReferenceNumber() {
        return referenceNumber;
    }

    public void setReferenceNumber(String referenceNumber) {
        this.referenceNumber = referenceNumber;
    }
}
