package de.escidoc.core.client.exceptions.system;

import java.io.Serializable;

import de.escidoc.core.client.exceptions.EscidocException;

public class SystemException extends EscidocException implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 7784081571466471616L;

    public SystemException() {
    }

    public SystemException(final String message, final Throwable cause) {
        super(message, cause);
    }

    @Deprecated
    public SystemException(final int httpStatusCode, final String httpStatusLine, final String httpStatusMsg) {
        super(httpStatusCode, httpStatusLine, httpStatusMsg);
    }

    public SystemException(final String message, final Throwable cause, final int httpStatusCode,
        final String httpStatusLine, final String httpStatusMsg) {
        super(message, cause, httpStatusCode, httpStatusLine, httpStatusMsg);
    }
}