package de.escidoc.core.client.exceptions.application.missing;

import java.io.Serializable;

/**
 * @author Marko Voß
 * 
 */
public class MissingMethodParameterException extends MissingParameterException implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 5099561740080270811L;

    public MissingMethodParameterException() {
    }

    public MissingMethodParameterException(final String message, final Throwable cause) {
        super(message, cause);
    }

    @Deprecated
    public MissingMethodParameterException(final int httpStatusCode, final String httpStatusLine,
        final String httpStatusMsg) {
        super(httpStatusCode, httpStatusLine, httpStatusMsg);
    }

    public MissingMethodParameterException(final String message, final Throwable cause, final int httpStatusCode,
        final String httpStatusLine, final String httpStatusMsg) {
        super(message, cause, httpStatusCode, httpStatusLine, httpStatusMsg);
    }
}