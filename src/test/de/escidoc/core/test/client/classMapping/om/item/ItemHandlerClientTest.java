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

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.log4j.Logger;
import org.apache.xpath.XPathAPI;
import org.joda.time.DateTime;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import de.escidoc.core.client.ItemHandlerClient;
import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.client.exceptions.application.notfound.ItemNotFoundException;
import de.escidoc.core.common.configuration.ConfigurationProvider;
import de.escidoc.core.common.jibx.Factory;
import de.escidoc.core.resources.common.Filter;
import de.escidoc.core.resources.common.Result;
import de.escidoc.core.resources.common.TaskParam;
import de.escidoc.core.resources.common.properties.ContentModelSpecific;
import de.escidoc.core.resources.common.versionhistory.Version;
import de.escidoc.core.resources.common.versionhistory.VersionHistory;
import de.escidoc.core.resources.om.item.Item;
import de.escidoc.core.resources.om.item.ItemList;
import de.escidoc.core.test.client.EscidocClientTestBase;

/**
 * Test the Item Handler Client.
 * 
 * @author SWA
 * 
 */
public class ItemHandlerClientTest extends EscidocClientTestBase {

    private final Logger logger =
        Logger.getLogger(ItemHandlerClientTest.class.getName());

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
     * Test retrieving settings from properties.
     * 
     * @throws InternalClientException
     *             Thrown if ConfigurationProvider failed.
     */
    public void testProperties() throws InternalClientException {

        ConfigurationProvider cp = ConfigurationProvider.getInstance();
        logger.debug("Server name: "
            + cp.getProperty(ConfigurationProvider.PROP_SERVER_NAME));
        logger.debug("Server port: "
            + cp.getProperty(ConfigurationProvider.PROP_SERVER_PORT));
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
        ic.setHandle(EscidocClientTestBase.DEFAULT_HANDLE);
        Item item = ic.retrieve(EXAMPLE_ITEM_ID);
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
            ic.setHandle(EscidocClientTestBase.DEFAULT_HANDLE);
            Item item = ic.retrieve(INVALID_RESOURCE_ID);
            logger.debug(Factory.getItemMarshaller().marshalDocument(
                (Item) item));
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
        ids.add(EXAMPLE_ITEM_ID);
        filters.add(getFilter("http://purl.org/dc/elements/1.1/identifier",
            null, ids));
        filters.add(getFilter(
            "http://escidoc.de/core/01/properties/public-status", "pending",
            null));
        filters.add(getFilter(
            "http://escidoc.de/core/01/structural-relations/created-by",
            "escidoc:user42", null));
        tp.setFilters(filters);
        logger.debug(Factory.getTaskParamMarshaller().marshalDocument(tp));

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
            "escidoc:user42", null));
        filterParam.setFilters(filters);
        logger.debug("Call retrieveItems with filter "
            + Factory.getTaskParamMarshaller().marshalDocument(filterParam));
        ItemHandlerClient ic = new ItemHandlerClient();
        ItemList itemList = ic.retrieveItems(filterParam);

        // FIXME check itemList
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
        ihc.setServiceAddress("http://localhost:8080");
        Item item = ihc.retrieve(EXAMPLE_ITEM_ID);

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
        Item item = ic.retrieve(EXAMPLE_ITEM_ID);

        Item result = ic.create(item);
        VersionHistory vh1 =
            ic.retrieveVersionHistory(((Item) result).getObjid());
        logger.debug(vh1.getLastModificationDate() + " " + vh1.getObjid());
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.newDocument();
        Element contentModelSpecific = doc.createElementNS(
            null,
            "props");
        contentModelSpecific.setTextContent("3. 2. 1. ");
        Element element1= doc.createElement("some-other-stuff1");
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

        logger.debug(vh2.getLastModificationDate());
        for (Version version : vh2.getVersions()) {
            logger.debug("=========================");
            logger.debug(version.getHref());
            logger.debug(version.getObjid());
            logger.debug(version.getTimestamp());
            logger.debug(version.getComment());
        }

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
        Item item = ic.retrieve(EXAMPLE_ITEM_ID);

        Item resultItem = ic.create(item);
        logger.debug("Created Item with id '" + resultItem.getObjid()
            + "' and status '" + resultItem.getProperties().getPublicStatus()
            + "'.");
        TaskParam tp = new TaskParam();
        tp.setLastModificationDate(resultItem.getLastModificationDate());

        // submit
        tp.setComment("Item '" + resultItem.getObjid() + " will be submitted.");
        ic.submit(resultItem.getObjid(), tp);
        resultItem = ic.retrieve(resultItem.getObjid());
        logger.debug("Submitted Item with id '" + resultItem.getObjid()
            + "', status '" + resultItem.getProperties().getPublicStatus()
            + "'.");

        // assign object pid
        tp.setLastModificationDate(resultItem.getLastModificationDate());
        tp.setComment(null);
        tp.setUrl("http://www.escidoc.de/test-pid");
        Result pidResult = ic.assignObjectPid(resultItem.getObjid(), tp);
        assertNotNull("AssignObjectPid returns null", pidResult);
        Node result =
            XPathAPI.selectSingleNode(pidResult.getPidParam(), "/pid");
        assertNotNull(result.getTextContent());

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
        logger.debug("Released Item with id '" + resultItem.getObjid()
            + "', status '" + resultItem.getProperties().getPublicStatus()
            + "'.");

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
     * @param value
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
