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
package de.escidoc.core.resources.om.item.component;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * Components of Item.
 * 
 * @author SWA
 * 
 */
public class Components extends LinkedList<Component> {

    /**
     * 
     */
    private static final long serialVersionUID = 4368909050450367931L;

    /**
     * Components.
     */
    public Components() {

    }

    /**
     * Get the id of a Component selected by objid of Component.
     * 
     * @param componentId
     *            The name of the Component.
     * @return Component
     */
    public Component getComponent(final String componentId) {

        Iterator<Component> componentIter = this.iterator();
        while (componentIter.hasNext()) {
            Component next = componentIter.next();
            if (componentId.equals(next.getObjid())) {
                return next;
            }
        }
        return null;
    }

    /**
     * Delete a Component.
     * 
     * @param componentId
     *            The objid of the to delete Component.
     */
    public void del(final String componentId) {

            Iterator<Component> componentIter = this.iterator();
            while (componentIter.hasNext()) {
                Component next = componentIter.next();
                if (!componentId.equals(next.getObjid())) {
                   remove(next);
                }
            }
    }

}
