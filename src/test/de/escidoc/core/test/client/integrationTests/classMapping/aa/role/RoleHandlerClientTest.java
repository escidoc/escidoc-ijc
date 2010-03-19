package de.escidoc.core.test.client.classMapping.aa.role;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.InputStream;
import java.util.Collection;
import java.util.LinkedList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import de.escidoc.core.client.RoleHandlerClient;
import de.escidoc.core.client.exceptions.EscidocException;
import de.escidoc.core.client.exceptions.application.notfound.RoleNotFoundException;
import de.escidoc.core.common.jibx.Factory;
import de.escidoc.core.common.jibx.Marshaller;
import de.escidoc.core.resources.aa.role.Role;
import de.escidoc.core.resources.aa.role.RoleProperties;
import de.escidoc.core.resources.aa.role.Roles;
import de.escidoc.core.resources.aa.role.Scope;
import de.escidoc.core.resources.aa.role.ScopeDef;
import de.escidoc.core.resources.common.Filter;
import de.escidoc.core.resources.common.TaskParam;
import de.escidoc.core.test.client.Constants;

/**
 * Test client lib role handler.
 * 
 * 
 */
public class RoleHandlerClientTest {

    /**
     * Test to create and retrieve user account.
     * 
     * @throws Exception
     *             Thrown if anythings failed.
     */
    @Test
    public void testCreateAndRetrieveSuccessfulRole() throws Exception {

        RoleHandlerClient rc = new RoleHandlerClient();
        rc.setHandle(Constants.DEFAULT_HANDLE);
        // rc.setServiceAddress("http://localhost:8080");
        Role role = createRole();
        Role createdRole = rc.create(role);

        String objId = createdRole.getObjid();

        Factory.getRoleMarshaller().marshalDocument(rc.retrieve(objId));

    }

    /**
     * 
     * @throws Exception
     */
    @Test
    public void testUpdateSuccessfulRole() throws Exception {

        RoleHandlerClient rc = new RoleHandlerClient();
        rc.setHandle(Constants.DEFAULT_HANDLE);
        Role role = createRole();
        Role createdRole = rc.create(role);
        String newName = "newName" + System.currentTimeMillis();
        createdRole.getProperties().setName(newName);
        Role updatedRole = rc.update(createdRole);
        String updatedName = updatedRole.getProperties().getName();
        assertEquals(updatedName, newName);

    }

    /**
     * 
     * @throws Exception
     */
    @Test
    public void testDeleteSuccessfulRole() throws Exception {

        RoleHandlerClient rc = new RoleHandlerClient();
        rc.setHandle(Constants.DEFAULT_HANDLE);
        Role role = createRole();
        Role createdRole = rc.create(role);
        String objId = createdRole.getObjid();
        rc.delete(objId);
        try {
            rc.retrieve(objId);
            fail("Missing Exception");
        }
        catch (EscidocException e) {
            if (!(e instanceof RoleNotFoundException)) {

                fail("Wrong exception.Excepted exception of type "
                    + "RoleNotFoundException but was " + e.getClass());
            }
        }

    }

    /**
     * Test retrieving RetrieveUserAccounts through filter request.
     * 
     * @throws Exception
     */
    @Test
    public void testRetrieveRoles() throws Exception {
        TaskParam filterParam = new TaskParam();
        Collection<Filter> filters = TaskParam.filtersFactory();

        filters.add(getFilter("limited", "false", null));
        filterParam.setFilters(filters);

        Factory.getTaskParamMarshaller().marshalDocument(filterParam);
        RoleHandlerClient rc = new RoleHandlerClient();
        rc.setHandle(Constants.DEFAULT_HANDLE);
        Roles roleList = rc.retrieveRoles(filterParam);
        Factory.getRoleListMarshaller().marshalDocument(roleList);
    }

    /**
     * 
     * @return
     * @throws Exception
     */
    private Role createRole() throws Exception {
        Role role = new Role();
        RoleProperties properties = new RoleProperties();
        properties.setName("name" + System.currentTimeMillis());
        properties.setDescription("description");
        Scope scope = new Scope();
        ScopeDef scopeDef1 = new ScopeDef();
        scopeDef1
            .setRelationAttributeId("info:escidoc/names:aa:1.0:resource:item:context");
        scopeDef1.setResourceType("item");
        ScopeDef scopeDef2 = new ScopeDef();
        scopeDef2
            .setRelationAttributeId("info:escidoc/names:aa:1.0:resource:item:context");
        scopeDef2.setResourceType("container");
        Collection<ScopeDef> scopeDefinitions = new LinkedList<ScopeDef>();
        scopeDefinitions.add(scopeDef1);
        scopeDefinitions.add(scopeDef2);
        scope.setScopeDefinitions(scopeDefinitions);
        role.setScope(scope);

        role.setProperties(properties);
        InputStream input =
            getClass().getResourceAsStream("policy_for_create.xml");

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(input);
        Element root = doc.getDocumentElement();

        role.setPolicyOrPolicySet(root);
        Marshaller<Role> m = new Marshaller<Role>(role.getClass());
        String xml = m.marshalDocument(role);
        System.out.println(xml);

        Role urole = m.unmarshalDocument(xml);
        String roleXml = Factory.getRoleMarshaller().marshalDocument(urole);
        System.out.println("role " + roleXml);
        return role;

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
