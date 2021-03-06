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
package de.escidoc.core.test.client.integrationTests.classMapping.oum;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import de.escidoc.core.client.Authentication;
import de.escidoc.core.client.OrganizationalUnitHandlerClient;
import de.escidoc.core.client.exceptions.application.invalid.InvalidXmlException;
import de.escidoc.core.client.exceptions.application.invalid.XmlSchemaValidationException;
import de.escidoc.core.client.exceptions.application.missing.MissingElementValueException;
import de.escidoc.core.client.exceptions.application.missing.MissingMdRecordException;
import de.escidoc.core.client.interfaces.OrganizationalUnitHandlerClientInterface;
import de.escidoc.core.resources.common.MetadataRecord;
import de.escidoc.core.resources.common.MetadataRecords;
import de.escidoc.core.resources.oum.OrganizationalUnit;
import de.escidoc.core.resources.oum.OrganizationalUnitProperties;
import de.escidoc.core.resources.oum.Parent;
import de.escidoc.core.resources.oum.Parents;
import de.escidoc.core.resources.oum.Predecessor;
import de.escidoc.core.resources.oum.PredecessorForm;
import de.escidoc.core.resources.oum.Predecessors;
import de.escidoc.core.test.client.EscidocClientTestBase;
import de.escidoc.core.test.client.util.Asserts;

/**
 * Test create OrganizationalUnit.
 * 
 * @author SWA
 * 
 */
public class OuCreateTest {

    private Authentication auth;

    private OrganizationalUnitHandlerClientInterface ohc;

    @Before
    public void init() throws Exception {
        auth =
            new Authentication(EscidocClientTestBase.getDefaultInfrastructureURL(),
                EscidocClientTestBase.SYSTEM_ADMIN_USER, EscidocClientTestBase.SYSTEM_ADMIN_PASSWORD);
        ohc = new OrganizationalUnitHandlerClient(auth.getServiceAddress());
        ohc.setHandle(auth.getHandle());
    }

    @After
    public void post() throws Exception {
        if (auth != null)
            auth.logout();
    }

    /**
     * Test if the right exception is thrown if calling create with an
     * incomplete OrganizationalUnit.
     * 
     * md-record is missing
     * 
     * @throws Exception
     *             Thrown if no or wrong exception is caught from the framework.
     */
    @Test(expected = XmlSchemaValidationException.class)
    public void testCreateOrganizationalUnit01() throws Exception {
        final OrganizationalUnit organizationalUnit = new OrganizationalUnit();
        ohc.create(organizationalUnit);
    }

    /**
     * Test if the right exception is thrown if calling create with an
     * incomplete OrganizationalUnit.
     * 
     * md-record is set, but name is still missing
     * 
     * @throws Exception
     *             Thrown if no or wrong exception is caught from the framework.
     */
    @Test(expected = MissingElementValueException.class)
    public void testCreateOrganizationalUnit02() throws Exception {
        final OrganizationalUnit organizationalUnit = new OrganizationalUnit();
        final MetadataRecords mdRecords = new MetadataRecords();

        final MetadataRecord mdRecord = new MetadataRecord("escidoc");
        final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        final DocumentBuilder builder = factory.newDocumentBuilder();
        final Document doc = builder.newDocument();
        final Element element = doc.createElementNS(null, "myMdRecord");

        mdRecord.setContent(element);
        mdRecords.add(mdRecord);
        organizationalUnit.setMetadataRecords(mdRecords);

        ohc.create(organizationalUnit);
    }

    /**
     * Test if the right exception is thrown if calling create with an
     * incomplete OrganizationalUnit.
     * 
     * empty properties
     * 
     * @throws Exception
     *             Thrown if no or wrong exception is caught from the framework.
     */
    @Test(expected = MissingElementValueException.class)
    public void testCreateOrganizationalUnit03() throws Exception {
        final OrganizationalUnit organizationalUnit = new OrganizationalUnit();
        final OrganizationalUnitProperties properties = new OrganizationalUnitProperties();
        organizationalUnit.setProperties(properties);

        final MetadataRecords mdRecords = new MetadataRecords();

        final MetadataRecord mdRecord = new MetadataRecord("escidoc");
        final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        final DocumentBuilder builder = factory.newDocumentBuilder();
        final Document doc = builder.newDocument();
        final Element element = doc.createElementNS(null, "myMdRecord");

        mdRecord.setContent(element);
        mdRecords.add(mdRecord);
        organizationalUnit.setMetadataRecords(mdRecords);

        ohc.create(organizationalUnit);
    }

