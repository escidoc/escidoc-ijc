package de.escidoc.core.test.client.unitTests.om.context;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import de.escidoc.core.common.XmlUtility;
import de.escidoc.core.common.jibx.MarshallerFactory;
import de.escidoc.core.resources.common.properties.PublicStatus;
import de.escidoc.core.resources.common.reference.OrganizationalUnitRef;
import de.escidoc.core.resources.om.context.AdminDescriptor;
import de.escidoc.core.resources.om.context.AdminDescriptors;
import de.escidoc.core.resources.om.context.Context;
import de.escidoc.core.resources.om.context.ContextProperties;
import de.escidoc.core.resources.om.context.OrganizationalUnitRefs;

/**
 * Test admin descriptor content handling.
 * 
 * @author SWA
 * 
 */
public class TestContentXmlParser {

    private static final Logger LOG = LoggerFactory.getLogger(TestContentXmlParser.class);

    /**
     * Test setting content of admin descriptor as String.
     * 
     * @throws Exception
     *             Thrown if behavior is not as expected.
     */
    @Test
    public void setContentOfAdminDescriptorAsString() throws Exception {

        final String contentElementName = "admin-descriptor-content";
        final String adminDescriptorName = "AdminDescriptorDemoName";

        final Context context = new Context();
        final ContextProperties properties = new ContextProperties();
        properties.setDescription("ContextDescription");
        properties.setName("ContextName" + System.currentTimeMillis());
        properties.setPublicStatus(PublicStatus.OPENED);
        properties.setPublicStatusComment("PublicStatusComment");

        final OrganizationalUnitRefs organizationalUnitRefs = new OrganizationalUnitRefs();
        organizationalUnitRefs.add(new OrganizationalUnitRef("escidoc:ex3"));

        properties.setOrganizationalUnitRefs(organizationalUnitRefs);
        properties.setType("type");
        context.setProperties(properties);

        final AdminDescriptors adminDescriptors = new AdminDescriptors();
        final AdminDescriptor adminDescriptor = new AdminDescriptor(adminDescriptorName);
        adminDescriptor.setContent("<" + contentElementName + "/>");

        adminDescriptors.add(adminDescriptor);
        context.setAdminDescriptors(adminDescriptors);

        final String contextXml = MarshallerFactory.getInstance().getMarshaller(Context.class).marshalDocument(context);

        final Document contextDoc = XmlUtility.getDocument(contextXml);

        final NodeList admDescNodes = contextDoc.getElementsByTagName("escidocContext:admin-descriptor");
        assertEquals("Wrong number of admin-descriptor elements", 1, admDescNodes.getLength());
        final Node admDescNode = admDescNodes.item(0);
        assertEquals("Wrong attribute name of admin-descriptor", adminDescriptorName, admDescNode.getAttributes().item(
            0).getTextContent());
        assertTrue("Wrong number of attributes", admDescNode.getAttributes().getLength() == 1);
        assertTrue("Wrong node type", admDescNode.getNodeType() == Node.ELEMENT_NODE);

        Node admContentNode = admDescNode.getFirstChild();
        // check if only whitespaces are between admin-descriptor and
        // admin-descriptor-content element
        while (admContentNode.getNodeType() != Node.ELEMENT_NODE) {
            admContentNode = admContentNode.getNextSibling();
        }

        assertTrue("Wrong node type", admContentNode.getNodeType() == Node.ELEMENT_NODE);

        assertEquals("Content lost", contentElementName, admContentNode.getNodeName());

        LOG.debug("-" + admContentNode.getTextContent() + "-");
        assertTrue("Wrong content", admContentNode.getTextContent().length() == 0);

        // unmarshall
        final Context contextRev =
            MarshallerFactory.getInstance().getMarshaller(Context.class).unmarshalDocument(contextXml);

        assertEquals("Content failure", contentElementName, contextRev
            .getAdminDescriptors().get(0).getContent().getNodeName());
        assertTrue("Content failure",
            contextRev.getAdminDescriptors().get(0).getContent().getChildNodes().getLength() == 0);
    }

    /**
     * Test setting content of admin descriptor as DOM element.
     * 
     * @throws Exception
     *             Thrown if behavior is not as expected.
     */
    @Test
    public void setContentOfAdminDescriptorAsElement() throws Exception {

        final String contentElementName = "admin-descriptor-content";
        final String adminDescriptorName = "AdminDescriptorDemoName";

        final Context context = new Context();
        final ContextProperties properties = new ContextProperties();
        properties.setDescription("ContextDescription");
        properties.setName("ContextName" + System.currentTimeMillis());
        properties.setPublicStatus(PublicStatus.OPENED);
        properties.setPublicStatusComment("PublicStatusComment");

        final OrganizationalUnitRefs organizationalUnitRefs = new OrganizationalUnitRefs();
        organizationalUnitRefs.add(new OrganizationalUnitRef("escidoc:ex3"));

        properties.setOrganizationalUnitRefs(organizationalUnitRefs);
        properties.setType("type");
        context.setProperties(properties);

        final AdminDescriptors adminDescriptors = new AdminDescriptors();
        final AdminDescriptor adminDescriptor = new AdminDescriptor(adminDescriptorName);
        final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        final DocumentBuilder builder = factory.newDocumentBuilder();
        final Document doc = builder.newDocument();
        final Element element = doc.createElementNS(null, contentElementName);
        adminDescriptor.setContent(element);

        adminDescriptors.add(adminDescriptor);
        context.setAdminDescriptors(adminDescriptors);

        final String contextXml = MarshallerFactory.getInstance().getMarshaller(Context.class).marshalDocument(context);

        final Document contextDoc = XmlUtility.getDocument(contextXml);

        final NodeList admDescNodes = contextDoc.getElementsByTagName("escidocContext:admin-descriptor");
        assertEquals("Wrong number of admin-descriptor elements", 1, admDescNodes.getLength());
        final Node admDescNode = admDescNodes.item(0);
        assertEquals("Wrong attribute name of admin-descriptor", adminDescriptorName, admDescNode.getAttributes().item(
            0).getTextContent());
        assertTrue("Wrong number of attributes", admDescNode.getAttributes().getLength() == 1);
        assertTrue("Wrong node type", admDescNode.getNodeType() == Node.ELEMENT_NODE);

        Node admContentNode = admDescNode.getFirstChild();
        // check if only whitespaces are between admin-descriptor and
        // admin-descriptor-content element
        while (admContentNode.getNodeType() != Node.ELEMENT_NODE) {
            admContentNode = admContentNode.getNextSibling();
        }

        assertTrue("Wrong node type", admContentNode.getNodeType() == Node.ELEMENT_NODE);

        assertEquals("Content lost", contentElementName, admContentNode.getNodeName());

        LOG.debug("-" + admContentNode.getTextContent() + "-");
        assertTrue("Wrong content", admContentNode.getTextContent().length() == 0);

        // unmarshall
        final Context contextRev =
            MarshallerFactory.getInstance().getMarshaller(Context.class).unmarshalDocument(contextXml);

        assertEquals("Content failue", contentElementName, contextRev
            .getAdminDescriptors().get(0).getContent().getNodeName());
        assertTrue("Content failue",
            contextRev.getAdminDescriptors().get(0).getContent().getChildNodes().getLength() == 0);

    }
}
