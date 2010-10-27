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

import java.net.URL;
import java.util.LinkedList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import de.escidoc.core.client.Authentication;
import de.escidoc.core.client.ItemHandlerClient;
import de.escidoc.core.client.TransportProtocol;
import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.client.exceptions.application.notfound.ItemNotFoundException;
import de.escidoc.core.client.interfaces.ItemHandlerClientInterface;
import de.escidoc.core.common.configuration.ConfigurationProvider;
import de.escidoc.core.common.jibx.Factory;
import de.escidoc.core.resources.common.Result;
import de.escidoc.core.resources.common.TaskParam;
import de.escidoc.core.resources.common.properties.ContentModelSpecific;
import de.escidoc.core.resources.common.versionhistory.VersionHistory;
import de.escidoc.core.resources.om.item.Item;
import de.escidoc.core.test.client.AbstractParameterizedTestBase;
import de.escidoc.core.test.client.Constants;
import de.escidoc.core.test.client.EscidocClientTestBase;

/**
 * Test the Item Handler Client.
 * 
 * @author SWA
 * 
 */
public class ItemHandlerClientTest extends AbstractParameterizedTestBase {

    private static final Logger LOG = Logger
        .getLogger(ItemHandlerClientTest.class);

    private Authentication auth;

    private ItemHandlerClientInterface ihc;

    public ItemHandlerClientTest(TransportProtocol transport) {
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

        Item item = ihc.retrieve(Constants.EXAMPLE_ITEM_ID);
        Factory
            .getMarshallerFactory(ihc.getTransport()).getItemMarshaller()
            .marshalDocument(item);
    }

    /**
     * Retrieve an non-existing Item.
     * 
     * @throws Exception
     *             Thrown if the wrong exception is caught.
     */
    @Test(expected = ItemNotFoundException.class)
    public void testRetrieve02() throws Exception {
        ihc.retrieve(Constants.INVALID_RESOURCE_ID);
    }

    /**
     * Test to set the Item status to submit.
     * 
     * @throws Exception
     *             Thrown if anythings failed.
     */
    @Test
    public void testCreateAndSubmit() throws Exception {
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
        Item item = ihc.retrieve(Constants.EXAMPLE_ITEM_ID);

        Item item2 = ihc.create(item);

        VersionHistory vh1 = ihc.retrieveVersionHistory(item2.getObjid());

        assertEquals("WOV has wrong number of versions in WOV of Item '"
            + item2.getObjid() + "'", 1, vh1.getVersions().size());
        assertEquals("Wrong timestamp in Item '" + item2.getObjid() + "'",
            item2.getProperties().getCreationDate(), vh1
                .getVersions().iterator().next().getTimestamp());

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

        item2.getProperties().setContentModelSpecific(cms);

        item2 = ihc.update(item2);

        VersionHistory vh2 =
            ihc.retrieveVersionHistory(((Item) item2).getObjid());

    }

    /**
     * Test an Item lifecycle.
     * 
     * @throws Exception
     *             Thrown if lifecycle failed.
     */
    @Test
    public void testItemLifecycle() throws Exception {
        Item item = ihc.retrieve(Constants.EXAMPLE_ITEM_ID);

        Item resultItem = ihc.create(item);
        TaskParam tp = new TaskParam();
        tp.setLastModificationDate(resultItem.getLastModificationDate());

        // submit
        tp.setComment("Item '" + resultItem.getObjid() + " will be submitted.");
        ihc.submit(resultItem.getObjid(), tp);
        resultItem = ihc.retrieve(resultItem.getObjid());

        // assign object pid
        tp.setLastModificationDate(resultItem.getLastModificationDate());
        tp.setComment(null);
        tp.setUrl(new URL("http://www.escidoc.de/test-pid"));
        Result pidResult = ihc.assignObjectPid(resultItem.getObjid(), tp);
        assertNotNull("AssignObjectPid returns null", pidResult);
        assertTrue("AssignObjectPid returns empty result", !pidResult.isEmpty());
        assertTrue("AssignObjectPid returns invalid result", pidResult
            .get(0).getNodeName().equals("pid"));
        LOG.debug(pidResult.get(0).getTextContent());
        assertTrue("AssignObjectPid returns invalid result", !pidResult
            .get(0).getTextContent().isEmpty());

        // retrieve
        resultItem = ihc.retrieve(resultItem.getObjid());

        // assign version pid
        tp.setLastModificationDate(resultItem.getLastModificationDate());
        tp.setComment(null);
        tp.setUrl(new URL("http://www.escidoc.de/test-pid"));
        pidResult = ihc.assignVersionPid(resultItem.getObjid() + ":1", tp);
        resultItem = ihc.retrieve(resultItem.getObjid());

        // release
        tp.setLastModificationDate(resultItem.getLastModificationDate());
        tp.setComment("Item '" + resultItem.getObjid() + " will be released.");
        ihc.release(resultItem.getObjid(), tp);
        resultItem = ihc.retrieve(resultItem.getObjid());

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

}
