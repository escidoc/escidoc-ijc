package de.escidoc.core.client.exceptions.application.violated;

import java.io.Serializable;

public class OrganizationalUnitNameNotUniqueException extends RuleViolationException implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -8664447437537801119L;

    public OrganizationalUnitNameNotUniqueException() {
    }

    public OrganizationalUnitNameNotUniqueException(final String message, final Throwable cause) {
        super(message, cause);
    }

    @Deprecated
    public OrganizationalUnitNameNotUniqueException(final int httpStatusCode, final String httpStatusLine,
        final String httpStatusMsg) {
        super(httpStatusCode, httpStatusLine, httpStatusMsg);
    }

    public OrganizationalUnitNameNotUniqueException(final String message, final Throwable cause,
        final int httpStatusCode, final String httpStatusLine, final String httpStatusMsg) {
        super(message, cause, httpStatusCode, httpStatusLine, httpStatusMsg);
    }
}