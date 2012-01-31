package de.escidoc.core.client.exceptions.application.notfound;

public class ContextNotFoundException extends ResourceNotFoundException {

    /**
     * 
     */
    private static final long serialVersionUID = -7267058828362537082L;

    public ContextNotFoundException() {
    }

    public ContextNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }

    @Deprecated
    public ContextNotFoundException(final int httpStatusCode, final String httpStatusLine, final String httpStatusMsg) {
        super(httpStatusCode, httpStatusLine, httpStatusMsg);
    }

    public ContextNotFoundException(final String message, final Throwable cause, final int httpStatusCode,
        final String httpStatusLine, final String httpStatusMsg) {
        super(message, cause, httpStatusCode, httpStatusLine, httpStatusMsg);
    }
}