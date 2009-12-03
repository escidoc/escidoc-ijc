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
package de.escidoc.core.resources.om.toc;

import org.joda.time.DateTime;

import de.escidoc.core.resources.ResourceRef;
import de.escidoc.core.resources.interfaces.common.TocPropertiesInterface;

/**
 * Properties for eSciDoc Table Of Content (TOC - versioned resource).
 * 
 * @author SWA
 * 
 */
public class Properties implements TocPropertiesInterface {

    private DateTime creationDate = null;

    private DateTime lastModificationDate = null;

    private ResourceRef createdBy = null;

    private ResourceRef modifiedBy = null;

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
     * @return The creation date.
     */
    public DateTime getLastModificationDate() {
        return this.lastModificationDate;
    }

    /**
     * Set the last modification date.
     * 
     * @param lastModificationDate
     *            The new last modification date.
     */
    public void setLastModificationDate(final DateTime lastModificationDate) {
        this.lastModificationDate = lastModificationDate;
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

}
