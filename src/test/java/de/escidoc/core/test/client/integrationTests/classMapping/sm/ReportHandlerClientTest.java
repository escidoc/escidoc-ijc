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
import de.escidoc.core.client.ReportHandlerClient;
import de.escidoc.core.client.ScopeHandlerClient;
import de.escidoc.core.client.exceptions.EscidocException;
import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.client.exceptions.TransportException;
import de.escidoc.core.common.jibx.Marshaller;
import de.escidoc.core.common.jibx.MarshallerFactory;
import de.escidoc.core.resources.common.reference.ReportDefinitionRef;
import de.escidoc.core.resources.common.reference.ScopeRef;
import de.escidoc.core.resources.sm.report.Report;
import de.escidoc.core.resources.sm.report.ReportDefinition;
import de.escidoc.core.resources.sm.report.ReportParameters;
import de.escidoc.core.resources.sm.scope.Scope;
import de.escidoc.core.resources.sm.scope.ScopeType;
import de.escidoc.core.test.client.EscidocClientTestBase;

/**
 * @author MVO
 * 
 */
public class ReportHandlerClientTest {

    private Authentication auth;

    private ReportHandlerClient rhc;

    private String reportDefId;

    private String scopeId;

    /**
     * 
     * @throws Exception
     */
    @Before
    public void init() throws Exception {
        auth =
            new Authentication(
                EscidocClientTestBase.getDefaultInfrastructureURL(),
                EscidocClientTestBase.SYSTEM_ADMIN_USER,
                EscidocClientTestBase.SYSTEM_ADMIN_PASSWORD);
        rhc = new ReportHandlerClient(auth.getServiceAddress());
        rhc.setHandle(auth.getHandle());
        // create test scope
        scopeId = createScope(ScopeType.ADMIN);
        if (scopeId == null) {
            fail("Test initialization failed: scopeId is null.");
        }
        reportDefId =
            createReportDefinition("Report Name",
                "select day, month from escidocaggdef1_request_statistics;");
        if (reportDefId == null) {
            fail("Test initialization failed: reportDefId is null.");
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
    public void testRetrieve01() throws Exception {
        ReportParameters parameters =
            new ReportParameters(new ReportDefinitionRef(reportDefId));

        Marshaller<ReportParameters> m1 =
            MarshallerFactory.getInstance().getMarshaller(
                ReportParameters.class);

        Marshaller<Report> m2 =
            MarshallerFactory.getInstance().getMarshaller(Report.class);

        String xml = m1.marshalDocument(parameters);
        m1.unmarshalDocument(xml);

        Report report = rhc.retrieve(parameters);

        xml = m2.marshalDocument(report);
        m2.unmarshalDocument(xml);
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

        return shc.create(scope).getObjid();
    }

    /**
     * 
     * @param name
     * @param sql
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    private String createReportDefinition(final String name, final String sql)
        throws EscidocException, InternalClientException, TransportException {
        ReportDefinitionHandlerClient rdhc =
            new ReportDefinitionHandlerClient(auth.getServiceAddress());
        rdhc.setHandle(auth.getHandle());

        ReportDefinition rd =
            new ReportDefinition(name, new ScopeRef(scopeId), sql);
        return rdhc.create(rd).getObjid();
    }
}