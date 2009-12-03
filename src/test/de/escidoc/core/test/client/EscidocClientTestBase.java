package de.escidoc.core.test.client;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.transform.TransformerException;

import junit.framework.TestCase;

import org.apache.xpath.XPathAPI;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import de.escidoc.core.common.XmlUtility;
import de.escidoc.core.common.jibx.Marshaller;
import de.escidoc.core.resources.common.MetadataRecord;
import de.escidoc.core.resources.common.MetadataRecords;

/**
 * Utiltiy methods for Tests.
 * 
 * @author SWA
 * 
 */
public class EscidocClientTestBase extends TestCase {

    private static AppLogger log =
        new AppLogger(EscidocClientTestBase.class.getName());

    public static final String DEFAULT_INFRASTRUCTURE_HOST = "localhost";

    public static final String DEFAULT_INFRASTRUCTURE_PORT = "8080";

    public static final String DEFAULT_INFRASTRUCTURE_PATH = "";

    public static final String DEFAULT_SERVICE_URL =
        "http://" + DEFAULT_INFRASTRUCTURE_HOST + ":"
            + DEFAULT_INFRASTRUCTURE_PORT + DEFAULT_INFRASTRUCTURE_PATH;

    /*
     * Username/Password logins
     */
    public static final String SYSTEM_ADMIN_USER = "sysadmin";

    public static final String SYSTEM_ADMIN_PASSWORD = "eSciDoc";

    /*
     * Login Handles
     */
    public static final String DEFAULT_HANDLE = "Shibboleth-Handle-1";

    public static final String DEPOSITOR_HANDLE = "test_dep_scientist";

    public static final String ADMINISTRATOR_HANDLE = "test_dep_scientist";

    public static final String AUTHOR_HANDLE = "test_author";

    /**
     * This is definitive an invalid identifier (resource not found).
     */
    public static final String INVALID_RESOURCE_ID = "escidoc:-1";

    /**
     * This is an Context of the example package.
     */
    public static final String EXAMPLE_CONTEXT_ID = "escidoc:ex1";

    /**
     * This is an Organizational Unit of the example package.
     */
    public static final String EXAMPLE_ORGANIZATIONAL_UNIT_ID = "escidoc:ex3";

    /**
     * This is an ContentModel of the example package.
     */
    public static final String EXAMPLE_CONTENT_MODEL_ID = "escidoc:ex4";

    /**
     * This is an Item of the example package.
     */
    public static final String EXAMPLE_ITEM_ID = "escidoc:ex5";

    /**
     * This is an Container of the example package.
     */
    public static final String EXAMPLE_CONTAINER_ID = "escidoc:ex7";

    private static final Pattern PATTERN_OBJID_ATTRIBUTE =
        Pattern.compile("objid=\"([^\"]*)\"");

    private static final Pattern PATTERN_XLINK_HREF_ATTRIBUTE =
        Pattern.compile("xlink:href=\"([^\"]*)\"");

    private static final Pattern PATTERN_LMD_ATTRIBUTE =
        Pattern.compile("last-modification-date=\"([^\"]*)\"");

    /**
     * Asserts that the exception is of expected type<br>
     * This method compares the provided ecpected class with the class of the
     * provided exception.
     * 
     * @param message
     *            The message printed in case of failed assertion.
     * @param expectedClass
     *            The expected type.
     * @param e
     *            The exception to be asserted.
     */
    public static void assertExceptionType(
        final String message, final Class<?> expectedClass, final Exception e) {

        if (!e.getClass().equals(expectedClass)) {
            StringBuffer msg = new StringBuffer(message);
            msg.append(" expected:<");
            msg.append(expectedClass.getName());
            msg.append("> but was:<");
            msg.append(e.getClass().getName());
            msg.append(">");
            appendStackTrace(msg, e);
            fail(msg.toString());
        }
    }

