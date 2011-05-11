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

import de.escidoc.core.common.DateTimeUtility;
import de.escidoc.core.resources.ResourceType;
import de.escidoc.core.resources.common.properties.CommonProperties;
import de.escidoc.core.resources.common.properties.LockStatus;
import de.escidoc.core.resources.common.properties.PublicStatus;
import de.escidoc.core.resources.common.properties.VersionImpl;
import de.escidoc.core.resources.common.properties.interfaces.DescriptionProperties;
import de.escidoc.core.resources.common.properties.interfaces.LockingProperties;
import de.escidoc.core.resources.common.properties.interfaces.NameProperties;
import de.escidoc.core.resources.common.properties.interfaces.PidProperties;
import de.escidoc.core.resources.common.properties.interfaces.PublicStatusProperties;
import de.escidoc.core.resources.common.properties.interfaces.VersionProperties;
import de.escidoc.core.resources.common.reference.UserAccountRef;
import de.escidoc.core.resources.interfaces.common.LatestRelease;
import de.escidoc.core.resources.interfaces.common.LatestVersion;
import de.escidoc.core.resources.interfaces.common.Version;

/**
 * Properties of the eSciDoc resources Content Model.
 * 
 * @author SWA
 * 
 */
public class ContentModelProperties extends CommonProperties
    implements PublicStatusProperties, NameProperties, DescriptionProperties,
    LockingProperties, PidProperties, VersionProperties {

    private String name;

    private String description;

    private PublicStatus publicStatus;

    private String publicStatusComment;

    private LockStatus lockStatus;

    private DateTime lockDate;

    private UserAccountRef lockOwner;

    private String pid;

    private VersionImpl version;

    private VersionImpl latestVersion;

    private VersionImpl latestRelease;

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.resources.common.properties.interfaces.PublicStatusProperties
     * #getPublicStatus()
     */
    @Override
    public PublicStatus getPublicStatus() {
        return publicStatus;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.resources.common.properties.interfaces.PublicStatusProperties
     * #setPublicStatus(java.lang.String)
     */
    @Override
    public void setPublicStatus(final PublicStatus publicStatus) {
        this.publicStatus = publicStatus;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.resources.common.properties.interfaces.PublicStatusProperties
     * #getPublicStatusComment()
     */
    @Override
    public String getPublicStatusComment() {
        return publicStatusComment;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.resources.common.properties.interfaces.PublicStatusProperties
     * #setPublicStatusComment(java.lang.String)
     */
    @Override
    public void setPublicStatusComment(final String publicStatusComment) {
        this.publicStatusComment = publicStatusComment;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.resources.common.properties.interfaces.LockingProperties
     * #getLockDate()
     */
    @Override
    public DateTime getLockDate() {
        return this.lockDate;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.resources.common.properties.interfaces.LockingProperties
     * #setLockDate(org.joda.time.DateTime)
     */
    @Override
    public void setLockDate(final DateTime lockDate) {
        this.lockDate = DateTimeUtility.normalize(lockDate);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.resources.common.properties.interfaces.VersionProperties
     * #getVersion()
     */
    @Override
    public Version getVersion() {
        return version;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.resources.common.properties.interfaces.VersionProperties
     * #setVersion(de.escidoc.core.resources.interfaces.common.Version)
     */
    @Override
    public void setVersion(final Version version) {
        this.version = (VersionImpl) version;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.resources.common.properties.interfaces.VersionProperties
     * #getLatestVersion()
     */
    @Override
    public LatestVersion getLatestVersion() {
        return latestVersion;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.resources.common.properties.interfaces.VersionProperties
     * #setLatestVersion
     * (de.escidoc.core.resources.interfaces.common.LatestVersion)
     */
    @Override
    public void setLatestVersion(final LatestVersion latestVersion) {
        this.latestVersion = (VersionImpl) latestVersion;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.resources.common.properties.interfaces.VersionProperties
     * #getLatestRelease()
     */
    @Override
    public LatestRelease getLatestRelease() {
        return latestRelease;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.resources.common.properties.interfaces.VersionProperties
     * #setLatestRelease
     * (de.escidoc.core.resources.interfaces.common.LatestRelease)
     */
    @Override
    public void setLatestRelease(final LatestRelease latestRelease) {
        this.latestRelease = (VersionImpl) latestRelease;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.resources.common.properties.interfaces.LockingProperties
     * #getLockStatus()
     */
    @Override
    public LockStatus getLockStatus() {
        return this.lockStatus;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.resources.common.properties.interfaces.LockingProperties
     * #getLockOwner()
     */
    @Override
    public UserAccountRef getLockOwner() {
        return this.lockOwner;
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
        return this.pid;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.resources.common.properties.interfaces.LockingProperties
     * #setLockStatus(java.lang.String)
     */
    @Override
    public void setLockStatus(final LockStatus lockStatus) {
        this.lockStatus = lockStatus;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.resources.common.properties.interfaces.LockingProperties
     * #setLockOwner(de.escidoc.core.resources.common.reference.UserAccountRef)
     */
    @Override
    public void setLockOwner(final UserAccountRef lockOwner) {
        this.lockOwner = lockOwner;
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
     * de.escidoc.core.resources.common.properties.interfaces.DescriptionProperties
     * #getDescription()
     */
    @Override
    public String getDescription() {
        return this.description;
    }

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
     * de.escidoc.core.resources.common.properties.interfaces.NameProperties
     * #getName()
     */
    @Override
    public String getName() {
        return this.name;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.resources.common.properties.interfaces.NameProperties
     * #setName(java.lang.String)
     */
    @Override
    public void setName(final String name) {
        this.name = name;
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.resources.common.properties.CommonProperties#
     * generateXLinkHref(java.lang.String)
     */
    @Override
    public void generateXLinkHref(final String parentPath) {
        super.generateXLinkHref(parentPath);
        if (version != null) {
            version.generateXLinkHref(ResourceType.CONTENT_MODEL.getPath());
        }
        if (latestVersion != null) {
            latestVersion.generateXLinkHref(ResourceType.CONTENT_MODEL
                .getPath());
        }
        if (latestRelease != null) {
            latestRelease.generateXLinkHref(ResourceType.CONTENT_MODEL
                .getPath());
        }
    }
}