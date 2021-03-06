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
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import gov.loc.www.zing.srw.SearchRetrieveRequestType;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.axis.types.NonNegativeInteger;
import org.junit.After;
import org.junit.Before;
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
import de.escidoc.core.common.jibx.Marshaller;
import de.escidoc.core.common.jibx.MarshallerFactory;
import de.escidoc.core.resources.ResourceType;
import de.escidoc.core.resources.aa.role.Role;
import de.escidoc.core.resources.aa.role.RoleProperties;
import de.escidoc.core.resources.aa.role.Scope;
import de.escidoc.core.resources.aa.role.ScopeDef;
import de.escidoc.core.resources.common.Filter;
import de.escidoc.core.resources.common.TaskParam;
import de.escidoc.core.test.client.EscidocClientTestBase;
import de.escidoc.core.test.client.util.Template;

/**
 * Test client lib role handler.
 * 
 * 
 */
public class RoleHandlerClientTest {

    private Authentication auth;

    private RoleHandlerClientInterface rc;

    @Before
    public void init() throws Exception {
        auth =
            new Authentication(EscidocClientTestBase.getDefaultInfrastructureURL(),
                EscidocClientTestBase.SYSTEM_ADMIN_USER, EscidocClientTestBase.SYSTEM_ADMIN_PASSWORD);
        rc = new RoleHandlerClient(auth.getServiceAddress());
        rc.setHandle(auth.getHandle());
    }

    @After
    public void post() throws Exception {
        if (auth != null)
            auth.logout();
    }

    /**
     * Test to create and retrieve user account.
     * 
     * @throws Exception
     *             Thrown if anythings failed.
     */
    @Test
    public void testCreateAndRetrieveSuccessfulRole() throws Exception {
        final Role role = createRole();
        final Role createdRole = rc.create(role);

        final String objId = createdRole.getObjid();

        MarshallerFactory.getInstance().getMarshaller(Role.class).marshalDocument(rc.retrieve(objId));

    }

    /**
     * 
     * @throws Exception
     */
    @Test
    public void testUpdateSuccessfulRole() throws Exception {
        final Role role = createRole();
        final Role createdRole = rc.create(role);
        final String newName = "newName" + System.currentTimeMillis();
        createdRole.getProperties().setName(newName);
        final Role updatedRole = rc.update(createdRole);
        final String updatedName = updatedRole.getProperties().getName();
        assertEquals(updatedName, newName);

    }

    /**
     * 
     * @throws Exception
     */
    @Test
    public void testDeleteSuccessfulRole() throws Exception {
        final Role role = createRole();

        final Role createdRole = rc.create(role);

        final String objId = createdRole.getObjid();
        rc.delete(objId);
        try {
            rc.retrieve(objId);
            fail("Missing Exception");
        }
        catch (final EscidocException e) {
            if (!(e instanceof RoleNotFoundException)) {

                fail("Wrong exception.Excepted exception of type " + "RoleNotFoundException but was " + e.getClass());
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

        final TaskParam filterParam = new TaskParam();
        final List<Filter> filters = filterParam.getFilters();

        filters.add(new Filter("limited", "false", null));
        filterParam.setFilters(filters);

        // serialize data
        MarshallerFactory.getInstance().getMarshaller(TaskParam.class).marshalDocument(filterParam);
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

        final SearchRetrieveRequestType request = new SearchRetrieveRequestType();
        request.setMaximumRecords(new NonNegativeInteger("0"));

        final List<Role> roleList = rc.retrieveRolesAsList(request);

        assertNotNull("No roles returned.", roleList);
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
    private Role createRole() throws ParserConfigurationException, SAXException, IOException, InternalClientException {

        final Role role = new Role();

        // properties
        final RoleProperties properties = new RoleProperties();
        properties.setName("name" + System.currentTimeMillis());
        properties.setDescription("description");

        // Scope
        final Scope scope = new Scope();
        final ScopeDef scopeDef1 =
            new ScopeDef(ResourceType.ITEM, "info:escidoc/names:aa:1.0:resource:item:context", ResourceType.CONTEXT);
        final ScopeDef scopeDef2 =
            new ScopeDef(ResourceType.CONTAINER, "info:escidoc/names:aa:1.0:resource:item:context",
                ResourceType.CONTEXT);

        final List<ScopeDef> scopeDefinitions = new LinkedList<ScopeDef>();
        scopeDefinitions.add(scopeDef1);
        scopeDefinitions.add(scopeDef2);
        scope.setScopeDefinitions(scopeDefinitions);
        role.setScope(scope);

        role.setProperties(properties);

        // Policy
        final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        final DocumentBuilder builder = factory.newDocumentBuilder();
        final Document doc =
            builder
                .parse(Template.load(TransportProtocol.REST.name().toLowerCase() + "/aa/role/policy_for_create.xml"));
        final Element root = doc.getDocumentElement();

        role.setPolicyOrPolicySet(root);

        // FIXME done without result handling
        final Marshaller<Role> m = MarshallerFactory.getInstance().getMarshaller(Role.class);

        final String xml = m.marshalDocument(role);

        final Role urole = m.unmarshalDocument(xml);

        return urole;
    }
}