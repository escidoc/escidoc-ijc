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

import java.util.Collection;
import java.util.LinkedList;

/**
 * Request Results.
 * 
 * @author SWA
 * 
 */
public class RequestsResults {

    private Collection<Result> results = new LinkedList<Result>();

    /**
     * Get Results of Request.
     * 
     * @return Collection of request results.
     */
    public Collection<Result> getResults() {
        return this.results;
    }

    /**
     * Set regest results.
     * 
     * @param requestResults
     *            Request results.
     */
    public void setResults(final Collection<Result> requestResults) {
        this.results = requestResults;
    }
}
