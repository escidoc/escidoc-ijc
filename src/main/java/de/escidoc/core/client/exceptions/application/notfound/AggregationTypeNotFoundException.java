package de.escidoc.core.client.exceptions.application.notfound;

import java.io.Serializable;

public class AggregationTypeNotFoundException extends ResourceNotFoundException implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -6084212059915448909L;

    public AggregationTypeNotFoundException() {
    }

    public AggregationTypeNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }

    @Deprecated
    public AggregationTypeNotFoundException(final int httpStatusCode, final String httpStatusLine,
        final String httpStatusMsg) {
        super(httpStatusCode, httpStatusLine, httpStatusMsg);
    }

    public AggregationTypeNotFoundException(final String message, final Throwable cause, final int httpStatusCode,
        final String httpStatusLine, final String httpStatusMsg) {
        super(message, cause, httpStatusCode, httpStatusLine, httpStatusMsg);
    }
}