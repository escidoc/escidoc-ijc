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
package de.escidoc.core.resources.common.versionhistory;

import java.util.LinkedList;
import java.util.List;

import org.joda.time.DateTime;

import de.escidoc.core.annotations.JiBX;
import de.escidoc.core.common.DateTimeUtility;
import de.escidoc.core.resources.common.reference.Reference;

/**
 * 
 * @author ?, MVO
 * 
 */
@JiBX
public class Version extends Reference {

    private String versionNumber;

    private DateTime timestamp;

    private String versionStatus;

    private String comment;

    private List<Event> events;

    /**
     * @return the versionNumber
     */
    public String getVersionNumber() {
        return versionNumber;
    }

    /**
     * @return the timestamp
     */
    public DateTime getTimestamp() {
        return timestamp;
    }

    /**
     * @param date
     */
    protected void setTimestamp(final DateTime date) {
        this.timestamp = DateTimeUtility.normalize(date);
    }

    /**
     * @return the versionStatus
     */
    public String getVersionStatus() {
        return versionStatus;
    }

    /**
     * @return the comment
     */
    public String getComment() {
        return comment;
    }

    /**
     * @return the events
     */
    public List<Event> getEvents() {
        if (events == null) {
            events = new LinkedList<Event>();
        }
        return events;
    }
}