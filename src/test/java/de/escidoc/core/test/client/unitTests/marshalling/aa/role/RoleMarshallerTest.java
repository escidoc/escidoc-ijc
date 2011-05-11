package de.escidoc.core.test.client.unitTests.marshalling.aa.role;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import de.escidoc.core.client.TransportProtocol;
import de.escidoc.core.resources.aa.role.Role;
import de.escidoc.core.test.client.unitTests.marshalling.MarshallerTestBase;

public class RoleMarshallerTest extends MarshallerTestBase<Role> {

    private static final String BASE = "aa/role";

    private static final String XSD = "0.5";

    /**
     * @param transport
     * @throws IOException
     * @throws ParserConfigurationException
     * @throws SAXException
     */
    public RoleMarshallerTest(final TransportProtocol transport)
        throws IOException, ParserConfigurationException, SAXException {
        super(Role.class, BASE, XSD, "role_complete.xml", transport);
    }

    @Override
    protected void validate(final Role obj) throws Exception {
        /*
         * TODO: LMDs for sub-resources
         */

        // Role
        assertResource("/role:role", obj);
        assertDateTime("/role:role/@last-modification-date",
            obj.getLastModificationDate());

        // RoleProperties
        String path = "/role:role/role:properties";
        assertNotNull(obj.getProperties());

        assertDateTime(path + "/prop:creation-date", obj
            .getProperties().getCreationDate());
        assertResource(path + "/srel:created-by", obj
            .getProperties().getCreatedBy());
        assertResource(path + "/srel:modified-by", obj
            .getProperties().getModifiedBy());
        assertXPath(path + "/prop:name", obj.getProperties().getName());
        assertXPath(path + "/prop:description", obj
            .getProperties().getDescription());

        // Scope
        path = "/role:role/role:scope";
        assertNotNull(obj.getScope());
        assertNotNull(obj.getScope().getScopeDefinitions());

        assertXPath(path + "/@unlimited", obj.getScope().isUnlimited());

        assertEnum(path + "/role:scope-def[1]/@resource-type", obj
            .getScope().getScopeDefinitions().get(0).getResourceType());
        assertXPath(path + "/role:scope-def[1]/@relation-attribute-id", obj
            .getScope().getScopeDefinitions().get(0).getRelationAttributeId());
        assertEnum(path + "/role:scope-def[1]/@relation-attribute-object-type",
            obj.getScope().getScopeDefinitions().get(0)
                .getRelationAttributeObjectType());

        // XACML
        assertNotNull(obj.getPolicyOrPolicySet());
        // TODO validate DOM
    }

    @Override
    protected void testSubResources(final Role obj) throws Exception {
        // TODO Auto-generated method stub
    }

    @Override
    protected void testResourceWithoutSubResources(final Role obj)
        throws Exception {
        // TODO Auto-generated method stub
    }
}