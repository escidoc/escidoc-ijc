package de.escidoc.core.client.exceptions.application.violated;

public class PidAlreadyAssignedException extends RuleViolationException {

    /**
     * 
     */
    private static final long serialVersionUID = -7802905455677176701L;

    public PidAlreadyAssignedException() {
    }

    public PidAlreadyAssignedException(final String message, final Throwable cause) {
        super(message, cause);
    }

    @Deprecated
    public PidAlreadyAssignedException(final int httpStatusCode, final String httpStatusLine, final String httpStatusMsg) {
        super(httpStatusCode, httpStatusLine, httpStatusMsg);
    }

    public PidAlreadyAssignedException(final String message, final Throwable cause, final int httpStatusCode,
        final String httpStatusLine, final String httpStatusMsg) {
        super(message, cause, httpStatusCode, httpStatusLine, httpStatusMsg);
    }
}