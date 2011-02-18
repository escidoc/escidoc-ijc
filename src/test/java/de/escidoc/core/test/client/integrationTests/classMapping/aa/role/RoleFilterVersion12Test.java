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
package de.escidoc.core.test.client.integrationTests.classMapping.aa.role;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import gov.loc.www.zing.srw.ExplainRequestType;
import gov.loc.www.zing.srw.SearchRetrieveRequestType;

import java.io.IOException;
import java.util.Collection;
import java.util.LinkedList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.axis.types.NonNegativeInteger;
import org.joda.time.DateTimeZone;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import de.escidoc.core.client.Authentication;
import de.escidoc.core.client.RoleHandlerClient;
import de.escidoc.core.client.TransportProtocol;
import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.client.interfaces.RoleHandlerClientInterface;
import de.escidoc.core.common.jibx.Marshaller;
import de.escidoc.core.common.jibx.MarshallerFactory;
import de.escidoc.core.resources.ResourceType;
import de.escidoc.core.resources.aa.role.Role;
import de.escidoc.core.resources.aa.role.RoleProperties;
import de.escidoc.core.resources.aa.role.Scope;
import de.escidoc.core.resources.aa.role.ScopeDef;
import de.escidoc.core.resources.sb.explain.Explain;
import de.escidoc.core.resources.sb.explain.ExplainResponse;
import de.escidoc.core.resources.sb.search.SearchRetrieveResponse;
import de.escidoc.core.test.client.Constants;
import de.escidoc.core.test.client.EscidocClientTestBase;
import de.escidoc.core.test.client.util.Template;

/**
 * Test the CQL Role Filter.
 * 
 * @author SWA
 * 
 */
public class RoleFilterVersion12Test {

    private Authentication auth;

    private RoleHandlerClientInterface rc;

    @Before
    public void init() throws Exception {
        auth =
            new Authentication(EscidocClientTestBase.DEFAULT_SERVICE_URL,
                Constants.SYSTEM_ADMIN_USER, Constants.SYSTEM_ADMIN_PASSWORD);
        rc = new RoleHandlerClient(auth.getServiceAddress());
        rc.setHandle(auth.getHandle());
    }

    @After
    public void post() throws Exception {
        if (auth != null)
            auth.logout();
    }

    /**
     * Test for User Account Explain. (filter for version 1.2).
     * 
     * @throws Exception
     *             Thrown if anythings failed.
     */
    @Test
    public void testExplain() throws Exception {
        rc.create(createRole());

        ExplainResponse response = rc.retrieveRoles(new ExplainRequestType());

        Explain explain = response.getRecord().getRecordData();

        assertEquals("Wrong version number", "1.1", response.getVersion());
        assertTrue("No index definitions found", explain
            .getIndexInfo().getIndexes().size() > 0);
    }

    /**
     * Test retrieving Roles through filter request (filter for version 1.2).
     * 
     * @throws Exception
     *             Thrown if anythings failed.
     */
    @Test
    public void testFilter01() throws Exception {
        Role createdRole = rc.create(createRole());

        // now check if at least this Container is in the list

        SearchRetrieveRequestType srwFilter = new SearchRetrieveRequestType();
        srwFilter
            .setQuery("\"http://escidoc.de/core/01/properties/creation-date\"=\""
                + createdRole
                    .getProperties().getCreationDate()
                    .withZone(DateTimeZone.UTC) + "\"");
        srwFilter.setMaximumRecords(new NonNegativeInteger("1"));

        SearchRetrieveResponse response = rc.retrieveRoles(srwFilter);

        assertEquals("Wrong version number", "1.1", response.getVersion());
        assertTrue("Wrong number of matching records",
            response.getNumberOfMatchingRecords() >= 1);
        assertEquals("Wrong record position", 1, response
            .getRecords().iterator().next().getRecordPosition().intValue());

        Collection<Role> roles = rc.retrieveRolesAsList(srwFilter);
        assertTrue("Different number of resulting records",
            response.getNumberOfResultingRecords() == roles.size());
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
        // context -> item scope
        ScopeDef scopeDef1 = new ScopeDef(ResourceType.Item);
        scopeDef1
            .setRelationAttributeId("info:escidoc/names:aa:1.0:resource:item:context");
        scopeDef1.setRelationAttributeObjectType(ResourceType.Context);
        // context -> container scope
        ScopeDef scopeDef2 = new ScopeDef(ResourceType.Container);
        scopeDef2
            .setRelationAttributeId("info:escidoc/names:aa:1.0:resource:container:context");
        scopeDef2.setRelationAttributeObjectType(ResourceType.Context);

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
            builder.parse(Template.load(TransportProtocol.REST
                .name().toLowerCase() + "/aa/role/policy_for_create.xml"));
        Element root = doc.getDocumentElement();

        role.setPolicyOrPolicySet(root);

        // FIXME done without result handling
        Marshaller<Role> m =
            MarshallerFactory.getInstance().getMarshaller(Role.class);

        String xml = m.marshalDocument(role);

        Role urole = m.unmarshalDocument(xml);

        return urole;
    }
}
