/**
 * 
 */
package de.escidoc.core.test.client.integrationTests.classMapping.sm;

import static org.junit.Assert.fail;

import org.joda.time.DateTime;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.escidoc.core.client.Authentication;
import de.escidoc.core.client.ScopeHandlerClient;
import de.escidoc.core.client.StatisticDataHandlerClient;
import de.escidoc.core.client.exceptions.EscidocException;
import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.client.exceptions.TransportException;
import de.escidoc.core.resources.common.reference.ScopeRef;
import de.escidoc.core.resources.sm.DateParameter;
import de.escidoc.core.resources.sm.DecimalParameter;
import de.escidoc.core.resources.sm.StringParameter;
import de.escidoc.core.resources.sm.scope.Scope;
import de.escidoc.core.resources.sm.scope.ScopeType;
import de.escidoc.core.resources.sm.sd.StatisticData;
import de.escidoc.core.test.client.EscidocClientTestBase;

/**
 * @author MVO
 * 
 */
public class StatisticDataHandlerClientTest {

    private Authentication auth;

    private StatisticDataHandlerClient shc;

    private String scopeId;

    /**
     * 
     * @throws Exception
     */
    @Before
    public void init() throws Exception {
        auth =
            new Authentication(EscidocClientTestBase.getDefaultInfrastructureURL(),
                EscidocClientTestBase.SYSTEM_ADMIN_USER, EscidocClientTestBase.SYSTEM_ADMIN_PASSWORD);
        shc = new StatisticDataHandlerClient(auth.getServiceAddress());
        shc.setHandle(auth.getHandle());
        // create test scope
        scopeId = createScope(ScopeType.ADMIN);
        if (scopeId == null) {
            fail("Test initialization failed: scopeId is null.");
        }
    }

    /**
     * 
     * @throws Exception
     */
    @After
    public void post() throws Exception {
        if (auth != null) {
            auth.logout();
        }
    }

    /**
     * 
     * @throws Exception
     */
    @Test
    public void testCreate01() throws Exception {
        final StatisticData sd = new StatisticData(new ScopeRef(scopeId));
        sd.getParameters().add(new StringParameter("name01", "Test String"));
        sd.getParameters().add(new DecimalParameter("name02", new Float(10.5)));
        sd.getParameters().add(new DateParameter("name03", new DateTime()));

        shc.create(sd);
    }

    /**
     * 
     * @param type
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    private String createScope(final ScopeType type) throws EscidocException, InternalClientException,
        TransportException {
        final Scope scope = new Scope("AdminScope @" + System.currentTimeMillis(), type);

        final ScopeHandlerClient shc = new ScopeHandlerClient(auth.getServiceAddress());
        shc.setHandle(auth.getHandle());

        return shc.create(scope).getObjid();
    }
}
