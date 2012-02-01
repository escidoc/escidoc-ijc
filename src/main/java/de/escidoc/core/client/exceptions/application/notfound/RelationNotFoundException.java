package de.escidoc.core.client.exceptions.application.notfound;

public class RelationNotFoundException extends ResourceNotFoundException {

    /**
     * 
     */
    private static final long serialVersionUID = 8516025211342748405L;

    public RelationNotFoundException() {
    }

    public RelationNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }

    @Deprecated
    public RelationNotFoundException(final int httpStatusCode, final String httpStatusLine, final String httpStatusMsg) {
        super(httpStatusCode, httpStatusLine, httpStatusMsg);
    }

    public RelationNotFoundException(final String message, final Throwable cause, final int httpStatusCode,
        final String httpStatusLine, final String httpStatusMsg) {
        super(message, cause, httpStatusCode, httpStatusLine, httpStatusMsg);
    }
}