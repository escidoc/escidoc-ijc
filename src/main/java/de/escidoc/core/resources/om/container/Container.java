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
package de.escidoc.core.resources.om.container;

import de.escidoc.core.resources.ResourceType;
import de.escidoc.core.resources.XLinkAutonomous;
import de.escidoc.core.resources.common.MetadataRecord;
import de.escidoc.core.resources.common.MetadataRecords;
import de.escidoc.core.resources.common.Relation;
import de.escidoc.core.resources.common.Relations;
import de.escidoc.core.resources.common.properties.Version;
import de.escidoc.core.resources.common.reference.ContainerRef;
import de.escidoc.core.resources.common.reference.Referenceable;
import de.escidoc.core.resources.common.structmap.MemberRef;
import de.escidoc.core.resources.common.structmap.StructMap;
import de.escidoc.core.resources.om.GenericVersionableResource;

/**
 * Container.
 * 
 * @author SWA
 * 
 */
public class Container extends GenericVersionableResource
    implements XLinkAutonomous, Referenceable<ContainerRef> {

    private MetadataRecords mdRecords = null;

    private ContainerProperties properties = new ContainerProperties();

    private StructMap structMap = null;

    private Relations relations = null;

    /**
     * 
     */
    public Container() {
        setResourceType(ResourceType.Container);
    }

    /**
     * Return the Resource properties.
     * 
     * @return properties
     */
    public ContainerProperties getProperties() {
        return this.properties;
    }

    /**
     * Set Resource properties.
     * 
     * @param properties
     *            The new ContainerProperties.
     */
    public void setProperties(final ContainerProperties properties) {
        this.properties = properties;
    }

    /**
     * Get the Relations.
     * 
     * @return Relations
     */
    public Relations getRelations() {

        return this.relations;
    }

    /**
     * Set the relations.
     * 
     * @param relations
     *            The new relations.
     */
    public void setRelations(final Relations relations) {

        this.relations = relations;

    }

    /**
     * Get the Container metadata records.
     * 
     * @return MetadataRecords
     */
    public MetadataRecords getMetadataRecords() {

        return this.mdRecords;
    }

    /**
     * Set the Container metadata records.
     * 
     * @param metadataRecords
     *            The new metadataRecords of Container.
     */
    public void setMetadataRecords(final MetadataRecords metadataRecords) {

        this.mdRecords = metadataRecords;

    }

    /**
     * Get the StuctMap of the Container.
     * 
     * @return StructMap of Container.
     */
    public StructMap getStructMap() {
        return this.structMap;
    }

    /**
     * Set the StructMap of the Container. An Existing StructMap is replaced.
     * 
     * @param structMap
     *            StructMap of Container
     */
    public void setStructMap(final StructMap structMap) {
        this.structMap = structMap;
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
            genXLinkHref(properties.getCreatedBy(), ResourceType.UserAccount,
                null);
            genXLinkHref(properties.getContext(), ResourceType.Context, null);
            genXLinkHref(properties.getContentModel(),
                ResourceType.ContentModel, null);
            genVersionHref((Version) properties.getVersion());
            genVersionHref((Version) properties.getLatestVersion());
            genVersionHref((Version) properties.getLatestRelease());
        }
        if (mdRecords != null && getXLinkHref() != null) {
            if (mdRecords.getXLinkHref() == null) {
                mdRecords.setXLinkHref(getXLinkHref() + "/md-records");
            }

            for (MetadataRecord record : mdRecords) {
                if (record.getXLinkHref() == null) {
                    record.setXLinkHref(getXLinkHref()
                        + "/md-records/md-record/" + record.getName());
                }
            }
        }
        if (relations != null) {
            if (relations.getXLinkHref() == null && getXLinkHref() != null) {
                relations.setXLinkHref(getXLinkHref() + "/relations");
            }
            for (Relation relation : relations) {
                if (relation.getXLinkHref() == null
                    && relation.getResourceType() != null
                    && relation.getResourceType().isRootResource()) {
                    genXLinkHref(relation, relation.getResourceType(), null);
                }
            }
        }
        if (structMap != null) {
            if (structMap.getXLinkHref() == null && getXLinkHref() != null) {
                structMap.setXLinkHref(getXLinkHref() + "/struct-map");
            }

            for (MemberRef memberRef : structMap) {
                if (memberRef.getXLinkHref() == null
                    && memberRef.getResourceType() != null
                    && memberRef.getResourceType().isRootResource()) {
                    genXLinkHref(memberRef, memberRef.getResourceType(), null);
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
            version.setXLinkHref(RESOURCE_URL_MAP.get(ResourceType.Container)
                + "/" + getObjid() + ":" + version.getNumber());
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
    public ContainerRef getReference() {
        return new ContainerRef(getObjid());
    }
}
