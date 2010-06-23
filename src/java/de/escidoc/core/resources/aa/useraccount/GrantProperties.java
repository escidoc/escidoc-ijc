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

import de.escidoc.core.resources.ResourceRef;

/**
 * Properties of Grant.
 * 
 * @author ?, SWA
 * 
 */
public class GrantProperties {

    private DateTime creationDate = null;

    private ResourceRef createdBy;

    private ResourceRef modifiedBy;

    private GrantedTo grantedTo;

    private String revocationDate;

    private ResourceRef revokedBy;

    private String grantRemark;

    private String revocationRemark;

    private ResourceRef role;

    private ResourceRef assignedOn;

    /**
     * Properties for Grant.
     */
    public GrantProperties() {

    }

    /**
     * Get creation date.
     * 
     * @return creation date
     */
    public DateTime getCreationDate() {
        return this.creationDate;
    }

    /**
     * @return the creationDate
     */
    public String getCreationDateAsString() {
        if (this.creationDate != null) {
            return this.creationDate.toString();
        }

        return null;
    }

    /**
     * @param creationDate
     *            the creationDate to set
     */
    public void setCreationDate(final DateTime creationDate) {

        this.creationDate = creationDate;
    }

    /**
     * @param creationDate
     *            the creationDate to set
     */
    public void setCreationDateAsString(final String creationDate) {
        if (creationDate == null) {
            this.creationDate = null;
        }
        else {
            this.creationDate = new DateTime(creationDate);
        }
    }

    /**
     * @return the createdBy
     */
    public ResourceRef getCreatedBy() {
        return this.createdBy;
    }

    /**
     * @param createdBy
     *            the createdBy to set
     */
    public void setCreatedBy(final ResourceRef createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * @return the modifedBy
     */
    public ResourceRef getModifiedBy() {
        return this.modifiedBy;
    }

    /**
     * @param modifiedBy
     *            the createdBy to set
     */
    public void setModifiedBy(final ResourceRef modifiedBy) {
        this.modifiedBy = modifiedBy;
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
    // FIXME use DateTime
    public String getRevocationDate() {
        return revocationDate;
    }

    /**
     * Set revocation date.
     * 
     * @param revocationDate
     *            Revocation date as String
     */
    public void setRevocationDate(final String revocationDate) {
        this.revocationDate = revocationDate;
    }

    /**
     * Get revoked by.
     * 
     * @return id of revoker
     */
    public ResourceRef getRevokedBy() {
        return revokedBy;
    }

    /**
     * Set id of revoker.
     * 
     * @param revokedBy
     *            ResourcRef of revoker
     */
    public void setRevokedBy(final ResourceRef revokedBy) {
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
    public ResourceRef getRole() {
        return role;
    }

    /**
     * The method is mandatory on create of properties, but not-allowed on
     * update of grant properties.
     * 
     * @param role
     *            Role
     */
    public void setRole(final ResourceRef role) {
        this.role = role;
    }

    /**
     * Get assigned on.
     * 
     * @return ResourceRef of assigned on.
     */
    public ResourceRef getAssignedOn() {
        return assignedOn;
    }

    /**
     * The method is optional on create of properties, but not-allowed on update
     * of grant properties.
     * 
     * @param assignedOn
     *            ResourceRef where Grant is assigned on
     */
    public void setAssignedOn(final ResourceRef assignedOn) {
        this.assignedOn = assignedOn;
    }
}
