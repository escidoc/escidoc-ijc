package de.escidoc.core.client.exceptions.application.violated;

import java.io.Serializable;

public class ReadonlyElementViolationException extends ReadonlyViolationException implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 5010040366281472749L;

    public ReadonlyElementViolationException() {
    }

    public ReadonlyElementViolationException(final String message, final Throwable cause) {
        super(message, cause);
    }

    @Deprecated
    public ReadonlyElementViolationException(final int httpStatusCode, final String httpStatusLine,
        final String httpStatusMsg) {
        super(httpStatusCode, httpStatusLine, httpStatusMsg);
    }

    public ReadonlyElementViolationException(final String message, final Throwable cause, final int httpStatusCode,
        final String httpStatusLine, final String httpStatusMsg) {
        super(message, cause, httpStatusCode, httpStatusLine, httpStatusMsg);
    }
}