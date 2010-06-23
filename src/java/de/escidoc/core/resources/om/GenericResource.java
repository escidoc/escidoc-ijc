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
     * Get Namespace binding.
     * 
     * @return namespaces
     */
    public HashMap<String, URL> getNsBinding() {
        return this.nsBinding;
    }

    /**
     * Set NamespacePrefix & Namespace binding.
     * 
     * @param ns
     *            Map with bindings.
     */
    public void setNsBinding(final HashMap<String, URL> ns) {
        this.nsBinding = ns;
    }

    /**
     * Set Namespace binding.
     * 
     * @param binding
     *            binding
     * @param ns
     *            Namespace
     * 
     */
    public void setNsBinding(final String binding, final URL ns) {
        this.nsBinding.put(binding, ns);
    }

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
     * Set last-modification-date.
     * 
     * @param lmd
     *            last-modification-date
     */
    public void setLastModificationDate(final DateTime lmd) {

        this.lastModificationDate = lmd;
    }

    /**
     * Set last-modification-date.
     * 
     * @param lmd
     *            last-modification-date
     */
    public void setLastModificationDateAsString(final String lmd) {

        if (lmd == null) {
            this.lastModificationDate = null;
        }
        else {
            this.lastModificationDate = new DateTime(lmd);
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
     * Set Persistent Identifier for object.
     * 
     * @param pid
     *            Persistent Identifier.
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
