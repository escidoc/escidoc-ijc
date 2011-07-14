package de.escidoc.core.client.exceptions.application.notfound;

import java.io.Serializable;

public class WorkflowDefinitionNotFoundException extends ResourceNotFoundException implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -3648320308771320698L;

    public WorkflowDefinitionNotFoundException() {
    }

    public WorkflowDefinitionNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }

    @Deprecated
    public WorkflowDefinitionNotFoundException(final int httpStatusCode, final String httpStatusLine,
        final String httpStatusMsg) {
        super(httpStatusCode, httpStatusLine, httpStatusMsg);
    }

    public WorkflowDefinitionNotFoundException(final String message, final Throwable cause, final int httpStatusCode,
        final String httpStatusLine, final String httpStatusMsg) {
        super(message, cause, httpStatusCode, httpStatusLine, httpStatusMsg);
    }
}