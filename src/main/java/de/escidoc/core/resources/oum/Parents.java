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

import de.escidoc.core.resources.Resource;
import de.escidoc.core.resources.Resource.RESOURCE_TYPE;
import de.escidoc.core.resources.XLinkAutonomous;
import de.escidoc.core.resources.XLinkResourceList;
import de.escidoc.core.resources.interfaces.OptimisticLocking;

/**
 * Organizational Unit Parents.
 * 
 * @author SWA
 * 
 */
public class Parents extends XLinkResourceList<Parent>
    implements OptimisticLocking, XLinkAutonomous {

    private DateTime lmd;

    /**
     * 
     */
    private static final long serialVersionUID = -962919764840581526L;

    @Override
    public void setLastModificationDate(final DateTime lmd) {
        this.lmd = lmd;
    }

    @Override
    public DateTime getLastModificationDate() {
        return this.lmd;
    }

    @Override
    public void genXLink() {
        for (Parent parent : this) {
            if (parent.getXLinkHref() == null && parent.getObjid() != null) {
                parent.setXLinkHref(Resource.RESOURCE_URL_MAP
                    .get(RESOURCE_TYPE.OrganizationalUnit)
                    + "/"
                    + parent.getObjid());
            }
        }
    }
}
