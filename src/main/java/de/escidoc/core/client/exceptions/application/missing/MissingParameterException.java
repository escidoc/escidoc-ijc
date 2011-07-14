package de.escidoc.core.client.exceptions.application.missing;

import java.io.Serializable;

import de.escidoc.core.client.exceptions.application.ApplicationException;

public class MissingParameterException extends ApplicationException implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 9031462602137247420L;

    /**
     * 
     */
    public MissingParameterException() {
    }

    /**
     * @param message
     * @param cause
     */
    public MissingParameterException(final String message, final Throwable cause) {
        super(message, cause);
    }

    /**
     * @param httpStatusCode
     * @param httpStatusLine
     * @param httpStatusMsg
     */
    @Deprecated
    public MissingParameterException(final int httpStatusCode, final String httpStatusLine, final String httpStatusMsg) {
        super(httpStatusCode, httpStatusLine, httpStatusMsg);
    }

    /**
     * @param message
     * @param cause
     * @param httpStatusCode
     * @param httpStatusLine
     * @param httpStatusMsg
     */
    public MissingParameterException(final String message, final Throwable cause, final int httpStatusCode,
        final String httpStatusLine, final String httpStatusMsg) {
        super(message, cause, httpStatusCode, httpStatusLine, httpStatusMsg);
    }
}