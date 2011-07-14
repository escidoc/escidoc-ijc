package de.escidoc.core.client.exceptions.application.notfound;

import java.io.Serializable;

public class IndexNotFoundException extends ResourceNotFoundException implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 8674033731242407977L;

    public IndexNotFoundException() {
    }

    public IndexNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }

    @Deprecated
    public IndexNotFoundException(final int httpStatusCode, final String httpStatusLine, final String httpStatusMsg) {
        super(httpStatusCode, httpStatusLine, httpStatusMsg);
    }

    public IndexNotFoundException(final String message, final Throwable cause, final int httpStatusCode,
        final String httpStatusLine, final String httpStatusMsg) {
        super(message, cause, httpStatusCode, httpStatusLine, httpStatusMsg);
    }
}