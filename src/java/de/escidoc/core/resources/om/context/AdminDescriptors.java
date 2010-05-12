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
package de.escidoc.core.resources.om.context;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Admin Descriptors.
 * 
 * @author SWA
 * 
 */
public class AdminDescriptors {

    private Collection<AdminDescriptor> adminDescriptors =
        new LinkedList<AdminDescriptor>();

    /**
     * 
     * @return
     */
    public static LinkedList<AdminDescriptor> adminDescriptorsFactory() {
        return new LinkedList<AdminDescriptor>();
    }

    /**
     * 
     */
    public AdminDescriptors() {
    }

    /**
     * @return the items
     */
    public Collection<AdminDescriptor> getAdminDescriptors() {
        return adminDescriptors;
    }

    /**
     * Set the Collection of AdminDescriptors.
     * 
     * @param adminDescriptors
     *            the AdminDescriptors to set
     */
    public void setAdminDescriptors(
        final Collection<AdminDescriptor> adminDescriptors) {
        this.adminDescriptors = adminDescriptors;
    }

    /**
     * Add AdminDescriptor to AdminDescriptors.
     * 
     * @param adminDescriptor
     */
    public void addAdminDescriptor(final AdminDescriptor adminDescriptor) {
        this.adminDescriptors.add(adminDescriptor);
    }

    /**
     * Get a AdminDescriptor.
     * 
     * @param name
     *            The name of the AdminDescriptor.
     * @return AdminDescriptor object.
     */
    public AdminDescriptor get(final String name) {

        AdminDescriptor result = null;
        Iterator<AdminDescriptor> adIter = this.adminDescriptors.iterator();
        while (adIter.hasNext()) {
            AdminDescriptor next = adIter.next();
            if (next.getName().equals(name)) {
                result = next;
                break;
            }
        }
        return result;
    }
}
