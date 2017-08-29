package com.bbva.intranet.utilities.exceptions;

/**
 * Application handled exception.
 *
 * @author Alberto
 */
public class RestServiceException extends Exception {
    
    private transient Throwable cause;
    private transient String message;

    public RestServiceException() {
        super();
    }

    public RestServiceException(final String message) {
        this.message = message;
    }

    public RestServiceException(final String message, final Throwable cause) {
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