package de.escidoc.core.client.exceptions.application;

import java.io.Serializable;

import de.escidoc.core.client.exceptions.EscidocException;

public abstract class ApplicationException extends EscidocException {

    /**
     * 
     */
    private static final long serialVersionUID = -1975554686552131895L;

    /**
     * 
     */
    public ApplicationException() {
    }

    /**
     * @param message
     * @param cause
     */
    public ApplicationException(final String message, final Throwable cause) {
        super(message, cause);
    }

    /**
     * @param httpStatusCode
     * @param httpStatusLine
     * @param httpStatusMsg
     */
    @Deprecated
    public ApplicationException(final int httpStatusCode, final String httpStatusLine, final String httpStatusMsg) {
        super(httpStatusCode, httpStatusLine, httpStatusMsg);
    }

    /**
     * @param message
     * @param cause
     * @param httpStatusCode
     * @param httpStatusLine
     * @param httpStatusMsg
     */
    public ApplicationException(final String message, final Throwable cause, final int httpStatusCode,
        final String httpStatusLine, final String httpStatusMsg) {
        super(message, cause, httpStatusCode, httpStatusLine, httpStatusMsg);
    }
}