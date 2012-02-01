package de.escidoc.core.client.exceptions.system;

public class FedoraSystemException extends SystemException {

    /**
     * 
     */
    private static final long serialVersionUID = 9220549368722152424L;

    public FedoraSystemException() {
    }

    public FedoraSystemException(final String message, final Throwable cause) {
        super(message, cause);
    }

    @Deprecated
    public FedoraSystemException(final int httpStatusCode, final String httpStatusLine, final String httpStatusMsg) {
        super(httpStatusCode, httpStatusLine, httpStatusMsg);
    }

    public FedoraSystemException(final String message, final Throwable cause, final int httpStatusCode,
        final String httpStatusLine, final String httpStatusMsg) {
        super(message, cause, httpStatusCode, httpStatusLine, httpStatusMsg);
    }
}