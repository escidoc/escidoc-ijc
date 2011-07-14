package de.escidoc.core.client.exceptions.application.notfound;

import java.io.Serializable;

public class IngestionSourceNotFoundException extends ResourceNotFoundException implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -1629806646866628236L;

    public IngestionSourceNotFoundException() {
    }

    public IngestionSourceNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }

    @Deprecated
    public IngestionSourceNotFoundException(final int httpStatusCode, final String httpStatusLine,
        final String httpStatusMsg) {
        super(httpStatusCode, httpStatusLine, httpStatusMsg);
    }

    public IngestionSourceNotFoundException(final String message, final Throwable cause, final int httpStatusCode,
        final String httpStatusLine, final String httpStatusMsg) {
        super(message, cause, httpStatusCode, httpStatusLine, httpStatusMsg);
    }
}