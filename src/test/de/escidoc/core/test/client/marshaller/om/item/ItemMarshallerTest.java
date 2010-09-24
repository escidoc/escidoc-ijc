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
package de.escidoc.core.test.client.marshaller.om.item;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.apache.xpath.XPathAPI;
import org.joda.time.DateTime;
import org.junit.Test;
import org.w3c.dom.Document;

import de.escidoc.core.client.TransportProtocol;
import de.escidoc.core.common.XmlUtility;
import de.escidoc.core.common.jibx.Factory;
import de.escidoc.core.resources.common.MetadataRecord;
import de.escidoc.core.resources.common.MetadataRecords;
import de.escidoc.core.resources.common.reference.ContentModelRef;
import de.escidoc.core.resources.common.reference.ContextRef;
import de.escidoc.core.resources.om.item.Item;
import de.escidoc.core.resources.om.item.component.Component;
import de.escidoc.core.resources.om.item.component.ComponentContent;
import de.escidoc.core.resources.om.item.component.ComponentProperties;
import de.escidoc.core.resources.om.item.component.Components;
import de.escidoc.core.test.client.AbstractParameterizedTestBase;
import de.escidoc.core.test.client.EscidocClientTestBase;
import de.escidoc.core.test.client.integrationTests.classMapping.om.ResourceUtility;

/**
 * Test marshalling/unmarshalling of Items with mockups.
 * 
 * @author SWA
 * 
 */
public class ItemMarshallerTest extends AbstractParameterizedTestBase {

    public ItemMarshallerTest(TransportProtocol transport) {
        super(transport);
    }

