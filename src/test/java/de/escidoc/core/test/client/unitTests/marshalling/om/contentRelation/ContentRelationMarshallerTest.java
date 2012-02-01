/**
 * 
 */
package de.escidoc.core.test.client.unitTests.marshalling.om.contentRelation;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import de.escidoc.core.resources.om.contentRelation.ContentRelation;
import de.escidoc.core.test.client.unitTests.marshalling.MarshallerTestBase;

/**
 * @author Marko Voß
 * 
 */
public class ContentRelationMarshallerTest extends MarshallerTestBase<ContentRelation> {

    private static final String BASE = "om/contentRelation";

    private static final String XSD = "0.1";

    /**
     * @param transport
     * @throws IOException
     * @throws ParserConfigurationException
     * @throws SAXException
     */
    public ContentRelationMarshallerTest() throws IOException, ParserConfigurationException, SAXException {
        super(ContentRelation.class, BASE, XSD, "content-relation_complete.xml");
    }

    @Override
    protected void validate(final ContentRelation obj) throws Exception {
        /*
         * TODO: LMDs for sub-resources
         */

        // ContentRelation
        assertResource("/escidocContentRelation:content-relation", obj);
        assertDateTime("/escidocContentRelation:content-relation/@last-modification-date", obj
            .getLastModificationDate());

        // ContentRelationProperties
        assertNotNull(obj.getProperties());
        final String path = "/escidocContentRelation:content-relation/escidocContentRelation:properties";
        assertXLinkResource(path, obj.getProperties());

        assertDateTime(path + "/prop:creation-date", obj.getProperties().getCreationDate());
        assertResource(path + "/srel:created-by", obj.getProperties().getCreatedBy());
        assertResource(path + "/srel:modified-by", obj.getProperties().getModifiedBy());
        assertXPath(path + "/prop:description", obj.getProperties().getDescription());
        assertEnum(path + "/prop:public-status", obj.getProperties().getPublicStatus());
        assertXPath(path + "/prop:public-status-comment", obj.getProperties().getPublicStatusComment());
        assertEnum(path + "/prop:lock-status", obj.getProperties().getLockStatus());
        assertDateTime(path + "/prop:lock-date", obj.getProperties().getLockDate());
        assertResource(path + "/srel:lock-owner", obj.getProperties().getLockOwner());
        assertXPath(path + "/prop:pid", obj.getProperties().getPid());

        // ContentRelation values
        assertXPath("/escidocContentRelation:content-relation/escidocContentRelation:type", obj.getType());

        /*
         * It is not possible to generate the xlink in case of SOAP transport
         * protocol
         */
        assertResource("/escidocContentRelation:content-relation/escidocContentRelation:subject", obj.getSubject(),
            false);
        assertResource("/escidocContentRelation:content-relation/escidocContentRelation:object", obj.getObject(), false);

        // MdRecords
        assertMdRecord("/escidocContentRelation:content-relation/escidocMetadataRecords:md-records", obj
            .getMetadataRecords(), 0);
        assertMdRecord("/escidocContentRelation:content-relation/escidocMetadataRecords:md-records", obj
            .getMetadataRecords(), 1);
        assertMdRecord("/escidocContentRelation:content-relation/escidocMetadataRecords:md-records", obj
            .getMetadataRecords(), 2);
    }

    @Override
    protected void testSubResources(final ContentRelation obj) throws Exception {

    }

    @Override
    protected void testResourceWithoutSubResources(final ContentRelation obj) throws Exception {

    }
}