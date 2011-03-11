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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.xpath.XPathAPI;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import de.escidoc.core.client.Authentication;
import de.escidoc.core.client.ContainerHandlerClient;
import de.escidoc.core.client.ItemHandlerClient;
import de.escidoc.core.client.exceptions.EscidocException;
import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.client.exceptions.TransportException;
import de.escidoc.core.client.exceptions.application.invalid.InvalidXmlException;
import de.escidoc.core.client.exceptions.application.invalid.XmlSchemaValidationException;
import de.escidoc.core.client.interfaces.ContainerHandlerClientInterface;
import de.escidoc.core.client.interfaces.ItemHandlerClientInterface;
import de.escidoc.core.common.XmlUtility;
import de.escidoc.core.common.jibx.Marshaller;
import de.escidoc.core.common.jibx.MarshallerFactory;
import de.escidoc.core.resources.ResourceType;
import de.escidoc.core.resources.common.MetadataRecord;
import de.escidoc.core.resources.common.MetadataRecords;
import de.escidoc.core.resources.common.Relation;
import de.escidoc.core.resources.common.Relations;
import de.escidoc.core.resources.common.properties.ContentModelSpecific;
import de.escidoc.core.resources.common.reference.ContentModelRef;
import de.escidoc.core.resources.common.reference.ContextRef;
import de.escidoc.core.resources.common.reference.ItemRef;
import de.escidoc.core.resources.common.structmap.ContainerMemberRef;
import de.escidoc.core.resources.common.structmap.ItemMemberRef;
import de.escidoc.core.resources.common.structmap.MemberRef;
import de.escidoc.core.resources.common.structmap.StructMap;
import de.escidoc.core.resources.om.container.Container;
import de.escidoc.core.resources.om.container.ContainerProperties;
import de.escidoc.core.resources.om.item.Item;
import de.escidoc.core.test.client.EscidocClientTestBase;
import de.escidoc.core.test.client.integrationTests.classMapping.om.ResourceUtility;
import de.escidoc.core.test.client.util.Asserts;

/**
 * Test create Container.
 * 
 * @author SWA
 * 
 */
public class ContainerCreateTest {

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
     * Test if the right exception is thrown if calling create with an
     * incomplete Container.
     * 
     * @throws Exception
     *             Thrown if no or wrong exception is caught from the framework.
     */
    @Test(expected = XmlSchemaValidationException.class)
    public void testCreateContainer01() throws Exception {
        Container container = new Container();
        cc.create(container);
    }

    /**
     * Test if the right exception is thrown if calling create with an
     * incomplete Container.
     * 
     * A title is set (but shouldn't have influence).
     * 
     * @throws Exception
     *             Thrown if no or wrong exception is caught from the framework.
     */
    @Test(expected = XmlSchemaValidationException.class)
    public void testCreateContainer02() throws Exception {
        Container container = new Container();
        container.setXLinkTitle("New title for test");
        cc.create(container);
    }

    /**
     * Test if the right exception is thrown if calling create with an
     * incomplete Container.
     * 
     * @throws Exception
     *             Thrown if no or wrong exception is caught from the framework.
     */
    @Test(expected = XmlSchemaValidationException.class)
    public void testCreateContainer03() throws Exception {
        Container container = new Container();
        ContainerProperties properties = new ContainerProperties();
        container.setProperties(properties);
        cc.create(container);
    }

    /**
     * Test if the right exception is thrown if calling create with an
     * incomplete Container.
     * 
     * @throws Exception
     *             Thrown if no or wrong exception is caught from the framework.
     */
    @Test(expected = XmlSchemaValidationException.class)
    public void testCreateContainer04() throws Exception {
        Container container = new Container();
        ContainerProperties properties = new ContainerProperties();
        container.setProperties(properties);
        cc.create(container);
    }

    /**
     * Test if the right exception is thrown if calling create with an
     * incomplete Container.
     * 
     * @throws Exception
     *             Thrown if no or wrong exception is caught from the framework.
     */
    @Test(expected = XmlSchemaValidationException.class)
    public void testCreateContainer05() throws Exception {
        Container container = new Container();
        ContainerProperties properties = new ContainerProperties();
        properties.setContext(new ContextRef(EscidocClientTestBase
            .getStaticContextId()));
        container.setProperties(properties);
        cc.create(container);
    }

