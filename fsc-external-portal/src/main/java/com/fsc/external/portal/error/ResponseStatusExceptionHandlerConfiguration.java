package com.fsc.external.portal.error;

import com.fsc.exception.library.exception.GlobalExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import javax.annotation.PostConstruct;

@Component
public class ResponseStatusExceptionHandlerConfiguration {

    @Autowired
    private GlobalExceptionHandler globalExceptionHandler;

    @PostConstruct
    public void init() {
        globalExceptionHandler.addRegisterExceptionHandler(ResponseStatusException.class, (ResponseStatusException exception) ->
                ResponseEntity.status(exception.getStatus()).build()
        );
    }

}
