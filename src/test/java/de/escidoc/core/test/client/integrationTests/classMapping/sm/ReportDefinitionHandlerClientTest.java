/**
 * 
 */
package de.escidoc.core.test.client.integrationTests.classMapping.sm;

import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.escidoc.core.client.Authentication;
import de.escidoc.core.client.ReportDefinitionHandlerClient;
import de.escidoc.core.client.ScopeHandlerClient;
import de.escidoc.core.client.TransportProtocol;
import de.escidoc.core.client.exceptions.EscidocException;
import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.client.exceptions.TransportException;
import de.escidoc.core.resources.common.reference.ScopeRef;
import de.escidoc.core.resources.sm.report.ReportDefinition;
import de.escidoc.core.resources.sm.scope.Scope;
import de.escidoc.core.resources.sm.scope.ScopeType;
import de.escidoc.core.test.client.AbstractParameterizedTestBase;
import de.escidoc.core.test.client.Constants;
import de.escidoc.core.test.client.EscidocClientTestBase;

/**
 * @author MVO
 * 
 */
public class ReportDefinitionHandlerClientTest
    extends AbstractParameterizedTestBase {

    private Authentication auth;

    private ReportDefinitionHandlerClient rhc;

    private String scopeId;

    /**
     * 
     * @param transport
     */
    public ReportDefinitionHandlerClientTest(final TransportProtocol transport) {
        super(transport);
    }

    @Before
    public void init() throws Exception {
        auth =
            new Authentication(EscidocClientTestBase.DEFAULT_SERVICE_URL,
                Constants.SYSTEM_ADMIN_USER, Constants.SYSTEM_ADMIN_PASSWORD);
        rhc = new ReportDefinitionHandlerClient(auth.getServiceAddress());
        rhc.setHandle(auth.getHandle());
        rhc.setTransport(transport);

        // prepare scope
        scopeId = createScope(ScopeType.admin);
        if (scopeId == null)
            fail("Unable to initialze test case. ScopeId is null.");
    }

    @After
    public void post() throws Exception {
        if (auth != null)
            auth.logout();
    }

    /**
     * 
     * @throws Exception
     */
    @Test
    public void testCreate01() throws Exception {

        // RoleHandlerClientInterface c = new RoleHandlerClient();
        // c.setHandle(auth.getHandle());
        // c.setTransport(transport);

        ReportDefinition rd =
            new ReportDefinition("Report Name", new ScopeRef(scopeId),
                "select day, month from _escidocaggdef1_request_statistics;");
        rhc.create(rd);
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
