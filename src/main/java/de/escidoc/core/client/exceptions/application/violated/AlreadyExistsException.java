package de.escidoc.core.client.exceptions.application.violated;

public class AlreadyExistsException extends RuleViolationException {

    /**
     * 
     */
    private static final long serialVersionUID = -424757439955870934L;

    public AlreadyExistsException() {
    }

    public AlreadyExistsException(final String message, final Throwable cause) {
        super(message, cause);
    }

    @Deprecated
    public AlreadyExistsException(final int httpStatusCode, final String httpStatusLine, final String httpStatusMsg) {
        super(httpStatusCode, httpStatusLine, httpStatusMsg);
    }

    public AlreadyExistsException(final String message, final Throwable cause, final int httpStatusCode,
        final String httpStatusLine, final String httpStatusMsg) {
        super(message, cause, httpStatusCode, httpStatusLine, httpStatusMsg);
    }
}