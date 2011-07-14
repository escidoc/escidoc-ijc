package de.escidoc.core.client.exceptions.application.notfound;

import java.io.Serializable;

public class XmlSchemaNotFoundException extends ResourceNotFoundException implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -2555445192364754564L;

    public XmlSchemaNotFoundException() {
    }

    public XmlSchemaNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }

    @Deprecated
    public XmlSchemaNotFoundException(final int httpStatusCode, final String httpStatusLine, final String httpStatusMsg) {
        super(httpStatusCode, httpStatusLine, httpStatusMsg);
    }

    public XmlSchemaNotFoundException(final String message, final Throwable cause, final int httpStatusCode,
        final String httpStatusLine, final String httpStatusMsg) {
        super(message, cause, httpStatusCode, httpStatusLine, httpStatusMsg);
    }
}