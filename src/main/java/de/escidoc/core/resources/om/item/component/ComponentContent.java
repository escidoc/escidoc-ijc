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

import org.apache.commons.codec.binary.Base64;

import de.escidoc.core.resources.XLinkType;
import de.escidoc.core.resources.om.item.Content;

/**
 * Content of Item component.
 * 
 * @author ROF, SWA
 * 
 */
public class ComponentContent extends Content {

    private String base64EncodedContent;

    /**
     * 
     * @param base64EncodedContent
     */
    public void setBase64EncodedContent(final String base64EncodedContent) {
        this.base64EncodedContent = base64EncodedContent;
    }

    /**
     * 
     * @return
     */
    public String getBase64EncodedContent() {
        return base64EncodedContent;
    }

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

    /**
     * Encode binary content.
     * 
     * @param inlineContent
     * @return base64 encoded content as String
     */
    public static final String encodeBinaryContent(final String inlineContent) {
        return Base64.encodeBase64String(inlineContent.getBytes());
    }

    /**
     * 
     * @param inlineContent
     * @return base64 encoded content as String
     */
    public static final byte[] encodeBinaryContent(final byte[] inlineContent) {
        return Base64.encodeBase64(inlineContent);
    }

    /**
     * Decode binary content.
     * 
     * @param base64EncodedContent
     * @return base64 decoded content as String
     */
    public static final String decodeBinaryContent(final String base64EncodedContent) {
        return new String(Base64.decodeBase64(base64EncodedContent.getBytes()));
    }

    /**
     * 
     * @param base64EncodedContent
     * @return base64 decoded content as byte[]
     */
    public static final byte[] decodeBinaryContent(final byte[] base64EncodedContent) {
        return Base64.decodeBase64(base64EncodedContent);
    }
}