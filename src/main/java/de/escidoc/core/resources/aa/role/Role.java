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
 * Copyright 2006-2010 Fachinformationszentrum Karlsruhe Gesellschaft
 * fuer wissenschaftlich-technische Information mbH and Max-Planck-
 * Gesellschaft zur Foerderung der Wissenschaft e.V.  
 * All rights reserved.  Use is subject to license terms.
 */
package de.escidoc.core.resources.aa.role;

import org.w3c.dom.Element;

import de.escidoc.core.annotations.JiBX;
import de.escidoc.core.resources.GenericResource;
import de.escidoc.core.resources.ResourceType;
import de.escidoc.core.resources.XLinkAutonomous;
import de.escidoc.core.resources.common.reference.Referenceable;
import de.escidoc.core.resources.common.reference.RoleRef;

/**
 * Role.
 * 
 * @author ?, SWA
 * 
 */
@JiBX
public class Role extends GenericResource
    implements XLinkAutonomous, Referenceable<RoleRef> {

    private RoleProperties properties;

    private Scope scope;

    private Element policyOrPolicySet;

    public Role() {
    }

    /**
     * Get Policy or PolicySet (as DOM Element).
     * 
     * @return DOM Element with Policy or PolicySet
     */
    public Element getPolicyOrPolicySet() {
        return policyOrPolicySet;
    }

    /**
     * Set Policy or PolicySet (as DOM Element).
     * 
     * @param policyOrPolicySet
     *            DOM Element with Policy or PolicySet
     */
    public void setPolicyOrPolicySet(final Element policyOrPolicySet) {
        this.policyOrPolicySet = policyOrPolicySet;
    }

    /**
     * Get Scope.
     * 
     * @return Scope
     */
    public Scope getScope() {
        return scope;
    }

    /**
     * Set Scope.
     * 
     * @param scope
     *            Scope
     */
    public void setScope(final Scope scope) {
        this.scope = scope;
    }

    /**
     * Get properties of Role.
     * 
     * @return role properties
     */
    public RoleProperties getProperties() {
        if (properties == null) {
            properties = new RoleProperties();
        }
        return properties;
    }

    /**
     * Set properties of role.
     * 
     * @param properties
     *            role properties
     */
    public void setProperties(final RoleProperties properties) {
        this.properties = properties;
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
    public RoleRef getReference() {
        return new RoleRef(getObjid());
    }

    @Override
    public ResourceType getResourceType() {
        return ResourceType.Role;
    }
}
