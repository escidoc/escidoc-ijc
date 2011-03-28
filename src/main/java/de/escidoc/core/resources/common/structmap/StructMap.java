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
package de.escidoc.core.resources.common.structmap;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

import de.escidoc.core.resources.ResourceType;
import de.escidoc.core.resources.XLinkResource;

/**
 * StructMap (Structure Map of Container).
 * 
 * The struct map could only directly altered for create. Each direct update of
 * struct map is discarded. To add member to an existing Container use the
 * addMembers() and removeMembers() methods.
 * 
 * A struct map is divided into two groups:
 * <ul>
 * <li>item references</li>
 * <li>container references</li>
 * </ul>
 * 
 * Therefore, item references will be stored before container references. This
 * behavior applies to all methods.
 * 
 * @author MVO
 * 
 */
public class StructMap extends XLinkResource
    implements Iterable<MemberRef>, Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -657913209962433330L;

    private List<ItemMemberRef> items = new LinkedList<ItemMemberRef>();

    private List<ContainerMemberRef> containers =
        new LinkedList<ContainerMemberRef>();

    /**
     * StructMap.
     * 
     * The struct map could only directly altered for create. Each direct update
     * of struct map is discarded. To add member to an existing Container use
     * the addMembers() and removeMembers() methods.
     */
    public StructMap() {
    }

    /**
     * @return
     */
    public List<ItemMemberRef> getItems() {
        if (items == null)
            items = new LinkedList<ItemMemberRef>();
        return items;
    }

    /**
     * @return
     */
    public List<ContainerMemberRef> getContainers() {
        if (containers == null)
            containers = new LinkedList<ContainerMemberRef>();
        return containers;
    }

    /**
     * Convenience method to add an {@link ItemMemberRef} or a
     * {@link ContainerMemberRef} to the Struct-Map.
     * 
     * @param m
     * @return
     */
    public boolean add(final MemberRef m) {
        if (m == null)
            return false;
        if (m.getResourceType() == ResourceType.ITEM)
            return getItems().add((ItemMemberRef) m);
        if (m.getResourceType() == ResourceType.CONTAINER)
            return getContainers().add((ContainerMemberRef) m);
        return false;
    }

    /**
     * Convenience method to remove an {@link ItemMemberRef} or a
     * {@link ContainerMemberRef} from the Struct-Map.
     * 
     * @param m
     * @return
     */
    public boolean remove(final MemberRef m) {
        if (m == null)
            return false;
        if (m.getResourceType() == ResourceType.ITEM) {
            return items != null ? items.remove(m) : false;
        }
        if (m.getResourceType() == ResourceType.CONTAINER)
            return containers != null ? containers.remove(m) : false;
        return false;
    }

    /**
     * Convenience method to get the size of the Struct-Map.
     * 
     * @return
     */
    public int size() {
        int size = 0;
        if (items != null)
            size += items.size();
        if (containers != null)
            size += containers.size();
        return size;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Iterable#iterator()
     */
    @Override
    public Iterator<MemberRef> iterator() {
        return new StructMapIterator();
    }

    /**
     * 
     * @author MVO
     * 
     */
    public class StructMapIterator implements Iterator<MemberRef> {

        private Iterator<ItemMemberRef> itItems;

        private Iterator<ContainerMemberRef> itContainers;

        StructMapIterator() {
            if (items != null)
                itItems = items.iterator();
            if (containers != null)
                itContainers = containers.iterator();
        }

        /*
         * (non-Javadoc)
         * 
         * @see java.util.Iterator#hasNext()
         */
        @Override
        public boolean hasNext() {
            if (itItems != null && itItems.hasNext())
                return true;
            else if (itContainers != null && itContainers.hasNext())
                return true;

            return false;
        }

        /*
         * (non-Javadoc)
         * 
         * @see java.util.Iterator#next()
         */
        @Override
        public MemberRef next() {
            if (itItems != null && itItems.hasNext())
                return itItems.next();
            else if (itContainers != null && itContainers.hasNext())
                return itContainers.next();

            throw new NoSuchElementException();
        }

        /*
         * (non-Javadoc)
         * 
         * @see java.util.Iterator#remove()
         */
        @Override
        public void remove() {
            if (itItems != null && itItems.hasNext())
                itItems.remove();
            else if (itContainers != null && itContainers.hasNext())
                itContainers.remove();
            else
                throw new IllegalStateException();
        }
    }
}