package de.escidoc.core.client.exceptions.application.notfound;

public class ItemReferenceNotFoundException extends ResourceNotFoundException {

    /**
     * 
     */
    private static final long serialVersionUID = -129903165625473370L;

    public ItemReferenceNotFoundException() {
    }

    public ItemReferenceNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }

    @Deprecated
    public ItemReferenceNotFoundException(final int httpStatusCode, final String httpStatusLine,
        final String httpStatusMsg) {
        super(httpStatusCode, httpStatusLine, httpStatusMsg);
    }

    public ItemReferenceNotFoundException(final String message, final Throwable cause, final int httpStatusCode,
        final String httpStatusLine, final String httpStatusMsg) {
        super(message, cause, httpStatusCode, httpStatusLine, httpStatusMsg);
    }
}