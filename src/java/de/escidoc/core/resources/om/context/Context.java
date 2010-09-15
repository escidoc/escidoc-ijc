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

import de.escidoc.core.resources.ResourceRef;
import de.escidoc.core.resources.XLinkAutonomous;
import de.escidoc.core.resources.om.GenericResource;

/**
 * The eSciDoc Context.
 * 
 * @author SWA
 * 
 */
public class Context extends GenericResource implements XLinkAutonomous {

    private AdminDescriptors adminDescriptors = null;

    private Properties properties = null;

    /**
     */
    public Context() {
        setResourceType(RESOURCE_TYPE.Context);
    }

    /**
     * Return the Resource properties.
     * 
     * @return properties
     */
    public Properties getProperties() {

        return this.properties;
    }

    /**
     * Set Resource properties.
     * 
     * @param properties
     *            The new ContextProperties.
     */
    public void setProperties(final Properties properties) {
        this.properties = properties;
    }

    /**
     * Get the AdminDescriptors.
     * 
     * @return AdminDescriptors
     */
    public AdminDescriptors getAdminDescriptors() {
        return this.adminDescriptors;
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

    /**
     * XLinkHref validation for JiBX. This method will be called by the JiBX
     * binding for the REST transport protocol as post-set.
     */
    public void genXLink() {
        genOwnXLinkHref();

        if (properties != null) {
            if (properties.getXLinkHref() == null && getXLinkHref() != null) {
                properties.setXLinkHref(getXLinkHref() + "/properties");
            }
            genXLinkHref(properties.getCreatedBy(), RESOURCE_TYPE.UserAccount,
                null);
            genXLinkHref(properties.getModifiedBy(), RESOURCE_TYPE.UserAccount,
                null);

            if (properties.getOrganizationalUnitRefs() != null) {
                for (ResourceRef ouRef : properties.getOrganizationalUnitRefs()) {
                    genXLinkHref(ouRef, RESOURCE_TYPE.OrganizationalUnit, null);
                }
            }
        }
        if (adminDescriptors != null && getXLinkHref() != null) {
            if (adminDescriptors.getXLinkHref() == null) {
                adminDescriptors.setXLinkHref(getXLinkHref()
                    + "/admin-descriptors");
            }

            for (AdminDescriptor descriptor : adminDescriptors) {
                if (descriptor.getXLinkHref() == null) {
                    descriptor.setXLinkHref(getXLinkHref()
                        + "/admin-descriptors/admin-descriptor/"
                        + descriptor.getName());
                }
            }
        }
    }
}
