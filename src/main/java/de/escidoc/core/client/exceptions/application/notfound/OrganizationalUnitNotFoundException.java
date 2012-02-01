package de.escidoc.core.client.exceptions.application.notfound;

public class OrganizationalUnitNotFoundException extends ResourceNotFoundException {

    /**
     * 
     */
    private static final long serialVersionUID = -6088263973726784735L;

    public OrganizationalUnitNotFoundException() {
    }

    public OrganizationalUnitNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }

    @Deprecated
    public OrganizationalUnitNotFoundException(final int httpStatusCode, final String httpStatusLine,
        final String httpStatusMsg) {
        super(httpStatusCode, httpStatusLine, httpStatusMsg);
    }

    public OrganizationalUnitNotFoundException(final String message, final Throwable cause, final int httpStatusCode,
        final String httpStatusLine, final String httpStatusMsg) {
        super(message, cause, httpStatusCode, httpStatusLine, httpStatusMsg);
    }
}