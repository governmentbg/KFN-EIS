package com.fsc.external.portal.error;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fsc.exception.library.exception.enums.ErrorCodes;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum DocumentSigningErrorDefinition implements ErrorCodes {

    CODE_001(600_101, "Документът не е подписан!");

    private final Integer systemCode;
    private final String message;

    DocumentSigningErrorDefinition(Integer systemCode, String message) {
        this.systemCode = systemCode;
        this.message = message;
    }

    @Override
    public Integer getSystemCode() {
        return systemCode;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
