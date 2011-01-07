package de.escidoc.core.test.client.integrationTests.RESTHandler.aa.user_account;

import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.escidoc.core.client.Authentication;
import de.escidoc.core.client.TransportProtocol;
import de.escidoc.core.client.exceptions.EscidocClientException;
import de.escidoc.core.client.exceptions.EscidocException;
import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.client.exceptions.TransportException;
import de.escidoc.core.client.rest.RestUserAccountHandlerClient;
import de.escidoc.core.common.jibx.Marshaller;
import de.escidoc.core.common.jibx.MarshallerFactory;
import de.escidoc.core.resources.aa.useraccount.UserAccount;
import de.escidoc.core.resources.aa.useraccount.UserAccountProperties;
import de.escidoc.core.test.client.Constants;
import de.escidoc.core.test.client.EscidocClientTestBase;

public class RetrieveUserAccountsTest {

    private Authentication auth;

    private RestUserAccountHandlerClient uahc;

    @Before
    public void init() throws Exception {
        auth =
            new Authentication(EscidocClientTestBase.DEFAULT_SERVICE_URL,
                Constants.SYSTEM_ADMIN_USER, Constants.SYSTEM_ADMIN_PASSWORD);
        uahc = new RestUserAccountHandlerClient(auth.getServiceAddress());
        uahc.setHandle(auth.getHandle());
    }

    @After
    public void post() throws Exception {
        auth.logout();
    }

    @Test
    public void ShouldReturnUserAccount() throws InternalClientException,
        TransportException, EscidocClientException {

        String objid = createTestUserAccount();
        assertNotNull(objid);

        final String createdUser = uahc.retrieve(objid);

        Marshaller<UserAccount> m =
            MarshallerFactory.getInstance(TransportProtocol.REST)
                .getMarshaller(UserAccount.class);
        UserAccount user = m.unmarshalDocument(createdUser);
        assertNotNull("Object Id should not be null. ", user.getObjid());
    }

    /**
     * 
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    private String createTestUserAccount() throws EscidocException,
        InternalClientException, TransportException {

        UserAccount ua = new UserAccount();
        UserAccountProperties properties = new UserAccountProperties();
        String login = "login" + System.currentTimeMillis();
        properties.setName("Name " + login);
        properties.setLoginName(login);
        ua.setProperties(properties);

        Marshaller<UserAccount> m =
            MarshallerFactory.getInstance(TransportProtocol.REST)
                .getMarshaller(UserAccount.class);
        String xml = m.marshalDocument(ua);

        xml = uahc.create(xml);

        UserAccount createdUser = m.unmarshalDocument(xml);
        return createdUser.getObjid();
    }
}