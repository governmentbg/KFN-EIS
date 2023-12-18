package com.fsc.authorization.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @Value("${external.portal.ui}")
    private String externalPortalUi;

    @ExceptionHandler(Exception.class)
    public String handleException(Exception ex) {

        log.error(ex.getMessage(), ex);

        return "redirect:" + externalPortalUi + "/500";
    }
}
