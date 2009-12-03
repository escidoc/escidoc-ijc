package de.escidoc.core.test.client.rest.aa.user_account;

import java.io.File;

import org.apache.log4j.Logger;

import de.escidoc.core.client.rest.RestUserAccountHandlerClient;
import de.escidoc.core.test.client.EscidocClientTestBase;

/**
 * Test User Account via REST interface.
 * 
 * @author SWA
 * 
 */
public class UserAccountTestRest extends EscidocClientTestBase {

    private final Logger logger =
        Logger.getLogger(UserAccountTestRest.class.getName());

    /**
     * Test to create and retrieve user account.
     * 
     * @throws Exception
     *             Thrown if anythings failed.
     */
    public void testCreateAndRetrieveSuccessfulUserAccount() throws Exception {

        RestUserAccountHandlerClient uahc = new RestUserAccountHandlerClient();
        uahc.setHandle(EscidocClientTestBase.DEFAULT_HANDLE);

        // load XML template of organizational unit
        File templ =
            new File("./templates/rest/aa/user_account/"
                + "escidoc_useraccount_for_create.xml");
        String resourceXml = getXmlFileAsString(templ);

        String cAccountXml = uahc.create(resourceXml);
    }
}
