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
package de.escidoc.core.resources.om.context;

import de.escidoc.core.annotations.JiBX;
import de.escidoc.core.resources.GenericResource;
import de.escidoc.core.resources.ResourceType;
import de.escidoc.core.resources.common.reference.ContextRef;
import de.escidoc.core.resources.common.reference.Referenceable;

/**
 * The eSciDoc Context.
 * 
 * @author SWA
 * 
 */
@JiBX
public class Context extends GenericResource
    implements Referenceable<ContextRef> {

    private AdminDescriptors adminDescriptors;

    private ContextProperties properties;

    /**
     */
    public Context() {
    }

    /**
     * Return the Resource properties.
     * 
     * @return properties
     */
    public ContextProperties getProperties() {
        if (properties == null) {
            properties = new ContextProperties();
        }
        return properties;
    }

    /**
     * Set Resource properties.
     * 
     * @param properties
     *            The new ContextProperties.
     */
    public void setProperties(final ContextProperties properties) {
        this.properties = properties;
    }

    /**
     * Get the AdminDescriptors.
     * 
     * @return AdminDescriptors
     */
    public AdminDescriptors getAdminDescriptors() {
        if (adminDescriptors == null) {
            adminDescriptors = new AdminDescriptors();
        }
        return adminDescriptors;
    }

    /**
     * Set AdminDescriptors.
     * 
     * @param adminDescriptors
     *            New AdminDescriptors.
     */
    public void setAdminDescriptors(final AdminDescriptors adminDescriptors) {
        this.adminDescriptors = adminDescriptors;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.resources.common.reference.Referenceable#getReference()
     */
    @Override
    public ContextRef getReference() {
        return new ContextRef(getObjid());
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.resources.Resource#getResourceType()
     */
    @Override
    public ResourceType getResourceType() {
        return ResourceType.CONTEXT;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.resources.XLinkAutonomousX#generateXLinkHref(java.lang
     * .String)
     */
    @Override
    public void generateXLinkHref(final String parentPath) {
        if (getXLinkHref() != null) {

            if (properties != null) {
                properties.generateXLinkHref(getXLinkHref());
            }
            if (adminDescriptors != null) {
                adminDescriptors.generateXLinkHref(getXLinkHref());
            }
        }
    }
}