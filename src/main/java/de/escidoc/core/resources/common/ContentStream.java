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
import de.escidoc.core.annotations.JiBX;
import de.escidoc.core.resources.GenericResource;
import de.escidoc.core.resources.ResourceType;

/**
 * Content Stream.
 * 
 * @author MSC, SWA
 * 
 */
public class ContentStream extends GenericResource {

    private String mimeType;

    /**
     * Max. 64 characters.
     */
    private String name;

    private String storage;

    private String hrefOrBase64Content;

    private boolean inherited = false;

    /**
     * 
     */
    public ContentStream(final String name, final String storage,
        final String mimeType) {
        super();
        init(name, storage, mimeType);
    }

    /**
     * @param objid
     * @param href
     * @param title
     */
    public ContentStream(final String objid, final String href,
        final String title, final String name, final String storage,
        final String mimeType) {
        super(objid, href, title);
        init(name, storage, mimeType);
    }

    /**
     * @param href
     * @param title
     */
    public ContentStream(final String href, final String title,
        final String name, final String storage, final String mimeType) {
        super(href, title);
        init(name, storage, mimeType);
    }

    /**
     * @param objid
     */
    public ContentStream(final String objid, final String name,
        final String storage, final String mimeType) {
        super(objid);
        init(name, storage, mimeType);
    }

    @JiBX
    @SuppressWarnings("unused")
    private ContentStream() {

    }

    /**
     * @param name
     * @param storage
     * @param mimeType
     */
    private void init(
        final String name, final String storage, final String mimeType) {
        checkNotNull(name);
        checkNotNull(storage);
        checkNotNull(mimeType);
        this.name = name;
        this.storage = storage;
        this.mimeType = mimeType;
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
        this.mimeType = mimeType;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * @return the storage
     */
    public String getStorage() {
        return storage;
    }

    /**
     * @param storage
     *            the storage to set
     */
    public void setStorage(final String storage) {
        this.storage = storage;
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
    public void setHrefOrBase64Content(final String hrefOrBase64Content) {
        this.hrefOrBase64Content = hrefOrBase64Content;
    }

    /**
     * @return the hrefOrBase64Content
     */
    public String getHrefOrBase64Content() {
        return hrefOrBase64Content;
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.resources.Resource#getResourceType()
     */
    @Override
    public ResourceType getResourceType() {
        return null;
    }
}