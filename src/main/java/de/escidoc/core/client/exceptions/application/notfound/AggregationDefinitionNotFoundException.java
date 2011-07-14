package de.escidoc.core.client.exceptions.application.notfound;

import java.io.Serializable;

public class AggregationDefinitionNotFoundException extends ResourceNotFoundException implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 6220840915694775850L;

    public AggregationDefinitionNotFoundException() {
    }

    public AggregationDefinitionNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }

    @Deprecated
    public AggregationDefinitionNotFoundException(final int httpStatusCode, final String httpStatusLine,
        final String httpStatusMsg) {
        super(httpStatusCode, httpStatusLine, httpStatusMsg);
    }

    public AggregationDefinitionNotFoundException(final String message, final Throwable cause,
        final int httpStatusCode, final String httpStatusLine, final String httpStatusMsg) {
        super(message, cause, httpStatusCode, httpStatusLine, httpStatusMsg);
    }
}