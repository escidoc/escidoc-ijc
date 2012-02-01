/**
 * 
 */
package de.escidoc.core.test.client.unitTests.marshalling.aa.userAccount;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import de.escidoc.core.resources.aa.useraccount.UserAccount;
import de.escidoc.core.test.client.unitTests.marshalling.MarshallerTestBase;

/**
 * @author Marko Voß
 * 
 */
public class UserAccountMarshallerTest extends MarshallerTestBase<UserAccount> {

    private static final String BASE = "aa/useraccount";

    private static final String XSD = "0.7";

    /**
     * @param transport
     * @throws IOException
     * @throws ParserConfigurationException
     * @throws SAXException
     */
    public UserAccountMarshallerTest() throws IOException, ParserConfigurationException, SAXException {
        super(UserAccount.class, BASE, XSD, "user-account_complete.xml");
    }

    @Override
    protected void validate(final UserAccount obj) throws Exception {
        /*
         * TODO: LMDs for sub-resources
         */

        // UserAccount
        assertResource("/user-account:user-account", obj);
        assertDateTime("/user-account:user-account/@last-modification-date", obj.getLastModificationDate());

        // UserAccountProperties
        final String path = "/user-account:user-account/user-account:properties";
        assertNotNull(obj.getProperties());

        assertDateTime(path + "/prop:creation-date", obj.getProperties().getCreationDate());
        assertResource(path + "/srel:created-by", obj.getProperties().getCreatedBy());
        assertResource(path + "/srel:modified-by", obj.getProperties().getModifiedBy());
        assertXPath(path + "/prop:name", obj.getProperties().getName());
        assertXPath(path + "/prop:login-name", obj.getProperties().getLoginName());
        assertXPath(path + "/prop:active", obj.getProperties().isActive());
    }

    @Override
    protected void testSubResources(final UserAccount obj) throws Exception {
        // TODO Auto-generated method stub
    }

    @Override
    protected void testResourceWithoutSubResources(final UserAccount obj) throws Exception {
        // TODO Auto-generated method stub
    }
}