package de.escidoc.core.client.exceptions.application.notfound;

import java.io.Serializable;

public class StagingFileNotFoundException extends ResourceNotFoundException implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 5833769725493084797L;

    public StagingFileNotFoundException() {
    }

    public StagingFileNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }

    @Deprecated
    public StagingFileNotFoundException(final int httpStatusCode, final String httpStatusLine,
        final String httpStatusMsg) {
        super(httpStatusCode, httpStatusLine, httpStatusMsg);
    }

    public StagingFileNotFoundException(final String message, final Throwable cause, final int httpStatusCode,
        final String httpStatusLine, final String httpStatusMsg) {
        super(message, cause, httpStatusCode, httpStatusLine, httpStatusMsg);
    }
}