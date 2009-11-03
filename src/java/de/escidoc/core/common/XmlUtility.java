package de.escidoc.core.common;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.Logger;
import org.apache.xerces.dom.AttrImpl;
import org.apache.xml.serialize.OutputFormat;
import org.apache.xml.serialize.XMLSerializer;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.bootstrap.DOMImplementationRegistry;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSOutput;
import org.w3c.dom.ls.LSSerializer;
import org.xml.sax.SAXException;

/**
 * 
 * @author SWA
 * 
 */
public class XmlUtility {

    private static final Logger LOGGER = Logger.getLogger(XmlUtility.class);

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

    /**
     * Serialize the given Dom Object to a String.
     * 
     * @param xml
     *            The Xml Node to serialize.
     * @param omitXMLDeclaration
     *            Indicates if XML declaration will be omitted.
     * @return The String representation of the Xml Node.
     * @throws IOException
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws ClassNotFoundException
     * @throws ClassCastException
     */
    public static String toString(
        final Node xml, final boolean omitXMLDeclaration) throws IOException,
        ClassCastException, ClassNotFoundException, InstantiationException,
        IllegalAccessException {

        String result = new String();
        if (xml instanceof AttrImpl) {
            result = xml.getTextContent();
        }
        else if (xml instanceof Document) {
            StringWriter stringOut = new StringWriter();
            // format
            OutputFormat format = new OutputFormat((Document) xml);
            format.setIndenting(true);
            format.setPreserveSpace(true);
            format.setOmitXMLDeclaration(omitXMLDeclaration);
            format.setEncoding(Constants.DEFAULT_CHARSET);
            // serialize
            XMLSerializer serial = new XMLSerializer(stringOut, format);
            serial.asDOMSerializer();

            serial.serialize((Document) xml);
            result = stringOut.toString();
        }
        else {
            DOMImplementationRegistry registry =
                DOMImplementationRegistry.newInstance();
            DOMImplementationLS impl =
                (DOMImplementationLS) registry.getDOMImplementation("LS");
            LSOutput lsOutput = impl.createLSOutput();
            lsOutput.setEncoding(Constants.DEFAULT_CHARSET);

            ByteArrayOutputStream os = new ByteArrayOutputStream();
            lsOutput.setByteStream(os);
            LSSerializer writer = impl.createLSSerializer();
            writer.write(xml, lsOutput);
            result =
                ((ByteArrayOutputStream) lsOutput.getByteStream())
                    .toString(Constants.DEFAULT_CHARSET);
            if ((omitXMLDeclaration) && (result.indexOf("?>") != -1)) {
                result = result.substring(result.indexOf("?>") + 2);
            }
        }
        return result;
    }

}
