package com.bbva.intranet.exceptions;

/**
 * Application handled exception.
 *
 * @author Alberto
 */
@Deprecated
public class RestServiceException extends Exception {
    
    private static final long serialVersionUID = -4356668116790063122L;

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