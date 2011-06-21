/**
 * 
 */
package de.escidoc.core.resources.common.properties.interfaces;

import org.joda.time.DateTime;

import de.escidoc.core.resources.common.reference.UserAccountRef;

/**
 * @author MVO
 * 
 */
public interface CreateProperties {

    /**
     * @return The reference to the creator of this resource.
     */
    UserAccountRef getCreatedBy();

    /**
     * 
     * @param ref
     */
    void setCreatedBy(UserAccountRef ref);

    /**
     * @return The date of the creation of this resource.
     */
    DateTime getCreationDate();

    /**
     * @param date
     */
    void setCreationDate(DateTime date);
}
