/**
 * 
 */
package de.escidoc.core.test.client.unitTests.marshalling.om.container;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import de.escidoc.core.resources.common.properties.VersionImpl;
import de.escidoc.core.resources.om.container.Container;
import de.escidoc.core.test.client.unitTests.marshalling.MarshallerTestBase;

/**
 * @author Marko Voß
 * 
 */
public class ContainerMarshallerTest extends MarshallerTestBase<Container> {

    private static final String BASE = "om/container";

    private static final String XSD = "0.9";

    /**
     * @param transport
     * @throws IOException
     * @throws ParserConfigurationException
     * @throws SAXException
     */
    public ContainerMarshallerTest() throws IOException, ParserConfigurationException, SAXException {
        super(Container.class, BASE, XSD, "container_complete.xml");
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.test.client.marshaller.MarshallerTestBase#validate(java
     * .lang.Object)
     */
    @Override
    protected void validate(final Container obj) throws Exception {
        /*
         * TODO: LMDs for sub-resources
         */

        // Container
        assertResource("/escidocContainer:container", obj);
        assertDateTime("/escidocContainer:container/@last-modification-date", obj.getLastModificationDate());

        // ContainerProperties
        assertNotNull(obj.getProperties());
        String path = "/escidocContainer:container/escidocContainer:properties";
        assertXLinkResource(path, obj.getProperties());
        assertDateTime(path + "/prop:creation-date", obj.getProperties().getCreationDate());
        assertResource(path + "/srel:created-by", obj.getProperties().getCreatedBy());
        assertEnum(path + "/prop:public-status", obj.getProperties().getPublicStatus());
        assertXPath(path + "/prop:public-status-comment", obj.getProperties().getPublicStatusComment());
        assertXPath(path + "/prop:name", obj.getProperties().getName());
        assertXPath(path + "/prop:description", obj.getProperties().getDescription());
        assertResource(path + "/srel:context", obj.getProperties().getContext());
        assertResource(path + "/srel:content-model", obj.getProperties().getContentModel());
        assertEnum(path + "/prop:lock-status", obj.getProperties().getLockStatus());
        assertDateTime(path + "/prop:lock-date", obj.getProperties().getLockDate());
        assertResource(path + "/srel:lock-owner", obj.getProperties().getLockOwner());
        assertXPath(path + "/prop:pid", obj.getProperties().getPid());

        // Container.Properties.Version
        assertNotNull(obj.getProperties().getVersion());
        path = "/escidocContainer:container/escidocContainer:properties/prop:version";
        assertResource(path, (VersionImpl) obj.getProperties().getVersion());
        assertXPath(path + "/version:number", obj.getProperties().getVersion().getNumber());
        assertDateTime(path + "/version:date", obj.getProperties().getVersion().getDate());
        assertXPath(path + "/version:status", obj.getProperties().getVersion().getStatus());
        assertResource(path + "/srel:modified-by", obj.getProperties().getVersion().getModifiedBy());
        assertXPath(path + "/version:comment", obj.getProperties().getVersion().getComment());
        assertXPath(path + "/version:pid", obj.getProperties().getVersion().getPid());

        // Container.Properties.LatestVersion
        assertNotNull(obj.getProperties().getLatestVersion());
        path = "/escidocContainer:container/escidocContainer:properties/prop:latest-version";
        assertResource(path, (VersionImpl) obj.getProperties().getLatestVersion());
        assertXPath(path + "/version:number", obj.getProperties().getLatestVersion().getNumber());
        assertDateTime(path + "/version:date", obj.getProperties().getLatestVersion().getDate());

        // Container.Properties.LatestRelease
        assertNotNull(obj.getProperties().getLatestRelease());
        path = "/escidocContainer:container/escidocContainer:properties/prop:latest-release";
        assertResource(path, (VersionImpl) obj.getProperties().getLatestRelease());
        assertXPath(path + "/release:number", obj.getProperties().getLatestRelease().getNumber());
        assertDateTime(path + "/release:date", obj.getProperties().getLatestRelease().getDate());
        assertXPath(path + "/release:pid", obj.getProperties().getLatestRelease().getPid());

        // Container.Properties.CMS
        assertNotNull(obj.getProperties().getContentModelSpecific());
        // TODO validate DOM

        // Container.MdRecords
        assertNotNull(obj.getMetadataRecords());
        assertXLinkList("/escidocContainer:container/escidocMetadataRecords:md-records", obj.getMetadataRecords());

        // Container.MdRecords.MdRecord[1]
        assertMdRecord("/escidocContainer:container/escidocMetadataRecords:md-records", obj.getMetadataRecords(), 0);

        // Container.StructMap
        assertNotNull(obj.getStructMap());
        assertNotNull(obj.getStructMap().getItems());
        assertNotNull(obj.getStructMap().getContainers());
        assertXLinkResource("/escidocContainer:container/struct-map:struct-map", obj.getStructMap());

        // Container.StructMap.[1]
        assertResource("/escidocContainer:container/struct-map:struct-map/srel:item[1]", obj
            .getStructMap().getItems().get(0));
        // Container.StructMap.[2]
        assertResource("/escidocContainer:container/struct-map:struct-map/srel:item[2]", obj
            .getStructMap().getItems().get(1));
        // Container.StructMap.[3]
        assertResource("/escidocContainer:container/struct-map:struct-map/srel:container[1]", obj
            .getStructMap().getContainers().get(0));

        // Container.Relations
        assertNotNull(obj.getRelations());
        assertXLinkList("/escidocContainer:container/relations:relations", obj.getRelations());

        // Container.Relation[1]
        assertResource("/escidocContainer:container/relations:relations/relations:relation[1]", obj.getRelations().get(
            0), false);
        assertXPath("/escidocContainer:container/relations:relations/relations:relation[1]/@predicate", obj
            .getRelations().get(0).getPredicate());

        // Container.Relation[2]
        assertResource("/escidocContainer:container/relations:relations/relations:relation[2]", obj.getRelations().get(
            1), false);
        assertXPath("/escidocContainer:container/relations:relations/relations:relation[2]/@predicate", obj
            .getRelations().get(1).getPredicate());
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.test.client.marshaller.MarshallerTestBase#testSubResources
     * (java.lang.Object)
     */
    @Override
    protected void testSubResources(final Container obj) throws Exception {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.test.client.marshaller.MarshallerTestBase#
     * testResourceWithoutSubResources(java.lang.Object)
     */
    @Override
    protected void testResourceWithoutSubResources(final Container obj) throws Exception {
        // TODO Auto-generated method stub

    }

}
