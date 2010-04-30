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
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import de.escidoc.core.client.ContainerHandlerClient;
import de.escidoc.core.client.ItemHandlerClient;
import de.escidoc.core.client.exceptions.EscidocException;
import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.client.exceptions.TransportException;
import de.escidoc.core.resources.ResourceRef;
import de.escidoc.core.resources.common.MetadataRecord;
import de.escidoc.core.resources.common.MetadataRecords;
import de.escidoc.core.resources.common.TaskParam;
import de.escidoc.core.resources.common.properties.ContentModelSpecific;
import de.escidoc.core.resources.om.container.Container;
import de.escidoc.core.resources.om.container.ContainerProperties;
import de.escidoc.core.resources.om.item.Item;
import de.escidoc.core.test.client.Constants;
import de.escidoc.core.test.client.EscidocClientTestBase;

/**
 * Test add and delete members of Container.
 * 
 * @author SWA
 * 
 */
public class MemberTest {

    /**
     * Test to add an Item as member of a Container.
     * 
     * @throws Exception
     *             Thrown if no or wrong exception is caught from the framework.
     */
    @Test
    public void testAddMember01() throws Exception {

        ContainerHandlerClient cc = new ContainerHandlerClient();
        cc.login(EscidocClientTestBase.DEFAULT_SERVICE_URL,
            Constants.SYSTEM_ADMIN_USER, Constants.SYSTEM_ADMIN_PASSWORD);

        // create Container
        Container container = createContainer(cc);

        // create Item
        Item item = createItem();

        // task oriented methods take values via task param
        TaskParam taskParam = new TaskParam();
        taskParam.setLastModificationDate(container.getLastModificationDate());
        taskParam.addResourceRef(item.getObjid());

        // call addMembers method
        cc.addMembers(container.getObjid(), taskParam);

        Container updatedContainer = cc.retrieve(container.getObjid());

        assertEquals("Wrong member count", 1, updatedContainer
            .getMembers().size());

        Iterator<ResourceRef> it = updatedContainer.getMembers().iterator();
        assertEquals("Wrong member ref", item.getObjid(), it.next().getObjid());
    }

    /**
     * Test to add multiple Item as members of a Container.
     * 
     * @throws Exception
     *             Thrown if no or wrong exception is caught from the framework.
     */
    @Test
    public void testAddMember02() throws Exception {

        ContainerHandlerClient cc = new ContainerHandlerClient();
        cc.login(EscidocClientTestBase.DEFAULT_SERVICE_URL,
            Constants.SYSTEM_ADMIN_USER, Constants.SYSTEM_ADMIN_PASSWORD);

        // create Container
        Container container = createContainer(cc);

        // create Item
        Item item1 = createItem();
        Item item2 = createItem();
        Item item3 = createItem();
        Item item4 = createItem();
        Item item5 = createItem();
        Item item6 = createItem();

        // task oriented methods take values via task param
        TaskParam taskParam = new TaskParam();
        taskParam.setLastModificationDate(container.getLastModificationDate());
        taskParam.addResourceRef(item1.getObjid());
        taskParam.addResourceRef(item2.getObjid());
        taskParam.addResourceRef(item3.getObjid());
        taskParam.addResourceRef(item4.getObjid());
        taskParam.addResourceRef(item5.getObjid());
        taskParam.addResourceRef(item6.getObjid());

        // call addMembers method
        cc.addMembers(container.getObjid(), taskParam);

        Container updatedContainer = cc.retrieve(container.getObjid());

        assertEquals("Wrong member count", 6, updatedContainer
            .getMembers().size());

        /*
         * compare objids of members
         */

        // prepare values
        Collection<String> ids = new ArrayList<String>();
        Iterator<ResourceRef> it = updatedContainer.getMembers().iterator();
        while (it.hasNext()) {
            ids.add(it.next().getObjid());
        }

        assertTrue("Missing item 1", ids.contains(item1.getObjid()));
        assertTrue("Missing item 2", ids.contains(item2.getObjid()));
        assertTrue("Missing item 3", ids.contains(item3.getObjid()));
        assertTrue("Missing item 4", ids.contains(item4.getObjid()));
        assertTrue("Missing item 5", ids.contains(item5.getObjid()));
        assertTrue("Missing item 6", ids.contains(item6.getObjid()));
    }

