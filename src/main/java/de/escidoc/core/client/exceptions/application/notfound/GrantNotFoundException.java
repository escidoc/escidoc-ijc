package de.escidoc.core.client.exceptions.application.notfound;

public class GrantNotFoundException extends ResourceNotFoundException {

    /**
     * 
     */
    private static final long serialVersionUID = -5159145107153741003L;

    public GrantNotFoundException() {
    }

    public GrantNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }

    @Deprecated
    public GrantNotFoundException(final int httpStatusCode, final String httpStatusLine, final String httpStatusMsg) {
        super(httpStatusCode, httpStatusLine, httpStatusMsg);
    }

    public GrantNotFoundException(final String message, final Throwable cause, final int httpStatusCode,
        final String httpStatusLine, final String httpStatusMsg) {
        super(message, cause, httpStatusCode, httpStatusLine, httpStatusMsg);
    }
}