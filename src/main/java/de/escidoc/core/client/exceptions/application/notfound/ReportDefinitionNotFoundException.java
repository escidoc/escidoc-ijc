package de.escidoc.core.client.exceptions.application.notfound;

public class ReportDefinitionNotFoundException extends ResourceNotFoundException {

    /**
     * 
     */
    private static final long serialVersionUID = -856929694688953274L;

    public ReportDefinitionNotFoundException() {
    }

    public ReportDefinitionNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }

    @Deprecated
    public ReportDefinitionNotFoundException(final int httpStatusCode, final String httpStatusLine,
        final String httpStatusMsg) {
        super(httpStatusCode, httpStatusLine, httpStatusMsg);
    }

    public ReportDefinitionNotFoundException(final String message, final Throwable cause, final int httpStatusCode,
        final String httpStatusLine, final String httpStatusMsg) {
        super(message, cause, httpStatusCode, httpStatusLine, httpStatusMsg);
    }
}