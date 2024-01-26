package com.fsc.authorization.web.controller;

import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.saml2.provider.service.authentication.Saml2AuthenticatedPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;

import com.fsc.authorization.service.RedisService;
import com.fsc.authorization.service.TokenService;
import com.fsc.authorization.web.dto.RegisterUserDto;
import com.fsc.authorization.web.dto.TokenDto;

@Controller
public class KeyController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private RedisService redisService;

    @Value("${external.portal.ui}")
    private String externalPortalUi;

    private static final String ATTRIBUTE_PERSON_IDENTIFIER = "urn:egov:bg:eauth:2.0:attributes:personIdentifier";
    private static final String ATTRIBUTE_PERSON_NAME = "urn:egov:bg:eauth:2.0:attributes:personName";
    private static final String ATTRIBUTE_EMAIL =  "urn:egov:bg:eauth:2.0:attributes:email";
    private static final String ATTRIBUTE_PHONE = "urn:egov:bg:eauth:2.0:attributes:phone";
    private static final String ATTRIBUTE_GENDER = "urn:egov:bg:eauth:2.0:attributes:gender";
    private static final String ATTRIBUTE_BIRTH_DATE = "urn:egov:bg:eauth:2.0:attributes:dateOfBirth";
    private static final String ATTRIBUTE_BIRTH_PLACE = "urn:egov:bg:eauth:2.0:attributes:placeOfBirth";
    private static final String ATTRIBUTE_ADDRESS = "urn:egov:bg:eauth:2.0:attributes:canonicalResidenceAddress";

    @GetMapping("/getKey")
    public String getKey(@AuthenticationPrincipal Saml2AuthenticatedPrincipal principal,
                         HttpSession session) {

        final StringBuilder builder = new StringBuilder("redirect:" + externalPortalUi);

        String egn = parseEgn(principal);
        String name = parseName(principal);
        TokenDto tokenDto = tokenService.getToken(egn, name);

        if (Objects.isNull(tokenDto)) { // no user for this egn -> try to register user
            RegisterUserDto registerUserDto = createRegisterUserDto(principal);
            boolean userRegisterSuccessfully = tokenService.registerUser(registerUserDto);
            if (userRegisterSuccessfully) {  // the user is registered -> try to get token again
                tokenDto = tokenService.getToken(egn, name);
            }

            if (Objects.isNull(tokenDto)) {  // still no token after attempt to register user -> return to error page
                builder.append("/500");
                session.invalidate();
                return builder.toString();
            }
        }

        String key = redisService.addToken(tokenDto);

        builder.append("?loginKey=" + key);

        session.invalidate();

        return builder.toString();
    }

    private String parseEgn(Saml2AuthenticatedPrincipal principal) {

        String egn = principal.getAttributes().get(ATTRIBUTE_PERSON_IDENTIFIER).get(0).toString();

        // PNOBG-2222222222, PNODE-LFVTV34J2
        return egn.substring(egn.indexOf("-") + 1);
    }

    private String parseName(Saml2AuthenticatedPrincipal principal) {

        return parseAttribute(principal, ATTRIBUTE_PERSON_NAME);
    }

    private String parseAttribute(Saml2AuthenticatedPrincipal principal, String attributeName) {

        List<Object> list = principal.getAttributes().get(attributeName);

        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        return list.get(0).toString();
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
}