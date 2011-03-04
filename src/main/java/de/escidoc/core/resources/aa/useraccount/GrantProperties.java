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
 * Copyright 2006-2010 Fachinformationszentrum Karlsruhe Gesellschaft
 * fuer wissenschaftlich-technische Information mbH and Max-Planck-
 * Gesellschaft zur Foerderung der Wissenschaft e.V.  
 * All rights reserved.  Use is subject to license terms.
 */
package de.escidoc.core.resources.aa.useraccount;

import org.joda.time.DateTime;

import de.escidoc.core.resources.common.properties.interfaces.CreateProperties;
import de.escidoc.core.resources.common.reference.Reference;
import de.escidoc.core.resources.common.reference.RoleRef;
import de.escidoc.core.resources.common.reference.UserAccountRef;

/**
 * Properties of Grant.
 * 
 * @author ?, SWA
 * 
 */
public class GrantProperties implements CreateProperties {

    private DateTime creationDate;

    private UserAccountRef createdBy;

    private GrantedTo grantedTo;

    private DateTime revocationDate;

    private UserAccountRef revokedBy;

    private String grantRemark;

    private String revocationRemark;

    private RoleRef role;

    private Reference assignedOn;

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.resources.common.properties.interfaces.CreateProperties
     * #getCreationDate()
     */
    @Override
    public DateTime getCreationDate() {
        return this.creationDate;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.resources.common.properties.interfaces.CreateProperties
     * #setCreationDate(org.joda.time.DateTime)
     */
    @Override
    public void setCreationDate(final DateTime creationDate) {
        this.creationDate = creationDate;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.resources.common.properties.interfaces.CreateProperties
     * #getCreatedBy()
     */
    @Override
    public UserAccountRef getCreatedBy() {
        return this.createdBy;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.resources.common.properties.interfaces.CreateProperties
     * #setCreatedBy(de.escidoc.core.resources.common.reference.UserAccountRef)
     */
    @Override
    public void setCreatedBy(final UserAccountRef createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * @return granted-to
     */
    public GrantedTo getGrantedTo() {
        return grantedTo;
    }

    /**
     * 
     * @param grantedTo
     */
    public void setGrantedTo(final GrantedTo grantedTo) {
        this.grantedTo = grantedTo;
    }

    /**
     * Get revocation date.
     * 
     * @return revocation date
     */
    public DateTime getRevocationDate() {
        return revocationDate;
    }

    /**
     * Set revocation date.
     * 
     * @param revocationDate
     *            Revocation date as String
     */
    public void setRevocationDate(final DateTime revocationDate) {
        this.revocationDate = revocationDate;
    }

    /**
     * Get revoked by.
     * 
     * @return id of revoker
     */
    public UserAccountRef getRevokedBy() {
        return revokedBy;
    }

    /**
     * Set id of revoker.
     * 
     * @param revokedBy
     *            ResourcRef of revoker
     */
    public void setRevokedBy(final UserAccountRef revokedBy) {
        this.revokedBy = revokedBy;
    }

    /**
     * Get grant remark.
     * 
     * @return grant remark
     */
    public String getGrantRemark() {
        return grantRemark;
    }

    /**
     * The method is optional on create of properties, but not-allowed on update
     * of grant properties.
     * 
     * @param grantRemark
     *            Grant remark
     */
    public void setGrantRemark(final String grantRemark) {
        this.grantRemark = grantRemark;
    }

    /**
     * Get revokation mark.
     * 
     * @return revokation mark
     */
    public String getRevocationRemark() {
        return revocationRemark;
    }

    /**
     * Set revokation mark.
     * 
     * @param revocationRemark
     *            revokation mark
     */
    public void setRevocationRemark(final String revocationRemark) {
        this.revocationRemark = revocationRemark;
    }

    /**
     * Get Role.
     * 
     * @return Role
     */
    public RoleRef getRole() {
        return role;
    }

    /**
     * The method is mandatory on create of properties, but not-allowed on
     * update of grant properties.
     * 
     * @param role
     *            Role
     */
    public void setRole(final RoleRef role) {
        this.role = role;
    }

    /**
     * Get assigned on.
     * 
     * @return ResourceRef of assigned on.
     */
    public Reference getAssignedOn() {
        return assignedOn;
    }

    /**
     * The method is optional on create of properties, but not-allowed on update
     * of grant properties.
     * 
     * @param assignedOn
     *            ResourceRef where Grant is assigned on
     */
    public void setAssignedOn(final Reference assignedOn) {
        this.assignedOn = assignedOn;
    }
}
