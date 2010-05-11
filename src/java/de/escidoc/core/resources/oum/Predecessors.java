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
 * Organizational Unit Predecessors.
 * 
 * @author SWA
 * 
 */
public class Predecessors {

    private Collection<Predecessor> predecessorRefs =
        new LinkedList<Predecessor>();

    /**
     * Get the whole Predecessors collection.
     * 
     * @return Collection of Predecessors for the OrganizationalUnit.
     */
    public Collection<Predecessor> getPredecessorRef() {
        return this.predecessorRefs;
    }

    /**
     * Set the whole Predecessors collection.
     * 
     * @param predecessorRef
     *            Collection of Predecessors for the OrganizationalUnit.
     */
    public void setPredecessorRef(final Collection<Predecessor> predecessorRef) {
        this.predecessorRefs = predecessorRef;
    }

    /**
     * Add a Predecessor to the predecessor collection of the
     * OrganizationalUnit.
     * 
     * @param predecessorRef
     *            New resource reference to the predecessor.
     */
    public void addPredecessorRef(final Predecessor predecessorRef) {
        this.predecessorRefs.add(predecessorRef);
    }

    /**
     * Iterator over Predecessors.
     * 
     * @return Iterator
     */
    public Iterator<Predecessor> iterator() {
        return this.predecessorRefs.iterator();
    }
}
