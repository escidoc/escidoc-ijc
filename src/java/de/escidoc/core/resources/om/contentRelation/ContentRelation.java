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

import de.escidoc.core.resources.common.MetadataRecords;
import de.escidoc.core.resources.om.GenericResource;

/**
 * Content Relation.
 * 
 * @author SWA
 * 
 */
public class ContentRelation extends GenericResource {

    private MetadataRecords mdRecords = null;

    private ContentRelationProperties properties =
        new ContentRelationProperties();

    private URI type;

    private String subject;

    private String object;

    private String subjectVersion;

    private String objectVersion;

    /**
     * 
     */
    public ContentRelation() {
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

    public void setType(final URI type) {
        this.type = type;
    }

    public URI getType() {
        return type;
    }

    public void setSubject(final String subject) {
        this.subject = subject;
    }

    public String getSubject() {
        return subject;
    }

    public void setObject(final String object) {
        this.object = object;
    }

    public String getObject() {
        return object;
    }

    public void setSubjectVersion(final String subjectVersion) {
        this.subjectVersion = subjectVersion;
    }

    public String getSubjectVersion() {
        return subjectVersion;
    }

    public void setObjectVersion(final String objectVersion) {
        this.objectVersion = objectVersion;
    }

    public String getObjectVersion() {
        return objectVersion;
    }

}
