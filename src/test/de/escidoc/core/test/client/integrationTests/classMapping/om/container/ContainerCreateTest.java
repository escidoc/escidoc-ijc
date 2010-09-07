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
import static org.junit.Assert.assertNotNull;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.xpath.XPathAPI;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import de.escidoc.core.client.Authentication;
import de.escidoc.core.client.ContainerHandlerClient;
import de.escidoc.core.client.exceptions.application.invalid.InvalidXmlException;
import de.escidoc.core.client.exceptions.application.invalid.XmlSchemaValidationException;
import de.escidoc.core.common.XmlUtility;
import de.escidoc.core.common.jibx.Marshaller;
import de.escidoc.core.resources.ResourceRef;
import de.escidoc.core.resources.common.MetadataRecord;
import de.escidoc.core.resources.common.MetadataRecords;
import de.escidoc.core.resources.common.Relation;
import de.escidoc.core.resources.common.Relations;
import de.escidoc.core.resources.common.properties.ContentModelSpecific;
import de.escidoc.core.resources.common.structmap.ContainerRef;
import de.escidoc.core.resources.common.structmap.MemberRef;
import de.escidoc.core.resources.common.structmap.StructMap;
import de.escidoc.core.resources.om.container.Container;
import de.escidoc.core.resources.om.container.ContainerProperties;
import de.escidoc.core.test.client.Constants;
import de.escidoc.core.test.client.EscidocClientTestBase;
import de.escidoc.core.test.client.util.Asserts;

/**
 * Test create Container.
 * 
 * @author SWA
 * 
 */
public class ContainerCreateTest {

    /**
     * Test if the right exception is thrown if calling create with an
     * incomplete Container.
     * 
     * @throws Exception
     *             Thrown if no or wrong exception is caught from the framework.
     */
    @Test(expected = XmlSchemaValidationException.class)
    public void testCreateContainer01() throws Exception {

        Authentication auth =
            new Authentication(EscidocClientTestBase.DEFAULT_SERVICE_URL,
                Constants.SYSTEM_ADMIN_USER, Constants.SYSTEM_ADMIN_PASSWORD);

        ContainerHandlerClient cc = new ContainerHandlerClient();
        cc.setServiceAddress(EscidocClientTestBase.DEFAULT_SERVICE_URL);
        cc.setHandle(auth.getHandle());

        Container container = new Container();
        cc.create(container);
    }

    /**
     * Test if the right exception is thrown if calling create with an
     * incomplete Container.
     * 
     * A title is set (but shouldn't have influence).
     * 
     * @throws Exception
     *             Thrown if no or wrong exception is caught from the framework.
     */
    @Test(expected = XmlSchemaValidationException.class)
    public void testCreateContainer02() throws Exception {

        Authentication auth =
            new Authentication(EscidocClientTestBase.DEFAULT_SERVICE_URL,
                Constants.SYSTEM_ADMIN_USER, Constants.SYSTEM_ADMIN_PASSWORD);

        ContainerHandlerClient cc = new ContainerHandlerClient();
        cc.setServiceAddress(EscidocClientTestBase.DEFAULT_SERVICE_URL);
        cc.setHandle(auth.getHandle());

        Container container = new Container();
        container.setXLinkTitle("New title for test");
        cc.create(container);
    }

    /**
     * Test if the right exception is thrown if calling create with an
     * incomplete Container.
     * 
     * @throws Exception
     *             Thrown if no or wrong exception is caught from the framework.
     */
    @Test(expected = XmlSchemaValidationException.class)
    public void testCreateContainer03() throws Exception {

        Authentication auth =
            new Authentication(EscidocClientTestBase.DEFAULT_SERVICE_URL,
                Constants.SYSTEM_ADMIN_USER, Constants.SYSTEM_ADMIN_PASSWORD);

        ContainerHandlerClient cc = new ContainerHandlerClient();
        cc.setServiceAddress(EscidocClientTestBase.DEFAULT_SERVICE_URL);
        cc.setHandle(auth.getHandle());

        Container container = new Container();
        ContainerProperties properties = new ContainerProperties();
        container.setProperties(properties);
        cc.create(container);
    }

