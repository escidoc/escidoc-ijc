package de.escidoc.core.client.exceptions.application.violated;

import java.io.Serializable;

public class NotPublishedException extends RuleViolationException implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1516381475540452352L;

    public NotPublishedException() {
    }

    public NotPublishedException(final String message, final Throwable cause) {
        super(message, cause);
    }

    @Deprecated
    public NotPublishedException(final int httpStatusCode, final String httpStatusLine, final String httpStatusMsg) {
        super(httpStatusCode, httpStatusLine, httpStatusMsg);
    }

    public NotPublishedException(final String message, final Throwable cause, final int httpStatusCode,
        final String httpStatusLine, final String httpStatusMsg) {
        super(message, cause, httpStatusCode, httpStatusLine, httpStatusMsg);
    }
}