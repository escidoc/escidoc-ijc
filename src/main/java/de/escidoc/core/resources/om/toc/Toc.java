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
package de.escidoc.core.resources.om.toc;

import de.escidoc.core.resources.ResourceType;
import de.escidoc.core.resources.common.ContentStreams;
import de.escidoc.core.resources.common.MetadataRecords;
import de.escidoc.core.resources.common.properties.Properties;
import de.escidoc.core.resources.om.GenericVersionableResource;

/**
 * Interface for Table Of Content Properties.
 * 
 * @author SWA
 * 
 */
public class Toc extends GenericVersionableResource {

    public MetadataRecords mdRecords = new MetadataRecords();

    public ContentStreams contentStreams = new ContentStreams();

    private Properties properties = null;

    /**
     * 
     */
    public Toc() {
    }

    /**
     * See Interface for functional description.
     * 
     * @return
     * @see de.escidoc.core.resources.interfaces.toc.TocInterface#getProperties()
     */
    public Properties getProperties() {

        return this.properties;
    }

    /**
     * See Interface for functional description.
     * 
     * @param properties
     * @see de.escidoc.core.resources.interfaces.toc.TocInterface#setProperties(de.escidoc.core.om.resources.interfaces.om.toc.Properties)
     */
    public void setProperties(final Properties properties) {

        this.properties = properties;
    }

    /**
     * See Interface for functional description.
     * 
     * @param mdRecords
     * @see de.escidoc.core.resources.interfaces.toc.TocInterface#setMetadataRecords(de.escidoc.core.resources.common.MetadataRecords)
     */
    public void setMetadataRecords(final MetadataRecords metadataRecords) {

        this.mdRecords = metadataRecords;

    }

    /**
     * 
     * @return
     */
    public MetadataRecords getMetadataRecords() {

        return mdRecords;
    }

    @Override
    public ResourceType getResourceType() {
        return ResourceType.Toc;
    }

}
