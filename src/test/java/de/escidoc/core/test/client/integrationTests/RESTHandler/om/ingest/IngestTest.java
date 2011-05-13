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
package de.escidoc.core.test.client.integrationTests.RESTHandler.om.ingest;

import java.util.LinkedList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import de.escidoc.core.client.Authentication;
import de.escidoc.core.client.TransportProtocol;
import de.escidoc.core.client.rest.RestContainerHandlerClient;
import de.escidoc.core.client.rest.RestIngestHandlerClient;
import de.escidoc.core.client.rest.RestItemHandlerClient;
import de.escidoc.core.common.jibx.Marshaller;
import de.escidoc.core.common.jibx.MarshallerFactory;
import de.escidoc.core.resources.common.MetadataRecord;
import de.escidoc.core.resources.common.MetadataRecords;
import de.escidoc.core.resources.common.properties.ContentModelSpecific;
import de.escidoc.core.resources.common.reference.ContentModelRef;
import de.escidoc.core.resources.common.reference.ContextRef;
import de.escidoc.core.resources.om.container.Container;
import de.escidoc.core.resources.om.container.ContainerProperties;
import de.escidoc.core.resources.om.item.Item;
import de.escidoc.core.test.client.EscidocClientTestBase;
import de.escidoc.core.test.client.integrationTests.classMapping.om.ResourceUtility;

/**
 * Test ingest REST interface.
 * 
 * @author SWA
 * 
 */
public class IngestTest {

    private Authentication auth;

    @Before
    public void init() throws Exception {
        auth =
            new Authentication(EscidocClientTestBase.getDefaultInfrastructureURL(),
                EscidocClientTestBase.SYSTEM_ADMIN_USER, EscidocClientTestBase.SYSTEM_ADMIN_PASSWORD);
    }

    @After
    public void post() throws Exception {
        auth.logout();
    }

    /**
     * Test ingesting an Item.
     * 
     * @throws Exception
     *             Thrown if no or wrong exception is caught from the framework.
     */
    @Test
    public void testIngestItem01() throws Exception {

        RestItemHandlerClient ihc = new RestItemHandlerClient(auth.getServiceAddress());
        ihc.setHandle(auth.getHandle());

        Marshaller<Item> m = MarshallerFactory.getInstance(TransportProtocol.REST).getMarshaller(Item.class);

        Item item = new Item();

        item.getProperties().setContext(new ContextRef(EscidocClientTestBase.getStaticContextId()));
        item.getProperties().setContentModel(new ContentModelRef(EscidocClientTestBase.getStaticContentModelId()));

        // Content-model
        ContentModelSpecific cms = ResourceUtility.getContentModelSpecific();
        item.getProperties().setContentModelSpecific(cms);

        // Metadata Record(s)
        MetadataRecords mdRecords = new MetadataRecords();
        MetadataRecord mdrecord = ResourceUtility.getMdRecord("escidoc");
        mdRecords.add(mdrecord);
        item.setMetadataRecords(mdRecords);

        // create
        String resultXml = ihc.create(m.marshalDocument(item));

        Item createdItem = m.unmarshalDocument(resultXml);

        String objId = createdItem.getObjid();

        // organize Item
        String itemXml = ihc.retrieve(objId);

        // ingest Item
        RestIngestHandlerClient inhc = new RestIngestHandlerClient(auth.getServiceAddress());
        inhc.setHandle(auth.getHandle());

        inhc.ingest(itemXml);
    }

    /**
     * Test ingesting a Container.
     * 
     * @throws Exception
     *             Thrown if no or wrong exception is caught from the framework.
     */
    @Test
    public void testIngestContainer01() throws Exception {
        RestContainerHandlerClient cc = new RestContainerHandlerClient(auth.getServiceAddress());
        cc.setHandle(auth.getHandle());

        Marshaller<Container> m = MarshallerFactory.getInstance(TransportProtocol.REST).getMarshaller(Container.class);

        Container container = new Container();

        // properties
        ContainerProperties properties = new ContainerProperties();
        properties.setContext(new ContextRef(EscidocClientTestBase.getStaticContextId()));
        properties.setContentModel(new ContentModelRef(EscidocClientTestBase.getStaticContentModelId()));

        // Content-model-specific
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.newDocument();
        Element contentModelSpecific = doc.createElementNS(null, "cms");

        List<Element> cmsContent = new LinkedList<Element>();
        cmsContent.add(contentModelSpecific);
        ContentModelSpecific cms = new ContentModelSpecific();

        cms.setContent(cmsContent);

        properties.setContentModelSpecific(cms);
        container.setProperties(properties);

        MetadataRecords mdRecords = new MetadataRecords();
        MetadataRecord mdRecord = new MetadataRecord("escidoc");

        Document docMdRecord = builder.newDocument();
        Element element = docMdRecord.createElementNS(null, "myMdRecord");
        mdRecord.setContent(element);

        mdRecords.add(mdRecord);
        container.setMetadataRecords(mdRecords);

        String resultXml = cc.create(m.marshalDocument(container));

        Container createdContainer = m.unmarshalDocument(resultXml);
        String objId = createdContainer.getObjid();

        // organize Container
        String containerXml = cc.retrieve(objId);

        // ingest Item
        RestIngestHandlerClient rihc = new RestIngestHandlerClient(auth.getServiceAddress());
        rihc.setHandle(auth.getHandle());

        rihc.ingest(containerXml);
    }

    @Test
    public void testIngestContainer02() throws Exception {
        RestContainerHandlerClient cc = new RestContainerHandlerClient(auth.getServiceAddress());
        cc.setHandle(auth.getHandle());

        Marshaller<Container> m = MarshallerFactory.getInstance(TransportProtocol.REST).getMarshaller(Container.class);

        Container container = new Container();

        // properties
        ContainerProperties properties = new ContainerProperties();
        properties.setContext(new ContextRef(EscidocClientTestBase.getStaticContextId()));
        properties.setContentModel(new ContentModelRef(EscidocClientTestBase.getStaticContentModelId()));

        // Content-model-specific
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.newDocument();
        Element contentModelSpecific = doc.createElementNS(null, "cms");

        List<Element> cmsContent = new LinkedList<Element>();
        cmsContent.add(contentModelSpecific);
        ContentModelSpecific cms = new ContentModelSpecific();

        cms.setContent(cmsContent);

        properties.setContentModelSpecific(cms);
        container.setProperties(properties);

        MetadataRecords mdRecords = new MetadataRecords();
        MetadataRecord mdRecord = new MetadataRecord("escidoc");

        Document docMdRecord = builder.newDocument();
        Element element = docMdRecord.createElementNS(null, "myMdRecord");
        mdRecord.setContent(element);

        mdRecords.add(mdRecord);
        container.setMetadataRecords(mdRecords);

        String resultXml = m.marshalDocument(container);

        // ingest Item
        RestIngestHandlerClient rihc = new RestIngestHandlerClient(auth.getServiceAddress());
        rihc.setHandle(auth.getHandle());

        rihc.ingest(resultXml);
    }

}
