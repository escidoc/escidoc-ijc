/**
 * 
 */
package de.escidoc.core.resources;

import org.joda.time.DateTime;

/**
 * @author MVO
 * 
 */
public abstract class GenericResource extends Resource {

    /**
     * The time stamp for optimistic locking.
     */
    private DateTime lastModificationDate;

    /**
     * 
     */
    public GenericResource() {
        super();
    }

    /**
     * @param objid
     */
    public GenericResource(final String objid) {
        super(objid);
    }

    /**
     * @param href
     * @param title
     */
    public GenericResource(final String href, final String title) {
        super(href, title);
    }

    /**
     * @param objid
     * @param href
     * @param title
     */
    public GenericResource(final String objid, final String href,
        final String title) {
        super(objid, href, title);
    }

    /**
     * @param lastModificationDate
     *            the lastModificationDate to set
     */
    public void setLastModificationDate(final DateTime lastModificationDate) {
        this.lastModificationDate = lastModificationDate;
    }

    /**
     * @return the lastModificationDate
     */
    public DateTime getLastModificationDate() {
        return lastModificationDate;
    }
}