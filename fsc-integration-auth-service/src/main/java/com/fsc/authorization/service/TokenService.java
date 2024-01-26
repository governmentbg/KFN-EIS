package com.fsc.authorization.service;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import com.fsc.authorization.web.dto.RegisterUserDto;
import com.fsc.authorization.web.dto.TokenDto;

@Service
public class TokenService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${external.portal.url}")
    private String externalPortalUrl;

    @Value("${external.portal.client}")
    private String externalPortalClient;

    @Value("${external.portal.secret}")
    private String externalPortalSecret;

    private static final String PARAM_EGN = "egn";
    private static final String PARAM_PERSON_NAME = "name";
    private static final String PARAM_GRANT_TYPE = "grant_type";
    private static final String PARAM_GRANT_TYPE_VALUE = "external";

    private static final String API_TOKEN = "/oauth/token";
    private static final String API_REGISTER = "/rest/entities/User/register";

    private static final Logger log = LoggerFactory.getLogger(TokenService.class);

    public TokenDto getToken(String egn, String name) {

        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        requestBody.add(PARAM_EGN, egn);
        requestBody.add(PARAM_PERSON_NAME, name);
        requestBody.add(PARAM_GRANT_TYPE, PARAM_GRANT_TYPE_VALUE);

        String auth = externalPortalClient + ":" + externalPortalSecret;

        String authHeader = "Basic " + new String(
                Base64.getEncoder().encode(auth.getBytes(StandardCharsets.UTF_8)),
                StandardCharsets.UTF_8);

        var request = RequestEntity
                .post(URI.create(externalPortalUrl + API_TOKEN))
                .accept(MediaType.APPLICATION_FORM_URLENCODED)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .header(HttpHeaders.AUTHORIZATION, authHeader)
                .body(requestBody);

        TokenDto response;
        try {
            response = restTemplate.exchange(request, TokenDto.class).getBody();
        } catch (RestClientResponseException e) {
            log.debug("Unable to get token.", e);
            response = null;
        }

        return response;
    }

    public boolean registerUser(RegisterUserDto user) {

        try {
            restTemplate.postForObject(URI.create(externalPortalUrl + API_REGISTER), user, Object.class);
            return true;
        } catch (RestClientResponseException e) {
            log.debug("Unable to register user.", e);
            return false;
        }
    }
}