    /**
     * Test if the right exception is thrown if calling create with an
     * incomplete Container.
     * 
     * One invalid MetadataRecord (no name, no content) is part of Container.
     * 
     * @throws Exception
     *             Thrown if no or wrong exception is caught from the framework.
     */
    @Test(expected = XmlSchemaValidationException.class)
    public void testCreateContainer06() throws Exception {
        Container container = new Container();
        ContainerProperties properties = new ContainerProperties();
        properties.setContext(new ContextRef(EscidocClientTestBase
            .getStaticContextId()));
        container.setProperties(properties);
        MetadataRecords mdRecords = new MetadataRecords();
        MetadataRecord mdRecord = new MetadataRecord();
        mdRecords.add(mdRecord);
        container.setMetadataRecords(mdRecords);

        cc.create(container);
    }

    /**
     * Test if the right exception is thrown if calling create with an
     * incomplete Container.
     * 
     * One invalid MetadataRecord (no content) is part of Container.
     * 
     * @throws Exception
     *             Thrown if no or wrong exception is caught from the framework.
     */
    @Test(expected = XmlSchemaValidationException.class)
    public void testCreateContainer07() throws Exception {
        Container container = new Container();
        ContainerProperties properties = new ContainerProperties();
        properties.setContext(new ContextRef(EscidocClientTestBase
            .getStaticContextId()));
        container.setProperties(properties);
        MetadataRecords mdRecords = new MetadataRecords();
        MetadataRecord mdRecord = new MetadataRecord();
        mdRecord.setName("escidoc");
        mdRecords.add(mdRecord);
        container.setMetadataRecords(mdRecords);

        cc.create(container);
    }

    /**
     * Test if the right exception is thrown if calling create with an
     * incomplete Container.
     * <p>
     * At last content model is missing.
     * </p>
     * <p>
     * MetadataRecord has name and content. Relations is at least missing.
     * <p>
     * 
     * @throws Exception
     *             Thrown if no or wrong exception is caught from the framework.
     */
    @Test(expected = InvalidXmlException.class)
    public void testCreateContainer08() throws Exception {
        Container container = new Container();

        // properties
        ContainerProperties properties = new ContainerProperties();
        properties.setContext(new ContextRef(EscidocClientTestBase
            .getStaticContextId()));
        container.setProperties(properties);

        // md-record
        MetadataRecords mdRecords = new MetadataRecords();
        MetadataRecord mdRecord = new MetadataRecord();
        mdRecord.setName("escidoc");

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.newDocument();

        Element element = doc.createElementNS(null, "myMdRecord");

        mdRecord.setContent(element);

        mdRecords.add(mdRecord);
        container.setMetadataRecords(mdRecords);

        cc.create(container);
    }

    /**
     * Test successfully creation of a Container.
     * 
     * @throws Exception
     *             Thrown if no or wrong exception is caught from the framework.
     */
    @Test
    public void testCreateContainer09() throws Exception {
        Container container = new Container();

        // properties
        ContainerProperties properties = new ContainerProperties();
        properties.setContext(new ContextRef(EscidocClientTestBase
            .getStaticContextId()));
        properties.setContentModel(new ContentModelRef(EscidocClientTestBase
            .getStaticContentModelId()));

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
        MetadataRecord mdRecord = new MetadataRecord();
        mdRecord.setName("escidoc");

        Document docMdRecord = builder.newDocument();
        Element element = docMdRecord.createElementNS(null, "myMdRecord");
        mdRecord.setContent(element);

        mdRecords.add(mdRecord);
        container.setMetadataRecords(mdRecords);

        Container createdContainer = cc.create(container);

        // compare the new created Container with the Container from the
        // request
        String objId = createdContainer.getObjid();

        assertNotNull("Object id is null", objId);
        assertEquals(container.getProperties().getContext().getObjid(),
            createdContainer.getProperties().getContext().getObjid());
        assertEquals(container.getProperties().getContentModel().getObjid(),
            createdContainer.getProperties().getContentModel().getObjid());

        Marshaller<MetadataRecord> m =
            MarshallerFactory.getInstance().getMarshaller(MetadataRecord.class);

        String xml1 =
            m.marshalDocument(container.getMetadataRecords().get("escidoc"));

        Document mdRecordBeforeCreate = XmlUtility.getDocument(xml1);
        Node mdRecordBeforeCreateNode =
            XPathAPI.selectSingleNode(mdRecordBeforeCreate, "/md-record");
        Node mdRecordBeforeCreateContent =
            mdRecordBeforeCreateNode.getFirstChild();

        MetadataRecord createdContainerMdRecord =
            createdContainer.getMetadataRecords().get("escidoc");

        String xml2 = m.marshalDocument(createdContainerMdRecord);

        Document mdRecordAfterCreate = XmlUtility.getDocument(xml2);
        Node mdRecordAfterCreateNode =
            XPathAPI.selectSingleNode(mdRecordAfterCreate, "/md-record");
        Node mdRecordAfterCreateContent =
            mdRecordAfterCreateNode.getFirstChild();

        Asserts.assertXmlEquals("Metadata Records differ",
            mdRecordAfterCreateContent, mdRecordBeforeCreateContent);

    }

