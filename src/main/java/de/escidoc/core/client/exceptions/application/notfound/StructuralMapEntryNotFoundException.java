package de.escidoc.core.client.exceptions.application.notfound;

import java.io.Serializable;

public class StructuralMapEntryNotFoundException extends ResourceNotFoundException implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -4474206319453391960L;

    public StructuralMapEntryNotFoundException() {
    }

    public StructuralMapEntryNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }

    @Deprecated
    public StructuralMapEntryNotFoundException(final int httpStatusCode, final String httpStatusLine,
        final String httpStatusMsg) {
        super(httpStatusCode, httpStatusLine, httpStatusMsg);
    }

    public StructuralMapEntryNotFoundException(final String message, final Throwable cause, final int httpStatusCode,
        final String httpStatusLine, final String httpStatusMsg) {
        super(message, cause, httpStatusCode, httpStatusLine, httpStatusMsg);
    }
}