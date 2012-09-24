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

import static de.escidoc.core.common.Precondition.checkNotNull;
import de.escidoc.core.annotations.JiBX;
import de.escidoc.core.resources.ResourceType;
import de.escidoc.core.resources.common.reference.Reference;

/**
 * Relation (Content Relation).
 * 
 * @author SWA
 * 
 */
@JiBX
public class Relation extends Reference {

    private String predicate;

    private ResourceType type;

    /**
     * JiBX Constructor
     */
    @JiBX
    protected Relation() {
    }

    /**
     * 
     * @param ref
     *            The resource reference.
     */
    public Relation(final Reference ref) {
        checkNotNull(ref);
        identifier = new Identifier(ref.getObjid());
        identifier.setHref(ref.getXLinkHref());
        identifier.setTitle(ref.getXLinkTitle());
        identifier.setType(ref.getXLinkType());
        type = ref.getResourceType();
    }

    /**
     * 
     * @param ref
     * @param predicate
     */
    public Relation(final Reference ref, final String predicate) {
        this(ref);
        this.predicate = predicate;
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

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.resources.Resource#getResourceType()
     */
    @Override
    public ResourceType getResourceType() {
        return type;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.resources.common.reference.Reference#generateXLinkHref
     * (java.lang.String)
     */
    @Override
    public void generateXLinkHref(final String parentPath) {
        /*
         * It is impossible to generate an xlink:href out of an objid, since
         * there is no type supported in SOAP for relations. The only
         * possibility we have here, is to generate the type of this Relation
         * out of the xlink:href if and only if the xlink:href was set before
         * unmarshalling to an instance of this class. Therefore the type is
         * only supported, if REST is being used as the TransportProtocol.
         */
        if (getXLinkHref() != null && this.type == null) {
            final String prefixPath = getXLinkHref().substring(0, getXLinkHref().lastIndexOf('/'));
            final ResourceType type = ResourceType.getValue(prefixPath);
            if (type != null) {
                this.type = type;
            }
        }
    }
}