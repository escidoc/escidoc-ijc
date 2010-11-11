/**
 * 
 */
package de.escidoc.core.test.client.integrationTests.classMapping.oum;

import org.junit.Before;
import org.junit.Test;

import de.escidoc.core.client.Authentication;
import de.escidoc.core.client.OrganizationalUnitHandlerClient;
import de.escidoc.core.client.TransportProtocol;
import de.escidoc.core.client.interfaces.OrganizationalUnitHandlerClientInterface;
import de.escidoc.core.resources.oum.OrganizationalUnit;
import de.escidoc.core.test.client.AbstractParameterizedTestBase;
import de.escidoc.core.test.client.Constants;
import de.escidoc.core.test.client.EscidocClientTestBase;

/**
 * FIXME: TODO
 * 
 * @author MVO
 * 
 */
public class OuParentsTest extends AbstractParameterizedTestBase {

    private Authentication auth;

    private OrganizationalUnitHandlerClientInterface ohc;

    /**
     * 
     * @param transport
     */
    public OuParentsTest(final TransportProtocol transport) {
        super(transport);
    }

    /**
     * 
     * @throws Exception
     */
    @Before
    public void init() throws Exception {
        auth =
            new Authentication(EscidocClientTestBase.DEFAULT_SERVICE_URL,
                Constants.SYSTEM_ADMIN_USER, Constants.SYSTEM_ADMIN_PASSWORD);
        ohc = new OrganizationalUnitHandlerClient(auth.getServiceAddress());
        ohc.setHandle(auth.getHandle());
        ohc.setTransport(transport);
    }

    /**
     * 
     * @throws Exception
     */
    @Test
    public void testParentsUpdate() throws Exception {

    }

    private OrganizationalUnit createOU() {
        // final String ouName =
        // "Generic Organizational Unit " + System.currentTimeMillis();
        // final String ouDescription = "Description of Organizational Unit.";
        //
        // OrganizationalUnit organizationalUnit = new OrganizationalUnit();
        // Properties properties = new Properties();
        // properties.setName("Organizational_Unit_Test_Name");
        // organizationalUnit.setProperties(properties);
        //
        // MetadataRecords mdRecords = new MetadataRecords();
        //
        // MetadataRecord mdRecord =
        // createMdRecordDC("escidoc", "organization-details", ouName,
        // ouDescription);
        //
        // mdRecords.add(mdRecord);
        // organizationalUnit.setMetadataRecords(mdRecords);
        //
        // // create parent OU
        // OrganizationalUnit parentOU = ohc.create(organizationalUnit);
        //
        // // create child OU
        // Parents parents = new Parents();
        // parents.addParentRef(new Parent(parentOU.getObjid()));
        // organizationalUnit.setParents(parents);
        //
        // OrganizationalUnit childOU = ohc.create(organizationalUnit);
        return null;
    }
}