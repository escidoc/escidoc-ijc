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
package de.escidoc.core.test.client.classMapping.om.item;

import java.util.LinkedList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import de.escidoc.core.client.ItemHandlerClient;
import de.escidoc.core.client.exceptions.EscidocException;
import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.client.exceptions.TransportException;
import de.escidoc.core.client.exceptions.application.invalid.InvalidXmlException;
import de.escidoc.core.client.exceptions.application.invalid.XmlSchemaValidationException;
import de.escidoc.core.common.jibx.Marshaller;
import de.escidoc.core.resources.ResourceRef;
import de.escidoc.core.resources.common.MetadataRecord;
import de.escidoc.core.resources.common.MetadataRecords;
import de.escidoc.core.resources.common.Result;
import de.escidoc.core.resources.common.TaskParam;
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
        Element contentModelSpecific = doc.createElementNS(null, "cms");
        Element element1 = doc.createElement("some-other-stuff1");
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
        Element element = doc1.createElementNS(null, "myMdRecord");

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

        ItemHandlerClient ihc = new ItemHandlerClient();
        ihc.login(EscidocClientTestBase.DEFAULT_SERVICE_URL,
            EscidocClientTestBase.SYSTEM_ADMIN_USER,
            EscidocClientTestBase.SYSTEM_ADMIN_PASSWORD);

        Item item = new Item();

        item.getProperties().setContext(
            new ResourceRef(EscidocClientTestBase.EXAMPLE_CONTEXT_ID));
        item.getProperties().setContentModel(
            new ResourceRef(EscidocClientTestBase.EXAMPLE_CONTENT_MODEL_ID));

        // Content-model
        ContentModelSpecific cms = getContentModelSpecific();
        item.getProperties().setContentModelSpecific(cms);

        // Metadata Record(s)
        MetadataRecords mdRecords = new MetadataRecords();
        MetadataRecord mdrecord = getMdRecord("escidoc");
        mdRecords.add(mdrecord);
        item.setMetadataRecords(mdRecords);

        // create
        Item createdItem = ihc.create(item);

        /*
         * compare the new created Item with the Item from the request
         */
        String objId = createdItem.getObjid();

        assertNotNull("Object id is null", objId);
        assertEquals(item.getProperties().getContext().getObjid(), createdItem
            .getProperties().getContext().getObjid());
        assertEquals(item.getProperties().getContentModel().getObjid(),
            createdItem.getProperties().getContentModel().getObjid());
    }

    /**
     * Test dealing with multiple MetadataRecords of Item.
     * 
     * @throws Exception
     *             Thrown if metadata records handling failed.
     */
    @Test
    public void testMultipleMetadataRecords01() throws Exception {

        ItemHandlerClient ihc = new ItemHandlerClient();
        ihc.login(EscidocClientTestBase.DEFAULT_SERVICE_URL,
            EscidocClientTestBase.SYSTEM_ADMIN_USER,
            EscidocClientTestBase.SYSTEM_ADMIN_PASSWORD);

        Item item = new Item();

        item.getProperties().setContext(
            new ResourceRef(EscidocClientTestBase.EXAMPLE_CONTEXT_ID));
        item.getProperties().setContentModel(
            new ResourceRef(EscidocClientTestBase.EXAMPLE_CONTENT_MODEL_ID));

        // Content-model
        ContentModelSpecific cms = getContentModelSpecific();
        item.getProperties().setContentModelSpecific(cms);

        // Metadata Record(s)
        MetadataRecords mdRecords = new MetadataRecords();

        MetadataRecord mdrecord1 = getMdRecord("escidoc");
        mdRecords.add(mdrecord1);

        MetadataRecord mdrecord2 = getMdRecord("mdRecord2");
        mdRecords.add(mdrecord2);

        MetadataRecord mdrecord3 = getMdRecord("mdRecord3");
        mdRecords.add(mdrecord3);

        item.setMetadataRecords(mdRecords);

        // create
        Item createdItem = ihc.create(item);

        // compare the new created Item with the Item from the request
        assertNotNull("Object id is null", createdItem.getObjid());
        assertEquals(item.getProperties().getContext().getObjid(), createdItem
            .getProperties().getContext().getObjid());
        assertEquals(item.getProperties().getContentModel().getObjid(),
            createdItem.getProperties().getContentModel().getObjid());
        assertEquals(item.getMetadataRecords().getMetadataRecords().size(),
            createdItem.getMetadataRecords().getMetadataRecords().size());
    }

    /**
     * Test create an Item with one Component.
     * 
     * @throws Exception
     *             Thrown if creation failed or non-volatile Item values differ.
     */
    @Test
    public void testCreateItemWithOneComponent() throws Exception {

        ItemHandlerClient cc = new ItemHandlerClient();
        cc.setHandle(EscidocClientTestBase.DEFAULT_HANDLE);

        Item item = new Item();
        ItemProperties properties = new ItemProperties();
        properties.setContext(new ResourceRef(EXAMPLE_CONTEXT_ID));
        properties.setContentModel(new ResourceRef(EXAMPLE_CONTENT_MODEL_ID));
        // Content-model-specific
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.newDocument();
        Element contentModelSpecific = doc.createElementNS(null, "cms");
        Element element1 = doc.createElement("some-other-stuff1");
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
        Element element = doc1.createElementNS(null, "myMdRecord");
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
        logger.debug("ITem to create:\n" + xml);

        Item createdItem = cc.create(item);
        String createdItemXml = m.marshalDocument(createdItem);
        logger.debug("created item\n" + createdItemXml);
    }

    /**
     * Test login to framework and create an Item with one Component.
     * 
     * @throws Exception
     *             Thrown if creation failed or non-volatile Item values differ.
     */
    @Test
    public void testloginAndCreateItemWith02() throws Exception {

        ItemHandlerClient cc = new ItemHandlerClient();
        cc.login(EscidocClientTestBase.DEFAULT_SERVICE_URL,
            EscidocClientTestBase.SYSTEM_ADMIN_USER,
            EscidocClientTestBase.SYSTEM_ADMIN_PASSWORD);

        Item item = new Item();

        // Properties
        ItemProperties properties = new ItemProperties();
        properties.setContext(new ResourceRef(EXAMPLE_CONTEXT_ID));
        properties.setContentModel(new ResourceRef(EXAMPLE_CONTENT_MODEL_ID));
        properties.setContentModelSpecific(getContentModelSpecific());
        item.setProperties(properties);

        // Md-Record
        MetadataRecords mdRecords = new MetadataRecords();
        mdRecords.add(getMdRecord("escidoc"));
        item.setMetadataRecords(mdRecords);

        // Components
        Component component = new Component();
        ComponentProperties componentProperties = new ComponentProperties();
        componentProperties.setContentCategory("content-category");
        componentProperties.setMimeType("text/xml");
        componentProperties.setValidStatus("valid");
        componentProperties.setVisibility("institutional");
        component.setProperties(componentProperties);
        Components components = item.getComponents();
        components.add(component);
        ComponentContent content = new ComponentContent();
        content.setStorage("internal-managed");
        content.setBase64EncodedContent("skfjlfdf");
        component.setContent(content);

        // only for debug
        Marshaller<Item> m = new Marshaller<Item>(item.getClass());
        String xml = m.marshalDocument(item);
        logger.debug("ITem to create:\n" + xml);

        Item createdItem = cc.create(item);

        // only for debug
        String createdItemXml = m.marshalDocument(createdItem);
        logger.debug("created item\n" + createdItemXml);
    }

    /**
     * Test lifecycle of Item.
     * 
     * @throws Exception
     *             Thrown if creation failed or non-volatile Item values differ.
     */
    @Test
    public void testLifecycleItem01() throws Exception {

        Item item = createItem();

        ItemHandlerClient ihc = new ItemHandlerClient();
        ihc.login(EscidocClientTestBase.DEFAULT_SERVICE_URL,
            EscidocClientTestBase.SYSTEM_ADMIN_USER,
            EscidocClientTestBase.SYSTEM_ADMIN_PASSWORD);

        System.out.println(item.getObjid());

        // submit --------------------------------------------------------------
        TaskParam tp = new TaskParam();
        tp.setLastModificationDate(item.getLastModificationDate());
        tp.setComment("submitted as java client lib test");

        Result result = ihc.submit(item, tp);

        // check Item
        // compare timestamps
        DateTime lmdResult = result.getLastModificationDate();
        Item retrievedItem = ihc.retrieve(item);
        DateTime lmdRetrievedItem = retrievedItem.getLastModificationDate();

        assertEquals("Timestamps differ", lmdRetrievedItem, lmdResult);

        // assign object PID ---------------------------------------------------
        TaskParam taskParam = new TaskParam();
        taskParam.setLastModificationDate(result.getLastModificationDate());
        taskParam.setUrl("http://url.to.the.solution/path/for/this/resource/"
            + System.nanoTime());
        taskParam.setComment("Object PID on eSciDoc Days 2009");

        result = ihc.assignObjectPid(item, taskParam);

        // compare timestamps
        lmdResult = result.getLastModificationDate();
        retrievedItem = ihc.retrieve(item);
        lmdRetrievedItem = retrievedItem.getLastModificationDate();

        assertEquals("Timestamps differ", lmdRetrievedItem, lmdResult);

        // assign version PID --------------------------------------------------
        // release -------------------------------------------------------------
        // update --------------------------------------------------------------
        // withdraw ------------------------------------------------------------

    }

    /**
     * Get md record with provided name.
     * 
     * @param name
     *            Name of md-record
     * @return md-record
     * @throws ParserConfigurationException
     *             Thrown if instance of DocumentBuiler failed to create.
     */
    private MetadataRecord getMdRecord(final String name)
        throws ParserConfigurationException {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.newDocument();

        MetadataRecord mdRecord = new MetadataRecord();
        mdRecord.setName(name);

        Element element = doc.createElementNS(null, "myMdRecord");
        mdRecord.setContent(element);

        return mdRecord;
    }

    /**
     * Create an Item with one Component.
     * 
     * @return Just created Item.
     * 
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     * @throws ParserConfigurationException
     */
    private Item createItem() throws EscidocException, InternalClientException,
        TransportException, ParserConfigurationException {

        ItemHandlerClient cc = new ItemHandlerClient();
        cc.login(EscidocClientTestBase.DEFAULT_SERVICE_URL,
            EscidocClientTestBase.SYSTEM_ADMIN_USER,
            EscidocClientTestBase.SYSTEM_ADMIN_PASSWORD);

        Item item = new Item();

        // Properties
        ItemProperties properties = new ItemProperties();
        properties.setContext(new ResourceRef(EXAMPLE_CONTEXT_ID));
        properties.setContentModel(new ResourceRef(EXAMPLE_CONTENT_MODEL_ID));
        properties.setContentModelSpecific(getContentModelSpecific());
        item.setProperties(properties);

        // Md-Record
        MetadataRecords mdRecords = new MetadataRecords();
        mdRecords.add(getMdRecord("escidoc"));
        item.setMetadataRecords(mdRecords);

        // Components
        Component component = new Component();
        ComponentProperties componentProperties = new ComponentProperties();
        componentProperties.setContentCategory("content-category");
        componentProperties.setMimeType("text/xml");
        componentProperties.setValidStatus("valid");
        componentProperties.setVisibility("institutional");
        component.setProperties(componentProperties);
        Components components = item.getComponents();
        components.add(component);
        ComponentContent content = new ComponentContent();
        content.setStorage("internal-managed");
        content.setBase64EncodedContent("skfjlfdf");
        component.setContent(content);

        return cc.create(item);
    }

    /**
     * Prepare data for content model specific.
     * 
     * @return ContentModelSpecific with some content
     * @throws ParserConfigurationException
     */
    private static ContentModelSpecific getContentModelSpecific()
        throws ParserConfigurationException {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.newDocument();

        Element contentModelSpecific = doc.createElementNS(null, "cms");
        Element element1 = doc.createElement("some-other-stuff");
        element1.setTextContent("some content - " + System.nanoTime());

        List<Element> cmsContent = new LinkedList<Element>();
        cmsContent.add(contentModelSpecific);
        cmsContent.add(element1);

        ContentModelSpecific cms = new ContentModelSpecific();
        cms.setContent(cmsContent);

        return cms;
    }

}