    /**
     * Test the Container with relations.
     * 
     * Valid minimal Container with relation.
     * 
     * @throws Exception
     *             Thrown if no or wrong exception is caught from the framework.
     */
    @Test
    public void testCreateContainer10() throws Exception {
        Container container = createContainerWithMinContent();
        Relations relations = new Relations();
        Relation relation =
            new Relation(new ItemRef(EscidocClientTestBase.getStaticItemId()));
        relation
            .setPredicate("http://www.escidoc.de/ontologies/mpdl-ontologies/content-relations#isPartOf");
        relations.add(relation);
        container.setRelations(relations);

        Container createdContainer = cc.create(container);

        // test marshalling created Container.
        Marshaller<Container> mContainer =
            MarshallerFactory.getInstance().getMarshaller(Container.class);
        Marshaller<MetadataRecord> mMetadataRecord =
            MarshallerFactory.getInstance().getMarshaller(MetadataRecord.class);

        String xmlc1 = mContainer.marshalDocument(createdContainer);

        // compare the new created Container with the Container from the
        // request
        String objId = createdContainer.getObjid();
        assertNotNull("Object id is null", objId);
        assertEquals(container.getProperties().getContext().getObjid(),
            createdContainer.getProperties().getContext().getObjid());
        assertEquals(container.getProperties().getContentModel().getObjid(),
            createdContainer.getProperties().getContentModel().getObjid());

        // MetadataRecords

        String xml1 =
            mMetadataRecord.marshalDocument(container.getMetadataRecords().get(
                "escidoc"));

        Document mdRecordBeforeCreate = XmlUtility.getDocument(xml1);
        Node mdRecordBeforeCreateNode =
            XPathAPI.selectSingleNode(mdRecordBeforeCreate, "/md-record");
        Node mdRecordBeforeCreateContent =
            mdRecordBeforeCreateNode.getFirstChild();

        MetadataRecord createdContainerMdRecord =
            createdContainer.getMetadataRecords().get("escidoc");

        String xml2 = mMetadataRecord.marshalDocument(createdContainerMdRecord);

        Document mdRecordAfterCreate = XmlUtility.getDocument(xml2);
        Node mdRecordAfterCreateNode =
            XPathAPI.selectSingleNode(mdRecordAfterCreate, "/md-record");
        Node mdRecordAfterCreateContent =
            mdRecordAfterCreateNode.getFirstChild();

        Asserts.assertXmlEquals("Metadata Records differ",
            mdRecordAfterCreateContent, mdRecordBeforeCreateContent);

        // Relations
        Relations masterRelations = container.getRelations();
        Relations compareRelations = createdContainer.getRelations();

        assertEquals("Number of Relations differ", masterRelations.size(),
            compareRelations.size());
        assertEquals("Missing Relations", 1, masterRelations.size());
        Relations retrievedRelations = cc.retrieveRelations(objId);
        assertEquals("Number of Relations differ", retrievedRelations.size(),
            compareRelations.size());
    }

