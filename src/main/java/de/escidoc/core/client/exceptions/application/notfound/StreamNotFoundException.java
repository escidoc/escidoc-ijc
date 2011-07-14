package de.escidoc.core.client.exceptions.application.notfound;

import java.io.Serializable;

import de.escidoc.core.client.exceptions.EscidocException;

public class StreamNotFoundException extends EscidocException implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 2915071552034374730L;

    public StreamNotFoundException() {
    }

    public StreamNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }

    @Deprecated
    public StreamNotFoundException(final int httpStatusCode, final String httpStatusLine, final String httpStatusMsg) {
        super(httpStatusCode, httpStatusLine, httpStatusMsg);
    }

    public StreamNotFoundException(final String message, final Throwable cause, final int httpStatusCode,
        final String httpStatusLine, final String httpStatusMsg) {
        super(message, cause, httpStatusCode, httpStatusLine, httpStatusMsg);
    }
}