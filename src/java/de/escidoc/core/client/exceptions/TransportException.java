package de.escidoc.core.client.exceptions;

public class TransportException extends EscidocClientException {

    /**
     *
     */
    private static final long serialVersionUID = -3044846284884218476L;

    public TransportException() {
        super();
    }

    public TransportException(String message) {
        super(message);
    }

    public TransportException(Throwable cause) {
        super(cause);
    }

    public TransportException(String message, Throwable cause) {
        super(message, cause);
    }

}
