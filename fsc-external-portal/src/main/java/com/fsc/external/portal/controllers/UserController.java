package com.fsc.external.portal.controllers;

import com.fsc.common.addon.entity.User;
import com.fsc.external.portal.dtos.RegisterUserRequest;
import com.fsc.external.portal.dtos.UserContextResponse;
import com.fsc.external.portal.dtos.UserResponse;
import com.fsc.external.portal.services.ExternalUserService;
import com.fsc.external.portal.services.UserService;
import io.jmix.core.security.Authenticated;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;
import javax.validation.Valid;

@RestController
@RequestMapping("/rest/entities/User")
@Validated
public class UserController {

    private final ExternalUserService externalUserService;
    private final UserService userService;

    public UserController(
            final ExternalUserService externalUserService,
            final UserService userService) {
        this.externalUserService = externalUserService;
        this.userService = userService;
    }

    @Operation(summary = "Get user info for currently logged user")
    @GetMapping()
    public ResponseEntity<UserResponse> getLoggedUserInfo() {
        return ResponseEntity.ok(externalUserService.getLoggedUserInfo());
    }

    @Operation(summary = "Get user context for currently logged user")
    @GetMapping("user-contexts")
    public ResponseEntity<List<UserContextResponse>> getLoggedUserContext() {
        return ResponseEntity.ok(externalUserService.getLoggedUserContext());
    }

    @Operation(summary = "Register new user")
    @PostMapping("/register")
    @Authenticated
    public ResponseEntity<Void> registerUser(@Valid @RequestBody RegisterUserRequest registerUserRequest) {

        User user = userService.getUserByEgn(registerUserRequest.getEgn());

        if (Objects.isNull(user)) {
            externalUserService.registerUser(registerUserRequest);
        }

        return ResponseEntity.ok().build();
    }
}