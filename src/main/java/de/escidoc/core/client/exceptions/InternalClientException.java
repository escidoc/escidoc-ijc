package de.escidoc.core.client.exceptions;

public class InternalClientException extends EscidocClientException {

    /**
     *
     */
    private static final long serialVersionUID = -3829214846961207519L;

    public InternalClientException() {
        super();
    }

    public InternalClientException(final String message) {
        super(message);
    }

    public InternalClientException(final Throwable cause) {
        super(cause);
    }

    public InternalClientException(final String message, final Throwable cause) {
        super(message, cause);
    }

}
