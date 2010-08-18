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
 * Copyright 2006-2010 Fachinformationszentrum Karlsruhe Gesellschaft
 * fuer wissenschaftlich-technische Information mbH and Max-Planck-
 * Gesellschaft zur Foerderung der Wissenschaft e.V.  
 * All rights reserved.  Use is subject to license terms.
 */
package de.escidoc.core.test.client.util;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import de.escidoc.core.common.Constants;

import java.io.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;

/**
 * Utilities for XML.
 * 
 * @author SWA
 * 
 */
public class XmlUtil {

    /**
     * Convert a XML Document to String.
     * 
     * @param node
     *            The Node
     * @return String
     * 
     * @throws TransformerException
     */
    public static String xmlToString(final Node node)
        throws TransformerException {

        Source source = new DOMSource(node);
        StringWriter stringWriter = new StringWriter();
        Result result = new StreamResult(stringWriter);
        
        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer transformer = factory.newTransformer();
        transformer.transform(source, result);
        
        return stringWriter.getBuffer().toString();
    }
    
    /**
     * Get a DOM object of the XML string.
     * 
     * @param xml
     *            The XML as String.
     * @return The DOM document representation of the XML.
     * 
     * @throws ParserConfigurationException
     * @throws UnsupportedEncodingException
     * @throws SAXException
     * @throws IOException
     */
    public static Document getDocument(final String xml)
        throws ParserConfigurationException, UnsupportedEncodingException,
        SAXException, IOException {

        DocumentBuilderFactory docBuilderFactory =
            DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
        Document result =
            docBuilder.parse(new ByteArrayInputStream(xml
                .getBytes(Constants.DEFAULT_CHARSET)));
        return result;
    }

}