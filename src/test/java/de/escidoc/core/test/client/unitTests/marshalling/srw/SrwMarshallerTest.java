package de.escidoc.core.test.client.unitTests.marshalling.srw;

import static org.junit.Assert.assertTrue;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import de.escidoc.core.resources.oum.OrganizationalUnit;
import de.escidoc.core.resources.sb.search.SearchResultRecord;
import de.escidoc.core.resources.sb.search.SearchRetrieveResponse;
import de.escidoc.core.test.client.unitTests.marshalling.MarshalOnlyTestBase;

/**
 * @author Marko Voss (marko.voss@fiz-karlsruhe.de)
 */
public class SrwMarshallerTest extends MarshalOnlyTestBase<SearchRetrieveResponse> {

    /**
     * @param transport
     * @throws IOException
     * @throws ParserConfigurationException
     * @throws SAXException
     */
    public SrwMarshallerTest() throws IOException, ParserConfigurationException, SAXException {
        super(SearchRetrieveResponse.class, "srw", null, "organizational-units.xml");
    }

    @Override
    protected void validate(final SearchRetrieveResponse obj) throws Exception {
        for (final SearchResultRecord searchResultRecord : obj.getRecords()) {
            final Object o = searchResultRecord.getRecordData().getContent();
            assertTrue(o instanceof OrganizationalUnit);
        }

    }

    @Override
    protected void testSubResources(final SearchRetrieveResponse obj) throws Exception {
        // TODO Auto-generated method stub

    }

    @Override
    protected void testResourceWithoutSubResources(final SearchRetrieveResponse obj) throws Exception {
        // TODO Auto-generated method stub

    }
}
