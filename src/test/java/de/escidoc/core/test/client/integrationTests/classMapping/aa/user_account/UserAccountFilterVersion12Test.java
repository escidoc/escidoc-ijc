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
 * Copyright 2006-2008 Fachinformationszentrum Karlsruhe Gesellschaft
 * fuer wissenschaftlich-technische Information mbH and Max-Planck-
 * Gesellschaft zur Foerderung der Wissenschaft e.V.  
 * All rights reserved.  Use is subject to license terms.
 */
package de.escidoc.core.test.client.integrationTests.classMapping.aa.user_account;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import gov.loc.www.zing.srw.ExplainRequestType;
import gov.loc.www.zing.srw.SearchRetrieveRequestType;

import java.util.Collection;

import org.apache.axis.types.NonNegativeInteger;
import org.joda.time.DateTimeZone;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.escidoc.core.client.Authentication;
import de.escidoc.core.client.UserAccountHandlerClient;
import de.escidoc.core.client.interfaces.UserAccountHandlerClientInterface;
import de.escidoc.core.resources.aa.useraccount.UserAccount;
import de.escidoc.core.resources.aa.useraccount.UserAccountProperties;
import de.escidoc.core.resources.sb.explain.Explain;
import de.escidoc.core.resources.sb.explain.ExplainResponse;
import de.escidoc.core.resources.sb.search.SearchRetrieveResponse;
import de.escidoc.core.test.client.EscidocClientTestBase;

/**
 * Test the CQL User Account Filter.
 * 
 * @author SWA
 * 
 */
public class UserAccountFilterVersion12Test {

    private Authentication auth;

    private UserAccountHandlerClientInterface uac;

    @Before
    public void init() throws Exception {
        auth =
            new Authentication(EscidocClientTestBase.getDefaultInfrastructureURL(),
                EscidocClientTestBase.SYSTEM_ADMIN_USER, EscidocClientTestBase.SYSTEM_ADMIN_PASSWORD);
        uac = new UserAccountHandlerClient(auth.getServiceAddress());
        uac.setHandle(auth.getHandle());
    }

    @After
    public void post() throws Exception {
        if (auth != null)
            auth.logout();
    }

    /**
     * Test for User Account Explain. (filter for version 1.2).
     * 
     * @throws Exception
     *             Thrown if anythings failed.
     */
    @Test
    public void testExplain() throws Exception {

        UserAccount ua = new UserAccount();

        // user properties
        UserAccountProperties properties = new UserAccountProperties();
        String login = "login" + System.currentTimeMillis();
        properties.setName("Name " + login);
        properties.setLoginName(login);

        ua.setProperties(properties);

        // create
        uac.create(ua);

        ExplainResponse response = uac.retrieveUserAccounts(new ExplainRequestType());

        Explain explain = response.getRecord().getRecordData();

        assertEquals("Wrong version number", "1.1", response.getVersion());
        assertTrue("No index definitions found", explain.getIndexInfo().getIndexes().size() > 0);
    }

    /**
     * Test retrieving User Account through filter request (filter for version
     * 1.2).
     * 
     * @throws Exception
     *             Thrown if anythings failed.
     */
    @Test
    public void testFilter01() throws Exception {

        UserAccount ua = new UserAccount();

        // user properties
        UserAccountProperties properties = new UserAccountProperties();
        String login = "login" + System.currentTimeMillis();
        properties.setName("Name " + login);
        properties.setLoginName(login);

        ua.setProperties(properties);

        // create
        UserAccount createdUa = uac.create(ua);

        SearchRetrieveRequestType srwFilter = new SearchRetrieveRequestType();
        srwFilter.setQuery("\"http://escidoc.de/core/01/properties/creation-date\"=\""
            + createdUa.getProperties().getCreationDate().withZone(DateTimeZone.UTC) + "\"");
        srwFilter.setMaximumRecords(new NonNegativeInteger("1"));

        SearchRetrieveResponse response = uac.retrieveUserAccounts(srwFilter);

        Collection<UserAccount> userAccountList = uac.retrieveUserAccountsAsList(srwFilter);

        assertEquals("Wrong version number", "1.1", response.getVersion());
        assertTrue("Wrong number of matching records", response.getNumberOfMatchingRecords() >= 1);
        assertEquals("Wrong record position", 1, response.getRecords().iterator().next().getRecordPosition().intValue());
        assertTrue("Different resulting records", userAccountList.size() == response.getNumberOfResultingRecords());
    }
}
