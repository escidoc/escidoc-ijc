package de.escidoc.core.client.exceptions;

public class InternalClientException extends EscidocClientException {

    /**
     *
     */
    private static final long serialVersionUID = -3829214846961207519L;

    public InternalClientException() {
        super();
    }

    public InternalClientException(String message) {
        super(message);
    }

    public InternalClientException(Throwable cause) {
        super(cause);
    }

    public InternalClientException(String message, Throwable cause) {
        super(message, cause);
    }

}
