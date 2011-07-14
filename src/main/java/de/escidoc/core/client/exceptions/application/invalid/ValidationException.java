package de.escidoc.core.client.exceptions.application.invalid;

import java.io.Serializable;

import de.escidoc.core.client.exceptions.application.ApplicationException;

/**
 * @author Marko Voß
 * 
 */
public class ValidationException extends ApplicationException implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 4083624284062023807L;

    /**
     * 
     */
    public ValidationException() {
    }

    /**
     * @param message
     * @param cause
     */
    public ValidationException(final String message, final Throwable cause) {
        super(message, cause);
    }

    /**
     * @param httpStatusCode
     * @param httpStatusLine
     * @param httpStatusMsg
     */
    @Deprecated
    public ValidationException(final int httpStatusCode, final String httpStatusLine, final String httpStatusMsg) {
        super(httpStatusCode, httpStatusLine, httpStatusMsg);
    }

    /**
     * @param message
     * @param cause
     * @param httpStatusCode
     * @param httpStatusLine
     * @param httpStatusMsg
     */
    public ValidationException(final String message, final Throwable cause, final int httpStatusCode,
        final String httpStatusLine, final String httpStatusMsg) {
        super(message, cause, httpStatusCode, httpStatusLine, httpStatusMsg);
    }
}