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
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.apache.xpath.XPathAPI;
import org.joda.time.DateTime;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import de.escidoc.core.client.TransportProtocol;
import de.escidoc.core.common.XmlUtility;
import de.escidoc.core.common.jibx.Factory;
import de.escidoc.core.resources.common.reference.ContentModelRef;
import de.escidoc.core.resources.common.reference.ContextRef;
import de.escidoc.core.resources.common.structmap.ContainerMemberRef;
import de.escidoc.core.resources.common.structmap.MemberRef;
import de.escidoc.core.resources.common.structmap.StructMap;
import de.escidoc.core.resources.om.container.Container;
import de.escidoc.core.resources.om.container.ContainerProperties;
import de.escidoc.core.test.client.AbstractParameterizedTestBase;
import de.escidoc.core.test.client.EscidocClientTestBase;

/**
 * Test marshalling/unmarshalling of Containers with mockups.
 * 
 * @author SWA
 * 
 */
public class ContainerMarshallerTest extends AbstractParameterizedTestBase {

    public ContainerMarshallerTest(TransportProtocol transport) {
        super(transport);
    }

    /**
     * Test unmarshalling of Container.
     * 
     * @throws Exception
     *             Thrown if no or wrong exception is caught from the framework.
     */
    @Test
    public void unmarshalling01() throws Exception {

        File templContainer =
            new File("./templates/mockups/" + transport.name().toLowerCase()
                + "/om/container/0.8/container01.xml");
        String containerXml =
            EscidocClientTestBase.getXmlFileAsString(templContainer);

        Container container =
            Factory
                .getMarshallerFactory(transport).getContainerMarshaller()
                .unmarshalDocument(containerXml);

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
    public void unmarshallingLocked() throws Exception {

        File templContainer =
            new File("./templates/mockups/" + transport.name().toLowerCase()
                + "/om/container/0.8/container_locked01.xml");
        String containerXml =
            EscidocClientTestBase.getXmlFileAsString(templContainer);

        Container container =
            Factory
                .getMarshallerFactory(transport).getContainerMarshaller()
                .unmarshalDocument(containerXml);

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
     * Test unmarshalling a Container with one container in struct-map.
     * 
     * @throws Exception
     *             Thrown if no or wrong exception is caught from the framework.
     */
    @Test
    public void unmarshallingWithContainerInStructMap() throws Exception {

        File templContainer =
            new File("./templates/mockups/" + transport.name().toLowerCase()
                + "/om/container/0.8/container_with_container_member.xml");
        String containerXml =
            EscidocClientTestBase.getXmlFileAsString(templContainer);

        Container container =
            Factory
                .getMarshallerFactory(transport).getContainerMarshaller()
                .unmarshalDocument(containerXml);

        assertEquals("Wrong objid", "escidoc:188602", container.getObjid());
        assertEquals("Wrong last modification date", new DateTime(
            "2010-07-08T14:22:25.199Z"), container.getLastModificationDate());
        assertEquals("Wrong creation date", new DateTime(
            "2010-07-08T14:22:25.199Z"), container
            .getProperties().getCreationDate());

        assertEquals("Wrong created-by", "escidoc:exuser1", container
            .getProperties().getCreatedBy().getObjid());
        assertEquals("Wrong public-status", "pending", container
            .getProperties().getPublicStatus());
        assertEquals("Wrong public-status comment",
            "Object escidoc:188602 created.", container
                .getProperties().getPublicStatusComment());
        assertEquals("Wrong name", "Container escidoc:188602", container
            .getProperties().getName());

        assertEquals("Wrong Context", "escidoc:ex1", container
            .getProperties().getContext().getObjid());
        assertEquals("Wrong Content Model", "escidoc:ex4", container
            .getProperties().getContentModel().getObjid());

        assertEquals("Wrong lock-status", "unlocked", container
            .getProperties().getLockStatus());
        assertTrue("Wrong lock-date",
            container.getProperties().getLockDate() == null);
        assertTrue("Wrong lock-owner",
            container.getProperties().getLockOwner() == null);
        assertTrue("Wrong [object] pid",
            container.getProperties().getPid() == null);

        // version
        assertEquals("Wrong version number", "1", container
            .getProperties().getVersion().getNumber());
        assertEquals("Wrong version date", new DateTime(
            "2010-07-08T14:22:25.199Z"), container
            .getProperties().getVersion().getDate());
        assertEquals("Wrong version status", "pending", container
            .getProperties().getVersion().getStatus());
        assertEquals("Wrong modified-by", "escidoc:exuser1", container
            .getProperties().getVersion().getModifiedBy().getObjid());
        assertEquals("Wrong version comment", "Object escidoc:188602 created.",
            container.getProperties().getVersion().getComment());

        // latest-version
        assertEquals("Wrong latest-version number", "1", container
            .getProperties().getLatestVersion().getNumber());
        assertEquals("Wrong latest-version date", new DateTime(
            "2010-07-08T14:22:25.199Z"), container
            .getProperties().getLatestVersion().getDate());

        // md-records
        assertEquals("Wrong number of md-records", 3, container
            .getMetadataRecords().size());
        // TODO validate md-record content

        assertEquals("Wrong number of member in struct-map", 3, container
            .getStructMap().size());

        // not sure if one can count on order of struct-map elements
        Iterator<MemberRef> it = container.getStructMap().iterator();
        Set<String> memberIds = new HashSet<String>();
        while (it.hasNext()) {
            MemberRef m = it.next();
            memberIds.add(m.getObjid());
        }

        assertTrue("Wrong struct-map member reference",
            memberIds.contains("escidoc:198576"));
        assertTrue("Wrong struct-map member reference",
            memberIds.contains("escidoc:188601"));
        assertTrue("Wrong struct-map member reference",
            memberIds.contains("escidoc:198577"));
    }

    /**
     * Test marshalling a a Container where user has added members directly.
     * (issue CLIB-44).
     * 
     * @throws Exception
     *             Thrown if no or wrong exception is caught from the framework.
     */
    @Test
    public void marshallingWithDirectAddedStrucutMapMembers() throws Exception {

        final String contextId = "escidoc:context";
        final String contentModelId = "escidoc:contentmodel";
        final String description = "description";
        final String member1 = "escidoc:member1";

        Container container = new Container();
        ContainerProperties cp = new ContainerProperties();
        cp.setContext(new ContextRef(contextId));
        cp.setContentModel(new ContentModelRef(contentModelId));
        cp.setDescription(description);
        container.setProperties(cp);

        StructMap structMap = new StructMap();
        structMap.add(new ContainerMemberRef(member1));
        container.setStructMap(structMap);

        String containerXml =
            Factory
                .getMarshallerFactory(transport).getContainerMarshaller()
                .marshalDocument(container);

        Document containerDoc = XmlUtility.getDocument(containerXml);

        assertEquals("Wrong Context reference", contextId,
            EscidocClientTestBase.obtainObjidByXPath(
                "/container/properties/context", containerDoc));
        assertEquals("Wrong content model reference", contentModelId,
            EscidocClientTestBase.obtainObjidByXPath(
                "/container/properties/content-model", containerDoc));
        assertEquals(
            "Wrong description",
            description,
            XPathAPI.selectSingleNode(containerDoc,
                "/container/properties/description").getTextContent());

        NodeList structMapNodes =
            containerDoc.getElementsByTagName("struct-map:struct-map");
        assertTrue("Wrong number of members", structMapNodes.getLength() == 1);
        assertEquals("Wrong member in struct map", member1,
            EscidocClientTestBase.obtainObjidByXPath(
                "/container/struct-map/container", containerDoc));
    }
}
