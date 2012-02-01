package de.escidoc.core.client.exceptions.application.notfound;

public class WorkflowInstanceNotFoundException extends ResourceNotFoundException {

    /**
     * 
     */
    private static final long serialVersionUID = -7950640302448279129L;

    public WorkflowInstanceNotFoundException() {
    }

    public WorkflowInstanceNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }

    @Deprecated
    public WorkflowInstanceNotFoundException(final int httpStatusCode, final String httpStatusLine,
        final String httpStatusMsg) {
        super(httpStatusCode, httpStatusLine, httpStatusMsg);
    }

    public WorkflowInstanceNotFoundException(final String message, final Throwable cause, final int httpStatusCode,
        final String httpStatusLine, final String httpStatusMsg) {
        super(message, cause, httpStatusCode, httpStatusLine, httpStatusMsg);
    }
}