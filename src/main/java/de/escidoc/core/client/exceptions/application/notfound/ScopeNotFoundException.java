package de.escidoc.core.client.exceptions.application.notfound;

public class ScopeNotFoundException extends ResourceNotFoundException {

    /**
     * 
     */
    private static final long serialVersionUID = -2146562332649266380L;

    public ScopeNotFoundException() {
    }

    public ScopeNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }

    @Deprecated
    public ScopeNotFoundException(final int httpStatusCode, final String httpStatusLine, final String httpStatusMsg) {
        super(httpStatusCode, httpStatusLine, httpStatusMsg);
    }

    public ScopeNotFoundException(final String message, final Throwable cause, final int httpStatusCode,
        final String httpStatusLine, final String httpStatusMsg) {
        super(message, cause, httpStatusCode, httpStatusLine, httpStatusMsg);
    }
}