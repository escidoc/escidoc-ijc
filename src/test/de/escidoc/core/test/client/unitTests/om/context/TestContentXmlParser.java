package de.escidoc.core.test.client.unitTests.om.context;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import de.escidoc.core.common.jibx.Factory;
import de.escidoc.core.resources.ResourceRef;
import de.escidoc.core.resources.om.context.AdminDescriptor;
import de.escidoc.core.resources.om.context.AdminDescriptors;
import de.escidoc.core.resources.om.context.Context;
import de.escidoc.core.resources.om.context.OrganizationalUnitRefs;
import de.escidoc.core.resources.om.context.Properties;

/**
 * Test create of Context.
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

        final String content = "<admin-descriptor/>";

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
        adminDescriptor.setName("AdminDescriptorDemoName");
        adminDescriptor.setContent(content);

        adminDescriptors.add(adminDescriptor);
        context.setAdminDescriptors(adminDescriptors);

        String contextXml =
            Factory.getContextMarshaller().marshalDocument(context);
        assertTrue("Content lost", contextXml.contains("<admin-descriptor"));

        Context contextRev =
            Factory.getContextMarshaller().unmarshalDocument(contextXml);
        assertEquals("Content failue", content, contextRev
            .getAdminDescriptors().get(0).getContentAsString());
    }

    /**
     * Test setting content of admin descriptor as DOM element.
     * 
     * @throws Exception
     *             Thrown if behavior is not as expected.
     */
    @Test
    public void setContentOfAdminDescriptorAsElement() throws Exception {

        final String contentElementName = "admin-descriptor";

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
        adminDescriptor.setName("AdminDescriptorDemoName");
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.newDocument();
        Element element = doc.createElementNS(null, contentElementName);
        adminDescriptor.setContent(element);

        adminDescriptors.add(adminDescriptor);
        context.setAdminDescriptors(adminDescriptors);

        String contextXml =
            Factory.getContextMarshaller().marshalDocument(context);
        assertTrue("Content lost", contextXml.contains("<admin-descriptor"));

        Context contextRev =
            Factory.getContextMarshaller().unmarshalDocument(contextXml);
        assertEquals("Content failue", "<" + contentElementName + "/>",
            contextRev.getAdminDescriptors().get(0).getContentAsString());

    }
}
