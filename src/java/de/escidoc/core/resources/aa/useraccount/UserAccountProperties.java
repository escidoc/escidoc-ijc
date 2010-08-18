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

import org.joda.time.DateTime;

import de.escidoc.core.resources.ResourceRef;

/**
 * Properties of User Account.
 * 
 * @author SWA
 * 
 */
public class UserAccountProperties {

    private DateTime creationDate = null;

    private String loginName;

    private ResourceRef createdBy;

    private ResourceRef modifiedBy;

    private String name;

    private boolean active;

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
     * Get name of user.
     * 
     * @return the name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Set name of user.
     * 
     * @param name
     *            User name
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * Is user account active.
     * 
     * @return true if active, false otherwise
     */
    public boolean isActive() {
        return this.active;
    }

    public void setActive(boolean isActive) {
        this.active = isActive;
    }

    /**
     * Get ResourceRef of creator.
     * 
     * @return the creator of the User Account
     */
    public ResourceRef getCreatedBy() {
        return this.createdBy;
    }

    /**
     * Get ResourceRef of latest modifier.
     * 
     * @return modified by
     */
    public ResourceRef getModifiedBy() {
        return this.modifiedBy;
    }

    /**
     * Get creation date of User Account.
     * 
     * @return creation date
     */
    public DateTime getCreationDate() {
        return this.creationDate;
    }

    /**
     * Set creation date of User Account.
     * 
     * @param creationDate
     *            the creationDate to set
     */
    public void setCreationDate(final DateTime creationDate) {

        this.creationDate = creationDate;
    }

}
