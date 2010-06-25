package de.escidoc.core.test.client.util;

import org.w3c.dom.*;
import java.io.*;
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
}