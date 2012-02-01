package de.escidoc.core.test.client.unitTests.marshalling.oum.ou;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import de.escidoc.core.resources.oum.Parents;
import de.escidoc.core.test.client.unitTests.marshalling.MarshallerTestBase;

public class ParentsMarshallerTest extends MarshallerTestBase<Parents> {

    private static final String BASE = "oum/ou";

    private static final String XSD = "0.8";

    /**
     * @param transport
     * @throws IOException
     * @throws ParserConfigurationException
     * @throws SAXException
     */
    public ParentsMarshallerTest() throws IOException, ParserConfigurationException, SAXException {
        super(Parents.class, BASE, XSD, "parents_complete.xml");
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.test.client.unitTests.marshalling.MarshallerTestBase#
     * validate(java.lang.Object)
     */
    @Override
    protected void validate(final Parents obj) throws Exception {
        assertXLinkList("/escidocOrganizationalUnit:parents", obj, false);
        assertResource("/escidocOrganizationalUnit:parents/srel:parent[1]", obj.get(0));
    }

    @Override
    protected void testSubResources(final Parents obj) throws Exception {
        // TODO Auto-generated method stub
    }

    @Override
    protected void testResourceWithoutSubResources(final Parents obj) throws Exception {
        // TODO Auto-generated method stub
    }
}