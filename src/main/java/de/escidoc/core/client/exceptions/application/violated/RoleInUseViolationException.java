package de.escidoc.core.client.exceptions.application.violated;

public class RoleInUseViolationException extends RuleViolationException {

    /**
     * 
     */
    private static final long serialVersionUID = -7981351332097846481L;

    public RoleInUseViolationException() {
    }

    public RoleInUseViolationException(final String message, final Throwable cause) {
        super(message, cause);
    }

    @Deprecated
    public RoleInUseViolationException(final int httpStatusCode, final String httpStatusLine, final String httpStatusMsg) {
        super(httpStatusCode, httpStatusLine, httpStatusMsg);
    }

    public RoleInUseViolationException(final String message, final Throwable cause, final int httpStatusCode,
        final String httpStatusLine, final String httpStatusMsg) {
        super(message, cause, httpStatusCode, httpStatusLine, httpStatusMsg);
    }
}