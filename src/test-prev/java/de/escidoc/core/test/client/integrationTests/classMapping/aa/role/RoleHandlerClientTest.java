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
package de.escidoc.core.test.client.integrationTests.classMapping.aa.role;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.util.Collection;
import java.util.LinkedList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import de.escidoc.core.client.Authentication;
import de.escidoc.core.client.RoleHandlerClient;
import de.escidoc.core.client.TransportProtocol;
import de.escidoc.core.client.exceptions.EscidocClientException;
import de.escidoc.core.client.exceptions.EscidocException;
import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.client.exceptions.application.notfound.RoleNotFoundException;
import de.escidoc.core.client.interfaces.RoleHandlerClientInterface;
import de.escidoc.core.common.jibx.Factory;
import de.escidoc.core.common.jibx.Marshaller;
import de.escidoc.core.resources.aa.role.Role;
import de.escidoc.core.resources.aa.role.RoleProperties;
import de.escidoc.core.resources.aa.role.Roles;
import de.escidoc.core.resources.aa.role.Scope;
import de.escidoc.core.resources.aa.role.ScopeDef;
import de.escidoc.core.resources.common.Filter;
import de.escidoc.core.resources.common.TaskParam;
import de.escidoc.core.test.client.AbstractParameterizedTestBase;
import de.escidoc.core.test.client.Constants;
import de.escidoc.core.test.client.EscidocClientTestBase;
import de.escidoc.core.test.client.util.Template;

/**
 * Test client lib role handler.
 * 
 * 
 */
public class RoleHandlerClientTest extends AbstractParameterizedTestBase {
	
	public RoleHandlerClientTest(TransportProtocol transport) {
		super(transport);
	}

    /**
     * Test to create and retrieve user account.
     * 
     * @throws Exception
     *             Thrown if anythings failed.
     */
    @Test
    public void testCreateAndRetrieveSuccessfulRole() throws Exception {

        Authentication auth =
            new Authentication(EscidocClientTestBase.DEFAULT_SERVICE_URL,
                Constants.SYSTEM_ADMIN_USER, Constants.SYSTEM_ADMIN_PASSWORD);

        RoleHandlerClientInterface rc = new RoleHandlerClient();
        rc.setServiceAddress(EscidocClientTestBase.DEFAULT_SERVICE_URL);
        rc.setHandle(auth.getHandle());
        rc.setTransport(transport);

        Role role = createRole();
        Role createdRole = rc.create(role);

        String objId = createdRole.getObjid();

        Factory.getMarshallerFactory(rc.getTransport()).getRoleMarshaller()
        	.marshalDocument(rc.retrieve(objId));

    }

    /**
     * 
     * @throws Exception
     */
    @Test
    public void testUpdateSuccessfulRole() throws Exception {

        Authentication auth =
            new Authentication(EscidocClientTestBase.DEFAULT_SERVICE_URL,
                Constants.SYSTEM_ADMIN_USER, Constants.SYSTEM_ADMIN_PASSWORD);

        RoleHandlerClientInterface rc = new RoleHandlerClient();
        rc.setServiceAddress(EscidocClientTestBase.DEFAULT_SERVICE_URL);
        rc.setHandle(auth.getHandle());
        rc.setTransport(transport);

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

        Role role = createRole();
        
        // login
        Authentication auth =
            new Authentication(EscidocClientTestBase.DEFAULT_SERVICE_URL,
                Constants.SYSTEM_ADMIN_USER, Constants.SYSTEM_ADMIN_PASSWORD);

        RoleHandlerClientInterface rc = new RoleHandlerClient();
        rc.setServiceAddress(EscidocClientTestBase.DEFAULT_SERVICE_URL);
        rc.setHandle(auth.getHandle());
        rc.setTransport(transport);
   
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
     * Test serialize Role Filter task param object.
     * 
     * @throws InternalClientException
     *             If internal client errors occur
     */
    @Test
    public void testSerializeRole() throws InternalClientException {

        TaskParam filterParam = new TaskParam();
        Collection<Filter> filters = TaskParam.filtersFactory();

        filters.add(getFilter("limited", "false", null));
        filterParam.setFilters(filters);

        // serialize data
        Factory.getMarshallerFactory(transport)
        	.getTaskParamMarshaller().marshalDocument(filterParam);
    }

    /**
     * Test retrieving RetrieveUserAccounts through filter request.
     * 
     * @throws EscidocClientException
     *             If errors occur on client internal, transport of framework
     *             level
     */
    @Test
    public void testRetrieveRoles() throws EscidocClientException {

        TaskParam filterParam = new TaskParam();
        Collection<Filter> filters = TaskParam.filtersFactory();

        filters.add(getFilter("limited", "false", null));
        filterParam.setFilters(filters);

        Authentication auth =
            new Authentication(EscidocClientTestBase.DEFAULT_SERVICE_URL,
                Constants.SYSTEM_ADMIN_USER, Constants.SYSTEM_ADMIN_PASSWORD);

        RoleHandlerClientInterface rc = new RoleHandlerClient();
        rc.setServiceAddress(EscidocClientTestBase.DEFAULT_SERVICE_URL);
        rc.setHandle(auth.getHandle());
        rc.setTransport(transport);

        Roles roleList = rc.retrieveRoles(filterParam);

        // test deserialize Role XML
        Factory.getMarshallerFactory(rc.getTransport()).getRoleListMarshaller()
        	.marshalDocument(roleList);
    }

    /**
     * Create a Role VO (but not create it at infrastructure).
     * 
     * @return Role VO
     * 
     * @throws ParserConfigurationException
     *             If parser configuration fails
     * @throws IOException
     *             If template access failed
     * @throws SAXException
     *             If template parsing failed
     * @throws InternalClientException
     *             If internal client errors occur
     * 
     * @throws Exception
     */
    private Role createRole() throws ParserConfigurationException,
        SAXException, IOException, InternalClientException {

        Role role = new Role();

        // properties
        RoleProperties properties = new RoleProperties();
        properties.setName("name" + System.currentTimeMillis());
        properties.setDescription("description");

        // Scope
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

        // Policy
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc =
            builder.parse(Template
                .load("templates/" + transport.name().toLowerCase()
                + "/aa/role/policy_for_create.xml"));
        Element root = doc.getDocumentElement();

        role.setPolicyOrPolicySet(root);

        // FIXME done without result handling
        Marshaller<Role> m = new Marshaller<Role>(role.getClass(), transport.name());
        
        String xml = m.marshalDocument(role);

        Role urole = m.unmarshalDocument(xml);
        Factory.getMarshallerFactory(transport)
        	.getRoleMarshaller().marshalDocument(urole);

        return role;
    }

    /**
     * Prepare and Filter class from the parameter collection.
     * 
     * @param name
     *            TODO
     * @param value
     *            TODO
     * @param ids
     *            Collection of IDs
     * @return Filter VO
     */
    private Filter getFilter(
        final String name, final String value, final Collection<String> ids) {

        Filter filter = new Filter();
        filter.setName(name);
        filter.setValue(value);
        filter.setIds(ids);

        return filter;
    }
}
