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
package de.escidoc.core.resources.common.properties;

import org.joda.time.DateTime;

import de.escidoc.core.resources.XLinkResource;
import de.escidoc.core.resources.common.reference.ContentModelRef;
import de.escidoc.core.resources.common.reference.ContextRef;
import de.escidoc.core.resources.common.reference.UserAccountRef;
import de.escidoc.core.resources.interfaces.common.LatestRelease;
import de.escidoc.core.resources.interfaces.common.LatestVersion;
import de.escidoc.core.resources.interfaces.common.PropertiesInterface;
import de.escidoc.core.resources.interfaces.common.Version;

/**
 * Properties of the eSciDoc resources. (I'm afraid this structure is close to
 * versionated resource properties.)
 * 
 * @author SWA
 * 
 */
public class Properties extends XLinkResource implements PropertiesInterface {

    protected DateTime creationDate = null;

    protected String description;

    protected UserAccountRef createdBy;

    private UserAccountRef modifiedBy;

    private String publicStatus;

    private String publicStatusComment;

    private ContextRef context = null;

    private ContentModelRef contentModel = null;

    private String lockStatus = null;

    private String lockDate;

    private UserAccountRef lockOwner = null;

    private String pid = null;

    protected String name = null;

    private VersionImpl version;

    private VersionImpl latestVersion;

    private VersionImpl latestRelease;

    private ContentModelSpecific contentModelSpecific =
        new ContentModelSpecific();

    // Component properties
    private final String validStatus = null;

    private final String visibility = null;

    private final String contentCategory = null;

    private final String fileName = null;

    private final String mimeType = null;

    /**
     * 
     */
    public Properties() {

    }

    /**
     * Get creation date.
     * 
     * @return creation date
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
     * @return the modifedBy
     */
    public UserAccountRef getModifiedBy() {
        return this.modifiedBy;
    }

    /**
     * @param modifiedBy
     *            the createdBy to set
     */
    public void setModifiedBy(final UserAccountRef modifiedBy) {
        this.modifiedBy = modifiedBy;
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
    public String getLockDate() {
        return lockDate;
    }

    /**
     * @param lockDate
     *            the lockDate to set
     */
    public void setLockDate(final String lockDate) {
        this.lockDate = lockDate;
    }

    /**
     * @return the version
     */
    public Version getVersion() {
        return version;
    }

    /**
     * @param version
     *            the version to set
     */
    public void setVersion(final Version version) {
        this.version = (VersionImpl) version;
    }

    /**
     * @return the latestVersion
     */
    public LatestVersion getLatestVersion() {
        return latestVersion;
    }

    /**
     * @param latestVersion
     *            the latestVersion to set
     */
    public void setLatestVersion(final LatestVersion latestVersion) {
        this.latestVersion = (VersionImpl) latestVersion;
    }

    /**
     * @return the latestRelease
     */
    public LatestRelease getLatestRelease() {
        return latestRelease;
    }

    /**
     * @param latestRelease
     *            the latestRelease to set
     */
    public void setLatestRelease(final LatestRelease latestRelease) {
        this.latestRelease = (VersionImpl) latestRelease;
    }

    /**
     * @return the context
     */
    public ContextRef getContext() {
        return context;
    }

    /**
     * @return the lockStatus
     */
    public String getLockStatus() {
        return lockStatus;
    }

    /**
     * @return the lockOwner
     */
    public UserAccountRef getLockOwner() {
        return lockOwner;
    }

    /**
     * @return the pid
     */
    public String getPid() {
        return pid;
    }

    /**
     * @return the contentModelSpecific
     */
    public ContentModelSpecific getContentModelSpecific() {
        return contentModelSpecific;
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
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description
     *            the description to set
     */
    public void setDescription(final String description) {
        this.description = description;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(final String name) {
        this.name = name;
    }

}
