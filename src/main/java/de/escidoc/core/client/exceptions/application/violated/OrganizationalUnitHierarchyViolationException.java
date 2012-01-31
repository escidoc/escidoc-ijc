package de.escidoc.core.client.exceptions.application.violated;

public class OrganizationalUnitHierarchyViolationException extends RuleViolationException {

    /**
     * 
     */
    private static final long serialVersionUID = -1993005776386092924L;

    public OrganizationalUnitHierarchyViolationException() {
    }

    public OrganizationalUnitHierarchyViolationException(final String message, final Throwable cause) {
        super(message, cause);
    }

    @Deprecated
    public OrganizationalUnitHierarchyViolationException(final int httpStatusCode, final String httpStatusLine,
        final String httpStatusMsg) {
        super(httpStatusCode, httpStatusLine, httpStatusMsg);
    }

    public OrganizationalUnitHierarchyViolationException(final String message, final Throwable cause,
        final int httpStatusCode, final String httpStatusLine, final String httpStatusMsg) {
        super(message, cause, httpStatusCode, httpStatusLine, httpStatusMsg);
    }
}