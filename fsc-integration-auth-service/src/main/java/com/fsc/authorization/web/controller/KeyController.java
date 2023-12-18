package com.fsc.authorization.web.controller;

import com.fsc.authorization.service.TokenService;
import com.fsc.authorization.web.dto.TokenDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.saml2.provider.service.authentication.Saml2AuthenticatedPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Objects;
import javax.servlet.http.HttpSession;

@Controller
public class KeyController {

    @Autowired
    private TokenService tokenService;

    @Value("${external.portal.ui}")
    private String externalPortalUi;

    @GetMapping("/getKey")
    public String getKey(@AuthenticationPrincipal Saml2AuthenticatedPrincipal principal,
                         HttpSession session,
                         @RequestParam(value = "callbackUrlPath", required = false) String callbackUrlPath) {

        final StringBuilder builder = new StringBuilder("redirect:" + externalPortalUi);

        TokenDto tokenDto = tokenService.getToken(principal);

        if (Objects.isNull(tokenDto)) { // no user for this egn -> try to register user
            boolean userRegisterSuccessfully = tokenService.registerUser(principal);
            if (userRegisterSuccessfully) {  // the user is registered -> try to get token again
                tokenDto = tokenService.getToken(principal);
            }

            if (Objects.isNull(tokenDto)) {  // still no token after attempt to register user -> return to error page
                builder.append("/500");
                session.invalidate();
                return builder.toString();
            }
        }

        String key = tokenService.addToken(tokenDto);

        if (StringUtils.isNotBlank(callbackUrlPath)) {
            builder.append(callbackUrlPath);
        }
        builder.append("?loginKey=" + key);

        session.invalidate();

        return builder.toString();
    }
}