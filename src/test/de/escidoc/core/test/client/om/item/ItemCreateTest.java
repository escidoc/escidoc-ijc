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
package de.escidoc.core.test.client.om.item;

import java.util.LinkedList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import de.escidoc.core.client.ItemHandlerClient;
import de.escidoc.core.client.exceptions.application.invalid.InvalidXmlException;
import de.escidoc.core.client.exceptions.application.invalid.XmlSchemaValidationException;
import de.escidoc.core.common.XmlUtility;
import de.escidoc.core.common.jibx.Factory;
import de.escidoc.core.common.jibx.Marshaller;
import de.escidoc.core.resources.ResourceRef;
import de.escidoc.core.resources.common.MetadataRecord;
import de.escidoc.core.resources.common.MetadataRecords;
import de.escidoc.core.resources.common.Relation;
import de.escidoc.core.resources.common.Relations;
import de.escidoc.core.resources.common.properties.ContentModelSpecific;
import de.escidoc.core.resources.om.item.Item;
import de.escidoc.core.resources.om.item.ItemProperties;
import de.escidoc.core.resources.om.item.component.Component;
import de.escidoc.core.resources.om.item.component.ComponentContent;
import de.escidoc.core.resources.om.item.component.ComponentProperties;
import de.escidoc.core.resources.om.item.component.Components;
import de.escidoc.core.test.client.EscidocClientTestBase;

/**
 * Test create Item.
 * 
 * @author SWA
 * 
 */
public class ItemCreateTest extends EscidocClientTestBase {

