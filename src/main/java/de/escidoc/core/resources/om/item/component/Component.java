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
package de.escidoc.core.resources.om.item.component;

import de.escidoc.core.annotations.JiBX;
import de.escidoc.core.resources.GenericResource;
import de.escidoc.core.resources.ResourceType;
import de.escidoc.core.resources.common.MetadataRecords;

/**
 * Component.
 * 
 * @author SWA
 * 
 */
@JiBX
public class Component extends GenericResource {

    private ComponentProperties properties;

    private ComponentContent content;

    private MetadataRecords mdRecords = null;

    /**
     * Component.
     */
    public Component() {

    }

    /**
     * Component.
     * 
     * @param id
     *            Objid of Component.
     */
    public Component(final String id) {
        super(id);
    }

    /**
     * Get Content of Component.
     * 
     * @return the content
     */
    public ComponentContent getContent() {
        return content;
    }

    /**
     * Set Content of Component.
     * 
     * @param content
     *            the content to set
     */
    public void setContent(final ComponentContent content) {
        this.content = content;
    }

    /**
     * @return the mdRecords
     */
    public MetadataRecords getMetadataRecords() {
        return mdRecords;
    }

    /**
     * Set Metadata records.
     * 
     * @param records
     *            the mdRecords to set
     */
    public void setMetadataRecords(final MetadataRecords records) {
        this.mdRecords = records;
    }

    /**
     * Get Properties of Component.
     * 
     * @return ComponentProperties
     */
    public ComponentProperties getProperties() {
        return properties;
    }

    /**
     * Set Properties of Component.
     * 
     * @param properties
     *            Properties of Component.
     */
    public void setProperties(final ComponentProperties properties) {
        this.properties = properties;
    }

    @Override
    public ResourceType getResourceType() {
        return ResourceType.Component;
    }

}
