package com.fsc.fscintegrationepaymentservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fsc.fscintegrationepaymentservice.configuration.property.ServiceAisProperties;
import com.fsc.fscintegrationepaymentservice.domain.CreatePaymentRequest;
import com.fsc.fscintegrationepaymentservice.domain.CreatePaymentResponse;
import com.fsc.fscintegrationepaymentservice.domain.epayment.AccessCodeResponse;
import com.fsc.fscintegrationepaymentservice.domain.epayment.PaymentNotificationResponse;
import com.fsc.fscintegrationepaymentservice.domain.epayment.PaymentResponse;
import com.fsc.fscintegrationepaymentservice.domain.epayment.PostRequest;
import com.fsc.fscintegrationepaymentservice.domain.external.StatusChangeRequest;
import com.fsc.fscintegrationepaymentservice.mapper.PaymentRequestMapper;
import com.fsc.fscintegrationepaymentservice.service.IntegrationService;
import com.fsc.fscintegrationepaymentservice.service.PaymentAisService;
import com.fsc.fscintegrationepaymentservice.util.TransformRequestData;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@RestController
public class PaymentAisController {
    private static final Logger log = LoggerFactory.getLogger(PaymentAisController.class);
    private static final String ENDPOINT_PAYMENT_JSON = "paymentJson";
    private static final String ENDPOINT_ACCESS_CODE = "accessCode";

    private final IntegrationService integrationService;
    private final PaymentAisService paymentAisService;
    private final PaymentRequestMapper paymentRequestMapper;
    private final ServiceAisProperties serviceAisProperties;

    PaymentAisController(IntegrationService integrationService,
                         PaymentAisService paymentAisService,
                         PaymentRequestMapper paymentRequestMapper,
                         ServiceAisProperties serviceAisProperties) {
        this.paymentAisService = paymentAisService;
        this.integrationService = integrationService;
        this.paymentRequestMapper = paymentRequestMapper;
        this.serviceAisProperties = serviceAisProperties;
    }

    @Operation(
            summary = "Returns detailed info about payment document number.",
            description = "Returns detailed info about payment document number.",
            responses = {
                @ApiResponse(responseCode = "200", description = "Standard response for successful HTTP requests"),
                @ApiResponse(responseCode = "400", description = "BAD REQUEST - data is not in correct format"),
            })
    public CreatePaymentResponse createPayment(@RequestBody CreatePaymentRequest createPaymentRequest)
        throws NoSuchAlgorithmException, InvalidKeyException, IOException, ParseException {

        final PostRequest postRequest = paymentAisService.createPaymentRequest(createPaymentRequest);

        final ResponseEntity<String> response = integrationService.callPaymentAisService(postRequest, ENDPOINT_PAYMENT_JSON);

        log.info("Response: " + response);

        ObjectMapper mapper = new ObjectMapper();
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ISO_DATE_TIME));
        mapper.registerModule(javaTimeModule);
        PaymentResponse paymentResponse = mapper.readValue(response.getBody(), PaymentResponse.class);

        if (paymentResponse.getAcceptedReceiptJson() != null) {
            final String id = paymentResponse.getAcceptedReceiptJson().getId();

            AccessCodeResponse accessCodeResponse = getAccessCode(id);
            return new CreatePaymentResponse(id, accessCodeResponse.getAccessCode(), paymentAisService.generateExpirationDate());
        }

        return new CreatePaymentResponse();
    }

    public AccessCodeResponse getAccessCode(@PathVariable("paymentId") String paymentId)
            throws IOException, NoSuchAlgorithmException, InvalidKeyException {

        PostRequest postRequest = paymentAisService.accessCodeRequest(paymentId);
        final ResponseEntity<String> response = integrationService.callPaymentAisService(postRequest, ENDPOINT_ACCESS_CODE);
        final AccessCodeResponse accessCodeResponse;
        if (HttpStatus.OK.equals(response.getStatusCode())) {
            ObjectMapper mapper = new ObjectMapper();
            accessCodeResponse = mapper.readValue(response.getBody(), AccessCodeResponse.class);
        } else {
            accessCodeResponse = new AccessCodeResponse();
        }
        return accessCodeResponse;
    }

    public PaymentNotificationResponse paymentNotification(
            @RequestParam MultiValueMap<String, String> formData) throws JsonProcessingException {

        final String clientId = formData.getFirst("ClientId");
        log.info("paymentNotification: " + formData);
        if (serviceAisProperties.getUsername().equals(clientId)) {
            String data = TransformRequestData.decodeBase64ToString(formData.getFirst("Data"));
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            Map<String, String> map = objectMapper.readValue(data, Map.class);
            log.info("paymentNotification map: " + map);
            StatusChangeRequest paymentStatusChangeRequest =
                    paymentRequestMapper.toStatusChangeRequest(map.get("Id"), map.get("Status"), map.get("ChangeTime"));

            log.info("paymentNotification / paymentStatusChangeRequest: " + paymentStatusChangeRequest);

            integrationService.callExternalPortal(paymentStatusChangeRequest);

            return new PaymentNotificationResponse(true);
        }

        log.error("paymentNotification: clientId not found");

        return new PaymentNotificationResponse(false);
    }
}
