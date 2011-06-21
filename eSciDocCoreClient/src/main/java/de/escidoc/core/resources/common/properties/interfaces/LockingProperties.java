/**
 * 
 */
package de.escidoc.core.resources.common.properties.interfaces;

import org.joda.time.DateTime;

import de.escidoc.core.resources.common.properties.LockStatus;
import de.escidoc.core.resources.common.reference.UserAccountRef;

/**
 * @author MVO
 * 
 */
public interface LockingProperties {

    /**
     * @return
     */
    LockStatus getLockStatus();

    /**
     * @param status
     */
    void setLockStatus(LockStatus status);

    /**
     * @return
     */
    DateTime getLockDate();

    /**
     * @param date
     */
    void setLockDate(DateTime date);

    /**
     * @return
     */
    UserAccountRef getLockOwner();

    /**
     * @param user
     */
    void setLockOwner(UserAccountRef user);
}