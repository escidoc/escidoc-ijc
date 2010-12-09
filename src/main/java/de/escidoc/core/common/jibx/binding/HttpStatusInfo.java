/**
 * 
 */
package de.escidoc.core.common.jibx.binding;

/**
 * @author MVO
 * 
 */
public class HttpStatusInfo {

    private int statusCode;

    private String redirectLocation;

    public HttpStatusInfo(final int statusCode) {
        this.statusCode = statusCode;
    }

    public HttpStatusInfo(final int statusCode, final String redirectLocation) {
        this.statusCode = statusCode;
        this.redirectLocation = redirectLocation;
    }

    /**
     * @return the statusCode
     */
    public final int getStatusCode() {
        return statusCode;
    }

    /**
     * @param statusCode
     *            the statusCode to set
     */
    public final void setStatusCode(final int statusCode) {
        this.statusCode = statusCode;
    }

    /**
     * @return the redirectLocation
     */
    public final String getRedirectLocation() {
        return redirectLocation;
    }

    /**
     * @param redirectLocation
     *            the redirectLocation to set
     */
    public final void setRedirectLocation(final String redirectLocation) {
        this.redirectLocation = redirectLocation;
    }
}
