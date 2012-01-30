/**
 * 
 */
package de.escidoc.core.test.client.unitTests.marshalling.om.item;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import de.escidoc.core.resources.common.properties.VersionImpl;
import de.escidoc.core.resources.om.item.Item;
import de.escidoc.core.test.client.unitTests.marshalling.MarshallerTestBase;

/**
 * @author Marko Voß
 * 
 */
public class ItemMarshallerTest extends MarshallerTestBase<Item> {

    private static final String BASE = "om/item";

    private static final String XSD = "0.10";

    /**
     * @param transport
     * @throws IOException
     * @throws ParserConfigurationException
     * @throws SAXException
     */
    public ItemMarshallerTest() throws IOException, ParserConfigurationException, SAXException {
        super(Item.class, BASE, XSD, "item_complete.xml");
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.test.client.marshaller.MarshallerTestBase#validate(java
     * .lang.Object)
     */
    @Override
    protected void validate(final Item obj) throws Exception {
        /*
         * TODO: LMDs for sub-resources
         */

        // Item
        assertResource("/escidocItem:item", obj);
        assertDateTime("/escidocItem:item/@last-modification-date", obj.getLastModificationDate());

        // ItemProperties
        assertNotNull(obj.getProperties());
        String path = "/escidocItem:item/escidocItem:properties";
        assertXLinkResource(path, obj.getProperties());
        assertDateTime(path + "/prop:creation-date", obj.getProperties().getCreationDate());
        assertResource(path + "/srel:created-by", obj.getProperties().getCreatedBy());
        assertEnum(path + "/prop:public-status", obj.getProperties().getPublicStatus());
        assertXPath(path + "/prop:public-status-comment", obj.getProperties().getPublicStatusComment());
        assertResource(path + "/srel:context", obj.getProperties().getContext());
        assertResource(path + "/srel:content-model", obj.getProperties().getContentModel());
        assertEnum(path + "/prop:lock-status", obj.getProperties().getLockStatus());
        assertDateTime(path + "/prop:lock-date", obj.getProperties().getLockDate());
        assertResource(path + "/srel:lock-owner", obj.getProperties().getLockOwner());
        assertXPath(path + "/prop:pid", obj.getProperties().getPid());

        // Item.Properties.Version
        assertNotNull(obj.getProperties().getVersion());
        path = "/escidocItem:item/escidocItem:properties/prop:version";
        assertResource(path, (VersionImpl) obj.getProperties().getVersion());
        assertXPath(path + "/version:number", obj.getProperties().getVersion().getNumber());
        assertDateTime(path + "/version:date", obj.getProperties().getVersion().getDate());
        assertXPath(path + "/version:status", obj.getProperties().getVersion().getStatus());
        assertResource(path + "/srel:modified-by", obj.getProperties().getVersion().getModifiedBy());
        assertXPath(path + "/version:comment", obj.getProperties().getVersion().getComment());
        assertXPath(path + "/version:pid", obj.getProperties().getVersion().getPid());

        // Item.Properties.LatestVersion
        assertNotNull(obj.getProperties().getLatestVersion());
        assertResource("/escidocItem:item/escidocItem:properties/prop:latest-version", (VersionImpl) obj
            .getProperties().getLatestVersion());
        assertXPath("/escidocItem:item/escidocItem:properties/prop:latest-version/version:number", obj
            .getProperties().getLatestVersion().getNumber());
        assertDateTime("/escidocItem:item/escidocItem:properties/prop:latest-version/version:date", obj
            .getProperties().getLatestVersion().getDate());

        // Item.Properties.LatestRelease
        assertNotNull(obj.getProperties().getLatestRelease());
        assertResource("/escidocItem:item/escidocItem:properties/prop:latest-release", (VersionImpl) obj
            .getProperties().getLatestRelease());
        assertXPath("/escidocItem:item/escidocItem:properties/prop:latest-release/release:number", obj
            .getProperties().getLatestRelease().getNumber());
        assertDateTime("/escidocItem:item/escidocItem:properties/prop:latest-release/release:date", obj
            .getProperties().getLatestRelease().getDate());
        assertXPath("/escidocItem:item/escidocItem:properties/prop:latest-release/release:pid", obj
            .getProperties().getLatestRelease().getPid());

        // Item.Properties.CMS
        assertNotNull(obj.getProperties().getContentModelSpecific());
        // TODO validate DOM

        // Item.MdRecords
        assertNotNull(obj.getMetadataRecords());
        assertXLinkList("/escidocItem:item/escidocMetadataRecords:md-records", obj.getMetadataRecords());

        // Item.MdRecords.MdRecord[1]
        assertMdRecord("/escidocItem:item/escidocMetadataRecords:md-records", obj.getMetadataRecords(), 0);

        // Item.ContentStreams
        assertNotNull(obj.getContentStreams());
        assertXLinkList("/escidocItem:item/escidocContentStreams:content-streams", obj.getContentStreams());
        final String contentStreamXPath =
            "/escidocItem:item/escidocContentStreams:content-streams/escidocContentStreams:content-stream[1]";
        assertNamedSubResource(contentStreamXPath, obj.getContentStreams().get(0));
        assertNotNull(obj.getContentStreams().get(0).getContent());
        // TODO: test content (XPath cannot resolve the namspace for foo:bar
        // because it is not part of the namespace-node.

        // Item.Components
        assertNotNull(obj.getComponents());
        assertXLinkList("/escidocItem:item/escidocComponents:components", obj.getComponents());

        // Item.Components.Component[1]
        final String componentXPath = "/escidocItem:item/escidocComponents:components/escidocComponents:component[1]";
        assertResource(componentXPath, obj.getComponents().get(0));

        // Item.Components.Component[1].Properties
        assertNotNull(obj.getComponents().get(0).getProperties());
        assertXLinkResource(componentXPath + "/escidocComponents:properties", obj
            .getComponents().get(0).getProperties());
        assertDateTime(componentXPath + "/escidocComponents:properties/prop:creation-date", obj
            .getComponents().get(0).getProperties().getCreationDate());
        assertResource(componentXPath + "/escidocComponents:properties/srel:created-by", obj
            .getComponents().get(0).getProperties().getCreatedBy());
        assertXPath(componentXPath + "/escidocComponents:properties/prop:valid-status", obj
            .getComponents().get(0).getProperties().getValidStatus());
        assertXPath(componentXPath + "/escidocComponents:properties/prop:visibility", obj
            .getComponents().get(0).getProperties().getVisibility());
        assertXPath(componentXPath + "/escidocComponents:properties/prop:pid", obj
            .getComponents().get(0).getProperties().getPid());
        assertXPath(componentXPath + "/escidocComponents:properties/prop:content-category", obj
            .getComponents().get(0).getProperties().getContentCategory());
        assertXPath(componentXPath + "/escidocComponents:properties/prop:file-name", obj
            .getComponents().get(0).getProperties().getFileName());
        assertXPath(componentXPath + "/escidocComponents:properties/prop:mime-type", obj
            .getComponents().get(0).getProperties().getMimeType());
        assertXPath(componentXPath + "/escidocComponents:properties/prop:checksum", obj
            .getComponents().get(0).getProperties().getChecksum());
        assertXPath(componentXPath + "/escidocComponents:properties/prop:checksum-algorithm", obj
            .getComponents().get(0).getProperties().getChecksumAlgorithm());

        // Item.Components.Component[1].Content
        assertNotNull(obj.getComponents().get(0).getContent());
        assertXLinkResource(componentXPath + "/escidocComponents:content", obj.getComponents().get(0).getContent());
        assertEnum(componentXPath + "/escidocComponents:content/@storage", obj
            .getComponents().get(0).getContent().getStorageType());

        // TODO validate DOM
        // assertXPath(componentXPath + "/escidocComponents:content", obj
        // .getComponents().get(0).getContent().getBase64EncodedContent());

        // Item.Components.Component[1].MdRecords
        assertNotNull(obj.getComponents().get(0).getMetadataRecords());
        assertXLinkList(componentXPath + "/escidocMetadataRecords:md-records", obj
            .getComponents().get(0).getMetadataRecords());
        assertNamedSubResource(componentXPath
            + "/escidocMetadataRecords:md-records/escidocMetadataRecords:md-record[1]", obj
            .getComponents().get(0).getMetadataRecords().get(0));
        // TODO validate DOM

        // Item.Relations
        assertNotNull(obj.getRelations());
        assertXLinkList("/escidocItem:item/relations:relations", obj.getRelations());

        // Item.Relations.Relation[1]
        assertResource("/escidocItem:item/relations:relations/relations:relation[1]", obj.getRelations().get(0), false);
        assertXPath("/escidocItem:item/relations:relations/relations:relation[1]/@predicate", obj
            .getRelations().get(0).getPredicate());
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.test.client.marshaller.MarshallerTestBase#testSubResources
     * (java.lang.Object)
     */
    @Override
    protected void testSubResources(final Item obj) {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.test.client.marshaller.MarshallerTestBase#
     * testResourceWithoutSubResources(java.lang.Object)
     */
    @Override
    protected void testResourceWithoutSubResources(final Item obj) {
        // TODO Auto-generated method stub
    }
}