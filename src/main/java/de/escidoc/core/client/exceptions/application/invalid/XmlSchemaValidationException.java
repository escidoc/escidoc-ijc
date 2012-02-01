package de.escidoc.core.client.exceptions.application.invalid;

/**
 * @author Marko Voß
 * 
 */
public class XmlSchemaValidationException extends InvalidXmlException {

    /**
     * 
     */
    private static final long serialVersionUID = 3217904621978874327L;

    /**
     * 
     */
    public XmlSchemaValidationException() {
    }

    /**
     * @param message
     * @param cause
     */
    public XmlSchemaValidationException(final String message, final Throwable cause) {
        super(message, cause);
    }

    /**
     * @param httpStatusCode
     * @param httpStatusLine
     * @param httpStatusMsg
     */
    @Deprecated
    public XmlSchemaValidationException(final int httpStatusCode, final String httpStatusLine,
        final String httpStatusMsg) {
        super(httpStatusCode, httpStatusLine, httpStatusMsg);
    }

    /**
     * @param message
     * @param cause
     * @param httpStatusCode
     * @param httpStatusLine
     * @param httpStatusMsg
     */
    public XmlSchemaValidationException(final String message, final Throwable cause, final int httpStatusCode,
        final String httpStatusLine, final String httpStatusMsg) {
        super(message, cause, httpStatusCode, httpStatusLine, httpStatusMsg);
    }
}