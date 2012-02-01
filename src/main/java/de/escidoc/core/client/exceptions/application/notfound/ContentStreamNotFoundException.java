package de.escidoc.core.client.exceptions.application.notfound;

public class ContentStreamNotFoundException extends ResourceNotFoundException {

    /**
     * 
     */
    private static final long serialVersionUID = 1196141418852385460L;

    public ContentStreamNotFoundException() {
    }

    public ContentStreamNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }

    @Deprecated
    public ContentStreamNotFoundException(final int httpStatusCode, final String httpStatusLine,
        final String httpStatusMsg) {
        super(httpStatusCode, httpStatusLine, httpStatusMsg);
    }

    public ContentStreamNotFoundException(final String message, final Throwable cause, final int httpStatusCode,
        final String httpStatusLine, final String httpStatusMsg) {
        super(message, cause, httpStatusCode, httpStatusLine, httpStatusMsg);
    }
}