    /**
     * Test if the right exception is thrown if calling create with an
     * incomplete OrganizationalUnit.
     * 
     * empty properties
     * 
     * @throws Exception
     *             Thrown if no or wrong exception is caught from the framework.
     */
    @Test(expected = MissingElementValueException.class)
    public void testCreateOrganizationalUnit04() throws Exception {
        final OrganizationalUnit organizationalUnit = new OrganizationalUnit();
        final OrganizationalUnitProperties properties = new OrganizationalUnitProperties();
        properties.setName("Organizational_Unit_Test_Name");
        organizationalUnit.setProperties(properties);

        final MetadataRecords mdRecords = new MetadataRecords();

        final MetadataRecord mdRecord = new MetadataRecord("escidoc");
        final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        final DocumentBuilder builder = factory.newDocumentBuilder();
        final Document doc = builder.newDocument();
        final Element element = doc.createElementNS(null, "myMdRecord");

        mdRecord.setContent(element);
        mdRecords.add(mdRecord);
        organizationalUnit.setMetadataRecords(mdRecords);

        ohc.create(organizationalUnit);
    }

    /**
     * Test successful creation of OrganizationalUnit. The md-record is defined
     * as String and parsed by DocumentBuilder.
     * 
     * @throws Exception
     *             Thrown if successful creation failed.
     */
    @Test
    public void testCreateOrganizationalUnit05() throws Exception {

        final String ouName = "Generic Organizational Unit";

        final OrganizationalUnit organizationalUnit = new OrganizationalUnit();
        final OrganizationalUnitProperties properties = new OrganizationalUnitProperties();
        organizationalUnit.setProperties(properties);

        final MetadataRecords mdRecords = new MetadataRecords();
        final MetadataRecord mdRecord = new MetadataRecord("escidoc");

        final String str =
            "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + "<ou:organization-details "
                + "xmlns:ou=\"http://escidoc.mpg.de/metadataprofile/schema/0.1/organization\">\n"
                + "<dc:title xmlns:dc=\"http://purl.org/dc/elements/1.1/\">" + ouName + "</dc:title>\n"
                + "</ou:organization-details>";
        final InputStream in = new ByteArrayInputStream(str.getBytes());

        final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        final DocumentBuilder builder = factory.newDocumentBuilder();
        final Document doc = builder.parse(in);
        mdRecord.setContent(doc.getDocumentElement());
        mdRecords.add(mdRecord);

        organizationalUnit.setMetadataRecords(mdRecords);

        // create OU
        final OrganizationalUnit createdOU = ohc.create(organizationalUnit);

        // assert values of created OU
        assertEquals("Name of OU wrong", createdOU.getProperties().getName(), ouName);
    }

    /**
     * Test successful creation of OrganizationalUnit.
     * 
     * empty properties
     * 
     * @throws Exception
     *             Thrown if successful creation failed.
     */
    @Test
    public void testCreateOrganizationalUnit06() throws Exception {

        final String ouName = "name" + System.currentTimeMillis();
        final String ouDescription = "Just a generic organizational unit.";

        final OrganizationalUnit organizationalUnit = new OrganizationalUnit();
        final OrganizationalUnitProperties properties = new OrganizationalUnitProperties();
        properties.setName("Organizational_Unit_Test_Name");
        organizationalUnit.setProperties(properties);

        // md-record "escidoc"
        final MetadataRecord mdRecord = new MetadataRecord("escidoc");

        final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        factory.setCoalescing(true);
        factory.setValidating(true);
        final DocumentBuilder builder = factory.newDocumentBuilder();

        final Document doc = builder.newDocument();
        final Element mdRecordContent = doc.createElementNS(null, "myMdRecord");
        mdRecord.setContent(mdRecordContent);

        // title
        final Element title = doc.createElementNS("http://purl.org/dc/elements/1.1/", "title");
        title.setTextContent(ouName);
        mdRecordContent.appendChild(title);

        // dc:description
        final Element description = doc.createElementNS("http://purl.org/dc/elements/1.1/", "description");
        description.setTextContent(ouDescription);
        mdRecordContent.appendChild(description);
        mdRecord.setContent(mdRecordContent);

        final MetadataRecords mdRecords = new MetadataRecords();
        mdRecords.add(mdRecord);

        organizationalUnit.setMetadataRecords(mdRecords);

        // create OU
        final OrganizationalUnit createdOU = ohc.create(organizationalUnit);

        // assert values of created OU
        assertEquals("Name of OU wrong", createdOU.getProperties().getName(), ouName);
        assertEquals("Description of OU wrong", createdOU.getProperties().getDescription(), ouDescription);
    }

