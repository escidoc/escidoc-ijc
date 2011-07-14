package de.escidoc.core.client.exceptions.application.notfound;

import java.io.Serializable;

public class UserAttributeNotFoundException extends ResourceNotFoundException implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -5976303520154558411L;

    public UserAttributeNotFoundException() {
    }

    public UserAttributeNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }

    @Deprecated
    public UserAttributeNotFoundException(final int httpStatusCode, final String httpStatusLine,
        final String httpStatusMsg) {
        super(httpStatusCode, httpStatusLine, httpStatusMsg);
    }

    public UserAttributeNotFoundException(final String message, final Throwable cause, final int httpStatusCode,
        final String httpStatusLine, final String httpStatusMsg) {
        super(message, cause, httpStatusCode, httpStatusLine, httpStatusMsg);
    }
}