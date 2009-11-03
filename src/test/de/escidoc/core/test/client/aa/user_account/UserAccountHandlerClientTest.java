package de.escidoc.core.test.client.aa.user_account;

import java.util.Collection;
import java.util.LinkedList;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;

import de.escidoc.core.client.UserAccountHandlerClient;
import de.escidoc.core.common.jibx.Factory;
import de.escidoc.core.resources.ResourceRef;
import de.escidoc.core.resources.aa.useraccount.Grants;
import de.escidoc.core.resources.aa.useraccount.PropertiesUserAccount;
import de.escidoc.core.resources.aa.useraccount.UserAccount;
import de.escidoc.core.resources.aa.useraccount.UserAccounts;
import de.escidoc.core.resources.common.Filter;
import de.escidoc.core.resources.common.TaskParam;
import de.escidoc.core.test.client.EscidocClientTestBase;

public class UserAccountHandlerClientTest extends EscidocClientTestBase {
    private final Logger logger =
        Logger.getLogger(UserAccountHandlerClientTest.class.getName());

    /**
     * Test to create and retrieve user account.
     * 
     * @throws Exception
     *             Thrown if anythings failed.
     */
    public void testCreateAndRetrieveSuccessfulUserAccount() throws Exception {

        UserAccountHandlerClient uac = new UserAccountHandlerClient();
        uac.setHandle(EscidocClientTestBase.DEFAULT_HANDLE);
        UserAccount ua = createUserAccount();
        UserAccount createdUa = uac.create(ua);
        String objId = createdUa.getObjid();

        String xml =
            Factory.getUserAccountMarshaller().marshalDocument(
                (UserAccount) uac.retrieve(objId));
        System.out.println(" created ua " + xml);

    }

    /**
     * Test to update an user account.
     * 
     * @throws Exception
     *             Thrown if anythings failed.
     */
    public void testUpdateUserAccount() throws Exception {

        UserAccountHandlerClient uac = new UserAccountHandlerClient();
        uac.setHandle(EscidocClientTestBase.DEFAULT_HANDLE);
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
        
       
        String xml =
            Factory.getUserAccountMarshaller().marshalDocument(
                (UserAccount) updatedUserAccont);
        System.out.println(" created ua " + xml);

    }

    public void testDeactivate_Activate() throws Exception {
        UserAccountHandlerClient uac = new UserAccountHandlerClient();
        uac.setHandle(EscidocClientTestBase.DEFAULT_HANDLE);
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
    public void testRetrieveUserAccounts() throws Exception {
        TaskParam filterParam = new TaskParam();
        Collection<Filter> filters = TaskParam.filtersFactory();

        filters.add(getFilter(
            "http://escidoc.de/core/01/structural-relations/created-by",
            "escidoc:user42", null));
        filterParam.setFilters(filters);
        try {
            logger
                .debug("Call retrieves with filter "
                    + Factory.getTaskParamMarshaller().marshalDocument(
                        filterParam));
            UserAccountHandlerClient uac = new UserAccountHandlerClient();
            uac.setHandle(EscidocClientTestBase.DEFAULT_HANDLE);
            UserAccounts userAccountList =
                uac.retrieveUserAccounts(filterParam);
            logger.debug("------------------------ ");
            String xml = Factory.getUserAccountListMarshaller().marshalDocument(userAccountList);
            System.out.println("user account list " + xml);
        }
        catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * Test to create and retrieve user account.
     * 
     * @throws Exception
     *             Thrown if anythings failed.
     */
    public void testretrieveGrants() throws Exception {

        UserAccountHandlerClient uac = new UserAccountHandlerClient();
        uac.setHandle(EscidocClientTestBase.DEFAULT_HANDLE);
        UserAccount ua = createUserAccount();
        
        UserAccount createdUa = uac.create(ua);
        String objId = createdUa.getObjid();

        String xml =
            Factory.getGrantsMarshaller().marshalDocument(
                (Grants) uac.retrieveCurrentGrants(objId));
        System.out.println(" grants " + xml);

    }

    public void testupdatePassword() throws Exception {
        UserAccountHandlerClient uac = new UserAccountHandlerClient();
        uac.setHandle(EscidocClientTestBase.DEFAULT_HANDLE);
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
     * @return
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
