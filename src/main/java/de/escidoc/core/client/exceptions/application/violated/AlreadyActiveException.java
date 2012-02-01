package de.escidoc.core.client.exceptions.application.violated;

public class AlreadyActiveException extends RuleViolationException {

    /**
     * 
     */
    private static final long serialVersionUID = 558432479291769595L;

    public AlreadyActiveException() {
    }

    public AlreadyActiveException(final String message, final Throwable cause) {
        super(message, cause);
    }

    @Deprecated
    public AlreadyActiveException(final int httpStatusCode, final String httpStatusLine, final String httpStatusMsg) {
        super(httpStatusCode, httpStatusLine, httpStatusMsg);
    }

    public AlreadyActiveException(final String message, final Throwable cause, final int httpStatusCode,
        final String httpStatusLine, final String httpStatusMsg) {
        super(message, cause, httpStatusCode, httpStatusLine, httpStatusMsg);
    }
}