    /**
     * Test if the right exception is thrown if calling create with an
     * incomplete Container.
     * 
     * @throws Exception
     *             Thrown if no or wrong exception is caught from the framework.
     */
    @Test(expected = XmlSchemaValidationException.class)
    public void testCreateContainer04() throws Exception {

        Authentication auth =
            new Authentication(EscidocClientTestBase.DEFAULT_SERVICE_URL,
                Constants.SYSTEM_ADMIN_USER, Constants.SYSTEM_ADMIN_PASSWORD);

        ContainerHandlerClient cc = new ContainerHandlerClient();
        cc.setServiceAddress(EscidocClientTestBase.DEFAULT_SERVICE_URL);
        cc.setHandle(auth.getHandle());

        Container container = new Container();
        ContainerProperties properties = new ContainerProperties();
        container.setProperties(properties);
        cc.create(container);
    }

    /**
     * Test if the right exception is thrown if calling create with an
     * incomplete Container.
     * 
     * @throws Exception
     *             Thrown if no or wrong exception is caught from the framework.
     */
    @Test(expected = XmlSchemaValidationException.class)
    public void testCreateContainer05() throws Exception {

        Authentication auth =
            new Authentication(EscidocClientTestBase.DEFAULT_SERVICE_URL,
                Constants.SYSTEM_ADMIN_USER, Constants.SYSTEM_ADMIN_PASSWORD);

        ContainerHandlerClient cc = new ContainerHandlerClient();
        cc.setServiceAddress(EscidocClientTestBase.DEFAULT_SERVICE_URL);
        cc.setHandle(auth.getHandle());

        Container container = new Container();
        ContainerProperties properties = new ContainerProperties();
        properties.setContext(new ResourceRef(Constants.EXAMPLE_CONTEXT_ID, 
        		ResourceRef.RESOURCE_TYPE.Context));
        container.setProperties(properties);
        cc.create(container);
    }

    /**
     * Test if the right exception is thrown if calling create with an
     * incomplete Container.
     * 
     * One invalid MetadataRecord (no name, no content) is part of Container.
     * 
     * @throws Exception
     *             Thrown if no or wrong exception is caught from the framework.
     */
    @Test(expected = XmlSchemaValidationException.class)
    public void testCreateContainer06() throws Exception {

        Authentication auth =
            new Authentication(EscidocClientTestBase.DEFAULT_SERVICE_URL,
                Constants.SYSTEM_ADMIN_USER, Constants.SYSTEM_ADMIN_PASSWORD);

        ContainerHandlerClient cc = new ContainerHandlerClient();
        cc.setServiceAddress(EscidocClientTestBase.DEFAULT_SERVICE_URL);
        cc.setHandle(auth.getHandle());

        Container container = new Container();
        ContainerProperties properties = new ContainerProperties();
        properties.setContext(new ResourceRef(Constants.EXAMPLE_CONTEXT_ID, ResourceRef.RESOURCE_TYPE.Context));
        container.setProperties(properties);
        MetadataRecords mdRecords = new MetadataRecords();
        MetadataRecord mdRecord = new MetadataRecord();
        mdRecords.add(mdRecord);
        container.setMetadataRecords(mdRecords);

        cc.create(container);
    }

