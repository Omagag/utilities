package com.bbva.intranet.utilities.vos;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

/**
 * Created by Omar on 3/31/17.
 */
public class HttpResponse {

    private static final Logger logger = LoggerFactory.getLogger(HttpResponse.class);

    private int code;
    private String message;
    private String contentType;
    private String output;
    private Map<String, List<String>> headers;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public Map<String, List<String>> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, List<String>> headers) {
        this.headers = headers;
    }

    public String getHeaderField(String key) {
        for (String header : this.headers.keySet()) {
            if (header != null && header.equalsIgnoreCase(key)) {
                for (String value : this.headers.get(header)) {
                    if (value != null) {
                        return value;
                    }
                }
            }
        }
        return null;
    }

    public void printHeader() {
        if (this.headers != null) {
            logger.debug("+++++ BEGIN RESPONSE HEADER +++++");
            for (String header : headers.keySet()) {
                for (String value : headers.get(header)) {
                    logger.debug(String.format("key: %s value: %s", header, value));
                }
            }
            logger.debug("+++++  END RESPONSE HEADER  +++++");
        }
    }

    @Override
    public String toString() {
        return "HttpResponse{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", contentType='" + contentType + '\'' +
                ", output='" + output + '\'' +
                ", headers=" + headers +
                '}';
    }
}
