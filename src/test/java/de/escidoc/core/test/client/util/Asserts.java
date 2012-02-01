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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.sun.org.apache.xpath.internal.XPathAPI;

import de.escidoc.core.common.XmlUtility;
import de.escidoc.core.common.jibx.Marshaller;
import de.escidoc.core.resources.common.MetadataRecord;
import de.escidoc.core.resources.common.MetadataRecords;

/**
 * A collection of eSciDoc resource asserts.
 * 
 * @author SWA (Most of methods are moved from other escidoc classes. This was
 *         done to create a clean structure.)
 * 
 */
public class Asserts {

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
    public static void assertMdRecords(final MetadataRecords master, final MetadataRecords toCompare) throws Exception {

        assertEquals("Number of MetadataRecord(s) differs", master.size(), toCompare.size());

        final Iterator<MetadataRecord> it = master.iterator();

        while (it.hasNext()) {

            final MetadataRecord mdMaster = it.next();
            final String masterName = mdMaster.getName();
            final String masterMdType = mdMaster.getMdType();
            final String masterSchema = mdMaster.getSchema();

            final MetadataRecord mdToComp = toCompare.get(masterName);
            assertNotNull("MetadataRecord with name '" + masterName + "' is missing. ", mdToComp);

            final String toCompareMdType = mdToComp.getMdType();
            assertEquals("MetadataRecord types not equal.", masterMdType, toCompareMdType);

            final String toCompareSchema = mdToComp.getSchema();
            assertEquals("MetadataRecord Schemas not equal.", masterSchema, toCompareSchema);

            final Marshaller<MetadataRecord> m = Marshaller.getMarshaller(MetadataRecord.class);

            final String xml1 = m.marshalDocument(mdMaster);

            final Document mdRecordMaster = XmlUtility.getDocument(xml1);
            final Node mdRecordMasterNode = XPathAPI.selectSingleNode(mdRecordMaster, "/md-record");
            final Node mdRecordMasterContent = mdRecordMasterNode.getFirstChild();

            final String xml2 = m.marshalDocument(mdToComp);

            final Document mdRecordToCompare = XmlUtility.getDocument(xml2);
            final Node mdRecordToCompareNode = XPathAPI.selectSingleNode(mdRecordToCompare, "/md-record");
            final Node mdRecordToCompareContent = mdRecordToCompareNode.getFirstChild();

            assertXmlEquals("Metadata Records differ", mdRecordToCompareContent, mdRecordMasterContent);

        }
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
        final String message, final Node expected, final String expectedXpath, final Node toBeAsserted,
        final String toBeAssertedXpath) throws Exception {

        final String msg = prepareAssertionFailedMessage(message);

        if (expected == toBeAsserted) {
            return;
        }
        final NodeList expectedNodes = XPathAPI.selectNodeList(expected, expectedXpath);
        final NodeList toBeAssertedNodes = XPathAPI.selectNodeList(toBeAsserted, toBeAssertedXpath);
        assertEquals(msg + "Number of selected nodes differ. ", expectedNodes.getLength(), toBeAssertedNodes
            .getLength());
        final int length = toBeAssertedNodes.getLength();
        for (int i = 0; i < length; i++) {
            assertXmlEquals(msg + "Asserting " + (i + 1) + ". node. ", expectedNodes.item(i), toBeAssertedNodes.item(i));
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
    public static void assertXmlEquals(final String messageIn, final Node expected, final Node toBeAsserted)
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

        final String nodeName = getLocalName(expected);

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
        assertEquals(message + " Type of nodes are different", expected.getNodeType(), toBeAsserted.getNodeType());

        if (expected.getNodeType() == Node.TEXT_NODE) {
            assertEquals(message + " Text nodes are different. ", expected.getTextContent().trim(), toBeAsserted
                .getTextContent().trim());
        }

        // assert attributes
        final NamedNodeMap expectedAttributes = expected.getAttributes();
        final NamedNodeMap toBeAssertedAttributes = toBeAsserted.getAttributes();
        if (expectedAttributes == null) {
            assertNull(message + " Unexpected attributes. [" + nodeName + "]", toBeAssertedAttributes);
        }
        else {
            assertNotNull(message + " Expected attributes. ", toBeAssertedAttributes);
            final int expectedNumberAttributes = expectedAttributes.getLength();
            // final int toBeAssertedNumberAttributes =
            // toBeAssertedAttributes.getLength();
            // assertEquals(message + " Number of attributes differs. ",
            // expectedNumberAttributes, toBeAssertedNumberAttributes);
            for (int i = 0; i < expectedNumberAttributes; i++) {
                final Node expectedAttribute = expectedAttributes.item(i);
                final String expectedAttributeNamespace = expectedAttribute.getNamespaceURI();
                Node toBeAssertedAttribute = null;
                if (expectedAttributeNamespace != null) {
                    final String localName = expectedAttribute.getLocalName();
                    toBeAssertedAttribute =
                        toBeAssertedAttributes.getNamedItemNS(expectedAttributeNamespace, localName);
                    assertNotNull(message + " Expected attribute " + expectedAttribute.getNodeName(),
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
                    final String expectedAttributeNodeName = expectedAttribute.getNodeName();
                    toBeAssertedAttribute = toBeAssertedAttributes.getNamedItem(expectedAttributeNodeName);
                    if (toBeAssertedAttribute == null) {
                        final String attributeName = getLocalName(expectedAttribute);
                        final String attributeXpath = "@" + attributeName;
                        toBeAssertedAttribute = XPathAPI.selectSingleNode(toBeAsserted, attributeXpath);
                    }
                    assertNotNull(message + " Expected attribute " + expectedAttributeNodeName, toBeAssertedAttribute);
                }

                assertEquals(message + " Attribute value mismatch [" + expectedAttribute.getNodeName() + "] ",
                    expectedAttribute.getTextContent(), toBeAssertedAttribute.getTextContent());
            }
        }

        // As mixed content (text + child elements) is not supported,
        // either the child elements or the text content have to be asserted.
        // Therefore, it is first tried to assert the children.
        // After that it is checked if children have been found. If this is not
        // the case, the text content is compared.
        final NodeList expectedChildren = expected.getChildNodes();
        final NodeList toBeAssertedChildren = toBeAsserted.getChildNodes();

        int expectedNumberElementNodes = 0;
        int toBeAssertedNumberElementNodes = 0;
        final List<Node> previouslyAssertedChildren = new ArrayList<Node>();
        for (int i = 0; i < expectedChildren.getLength(); i++) {
            final Node expectedChild = expectedChildren.item(i);
            if (expectedChild.getNodeType() == Node.ELEMENT_NODE) {
                expectedNumberElementNodes++;
                final String expectedChildName = getLocalName(expectedChild);
                final String expectedUri = expectedChild.getNamespaceURI();
                boolean expectedElementAsserted = false;
                for (int j = 0; j < toBeAssertedChildren.getLength(); j++) {
                    final Node toBeAssertedChild = toBeAssertedChildren.item(j);

                    // prevent previously asserted children from being
                    // asserted again
                    if (previouslyAssertedChildren.contains(toBeAssertedChild)) {
                        continue;
                    }

                    if (toBeAssertedChild.getNodeType() == Node.ELEMENT_NODE
                        && expectedChildName.equals(getLocalName(toBeAssertedChild))
                        && (expectedUri == null || expectedUri.equals(toBeAssertedChild.getNamespaceURI()))) {

                        expectedElementAsserted = true;
                        toBeAssertedNumberElementNodes++;
                        assertXmlEquals(message, expectedChild, toBeAssertedChild);
                        // add asserted child to list of asserted children to
                        // prevent it from being asserted again.
                        previouslyAssertedChildren.add(toBeAssertedChild);
                        break;
                    }
                }
                if (!expectedElementAsserted) {
                    fail(new StringBuffer(message).append(" Did not found expected corresponding element [").append(
                        nodeName).append(", ").append(expectedChildName).append(", ").append(i).append("]").toString());
                }
            }
        }
        // check if any element node in toBeAssertedChildren exists
        // that has not been asserted. In this case, this element node
        // is unexpected!
        for (int i = 0; i < toBeAssertedChildren.getLength(); i++) {
            final Node toBeAssertedChild = toBeAssertedChildren.item(i);

            // prevent previously asserted children from being
            // asserted again
            if (previouslyAssertedChildren.contains(toBeAssertedChild)) {
                continue;
            }

            if (toBeAssertedChild.getNodeType() == Node.ELEMENT_NODE) {
                fail(new StringBuffer(message)
                    .append("Found unexpected element node [").append(nodeName).append(", ").append(
                        getLocalName(toBeAssertedChild)).append(", ").append(i).append("]").toString());
            }
        }

        // if no children have been found, text content must be compared
        if (expectedNumberElementNodes == 0 && toBeAssertedNumberElementNodes == 0) {
            final String expectedContent = expected.getTextContent();
            final String toBeAssertedContent = toBeAsserted.getTextContent();
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

}
