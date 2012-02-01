package de.escidoc.core.client.exceptions;

public abstract class EscidocException extends EscidocClientException {

    /**
     * 
     */
    private static final long serialVersionUID = 3558216761374671827L;

    private int httpStatusCode;

    private String httpStatusLine;

    private String httpStatusMsg;

    /**
     * 
     */
    public EscidocException() {
    }

    /**
     * @param message
     * @param cause
     */
    public EscidocException(final String message, final Throwable cause) {
        super(message, cause);
    }

    /**
     * @param httpStatusCode
     * @param httpStatusLine
     * @param httpStatusMsg
     */
    @Deprecated
    public EscidocException(final int httpStatusCode, final String httpStatusLine, final String httpStatusMsg) {
        this.httpStatusCode = httpStatusCode;
        this.httpStatusLine = httpStatusLine;
        this.httpStatusMsg = httpStatusMsg;
    }

    /**
     * @param message
     * @param cause
     * @param httpStatusCode
     * @param httpStatusLine
     * @param httpStatusMsg
     */
    public EscidocException(final String message, final Throwable cause, final int httpStatusCode,
        final String httpStatusLine, final String httpStatusMsg) {
        super(message, cause);
        this.httpStatusCode = httpStatusCode;
        this.httpStatusLine = httpStatusLine;
        this.httpStatusMsg = httpStatusMsg;
    }

    /**
     * Gets the httpStatusCode value for this EscidocException.
     * 
     * @return httpStatusCode
     */
    public int getHttpStatusCode() {
        return httpStatusCode;
    }

    /**
     * Sets the httpStatusCode value for this EscidocException.
     * 
     * @param httpStatusCode
     */
    public void setHttpStatusCode(final int httpStatusCode) {
        this.httpStatusCode = httpStatusCode;
    }

    /**
     * Gets the httpStatusLine value for this EscidocException.
     * 
     * @return httpStatusLine
     */
    public String getHttpStatusLine() {
        return httpStatusLine;
    }

    /**
     * Sets the httpStatusLine value for this EscidocException.
     * 
     * @param httpStatusLine
     */
    public void setHttpStatusLine(final String httpStatusLine) {
        this.httpStatusLine = httpStatusLine;
    }

    /**
     * Gets the httpStatusMsg value for this EscidocException.
     * 
     * @return httpStatusMsg
     */
    public String getHttpStatusMsg() {
        return httpStatusMsg;
    }

    /**
     * Sets the httpStatusMsg value for this EscidocException.
     * 
     * @param httpStatusMsg
     */
    public void setHttpStatusMsg(final String httpStatusMsg) {
        this.httpStatusMsg = httpStatusMsg;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + httpStatusCode;
        result = prime * result + (httpStatusLine == null ? 0 : httpStatusLine.hashCode());
        result = prime * result + (httpStatusMsg == null ? 0 : httpStatusMsg.hashCode());
        return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final EscidocException other = (EscidocException) obj;
        if (httpStatusCode != other.httpStatusCode)
            return false;
        if (httpStatusLine == null) {
            if (other.httpStatusLine != null)
                return false;
        }
        else if (!httpStatusLine.equals(other.httpStatusLine))
            return false;
        if (httpStatusMsg == null) {
            if (other.httpStatusMsg != null)
                return false;
        }
        else if (!httpStatusMsg.equals(other.httpStatusMsg))
            return false;
        return true;
    }
}
