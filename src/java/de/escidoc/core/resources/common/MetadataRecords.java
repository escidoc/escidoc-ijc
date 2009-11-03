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

/**
 * MetadataRecords.
 * 
 * @author SWA
 * 
 */
public class MetadataRecords {

    private final Collection<MetadataRecord> mdRecords =
        new LinkedList<MetadataRecord>();

    /**
     * Consturctor MetadataRecords.
     */
    public MetadataRecords() {

    }

    /**
     * Get all MetadataRecord of MetadataRecords.
     * 
     * @return The Collection of MetadataRecords
     */
    public Collection<MetadataRecord> getMetadataRecords() {

        return this.mdRecords;
    }

    /**
     * Get a MetadataRecord.
     * 
     * @param name
     *            The name of the MetadataRecord.
     * @return MetadataRecord object.
     */
    public MetadataRecord get(final String name) {

        MetadataRecord result = null;
        Iterator<MetadataRecord> mdRecordIter = this.mdRecords.iterator();
        while (mdRecordIter.hasNext()) {
            MetadataRecord next = mdRecordIter.next();
            if (next.getName().equals(name)) {
                result = next;
                break;
            }
        }
        return result;
    }

    /**
     * Add metadataRecord to list of metadataRecords.
     * 
     * @param metadataRecord
     *            MetadataRecord object.
     */
    public void add(final MetadataRecord metadataRecord) {
        this.mdRecords.add(metadataRecord);
    }

    /**
     * 
     * @param name
     *            The name of the metadataRecord.
     */
    public void del(final String name) {

        Iterator<MetadataRecord> mdRecordIter = this.mdRecords.iterator();
        while (mdRecordIter.hasNext()) {
            MetadataRecord next = mdRecordIter.next();
            if (next.getName().equals(name)) {
                this.mdRecords.remove(name);
                break;
            }
        }
    }
}
