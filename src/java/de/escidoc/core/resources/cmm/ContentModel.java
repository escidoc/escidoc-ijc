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

import de.escidoc.core.resources.om.GenericResource;

/**
 * The eSciDoc Content Model.
 * 
 * @author SWA
 * 
 */
public class ContentModel extends GenericResource {

    private ContentModelProperties properties = new ContentModelProperties();

    private MetadataRecordDefinitions metadataRecordDefinitions = null;

    private ResourceDefinitions resourceDefinitions = null;

    /**
     */
    public ContentModel() {
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

}
