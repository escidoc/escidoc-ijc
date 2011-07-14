package de.escidoc.core.client.exceptions.application.missing;

import java.io.Serializable;

public class MissingUserListException extends MissingParameterException implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -4640636576665260443L;

    public MissingUserListException() {
    }

    public MissingUserListException(final String message, final Throwable cause) {
        super(message, cause);
    }

    @Deprecated
    public MissingUserListException(final int httpStatusCode, final String httpStatusLine, final String httpStatusMsg) {
        super(httpStatusCode, httpStatusLine, httpStatusMsg);
    }

    public MissingUserListException(final String message, final Throwable cause, final int httpStatusCode,
        final String httpStatusLine, final String httpStatusMsg) {
        super(message, cause, httpStatusCode, httpStatusLine, httpStatusMsg);
    }
}