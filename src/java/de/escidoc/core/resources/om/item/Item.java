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
package de.escidoc.core.resources.om.item;

import de.escidoc.core.resources.common.ContentStreams;
import de.escidoc.core.resources.common.MetadataRecords;
import de.escidoc.core.resources.common.Relations;
import de.escidoc.core.resources.om.GenericVersionableResource;
import de.escidoc.core.resources.om.item.component.Components;

/**
 * Item.
 * 
 * @author ?, SWA
 */
public class Item extends GenericVersionableResource {

    private ItemProperties properties = new ItemProperties();

    private Components components = new Components();

    private MetadataRecords mdRecords = new MetadataRecords();

    private ContentStreams contentStreams = new ContentStreams();

    private Relations relations = new Relations();

    /**
     * Item.
     */
    public Item() {
    }

    /**
     * Return the Resource properties.
     * 
     * @return properties
     */
    public ItemProperties getProperties() {
        return this.properties;
    }

    /**
     * Set Resource properties.
     * 
     * @param properties
     *            The new ItemProperties.
     */
    public void setProperties(final ItemProperties properties) {
        this.properties = properties;
    }

    /**
     * Get Components.
     * 
     * @return Components
     */
    public Components getComponents() {

        return this.components;
    }

    /**
     * Get Metadata Records.
     * 
     * @return MetadataRecords
     */
    public MetadataRecords getMetadataRecords() {

        return this.mdRecords;
    }

    /**
     * Get Relations.
     * 
     * @return Relations
     */
    public Relations getRelations() {

        return this.relations;
    }

    /**
     * Set Components.
     * 
     * @param components
     *            The new Components of Item.
     */
    public void setComponents(final Components components) {

        this.components = components;

    }

    /**
     * Set MetadataRecords.
     * 
     * @param metadataRecords
     *            The new MetadataRecords of Item.
     */
    public void setMetadataRecords(final MetadataRecords metadataRecords) {

        this.mdRecords = metadataRecords;
    }

    /**
     * Set Relations.
     * 
     * @param relations
     *            The new Relations of Item.
     */
    public void setRelations(final Relations relations) {

        this.relations = relations;

    }

    /**
     * @return the contentStreams
     */
    public ContentStreams getContentStreams() {
        return contentStreams;
    }

    /**
     * @param contentStreams
     *            the contentStreams to set
     */
    public void setContentStreams(final ContentStreams contentStreams) {
        this.contentStreams = contentStreams;
    }

}
