package de.escidoc.core.test.client.integrationTests.RESTHandler.aa.user_account;

import java.io.File;

import org.junit.Test;

import de.escidoc.core.client.rest.RestUserAccountHandlerClient;
import de.escidoc.core.test.client.Constants;
import de.escidoc.core.test.client.EscidocClientTestBase;

/**
 * Test User Account via REST interface.
 * 
 * @author SWA
 * 
 */
public class UserAccountTestRest {

    /**
     * Test to create and retrieve user account.
     * 
     * @throws Exception
     *             Thrown if anythings failed.
     */
    @Test
    public void testCreateAndRetrieveSuccessfulUserAccount() throws Exception {

        RestUserAccountHandlerClient uahc = new RestUserAccountHandlerClient();
        uahc.setHandle(Constants.DEFAULT_HANDLE);

        // load XML template of organizational unit
        File templ =
            new File("./templates/rest/aa/user_account/"
                + "escidoc_useraccount_for_create.xml");
        String resourceXml = EscidocClientTestBase.getXmlFileAsString(templ);

        String cAccountXml = uahc.create(resourceXml);
    }
}
