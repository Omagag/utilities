package com.bbva.intranet.vo;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Admin on 11/02/2016.
 */
@Deprecated
public class HttpClientData {

    // Attrs to invokes the Services
    private String url;
    private String payload;
    private String contentType;
    private String encoding;
    private int timeout;

    private Map<String, String> headers;

    // Extra data
    private boolean validateCerts;

    public HttpClientData() {}

    public HttpClientData(String url) {
        this.url = url;
    }

    public HttpClientData(String url, String payload) {
        this.url = url;
        this.payload = payload;
    }

    public HttpClientData(String url, String payload, String contentType, String encoding) {
        this.url = url;
        this.payload = payload;
        this.contentType = contentType;
        this.encoding = encoding;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public boolean isValidateCerts() {
        return validateCerts;
    }

    public void setValidateCerts(boolean validateCerts) {
        this.validateCerts = validateCerts;
    }

    public void addHeader(String key, String value) {
        if (this.headers == null) {
            this.headers = new HashMap<String, String>();
        }
        this.headers.put(key, value);
    }

    public void removeHeader(String key) {
        if (this.headers != null) {
            this.headers.remove(key);
        }
    }
}
