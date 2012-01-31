package de.escidoc.core.client.exceptions.application.violated;

public class AlreadyDeletedException extends RuleViolationException {

    /**
     * 
     */
    private static final long serialVersionUID = 6149014470939470977L;

    public AlreadyDeletedException() {
    }

    public AlreadyDeletedException(final String message, final Throwable cause) {
        super(message, cause);
    }

    @Deprecated
    public AlreadyDeletedException(final int httpStatusCode, final String httpStatusLine, final String httpStatusMsg) {
        super(httpStatusCode, httpStatusLine, httpStatusMsg);
    }

    public AlreadyDeletedException(final String message, final Throwable cause, final int httpStatusCode,
        final String httpStatusLine, final String httpStatusMsg) {
        super(message, cause, httpStatusCode, httpStatusLine, httpStatusMsg);
    }
}