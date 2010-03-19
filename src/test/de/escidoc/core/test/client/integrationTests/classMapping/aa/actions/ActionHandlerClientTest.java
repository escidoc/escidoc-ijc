package de.escidoc.core.test.client.integrationTests.classMapping.aa.actions;

import org.junit.Test;

import de.escidoc.core.client.ActionHandlerClient;
import de.escidoc.core.common.jibx.Factory;
import de.escidoc.core.resources.aa.actions.UnsecuredActions;
import de.escidoc.core.test.client.Constants;

public class ActionHandlerClientTest {

    /**
     * Test to create and retrieve user account.
     * 
     * @throws Exception
     *             Thrown if anythings failed.
     */
    @Test
    public void testCreateAndRetrieveSuccessfulActions() throws Exception {

        ActionHandlerClient ac = new ActionHandlerClient();
        ac.setHandle(Constants.DEFAULT_HANDLE);
        // rc.setServiceAddress("http://localhost:8080");
        UnsecuredActions actions = new UnsecuredActions();
        actions.addAction("1");
        actions.addAction("2");

        // Marshaller<UnsecuredActions> m = new
        // Marshaller<UnsecuredActions>(actions.getClass());
        // String xml = m.marshalDocument(actions);
        // System.out.println(xml);
        //
        // UnsecuredActions uactions = m.unmarshalDocument(xml);
        // String actionsXml =
        // Factory.getUnsecuredActionsMarshaller().marshalDocument(
        // uactions);
        // System.out.println("actions " + actionsXml);
        UnsecuredActions ua =
            ac.createUnsecuredActions("escidoc:persistent3", actions);

        Factory.getUnsecuredActionsMarshaller().marshalDocument(
            ac.retrieveUnsecuredActions("escidoc:persistent3"));

    }

    // public void testUpdateSuccessfulRole() throws Exception {
    //
    // RoleHandlerClient rc = new RoleHandlerClient();
    //        
    // Role role = createRole();
    // Role createdRole = rc.create(role);
    // String newName = "newName" + System.currentTimeMillis();
    // createdRole.getProperties().setName(newName);
    // Role updatedRole = rc.update(createdRole);
    // String updatedName = updatedRole.getProperties().getName();
    // assertEquals(updatedName, newName);
    //
    // }
    //    
    // public void testDeleteSuccessfulRole() throws Exception {
    //
    // RoleHandlerClient rc = new RoleHandlerClient();
    //
    // Role role = createRole();
    // Role createdRole = rc.create(role);
    // String objId = createdRole.getObjid();
    // rc.delete(objId);
    // try {
    // rc.retrieve(objId);
    // fail("Missing Exception");
    // }
    // catch (EscidocException e) {
    // if (!(e instanceof RoleNotFoundException)) {
    //
    // fail("Wrong exception.Excepted exception of type RoleNotFoundException "
    // + "but was " + e.getClass());
    // }
    // }
    //        
    // }
    //
    //    
    // /**
    // * Test retrieving RetrieveUserAccounts through filter request.
    // *
    // * @throws Exception
    // */
    // public void testRetrieveRoles() throws Exception {
    // TaskParam filterParam = new TaskParam();
    // Collection<Filter> filters = TaskParam.filtersFactory();
    //
    // filters.add(getFilter("limited", "false", null));
    // filterParam.setFilters(filters);
    // try {
    // logger
    // .debug("Call retrieves with filter "
    // + Factory.getTaskParamMarshaller().marshalDocument(
    // filterParam));
    // RoleHandlerClient rc = new RoleHandlerClient();
    // Roles roleList =
    // rc.retrieveRoles(filterParam);
    // logger.debug("------------------------ ");
    // String xml = Factory.getRoleListMarshaller().marshalDocument(roleList);
    // System.out.println("role list " + xml);
    // }
    // catch (Exception e) {
    // // TODO Auto-generated catch block
    // e.printStackTrace();
    // throw e;
    // }
    // }
    //
    // public Role createRole() throws Exception {
    // Role role = new Role();
    // RoleProperties properties = new RoleProperties();
    // properties.setName("name" + System.currentTimeMillis());
    // properties.setDescription("description");
    // Scope scope = new Scope();
    // ScopeDef scopeDef1 = new ScopeDef();
    // scopeDef1.setRelationAttributeId("info:escidoc/names:aa:1.0:resource:item:context");
    // scopeDef1.setResourceType("item");
    // ScopeDef scopeDef2 = new ScopeDef();
    // scopeDef2.setRelationAttributeId("info:escidoc/names:aa:1.0:resource:item:context");
    // scopeDef2.setResourceType("container");
    // Collection<ScopeDef> scopeDefinitions = new LinkedList<ScopeDef>();
    // scopeDefinitions.add(scopeDef1);
    // scopeDefinitions.add(scopeDef2);
    // scope.setScopeDefinitions(scopeDefinitions);
    // role.setScope(scope);
    //
    // role.setProperties(properties);
    // InputStream input =
    // getClass().getResourceAsStream("policy_for_create.xml");
    // SAXReader reader = new SAXReader();
    // Document document = reader.read(input);
    // Element root = document.getRootElement();
    // role.setPolicyOrPolicySet(root);
    // Marshaller<Role> m = new Marshaller<Role>(role.getClass());
    // String xml = m.marshalDocument(role);
    // System.out.println(xml);
    //
    // Role urole = m.unmarshalDocument(xml);
    // String roleXml =
    // Factory.getRoleMarshaller().marshalDocument(
    // urole);
    // System.out.println("role " + roleXml);
    // return role;
    //
    // }
    //
    // /**
    // * Prepare and Filter class from the parameter collection.
    // *
    // * @param name
    // * @param value
    // * @param ids
    // * @return
    // */
    // private Filter getFilter(
    // final String name, final String value, Collection<String> ids) {
    //
    // Filter filter = new Filter();
    // filter.setName(name);
    // filter.setValue(value);
    // filter.setIds(ids);
    // return filter;
    // }
}
