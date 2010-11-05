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
import de.escidoc.core.client.ScopeHandlerClient;
import de.escidoc.core.client.TransportProtocol;
import de.escidoc.core.client.exceptions.EscidocException;
import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.client.exceptions.TransportException;
import de.escidoc.core.resources.sm.Scope;
import de.escidoc.core.resources.sm.Scope.ScopeType;
import de.escidoc.core.test.client.AbstractParameterizedTestBase;
import de.escidoc.core.test.client.Constants;
import de.escidoc.core.test.client.EscidocClientTestBase;

/**
 * @author MVO
 * 
 */
public class AggregationDefinitionHandlerClientTest
    extends AbstractParameterizedTestBase {

    private Authentication auth;

    private AggregationDefinitionHandlerClient adhc;

    private String scopeId;

    private static final String SCOPE_ADMIN_ID = "escidoc:scope2";

    private static final String SCOPE_NORMAL_ID = "escidoc:scope1";

    public AggregationDefinitionHandlerClientTest(TransportProtocol transport) {
        super(transport);
    }

    @Before
    public void init() throws Exception {
        auth =
            new Authentication(EscidocClientTestBase.DEFAULT_SERVICE_URL,
                Constants.SYSTEM_ADMIN_USER, Constants.SYSTEM_ADMIN_PASSWORD);
        adhc = new AggregationDefinitionHandlerClient(auth.getServiceAddress());
        adhc.setHandle(auth.getHandle());
        adhc.setTransport(transport);

        // prepare scope
        scopeId = createScope(ScopeType.admin);
        if (scopeId == null)
            fail("Unable to initialze test case. ScopeId is null.");
    }

    @After
    public void post() throws Exception {
        auth.logout();
    }

    /**
     * 
     * @throws Exception
     */
    @Test
    public void testCreate() throws Exception {
        String xml =
            "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
                + "<aggregation-definition:aggregation-definition xmlns:aggregation-definition=\"http://www.escidoc.de/schemas/aggregationdefinition/0.3\">"
                + "<aggregation-definition:name>AD Test "
                + System.currentTimeMillis()
                + "</aggregation-definition:name>"
                + "<aggregation-definition:scope objid=\""
                + scopeId
                + "\"/>"
                +

                "<aggregation-definition:aggregation-table>"
                + "<aggregation-definition:name>AD_Test_Tablename</aggregation-definition:name>"
                + "<aggregation-definition:field>"
                + "<aggregation-definition:info-field feed=\"statistics-data\">"
                + "<aggregation-definition:name>Test_Info_Field</aggregation-definition:name>"
                + "<aggregation-definition:type>numeric</aggregation-definition:type>"
                + "<aggregation-definition:xpath></aggregation-definition:xpath>"
                + "</aggregation-definition:info-field>"
                + "</aggregation-definition:field>"
                + "<aggregation-definition:field>"
                + "<aggregation-definition:info-field feed=\"statistics-data\">"
                + "<aggregation-definition:name>Test_Info_Field2</aggregation-definition:name>"
                + "<aggregation-definition:type>text</aggregation-definition:type>"
                + "<aggregation-definition:xpath></aggregation-definition:xpath>"
                + "</aggregation-definition:info-field>"
                + "</aggregation-definition:field>"
                +

                "<aggregation-definition:index>"
                + "<aggregation-definition:name>Index_Name_Test</aggregation-definition:name>"
                + "<aggregation-definition:field>Test_Info_Field</aggregation-definition:field>"
                + "<aggregation-definition:field>Test_Info_Field2</aggregation-definition:field>"
                + "</aggregation-definition:index>"
                + "</aggregation-definition:aggregation-table>"

                + "<aggregation-definition:statistic-data>"
                + "<aggregation-definition:statistic-table>"
                + "<aggregation-definition:xpath></aggregation-definition:xpath>"
                + "</aggregation-definition:statistic-table>"
                + "</aggregation-definition:statistic-data>"

                + "</aggregation-definition:aggregation-definition>";

        System.out.println(xml);
        
        System.out.println(adhc.create(xml));
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
