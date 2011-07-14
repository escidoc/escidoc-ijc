package de.escidoc.core.client.exceptions.application.notfound;

import java.io.Serializable;

public class ReferencedResourceNotFoundException extends ResourceNotFoundException implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -5644412510966417806L;

    public ReferencedResourceNotFoundException() {
    }

    public ReferencedResourceNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }

    @Deprecated
    public ReferencedResourceNotFoundException(final int httpStatusCode, final String httpStatusLine,
        final String httpStatusMsg) {
        super(httpStatusCode, httpStatusLine, httpStatusMsg);
    }

    public ReferencedResourceNotFoundException(final String message, final Throwable cause, final int httpStatusCode,
        final String httpStatusLine, final String httpStatusMsg) {
        super(message, cause, httpStatusCode, httpStatusLine, httpStatusMsg);
    }
}