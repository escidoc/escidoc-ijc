package de.escidoc.core.client.exceptions.application.invalid;

import java.io.Serializable;

/**
 * @author Marko Voß
 * 
 */
public class InvalidXmlException extends ValidationException implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 3894713812556196418L;

    /**
     * 
     */
    public InvalidXmlException() {
    }

    /**
     * @param message
     * @param cause
     */
    public InvalidXmlException(final String message, final Throwable cause) {
        super(message, cause);
    }

    /**
     * @param httpStatusCode
     * @param httpStatusLine
     * @param httpStatusMsg
     */
    @Deprecated
    public InvalidXmlException(final int httpStatusCode, final String httpStatusLine, final String httpStatusMsg) {
        super(httpStatusCode, httpStatusLine, httpStatusMsg);
    }

    /**
     * @param message
     * @param cause
     * @param httpStatusCode
     * @param httpStatusLine
     * @param httpStatusMsg
     */
    public InvalidXmlException(final String message, final Throwable cause, final int httpStatusCode,
        final String httpStatusLine, final String httpStatusMsg) {
        super(message, cause, httpStatusCode, httpStatusLine, httpStatusMsg);
    }
}