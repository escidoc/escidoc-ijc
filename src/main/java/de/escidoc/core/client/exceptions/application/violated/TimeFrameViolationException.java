package de.escidoc.core.client.exceptions.application.violated;

public class TimeFrameViolationException extends RuleViolationException {

    /**
     * 
     */
    private static final long serialVersionUID = 7144331772869191065L;

    public TimeFrameViolationException() {
    }

    public TimeFrameViolationException(final String message, final Throwable cause) {
        super(message, cause);
    }

    @Deprecated
    public TimeFrameViolationException(final int httpStatusCode, final String httpStatusLine, final String httpStatusMsg) {
        super(httpStatusCode, httpStatusLine, httpStatusMsg);
    }

    public TimeFrameViolationException(final String message, final Throwable cause, final int httpStatusCode,
        final String httpStatusLine, final String httpStatusMsg) {
        super(message, cause, httpStatusCode, httpStatusLine, httpStatusMsg);
    }
}