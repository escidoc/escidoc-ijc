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
package de.escidoc.core.resources.cmm;

import java.net.URI;

import de.escidoc.core.common.jibx.CustomConverter;
import de.escidoc.core.resources.XLinkResource;

/**
 * Metadata Record definition of Content Model.
 * 
 * @author SWA
 * 
 */
public class MetadataRecordDefinition extends XLinkResource {

    private String name;

    private URI schema;

    public MetadataRecordDefinition() {

    }

    /**
     * Set name.
     * 
     * @param name
     *            The name
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * Get name.
     * 
     * @return The name
     */
    public String getName() {
        return name;
    }

    /**
     * Set schema URI.
     * 
     * @param schema
     *            The schema URI
     */
    public void setSchema(final URI schema) {
        this.schema = schema;
    }

    /**
     * Get schema URI.
     * 
     * @return The schema URI.
     */
    public URI getSchema() {
        return schema;
    }

    @Override
	public String getXLinkHref() {
		return CustomConverter.serializeURI(getSchema());
	}

	@Override
	public void setXLinkHref(String href) {
		setSchema(CustomConverter.deserializeURI(href));
	}
}