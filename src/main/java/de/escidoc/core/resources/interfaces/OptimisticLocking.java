/**
 * 
 */
package de.escidoc.core.resources.interfaces;

import org.joda.time.DateTime;

/**
 * @author MVO
 * 
 */
public interface OptimisticLocking {

    /**
     * Set the last modification date.
     * 
     * TODO: should the client be able to set it?
     * 
     * @param lmd
     */
    void setLastModificationDate(final DateTime lmd);

    /**
     * Get the last modification date.
     * 
     * @return the last modification date.
     */
    DateTime getLastModificationDate();
}