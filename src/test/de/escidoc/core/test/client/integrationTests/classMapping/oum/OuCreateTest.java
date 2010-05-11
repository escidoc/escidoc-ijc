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
import static org.junit.Assert.fail;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import de.escidoc.core.client.OrganizationalUnitHandlerClient;
import de.escidoc.core.client.exceptions.application.invalid.XmlSchemaValidationException;
import de.escidoc.core.client.exceptions.application.missing.MissingElementValueException;
import de.escidoc.core.resources.ResourceRef;
import de.escidoc.core.resources.common.MetadataRecord;
import de.escidoc.core.resources.common.MetadataRecords;
import de.escidoc.core.resources.oum.OrganizationalUnit;
import de.escidoc.core.resources.oum.Parents;
import de.escidoc.core.resources.oum.Predecessor;
import de.escidoc.core.resources.oum.PredecessorForm;
import de.escidoc.core.resources.oum.Predecessors;
import de.escidoc.core.resources.oum.Properties;
import de.escidoc.core.test.client.Constants;
import de.escidoc.core.test.client.EscidocClientTestBase;
import de.escidoc.core.test.client.util.Asserts;

/**
 * Test create OrganizationalUnit.
 * 
 * @author SWA
 * 
 */
public class OuCreateTest {

    /**
     * Test if the right exception is thrown if calling create with an
     * incomplete OrganizationalUnit.
     * 
     * md-record is missing
     * 
     * @throws Exception
     *             Thrown if no or wrong exception is caught from the framework.
     */
    @Test
    public void testCreateOrganizationalUnit01() throws Exception {

        OrganizationalUnitHandlerClient cc =
            new OrganizationalUnitHandlerClient();
        cc.login(EscidocClientTestBase.DEFAULT_SERVICE_URL,
            Constants.SYSTEM_ADMIN_USER, Constants.SYSTEM_ADMIN_PASSWORD);

        OrganizationalUnit organizationalUnit = new OrganizationalUnit();
        try {
            cc.create(organizationalUnit);
            fail("Missing Exception");
        }
        catch (Exception e) {
            Class<?> ec = XmlSchemaValidationException.class;
            EscidocClientTestBase.assertExceptionType(ec.getName()
                + " expected.", ec, e);

        }
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
    @Test
    public void testCreateOrganizationalUnit02() throws Exception {

        OrganizationalUnitHandlerClient cc =
            new OrganizationalUnitHandlerClient();
        cc.login(EscidocClientTestBase.DEFAULT_SERVICE_URL,
            Constants.SYSTEM_ADMIN_USER, Constants.SYSTEM_ADMIN_PASSWORD);

        OrganizationalUnit organizationalUnit = new OrganizationalUnit();
        MetadataRecords mdRecords = new MetadataRecords();

        MetadataRecord mdRecord = new MetadataRecord();
        mdRecord.setName("escidoc");
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.newDocument();
        Element element = doc.createElementNS(null, "myMdRecord");

        mdRecord.setContent(element);
        mdRecords.add(mdRecord);
        organizationalUnit.setMetadataRecords(mdRecords);

        try {
            cc.create(organizationalUnit);
            fail("Missing Exception");
        }
        catch (Exception e) {
            Class<?> ec = MissingElementValueException.class;
            EscidocClientTestBase.assertExceptionType(ec.getName()
                + " expected.", ec, e);
        }
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
    @Test
    public void testCreateOrganizationalUnit03() throws Exception {

        OrganizationalUnitHandlerClient cc =
            new OrganizationalUnitHandlerClient();
        cc.login(EscidocClientTestBase.DEFAULT_SERVICE_URL,
            Constants.SYSTEM_ADMIN_USER, Constants.SYSTEM_ADMIN_PASSWORD);

        OrganizationalUnit organizationalUnit = new OrganizationalUnit();
        Properties properties = new Properties();
        organizationalUnit.setProperties(properties);

        MetadataRecords mdRecords = new MetadataRecords();

        MetadataRecord mdRecord = new MetadataRecord();
        mdRecord.setName("escidoc");
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.newDocument();
        Element element = doc.createElementNS(null, "myMdRecord");

        mdRecord.setContent(element);
        mdRecords.add(mdRecord);
        organizationalUnit.setMetadataRecords(mdRecords);

        try {
            cc.create(organizationalUnit);
            fail("Missing Exception");
        }
        catch (Exception e) {
            Class<?> ec = MissingElementValueException.class;
            EscidocClientTestBase.assertExceptionType(ec.getName()
                + " expected.", ec, e);
        }
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
    @Test
    public void testCreateOrganizationalUnit04() throws Exception {

        OrganizationalUnitHandlerClient cc =
            new OrganizationalUnitHandlerClient();
        cc.login(EscidocClientTestBase.DEFAULT_SERVICE_URL,
            Constants.SYSTEM_ADMIN_USER, Constants.SYSTEM_ADMIN_PASSWORD);

        OrganizationalUnit organizationalUnit = new OrganizationalUnit();
        Properties properties = new Properties();
        properties.setName("Organizational_Unit_Test_Name");
        organizationalUnit.setProperties(properties);

        MetadataRecords mdRecords = new MetadataRecords();

        MetadataRecord mdRecord = new MetadataRecord();
        mdRecord.setName("escidoc");
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.newDocument();
        Element element = doc.createElementNS(null, "myMdRecord");

        mdRecord.setContent(element);
        mdRecords.add(mdRecord);
        organizationalUnit.setMetadataRecords(mdRecords);

        try {
            cc.create(organizationalUnit);
            fail("Missing Exception");
        }
        catch (Exception e) {
            Class<?> ec = MissingElementValueException.class;
            EscidocClientTestBase.assertExceptionType(ec.getName()
                + " expected.", ec, e);
        }
    }

    /**
     * Test successful creation of OrganizationalUnit. The md-record is defined
     * as String and parsed by DocumentBuilder.
     * 
     * @throws Exception
     *             Thrown if successful creation failed.
     */
    @Test
    public void testCreateOrganizationalUni05() throws Exception {

        final String ouName = "Generic Organizational Unit";

        OrganizationalUnit organizationalUnit = new OrganizationalUnit();
        Properties properties = new Properties();
        organizationalUnit.setProperties(properties);

        MetadataRecords mdRecords = new MetadataRecords();
        MetadataRecord mdRecord = new MetadataRecord();
        mdRecord.setName("escidoc");

        String str =
            "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
                + "<ou:organization-details "
                + "xmlns:ou=\"http://escidoc.mpg.de/metadataprofile/schema/0.1/organization\">\n"
                + "<dc:title xmlns:dc=\"http://purl.org/dc/elements/1.1/\">"
                + ouName + "</dc:title>\n" + "</ou:organization-details>";
        InputStream in = new ByteArrayInputStream(str.getBytes());

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(in);
        mdRecord.setContent(doc.getDocumentElement());
        mdRecords.add(mdRecord);

        organizationalUnit.setMetadataRecords(mdRecords);

        // create OU
        OrganizationalUnitHandlerClient cc =
            new OrganizationalUnitHandlerClient();
        cc.login(EscidocClientTestBase.DEFAULT_SERVICE_URL,
            Constants.SYSTEM_ADMIN_USER, Constants.SYSTEM_ADMIN_PASSWORD);

        OrganizationalUnit createdOU = cc.create(organizationalUnit);

        // assert values of created OU
        assertEquals("Name of OU wrong", createdOU.getProperties().getName(),
            ouName);
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

        OrganizationalUnit organizationalUnit = new OrganizationalUnit();
        Properties properties = new Properties();
        properties.setName("Organizational_Unit_Test_Name");
        organizationalUnit.setProperties(properties);

        // md-record "escidoc"
        MetadataRecord mdRecord = new MetadataRecord();
        mdRecord.setName("escidoc");

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        factory.setCoalescing(true);
        factory.setValidating(true);
        DocumentBuilder builder = factory.newDocumentBuilder();

        Document doc = builder.newDocument();
        Element mdRecordContent = doc.createElementNS(null, "myMdRecord");
        mdRecord.setContent(mdRecordContent);

        // title
        Element title =
            doc.createElementNS("http://purl.org/dc/elements/1.1/", "title");
        title.setTextContent(ouName);
        mdRecordContent.appendChild(title);

        // dc:description
        Element description =
            doc.createElementNS("http://purl.org/dc/elements/1.1/",
                "description");
        description.setTextContent(ouDescription);
        mdRecordContent.appendChild(description);
        mdRecord.setContent(mdRecordContent);

        MetadataRecords mdRecords = new MetadataRecords();
        mdRecords.add(mdRecord);

        organizationalUnit.setMetadataRecords(mdRecords);

        // create OU
        OrganizationalUnitHandlerClient cc =
            new OrganizationalUnitHandlerClient();
        cc.login(EscidocClientTestBase.DEFAULT_SERVICE_URL,
            Constants.SYSTEM_ADMIN_USER, Constants.SYSTEM_ADMIN_PASSWORD);

        OrganizationalUnit createdOU = cc.create(organizationalUnit);

        // assert values of created OU
        assertEquals("Name of OU wrong", createdOU.getProperties().getName(),
            ouName);
        assertEquals("Description of OU wrong", createdOU
            .getProperties().getDescription(), ouDescription);
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
    public void testCreateOrganizationalUni07() throws Exception {

        final String ouName =
            "Generic Organizational Unit " + System.currentTimeMillis();
        final String ouDescription = "Description of Organizational Unit.";

        OrganizationalUnit organizationalUnit = new OrganizationalUnit();
        Properties properties = new Properties();
        properties.setName("Organizational_Unit_Test_Name");
        organizationalUnit.setProperties(properties);

        MetadataRecords mdRecords = new MetadataRecords();

        MetadataRecord mdRecord =
            createMdRecordDC("escidoc", "organization-details", ouName,
                ouDescription);

        mdRecords.add(mdRecord);
        organizationalUnit.setMetadataRecords(mdRecords);

        // create parent OU
        OrganizationalUnitHandlerClient cc =
            new OrganizationalUnitHandlerClient();
        cc.login(EscidocClientTestBase.DEFAULT_SERVICE_URL,
            Constants.SYSTEM_ADMIN_USER, Constants.SYSTEM_ADMIN_PASSWORD);

        OrganizationalUnit parentOU = cc.create(organizationalUnit);

        // create child OU
        Parents parents = new Parents();
        parents.addParentRef(new ResourceRef(parentOU.getObjid()));
        organizationalUnit.setParents(parents);

        OrganizationalUnit childOU = cc.create(organizationalUnit);

        // assert values of created OU
        assertEquals("Name of OU wrong", childOU.getProperties().getName(),
            ouName);
        assertEquals("Description of OU wrong", childOU
            .getProperties().getDescription(), ouDescription);

        assertEquals("Number of parents wrong", childOU
            .getParents().getParentRef().size(), parents.getParentRef().size());
        assertEquals("Wrong parent objid", childOU
            .getParents().getParentRef().iterator().next().getObjid(), parents
            .getParentRef().iterator().next().getObjid());

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
        final String ou2Description =
            "The second OU of a test. " + System.currentTimeMillis();

        OrganizationalUnitHandlerClient cc =
            new OrganizationalUnitHandlerClient();
        cc.login(EscidocClientTestBase.DEFAULT_SERVICE_URL,
            Constants.SYSTEM_ADMIN_USER, Constants.SYSTEM_ADMIN_PASSWORD);

        // OU 1
        OrganizationalUnit ou1 = new OrganizationalUnit();
        Properties properties = new Properties();
        ou1.setProperties(properties);

        MetadataRecord mdRecord =
            createMdRecordDC("escidoc", "myMdRecord", ou1Name, ou1Description);

        MetadataRecords mdRecords = new MetadataRecords();
        mdRecords.add(mdRecord);

        ou1.setMetadataRecords(mdRecords);

        ou1 = cc.create(ou1);

        // OU 2
        OrganizationalUnit ou2 = new OrganizationalUnit();
        Properties properties2 = new Properties();
        ou2.setProperties(properties2);

        // set OU1 Predecessor of OU2
        Predecessors predecessors = new Predecessors();

        Predecessor predecessor =
            new Predecessor(ou1.getObjid(), PredecessorForm.REPLACEMENT);
        predecessors.addPredecessorRef(predecessor);
        ou2.setPredecessors(predecessors);

        mdRecord =
            createMdRecordDC("escidoc", "myMdRecord", ou2Name, ou2Description);

        mdRecords = new MetadataRecords();
        mdRecords.add(mdRecord);

        ou2.setMetadataRecords(mdRecords);

        ou2 = cc.create(ou2);

        // assert OU values
        assertEquals("Wrong predecessor", ou2
            .getPredecessors().iterator().next().getObjid(), ou1.getObjid());
        assertEquals("Wrong predecessor", 1, ou2
            .getPredecessors().getPredecessorRef().size());
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
        final String mdRecordName, final String rootElementName,
        final String title, final String description)
        throws ParserConfigurationException {

        // md-record
        MetadataRecord mdRecord = new MetadataRecord();
        mdRecord.setName(mdRecordName);

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        factory.setCoalescing(true);
        factory.setValidating(true);
        DocumentBuilder builder = factory.newDocumentBuilder();

        Document doc = builder.newDocument();
        Element mdRecordContent = doc.createElementNS(null, rootElementName);
        mdRecord.setContent(mdRecordContent);

        // title
        Element titleElmt =
            doc.createElementNS("http://purl.org/dc/elements/1.1/", "title");
        titleElmt.setPrefix("dc");
        titleElmt.setTextContent(title);
        mdRecordContent.appendChild(titleElmt);

        // dc:description
        Element descriptionElmt =
            doc.createElementNS("http://purl.org/dc/elements/1.1/",
                "description");
        descriptionElmt.setPrefix("dc");
        descriptionElmt.setTextContent(description);
        mdRecordContent.appendChild(descriptionElmt);
        mdRecord.setContent(mdRecordContent);

        return mdRecord;

    }

    /**
     * Test dealing with multiple MetadataRecords of OrganizationalUnit.
     * 
     * @throws Exception
     *             Thrown if metadata records handling failed.
     */
    @Test
    public void testMultipleMetadataRecords01() throws Exception {

        OrganizationalUnitHandlerClient cc =
            new OrganizationalUnitHandlerClient();
        cc.login(EscidocClientTestBase.DEFAULT_SERVICE_URL,
            Constants.SYSTEM_ADMIN_USER, Constants.SYSTEM_ADMIN_PASSWORD);

        OrganizationalUnit organizationalUnit = new OrganizationalUnit();
        Properties properties = new Properties();
        organizationalUnit.setProperties(properties);

        MetadataRecord mdRecord =
            createMdRecordDC("escidoc", "myMdRecord", "Test OU "
                + System.currentTimeMillis(), "The fist OU of a test. ");

        MetadataRecord mdRecord2 =
            createMdRecordDC("md-record2", "mySecondMdRecord", "Test OU "
                + System.currentTimeMillis(), "22222222222222222222222");

        MetadataRecord mdRecord3 =
            createMdRecordDC("md-record3", "myThirdMdRecord", "Test OU "
                + System.currentTimeMillis(), "some-other-stuff");

        MetadataRecords mdRecords = new MetadataRecords();
        mdRecords.add(mdRecord);
        mdRecords.add(mdRecord2);
        mdRecords.add(mdRecord3);

        organizationalUnit.setMetadataRecords(mdRecords);

        OrganizationalUnit createdOrganizationalUnit =
            cc.create(organizationalUnit);

        // compare the new created OrganizationalUnit with the
        // OrganizationalUnit from the request
        String objId = createdOrganizationalUnit.getObjid();
        assertNotNull("Object id is null", objId);

        Asserts.assertMdRecords(organizationalUnit.getMetadataRecords(),
            createdOrganizationalUnit.getMetadataRecords());

        // now delete some metadataRecords
        createdOrganizationalUnit.getMetadataRecords().del("md-record2");
        OrganizationalUnit updatedOrganizationalUnit1 =
            cc.update(createdOrganizationalUnit);

        assertNotNull("Object id is null", objId);

        Asserts.assertMdRecords(createdOrganizationalUnit.getMetadataRecords(),
            updatedOrganizationalUnit1.getMetadataRecords());
    }
}
