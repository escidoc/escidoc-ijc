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
package de.escidoc.core.test.client.integrationTests.classMapping.aa.login;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import de.escidoc.core.client.Authentication;
import de.escidoc.core.client.ContextHandlerClient;
import de.escidoc.core.client.ItemHandlerClient;
import de.escidoc.core.client.TransportProtocol;
import de.escidoc.core.client.UserManagementWrapperClient;
import de.escidoc.core.client.exceptions.EscidocClientException;
import de.escidoc.core.client.exceptions.application.security.AuthenticationException;
import de.escidoc.core.client.exceptions.application.security.AuthorizationException;
import de.escidoc.core.client.interfaces.ContextHandlerClientInterface;
import de.escidoc.core.client.interfaces.ItemHandlerClientInterface;
import de.escidoc.core.resources.common.reference.OrganizationalUnitRef;
import de.escidoc.core.resources.om.context.AdminDescriptor;
import de.escidoc.core.resources.om.context.AdminDescriptors;
import de.escidoc.core.resources.om.context.Context;
import de.escidoc.core.resources.om.context.OrganizationalUnitRefs;
import de.escidoc.core.resources.om.context.ContextProperties;
import de.escidoc.core.test.client.AbstractParameterizedTestBase;
import de.escidoc.core.test.client.Constants;
import de.escidoc.core.test.client.EscidocClientTestBase;

/**
 * Test login/logout methods.
 * 
 * @author SWA
 * 
 */
public class AuthenticationTest extends AbstractParameterizedTestBase {

    /**
     * 
     * @param transport
     */
    public AuthenticationTest(TransportProtocol transport) {
        super(transport);
    }

    /**
     * Test to logout.
     * 
     * @throws Exception
     *             Thrown if anythings failed.
     */
    @Test
    public void testLogout() throws Exception {
        UserManagementWrapperClient umw = new UserManagementWrapperClient();
        umw.setTransport(transport);
        umw.logout();
    }

    /**
     * 
     * @throws Exception
     */
    @Test(expected = AuthenticationException.class)
    public void shouldThrowAnAuthenticationExceptionAfterUnsuccesfulLogin()
        throws Exception {

        new Authentication(EscidocClientTestBase.DEFAULT_SERVICE_URL, "Foo", "");

    }

    /**
     * 
     * @throws Exception
     */
    @Test
    public void testAuthentication01() throws Exception {
        Authentication auth =
            new Authentication(EscidocClientTestBase.DEFAULT_SERVICE_URL,
                Constants.SYSTEM_ADMIN_USER, Constants.SYSTEM_ADMIN_PASSWORD);

        ItemHandlerClientInterface ih =
            new ItemHandlerClient(EscidocClientTestBase.DEFAULT_SERVICE_URL);
        ih.setHandle(auth.getHandle());
        ih.setTransport(transport);
        ih.retrieve(Constants.EXAMPLE_ITEM_ID);
        auth.logout();
    }

    /**
     * 
     * @throws Exception
     */
    @Test
    public void testAuthentication02() throws Exception {
        Authentication auth =
            new Authentication(EscidocClientTestBase.DEFAULT_SERVICE_URL,
                Constants.SYSTEM_ADMIN_USER, Constants.SYSTEM_ADMIN_PASSWORD);

        auth.logout();

        ItemHandlerClientInterface ih =
            new ItemHandlerClient(EscidocClientTestBase.DEFAULT_SERVICE_URL);
        ih.setHandle(auth.getHandle());
        ih.setTransport(transport);

        try {
            ih.retrieve(Constants.EXAMPLE_ITEM_ID);
        }
        catch (AuthenticationException e) {
            assertTrue(
                "AuthenticationException does not contain redirectLocation",
                e.getRedirectLocation() != null);
            try {
                new URL(e.getRedirectLocation());
            }
            catch (MalformedURLException me) {
                fail("Invalid redirectLocation");
            }
        }
    }

    /**
     * 
     * @throws Exception
     */
    @Test(expected = AuthorizationException.class)
    public void shouldThrownAnExceptionIfHandleIsNotSet() throws Exception {
        createNewContext();
    }

    /**
     * 
     * @return
     * @throws ParserConfigurationException
     * @throws EscidocClientException
     */
    private Context createNewContext() throws ParserConfigurationException,
        EscidocClientException {

        ContextHandlerClientInterface cc =
            new ContextHandlerClient(EscidocClientTestBase.DEFAULT_SERVICE_URL);
        cc.setTransport(TransportProtocol.REST);

        final Context context = new Context();
        final ContextProperties properties = new ContextProperties();
        properties.setDescription("ContextDescription");
        properties.setName("ContextName" + System.currentTimeMillis());
        properties.setPublicStatus("opened");
        properties.setPublicStatusComment("PublicStatusComment");

        final OrganizationalUnitRefs organizationalUnitRefs =
            new OrganizationalUnitRefs();
        organizationalUnitRefs.add(new OrganizationalUnitRef("escidoc:ex3"));
        properties.setOrganizationalUnitRefs(organizationalUnitRefs);
        properties.setType("type");
        context.setProperties(properties);

        final AdminDescriptors adminDescriptors = new AdminDescriptors();
        final AdminDescriptor adminDescriptor = new AdminDescriptor();
        adminDescriptor.setName("AdminDescriptorDemoName");
        final DocumentBuilderFactory factory =
            DocumentBuilderFactory.newInstance();
        final DocumentBuilder builder = factory.newDocumentBuilder();
        final Document doc = builder.newDocument();
        final Element element = doc.createElementNS(null, "admin-descriptor");
        adminDescriptor.setContent(element);

        adminDescriptors.add(adminDescriptor);
        context.setAdminDescriptors(adminDescriptors);

        // create
        return cc.create(context);
    }
}