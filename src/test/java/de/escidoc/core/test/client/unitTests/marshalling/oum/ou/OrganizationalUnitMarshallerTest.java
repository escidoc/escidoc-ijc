/**
 * 
 */
package de.escidoc.core.test.client.unitTests.marshalling.oum.ou;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import de.escidoc.core.resources.oum.OrganizationalUnit;
import de.escidoc.core.test.client.unitTests.marshalling.MarshallerTestBase;

/**
 * @author Marko Voß
 * 
 */
public class OrganizationalUnitMarshallerTest extends MarshallerTestBase<OrganizationalUnit> {

    private static final String BASE = "oum/ou";

    private static final String XSD = "0.8";

    /**
     * @param transport
     * @throws IOException
     * @throws ParserConfigurationException
     * @throws SAXException
     */
    public OrganizationalUnitMarshallerTest() throws IOException, ParserConfigurationException, SAXException {
        super(OrganizationalUnit.class, BASE, XSD, "organizational-unit_complete.xml");
    }

    @Override
    protected void validate(final OrganizationalUnit obj) throws Exception {
        /*
         * TODO: LMDs for sub-resources
         */

        // OU
        String path = "/escidocOrganizationalUnit:organizational-unit";
        assertResource(path, obj);
        assertDateTime(path + "/@last-modification-date", obj.getLastModificationDate());

        // OU.Properties
        assertNotNull(obj.getProperties());
        path = path + "/escidocOrganizationalUnit:properties";
        assertXLinkResource(path, obj.getProperties());
        assertDateTime(path + "/prop:creation-date", obj.getProperties().getCreationDate());
        assertResource(path + "/srel:created-by", obj.getProperties().getCreatedBy());
        assertResource(path + "/srel:modified-by", obj.getProperties().getModifiedBy());
        assertEnum(path + "/prop:public-status", obj.getProperties().getPublicStatus());
        assertXPath(path + "/prop:public-status-comment", obj.getProperties().getPublicStatusComment());
        assertXPath(path + "/prop:name", obj.getProperties().getName());
        assertXPath(path + "/prop:description", obj.getProperties().getDescription());
        assertNotNull(obj.getProperties().getExternalIds());
        assertEquals(2, obj.getProperties().getExternalIds().size());
        assertXPath(path + "/prop:external-ids/prop:external-id[1]", obj.getProperties().getExternalIds().get(0));
        assertXPath(path + "/prop:external-ids/prop:external-id[2]", obj.getProperties().getExternalIds().get(1));
        assertXPath(path + "/prop:has-children", obj.getProperties().getHasChildren());

        path = "/escidocOrganizationalUnit:organizational-unit";

        // MdRecord
        assertMdRecord(path + "/escidocMetadataRecords:md-records", obj.getMetadataRecords(), 0);

        // Parents
        assertNotNull(obj.getParents());
        assertXLinkList(path + "/escidocOrganizationalUnit:parents", obj.getParents());

        // Parent[1]
        assertResource(path + "/escidocOrganizationalUnit:parents/srel:parent[1]", obj.getParents().get(0));

        // Predecessors
        assertNotNull(obj.getPredecessors());
        assertXLinkList(path + "/escidocOrganizationalUnit:predecessors", obj.getPredecessors());

        // Predecessor[1]
        assertResource(path + "/escidocOrganizationalUnit:predecessors/srel:predecessor[1]",
            obj.getPredecessors().get(0));
        assertEnum(path + "/escidocOrganizationalUnit:predecessors/srel:predecessor[1]/@form", obj
            .getPredecessors().get(0).getForm());
    }

    @Override
    protected void testSubResources(final OrganizationalUnit obj) throws Exception {
        // TODO Auto-generated method stub

    }

    @Override
    protected void testResourceWithoutSubResources(final OrganizationalUnit obj) throws Exception {
        // TODO Auto-generated method stub

    }
}