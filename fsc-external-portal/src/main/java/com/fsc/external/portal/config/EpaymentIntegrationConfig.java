package com.fsc.external.portal.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.validation.constraints.NotNull;

@Configuration
@ConfigurationProperties("e-payment.service")
public class EpaymentIntegrationConfig {

    @NotNull
    private String createPaymentUrl;

    @NotNull
    private String accessCodeUrl;

    public String getCreatePaymentUrl() {
        return createPaymentUrl;
    }

    public void setCreatePaymentUrl(String createPaymentUrl) {
        this.createPaymentUrl = createPaymentUrl;
    }

    public String getAccessCodeUrl() {
        return accessCodeUrl;
    }

    public void setAccessCodeUrl(String accessCodeUrl) {
        this.accessCodeUrl = accessCodeUrl;
    }
}
