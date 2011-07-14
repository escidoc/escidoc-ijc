package de.escidoc.core.client.exceptions.application.missing;

import java.io.Serializable;

/**
 * @author Marko Voß
 * 
 */
public class MissingContentException extends MissingParameterException implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -603248329175608361L;

    /**
     * 
     */
    public MissingContentException() {
    }

    /**
     * @param message
     * @param cause
     */
    public MissingContentException(final String message, final Throwable cause) {
        super(message, cause);
    }

    /**
     * @param httpStatusCode
     * @param httpStatusLine
     * @param httpStatusMsg
     */
    @Deprecated
    public MissingContentException(final int httpStatusCode, final String httpStatusLine, final String httpStatusMsg) {
        super(httpStatusCode, httpStatusLine, httpStatusMsg);
    }

    /**
     * @param message
     * @param cause
     * @param httpStatusCode
     * @param httpStatusLine
     * @param httpStatusMsg
     */
    public MissingContentException(final String message, final Throwable cause, final int httpStatusCode,
        final String httpStatusLine, final String httpStatusMsg) {
        super(message, cause, httpStatusCode, httpStatusLine, httpStatusMsg);
    }
}