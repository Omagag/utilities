package com.bbva.intranet.utilities.web.ws.enums;

public enum CodeError{
    GENERIC("200"),
    DATABASE("300"),
    ENOUGH_DATA("101");


    private String code;

    CodeError(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
