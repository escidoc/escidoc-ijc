package de.escidoc.core.client.exceptions.system;

public class FileSystemException extends SystemException {

    /**
     * 
     */
    private static final long serialVersionUID = 5779508713097038722L;

    public FileSystemException() {
    }

    public FileSystemException(final String message, final Throwable cause) {
        super(message, cause);
    }

    @Deprecated
    public FileSystemException(final int httpStatusCode, final String httpStatusLine, final String httpStatusMsg) {
        super(httpStatusCode, httpStatusLine, httpStatusMsg);
    }

    public FileSystemException(final String message, final Throwable cause, final int httpStatusCode,
        final String httpStatusLine, final String httpStatusMsg) {
        super(message, cause, httpStatusCode, httpStatusLine, httpStatusMsg);
    }
}