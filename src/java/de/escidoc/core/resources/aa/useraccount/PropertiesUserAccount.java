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
package de.escidoc.core.resources.aa.useraccount;

import de.escidoc.core.resources.ResourceRef;
import de.escidoc.core.resources.common.properties.Properties;

public class PropertiesUserAccount extends Properties {

    private String loginName;

    private ResourceRef modifiedBy;

    private boolean active;

    /**
     * @return the loginName
     */
    public String getLoginName() {
        return this.loginName;
    }

    /**
     * 
     * @param loginName
     */
    public void setLoginName(final String loginName) {
        this.loginName = loginName;
    }

    /**
     * 
     * @return
     */
    public boolean isActive() {
        return this.active;
    }

    /**
     * @return the createdBy
     */
    public ResourceRef getModifiedBy() {
        return this.modifiedBy;
    }
}
