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

import de.escidoc.core.resources.common.versionhistory.VersionHistory;

/**
 * 
 * @author SWA
 * 
 */
public abstract class VersionableResource extends GenericResource {

    private final VersionHistory versionHistory = null;

    private int versionNumber = 0;

    private boolean hasVersionNumber = false;

    /**
     * 
     */
    public VersionableResource() {
        super();
    }

    /**
     * @param objid
     * @param href
     * @param title
     */
    public VersionableResource(final String objid, final String href,
        final String title) {
        super(objid, href, title);
    }

    /**
     * @param href
     * @param title
     */
    public VersionableResource(final String href, final String title) {
        super(href, title);
    }

    /**
     * @param objid
     */
    public VersionableResource(final String objid) {
        super(objid);
    }

    /**
     * Get the number of the version.
     * 
     * @return number of version
     */
    public int getVersionNumber() {
        return this.versionNumber;
    }

    /**
     * Get the version history of the resource.
     * 
     * @return version history
     */
    public VersionHistory getVersionHistory() {
        return this.versionHistory;
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
     * @see de.escidoc.core.resources.Resource#setObjid(java.lang.String)
     */
    @Override
    public void setObjid(final String objid) {
        if (objid == null) {
            super.setObjid(null);
        }
        else {
            String[] v = objid.split(":");
            if (v.length < 2)
                throw new IllegalArgumentException(
                    "Illegal argument for eSciDoc Objid: " + objid);

            super.setObjid(v[0] + ":" + v[1]);

            if (v.length > 2) {
                this.versionNumber = Integer.valueOf(v[2]);
                this.hasVersionNumber = true;
            }
        }
    }
}