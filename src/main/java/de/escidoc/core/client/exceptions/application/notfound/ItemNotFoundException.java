package de.escidoc.core.client.exceptions.application.notfound;

public class ItemNotFoundException extends ResourceNotFoundException {

    /**
     * 
     */
    private static final long serialVersionUID = 6839182069614643239L;

    public ItemNotFoundException() {
    }

    public ItemNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }

    @Deprecated
    public ItemNotFoundException(final int httpStatusCode, final String httpStatusLine, final String httpStatusMsg) {
        super(httpStatusCode, httpStatusLine, httpStatusMsg);
    }

    public ItemNotFoundException(final String message, final Throwable cause, final int httpStatusCode,
        final String httpStatusLine, final String httpStatusMsg) {
        super(message, cause, httpStatusCode, httpStatusLine, httpStatusMsg);
    }
}