    /**
     * Test if the right exception is thrown if calling create with an
     * incomplete Container.
     * 
     * One invalid MetadataRecord (no content) is part of Container.
     * 
     * @throws Exception
     *             Thrown if no or wrong exception is caught from the framework.
     */
    @Test(expected = XmlSchemaValidationException.class)
    public void testCreateContainer07() throws Exception {

        Authentication auth =
            new Authentication(EscidocClientTestBase.DEFAULT_SERVICE_URL,
                Constants.SYSTEM_ADMIN_USER, Constants.SYSTEM_ADMIN_PASSWORD);

        ContainerHandlerClient cc = new ContainerHandlerClient();
        cc.setServiceAddress(EscidocClientTestBase.DEFAULT_SERVICE_URL);
        cc.setHandle(auth.getHandle());

        Container container = new Container();
        ContainerProperties properties = new ContainerProperties();
        properties.setContext(new ResourceRef(Constants.EXAMPLE_CONTEXT_ID, ResourceRef.RESOURCE_TYPE.Context));
        container.setProperties(properties);
        MetadataRecords mdRecords = new MetadataRecords();
        MetadataRecord mdRecord = new MetadataRecord();
        mdRecord.setName("escidoc");
        mdRecords.add(mdRecord);
        container.setMetadataRecords(mdRecords);

        cc.create(container);
    }

    /**
     * Test if the right exception is thrown if calling create with an
     * incomplete Container.
     * <p>
     * At last content model is missing.
     * </p>
     * <p>
     * MetadataRecord has name and content. Relations is at least missing.
     * <p>
     * 
     * @throws Exception
     *             Thrown if no or wrong exception is caught from the framework.
     */
    @Test(expected = InvalidXmlException.class)
    public void testCreateContainer08() throws Exception {

        Authentication auth =
            new Authentication(EscidocClientTestBase.DEFAULT_SERVICE_URL,
                Constants.SYSTEM_ADMIN_USER, Constants.SYSTEM_ADMIN_PASSWORD);

        ContainerHandlerClient cc = new ContainerHandlerClient();
        cc.setServiceAddress(EscidocClientTestBase.DEFAULT_SERVICE_URL);
        cc.setHandle(auth.getHandle());

        Container container = new Container();

        // properties
        ContainerProperties properties = new ContainerProperties();
        properties.setContext(new ResourceRef(Constants.EXAMPLE_CONTEXT_ID, ResourceRef.RESOURCE_TYPE.Context));
        container.setProperties(properties);

        // md-record
        MetadataRecords mdRecords = new MetadataRecords();
        MetadataRecord mdRecord = new MetadataRecord();
        mdRecord.setName("escidoc");

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.newDocument();

        Element element = doc.createElementNS(null, "myMdRecord");

        mdRecord.setContent(element);

        mdRecords.add(mdRecord);
        container.setMetadataRecords(mdRecords);

        cc.create(container);
    }

