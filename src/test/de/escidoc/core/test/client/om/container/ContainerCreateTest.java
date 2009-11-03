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
package de.escidoc.core.test.client.om.container;

import java.util.Collection;
import java.util.Iterator;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import com.sun.jimi.core.component.AbstractRenderer.ResizeWatcher;

import de.escidoc.core.client.ContainerHandlerClient;
import de.escidoc.core.client.exceptions.application.invalid.InvalidXmlException;
import de.escidoc.core.client.exceptions.application.invalid.XmlSchemaValidationException;
import de.escidoc.core.common.XmlUtility;
import de.escidoc.core.common.jibx.Marshaller;
import de.escidoc.core.resources.ResourceRef;
import de.escidoc.core.resources.common.MetadataRecord;
import de.escidoc.core.resources.common.MetadataRecords;
import de.escidoc.core.resources.common.Relation;
import de.escidoc.core.resources.common.Relations;
import de.escidoc.core.resources.common.properties.Properties;
import de.escidoc.core.resources.common.structmap.ContainerRef;
import de.escidoc.core.resources.common.structmap.StructMap;
import de.escidoc.core.resources.om.container.Container;
import de.escidoc.core.test.client.EscidocClientTestBase;

/**
 * Test create Container.
 * 
 * @author SWA
 * 
 */
public class ContainerCreateTest extends EscidocClientTestBase {

    private final Logger logger =
        Logger.getLogger(ContainerCreateTest.class.getName());

    /**
     * Set up the tests.
     * 
     * @throws Exception
     *             If anything fails.
     */
    @Override
    protected void setUp() throws Exception {

        super.setUp();

    }

    /**
     * Clean up after tests.
     * 
     * @throws Exception
     *             If anything fails.
     */
    @Override
    protected void tearDown() throws Exception {

        super.tearDown();
    }