    /**
     * Adds the stack trace to the provided string buffer, if debug logging
     * level is enabled.
     * 
     * @param msg
     *            The StringBuffer to append the stack trace to.
     * @param e
     *            The exception for that the stack trace shall be appended.
     */
    private static void appendStackTrace(
        final StringBuffer msg, final Exception e) {

        if (log.isDebugEnabled()) {
            msg.append("\n");
            msg.append(getStackTrace(e));
        }
    }

    /**
     * Retrieve the stack trace from the provided exception and returns it in a
     * <code>String</code>.
     * 
     * @param e
     *            The exception to retrieve the stack trace from.
     * @return Returns the stack trace in a <code>String</code>.
     */
    public static String getStackTrace(final Exception e) {

        StringWriter writer = new StringWriter();
        PrintWriter printwriter = new PrintWriter(writer);
        e.printStackTrace(printwriter);
        return writer.toString();
    }

    /**
     * Return the child of the node selected by the xPath.
     * 
     * @param node
     *            The node.
     * 
     * @param xPath
     *            The xPath.
     * @return The child of the node selected by the xPath.
     * @throws TransformerException
     *             If anything fails.
     */
    public static Node selectSingleNode(final Node node, final String xPath)
        throws TransformerException {

        Node result = XPathAPI.selectSingleNode(node, xPath);
        return result;
    }

    /**
     * Return the list of children of the node selected by the xPath.
     * 
     * @param node
     *            The node.
     * 
     * @param xPath
     *            The xPath.
     * @return The list of children of the node selected by the xPath.
     * @throws TransformerException
     *             If anything fails.
     */
    public static NodeList selectNodeList(final Node node, final String xPath)
        throws TransformerException {
        NodeList result = XPathAPI.selectNodeList(node, xPath);
        return result;
    }

    /**
     * Assert that the value(s) in the to be asserted node selected by the xPath
     * equals the expected value.
     * 
     * @param message
     *            The message printed if assertion fails.
     * @param expected
     *            The node from which the expected value is selected by the
     *            xpath.
     * @param expectedXpath
     *            The xpath expression navigating in the node to the expected
     *            value.
     * @param toBeAsserted
     *            The node for that the value selected by the xpath shall be
     *            asserted.
     * @param toBeAssertedXpath
     *            The xPath navigating to the value that shall be asserted.
     * @throws Exception
     *             If anything fails.
     */
    public static void assertXmlEquals(
        final String message, final Node expected, final String expectedXpath,
        final Node toBeAsserted, final String toBeAssertedXpath)
        throws Exception {

        final String msg = prepareAssertionFailedMessage(message);

        if (expected == toBeAsserted) {
            return;
        }
        final NodeList expectedNodes = selectNodeList(expected, expectedXpath);
        final NodeList toBeAssertedNodes =
            selectNodeList(toBeAsserted, toBeAssertedXpath);
        assertEquals(msg + "Number of selected nodes differ. ", expectedNodes
            .getLength(), toBeAssertedNodes.getLength());
        final int length = toBeAssertedNodes.getLength();
        for (int i = 0; i < length; i++) {
            assertXmlEquals(msg + "Asserting " + (i + 1) + ". node. ",
                expectedNodes.item(i), toBeAssertedNodes.item(i));
        }
    }

