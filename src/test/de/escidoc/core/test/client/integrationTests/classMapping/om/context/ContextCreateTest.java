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
import static org.junit.Assert.fail;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import de.escidoc.core.client.Authentication;
import de.escidoc.core.client.ContextHandlerClient;
import de.escidoc.core.client.exceptions.application.invalid.XmlSchemaValidationException;
import de.escidoc.core.resources.ResourceRef;
import de.escidoc.core.resources.om.context.AdminDescriptor;
import de.escidoc.core.resources.om.context.AdminDescriptors;
import de.escidoc.core.resources.om.context.Context;
import de.escidoc.core.resources.om.context.OrganizationalUnitRefs;
import de.escidoc.core.resources.om.context.Properties;
import de.escidoc.core.test.client.Constants;
import de.escidoc.core.test.client.EscidocClientTestBase;

/**
 * Test create of Context.
 * 
 * @author SWA
 * 
 */
public class ContextCreateTest {

    /**
     * Test if the right exception is thrown if calling create with an
     * incomplete Context.
     * 
     * @throws Exception
     *             Thrown if no or wrong exception is caught from the framework.
     */
    @Test
    public void testCreateContext01() throws Exception {

        Authentication auth =
            new Authentication(EscidocClientTestBase.DEFAULT_SERVICE_URL,
                Constants.SYSTEM_ADMIN_USER, Constants.SYSTEM_ADMIN_PASSWORD);

        ContextHandlerClient cc = new ContextHandlerClient();
        cc.setServiceAddress(EscidocClientTestBase.DEFAULT_SERVICE_URL);
        cc.setHandle(auth.getHandle());

        Context context = new Context();
        try {
            cc.create(context);
            fail("Missing Exception");
        }
        catch (Exception e) {
            Class<?> ec = XmlSchemaValidationException.class;
            EscidocClientTestBase.assertExceptionType(ec.getName()
                + " expected.", ec, e);
        }
    }

    /**
     * Test if the right exception is thrown if calling create with an
     * incomplete Context. + Properties
     * 
     * @throws Exception
     *             Thrown if no or wrong exception is caught from the framework.
     */
    @Test
    public void testCreateContext02() throws Exception {

        Authentication auth =
            new Authentication(EscidocClientTestBase.DEFAULT_SERVICE_URL,
                Constants.SYSTEM_ADMIN_USER, Constants.SYSTEM_ADMIN_PASSWORD);

        ContextHandlerClient cc = new ContextHandlerClient();
        cc.setServiceAddress(EscidocClientTestBase.DEFAULT_SERVICE_URL);
        cc.setHandle(auth.getHandle());

        Context context = new Context();
        Properties properties = new Properties();
        context.setProperties(properties);

        try {
            cc.create(context);
            fail("Missing Exception");
        }
        catch (Exception e) {
            Class<?> ec = XmlSchemaValidationException.class;
            EscidocClientTestBase.assertExceptionType(ec.getName()
                + " expected.", ec, e);
        }
    }

    /**
     * Test if the right exception is thrown if calling create with an
     * incomplete Context. + Properties, + AdminDescritor
     * 
     * @throws Exception
     *             Thrown if no or wrong exception is caught from the framework.
     */
    @Test
    public void testCreateContext03() throws Exception {

        Authentication auth =
            new Authentication(EscidocClientTestBase.DEFAULT_SERVICE_URL,
                Constants.SYSTEM_ADMIN_USER, Constants.SYSTEM_ADMIN_PASSWORD);

        ContextHandlerClient cc = new ContextHandlerClient();
        cc.setServiceAddress(EscidocClientTestBase.DEFAULT_SERVICE_URL);
        cc.setHandle(auth.getHandle());

        Context context = new Context();
        Properties properties = new Properties();
        context.setProperties(properties);
        AdminDescriptors adminDescriptors = new AdminDescriptors();
        context.setAdminDescriptors(adminDescriptors);

        try {
            cc.create(context);
            fail("Missing Exception");
        }
        catch (Exception e) {
            Class<?> ec = XmlSchemaValidationException.class;
            EscidocClientTestBase.assertExceptionType(ec.getName()
                + " expected.", ec, e);
        }
    }

