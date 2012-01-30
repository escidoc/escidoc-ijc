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
package de.escidoc.core.resources.om.item.component;

import org.w3c.dom.Element;

import de.escidoc.core.resources.XLinkType;
import de.escidoc.core.resources.om.item.Content;

/**
 * Content of Item component.
 * 
 * @author ROF, SWA
 * 
 */
public class ComponentContent extends Content {

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.resources.XLinkResource#setXLinkHref(java.lang.String)
     */
    @Override
    public void setXLinkHref(final String xLinkHref) {
        super.setXLinkHref(xLinkHref);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.resources.XLinkResource#setXLinkType(de.escidoc.core.
     * resources.XLinkType)
     */
    @Override
    public void setXLinkType(final XLinkType type) {
        super.setXLinkType(type);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.resources.XLinkResource#setXLinkTitle(java.lang.String)
     */
    @Override
    public void setXLinkTitle(final String title) {
        super.setXLinkTitle(title);
    }

    /**
     * @see de.escidoc.core.resources.om.item.Content#setContent(Element)
     * @deprecated See INFR-1377
     */
    @Override
    @Deprecated
    public void setContent(final Element content) {
        super.setContent(content);
    }

    /**
     * @see de.escidoc.core.resources.om.item.Content#getContent()
     * @deprecated See INFR-1377
     */
    @Deprecated
    @Override
    public Element getContent() {
        return super.getContent();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.resources.XLinkAutonomous#generateXLinkHref(java.lang
     * .String)
     */
    @Override
    public void generateXLinkHref(final String parentPath) {
        /*
         * The xlink:href has to be set either by the user to use a reference
         * instead of a base64 encoded content for create/update methods of the
         * item resource or by the infrastructure in order to return the href
         * for the content for retrieval.
         */
    }
}