package de.escidoc.core.client.exceptions.application.notfound;

public class TaskNotFoundException extends ResourceNotFoundException {

    /**
     * 
     */
    private static final long serialVersionUID = 8394067055508656583L;

    public TaskNotFoundException() {
    }

    public TaskNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }

    @Deprecated
    public TaskNotFoundException(final int httpStatusCode, final String httpStatusLine, final String httpStatusMsg) {
        super(httpStatusCode, httpStatusLine, httpStatusMsg);
    }

    public TaskNotFoundException(final String message, final Throwable cause, final int httpStatusCode,
        final String httpStatusLine, final String httpStatusMsg) {
        super(message, cause, httpStatusCode, httpStatusLine, httpStatusMsg);
    }
}