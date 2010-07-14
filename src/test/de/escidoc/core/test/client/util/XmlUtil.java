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