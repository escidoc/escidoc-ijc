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
package de.escidoc.core.test.client.integrationTests.classMapping.aa.user_account;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import gov.loc.www.zing.srw.SearchRetrieveRequestType;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.escidoc.core.client.Authentication;
import de.escidoc.core.client.UserAccountHandlerClient;
import de.escidoc.core.client.exceptions.EscidocClientException;
import de.escidoc.core.client.exceptions.application.notfound.UserAccountNotFoundException;
import de.escidoc.core.client.interfaces.UserAccountHandlerClientInterface;
import de.escidoc.core.common.jibx.MarshallerFactory;
import de.escidoc.core.resources.aa.useraccount.Grants;
import de.escidoc.core.resources.aa.useraccount.UserAccount;
import de.escidoc.core.resources.aa.useraccount.UserAccountProperties;
import de.escidoc.core.resources.common.TaskParam;
import de.escidoc.core.test.client.Constants;
import de.escidoc.core.test.client.EscidocClientTestBase;

/**
 * Test client lib user account handler.
 * 
 * @author ROF, SWA
 * 
 */
public class UserAccountHandlerClientTest {

    private Authentication auth;

    private UserAccountHandlerClientInterface uac;

    @Before
    public void init() throws Exception {
        auth =
            new Authentication(EscidocClientTestBase.DEFAULT_SERVICE_URL,
                Constants.SYSTEM_ADMIN_USER, Constants.SYSTEM_ADMIN_PASSWORD);
        uac = new UserAccountHandlerClient(auth.getServiceAddress());
        uac.setHandle(auth.getHandle());
    }

    @After
    public void post() throws Exception {
        if (auth != null)
            auth.logout();
    }

    /**
     * Test to create and retrieve user account.
     * 
     * @throws Exception
     *             If creation of user account happens not as expected
     */
    @Test
    public void testCreateUserAccount() throws Exception {

        UserAccount ua = createUserAccount();

        // create
        UserAccount createdUa = uac.create(ua);

        // test marshalling
        String objId = createdUa.getObjid();
        MarshallerFactory
            .getInstance(uac.getTransport()).getMarshaller(UserAccount.class)
            .marshalDocument(uac.retrieve(objId));
    }

    /**
     * Test if lib handles a create of an user account well if the UserAccount
     * is null.
     * 
     * @throws Exception
     *             If creation of user account happens not as expected
     */
    @Test(expected = IllegalArgumentException.class)
    public void createUserAccountWithNull() throws Exception {
        // create
        uac.create(null);
    }

    /**
     * Test to update an user account.
     * 
     * @throws Exception
     *             If update behavior is not as expected
     */
    @Test
    public void testUpdateUserAccount() throws Exception {

        UserAccount ua = createUserAccount();

        UserAccount createdUa = uac.create(ua);

        // alter Properties
        UserAccountProperties properties = createdUa.getProperties();

        properties.setName("new Name");
        String newLoginName = getUniqueLoginName();
        properties.setLoginName(newLoginName);

        // update at infrastructure
        UserAccount updatedUserAccont = uac.update(createdUa);

        UserAccountProperties updatedProperties =
            updatedUserAccont.getProperties();

        assertEquals("new Name", updatedProperties.getName());
        assertEquals(newLoginName, updatedProperties.getLoginName());

        // test marshalling
        MarshallerFactory
            .getInstance(uac.getTransport()).getMarshaller(UserAccount.class)
            .marshalDocument(updatedUserAccont);
    }

