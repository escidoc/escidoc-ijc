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
package de.escidoc.core.resources.interfaces.common;

import org.joda.time.DateTime;

import de.escidoc.core.resources.ResourceRef;

/**
 * Interface for (Unversioned) Resources.
 * 
 * @author SWA
 * 
 */
public interface PropertiesInterface {

    /**
     * @return The creation date.
     */
    DateTime getCreationDate();

    /**
     * Set the creation date.
     * 
     * @param creationDate
     *            The new creation date.
     */
    void setCreationDate(final DateTime creationDate);

    /**
     * @return The creator.
     */
    ResourceRef getCreatedBy();

    /**
     * Set the creator.
     * 
     * @param createdBy
     *            The link to the new creator.
     */
    void setCreatedBy(final ResourceRef createdBy);

    /**
     * @return The public status.
     */
    String getPublicStatus();

    /**
     * Set the new public status.
     * 
     * @param publicStatus
     *            The new public status.
     */
    void setPublicStatus(final String publicStatus);

    /**
     * @return The public status.
     */
    String getPublicStatusComment();

    /**
     * Set the new public status.
     * 
     * @param publicStatusComment
     *            The new public status comment.
     */
    void setPublicStatusComment(final String publicStatusComment);

    // /**
    // * @return Get the context.
    // */
    // ResourceRef getContext();
    //
    // /**
    // * Set the new context.
    // *
    // * @param context
    // * the link to the new context.
    // */
    // void setContext(ResourceRef context);
    //
    // /**
    // * @return Get the content model.
    // */
    // ResourceRef getContentModel();
    //
    // /**
    // * Set the new content model.
    // *
    // * @param context
    // * the link to the new content model.
    // */
    // void setContentModel(ResourceRef contentModel);
    //
    // /**
    // * @return The lock status.
    // */
    // String getLockStatus();
    //
    // /**
    // * Set the new lock status.
    // *
    // * @param lockStatus
    // * The new lock status.
    // */
    // void setLockStatus(String lockStatus);
    //
    // /**
    // * @return The lock date.
    // */
    // String getLockDate();
    //
    // /**
    // * Set the lock date.
    // *
    // * @param lockDate
    // * The new lock date.
    // */
    // void setLockDate(String lockDate);
    //
    // /**
    // * @return The lock owner.
    // */
    // ResourceRef getLockOwner();
    //
    // /**
    // * Set the lock owner.
    // *
    // * @param lockOwner
    // * The link to the new lock owner.
    // */
    // void setLockOwner(ResourceRef lockOwner);
    //
    // /**
    // * @return The object pid.
    // */
    // String getPid();
    //
    // /**
    // * Set the new object pid.
    // *
    // * @param pid
    // * The new object pid.
    // */
    // void setPid(String pid);
    //
    // /**
    // * Get information about the current version.
    // *
    // * @return The version properties.
    // */
    // VersionInterface getVersion();
    //
    // /**
    // * Set the version properties.
    // *
    // * @param version
    // * The new version properties.
    // */
    // void setVersion(VersionInterface version);
    //
    // /**
    // * Get information about the latest version.
    // *
    // * @return The latest version properties.
    // */
    // LatestVersionInterface getLatestVersion();
    //
    // /**
    // * Set the latest version properties.
    // *
    // * @param latestVersion
    // * The new latest version properties.
    // */
    // void setLatestVersion(LatestVersionInterface latestVersion);
    //
    // /**
    // * Get information about the latest release.
    // *
    // * @return The latest release properties.
    // */
    // LatestReleaseInterface getLatestRelease();
    //
    // /**
    // * Set the latest release properties.
    // *
    // * @param latestRelease
    // * The new latest release properties.
    // */
    // void setLatestRelease(LatestReleaseInterface latestRelease);
    //
    // /**
    // * @return Get content model specific properties.
    // */
    // ContentModelSpecific getContentModelSpecific();
    //
    // /**
    // * Set content model specific properties.
    // *
    // * @param contentModelSpecific
    // * The new content model specific properties.
    // */
    // void setContentModelSpecific(ContentModelSpecific contentModelSpecific);
}
