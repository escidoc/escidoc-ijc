package de.escidoc.core.client.exceptions.application.notfound;

public class UserAccountNotFoundException extends ResourceNotFoundException {

    /**
     * 
     */
    private static final long serialVersionUID = -2236688606833784001L;

    public UserAccountNotFoundException() {
    }

    public UserAccountNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }

    @Deprecated
    public UserAccountNotFoundException(final int httpStatusCode, final String httpStatusLine,
        final String httpStatusMsg) {
        super(httpStatusCode, httpStatusLine, httpStatusMsg);
    }

    public UserAccountNotFoundException(final String message, final Throwable cause, final int httpStatusCode,
        final String httpStatusLine, final String httpStatusMsg) {
        super(message, cause, httpStatusCode, httpStatusLine, httpStatusMsg);
    }
}