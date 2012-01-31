package de.escidoc.core.client.exceptions.system;

public class ApplicationServerSystemException extends SystemException {

    /**
     * 
     */
    private static final long serialVersionUID = -3781660447160530824L;

    public ApplicationServerSystemException() {
    }

    public ApplicationServerSystemException(final String message, final Throwable cause) {
        super(message, cause);
    }

    @Deprecated
    public ApplicationServerSystemException(final int httpStatusCode, final String httpStatusLine,
        final String httpStatusMsg) {
        super(httpStatusCode, httpStatusLine, httpStatusMsg);
    }

    public ApplicationServerSystemException(final String message, final Throwable cause, final int httpStatusCode,
        final String httpStatusLine, final String httpStatusMsg) {
        super(message, cause, httpStatusCode, httpStatusLine, httpStatusMsg);
    }
}