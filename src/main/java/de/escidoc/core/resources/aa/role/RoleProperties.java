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
 * Copyright 2006-2010 Fachinformationszentrum Karlsruhe Gesellschaft
 * fuer wissenschaftlich-technische Information mbH and Max-Planck-
 * Gesellschaft zur Foerderung der Wissenschaft e.V.  
 * All rights reserved.  Use is subject to license terms.
 */
package de.escidoc.core.resources.aa.role;

import org.joda.time.DateTime;

import de.escidoc.core.annotations.JiBX;
import de.escidoc.core.common.DateTimeUtility;
import de.escidoc.core.resources.common.properties.interfaces.CreateProperties;
import de.escidoc.core.resources.common.properties.interfaces.DescriptionProperties;
import de.escidoc.core.resources.common.properties.interfaces.ModifyProperties;
import de.escidoc.core.resources.common.properties.interfaces.NameProperties;
import de.escidoc.core.resources.common.reference.UserAccountRef;

/**
 * Properties of a Role.
 * 
 * @author SWA
 * 
 */
@JiBX
public class RoleProperties implements CreateProperties, ModifyProperties, NameProperties, DescriptionProperties {

    private UserAccountRef createdBy;

    private DateTime creationDate;

    private UserAccountRef modifiedBy;

    private String name;

    private String description;

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.resources.common.properties.interfaces.ModifyProperties
     * #getModifiedBy()
     */
    @Override
    public UserAccountRef getModifiedBy() {
        return modifiedBy;
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
     * de.escidoc.core.resources.common.properties.interfaces.CreateProperties
     * #getCreatedBy()
     */
    @Override
    public UserAccountRef getCreatedBy() {
        return createdBy;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.resources.common.properties.interfaces.CreateProperties
     * #setCreatedBy(de.escidoc.core.resources.common.reference.UserAccountRef)
     */
    @Override
    public void setCreatedBy(final UserAccountRef createdBy) {
        this.createdBy = createdBy;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.resources.common.properties.interfaces.CreateProperties
     * #getCreationDate()
     */
    @Override
    public DateTime getCreationDate() {
        return creationDate;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.resources.common.properties.interfaces.CreateProperties
     * #setCreationDate(org.joda.time.DateTime)
     */
    @Override
    public void setCreationDate(final DateTime creationDate) {
        this.creationDate = DateTimeUtility.normalize(creationDate);
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
        return name;
    }

    @Override
    public void setName(final String name) {
        this.name = name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(final String description) {
        this.description = description;
    }
}