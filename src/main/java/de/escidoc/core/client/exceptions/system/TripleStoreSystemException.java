package de.escidoc.core.client.exceptions.system;

import java.io.Serializable;

public class TripleStoreSystemException extends SystemException implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -3131122576464488223L;

    public TripleStoreSystemException() {
    }

    public TripleStoreSystemException(final String message, final Throwable cause) {
        super(message, cause);
    }

    @Deprecated
    public TripleStoreSystemException(final int httpStatusCode, final String httpStatusLine, final String httpStatusMsg) {
        super(httpStatusCode, httpStatusLine, httpStatusMsg);
    }

    public TripleStoreSystemException(final String message, final Throwable cause, final int httpStatusCode,
        final String httpStatusLine, final String httpStatusMsg) {
        super(message, cause, httpStatusCode, httpStatusLine, httpStatusMsg);
    }
}