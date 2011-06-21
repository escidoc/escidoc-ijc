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

import static de.escidoc.core.common.Precondition.checkNotNull;
import de.escidoc.core.annotations.JiBX;
import de.escidoc.core.resources.ResourceType;

/**
 * Scope definition.
 * 
 * @author ?, SWA
 * 
 */
@JiBX
public class ScopeDef {

    private ResourceType resourceType;

    private String relationAttributeId;

    private ResourceType relationAttributeObjectType;

    /**
     * 
     */
    @SuppressWarnings("unused")
    @JiBX
    private ScopeDef() {

    }

    /**
     * @param resourceType
     */
    public ScopeDef(final ResourceType resourceType) {
        checkNotNull(resourceType);
        this.resourceType = resourceType;
    }

    /**
     * @param resourceType
     * @param relationAttributeId
     * @param relationAttributeObjectType
     */
    public ScopeDef(final ResourceType resourceType, final String relationAttributeId,
        final ResourceType relationAttributeObjectType) {

        checkNotNull(resourceType);
        checkNotNull(relationAttributeId);
        checkNotNull(relationAttributeObjectType);

        this.resourceType = resourceType;
        this.relationAttributeId = relationAttributeId;
        this.relationAttributeObjectType = relationAttributeObjectType;
    }

    /**
     * Type of Resource.
     * 
     * @return resource type
     */
    public ResourceType getResourceType() {
        return resourceType;
    }

    /**
     * Get id of relation attribute.
     * 
     * @return id
     */
    public String getRelationAttributeId() {
        return relationAttributeId;
    }

    /**
     * @return the relationAttributeObjectType
     */
    public ResourceType getRelationAttributeObjectType() {
        return relationAttributeObjectType;
    }
}