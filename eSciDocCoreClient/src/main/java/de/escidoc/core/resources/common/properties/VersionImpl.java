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

import de.escidoc.core.annotations.JiBX;
import de.escidoc.core.common.DateTimeUtility;
import de.escidoc.core.resources.Resource;
import de.escidoc.core.resources.ResourceType;
import de.escidoc.core.resources.XLinkAutonomous;
import de.escidoc.core.resources.common.reference.Reference;
import de.escidoc.core.resources.common.reference.UserAccountRef;
import de.escidoc.core.resources.interfaces.common.Version;

/**
 * Represents a version of a {@link Resource}. An instance of this class has no
 * {@link ResourceType} defined and therefore the
 * {@link XLinkAutonomous#generateXLinkHref(String)} method has to be invoked on
 * the object, which is declaring an instance of this class as a variable, in
 * order to generate the XLinkHref for an instance of this class.
 * 
 * @author Marko Voß
 * 
 */
@JiBX
public class VersionImpl extends Reference implements Version {

    private String status = null;

    private UserAccountRef modifiedBy = null;

    private String comment = null;

    private String number = null;

    private DateTime date = null;

    private String pid = null;

    /**
     * 
     */
    @JiBX
    public VersionImpl() {
        super();
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.resources.interfaces.common.LatestVersion#getDate()
     */
    @Override
    public DateTime getDate() {
        return this.date;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.resources.interfaces.common.LatestVersion#setDate(org
     * .joda.time.DateTime)
     */
    @Override
    public void setDate(final DateTime date) {
        this.date = DateTimeUtility.normalize(date);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.resources.interfaces.common.LatestVersion#getNumber()
     */
    @Override
    public String getNumber() {
        return this.number;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.resources.interfaces.common.LatestVersion#setNumber(java
     * .lang.String)
     */
    @Override
    public void setNumber(final String number) {
        this.number = number;
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.resources.interfaces.common.Version#getStatus()
     */
    @Override
    public String getStatus() {
        return this.status;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.resources.interfaces.common.Version#setStatus(java.lang
     * .String)
     */
    @Override
    public void setStatus(final String status) {
        this.status = status;
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.resources.interfaces.common.Version#getModifiedBy()
     */
    @Override
    public UserAccountRef getModifiedBy() {
        return this.modifiedBy;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.resources.interfaces.common.Version#setModifiedBy(de.
     * escidoc.core.resources.common.reference.UserAccountRef)
     */
    @Override
    public void setModifiedBy(final UserAccountRef modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.resources.interfaces.common.LatestRelease#getPid()
     */
    @Override
    public String getPid() {
        return this.pid;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.resources.interfaces.common.LatestRelease#setPid(java
     * .lang.String)
     */
    @Override
    public void setPid(final String pid) {
        this.pid = pid;
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.resources.interfaces.common.Version#getComment()
     */
    @Override
    public String getComment() {
        return this.comment;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.resources.interfaces.common.Version#setComment(java.lang
     * .String)
     */
    @Override
    public void setComment(final String comment) {
        this.comment = comment;
    }

    /**
     * An instance of a {@link VersionImpl} has no {@link ResourceType} defined
     * because of the {@link ResourceType} depends of the {@link Resource},
     * which is defining the properties of itself, where the version is a part
     * of the properties and therefore the method
     * {@link XLinkAutonomous#generateXLinkHref(String)} has to be invoked on
     * the properties object, which has to call this method with the path from
     * {@link ResourceType#getPath()} as the value for the
     * <code>parentPath</code> parameter.
     */
    @Override
    public void generateXLinkHref(final String parentPath) {
        if (getXLinkHref() == null && parentPath != null && getObjid() != null)
            setXLinkHref(parentPath + '/' + getObjid());
    }
}