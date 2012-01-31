package de.escidoc.core.client.exceptions.system;

public class WebserverSystemException extends SystemException {

    /**
     * 
     */
    private static final long serialVersionUID = 3446510218458783255L;

    public WebserverSystemException() {
    }

    public WebserverSystemException(final String message, final Throwable cause) {
        super(message, cause);
    }

    @Deprecated
    public WebserverSystemException(final int httpStatusCode, final String httpStatusLine, final String httpStatusMsg) {
        super(httpStatusCode, httpStatusLine, httpStatusMsg);
    }

    public WebserverSystemException(final String message, final Throwable cause, final int httpStatusCode,
        final String httpStatusLine, final String httpStatusMsg) {
        super(message, cause, httpStatusCode, httpStatusLine, httpStatusMsg);
    }
}