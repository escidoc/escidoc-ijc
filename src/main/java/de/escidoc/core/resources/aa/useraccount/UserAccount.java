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

import de.escidoc.core.annotations.JiBX;
import de.escidoc.core.resources.ResourceType;
import de.escidoc.core.resources.XLinkAutonomous;
import de.escidoc.core.resources.common.reference.Referenceable;
import de.escidoc.core.resources.common.reference.UserAccountRef;
import de.escidoc.core.resources.om.GenericResource;

/**
 * User Account.
 * 
 * @author ROF, SWA
 * 
 */
@JiBX
public class UserAccount extends GenericResource
    implements XLinkAutonomous, Referenceable<UserAccountRef> {

    private UserAccountProperties properties;

    /**
     * 
     */
    public UserAccount() {
    }

    /**
     * Get User Account Properties.
     * 
     * @return Properties of User Account
     */
    public UserAccountProperties getProperties() {
        if (properties == null) {
            properties = new UserAccountProperties();
        }
        return properties;
    }

    /**
     * Set User Account Properties.
     * 
     * @param properties
     *            Properties of User Account
     */
    public void setProperties(final UserAccountProperties properties) {
        this.properties = properties;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result =
            prime * result + (properties == null ? 0 : properties.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!super.equals(obj)) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        UserAccount other = (UserAccount) obj;
        if (properties == null) {
            if (other.properties != null) {
                return false;
            }
        }
        else if (!properties.equals(other.properties)) {
            return false;
        }
        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.resources.XLinkAutonomous#genXLink()
     */
    @Override
    public void genXLink() {
        genOwnXLinkHref();

        if (properties != null) {
            genXLinkHref(properties.getCreatedBy(), ResourceType.UserAccount,
                null);
            genXLinkHref(properties.getModifiedBy(), ResourceType.UserAccount,
                null);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.resources.common.reference.Referenceable#getReference()
     */
    @Override
    public UserAccountRef getReference() {
        return new UserAccountRef(getObjid());
    }

    @Override
    public ResourceType getResourceType() {
        return ResourceType.UserAccount;
    }
}
