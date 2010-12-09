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
package de.escidoc.core.resources.common.properties;

import org.joda.time.DateTime;

import de.escidoc.core.resources.ResourceType;
import de.escidoc.core.resources.common.reference.Reference;
import de.escidoc.core.resources.common.reference.UserAccountRef;
import de.escidoc.core.resources.interfaces.common.LatestReleaseInterface;
import de.escidoc.core.resources.interfaces.common.LatestVersionInterface;
import de.escidoc.core.resources.interfaces.common.VersionInterface;

public class Version extends Reference
    implements LatestVersionInterface, LatestReleaseInterface, VersionInterface {

    public Version() {
        super();
    }

    private String status = null;

    private UserAccountRef modifiedBy = null;

    private String comment = null;

    private String number = null;

    private DateTime date = null;

    private String pid = null;

    @Override
    public DateTime getDate() {
        return this.date;
    }

    @Override
    public void setDate(final DateTime date) {
        this.date = date;
    }

    @Override
    public String getNumber() {
        return this.number;
    }

    @Override
    public void setNumber(final String number) {
        this.number = number;
    }

    @Override
    public String getStatus() {
        return this.status;
    }

    @Override
    public void setStatus(final String status) {
        this.status = status;
    }

    @Override
    public UserAccountRef getModifiedBy() {

        return this.modifiedBy;
    }

    @Override
    public void setModifiedBy(final UserAccountRef modifiedBy) {

        this.modifiedBy = modifiedBy;
    }

    /**
     * See Interface for functional description.
     * 
     * @return
     * @see de.escidoc.core.resources.interfaces.common.LatestReleaseInterface#getPid()
     */
    @Override
    public String getPid() {

        return this.pid;
    }

    @Override
    public void setPid(final String pid) {

        this.pid = pid;
    }

    @Override
    public String getComment() {

        return this.comment;
    }

    @Override
    public void setComment(final String comment) {

        this.comment = comment;
    }

    @Override
    public ResourceType getResourceType() {
        return null;
    }
}
