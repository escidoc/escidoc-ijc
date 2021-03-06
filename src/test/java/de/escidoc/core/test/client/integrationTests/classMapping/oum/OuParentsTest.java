/**
 * 
 */
package de.escidoc.core.test.client.integrationTests.classMapping.oum;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import de.escidoc.core.client.Authentication;
import de.escidoc.core.client.OrganizationalUnitHandlerClient;
import de.escidoc.core.client.exceptions.EscidocException;
import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.client.exceptions.TransportException;
import de.escidoc.core.client.exceptions.application.violated.OptimisticLockingException;
import de.escidoc.core.client.interfaces.OrganizationalUnitHandlerClientInterface;
import de.escidoc.core.resources.common.MetadataRecord;
import de.escidoc.core.resources.common.MetadataRecords;
import de.escidoc.core.resources.oum.OrganizationalUnit;
import de.escidoc.core.resources.oum.OrganizationalUnitProperties;
import de.escidoc.core.resources.oum.Parent;
import de.escidoc.core.resources.oum.Parents;
import de.escidoc.core.test.client.EscidocClientTestBase;

/**
 * 
 * 
 * @author MVO
 * 
 */
public class OuParentsTest {

    private Authentication auth;

    private OrganizationalUnitHandlerClientInterface ohc;

    /**
     * 
     * @throws Exception
     */
    @Before
    public void init() throws Exception {
        auth =
            new Authentication(EscidocClientTestBase.getDefaultInfrastructureURL(),
                EscidocClientTestBase.SYSTEM_ADMIN_USER, EscidocClientTestBase.SYSTEM_ADMIN_PASSWORD);
        ohc = new OrganizationalUnitHandlerClient(auth.getServiceAddress());
        ohc.setHandle(auth.getHandle());
    }

    /**
     * 
     * @throws Exception
     */
    @After
    public void post() throws Exception {
        if (auth != null)
            auth.logout();
    }

    /**
     * Before:<br/>
     * <- OU parent<br/>
     * <- OU child<br/>
     * <br/>
     * After:<br/>
     * <- OU parent <- OU child
     * 
     * @throws Exception
     */
    @Test
    public void testParentsUpdate01() throws Exception {

        final OrganizationalUnit ouParent = createOU("parent OU @ " + System.currentTimeMillis(), "parent description");
        final OrganizationalUnit ouChild = createOU("child OU @ " + System.currentTimeMillis(), "child description");

        final Parents p = new Parents();
        p.add(new Parent(ouParent.getObjid()));

        final Parents newP = ohc.updateParents(ouChild, p);

        // retrieve child
        final OrganizationalUnit ouChildNew = ohc.retrieve(ouChild.getObjid());
        // test parents
        assertNotNull(ouChildNew.getParents());
        assertTrue(ouChildNew.getParents().size() == 1);
        assertEquals(p.get(0).getObjid(), ouChildNew.getParents().get(0).getObjid());

        // test if parents children got updated
        final List<OrganizationalUnit> ouChildren = ohc.retrieveChildObjectsAsList(ouParent.getObjid());

        assertTrue(ouChildren.size() == 1);
        assertEquals(ouChildNew.getObjid(), ouChildren.get(0).getObjid());
    }

    /**
     * Before:<br/>
     * <code>
     * A<br/>
     * |_B<br/>
     * &nbsp;&nbsp;|_C
     * </code><br/>
     * <br/>
     * After:<br/>
     * <code>
     * A<br/>
     * |_B<br/>
     * |_C
     * </code>
     * 
     * @throws Exception
     */
    @Test
    public void testParentsUpdate02() throws Exception {

        final OrganizationalUnit A = createOU("OU A @ " + System.currentTimeMillis(), "parent description");
        final OrganizationalUnit B = createOU("OU B @ " + System.currentTimeMillis(), "child description");
        final OrganizationalUnit C = createOU("OU C @ " + System.currentTimeMillis(), "child description");

        final Parents B2A = new Parents();
        B2A.add(new Parent(A.getObjid()));

        final Parents C2B = new Parents();
        C2B.add(new Parent(B.getObjid()));

        // initial constellation
        ohc.updateParents(B, B2A);
        final Parents newCParents = ohc.updateParents(C, C2B);

        // test case: A <- C
        final Parents C2A = new Parents();
        C2A.add(new Parent(A.getObjid()));
        // this is required because C has been changed before
        C2A.setLastModificationDate(newCParents.getLastModificationDate());

        ohc.updateParents(C, C2A);

        // TODO asserts
    }

