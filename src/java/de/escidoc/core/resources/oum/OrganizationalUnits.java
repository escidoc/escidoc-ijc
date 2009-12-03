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
import java.util.Iterator;
import java.util.LinkedList;

/**
 * OrganizationalUnits of eSciDoc.
 * 
 * @author SWA
 * 
 */
public class OrganizationalUnits {

    private Collection<OrganizationalUnit> organizationalUnits =
        new LinkedList<OrganizationalUnit>();

    /**
     * 
     */
    public OrganizationalUnits() {
    }

    /**
     * Get Collection of OrganizationalUnits.
     * 
     * @return Collection of OrganizationalUnits
     */
    public Collection<OrganizationalUnit> getOrganizationalUnits() {
        return organizationalUnits;
    }

    /**
     * Get a OrganizationalUnit.
     * 
     * @param organizationalUnitId
     *            The id of the OrganizationalUnit.
     * @return The OrganizationalUnit with the provided objid.
     */
    public OrganizationalUnit get(final String organizationalUnitId) {
        OrganizationalUnit result = null;
        Iterator<OrganizationalUnit> ouIter =
            this.organizationalUnits.iterator();
        while (ouIter.hasNext()) {
            OrganizationalUnit next = ouIter.next();
            if (next.getObjid().equals(organizationalUnitId)) {
                result = next;
                break;
            }
        }
        return result;
    }

    /**
     * Set Collection of OrganizationalUnits.
     * 
     * @param organizationalUnits
     *            The OrganizationalUnit to set.
     */
    public void setOrganizationalUnits(
        final Collection<OrganizationalUnit> organizationalUnits) {
        this.organizationalUnits = organizationalUnits;
    }

}
