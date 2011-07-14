package de.escidoc.core.client.exceptions.application.violated;

import java.io.Serializable;

public class AlreadyRevokedException extends RuleViolationException implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 3404520588330590565L;

    public AlreadyRevokedException() {
    }

    public AlreadyRevokedException(final String message, final Throwable cause) {
        super(message, cause);
    }

    @Deprecated
    public AlreadyRevokedException(final int httpStatusCode, final String httpStatusLine, final String httpStatusMsg) {
        super(httpStatusCode, httpStatusLine, httpStatusMsg);
    }

    public AlreadyRevokedException(final String message, final Throwable cause, final int httpStatusCode,
        final String httpStatusLine, final String httpStatusMsg) {
        super(message, cause, httpStatusCode, httpStatusLine, httpStatusMsg);
    }
}