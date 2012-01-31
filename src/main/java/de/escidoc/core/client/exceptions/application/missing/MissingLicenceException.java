package de.escidoc.core.client.exceptions.application.missing;

/**
 * @author Marko Voß
 * 
 */
public class MissingLicenceException extends MissingParameterException {

    /**
     * 
     */
    private static final long serialVersionUID = -5174265920646658621L;

    /**
     * 
     */
    public MissingLicenceException() {
    }

    /**
     * @param message
     * @param cause
     */
    public MissingLicenceException(final String message, final Throwable cause) {
        super(message, cause);
    }

    /**
     * @param httpStatusCode
     * @param httpStatusLine
     * @param httpStatusMsg
     */
    @Deprecated
    public MissingLicenceException(final int httpStatusCode, final String httpStatusLine, final String httpStatusMsg) {
        super(httpStatusCode, httpStatusLine, httpStatusMsg);
    }

    /**
     * @param message
     * @param cause
     * @param httpStatusCode
     * @param httpStatusLine
     * @param httpStatusMsg
     */
    public MissingLicenceException(final String message, final Throwable cause, final int httpStatusCode,
        final String httpStatusLine, final String httpStatusMsg) {
        super(message, cause, httpStatusCode, httpStatusLine, httpStatusMsg);
    }
}