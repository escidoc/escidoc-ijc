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
package de.escidoc.core.test.client.marshaller.om.container;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.Iterator;

import org.apache.xpath.XPathAPI;
import org.joda.time.DateTime;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import de.escidoc.core.common.XmlUtility;
import de.escidoc.core.common.jibx.Factory;
import de.escidoc.core.resources.ResourceRef;
import de.escidoc.core.resources.common.structmap.ContainerRef;
import de.escidoc.core.resources.common.structmap.MemberRef;
import de.escidoc.core.resources.common.structmap.StructMap;
import de.escidoc.core.resources.om.container.Container;
import de.escidoc.core.resources.om.container.ContainerProperties;
import de.escidoc.core.test.client.EscidocClientTestBase;

/**
 * Test marshalling/unmarshalling of Containers with mockups.
 * 
 * @author SWA
 * 
 */
public class ContainerMarshallerTest {

    /**
     * Test unmarshalling of Container.
     * 
     * @throws Exception
     *             Thrown if no or wrong exception is caught from the framework.
     */
    @Test
    public void unmarshalling01() throws Exception {

        File templContainer =
            new File(
                "./templates/mockups/soap/om/container/0.8/container01.xml");
        String containerXml =
            EscidocClientTestBase.getXmlFileAsString(templContainer);

        Container container =
            Factory.getContainerMarshaller().unmarshalDocument(containerXml);

        assertEquals("Wrong objid", "escidoc:157513", container.getObjid());
        assertEquals("Wrong last modification date", new DateTime(
            "2010-06-08T16:24:51.875Z"), container.getLastModificationDate());
        assertEquals("Wrong creation date", new DateTime(
            "2010-06-08T16:24:45.679Z"), container
            .getProperties().getCreationDate());

        assertEquals("Wrong created-by", "escidoc:exuser1", container
            .getProperties().getCreatedBy().getObjid());
        assertEquals("Wrong public-status", "pending", container
            .getProperties().getPublicStatus());
        assertEquals("Wrong public-status comment",
            "Object escidoc:157513 created.", container
                .getProperties().getPublicStatusComment());
        assertEquals("Wrong name", "Container escidoc:157513", container
            .getProperties().getName());

        assertEquals("Wrong Context", "escidoc:ex1", container
            .getProperties().getContext().getObjid());
        assertEquals("Wrong Content Model", "escidoc:ex4", container
            .getProperties().getContentModel().getObjid());

        assertEquals("Wrong lock-status", "unlocked", container
            .getProperties().getLockStatus());

        // version
        assertEquals("Wrong version number", "2", container
            .getProperties().getVersion().getNumber());
        assertEquals("Wrong version date", new DateTime(
            "2010-06-08T16:24:51.875Z"), container
            .getProperties().getVersion().getDate());
        assertEquals("Wrong version status", "pending", container
            .getProperties().getVersion().getStatus());
        assertEquals("Wrong modified-by", "escidoc:exuser1", container
            .getProperties().getVersion().getModifiedBy().getObjid());
        assertEquals("Wrong version comment", "Container.addMembers", container
            .getProperties().getVersion().getComment());

        // latest-version
        assertEquals("Wrong latest-version number", "2", container
            .getProperties().getLatestVersion().getNumber());
        assertEquals("Wrong latest-version date", new DateTime(
            "2010-06-08T16:24:51.875Z"), container
            .getProperties().getLatestVersion().getDate());

        // md-records
        assertEquals("Wrong number of md-records", 1, container
            .getMetadataRecords().size());
        assertEquals("Wrong name of md-record", "escidoc", container
            .getMetadataRecords().element().getName());
        assertEquals("Wrong schema of md-record", null, container
            .getMetadataRecords().element().getSchema());
        // TODO validate md-record content

        assertEquals("Wrong number of member in struct-map", 6, container
            .getStructMap().size());

        // not sure if one can count on order of struct-map elements
        Iterator<MemberRef> it = container.getStructMap().iterator();

        assertEquals("Wrong struct-map member reference", "escidoc:157517", it
            .next().getObjid());
        assertEquals("Wrong struct-map member reference", "escidoc:157516", it
            .next().getObjid());
        assertEquals("Wrong struct-map member reference", "escidoc:157515", it
            .next().getObjid());
        assertEquals("Wrong struct-map member reference", "escidoc:157514", it
            .next().getObjid());
        assertEquals("Wrong struct-map member reference", "escidoc:157519", it
            .next().getObjid());
        assertEquals("Wrong struct-map member reference", "escidoc:157518", it
            .next().getObjid());

    }

