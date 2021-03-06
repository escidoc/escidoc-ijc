package de.escidoc.core.client.exceptions.application.invalid;

/**
 * @author Marko Voß
 * 
 */
public class InvalidResourceException extends ValidationException {

    /**
     * 
     */
    private static final long serialVersionUID = 2624038064849636146L;

    /**
     * 
     */
    public InvalidResourceException() {
    }

    /**
     * @param message
     * @param cause
     */
    public InvalidResourceException(final String message, final Throwable cause) {
        super(message, cause);
    }

    /**
     * @param httpStatusCode
     * @param httpStatusLine
     * @param httpStatusMsg
     */
    @Deprecated
    public InvalidResourceException(final int httpStatusCode, final String httpStatusLine, final String httpStatusMsg) {
        super(httpStatusCode, httpStatusLine, httpStatusMsg);
    }

    /**
     * @param message
     * @param cause
     * @param httpStatusCode
     * @param httpStatusLine
     * @param httpStatusMsg
     */
    public InvalidResourceException(final String message, final Throwable cause, final int httpStatusCode,
        final String httpStatusLine, final String httpStatusMsg) {
        super(message, cause, httpStatusCode, httpStatusLine, httpStatusMsg);
    }
}