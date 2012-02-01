package de.escidoc.core.client.exceptions.application.security;

import de.escidoc.core.client.exceptions.EscidocException;

public class SecurityException extends EscidocException implements java.io.Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -3897260962301405234L;

    private String redirectLocation;

    public SecurityException() {
    }

    /**
     * @param message
     * @param cause
     */
    public SecurityException(final String message, final Throwable cause) {
        super(message, cause);
    }

    /**
     * @param httpStatusCode
     * @param httpStatusLine
     * @param httpStatusMsg
     * @param redirectLocation
     */
    @Deprecated
    public SecurityException(final int httpStatusCode, final String httpStatusLine, final String httpStatusMsg,
        final String redirectLocation) {
        super(httpStatusCode, httpStatusLine, httpStatusMsg);
        this.redirectLocation = redirectLocation;
    }

    /**
     * @param message
     * @param cause
     * @param httpStatusCode
     * @param httpStatusLine
     * @param httpStatusMsg
     * @param redirectLocation
     */
    public SecurityException(final String message, final Throwable cause, final int httpStatusCode,
        final String httpStatusLine, final String httpStatusMsg, final String redirectLocation) {
        super(message, cause, httpStatusCode, httpStatusLine, httpStatusMsg);
        this.redirectLocation = redirectLocation;
    }

    /**
     * Gets the redirectLocation value for this SecurityException.
     * 
     * @return redirectLocation
     */
    public String getRedirectLocation() {
        return redirectLocation;
    }

    /**
     * Sets the redirectLocation value for this SecurityException.
     * 
     * @param redirectLocation
     */
    public void setRedirectLocation(final String redirectLocation) {
        this.redirectLocation = redirectLocation;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + (redirectLocation == null ? 0 : redirectLocation.hashCode());
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
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        final SecurityException other = (SecurityException) obj;

        // TODO
        super.equals(obj);

        if (redirectLocation == null) {
            if (other.redirectLocation != null)
                return false;
        }
        else if (!redirectLocation.equals(other.redirectLocation))
            return false;
        return true;
    }

}