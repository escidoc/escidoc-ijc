/**
 * 
 */
package de.escidoc.core.resources;

import org.joda.time.DateTime;

import de.escidoc.core.common.DateTimeUtility;

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
     * @param lastModificationDate
     *            the lastModificationDate to set
     */
    public void setLastModificationDate(final DateTime lastModificationDate) {
        this.lastModificationDate = DateTimeUtility.normalize(lastModificationDate);
    }

    /**
     * @return the lastModificationDate
     */
    public DateTime getLastModificationDate() {
        return lastModificationDate;
    }
}