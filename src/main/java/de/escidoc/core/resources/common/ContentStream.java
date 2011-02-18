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

import de.escidoc.core.resources.ResourceType;
import de.escidoc.core.resources.om.GenericResource;

/**
 * Content Stream.
 * 
 * @author MSC, SWA
 * 
 */
public class ContentStream extends GenericResource {

    private boolean inherited = false;

    /**
     * required
     */
    private String mimeType = null;

    /**
     * required
     */
    private String name = null;

    /**
     * required
     */
    private String storage = null;

    /**
     * required
     */
    private String hrefOrBase64Content = null;

    /**
     * @param name
     * @param storage
     * @param content
     */
    public ContentStream(final String name, final String storage,
        final String mimeType) {

        setName(name);
        setStorage(storage);
        setMimeType(mimeType);
    }

    /**
     * JiBX constructor.
     */
    @SuppressWarnings("unused")
    private ContentStream() {

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
        if (mimeType == null)
            throw new IllegalArgumentException("mimeType must not be null.");
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
        if (name == null)
            throw new IllegalArgumentException("name must not be null.");
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
        if (storage == null)
            throw new IllegalArgumentException("storage must not be null.");
        this.storage = storage;
    }

    @Override
    public ResourceType getResourceType() {
        return null;
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

}
