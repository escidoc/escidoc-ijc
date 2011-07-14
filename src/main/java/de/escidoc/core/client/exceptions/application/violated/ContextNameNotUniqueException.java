package de.escidoc.core.client.exceptions.application.violated;

import java.io.Serializable;

public class ContextNameNotUniqueException extends RuleViolationException implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -7164677630100088143L;

    public ContextNameNotUniqueException() {
    }

    public ContextNameNotUniqueException(final String message, final Throwable cause) {
        super(message, cause);
    }

    @Deprecated
    public ContextNameNotUniqueException(final int httpStatusCode, final String httpStatusLine,
        final String httpStatusMsg) {
        super(httpStatusCode, httpStatusLine, httpStatusMsg);
    }

    public ContextNameNotUniqueException(final String message, final Throwable cause, final int httpStatusCode,
        final String httpStatusLine, final String httpStatusMsg) {
        super(message, cause, httpStatusCode, httpStatusLine, httpStatusMsg);
    }
}