    /**
     * Test if the right exception is thrown if calling create with an
     * incomplete Container.
     * 
     * @throws Exception
     *             Thrown if no or wrong exception is caught from the framework.
     */
    @Test
    public void testCreateContainer01() throws Exception {

        ContainerHandlerClient cc = new ContainerHandlerClient();
        cc.setHandle(EscidocClientTestBase.DEFAULT_HANDLE);

        Container container = new Container();
        try {
            cc.create(container);
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
     * incomplete Container.
     * 
     * A title is set (but shouldn't have influence).
     * 
     * @throws Exception
     *             Thrown if no or wrong exception is caught from the framework.
     */
    @Test
    public void testCreateContainer02() throws Exception {

        ContainerHandlerClient cc = new ContainerHandlerClient();
        cc.setHandle(EscidocClientTestBase.DEFAULT_HANDLE);

        Container container = new Container();
        container.setTitle("New title for test");
        try {
            cc.create(container);
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
     * incomplete Container.
     * 
     * @throws Exception
     *             Thrown if no or wrong exception is caught from the framework.
     */
    @Test
    public void testCreateContainer03() throws Exception {

        ContainerHandlerClient cc = new ContainerHandlerClient();
        cc.setHandle(EscidocClientTestBase.DEFAULT_HANDLE);

        Container container = new Container();
        Properties properties = new Properties();
        container.setProperties(properties);
        try {
            cc.create(container);
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
     * incomplete Container.
     * 
     * @throws Exception
     *             Thrown if no or wrong exception is caught from the framework.
     */
    @Test
    public void testCreateContainer04() throws Exception {

        ContainerHandlerClient cc = new ContainerHandlerClient();
        cc.setHandle(EscidocClientTestBase.DEFAULT_HANDLE);

        Container container = new Container();
        Properties properties = new Properties();
        container.setProperties(properties);
        try {
            cc.create(container);
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
     * incomplete Container.
     * 
     * @throws Exception
     *             Thrown if no or wrong exception is caught from the framework.
     */
    @Test
    public void testCreateContainer05() throws Exception {

        ContainerHandlerClient cc = new ContainerHandlerClient();
        cc.setHandle(EscidocClientTestBase.DEFAULT_HANDLE);

        Container container = new Container();
        Properties properties = new Properties();
        properties.setContext(new ResourceRef(EXAMPLE_CONTEXT_ID));
        container.setProperties(properties);
        try {
            cc.create(container);
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
     * incomplete Container.
     * 
     * One invalid MetadataRecord (no name, no content) is part of Container.
     * 
     * @throws Exception
     *             Thrown if no or wrong exception is caught from the framework.
     */
    @Test
    public void testCreateContainer06() throws Exception {

        ContainerHandlerClient cc = new ContainerHandlerClient();
        cc.setHandle(EscidocClientTestBase.DEFAULT_HANDLE);

        Container container = new Container();
        Properties properties = new Properties();
        properties.setContext(new ResourceRef(EXAMPLE_CONTEXT_ID));
        container.setProperties(properties);
        MetadataRecords mdRecords = new MetadataRecords();
        MetadataRecord mdRecord = new MetadataRecord();
        mdRecords.add(mdRecord);
        container.setMetadataRecords(mdRecords);

        try {
            cc.create(container);
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
     * incomplete Container.
     * 
     * One invalid MetadataRecord (no content) is part of Container.
     * 
     * @throws Exception
     *             Thrown if no or wrong exception is caught from the framework.
     */
    @Test
    public void testCreateContainer07() throws Exception {

        ContainerHandlerClient cc = new ContainerHandlerClient();
        cc.setHandle(EscidocClientTestBase.DEFAULT_HANDLE);

        Container container = new Container();
        Properties properties = new Properties();
        properties.setContext(new ResourceRef(EXAMPLE_CONTEXT_ID));
        container.setProperties(properties);
        MetadataRecords mdRecords = new MetadataRecords();
        MetadataRecord mdRecord = new MetadataRecord();
        mdRecord.setName("escidoc");
        mdRecords.add(mdRecord);
        container.setMetadataRecords(mdRecords);

        try {
            cc.create(container);
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
     * incomplete Container.
     * 
     * MetadataRecord has name and content. Relations is at least missing.
     * 
     * @throws Exception
     *             Thrown if no or wrong exception is caught from the framework.
     */
    @Test
    public void testCreateContainer08() throws Exception {

        ContainerHandlerClient cc = new ContainerHandlerClient();
        cc.setHandle(EscidocClientTestBase.DEFAULT_HANDLE);

        Container container = new Container();
        Properties properties = new Properties();
        properties.setContext(new ResourceRef(EXAMPLE_CONTEXT_ID));
        container.setProperties(properties);
        MetadataRecords mdRecords = new MetadataRecords();
        MetadataRecord mdRecord = new MetadataRecord();
        mdRecord.setName("escidoc");
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.newDocument();
        Element element = doc.createElementNS(
            null,
            "myMdRecord");
        

        mdRecord.setContent(element);

        mdRecords.add(mdRecord);
        container.setMetadataRecords(mdRecords);

        try {
            cc.create(container);
            fail("Missing Exception");
        }
        catch (Exception e) {
            Class<?> ec = InvalidXmlException.class;
            EscidocClientTestBase.assertExceptionType(ec.getName()
                + " expected.", ec, e);
        }
    }

    /**
     * Test if the right exception is thrown if calling create with an
     * incomplete Container.
     * 
     * MetadataRecord has name and content, ContentModel is added.
     * 
     * @throws Exception
     *             Thrown if no or wrong exception is caught from the framework.
     */
    @Test
    public void testCreateContainer09() throws Exception {

        ContainerHandlerClient cc = new ContainerHandlerClient();
        cc.setHandle(EscidocClientTestBase.DEFAULT_HANDLE);

        Container container = new Container();
        Properties properties = new Properties();
        properties.setContext(new ResourceRef(EXAMPLE_CONTEXT_ID));
        properties.setContentModel(new ResourceRef(EXAMPLE_CONTENT_MODEL_ID));
        container.setProperties(properties);
        MetadataRecords mdRecords = new MetadataRecords();
        MetadataRecord mdRecord = new MetadataRecord();
        mdRecord.setName("escidoc");
        
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.newDocument();
        Element element = doc.createElementNS(
            null,
            "myMdRecord");
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

        Marshaller<MetadataRecord> m1 =
            new Marshaller<MetadataRecord>(container.getMetadataRecords().get(
                "escidoc").getClass());
        String xml1 =
            m1.marshalDocument(container.getMetadataRecords().get("escidoc"));

        Document mdRecordBeforeCreate = XmlUtility.getDocument(xml1);
        Node mdRecordBeforeCreateNode =
            selectSingleNode(mdRecordBeforeCreate, "/md-record");
        org.w3c.dom.Element mdRecordBeforeCreateContent =
            (org.w3c.dom.Element) mdRecordBeforeCreateNode.getFirstChild();

        MetadataRecord createdContainerMdRecord =
            createdContainer.getMetadataRecords().get("escidoc");

        Marshaller<MetadataRecord> m2 =
            new Marshaller<MetadataRecord>(createdContainerMdRecord.getClass());
        String xml2 = m2.marshalDocument(createdContainerMdRecord);

        Document mdRecordAfterCreate = XmlUtility.getDocument(xml2);
        Node mdRecordAfterCreateNode =
            selectSingleNode(mdRecordAfterCreate, "/md-record");
        org.w3c.dom.Element mdRecordAfterCreateContent =
            (org.w3c.dom.Element) mdRecordAfterCreateNode.getFirstChild();

        assertXmlEquals("Metadata Records differ",
            (Node) mdRecordAfterCreateContent,
            (Node) mdRecordBeforeCreateContent);

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

        ContainerHandlerClient cc = new ContainerHandlerClient();
        cc.setHandle(EscidocClientTestBase.DEFAULT_HANDLE);

        Container container = createContainerWithMinContent();
        Relations relations = new Relations();
        Relation relation = new Relation(new ResourceRef(EXAMPLE_ITEM_ID));
        relation
            .setPredicate("http://www.escidoc.de/ontologies/mpdl-ontologies/content-relations#isPartOf");
        relations.add(relation);
        container.setRelations(relations);

        Container createdContainer = cc.create(container);
        Marshaller<Container> c1 =
            new Marshaller<Container>(createdContainer.getClass());
        String xmlc1 = c1.marshalDocument(createdContainer);
        System.out.println("cretaed container " + xmlc1);
        // compare the new created Container with the Container from the
        // request
        String objId = createdContainer.getObjid();
        assertNotNull("Object id is null", objId);
        assertEquals(container.getProperties().getContext().getObjid(),
            createdContainer.getProperties().getContext().getObjid());
        assertEquals(container.getProperties().getContentModel().getObjid(),
            createdContainer.getProperties().getContentModel().getObjid());

        // MetadataRecords

        Marshaller<MetadataRecord> m1 =
            new Marshaller<MetadataRecord>(container.getMetadataRecords().get(
                "escidoc").getClass());
        String xml1 =
            m1.marshalDocument(container.getMetadataRecords().get("escidoc"));

        Document mdRecordBeforeCreate = XmlUtility.getDocument(xml1);
        Node mdRecordBeforeCreateNode =
            selectSingleNode(mdRecordBeforeCreate, "/md-record");
        org.w3c.dom.Element mdRecordBeforeCreateContent =
            (org.w3c.dom.Element) mdRecordBeforeCreateNode.getFirstChild();

        MetadataRecord createdContainerMdRecord =
            createdContainer.getMetadataRecords().get("escidoc");

        Marshaller<MetadataRecord> m2 =
            new Marshaller<MetadataRecord>(createdContainerMdRecord.getClass());
        String xml2 = m2.marshalDocument(createdContainerMdRecord);

        Document mdRecordAfterCreate = XmlUtility.getDocument(xml2);
        Node mdRecordAfterCreateNode =
            selectSingleNode(mdRecordAfterCreate, "/md-record");
        org.w3c.dom.Element mdRecordAfterCreateContent =
            (org.w3c.dom.Element) mdRecordAfterCreateNode.getFirstChild();

        assertXmlEquals("Metadata Records differ",
            (Node) mdRecordAfterCreateContent,
            (Node) mdRecordBeforeCreateContent);

        // Relations
        Relations masterRelations = container.getRelations();
        Relations compareRelations = createdContainer.getRelations();

        assertEquals("Number of Relations differ",
            masterRelations.get().size(), compareRelations.get().size());
        assertEquals("Missing Relations", 1, masterRelations.get().size());
        Relations retrievedRelations = cc.retrieveRelations(objId);
        assertEquals("Number of Relations differ",
            retrievedRelations.get().size(), compareRelations.get().size());
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
    public void testCreateContainerWithOneMember() throws Exception {

        ContainerHandlerClient cc = new ContainerHandlerClient();
        cc.setHandle(EscidocClientTestBase.DEFAULT_HANDLE);

        Container container = createContainerWithMinContent();
        Container memberContainer = createContainerWithMinContent();
        
        Container createdMemberContainer = cc.create(memberContainer);
        String memberId = createdMemberContainer.getObjid();
        StructMap structMap = new StructMap();
        ContainerRef member = new ContainerRef();
        member.setObjid(memberId);
        structMap.addMember(member);
        container.setStructMap(structMap);
        Container createdContainer = cc.create(container);
        Marshaller<Container> c1 =
            new Marshaller<Container>(createdContainer.getClass());
        String xmlc1 = c1.marshalDocument(createdContainer);
        System.out.println("created container " + xmlc1);
        StructMap createdStructMap = createdContainer.getStructMap();
        Collection<ResourceRef> members = createdStructMap.getMembers();
        assertEquals("Number of members is wrong", 1, members.size());
        Iterator<ResourceRef> iterator = members.iterator();
        while(iterator.hasNext()) {
            ResourceRef memberResource = iterator.next();
            assertEquals("member is the wrong resource type", 
                memberResource.getClass(), member.getClass());
            
            assertEquals("member has a wrong id", 
                memberResource.getObjid(), memberId);
            
        }
        
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

        ContainerHandlerClient cc = new ContainerHandlerClient();
        cc.setHandle(EscidocClientTestBase.DEFAULT_HANDLE);

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

        Marshaller<MetadataRecord> m1 =
            new Marshaller<MetadataRecord>(container.getMetadataRecords().get(
                "escidoc").getClass());
        String xml1 =
            m1.marshalDocument(container.getMetadataRecords().get("escidoc"));

        Document mdRecordBeforeCreate = XmlUtility.getDocument(xml1);
        Node mdRecordBeforeCreateNode =
            selectSingleNode(mdRecordBeforeCreate, "/md-record");
        org.w3c.dom.Element mdRecordBeforeCreateContent =
            (org.w3c.dom.Element) mdRecordBeforeCreateNode.getFirstChild();

        MetadataRecord createdContainerMdRecord =
            createdContainer.getMetadataRecords().get("escidoc");

        Marshaller<MetadataRecord> m2 =
            new Marshaller<MetadataRecord>(createdContainerMdRecord.getClass());
        String xml2 = m2.marshalDocument(createdContainerMdRecord);

        Document mdRecordAfterCreate = XmlUtility.getDocument(xml2);
        Node mdRecordAfterCreateNode =
            selectSingleNode(mdRecordAfterCreate, "/md-record");
        org.w3c.dom.Element mdRecordAfterCreateContent =
            (org.w3c.dom.Element) mdRecordAfterCreateNode.getFirstChild();

        assertXmlEquals("Metadata Records differ",
            (Node) mdRecordAfterCreateContent,
            (Node) mdRecordBeforeCreateContent);
    }

    /**
     * Test dealing with multiple MetadataRecords of Container.
     * 
     * @throws Exception
     *             Thrown if metadata records handling failed.
     */
    @Test
    public void testMultipleMetadataRecords01() throws Exception {

        ContainerHandlerClient cc = new ContainerHandlerClient();
        cc.setHandle(EscidocClientTestBase.DEFAULT_HANDLE);

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

        assertMdRecords(container.getMetadataRecords(), createdContainer
            .getMetadataRecords());

        // now delete some metadataRecords
        createdContainer.getMetadataRecords().del("md-record2");
        Container updatedContainer1 = cc.update(createdContainer);

        assertNotNull("Object id is null", objId);
        assertEquals(container.getProperties().getContext().getObjid(),
            createdContainer.getProperties().getContext().getObjid());
        assertEquals(container.getProperties().getContentModel().getObjid(),
            createdContainer.getProperties().getContentModel().getObjid());

        assertMdRecords(createdContainer.getMetadataRecords(),
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
    
    Container createContainerWithMinContent() throws ParserConfigurationException {
        Container container = new Container();
        Properties properties = new Properties();
        properties.setContext(new ResourceRef(EXAMPLE_CONTEXT_ID));
        properties.setContentModel(new ResourceRef(EXAMPLE_CONTENT_MODEL_ID));

        container.setProperties(properties);
        MetadataRecords mdRecords = new MetadataRecords();

        MetadataRecord mdRecord = new MetadataRecord();
        mdRecord.setName("escidoc");
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.newDocument();
        Element element = doc.createElementNS(
            null,
            "myMdRecord");

        mdRecord.setContent(element);
        mdRecords.add(mdRecord);
        MetadataRecord mdRecord2 = new MetadataRecord();
        mdRecord2.setName("md-record2");
        Document doc2 = builder.newDocument();
        Element element2 = doc.createElementNS(
            null, "myMdRecord");
        element2.setTextContent("222222222");
        
        mdRecord2.setContent(element2);

        mdRecords.add(mdRecord2);

        MetadataRecord mdRecord3 = new MetadataRecord();
        mdRecord3.setName("md-record3");
        Document doc3 = builder.newDocument();
        Element element3 = doc.createElementNS(
            null, "myMdRecord");
        Document doc4 = builder.newDocument();
        Element element4= doc.createElementNS(
            null, "some-other-stuff");
        element2.setTextContent("33333333333333333333");
        element2.appendChild(element4);
        
        mdRecord3.setContent(element3);
        mdRecords.add(mdRecord3);
        
        container.setMetadataRecords(mdRecords);
        return container;

    }
}
