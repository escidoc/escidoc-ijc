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
package de.escidoc.core.resources.cmm;

import org.joda.time.DateTime;

import de.escidoc.core.resources.ResourceRef;
import de.escidoc.core.resources.common.properties.Version;
import de.escidoc.core.resources.interfaces.common.LatestReleaseInterface;
import de.escidoc.core.resources.interfaces.common.LatestVersionInterface;
import de.escidoc.core.resources.interfaces.common.VersionInterface;

/**
 * Properties of the eSciDoc resources Content Model.
 * 
 * @author SWA
 * 
 */
public class ContentModelProperties {

    private String name = null;

    private String description = null;

    private DateTime creationDate = null;

    private ResourceRef createdBy = null;

    private String publicStatus = null;

    private String publicStatusComment = null;

    private String lockStatus = null;

    private DateTime lockDate = null;

    private ResourceRef lockOwner = null;

    private String pid = null;

    private Version version = null;

    private Version latestVersion = null;

    private Version latestRelease = null;


    /**
     * 
     */
    public ContentModelProperties() {

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
    public ResourceRef getCreatedBy() {
        return this.createdBy;
    }

    /**
     * @param createdBy
     *            the createdBy to set
     */
    public void setCreatedBy(final ResourceRef createdBy) {
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
     * @param lockDate
     *            the lockDate to set
     */
    public void setLockDate(final String lockDate) {
        this.lockDate = new DateTime(lockDate);
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
     * @return the lockStatus
     */
    public String getLockStatus() {
        return this.lockStatus;
    }

    /**
     * @return the lockOwner
     */
    public ResourceRef getLockOwner() {
        return this.lockOwner;
    }

    /**
     * @return the pid
     */
    public String getPid() {
        return this.pid;
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
    public void setLockOwner(final ResourceRef lockOwner) {
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
     * @return the description
     */
    public String getDescription() {
        return this.description;
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
        return this.name;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(final String name) {
        this.name = name;
    }

}
