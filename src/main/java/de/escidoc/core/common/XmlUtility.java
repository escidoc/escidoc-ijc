package de.escidoc.core.common;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

/**
 * 
 * @author SWA
 * 
 */
public class XmlUtility {

    public static final String XML_HEADER = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";

    public static final String AMPERSAND = "&";

    public static final String ESC_AMPERSAND = "&amp;";

    public static final String LESS_THAN = "<";

    public static final String ESC_LESS_THAN = "&lt;";

    public static final String GREATER_THAN = ">";

    public static final String ESC_GREATER_THAN = "&gt;";

    public static final String APOS = "'";

    public static final String ESC_APOS = "&apos;";

    public static final String QUOT = "\"";

    public static final String ESC_QUOT = "&quot;";

    private static final Pattern PATTERN_ESCAPE_NEEDED =
        Pattern.compile(AMPERSAND + "|" + LESS_THAN + "|" + GREATER_THAN + "|" + QUOT + "|" + APOS);

    private static final Pattern PATTERN_UNESCAPE_NEEDED =
        Pattern.compile(ESC_AMPERSAND + "|" + ESC_LESS_THAN + "|" + ESC_GREATER_THAN + "|" + ESC_QUOT + "|" + ESC_APOS);

    private static final Pattern PATTERN_AMPERSAND = Pattern.compile("(" + AMPERSAND + ")");

    private static final Pattern PATTERN_LESS_THAN = Pattern.compile("(" + LESS_THAN + ")");

    private static final Pattern PATTERN_GREATER_THAN = Pattern.compile("(" + GREATER_THAN + ")");

    private static final Pattern PATTERN_QUOT = Pattern.compile("(" + QUOT + ")");

    private static final Pattern PATTERN_APOS = Pattern.compile("(" + APOS + ")");

    private static final Pattern PATTERN_ESC_AMPERSAND = Pattern.compile("(" + ESC_AMPERSAND + ")");

    private static final Pattern PATTERN_ESC_LESS_THAN = Pattern.compile("(" + ESC_LESS_THAN + ")");

    private static final Pattern PATTERN_ESC_GREATER_THAN = Pattern.compile("(" + ESC_GREATER_THAN + ")");

    private static final Pattern PATTERN_ESC_QUOT = Pattern.compile("(" + ESC_QUOT + ")");

    private static final Pattern PATTERN_ESC_APOS = Pattern.compile("(" + ESC_APOS + ")");

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
    public static Document getDocument(final String xml) throws ParserConfigurationException,
        UnsupportedEncodingException, SAXException, IOException {

        return getDocument(xml, false);
    }

    /**
     * @param xml
     * @param namespaceAware
     * @return
     * @throws ParserConfigurationException
     * @throws UnsupportedEncodingException
     * @throws SAXException
     * @throws IOException
     */
    public static Document getDocument(final String xml, final boolean namespaceAware)
        throws ParserConfigurationException, UnsupportedEncodingException, SAXException, IOException {

        DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
        docBuilderFactory.setFeature("http://xml.org/sax/features/namespaces", namespaceAware);
        DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
        Document result = docBuilder.parse(new ByteArrayInputStream(xml.getBytes(Constants.DEFAULT_CHARSET)));
        return result;
    }

    /**
     * Convert a XML Document to String.
     * 
     * @param node
     *            The Node
     * @return String
     * 
     * @throws TransformerException
     */
    public static String xmlToString(final Node node, final boolean omitXMLDeclaration) throws TransformerException {

        Source source = new DOMSource(node);
        StringWriter stringWriter = new StringWriter();
        Result result = new StreamResult(stringWriter);

        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer transformer = factory.newTransformer();
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, omitXMLDeclaration ? "yes" : "no");
        transformer.transform(source, result);

        return stringWriter.getBuffer().toString();
    }

    /**
     * Convert a XML Document to String omitting XML declaration.
     * 
     * @param node
     * @return
     * @throws TransformerException
     */
    public static String xmlToString(final Node node) throws TransformerException {
        return xmlToString(node, true);
    }

    /**
     * Replace forbidden characters in XML content with their escape sequence.<br/>
     * This method escapes &, <, and > in attributes and text content. In
     * attributes, it additionally escapes " and '.
     * 
     * @param xmlText
     *            The XML text.
     * @return The resulting text with escaped characters.
     */
    public static final String escapeForbiddenXmlCharacters(final String xmlText) {

        String result = xmlText;
        if (result != null) {
            if (PATTERN_ESCAPE_NEEDED.matcher(result).find()) {
                result = PATTERN_AMPERSAND.matcher(result).replaceAll(ESC_AMPERSAND);
                result = PATTERN_LESS_THAN.matcher(result).replaceAll(ESC_LESS_THAN);
                result = PATTERN_GREATER_THAN.matcher(result).replaceAll(ESC_GREATER_THAN);
                result = PATTERN_QUOT.matcher(result).replaceAll(ESC_QUOT);
                result = PATTERN_APOS.matcher(result).replaceAll(ESC_APOS);
            }
        }

        return result;
    }

    /**
     * Replace all escape sequences for forbidden characters with their
     * readable.
     * 
     * @param xmlText
     *            The XML text with escape sequences.
     * @param isAttribute
     *            Indicates if this is an attribute.
     * @return The resulting text with unescaped characters.
     */
    public static String unescapeForbiddenXmlCharacters(final String xmlText) {

        String result = xmlText;
        if (result != null && PATTERN_UNESCAPE_NEEDED.matcher(result).find()) {
            result = PATTERN_ESC_LESS_THAN.matcher(result).replaceAll(LESS_THAN);
            result = PATTERN_ESC_GREATER_THAN.matcher(result).replaceAll(GREATER_THAN);
            result = PATTERN_ESC_QUOT.matcher(result).replaceAll(QUOT);
            result = PATTERN_ESC_APOS.matcher(result).replaceAll(APOS);
            result = PATTERN_ESC_AMPERSAND.matcher(result).replaceAll(AMPERSAND);
        }
        return result;
    }
}
