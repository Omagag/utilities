package com.bbva.intranet.vo;

/**
 * Created by XMZ0860 on 05/07/2016.
 */
@Deprecated
public class HttpClientResponse {

    private String code;
    private String message;
    private String error;

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

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
