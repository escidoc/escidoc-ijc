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
package de.escidoc.core.resources.common;

import de.escidoc.core.annotations.JiBX;
import de.escidoc.core.resources.Resource;
import de.escidoc.core.resources.ResourceType;
import de.escidoc.core.resources.common.reference.Reference;

/**
 * Relation (Content Relation).
 * 
 * @author SWA
 * 
 */
@JiBX
public class Relation extends Resource {

    private String predicate;

    private ResourceType type;

    @SuppressWarnings("unused")
    @JiBX
    private Relation() {
    }

    /**
     * 
     * @param ref
     *            The resource reference.
     */
    public Relation(final Reference ref) {

        if (ref == null)
            throw new IllegalArgumentException("ref must not be null.");

        setObjid(ref.getObjid());
        setXLinkHref(ref.getXLinkHref());
        setXLinkTitle(ref.getXLinkTitle());

        this.type = ref.getResourceType();
    }

    /**
     * Get Predicate.
     * 
     * @return predicate
     */
    public String getPredicate() {
        return predicate;
    }

    /**
     * Set Predicate.
     * 
     * @param predicate
     *            Predicate
     */
    public void setPredicate(final String predicate) {
        this.predicate = predicate;
    }

    @Override
    public ResourceType getResourceType() {
        return type;
    }
}