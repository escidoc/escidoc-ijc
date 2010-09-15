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
package de.escidoc.core.resources.om.contentRelation;

import java.net.URI;

import de.escidoc.core.resources.ResourceRef;
import de.escidoc.core.resources.XLinkAutonomous;
import de.escidoc.core.resources.common.MetadataRecord;
import de.escidoc.core.resources.common.MetadataRecords;
import de.escidoc.core.resources.om.GenericResource;

/**
 * Content Relation.
 * 
 * @author SWA
 * 
 */
public class ContentRelation extends GenericResource implements XLinkAutonomous {

    private MetadataRecords mdRecords;

    private ContentRelationProperties properties =
        new ContentRelationProperties();

    private URI type;

    private String subjectVersion;

    private String objectVersion;

    private ResourceRef subject;

    private ResourceRef object;

    /**
     * 
     */
    public ContentRelation() {
        setResourceType(RESOURCE_TYPE.ContentRelation);
    }

    /**
     * Return the Resource properties.
     * 
     * @return properties
     */
    public ContentRelationProperties getProperties() {
        return this.properties;
    }

    /**
     * Set Resource properties.
     * 
     * @param properties
     *            The new Properties.
     */
    public void setProperties(final ContentRelationProperties properties) {
        this.properties = properties;
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
     * Set type of relation.
     * 
     * @param type
     *            type of relation
     */
    public void setType(final URI type) {
        this.type = type;
    }

    /**
     * Get type of relation
     * 
     * @return type of relation
     */
    public URI getType() {
        return type;
    }

    /**
     * Set version number of subject
     * 
     * @param subjectVersion
     *            version number of subject (leave it empty if the whole
     *            resource is to reference)
     */
    public void setSubjectVersion(final String subjectVersion) {
        this.subjectVersion = subjectVersion;
    }

    /**
     * Get version number of subject
     * 
     * @return version number of subject (is <code>null</code> if the whole
     *         object is to reference)
     */
    public String getSubjectVersion() {
        return subjectVersion;
    }

    /**
     * Set version number of object
     * 
     * @param objectVersion
     *            version number of object (leave it empty if the whole object
     *            is to reference)
     */
    public void setObjectVersion(final String objectVersion) {
        this.objectVersion = objectVersion;
    }

    /**
     * Get version number of object
     * 
     * @return version number of object (is <code>null</code> if the whole
     *         resource is to reference)
     */
    public String getObjectVersion() {
        return objectVersion;
    }

    /**
     * @return the subjectResourceRef
     */
    public ResourceRef getSubject() {
        return subject;
    }

    /**
     * @param subjectResourceRef
     *            the subjectResourceRef to set
     */
    public void setSubject(ResourceRef subjectResourceRef) {
        this.subject = subjectResourceRef;
    }

    /**
     * @return the objectResourceRef
     */
    public ResourceRef getObject() {
        return object;
    }

    /**
     * @param objectResourceRef
     *            the objectResourceRef to set
     */
    public void setObject(ResourceRef objectResourceRef) {
        this.object = objectResourceRef;
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
            genXLinkHref(properties.getModifiedBy(), RESOURCE_TYPE.UserAccount,
                null);
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
        if (subject != null && subject.getResourceType() != null
            && subject.getResourceType().isRootResource()) {
            genXLinkHref(subject, subject.getResourceType(), null);
        }
        if (object != null && object.getResourceType() != null
            && object.getResourceType().isRootResource()) {
            genXLinkHref(object, object.getResourceType(), null);
        }
    }
}
