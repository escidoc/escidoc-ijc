/**
 * 
 */
package de.escidoc.core.test.client.integrationTests.classMapping.sm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.escidoc.core.client.Authentication;
import de.escidoc.core.client.ScopeHandlerClient;
import de.escidoc.core.client.TransportProtocol;
import de.escidoc.core.resources.sm.Scope;
import de.escidoc.core.resources.sm.Scope.ScopeType;
import de.escidoc.core.test.client.AbstractParameterizedTestBase;
import de.escidoc.core.test.client.Constants;
import de.escidoc.core.test.client.EscidocClientTestBase;

/**
 * @author MVO
 * 
 */
public class ScopeHandlerClientTest extends AbstractParameterizedTestBase {

    private Authentication auth;

    private ScopeHandlerClient shc;

    /**
     * 
     * @param transport
     */
    public ScopeHandlerClientTest(final TransportProtocol transport) {
        super(transport);
    }

    @Before
    public void init() throws Exception {
        auth =
            new Authentication(EscidocClientTestBase.DEFAULT_SERVICE_URL,
                Constants.SYSTEM_ADMIN_USER, Constants.SYSTEM_ADMIN_PASSWORD);
        shc = new ScopeHandlerClient(auth.getServiceAddress());
        shc.setHandle(auth.getHandle());
        shc.setTransport(transport);
    }

    @After
    public void post() throws Exception {
        auth.logout();
    }

    /**
     * REST case fails: See INFR-1028.
     * 
     * @throws Exception
     */
    @Test
    public void testCreateScope01() throws Exception {
        Scope normalScope =
            new Scope("NormalScope @ " + System.currentTimeMillis(),
                ScopeType.normal);

        Scope adminScope =
            new Scope("AdminScope @ " + System.currentTimeMillis(),
                ScopeType.admin);

        Scope resultScope = shc.create(normalScope);

        assertNotNull("Objid should not be null", resultScope.getObjid());
        assertEquals("Name is not equals", normalScope.getName(),
            resultScope.getName());
        assertEquals("ScopeType is not equals", normalScope.getScopeType(),
            resultScope.getScopeType());

        resultScope = shc.create(adminScope);

        assertNotNull("Objid should not be null", resultScope.getObjid());
        assertEquals("Name is not equals", adminScope.getName(),
            resultScope.getName());
        assertEquals("ScopeType is not equals", adminScope.getScopeType(),
            resultScope.getScopeType());
    }
}
