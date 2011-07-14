package de.escidoc.core.client.exceptions.application.notfound;

import java.io.Serializable;

public class RevisionNotFoundException extends ResourceNotFoundException implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 2545675698853883072L;

    public RevisionNotFoundException() {
    }

    public RevisionNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }

    @Deprecated
    public RevisionNotFoundException(final int httpStatusCode, final String httpStatusLine, final String httpStatusMsg) {
        super(httpStatusCode, httpStatusLine, httpStatusMsg);
    }

    public RevisionNotFoundException(final String message, final Throwable cause, final int httpStatusCode,
        final String httpStatusLine, final String httpStatusMsg) {
        super(message, cause, httpStatusCode, httpStatusLine, httpStatusMsg);
    }
}