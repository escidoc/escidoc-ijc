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
package de.escidoc.core.resources.om.item;

import java.util.Collection;
import java.util.LinkedList;

/**
 * List of Items.
 * 
 * @author SWA
 * 
 *         TODO support paging
 */
public class ItemList {

    private Collection<Item> items = new LinkedList<Item>();

    /**
     * ItemList Factory.
     * 
     * @return ItemList
     */
    public static LinkedList<Item> itemsFactory() {
        return new LinkedList<Item>();
    }

    /**
     * 
     */
    public ItemList() {
    }

    /**
     * @return the items
     */
    public Collection<Item> getItems() {
        return items;
    }

    /**
     * @param items
     *            the items to set
     */
    public void setItems(final Collection<Item> items) {
        this.items = items;
    }

}
