package de.escidoc.core.client.exceptions.application.violated;

import java.io.Serializable;

public class AdminDescriptorViolationException extends RuleViolationException implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 4546614670393377929L;

    public AdminDescriptorViolationException() {
    }

    public AdminDescriptorViolationException(final String message, final Throwable cause) {
        super(message, cause);
    }

    @Deprecated
    public AdminDescriptorViolationException(final int httpStatusCode, final String httpStatusLine,
        final String httpStatusMsg) {
        super(httpStatusCode, httpStatusLine, httpStatusMsg);
    }

    public AdminDescriptorViolationException(final String message, final Throwable cause, final int httpStatusCode,
        final String httpStatusLine, final String httpStatusMsg) {
        super(message, cause, httpStatusCode, httpStatusLine, httpStatusMsg);
    }
}