    /**
     * Assert XML content is equal.<br/>
     * 
     * This methods compares the attributes (if any exist) and either
     * recursively compares the child elements (if any exists) or the text
     * content.<br/>
     * Therefore, mixed content is NOT supported by this method.
     * 
     * @param messageIn
     *            The message printed if assertion fails.
     * @param expected
     *            The expected XML content.
     * @param toBeAsserted
     *            The XML content to be compared with the expected content.
     * @throws Exception
     *             If anything fails.
     */
    public static void assertXmlEquals(
        final String messageIn, final Node expected, final Node toBeAsserted)
        throws Exception {

        // Assert both nodes are null or both nodes are not null
        if (expected == null) {
            assertNull(messageIn + "Unexpected node. ", toBeAsserted);
            return;
        }
        assertNotNull(messageIn + " Expected node. ", toBeAsserted);
        if (expected.equals(toBeAsserted)) {
            return;
        }

        String nodeName = getLocalName(expected);

        String message = messageIn;
        if (!message.contains("-- Asserting ")) {
            message = message + "-- Asserting " + nodeName + ". ";
        }
        else {
            message = message + "/" + nodeName;
        }

        // assert both nodes are nodes of the same node type
        // if thedocument container xslt directive than is the nodeName
        // "#document" is here compared
        assertEquals(message + " Type of nodes are different", expected
            .getNodeType(), toBeAsserted.getNodeType());

        if (expected.getNodeType() == Node.TEXT_NODE) {
            assertEquals(message + " Text nodes are different. ", expected
                .getTextContent().trim(), toBeAsserted.getTextContent().trim());
        }

        // assert attributes
        NamedNodeMap expectedAttributes = expected.getAttributes();
        NamedNodeMap toBeAssertedAttributes = toBeAsserted.getAttributes();
        if (expectedAttributes == null) {
            assertNull(message + " Unexpected attributes. [" + nodeName + "]",
                toBeAssertedAttributes);
        }
        else {
            assertNotNull(message + " Expected attributes. ",
                toBeAssertedAttributes);
            final int expectedNumberAttributes = expectedAttributes.getLength();
            // final int toBeAssertedNumberAttributes =
            // toBeAssertedAttributes.getLength();
            // assertEquals(message + " Number of attributes differs. ",
            // expectedNumberAttributes, toBeAssertedNumberAttributes);
            for (int i = 0; i < expectedNumberAttributes; i++) {
                Node expectedAttribute = expectedAttributes.item(i);
                String expectedAttributeNamespace =
                    expectedAttribute.getNamespaceURI();
                Node toBeAssertedAttribute = null;
                if (expectedAttributeNamespace != null) {
                    final String localName = expectedAttribute.getLocalName();
                    toBeAssertedAttribute =
                        toBeAssertedAttributes.getNamedItemNS(
                            expectedAttributeNamespace, localName);
                    assertNotNull(message + " Expected attribute "
                        + expectedAttribute.getNodeName(),
                        toBeAssertedAttribute);
                }
                else {
                    // not namespace aware parsed. Attributes may have different
                    // prefixes which are now part of their node name.
                    // To compare expected and to be asserted attribute, it is
                    // first it is tried to find the appropriate to be asserted
                    // attribute by the node name. If this fails, xpath
                    // selection is used after extracting the expected
                    // attribute name
                    final String expectedAttributeNodeName =
                        expectedAttribute.getNodeName();
                    toBeAssertedAttribute =
                        toBeAssertedAttributes
                            .getNamedItem(expectedAttributeNodeName);
                    if (toBeAssertedAttribute == null) {
                        final String attributeName =
                            getLocalName(expectedAttribute);
                        final String attributeXpath = "@" + attributeName;
                        toBeAssertedAttribute =
                            selectSingleNode(toBeAsserted, attributeXpath);
                    }
                    assertNotNull(message + " Expected attribute "
                        + expectedAttributeNodeName, toBeAssertedAttribute);
                }

                assertEquals(message + " Attribute value mismatch ["
                    + expectedAttribute.getNodeName() + "] ", expectedAttribute
                    .getTextContent(), toBeAssertedAttribute.getTextContent());
            }
        }

        // As mixed content (text + child elements) is not supported,
        // either the child elements or the text content have to be asserted.
        // Therefore, it is first tried to assert the children.
        // After that it is checked if children have been found. If this is not
        // the case, the text content is compared.
        NodeList expectedChildren = expected.getChildNodes();
        NodeList toBeAssertedChildren = toBeAsserted.getChildNodes();

        int expectedNumberElementNodes = 0;
        int toBeAssertedNumberElementNodes = 0;
        List<Node> previouslyAssertedChildren = new ArrayList<Node>();
        for (int i = 0; i < expectedChildren.getLength(); i++) {
            Node expectedChild = expectedChildren.item(i);
            if (expectedChild.getNodeType() == Node.ELEMENT_NODE) {
                expectedNumberElementNodes++;
                String expectedChildName = getLocalName(expectedChild);
                String expectedUri = expectedChild.getNamespaceURI();
                boolean expectedElementAsserted = false;
                for (int j = 0; j < toBeAssertedChildren.getLength(); j++) {
                    final Node toBeAssertedChild = toBeAssertedChildren.item(j);

                    // prevent previously asserted children from being
                    // asserted again
                    if (previouslyAssertedChildren.contains(toBeAssertedChild)) {
                        continue;
                    }

                    if (toBeAssertedChild.getNodeType() == Node.ELEMENT_NODE
                        && expectedChildName
                            .equals(getLocalName(toBeAssertedChild))
                        && (expectedUri == null || expectedUri
                            .equals(toBeAssertedChild.getNamespaceURI()))) {

                        expectedElementAsserted = true;
                        toBeAssertedNumberElementNodes++;
                        assertXmlEquals(message, expectedChild,
                            toBeAssertedChild);
                        // add asserted child to list of asserted children to
                        // prevent it from being asserted again.
                        previouslyAssertedChildren.add(toBeAssertedChild);
                        break;
                    }
                }
                if (!expectedElementAsserted) {
                    fail(new StringBuffer(message)
                        .append(
                            " Did not found expected corresponding element [")
                        .append(nodeName).append(", ")
                        .append(expectedChildName).append(", ").append(i)
                        .append("]").toString());
                }
            }
        }
        // check if any element node in toBeAssertedChildren exists
        // that has not been asserted. In this case, this element node
        // is unexpected!
        for (int i = 0; i < toBeAssertedChildren.getLength(); i++) {
            Node toBeAssertedChild = toBeAssertedChildren.item(i);

            // prevent previously asserted children from being
            // asserted again
            if (previouslyAssertedChildren.contains(toBeAssertedChild)) {
                continue;
            }

            if (toBeAssertedChild.getNodeType() == Node.ELEMENT_NODE) {
                fail(new StringBuffer(message)
                    .append("Found unexpected element node [").append(nodeName)
                    .append(", ").append(getLocalName(toBeAssertedChild))
                    .append(", ").append(i).append("]").toString());
            }
        }

        // if no children have been found, text content must be compared
        if (expectedNumberElementNodes == 0
            && toBeAssertedNumberElementNodes == 0) {
            String expectedContent = expected.getTextContent();
            String toBeAssertedContent = toBeAsserted.getTextContent();
            assertEquals(message, expectedContent, toBeAssertedContent);
        }
    }