    /**
     * Test successful creation of two OrganizationalUnits, whereas one OU is
     * parent of the other. The md-record is defined as String and parsed by
     * DocumentBuilder.
     * 
     * @throws Exception
     *             Thrown if successful creation failed.
     */
    @Test
    public void testCreateOrganizationalUnit07() throws Exception {

        final String ouName = "Generic Organizational Unit " + System.currentTimeMillis();
        final String ouDescription = "Description of Organizational Unit.";

        final OrganizationalUnit organizationalUnit = new OrganizationalUnit();
        final OrganizationalUnitProperties properties = new OrganizationalUnitProperties();
        properties.setName("Organizational_Unit_Test_Name");
        organizationalUnit.setProperties(properties);

        final MetadataRecords mdRecords = new MetadataRecords();

        final MetadataRecord mdRecord = createMdRecordDC("escidoc", "organization-details", ouName, ouDescription);

        mdRecords.add(mdRecord);
        organizationalUnit.setMetadataRecords(mdRecords);

        // create parent OU
        final OrganizationalUnit parentOU = ohc.create(organizationalUnit);

        // create child OU
        final Parents parents = new Parents();
        parents.add(new Parent(parentOU.getObjid()));
        organizationalUnit.setParents(parents);

        final OrganizationalUnit childOU = ohc.create(organizationalUnit);

        // assert values of created OU
        assertEquals("Name of OU wrong", childOU.getProperties().getName(), ouName);
        assertEquals("Description of OU wrong", childOU.getProperties().getDescription(), ouDescription);

        assertEquals("Number of parents wrong", childOU.getParents().size(), parents.size());
        assertEquals("Wrong parent objid", childOU.getParents().iterator().next().getObjid(), parents
            .iterator().next().getObjid());

    }

    /**
     * Test creating Organizational Unit with predecessors. Two Organizational
     * Units are created whereas the first OU is set as Predecessor of the
     * second.
     * 
     * @throws Exception
     *             Thrown if no or wrong exception is caught from the framework.
     */
    @Test
    public void testOuPredecessor05() throws Exception {

        final String ou1Name = "Test OU 1 " + System.currentTimeMillis();
        final String ou1Description = "The fist OU of a test. ";

        final String ou2Name = "Test OU 2 " + System.currentTimeMillis();
        final String ou2Description = "The second OU of a test. " + System.currentTimeMillis();

        // OU 1
        OrganizationalUnit ou1 = new OrganizationalUnit();
        final OrganizationalUnitProperties properties = new OrganizationalUnitProperties();
        ou1.setProperties(properties);

        MetadataRecord mdRecord = createMdRecordDC("escidoc", "myMdRecord", ou1Name, ou1Description);

        MetadataRecords mdRecords = new MetadataRecords();
        mdRecords.add(mdRecord);

        ou1.setMetadataRecords(mdRecords);

        ou1 = ohc.create(ou1);

        // OU 2
        OrganizationalUnit ou2 = new OrganizationalUnit();
        final OrganizationalUnitProperties properties2 = new OrganizationalUnitProperties();
        ou2.setProperties(properties2);

        // set OU1 Predecessor of OU2
        final Predecessors predecessors = new Predecessors();

        final Predecessor predecessor = new Predecessor(ou1.getObjid(), PredecessorForm.REPLACEMENT);
        predecessors.add(predecessor);
        ou2.setPredecessors(predecessors);

        mdRecord = createMdRecordDC("escidoc", "myMdRecord", ou2Name, ou2Description);

        mdRecords = new MetadataRecords();
        mdRecords.add(mdRecord);

        ou2.setMetadataRecords(mdRecords);

        ou2 = ohc.create(ou2);

        // assert OU values
        assertEquals("Wrong predecessor", ou2.getPredecessors().iterator().next().getObjid(), ou1.getObjid());
        assertEquals("Wrong predecessor", 1, ou2.getPredecessors().size());
    }

