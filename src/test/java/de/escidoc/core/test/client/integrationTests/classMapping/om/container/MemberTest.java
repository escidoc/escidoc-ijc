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

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import de.escidoc.core.client.Authentication;
import de.escidoc.core.client.ContainerHandlerClient;
import de.escidoc.core.client.ItemHandlerClient;
import de.escidoc.core.client.exceptions.EscidocClientException;
import de.escidoc.core.client.exceptions.EscidocException;
import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.client.exceptions.TransportException;
import de.escidoc.core.client.interfaces.ContainerHandlerClientInterface;
import de.escidoc.core.client.interfaces.ItemHandlerClientInterface;
import de.escidoc.core.resources.common.MetadataRecord;
import de.escidoc.core.resources.common.MetadataRecords;
import de.escidoc.core.resources.common.TaskParam;
import de.escidoc.core.resources.common.reference.ContentModelRef;
import de.escidoc.core.resources.common.reference.ContextRef;
import de.escidoc.core.resources.common.structmap.MemberRef;
import de.escidoc.core.resources.om.container.Container;
import de.escidoc.core.resources.om.container.ContainerProperties;
import de.escidoc.core.resources.om.item.Item;
import de.escidoc.core.test.client.EscidocClientTestBase;

/**
 * Test add and delete members of Container.
 * 
 * @author SWA
 * 
 */
public class MemberTest {

    private Authentication auth;

    private ContainerHandlerClientInterface cc;

    @Before
    public void init() throws Exception {
        auth =
            new Authentication(EscidocClientTestBase.getDefaultInfrastructureURL(),
                EscidocClientTestBase.SYSTEM_ADMIN_USER, EscidocClientTestBase.SYSTEM_ADMIN_PASSWORD);
        cc = new ContainerHandlerClient(auth.getServiceAddress());
        cc.setHandle(auth.getHandle());
    }

    @After
    public void post() throws Exception {
        if (auth != null)
            auth.logout();
    }

    /**
     * Test to add an Item as member of a Container.
     * 
     * @throws Exception
     *             Thrown if no or wrong exception is caught from the framework.
     */
    @Test
    public void addMember01() throws Exception {
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

        assertEquals("Wrong member count", 1, updatedContainer.getStructMap().size());

        Iterator<MemberRef> it = updatedContainer.getStructMap().iterator();
        assertEquals("Wrong member ref", item.getObjid(), it.next().getObjid());
    }

    /**
     * Test to add multiple Item as members of a Container.
     * 
     * @throws Exception
     *             Thrown if no or wrong exception is caught from the framework.
     */
    @Test
    public void addMember02() throws Exception {
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

        assertEquals("Wrong member count", 6, updatedContainer.getStructMap().size());

        /*
         * compare objids of members
         */

        // prepare values
        Collection<String> ids = new ArrayList<String>();
        Iterator<MemberRef> it = updatedContainer.getStructMap().iterator();
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
    public void deleteMember02() throws Exception {
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

        assertEquals("Wrong member count", 6, updatedContainer.getStructMap().size());

        /*
         * compare objids of members
         */

        // prepare values
        Collection<String> ids = new ArrayList<String>();
        Iterator<MemberRef> it = updatedContainer.getStructMap().iterator();
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
        taskParam2.setLastModificationDate(updatedContainer.getLastModificationDate());
        taskParam2.addResourceRef(item3.getObjid());
        taskParam2.addResourceRef(item6.getObjid());

        // remove members
        cc.removeMembers(container.getObjid(), taskParam2);

        Container updatedContainer2 = cc.retrieve(container.getObjid());

        assertEquals("Wrong member count", 4, updatedContainer2.getStructMap().size());

        /*
         * compare objids of members
         */

        // prepare values
        Collection<String> ids2 = new ArrayList<String>();
        Iterator<MemberRef> it2 = updatedContainer2.getStructMap().iterator();
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
     * @throws EscidocClientException
     */
    private Container createContainer(final ContainerHandlerClientInterface cc) throws ParserConfigurationException,
        EscidocClientException {

        Container container = new Container();

        // properties
        ContainerProperties properties = new ContainerProperties();
        ContextRef cRef = new ContextRef(EscidocClientTestBase.getStaticContextId());
        properties.setContext(cRef);
        ContentModelRef cmRef = new ContentModelRef(EscidocClientTestBase.getStaticContentModelId());
        properties.setContentModel(cmRef);
        container.setProperties(properties);

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
    private Item createItem() throws ParserConfigurationException, InternalClientException, EscidocException,
        TransportException {

        Item item = new Item();

        item.getProperties().setContext(new ContextRef(EscidocClientTestBase.getStaticContextId()));
        item.getProperties().setContentModel(new ContentModelRef(EscidocClientTestBase.getStaticContentModelId()));

        // Metadata Record(s)
        MetadataRecords mdRecords = new MetadataRecords();
        MetadataRecord mdrecord = getMdRecord("escidoc");
        mdRecords.add(mdrecord);
        item.setMetadataRecords(mdRecords);

        // login
        ItemHandlerClientInterface ihc = new ItemHandlerClient(auth.getServiceAddress());
        ihc.setHandle(auth.getHandle());

        // create
        Item newItem = ihc.create(item);

        return newItem;
    }

    /**
     * Get MetadataRecord.
     * 
     * @param name
     *            Name of md-record.
     * @return MetadataRecord
     * @throws ParserConfigurationException
     */
    private static MetadataRecord getMdRecord(final String name) throws ParserConfigurationException {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.newDocument();

        MetadataRecord mdRecord = new MetadataRecord(name);

        Element element = doc.createElementNS(null, "myMdRecord");
        mdRecord.setContent(element);

        return mdRecord;
    }

}
