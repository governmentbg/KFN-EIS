package com.fsc.fscintegrationepaymentservice.service;

import com.fsc.fscintegrationepaymentservice.configuration.property.ServiceAisProperties;
import com.fsc.fscintegrationepaymentservice.domain.external.StatusChangeRequest;
import com.fsc.fscintegrationepaymentservice.domain.epayment.PostRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

@Service
public class IntegrationServiceImpl implements IntegrationService {

    private static final Logger log = LoggerFactory.getLogger(PaymentAisServiceImpl.class);

    private final RestTemplate restTemplate;
    private final ServiceAisProperties serviceAisProperties;

    @Value("${external.service.host}")
    private String externalPortalUrl;

    public IntegrationServiceImpl(RestTemplate restTemplate, ServiceAisProperties serviceAisProperties) {
        this.restTemplate = restTemplate;
        this.serviceAisProperties = serviceAisProperties;
    }

    @Override
    public ResponseEntity<String> callPaymentAisService(PostRequest postRequest, String endpoint) {
        String url = String.format("%s%s", serviceAisProperties.getServiceUrl(), endpoint);
        return callEndpoint(postRequest, url);
    }

    @Override
    public boolean callExternalPortal(StatusChangeRequest paymentStatusChangeRequest) {

        try {
            log.info("paymentNotification / externalPortalUrl: " + externalPortalUrl);

            HttpEntity<StatusChangeRequest> request = new HttpEntity<>(paymentStatusChangeRequest);
            HttpStatus responseHttpStatus = restTemplate.postForEntity(externalPortalUrl, request, Boolean.class).getStatusCode();
            return HttpStatus.OK.equals((responseHttpStatus));
        } catch (HttpClientErrorException e) {
            throw new ResponseStatusException(e.getStatusCode(), "");
        }
    }

    private ResponseEntity<String> callEndpoint(PostRequest postRequest, String url) {
        // create headers
        System.out.println("url:" + url);

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

            MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
            map.add("clientId", postRequest.getClientId());
            map.add("hmac", postRequest.getHmac());
            map.add("data", postRequest.getData());

            // set `content-type` header
            HttpEntity request = new HttpEntity(map, headers);
            return restTemplate.postForEntity(url, request, String.class);
        } catch (HttpClientErrorException e) {
            throw new ResponseStatusException(e.getStatusCode(), "");
        }
    }
}
