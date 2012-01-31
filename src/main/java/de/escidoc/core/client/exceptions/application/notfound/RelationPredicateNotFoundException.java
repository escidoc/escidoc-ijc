package de.escidoc.core.client.exceptions.application.notfound;

public class RelationPredicateNotFoundException extends ResourceNotFoundException {

    /**
     * 
     */
    private static final long serialVersionUID = 671308582510420083L;

    public RelationPredicateNotFoundException() {
    }

    public RelationPredicateNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }

    @Deprecated
    public RelationPredicateNotFoundException(final int httpStatusCode, final String httpStatusLine,
        final String httpStatusMsg) {
        super(httpStatusCode, httpStatusLine, httpStatusMsg);
    }

    public RelationPredicateNotFoundException(final String message, final Throwable cause, final int httpStatusCode,
        final String httpStatusLine, final String httpStatusMsg) {
        super(message, cause, httpStatusCode, httpStatusLine, httpStatusMsg);
    }
}