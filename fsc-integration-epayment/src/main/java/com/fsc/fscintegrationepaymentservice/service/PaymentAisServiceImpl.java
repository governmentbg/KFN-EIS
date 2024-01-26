package com.fsc.fscintegrationepaymentservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import com.fsc.fscintegrationepaymentservice.configuration.property.ServiceAisProperties;
import com.fsc.fscintegrationepaymentservice.domain.CreatePaymentRequest;
import com.fsc.fscintegrationepaymentservice.domain.epayment.PaymentRequest;
import com.fsc.fscintegrationepaymentservice.domain.epayment.PostRequest;
import com.fsc.fscintegrationepaymentservice.mapper.PaymentRequestMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Service
public class PaymentAisServiceImpl implements PaymentAisService {
    private static final Logger log = LoggerFactory.getLogger(PaymentAisServiceImpl.class);
    private static final String ID_REQUEST_FORMAT = "{id:\"%s\"}";

    private final ServiceAisProperties serviceAisProperties;
    private final PaymentRequestMapper paymentRequestMapper;

    PaymentAisServiceImpl(ServiceAisProperties serviceAisProperties, PaymentRequestMapper paymentRequestMapper) {
        this.serviceAisProperties = serviceAisProperties;
        this.paymentRequestMapper = paymentRequestMapper;
    }

    @Override
    public PostRequest createPaymentRequest(CreatePaymentRequest createPaymentRequest) throws IOException, NoSuchAlgorithmException,
                                                                                                          InvalidKeyException {
        final LocalDateTime today = LocalDateTime.now();

        PaymentRequest paymentRequest = paymentRequestMapper.toPaymentRequest(createPaymentRequest, generateExpirationDate(),
                today, serviceAisProperties);


        // transform data as string
        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();
        mapper.setDateFormat(new StdDateFormat().withColonInTimeZone(true));
        String dataString = mapper.writeValueAsString(paymentRequest);

        return trasformRawDataToPostRequest(dataString);
    }

    @Override
    public PostRequest accessCodeRequest(String id) throws NoSuchAlgorithmException, InvalidKeyException {
        final String requestData = String.format(ID_REQUEST_FORMAT, id);
        
        return trasformRawDataToPostRequest(requestData);
    }

    @Override
    public LocalDateTime generateExpirationDate() {
        return LocalDateTime.of(LocalDate.now().plusDays(serviceAisProperties.getExpirationDays()), LocalTime.MIDNIGHT);
    }

    private PostRequest createPostRequest(String clientId, String data, String hmac) {
        // set post request data
        PostRequest postRequest = new PostRequest();
        postRequest.setClientId(clientId);
        postRequest.setData(data);
        postRequest.setHmac(hmac);

        return postRequest;
    }

    private PostRequest trasformRawDataToPostRequest(String dataString) throws NoSuchAlgorithmException, InvalidKeyException {

        return createPostRequest(serviceAisProperties.getUsername(), data, hmac);
    }
}
