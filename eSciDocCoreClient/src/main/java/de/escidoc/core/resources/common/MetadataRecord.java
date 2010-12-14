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

import org.w3c.dom.Element;

import de.escidoc.core.resources.XLinkResource;

/**
 * MetadataRecord.
 * 
 * @author SWA
 * 
 */
public class MetadataRecord extends XLinkResource {

    private String name = null;

    private String mdType = null;

    private String schema = null;

    private Element content = null;

    /**
     * 
     */
    public MetadataRecord() {
    }

    /**
     * @return the record
     */
    public Element getContent() {
        return content;
    }

    /**
     * @param content
     *            the record to set
     */
    public void setContent(final Element content) {

        this.content = content;
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
     * @return the mdType
     */
    public String getMdType() {
        return mdType;
    }

    /**
     * @param mdType
     *            the mdType to set
     */
    public void setMdType(final String mdType) {
        this.mdType = mdType;
    }

    /**
     * @return the schema
     */
    public String getSchema() {
        return schema;
    }

    /**
     * @param schema
     *            the schema to set
     */
    public void setSchema(final String schema) {
        this.schema = schema;
    }

}
