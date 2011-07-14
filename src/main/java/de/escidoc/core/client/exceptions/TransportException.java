package de.escidoc.core.client.exceptions;

import java.io.Serializable;

public class TransportException extends EscidocClientException implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -3044846284884218476L;

    public TransportException() {
        super();
    }

    public TransportException(final String message) {
        super(message);
    }

    public TransportException(final Throwable cause) {
        super(cause);
    }

    public TransportException(final String message, final Throwable cause) {
        super(message, cause);
    }

}
