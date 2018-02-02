package com.bbva.intranet.utilities.web.ws.vos;

public class ServiceResponse {

    private String code;
    private String message;
    private Integer httpCode;
    private String httpMessage;

    private Object data;

    public ServiceResponse() {
    }

    public ServiceResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public ServiceResponse(String code, String message, int httpCode, String httpMessage) {
        this.code = code;
        this.message = message;
        this.httpCode = httpCode;
        this.httpMessage = httpMessage;
    }

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

    public Integer getHttpCode() {
        return httpCode;
    }

    public void setHttpCode(Integer httpCode) {
        this.httpCode = httpCode;
    }

    public String getHttpMessage() {
        return httpMessage;
    }

    public void setHttpMessage(String httpMessage) {
        this.httpMessage = httpMessage;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
