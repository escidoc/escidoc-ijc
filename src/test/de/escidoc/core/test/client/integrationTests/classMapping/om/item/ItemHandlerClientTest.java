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
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.joda.time.DateTime;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import de.escidoc.core.client.ItemHandlerClient;
import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.client.exceptions.application.notfound.ItemNotFoundException;
import de.escidoc.core.common.configuration.ConfigurationProvider;
import de.escidoc.core.common.jibx.Factory;
import de.escidoc.core.resources.ResourceRef;
import de.escidoc.core.resources.common.Filter;
import de.escidoc.core.resources.common.MetadataRecord;
import de.escidoc.core.resources.common.MetadataRecords;
import de.escidoc.core.resources.common.Result;
import de.escidoc.core.resources.common.TaskParam;
import de.escidoc.core.resources.common.properties.ContentModelSpecific;
import de.escidoc.core.resources.common.versionhistory.VersionHistory;
import de.escidoc.core.resources.om.item.Item;
import de.escidoc.core.resources.om.item.ItemList;
import de.escidoc.core.resources.om.item.ItemProperties;
import de.escidoc.core.test.client.Constants;
import de.escidoc.core.test.client.EscidocClientTestBase;

/**
 * Test the Item Handler Client.
 * 
 * @author SWA
 * 
 */
public class ItemHandlerClientTest {

    /**
     * Test retrieving settings from properties.
     * 
     * @throws InternalClientException
     *             Thrown if ConfigurationProvider failed.
     */
    @Test
    public void testProperties() throws InternalClientException {

        ConfigurationProvider cp = ConfigurationProvider.getInstance();
    }

    /**
     * Retrieve an existing Item.
     * 
     * @throws Exception
     *             Thrown if the item is not retrievable or invalid.
     */
    @Test
    public void testRetrieve01() throws Exception {

        ItemHandlerClient ic = new ItemHandlerClient();
        ic.login(EscidocClientTestBase.DEFAULT_SERVICE_URL,
            Constants.SYSTEM_ADMIN_USER, Constants.SYSTEM_ADMIN_PASSWORD);
        Item item = ic.retrieve(Constants.EXAMPLE_ITEM_ID);
        Factory.getItemMarshaller().marshalDocument(item);
    }

    /**
     * Retrieve an non-existing Item.
     * 
     * @throws Exception
     *             Thrown if the wrong exception is caught.
     */
    @Test
    public void testRetrieve02() throws Exception {
        try {
            ItemHandlerClient ic = new ItemHandlerClient();
            ic.login(EscidocClientTestBase.DEFAULT_SERVICE_URL,
                Constants.SYSTEM_ADMIN_USER, Constants.SYSTEM_ADMIN_PASSWORD);
            ic.retrieve(Constants.INVALID_RESOURCE_ID);

            fail("Missing Exception retrieving an non existing Item.");
        }
        catch (Exception e) {
            Class<?> ec = ItemNotFoundException.class;
            EscidocClientTestBase.assertExceptionType(ec.getName()
                + " expected.", ec, e);

        }
    }

    /**
     * Test binding filter.
     * 
     * @throws Exception
     *             Thrown if anythings failed.
     */
    @Test
    public void testFilterBinding() throws Exception {

        TaskParam tp = new TaskParam();
        tp.setLastModificationDate(new DateTime(System.currentTimeMillis()));
        tp.setComment("Lalalala!");
        tp.setPid("pid");

        Collection<Filter> filters = TaskParam.filtersFactory();
        Collection<String> ids = new LinkedList<String>();
        ids.add("escidoc:7043");
        ids.add("escidoc:7044");
        ids.add(Constants.EXAMPLE_ITEM_ID);
        filters.add(getFilter("http://purl.org/dc/elements/1.1/identifier",
            null, ids));
        filters.add(getFilter(
            "http://escidoc.de/core/01/properties/public-status", "pending",
            null));
        filters.add(getFilter(
            "http://escidoc.de/core/01/structural-relations/created-by",
            "escidoc:user42", null));
        tp.setFilters(filters);
    }

