package de.escidoc.core.client.exceptions.application.notfound;

import java.io.Serializable;

public class TransitionNotFoundException extends ResourceNotFoundException implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -6017369450889224021L;

    public TransitionNotFoundException() {
    }

    public TransitionNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }

    @Deprecated
    public TransitionNotFoundException(final int httpStatusCode, final String httpStatusLine, final String httpStatusMsg) {
        super(httpStatusCode, httpStatusLine, httpStatusMsg);
    }

    public TransitionNotFoundException(final String message, final Throwable cause, final int httpStatusCode,
        final String httpStatusLine, final String httpStatusMsg) {
        super(message, cause, httpStatusCode, httpStatusLine, httpStatusMsg);
    }
}