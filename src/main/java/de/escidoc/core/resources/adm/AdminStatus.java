/**
 * 
 */
package de.escidoc.core.resources.adm;

/**
 * @author MVO
 * 
 */
public abstract class AdminStatus {

    public static final int STATUS_FINISHED = 0;

    public static final int STATUS_IN_PROGRESS = 1;

    public static final int STATUS_INVALID_RESULT = -1;

    protected String statusMessage;

    protected int statusCode;

    /**
     * @return the statusMessage
     */
    public String getStatusMessage() {
        return statusMessage;
    }

    /**
     * @return the statusCode
     */
    public int getStatusCode() {
        return statusCode;
    }
}
