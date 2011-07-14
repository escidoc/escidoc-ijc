package de.escidoc.core.client.exceptions.application.invalid;

import java.io.Serializable;

import de.escidoc.core.client.exceptions.application.ApplicationException;

/**
 * @author Marko Voß
 * 
 */
public class TmeException extends ApplicationException implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 6063751457925713357L;

    /**
     * 
     */
    public TmeException() {
    }

    /**
     * @param message
     * @param cause
     */
    public TmeException(final String message, final Throwable cause) {
        super(message, cause);
    }

    /**
     * @param httpStatusCode
     * @param httpStatusLine
     * @param httpStatusMsg
     */
    @Deprecated
    public TmeException(final int httpStatusCode, final String httpStatusLine, final String httpStatusMsg) {
        super(httpStatusCode, httpStatusLine, httpStatusMsg);
    }

    /**
     * @param message
     * @param cause
     * @param httpStatusCode
     * @param httpStatusLine
     * @param httpStatusMsg
     */
    public TmeException(final String message, final Throwable cause, final int httpStatusCode,
        final String httpStatusLine, final String httpStatusMsg) {
        super(message, cause, httpStatusCode, httpStatusLine, httpStatusMsg);
    }
}
