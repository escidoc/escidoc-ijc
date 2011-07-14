package de.escidoc.core.client.exceptions.application.missing;

import java.io.Serializable;

/**
 * @author Marko Voß
 * 
 */
public class MissingAttributeValueException extends MissingParameterException implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -3762453411325025637L;

    /**
     * 
     */
    public MissingAttributeValueException() {
    }

    /**
     * @param message
     * @param cause
     */
    public MissingAttributeValueException(final String message, final Throwable cause) {
        super(message, cause);
    }

    /**
     * @param httpStatusCode
     * @param httpStatusLine
     * @param httpStatusMsg
     */
    @Deprecated
    public MissingAttributeValueException(final int httpStatusCode, final String httpStatusLine,
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
    public MissingAttributeValueException(final String message, final Throwable cause, final int httpStatusCode,
        final String httpStatusLine, final String httpStatusMsg) {
        super(message, cause, httpStatusCode, httpStatusLine, httpStatusMsg);
    }
}