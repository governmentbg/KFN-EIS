package com.fsc.fscintegrationepaymentservice.service;

import com.fsc.fscintegrationepaymentservice.domain.CreatePaymentRequest;
import com.fsc.fscintegrationepaymentservice.domain.epayment.PostRequest;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.time.LocalDateTime;

public interface PaymentAisService {

    /**
     * create a payment document
     *
     * @param createPaymentRequest - payment request data
     *
     * @return ais payment id
     *
     * @throws IOException - io exception
     * @throws NoSuchAlgorithmException - no such algorithm exception
     * @throws InvalidKeyException - invalid key exception
     * @throws ParseException - parse algorithm exception
     */
    PostRequest createPaymentRequest(CreatePaymentRequest createPaymentRequest) throws IOException, NoSuchAlgorithmException,
                                                                                               InvalidKeyException, ParseException;

    /**
     * get access code for egov portal where there is a virtual post
     * @param id - ais payment id
     * @return access code
     * @throws NoSuchAlgorithmException  - no such algorithm exception
     * @throws InvalidKeyException - invalid key exception
     */
    PostRequest accessCodeRequest(String id) throws NoSuchAlgorithmException, InvalidKeyException;

    /**
     * @return payment request expiration date
     */
    LocalDateTime generateExpirationDate();
}
