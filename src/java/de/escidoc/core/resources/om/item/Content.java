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

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import de.escidoc.core.resources.ResourceRef;

/**
 * Item Content.
 * 
 * @author SWA
 * 
 */
public class Content extends ResourceRef {

    private String storage;

    /**
     * Content.
     */
    public Content() {
    	
    }

    /**
     * 
     * @param id
     *            The resource reference.
     */
    public Content(final ResourceRef id) {
        super(id.getObjid(), id.getXLinkHref(), id.getXLinkTitle());
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
     * Get InputStream of the binary content.
     * 
     * @return binary content InputStream
     * @throws IOException
     *             Thrown if storage URL is invalid or if the data access and
     *             transport fails.
     */
    protected InputStream getBinaryContent() throws IOException {

        URL url = new URL(this.storage);
        return url.openStream();
    }
}
