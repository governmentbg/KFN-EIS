package com.fsc.authorization.service;

import com.fsc.authorization.web.dto.RegisterUserDto;
import com.fsc.authorization.web.dto.TokenDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.security.saml2.provider.service.authentication.Saml2AuthenticatedPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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

    private static final String ATTRIBUTE_PERSON_IDENTIFIER = "urn:egov:bg:eauth:2.0:attributes:personIdentifier";
    private static final String ATTRIBUTE_PERSON_NAME = "urn:egov:bg:eauth:2.0:attributes:personName";
    private static final String ATTRIBUTE_EMAIL =  "urn:egov:bg:eauth:2.0:attributes:email";
    private static final String ATTRIBUTE_PHONE = "urn:egov:bg:eauth:2.0:attributes:phone";
    private static final String ATTRIBUTE_GENDER = "urn:egov:bg:eauth:2.0:attributes:gender";
    private static final String ATTRIBUTE_BIRTH_DATE = "urn:egov:bg:eauth:2.0:attributes:dateOfBirth";
    private static final String ATTRIBUTE_BIRTH_PLACE = "urn:egov:bg:eauth:2.0:attributes:placeOfBirth";
    private static final String ATTRIBUTE_ADDRESS = "urn:egov:bg:eauth:2.0:attributes:canonicalResidenceAddress";
    // latinName and birthName are also provided

    private static final String API_TOKEN = "/oauth/token";
    private static final String API_REGISTER = "/rest/entities/User/register";

    private static final Logger log = LoggerFactory.getLogger(TokenService.class);

    private Map<String, TokenDto> storage = new HashMap<>();

    public TokenDto getTokenByKey(String key) {
        return storage.remove(key);
    }

    public String addToken(TokenDto token) {

        String key = UUID.randomUUID().toString();

        storage.put(key, token);

        return key;
    }

    public TokenDto getToken(Saml2AuthenticatedPrincipal principal) {

        String egn = parseEgn(principal);

        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        requestBody.add(PARAM_EGN, egn);
        requestBody.add(PARAM_PERSON_NAME, parseAttribute(principal, ATTRIBUTE_PERSON_NAME));
        requestBody.add(PARAM_GRANT_TYPE, PARAM_GRANT_TYPE_VALUE);

        String auth = externalPortalClient + ":" + externalPortalSecret;

        String authHeader = "Basic " + new String(
                Base64.getEncoder().encode(auth.getBytes(StandardCharsets.UTF_8)),
                StandardCharsets.UTF_8);

        RequestEntity request = RequestEntity
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

    public boolean registerUser(Saml2AuthenticatedPrincipal principal) {

        try {
            restTemplate.postForObject(URI.create(externalPortalUrl + API_REGISTER), createRegisterUserDto(principal), Object.class);
            return true;
        } catch (RestClientResponseException e) {
            log.debug("Unable to register user.", e);
            return false;
        }
    }

    private String parseEgn(Saml2AuthenticatedPrincipal principal) {

        String egn = principal.getAttributes().get(ATTRIBUTE_PERSON_IDENTIFIER).get(0).toString();

        return egn.replaceAll("\\D+","");
    }

    private RegisterUserDto createRegisterUserDto(Saml2AuthenticatedPrincipal principal) {

        RegisterUserDto registerUserDto = new RegisterUserDto();
        registerUserDto.setEgn(parseEgn(principal));
        registerUserDto.setName(parseAttribute(principal, ATTRIBUTE_PERSON_NAME));
        registerUserDto.setAddress(parseAttribute(principal, ATTRIBUTE_ADDRESS));
        registerUserDto.setBirthDate(parseAttribute(principal, ATTRIBUTE_BIRTH_DATE));
        registerUserDto.setEmail(parseAttribute(principal, ATTRIBUTE_EMAIL));
        registerUserDto.setBirthPlace(parseAttribute(principal, ATTRIBUTE_BIRTH_PLACE));
        registerUserDto.setGender(parseAttribute(principal, ATTRIBUTE_GENDER));
        registerUserDto.setPhone(parseAttribute(principal, ATTRIBUTE_PHONE));

        return registerUserDto;
    }

    private String parseAttribute(Saml2AuthenticatedPrincipal principal, String attributeName) {

        List<Object> list = principal.getAttributes().get(attributeName);

        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        return list.get(0).toString();
    }
}