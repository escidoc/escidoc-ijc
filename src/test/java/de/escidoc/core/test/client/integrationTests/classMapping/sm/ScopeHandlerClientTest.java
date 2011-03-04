/**
 * 
 */
package de.escidoc.core.test.client.integrationTests.classMapping.sm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import de.escidoc.core.client.AggregationDefinitionHandlerClient;
import de.escidoc.core.client.Authentication;
import de.escidoc.core.client.ScopeHandlerClient;
import de.escidoc.core.resources.sm.scope.Scope;
import de.escidoc.core.resources.sm.scope.ScopeType;
import de.escidoc.core.test.client.EscidocClientTestBase;

/**
 * @author MVO
 * 
 */
public class ScopeHandlerClientTest {

    private Authentication auth;

    private ScopeHandlerClient shc;

    @Before
    public void init() throws Exception {
        auth =
            new Authentication(EscidocClientTestBase.getDefaultInfrastructureURL(),
                EscidocClientTestBase.SYSTEM_ADMIN_USER, EscidocClientTestBase.SYSTEM_ADMIN_PASSWORD);
        shc = new ScopeHandlerClient(auth.getServiceAddress());
        shc.setHandle(auth.getHandle());
    }

    @After
    public void post() throws Exception {
        if (auth != null)
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
                ScopeType.NORMAL);

        Scope adminScope =
            new Scope("AdminScope @ " + System.currentTimeMillis(),
                ScopeType.ADMIN);

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

    /**
     * 
     * @throws Exception
     */
    @Test
    public void testDelete01() throws Exception {
        Scope normalScope =
            new Scope("NormalScope @ " + System.currentTimeMillis(),
                ScopeType.NORMAL);
        Scope createdScope = shc.create(normalScope);

        assertNotNull("Objid should not be null.", createdScope.getObjid());

        shc.delete(createdScope.getObjid());
    }

    /**
     * See #INFR-1044.
     * 
     * @throws Exception
     */
    @Ignore
    @Test
    public void testDelete02() throws Exception {
        Scope normalScope =
            new Scope("NormalScope @ " + System.currentTimeMillis(),
                ScopeType.NORMAL);
        Scope createdScope = shc.create(normalScope);

        assertNotNull("Objid should not be null.", createdScope.getObjid());

        // Create an aggregation definition referring to this scope.
        AggregationDefinitionHandlerClient c =
            new AggregationDefinitionHandlerClient(
                EscidocClientTestBase.getDefaultInfrastructureURL());
        c.setHandle(auth.getHandle());

        c.create(AggregationDefinitionHandlerClientTest.defineValidAD(
            "ad-test", "test_table", createdScope.getObjid()));

        // delete scope
        shc.delete(createdScope.getObjid());
    }
}
