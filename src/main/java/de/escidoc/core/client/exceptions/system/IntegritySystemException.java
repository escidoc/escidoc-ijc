package de.escidoc.core.client.exceptions.system;

public class IntegritySystemException extends SystemException {

    /**
     * 
     */
    private static final long serialVersionUID = -1514157896364865240L;

    public IntegritySystemException() {
    }

    public IntegritySystemException(final String message, final Throwable cause) {
        super(message, cause);
    }

    @Deprecated
    public IntegritySystemException(final int httpStatusCode, final String httpStatusLine, final String httpStatusMsg) {
        super(httpStatusCode, httpStatusLine, httpStatusMsg);
    }

    public IntegritySystemException(final String message, final Throwable cause, final int httpStatusCode,
        final String httpStatusLine, final String httpStatusMsg) {
        super(message, cause, httpStatusCode, httpStatusLine, httpStatusMsg);
    }
}