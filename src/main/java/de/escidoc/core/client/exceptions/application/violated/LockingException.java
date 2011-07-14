package de.escidoc.core.client.exceptions.application.violated;

import java.io.Serializable;

public class LockingException extends RuleViolationException implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -8256556809284575636L;

    public LockingException() {
    }

    public LockingException(final String message, final Throwable cause) {
        super(message, cause);
    }

    @Deprecated
    public LockingException(final int httpStatusCode, final String httpStatusLine, final String httpStatusMsg) {
        super(httpStatusCode, httpStatusLine, httpStatusMsg);
    }

    public LockingException(final String message, final Throwable cause, final int httpStatusCode,
        final String httpStatusLine, final String httpStatusMsg) {
        super(message, cause, httpStatusCode, httpStatusLine, httpStatusMsg);
    }
}