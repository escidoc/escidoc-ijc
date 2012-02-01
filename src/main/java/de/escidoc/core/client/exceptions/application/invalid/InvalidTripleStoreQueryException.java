package de.escidoc.core.client.exceptions.application.invalid;

/**
 * @author Marko Voß
 * 
 */
public class InvalidTripleStoreQueryException extends ValidationException {

    /**
     * 
     */
    private static final long serialVersionUID = -2161395260972012427L;

    /**
     * 
     */
    public InvalidTripleStoreQueryException() {
    }

    /**
     * @param message
     * @param cause
     */
    public InvalidTripleStoreQueryException(final String message, final Throwable cause) {
        super(message, cause);
    }

    /**
     * @param httpStatusCode
     * @param httpStatusLine
     * @param httpStatusMsg
     */
    @Deprecated
    public InvalidTripleStoreQueryException(final int httpStatusCode, final String httpStatusLine,
        final String httpStatusMsg) {
        super(httpStatusCode, httpStatusLine, httpStatusMsg);
    }

    /**
     * @param message
     * @param cause
     * @param httpStatusCode
     * @param httpStatusLine
     * @param httpStatusMsg
     */
    public InvalidTripleStoreQueryException(final String message, final Throwable cause, final int httpStatusCode,
        final String httpStatusLine, final String httpStatusMsg) {
        super(message, cause, httpStatusCode, httpStatusLine, httpStatusMsg);
    }
}