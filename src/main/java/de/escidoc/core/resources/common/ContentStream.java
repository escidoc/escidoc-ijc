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
package de.escidoc.core.resources.common;

import static de.escidoc.core.common.Precondition.checkNotNull;

import org.w3c.dom.Element;

import de.escidoc.core.annotations.JiBX;
import de.escidoc.core.resources.NamedSubResource;
import de.escidoc.core.resources.XLinkType;
import de.escidoc.core.resources.om.item.StorageType;

/**
 * Content Stream.
 * 
 * @author MSC, SWA
 * 
 */
public class ContentStream extends NamedSubResource {

    private String mimeType;

    private StorageType storageType;

    private Element content;

    private boolean inherited = false;

    private static final String CONTENT_STREAM_PATH = "/content-stream";

    /**
     * @param name
     * @param storage
     * @param mimeType
     */
    public ContentStream(final String name, final StorageType storageType, final String mimeType) {
        super(name);
        setStorageType(storageType);
        setMimeType(mimeType);
    }

    /**
     * JiBX Constructor
     */
    @JiBX
    protected ContentStream() {
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

    /**
     * @return the mimeType
     */
    public String getMimeType() {
        return mimeType;
    }

    /**
     * @param mimeType
     *            the mimeType to set
     */
    public void setMimeType(final String mimeType) {
        this.mimeType = checkNotNull(mimeType);
    }

    /**
     * @return the StorageType
     */
    public StorageType getStorageType() {
        return storageType;
    }

    /**
     * @param storage
     *            the storage to set
     */
    public void setStorageType(final StorageType storageType) {
        this.storageType = checkNotNull(storageType);
    }

    /**
     * @param inherited
     *            the inherited to set
     */
    public void setInherited(final boolean inherited) {
        this.inherited = inherited;
    }

    /**
     * @return the inherited
     */
    public boolean isInherited() {
        return inherited;
    }

    /**
     * @param hrefOrBase64Content
     *            the hrefOrBase64Content to set
     */
    public void setContent(final Element content) {
        this.content = content;
    }

    /**
     * @return the content
     */
    public Element getContent() {
        return content;
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.resources.NamedSubResource#getSubRecourcePath()
     */
    @Override
    protected String getSubRecourcePath() {
        return CONTENT_STREAM_PATH;
    }
}