    /**
     * Test create and retrieve of parent Organizational Units.
     * 
     * @throws Exception
     *             Thrown if anythings failed.
     */
    @Test
    public void testParentObjects01() throws Exception {

        final String ou1Name = "Test OU 1 " + System.currentTimeMillis();
        final String ou1Description = "The fist OU of a test. ";

        final String ou2Name = "Test OU 2 " + System.currentTimeMillis();
        final String ou2Description = "The second OU of a test. " + System.currentTimeMillis();

        final String ou3Name = "Test OU 3 " + System.currentTimeMillis();
        final String ou3Description = "The third OU of a test. " + System.currentTimeMillis();

        final String ou4Name = "Test OU 4 " + System.currentTimeMillis();
        final String ou4Description = "The forth OU of a test. " + System.currentTimeMillis();

        // create OU 1
        OrganizationalUnit ou1 = new OrganizationalUnit();
        final OrganizationalUnitProperties properties = new OrganizationalUnitProperties();
        ou1.setProperties(properties);
        MetadataRecord mdRecord = createMdRecordDC("escidoc", "myMdRecord", ou1Name, ou1Description);
        MetadataRecords mdRecords = new MetadataRecords();
        mdRecords.add(mdRecord);
        ou1.setMetadataRecords(mdRecords);
        ou1 = ohc.create(ou1);

        // create OU 2
        OrganizationalUnit ou2 = new OrganizationalUnit();
        final OrganizationalUnitProperties properties2 = new OrganizationalUnitProperties();
        ou2.setProperties(properties2);
        mdRecord = createMdRecordDC("escidoc", "myMdRecord", ou2Name, ou2Description);
        mdRecords = new MetadataRecords();
        mdRecords.add(mdRecord);
        ou2.setMetadataRecords(mdRecords);
        ou2 = ohc.create(ou2);

        // create OU 3
        OrganizationalUnit ou3 = new OrganizationalUnit();
        final OrganizationalUnitProperties properties3 = new OrganizationalUnitProperties();
        ou3.setProperties(properties3);
        mdRecord = createMdRecordDC("escidoc", "myMdRecord", ou3Name, ou3Description);
        mdRecords = new MetadataRecords();
        mdRecords.add(mdRecord);
        ou3.setMetadataRecords(mdRecords);
        ou3 = ohc.create(ou3);

        // create OU 4
        OrganizationalUnit ou4 = new OrganizationalUnit();
        final OrganizationalUnitProperties properties4 = new OrganizationalUnitProperties();

        final Parents parents = new Parents();
        parents.add(new Parent(ou1.getObjid()));
        parents.add(new Parent(ou2.getObjid()));
        parents.add(new Parent(ou3.getObjid()));
        ou4.setParents(parents);

        ou4.setProperties(properties4);
        mdRecord = createMdRecordDC("escidoc", "myMdRecord", ou4Name, ou4Description);
        mdRecords = new MetadataRecords();
        mdRecords.add(mdRecord);
        ou4.setMetadataRecords(mdRecords);
        ou4 = ohc.create(ou4);

        // retrieve parent objects
        final List<OrganizationalUnit> ouList = ohc.retrieveParentObjectsAsList(ou4.getObjid());

        assertEquals("Wrong number ob OUs in List", 3, ouList.size());

        // retrieve parent references
        final Parents parentRefs = ohc.retrieveParents(ou4.getObjid());
        assertEquals("Wrong number ob OUs in List", 3, parentRefs.size());
    }

