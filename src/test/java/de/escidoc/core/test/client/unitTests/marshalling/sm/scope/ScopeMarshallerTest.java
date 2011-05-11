/**
 * 
 */
package de.escidoc.core.test.client.unitTests.marshalling.sm.scope;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import de.escidoc.core.client.TransportProtocol;
import de.escidoc.core.resources.sm.scope.Scope;
import de.escidoc.core.test.client.unitTests.marshalling.MarshallerTestBase;

/**
 * @author Marko Voß
 * 
 */
public class ScopeMarshallerTest extends MarshallerTestBase<Scope> {

    private static final String BASE = "sm/scope";

    private static final String XSD = "0.4";

    /**
     * @param transport
     * @throws IOException
     * @throws ParserConfigurationException
     * @throws SAXException
     */
    public ScopeMarshallerTest(final TransportProtocol transport)
        throws IOException, ParserConfigurationException, SAXException {
        super(Scope.class, BASE, XSD, "scope_complete.xml", transport);
    }

    @Override
    protected void validate(final Scope obj) throws Exception {
        assertResource("/scope:scope", obj);
        assertDateTime("/scope:scope/@last-modification-date",
            obj.getLastModificationDate());
        assertXPath("/scope:scope/scope:name", obj.getName());
        assertEnum("/scope:scope/scope:type", obj.getScopeType());
    }

    @Override
    protected void testSubResources(final Scope obj) throws Exception {
        // TODO Auto-generated method stub
    }

    @Override
    protected void testResourceWithoutSubResources(final Scope obj)
        throws Exception {
        // TODO Auto-generated method stub
    }
}