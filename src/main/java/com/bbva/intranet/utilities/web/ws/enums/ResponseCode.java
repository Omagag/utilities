package com.bbva.intranet.utilities.web.ws.enums;

public enum ResponseCode {

    SUCCESSFUL("100"),
    GENERIC("200"),
    DATABASE("300"),
    ENOUGH_DATA("101");

    private String code;

    ResponseCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
