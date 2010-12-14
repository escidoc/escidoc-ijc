package de.escidoc.core.test.client.integrationTests.RESTHandler.aa.user_account;

import de.escidoc.core.resources.aa.useraccount.UserAccount;
import de.escidoc.core.resources.aa.useraccount.UserAccountProperties;

public class FakeUserAccountFactory {

    private static final String LOGIN_PREFIX = "login";

    public static UserAccount createFakeUserAccount() {
        final UserAccount ua = new UserAccount();
        final UserAccountProperties properties = new UserAccountProperties();
        final String login = getUniqueLoginName();
        properties.setName("Name " + login);
        properties.setLoginName(login);
        ua.setProperties(properties);
        return ua;
    }

    private static String getUniqueLoginName() {
        return LOGIN_PREFIX + System.currentTimeMillis();
    }
}