    /**
     * Test unmarshalling a locked Container.
     * 
     * @throws Exception
     *             Thrown if no or wrong exception is caught from the framework.
     */
    @Test
    public void unmarshalling02() throws Exception {

        File templContainer =
            new File(
                "./templates/mockups/soap/om/container/0.8/container_locked01.xml");
        String containerXml =
            EscidocClientTestBase.getXmlFileAsString(templContainer);

        Container container =
            Factory.getContainerMarshaller().unmarshalDocument(containerXml);

        assertEquals("Wrong objid", "escidoc:157513", container.getObjid());
        assertEquals("Wrong last modification date", new DateTime(
            "2010-06-08T16:24:51.875Z"), container.getLastModificationDate());
        assertEquals("Wrong creation date", new DateTime(
            "2010-06-08T16:24:45.679Z"), container
            .getProperties().getCreationDate());

        assertEquals("Wrong created-by", "escidoc:exuser1", container
            .getProperties().getCreatedBy().getObjid());
        assertEquals("Wrong public-status", "pending", container
            .getProperties().getPublicStatus());
        assertEquals("Wrong public-status comment",
            "Object escidoc:157513 created.", container
                .getProperties().getPublicStatusComment());
        assertEquals("Wrong name", "Container escidoc:157513", container
            .getProperties().getName());

        assertEquals("Wrong Context", "escidoc:ex1", container
            .getProperties().getContext().getObjid());
        assertEquals("Wrong Content Model", "escidoc:ex4", container
            .getProperties().getContentModel().getObjid());

        assertEquals("Wrong lock-status", "locked", container
            .getProperties().getLockStatus());
        assertEquals("Wrong lock-date",
            new DateTime("2010-07-09T12:24:19.314Z"), container
                .getProperties().getLockDate());
        assertEquals("Wrong lock-owner", "escidoc:10001", container
            .getProperties().getLockOwner().getObjid());
        assertEquals("Wrong [object] pid", "hdl:someHandle/test/escidoc:12345",
            container.getProperties().getPid());

        // version
        assertEquals("Wrong version number", "2", container
            .getProperties().getVersion().getNumber());
        assertEquals("Wrong version date", new DateTime(
            "2010-06-08T16:24:51.875Z"), container
            .getProperties().getVersion().getDate());
        assertEquals("Wrong version status", "pending", container
            .getProperties().getVersion().getStatus());
        assertEquals("Wrong modified-by", "escidoc:exuser1", container
            .getProperties().getVersion().getModifiedBy().getObjid());
        assertEquals("Wrong version comment", "Container.addMembers", container
            .getProperties().getVersion().getComment());

        // latest-version
        assertEquals("Wrong latest-version number", "2", container
            .getProperties().getLatestVersion().getNumber());
        assertEquals("Wrong latest-version date", new DateTime(
            "2010-06-08T16:24:51.875Z"), container
            .getProperties().getLatestVersion().getDate());

        // md-records
        assertEquals("Wrong number of md-records", 1, container
            .getMetadataRecords().size());
        assertEquals("Wrong name of md-record", "escidoc", container
            .getMetadataRecords().element().getName());
        assertEquals("Wrong schema of md-record", null, container
            .getMetadataRecords().element().getSchema());
        // TODO validate md-record content

        assertEquals("Wrong number of member in struct-map", 6, container
            .getStructMap().size());

        // not sure if one can count on order of struct-map elements
        Iterator<MemberRef> it = container.getStructMap().iterator();

        assertEquals("Wrong struct-map member reference", "escidoc:157517", it
            .next().getObjid());
        assertEquals("Wrong struct-map member reference", "escidoc:157516", it
            .next().getObjid());
        assertEquals("Wrong struct-map member reference", "escidoc:157515", it
            .next().getObjid());
        assertEquals("Wrong struct-map member reference", "escidoc:157514", it
            .next().getObjid());
        assertEquals("Wrong struct-map member reference", "escidoc:157519", it
            .next().getObjid());
        assertEquals("Wrong struct-map member reference", "escidoc:157518", it
            .next().getObjid());

    }

    /**
     * Test marshalling a a Container where user has added members directly.
     * (issue CLIB-44).
     * 
     * @throws Exception
     *             Thrown if no or wrong exception is caught from the framework.
     */
    @Test
    public void marshallingIssueCLib44() throws Exception {

        final String contextId = "escidoc:context";
        final String contentModelId = "escidoc:contentmodel";
        final String description = "description";
        final String member1 = "escidoc:member1";

        Container container = new Container();
        ContainerProperties cp = new ContainerProperties();
        cp.setContext(new ResourceRef(contextId));
        cp.setContentModel(new ResourceRef(contentModelId));
        cp.setDescription(description);
        container.setProperties(cp);

        StructMap structMap = new StructMap();
        structMap.add(new ContainerRef(member1));
        container.setStructMap(structMap);

        String containerXml =
            Factory.getContainerMarshaller().marshalDocument(container);

        Document containerDoc = XmlUtility.getDocument(containerXml);
        assertEquals(
            "Wrong Context reference",
            contextId,
            XPathAPI.selectSingleNode(containerDoc,
                "/container/properties/context/@objid").getTextContent());
        assertEquals(
            "Wrong content model reference",
            contentModelId,
            XPathAPI.selectSingleNode(containerDoc,
                "/container/properties/content-model/@objid").getTextContent());
        assertEquals(
            "Wrong description",
            description,
            XPathAPI.selectSingleNode(containerDoc,
                "/container/properties/description").getTextContent());

        NodeList structMapNodes =
            containerDoc.getElementsByTagName("struct-map:struct-map");
        assertTrue("Wrong number of members", structMapNodes.getLength() == 1);
        assertEquals(
            "Wrong member in struct map",
            member1,
            XPathAPI.selectSingleNode(containerDoc,
                "/container/struct-map/container/@objid").getTextContent());

    }
}