    /**
     * Test unmarshalling of complex Item (see issue CLIB-37).
     * 
     * @throws Exception
     *             Thrown if no or wrong exception is caught from the framework.
     */
    @Test
    public void unmarshalling01() throws Exception {

        File templItem =
            new File("./templates/mockups/" + transport.name().toLowerCase()
                + "/om/item/0.9/item_released01.xml");
        String itemXml = EscidocClientTestBase.getXmlFileAsString(templItem);

        Item item =
            Factory
                .getMarshallerFactory(transport).getItemMarshaller()
                .unmarshalDocument(itemXml);

        assertEquals("Wrong objid", "escidoc:40011", item.getObjid());
        assertEquals("Wrong last modification date", new DateTime(
            "2010-07-06T13:10:16.536Z"), item.getLastModificationDate());
        assertEquals("Wrong creation date", new DateTime(
            "2010-07-06T12:50:20.219Z"), item.getProperties().getCreationDate());

        assertEquals("Wrong created-by", "escidoc:10001", item
            .getProperties().getCreatedBy().getObjid());
        assertEquals("Wrong public-status", "released", item
            .getProperties().getPublicStatus());
        assertEquals("Wrong public-status comment",
            "testweise akzeptiert\n\t\t", item
                .getProperties().getPublicStatusComment());

        assertEquals("Wrong Context", "escidoc:8048", item
            .getProperties().getContext().getObjid());
        assertEquals("Wrong Content Model", "escidoc:persistent4", item
            .getProperties().getContentModel().getObjid());

        assertEquals("Wrong lock-status", "unlocked", item
            .getProperties().getLockStatus());

        assertEquals("Wrong [object] pid", "hdl:someHandle/test/escidoc:40011",
            item.getProperties().getPid());

        // version
        assertEquals("Wrong version number", "1", item
            .getProperties().getVersion().getNumber());
        assertEquals("Wrong version date", new DateTime(
            "2010-07-06T13:10:16.536Z"), item
            .getProperties().getVersion().getDate());
        assertEquals("Wrong version status", "released", item
            .getProperties().getVersion().getStatus());
        assertEquals("Wrong modified-by", "escidoc:10001", item
            .getProperties().getVersion().getModifiedBy().getObjid());
        assertEquals("Wrong version comment", "testweise akzeptiert ", item
            .getProperties().getVersion().getComment());
        assertEquals("Wrong version pid",
            "hdl:someHandle/test/escidoc:40011:1", item
                .getProperties().getVersion().getPid());

        // latest-version
        assertEquals("Wrong latest-version number", "1", item
            .getProperties().getLatestVersion().getNumber());
        assertEquals("Wrong latest-version date", new DateTime(
            "2010-07-06T13:10:16.536Z"), item
            .getProperties().getLatestVersion().getDate());

        // latest-release
        assertEquals("Wrong latest-release number", "1", item
            .getProperties().getLatestRelease().getNumber());
        assertEquals("Wrong latest-release date", new DateTime(
            "2010-07-06T13:10:16.536Z"), item
            .getProperties().getLatestRelease().getDate());

        // content-model-specific
        // TODO validate content-model-specific content
        // assertEquals("Wrong content-model-specific", "", item
        // .getProperties().getContentModelSpecific().getContent());

        // md-records
        assertEquals("Wrong number of md-records", 1, item
            .getMetadataRecords().size());
        assertEquals("Wrong name of md-record", "escidoc", item
            .getMetadataRecords().element().getName());
        assertEquals("Wrong schema of md-record", null, item
            .getMetadataRecords().element().getSchema());
        // TODO validate md-record content

        // components
        assertEquals("Wrong number of components", 1, item
            .getComponents().size());
        assertEquals("Wrong objid of component", "escidoc:40010", item
            .getComponents().element().getObjid());

        assertEquals("Wrong component property creation-date", new DateTime(
            "2010-07-06T12:50:18.195Z"), item
            .getComponents().element().getProperties().getCreationDate());
        assertEquals("Wrong component property created-by", "escidoc:10001",
            item.getComponents().element().getProperties().getCreatedBy()
                .getObjid());
        assertEquals("Wrong component property valid-status", "valid", item
            .getComponents().element().getProperties().getValidStatus());
        assertEquals("Wrong component property visibility", "public", item
            .getComponents().element().getProperties().getVisibility());
        assertEquals("Wrong component property pid",
            "hdl:someHandle/test/escidoc:40010", item
                .getComponents().element().getProperties().getPid());
        assertEquals(
            "Wrong component property content-category",
            "http://purl.org/escidoc/metadata/ves/content-categories/pre-print\n\t\t\t\t",
            item.getComponents().element().getProperties().getContentCategory());
        assertEquals("Wrong component property file-name", "test-2010.pdf",
            item.getComponents().element().getProperties().getFileName());
        assertEquals("Wrong component property mime-type", "application/pdf",
            item.getComponents().element().getProperties().getMimeType());
        assertEquals("Wrong component property checksum",
            "98d25a345678937832b977310442fa31c", item
                .getComponents().element().getProperties().getChecksum());
        assertEquals("Wrong component property checksum-algorithm", "MD5", item
            .getComponents().element().getProperties().getChecksumAlgorithm());

        assertEquals("Wrong component content title", "test-2010.pdf", item
            .getComponents().element().getContent().getXLinkTitle());
        assertEquals(
            "Wrong component content href",
            "/ir/item/escidoc:40011/components/component/escidoc:40010/content",
            item.getComponents().element().getContent().getXLinkHref());
        assertEquals("Wrong component content storage", "internal-managed",
            item.getComponents().element().getContent().getStorage());

        assertEquals("Wrong number of component md-record", 1, item
            .getComponents().element().getMetadataRecords().size());
        assertEquals("Wrong name of component md-record", "escidoc", item
            .getComponents().element().getMetadataRecords().element().getName());
        // TODO validate content
    }

