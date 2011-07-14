package de.escidoc.core.client.exceptions.application.invalid;

import java.io.Serializable;

/**
 * @author Marko Voß
 * 
 */
public class InvalidContextException extends ValidationException implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 3990997635547725310L;

    /**
     * 
     */
    public InvalidContextException() {
    }

    /**
     * @param message
     * @param cause
     */
    public InvalidContextException(final String message, final Throwable cause) {
        super(message, cause);
    }

    /**
     * @param httpStatusCode
     * @param httpStatusLine
     * @param httpStatusMsg
     */
    @Deprecated
    public InvalidContextException(final int httpStatusCode, final String httpStatusLine, final String httpStatusMsg) {
        super(httpStatusCode, httpStatusLine, httpStatusMsg);
    }

    /**
     * @param message
     * @param cause
     * @param httpStatusCode
     * @param httpStatusLine
     * @param httpStatusMsg
     */
    public InvalidContextException(final String message, final Throwable cause, final int httpStatusCode,
        final String httpStatusLine, final String httpStatusMsg) {
        super(message, cause, httpStatusCode, httpStatusLine, httpStatusMsg);
    }
}