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

import de.escidoc.core.common.exceptions.remote.system.SystemException;
import de.escidoc.core.resources.common.versionhistory.VersionHistory;

/**
 * 
 * @author SWA
 * 
 */
public class GenericVersionableResource extends GenericResource {

    private final VersionHistory versionHistory = null;

    private int versionNumber = 0;

    private final String latestVersionNumber = null;

    public GenericVersionableResource() {
    }

    /**
     * Generic Versionable Object.
     * 
     * @param id
     *            The id of the object in the repository.
     * @throws SystemException
     *             Thrown in case of internal error.
     */
    public GenericVersionableResource(final String id) throws SystemException {
        super();
        setVersionNumber(0); // split up version number
        setObjid(getObjectId(id));
    }

    /**
     * Get the Number of the version.
     * 
     * @return number of version
     */
    public int getVersionNumber() {
        return this.versionNumber;
    }

    /**
     * Extract the version number from id.
     * 
     * @param id
     */
    private void setVersionNumber(final int no) {
        this.versionNumber = no; // split ip version number
    }

    /**
     * Get the object id form a id with version number.
     * 
     * @return
     * @throws SystemException
     */
    private String getObjectId(final String id) throws SystemException {
        String objId = id; // remove version information
        return objId;
    }

    /**
     * Get the latest version number of the object.
     * 
     * @return latest version number
     */
    public String getLatestVersionNumber() {
        return this.latestVersionNumber;
    }

    /**
     * Get the Whole Object Versioning data set.
     * 
     * @return
     * @throws SystemException
     */
    public VersionHistory getVersionHistory() throws SystemException {
        return this.versionHistory;
    }

}
