package de.escidoc.core.client.exceptions.application.violated;

import java.io.Serializable;

public class OrganizationalUnitHasChildrenException extends RuleViolationException implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -6641904571418020358L;

    public OrganizationalUnitHasChildrenException() {
    }

    public OrganizationalUnitHasChildrenException(final String message, final Throwable cause) {
        super(message, cause);
    }

    @Deprecated
    public OrganizationalUnitHasChildrenException(final int httpStatusCode, final String httpStatusLine,
        final String httpStatusMsg) {
        super(httpStatusCode, httpStatusLine, httpStatusMsg);
    }

    public OrganizationalUnitHasChildrenException(final String message, final Throwable cause,
        final int httpStatusCode, final String httpStatusLine, final String httpStatusMsg) {
        super(message, cause, httpStatusCode, httpStatusLine, httpStatusMsg);
    }
}