    /**
     * Gets the local name (the node name without the namespace prefix) of the
     * provided node.
     * 
     * @param node
     *            The node to extract the name from.
     * @return Returns <code>node.getLocalName</code> if this is set, or the
     *         value of <code>node.getNodeName</code> without the namespace
     *         prefix.
     */
    private static String getLocalName(final Node node) {

        String name = node.getLocalName();
        if (name == null) {
            name = node.getNodeName().replaceAll(".*?:", "");
        }
        return name;
    }

    /**
     * Prepare the failure message.
     * 
     * @param message
     *            The message.
     * @return The standard failure message.
     */
    protected static String prepareAssertionFailedMessage(final String message) {

        final String msg;
        if (message == null) {
            msg = "";
        }
        else if (!message.endsWith(" ")) {
            msg = message + " ";
        }
        else {
            msg = message;
        }
        return msg;
    }

    /**
     * Compares all MetadataRecords with the master. If number, attributes or
     * content of the MetadataRecords differ.
     * 
     * @param master
     *            The master MetadataRecords.
     * @param toCompare
     *            The to compare MetadataRecords.
     * @throws Exception
     *             Thrown if both MetadataRecords not differ.
     */
    public void assertMdRecords(
        final MetadataRecords master, final MetadataRecords toCompare)
        throws Exception {

        assertEquals("Number of MetadataRecord(s) differs", master
            .getMetadataRecords().size(), toCompare.getMetadataRecords().size());

        Iterator<MetadataRecord> it = master.getMetadataRecords().iterator();

        while (it.hasNext()) {

            MetadataRecord mdMaster = it.next();
            String masterName = mdMaster.getName();
            String masterMdType = mdMaster.getMdType();
            String masterSchema = mdMaster.getSchema();

            MetadataRecord mdToComp = toCompare.get(masterName);
            assertNotNull("MetadataRecord with name '" + masterName
                + "' is missing. ", mdToComp);

            String toCompareMdType = mdToComp.getMdType();
            assertEquals("MetadataRecord types not equal.", masterMdType,
                toCompareMdType);

            String toCompareSchema = mdToComp.getSchema();
            assertEquals("MetadataRecord Schemas not equal.", masterSchema,
                toCompareSchema);

            Marshaller<MetadataRecord> m1 =
                new Marshaller<MetadataRecord>(mdMaster.getClass());
            String xml1 = m1.marshalDocument(mdMaster);

            Document mdRecordMaster = XmlUtility.getDocument(xml1);
            Node mdRecordMasterNode =
                selectSingleNode(mdRecordMaster, "/md-record");
            org.w3c.dom.Element mdRecordMasterContent =
                (org.w3c.dom.Element) mdRecordMasterNode.getFirstChild();

            Marshaller<MetadataRecord> m2 =
                new Marshaller<MetadataRecord>(mdToComp.getClass());
            String xml2 = m2.marshalDocument(mdToComp);

            Document mdRecordToCompare = XmlUtility.getDocument(xml2);
            Node mdRecordToCompareNode =
                selectSingleNode(mdRecordToCompare, "/md-record");
            org.w3c.dom.Element mdRecordToCompareContent =
                (org.w3c.dom.Element) mdRecordToCompareNode.getFirstChild();

            assertXmlEquals("Metadata Records differ",
                (Node) mdRecordToCompareContent, (Node) mdRecordMasterContent);

        }
    }

