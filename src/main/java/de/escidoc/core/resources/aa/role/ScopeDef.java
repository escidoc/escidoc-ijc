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
        setResourceType(resourceType);
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
     * Set resource type.
     * 
     * @param resourceType
     *            Resource Type (item, container, context, ..)
     */
    public void setResourceType(final ResourceType resourceType) {
        checkNotNull(resourceType);
        this.resourceType = resourceType;
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
     * Set id of relation of attribute.
     * 
     * @param relationAttributeId
     *            id of relation
     */
    public void setRelationAttributeId(final String relationAttributeId) {
        this.relationAttributeId = relationAttributeId;
    }

    /**
     * @return the relationAttributeObjectType
     */
    public ResourceType getRelationAttributeObjectType() {
        return relationAttributeObjectType;
    }

    /**
     * @param relationAttributeObjectType
     *            the relationAttributeObjectType to set
     */
    public void setRelationAttributeObjectType(
        final ResourceType relationAttributeObjectType) {
        this.relationAttributeObjectType = relationAttributeObjectType;
    }

    /**
     * @return
     */
    @SuppressWarnings("unused")
    private String getXmlResourceType() {
        return resourceType == null ? null : resourceType.getXmlValue();
    }

    /**
     * @return
     */
    @SuppressWarnings("unused")
    private String getXmlRelationAttributeObjectType() {
        return relationAttributeObjectType == null ? null
            : relationAttributeObjectType.getXmlValue();
    }

    /**
     * @param value
     */
    @SuppressWarnings("unused")
    private void setXmlResourceType(final String value) {
        resourceType = ResourceType.getValue(value);
    }

    /**
     * @param value
     */
    @SuppressWarnings("unused")
    private void setXmlRelationAttributeObjectType(final String value) {
        relationAttributeObjectType = ResourceType.getValue(value);
    }
}