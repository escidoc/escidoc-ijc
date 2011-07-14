package de.escidoc.core.client.exceptions.application.violated;

import java.io.Serializable;

public class ScopeContextViolationException extends RuleViolationException implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 2299018606006612226L;

    public ScopeContextViolationException() {
    }

    public ScopeContextViolationException(final String message, final Throwable cause) {
        super(message, cause);
    }

    @Deprecated
    public ScopeContextViolationException(final int httpStatusCode, final String httpStatusLine,
        final String httpStatusMsg) {
        super(httpStatusCode, httpStatusLine, httpStatusMsg);
    }

    public ScopeContextViolationException(final String message, final Throwable cause, final int httpStatusCode,
        final String httpStatusLine, final String httpStatusMsg) {
        super(message, cause, httpStatusCode, httpStatusLine, httpStatusMsg);
    }
}