    /**
     * Test unmarshalling of complex Item which is locked (see issue CLIB-41).
     * 
     * @throws Exception
     *             Thrown if no or wrong exception is caught from the framework.
     */
    @Test
    public void unmarshalling02() throws Exception {

        File templItem =
            new File("./templates/mockups/" + transport.name().toLowerCase()
                + "/om/item/0.9/item_locked01.xml");
        String itemXml = EscidocClientTestBase.getXmlFileAsString(templItem);

        Item item =
            Factory
                .getMarshallerFactory(transport).getItemMarshaller()
                .unmarshalDocument(itemXml);

        assertEquals("Wrong objid", "escidoc:40011", item.getObjid());
        assertEquals("Wrong last modification date", new DateTime(
            "2010-07-06T13:10:16.536Z"), item.getLastModificationDate());
        assertEquals("Wrong creation date", new DateTime(
            "2010-07-06T12:50:20.219Z"), item.getProperties().getCreationDate());

        assertEquals("Wrong created-by", "escidoc:10001", item
            .getProperties().getCreatedBy().getObjid());
        assertEquals("Wrong public-status", "released", item
            .getProperties().getPublicStatus());
        assertEquals("Wrong public-status comment",
            "testweise akzeptiert\n\t\t", item
                .getProperties().getPublicStatusComment());

        assertEquals("Wrong Context", "escidoc:8048", item
            .getProperties().getContext().getObjid());
        assertEquals("Wrong Content Model", "escidoc:persistent4", item
            .getProperties().getContentModel().getObjid());

        assertEquals("Wrong lock-status", "locked", item
            .getProperties().getLockStatus());
        assertEquals("Wrong lock-date",
            new DateTime("2010-07-09T12:24:19.314Z"), item
                .getProperties().getLockDate());
        assertEquals("Wrong lock-owner", "escidoc:10001", item
            .getProperties().getLockOwner().getObjid());

        assertEquals("Wrong [object] pid", "hdl:someHandle/test/escidoc:40011",
            item.getProperties().getPid());

        // version
        assertEquals("Wrong version number", "1", item
            .getProperties().getVersion().getNumber());
        assertEquals("Wrong version date", new DateTime(
            "2010-07-06T13:10:16.536Z"), item
            .getProperties().getVersion().getDate());
        assertEquals("Wrong version status", "released", item
            .getProperties().getVersion().getStatus());
        assertEquals("Wrong modified-by", "escidoc:10001", item
            .getProperties().getVersion().getModifiedBy().getObjid());
        assertEquals("Wrong version comment", "testweise akzeptiert ", item
            .getProperties().getVersion().getComment());
        assertEquals("Wrong version pid",
            "hdl:someHandle/test/escidoc:40011:1", item
                .getProperties().getVersion().getPid());

        // latest-version
        assertEquals("Wrong latest-version number", "1", item
            .getProperties().getLatestVersion().getNumber());
        assertEquals("Wrong latest-version date", new DateTime(
            "2010-07-06T13:10:16.536Z"), item
            .getProperties().getLatestVersion().getDate());

        // latest-release
        assertEquals("Wrong latest-release number", "1", item
            .getProperties().getLatestRelease().getNumber());
        assertEquals("Wrong latest-release date", new DateTime(
            "2010-07-06T13:10:16.536Z"), item
            .getProperties().getLatestRelease().getDate());

        // content-model-specific
        // TODO validate content-model-specific content
        // assertEquals("Wrong content-model-specific", "", item
        // .getProperties().getContentModelSpecific().getContent());

        // md-records
        assertEquals("Wrong number of md-records", 1, item
            .getMetadataRecords().size());
        assertEquals("Wrong name of md-record", "escidoc", item
            .getMetadataRecords().element().getName());
        assertEquals("Wrong schema of md-record", null, item
            .getMetadataRecords().element().getSchema());
        // TODO validate md-record content

        // components
        assertEquals("Wrong number of components", 1, item
            .getComponents().size());
        assertEquals("Wrong objid of component", "escidoc:40010", item
            .getComponents().element().getObjid());

        assertEquals("Wrong component property creation-date", new DateTime(
            "2010-07-06T12:50:18.195Z"), item
            .getComponents().element().getProperties().getCreationDate());
        assertEquals("Wrong component property created-by", "escidoc:10001",
            item.getComponents().element().getProperties().getCreatedBy()
                .getObjid());
        assertEquals("Wrong component property valid-status", "valid", item
            .getComponents().element().getProperties().getValidStatus());
        assertEquals("Wrong component property visibility", "public", item
            .getComponents().element().getProperties().getVisibility());
        assertEquals("Wrong component property pid",
            "hdl:someHandle/test/escidoc:40010", item
                .getComponents().element().getProperties().getPid());
        assertEquals(
            "Wrong component property content-category",
            "http://purl.org/escidoc/metadata/ves/content-categories/pre-print\n\t\t\t\t",
            item.getComponents().element().getProperties().getContentCategory());
        assertEquals("Wrong component property file-name", "test-2010.pdf",
            item.getComponents().element().getProperties().getFileName());
        assertEquals("Wrong component property mime-type", "application/pdf",
            item.getComponents().element().getProperties().getMimeType());
        assertEquals("Wrong component property checksum",
            "98d25a345678937832b977310442fa31c", item
                .getComponents().element().getProperties().getChecksum());
        assertEquals("Wrong component property checksum-algorithm", "MD5", item
            .getComponents().element().getProperties().getChecksumAlgorithm());

        assertEquals("Wrong component content title", "test-2010.pdf", item
            .getComponents().element().getContent().getXLinkTitle());
        assertEquals(
            "Wrong component content href",
            "/ir/item/escidoc:40011/components/component/escidoc:40010/content",
            item.getComponents().element().getContent().getXLinkHref());
        assertEquals("Wrong component content storage", "internal-managed",
            item.getComponents().element().getContent().getStorage());

        assertEquals("Wrong number of component md-record", 1, item
            .getComponents().element().getMetadataRecords().size());
        assertEquals("Wrong name of component md-record", "escidoc", item
            .getComponents().element().getMetadataRecords().element().getName());
        // TODO validate content
    }

