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

import java.io.File;

import org.joda.time.DateTime;
import org.junit.Test;

import de.escidoc.core.common.jibx.Factory;
import de.escidoc.core.resources.om.item.Item;
import de.escidoc.core.test.client.EscidocClientTestBase;

/**
 * Test marshalling/unmarshalling of Items with mockups.
 * 
 * @author SWA
 * 
 */
public class ItemMarshallerTest {

    /**
     * Test unmarshalling of complex Item (see issue CLIB-37).
     * 
     * @throws Exception
     *             Thrown if no or wrong exception is caught from the framework.
     */
    @Test
    public void unmarshalling01() throws Exception {

        File templItem =
            new File("./templates/mockups/soap/om/item/0.9/item_released01.xml");
        String itemXml = EscidocClientTestBase.getXmlFileAsString(templItem);

        Item item = Factory.getItemMarshaller().unmarshalDocument(itemXml);

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
            .getComponents().element().getContent().getTitle());
        assertEquals(
            "Wrong component content href",
            "/ir/item/escidoc:40011/components/component/escidoc:40010/content",
            item.getComponents().element().getContent().getHref());
        assertEquals("Wrong component content storage", "internal-managed",
            item.getComponents().element().getContent().getStorage());

        assertEquals("Wrong number of component md-record", 1, item
            .getComponents().element().getMetadataRecords().size());
        assertEquals("Wrong name of component md-record", "escidoc", item
            .getComponents().element().getMetadataRecords().element().getName());
        // TODO validate content
    }

    /**
     * Test unmarshalling of complex Item eich is locked (see issue CLIB-37).
     * 
     * @throws Exception
     *             Thrown if no or wrong exception is caught from the framework.
     */
    @Test
    public void unmarshalling02() throws Exception {

        File templItem =
            new File("./templates/mockups/soap/om/item/0.9/item_locked01.xml");
        String itemXml = EscidocClientTestBase.getXmlFileAsString(templItem);

        Item item = Factory.getItemMarshaller().unmarshalDocument(itemXml);

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
            .getComponents().element().getContent().getTitle());
        assertEquals(
            "Wrong component content href",
            "/ir/item/escidoc:40011/components/component/escidoc:40010/content",
            item.getComponents().element().getContent().getHref());
        assertEquals("Wrong component content storage", "internal-managed",
            item.getComponents().element().getContent().getStorage());

        assertEquals("Wrong number of component md-record", 1, item
            .getComponents().element().getMetadataRecords().size());
        assertEquals("Wrong name of component md-record", "escidoc", item
            .getComponents().element().getMetadataRecords().element().getName());
        // TODO validate content

    }
}
