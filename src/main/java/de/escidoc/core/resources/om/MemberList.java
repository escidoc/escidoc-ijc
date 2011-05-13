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
package de.escidoc.core.resources.om;

import java.util.Collection;
import java.util.LinkedList;

import de.escidoc.core.resources.VersionableResource;

/**
 * Member List.
 * 
 * @author SWA
 * 
 */
public class MemberList {

    private int limit = -1;

    private int offset = -1;

    private int numberOfRecords = -1;

    private Collection<VersionableResource> members = new LinkedList<VersionableResource>();

    public static LinkedList<VersionableResource> membersFactory() {
        return new LinkedList<VersionableResource>();
    }

    /**
     * 
     */
    public MemberList() {
    }

    /**
     * @return the items
     */
    public Collection<VersionableResource> getMembers() {
        return members;
    }

    /**
     * @param members
     *            the items to set
     */
    public void setMembers(final Collection<VersionableResource> members) {
        this.members = members;
    }

    /**
     * Set limit.
     * 
     * @param limit
     *            Number of max results per page
     */
    public void setLimit(int limit) {
        this.limit = limit;
    }

    /**
     * Get limit.
     * 
     * @return Number of results
     */
    public int getLimit() {
        return limit;
    }

    /**
     * Set offset.
     * 
     * @param offset
     *            Offset
     */
    public void setOffset(int offset) {
        this.offset = offset;
    }

    /**
     * Get offset.
     * 
     * @return number off offset
     * 
     */
    public int getOffset() {
        return offset;
    }

    /**
     * Set number of result records
     * 
     * @param numberOfRecords
     *            number of result records
     */
    public void setNumberOfRecords(int numberOfRecords) {
        this.numberOfRecords = numberOfRecords;
    }

    /**
     * Get number of result records
     * 
     * @return number of result records
     */
    public int getNumberOfRecords() {
        return numberOfRecords;
    }

}
