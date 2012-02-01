package de.escidoc.core.client.exceptions.application.notfound;

public class UserNotFoundException extends ResourceNotFoundException {

    /**
     * 
     */
    private static final long serialVersionUID = 8634137794082380974L;

    public UserNotFoundException() {
    }

    public UserNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }

    @Deprecated
    public UserNotFoundException(final int httpStatusCode, final String httpStatusLine, final String httpStatusMsg) {
        super(httpStatusCode, httpStatusLine, httpStatusMsg);
    }

    public UserNotFoundException(final String message, final Throwable cause, final int httpStatusCode,
        final String httpStatusLine, final String httpStatusMsg) {
        super(message, cause, httpStatusCode, httpStatusLine, httpStatusMsg);
    }
}