package de.escidoc.core.client.exceptions.application.notfound;

import java.io.Serializable;

public class TaskListNotFoundException extends ResourceNotFoundException implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -4642408515716919570L;

    public TaskListNotFoundException() {
    }

    public TaskListNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }

    @Deprecated
    public TaskListNotFoundException(final int httpStatusCode, final String httpStatusLine, final String httpStatusMsg) {
        super(httpStatusCode, httpStatusLine, httpStatusMsg);
    }

    public TaskListNotFoundException(final String message, final Throwable cause, final int httpStatusCode,
        final String httpStatusLine, final String httpStatusMsg) {
        super(message, cause, httpStatusCode, httpStatusLine, httpStatusMsg);
    }
}