package com.bbva.intranet.utilities.web.ws.vos;

import com.google.gson.annotations.Expose;

public class ResponseVO <T> {

    @Expose
    private String code;
    @Expose
    private String message;
    @Expose
    private T data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
