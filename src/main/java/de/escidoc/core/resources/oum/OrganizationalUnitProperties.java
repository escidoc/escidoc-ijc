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

import org.joda.time.DateTime;

import de.escidoc.core.resources.XLinkResource;
import de.escidoc.core.resources.common.reference.UserAccountRef;

/**
 * Properties for eSciDoc OrganizationalUnit (non-versioned resource).
 * 
 * @author SWA
 * 
 */
public class OrganizationalUnitProperties extends XLinkResource {

    private DateTime creationDate = null;

    private UserAccountRef createdBy = null;

    private UserAccountRef modifiedBy = null;

    private String name = null;

    private String publicStatus = null;

    private String publicStatusComment = null;

    private String description = null;

    private String externalIds = null;

    private Boolean hasChildren = Boolean.FALSE;

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
    public UserAccountRef getCreatedBy() {
        return this.createdBy;
    }

    /**
     * Set the creator.
     * 
     * @param createdBy
     *            The link to the new creator.
     */
    public void setCreatedBy(final UserAccountRef createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * @return The creator.
     */
    public UserAccountRef getModifiedBy() {
        return this.modifiedBy;
    }

    /**
     * Set the creator.
     * 
     * @param modifiedBy
     *            The link to the new modifier.
     */
    public void setModifiedBy(final UserAccountRef modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    /**
     * See Interface for functional description.
     * 
     * @return
     * @see de.escidoc.core.resources.interfaces.common.OrganizationalUnitPropertiesInterface#getName()
     */
    public String getName() {
        return this.name;
    }

    /**
     * See Interface for functional description.
     * 
     * @param name
     * @see de.escidoc.core.resources.interfaces.common.OrganizationalUnitPropertiesInterface#setName(java.lang.String)
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * Get the public status comment.
     * 
     * @return The public status comment.
     */
    public String getPublicStatusComment() {
        return this.publicStatusComment;
    }

    /**
     * Set the comment for the public status.
     * 
     * @param comment
     *            The public status comment.
     */
    public void setPublicStatusComment(final String comment) {
        this.publicStatusComment = comment;
    }

    /**
     * Get the public status comment.
     * 
     * @return The public status comment.
     */
    public String getPublicStatus() {
        return this.publicStatus;
    }

    /**
     * Set the the public status.
     * 
     * @param status
     *            The public status.
     */
    public void setPublicStatus(final String status) {
        this.publicStatus = status;
    }

    /**
     * Get description of OrganizationalUnit.
     * 
     * @return description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Set description of OrganizationalUnit.
     * 
     * @param description
     *            The new description.
     */
    public void setDescription(final String description) {
        this.description = description;
    }

    /**
     * Get externalIds of OrganizationalUnit.
     * 
     * @return externalIds
     */
    public String getExternalIds() {
        return this.externalIds;
    }

    /**
     * Set externalIds of OrganizationalUnit.
     * 
     * @param externalIds
     *            The new externalIds.
     */
    public void setExternalIds(final String externalIds) {
        this.externalIds = externalIds;
    }

    /**
     * Get hasChildren of OrganizationalUnit.
     * 
     * @return hasChildren
     */
    public Boolean getHasChildren() {
        return this.hasChildren;
    }

    /**
     * Get hasChildren of OrganizationalUnit.
     * 
     * @return hasChildren
     */
    public String getHasChildrenAsString() {
        return this.hasChildren.toString();
    }

    /**
     * Set hasChildren of OrganizationalUnit.
     * 
     * @param hasChildren
     *            The new hasChildren.
     */
    public void setHasChildren(final Boolean hasChildren) {
        this.hasChildren = hasChildren;
    }

    /**
     * Set hasChildren of OrganizationalUnit.
     * 
     * @param value
     *            The String representation of the new hasChildren value.
     */
    public void setHasChildrenAsString(final String value) {
        this.hasChildren = Boolean.valueOf(value);
    }
}
