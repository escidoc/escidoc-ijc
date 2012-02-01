package de.escidoc.core.client.exceptions.application.violated;

public class UserGroupHierarchyViolationException extends RuleViolationException {

    /**
     * 
     */
    private static final long serialVersionUID = 2946099500526716535L;

    public UserGroupHierarchyViolationException() {
    }

    public UserGroupHierarchyViolationException(final String message, final Throwable cause) {
        super(message, cause);
    }

    @Deprecated
    public UserGroupHierarchyViolationException(final int httpStatusCode, final String httpStatusLine,
        final String httpStatusMsg) {
        super(httpStatusCode, httpStatusLine, httpStatusMsg);
    }

    public UserGroupHierarchyViolationException(final String message, final Throwable cause, final int httpStatusCode,
        final String httpStatusLine, final String httpStatusMsg) {
        super(message, cause, httpStatusCode, httpStatusLine, httpStatusMsg);
    }
}