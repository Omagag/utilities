package com.bbva.intranet.utilities.exceptions;

/**
 * Created by Omar on 6/23/17.
 */
public class HttpClientException extends Exception {

    private transient Throwable cause;
    private transient String message;

    public HttpClientException() {
        super();
    }

    public HttpClientException(final Throwable cause) {
        this.cause = cause;
    }

    public HttpClientException(final String message) {
        this.message = message;
    }

    public HttpClientException(final String message, final Throwable cause) {
        this.message = message;
        this.cause = cause;
    }

    @Override
    public Throwable getCause() {
        return this.cause;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
