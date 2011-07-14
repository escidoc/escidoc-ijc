package de.escidoc.core.client.exceptions.application.notfound;

import java.io.Serializable;

public class ContentRelationNotFoundException extends ResourceNotFoundException implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 7278496844679049114L;

    public ContentRelationNotFoundException() {
    }

    public ContentRelationNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }

    @Deprecated
    public ContentRelationNotFoundException(final int httpStatusCode, final String httpStatusLine,
        final String httpStatusMsg) {
        super(httpStatusCode, httpStatusLine, httpStatusMsg);
    }

    public ContentRelationNotFoundException(final String message, final Throwable cause, final int httpStatusCode,
        final String httpStatusLine, final String httpStatusMsg) {
        super(message, cause, httpStatusCode, httpStatusLine, httpStatusMsg);
    }
}