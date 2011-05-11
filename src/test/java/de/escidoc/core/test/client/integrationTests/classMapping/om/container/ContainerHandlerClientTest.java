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

import static org.junit.Assert.assertNotNull;
import gov.loc.www.zing.srw.SearchRetrieveRequestType;

import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import de.escidoc.core.client.Authentication;
import de.escidoc.core.client.ContainerHandlerClient;
import de.escidoc.core.client.exceptions.application.notfound.ContainerNotFoundException;
import de.escidoc.core.client.interfaces.ContainerHandlerClientInterface;
import de.escidoc.core.common.jibx.MarshallerFactory;
import de.escidoc.core.resources.VersionableResource;
import de.escidoc.core.resources.common.MetadataRecord;
import de.escidoc.core.resources.common.MetadataRecords;
import de.escidoc.core.resources.common.reference.ContentModelRef;
import de.escidoc.core.resources.common.reference.ContextRef;
import de.escidoc.core.resources.om.container.Container;
import de.escidoc.core.resources.om.container.ContainerProperties;
import de.escidoc.core.test.client.EscidocClientTestBase;

/**
 * Test ContainerHandler.
 * 
 * @author SWA
 * 
 */
public class ContainerHandlerClientTest {

    private Authentication auth;

    private ContainerHandlerClientInterface cc;

    @Before
    public void init() throws Exception {
        auth =
            new Authentication(
                EscidocClientTestBase.getDefaultInfrastructureURL(),
                EscidocClientTestBase.SYSTEM_ADMIN_USER,
                EscidocClientTestBase.SYSTEM_ADMIN_PASSWORD);
        cc = new ContainerHandlerClient(auth.getServiceAddress());
        cc.setHandle(auth.getHandle());
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
        properties.setContext(new ContextRef(EscidocClientTestBase
            .getStaticContextId()));
        properties.setContentModel(new ContentModelRef(EscidocClientTestBase
            .getStaticContentModelId()));
        containerNew.setProperties(properties);
        MetadataRecords mdRecords = new MetadataRecords();
        MetadataRecord mdRecord = new MetadataRecord("escidoc");
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

        MarshallerFactory
            .getInstance(cc.getTransport()).getMarshaller(Container.class)
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
        SearchRetrieveRequestType request = new SearchRetrieveRequestType();
        request
            .setQuery("\"http://escidoc.de/core/01/structural-relations/created-by\"=escidoc:user42");

        List<Container> containerList = cc.retrieveContainersAsList(request);

        // FIXME check containerList
        assertNotNull(containerList);
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
        properties.setContext(new ContextRef(EscidocClientTestBase
            .getStaticContextId()));
        properties.setContentModel(new ContentModelRef(EscidocClientTestBase
            .getStaticContentModelId()));
        containerNew.setProperties(properties);
        MetadataRecords mdRecords = new MetadataRecords();
        MetadataRecord mdRecord = new MetadataRecord("escidoc");
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
        MarshallerFactory
            .getInstance(cc.getTransport()).getMarshaller(Container.class)
            .marshalDocument(container);

        SearchRetrieveRequestType request = new SearchRetrieveRequestType();
        request
            .setQuery("\"http://escidoc.de/core/01/structural-relations/created-by\"=escidoc:user42");

        List<VersionableResource> memberList =
            cc.retrieveMembersAsList(objid, request);

        // FIXME check containerList
        assertNotNull(memberList);
    }
}