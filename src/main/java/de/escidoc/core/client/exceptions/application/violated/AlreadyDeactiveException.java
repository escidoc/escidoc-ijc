package de.escidoc.core.client.exceptions.application.violated;

import java.io.Serializable;

public class AlreadyDeactiveException extends RuleViolationException implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 3292241389648694267L;

    public AlreadyDeactiveException() {
    }

    public AlreadyDeactiveException(final String message, final Throwable cause) {
        super(message, cause);
    }

    @Deprecated
    public AlreadyDeactiveException(final int httpStatusCode, final String httpStatusLine, final String httpStatusMsg) {
        super(httpStatusCode, httpStatusLine, httpStatusMsg);
    }

    public AlreadyDeactiveException(final String message, final Throwable cause, final int httpStatusCode,
        final String httpStatusLine, final String httpStatusMsg) {
        super(message, cause, httpStatusCode, httpStatusLine, httpStatusMsg);
    }
}