    /**
     * Test deletion of non-default md-record.
     * 
     * @throws Exception
     */
    @Test
    public void testMDRecordsDeletion01() throws Exception {
        final String ouName = "Generic Organizational Unit " + System.currentTimeMillis();
        final String ouDescription = "Description of Organizational Unit.";

        final OrganizationalUnit organizationalUnit = new OrganizationalUnit();
        final OrganizationalUnitProperties properties = new OrganizationalUnitProperties();
        properties.setName("Organizational_Unit_Test_Name");
        organizationalUnit.setProperties(properties);

        final MetadataRecords mdRecords = new MetadataRecords();

        final MetadataRecord mdRecord1 = createMdRecordDC("escidoc", "organization-details", ouName, ouDescription);

        final MetadataRecord mdRecord2 = createMdRecordDC("mytest", "organization-details2", ouName, ouDescription);

        mdRecords.add(mdRecord1);
        mdRecords.add(mdRecord2);
        organizationalUnit.setMetadataRecords(mdRecords);

        // create parent OU
        final OrganizationalUnit ou = ohc.create(organizationalUnit);

        // assert values of created OU
        assertEquals("Name of OU wrong", ou.getProperties().getName(), ouName);
        assertEquals("Description of OU wrong", ou.getProperties().getDescription(), ouDescription);
        assertEquals(2, ou.getMetadataRecords().size());

        // delete md-record
        ou.getMetadataRecords().del(mdRecord2.getName());

        final OrganizationalUnit updatedOU = ohc.update(ou);

        // assert values of created OU
        assertEquals("Name of OU wrong", updatedOU.getProperties().getName(), ouName);
        assertEquals("Description of OU wrong", updatedOU.getProperties().getDescription(), ouDescription);
        assertEquals(1, updatedOU.getMetadataRecords().size());
    }

    /**
     * Test deletion of required default md-record.
     * 
     * TODO: Currently fails. See #INFR-1016.
     * 
     * @throws Exception
     */
    @Ignore("INFR-1016")
    @Test(expected = MissingMdRecordException.class)
    public void testMDRecordsDeletion02() throws Exception {
        final String ouName = "Generic Organizational Unit " + System.currentTimeMillis();
        final String ouDescription = "Description of Organizational Unit.";

        final OrganizationalUnit organizationalUnit = new OrganizationalUnit();
        final OrganizationalUnitProperties properties = new OrganizationalUnitProperties();
        properties.setName("Organizational_Unit_Test_Name");
        organizationalUnit.setProperties(properties);

        final MetadataRecords mdRecords = new MetadataRecords();

        final MetadataRecord mdRecord1 = createMdRecordDC("escidoc", "organization-details", ouName, ouDescription);

        final MetadataRecord mdRecord2 = createMdRecordDC("mytest", "organization-details2", ouName, ouDescription);

        mdRecords.add(mdRecord1);
        mdRecords.add(mdRecord2);
        organizationalUnit.setMetadataRecords(mdRecords);

        // create parent OU
        final OrganizationalUnit ou = ohc.create(organizationalUnit);

        // assert values of created OU
        assertEquals("Name of OU wrong", ou.getProperties().getName(), ouName);
        assertEquals("Description of OU wrong", ou.getProperties().getDescription(), ouDescription);
        assertEquals(2, ou.getMetadataRecords().size());

        // delete md-record
        ou.getMetadataRecords().del(mdRecord1.getName());

        ohc.update(ou);
    }

    /**
     * Test deletion of all md-record entries.
     * 
     * @throws Exception
     */
    @Ignore("INFR-1016")
    @Test(expected = InvalidXmlException.class)
    public void testMDRecordsDeletion03() throws Exception {
        final String ouName = "Generic Organizational Unit " + System.currentTimeMillis();
        final String ouDescription = "Description of Organizational Unit.";

        final OrganizationalUnit organizationalUnit = new OrganizationalUnit();
        final OrganizationalUnitProperties properties = new OrganizationalUnitProperties();
        properties.setName("Organizational_Unit_Test_Name");
        organizationalUnit.setProperties(properties);

        final MetadataRecords mdRecords = new MetadataRecords();

        final MetadataRecord mdRecord1 = createMdRecordDC("escidoc", "organization-details", ouName, ouDescription);

        final MetadataRecord mdRecord2 = createMdRecordDC("mytest", "organization-details2", ouName, ouDescription);

        mdRecords.add(mdRecord1);
        mdRecords.add(mdRecord2);
        organizationalUnit.setMetadataRecords(mdRecords);

        // create parent OU
        final OrganizationalUnit ou = ohc.create(organizationalUnit);

        // assert values of created OU
        assertEquals("Name of OU wrong", ou.getProperties().getName(), ouName);
        assertEquals("Description of OU wrong", ou.getProperties().getDescription(), ouDescription);
        assertEquals(2, ou.getMetadataRecords().size());

        // delete md-record
        ou.getMetadataRecords().del(mdRecord1.getName());
        ou.getMetadataRecords().del(mdRecord2.getName());

        ohc.update(ou);
    }