    /**
     * Test retrieving Items through filter request.
     * 
     * @throws Exception
     *             Thrown if anythings failed.
     */
    @Test
    public void testRetrieveItems() throws Exception {

        TaskParam filterParam = new TaskParam();
        Collection<Filter> filters = TaskParam.filtersFactory();

        filters.add(getFilter(
            "http://escidoc.de/core/01/structural-relations/created-by",
            "non-existing-user", null));
        filterParam.setFilters(filters);

        ItemHandlerClient ic = new ItemHandlerClient();
        ic.login(EscidocClientTestBase.DEFAULT_SERVICE_URL,
            Constants.SYSTEM_ADMIN_USER, Constants.SYSTEM_ADMIN_PASSWORD);

        ItemList itemList = ic.retrieveItems(filterParam);

        assertEquals("Wrong number of elements in list", 0, itemList
            .getItems().size());
    }

    /**
     * Test retrieving Items through filter request.
     * 
     * @throws Exception
     *             Thrown if anythings failed.
     */
    @Test
    public void testRetrieveItems02() throws Exception {

        // create an Item
        Item item = new Item();

        // Properties
        ItemProperties properties = new ItemProperties();
        properties.setContext(new ResourceRef(Constants.EXAMPLE_CONTEXT_ID));
        properties.setContentModel(new ResourceRef(
            Constants.EXAMPLE_CONTENT_MODEL_ID));
        // properties.setContentModelSpecific(getContentModelSpecific());
        item.setProperties(properties);

         // Md-Record
         MetadataRecord mdRecord = new MetadataRecord();
         mdRecord.setName("escidoc");

         DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
         DocumentBuilder builder = factory.newDocumentBuilder();
         Document doc = builder.newDocument();
         Element element = doc.createElementNS(null, "myMdRecord");
         mdRecord.setContent(element);
         
         MetadataRecords mdRecords = new MetadataRecords();
         mdRecords.add(mdRecord);
         item.setMetadataRecords(mdRecords);

        ItemHandlerClient ic = new ItemHandlerClient();
        ic.login(EscidocClientTestBase.DEFAULT_SERVICE_URL,
            Constants.SYSTEM_ADMIN_USER, Constants.SYSTEM_ADMIN_PASSWORD);

        Item createdItem = ic.create(item);

        // now check if at least this Item is in the list
        TaskParam filterParam = new TaskParam();
        Collection<Filter> filters = TaskParam.filtersFactory();

        filters.add(getFilter(
            "http://escidoc.de/core/01/structural-relations/created-by",
            Constants.SYSTEM_ADMIN_USER, null));
        filterParam.setFilters(filters);

        ItemList itemList = ic.retrieveItems(filterParam);
        
        assertTrue("Wrong number of elements in list", itemList
            .getItems().size() > 0);

        List<String> idList = new Vector<String>();

        Iterator<Item> it = itemList.getItems().iterator();
        while (it.hasNext()) {
            Item n = it.next();
            idList.add(n.getObjid());
        }

        assertTrue("Created Item missing in list", idList.contains(createdItem
            .getObjid()));

    }

    /**
     * Test to set the Item status to submit.
     * 
     * @throws Exception
     *             Thrown if anythings failed.
     */
    @Test
    public void testCreateAndSubmit() throws Exception {

        ItemHandlerClient ihc = new ItemHandlerClient();
        ihc.login(EscidocClientTestBase.DEFAULT_SERVICE_URL,
            Constants.SYSTEM_ADMIN_USER, Constants.SYSTEM_ADMIN_PASSWORD);

        Item item = ihc.retrieve(Constants.EXAMPLE_ITEM_ID);

        Item resultItem = ihc.create(item);

        TaskParam taskParam =
            getTaskParam(resultItem.getLastModificationDate(), "Submit Item "
                + resultItem.getObjid());

        Result result = ihc.submit(resultItem.getObjid(), taskParam);

        // check result
        result.getLastModificationDate();

    }

