package de.escidoc.core.client.exceptions.application.notfound;

import java.io.Serializable;

public class RelationTypeNotFoundException extends ResourceNotFoundException implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -1948743264633830627L;

    public RelationTypeNotFoundException() {
    }

    public RelationTypeNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }

    @Deprecated
    public RelationTypeNotFoundException(final int httpStatusCode, final String httpStatusLine,
        final String httpStatusMsg) {
        super(httpStatusCode, httpStatusLine, httpStatusMsg);
    }

    public RelationTypeNotFoundException(final String message, final Throwable cause, final int httpStatusCode,
        final String httpStatusLine, final String httpStatusMsg) {
        super(message, cause, httpStatusCode, httpStatusLine, httpStatusMsg);
    }
}