    /**
     * Test if the right exception is thrown if calling create with an
     * incomplete Context. + Properties, + AdminDescritors + AdminDescriptor
     * 
     * @throws Exception
     *             Thrown if no or wrong exception is caught from the framework.
     */
    @Test
    public void testCreateContext04() throws Exception {

        Authentication auth =
            new Authentication(EscidocClientTestBase.DEFAULT_SERVICE_URL,
                Constants.SYSTEM_ADMIN_USER, Constants.SYSTEM_ADMIN_PASSWORD);

        ContextHandlerClient cc = new ContextHandlerClient();
        cc.setServiceAddress(EscidocClientTestBase.DEFAULT_SERVICE_URL);
        cc.setHandle(auth.getHandle());

        Context context = new Context();
        Properties properties = new Properties();
        context.setProperties(properties);
        AdminDescriptors adminDescriptors = new AdminDescriptors();
        AdminDescriptor adminDescriptor = new AdminDescriptor();
        adminDescriptor.setName("AdminDescriptorDemoName");
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.newDocument();
        Element element = doc.createElementNS(null, "adminDescriptor");

        adminDescriptor.setContent(element);

        adminDescriptors.add(adminDescriptor);
        context.setAdminDescriptors(adminDescriptors);

        try {
            cc.create(context);
            fail("Missing Exception");
        }
        catch (Exception e) {
            Class<?> ec = XmlSchemaValidationException.class;
            EscidocClientTestBase.assertExceptionType(ec.getName()
                + " expected.", ec, e);
        }
    }

    /**
     * Test if the right exception is thrown if calling create with an
     * incomplete Context. + Properties
     * 
     * @throws Exception
     *             Thrown if no or wrong exception is caught from the framework.
     */
    @Test
    public void testCreateContext08() throws Exception {

        Authentication auth =
            new Authentication(EscidocClientTestBase.DEFAULT_SERVICE_URL,
                Constants.SYSTEM_ADMIN_USER, Constants.SYSTEM_ADMIN_PASSWORD);

        ContextHandlerClient cc = new ContextHandlerClient();
        cc.setServiceAddress(EscidocClientTestBase.DEFAULT_SERVICE_URL);
        cc.setHandle(auth.getHandle());

        Context context = new Context();
        Properties properties = new Properties();
        properties.setDescription("ContextDescription");
        properties.setName("ContextName");
        properties.setPublicStatus("open");
        properties.setPublicStatusComment("PublicStatusComment");

        OrganizationalUnitRefs organizationalUnitRefs =
            new OrganizationalUnitRefs();
        ResourceRef organizationalUnitRef = new ResourceRef("escidoc:ex3");
        organizationalUnitRefs.add(organizationalUnitRef);
        properties.setOrganizationalUnitRefs(organizationalUnitRefs);
        context.setProperties(properties);

        AdminDescriptors adminDescriptors = new AdminDescriptors();
        AdminDescriptor adminDescriptor = new AdminDescriptor();
        adminDescriptor.setName("AdminDescriptorDemoName");
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.newDocument();
        Element element = doc.createElement("admin-descriptor");

        adminDescriptor.setContent(element);

        adminDescriptors.add(adminDescriptor);
        context.setAdminDescriptors(adminDescriptors);

        try {
            cc.create(context);
            fail("Missing Exception");
        }
        catch (Exception e) {
            Class<?> ec = XmlSchemaValidationException.class;
            EscidocClientTestBase.assertExceptionType(ec.getName()
                + " expected.", ec, e);
        }
    }

    /**
     * Test successful creation of Context.
     * 
     * @throws Exception
     *             Thrown if creation failed.
     */
    @Test
    public void testCreateContext09() throws Exception {

        Authentication auth =
            new Authentication(EscidocClientTestBase.DEFAULT_SERVICE_URL,
                Constants.SYSTEM_ADMIN_USER, Constants.SYSTEM_ADMIN_PASSWORD);

        ContextHandlerClient cc = new ContextHandlerClient();
        cc.setServiceAddress(EscidocClientTestBase.DEFAULT_SERVICE_URL);
        cc.setHandle(auth.getHandle());

        Context context = new Context();
        Properties properties = new Properties();
        properties.setDescription("ContextDescription");
        properties.setName("ContextName" + System.currentTimeMillis());
        properties.setPublicStatus("opened");
        properties.setPublicStatusComment("PublicStatusComment");

        OrganizationalUnitRefs organizationalUnitRefs =
            new OrganizationalUnitRefs();
        ResourceRef organizationalUnitRef = new ResourceRef("escidoc:ex3");
        organizationalUnitRefs.add(organizationalUnitRef);
        properties.setOrganizationalUnitRefs(organizationalUnitRefs);
        properties.setType("type");
        context.setProperties(properties);

        AdminDescriptors adminDescriptors = new AdminDescriptors();
        AdminDescriptor adminDescriptor = new AdminDescriptor();
        adminDescriptor.setName("AdminDescriptorDemoName");
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.newDocument();
        Element element = doc.createElementNS(null, "admin-descriptor");
        adminDescriptor.setContent(element);

        adminDescriptors.add(adminDescriptor);
        context.setAdminDescriptors(adminDescriptors);

        // create
        Context createdContext = cc.create(context);

        assertEquals("Name differs", context.getProperties().getName(),
            createdContext.getProperties().getName());
        assertEquals("Description differs", context
            .getProperties().getDescription(), createdContext
            .getProperties().getDescription());

    }

}
