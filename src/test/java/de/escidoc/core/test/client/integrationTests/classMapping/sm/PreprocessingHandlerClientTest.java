/**
 * 
 */
package de.escidoc.core.test.client.integrationTests.classMapping.sm;

import org.joda.time.DateTime;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.escidoc.core.client.Authentication;
import de.escidoc.core.client.PreprocessingHandlerClient;
import de.escidoc.core.client.TransportProtocol;
import de.escidoc.core.common.jibx.Marshaller;
import de.escidoc.core.common.jibx.MarshallerFactory;
import de.escidoc.core.resources.sm.preprocess.PreprocessingInformation;
import de.escidoc.core.test.client.AbstractParameterizedTestBase;
import de.escidoc.core.test.client.Constants;
import de.escidoc.core.test.client.EscidocClientTestBase;

/**
 * @author MVO
 * 
 */
public class PreprocessingHandlerClientTest
    extends AbstractParameterizedTestBase {

    private Authentication auth;

    private PreprocessingHandlerClient phc;

    /**
     * 
     * @param transport
     */
    public PreprocessingHandlerClientTest(final TransportProtocol transport) {
        super(transport);
    }

    @Before
    public void init() throws Exception {
        auth =
            new Authentication(EscidocClientTestBase.DEFAULT_SERVICE_URL,
                Constants.SYSTEM_ADMIN_USER, Constants.SYSTEM_ADMIN_PASSWORD);
        phc = new PreprocessingHandlerClient(auth.getServiceAddress());
        phc.setHandle(auth.getHandle());
        phc.setTransport(transport);
    }

    @After
    public void post() throws Exception {
        if (auth != null)
            auth.logout();
    }

    /**
     * TODO implement test case instead of marshalling-test.
     * 
     * @throws Exception
     */
    @Test
    public void testPreprocessing() throws Exception {
        Marshaller<PreprocessingInformation> m =
            MarshallerFactory.getInstance(transport).getMarshaller(
                PreprocessingInformation.class);

        DateTime today = new DateTime();
        DateTime tomorrow = today.plusDays(1);

        PreprocessingInformation info =
            new PreprocessingInformation(today, tomorrow);

        String xml = m.marshalDocument(info);
        m.unmarshalDocument(xml);
    }
}