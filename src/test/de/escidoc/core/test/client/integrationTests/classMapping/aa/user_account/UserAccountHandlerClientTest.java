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
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Collection;

import org.junit.Test;

import de.escidoc.core.client.Authentication;
import de.escidoc.core.client.UserAccountHandlerClient;
import de.escidoc.core.client.exceptions.EscidocClientException;
import de.escidoc.core.client.exceptions.application.notfound.UserAccountNotFoundException;
import de.escidoc.core.common.jibx.Factory;
import de.escidoc.core.resources.aa.useraccount.UserAccount;
import de.escidoc.core.resources.aa.useraccount.UserAccountProperties;
import de.escidoc.core.resources.aa.useraccount.UserAccounts;
import de.escidoc.core.resources.common.Filter;
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

    /**
     * Test to create and retrieve user account.
     * 
     * @throws Exception
     *             If creation of user account happens not as expected
     */
    @Test
    public void testCreateUserAccount() throws Exception {

        UserAccount ua = createUserAccount();

        Authentication auth =
            new Authentication(EscidocClientTestBase.DEFAULT_SERVICE_URL,
                Constants.SYSTEM_ADMIN_USER, Constants.SYSTEM_ADMIN_PASSWORD);

        // login
        UserAccountHandlerClient uac = new UserAccountHandlerClient();
        uac.setServiceAddress(EscidocClientTestBase.DEFAULT_SERVICE_URL);
        uac.setHandle(auth.getHandle());

        // create
        UserAccount createdUa = uac.create(ua);

        // test marshalling
        String objId = createdUa.getObjid();
        Factory.getUserAccountMarshaller().marshalDocument(uac.retrieve(objId));
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

        Authentication auth =
            new Authentication(EscidocClientTestBase.DEFAULT_SERVICE_URL,
                Constants.SYSTEM_ADMIN_USER, Constants.SYSTEM_ADMIN_PASSWORD);

        // login
        UserAccountHandlerClient uac = new UserAccountHandlerClient();
        uac.setServiceAddress(EscidocClientTestBase.DEFAULT_SERVICE_URL);
        uac.setHandle(auth.getHandle());

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
        Factory.getUserAccountMarshaller().marshalDocument(updatedUserAccont);
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

        // login
        Authentication auth =
            new Authentication(EscidocClientTestBase.DEFAULT_SERVICE_URL,
                Constants.SYSTEM_ADMIN_USER, Constants.SYSTEM_ADMIN_PASSWORD);

        UserAccountHandlerClient uac = new UserAccountHandlerClient();
        uac.setServiceAddress(EscidocClientTestBase.DEFAULT_SERVICE_URL);
        uac.setHandle(auth.getHandle());

        UserAccount createdUa = uac.create(ua);

        // prepare task param for deactivation of user account
        TaskParam taskParam = new TaskParam();
        taskParam.setLastModificationDate(createdUa.getLastModificationDate());

        // FIXME there is something redundant! The objid has to be set in the
        // task param and in the method call?
        String objId = createdUa.getObjid();
        taskParam.setPid(objId);
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

        TaskParam filterParam = new TaskParam();
        Collection<Filter> filters = TaskParam.filtersFactory();

        filters.add(getFilter(
            "http://escidoc.de/core/01/structural-relations/created-by",
            "escidoc:user42", null));
        filterParam.setFilters(filters);

        Authentication auth =
            new Authentication(EscidocClientTestBase.DEFAULT_SERVICE_URL,
                Constants.SYSTEM_ADMIN_USER, Constants.SYSTEM_ADMIN_PASSWORD);

        // login
        UserAccountHandlerClient uac = new UserAccountHandlerClient();
        uac.setServiceAddress(EscidocClientTestBase.DEFAULT_SERVICE_URL);
        uac.setHandle(auth.getHandle());

        UserAccounts userAccountList = uac.retrieveUserAccounts(filterParam);

        Factory.getUserAccountListMarshaller().marshalDocument(userAccountList);
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

        Authentication auth =
            new Authentication(EscidocClientTestBase.DEFAULT_SERVICE_URL,
                Constants.SYSTEM_ADMIN_USER, Constants.SYSTEM_ADMIN_PASSWORD);

        // login
        UserAccountHandlerClient uac = new UserAccountHandlerClient();
        uac.setServiceAddress(EscidocClientTestBase.DEFAULT_SERVICE_URL);
        uac.setHandle(auth.getHandle());

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
    public void testRetrieveGrants() throws Exception {

        Authentication auth =
            new Authentication(EscidocClientTestBase.DEFAULT_SERVICE_URL,
                Constants.SYSTEM_ADMIN_USER, Constants.SYSTEM_ADMIN_PASSWORD);

        // login
        UserAccountHandlerClient uac = new UserAccountHandlerClient();
        uac.setServiceAddress(EscidocClientTestBase.DEFAULT_SERVICE_URL);
        uac.setHandle(auth.getHandle());

        UserAccount ua = createUserAccount();

        UserAccount createdUa = uac.create(ua);
        String objId = createdUa.getObjid();

        // FIXME there are no grant, that's why the test is bad  
        
        Factory.getGrantsMarshaller().marshalDocument(
            uac.retrieveCurrentGrants(objId));
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
    public void testUpdatePassword() throws Exception {

        Authentication auth =
            new Authentication(EscidocClientTestBase.DEFAULT_SERVICE_URL,
                Constants.SYSTEM_ADMIN_USER, Constants.SYSTEM_ADMIN_PASSWORD);

        // login
        UserAccountHandlerClient uac = new UserAccountHandlerClient();
        uac.setServiceAddress(EscidocClientTestBase.DEFAULT_SERVICE_URL);
        uac.setHandle(auth.getHandle());

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
            new Authentication(EscidocClientTestBase.DEFAULT_SERVICE_URL,
                Constants.SYSTEM_ADMIN_USER, Constants.SYSTEM_ADMIN_PASSWORD);

        UserAccountHandlerClient uac2 = new UserAccountHandlerClient();
        uac2.setServiceAddress(EscidocClientTestBase.DEFAULT_SERVICE_URL);
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

    /**
     * Prepare and Filter class from the parameter collection.
     * 
     * @param name
     * @param value
     * @param ids
     * @return Filter
     * 
     */
    // FIXME method is duplicated (see role handler)
    private Filter getFilter(
        final String name, final String value, Collection<String> ids) {

        Filter filter = new Filter();
        filter.setName(name);
        filter.setValue(value);
        filter.setIds(ids);
        return filter;
    }

}
