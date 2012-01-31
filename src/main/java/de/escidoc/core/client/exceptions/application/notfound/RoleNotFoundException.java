package de.escidoc.core.client.exceptions.application.notfound;

public class RoleNotFoundException extends ResourceNotFoundException {

    /**
     * 
     */
    private static final long serialVersionUID = 1501457344320801783L;

    public RoleNotFoundException() {
    }

    public RoleNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }

    @Deprecated
    public RoleNotFoundException(final int httpStatusCode, final String httpStatusLine, final String httpStatusMsg) {
        super(httpStatusCode, httpStatusLine, httpStatusMsg);
    }

    public RoleNotFoundException(final String message, final Throwable cause, final int httpStatusCode,
        final String httpStatusLine, final String httpStatusMsg) {
        super(message, cause, httpStatusCode, httpStatusLine, httpStatusMsg);
    }
}