    /**
     * Test deletion of md-records.
     * 
     * @throws Exception
     */
    @Test(expected = XmlSchemaValidationException.class)
    public void testMDRecordsDeletion04() throws Exception {
        final String ouName = "Generic Organizational Unit " + System.currentTimeMillis();
        final String ouDescription = "Description of Organizational Unit.";

        final OrganizationalUnit organizationalUnit = new OrganizationalUnit();
        final OrganizationalUnitProperties properties = new OrganizationalUnitProperties();
        properties.setName("Organizational_Unit_Test_Name");
        organizationalUnit.setProperties(properties);

        final MetadataRecords mdRecords = new MetadataRecords();

        final MetadataRecord mdRecord1 = createMdRecordDC("escidoc", "organization-details", ouName, ouDescription);

        final MetadataRecord mdRecord2 = createMdRecordDC("mytest", "organization-details2", ouName, ouDescription);

        mdRecords.add(mdRecord1);
        mdRecords.add(mdRecord2);
        organizationalUnit.setMetadataRecords(mdRecords);

        // create parent OU
        final OrganizationalUnit ou = ohc.create(organizationalUnit);

        // assert values of created OU
        assertEquals("Name of OU wrong", ou.getProperties().getName(), ouName);
        assertEquals("Description of OU wrong", ou.getProperties().getDescription(), ouDescription);
        assertEquals(2, ou.getMetadataRecords().size());

        // delete md-records
        ou.setMetadataRecords(null);

        ohc.update(ou);
    }

    /**
     * Test dealing with multiple MetadataRecords of OrganizationalUnit.
     * 
     * @throws Exception
     *             Thrown if metadata records handling failed.
     */
    @Test
    public void testMultipleMetadataRecords01() throws Exception {
        final OrganizationalUnit organizationalUnit = new OrganizationalUnit();
        final OrganizationalUnitProperties properties = new OrganizationalUnitProperties();
        organizationalUnit.setProperties(properties);

        final MetadataRecord mdRecord =
            createMdRecordDC("escidoc", "myMdRecord", "Test OU " + System.currentTimeMillis(),
                "The fist OU of a test. ");

        final MetadataRecord mdRecord2 =
            createMdRecordDC("md-record2", "mySecondMdRecord", "Test OU " + System.currentTimeMillis(),
                "22222222222222222222222");

        final MetadataRecord mdRecord3 =
            createMdRecordDC("md-record3", "myThirdMdRecord", "Test OU " + System.currentTimeMillis(),
                "some-other-stuff");

        final MetadataRecords mdRecords = new MetadataRecords();
        mdRecords.add(mdRecord);
        mdRecords.add(mdRecord2);
        mdRecords.add(mdRecord3);

        organizationalUnit.setMetadataRecords(mdRecords);

        final OrganizationalUnit createdOrganizationalUnit = ohc.create(organizationalUnit);

        // compare the new created OrganizationalUnit with the
        // OrganizationalUnit from the request
        final String objId = createdOrganizationalUnit.getObjid();
        assertNotNull("Object id is null", objId);

        Asserts
            .assertMdRecords(organizationalUnit.getMetadataRecords(), createdOrganizationalUnit.getMetadataRecords());

        // now delete some metadataRecords
        createdOrganizationalUnit.getMetadataRecords().del("md-record2");
        final OrganizationalUnit updatedOrganizationalUnit1 = ohc.update(createdOrganizationalUnit);

        assertNotNull("Object id is null", objId);

        Asserts.assertMdRecords(createdOrganizationalUnit.getMetadataRecords(), updatedOrganizationalUnit1
            .getMetadataRecords());
    }

    /**
     * Creates an Metadata Record with DC content.
     * 
     * @param mdRecordName
     *            Name of the MdRecord
     * @param rootElementName
     *            Name of the root element of the MdRecord content
     * @param title
     *            The title which is to set in the DC metadata
     * @param description
     *            The description which is to set in the DC metadata
     * @return The MetadataRecord with the given values
     * @throws ParserConfigurationException
     *             If instantiation of DocumentBuilder fail
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
