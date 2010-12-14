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
import de.escidoc.core.client.TransportProtocol;
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
import de.escidoc.core.test.client.AbstractParameterizedTestBase;
import de.escidoc.core.test.client.Constants;
import de.escidoc.core.test.client.EscidocClientTestBase;

/**
 * @author MVO
 * 
 */
public class StatisticDataHandlerClientTest
    extends AbstractParameterizedTestBase {

    private Authentication auth;

    private StatisticDataHandlerClient shc;

    private String scopeId;

    /**
     * 
     * @param transport
     */
    public StatisticDataHandlerClientTest(final TransportProtocol transport) {
        super(transport);
    }

    /**
     * 
     * @throws Exception
     */
    @Before
    public void init() throws Exception {
        auth =
            new Authentication(EscidocClientTestBase.DEFAULT_SERVICE_URL,
                Constants.SYSTEM_ADMIN_USER, Constants.SYSTEM_ADMIN_PASSWORD);
        shc = new StatisticDataHandlerClient(auth.getServiceAddress());
        shc.setHandle(auth.getHandle());
        shc.setTransport(transport);
        // create test scope
        scopeId = createScope(ScopeType.admin);
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
        StatisticData sd = new StatisticData(new ScopeRef(scopeId));
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
    private String createScope(final ScopeType type) throws EscidocException,
        InternalClientException, TransportException {
        Scope scope =
            new Scope("AdminScope @" + System.currentTimeMillis(), type);

        ScopeHandlerClient shc =
            new ScopeHandlerClient(auth.getServiceAddress());
        shc.setHandle(auth.getHandle());
        shc.setTransport(transport);

        return shc.create(scope).getObjid();
    }
}
