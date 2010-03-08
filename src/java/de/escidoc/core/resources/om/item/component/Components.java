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

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * 
 * @author SWA
 * 
 */
public class Components {

    private Collection<Component> components = new LinkedList<Component>();

    /**
     * Components.
     */
    public Components() {

    }

    /**
     * Linked Component Factory.
     * 
     * @return LinkedList of Components
     */
    public static LinkedList<Component> componentsFactory() {
        return new LinkedList<Component>();
    }

    /**
     * Get all Component of Components.
     * 
     * @return Collection of Components.
     */
    public Collection<Component> getComponents() {

        return this.components;
    }

    /**
     * Get the id of a Component selected by objid of Component.
     * 
     * @param componentId
     *            The name of the Component.
     * @return Component
     */
    public Component getComponent(final String componentId) {

        Iterator<Component> componentIter = components.iterator();
        while (componentIter.hasNext()) {
            Component next = componentIter.next();
            if (componentId.equals(next.getObjid())) {
                return next;
            }
        }
        return null;
    }

    /**
     * Add a Component.
     * 
     * @param component
     *            A new Component
     */
    public void add(final Component component) {

        Collection<Component> result = componentsFactory();
        result.addAll(this.components);
        result.add(component);
        this.components = result;
    }

    /**
     * Delete a Component.
     * 
     * @param componentId
     *            The objid of the to delete Component.
     */
    public void del(final String componentId) {
        if (components != null) {
            Collection<Component> result = componentsFactory();
            Iterator<Component> componentIter = components.iterator();
            while (componentIter.hasNext()) {
                Component next = componentIter.next();
                if (!componentId.equals(next.getObjid())) {
                    result.add(next);
                }
            }
            this.components = result;
        }
    }

}
