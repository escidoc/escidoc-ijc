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
package de.escidoc.core.test.client.integrationTests.classMapping.om.context;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import gov.loc.www.zing.srw.SearchRetrieveRequestType;

import java.util.Collection;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import de.escidoc.core.client.Authentication;
import de.escidoc.core.client.ContextHandlerClient;
import de.escidoc.core.client.TransportProtocol;
import de.escidoc.core.client.UserAccountHandlerClient;
import de.escidoc.core.client.exceptions.application.notfound.ContextNotFoundException;
import de.escidoc.core.client.interfaces.ContextHandlerClientInterface;
import de.escidoc.core.client.interfaces.UserAccountHandlerClientInterface;
import de.escidoc.core.common.jibx.Factory;
import de.escidoc.core.common.jibx.Marshaller;
import de.escidoc.core.resources.ResourceRef;
import de.escidoc.core.resources.ResourceRef.RESOURCE_TYPE;
import de.escidoc.core.resources.aa.useraccount.UserAccount;
import de.escidoc.core.resources.common.Filter;
import de.escidoc.core.resources.common.TaskParam;
import de.escidoc.core.resources.om.GenericVersionableResource;
import de.escidoc.core.resources.om.MemberList;
import de.escidoc.core.resources.om.context.AdminDescriptor;
import de.escidoc.core.resources.om.context.AdminDescriptors;
import de.escidoc.core.resources.om.context.Context;
import de.escidoc.core.resources.om.context.ContextList;
import de.escidoc.core.resources.om.context.OrganizationalUnitRefs;
import de.escidoc.core.resources.om.context.Properties;
import de.escidoc.core.test.client.AbstractParameterizedTestBase;
import de.escidoc.core.test.client.Constants;
import de.escidoc.core.test.client.EscidocClientTestBase;

/**
 * 
 * @author SWA
 * 
 */
public class ContextHandlerClientTest extends AbstractParameterizedTestBase {

    public ContextHandlerClientTest(TransportProtocol transport) {
        super(transport);
    }

    /**
     * Test if the right exception is thrown if a non existing Context is
     * retrieved.
     * 
     * @throws Exception
     *             Thrown if not the right exception is caught.
     */
    @Test
    public void testRetrieveUnknown() throws Exception {
        try {

            Authentication auth =
                new Authentication(EscidocClientTestBase.DEFAULT_SERVICE_URL,
                    Constants.SYSTEM_ADMIN_USER,
                    Constants.SYSTEM_ADMIN_PASSWORD);

            ContextHandlerClient cc = new ContextHandlerClient();
            cc.setServiceAddress(EscidocClientTestBase.DEFAULT_SERVICE_URL);
            cc.setHandle(auth.getHandle());
            cc.setTransport(transport);

            cc.retrieve(Constants.INVALID_RESOURCE_ID);
            fail("Missing Exception");
        }
        catch (ContextNotFoundException e) {
            return;
        }
        catch (Exception e) {
            fail("Wrong exception caught: " + e.getMessage());
        }
    }

    /**
     * Test retrieving existing Context.
     * 
     * @throws Exception
     *             Thrown if retrieve and/or marshalling failed.
     */
    @Test
    public void testRetrieve01() throws Exception {

        Authentication auth =
            new Authentication(EscidocClientTestBase.DEFAULT_SERVICE_URL,
                Constants.SYSTEM_ADMIN_USER, Constants.SYSTEM_ADMIN_PASSWORD);

        ContextHandlerClientInterface cc = new ContextHandlerClient();
        cc.setServiceAddress(EscidocClientTestBase.DEFAULT_SERVICE_URL);
        cc.setHandle(auth.getHandle());
        cc.setTransport(transport);

        Context context = new Context();
        Properties properties = new Properties();
        properties.setDescription("ContextDescription");
        properties.setName("ContextName" + System.currentTimeMillis());
        properties.setPublicStatus("opened");
        properties.setPublicStatusComment("PublicStatusComment");

        OrganizationalUnitRefs organizationalUnitRefs =
            new OrganizationalUnitRefs();
        ResourceRef organizationalUnitRef =
            new ResourceRef("escidoc:ex3", RESOURCE_TYPE.OrganizationalUnit);
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

        Context createdContext = cc.create(context);
        String objid = createdContext.getObjid();
        Context retrievedContext = cc.retrieve(objid);

        Factory
            .getMarshallerFactory(cc.getTransport()).getContextMarshaller()
            .marshalDocument(retrievedContext);
    }

    /**
     * Test retrieving existing Context.
     * 
     * @throws Exception
     *             Thrown if retrieve and/or marshalling failed.
     */
    @Test
    public void testRetrieveUpdate() throws Exception {

        Authentication auth =
            new Authentication(EscidocClientTestBase.DEFAULT_SERVICE_URL,
                Constants.SYSTEM_ADMIN_USER, Constants.SYSTEM_ADMIN_PASSWORD);

        ContextHandlerClientInterface cc = new ContextHandlerClient();
        cc.setServiceAddress(EscidocClientTestBase.DEFAULT_SERVICE_URL);
        cc.setHandle(auth.getHandle());
        cc.setTransport(transport);

        Context context = new Context();
        Properties properties = new Properties();
        properties.setDescription("ContextDescription");
        properties.setName("ContextName" + System.currentTimeMillis());
        properties.setPublicStatus("opened");
        properties.setPublicStatusComment("PublicStatusComment");

        OrganizationalUnitRefs organizationalUnitRefs =
            new OrganizationalUnitRefs();
        ResourceRef organizationalUnitRef =
            new ResourceRef("escidoc:ex3", RESOURCE_TYPE.OrganizationalUnit);
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
        String objid = createdContext.getObjid();

        // retrieve
        Context retrivedContext = cc.retrieve(objid);

        // update
        cc.update(retrivedContext);
        Factory
            .getMarshallerFactory(cc.getTransport()).getContextMarshaller()
            .marshalDocument(retrivedContext);
    }

