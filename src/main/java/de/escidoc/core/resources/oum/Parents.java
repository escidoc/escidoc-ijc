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
package de.escidoc.core.resources.oum;

import org.joda.time.DateTime;

import de.escidoc.core.resources.ResourceList;
import de.escidoc.core.resources.interfaces.OptimisticLocking;

/**
 * Organizational Unit Parents.
 * 
 * @author SWA
 * 
 */
public class Parents extends ResourceList<Parent> implements OptimisticLocking {

    private DateTime lmd;

    private static final String OU_PARENTS_PATH = "/parents";

    /**
     * 
     */
    private static final long serialVersionUID = -962919764840581526L;

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.resources.interfaces.OptimisticLocking#
     * setLastModificationDate(org.joda.time.DateTime)
     */
    @Override
    public void setLastModificationDate(final DateTime lmd) {
        this.lmd = lmd;
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.resources.interfaces.OptimisticLocking#
     * getLastModificationDate()
     */
    @Override
    public DateTime getLastModificationDate() {
        return this.lmd;
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.resources.XLinkResourceList#getListXLinkPath()
     */
    @Override
    protected String getListXLinkPath() {
        return OU_PARENTS_PATH;
    }
}