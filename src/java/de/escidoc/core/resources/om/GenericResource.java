/*
 * CDDL HEADER START
 *
 * The contents of this file are subject to the terms of the
 * Common Development and Distribution License, Version 1.0 only
 * (the "License").  You may not use this file except in compliance
 * with the License.
 *
 * You can obtain a copy of the license at license/ESCIDOC.LICENSE
 * or http://www.escidoc.de/license.
 * See the License for the specific language governing permissions
 * and limitations under the License.
 *
 * When distributing Covered Code, include this CDDL HEADER in each
 * file and include the License file at license/ESCIDOC.LICENSE.
 * If applicable, add the following below this CDDL HEADER, with the
 * fields enclosed by brackets "[]" replaced with your own identifying
 * information: Portions Copyright [yyyy] [name of copyright owner]
 *
 * CDDL HEADER END
 */

/*
 * Copyright 2006-2008 Fachinformationszentrum Karlsruhe Gesellschaft
 * fuer wissenschaftlich-technische Information mbH and Max-Planck-
 * Gesellschaft zur Foerderung der Wissenschaft e.V.  
 * All rights reserved.  Use is subject to license terms.
 */
package de.escidoc.core.resources.om;

import java.net.URL;
import java.util.HashMap;

import org.joda.time.DateTime;

import de.escidoc.core.resources.ResourceRef;

/**
 * 
 * @author SWA
 * 
 */
public class GenericResource extends ResourceRef {

    private DateTime lastModificationDate = null;

    private final boolean isLocked = false;

    private final String lockOwner = null;

    private final String lockDate = null;

    private HashMap<String, URL> nsBinding = null;

    private String objectPid = null;

    /**
     * 
     */
    public GenericResource() {
    }

    /**
     * 
     * @param id
     *            The id of the object in the repository.
     */
    public GenericResource(final String id) {

        setObjid(id);
    }

    /**
     * 
     * @return
     */
    public HashMap<String, URL> getNsBinding() {
        return this.nsBinding;
    }

    /**
     * 
     * @param ns
     */
    public void setNsBinding(final HashMap<String, URL> ns) {
        this.nsBinding = ns;
    }

    /**
     * 
     * @param ns
     */
    public void setNsBinding(final String binding, final URL ns) {
        this.nsBinding.put(binding, ns);
    }

    // /**
    // * Return the Resource properties.
    // *
    // * @return properties
    // */
    // public Properties getProperties() {
    // return this.properties;
    // }
    //
    // /**
    // * Set Resource properties.
    // *
    // * @param properties
    // */
    // public void setProperties(final Properties properties) {
    // this.properties = properties;
    // }
    //
    /**
     * Get last modification date of the resource.
     * 
     * @return last-modification-date
     */
    public DateTime getLastModificationDate() {
        return this.lastModificationDate;
    }

    /**
     * Get last modification date of the resource.
     * 
     * @return last-modification-date
     */
    public String getLastModificationDateAsString() {
        if (this.lastModificationDate != null) {
            return this.lastModificationDate.toString();
        }
        return null;
    }

    /**
     * 
     * @param lastModificationDate
     */
    public void setLastModificationDate(final DateTime lastModificationDate) {

        this.lastModificationDate = lastModificationDate;
    }

    /**
     * 
     * @param lastModificationDate
     */
    public void setLastModificationDateAsString(
        final String lastModificationDate) {
        if (lastModificationDate == null) {
            this.lastModificationDate = null;  
        } else {
        this.lastModificationDate = new DateTime(lastModificationDate);
        }
    }

    /**
     * Check if object is locked.
     * 
     * @return Whether the resource is locked or not.
     */
    public boolean isLocked() {

        return this.isLocked;
    }

    /**
     * If the item is locked the lock owner is returned, null otherwise.
     * 
     * @return The lock owner or null.
     */
    public String getLockOwner() {

        return this.lockOwner;
    }

    /**
     * If the container is locked the lock date is returned, null otherwise.
     * 
     * @return The lock date or null.
     */
    public String getLockDate() {

        return this.lockDate;
    }

    /**
     * Lock/Unlock object.
     * 
     * @param lock
     *            True == lock object. False == unlock object.
     */
    public void setLocked(final boolean lock) {
        // if(lock) { this.lock = true; }
        // else { this.lock = false; }
    }

    /**
     * 
     * @param pid
     */
    public void setObjectPid(final String pid) {
        this.objectPid = pid;
    }

    /**
     * Get the objectPid of the resource.
     * 
     * @return The ObjectPid of the resource.
     */
    public String getObjectPid() {
        return this.objectPid;
    }

}
