package de.escidoc.core.client.exceptions.application.invalid;

import java.io.Serializable;

/**
 * @author Marko Voß
 * 
 */
public class InvalidWorkflowDefinitionException extends ValidationException implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -2004638452694799945L;

    /**
     * 
     */
    public InvalidWorkflowDefinitionException() {
    }

    /**
     * @param message
     * @param cause
     */
    public InvalidWorkflowDefinitionException(final String message, final Throwable cause) {
        super(message, cause);
    }

    /**
     * @param httpStatusCode
     * @param httpStatusLine
     * @param httpStatusMsg
     */
    @Deprecated
    public InvalidWorkflowDefinitionException(final int httpStatusCode, final String httpStatusLine,
        final String httpStatusMsg) {
        super(httpStatusCode, httpStatusLine, httpStatusMsg);
    }

    /**
     * @param message
     * @param cause
     * @param httpStatusCode
     * @param httpStatusLine
     * @param httpStatusMsg
     */
    public InvalidWorkflowDefinitionException(final String message, final Throwable cause, final int httpStatusCode,
        final String httpStatusLine, final String httpStatusMsg) {
        super(message, cause, httpStatusCode, httpStatusLine, httpStatusMsg);
    }
}