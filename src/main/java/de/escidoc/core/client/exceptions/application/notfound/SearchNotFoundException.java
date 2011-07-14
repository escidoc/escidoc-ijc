package de.escidoc.core.client.exceptions.application.notfound;

import java.io.Serializable;

public class SearchNotFoundException extends ResourceNotFoundException implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 34848152052659556L;

    public SearchNotFoundException() {
    }

    public SearchNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }

    @Deprecated
    public SearchNotFoundException(final int httpStatusCode, final String httpStatusLine, final String httpStatusMsg) {
        super(httpStatusCode, httpStatusLine, httpStatusMsg);
    }

    public SearchNotFoundException(final String message, final Throwable cause, final int httpStatusCode,
        final String httpStatusLine, final String httpStatusMsg) {
        super(message, cause, httpStatusCode, httpStatusLine, httpStatusMsg);
    }
}