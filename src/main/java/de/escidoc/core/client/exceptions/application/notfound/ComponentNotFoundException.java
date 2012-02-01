package de.escidoc.core.client.exceptions.application.notfound;

public class ComponentNotFoundException extends ResourceNotFoundException {

    /**
     * 
     */
    private static final long serialVersionUID = -1719503192645200759L;

    public ComponentNotFoundException() {
    }

    public ComponentNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }

    @Deprecated
    public ComponentNotFoundException(final int httpStatusCode, final String httpStatusLine, final String httpStatusMsg) {
        super(httpStatusCode, httpStatusLine, httpStatusMsg);
    }

    public ComponentNotFoundException(final String message, final Throwable cause, final int httpStatusCode,
        final String httpStatusLine, final String httpStatusMsg) {
        super(message, cause, httpStatusCode, httpStatusLine, httpStatusMsg);
    }
}