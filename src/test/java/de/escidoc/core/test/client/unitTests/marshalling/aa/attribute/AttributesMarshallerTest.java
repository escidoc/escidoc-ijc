/**
 * 
 */
package de.escidoc.core.test.client.unitTests.marshalling.aa.attribute;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import de.escidoc.core.resources.aa.useraccount.Attributes;
import de.escidoc.core.test.client.unitTests.marshalling.MarshallerTestBase;

/**
 * @author Marko Voß
 * 
 */
public class AttributesMarshallerTest extends MarshallerTestBase<Attributes> {

    private static final String BASE = "aa/attribute";

    private static final String XSD = "0.1";

    /**
     * @param transport
     * @throws IOException
     * @throws ParserConfigurationException
     * @throws SAXException
     */
    public AttributesMarshallerTest() throws IOException, ParserConfigurationException, SAXException {
        super(Attributes.class, BASE, XSD, "attributes_complete.xml");
    }

    @Override
    protected void validate(final Attributes obj) throws Exception {

        // Attributes
        assertXLinkList("/attributes:attributes", obj);
        assertXPath("/attributes:attributes/@user-objid", obj.getUserObjid());

        // Attribute
        assertResource("/attributes:attributes/attributes:attribute[1]", obj.get(0));
        // Attributes within an attributes-tag may not have a LMD set.
        assertDateTime("/attributes:attributes/attributes:attribute[1]/@last-modification-date", obj
            .get(0).getLastModificationDate(), false);

        assertXPath("/attributes:attributes/attributes:attribute[1]/@name", obj.get(0).getName());
        assertXPath("/attributes:attributes/attributes:attribute[1]/@internal", obj.get(0).isInternal());
        assertXPath("/attributes:attributes/attributes:attribute[1]", obj.get(0).getValue());
    }

    @Override
    protected void testSubResources(final Attributes obj) throws Exception {
        // TODO Auto-generated method stub
    }

    @Override
    protected void testResourceWithoutSubResources(final Attributes obj) throws Exception {
        // TODO Auto-generated method stub
    }
}