    /**
     * Load content of file as String.
     * 
     * @param file
     *            File
     * @return Content of File as String
     * @throws IOException
     *             Thrown if access to file or reading failed.
     */
    public static String getXmlFileAsString(final File file) throws IOException {

        // FIXME the fixed UTF-8 type let this method mark as garbage
        StringWriter writer = new StringWriter();
        BufferedReader reader =
            new BufferedReader(new InputStreamReader(new FileInputStream(file),
                "UTF-8"));
        String line = reader.readLine();
        while (line != null) {
            writer.append(line + "\n");
            line = reader.readLine();
        }
        reader.close();

        return writer.toString();
    }

    /**
     * Obtain objid and last-modification-date from eSciDoc XML.
     * 
     * @param xml
     *            XML representation of eSciDoc resource
     * @return String[objid, last-modification-date]
     */
    public static String[] obtainObjidAndLmd(final String xml) {

        String[] objidLmd = new String[2];

        // objid from href
        Matcher m = PATTERN_XLINK_HREF_ATTRIBUTE.matcher(xml);
        if (m.find()) {
            String href = m.group(1);
            int p = href.lastIndexOf("/");
            objidLmd[0] = href.substring(p + 1);
        }
        else {
            // fall back to objid (SOAP)
            m = PATTERN_OBJID_ATTRIBUTE.matcher(xml);
            if (m.find()) {
                objidLmd[0] = m.group(1);
            }
            else {
                objidLmd[0] = null;
            }
        }

        // lmd
        m = PATTERN_LMD_ATTRIBUTE.matcher(xml);
        if (m.find()) {
            objidLmd[1] = m.group(1);
        }
        else {
            objidLmd[1] = null;
        }

        return objidLmd;
    }

}
