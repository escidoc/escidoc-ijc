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
package de.escidoc.core.test.client.integrationTests.classMapping.om.context;

import static org.junit.Assert.assertEquals;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import de.escidoc.core.client.Authentication;
import de.escidoc.core.client.ContextHandlerClient;
import de.escidoc.core.client.exceptions.application.invalid.XmlSchemaValidationException;
import de.escidoc.core.client.interfaces.ContextHandlerClientInterface;
import de.escidoc.core.resources.common.properties.PublicStatus;
import de.escidoc.core.resources.common.reference.OrganizationalUnitRef;
import de.escidoc.core.resources.om.context.AdminDescriptor;
import de.escidoc.core.resources.om.context.AdminDescriptors;
import de.escidoc.core.resources.om.context.Context;
import de.escidoc.core.resources.om.context.ContextProperties;
import de.escidoc.core.resources.om.context.OrganizationalUnitRefs;
import de.escidoc.core.test.client.EscidocClientTestBase;

/**
 * Test create of Context.
 * 
 * @author SWA
 * 
 */
public class ContextCreateTest {

    private Authentication auth;

    private ContextHandlerClientInterface cc;

    @Before
    public void init() throws Exception {
        auth =
            new Authentication(EscidocClientTestBase.getDefaultInfrastructureURL(),
                EscidocClientTestBase.SYSTEM_ADMIN_USER, EscidocClientTestBase.SYSTEM_ADMIN_PASSWORD);
        cc = new ContextHandlerClient(auth.getServiceAddress());
        cc.setHandle(auth.getHandle());
    }

    @After
    public void post() throws Exception {
        if (auth != null)
            auth.logout();
    }

    /**
     * Test if the right exception is thrown if calling create with an
     * incomplete Context.
     * 
     * @throws Exception
     *             Thrown if no or wrong exception is caught from the framework.
     */
    @Test(expected = XmlSchemaValidationException.class)
    public void testCreateContext01() throws Exception {
        final Context context = new Context();
        cc.create(context);
    }

    /**
     * Test if the right exception is thrown if calling create with an
     * incomplete Context. + Properties
     * 
     * @throws Exception
     *             Thrown if no or wrong exception is caught from the framework.
     */
    @Test(expected = XmlSchemaValidationException.class)
    public void testCreateContext02() throws Exception {
        final Context context = new Context();
        final ContextProperties properties = new ContextProperties();
        context.setProperties(properties);

        cc.create(context);
    }

    /**
     * Test if the right exception is thrown if calling create with an
     * incomplete Context. + Properties, + AdminDescritor
     * 
     * @throws Exception
     *             Thrown if no or wrong exception is caught from the framework.
     */
    @Test(expected = XmlSchemaValidationException.class)
    public void testCreateContext03() throws Exception {
        final Context context = new Context();
        final ContextProperties properties = new ContextProperties();
        context.setProperties(properties);
        final AdminDescriptors adminDescriptors = new AdminDescriptors();
        context.setAdminDescriptors(adminDescriptors);

        cc.create(context);
    }

    /**
     * Test if the right exception is thrown if calling create with an
     * incomplete Context. + Properties, + AdminDescritors + AdminDescriptor
     * 
     * @throws Exception
     *             Thrown if no or wrong exception is caught from the framework.
     */
    @Test(expected = XmlSchemaValidationException.class)
    public void testCreateContext04() throws Exception {
        final Context context = new Context();
        final ContextProperties properties = new ContextProperties();
        context.setProperties(properties);
        final AdminDescriptors adminDescriptors = new AdminDescriptors();
        final AdminDescriptor adminDescriptor = new AdminDescriptor("AdminDescriptorDemoName");
        final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        final DocumentBuilder builder = factory.newDocumentBuilder();
        final Document doc = builder.newDocument();
        final Element element = doc.createElementNS(null, "adminDescriptor");

        adminDescriptor.setContent(element);

        adminDescriptors.add(adminDescriptor);
        context.setAdminDescriptors(adminDescriptors);

        cc.create(context);
    }

    /**
     * Test if the right exception is thrown if calling create with an
     * incomplete Context. + Properties
     * 
     * @throws Exception
     *             Thrown if no or wrong exception is caught from the framework.
     */
    @Test(expected = XmlSchemaValidationException.class)
    public void testCreateContext08() throws Exception {
        final Context context = new Context();
        final ContextProperties properties = new ContextProperties();
        properties.setDescription("ContextDescription");
        properties.setName("ContextName");
        properties.setPublicStatus(PublicStatus.OPENED);
        properties.setPublicStatusComment("PublicStatusComment");

        final OrganizationalUnitRefs organizationalUnitRefs = new OrganizationalUnitRefs();
        organizationalUnitRefs.add(new OrganizationalUnitRef(EscidocClientTestBase.getStaticOrganizationalUnitId()));

        properties.setOrganizationalUnitRefs(organizationalUnitRefs);
        context.setProperties(properties);

        final AdminDescriptors adminDescriptors = new AdminDescriptors();
        final AdminDescriptor adminDescriptor = new AdminDescriptor("AdminDescriptorDemoName");
        final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        final DocumentBuilder builder = factory.newDocumentBuilder();
        final Document doc = builder.newDocument();
        final Element element = doc.createElement("admin-descriptor");

        adminDescriptor.setContent(element);

        adminDescriptors.add(adminDescriptor);
        context.setAdminDescriptors(adminDescriptors);

        cc.create(context);
    }

    /**
     * Test successful creation of Context.
     * 
     * @throws Exception
     *             Thrown if creation failed.
     */
    @Test
    public void testCreateContext09() throws Exception {
        final Context context = new Context();
        final ContextProperties properties = new ContextProperties();
        properties.setDescription("ContextDescription");
        properties.setName("ContextName" + System.currentTimeMillis());
        properties.setPublicStatus(PublicStatus.OPENED);
        properties.setPublicStatusComment("PublicStatusComment");

        final OrganizationalUnitRefs organizationalUnitRefs = new OrganizationalUnitRefs();
        organizationalUnitRefs.add(new OrganizationalUnitRef(EscidocClientTestBase.getStaticOrganizationalUnitId()));

        properties.setOrganizationalUnitRefs(organizationalUnitRefs);
        properties.setType("type");
        context.setProperties(properties);

        final AdminDescriptors adminDescriptors = new AdminDescriptors();
        final AdminDescriptor adminDescriptor = new AdminDescriptor("AdminDescriptorDemoName");
        final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        final DocumentBuilder builder = factory.newDocumentBuilder();
        final Document doc = builder.newDocument();
        final Element element = doc.createElementNS(null, "admin-descriptor");
        adminDescriptor.setContent(element);

        adminDescriptors.add(adminDescriptor);
        context.setAdminDescriptors(adminDescriptors);

        // create
        final Context createdContext = cc.create(context);

        assertEquals("Name differs", context.getProperties().getName(), createdContext.getProperties().getName());
        assertEquals("Description differs", context.getProperties().getDescription(), createdContext
            .getProperties().getDescription());
        // FIXME uncomment as soon as this bug has been fixed
        // assertEquals("Wrong number of admin-descriptors", 1,
        // createdContext.getAdminDescriptors().size());
    }
}