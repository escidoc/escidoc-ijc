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

import java.util.Arrays;
import java.util.Collection;

import gov.loc.www.zing.srw.SearchRetrieveRequestType;

import org.apache.axis.types.NonNegativeInteger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import de.escidoc.core.client.Authentication;
import de.escidoc.core.client.TransportProtocol;
import de.escidoc.core.client.UserAccountHandlerClient;
import de.escidoc.core.resources.aa.useraccount.UserAccount;
import de.escidoc.core.resources.aa.useraccount.UserAccountProperties;
import de.escidoc.core.resources.sb.search.SearchRetrieveResponse;
import de.escidoc.core.test.client.Constants;
import de.escidoc.core.test.client.EscidocClientTestBase;

/**
 * Test the CQL User Account Filter.
 * 
 * @author SWA
 * 
 */
@RunWith(Parameterized.class)
public class UserAccountFilterVersion12Test {

	private TransportProtocol transport;
	
	public UserAccountFilterVersion12Test(TransportProtocol transport) {
		this.transport = transport;
	}

	@SuppressWarnings("rawtypes")
    @Parameters
    public static Collection data() {
        return Arrays.asList(new Object[][] { { TransportProtocol.SOAP },
            { TransportProtocol.REST } });
    }
	
    /**
     * Test retrieving User Account through filter request (filter for version
     * 1.2).
     * 
     * @throws Exception
     *             Thrown if anythings failed.
     */
    @Test
    public void testRetrieveUserAccounts01() throws Exception {

        UserAccount ua = new UserAccount();

        // user properties
        UserAccountProperties properties = new UserAccountProperties();
        String login = "login" + System.currentTimeMillis();
        properties.setName("Name " + login);
        properties.setLoginName(login);

        ua.setProperties(properties);

        Authentication auth =
            new Authentication(EscidocClientTestBase.DEFAULT_SERVICE_URL,
                Constants.SYSTEM_ADMIN_USER, Constants.SYSTEM_ADMIN_PASSWORD);

        // login
        UserAccountHandlerClient uac = new UserAccountHandlerClient();
        uac.setServiceAddress(EscidocClientTestBase.DEFAULT_SERVICE_URL);
        uac.setHandle(auth.getHandle());
        uac.setTransport(transport);

        // create
        UserAccount createdUa = uac.create(ua);

        UserAccount me = uac.retrieveCurrentUser();

        SearchRetrieveRequestType srwFilter = new SearchRetrieveRequestType();
        srwFilter.setQuery(
        		"\"http://escidoc.de/core/01/structural-relations/created-by\"="
                + me.getObjid());
        srwFilter.setMaximumRecords(new NonNegativeInteger("1"));

        SearchRetrieveResponse response = uac.retrieveUserAccounts(srwFilter);
        
        Collection<UserAccount> userAccountList = 
        	uac.retrieveUserAccountsAsList(srwFilter);

        assertEquals("Wrong version number", "1.1", response
            .getVersion());
        assertTrue("Wrong number of matching records", response
            .getNumberOfMatchingRecords() >= 1);
        assertEquals("Wrong record position", 1, response.getRecords()
        		.iterator().next().getRecordPosition());
        assertTrue("Different resulting records", userAccountList.size() == 
        	response.getNumberOfResultingRecords());
    }
}
