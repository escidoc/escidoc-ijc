package de.escidoc.core.client.exceptions.application.notfound;

public class PidNotFoundException extends ResourceNotFoundException {

    /**
     * 
     */
    private static final long serialVersionUID = -5039337804642162445L;

    public PidNotFoundException() {
    }

    public PidNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }

    @Deprecated
    public PidNotFoundException(final int httpStatusCode, final String httpStatusLine, final String httpStatusMsg) {
        super(httpStatusCode, httpStatusLine, httpStatusMsg);
    }

    public PidNotFoundException(final String message, final Throwable cause, final int httpStatusCode,
        final String httpStatusLine, final String httpStatusMsg) {
        super(message, cause, httpStatusCode, httpStatusLine, httpStatusMsg);
    }
}