package de.escidoc.core.client.exceptions.system;

public class XmlParserSystemException extends SystemException {

    /**
     * 
     */
    private static final long serialVersionUID = 8917789116436120450L;

    public XmlParserSystemException() {
    }

    public XmlParserSystemException(final String message, final Throwable cause) {
        super(message, cause);
    }

    @Deprecated
    public XmlParserSystemException(final int httpStatusCode, final String httpStatusLine, final String httpStatusMsg) {
        super(httpStatusCode, httpStatusLine, httpStatusMsg);
    }

    public XmlParserSystemException(final String message, final Throwable cause, final int httpStatusCode,
        final String httpStatusLine, final String httpStatusMsg) {
        super(message, cause, httpStatusCode, httpStatusLine, httpStatusMsg);
    }
}