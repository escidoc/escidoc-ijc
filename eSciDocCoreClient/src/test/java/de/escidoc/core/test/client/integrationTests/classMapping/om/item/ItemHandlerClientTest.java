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

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.joda.time.DateTime;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import de.escidoc.core.client.Authentication;
import de.escidoc.core.client.ItemHandlerClient;
import de.escidoc.core.client.exceptions.EscidocException;
import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.client.exceptions.TransportException;
import de.escidoc.core.client.exceptions.application.notfound.ItemNotFoundException;
import de.escidoc.core.client.interfaces.ItemHandlerClientInterface;
import de.escidoc.core.common.jibx.MarshallerFactory;
import de.escidoc.core.resources.common.MetadataRecord;
import de.escidoc.core.resources.common.MetadataRecords;
import de.escidoc.core.resources.common.Relation;
import de.escidoc.core.resources.common.Relations;
import de.escidoc.core.resources.common.Result;
import de.escidoc.core.resources.common.TaskParam;
import de.escidoc.core.resources.common.properties.ContentModelSpecific;
import de.escidoc.core.resources.common.reference.ContainerRef;
import de.escidoc.core.resources.common.reference.ContentModelRef;
import de.escidoc.core.resources.common.reference.ContextRef;
import de.escidoc.core.resources.common.versionhistory.VersionHistory;
import de.escidoc.core.resources.om.item.Item;
import de.escidoc.core.test.client.EscidocClientTestBase;
import de.escidoc.core.test.client.integrationTests.classMapping.om.ResourceUtility;

/**
 * Test the Item Handler Client.
 * 
 * @author SWA
 * 
 */
public class ItemHandlerClientTest {

    private static final Logger LOG = LoggerFactory.getLogger(ItemHandlerClientTest.class);

    private Authentication auth;

    private ItemHandlerClientInterface ihc;

    @Before
    public void init() throws Exception {
        auth =
            new Authentication(EscidocClientTestBase.getDefaultInfrastructureURL(),
                EscidocClientTestBase.SYSTEM_ADMIN_USER, EscidocClientTestBase.SYSTEM_ADMIN_PASSWORD);
        ihc = new ItemHandlerClient(auth.getServiceAddress());
        ihc.setHandle(auth.getHandle());
    }

    @After
    public void post() throws Exception {
        if (auth != null)
            auth.logout();
    }

    /**
     * Retrieve an existing Item.
     * 
     * @throws Exception
     *             Thrown if the item is not retrievable or invalid.
     */
    @Test
    public void testRetrieve01() throws Exception {

        final Item item = ihc.retrieve(EscidocClientTestBase.getStaticItemId());
        MarshallerFactory.getInstance(ihc.getTransport()).getMarshaller(Item.class).marshalDocument(item);
    }

    /**
     * Retrieve an non-existing Item.
     * 
     * @throws Exception
     *             Thrown if the wrong exception is caught.
     */
    @Test(expected = ItemNotFoundException.class)
    public void testRetrieve02() throws Exception {
        ihc.retrieve(EscidocClientTestBase.INVALID_RESOURCE_ID);
    }

    /**
     * 
     * @throws Exception
     */
    @Test
    public void testRelations01() throws Exception {
        Item item = ihc.retrieve(EscidocClientTestBase.getStaticItemId());

        Relations relations = new Relations();
        relations.add(new Relation(new ContainerRef(EscidocClientTestBase.getStaticContainerId()),
            "http://www.escidoc.de/ontologies/mpdl-ontologies/content-relations#isAnnotationOf"));
        item.setRelations(relations);

        item = ihc.create(item);

        assertNotNull(item);
        assertNotNull(item.getRelations());
        assertTrue(item.getRelations().size() > 0);
        for (final Relation relation : item.getRelations()) {
            // check if type has been generated
            assertNotNull(relation.getResourceType());
        }

        // check if single method call also returns valid relation objects
        relations = ihc.retrieveRelations(item.getObjid());
        assertNotNull(relations);
        assertTrue(relations.size() > 0);
        for (final Relation relation : relations) {
            // check if type has been generated
            assertNotNull(relation.getResourceType());
        }
    }

