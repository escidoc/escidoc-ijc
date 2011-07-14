package de.escidoc.core.client.exceptions.application.missing;

import java.io.Serializable;

/**
 * @author Marko Voß
 * 
 */
public class MissingMdRecordException extends MissingParameterException implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -6123245273623709103L;

    /**
     * 
     */
    public MissingMdRecordException() {
    }

    /**
     * @param message
     * @param cause
     */
    public MissingMdRecordException(final String message, final Throwable cause) {
        super(message, cause);
    }

    /**
     * @param httpStatusCode
     * @param httpStatusLine
     * @param httpStatusMsg
     */
    @Deprecated
    public MissingMdRecordException(final int httpStatusCode, final String httpStatusLine, final String httpStatusMsg) {
        super(httpStatusCode, httpStatusLine, httpStatusMsg);
    }

    /**
     * @param message
     * @param cause
     * @param httpStatusCode
     * @param httpStatusLine
     * @param httpStatusMsg
     */
    public MissingMdRecordException(final String message, final Throwable cause, final int httpStatusCode,
        final String httpStatusLine, final String httpStatusMsg) {
        super(message, cause, httpStatusCode, httpStatusLine, httpStatusMsg);
    }
}