package de.escidoc.core.client.exceptions.application.notfound;

import java.io.Serializable;

public class VersionNotFoundException extends ResourceNotFoundException implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 6825401577756902255L;

    public VersionNotFoundException() {
    }

    public VersionNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }

    @Deprecated
    public VersionNotFoundException(final int httpStatusCode, final String httpStatusLine, final String httpStatusMsg) {
        super(httpStatusCode, httpStatusLine, httpStatusMsg);
    }

    public VersionNotFoundException(final String message, final Throwable cause, final int httpStatusCode,
        final String httpStatusLine, final String httpStatusMsg) {
        super(message, cause, httpStatusCode, httpStatusLine, httpStatusMsg);
    }
}