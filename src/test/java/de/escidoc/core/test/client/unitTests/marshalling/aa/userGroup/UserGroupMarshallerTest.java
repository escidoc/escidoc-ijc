/**
 * 
 */
package de.escidoc.core.test.client.unitTests.marshalling.aa.userGroup;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import de.escidoc.core.client.TransportProtocol;
import de.escidoc.core.resources.aa.usergroup.UserGroup;
import de.escidoc.core.test.client.unitTests.marshalling.MarshallerTestBase;

/**
 * @author Marko Voß
 * 
 */
public class UserGroupMarshallerTest extends MarshallerTestBase<UserGroup> {

    private static final String BASE = "aa/usergroup";

    private static final String XSD = "0.6";

    /**
     * @param transport
     * @throws IOException
     * @throws ParserConfigurationException
     * @throws SAXException
     */
    public UserGroupMarshallerTest(final TransportProtocol transport) throws IOException, ParserConfigurationException,
        SAXException {
        super(UserGroup.class, BASE, XSD, "user-group_complete.xml", transport);
    }

    @Override
    protected void validate(final UserGroup obj) throws Exception {
        /*
         * TODO: LMDs for sub-resources
         */

        // UserGroup
        assertResource("/user-group:user-group", obj);
        assertDateTime("/user-group:user-group/@last-modification-date", obj.getLastModificationDate());

        // UserGroupProperties
        String path = "/user-group:user-group/user-group:properties";
        assertNotNull(obj.getProperties());

        assertDateTime(path + "/prop:creation-date", obj.getProperties().getCreationDate());
        assertResource(path + "/srel:created-by", obj.getProperties().getCreatedBy());
        assertResource(path + "/srel:modified-by", obj.getProperties().getModifiedBy());
        assertXPath(path + "/prop:email", obj.getProperties().getEmail());
        assertXPath(path + "/prop:name", obj.getProperties().getName());
        assertXPath(path + "/prop:label", obj.getProperties().getLabel());
        assertXPath(path + "/prop:description", obj.getProperties().getDescription());
        assertXPath(path + "/prop:type", obj.getProperties().getType());
        assertXPath(path + "/prop:active", obj.getProperties().isActive());

        // Selectors
        path = "/user-group:user-group/user-group:selectors/user-group:selector[1]";

        assertNotNull(obj.getSelectors());
        assertNotNull(obj.getSelectors().get(0));

        /*
         * it is not possible to generate the xlink:href on selector elements in
         * case of SOAP
         */
        assertResource(path, obj.getSelectors().get(0), false);

        assertXPath(path + "/@name", obj.getSelectors().get(0).getName());
        assertEnum(path + "/@type", obj.getSelectors().get(0).getType());
        assertXPath(path, obj.getSelectors().get(0).getContent());
    }

    @Override
    protected void testSubResources(final UserGroup obj) throws Exception {
        // TODO Auto-generated method stub
    }

    @Override
    protected void testResourceWithoutSubResources(final UserGroup obj) throws Exception {
        // TODO Auto-generated method stub
    }
}