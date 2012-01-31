package de.escidoc.core.test.client.unitTests.marshalling.oum.ou;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import de.escidoc.core.client.TransportProtocol;
import de.escidoc.core.resources.oum.PathList;
import de.escidoc.core.test.client.unitTests.marshalling.MarshallerTestBase;

public class PathListMarshallerTest extends MarshallerTestBase<PathList> {

    private static final String BASE = "oum/ou";

    private static final String XSD = "0.8";

    /**
     * @param transport
     * @throws IOException
     * @throws ParserConfigurationException
     * @throws SAXException
     */
    public PathListMarshallerTest(final TransportProtocol transport) throws IOException, ParserConfigurationException,
        SAXException {
        super(PathList.class, BASE, XSD, "pathlist_complete.xml", transport);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.test.client.unitTests.marshalling.MarshallerTestBase#
     * validate(java.lang.Object)
     */
    @Override
    protected void validate(final PathList obj) throws Exception {
        final String path = "/organizational-unit-path-list:organizational-unit-path-list";
        assertXLinkList(path, obj, false);
        assertEquals(2, obj.size());
        assertEquals(2, obj.get(0).size());
        assertEquals(2, obj.get(1).size());
        assertXLinkResource(
            path
                + "/organizational-unit-path-list:organizational-unit-path[1]/organizational-unit-ref:organizational-unit-ref[1]",
            obj.get(0).get(0));
        assertXLinkResource(
            path
                + "/organizational-unit-path-list:organizational-unit-path[1]/organizational-unit-ref:organizational-unit-ref[2]",
            obj.get(0).get(1));
        assertXLinkResource(
            path
                + "/organizational-unit-path-list:organizational-unit-path[2]/organizational-unit-ref:organizational-unit-ref[1]",
            obj.get(1).get(0));
        assertXLinkResource(
            path
                + "/organizational-unit-path-list:organizational-unit-path[2]/organizational-unit-ref:organizational-unit-ref[2]",
            obj.get(1).get(1));
    }

    @Override
    protected void testSubResources(final PathList obj) throws Exception {
        // TODO Auto-generated method stub
    }

    @Override
    protected void testResourceWithoutSubResources(final PathList obj) throws Exception {
        // TODO Auto-generated method stub
    }
}
