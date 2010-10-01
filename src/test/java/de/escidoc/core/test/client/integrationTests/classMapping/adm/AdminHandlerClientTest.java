/**
 * 
 */
package de.escidoc.core.test.client.integrationTests.classMapping.adm;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import de.escidoc.core.client.AdminHandlerClient;
import de.escidoc.core.client.Authentication;
import de.escidoc.core.client.TransportProtocol;
import de.escidoc.core.client.interfaces.AdminHandlerClientInterface;
import de.escidoc.core.resources.adm.LoadExamplesResult;
import de.escidoc.core.resources.adm.LoadExamplesResult.Entry;
import de.escidoc.core.test.client.Constants;
import de.escidoc.core.test.client.EscidocClientTestBase;

/**
 * @author MVO
 * 
 * TODO Finish test implementation.
 * 
 */
public class AdminHandlerClientTest {

    /**
     * @throws Exception
     * 
     */
    @Test
    public void testLoadExamples() throws Exception {
        Authentication auth =
            new Authentication(EscidocClientTestBase.DEFAULT_SERVICE_URL,
                Constants.SYSTEM_ADMIN_USER, Constants.SYSTEM_ADMIN_PASSWORD);
        AdminHandlerClientInterface ahc = new AdminHandlerClient();
        ahc.setServiceAddress(EscidocClientTestBase.DEFAULT_SERVICE_URL);
        ahc.setHandle(auth.getHandle());
        ahc.setTransport(TransportProtocol.REST);

        LoadExamplesResult result = ahc.loadExamples();
        for (Entry entry : result) {
            assertNotNull("Objid is null", entry.getObjid());
            assertNotNull("ResourceType is null", entry.getResourceType());
            assertNotNull("Message is null", entry.getMessage());
        }
    }
}
