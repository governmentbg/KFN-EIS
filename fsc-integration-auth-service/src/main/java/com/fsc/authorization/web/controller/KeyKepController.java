package com.fsc.authorization.web.controller;

import java.util.Objects;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.fsc.authorization.service.KepAuthService;
import com.fsc.authorization.service.RedisService;
import com.fsc.authorization.service.TokenService;
import com.fsc.authorization.util.KepConstants;
import com.fsc.authorization.web.dto.RegisterUserDto;
import com.fsc.authorization.web.dto.UserInfoDto;

@RestController
public class KeyKepController {

    @Value("${stampit.url}")
    private String stampitUrl;

    @Value("${stampit.publicKey}")
    private String publicKey;

    @Value("${auth.url}")
    private String authUrl;

    @Value("${external.portal.ui}")
    private String externalPortalUi;

    @Autowired
    private KepAuthService authService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private RedisService redisService;

    @GetMapping ("/getKepKey")
    public ModelAndView getKey() {
        final var builder = new StringBuilder("redirect:");
        builder.append(stampitUrl).append("/authorize");
        builder.append("?client_id=").append(publicKey);
        builder.append("&scope=").append(KepConstants.EXTERNAL_AUTH_SCOPE);
        builder.append("&redirect_uri=").append(authUrl).append(KepConstants.AUTH_ENDPOINT);
        builder.append("&lang=bg");

        return new ModelAndView(builder.toString());
    }

    @GetMapping (value = KepConstants.AUTH_ENDPOINT, params = {"code", "state"})
    public ModelAndView auth(
            @RequestParam(value = "code") String code,
            @RequestParam(value = "state") String state,
            HttpSession session) {

                
        var externalToken = authService.getToken(code);
        var userInfo = authService.getUserInfo(externalToken.token());
                
        final var builder = new StringBuilder("redirect:");
        builder.append(externalPortalUi);
        var tokenDto = tokenService.getToken(userInfo.egn(), userInfo.name());

        if (Objects.isNull(tokenDto)) { // no user for this egn -> try to register user
            RegisterUserDto registerUserDto = createRegisterUserDto(userInfo);
            boolean userRegisterSuccessfully = tokenService.registerUser(registerUserDto);
            if (userRegisterSuccessfully) {  // the user is registered -> try to get token again
                tokenDto = tokenService.getToken(userInfo.egn(), userInfo.name());
            }

            if (Objects.isNull(tokenDto)) {  // still no token after attempt to register user -> return to error page
                builder.append("/500");
                session.invalidate();
                return new ModelAndView(builder.toString());
            }
        }

        String key = redisService.addToken(tokenDto);

        builder.append("?loginKey=" + key);

        session.invalidate();

        return new ModelAndView(builder.toString());
    }

    @GetMapping (value = KepConstants.AUTH_ENDPOINT, params = {"error", "error_reason", "error_description"})
    public ModelAndView authWithError(
            @RequestParam(value = "error") String error,
            @RequestParam(value = "error_reason") String errorReason,
            @RequestParam(value = "error_description") String errorDescription) {

        final var builder = new StringBuilder("redirect:");
        builder.append(externalPortalUi).append("/403");

        builder.append("?error=").append(error);
        builder.append("&error_reason=").append(errorReason);
        builder.append("&error_description=").append(errorDescription);

        return new ModelAndView(builder.toString());
    }

    private RegisterUserDto createRegisterUserDto(UserInfoDto userInfo) {
        RegisterUserDto registerUserDto = new RegisterUserDto();
        registerUserDto.setEgn(userInfo.egn());
        registerUserDto.setName(userInfo.name());
        registerUserDto.setEmail(userInfo.mail());

        return registerUserDto;
    }
}
