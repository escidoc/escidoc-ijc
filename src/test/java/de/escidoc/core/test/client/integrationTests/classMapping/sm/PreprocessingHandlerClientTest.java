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
import de.escidoc.core.common.jibx.Marshaller;
import de.escidoc.core.common.jibx.MarshallerFactory;
import de.escidoc.core.resources.sm.preprocess.PreprocessingInformation;
import de.escidoc.core.test.client.EscidocClientTestBase;

/**
 * @author MVO
 * 
 */
public class PreprocessingHandlerClientTest {

    private Authentication auth;

    private PreprocessingHandlerClient phc;

    @Before
    public void init() throws Exception {
        auth =
            new Authentication(EscidocClientTestBase.getDefaultInfrastructureURL(),
                EscidocClientTestBase.SYSTEM_ADMIN_USER, EscidocClientTestBase.SYSTEM_ADMIN_PASSWORD);
        phc = new PreprocessingHandlerClient(auth.getServiceAddress());
        phc.setHandle(auth.getHandle());
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
            MarshallerFactory.getInstance().getMarshaller(
                PreprocessingInformation.class);

        DateTime today = new DateTime();
        DateTime tomorrow = today.plusDays(1);

        PreprocessingInformation info =
            new PreprocessingInformation(today, tomorrow);

        String xml = m.marshalDocument(info);
        m.unmarshalDocument(xml);
    }
}