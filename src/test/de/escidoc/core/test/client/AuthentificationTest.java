package de.escidoc.core.test.client;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import de.escidoc.core.client.Authentication;
import de.escidoc.core.client.exceptions.application.security.AuthenticationException;

/**
 * Test Authentication.
 * 
 * @author CHH
 * 
 */
public class AuthentificationTest {

    private static final String NON_EXISTING_LOGIN = "Non Existing login";

    /**
     * Test Authentication with a not existing login.
     * 
     * @throws Exception
     *             If behavior is not like expected
     */
    @Test(expected = AuthenticationException.class)
    public void shouldThrowAnAuthenticationExceptionAfterUnsuccesfulLogin()
        throws Exception {

        new Authentication().login(EscidocClientTestBase.DEFAULT_SERVICE_URL,
            NON_EXISTING_LOGIN, "");

    }

    /**
     * Check if handle is not empty after successful login
     * 
     * @throws Exception
     *             If behavior is not like expected
     */
    @Test
    public void shouldSetHandleAfterSuccesfulLogin() throws Exception {

        final Authentication auth =
            new Authentication(EscidocClientTestBase.DEFAULT_SERVICE_URL,
                Constants.SYSTEM_ADMIN_USER, Constants.SYSTEM_ADMIN_PASSWORD);
        assertNotNull("Handle is null", auth.getHandle());
    }
}