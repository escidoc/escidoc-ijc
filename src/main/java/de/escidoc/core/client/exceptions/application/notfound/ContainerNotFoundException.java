package de.escidoc.core.client.exceptions.application.notfound;

public class ContainerNotFoundException extends ResourceNotFoundException {

    /**
     * 
     */
    private static final long serialVersionUID = -3491057364907144L;

    public ContainerNotFoundException() {
    }

    public ContainerNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }

    @Deprecated
    public ContainerNotFoundException(final int httpStatusCode, final String httpStatusLine, final String httpStatusMsg) {
        super(httpStatusCode, httpStatusLine, httpStatusMsg);
    }

    public ContainerNotFoundException(final String message, final Throwable cause, final int httpStatusCode,
        final String httpStatusLine, final String httpStatusMsg) {
        super(message, cause, httpStatusCode, httpStatusLine, httpStatusMsg);
    }
}