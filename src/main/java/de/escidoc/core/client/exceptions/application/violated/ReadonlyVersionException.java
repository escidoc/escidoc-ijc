package de.escidoc.core.client.exceptions.application.violated;

public class ReadonlyVersionException extends RuleViolationException {

    /**
     * 
     */
    private static final long serialVersionUID = 8321481493816546959L;

    public ReadonlyVersionException() {
    }

    public ReadonlyVersionException(final String message, final Throwable cause) {
        super(message, cause);
    }

    @Deprecated
    public ReadonlyVersionException(final int httpStatusCode, final String httpStatusLine, final String httpStatusMsg) {
        super(httpStatusCode, httpStatusLine, httpStatusMsg);
    }

    public ReadonlyVersionException(final String message, final Throwable cause, final int httpStatusCode,
        final String httpStatusLine, final String httpStatusMsg) {
        super(message, cause, httpStatusCode, httpStatusLine, httpStatusMsg);
    }
}