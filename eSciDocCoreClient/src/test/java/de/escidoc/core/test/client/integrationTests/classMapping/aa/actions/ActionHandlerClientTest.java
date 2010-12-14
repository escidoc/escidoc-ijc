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
package de.escidoc.core.test.client.integrationTests.classMapping.aa.actions;

import org.junit.Test;

import de.escidoc.core.client.ActionHandlerClient;
import de.escidoc.core.client.Authentication;
import de.escidoc.core.client.TransportProtocol;
import de.escidoc.core.common.jibx.MarshallerFactory;
import de.escidoc.core.resources.aa.actions.UnsecuredActions;
import de.escidoc.core.test.client.Constants;
import de.escidoc.core.test.client.EscidocClientTestBase;

/**
 * Test Action Handler.
 * 
 * @author ?, SWA
 * 
 */
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
        Authentication auth = new Authentication(
        		EscidocClientTestBase.DEFAULT_SERVICE_URL,
        		Constants.SYSTEM_ADMIN_USER, Constants.SYSTEM_ADMIN_PASSWORD);
        ac.setHandle(auth.getHandle());

        UnsecuredActions actions = new UnsecuredActions();
        actions.addAction("1");
        actions.addAction("2");

        UnsecuredActions ua =
            ac.createUnsecuredActions("escidoc:persistent3", actions);
        MarshallerFactory.getInstance(TransportProtocol.SOAP)
        	.getMarshaller(UnsecuredActions.class).marshalDocument(
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
    // + Factory.getMarshaller(TaskParam.class).marshalDocument(
    // filterParam));
    // RoleHandlerClient rc = new RoleHandlerClient();
    // Roles roleList =
    // rc.retrieveRoles(filterParam);
    // logger.debug("------------------------ ");
    // String xml = Factory.getMarshaller(RoleList.class).marshalDocument(roleList);
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
    //
    // Role urole = m.unmarshalDocument(xml);
    // String roleXml =
    // Factory.getMarshaller(Role.class).marshalDocument(
    // urole);
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
