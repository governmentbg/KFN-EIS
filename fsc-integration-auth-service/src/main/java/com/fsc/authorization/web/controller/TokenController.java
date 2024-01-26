package com.fsc.authorization.web.controller;

import com.fsc.authorization.service.RedisService;
import com.fsc.authorization.web.dto.TokenDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/token")
public class TokenController {

    @Autowired
    private RedisService redisService;

    @GetMapping
    public ResponseEntity<TokenDto> getTokenByKey(@RequestParam("loginKey") String loginKey) {
        return ResponseEntity.ok(redisService.getToken(loginKey));
    }
}