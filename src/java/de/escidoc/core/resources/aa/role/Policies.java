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

/**
 * Policies.
 * 
 * @author ?, SWA
 * 
 */
public class Policies {

    private String policy;

    private String policySet;

    /**
     * Get policy.
     * 
     * @return policy
     */
    public String getPolicy() {
        return policy;
    }

    /**
     * Set policy.
     * 
     * @param policy
     *            Policy
     */
    public void setPolicy(final String policy) {
        this.policy = policy;
    }

    /**
     * Get policy set (as String).
     * 
     * @return policy set
     */
    public String getPolicySet() {
        return policySet;
    }

    /**
     * Set policy set.
     * 
     * @param policySet
     *            Set of policies
     */
    public void setPolicySet(final String policySet) {
        this.policySet = policySet;
    }

}
