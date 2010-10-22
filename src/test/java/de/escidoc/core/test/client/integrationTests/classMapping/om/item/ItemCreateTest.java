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
package de.escidoc.core.test.client.integrationTests.classMapping.om.item;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.io.File;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.joda.time.DateTime;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import de.escidoc.core.client.Authentication;
import de.escidoc.core.client.ItemHandlerClient;
import de.escidoc.core.client.StagingHandlerClient;
import de.escidoc.core.client.TransportProtocol;
import de.escidoc.core.client.exceptions.EscidocClientException;
import de.escidoc.core.client.exceptions.EscidocException;
import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.client.exceptions.TransportException;
import de.escidoc.core.client.exceptions.application.invalid.InvalidXmlException;
import de.escidoc.core.client.exceptions.application.invalid.XmlSchemaValidationException;
import de.escidoc.core.client.exceptions.application.missing.MissingMdRecordException;
import de.escidoc.core.client.interfaces.ItemHandlerClientInterface;
import de.escidoc.core.client.interfaces.StagingHandlerClientInterface;
import de.escidoc.core.common.jibx.Factory;
import de.escidoc.core.common.jibx.Marshaller;
import de.escidoc.core.resources.common.MetadataRecord;
import de.escidoc.core.resources.common.MetadataRecords;
import de.escidoc.core.resources.common.Result;
import de.escidoc.core.resources.common.TaskParam;
import de.escidoc.core.resources.common.properties.ContentModelSpecific;
import de.escidoc.core.resources.common.reference.ContentModelRef;
import de.escidoc.core.resources.common.reference.ContextRef;
import de.escidoc.core.resources.om.item.Item;
import de.escidoc.core.resources.om.item.ItemProperties;
import de.escidoc.core.resources.om.item.component.Component;
import de.escidoc.core.resources.om.item.component.ComponentContent;
import de.escidoc.core.resources.om.item.component.ComponentProperties;
import de.escidoc.core.resources.om.item.component.Components;
import de.escidoc.core.test.client.AbstractParameterizedTestBase;
import de.escidoc.core.test.client.Constants;
import de.escidoc.core.test.client.EscidocClientTestBase;
import de.escidoc.core.test.client.integrationTests.classMapping.om.ResourceUtility;

/**
 * Test create Item.
 * 
 * @author SWA
 * 
 */
public class ItemCreateTest extends AbstractParameterizedTestBase {

    private Authentication auth;

    private ItemHandlerClientInterface ihc;

    public ItemCreateTest(TransportProtocol transport) {
        super(transport);
    }

    @Before
    public void init() throws Exception {
        auth =
            new Authentication(EscidocClientTestBase.DEFAULT_SERVICE_URL,
                Constants.SYSTEM_ADMIN_USER, Constants.SYSTEM_ADMIN_PASSWORD);
        ihc = new ItemHandlerClient(auth.getServiceAddress());
        ihc.setHandle(auth.getHandle());
        ihc.setTransport(transport);
    }

    @After
    public void post() throws Exception {
        auth.logout();
    }

