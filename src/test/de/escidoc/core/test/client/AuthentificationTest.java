/*
 * CDDL HEADER START
 *
 * The contents of this file are subject to the terms of the
 * Common Development and Distribution License, Version 1.0 only
 * (the "License").  You may not use this file except in compliance
 * with the License.
 *
 * You can obtain a copy of the license at license/ESCIDOC.LICENSE
 * or http://www.escidoc.de/license.
 * See the License for the specific language governing permissions
 * and limitations under the License.
 *
 * When distributing Covered Code, include this CDDL HEADER in each
 * file and include the License file at license/ESCIDOC.LICENSE.
 * If applicable, add the following below this CDDL HEADER, with the
 * fields enclosed by brackets "[]" replaced with your own identifying
 * information: Portions Copyright [yyyy] [name of copyright owner]
 *
 * CDDL HEADER END
 */

/*
 * Copyright 2006-2010 Fachinformationszentrum Karlsruhe Gesellschaft
 * fuer wissenschaftlich-technische Information mbH and Max-Planck-
 * Gesellschaft zur Foerderung der Wissenschaft e.V.  
 * All rights reserved.  Use is subject to license terms.
 */
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