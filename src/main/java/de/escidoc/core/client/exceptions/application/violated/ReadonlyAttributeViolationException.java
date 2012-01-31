package de.escidoc.core.client.exceptions.application.violated;

public class ReadonlyAttributeViolationException extends ReadonlyViolationException {

    /**
     * 
     */
    private static final long serialVersionUID = 5861006781677536241L;

    public ReadonlyAttributeViolationException() {
    }

    public ReadonlyAttributeViolationException(final String message, final Throwable cause) {
        super(message, cause);
    }

    @Deprecated
    public ReadonlyAttributeViolationException(final int httpStatusCode, final String httpStatusLine,
        final String httpStatusMsg) {
        super(httpStatusCode, httpStatusLine, httpStatusMsg);
    }

    public ReadonlyAttributeViolationException(final String message, final Throwable cause, final int httpStatusCode,
        final String httpStatusLine, final String httpStatusMsg) {
        super(message, cause, httpStatusCode, httpStatusLine, httpStatusMsg);
    }
}