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
package de.escidoc.core.resources.aa.useraccount;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import de.escidoc.core.resources.ResourceRef;
import de.escidoc.core.resources.common.properties.Properties;

/**
 * Properties of User Account.
 * 
 * @author ?, SWA
 * 
 */
public class UserAccountProperties extends Properties {

    private String email;

    private String loginName;

    private ResourceRef modifiedBy;

    private boolean active;

    private Collection<ResourceRef> ous = new LinkedList<ResourceRef>();

    /**
     * @return the email
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * Set e-mail of user.
     * 
     * @param email
     *            User e-mail address
     */
    public void setEmail(final String email) {
        this.email = email;
    }

    /**
     * Get login name of user.
     * 
     * @return the login name
     */
    public String getLoginName() {
        return this.loginName;
    }

    /**
     * Set login name of user.
     * 
     * @param loginName
     *            User login name
     */
    public void setLoginName(final String loginName) {
        this.loginName = loginName;
    }

    /**
     * Is user account active.
     * 
     * @return true if active, false otherwise
     */
    public boolean isActive() {
        return this.active;
    }

    /**
     * Get related Organizational Unit.
     * 
     * @return Collection of Organizational Units
     */
    public Collection<ResourceRef> getOus() {
        return ous;
    }

    /**
     * Set related Organizational Units.
     * 
     * @param ous
     *            Collection of Organizational Units
     */
    public void setOus(final Collection<ResourceRef> ous) {
        this.ous = ous;
    }

    /**
     * Add an Organizational Unit to the list of related Organizational Units.
     * 
     * @param organizationalUnitRef
     *            ResourceRef of related Organizational Unit
     */
    public void add(final ResourceRef organizationalUnitRef) {
        this.ous.add(organizationalUnitRef);
    }

    /**
     * Delete an Organizational Unit from the related Organizational Unit list.
     * 
     * @param organizationalUnitId
     *            objid of organizational Unit
     * 
     */
    public void del(final String organizationalUnitId) {

        Iterator<ResourceRef> resRefIter = this.ous.iterator();
        while (resRefIter.hasNext()) {
            ResourceRef next = resRefIter.next();
            if (next.getObjid().equals(organizationalUnitId)) {
                resRefIter.remove();
                break;
            }
        }

    }

    /**
     * Get ResourceRef of latest modifier.
     * 
     * @return modified by
     */
    public ResourceRef getModifiedBy() {
        return this.modifiedBy;
    }
}
