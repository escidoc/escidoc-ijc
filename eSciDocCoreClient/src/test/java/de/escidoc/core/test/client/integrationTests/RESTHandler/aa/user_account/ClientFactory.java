package de.escidoc.core.test.client.integrationTests.RESTHandler.aa.user_account;

import de.escidoc.core.client.Authentication;
import de.escidoc.core.client.TransportProtocol;
import de.escidoc.core.client.UserAccountHandlerClient;
import de.escidoc.core.client.exceptions.EscidocClientException;
import de.escidoc.core.test.client.Constants;
import de.escidoc.core.test.client.EscidocClientTestBase;

public class ClientFactory {

    public static UserAccountHandlerClient createRestClientForUserAccount()
        throws EscidocClientException {
        final Authentication auth =
            new Authentication(EscidocClientTestBase.DEFAULT_SERVICE_URL,
                Constants.SYSTEM_ADMIN_USER, Constants.SYSTEM_ADMIN_PASSWORD);
        final UserAccountHandlerClient client =
            new UserAccountHandlerClient(
                EscidocClientTestBase.DEFAULT_SERVICE_URL);
        client.setTransport(TransportProtocol.REST);
        client.setHandle(auth.getHandle());
        return client;
    }
}
