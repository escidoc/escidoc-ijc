/**
 * 
 */
package de.escidoc.core.test.client.unitTests.marshalling.cmm.cm;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import de.escidoc.core.client.TransportProtocol;
import de.escidoc.core.resources.cmm.ContentModel;
import de.escidoc.core.resources.common.properties.VersionImpl;
import de.escidoc.core.test.client.unitTests.marshalling.MarshallerTestBase;

/**
 * @author Marko Voß
 * 
 */
public class ContentModelMarshallerTest
    extends MarshallerTestBase<ContentModel> {

    private static final String BASE = "cmm/cm";

    private static final String XSD = "0.1";

    /**
     * @param transport
     * @throws IOException
     * @throws ParserConfigurationException
     * @throws SAXException
     */
    public ContentModelMarshallerTest(final TransportProtocol transport)
        throws IOException, ParserConfigurationException, SAXException {
        super(ContentModel.class, BASE, XSD, "content-model_complete.xml",
            transport);
    }

    @Override
    protected void validate(final ContentModel obj) throws Exception {
        /*
         * TODO: LMDs for sub-resources
         */

        // ContentModel
        assertResource("/escidocContentModel:content-model", obj);
        assertDateTime(
            "/escidocContentModel:content-model/@last-modification-date",
            obj.getLastModificationDate());

        // ContentModelProperties
        String path =
            "/escidocContentModel:content-model/escidocContentModel:properties";
        assertNotNull(obj.getProperties());
        assertXLinkResource(path, obj.getProperties());

        assertXPath(path + "/prop:name", obj.getProperties().getName());
        assertXPath(path + "/prop:description", obj
            .getProperties().getDescription());
        assertDateTime(path + "/prop:creation-date", obj
            .getProperties().getCreationDate());
        assertResource(path + "/srel:created-by", obj
            .getProperties().getCreatedBy());
        assertEnum(path + "/prop:public-status", obj
            .getProperties().getPublicStatus());
        assertXPath(path + "/prop:public-status-comment", obj
            .getProperties().getPublicStatusComment());
        assertEnum(path + "/prop:lock-status", obj
            .getProperties().getLockStatus());
        assertDateTime(path + "/prop:lock-date", obj
            .getProperties().getLockDate());
        assertResource(path + "/srel:lock-owner", obj
            .getProperties().getLockOwner());
        assertXPath(path + "/prop:pid", obj.getProperties().getPid());

        // ContentModelProperties.Version
        assertNotNull(obj.getProperties().getVersion());
        assertResource(path + "/prop:version", (VersionImpl) obj
            .getProperties().getVersion());
        assertXPath(path + "/prop:version/version:number", obj
            .getProperties().getVersion().getNumber());
        assertDateTime(path + "/prop:version/version:date", obj
            .getProperties().getVersion().getDate());
        assertXPath(path + "/prop:version/version:status", obj
            .getProperties().getVersion().getStatus());
        assertResource(path + "/prop:version/srel:modified-by", obj
            .getProperties().getVersion().getModifiedBy());
        assertXPath(path + "/prop:version/version:comment", obj
            .getProperties().getVersion().getComment());
        assertXPath(path + "/prop:version/version:pid", obj
            .getProperties().getVersion().getPid());

        // ContentModelProperties.LatestVersion
        assertNotNull(obj.getProperties().getLatestVersion());
        assertResource(path + "/prop:latest-version", (VersionImpl) obj
            .getProperties().getLatestVersion());
        assertXPath(path + "/prop:latest-version/version:number", obj
            .getProperties().getLatestVersion().getNumber());
        assertDateTime(path + "/prop:latest-version/version:date", obj
            .getProperties().getLatestVersion().getDate());

        // ContentModelProperties.LatestRelease
        assertNotNull(obj.getProperties().getLatestRelease());
        assertResource(path + "/prop:latest-release", (VersionImpl) obj
            .getProperties().getLatestRelease());
        assertXPath(path + "/prop:latest-release/release:number", obj
            .getProperties().getLatestRelease().getNumber());
        assertDateTime(path + "/prop:latest-release/release:date", obj
            .getProperties().getLatestRelease().getDate());
        assertXPath(path + "/prop:latest-release/release:pid", obj
            .getProperties().getLatestRelease().getPid());

        // MdRecordDefinitions
        assertNotNull(obj.getMetadataRecordDefinitions());
        assertNotNull(obj.getMetadataRecordDefinitions().get(0));

        assertXPath(
            "/escidocContentModel:content-model/escidocContentModel:md-record-definitions/escidocContentModel:md-record-definition[1]/@name",
            obj.getMetadataRecordDefinitions().get(0).getName());

        // check xlink no matter, what transport protocol we are using
        assertXLinkResource(
            "/escidocContentModel:content-model/escidocContentModel:md-record-definitions/escidocContentModel:md-record-definition[1]/escidocContentModel:schema",
            obj.getMetadataRecordDefinitions().get(0).getSchema(), true);

        // ResourceDefinitions
        assertNotNull(obj.getResourceDefinitions());
        assertNotNull(obj.getResourceDefinitions().get(0));

        assertXPath(
            "/escidocContentModel:content-model/escidocContentModel:resource-definitions/escidocContentModel:resource-definition[1]/@name",
            obj.getResourceDefinitions().get(0).getName());
        assertXPath(
            "/escidocContentModel:content-model/escidocContentModel:resource-definitions/escidocContentModel:resource-definition[1]/escidocContentModel:md-record-name",
            obj.getResourceDefinitions().get(0).getMetadataRecordName());

        // check xlink no matter, what transport protocol we are using
        assertXLinkResource(
            "/escidocContentModel:content-model/escidocContentModel:resource-definitions/escidocContentModel:resource-definition[1]/escidocContentModel:xslt",
            obj.getResourceDefinitions().get(0).getXslt(), true);
    }

    @Override
    protected void testSubResources(final ContentModel obj) throws Exception {
        // TODO Auto-generated method stub
    }

    @Override
    protected void testResourceWithoutSubResources(final ContentModel obj)
        throws Exception {
        // TODO Auto-generated method stub
    }
}