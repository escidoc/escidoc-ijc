package de.escidoc.core.client.exceptions.application.security;

import java.io.Serializable;

public class AuthenticationException extends SecurityException implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -6393479288677843005L;

    /**
     * 
     */
    public AuthenticationException() {
    }

    /**
     * @param message
     * @param cause
     */
    public AuthenticationException(final String message, final Throwable cause) {
        super(message, cause);
    }

    /**
     * @param httpStatusCode
     * @param httpStatusLine
     * @param httpStatusMsg
     * @param redirectLocation
     */
    @Deprecated
    public AuthenticationException(final int httpStatusCode, final String httpStatusLine, final String httpStatusMsg,
        final String redirectLocation) {
        super(httpStatusCode, httpStatusLine, httpStatusMsg, redirectLocation);
    }

    /**
     * @param message
     * @param cause
     * @param httpStatusCode
     * @param httpStatusLine
     * @param httpStatusMsg
     * @param redirectLocation
     */
    public AuthenticationException(final String message, final Throwable cause, final int httpStatusCode,
        final String httpStatusLine, final String httpStatusMsg, final String redirectLocation) {
        super(message, cause, httpStatusCode, httpStatusLine, httpStatusMsg, redirectLocation);
    }
}