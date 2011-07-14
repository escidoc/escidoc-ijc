package de.escidoc.core.client.exceptions.application.violated;

import java.io.Serializable;

public class ReadonlyViolationException extends RuleViolationException implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -4585843107358874081L;

    public ReadonlyViolationException() {
    }

    public ReadonlyViolationException(final String message, final Throwable cause) {
        super(message, cause);
    }

    @Deprecated
    public ReadonlyViolationException(final int httpStatusCode, final String httpStatusLine, final String httpStatusMsg) {
        super(httpStatusCode, httpStatusLine, httpStatusMsg);
    }

    public ReadonlyViolationException(final String message, final Throwable cause, final int httpStatusCode,
        final String httpStatusLine, final String httpStatusMsg) {
        super(message, cause, httpStatusCode, httpStatusLine, httpStatusMsg);
    }
}