    /**
     * Test to set the Item status to submit.
     * 
     * @throws Exception
     *             Thrown if anythings failed.
     */
    @Test
    public void testCreateAndSubmit() throws Exception {
        final Item item = ihc.retrieve(EscidocClientTestBase.getStaticItemId());

        final Item resultItem = ihc.create(item);

        final TaskParam taskParam =
            getTaskParam(resultItem.getLastModificationDate(), "Submit Item " + resultItem.getObjid());

        final Result result = ihc.submit(resultItem.getObjid(), taskParam);

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
        final Item item = ihc.retrieve(EscidocClientTestBase.getStaticItemId());

        Item item2 = ihc.create(item);

        final VersionHistory vh1 = ihc.retrieveVersionHistory(item2.getObjid());

        assertEquals("WOV has wrong number of versions in WOV of Item '" + item2.getObjid() + "'", 1, vh1
            .getVersions().size());
        assertEquals("Wrong timestamp in Item '" + item2.getObjid() + "'", item2.getProperties().getCreationDate(), vh1
            .getVersions().iterator().next().getTimestamp());

        final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        final DocumentBuilder builder = factory.newDocumentBuilder();
        final Document doc = builder.newDocument();
        final Element contentModelSpecific = doc.createElementNS(null, "props");
        contentModelSpecific.setTextContent("3. 2. 1. ");
        final Element element1 = doc.createElement("some-other-stuff1");
        element1.setTextContent("33333333333333333333");

        item2 = ihc.update(item2);

        final VersionHistory vh2 = ihc.retrieveVersionHistory(item2.getObjid());

    }

    /**
     * Test an Item lifecycle.
     * 
     * @throws Exception
     *             Thrown if lifecycle failed.
     */
    @Test
    public void testItemLifecycle() throws Exception {
        // Item item = ihc.retrieve(EscidocClientTestBase.getStaticItemId());
        Item resultItem = createBasicItem();

        // Item resultItem = ihc.create(item);
        final TaskParam tp = new TaskParam();
        tp.setLastModificationDate(resultItem.getLastModificationDate());

        // submit
        tp.setComment("Item '" + resultItem.getObjid() + " will be submitted.");
        ihc.submit(resultItem.getObjid(), tp);

        // retrieve submitted item
        resultItem = ihc.retrieve(resultItem.getObjid());

        // assign object pid
        tp.setLastModificationDate(resultItem.getLastModificationDate());
        tp.setComment(null);
        tp.setUrl(new URL("http://www.escidoc.de/test-pid"));
        Result pidResult = ihc.assignObjectPid(resultItem.getObjid(), tp);

        // asserts
        assertNotNull("AssignObjectPid returns null", pidResult);
        assertTrue("AssignObjectPid returns empty result", !pidResult.isEmpty());
        assertTrue("AssignObjectPid returns invalid result", pidResult.get(0).getNodeName().equals("pid"));
        LOG.debug(pidResult.get(0).getTextContent());
        assertTrue("AssignObjectPid returns invalid result", !pidResult.get(0).getTextContent().isEmpty());

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

        // retrieve released item
        resultItem = ihc.retrieve(resultItem.getObjid());

    }

    /**
     * @return
     * @throws TransportException
     * @throws EscidocException
     * @throws InternalClientException
     * @throws ParserConfigurationException
     */
    private Item createBasicItem() throws TransportException, EscidocException, InternalClientException,
        ParserConfigurationException {
        final Item item = new Item();

        item.getProperties().setContext(new ContextRef(EscidocClientTestBase.getStaticContextId()));
        item.getProperties().setContentModel(new ContentModelRef(EscidocClientTestBase.getStaticContentModelId()));

        // Content-model
        final ContentModelSpecific cms = ResourceUtility.getContentModelSpecific();
        item.getProperties().setContentModelSpecific(cms);

        // Metadata Record(s)
        final MetadataRecords mdRecords = new MetadataRecords();
        final MetadataRecord mdrecord = ResourceUtility.getMdRecord("escidoc");
        mdRecords.add(mdrecord);
        item.setMetadataRecords(mdRecords);

        // create
        return ihc.create(item);
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
    private TaskParam getTaskParam(final DateTime lastModificationDate, final String comment) {

        final TaskParam result = new TaskParam();
        result.setLastModificationDate(lastModificationDate);
        result.setComment(comment);
        if (comment == null) {
            result.setComment("");
        }
        return result;
    }

}