    /**
     * Test unmarshalling of complex Item with one component and inline content.
     * 
     * @throws Exception
     *             Thrown if no or wrong exception is caught from the framework.
     */
    @Test
    public void unmarshallItemWithComponentInlineContent() throws Exception {

        File templItem =
            new File(
                "./templates/mockups/" + transport.name().toLowerCase()
                + "/om/item/0.9/item_one_component_inline_content.xml");
        String itemXml = EscidocClientTestBase.getXmlFileAsString(templItem);

        Item item =
            Factory
                .getMarshallerFactory(transport).getItemMarshaller()
                .unmarshalDocument(itemXml);

        assertNull("Wrong objid", item.getObjid());
        assertNull("Wrong last modification date",
            item.getLastModificationDate());
        assertNull("Wrong creation date", item
            .getProperties().getCreationDate());

        assertNull("Wrong created-by", item.getProperties().getCreatedBy());
        assertNull("Wrong public-status", item
            .getProperties().getPublicStatus());
        assertNull("Wrong public-status comment", item
            .getProperties().getPublicStatusComment());

        assertEquals("Wrong Context", "escidoc:context", item
            .getProperties().getContext().getObjid());
        assertEquals("Wrong Content Model", "escidoc:contentModel", item
            .getProperties().getContentModel().getObjid());

        assertNull("Wrong lock-status", item.getProperties().getLockStatus());
        assertNull("Wrong lock-date", item.getProperties().getLockDate());
        assertNull("Wrong lock-owner", item.getProperties().getLockOwner());

        assertNull("Wrong [object] pid", item.getProperties().getPid());

        // version
        assertNull("Wrong version number", item.getProperties().getVersion());

        // latest-version
        assertNull("Wrong latest-version number", item
            .getProperties().getLatestVersion());

        // latest-release
        assertNull("Wrong latest-release number", item
            .getProperties().getLatestRelease());

        // md-records
        assertEquals("Wrong number of md-records", 1, item
            .getMetadataRecords().size());
        assertEquals("Wrong name of md-record", "escidoc", item
            .getMetadataRecords().element().getName());
        assertEquals("Wrong schema of md-record",
            "http://www.escidoc-project.de/metadata/schema/0.1", item
                .getMetadataRecords().element().getSchema());
        // TODO validate md-record content

        // components
        assertEquals("Wrong number of components", 1, item
            .getComponents().size());
        assertNull("Wrong objid of component", item
            .getComponents().element().getObjid());

        assertNull("Wrong component property creation-date", item
            .getComponents().element().getProperties().getCreationDate());
        assertNull("Wrong component property created-by", item
            .getComponents().element().getProperties().getCreatedBy());

        // FIXME check content exactly
        assertTrue("Wrong component property description", item
            .getComponents().element().getProperties().getDescription()
            .length() > 1);
        assertEquals("Wrong component property valid-status", "valid", item
            .getComponents().element().getProperties().getValidStatus());
        assertEquals("Wrong component property visibility", "public", item
            .getComponents().element().getProperties().getVisibility());
        assertNull("Wrong component property pid", item
            .getComponents().element().getProperties().getPid());

        assertEquals("Wrong component property content-category", "pre-print",
            item.getComponents().element().getProperties().getContentCategory());
        assertEquals("Wrong component property file-name", "inlinecontent.txt",
            item.getComponents().element().getProperties().getFileName());

        // FIXME fix trim of mime-type
        assertEquals("Wrong component property mime-type",
            "application/octet-stream", item
                .getComponents().element().getProperties().getMimeType().trim());

        assertNull("Wrong component property checksum", item
            .getComponents().element().getProperties().getChecksum());
        assertNull("Wrong component property checksum-algorithm", item
            .getComponents().element().getProperties().getChecksumAlgorithm());

        assertNull("Wrong component content title", item
            .getComponents().element().getContent().getXLinkTitle());
        assertNull("Wrong component content href", item
            .getComponents().element().getContent().getXLinkHref());
        assertEquals("Wrong component content storage", "internal-managed",
            item.getComponents().element().getContent().getStorage());

        // FIXME compare content without trim
        assertEquals(
            "Wrong inline content",
            "Inline Content -- Inline Content -- Inline Content -- Inline Content -- Inline Content -- Inline Content",
            item.getComponents().element().getContent()
                .getBase64EncodedContent().trim());

        assertEquals("Wrong number of component md-record", 2, item
            .getComponents().element().getMetadataRecords().size());
        assertEquals("Wrong name of component md-record", "escidoc", item
            .getComponents().element().getMetadataRecords().element().getName());
        // TODO validate content
    }