    /**
     * Test the Container with members.
     * 
     * Valid minimal Container with members.
     * 
     * @throws Exception
     *             Thrown if no or wrong exception is caught from the framework.
     */
    @Test
    public void testCreateContainerWithMembers() throws Exception {
        // create test objects
        Container container = createContainerWithMinContent();
        Container memberContainer = createContainerWithMinContent();
        Item memberItem = createItemWithMinContent();
        // get ItemHandlerClient for item member creation
        ItemHandlerClientInterface ihc =
            new ItemHandlerClient(auth.getServiceAddress());
        ihc.setHandle(auth.getHandle());
        // create members
        Container createdMemberContainer = cc.create(memberContainer);
        Item createdMemberItem = ihc.create(memberItem);
        // create struct-map (add container first, then item, which is invalid
        // due to schema definition)
        StructMap structMap = new StructMap();
        structMap
            .add(new ContainerMemberRef(createdMemberContainer.getObjid()));
        structMap.add(new ItemMemberRef(createdMemberItem.getObjid()));
        container.setStructMap(structMap);
        // create parent
        Container createdContainer = cc.create(container);

        // validate resulting struct-map
        StructMap createdStructMap = createdContainer.getStructMap();
        assertEquals("Number of members is wrong", 2, createdStructMap.size());
        Iterator<MemberRef> it = createdStructMap.iterator();

        // item should be returned first
        assertTrue(it.hasNext());
        MemberRef m = it.next();
        assertEquals(m.getResourceType(), ResourceType.Item);
        assertEquals(m.getObjid(), createdMemberItem.getObjid());
        // container should be returned last
        assertTrue(it.hasNext());
        m = it.next();
        assertEquals(m.getResourceType(), ResourceType.Container);
        assertEquals(m.getObjid(), createdMemberContainer.getObjid());
    }

    /**
     * Test if values of requested Container compares to the values of the
     * response.
     * 
     * @throws Exception
     *             Thrown if creation failed or non-volatile Container values
     *             differ.
     */
    @Test
    public void testCreateContainer20() throws Exception {
        Container container = createContainerWithMinContent();
        Container createdContainer = cc.create(container);

        // compare the new created Container with the Container from the
        // request
        String objId = createdContainer.getObjid();
        assertNotNull("Object id is null", objId);
        assertEquals(container.getProperties().getContext().getObjid(),
            createdContainer.getProperties().getContext().getObjid());
        assertEquals(container.getProperties().getContentModel().getObjid(),
            createdContainer.getProperties().getContentModel().getObjid());

        Marshaller<MetadataRecord> m =
            MarshallerFactory.getInstance().getMarshaller(MetadataRecord.class);
        String xml1 =
            m.marshalDocument(container.getMetadataRecords().get("escidoc"));

        Document mdRecordBeforeCreate = XmlUtility.getDocument(xml1);
        Node mdRecordBeforeCreateNode =
            XPathAPI.selectSingleNode(mdRecordBeforeCreate, "/md-record");
        Node mdRecordBeforeCreateContent =
            mdRecordBeforeCreateNode.getFirstChild();

        MetadataRecord createdContainerMdRecord =
            createdContainer.getMetadataRecords().get("escidoc");

        String xml2 = m.marshalDocument(createdContainerMdRecord);

        Document mdRecordAfterCreate = XmlUtility.getDocument(xml2);
        Node mdRecordAfterCreateNode =
            XPathAPI.selectSingleNode(mdRecordAfterCreate, "/md-record");
        Node mdRecordAfterCreateContent =
            mdRecordAfterCreateNode.getFirstChild();

        Asserts.assertXmlEquals("Metadata Records differ",
            mdRecordAfterCreateContent, mdRecordBeforeCreateContent);
    }

    /**
     * Test dealing with multiple MetadataRecords of Container.
     * 
     * @throws Exception
     *             Thrown if metadata records handling failed.
     */
    @Test
    public void testMultipleMetadataRecords01() throws Exception {
        Container container = createContainerWithMinContent();
        Container createdContainer = cc.create(container);

        // compare the new created Container with the Container from the
        // request
        String objId = createdContainer.getObjid();
        assertNotNull("Object id is null", objId);
        assertEquals(container.getProperties().getContext().getObjid(),
            createdContainer.getProperties().getContext().getObjid());
        assertEquals(container.getProperties().getContentModel().getObjid(),
            createdContainer.getProperties().getContentModel().getObjid());

        Asserts.assertMdRecords(container.getMetadataRecords(),
            createdContainer.getMetadataRecords());

        // now delete some metadataRecords
        createdContainer.getMetadataRecords().del("md-record2");
        Container updatedContainer1 = cc.update(createdContainer);

        assertNotNull("Object id is null", objId);
        assertEquals(container.getProperties().getContext().getObjid(),
            createdContainer.getProperties().getContext().getObjid());
        assertEquals(container.getProperties().getContentModel().getObjid(),
            createdContainer.getProperties().getContentModel().getObjid());

        Asserts.assertMdRecords(createdContainer.getMetadataRecords(),
            updatedContainer1.getMetadataRecords());

    }

