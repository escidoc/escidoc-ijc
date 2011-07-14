/**
 * 
 */
package de.escidoc.core.test.client.unitTests.marshalling.om.context;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import de.escidoc.core.resources.om.context.Context;
import de.escidoc.core.test.client.unitTests.marshalling.MarshallerTestBase;

/**
 * @author Marko Voß
 * 
 */
public class ContextMarshallerTest extends MarshallerTestBase<Context> {

    private static final String BASE = "om/context";

    private static final String XSD = "0.7";

    /**
     * @param transport
     * @throws IOException
     * @throws ParserConfigurationException
     * @throws SAXException
     */
    public ContextMarshallerTest() throws IOException, ParserConfigurationException, SAXException {
        super(Context.class, BASE, XSD, "context_complete.xml");
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.test.client.marshaller.MarshallerTestBase#validate(java
     * .lang.Object)
     */
    @Override
    protected void validate(final Context obj) throws Exception {
        /*
         * TODO: LMDs for sub-resources
         */

        // Context
        assertResource("/escidocContext:context", obj);
        assertDateTime("/escidocContext:context/@last-modification-date", obj.getLastModificationDate());

        // ContextProperties
        assertNotNull(obj.getProperties());
        assertXLinkResource("/escidocContext:context/escidocContext:properties", obj.getProperties());
        assertDateTime("/escidocContext:context/escidocContext:properties/prop:creation-date", obj
            .getProperties().getCreationDate());
        assertResource("/escidocContext:context/escidocContext:properties/srel:created-by", obj
            .getProperties().getCreatedBy());
        assertResource("/escidocContext:context/escidocContext:properties/srel:modified-by", obj
            .getProperties().getModifiedBy());
        assertEnum("/escidocContext:context/escidocContext:properties/prop:public-status", obj
            .getProperties().getPublicStatus());
        assertXPath("/escidocContext:context/escidocContext:properties/prop:public-status-comment", obj
            .getProperties().getPublicStatusComment());
        assertXPath("/escidocContext:context/escidocContext:properties/prop:name", obj.getProperties().getName());
        assertXPath("/escidocContext:context/escidocContext:properties/prop:description", obj
            .getProperties().getDescription());
        assertXPath("/escidocContext:context/escidocContext:properties/prop:type", obj.getProperties().getType());

        // ContextProperties.OrganizationalUnitRefs
        assertNotNull(obj.getProperties().getOrganizationalUnitRefs());
        assertResource(
            "/escidocContext:context/escidocContext:properties/prop:organizational-units/srel:organizational-unit[1]",
            obj.getProperties().getOrganizationalUnitRefs().get(0));

        // Context.AdminDescriptors
        assertNotNull(obj.getAdminDescriptors());
        assertXLinkList("/escidocContext:context/escidocContext:admin-descriptors", obj.getAdminDescriptors());

        // Context.AdminDescriptors.AdminDescriptor[1]
        assertNamedSubResource(
            "/escidocContext:context/escidocContext:admin-descriptors/escidocContext:admin-descriptor[1]", obj
                .getAdminDescriptors().get(0));
        // TODO validate DOM
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.test.client.marshaller.MarshallerTestBase#testSubResources
     * (java.lang.Object)
     */
    @Override
    protected void testSubResources(final Context obj) throws Exception {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.test.client.marshaller.MarshallerTestBase#
     * testResourceWithoutSubResources(java.lang.Object)
     */
    @Override
    protected void testResourceWithoutSubResources(final Context obj) throws Exception {
        // TODO Auto-generated method stub

    }
}