    /**
     * Test retrieving Version History.
     * 
     * @throws Exception
     *             Thrown if anythings failed.
     */
    @Test
    public void testRetrieveVersionHistory() throws Exception {

        ItemHandlerClient ic = new ItemHandlerClient();
        Item item = ic.retrieve(Constants.EXAMPLE_ITEM_ID);

        Item result = ic.create(item);
        VersionHistory vh1 =
            ic.retrieveVersionHistory(((Item) result).getObjid());

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.newDocument();
        Element contentModelSpecific = doc.createElementNS(null, "props");
        contentModelSpecific.setTextContent("3. 2. 1. ");
        Element element1 = doc.createElement("some-other-stuff1");
        element1.setTextContent("33333333333333333333");

        List<Element> cmsContent = new LinkedList<Element>();
        cmsContent.add(contentModelSpecific);
        cmsContent.add(element1);
        ContentModelSpecific cms = new ContentModelSpecific();

        cms.setContent(cmsContent);

        result.getProperties().setContentModelSpecific(cms);

        result = ic.update(result);

        VersionHistory vh2 =
            ic.retrieveVersionHistory(((Item) result).getObjid());

    }

    /**
     * Test an Item lifecycle.
     * 
     * @throws Exception
     *             Thrown if lifecycle failed.
     */
    @Test
    public void testItemLifecycle() throws Exception {

        ItemHandlerClient ic = new ItemHandlerClient();
        Item item = ic.retrieve(Constants.EXAMPLE_ITEM_ID);

        Item resultItem = ic.create(item);
        TaskParam tp = new TaskParam();
        tp.setLastModificationDate(resultItem.getLastModificationDate());

        // submit
        tp.setComment("Item '" + resultItem.getObjid() + " will be submitted.");
        ic.submit(resultItem.getObjid(), tp);
        resultItem = ic.retrieve(resultItem.getObjid());

        // assign object pid
        tp.setLastModificationDate(resultItem.getLastModificationDate());
        tp.setComment(null);
        tp.setUrl("http://www.escidoc.de/test-pid");
        Result pidResult = ic.assignObjectPid(resultItem.getObjid(), tp);
        assertNotNull("AssignObjectPid returns null", pidResult);
        assertNotNull("PID is missing", pidResult.getPidParam());

        // retrieve
        resultItem = ic.retrieve(resultItem.getObjid());

        // assign version pid
        tp.setLastModificationDate(resultItem.getLastModificationDate());
        tp.setComment(null);
        tp.setUrl("http://www.escidoc.de/test-pid");
        pidResult = ic.assignVersionPid(resultItem.getObjid() + ":1", tp);
        resultItem = ic.retrieve(resultItem.getObjid());

        // release
        tp.setLastModificationDate(resultItem.getLastModificationDate());
        tp.setComment("Item '" + resultItem.getObjid() + " will be released.");
        ic.release(resultItem.getObjid(), tp);
        resultItem = ic.retrieve(resultItem.getObjid());

    }

    /* ********************************************************************** */

    /**
     * Prepare the TaskParam.
     * 
     * @param lastModificationDate
     *            Set the last-modification-date of the taskParam
     * @param comment
     *            Set comment for task oriented operation.
     * @return The prepared TaskParam
     */
    private TaskParam getTaskParam(
        final DateTime lastModificationDate, final String comment) {

        TaskParam result = new TaskParam();
        result.setLastModificationDate(lastModificationDate);
        result.setComment(comment);
        if (comment == null) {
            result.setComment("");
        }
        return result;
    }

    /**
     * Prepare and Filter class from the parameter collection.
     * 
     * @param name
     *            Parameter name
     * @param value
     *            filter value
     * @param ids
     * @return
     */
    private Filter getFilter(
        final String name, final String value, Collection<String> ids) {

        Filter filter = new Filter();
        filter.setName(name);
        filter.setValue(value);
        filter.setIds(ids);
        return filter;
    }
}
