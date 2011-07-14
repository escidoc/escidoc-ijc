package de.escidoc.core.client.exceptions.application.violated;

import java.io.Serializable;

public class AlreadyWithdrawnException extends RuleViolationException implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 3539819024768679986L;

    public AlreadyWithdrawnException() {
    }

    public AlreadyWithdrawnException(final String message, final Throwable cause) {
        super(message, cause);
    }

    @Deprecated
    public AlreadyWithdrawnException(final int httpStatusCode, final String httpStatusLine, final String httpStatusMsg) {
        super(httpStatusCode, httpStatusLine, httpStatusMsg);
    }

    public AlreadyWithdrawnException(final String message, final Throwable cause, final int httpStatusCode,
        final String httpStatusLine, final String httpStatusMsg) {
        super(message, cause, httpStatusCode, httpStatusLine, httpStatusMsg);
    }
}