    /**
     * Test to delete Item as members of a Container.
     * 
     * <p>
     * The test creates a Container, creates multiple Items and adds these Items
     * as members of the Container. Afterwards are two Items removed from the
     * Container.
     * </p>
     * 
     * @throws Exception
     *             Thrown if no or wrong exception is caught from the framework.
     */
    @Test
    public void testDeleteMember02() throws Exception {

        ContainerHandlerClient cc = new ContainerHandlerClient();
        cc.login(EscidocClientTestBase.DEFAULT_SERVICE_URL,
            Constants.SYSTEM_ADMIN_USER, Constants.SYSTEM_ADMIN_PASSWORD);

        // create Container
        Container container = createContainer(cc);

        // create Item
        Item item1 = createItem();
        Item item2 = createItem();
        Item item3 = createItem();
        Item item4 = createItem();
        Item item5 = createItem();
        Item item6 = createItem();

        // task oriented methods take values via task param
        TaskParam taskParam = new TaskParam();
        taskParam.setLastModificationDate(container.getLastModificationDate());
        taskParam.addResourceRef(item1.getObjid());
        taskParam.addResourceRef(item2.getObjid());
        taskParam.addResourceRef(item3.getObjid());
        taskParam.addResourceRef(item4.getObjid());
        taskParam.addResourceRef(item5.getObjid());
        taskParam.addResourceRef(item6.getObjid());

        // call addMembers method
        cc.addMembers(container.getObjid(), taskParam);

        Container updatedContainer = cc.retrieve(container.getObjid());

        assertEquals("Wrong member count", 6, updatedContainer
            .getMembers().size());

        /*
         * compare objids of members
         */

        // prepare values
        Collection<String> ids = new ArrayList<String>();
        Iterator<ResourceRef> it = updatedContainer.getMembers().iterator();
        while (it.hasNext()) {
            ids.add(it.next().getObjid());
        }

        assertTrue("Missing item 1", ids.contains(item1.getObjid()));
        assertTrue("Missing item 2", ids.contains(item2.getObjid()));
        assertTrue("Missing item 3", ids.contains(item3.getObjid()));
        assertTrue("Missing item 4", ids.contains(item4.getObjid()));
        assertTrue("Missing item 5", ids.contains(item5.getObjid()));
        assertTrue("Missing item 6", ids.contains(item6.getObjid()));

        /*
         * Check remove of members
         */

        // task oriented methods take values via task param
        TaskParam taskParam2 = new TaskParam();
        taskParam2.setLastModificationDate(updatedContainer
            .getLastModificationDate());
        taskParam2.addResourceRef(item3.getObjid());
        taskParam2.addResourceRef(item6.getObjid());

        // remove members
        cc.removeMembers(container.getObjid(), taskParam2);

        Container updatedContainer2 = cc.retrieve(container.getObjid());

        assertEquals("Wrong member count", 4, updatedContainer2
            .getMembers().size());

        /*
         * compare objids of members
         */

        // prepare values
        Collection<String> ids2 = new ArrayList<String>();
        Iterator<ResourceRef> it2 = updatedContainer2.getMembers().iterator();
        while (it2.hasNext()) {
            ids2.add(it2.next().getObjid());
        }

        assertTrue("Missing item 1", ids2.contains(item1.getObjid()));
        assertTrue("Missing item 2", ids2.contains(item2.getObjid()));
        assertFalse("Missing item 3", ids2.contains(item3.getObjid()));
        assertTrue("Missing item 4", ids2.contains(item4.getObjid()));
        assertTrue("Missing item 5", ids2.contains(item5.getObjid()));
        assertFalse("Missing item 6", ids2.contains(item6.getObjid()));

    }

    /**
     * Create a Container at infrastructure (with default values).
     * 
     * @param cc
     *            ContainerHandler
     * @return created Container
     * 
     * @throws ParserConfigurationException
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    private Container createContainer(final ContainerHandlerClient cc)
        throws ParserConfigurationException, EscidocException,
        InternalClientException, TransportException {

        Container container = new Container();

        // properties
        ContainerProperties properties = new ContainerProperties();
        properties.setContext(new ResourceRef(Constants.EXAMPLE_CONTEXT_ID));
        properties.setContentModel(new ResourceRef(
            Constants.EXAMPLE_CONTENT_MODEL_ID));
        container.setProperties(properties);

        // Content-model-specific
        ContentModelSpecific cms = getContentModelSpecific();
        container.getProperties().setContentModelSpecific(cms);

        // Metadata Record(s)
        MetadataRecords mdRecords = new MetadataRecords();
        MetadataRecord mdrecord = getMdRecord("escidoc");
        mdRecords.add(mdrecord);
        container.setMetadataRecords(mdRecords);

        return cc.create(container);
    }

    /**
     * Create Item at infrastructure (with default values).
     * 
     * @return created Item
     * 
     * @throws ParserConfigurationException
     * @throws InternalClientException
     * @throws EscidocException
     * @throws TransportException
     */
    private Item createItem() throws ParserConfigurationException,
        InternalClientException, EscidocException, TransportException {

        Item item = new Item();

        item.getProperties().setContext(
            new ResourceRef(Constants.EXAMPLE_CONTEXT_ID));
        item.getProperties().setContentModel(
            new ResourceRef(Constants.EXAMPLE_CONTENT_MODEL_ID));

        // Content-model
        ContentModelSpecific cms = getContentModelSpecific();
        item.getProperties().setContentModelSpecific(cms);

        // Metadata Record(s)
        MetadataRecords mdRecords = new MetadataRecords();
        MetadataRecord mdrecord = getMdRecord("escidoc");
        mdRecords.add(mdrecord);
        item.setMetadataRecords(mdRecords);

        // login
        ItemHandlerClient ihc = new ItemHandlerClient();
        ihc.login(EscidocClientTestBase.DEFAULT_SERVICE_URL,
            Constants.SYSTEM_ADMIN_USER, Constants.SYSTEM_ADMIN_PASSWORD);

        // create
        Item newItem = ihc.create(item);

        return newItem;
    }

    /**
     * Get content model specific.
     * 
     * @return
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

    /**
     * Get MetadataRecord.
     * 
     * @param name
     *            Name of md-record.
     * @return MetadataRecord
     * @throws ParserConfigurationException
     */
    private static MetadataRecord getMdRecord(final String name)
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

}
