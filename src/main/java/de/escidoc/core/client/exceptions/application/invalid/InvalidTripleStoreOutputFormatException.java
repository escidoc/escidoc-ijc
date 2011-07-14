package de.escidoc.core.client.exceptions.application.invalid;

import java.io.Serializable;

/**
 * @author Marko Voß
 * 
 */
public class InvalidTripleStoreOutputFormatException extends ValidationException implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -7180831481454932632L;

    /**
     * 
     */
    public InvalidTripleStoreOutputFormatException() {
    }

    /**
     * @param message
     * @param cause
     */
    public InvalidTripleStoreOutputFormatException(final String message, final Throwable cause) {
        super(message, cause);
    }

    /**
     * @param httpStatusCode
     * @param httpStatusLine
     * @param httpStatusMsg
     */
    @Deprecated
    public InvalidTripleStoreOutputFormatException(final int httpStatusCode, final String httpStatusLine,
        final String httpStatusMsg) {
        super(httpStatusCode, httpStatusLine, httpStatusMsg);
    }

    /**
     * @param message
     * @param cause
     * @param httpStatusCode
     * @param httpStatusLine
     * @param httpStatusMsg
     */
    public InvalidTripleStoreOutputFormatException(final String message, final Throwable cause,
        final int httpStatusCode, final String httpStatusLine, final String httpStatusMsg) {
        super(message, cause, httpStatusCode, httpStatusLine, httpStatusMsg);
    }
}