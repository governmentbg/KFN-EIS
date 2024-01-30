package com.fsc.fscintegrationepaymentservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@ConfigurationProperties
@ConfigurationPropertiesScan
@PropertySource(name = "com.fsc.fscintegrationepaymentservice", value = "classpath:application.properties")
public class FscIntegrationEpaymentServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(FscIntegrationEpaymentServiceApplication.class, args);
    }

}
