package com.fsc.external.portal.error;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fsc.exception.library.exception.enums.ErrorCodes;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ServiceRequestErrorDefinition implements ErrorCodes {

    CODE_001(600_001, "Подаване на чуждо заявление е забранена операция!"),
    CODE_002(600_002, "Подаване на вече подадено заявление е забранена операция!"),
    CODE_003(600_003, "Достъпът отказан."),
    CODE_004(600_004, "Заявлението не е попълнено.");

    private final Integer systemCode;
    private final String message;

    ServiceRequestErrorDefinition(Integer systemCode, String message) {
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
