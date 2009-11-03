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
package de.escidoc.core.resources.om.context;

import org.w3c.dom.Element;



/**
 * AdminDescriptor.
 * 
 * @author SWA
 * 
 */
public class AdminDescriptor {

    private String name = null;

    private Element content = null;

    public AdminDescriptor() {
    }

    /**
     * @return the admin descriptor name
     */
    public String getName() {
        return this.name;
    }

    /**
     * @param name
     *            the admin descriptor name
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * @return the content
     */
    public Element getContent() {
        return content;
    }

    /**
     * @param content
     *            the content to set
     */
    public void setContent(final Element content) {
        this.content = content;
    }

}
