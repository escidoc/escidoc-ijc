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

import java.util.Collection;
import java.util.LinkedList;

import org.joda.time.DateTime;

import de.escidoc.core.resources.Resource;

public class Version extends Resource {

    private String versionNumber;

    private DateTime timestamp;

    private String versionStatus;

    private String comment;

    private Collection<Event> events;

    public static LinkedList<Event> eventsFactory() {
        return new LinkedList<Event>();
    }

    /**
     * @return the versionNumber
     */
    public String getVersionNumber() {
        return versionNumber;
    }

    /**
     * @param versionNumber
     *            the versionNumber to set
     */
    public void setVersionNumber(String versionNumber) {
        this.versionNumber = versionNumber;
    }

    /**
     * @return the timestamp
     */
    public DateTime getTimestamp() {
        return timestamp;
    }

    /**
     * @param timestamp
     *            the timestamp to set
     */
    public void setTimestamp(final DateTime timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * @return the versionStatus
     */
    public String getVersionStatus() {
        return versionStatus;
    }

    /**
     * @param versionStatus
     *            the versionStatus to set
     */
    public void setVersionStatus(String versionStatus) {
        this.versionStatus = versionStatus;
    }

    /**
     * @return the comment
     */
    public String getComment() {
        return comment;
    }

    /**
     * @param comment
     *            the comment to set
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * @return the events
     */
    public Collection<Event> getEvents() {
        return events;
    }

    /**
     * @param events
     *            the events to set
     */
    public void setEvents(Collection<Event> events) {
        this.events = events;
    }
}
