package de.escidoc.core.client.exceptions.application.violated;

import java.io.Serializable;

import de.escidoc.core.client.exceptions.application.ApplicationException;

public class RuleViolationException extends ApplicationException implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -570164081619486176L;

    /**
     * 
     */
    public RuleViolationException() {
    }

    /**
     * @param message
     * @param cause
     */
    public RuleViolationException(final String message, final Throwable cause) {
        super(message, cause);
    }

    /**
     * @param httpStatusCode
     * @param httpStatusLine
     * @param httpStatusMsg
     */
    @Deprecated
    public RuleViolationException(final int httpStatusCode, final String httpStatusLine, final String httpStatusMsg) {
        super(httpStatusCode, httpStatusLine, httpStatusMsg);
    }

    /**
     * @param message
     * @param cause
     * @param httpStatusCode
     * @param httpStatusLine
     * @param httpStatusMsg
     */
    public RuleViolationException(final String message, final Throwable cause, final int httpStatusCode,
        final String httpStatusLine, final String httpStatusMsg) {
        super(message, cause, httpStatusCode, httpStatusLine, httpStatusMsg);
    }
}