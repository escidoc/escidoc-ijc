package de.escidoc.core.client.exceptions.application.invalid;

import java.io.Serializable;

/**
 * @author Marko Voß
 * 
 */
public class InvalidStatusException extends ValidationException implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -2920610481426988438L;

    /**
     * 
     */
    public InvalidStatusException() {
    }

    /**
     * @param message
     * @param cause
     */
    public InvalidStatusException(final String message, final Throwable cause) {
        super(message, cause);
    }

    /**
     * @param httpStatusCode
     * @param httpStatusLine
     * @param httpStatusMsg
     */
    @Deprecated
    public InvalidStatusException(final int httpStatusCode, final String httpStatusLine, final String httpStatusMsg) {
        super(httpStatusCode, httpStatusLine, httpStatusMsg);
    }

    /**
     * @param message
     * @param cause
     * @param httpStatusCode
     * @param httpStatusLine
     * @param httpStatusMsg
     */
    public InvalidStatusException(final String message, final Throwable cause, final int httpStatusCode,
        final String httpStatusLine, final String httpStatusMsg) {
        super(message, cause, httpStatusCode, httpStatusLine, httpStatusMsg);
    }
}