    // /**
    // * Compares all MetadataRecords with the master. If number, attributes or
    // * content of the MetadataRecords differ.
    // *
    // * @param master
    // * The master MetadataRecords.
    // * @param toCompare
    // * The to compare MetadataRecords.
    // * @throws Exception
    // * Thrown if both MetadataRecords not differ.
    // */
    // private void assertMdRecords(
    // final MetadataRecords master, final MetadataRecords toCompare)
    // throws Exception {
    //
    // assertEquals("Number of MetadataRecord(s) differs", master
    // .getMetadataRecords().size(), toCompare.getMetadataRecords().size());
    //
    // Iterator<MetadataRecord> it = master.getMetadataRecords().iterator();
    //
    // while (it.hasNext()) {
    //
    // MetadataRecord mdMaster = it.next();
    // String masterName = mdMaster.getName();
    // String masterMdType = mdMaster.getMdType();
    // String masterSchema = mdMaster.getSchema();
    // String masterContent = mdMaster.getContent();
    //
    // MetadataRecord mdToComp = toCompare.get(masterName);
    // assertNotNull("MetadataRecord with name '" + masterName
    // + "' is missing. ", mdToComp);
    //
    // String toCompareMdType = mdToComp.getMdType();
    // assertEquals("MetadataRecord types not equal.", masterMdType,
    // toCompareMdType);
    //
    // String toCompareSchema = mdToComp.getSchema();
    // assertEquals("MetadataRecord Schemas not equal.", masterSchema,
    // toCompareSchema);
    //
    // String toCompareContent = mdToComp.getContent();
    //
    // Document masterConDoc = XmlUtility.getDocument(masterContent);
    // Document toCompareDoc = XmlUtility.getDocument(toCompareContent);
    // assertXmlEquals("Content of MetadataRecords differ", masterConDoc,
    // toCompareDoc);
    // }
    // }

    private Container createContainerWithMinContent()
        throws ParserConfigurationException, TransportException,
        EscidocException, InternalClientException {

        Container container = new Container();
        ContainerProperties properties = new ContainerProperties();
        properties.setContext(new ContextRef(EscidocClientTestBase
            .getStaticContextId()));
        properties.setContentModel(new ContentModelRef(EscidocClientTestBase
            .getStaticContentModelId()));

        container.setProperties(properties);
        MetadataRecords mdRecords = new MetadataRecords();

        MetadataRecord mdRecord = new MetadataRecord();
        mdRecord.setName("escidoc");
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.newDocument();
        Element element = doc.createElementNS(null, "myMdRecord");

        mdRecord.setContent(element);
        mdRecords.add(mdRecord);
        MetadataRecord mdRecord2 = new MetadataRecord();
        mdRecord2.setName("md-record2");
        Element element2 = doc.createElementNS(null, "myMdRecord");
        element2.setTextContent("222222222");

        mdRecord2.setContent(element2);

        mdRecords.add(mdRecord2);

        MetadataRecord mdRecord3 = new MetadataRecord();
        mdRecord3.setName("md-record3");
        Element element3 = doc.createElementNS(null, "myMdRecord");
        Element element4 = doc.createElementNS(null, "some-other-stuff");
        element2.setTextContent("33333333333333333333");
        element2.appendChild(element4);

        mdRecord3.setContent(element3);
        mdRecords.add(mdRecord3);

        container.setMetadataRecords(mdRecords);
        return container;
    }

    /**
     * @return
     * @throws TransportException
     * @throws EscidocException
     * @throws InternalClientException
     * @throws ParserConfigurationException
     */
    private final Item createItemWithMinContent() throws TransportException,
        EscidocException, InternalClientException, ParserConfigurationException {
        Item item = new Item();

        item.getProperties().setContext(
            new ContextRef(EscidocClientTestBase.getStaticContextId()));
        item.getProperties()
            .setContentModel(
                new ContentModelRef(EscidocClientTestBase
                    .getStaticContentModelId()));
        item.setXLinkTitle("TEST");

        // Content-model
        ContentModelSpecific cms = ResourceUtility.getContentModelSpecific();
        item.getProperties().setContentModelSpecific(cms);

        // Metadata Record(s)
        MetadataRecords mdRecords = new MetadataRecords();
        MetadataRecord mdrecord = ResourceUtility.getMdRecord("escidoc");
        mdRecords.add(mdrecord);
        item.setMetadataRecords(mdRecords);

        return item;
    }
}