    /**
     * Test create an user account and test deactivation and activation of the
     * account.
     * 
     * @throws EscidocClientException
     *             If an error occurs at framework, transport or client lib
     *             level
     */
    @Test
    public void testDeactivateAndActivate() throws EscidocClientException {

        UserAccount ua = createUserAccount();

        UserAccount createdUa = uac.create(ua);

        // prepare task param for deactivation of user account
        TaskParam taskParam = new TaskParam();
        taskParam.setLastModificationDate(createdUa.getLastModificationDate());

        String objId = createdUa.getObjid();
        uac.deactivate(objId, taskParam);

        // check user account
        UserAccount deactivatedUserAccount = uac.retrieve(objId);
        UserAccountProperties properties =
            deactivatedUserAccount.getProperties();

        assertFalse(properties.isActive());

        // activate user account
        taskParam.setLastModificationDate(deactivatedUserAccount
            .getLastModificationDate());
        uac.activate(objId, taskParam);

        // check activated user account
        UserAccount reactivatedUserAccount = uac.retrieve(objId);
        UserAccountProperties propertiesReactivated =
            reactivatedUserAccount.getProperties();

        assertTrue(propertiesReactivated.isActive());
    }

    /**
     * Test retrieving RetrieveUserAccounts through filter request.
     * 
     * @throws Exception
     *             If retrieve or marshalling fail
     */
    @Test
    public void testRetrieveUserAccounts() throws Exception {

        SearchRetrieveRequestType request = new SearchRetrieveRequestType();
        request
            .setQuery("\"http://escidoc.de/core/01/structural-relations/created-by\"=escidoc:user42");

        List<UserAccount> userAccountList =
            uac.retrieveUserAccountsAsList(request);

        assertNotNull("No user account list returned.", userAccountList);
    }

    /**
     * Test to delete an user account.
     * 
     * @throws Exception
     *             If deletion of user account happens not as expected
     */
    @Test
    public void testDeleteUserAccount() throws Exception {

        UserAccount ua = createUserAccount();

        // create
        UserAccount createdUa = uac.create(ua);

        uac.delete(createdUa.getObjid());

        try {
            uac.retrieve(createdUa.getObjid());
            fail("User Account still exists after delete.");
        }
        catch (UserAccountNotFoundException e) {
            return;
        }

    }

    /**
     * Test to create and retrieve user account.
     * 
     * @throws Exception
     *             If retrieve or marshalling fail
     */
    public void retrieveGrants() throws Exception {

        UserAccount ua = createUserAccount();

        UserAccount createdUa = uac.create(ua);
        String objId = createdUa.getObjid();

        // FIXME there are no grant, that's why the test is bad

        MarshallerFactory
            .getInstance(uac.getTransport()).getMarshaller(Grants.class)
            .marshalDocument(uac.retrieveCurrentGrants(objId));
    }

    /**
     * Test update of user password (This is restricted to internal database. An
     * password update is impossible through this method if Shibboleth or LDAP
     * is used.)
     * 
     * @throws Exception
     *             If update of password failed
     */
    @Test
    public void updatePassword() throws Exception {

        UserAccount ua = createUserAccount();
        UserAccount createdUa = uac.create(ua);

        final String objId = createdUa.getObjid();

        final String login = createdUa.getProperties().getLoginName();
        final String password = String.valueOf(System.nanoTime());

        TaskParam taskParam = new TaskParam();
        taskParam.setLastModificationDate(createdUa.getLastModificationDate());
        taskParam.setPassword(password);

        uac.updatePassword(objId, taskParam);

        // check login with a new password
        // it assumed that a user is allowed to retrieve its own account
        Authentication auth2 =
            new Authentication(auth.getServiceAddress(), login, password);

        UserAccountHandlerClientInterface uac2 =
            new UserAccountHandlerClient(auth.getServiceAddress());
        uac2.setHandle(auth2.getHandle());

        uac2.retrieve(objId);
    }

    /**
     * Create an UserAccount object (not created at infrastructure).
     * 
     * @return UserAccount
     */
    private UserAccount createUserAccount() {

        UserAccount ua = new UserAccount();

        // user properties
        UserAccountProperties properties = new UserAccountProperties();
        String login = getUniqueLoginName();
        properties.setName("Name " + login);
        properties.setLoginName(login);

        ua.setProperties(properties);

        return ua;
    }

    /**
     * Get unique login name.
     * 
     * @return unique login name
     */
    private String getUniqueLoginName() {

        return "login" + System.currentTimeMillis();
    }
}