    /**
     * Test successfully creation of a Container.
     * 
     * @throws Exception
     *             Thrown if no or wrong exception is caught from the framework.
     */
    @Test
    public void testCreateContainer09() throws Exception {

        Authentication auth =
            new Authentication(EscidocClientTestBase.DEFAULT_SERVICE_URL,
                Constants.SYSTEM_ADMIN_USER, Constants.SYSTEM_ADMIN_PASSWORD);

        ContainerHandlerClient cc = new ContainerHandlerClient();
        cc.setServiceAddress(EscidocClientTestBase.DEFAULT_SERVICE_URL);
        cc.setHandle(auth.getHandle());

        Container container = new Container();

        // properties
        ContainerProperties properties = new ContainerProperties();
        properties.setContext(new ResourceRef(Constants.EXAMPLE_CONTEXT_ID, ResourceRef.RESOURCE_TYPE.Context));
        properties.setContentModel(new ResourceRef(
            Constants.EXAMPLE_CONTENT_MODEL_ID, ResourceRef.RESOURCE_TYPE.ContentModel));

        // Content-model-specific
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.newDocument();
        Element contentModelSpecific = doc.createElementNS(null, "cms");

        List<Element> cmsContent = new LinkedList<Element>();
        cmsContent.add(contentModelSpecific);
        ContentModelSpecific cms = new ContentModelSpecific();

        cms.setContent(cmsContent);

        properties.setContentModelSpecific(cms);
        container.setProperties(properties);

        MetadataRecords mdRecords = new MetadataRecords();
        MetadataRecord mdRecord = new MetadataRecord();
        mdRecord.setName("escidoc");

        Document docMdRecord = builder.newDocument();
        Element element = docMdRecord.createElementNS(null, "myMdRecord");
        mdRecord.setContent(element);

        mdRecords.add(mdRecord);
        container.setMetadataRecords(mdRecords);
     
        Container createdContainer = cc.create(container);

        // compare the new created Container with the Container from the
        // request
        String objId = createdContainer.getObjid();

        assertNotNull("Object id is null", objId);
        assertEquals(container.getProperties().getContext().getObjid(),
            createdContainer.getProperties().getContext().getObjid());
        assertEquals(container.getProperties().getContentModel().getObjid(),
            createdContainer.getProperties().getContentModel().getObjid());

        Marshaller<MetadataRecord> m1 =
            new Marshaller<MetadataRecord>(container.getMetadataRecords().get(
                "escidoc").getClass(), cc.getTransport());
        
        String xml1 = m1.marshalDocument(container.getMetadataRecords().get("escidoc"));

        Document mdRecordBeforeCreate = XmlUtility.getDocument(xml1);
        Node mdRecordBeforeCreateNode =
            XPathAPI.selectSingleNode(mdRecordBeforeCreate, "/md-record");
        Node mdRecordBeforeCreateContent =
            mdRecordBeforeCreateNode.getFirstChild();

        MetadataRecord createdContainerMdRecord =
            createdContainer.getMetadataRecords().get("escidoc");

        Marshaller<MetadataRecord> m2 =
            new Marshaller<MetadataRecord>(createdContainerMdRecord.getClass());
        String xml2 = m2.marshalDocument(createdContainerMdRecord);

        Document mdRecordAfterCreate = XmlUtility.getDocument(xml2);
        Node mdRecordAfterCreateNode =
            XPathAPI.selectSingleNode(mdRecordAfterCreate, "/md-record");
        Node mdRecordAfterCreateContent =
            mdRecordAfterCreateNode.getFirstChild();

        Asserts.assertXmlEquals("Metadata Records differ",
            mdRecordAfterCreateContent, mdRecordBeforeCreateContent);

    }

