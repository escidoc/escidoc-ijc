package de.escidoc.core.test.client.unitTests.marshalling.aa.grant;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import de.escidoc.core.resources.aa.useraccount.Grants;
import de.escidoc.core.test.client.unitTests.marshalling.MarshallerTestBase;

public class CurrentGrantsMarshallerTest extends MarshallerTestBase<Grants> {

    private static final String BASE = "aa/grant";

    private static final String XSD = "0.5";

    /**
     * @param transport
     * @throws IOException
     * @throws ParserConfigurationException
     * @throws SAXException
     */
    public CurrentGrantsMarshallerTest() throws IOException, ParserConfigurationException, SAXException {
        super(Grants.class, BASE, XSD, "current-grants.xml");
    }

    @Override
    protected void validate(final Grants obj) throws Exception {
        // TODO Auto-generated method stub
    }

    @Override
    protected void testSubResources(final Grants obj) throws Exception {
        // TODO Auto-generated method stub

    }

    @Override
    protected void testResourceWithoutSubResources(final Grants obj) throws Exception {
        // TODO Auto-generated method stub

    }
}