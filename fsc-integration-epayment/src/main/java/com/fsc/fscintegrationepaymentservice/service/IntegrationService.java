package com.fsc.fscintegrationepaymentservice.service;

import com.fsc.fscintegrationepaymentservice.domain.external.StatusChangeRequest;
import com.fsc.fscintegrationepaymentservice.domain.epayment.PostRequest;
import org.springframework.http.ResponseEntity;

public interface IntegrationService {

    ResponseEntity<String> callPaymentAisService(PostRequest postRequest, String endpoint);

    boolean callExternalPortal(StatusChangeRequest data);

}