    /**
     * Test the Container with relations.
     * 
     * Valid minimal Container with relation.
     * 
     * @throws Exception
     *             Thrown if no or wrong exception is caught from the framework.
     */
    @Test
    public void testCreateContainer10() throws Exception {

        Authentication auth =
            new Authentication(EscidocClientTestBase.DEFAULT_SERVICE_URL,
                Constants.SYSTEM_ADMIN_USER, Constants.SYSTEM_ADMIN_PASSWORD);

        ContainerHandlerClient cc = new ContainerHandlerClient();
        cc.setServiceAddress(EscidocClientTestBase.DEFAULT_SERVICE_URL);
        cc.setHandle(auth.getHandle());

        Container container = createContainerWithMinContent();
        Relations relations = new Relations();
        Relation relation = new Relation(
        		new ResourceRef(Constants.EXAMPLE_ITEM_ID, ResourceRef.RESOURCE_TYPE.Item));
        relation
            .setPredicate("http://www.escidoc.de/ontologies/mpdl-ontologies/content-relations#isPartOf");
        relations.add(relation);
        container.setRelations(relations);

        Container createdContainer = cc.create(container);

        // test marshalling created Container.
        Marshaller<Container> c1 =
            new Marshaller<Container>(createdContainer.getClass(), cc.getTransport());
        
        String xmlc1 = c1.marshalDocument(createdContainer);

        // compare the new created Container with the Container from the
        // request
        String objId = createdContainer.getObjid();
        assertNotNull("Object id is null", objId);
        assertEquals(container.getProperties().getContext().getObjid(),
            createdContainer.getProperties().getContext().getObjid());
        assertEquals(container.getProperties().getContentModel().getObjid(),
            createdContainer.getProperties().getContentModel().getObjid());

        // MetadataRecords

        Marshaller<MetadataRecord> m1 =
            new Marshaller<MetadataRecord>(container.getMetadataRecords().get(
                "escidoc").getClass(), cc.getTransport());
        
        String xml1 = m1.marshalDocument(container.getMetadataRecords().get("escidoc"));

        Document mdRecordBeforeCreate = XmlUtility.getDocument(xml1);
        Node mdRecordBeforeCreateNode =
            XPathAPI.selectSingleNode(mdRecordBeforeCreate, "/md-record");
        Node mdRecordBeforeCreateContent =
            mdRecordBeforeCreateNode.getFirstChild();

        MetadataRecord createdContainerMdRecord =
            createdContainer.getMetadataRecords().get("escidoc");

        Marshaller<MetadataRecord> m2 =
            new Marshaller<MetadataRecord>(createdContainerMdRecord.getClass(),
            		cc.getTransport());
        
        String xml2 = m2.marshalDocument(createdContainerMdRecord);

        Document mdRecordAfterCreate = XmlUtility.getDocument(xml2);
        Node mdRecordAfterCreateNode =
            XPathAPI.selectSingleNode(mdRecordAfterCreate, "/md-record");
        Node mdRecordAfterCreateContent =
            mdRecordAfterCreateNode.getFirstChild();

        Asserts.assertXmlEquals("Metadata Records differ",
            mdRecordAfterCreateContent, mdRecordBeforeCreateContent);

        // Relations
        Relations masterRelations = container.getRelations();
        Relations compareRelations = createdContainer.getRelations();

        assertEquals("Number of Relations differ", masterRelations.size(),
            compareRelations.size());
        assertEquals("Missing Relations", 1, masterRelations.size());
        Relations retrievedRelations = cc.retrieveRelations(objId);
        assertEquals("Number of Relations differ", retrievedRelations.size(),
            compareRelations.size());
    }

    /**
     * Test the Container with members.
     * 
     * Valid minimal Container with members.
     * 
     * @throws Exception
     *             Thrown if no or wrong exception is caught from the framework.
     */
    @Test
    public void testCreateContainerWithOneMember() throws Exception {

        Authentication auth =
            new Authentication(EscidocClientTestBase.DEFAULT_SERVICE_URL,
                Constants.SYSTEM_ADMIN_USER, Constants.SYSTEM_ADMIN_PASSWORD);

        ContainerHandlerClient cc = new ContainerHandlerClient();
        cc.setServiceAddress(EscidocClientTestBase.DEFAULT_SERVICE_URL);
        cc.setHandle(auth.getHandle());

        Container container = createContainerWithMinContent();
        Container memberContainer = createContainerWithMinContent();

        Container createdMemberContainer = cc.create(memberContainer);
        String memberId = createdMemberContainer.getObjid();
        StructMap structMap = new StructMap();
        ContainerRef member = new ContainerRef(memberId, ResourceRef.RESOURCE_TYPE.Container);
        structMap.add(member);
        container.setStructMap(structMap);
        Container createdContainer = cc.create(container);
        Marshaller<Container> c1 =
            new Marshaller<Container>(createdContainer.getClass(), cc.getTransport());
        c1.marshalDocument(createdContainer);

        StructMap createdStructMap = createdContainer.getStructMap();
        assertEquals("Number of members is wrong", 1, createdStructMap.size());
        Iterator<MemberRef> iterator = createdStructMap.iterator();
        while (iterator.hasNext()) {
            ResourceRef memberResource = iterator.next();
            assertEquals("member is the wrong resource type", memberResource
                .getClass(), member.getClass());

            assertEquals("member has a wrong id", memberResource.getObjid(),
                memberId);

        }

    }

