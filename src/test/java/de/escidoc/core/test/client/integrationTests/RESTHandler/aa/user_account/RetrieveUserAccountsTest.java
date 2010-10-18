package de.escidoc.core.test.client.integrationTests.RESTHandler.aa.user_account;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Collection;

import org.junit.Before;
import org.junit.Test;

import de.escidoc.core.client.UserAccountHandlerClient;
import de.escidoc.core.client.exceptions.EscidocClientException;
import de.escidoc.core.client.exceptions.EscidocException;
import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.client.exceptions.TransportException;
import de.escidoc.core.resources.aa.useraccount.UserAccount;
import de.escidoc.core.resources.aa.useraccount.UserAccounts;
import de.escidoc.core.resources.common.Filter;
import de.escidoc.core.resources.common.TaskParam;

public class RetrieveUserAccountsTest {
    private static final String SYSADMIN_OBJECT_ID = "escidoc:exuser1";

    private UserAccountHandlerClient client;

    @Before
    public void setUp() throws EscidocClientException {
        initUserAccountRestClient();
        craeteTestUserAccount();
    }

    private void craeteTestUserAccount() throws EscidocException,
        InternalClientException, TransportException {

        final UserAccount fakeUserAccount =
            FakeUserAccountFactory.createFakeUserAccount();

        client.create(fakeUserAccount);
    }

    private void initUserAccountRestClient() throws EscidocClientException {
        client = ClientFactory.createRestClientForUserAccount();
    }

    @Test
    public void ShouldReturnAllUserAccounts() throws InternalClientException,
        TransportException, EscidocClientException {
        final UserAccounts usersCreatedBySysAdmin =
            client.retrieveUserAccounts(createdBy(SYSADMIN_OBJECT_ID));

        final Collection<UserAccount> userAccountsCreatedBySysAdmin =
            usersCreatedBySysAdmin.getUserAccounts();

        assertTrue("User Accounts created by SysAdmin should not be empty.",
            !userAccountsCreatedBySysAdmin.isEmpty());

        for (final UserAccount userAccount : userAccountsCreatedBySysAdmin) {
            final String objid = userAccount.getObjid();
            assertNotNull("Object Id should not be null. ", objid);
        }
    }

    private static TaskParam createdBy(final String userAccountObjectId) {
        final TaskParam filterParam = new TaskParam();
        filterParam.getFilters().add(
            getFilter(
                "http://escidoc.de/core/01/structural-relations/created-by",
                userAccountObjectId, null));
        return filterParam;
    }

    private static Filter getFilter(
        final String name, final String value, final Collection<String> ids) {

        final Filter filter = new Filter();
        filter.setName(name);
        filter.setValue(value);
        filter.setIds(ids);
        return filter;
    }
}
