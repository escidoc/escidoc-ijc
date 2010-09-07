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

import org.joda.time.DateTime;

import de.escidoc.core.resources.ResourceRef;
import de.escidoc.core.resources.XLinkResource;
import de.escidoc.core.resources.interfaces.item.ComponentPropertiesInterface;

/**
 * Properties for eSciDoc Component (non-versioned resource).
 * 
 * @author SWA
 * 
 */
public class ComponentProperties extends XLinkResource 
	implements ComponentPropertiesInterface {

	private DateTime creationDate;

    private DateTime lastModificationDate;

    private ResourceRef createdBy;

    private ResourceRef modifiedBy;

    private String name;

    private String contentCategory;

    private String fileName;

    private String mimeType;

    private String checksum;

    private String checksumAlgorithm;

    private String visibility;

    private String validStatus;

    private String description;

    private String pid;

    /**
     * @return The creation date.
     */
    public DateTime getCreationDate() {
        return this.creationDate;
    }

    /**
     * Set the creation date.
     * 
     * @param creationDate
     *            The new creation date.
     */
    public void setCreationDate(final DateTime creationDate) {
        this.creationDate = creationDate;
    }

    /**
     * @return The creator.
     */
    public ResourceRef getCreatedBy() {
        return this.createdBy;
    }

    /**
     * Set the creator.
     * 
     * @param createdBy
     *            The link to the new creator.
     */
    public void setCreatedBy(final ResourceRef createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * @return The creation date.
     */
    public DateTime getLastModificationDate() {
        return this.lastModificationDate;
    }

    /**
     * Set the last modification date.
     * 
     * @param lastModificationDate
     *            The new last modification date.
     */
    public void setLastModificationDate(final DateTime lastModificationDate) {
        this.lastModificationDate = lastModificationDate;
    }

    /**
     * @return The creator.
     */
    public ResourceRef getModifiedBy() {
        return this.modifiedBy;
    }

    /**
     * Set the creator.
     * 
     * @param modifiedBy
     *            The link to the new modifier.
     */
    public void setModifiedBy(final ResourceRef modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    /**
     * See Interface for functional description.
     * 
     * @return Name of Component
     */
    public String getName() {
        return this.name;
    }

    /**
     * See Interface for functional description.
     * 
     * @param name
     *            Name of Component
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * Get content-category.
     * 
     * @return content-category
     */
    public String getContentCategory() {
        return this.contentCategory;
    }

    /**
     * Set content-category.
     * 
     * @param contentCategory
     *            content-category
     */
    public void setContentCategory(final String contentCategory) {
        this.contentCategory = contentCategory;
    }

    /**
     * Get file name.
     * 
     * @return file name
     */
    public String getFileName() {
        return this.fileName;
    }

    /**
     * Set file name.
     * 
     * @param fileName
     *            file name
     */
    public void setFileName(final String fileName) {
        this.fileName = fileName;
    }

    /**
     * Get MIME type.
     * 
     * @return MIME type
     */
    public String getMimeType() {
        return this.mimeType;
    }

    /**
     * Set MIME type.
     * 
     * @param mimeType
     *            MIME type
     */
    public void setMimeType(final String mimeType) {
        this.mimeType = mimeType;
    }

    /**
     * Set valid status.
     * 
     * @param validStatus
     */
    public void setValidStatus(final String validStatus) {
        this.validStatus = validStatus;
    }

    /**
     * Get valid status.
     * 
     * @return valid status
     */
    public String getValidStatus() {
        return this.validStatus;
    }

    /**
     * Get visibility.
     * 
     * @return visibility
     */
    public String getVisibility() {
        return this.visibility;
    }

    /**
     * Set visibility.
     * 
     * @param visibility
     *            visibility
     */
    public void setVisibility(final String visibility) {
        this.visibility = visibility;

    }

    /**
     * Set checksum.
     * 
     * @param checksum
     *            checksum
     */
    public void setChecksum(final String checksum) {
        this.checksum = checksum;
    }

    /**
     * Get checksum.
     * 
     * @return checksum
     */
    public String getChecksum() {
        return checksum;
    }

    /**
     * Set checksum algorithm.
     * 
     * @param checksumAlgorithm
     *            checksum algorithm (MD5, SHA-1, ..)
     */
    public void setChecksumAlgorithm(final String checksumAlgorithm) {
        this.checksumAlgorithm = checksumAlgorithm;
    }

    /**
     * Get checksum algorithm.
     * 
     * @return checksum algorithm
     */
    public String getChecksumAlgorithm() {
        return checksumAlgorithm;
    }

    /**
     * Set description.
     * 
     * @param description
     *            description
     */
    public void setDescription(final String description) {
        this.description = description;
    }

    /**
     * Get description.
     * 
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set PID of component (content PID).
     * 
     * @param pid
     *            The PID for the content.
     */
    public void setPid(final String pid) {
        this.pid = pid;
    }

    /**
     * Get PID of component (content PID).
     * 
     * @return PID of the content
     */
    public String getPid() {
        return pid;
    }

}
