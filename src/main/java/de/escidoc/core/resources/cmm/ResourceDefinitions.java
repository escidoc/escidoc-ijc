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
package de.escidoc.core.resources.cmm;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * Resource definitions of Content Model.
 * 
 * @author SWA
 * 
 */
public class ResourceDefinitions extends LinkedList<ResourceDefinition> {

    /**
     * 
     */
    private static final long serialVersionUID = 6294687849491902143L;

    /**
     * Consturctor ResourceDefinitions.
     */
    public ResourceDefinitions() {

    }

    /**
     * Get a resourceDefinition.
     * 
     * @param name
     *            The name of the resourceDefinition.
     * @return resourceDefinition object.
     */
    public ResourceDefinition get(final String name) {

        ResourceDefinition result = null;
        Iterator<ResourceDefinition> resourceDefIter = this.iterator();
        while (resourceDefIter.hasNext()) {
            ResourceDefinition next = resourceDefIter.next();
            if (next.getName().equals(name)) {
                result = next;
                break;
            }
        }
        return result;
    }

    /**
     * 
     * @param name
     *            The name of the resourceDefinition.
     */
    public void del(final String name) {

        Iterator<ResourceDefinition> resourceDefIter = this.iterator();
        while (resourceDefIter.hasNext()) {
            ResourceDefinition next = resourceDefIter.next();
            if (next.getName().equals(name)) {
                this.remove(name);
                break;
            }
        }
    }

}
