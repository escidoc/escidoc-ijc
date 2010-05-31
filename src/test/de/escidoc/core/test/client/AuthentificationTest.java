package de.escidoc.core.test.client;

import static org.junit.Assert.assertNotNull;

import org.junit.Ignore;
import org.junit.Test;

import de.escidoc.core.client.Authentication;
import de.escidoc.core.client.exceptions.EscidocClientException;
import de.escidoc.core.client.exceptions.application.security.AuthenticationException;

public class AuthentificationTest {

    private static final String NON_EXISTING_USERNAME = "Non Existing username";

    // this test currently fails, uncomment @Ignore to let JUnit run the test.
    @Ignore
    @Test(expected = AuthenticationException.class)
    public void ShouldThrowAnExceptionAfterUnsuccesfulLogin()
        throws EscidocClientException {

        new Authentication().login(EscidocClientTestBase.DEFAULT_SERVICE_URL,
            NON_EXISTING_USERNAME, "");

    }

    @Test
    public void ShouldSetHanldeAfterSuccesfulLogin()
        throws EscidocClientException {
        final Authentication auth =
            new Authentication(EscidocClientTestBase.DEFAULT_SERVICE_URL,
                Constants.SYSTEM_ADMIN_USER, Constants.SYSTEM_ADMIN_PASSWORD);
        assertNotNull("Handle is null", auth.getHandle());

    }
}