    private final Logger logger =
        Logger.getLogger(ItemCreateTest.class.getName());

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
     * incomplete Item.
     * 
     * @throws Exception
     *             Thrown if no or wrong exception is caught from the framework.
     */
    @Test
    public void testCreateItem01() throws Exception {

        ItemHandlerClient cc = new ItemHandlerClient();
        cc.setHandle(EscidocClientTestBase.DEFAULT_HANDLE);

        Item item = new Item();
        try {
            cc.create(item);
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
     * incomplete Item.
     * 
     * A title is set (but shouldn't have influence).
     * 
     * @throws Exception
     *             Thrown if no or wrong exception is caught from the framework.
     */
    @Test
    public void testCreateItem02() throws Exception {

        ItemHandlerClient cc = new ItemHandlerClient();
        cc.setHandle(EscidocClientTestBase.DEFAULT_HANDLE);

        Item item = new Item();
        item.setTitle("New title for test");
        try {
            cc.create(item);
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
     * incomplete Item.
     * 
     * @throws Exception
     *             Thrown if no or wrong exception is caught from the framework.
     */
    @Test
    public void testCreateItem03() throws Exception {

        ItemHandlerClient cc = new ItemHandlerClient();
        cc.setHandle(EscidocClientTestBase.DEFAULT_HANDLE);

        Item item = new Item();
        ItemProperties properties = new ItemProperties();
        item.setProperties(properties);
        try {
            cc.create(item);
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
     * incomplete Item.
     * 
     * @throws Exception
     *             Thrown if no or wrong exception is caught from the framework.
     */
    @Test
    public void testCreateItem04() throws Exception {

        ItemHandlerClient cc = new ItemHandlerClient();
        cc.setHandle(EscidocClientTestBase.DEFAULT_HANDLE);

        Item item = new Item();
        ItemProperties properties = new ItemProperties();
        item.setProperties(properties);
        try {
            cc.create(item);
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
     * incomplete Item.
     * 
     * @throws Exception
     *             Thrown if no or wrong exception is caught from the framework.
     */
    @Test
    public void testCreateItem05() throws Exception {

        ItemHandlerClient cc = new ItemHandlerClient();
        cc.setHandle(EscidocClientTestBase.DEFAULT_HANDLE);

        Item item = new Item();
        ItemProperties properties = new ItemProperties();
        properties.setContext(new ResourceRef(EXAMPLE_CONTEXT_ID));
        item.setProperties(properties);
        try {
            cc.create(item);
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
     * incomplete Item.
     * 
     * One invalid MetadataRecord (no name, no content) is part of Item.
     * 
     * @throws Exception
     *             Thrown if no or wrong exception is caught from the framework.
     */
    @Test
    public void testCreateItem06() throws Exception {

        ItemHandlerClient cc = new ItemHandlerClient();
        cc.setHandle(EscidocClientTestBase.DEFAULT_HANDLE);

        Item item = new Item();
        ItemProperties properties = new ItemProperties();
        properties.setContext(new ResourceRef(EXAMPLE_CONTEXT_ID));
        item.setProperties(properties);
        MetadataRecords mdRecords = new MetadataRecords();
        MetadataRecord mdRecord = new MetadataRecord();
        mdRecords.add(mdRecord);
        item.setMetadataRecords(mdRecords);

        try {
            cc.create(item);
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
     * incomplete Item.
     * 
     * One invalid MetadataRecord (no content) is part of Item.
     * 
     * @throws Exception
     *             Thrown if no or wrong exception is caught from the framework.
     */
    @Test
    public void testCreateItem07() throws Exception {

        ItemHandlerClient cc = new ItemHandlerClient();
        cc.setHandle(EscidocClientTestBase.DEFAULT_HANDLE);

        Item item = new Item();
        ItemProperties properties = new ItemProperties();
        properties.setContext(new ResourceRef(EXAMPLE_CONTEXT_ID));
        item.setProperties(properties);
        MetadataRecords mdRecords = new MetadataRecords();
        MetadataRecord mdRecord = new MetadataRecord();
        mdRecord.setName("escidoc");
        mdRecords.add(mdRecord);
        item.setMetadataRecords(mdRecords);

        try {
            cc.create(item);
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
     * incomplete Item.
     * 
     * MetadataRecord has name and content. ContentModel is at least missing.
     * 
     * @throws Exception
     *             Thrown if no or wrong exception is caught from the framework.
     */
    @Test
    public void testCreateItem08() throws Exception {

        ItemHandlerClient cc = new ItemHandlerClient();
        cc.setHandle(EscidocClientTestBase.DEFAULT_HANDLE);

        Item item = new Item();
        ItemProperties properties = new ItemProperties();
        properties.setContext(new ResourceRef(EXAMPLE_CONTEXT_ID));
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.newDocument();
        Element contentModelSpecific = doc.createElementNS(
            null,
            "cms");
        Element element1= doc.createElement("some-other-stuff1");
        element1.setTextContent("33333333333333333333");
        
        
        
        List<Element> cmsContent = new LinkedList<Element>();
        cmsContent.add(contentModelSpecific);
        cmsContent.add(element1);
        ContentModelSpecific cms = new ContentModelSpecific();
        
        cms.setContent(cmsContent);
        properties.setContentModelSpecific(cms);
        item.setProperties(properties);
        MetadataRecords mdRecords = new MetadataRecords();
        MetadataRecord mdRecord = new MetadataRecord();
        mdRecord.setName("escidoc");
        Document doc1 = builder.newDocument();
        Element element = doc1.createElementNS(
            null,
            "myMdRecord");
        

        mdRecord.setContent(element);

        mdRecords.add(mdRecord);
        item.setMetadataRecords(mdRecords);

        try {
            cc.create(item);
            fail("Missing Exception");
        }
        catch (Exception e) {
            Class<?> ec = InvalidXmlException.class;
            EscidocClientTestBase.assertExceptionType(ec.getName()
                + " expected.", ec, e);
        }
    }

    /**
     * Test if values of requested Item compares to the values of the response.
     * 
     * @throws Exception
     *             Thrown if creation failed or non-volatile Item values differ.
     */
   @Test
    public void testCreateItem09() throws Exception {

        ItemHandlerClient cc = new ItemHandlerClient();
        //cc.setServiceAddress("http://escidev6:8080");
        cc.setHandle(EscidocClientTestBase.DEFAULT_HANDLE);

        Item item = new Item();
        ItemProperties properties = new ItemProperties();
        properties.setContext(new ResourceRef(EXAMPLE_CONTEXT_ID));
        properties.setContentModel(new ResourceRef(EXAMPLE_CONTENT_MODEL_ID));
        //Content-model-specific
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.newDocument();
        Element contentModelSpecific = doc.createElementNS(
            null,
            "cms");
        Element element1= doc.createElement("some-other-stuff1");
        element1.setTextContent("33333333333333333333");
        
        
        
        List<Element> cmsContent = new LinkedList<Element>();
        cmsContent.add(contentModelSpecific);
        cmsContent.add(element1);
        ContentModelSpecific cms = new ContentModelSpecific();
        
        cms.setContent(cmsContent);
        
        properties.setContentModelSpecific(cms);
        
        item.setProperties(properties);
        MetadataRecords mdRecords = new MetadataRecords();
        MetadataRecord mdRecord = new MetadataRecord();
        mdRecord.setName("escidoc");
        
        Document doc1 = builder.newDocument();
        Element element = doc.createElementNS(
            null, "myMdRecord");
        mdRecord.setContent(element);

        mdRecords.add(mdRecord);
        item.setMetadataRecords(mdRecords);
        
        Relations relations = new Relations();
        Relation relation = new Relation(new ResourceRef(EXAMPLE_ITEM_ID));
        relation
            .setPredicate("http://www.escidoc.de/ontologies/mpdl-ontologies/content-relations#isPartOf");
        relations.add(relation);
        item.setRelations(relations);
        Marshaller<Item> m = new Marshaller<Item>(item.getClass());
        String xml = m.marshalDocument(item);
        System.out.println(xml);

        Item uitem = m.unmarshalDocument(xml);
        String itemXml = Factory.getItemMarshaller().marshalDocument(uitem);
        System.out.println("item " + itemXml);
        Item createdItem = cc.create(item);
        
        // compare the new created Item with the Item from the request
        String objId = createdItem.getObjid();
        
        assertNotNull("Object id is null", objId);
        assertEquals(item.getProperties().getContext().getObjid(), createdItem
            .getProperties().getContext().getObjid());
        assertEquals(item.getProperties().getContentModel().getObjid(),
            createdItem.getProperties().getContentModel().getObjid());

        Marshaller<MetadataRecord> m1 =
            new Marshaller<MetadataRecord>(item.getMetadataRecords().get(
                "escidoc").getClass());
        String xml1 =
            m1.marshalDocument(item.getMetadataRecords().get("escidoc"));

        // Marshaller<Item> m1 =
        // new Marshaller<Item>(item.getClass());
        // String xml1 =
        // m1.marshalDocument(item);
        System.out.println("md-record " + xml1);

        Document mdRecordBeforeCreate = XmlUtility.getDocument(xml1);
        Node mdRecordBeforeCreateNode =
            selectSingleNode(mdRecordBeforeCreate, "/md-record");
        org.w3c.dom.Element mdRecordBeforeCreateContent =
            (org.w3c.dom.Element) mdRecordBeforeCreateNode.getFirstChild();

        MetadataRecord createdContainerMdRecord =
            createdItem.getMetadataRecords().get("escidoc");

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
        Relations masterRelations = item.getRelations();
        Relations compareRelations = createdItem.getRelations();

        assertEquals("Number of Relations differ",
            masterRelations.get().size(), compareRelations.get().size());
        assertEquals("Missing Relations", 1, masterRelations.get().size());
        Relations retrievedRelations = cc.retrieveRelations(objId);
        assertEquals("Number of Relations differ",
            retrievedRelations.get().size(), compareRelations.get().size());
    }

    /**
     * Test dealing with multiple MetadataRecords of Item.
     * 
     * @throws Exception
     *             Thrown if metadata records handling failed.
     */
    @Test
    public void testMultipleMetadataRecords01() throws Exception {

        ItemHandlerClient cc = new ItemHandlerClient();
        cc.setHandle(EscidocClientTestBase.DEFAULT_HANDLE);

        Item item = new Item();
        ItemProperties properties = new ItemProperties();
        properties.setContext(new ResourceRef(EXAMPLE_CONTEXT_ID));
        properties.setContentModel(new ResourceRef(EXAMPLE_CONTENT_MODEL_ID));
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.newDocument();
        Element contentModelSpecific = doc.createElementNS(
            null,
            "cms");
        Element element1= doc.createElement("some-other-stuff1");
        element1.setTextContent("33333333333333333333");
        
        
        
        List<Element> cmsContent = new LinkedList<Element>();
        cmsContent.add(contentModelSpecific);
        cmsContent.add(element1);
        ContentModelSpecific cms = new ContentModelSpecific();
        
        cms.setContent(cmsContent);
        
        properties.setContentModelSpecific(cms);
        item.setProperties(properties);
        MetadataRecords mdRecords = new MetadataRecords();
        MetadataRecord mdRecord = new MetadataRecord();
        mdRecord.setName("escidoc");
        
        Document doc1 = builder.newDocument();
        Element element = doc.createElementNS(
            null, "myMdRecord");
        

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
        element4.setTextContent("33333333333333333333");
        element3.appendChild(element4);
        
        mdRecord3.setContent(element3);
        mdRecords.add(mdRecord3);

        item.setMetadataRecords(mdRecords);

        Item createdItem = cc.create(item);

        // compare the new created Item with the Item from the request
        String objId = createdItem.getObjid();
        assertNotNull("Object id is null", objId);
        assertEquals(item.getProperties().getContext().getObjid(), createdItem
            .getProperties().getContext().getObjid());
        assertEquals(item.getProperties().getContentModel().getObjid(),
            createdItem.getProperties().getContentModel().getObjid());

        assertMdRecords(item.getMetadataRecords(), createdItem
            .getMetadataRecords());

        // now delete some metadataRecords
        createdItem.getMetadataRecords().del("md-record2");
        Item updatedItem1 = cc.update(createdItem);

        assertNotNull("Object id is null", objId);
        assertEquals(item.getProperties().getContext().getObjid(), createdItem
            .getProperties().getContext().getObjid());
        assertEquals(item.getProperties().getContentModel().getObjid(),
            createdItem.getProperties().getContentModel().getObjid());

        assertMdRecords(createdItem.getMetadataRecords(), updatedItem1
            .getMetadataRecords());

    }
    /**
     * Test if the right exception is thrown if calling create Item with an
     * incomplete Component (content section is missing).
     * 
     * @throws Exception
     *             Thrown if creation failed or non-volatile Item values differ.
     */
   @Test
    public void testCreateItemWithOneComponent() throws Exception {

        ItemHandlerClient cc = new ItemHandlerClient();
        //cc.setServiceAddress("http://escidev6:8080");
        cc.setHandle(EscidocClientTestBase.DEFAULT_HANDLE);

        Item item = new Item();
        ItemProperties properties = new ItemProperties();
        properties.setContext(new ResourceRef(EXAMPLE_CONTEXT_ID));
        properties.setContentModel(new ResourceRef(EXAMPLE_CONTENT_MODEL_ID));
        //Content-model-specific
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.newDocument();
        Element contentModelSpecific = doc.createElementNS(
            null,
            "cms");
        Element element1= doc.createElement("some-other-stuff1");
        element1.setTextContent("33333333333333333333");
        
        
        
        List<Element> cmsContent = new LinkedList<Element>();
        cmsContent.add(contentModelSpecific);
        cmsContent.add(element1);
        ContentModelSpecific cms = new ContentModelSpecific();
        
        cms.setContent(cmsContent);
        
        properties.setContentModelSpecific(cms);
        
        item.setProperties(properties);
        MetadataRecords mdRecords = new MetadataRecords();
        MetadataRecord mdRecord = new MetadataRecord();
        mdRecord.setName("escidoc");
        
        Document doc1 = builder.newDocument();
        Element element = doc.createElementNS(
            null, "myMdRecord");
        mdRecord.setContent(element);

        mdRecords.add(mdRecord);
        item.setMetadataRecords(mdRecords);
        Component component = new Component();
        ComponentProperties componentProperties = new ComponentProperties();
        componentProperties.setContentCategory("content-category");
        componentProperties.setMimeType("text/xml");
        componentProperties.setValidStatus("valid");
        componentProperties.setVisibility("insitutional");
        component.setProperties(componentProperties);
        Components components = item.getComponents();
        components.add(component);
        ComponentContent content = new ComponentContent();
        content.setStorage("internal-managed");
        content.setBase64EncodedContent("skfjlfdf");
        component.setContent(content);
        Marshaller<Item> m = new Marshaller<Item>(item.getClass());
        String xml = m.marshalDocument(item);
        System.out.println(xml); 
        
        Item createdItem = cc.create(item);
        String createdItemXml = m.marshalDocument(createdItem);
        System.out.println("created item " + createdItemXml);
           
        
   }
}
