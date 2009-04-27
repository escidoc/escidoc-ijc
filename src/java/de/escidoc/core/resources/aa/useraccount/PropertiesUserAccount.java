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

public class PropertiesUserAccount extends Properties {

    private String email;

    private String loginName;

    private ResourceRef modifiedBy;

    private boolean active;

    public Collection<ResourceRef> ous = new LinkedList<ResourceRef>();

    /**
     * @return the email
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * 
     * @param email
     */
    public void setEmail(final String email) {
        this.email = email;
    }

    /**
     * @return the loginName
     */
    public String getLoginName() {
        return this.loginName;
    }

    /**
     * 
     * @param loginName
     */
    public void setLoginName(final String loginName) {
        this.loginName = loginName;
    }

    /**
     * 
     * @return
     */
    public boolean isActive() {
        return this.active;
    }

    /**
     * 
     * @return
     */

    public Collection<ResourceRef> getOus() {
        return ous;
    }

    /**
     * 
     * @param ous
     */
    public void setOus(final Collection<ResourceRef> ous) {
        this.ous = ous;
    }

    /**
     * 
     * @param OrganizationalUnitid
     */
    public void add(final ResourceRef organizationalUnitRef) {
        this.ous.add(organizationalUnitRef);
    }

    /**
     * 
     * @param organizationalUnitId
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
     * @return the createdBy
     */
    public ResourceRef getModifiedBy() {
        return this.modifiedBy;
    }
}
