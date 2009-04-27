package de.escidoc.core.client.exceptions;

public abstract class EscidocClientException extends Exception {

    public EscidocClientException() {
        super();
    }

    public EscidocClientException(String message) {
        super(message);
    }

    public EscidocClientException(Throwable cause) {
        super(cause);
    }

    public EscidocClientException(String message, Throwable cause) {
        super(message, cause);
    }

}
