/**
 * 
 */
package de.escidoc.core.test.client.unitTests.marshalling.aa.grant;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import de.escidoc.core.client.TransportProtocol;
import de.escidoc.core.resources.aa.useraccount.Grant;
import de.escidoc.core.test.client.unitTests.marshalling.MarshallerTestBase;

/**
 * @author Marko Voß
 * 
 */
public class GrantMarshallerTest extends MarshallerTestBase<Grant> {

    private static final String BASE = "aa/grant";

    private static final String XSD = "0.5";

    /**
     * @param transport
     * @throws IOException
     * @throws ParserConfigurationException
     * @throws SAXException
     */
    public GrantMarshallerTest(final TransportProtocol transport)
        throws IOException, ParserConfigurationException, SAXException {
        super(Grant.class, BASE, XSD, "grant_complete.xml", transport);
    }

    @Override
    protected void validate(final Grant obj) throws Exception {
        /*
         * TODO: LMDs for sub-resources
         */

        // UserAccount
        assertResource("/grants:grant", obj);
        assertDateTime("/grants:grant/@last-modification-date",
            obj.getLastModificationDate());

        // UserAccountProperties
        String path = "/grants:grant/grants:properties";
        assertNotNull(obj.getProperties());

        assertResource(path + "/srel:granted-to", obj
            .getProperties().getGrantedTo());
        assertXPath(path + "/srel:granted-to/@resource", obj
            .getProperties().getGrantedTo().getResource());
        assertDateTime(path + "/prop:creation-date", obj
            .getProperties().getCreationDate());
        assertResource(path + "/srel:created-by", obj
            .getProperties().getCreatedBy());
        assertDateTime(path + "/prop:revocation-date", obj
            .getProperties().getRevocationDate());
        assertResource(path + "/srel:revoked-by", obj
            .getProperties().getRevokedBy());
        assertXPath(path + "/prop:grant-remark", obj
            .getProperties().getGrantRemark());
        assertXPath(path + "/prop:revocation-remark", obj
            .getProperties().getRevocationRemark());
        assertResource(path + "/srel:role", obj.getProperties().getRole());
        // xlink cannot be generated in case of SOAP
        assertResource(path + "/srel:assigned-on", obj
            .getProperties().getAssignedOn(), false);
    }

    @Override
    protected void testSubResources(final Grant obj) throws Exception {
        // TODO Auto-generated method stub
    }

    @Override
    protected void testResourceWithoutSubResources(final Grant obj)
        throws Exception {
        // TODO Auto-generated method stub
    }
}