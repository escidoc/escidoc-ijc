package de.escidoc.core.client.exceptions.application.notfound;

import java.io.Serializable;

public class TargetBasketNotFoundException extends ResourceNotFoundException implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 4955722623773842930L;

    public TargetBasketNotFoundException() {
    }

    public TargetBasketNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }

    @Deprecated
    public TargetBasketNotFoundException(final int httpStatusCode, final String httpStatusLine,
        final String httpStatusMsg) {
        super(httpStatusCode, httpStatusLine, httpStatusMsg);
    }

    public TargetBasketNotFoundException(final String message, final Throwable cause, final int httpStatusCode,
        final String httpStatusLine, final String httpStatusMsg) {
        super(message, cause, httpStatusCode, httpStatusLine, httpStatusMsg);
    }
}