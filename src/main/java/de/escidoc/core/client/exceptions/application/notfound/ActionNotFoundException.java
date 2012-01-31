package de.escidoc.core.client.exceptions.application.notfound;

public class ActionNotFoundException extends ResourceNotFoundException {

    /**
     * 
     */
    private static final long serialVersionUID = -3707671865823799015L;

    public ActionNotFoundException() {
    }

    public ActionNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }

    @Deprecated
    public ActionNotFoundException(final int httpStatusCode, final String httpStatusLine, final String httpStatusMsg) {
        super(httpStatusCode, httpStatusLine, httpStatusMsg);
    }

    public ActionNotFoundException(final String message, final Throwable cause, final int httpStatusCode,
        final String httpStatusLine, final String httpStatusMsg) {
        super(message, cause, httpStatusCode, httpStatusLine, httpStatusMsg);
    }
}