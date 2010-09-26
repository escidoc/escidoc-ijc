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

import org.joda.time.DateTime;

import de.escidoc.core.resources.XLinkResource;
import de.escidoc.core.resources.common.properties.ContentModelSpecific;
import de.escidoc.core.resources.common.properties.Version;
import de.escidoc.core.resources.common.reference.ContentModelRef;
import de.escidoc.core.resources.common.reference.ContextRef;
import de.escidoc.core.resources.common.reference.ItemRef;
import de.escidoc.core.resources.common.reference.UserAccountRef;
import de.escidoc.core.resources.interfaces.common.LatestReleaseInterface;
import de.escidoc.core.resources.interfaces.common.LatestVersionInterface;
import de.escidoc.core.resources.interfaces.common.VersionInterface;

/**
 * Properties of the eSciDoc resources. (I'm afraid this structure is close to
 * versioned resource properties.)
 * 
 * @author SWA
 * 
 */
public class ItemProperties extends XLinkResource {

    private DateTime creationDate = null;

    /**
     * @deprecated Not yet supported.
     */
    private String description = null;

    private UserAccountRef createdBy = null;

    private ItemRef origin = null;

    private String publicStatus = null;

    private String publicStatusComment = null;

    private ContextRef context = null;

    private ContentModelRef contentModel = null;

    private String lockStatus = null;

    private DateTime lockDate = null;

    private UserAccountRef lockOwner = null;

    private String pid = null;

    /**
     * @deprecated Not yet supported.
     */
    private String name = null;

    private Version version = null;

    private Version latestVersion = null;

    private Version latestRelease = null;

    private ContentModelSpecific contentModelSpecific = null;

    // Component properties
    private final String validStatus = null;

    private final String visibility = null;

    private final String contentCategory = null;

    private final String fileName = null;

    private final String mimeType = null;

    /**
     * 
     */
    public ItemProperties() {

    }

    /**
     * @return the creationDate
     */
    public DateTime getCreationDate() {
        return this.creationDate;
    }

    /**
     * @param creationDate
     *            the creationDate to set
     */
    public void setCreationDate(final DateTime creationDate) {
        this.creationDate = creationDate;
    }

    /**
     * @return the createdBy
     */
    public UserAccountRef getCreatedBy() {
        return this.createdBy;
    }

    /**
     * @param createdBy
     *            the createdBy to set
     */
    public void setCreatedBy(final UserAccountRef createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * @return the publicStatus
     */
    public String getPublicStatus() {
        return publicStatus;
    }

    /**
     * @param publicStatus
     *            the publicStatus to set
     */
    public void setPublicStatus(final String publicStatus) {
        this.publicStatus = publicStatus;
    }

    /**
     * @return the publicStatusComment
     */
    public String getPublicStatusComment() {
        return publicStatusComment;
    }

    /**
     * @param publicStatusComment
     *            the publicStatusComment to set
     */
    public void setPublicStatusComment(final String publicStatusComment) {
        this.publicStatusComment = publicStatusComment;
    }

    /**
     * @return the contentModel
     */
    public ContentModelRef getContentModel() {
        return contentModel;
    }

    /**
     * @param contentModel
     *            the contentModel to set
     */
    public void setContentModel(final ContentModelRef contentModel) {
        this.contentModel = contentModel;
    }

    /**
     * @return the lockDate
     */
    public DateTime getLockDate() {
        return this.lockDate;
    }

    /**
     * @param lockDate
     *            the lockDate to set
     */
    public void setLockDate(final DateTime lockDate) {
        this.lockDate = lockDate;
    }

    /**
     * @return the version
     */
    public VersionInterface getVersion() {
        return version;
    }

    /**
     * @param version
     *            the version to set
     */
    public void setVersion(final VersionInterface version) {
        this.version = (Version) version;
    }

    /**
     * @return the latestVersion
     */
    public LatestVersionInterface getLatestVersion() {
        return latestVersion;
    }

    /**
     * @param latestVersion
     *            the latestVersion to set
     */
    public void setLatestVersion(final LatestVersionInterface latestVersion) {
        this.latestVersion = (Version) latestVersion;
    }

    /**
     * @return the latestRelease
     */
    public LatestReleaseInterface getLatestRelease() {
        return latestRelease;
    }

    /**
     * @param latestRelease
     *            the latestRelease to set
     */
    public void setLatestRelease(final LatestReleaseInterface latestRelease) {
        this.latestRelease = (Version) latestRelease;
    }

    /**
     * @return the context
     */
    public ContextRef getContext() {
        return this.context;
    }

    /**
     * @return the lockStatus
     */
    public String getLockStatus() {
        return this.lockStatus;
    }

    /**
     * @return the lockOwner
     */
    public UserAccountRef getLockOwner() {
        return this.lockOwner;
    }

    /**
     * @return the pid
     */
    public String getPid() {
        return this.pid;
    }

    /**
     * @return the contentModelSpecific
     */
    public ContentModelSpecific getContentModelSpecific() {
        return this.contentModelSpecific;
    }

    /**
     * @param context
     *            the context to set
     */
    public void setContext(final ContextRef context) {
        this.context = context;
    }

    /**
     * @param lockStatus
     *            the lockStatus to set
     */
    public void setLockStatus(final String lockStatus) {
        this.lockStatus = lockStatus;
    }

    /**
     * @param lockOwner
     *            the lockOwner to set
     */
    public void setLockOwner(final UserAccountRef lockOwner) {
        this.lockOwner = lockOwner;
    }

    /**
     * @param pid
     *            the pid to set
     */
    public void setPid(final String pid) {
        this.pid = pid;
    }

    /**
     * @param contentModelSpecific
     *            the contentModelSpecific to set
     */
    public void setContentModelSpecific(
        final ContentModelSpecific contentModelSpecific) {
        this.contentModelSpecific = contentModelSpecific;
    }

    /**
     * @return the description
     * @deprecated Not yet supported.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * @param description
     *            the description to set
     * @deprecated Not yet supported.
     */
    public void setDescription(final String description) {
        this.description = description;
    }

    /**
     * @return the name
     * @deprecated Not yet supported.
     */
    public String getName() {
        return this.name;
    }

    /**
     * @param name
     *            the name to set
     * @deprecated Not yet supported.
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * @return the validStatus
     */
    public String getValidStatus() {
        return validStatus;
    }

    /**
     * @return the visibility
     */
    public String getVisibility() {
        return visibility;
    }

    /**
     * @return the contentCategory
     */
    public String getContentCategory() {
        return contentCategory;
    }

    /**
     * @return the mimeType
     */
    public String getMimeType() {
        return mimeType;
    }

    /**
     * @return the fileName
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * Set the reference for to origin Item. If reference is set the Item is a
     * Surrogate Item.
     * 
     * @param origin
     */
    public void setOrigin(final ItemRef origin) {
        this.origin = origin;
    }

    /**
     * Get the reference of the origin Item.
     * 
     * @return
     */
    public ItemRef getOrigin() {
        return origin;
    }
}