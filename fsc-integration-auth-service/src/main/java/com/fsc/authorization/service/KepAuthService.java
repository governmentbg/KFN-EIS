package com.fsc.authorization.service;

import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import com.fsc.authorization.util.KepConstants;
import com.fsc.authorization.web.dto.ExternalTokenDto;
import com.fsc.authorization.web.dto.UserInfoDto;

@Service
public class KepAuthService {

    @Value("${stampit.url}")
    private String stampitUrl;

    @Value("${stampit.publicKey}")
    private String publicKey;

    @Value("${stampit.privateKey}")
    private String privateKey;

    @Value("${auth.url}")
    private String kepAuthUrl;

    @Value("${external.portal.ui}")
    private String externalPortalUi;

    @Autowired
    private RestTemplate restTemplate;

    private static final Logger log = LoggerFactory.getLogger(KepAuthService.class);

    public ExternalTokenDto getToken(String code) {
        String body = getTokenRequestFormData(code);
        
        var request = RequestEntity
                .post(URI.create(stampitUrl + "/access_token"))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(body);

        ExternalTokenDto response;
        try {
            response = restTemplate.exchange(request, ExternalTokenDto.class).getBody();
        } catch (RestClientResponseException e) {
            log.debug("Unable to get token.", e);
            response = null;
        }

        return response;
    }

    public UserInfoDto getUserInfo(String token) {
        UserInfoDto response;
        try {
            response = restTemplate.getForObject(stampitUrl + "/me?access_token=" + token, UserInfoDto.class);
        } catch (RestClientResponseException e) {
            log.debug("Unable to get token.", e);
            response = null;
        }

        return response;
    }

    private String getTokenRequestFormData(String code) {
        StringBuilder formBodyBuilder = new StringBuilder();

        appendFormDataKey(formBodyBuilder, "client_id", publicKey);
        appendFormDataKey(formBodyBuilder, "client_secret", privateKey);
        appendFormDataKey(formBodyBuilder, "redirect_uri", kepAuthUrl + KepConstants.AUTH_ENDPOINT);
        appendFormDataKey(formBodyBuilder, "code", code);

        return formBodyBuilder.toString();
    }

    private void appendFormDataKey(StringBuilder formBodyBuilder, String key, String value) {
        if (!formBodyBuilder.isEmpty()) {
            formBodyBuilder.append("&");
        }
        formBodyBuilder.append(URLEncoder.encode(key, StandardCharsets.UTF_8));
        formBodyBuilder.append("=");
        formBodyBuilder.append(URLEncoder.encode(value, StandardCharsets.UTF_8));
    }
}
