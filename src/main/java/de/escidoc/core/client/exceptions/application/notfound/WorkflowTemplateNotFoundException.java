package de.escidoc.core.client.exceptions.application.notfound;

import java.io.Serializable;

public class WorkflowTemplateNotFoundException extends ResourceNotFoundException implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 3591183022027874459L;

    public WorkflowTemplateNotFoundException() {
    }

    public WorkflowTemplateNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }

    @Deprecated
    public WorkflowTemplateNotFoundException(final int httpStatusCode, final String httpStatusLine,
        final String httpStatusMsg) {
        super(httpStatusCode, httpStatusLine, httpStatusMsg);
    }

    public WorkflowTemplateNotFoundException(final String message, final Throwable cause, final int httpStatusCode,
        final String httpStatusLine, final String httpStatusMsg) {
        super(message, cause, httpStatusCode, httpStatusLine, httpStatusMsg);
    }
}