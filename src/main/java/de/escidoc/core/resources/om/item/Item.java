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

import de.escidoc.core.resources.Resource;
import de.escidoc.core.resources.ResourceType;
import de.escidoc.core.resources.XLinkAutonomous;
import de.escidoc.core.resources.common.ContentStreams;
import de.escidoc.core.resources.common.MetadataRecord;
import de.escidoc.core.resources.common.MetadataRecords;
import de.escidoc.core.resources.common.Relations;
import de.escidoc.core.resources.common.properties.VersionImpl;
import de.escidoc.core.resources.common.reference.ItemRef;
import de.escidoc.core.resources.common.reference.Referenceable;
import de.escidoc.core.resources.om.GenericVersionableResource;
import de.escidoc.core.resources.om.item.component.Component;
import de.escidoc.core.resources.om.item.component.ComponentProperties;
import de.escidoc.core.resources.om.item.component.Components;

/**
 * Item.
 * 
 * @author ?, SWA
 */
public class Item extends GenericVersionableResource
    implements XLinkAutonomous, Referenceable<ItemRef> {

    private ItemProperties properties;

    private Components components;

    private MetadataRecords mdRecords;

    private ContentStreams contentStreams;

    private Relations relations;

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
        if (properties == null)
            properties = new ItemProperties();
        return properties;
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

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.resources.XLinkAutonomous#genXLink()
     */
    @Override
    public void genXLink() {
        genOwnXLinkHref();

        if (properties != null) {
            if (properties.getXLinkHref() == null && getXLinkHref() != null) {
                properties.setXLinkHref(getXLinkHref() + "/properties");
            }
            genXLinkHref(properties.getCreatedBy(), ResourceType.UserAccount,
                null);
            genXLinkHref(properties.getContext(), ResourceType.Context, null);
            genXLinkHref(properties.getContentModel(),
                ResourceType.ContentModel, null);
            genXLinkHref(properties.getOrigin(), ResourceType.Item, null);
            genXLinkHref(properties.getLockOwner(), ResourceType.UserAccount,
                null);
            genVersionHref((VersionImpl) properties.getVersion());
            genVersionHref((VersionImpl) properties.getLatestVersion());
            genVersionHref((VersionImpl) properties.getLatestRelease());
        }
        if (components != null) {
            if (components.getXLinkHref() == null && getXLinkHref() != null) {
                components.setXLinkHref(getXLinkHref() + "/components");
            }

            for (Component component : components) {
                String cPrefix = null;
                if (getXLinkHref() != null) {
                    cPrefix =
                        genXLinkHref(component, ResourceType.Component,
                            getXLinkHref());
                }

                ComponentProperties properties = component.getProperties();
                if (properties != null) {
                    if (properties.getXLinkHref() == null && cPrefix != null) {
                        properties.setXLinkHref(cPrefix + "/properties");
                    }
                    genXLinkHref(properties.getCreatedBy(),
                        ResourceType.UserAccount, null);
                    genXLinkHref(properties.getModifiedBy(),
                        ResourceType.UserAccount, null);
                }

                if (component.getContent() != null
                    && component.getContent().getXLinkHref() == null
                    && cPrefix != null) {
                    component.getContent().setXLinkHref(cPrefix + "/content");
                }

                if (component.getMetadataRecords() != null && cPrefix != null) {
                    if (component.getMetadataRecords() == null) {
                        component.getMetadataRecords().setXLinkHref(
                            cPrefix + "/md-records");
                    }

                    for (MetadataRecord record : component.getMetadataRecords()) {
                        if (record.getXLinkHref() == null) {
                            record.setXLinkHref(cPrefix
                                + "/md-records/md-record/" + record.getName());
                        }
                    }
                }
            }
        }
        if (mdRecords != null && getXLinkHref() != null) {
            if (mdRecords.getXLinkHref() == null) {
                mdRecords.setXLinkHref(getXLinkHref() + "/md-records");
            }

            for (MetadataRecord record : mdRecords) {
                if (record.getXLinkHref() == null && record.getName() != null) {
                    record.setXLinkHref(getXLinkHref()
                        + "/md-records/md-record/" + record.getName());
                }
            }
        }
        if (contentStreams != null) {
            // TODO
        }
        if (relations != null && relations.getXLinkHref() == null
            && getXLinkHref() != null) {
            relations.setXLinkHref(getXLinkHref() + "/relations");
            /**
             * If relations are not set by the binding, it is impossible to
             * calculate their HREF since we only know the ID but not the type.
             */
        }
    }

    /**
     * Method used by ResourceRef implementations to ensure a fully valid
     * xLinkHref definition for all sub resources they may own. The validation
     * methods calling this method may be called by JiBX as post-set methods.
     * 
     * @param version
     */
    protected void genVersionHref(final VersionImpl version) {
        if (version != null && version.getXLinkHref() == null) {
            version.setXLinkHref(Resource.RESOURCE_URL_MAP
                .get(ResourceType.Item)
                + "/"
                + getObjid()
                + ":"
                + version.getNumber());
            genXLinkHref(version.getModifiedBy(), ResourceType.UserAccount,
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
    public ItemRef getReference() {
        return new ItemRef(getObjid());
    }

    @Override
    public ResourceType getResourceType() {
        return ResourceType.Item;
    }
}
