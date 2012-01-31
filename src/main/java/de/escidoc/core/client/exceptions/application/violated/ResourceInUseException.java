package de.escidoc.core.client.exceptions.application.violated;

import de.escidoc.core.client.exceptions.application.ApplicationException;

public class ResourceInUseException extends ApplicationException {

    /**
     * 
     */
    private static final long serialVersionUID = -2941003340139175542L;

    /**
     * 
     */
    public ResourceInUseException() {
    }

    /**
     * @param message
     * @param cause
     */
    public ResourceInUseException(final String message, final Throwable cause) {
        super(message, cause);
    }

    /**
     * @param httpStatusCode
     * @param httpStatusLine
     * @param httpStatusMsg
     */
    @Deprecated
    public ResourceInUseException(final int httpStatusCode, final String httpStatusLine, final String httpStatusMsg) {
        super(httpStatusCode, httpStatusLine, httpStatusMsg);
    }

    /**
     * @param message
     * @param cause
     * @param httpStatusCode
     * @param httpStatusLine
     * @param httpStatusMsg
     */
    public ResourceInUseException(final String message, final Throwable cause, final int httpStatusCode,
        final String httpStatusLine, final String httpStatusMsg) {
        super(message, cause, httpStatusCode, httpStatusLine, httpStatusMsg);
    }
}