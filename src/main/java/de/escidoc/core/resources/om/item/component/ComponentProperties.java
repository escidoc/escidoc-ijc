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

import de.escidoc.core.resources.common.properties.CommonProperties;
import de.escidoc.core.resources.common.properties.interfaces.DescriptionProperties;
import de.escidoc.core.resources.common.properties.interfaces.PidProperties;

/**
 * Properties for eSciDoc Component (non-versioned resource).
 * 
 * @author SWA
 * 
 */
public class ComponentProperties extends CommonProperties
    implements DescriptionProperties, PidProperties {

    private String description;

    private String pid;

    private String validStatus;

    private String visibility;

    private String contentCategory;

    private String fileName;

    private String mimeType;

    private String checksum;

    private ChecksumAlgorithm checksumAlgorithm;

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.resources.common.properties.interfaces.DescriptionProperties
     * #setDescription(java.lang.String)
     */
    @Override
    public void setDescription(final String description) {
        this.description = description;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.resources.common.properties.interfaces.DescriptionProperties
     * #getDescription()
     */
    @Override
    public String getDescription() {
        return description;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.resources.common.properties.interfaces.PidProperties#
     * setPid(java.lang.String)
     */
    @Override
    public void setPid(final String pid) {
        this.pid = pid;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.resources.common.properties.interfaces.PidProperties#
     * getPid()
     */
    @Override
    public String getPid() {
        return pid;
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
    public void setChecksumAlgorithm(final ChecksumAlgorithm checksumAlgorithm) {
        this.checksumAlgorithm = checksumAlgorithm;
    }

    /**
     * Get checksum algorithm.
     * 
     * @return checksum algorithm
     */
    public ChecksumAlgorithm getChecksumAlgorithm() {
        return checksumAlgorithm;
    }
}