    /**
     * Before:<br/>
     * <code>
     * A<br/>
     * |_B<br/>
     * &nbsp;&nbsp;|_C
     * </code><br/>
     * <br/>
     * After:<br/>
     * <code>
     * A<br/>
     * |_B<br/>
     * |_C
     * </code>
     * 
     * @throws Exception
     */
    @Test(expected = OptimisticLockingException.class)
    public void testParentsUpdate03() throws Exception {

        final OrganizationalUnit A = createOU("OU A @ " + System.currentTimeMillis(), "parent description");
        final OrganizationalUnit B = createOU("OU B @ " + System.currentTimeMillis(), "child description");
        final OrganizationalUnit C = createOU("OU C @ " + System.currentTimeMillis(), "child description");

        final Parents B2A = new Parents();
        B2A.add(new Parent(A.getObjid()));

        final Parents C2B = new Parents();
        C2B.add(new Parent(B.getObjid()));

        // initial constellation
        ohc.updateParents(B, B2A);
        final Parents newCParents = ohc.updateParents(C, C2B);

        // test case: A <- C
        final Parents C2A = new Parents();
        C2A.add(new Parent(A.getObjid()));
        /*
         * Either LMD of C2A should be set to the LMD of newCParents or instead
         * of calling the updateParents method using the old C, we could call
         * this method using an updated C by retrieving it before.
         */
        ohc.updateParents(C, C2A);
    }

    /**
     * @param name
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     * @throws ParserConfigurationException
     */
    private OrganizationalUnit createOU(final String name, final String description) throws EscidocException,
        InternalClientException, TransportException, ParserConfigurationException {

        final OrganizationalUnit organizationalUnit = new OrganizationalUnit();
        final OrganizationalUnitProperties properties = new OrganizationalUnitProperties();
        properties.setName(name);
        organizationalUnit.setProperties(properties);

        final MetadataRecords mdRecords = new MetadataRecords();

        mdRecords.add(createMdRecordDC("escidoc", "foo", name, description));
        organizationalUnit.setMetadataRecords(mdRecords);

        return ohc.create(organizationalUnit);
    }

    /**
     * 
     * @param mdRecordName
     * @param rootElementName
     * @param title
     * @param description
     * @return
     * @throws ParserConfigurationException
     */
    private MetadataRecord createMdRecordDC(
        final String mdRecordName, final String rootElementName, final String title, final String description)
        throws ParserConfigurationException {

        // md-record
        final MetadataRecord mdRecord = new MetadataRecord(mdRecordName);

        final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        factory.setCoalescing(true);
        factory.setValidating(true);
        final DocumentBuilder builder = factory.newDocumentBuilder();

        final Document doc = builder.newDocument();
        final Element mdRecordContent = doc.createElementNS(null, rootElementName);
        mdRecord.setContent(mdRecordContent);

        // title
        final Element titleElmt = doc.createElementNS("http://purl.org/dc/elements/1.1/", "title");
        titleElmt.setPrefix("dc");
        titleElmt.setTextContent(title);
        mdRecordContent.appendChild(titleElmt);

        // dc:description
        final Element descriptionElmt = doc.createElementNS("http://purl.org/dc/elements/1.1/", "description");
        descriptionElmt.setPrefix("dc");
        descriptionElmt.setTextContent(description);
        mdRecordContent.appendChild(descriptionElmt);
        mdRecord.setContent(mdRecordContent);

        return mdRecord;
    }
}