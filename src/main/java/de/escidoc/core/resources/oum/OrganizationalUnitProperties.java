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

import java.util.ArrayList;
import java.util.List;

import de.escidoc.core.resources.common.properties.CommonProperties;
import de.escidoc.core.resources.common.properties.PublicStatus;
import de.escidoc.core.resources.common.properties.interfaces.DescriptionProperties;
import de.escidoc.core.resources.common.properties.interfaces.ModifyProperties;
import de.escidoc.core.resources.common.properties.interfaces.NameProperties;
import de.escidoc.core.resources.common.properties.interfaces.PublicStatusProperties;
import de.escidoc.core.resources.common.reference.UserAccountRef;

/**
 * Properties for eSciDoc OrganizationalUnit (non-versioned resource).
 * 
 * @author SWA
 * 
 */
public class OrganizationalUnitProperties extends CommonProperties
    implements ModifyProperties, PublicStatusProperties, NameProperties, DescriptionProperties {

    private UserAccountRef modifiedBy;

    private String name;

    private PublicStatus publicStatus;

    private String publicStatusComment;

    private String description;

    private List<String> externalIds;

    private Boolean hasChildren = false;

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.resources.common.properties.interfaces.ModifyProperties
     * #getModifiedBy()
     */
    @Override
    public UserAccountRef getModifiedBy() {
        return this.modifiedBy;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.resources.common.properties.interfaces.ModifyProperties
     * #setModifiedBy(de.escidoc.core.resources.common.reference.UserAccountRef)
     */
    @Override
    public void setModifiedBy(final UserAccountRef modifiedBy) {
        this.modifiedBy = modifiedBy;
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
     * @see
     * de.escidoc.core.resources.common.properties.interfaces.PublicStatusProperties
     * #getPublicStatusComment()
     */
    @Override
    public String getPublicStatusComment() {
        return this.publicStatusComment;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.resources.common.properties.interfaces.PublicStatusProperties
     * #setPublicStatusComment(java.lang.String)
     */
    @Override
    public void setPublicStatusComment(final String comment) {
        this.publicStatusComment = comment;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.resources.common.properties.interfaces.PublicStatusProperties
     * #getPublicStatus()
     */
    @Override
    public PublicStatus getPublicStatus() {
        return this.publicStatus;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.resources.common.properties.interfaces.PublicStatusProperties
     * #setPublicStatus(java.lang.String)
     */
    @Override
    public void setPublicStatus(final PublicStatus status) {
        this.publicStatus = status;
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

    /**
     * 
     * @param externalIds
     */
    public void setExternalIds(final List<String> externalIds) {
        this.externalIds = externalIds;
    }

    /**
     * 
     * @return
     */
    public List<String> getExternalIds() {
        if (this.externalIds == null) {
            this.externalIds = new ArrayList<String>();
        }
        return this.externalIds;
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
     * Set hasChildren of OrganizationalUnit.
     * 
     * @param hasChildren
     *            The new hasChildren.
     */
    public void setHasChildren(final Boolean hasChildren) {
        this.hasChildren = hasChildren;
    }
}