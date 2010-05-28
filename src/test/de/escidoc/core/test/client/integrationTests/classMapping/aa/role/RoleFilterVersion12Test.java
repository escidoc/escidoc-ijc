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
import gov.loc.www.zing.srw.SearchRetrieveRequestType;

import java.io.IOException;
import java.util.Collection;
import java.util.LinkedList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.axis.types.NonNegativeInteger;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import de.escidoc.core.client.Authentication;
import de.escidoc.core.client.RoleHandlerClient;
import de.escidoc.core.client.UserAccountHandlerClient;
import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.common.jibx.Factory;
import de.escidoc.core.common.jibx.Marshaller;
import de.escidoc.core.resources.aa.role.Role;
import de.escidoc.core.resources.aa.role.RoleProperties;
import de.escidoc.core.resources.aa.role.Scope;
import de.escidoc.core.resources.aa.role.ScopeDef;
import de.escidoc.core.resources.aa.useraccount.UserAccount;
import de.escidoc.core.resources.sb.srw.SearchRetrieveResponseType;
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

    public static final String FILTER_PARAMETER_QUERY = "query";

    /**
     * Test retrieving Roles through filter request (filter for version 1.2).
     * 
     * @throws Exception
     *             Thrown if anythings failed.
     */
    @Test
    public void testRetrieveRoles01() throws Exception {

        Authentication auth =
            new Authentication(EscidocClientTestBase.DEFAULT_SERVICE_URL,
                Constants.SYSTEM_ADMIN_USER, Constants.SYSTEM_ADMIN_PASSWORD);

        RoleHandlerClient rc = new RoleHandlerClient();
        rc.setServiceAddress(EscidocClientTestBase.DEFAULT_SERVICE_URL);
        rc.setHandle(auth.getHandle());

        Role role = createRole();
        Role createdRole = rc.create(role);

        
        // now check if at least this Container is in the list

        UserAccountHandlerClient uac = new UserAccountHandlerClient();
        uac.setServiceAddress(EscidocClientTestBase.DEFAULT_SERVICE_URL);
        uac.setHandle(auth.getHandle());
        UserAccount me = uac.retrieveCurrentUser();

        SearchRetrieveRequestType srwFilter = new SearchRetrieveRequestType();
        srwFilter.setQuery("\"http://escidoc.de/core/01/structural-relations/created-by\"=" + me.getObjid());
        srwFilter.setMaximumRecords(new NonNegativeInteger("1"));

        SearchRetrieveResponseType roleList = rc.retrieveRoles(srwFilter);

        assertEquals("Wrong version number", "1.1", roleList.getVersion());
        assertTrue("Wrong number of records",
            roleList.getNumberOfRecords() >= 1);
        assertEquals("Wrong record position", 1, roleList
            .getRecords().iterator().next().getRecordPosition());
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
                .load("templates/soap/role/policy_for_create.xml"));
        Element root = doc.getDocumentElement();

        role.setPolicyOrPolicySet(root);

        // FIXME done without result handling
        Marshaller<Role> m = new Marshaller<Role>(role.getClass());
        String xml = m.marshalDocument(role);

        Role urole = m.unmarshalDocument(xml);
        Factory.getRoleMarshaller().marshalDocument(urole);

        return role;
    }
}