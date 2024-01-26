package com.fsc.fscintegrationepaymentservice.domain;

import java.time.LocalDateTime;

public class CreatePaymentResponse {
    private String id;
    private String accessCode;
    private LocalDateTime expirationDate;

    public CreatePaymentResponse() {
    }

    public CreatePaymentResponse(String id, String accessCode, LocalDateTime expirationDate) {
        this.id = id;
        this.accessCode = accessCode;
        this.expirationDate = expirationDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccessCode() {
        return accessCode;
    }

    public void setAccessCode(String accessCode) {
        this.accessCode = accessCode;
    }

    public LocalDateTime getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDateTime expirationDate) {
        this.expirationDate = expirationDate;
    }
}
