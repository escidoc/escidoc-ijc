package de.escidoc.core.client.exceptions.application.violated;

import java.io.Serializable;

public class OptimisticLockingException extends RuleViolationException implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 5952498046540039947L;

    public OptimisticLockingException() {
    }

    public OptimisticLockingException(final String message, final Throwable cause) {
        super(message, cause);
    }

    @Deprecated
    public OptimisticLockingException(final int httpStatusCode, final String httpStatusLine, final String httpStatusMsg) {
        super(httpStatusCode, httpStatusLine, httpStatusMsg);
    }

    public OptimisticLockingException(final String message, final Throwable cause, final int httpStatusCode,
        final String httpStatusLine, final String httpStatusMsg) {
        super(message, cause, httpStatusCode, httpStatusLine, httpStatusMsg);
    }
}