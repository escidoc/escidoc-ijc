package de.escidoc.core.client.exceptions.application.notfound;

public class MdRecordNotFoundException extends ResourceNotFoundException {

    /**
     * 
     */
    private static final long serialVersionUID = -7983957972829260185L;

    public MdRecordNotFoundException() {
    }

    public MdRecordNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }

    @Deprecated
    public MdRecordNotFoundException(final int httpStatusCode, final String httpStatusLine, final String httpStatusMsg) {
        super(httpStatusCode, httpStatusLine, httpStatusMsg);
    }

    public MdRecordNotFoundException(final String message, final Throwable cause, final int httpStatusCode,
        final String httpStatusLine, final String httpStatusMsg) {
        super(message, cause, httpStatusCode, httpStatusLine, httpStatusMsg);
    }
}