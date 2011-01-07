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
package de.escidoc.core.test.client.integrationTests.classMapping.om.container;

import java.util.Collection;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import de.escidoc.core.client.Authentication;
import de.escidoc.core.client.ContainerHandlerClient;
import de.escidoc.core.client.TransportProtocol;
import de.escidoc.core.client.exceptions.application.notfound.ContainerNotFoundException;
import de.escidoc.core.client.interfaces.ContainerHandlerClientInterface;
import de.escidoc.core.common.jibx.Marshaller;
import de.escidoc.core.common.jibx.MarshallerFactory;
import de.escidoc.core.resources.common.Filter;
import de.escidoc.core.resources.common.MetadataRecord;
import de.escidoc.core.resources.common.MetadataRecords;
import de.escidoc.core.resources.common.TaskParam;
import de.escidoc.core.resources.common.reference.ContentModelRef;
import de.escidoc.core.resources.common.reference.ContextRef;
import de.escidoc.core.resources.om.MemberList;
import de.escidoc.core.resources.om.container.Container;
import de.escidoc.core.resources.om.container.ContainerList;
import de.escidoc.core.resources.om.container.ContainerProperties;
import de.escidoc.core.test.client.AbstractParameterizedTestBase;
import de.escidoc.core.test.client.Constants;
import de.escidoc.core.test.client.EscidocClientTestBase;

/**
 * Test ContainerHandler.
 * 
 * @author SWA
 * 
 */
public class ContainerHandlerClientTest extends AbstractParameterizedTestBase {

    private Authentication auth;

    private ContainerHandlerClientInterface cc;

    public ContainerHandlerClientTest(final TransportProtocol transport) {
        super(transport);
    }

    @Before
    public void init() throws Exception {
        auth =
            new Authentication(EscidocClientTestBase.DEFAULT_SERVICE_URL,
                Constants.SYSTEM_ADMIN_USER, Constants.SYSTEM_ADMIN_PASSWORD);
        cc = new ContainerHandlerClient(auth.getServiceAddress());
        cc.setHandle(auth.getHandle());
        cc.setTransport(transport);
    }

    @After
    public void post() throws Exception {
        if (auth != null)
            auth.logout();
    }

    /**
     * Test retrieving existing Container.
     * 
     * @throws Exception
     *             Thrown if retrieving or unmarshalling failed.
     */
    @Test
    public void testRetrieve() throws Exception {
        // create first a Container
        Container containerNew = new Container();
        ContainerProperties properties = new ContainerProperties();
        properties.setContext(new ContextRef(Constants.EXAMPLE_CONTEXT_ID));
        properties.setContentModel(new ContentModelRef(
            Constants.EXAMPLE_CONTENT_MODEL_ID));
        containerNew.setProperties(properties);
        MetadataRecords mdRecords = new MetadataRecords();
        MetadataRecord mdRecord = new MetadataRecord();
        mdRecord.setName("escidoc");
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.newDocument();
        Element element = doc.createElementNS(null, "myMdRecord");
        mdRecord.setContent(element);

        mdRecords.add(mdRecord);
        containerNew.setMetadataRecords(mdRecords);
        Container createdContainer = cc.create(containerNew);
        String objid = createdContainer.getObjid();

        // retrieve the created Container
        Container container = cc.retrieve(objid);

        MarshallerFactory.getInstance(cc.getTransport()).getMarshaller(Container.class)
            .marshalDocument(container);
    }

    /**
     * Test retrieving a Container which does not exists.
     * 
     * @throws Exception
     *             If infrastructure throws no or wrong Exception.
     */
    @Test(expected = ContainerNotFoundException.class)
    public void testRetrieveUnknown() throws Exception {
        cc.retrieve("escidoc:-1");
    }

    /**
     * Test retrieving Containers through filter request.
     * 
     * @throws Exception
     *             Thrown if anythings failed.
     */
    @Test
    public void testRetrieveContainers() throws Exception {
        TaskParam filterParam = new TaskParam();
        Collection<Filter> filters = filterParam.getFilters();

        filters.add(getFilter(
            "http://escidoc.de/core/01/structural-relations/created-by",
            "escidoc:user42", null));
        filterParam.setFilters(filters);

        MarshallerFactory.getInstance(cc.getTransport()).getMarshaller(TaskParam.class)
            .marshalDocument(filterParam);

        ContainerList containerList = cc.retrieveContainers(filterParam);
        Marshaller<ContainerList> m =
            MarshallerFactory.getInstance(transport).getMarshaller(ContainerList.class);
        String xml = m.marshalDocument(containerList);

        // FIXME check containerList
    }

    /**
     * Test retrieving Members through filter request.
     * 
     * @throws Exception
     *             Thrown if anythings failed.
     */
    @Test
    public void testRetrieveMembers() throws Exception {
        Container containerNew = new Container();
        ContainerProperties properties = new ContainerProperties();
        properties.setContext(new ContextRef(Constants.EXAMPLE_CONTEXT_ID));
        properties.setContentModel(new ContentModelRef(
            Constants.EXAMPLE_CONTENT_MODEL_ID));
        containerNew.setProperties(properties);
        MetadataRecords mdRecords = new MetadataRecords();
        MetadataRecord mdRecord = new MetadataRecord();
        mdRecord.setName("escidoc");
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.newDocument();
        Element element = doc.createElementNS(null, "myMdRecord");
        mdRecord.setContent(element);

        mdRecords.add(mdRecord);
        containerNew.setMetadataRecords(mdRecords);
        Container createdContainer = cc.create(containerNew);
        String objid = createdContainer.getObjid();
        Container container = cc.retrieve(objid);
        MarshallerFactory.getInstance(cc.getTransport()).getMarshaller(Container.class)
            .marshalDocument(container);

        TaskParam filterParam = new TaskParam();
        Collection<Filter> filters = filterParam.getFilters();

        filters.add(getFilter(
            "http://escidoc.de/core/01/structural-relations/created-by",
            "escidoc:user42", null));
        filterParam.setFilters(filters);
        MarshallerFactory.getInstance(cc.getTransport()).getMarshaller(TaskParam.class)
            .marshalDocument(filterParam);

        MemberList memberList = cc.retrieveMembers(objid, filterParam);
        Marshaller<MemberList> m =
            MarshallerFactory.getInstance(transport).getMarshaller(MemberList.class);
        String xml = m.marshalDocument(memberList);

        // FIXME check containerList
    }

    /**
     * Prepare and Filter class from the parameter collection.
     * 
     * @param name
     * @param value
     * @param ids
     * @return Filter
     */
    // FIXME redundant method
    private Filter getFilter(
        final String name, final String value, final Collection<String> ids) {

        Filter filter = new Filter();
        filter.setName(name);
        filter.setValue(value);
        filter.setIds(ids);
        return filter;
    }
}