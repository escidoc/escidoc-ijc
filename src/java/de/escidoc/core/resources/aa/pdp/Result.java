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
package de.escidoc.core.resources.aa.pdp;

import com.sun.xacml.ctx.ResponseCtx;

/**
 * PDP eSciDoc result.
 * 
 * FIXME: Invalid structure: JIRA - INFR-1006
 * 
 * @author ?
 * 
 */
public class Result {

    /**
     * The origin response
     */
    private ResponseCtx responseCtx;

    /**
     * The interpreted decision.
     */
    private Decision interpretedDecision;

    /**
     * Get interpreted decision.
     * 
     * The PDP evaluation may return more than the decisions of type
     * <i>permit</i> and <i>deny</i>. Those other decisions can be interpreted
     * in different ways. To point out how to interpret these decisions of the
     * origin response context, this method will return the eSciDoc
     * interpretation of these decisions.
     * 
     * @return Decision
     */
    public Decision getInterpretedDecision() {
        return interpretedDecision;
    }

    /**
     * Set the interpreted decision.
     * 
     * @param decision
     *            Decision
     */
    public void setInterpretedDecision(final Decision decision) {
        this.interpretedDecision = decision;
    }

    /**
     * 
     * @return the origin response
     */
    public ResponseCtx getResponseCtx() {
        return responseCtx;
    }

    /**
     * 
     * @param responceCtx
     */
    public void setResponseCtx(ResponseCtx responseCtx) {
        this.responseCtx = responseCtx;
    }
}
