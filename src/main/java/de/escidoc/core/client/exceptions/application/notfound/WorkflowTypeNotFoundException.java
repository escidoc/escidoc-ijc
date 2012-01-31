package de.escidoc.core.client.exceptions.application.notfound;

public class WorkflowTypeNotFoundException extends ResourceNotFoundException {

    /**
     * 
     */
    private static final long serialVersionUID = -1490536258582185877L;

    public WorkflowTypeNotFoundException() {
    }

    public WorkflowTypeNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }

    @Deprecated
    public WorkflowTypeNotFoundException(final int httpStatusCode, final String httpStatusLine,
        final String httpStatusMsg) {
        super(httpStatusCode, httpStatusLine, httpStatusMsg);
    }

    public WorkflowTypeNotFoundException(final String message, final Throwable cause, final int httpStatusCode,
        final String httpStatusLine, final String httpStatusMsg) {
        super(message, cause, httpStatusCode, httpStatusLine, httpStatusMsg);
    }
}