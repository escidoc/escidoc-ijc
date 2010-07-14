package de.escidoc.core.test.client.unitTests.om.context;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import de.escidoc.core.common.jibx.Factory;
import de.escidoc.core.resources.ResourceRef;
import de.escidoc.core.resources.om.context.AdminDescriptor;
import de.escidoc.core.resources.om.context.AdminDescriptors;
import de.escidoc.core.resources.om.context.Context;
import de.escidoc.core.resources.om.context.OrganizationalUnitRefs;
import de.escidoc.core.resources.om.context.Properties;
import de.escidoc.core.test.client.util.XmlUtil;

/**
 * Test admin descriptor content handling.
 * 
 * @author SWA
 * 
 */
public class TestContentXmlParser {

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

        Context context = new Context();
        Properties properties = new Properties();
        properties.setDescription("ContextDescription");
        properties.setName("ContextName" + System.currentTimeMillis());
        properties.setPublicStatus("opened");
        properties.setPublicStatusComment("PublicStatusComment");

        OrganizationalUnitRefs organizationalUnitRefs =
            new OrganizationalUnitRefs();
        ResourceRef organizationalUnitRef = new ResourceRef("escidoc:ex3");
        organizationalUnitRefs.add(organizationalUnitRef);
        properties.setOrganizationalUnitRefs(organizationalUnitRefs);
        properties.setType("type");
        context.setProperties(properties);

        AdminDescriptors adminDescriptors = new AdminDescriptors();
        AdminDescriptor adminDescriptor = new AdminDescriptor();
        adminDescriptor.setName(adminDescriptorName);
        adminDescriptor.setContent("<" + contentElementName + "/>");

        adminDescriptors.add(adminDescriptor);
        context.setAdminDescriptors(adminDescriptors);

        String contextXml =
            Factory.getContextMarshaller().marshalDocument(context);

        Document contextDoc = XmlUtil.getDocument(contextXml);

        NodeList admDescNodes =
            contextDoc.getElementsByTagName("escidocContext:admin-descriptor");
        assertEquals("Wrong number of admin-descriptor elements", 1,
            admDescNodes.getLength());
        Node admDescNode = admDescNodes.item(0);
        assertEquals("Wrong attribute name of admin-descriptor",
            adminDescriptorName, admDescNode
                .getAttributes().item(0).getTextContent());
        assertTrue("Wrong number of attributes", admDescNode
            .getAttributes().getLength() == 1);
        assertTrue("Wrong node type",
            admDescNode.getNodeType() == Node.ELEMENT_NODE);

        Node admContentNode = admDescNode.getFirstChild();
        // check if only whitespaces are between admin-descriptor and
        // admin-descriptor-content element
        while (admContentNode.getNodeType() != Node.ELEMENT_NODE) {
            assertTrue("Wrong characters", admContentNode
                .getTextContent().trim().length() == 0);
            admContentNode = admContentNode.getNextSibling();
        }

        assertTrue("Wrong node type",
            admContentNode.getNodeType() == Node.ELEMENT_NODE);

        assertEquals("Content lost", contentElementName,
            admContentNode.getNodeName());

        System.out.println("-" + admContentNode.getTextContent() + "-");
        assertTrue("Wrong content",
            admContentNode.getTextContent().length() == 0);

        // unmarshall
        Context contextRev =
            Factory.getContextMarshaller().unmarshalDocument(contextXml);

        assertEquals("Content failue", contentElementName, contextRev
            .getAdminDescriptors().get(0).getContent().getNodeName());
        assertTrue("Content failue", contextRev
            .getAdminDescriptors().get(0).getContent().getChildNodes()
            .getLength() == 0);
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

        Context context = new Context();
        Properties properties = new Properties();
        properties.setDescription("ContextDescription");
        properties.setName("ContextName" + System.currentTimeMillis());
        properties.setPublicStatus("opened");
        properties.setPublicStatusComment("PublicStatusComment");

        OrganizationalUnitRefs organizationalUnitRefs =
            new OrganizationalUnitRefs();
        ResourceRef organizationalUnitRef = new ResourceRef("escidoc:ex3");
        organizationalUnitRefs.add(organizationalUnitRef);
        properties.setOrganizationalUnitRefs(organizationalUnitRefs);
        properties.setType("type");
        context.setProperties(properties);

        AdminDescriptors adminDescriptors = new AdminDescriptors();
        AdminDescriptor adminDescriptor = new AdminDescriptor();
        adminDescriptor.setName(adminDescriptorName);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.newDocument();
        Element element = doc.createElementNS(null, contentElementName);
        adminDescriptor.setContent(element);

        adminDescriptors.add(adminDescriptor);
        context.setAdminDescriptors(adminDescriptors);

        String contextXml =
            Factory.getContextMarshaller().marshalDocument(context);

        Document contextDoc = XmlUtil.getDocument(contextXml);

        NodeList admDescNodes =
            contextDoc.getElementsByTagName("escidocContext:admin-descriptor");
        assertEquals("Wrong number of admin-descriptor elements", 1,
            admDescNodes.getLength());
        Node admDescNode = admDescNodes.item(0);
        assertEquals("Wrong attribute name of admin-descriptor",
            adminDescriptorName, admDescNode
                .getAttributes().item(0).getTextContent());
        assertTrue("Wrong number of attributes", admDescNode
            .getAttributes().getLength() == 1);
        assertTrue("Wrong node type",
            admDescNode.getNodeType() == Node.ELEMENT_NODE);

        Node admContentNode = admDescNode.getFirstChild();
        // check if only whitespaces are between admin-descriptor and
        // admin-descriptor-content element
        while (admContentNode.getNodeType() != Node.ELEMENT_NODE) {
            assertTrue("Wrong characters", admContentNode
                .getTextContent().trim().length() == 0);
            admContentNode = admContentNode.getNextSibling();
        }

        assertTrue("Wrong node type",
            admContentNode.getNodeType() == Node.ELEMENT_NODE);

        assertEquals("Content lost", contentElementName,
            admContentNode.getNodeName());

        System.out.println("-" + admContentNode.getTextContent() + "-");
        assertTrue("Wrong content",
            admContentNode.getTextContent().length() == 0);

        // unmarshall
        Context contextRev =
            Factory.getContextMarshaller().unmarshalDocument(contextXml);

        assertEquals("Content failue", contentElementName, contextRev
            .getAdminDescriptors().get(0).getContent().getNodeName());
        assertTrue("Content failue", contextRev
            .getAdminDescriptors().get(0).getContent().getChildNodes()
            .getLength() == 0);

    }
}
