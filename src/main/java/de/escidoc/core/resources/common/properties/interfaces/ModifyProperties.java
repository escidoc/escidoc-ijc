/**
 * 
 */
package de.escidoc.core.resources.common.properties.interfaces;

import de.escidoc.core.resources.common.reference.UserAccountRef;

/**
 * @author MVO
 * 
 */
public interface ModifyProperties {

    /**
     * 
     * @return The reference to the user, who updated this resource the last
     *         time.
     */
    UserAccountRef getModifiedBy();

    /**
     * @param ref
     */
    void setModifiedBy(UserAccountRef ref);
}
