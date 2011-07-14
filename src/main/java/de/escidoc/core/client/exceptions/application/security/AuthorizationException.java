package de.escidoc.core.client.exceptions.application.security;

import java.io.Serializable;

public class AuthorizationException extends SecurityException implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -7937409338178560085L;

    /**
     * 
     */
    public AuthorizationException() {
    }

    /**
     * @param message
     * @param cause
     */
    public AuthorizationException(final String message, final Throwable cause) {
        super(message, cause);
    }

    /**
     * @param httpStatusCode
     * @param httpStatusLine
     * @param httpStatusMsg
     * @param redirectLocation
     */
    @Deprecated
    public AuthorizationException(final int httpStatusCode, final String httpStatusLine, final String httpStatusMsg,
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
    public AuthorizationException(final String message, final Throwable cause, final int httpStatusCode,
        final String httpStatusLine, final String httpStatusMsg, final String redirectLocation) {
        super(message, cause, httpStatusCode, httpStatusLine, httpStatusMsg, redirectLocation);
    }
}