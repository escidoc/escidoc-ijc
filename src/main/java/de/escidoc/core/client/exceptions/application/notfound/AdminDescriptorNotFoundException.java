package de.escidoc.core.client.exceptions.application.notfound;

public class AdminDescriptorNotFoundException extends ResourceNotFoundException {

    /**
     * 
     */
    private static final long serialVersionUID = 3319280887519845726L;

    public AdminDescriptorNotFoundException() {
    }

    public AdminDescriptorNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }

    @Deprecated
    public AdminDescriptorNotFoundException(final int httpStatusCode, final String httpStatusLine,
        final String httpStatusMsg) {
        super(httpStatusCode, httpStatusLine, httpStatusMsg);
    }

    public AdminDescriptorNotFoundException(final String message, final Throwable cause, final int httpStatusCode,
        final String httpStatusLine, final String httpStatusMsg) {
        super(message, cause, httpStatusCode, httpStatusLine, httpStatusMsg);
    }
}