    /**
     * Test if values of requested Container compares to the values of the
     * response.
     * 
     * @throws Exception
     *             Thrown if creation failed or non-volatile Container values
     *             differ.
     */
    @Test
    public void testCreateContainer20() throws Exception {

        Authentication auth =
            new Authentication(EscidocClientTestBase.DEFAULT_SERVICE_URL,
                Constants.SYSTEM_ADMIN_USER, Constants.SYSTEM_ADMIN_PASSWORD);

        ContainerHandlerClient cc = new ContainerHandlerClient();
        cc.setServiceAddress(EscidocClientTestBase.DEFAULT_SERVICE_URL);
        cc.setHandle(auth.getHandle());

        Container container = createContainerWithMinContent();
        Container createdContainer = cc.create(container);

        // compare the new created Container with the Container from the
        // request
        String objId = createdContainer.getObjid();
        assertNotNull("Object id is null", objId);
        assertEquals(container.getProperties().getContext().getObjid(),
            createdContainer.getProperties().getContext().getObjid());
        assertEquals(container.getProperties().getContentModel().getObjid(),
            createdContainer.getProperties().getContentModel().getObjid());

        Marshaller<MetadataRecord> m1 =
            new Marshaller<MetadataRecord>(container.getMetadataRecords().get(
                "escidoc").getClass(), cc.getTransport());
        String xml1 =
            m1.marshalDocument(container.getMetadataRecords().get("escidoc"));

        Document mdRecordBeforeCreate = XmlUtility.getDocument(xml1);
        Node mdRecordBeforeCreateNode =
            XPathAPI.selectSingleNode(mdRecordBeforeCreate, "/md-record");
        Node mdRecordBeforeCreateContent =
            mdRecordBeforeCreateNode.getFirstChild();

        MetadataRecord createdContainerMdRecord =
            createdContainer.getMetadataRecords().get("escidoc");

        Marshaller<MetadataRecord> m2 =
            new Marshaller<MetadataRecord>(createdContainerMdRecord.getClass(),
            		cc.getTransport());
        String xml2 = m2.marshalDocument(createdContainerMdRecord);

        Document mdRecordAfterCreate = XmlUtility.getDocument(xml2);
        Node mdRecordAfterCreateNode =
            XPathAPI.selectSingleNode(mdRecordAfterCreate, "/md-record");
        Node mdRecordAfterCreateContent =
            mdRecordAfterCreateNode.getFirstChild();

        Asserts.assertXmlEquals("Metadata Records differ",
            mdRecordAfterCreateContent, mdRecordBeforeCreateContent);
    }

    /**
     * Test dealing with multiple MetadataRecords of Container.
     * 
     * @throws Exception
     *             Thrown if metadata records handling failed.
     */
    @Test
    public void testMultipleMetadataRecords01() throws Exception {

        Authentication auth =
            new Authentication(EscidocClientTestBase.DEFAULT_SERVICE_URL,
                Constants.SYSTEM_ADMIN_USER, Constants.SYSTEM_ADMIN_PASSWORD);

        ContainerHandlerClient cc = new ContainerHandlerClient();
        cc.setServiceAddress(EscidocClientTestBase.DEFAULT_SERVICE_URL);
        cc.setHandle(auth.getHandle());

        Container container = createContainerWithMinContent();
        Container createdContainer = cc.create(container);

        // compare the new created Container with the Container from the
        // request
        String objId = createdContainer.getObjid();
        assertNotNull("Object id is null", objId);
        assertEquals(container.getProperties().getContext().getObjid(),
            createdContainer.getProperties().getContext().getObjid());
        assertEquals(container.getProperties().getContentModel().getObjid(),
            createdContainer.getProperties().getContentModel().getObjid());

        Asserts.assertMdRecords(container.getMetadataRecords(),
            createdContainer.getMetadataRecords());

        // now delete some metadataRecords
        createdContainer.getMetadataRecords().del("md-record2");
        Container updatedContainer1 = cc.update(createdContainer);

        assertNotNull("Object id is null", objId);
        assertEquals(container.getProperties().getContext().getObjid(),
            createdContainer.getProperties().getContext().getObjid());
        assertEquals(container.getProperties().getContentModel().getObjid(),
            createdContainer.getProperties().getContentModel().getObjid());

        Asserts.assertMdRecords(createdContainer.getMetadataRecords(),
            updatedContainer1.getMetadataRecords());

    }

