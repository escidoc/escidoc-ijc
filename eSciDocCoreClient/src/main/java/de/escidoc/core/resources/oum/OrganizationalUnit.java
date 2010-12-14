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
package de.escidoc.core.resources.oum;

import de.escidoc.core.annotations.JiBX;
import de.escidoc.core.resources.ResourceType;
import de.escidoc.core.resources.XLinkAutonomous;
import de.escidoc.core.resources.common.MetadataRecord;
import de.escidoc.core.resources.common.MetadataRecords;
import de.escidoc.core.resources.om.GenericResource;

/**
 * OrganizationalUnit of eSciDoc.
 * 
 * @author SWA
 * 
 */
@JiBX
public class OrganizationalUnit extends GenericResource
    implements XLinkAutonomous {

    private OrganizationalUnitProperties properties;

    private Parents parents;

    private MetadataRecords mdRecords;

    private Predecessors predecessors;

    /**
     * 
     */
    public OrganizationalUnit() {
    }

    /**
     * Return the Resource properties.
     * 
     * @return properties
     */
    public OrganizationalUnitProperties getProperties() {

        if (this.properties == null) {
            this.properties = new OrganizationalUnitProperties();
        }
        return this.properties;
    }

    /**
     * See Interface for functional description.
     * 
     * @return
     * @see de.escidoc.core.resources.interfaces.organizationalUnit.OrganizationalUnitInterface#getParents()
     */
    public Parents getParents() {
        if (parents == null) {
            parents = new Parents();
        }
        return parents;
    }

    /**
     * See Interface for functional description.
     * 
     * @return
     * @see de.escidoc.core.resources.interfaces.organizationalUnit.OrganizationalUnitInterface#getMetadataRecords()
     */
    public MetadataRecords getMetadataRecords() {
        if (mdRecords == null) {
            mdRecords = new MetadataRecords();
        }
        return mdRecords;
    }

    /**
     * See Interface for functional description.
     * 
     * @return
     * @see de.escidoc.core.resources.interfaces.organizationalUnit.OrganizationalUnitInterface#getPredecessors()
     */
    public Predecessors getPredecessors() {
        if (predecessors == null) {
            predecessors = new Predecessors();
        }
        return predecessors;
    }

    /**
     * See Interface for functional description.
     * 
     * @param parents
     * @see de.escidoc.core.resources.interfaces.organizationalUnit.OrganizationalUnitInterface#setParents(de.escidoc.core.resources.om.organizationalUnit.Parents)
     */
    public void setParents(final Parents parents) {
        this.parents = parents;
    }

    /**
     * See Interface for functional description.
     * 
     * @param mdRecords
     * @see de.escidoc.core.resources.interfaces.organizationalUnit.OrganizationalUnitInterface#setMetadataRecords(de.escidoc.core.resources.common.MetadataRecords)
     */
    public void setMetadataRecords(final MetadataRecords metadataRecords) {
        this.mdRecords = metadataRecords;
    }

    /**
     * Set Resource properties.
     * 
     * @param properties
     *            The new ContextProperties.
     */
    public void setProperties(final OrganizationalUnitProperties properties) {
        this.properties = properties;
    }

    /**
     * See Interface for functional description.
     * 
     * @param predecessors
     * @see de.escidoc.core.resources.interfaces.organizationalUnit.OrganizationalUnitInterface#setPredecessors(de.escidoc.core.resources.om.organizationalUnit.Predecessors)
     */
    public void setPredecessors(final Predecessors predecessors) {
        this.predecessors = predecessors;
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
            genXLinkHref(properties.getModifiedBy(), ResourceType.UserAccount,
                null);
        }
        if (parents != null) {
            if (parents.getXLinkHref() == null && getXLinkHref() != null) {
                parents.setXLinkHref(getXLinkHref() + "/parents");
            }

            for (Parent parent : parents) {
                genXLinkHref(parent, ResourceType.OrganizationalUnit, null);
            }

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

        if (predecessors != null) {

            if (predecessors.getXLinkHref() == null && getXLinkHref() != null) {
                predecessors.setXLinkHref(getXLinkHref() + "/predecessors");
            }

            for (Predecessor predecessor : predecessors.getPredecessorRef()) {
                if (predecessor.getXLinkHref() == null) {
                    genXLinkHref(predecessor, ResourceType.OrganizationalUnit,
                        null);
                }
            }
        }
    }

    @Override
    public ResourceType getResourceType() {
        return ResourceType.OrganizationalUnit;
    }
}