    /**
     * Test marshalling of an Item with component and content reference.
     * 
     * @throws Exception
     */
    @Test
    public void marshallItemWithContent01() throws Exception {

        final String fileLocation = "http://localhost/some/file/abc.png";
        final String fileName = "abc.png";
        final String fileDescription = "Random content";

        Item item = new Item();

        item.getProperties().setContext(new ContextRef("escidoc:123"));
        item.getProperties()
            .setContentModel(new ContentModelRef("escidoc:345"));

        // Metadata Record(s)
        MetadataRecords mdRecords = new MetadataRecords();
        MetadataRecord mdrecord = ResourceUtility.getMdRecord("escidoc");
        mdRecords.add(mdrecord);
        item.setMetadataRecords(mdRecords);

        Component component = new Component();
        ComponentContent content = new ComponentContent();
        content.setXLinkHref(fileLocation);
        component.setContent(content);
        component.setProperties(new ComponentProperties());
        component.getProperties().setDescription(fileDescription);
        component.getProperties().setFileName(fileName);
        Components components = new Components();
        components.add(component);
        item.setComponents(components);

        String itemXml =
            Factory
                .getMarshallerFactory(transport).getItemMarshaller()
                .marshalDocument(item);

        Document itemDoc = XmlUtility.getDocument(itemXml);
        assertEquals(
            "Wrong file location",
            fileLocation,
            XPathAPI.selectSingleNode(itemDoc,
                "/item/components/component[1]/content/@href").getTextContent());
        assertEquals(
            "Wrong content file-name",
            fileName,
            XPathAPI
                .selectSingleNode(itemDoc,
                    "/item/components/component[1]/properties/file-name")
                .getTextContent());
        assertEquals(
            "Wrong content description",
            fileDescription,
            XPathAPI
                .selectSingleNode(itemDoc,
                    "/item/components/component[1]/properties/description")
                .getTextContent());
    }

    /**
     * Test marshalling of an Item with component and content reference.
     * 
     * @throws Exception
     */
    @Test
    public void marshallItemWithContent02() throws Exception {

        Item item = new Item();

        item.getProperties().setContext(new ContextRef("escidoc:context"));
        item.getProperties().setContentModel(
            new ContentModelRef("escidoc:content-model"));

        // Metadata Record(s)
        MetadataRecords mdRecords = new MetadataRecords();
        MetadataRecord mdrecord = ResourceUtility.getMdRecord("escidoc");
        mdRecords.add(mdrecord);
        item.setMetadataRecords(mdRecords);

        Component component = new Component();
        ComponentContent content = new ComponentContent();
        content.setXLinkHref("http://www.escidoc.org/content/image.jpg");
        component.setContent(content);
        component.setProperties(new ComponentProperties());
        component.getProperties().setDescription("Random content");
        component.getProperties().setFileName("image.jpg");
        Components components = new Components();
        components.add(component);
        item.setComponents(components);

        String itemXml =
            Factory
                .getMarshallerFactory(transport).getItemMarshaller()
                .marshalDocument(item);

    }
}
