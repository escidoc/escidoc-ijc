/**
 * 
 */
package de.escidoc.core.test.client.integrationTests.classMapping.sm;

import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.escidoc.core.client.AggregationDefinitionHandlerClient;
import de.escidoc.core.client.Authentication;
import de.escidoc.core.client.ReportDefinitionHandlerClient;
import de.escidoc.core.client.ScopeHandlerClient;
import de.escidoc.core.client.TransportProtocol;
import de.escidoc.core.client.exceptions.EscidocException;
import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.client.exceptions.TransportException;
import de.escidoc.core.resources.common.reference.ScopeRef;
import de.escidoc.core.resources.sm.ad.AggregationDefinition;
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

    private String adId;

    private String adTableName = "report_test";

    private static final String AD_NAME = "Page Statistics for PubMan";

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
        // prepare ad
        String[] tmp = createAD();
        adId = tmp[0];
        adTableName = tmp[1];
        if (adId == null || adTableName == null)
            fail("Unable to initialze test case. AdId or adTableName is null.");
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

        ReportDefinition rd =
            new ReportDefinition("Report Name", new ScopeRef(scopeId),
                "select page,year,sum(requests) as requests,sum(sessions) as sessions"
                    + " from " + adTableName + " where page='createItem'"
                    + " group by page,year order by page, year;");
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

    /**
     * 
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    private String[] createAD() throws EscidocException,
        InternalClientException, TransportException {

        AggregationDefinitionHandlerClient ahc =
            new AggregationDefinitionHandlerClient(auth.getServiceAddress());
        ahc.setHandle(auth.getHandle());
        ahc.setTransport(transport);

        AggregationDefinition a =
            ahc.create(AggregationDefinitionHandlerClientTest.defineValidAD(
                AD_NAME, adTableName, scopeId));

        if (a.getAggregationTables().size() == 0)
            fail("Missing aggregation table.");

        return new String[] { a.getObjid(),
            a.getAggregationTables().iterator().next().getName() };
    }
}
