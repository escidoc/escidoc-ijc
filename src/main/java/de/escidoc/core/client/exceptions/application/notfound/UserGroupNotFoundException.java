package de.escidoc.core.client.exceptions.application.notfound;

public class UserGroupNotFoundException extends ResourceNotFoundException {

    /**
     * 
     */
    private static final long serialVersionUID = 522006788077407593L;

    public UserGroupNotFoundException() {
    }

    public UserGroupNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }

    @Deprecated
    public UserGroupNotFoundException(final int httpStatusCode, final String httpStatusLine, final String httpStatusMsg) {
        super(httpStatusCode, httpStatusLine, httpStatusMsg);
    }

    public UserGroupNotFoundException(final String message, final Throwable cause, final int httpStatusCode,
        final String httpStatusLine, final String httpStatusMsg) {
        super(message, cause, httpStatusCode, httpStatusLine, httpStatusMsg);
    }
}