    /**
     * Test if the right exception is thrown if calling create with an
     * incomplete Item.
     * 
     * @throws Exception
     *             Thrown if no or wrong exception is caught from the framework.
     */
    @Test(expected = InvalidXmlException.class)
    public void testCreateItem01() throws Exception {
        Item item = new Item();
        ihc.create(item);
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
    @Test(expected = InvalidXmlException.class)
    public void testCreateItem02() throws Exception {
        Item item = new Item();
        item.setXLinkTitle("New title for test");
        ihc.create(item);
    }

    /**
     * Test if the right exception is thrown if calling create with an
     * incomplete Item.
     * 
     * @throws Exception
     *             Thrown if no or wrong exception is caught from the framework.
     */
    @Test(expected = InvalidXmlException.class)
    public void testCreateItem03() throws Exception {
        Item item = new Item();
        ItemProperties properties = new ItemProperties();
        item.setProperties(properties);

        ihc.create(item);
    }

    /**
     * Test if the right exception is thrown if calling create with an
     * incomplete Item.
     * 
     * @throws Exception
     *             Thrown if no or wrong exception is caught from the framework.
     */
    @Test(expected = InvalidXmlException.class)
    public void testCreateItem04() throws Exception {
        Item item = new Item();
        ItemProperties properties = new ItemProperties();
        item.setProperties(properties);
        ihc.create(item);
    }

    /**
     * Test if the right exception is thrown if calling create with an
     * incomplete Item.
     * 
     * @throws Exception
     *             Thrown if no or wrong exception is caught from the framework.
     */
    @Test(expected = InvalidXmlException.class)
    public void testCreateItem05() throws Exception {
        Item item = new Item();
        ItemProperties properties = new ItemProperties();
        properties.setContext(new ContextRef(Constants.EXAMPLE_CONTEXT_ID));
        item.setProperties(properties);
        ihc.create(item);
    }

    /**
     * Test if the right exception is thrown if calling create with an
     * incomplete Item.
     * 
     * @throws Exception
     *             Thrown if no or wrong exception is caught from the framework.
     */
    @Test(expected = XmlSchemaValidationException.class)
    public void testCreateItem06() throws Exception {
        Item item = new Item();
        ItemProperties properties = new ItemProperties();
        properties.setContext(new ContextRef(Constants.EXAMPLE_CONTEXT_ID));
        item.setProperties(properties);
        MetadataRecords mdRecords = new MetadataRecords();
        item.setMetadataRecords(mdRecords);

        ihc.create(item);
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
    @Test(expected = XmlSchemaValidationException.class)
    public void testCreateItem07() throws Exception {
        Item item = new Item();
        ItemProperties properties = new ItemProperties();
        properties.setContext(new ContextRef(Constants.EXAMPLE_CONTEXT_ID));
        item.setProperties(properties);
        MetadataRecords mdRecords = new MetadataRecords();
        MetadataRecord mdRecord = new MetadataRecord();
        mdRecords.add(mdRecord);
        item.setMetadataRecords(mdRecords);

        ihc.create(item);
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
    @Test(expected = XmlSchemaValidationException.class)
    public void testCreateItem08() throws Exception {
        Item item = new Item();
        ItemProperties properties = new ItemProperties();
        properties.setContext(new ContextRef(Constants.EXAMPLE_CONTEXT_ID));
        item.setProperties(properties);
        MetadataRecords mdRecords = new MetadataRecords();
        MetadataRecord mdRecord = new MetadataRecord();
        mdRecord.setName("escidoc");
        mdRecords.add(mdRecord);
        item.setMetadataRecords(mdRecords);

        ihc.create(item);
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
    @Test(expected = InvalidXmlException.class)
    public void testCreateItem09() throws Exception {
        Item item = new Item();
        ItemProperties properties = new ItemProperties();
        properties.setContext(new ContextRef(Constants.EXAMPLE_CONTEXT_ID));
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

        ihc.create(item);
    }

    /**
     * Test if values of requested Item compares to the values of the response.
     * 
     * @throws Exception
     *             Thrown if creation failed or non-volatile Item values differ.
     */
    @Test
    public void testCreateItem10() throws Exception {
        Item item = new Item();

        item.getProperties().setContext(
            new ContextRef(Constants.EXAMPLE_CONTEXT_ID));
        item.getProperties().setContentModel(
            new ContentModelRef(Constants.EXAMPLE_CONTENT_MODEL_ID));
        item.setXLinkTitle("TEST");

        // Content-model
        ContentModelSpecific cms = ResourceUtility.getContentModelSpecific();
        item.getProperties().setContentModelSpecific(cms);

        // Metadata Record(s)
        MetadataRecords mdRecords = new MetadataRecords();
        MetadataRecord mdrecord = ResourceUtility.getMdRecord("escidoc");
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
        Item item = new Item();

        item.getProperties().setContext(
            new ContextRef(Constants.EXAMPLE_CONTEXT_ID));
        item.getProperties().setContentModel(
            new ContentModelRef(Constants.EXAMPLE_CONTENT_MODEL_ID));

        // Content-model
        ContentModelSpecific cms = ResourceUtility.getContentModelSpecific();
        item.getProperties().setContentModelSpecific(cms);

        // Metadata Record(s)
        MetadataRecords mdRecords = new MetadataRecords();

        MetadataRecord mdrecord1 = ResourceUtility.getMdRecord("escidoc");
        mdRecords.add(mdrecord1);

        MetadataRecord mdrecord2 = ResourceUtility.getMdRecord("mdRecord2");
        mdRecords.add(mdrecord2);

        MetadataRecord mdrecord3 = ResourceUtility.getMdRecord("mdRecord3");
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
        assertEquals(item.getMetadataRecords().size(), createdItem
            .getMetadataRecords().size());
    }

    /**
     * Test deletion of non-default md-record.
     * 
     * @throws Exception
     */
    @Test
    public void testMDRecordsDeletion01() throws Exception {
        Item item = new Item();

        item.getProperties().setContext(
            new ContextRef(Constants.EXAMPLE_CONTEXT_ID));
        item.getProperties().setContentModel(
            new ContentModelRef(Constants.EXAMPLE_CONTENT_MODEL_ID));

        // Metadata Record(s)
        MetadataRecords mdRecords = new MetadataRecords();

        MetadataRecord mdrecord1 = ResourceUtility.getMdRecord("escidoc");
        mdRecords.add(mdrecord1);

        MetadataRecord mdrecord2 = ResourceUtility.getMdRecord("mdRecord2");
        mdRecords.add(mdrecord2);

        item.setMetadataRecords(mdRecords);

        // create
        Item createdItem = ihc.create(item);

        // compare the new created Item with the Item from the request
        assertNotNull("Object id is null", createdItem.getObjid());
        assertEquals(Constants.EXAMPLE_CONTEXT_ID, createdItem
            .getProperties().getContext().getObjid());
        assertEquals(Constants.EXAMPLE_CONTENT_MODEL_ID, createdItem
            .getProperties().getContentModel().getObjid());
        assertEquals(item.getMetadataRecords().size(), createdItem
            .getMetadataRecords().size());

        // delete md-record
        createdItem.getMetadataRecords().del(mdrecord2.getName());

        Item updatedItem = ihc.update(createdItem);

        // compare the new created Item with the Item from the request
        assertNotNull("Object id is null", updatedItem.getObjid());
        assertEquals(Constants.EXAMPLE_CONTEXT_ID, updatedItem
            .getProperties().getContext().getObjid());
        assertEquals(Constants.EXAMPLE_CONTENT_MODEL_ID, updatedItem
            .getProperties().getContentModel().getObjid());
        assertEquals(1, updatedItem.getMetadataRecords().size());
    }

    /**
     * Test deletion of required default md-record.
     * 
     * @throws Exception
     */
    @Test(expected = MissingMdRecordException.class)
    public void testMDRecordsDeletion02() throws Exception {
        Item item = new Item();

        item.getProperties().setContext(
            new ContextRef(Constants.EXAMPLE_CONTEXT_ID));
        item.getProperties().setContentModel(
            new ContentModelRef(Constants.EXAMPLE_CONTENT_MODEL_ID));

        // Metadata Record(s)
        MetadataRecords mdRecords = new MetadataRecords();

        MetadataRecord mdrecord1 = ResourceUtility.getMdRecord("escidoc");
        mdRecords.add(mdrecord1);

        MetadataRecord mdrecord2 = ResourceUtility.getMdRecord("mdRecord2");
        mdRecords.add(mdrecord2);

        item.setMetadataRecords(mdRecords);

        // create
        Item createdItem = ihc.create(item);

        // compare the new created Item with the Item from the request
        assertNotNull("Object id is null", createdItem.getObjid());
        assertEquals(Constants.EXAMPLE_CONTEXT_ID, createdItem
            .getProperties().getContext().getObjid());
        assertEquals(Constants.EXAMPLE_CONTENT_MODEL_ID, createdItem
            .getProperties().getContentModel().getObjid());
        assertEquals(item.getMetadataRecords().size(), createdItem
            .getMetadataRecords().size());

        // delete required md-record
        createdItem.getMetadataRecords().del(mdrecord1.getName());

        Item updatedItem = ihc.update(createdItem);

        // compare the new created Item with the Item from the request
        assertNotNull("Object id is null", updatedItem.getObjid());
        assertEquals(Constants.EXAMPLE_CONTEXT_ID, updatedItem
            .getProperties().getContext().getObjid());
        assertEquals(Constants.EXAMPLE_CONTENT_MODEL_ID, updatedItem
            .getProperties().getContentModel().getObjid());
        assertEquals(1, updatedItem.getMetadataRecords().size());
    }

    /**
     * Test deletion of all md-record entries.
     * 
     * @throws Exception
     */
    @Test(expected = InvalidXmlException.class)
    public void testMDRecordsDeletion03() throws Exception {
        Item item = new Item();

        item.getProperties().setContext(
            new ContextRef(Constants.EXAMPLE_CONTEXT_ID));
        item.getProperties().setContentModel(
            new ContentModelRef(Constants.EXAMPLE_CONTENT_MODEL_ID));

        // Metadata Record(s)
        MetadataRecords mdRecords = new MetadataRecords();

        MetadataRecord mdrecord1 = ResourceUtility.getMdRecord("escidoc");
        mdRecords.add(mdrecord1);

        MetadataRecord mdrecord2 = ResourceUtility.getMdRecord("mdRecord2");
        mdRecords.add(mdrecord2);

        item.setMetadataRecords(mdRecords);

        // create
        Item createdItem = ihc.create(item);

        // compare the new created Item with the Item from the request
        assertNotNull("Object id is null", createdItem.getObjid());
        assertEquals(Constants.EXAMPLE_CONTEXT_ID, createdItem
            .getProperties().getContext().getObjid());
        assertEquals(Constants.EXAMPLE_CONTENT_MODEL_ID, createdItem
            .getProperties().getContentModel().getObjid());
        assertEquals(item.getMetadataRecords().size(), createdItem
            .getMetadataRecords().size());

        // delete all md-record entries
        createdItem.getMetadataRecords().del(mdrecord1.getName());
        createdItem.getMetadataRecords().del(mdrecord2.getName());

        ihc.update(createdItem);
    }

    /**
     * Test deletion of md-records.
     * 
     * @throws Exception
     */
    @Test
    public void testMDRecordsDeletion04() throws Exception {
        Item item = new Item();

        item.getProperties().setContext(
            new ContextRef(Constants.EXAMPLE_CONTEXT_ID));
        item.getProperties().setContentModel(
            new ContentModelRef(Constants.EXAMPLE_CONTENT_MODEL_ID));

        // Metadata Record(s)
        MetadataRecords mdRecords = new MetadataRecords();

        MetadataRecord mdrecord1 = ResourceUtility.getMdRecord("escidoc");
        mdRecords.add(mdrecord1);

        MetadataRecord mdrecord2 = ResourceUtility.getMdRecord("mdRecord2");
        mdRecords.add(mdrecord2);

        item.setMetadataRecords(mdRecords);

        // create
        Item createdItem = ihc.create(item);

        // compare the new created Item with the Item from the request
        assertNotNull("Object id is null", createdItem.getObjid());
        assertEquals(Constants.EXAMPLE_CONTEXT_ID, createdItem
            .getProperties().getContext().getObjid());
        assertEquals(Constants.EXAMPLE_CONTENT_MODEL_ID, createdItem
            .getProperties().getContentModel().getObjid());
        assertEquals(item.getMetadataRecords().size(), createdItem
            .getMetadataRecords().size());

        // delete md-records
        createdItem.setMetadataRecords(null);

        Item updatedItem = ihc.update(createdItem);

        // compare the new created Item with the Item from the request
        assertNotNull("Object id is null", updatedItem.getObjid());
        assertEquals(Constants.EXAMPLE_CONTEXT_ID, updatedItem
            .getProperties().getContext().getObjid());
        assertEquals(Constants.EXAMPLE_CONTENT_MODEL_ID, updatedItem
            .getProperties().getContentModel().getObjid());
        assertNull(updatedItem.getMetadataRecords());
    }

    /**
     * Test create an Item with one Component.
     * 
     * @throws Exception
     *             Thrown if creation failed or non-volatile Item values differ.
     */
    @Test
    public void testCreateItemWithOneComponent() throws Exception {
        Item item = new Item();
        ItemProperties properties = new ItemProperties();

        properties.setContext(new ContextRef(Constants.EXAMPLE_CONTEXT_ID));
        properties.setContentModel(new ContentModelRef(
            Constants.EXAMPLE_CONTENT_MODEL_ID));

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
        ComponentContent content = new ComponentContent();
        content.setStorage("internal-managed");
        content.setBase64EncodedContent("skfjlfdf");
        component.setContent(content);

        Components components = new Components();
        components.add(component);
        item.setComponents(components);

        Marshaller<Item> m =
            Factory
                .getMarshallerFactory(ihc.getTransport()).getItemMarshaller();
        m.marshalDocument(item);

        Item createdItem = ihc.create(item);
        m.marshalDocument(createdItem);
    }

    /**
     * Test login to framework and create an Item with one Component.
     * 
     * @throws Exception
     *             Thrown if creation failed or non-volatile Item values differ.
     */
    @Test
    public void testloginAndCreateItemWith02() throws Exception {
        Item item = new Item();

        // Properties
        ItemProperties properties = new ItemProperties();
        properties.setContext(new ContextRef(Constants.EXAMPLE_CONTEXT_ID));
        properties.setContentModel(new ContentModelRef(
            Constants.EXAMPLE_CONTENT_MODEL_ID));
        properties.setContentModelSpecific(ResourceUtility
            .getContentModelSpecific());
        item.setProperties(properties);

        // Md-Record
        MetadataRecords mdRecords = new MetadataRecords();
        mdRecords.add(ResourceUtility.getMdRecord("escidoc"));
        item.setMetadataRecords(mdRecords);

        // Components
        Component component = new Component();
        ComponentProperties componentProperties = new ComponentProperties();
        componentProperties.setContentCategory("content-category");
        componentProperties.setMimeType("text/xml");
        componentProperties.setValidStatus("valid");
        componentProperties.setVisibility("institutional");
        component.setProperties(componentProperties);
        Components components = new Components();
        components.add(component);
        item.setComponents(components);
        ComponentContent content = new ComponentContent();
        content.setStorage("internal-managed");
        content.setBase64EncodedContent("skfjlfdf");
        component.setContent(content);

        // only for debug
        Marshaller<Item> m =
            Factory
                .getMarshallerFactory(ihc.getTransport()).getItemMarshaller();
        m.marshalDocument(item);

        Item createdItem = ihc.create(item);

        // only for debug
        m.marshalDocument(createdItem);
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

        // submit --------------------------------------------------------------
        TaskParam taskParam = new TaskParam();
        taskParam.setLastModificationDate(item.getLastModificationDate());
        taskParam.setComment("submitted as java client lib test");

        Result result = ihc.submit(item, taskParam);

        // check Item
        // compare timestamps
        Item retrievedItem = ihc.retrieve(item);
        DateTime lmdRetrievedItem = retrievedItem.getLastModificationDate();

        assertEquals("Timestamps differ", lmdRetrievedItem,
            result.getLastModificationDate());

        // assign object PID ---------------------------------------------------
        taskParam = new TaskParam();
        taskParam.setLastModificationDate(result.getLastModificationDate());
        taskParam.setUrl(new URL(
            "http://url.to.the.solution/path/for/this/resource/"
                + System.nanoTime()));
        taskParam.setComment("Test Object PID");

        result = ihc.assignObjectPid(item, taskParam);

        // compare timestamps
        retrievedItem = ihc.retrieve(item);
        lmdRetrievedItem = retrievedItem.getLastModificationDate();

        assertEquals("Timestamps differ", lmdRetrievedItem,
            result.getLastModificationDate());

        // assign version PID --------------------------------------------------
        taskParam = new TaskParam();
        taskParam.setLastModificationDate(result.getLastModificationDate());
        taskParam.setUrl(new URL(
            "http://url.to.the.solution/path/for/this/resource/"
                + System.nanoTime()));
        taskParam.setComment("Test Version PID");

        result = ihc.assignVersionPid(item, taskParam);

        // release -------------------------------------------------------------
        taskParam = new TaskParam();
        taskParam.setLastModificationDate(result.getLastModificationDate());
        taskParam.setComment("Release as java client lib test");

        result = ihc.release(item, taskParam);
        // update --------------------------------------------------------------
        // withdraw ------------------------------------------------------------
        taskParam = new TaskParam();
        taskParam.setLastModificationDate(result.getLastModificationDate());
        taskParam.setComment("Withdraw as java client lib test");

        result = ihc.withdraw(item, taskParam);
    }

    /**
     * Check create of Item with one component with content.
     * 
     * @throws Exception
     */
    @Test
    public void createItemWithComponentAndContent() throws Exception {
        // create file with random content
        File file = ResourceUtility.createFileWithRandomContent();

        StagingHandlerClientInterface sthc =
            new StagingHandlerClient(auth.getServiceAddress());
        sthc.setHandle(auth.getHandle());
        sthc.setTransport(transport);

        URL contentRef = sthc.upload(file);

        Item item = new Item();

        item.getProperties().setContext(
            new ContextRef(Constants.EXAMPLE_CONTEXT_ID));
        item.getProperties().setContentModel(
            new ContentModelRef(Constants.EXAMPLE_CONTENT_MODEL_ID));

        // Metadata Record(s)
        MetadataRecords mdRecords = new MetadataRecords();
        MetadataRecord mdrecord = ResourceUtility.getMdRecord("escidoc");
        mdRecords.add(mdrecord);
        item.setMetadataRecords(mdRecords);

        Component component = new Component();
        ComponentContent content = new ComponentContent();
        content.setXLinkHref(contentRef.toString());
        content.setStorage("internal-managed");
        component.setContent(content);
        component.setProperties(new ComponentProperties());
        component.getProperties().setDescription("Random content");
        component.getProperties().setFileName(contentRef.getFile());
        component.getProperties().setVisibility("public");
        component.getProperties().setContentCategory("pre-print");
        Components components = new Components();
        components.add(component);
        item.setComponents(components);

        // create
        Item newItem = ihc.create(item);
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
    private Item createItem() throws EscidocClientException,
        InternalClientException, TransportException,
        ParserConfigurationException {

        Item item = new Item();

        // Properties
        ItemProperties properties = new ItemProperties();
        properties.setContext(new ContextRef(Constants.EXAMPLE_CONTEXT_ID));
        properties.setContentModel(new ContentModelRef(
            Constants.EXAMPLE_CONTENT_MODEL_ID));

        properties.setContentModelSpecific(ResourceUtility
            .getContentModelSpecific());
        item.setProperties(properties);

        // Md-Record
        MetadataRecords mdRecords = new MetadataRecords();
        mdRecords.add(ResourceUtility.getMdRecord("escidoc"));
        item.setMetadataRecords(mdRecords);

        // Components
        Component component = new Component();
        ComponentProperties componentProperties = new ComponentProperties();
        componentProperties.setContentCategory("content-category");
        componentProperties.setMimeType("text/xml");
        componentProperties.setValidStatus("valid");
        componentProperties.setVisibility("institutional");
        component.setProperties(componentProperties);
        Components components = new Components();
        components.add(component);
        item.setComponents(components);
        ComponentContent content = new ComponentContent();
        content.setStorage("internal-managed");
        content.setBase64EncodedContent("skfjlfdf");
        component.setContent(content);

        return ihc.create(item);
    }
}