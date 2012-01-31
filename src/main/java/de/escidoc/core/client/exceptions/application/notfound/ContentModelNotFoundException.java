package de.escidoc.core.client.exceptions.application.notfound;

public class ContentModelNotFoundException extends ResourceNotFoundException {

    /**
     * 
     */
    private static final long serialVersionUID = 7626905468310973433L;

    public ContentModelNotFoundException() {
    }

    public ContentModelNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }

    @Deprecated
    public ContentModelNotFoundException(final int httpStatusCode, final String httpStatusLine,
        final String httpStatusMsg) {
        super(httpStatusCode, httpStatusLine, httpStatusMsg);
    }

    public ContentModelNotFoundException(final String message, final Throwable cause, final int httpStatusCode,
        final String httpStatusLine, final String httpStatusMsg) {
        super(message, cause, httpStatusCode, httpStatusLine, httpStatusMsg);
    }
}