package de.escidoc.core.client.exceptions.application.notfound;

import java.io.Serializable;

public class FileNotFoundException extends ResourceNotFoundException implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 6142415224284887555L;

    public FileNotFoundException() {
    }

    public FileNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }

    @Deprecated
    public FileNotFoundException(final int httpStatusCode, final String httpStatusLine, final String httpStatusMsg) {
        super(httpStatusCode, httpStatusLine, httpStatusMsg);
    }

    public FileNotFoundException(final String message, final Throwable cause, final int httpStatusCode,
        final String httpStatusLine, final String httpStatusMsg) {
        super(message, cause, httpStatusCode, httpStatusLine, httpStatusMsg);
    }
}