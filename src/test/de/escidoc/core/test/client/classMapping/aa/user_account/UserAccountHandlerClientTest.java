package de.escidoc.core.test.client.classMapping.aa.user_account;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Collection;
import java.util.LinkedList;

import org.joda.time.DateTime;
import org.junit.Test;

import de.escidoc.core.client.UserAccountHandlerClient;
import de.escidoc.core.common.jibx.Factory;
import de.escidoc.core.resources.ResourceRef;
import de.escidoc.core.resources.aa.useraccount.PropertiesUserAccount;
import de.escidoc.core.resources.aa.useraccount.UserAccount;
import de.escidoc.core.resources.aa.useraccount.UserAccounts;
import de.escidoc.core.resources.common.Filter;
import de.escidoc.core.resources.common.TaskParam;
import de.escidoc.core.test.client.Constants;

/**
 * Test client lib user account handler.
 * 
 * @author ROF
 * 
 */
public class UserAccountHandlerClientTest {

    /**
     * Test to create and retrieve user account.
     * 
     * @throws Exception
     *             Thrown if anythings failed.
     */
    @Test
    public void testCreateAndRetrieveSuccessfulUserAccount() throws Exception {

        UserAccountHandlerClient uac = new UserAccountHandlerClient();
        uac.setHandle(Constants.DEFAULT_HANDLE);
        UserAccount ua = createUserAccount();
        UserAccount createdUa = uac.create(ua);
        String objId = createdUa.getObjid();

        Factory.getUserAccountMarshaller().marshalDocument(uac.retrieve(objId));
    }

    /**
     * Test to update an user account.
     * 
     * @throws Exception
     *             Thrown if anythings failed.
     */
    @Test
    public void testUpdateUserAccount() throws Exception {

        UserAccountHandlerClient uac = new UserAccountHandlerClient();
        uac.setHandle(Constants.DEFAULT_HANDLE);
        UserAccount ua = createUserAccount();
        UserAccount createdUa = uac.create(ua);
        PropertiesUserAccount properties = createdUa.getProperties();

        properties.setName("new Name");
        String newLoginName = getUnicLoginName();
        properties.setLoginName(newLoginName);
        UserAccount updatedUserAccont = uac.update(createdUa);
        PropertiesUserAccount updatedProperties =
            updatedUserAccont.getProperties();
        String updatedName = updatedProperties.getName();
        String updatedLoginName = updatedProperties.getLoginName();

        assertEquals("new Name", updatedName);

        assertEquals(newLoginName, updatedLoginName);

        Factory.getUserAccountMarshaller().marshalDocument(updatedUserAccont);
    }

    /**
     * 
     * @throws Exception
     */
    @Test
    public void testDeactivate_Activate() throws Exception {
        UserAccountHandlerClient uac = new UserAccountHandlerClient();
        uac.setHandle(Constants.DEFAULT_HANDLE);
        UserAccount ua = createUserAccount();
        UserAccount createdUa = uac.create(ua);
        DateTime lastModificationDate = createdUa.getLastModificationDate();
        TaskParam taskParam = new TaskParam();
        taskParam.setLastModificationDate(lastModificationDate);
        String objId = createdUa.getObjid();
        taskParam.setPid(objId);
        uac.deactivate(objId, taskParam);
        UserAccount deactivatedUserAccount = uac.retrieve(objId);
        PropertiesUserAccount properties =
            deactivatedUserAccount.getProperties();
        boolean isActive = properties.isActive();
        assertTrue(!isActive);
        DateTime newLastModificationDate =
            deactivatedUserAccount.getLastModificationDate();
        taskParam.setLastModificationDate(newLastModificationDate);
        uac.activate(objId, taskParam);
        UserAccount reactivatedUserAccount = uac.retrieve(objId);
        PropertiesUserAccount propertiesReactivated =
            reactivatedUserAccount.getProperties();
        isActive = propertiesReactivated.isActive();
        assertTrue(isActive);
    }

    /**
     * Test retrieving RetrieveUserAccounts through filter request.
     * 
     * @throws Exception
     */
    @Test
    public void testRetrieveUserAccounts() throws Exception {
        TaskParam filterParam = new TaskParam();
        Collection<Filter> filters = TaskParam.filtersFactory();

        filters.add(getFilter(
            "http://escidoc.de/core/01/structural-relations/created-by",
            "escidoc:user42", null));
        filterParam.setFilters(filters);

        UserAccountHandlerClient uac = new UserAccountHandlerClient();
        uac.setHandle(Constants.DEFAULT_HANDLE);
        UserAccounts userAccountList = uac.retrieveUserAccounts(filterParam);

        Factory.getUserAccountListMarshaller().marshalDocument(userAccountList);
    }

    /**
     * Test to create and retrieve user account.
     * 
     * @throws Exception
     *             Thrown if anythings failed.
     */
    public void testretrieveGrants() throws Exception {

        UserAccountHandlerClient uac = new UserAccountHandlerClient();
        uac.setHandle(Constants.DEFAULT_HANDLE);
        UserAccount ua = createUserAccount();

        UserAccount createdUa = uac.create(ua);
        String objId = createdUa.getObjid();

        Factory.getGrantsMarshaller().marshalDocument(
            uac.retrieveCurrentGrants(objId));

    }

    @Test
    public void testupdatePassword() throws Exception {
        UserAccountHandlerClient uac = new UserAccountHandlerClient();
        uac.setHandle(Constants.DEFAULT_HANDLE);
        UserAccount ua = createUserAccount();
        UserAccount createdUa = uac.create(ua);
        String objId = createdUa.getObjid();
        DateTime lastModificationDate = createdUa.getLastModificationDate();
        final String password = "new-pass";
        TaskParam taskParam = new TaskParam();
        taskParam.setLastModificationDate(lastModificationDate);
        taskParam.setPassword(password);
        uac.updatePassword(objId, taskParam);
        // TODO: check login with a new password
    }

    @Test
    public UserAccount createUserAccount() {
        UserAccount ua = new UserAccount();
        PropertiesUserAccount properties = new PropertiesUserAccount();
        properties.setName("name");
        properties.setEmail("email@com");
        properties.setLoginName(getUnicLoginName());
        ResourceRef ouRef1 = new ResourceRef();
        ouRef1.setObjid("escidoc:persistent1");
        Collection<ResourceRef> ous = new LinkedList<ResourceRef>();
        ous.add(ouRef1);
        properties.setOus(ous);
        ua.setProperties(properties);
        return ua;
    }

    private String getUnicLoginName() {
        String loginName = "login name";
        loginName += System.currentTimeMillis();
        return loginName;
    }

    /**
     * Prepare and Filter class from the parameter collection.
     * 
     * @param name
     * @param value
     * @param ids
     * @return Filter
     */
    private Filter getFilter(
        final String name, final String value, Collection<String> ids) {

        Filter filter = new Filter();
        filter.setName(name);
        filter.setValue(value);
        filter.setIds(ids);
        return filter;
    }

}
