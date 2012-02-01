package de.escidoc.core.client.exceptions.application.invalid;

/**
 * @author Marko Voß
 * 
 */
public class InvalidItemStatusException extends ValidationException {

    /**
     * 
     */
    private static final long serialVersionUID = 2180008468509116386L;

    /**
     * 
     */
    public InvalidItemStatusException() {
    }

    /**
     * @param message
     * @param cause
     */
    public InvalidItemStatusException(final String message, final Throwable cause) {
        super(message, cause);
    }

    /**
     * @param httpStatusCode
     * @param httpStatusLine
     * @param httpStatusMsg
     */
    @Deprecated
    public InvalidItemStatusException(final int httpStatusCode, final String httpStatusLine, final String httpStatusMsg) {
        super(httpStatusCode, httpStatusLine, httpStatusMsg);
    }

    /**
     * @param message
     * @param cause
     * @param httpStatusCode
     * @param httpStatusLine
     * @param httpStatusMsg
     */
    public InvalidItemStatusException(final String message, final Throwable cause, final int httpStatusCode,
        final String httpStatusLine, final String httpStatusMsg) {
        super(message, cause, httpStatusCode, httpStatusLine, httpStatusMsg);
    }
}