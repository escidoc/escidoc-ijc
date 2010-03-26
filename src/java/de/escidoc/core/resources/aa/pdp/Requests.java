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

import org.w3c.dom.Element;

/**
 * PDP requests.
 * 
 * @author ?
 * 
 */
public class Requests {

    private final Collection<Element> requests = new LinkedList<Element>();

    /**
     * Get collection of requests.
     * 
     * @return Requests collection
     */
    public Collection<Element> getRequests() {
        return requests;
    }

    /**
     * Add a Request (as DOM Element).
     * 
     * @param request
     *            Request as DOM element.
     */
    public void addRequest(final Element request) {
        this.requests.add(request);
    }
}
