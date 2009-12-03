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
package de.escidoc.core.resources.om.context;

import org.joda.time.DateTime;

import de.escidoc.core.resources.ResourceRef;
import de.escidoc.core.resources.interfaces.common.ContextPropertiesInterface;

/**
 * Properties for eSciDoc Context (non-versioned resource).
 * 
 * @author SWA
 * 
 */
public class Properties implements ContextPropertiesInterface {

    private DateTime creationDate = null;

    private ResourceRef createdBy = null;

    private ResourceRef modifiedBy = null;

    private String name = null;

    private String publicStatus = null;

    private String publicStatusComment = null;

    private String description = null;

    private String type = null;

    private OrganizationalUnitRefs organizationalUnitRefs = null;

    /**
     * @return The creation date.
     */
    public DateTime getCreationDate() {
        return this.creationDate;
    }

    /**
     * @return The creation date as String.
     */
    public String getCreationDateAsString() {
        if (this.creationDate != null) {
            return this.creationDate.toString();
        }
        return null;
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
     * Set the creation date.
     * 
     * @param dateString
     *            The new creation date.
     */
    public void setCreationDateAsString(final String dateString) {
        this.creationDate = new DateTime(dateString);
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
     * @return
     * @see de.escidoc.core.resources.interfaces.common.ContextPropertiesInterface#getName()
     */
    public String getName() {
        return this.name;
    }

    /**
     * See Interface for functional description.
     * 
     * @param name
     * @see de.escidoc.core.resources.interfaces.common.ContextPropertiesInterface#setName(java.lang.String)
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
     * Get description of Context.
     * 
     * @return description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Set description of Context.
     * 
     * @param description
     *            The new description.
     */
    public void setDescription(final String description) {
        this.description = description;
    }

    /**
     * Get type of Context.
     * 
     * @return type
     */
    public String getType() {
        return this.type;
    }

    /**
     * Set type of Context.
     * 
     * @param type
     *            The new type.
     */
    public void setType(final String type) {
        this.type = type;
    }

    /**
     * Get the OrganizationalUnits of Context.
     * 
     * @return OrganizationalUnits
     */
    public OrganizationalUnitRefs getOrganizationalUnitRefs() {
        return this.organizationalUnitRefs;
    }

    /**
     * Set the OrganizationalUnits of Context.
     * 
     * @param organizationalUnitRefs
     *            The new OrganizationalUnitRefs (resource references) of
     *            Context.
     */
    public void setOrganizationalUnitRefs(
        final OrganizationalUnitRefs organizationalUnitRefs) {
        this.organizationalUnitRefs = organizationalUnitRefs;
    }
}
