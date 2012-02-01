package de.escidoc.core.client.exceptions.application.notfound;

public class PreferenceNotFoundException extends ResourceNotFoundException {

    /**
     * 
     */
    private static final long serialVersionUID = -8730890176635781414L;

    public PreferenceNotFoundException() {
    }

    public PreferenceNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }

    @Deprecated
    public PreferenceNotFoundException(final int httpStatusCode, final String httpStatusLine, final String httpStatusMsg) {
        super(httpStatusCode, httpStatusLine, httpStatusMsg);
    }

    public PreferenceNotFoundException(final String message, final Throwable cause, final int httpStatusCode,
        final String httpStatusLine, final String httpStatusMsg) {
        super(message, cause, httpStatusCode, httpStatusLine, httpStatusMsg);
    }
}