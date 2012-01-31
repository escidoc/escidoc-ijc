package de.escidoc.core.client.exceptions.application.invalid;

public class InvalidPidException extends ValidationException {

    /**
     * 
     */
    private static final long serialVersionUID = -6153679324951314733L;

    /**
     * 
     */
    public InvalidPidException() {
    }

    /**
     * @param message
     * @param cause
     */
    public InvalidPidException(final String message, final Throwable cause) {
        super(message, cause);
    }

    /**
     * @param httpStatusCode
     * @param httpStatusLine
     * @param httpStatusMsg
     */
    @Deprecated
    public InvalidPidException(final int httpStatusCode, final String httpStatusLine, final String httpStatusMsg) {
        super(httpStatusCode, httpStatusLine, httpStatusMsg);
    }

    /**
     * @param message
     * @param cause
     * @param httpStatusCode
     * @param httpStatusLine
     * @param httpStatusMsg
     */
    public InvalidPidException(final String message, final Throwable cause, final int httpStatusCode,
        final String httpStatusLine, final String httpStatusMsg) {
        super(message, cause, httpStatusCode, httpStatusLine, httpStatusMsg);
    }
}