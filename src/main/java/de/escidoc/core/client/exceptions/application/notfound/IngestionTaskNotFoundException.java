package de.escidoc.core.client.exceptions.application.notfound;

import java.io.Serializable;

public class IngestionTaskNotFoundException extends ResourceNotFoundException implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1518018360950887044L;

    public IngestionTaskNotFoundException() {
    }

    public IngestionTaskNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }

    @Deprecated
    public IngestionTaskNotFoundException(final int httpStatusCode, final String httpStatusLine,
        final String httpStatusMsg) {
        super(httpStatusCode, httpStatusLine, httpStatusMsg);
    }

    public IngestionTaskNotFoundException(final String message, final Throwable cause, final int httpStatusCode,
        final String httpStatusLine, final String httpStatusMsg) {
        super(message, cause, httpStatusCode, httpStatusLine, httpStatusMsg);
    }
}