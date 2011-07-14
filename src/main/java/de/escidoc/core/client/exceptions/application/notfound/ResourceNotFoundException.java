package de.escidoc.core.client.exceptions.application.notfound;

import java.io.Serializable;

import de.escidoc.core.client.exceptions.application.ApplicationException;

public class ResourceNotFoundException extends ApplicationException implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -1878643584111210160L;

    /**
     * 
     */
    public ResourceNotFoundException() {
    }

    /**
     * @param message
     * @param cause
     */
    public ResourceNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }

    /**
     * @param httpStatusCode
     * @param httpStatusLine
     * @param httpStatusMsg
     */
    @Deprecated
    public ResourceNotFoundException(final int httpStatusCode, final String httpStatusLine, final String httpStatusMsg) {
        super(httpStatusCode, httpStatusLine, httpStatusMsg);
    }

    /**
     * @param message
     * @param cause
     * @param httpStatusCode
     * @param httpStatusLine
     * @param httpStatusMsg
     */
    public ResourceNotFoundException(final String message, final Throwable cause, final int httpStatusCode,
        final String httpStatusLine, final String httpStatusMsg) {
        super(message, cause, httpStatusCode, httpStatusLine, httpStatusMsg);
    }
}