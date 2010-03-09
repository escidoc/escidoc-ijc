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
package de.escidoc.core.test.client.classMapping.om.container;

import java.util.LinkedList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import junit.framework.TestCase;

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
import de.escidoc.core.test.client.EscidocClientTestBase;

/**
 * Test add and delete members of Container.
 * 
 * @author SWA
 * 
 */
public class MemberTest extends TestCase {

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
     * Test to add an Item as member of a Container.
     * 
     * @throws Exception
     *             Thrown if no or wrong exception is caught from the framework.
     */
    @Test
    public void testAddMember01() throws Exception {

        ContainerHandlerClient cc = new ContainerHandlerClient();
        cc.setHandle(EscidocClientTestBase.DEFAULT_HANDLE);

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

    }

    private Container createContainer(final ContainerHandlerClient cc)
        throws ParserConfigurationException, EscidocException,
        InternalClientException, TransportException {

        Container container = new Container();

        // properties
        ContainerProperties properties = new ContainerProperties();
        properties.setContext(new ResourceRef(
            EscidocClientTestBase.EXAMPLE_CONTEXT_ID));
        properties.setContentModel(new ResourceRef(
            EscidocClientTestBase.EXAMPLE_CONTENT_MODEL_ID));
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

    private Item createItem() throws ParserConfigurationException,
        InternalClientException, EscidocException, TransportException {

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

        // login
        ItemHandlerClient ihc = new ItemHandlerClient();
        ihc.setHandle(EscidocClientTestBase.DEFAULT_HANDLE);

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