    // /**
    // * Compares all MetadataRecords with the master. If number, attributes or
    // * content of the MetadataRecords differ.
    // *
    // * @param master
    // * The master MetadataRecords.
    // * @param toCompare
    // * The to compare MetadataRecords.
    // * @throws Exception
    // * Thrown if both MetadataRecords not differ.
    // */
    // private void assertMdRecords(
    // final MetadataRecords master, final MetadataRecords toCompare)
    // throws Exception {
    //
    // assertEquals("Number of MetadataRecord(s) differs", master
    // .getMetadataRecords().size(), toCompare.getMetadataRecords().size());
    //
    // Iterator<MetadataRecord> it = master.getMetadataRecords().iterator();
    //
    // while (it.hasNext()) {
    //
    // MetadataRecord mdMaster = it.next();
    // String masterName = mdMaster.getName();
    // String masterMdType = mdMaster.getMdType();
    // String masterSchema = mdMaster.getSchema();
    // String masterContent = mdMaster.getContent();
    //
    // MetadataRecord mdToComp = toCompare.get(masterName);
    // assertNotNull("MetadataRecord with name '" + masterName
    // + "' is missing. ", mdToComp);
    //
    // String toCompareMdType = mdToComp.getMdType();
    // assertEquals("MetadataRecord types not equal.", masterMdType,
    // toCompareMdType);
    //
    // String toCompareSchema = mdToComp.getSchema();
    // assertEquals("MetadataRecord Schemas not equal.", masterSchema,
    // toCompareSchema);
    //
    // String toCompareContent = mdToComp.getContent();
    //
    // Document masterConDoc = XmlUtility.getDocument(masterContent);
    // Document toCompareDoc = XmlUtility.getDocument(toCompareContent);
    // assertXmlEquals("Content of MetadataRecords differ", masterConDoc,
    // toCompareDoc);
    // }
    // }

    private Container createContainerWithMinContent()
        throws ParserConfigurationException {

        Container container = new Container();
        ContainerProperties properties = new ContainerProperties();
        properties.setContext(new ResourceRef(Constants.EXAMPLE_CONTEXT_ID, ResourceRef.RESOURCE_TYPE.Context));
        properties.setContentModel(
        		new ResourceRef(Constants.EXAMPLE_CONTENT_MODEL_ID, ResourceRef.RESOURCE_TYPE.ContentModel));

        container.setProperties(properties);
        MetadataRecords mdRecords = new MetadataRecords();

        MetadataRecord mdRecord = new MetadataRecord();
        mdRecord.setName("escidoc");
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.newDocument();
        Element element = doc.createElementNS(null, "myMdRecord");

        mdRecord.setContent(element);
        mdRecords.add(mdRecord);
        MetadataRecord mdRecord2 = new MetadataRecord();
        mdRecord2.setName("md-record2");
        Document doc2 = builder.newDocument();
        Element element2 = doc.createElementNS(null, "myMdRecord");
        element2.setTextContent("222222222");

        mdRecord2.setContent(element2);

        mdRecords.add(mdRecord2);

        MetadataRecord mdRecord3 = new MetadataRecord();
        mdRecord3.setName("md-record3");
        Document doc3 = builder.newDocument();
        Element element3 = doc.createElementNS(null, "myMdRecord");
        Document doc4 = builder.newDocument();
        Element element4 = doc.createElementNS(null, "some-other-stuff");
        element2.setTextContent("33333333333333333333");
        element2.appendChild(element4);

        mdRecord3.setContent(element3);
        mdRecords.add(mdRecord3);

        container.setMetadataRecords(mdRecords);
        return container;
    }
}
