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

import java.util.Collection;
import java.util.LinkedList;

import de.escidoc.core.resources.ResourceRef;

/**
 * Organizational Unit Parents.
 * 
 * @author SWA
 * 
 */
public class Parents {

    private Collection<ResourceRef> parentRefs = new LinkedList<ResourceRef>();

    /**
     * Set the whole Parents collection.
     * 
     * @param parentRef
     *            Collection of Parents for the OrganizationalUnit.
     */
    public void setParentRef(final Collection<ResourceRef> parentRef) {
        this.parentRefs = parentRef;
    }

    /**
     * Add a Parent to the parent collection of the OrganizationalUnit.
     * 
     * @param parentRef
     *            New resource reference to the parent.
     */
    public void addParentRef(final ResourceRef parentRef) {
        this.parentRefs.add(parentRef);
    }

}
