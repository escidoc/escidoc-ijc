package de.escidoc.core.client.exceptions.application.violated;

import java.io.Serializable;

public class AlreadyPublishedException extends RuleViolationException implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -6337074700025783776L;

    public AlreadyPublishedException() {
    }

    public AlreadyPublishedException(final String message, final Throwable cause) {
        super(message, cause);
    }

    @Deprecated
    public AlreadyPublishedException(final int httpStatusCode, final String httpStatusLine, final String httpStatusMsg) {
        super(httpStatusCode, httpStatusLine, httpStatusMsg);
    }

    public AlreadyPublishedException(final String message, final Throwable cause, final int httpStatusCode,
        final String httpStatusLine, final String httpStatusMsg) {
        super(message, cause, httpStatusCode, httpStatusLine, httpStatusMsg);
    }
}