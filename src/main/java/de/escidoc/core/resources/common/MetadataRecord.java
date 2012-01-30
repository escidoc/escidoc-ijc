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

import de.escidoc.core.annotations.JiBX;
import de.escidoc.core.resources.NamedSubResource;

/**
 * MetadataRecord.
 * 
 * @author SWA
 * 
 */
@JiBX
public class MetadataRecord extends NamedSubResource {

    private String mdType;

    private String schema;

    private Element content;

    private static final String MD_RECORD_PATH = "/md-record";

    /**
     * JiBX Constructor
     */
    @JiBX
    protected MetadataRecord() {
    }

    /**
     * @param name
     */
    public MetadataRecord(final String name) {
        super(name);
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

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.resources.NamedSubResource#getSubRecourcePath()
     */
    @Override
    protected String getSubRecourcePath() {
        return MD_RECORD_PATH;
    }
}