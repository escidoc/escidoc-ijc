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
package de.escidoc.core.resources.om.item;

import org.w3c.dom.Element;

import de.escidoc.core.resources.XLinkResource;

/**
 * Item Content.
 * 
 * @author SWA
 * 
 */
public abstract class Content extends XLinkResource {

    private StorageType storageType;

    private Element content;

    /**
     * Content.
     */
    public Content() {

    }

    /**
     * @return the storage
     */
    public StorageType getStorageType() {
        return storageType;
    }

    /**
     * @param storage
     *            the storage to set
     */
    public void setStorageType(final StorageType storageType) {
        this.storageType = storageType;
    }

    /**
     * 
     * @return
     */
    public Element getContent() {
        return content;
    }

    /**
     * 
     * @param content
     */
    public void setContent(final Element content) {
        this.content = content;
    }
}