    /**
     * Test retrieving contexts through filter request.
     * 
     * @throws Exception
     *             Thrown if anythings failed.
     */
    @Test
    public void testRetrieveContextsOld() throws Exception {

        // just getting a valid objid of a user
        Authentication auth =
            new Authentication(EscidocClientTestBase.DEFAULT_SERVICE_URL,
                Constants.SYSTEM_ADMIN_USER, Constants.SYSTEM_ADMIN_PASSWORD);

        UserAccountHandlerClientInterface uac = new UserAccountHandlerClient();
        uac.setServiceAddress(EscidocClientTestBase.DEFAULT_SERVICE_URL);
        uac.setHandle(auth.getHandle());
        uac.setTransport(transport);

        UserAccount me = uac.retrieveCurrentUser();

        // call filter without Authentication (login)
        ContextHandlerClient cc = new ContextHandlerClient();
        cc.setServiceAddress(EscidocClientTestBase.DEFAULT_SERVICE_URL);
        cc.setHandle(auth.getHandle());
        cc.setTransport(transport);

        TaskParam filterParam = new TaskParam();
        Collection<Filter> filters = TaskParam.filtersFactory();

        filters.add(getFilter(
            "http://escidoc.de/core/01/structural-relations/created-by",
            me.getObjid(), null));
        filterParam.setFilters(filters);
        Factory
            .getMarshallerFactory(cc.getTransport()).getTaskParamMarshaller()
            .marshalDocument(filterParam);

        ContextList contextList = cc.retrieveContexts(filterParam);

        assertTrue("result list is empty, try another filter", contextList
            .getContexts().size() != 0);
    }

    /**
     * Test retrieving Members through filter request.
     * 
     * @throws Exception
     *             Thrown if anythings failed.
     */
    @Test
    public void testRetrieveMembersOld() throws Exception {
        Authentication auth =
            new Authentication(EscidocClientTestBase.DEFAULT_SERVICE_URL,
                Constants.SYSTEM_ADMIN_USER, Constants.SYSTEM_ADMIN_PASSWORD);

        ContextHandlerClientInterface cc = new ContextHandlerClient();
        cc.setServiceAddress(EscidocClientTestBase.DEFAULT_SERVICE_URL);
        cc.setHandle(auth.getHandle());
        cc.setTransport(transport);

        // just getting a valid objid of a user
        UserAccountHandlerClientInterface uac = new UserAccountHandlerClient();
        uac.setServiceAddress(EscidocClientTestBase.DEFAULT_SERVICE_URL);
        uac.setHandle(auth.getHandle());
        uac.setTransport(transport);

        UserAccount me = uac.retrieveCurrentUser();

        TaskParam filterParam = new TaskParam();
        Collection<Filter> filters = TaskParam.filtersFactory();

        filters.add(getFilter(
            "http://escidoc.de/core/01/structural-relations/created-by",
            me.getObjid(), null));
        filterParam.setFilters(filters);

        Factory
            .getMarshallerFactory(cc.getTransport()).getTaskParamMarshaller()
            .marshalDocument(filterParam);

        MemberList memberList = cc.retrieveMembers("escidoc:ex1", filterParam);
        Marshaller<MemberList> m =
            Factory.getMarshallerFactory(transport).getMemberListMarshaller();

        m.marshalDocument(memberList);

        assertTrue("result list is empty, try another filter", memberList
            .getMembers().size() != 0);
    }

    /**
     * Test retrieving Members through new filter request.
     * 
     * @throws Exception
     *             Thrown if anythings failed.
     */
    @Test
    public void testRetrieveMembersNew() throws Exception {

        Authentication auth =
            new Authentication(EscidocClientTestBase.DEFAULT_SERVICE_URL,
                Constants.SYSTEM_ADMIN_USER, Constants.SYSTEM_ADMIN_PASSWORD);

        ContextHandlerClientInterface cc = new ContextHandlerClient();
        cc.setServiceAddress(EscidocClientTestBase.DEFAULT_SERVICE_URL);
        cc.setHandle(auth.getHandle());
        cc.setTransport(transport);

        // just getting a valid objid of a user
        UserAccountHandlerClientInterface uac = new UserAccountHandlerClient();
        uac.setServiceAddress(EscidocClientTestBase.DEFAULT_SERVICE_URL);
        uac.setHandle(auth.getHandle());
        uac.setTransport(transport);

        UserAccount me = uac.retrieveCurrentUser();

        TaskParam filterParam = new TaskParam();
        Collection<Filter> filters = TaskParam.filtersFactory();

        filters.add(getFilter(
            "http://escidoc.de/core/01/structural-relations/created-by",
            me.getObjid(), null));
        filterParam.setFilters(filters);

        Factory
            .getMarshallerFactory(cc.getTransport()).getTaskParamMarshaller()
            .marshalDocument(filterParam);

        SearchRetrieveRequestType filter = new SearchRetrieveRequestType();
        filter.setQuery("\"/properties/created-by/id\"=" + me.getObjid());

        Collection<GenericVersionableResource> memberList =
            cc.retrieveMembersAsList("escidoc:ex1", filter);

        assertTrue("result list is empty, try another filter",
            memberList.size() != 0);
    }

    /**
     * Prepare and Filter class from the parameter collection.
     * 
     * @param name
     *            name
     * @param value
     *            value
     * @param ids
     *            ids
     * @return filter
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
