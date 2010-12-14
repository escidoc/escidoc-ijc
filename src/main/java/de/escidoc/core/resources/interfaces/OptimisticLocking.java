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

    public void setLastModificationDate(final DateTime lmd);

    public DateTime getLastModificationDate();
}
