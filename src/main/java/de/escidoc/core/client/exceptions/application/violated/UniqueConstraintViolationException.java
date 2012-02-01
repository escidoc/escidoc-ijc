package de.escidoc.core.client.exceptions.application.violated;

public class UniqueConstraintViolationException extends RuleViolationException {

    /**
     * 
     */
    private static final long serialVersionUID = 4859966734265574182L;

    public UniqueConstraintViolationException() {
    }

    public UniqueConstraintViolationException(final String message, final Throwable cause) {
        super(message, cause);
    }

    @Deprecated
    public UniqueConstraintViolationException(final int httpStatusCode, final String httpStatusLine,
        final String httpStatusMsg) {
        super(httpStatusCode, httpStatusLine, httpStatusMsg);
    }

    public UniqueConstraintViolationException(final String message, final Throwable cause, final int httpStatusCode,
        final String httpStatusLine, final String httpStatusMsg) {
        super(message, cause, httpStatusCode, httpStatusLine, httpStatusMsg);
    }
}