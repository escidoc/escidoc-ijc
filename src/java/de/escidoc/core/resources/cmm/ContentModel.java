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
package de.escidoc.core.resources.cmm;

import de.escidoc.core.resources.Resource;
import de.escidoc.core.resources.XLinkAutonomous;
import de.escidoc.core.resources.common.properties.Version;
import de.escidoc.core.resources.common.reference.ContentModelRef;
import de.escidoc.core.resources.common.reference.Referenceable;
import de.escidoc.core.resources.om.GenericResource;

/**
 * The eSciDoc Content Model.
 * 
 * @author SWA
 * 
 */
public class ContentModel extends GenericResource
    implements XLinkAutonomous, Referenceable<ContentModelRef> {

    private ContentModelProperties properties = new ContentModelProperties();

    private MetadataRecordDefinitions metadataRecordDefinitions;

    private ResourceDefinitions resourceDefinitions;

    /**
     */
    public ContentModel() {
        setResourceType(RESOURCE_TYPE.ContentModel);
    }

    /**
     * Return the ContentModel properties.
     * 
     * @return properties
     */
    public ContentModelProperties getProperties() {
        return this.properties;
    }

    /**
     * Set ContentModel properties.
     * 
     * @param properties
     *            The new ContentModelProperties.
     */
    public void setProperties(final ContentModelProperties properties) {
        this.properties = properties;
    }

    /**
     * Set MetadataRecordDefinitions.
     * 
     * @param metadataRecordDefinitions
     *            The MetadataRecordDefinitions.
     */
    public void setMetadataRecordDefinitions(
        final MetadataRecordDefinitions metadataRecordDefinitions) {
        this.metadataRecordDefinitions = metadataRecordDefinitions;
    }

    /**
     * Get MetadataRecordDefinitions.
     * 
     * @return MetadataRecordDefinitions
     */
    public MetadataRecordDefinitions getMetadataRecordDefinitions() {
        return metadataRecordDefinitions;
    }

    /**
     * Set ResourceDefinitions.
     * 
     * @param resourceDefinitions
     *            The resource definitions
     */
    public void setResourceDefinitions(
        final ResourceDefinitions resourceDefinitions) {
        this.resourceDefinitions = resourceDefinitions;
    }

    /**
     * Get resource definitions.
     * 
     * @return ResourceDefinitions
     */
    public ResourceDefinitions getResourceDefinitions() {
        return resourceDefinitions;
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.resources.XLinkAutonomous#genXLink()
     */
    public void genXLink() {
        genOwnXLinkHref();

        if (properties != null) {
            if (properties.getXLinkHref() == null && getXLinkHref() != null) {
                properties.setXLinkHref(getXLinkHref() + "/properties");
            }
            genXLinkHref(properties.getCreatedBy(), RESOURCE_TYPE.UserAccount,
                null);
            genXLinkHref(properties.getLockOwner(), RESOURCE_TYPE.UserAccount,
                null);
            genVersionHref((Version) properties.getVersion());
            genVersionHref((Version) properties.getLatestVersion());
            genVersionHref((Version) properties.getLatestRelease());
        }
        if (metadataRecordDefinitions != null && getXLinkHref() != null) {
            for (MetadataRecordDefinition def : metadataRecordDefinitions) {
                if (def.getXLinkHref() == null && def.getName() != null) {
                    def.setXLinkHref(getXLinkHref()
                        + "/md-record-definitions/md-record-definition/"
                        + def.getName() + "/schema/content");
                }
            }
        }
        if (resourceDefinitions != null && getXLinkHref() != null) {
            for (ResourceDefinition def : resourceDefinitions) {
                if (def.getXLinkHref() == null && def.getName() != null) {
                    def.setXLinkHref(getXLinkHref()
                        + "/md-record-definitions/md-record-definition/"
                        + def.getName() + "/xslt/content");
                }
            }
        }
    }

    /**
     * Method used by ResourceRef implementations to ensure a fully valid
     * xLinkHref definition for all sub resources they may own. The validation
     * methods calling this method may be called by JiBX as post-set methods.
     * 
     * @param version
     */
    protected void genVersionHref(Version version) {
        if (version != null && version.getXLinkHref() == null) {
            version.setXLinkHref(Resource.RESOURCE_URL_MAP
                .get(RESOURCE_TYPE.Item)
                + "/"
                + getObjid()
                + ":"
                + version.getNumber());
            genXLinkHref(version.getModifiedBy(), RESOURCE_TYPE.UserAccount,
                null);
        }
    }

    /* (non-Javadoc)
     * @see de.escidoc.core.resources.common.reference.Referenceable#getReference()
     */
    public ContentModelRef getReference() {
        return new ContentModelRef(getObjid());
    }
}
