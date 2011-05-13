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
package de.escidoc.core.resources;

/**
 * 
 * @author SWA
 * 
 */
public abstract class VersionableResource extends GenericResource {

    private static final String OBJID_PATTERN = "([^:]+:[^:]+)(:\\d+)?";

    /**
     * 
     */
    public VersionableResource() {
        super();
    }

    /**
     * Get the number of the version.
     * 
     * @return number of version
     */
    public int getVersionNumber() {
        return this.identifier == null ? 0 : ((VersionedIdentifier) identifier).getVersionNumber();
    }

    /**
     * @return <code>true</code> if and only if this resource has a version
     *         number.
     */
    public boolean hasVersionNumber() {
        return this.identifier == null ? false : ((VersionedIdentifier) identifier).hasVersionNumber();
    }

    /**
     * @return the objid without its version extension.
     */
    public String getOriginObjid() {
        if (this.identifier == null || this.identifier.getObjid() == null) {
            return null;
        }
        return identifier.getObjid().replaceAll(OBJID_PATTERN, "$1");
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.resources.Resource#getIdentifierInstance()
     */
    @Override
    protected Identifier getIdentifierInstance() {
        return this.new VersionedIdentifier();
    }

    /**
     * @author Marko Voß
     * 
     */
    public class VersionedIdentifier extends Identifier {

        private int versionNumber = 0;

        private boolean hasVersionNumber = false;

        /**
         * @param objid
         *            the objid to set
         */
        @Override
        public void setObjid(final String objid) {
            super.setObjid(objid);
            if (objid != null) {
                String[] v = objid.split(":");
                if (v.length > 2) {
                    this.versionNumber = Integer.valueOf(v[2]);
                    this.hasVersionNumber = true;
                }
            }
        }

        /**
         * @return the versionNumber
         */
        public int getVersionNumber() {
            return versionNumber;
        }

        /**
         * @return the hasVersionNumber
         */
        public boolean hasVersionNumber() {
            return hasVersionNumber;
        }

        /*
         * (non-Javadoc)
         * 
         * @see java.lang.Object#hashCode()
         */
        @Override
        public int hashCode() {
            final int prime = 31;
            int result = super.hashCode();
            result = prime * result + (hasVersionNumber ? 1231 : 1237);
            result = prime * result + versionNumber;
            return result;
        }

        /*
         * (non-Javadoc)
         * 
         * @see java.lang.Object#equals(java.lang.Object)
         */
        @Override
        public boolean equals(final Object obj) {
            if (this == obj)
                return true;
            if (!super.equals(obj))
                return false;
            if (getClass() != obj.getClass())
                return false;
            VersionedIdentifier other = (VersionedIdentifier) obj;
            if (hasVersionNumber != other.hasVersionNumber)
                return false;
            if (versionNumber != other.versionNumber)
                return false;
            return true;
        }
    }
}