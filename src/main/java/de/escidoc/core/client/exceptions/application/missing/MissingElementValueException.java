package de.escidoc.core.client.exceptions.application.missing;

import java.io.Serializable;

/**
 * @author Marko Voß
 * 
 */
public class MissingElementValueException extends MissingParameterException implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -6943689904865340645L;

    /**
     * 
     */
    public MissingElementValueException() {
    }

    /**
     * @param message
     * @param cause
     */
    public MissingElementValueException(final String message, final Throwable cause) {
        super(message, cause);
    }

    /**
     * @param httpStatusCode
     * @param httpStatusLine
     * @param httpStatusMsg
     */
    @Deprecated
    public MissingElementValueException(final int httpStatusCode, final String httpStatusLine,
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
    public MissingElementValueException(final String message, final Throwable cause, final int httpStatusCode,
        final String httpStatusLine, final String httpStatusMsg) {
        super(message, cause, httpStatusCode, httpStatusLine, httpStatusMsg);
    }
}