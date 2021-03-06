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
package de.escidoc.core.resources.aa.role;

import java.util.LinkedList;
import java.util.List;

import de.escidoc.core.annotations.JiBX;

/**
 * AA Scope.
 * 
 * @author SWA
 * 
 */
@JiBX
public class Scope {

    private List<ScopeDef> scopeDefinitions;

    private boolean unlimited = false;

    /**
     * Get scope definitions.
     * 
     * @return scope definitions
     */
    public List<ScopeDef> getScopeDefinitions() {
        if (scopeDefinitions == null) {
            scopeDefinitions = new LinkedList<ScopeDef>();
        }
        return scopeDefinitions;
    }

    /**
     * Set scope definitions.
     * 
     * @param scopeDefinitions
     *            Collection of scope definitions
     */
    public void setScopeDefinitions(final List<ScopeDef> scopeDefinitions) {
        this.scopeDefinitions = scopeDefinitions;
    }

    /**
     * Get limitation.
     * 
     * @return true if un-limited, false otherwise
     */
    public boolean isUnlimited() {
        return unlimited;
    }

    /**
     * Set limitation.
     * 
     * @param unlimited
     *            true if unlimited, false otherwise
     */
    public void setUnlimited(final boolean unlimited) {
        this.unlimited = unlimited;
    }
}