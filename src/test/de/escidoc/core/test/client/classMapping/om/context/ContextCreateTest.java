package de.escidoc.core.test.client.classMapping.om.context;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import junit.framework.TestCase;

import org.apache.log4j.Logger;

import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import de.escidoc.core.client.ContextHandlerClient;
import de.escidoc.core.client.exceptions.application.invalid.XmlSchemaValidationException;
import de.escidoc.core.resources.ResourceRef;
import de.escidoc.core.resources.om.context.AdminDescriptor;
import de.escidoc.core.resources.om.context.AdminDescriptors;
import de.escidoc.core.resources.om.context.Context;
import de.escidoc.core.resources.om.context.OrganizationalUnitRefs;
import de.escidoc.core.resources.om.context.Properties;
import de.escidoc.core.test.client.EscidocClientTestBase;

/**
 * Test create of Context.
 * 
 * @author SWA
 * 
 */
public class ContextCreateTest extends TestCase {
	
    private final Logger logger =
        Logger.getLogger(ContextHandlerClientTest.class.getName());

    /**
     * 
     */
    public ContextCreateTest() {
        super("ContextCreateTest");
    }

    /**
     * Set up the tests.
     * 
     * @throws Exception
     *             If anything fails.
     */
    @Override
    protected void setUp() throws Exception {

        super.setUp();

    }

    /**
     * Clean up after tests.
     * 
     * @throws Exception
     *             If anything fails.
     */
    @Override
    protected void tearDown() throws Exception {

        super.tearDown();
    }

    /**
     * Test if the right exception is thrown if calling create with an
     * incomplete Context.
     * 
     * @throws Exception
     *             Thrown if no or wrong exception is caught from the framework.
     */
    @Test
    public void testCreateContext01() throws Exception {

        ContextHandlerClient cc = new ContextHandlerClient();
        cc.setHandle(EscidocClientTestBase.DEFAULT_HANDLE);

        Context context = new Context();
        try {
            cc.create(context);
            fail("Missing Exception");
        }
        catch (Exception e) {
            Class<?> ec = XmlSchemaValidationException.class;
            EscidocClientTestBase.assertExceptionType(ec.getName()
                + " expected.", ec, e);
        }
    }

    /**
     * Test if the right exception is thrown if calling create with an
     * incomplete Context. + Properties
     * 
     * @throws Exception
     *             Thrown if no or wrong exception is caught from the framework.
     */
    @Test
    public void testCreateContext02() throws Exception {

        ContextHandlerClient cc = new ContextHandlerClient();
        cc.setHandle(EscidocClientTestBase.DEFAULT_HANDLE);

        Context context = new Context();
        Properties properties = new Properties();
        context.setProperties(properties);

        try {
            cc.create(context);
            fail("Missing Exception");
        }
        catch (Exception e) {
            Class<?> ec = XmlSchemaValidationException.class;
            EscidocClientTestBase.assertExceptionType(ec.getName()
                + " expected.", ec, e);
        }
    }

    /**
     * Test if the right exception is thrown if calling create with an
     * incomplete Context. + Properties, + AdminDescritor
     * 
     * @throws Exception
     *             Thrown if no or wrong exception is caught from the framework.
     */
    @Test
    public void testCreateContext03() throws Exception {

        ContextHandlerClient cc = new ContextHandlerClient();
        cc.setHandle(EscidocClientTestBase.DEFAULT_HANDLE);

        Context context = new Context();
        Properties properties = new Properties();
        context.setProperties(properties);
        AdminDescriptors adminDescriptors = new AdminDescriptors();
        context.setAdminDescriptors(adminDescriptors);

        try {
            cc.create(context);
            fail("Missing Exception");
        }
        catch (Exception e) {
            Class<?> ec = XmlSchemaValidationException.class;
            EscidocClientTestBase.assertExceptionType(ec.getName()
                + " expected.", ec, e);
        }
    }

    /**
     * Test if the right exception is thrown if calling create with an
     * incomplete Context. + Properties, + AdminDescritors + AdminDescriptor
     * 
     * @throws Exception
     *             Thrown if no or wrong exception is caught from the framework.
     */
    @Test
    public void testCreateContext04() throws Exception {

        ContextHandlerClient cc = new ContextHandlerClient();
        cc.setHandle(EscidocClientTestBase.DEFAULT_HANDLE);

        Context context = new Context();
        Properties properties = new Properties();
        context.setProperties(properties);
        AdminDescriptors adminDescriptors = new AdminDescriptors();
        AdminDescriptor adminDescriptor = new AdminDescriptor();
        adminDescriptor.setName("AdminDescriptorDemoName");
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.newDocument();
        Element element = doc.createElementNS(
            null,
            "adminDescriptor");
        

        adminDescriptor.setContent(element);

        adminDescriptors.addAdminDescriptor(adminDescriptor);
        context.setAdminDescriptors(adminDescriptors);

        try {
            cc.create(context);
            fail("Missing Exception");
        }
        catch (Exception e) {
            Class<?> ec = XmlSchemaValidationException.class;
            EscidocClientTestBase.assertExceptionType(ec.getName()
                + " expected.", ec, e);
        }
    }

    /**
     * Test if the right exception is thrown if calling create with an
     * incomplete Context. + Properties
     * 
     * @throws Exception
     *             Thrown if no or wrong exception is caught from the framework.
     */
    @Test
    public void testCreateContext08() throws Exception {

        ContextHandlerClient cc = new ContextHandlerClient();
        cc.setHandle(EscidocClientTestBase.DEFAULT_HANDLE);

        Context context = new Context();
        Properties properties = new Properties();
        properties.setDescription("ContextDescription");
        properties.setName("ContextName");
        properties.setPublicStatus("open");
        properties.setPublicStatusComment("PublicStatusComment");

        OrganizationalUnitRefs organizationalUnitRefs =
            new OrganizationalUnitRefs();
        ResourceRef organizationalUnitRef = new ResourceRef("escidoc:ex3");
        organizationalUnitRefs.addOrganizationalUnitRef(organizationalUnitRef);
        properties.setOrganizationalUnitRefs(organizationalUnitRefs);
        context.setProperties(properties);

        AdminDescriptors adminDescriptors = new AdminDescriptors();
        AdminDescriptor adminDescriptor = new AdminDescriptor();
        adminDescriptor.setName("AdminDescriptorDemoName");
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.newDocument();
        Element element = doc.createElement("admin-descriptor");
       

        adminDescriptor.setContent(element);

        adminDescriptors.addAdminDescriptor(adminDescriptor);
        context.setAdminDescriptors(adminDescriptors);

        try {
            cc.create(context);
            fail("Missing Exception");
        }
        catch (Exception e) {
            Class<?> ec = XmlSchemaValidationException.class;
            EscidocClientTestBase.assertExceptionType(ec.getName()
                + " expected.", ec, e);
        }
    }

}
