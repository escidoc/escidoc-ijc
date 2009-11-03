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
import java.util.Iterator;
import java.util.LinkedList;

import de.escidoc.core.resources.ResourceRef;

/**
 * 
 * @author SWA
 * 
 * @param <T>
 */
public class ResourceList<T extends ResourceRef> {

    public final Collection<T> resources = new LinkedList<T>();

    public ResourceList() {

    }

    /**
     * Get all Resources of ResourceList.
     * 
     * @return
     */
    public Collection<T> getResources() {

        return this.resources;
    }

    /**
     * Get a Resource.
     * 
     * @param resourceId
     *            The id of the Resource.
     * @return
     */
    public T get(final String resourceId) {
        T result = null;
        Iterator<T> resourcesIter = this.resources.iterator();
        while (resourcesIter.hasNext()) {
            T next = resourcesIter.next();
            if (next.getObjid().equals(resourceId)) {
                result = next;
                break;
            }
        }
        return result;
    }

    /**
     * 
     * @param Resource
     */
    public void add(final T resource) {
        this.resources.add(resource);
    }

    /**
     * 
     * @param ResourceId
     */
    public void del(final String resourceId) {
        this.resources.remove(resourceId);
    }
}
