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

import java.util.Collection;
import java.util.LinkedList;

import de.escidoc.core.resources.ResourceRef;

/**
 * Relations. (Has to be rework)
 * 
 * @author
 * 
 */
public class Relations {

    private final Collection<Relation> relations = new LinkedList<Relation>();

    // private Map<ResourceRef, Relation> relations =
    // new HashMap<ResourceRef, Relation>();

    /**
     * 
     */
    public Relations() {

    }

    /**
     * Get all Relation of Relations.
     * 
     * @return Get a HashMap with all relations.
     */
    public Collection<Relation> get() {

        return this.relations;
    }

//    /**
//     * Get the id of a Relation selected by id.
//     * 
//     * @param relationId
//     *            The resourceRef for relation.
//     * @return The Relation with the reference to the resource.
//     */
//    public Relation get(final ResourceRef relationId) {
//        return null;
//        // return relations.get(relationId);
//    }

    /**
     * Add relation.
     * 
     * @param relation
     */
    public void add(final Relation relation) {

        this.relations.add(relation);
    }

//    /**
//     * Add relation by id.
//     * 
//     * @param objId
//     */
//    public void addId(final String objId) {
//
//        // this.relations.put(new ResourceRef(objId), null);
//    }

    /**
     * Remove relation.
     * 
     * @param relation
     */
    public void del(final Relation relation) {
        this.relations.remove(relation);
    }

}
