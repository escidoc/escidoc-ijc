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
package de.escidoc.core.resources.om.context;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import de.escidoc.core.annotations.JiBX;
import de.escidoc.core.resources.NamedSubResource;

/**
 * AdminDescriptor.
 * 
 * @author SWA
 * 
 */
public class AdminDescriptor extends NamedSubResource {

    private static final String ADMIN_DESCRIPTOR_PATH = "/admin-descriptor";

    private Element content;

    /**
     * JiBX Constructor
     */
    @JiBX
    protected AdminDescriptor() {
    }

    /**
     * @param name
     */
    public AdminDescriptor(final String name) {
        super(name);
    }

    /**
     * @return the content
     */
    public Element getContent() {
        return content;
    }

    /**
     * Get content as String.
     * 
     * @return content
     * 
     * @throws TransformerException
     *             If an unrecoverable error occurs during the course of the
     *             transformation.
     */
    public String getContentAsString() throws TransformerException {

        TransformerFactory transFactory = TransformerFactory.newInstance();
        Transformer transformer = transFactory.newTransformer();
        StringWriter buffer = new StringWriter();
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
        transformer.transform(new DOMSource(this.content), new StreamResult(buffer));

        return buffer.toString();

    }

    /**
     * @param content
     *            the content to set
     */
    public void setContent(final Element content) {
        this.content = content;
    }

    /**
     * Set content as String.
     * 
     * @param xml
     *            The XML content
     * @throws ParserConfigurationException
     *             Thrown if setup of parser failed.
     * @throws IOException
     *             If an IO error at parser occurs
     * @throws SAXException
     *             If any parser error occurs
     */
    public void setContent(final String xml) throws ParserConfigurationException, SAXException, IOException {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        InputSource is = new InputSource(new StringReader(xml));
        Document d = builder.parse(is);

        this.content = d.getDocumentElement();
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.resources.NamedSubResource#getSubRecourcePath()
     */
    @Override
    protected String getSubRecourcePath() {
        return ADMIN_DESCRIPTOR_PATH;
    }
}