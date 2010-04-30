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

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import de.escidoc.core.client.OrganizationalUnitHandlerClient;
import de.escidoc.core.client.exceptions.application.invalid.XmlSchemaValidationException;
import de.escidoc.core.client.exceptions.application.missing.MissingElementValueException;
import de.escidoc.core.resources.ResourceRef;
import de.escidoc.core.resources.common.MetadataRecord;
import de.escidoc.core.resources.common.MetadataRecords;
import de.escidoc.core.resources.oum.OrganizationalUnit;
import de.escidoc.core.resources.oum.Parents;
import de.escidoc.core.resources.oum.Predecessor;
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
    public void testCreateOU05() throws Exception {

        OrganizationalUnit organizationalUnit = new OrganizationalUnit();
        Properties properties = new Properties();
        properties.setName("Organizational_Unit_Test_Name");
        organizationalUnit.setProperties(properties);

        MetadataRecords mdRecords = new MetadataRecords();
        MetadataRecord mdRecord = new MetadataRecord();
        mdRecord.setName("escidoc");

        String str =
            "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
                + "<ou:organization-details "
                + "xmlns:ou=\"http://escidoc.mpg.de/metadataprofile/schema/0.1/organization\">\n"
                + "<dc:title xmlns:dc=\"http://purl.org/dc/elements/1.1/\">"
                + "Generic Organizational Unit</dc:title>\n"
                + "</ou:organization-details>";
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

        // validate createdOU
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

        OrganizationalUnit organizationalUnit = new OrganizationalUnit();
        Properties properties = new Properties();
        properties.setName("Organizational_Unit_Test_Name");
        organizationalUnit.setProperties(properties);

        MetadataRecords mdRecords = new MetadataRecords();

        MetadataRecord mdRecord = new MetadataRecord();
        mdRecord.setName("escidoc");

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        factory.setCoalescing(true);
        factory.setValidating(true);
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.newDocument();

        Element mdRecordCnt =
            doc.createElementNS("http://escidoc.mpg.de/"
                + "metadataprofile/schema/0.1/organization",
                "ou:organization-details");

        // dc:title
        Element title =
            doc.createElementNS("http://purl.org/dc/elements/1.1/", "dc:title");
        title.setTextContent("name" + System.currentTimeMillis());
        mdRecordCnt.appendChild(title);
        //
        // // dc:description
        // Element description =
        // doc.createElementNS("http://purl.org/dc/elements/1.1/",
        // "dc:description");
        // description.setTextContent("Just a generic organizational unit.");
        // mdRecordCnt.appendChild(description);
        //
        mdRecord.setContent(mdRecordCnt);

        // mdRecord.setContent(doc.getDocumentElement());
        mdRecords.add(mdRecord);

        // mdRecord.setContent(tmpl.getMetadataRecords().get("escidoc").getContent());
        // mdRecords.add(mdRecord);

        // mdRecords.add(tmpl.getMetadataRecords().get("escidoc"));
        organizationalUnit.setMetadataRecords(mdRecords);

        // create OU
        OrganizationalUnitHandlerClient cc =
            new OrganizationalUnitHandlerClient();
        cc.login(EscidocClientTestBase.DEFAULT_SERVICE_URL,
            Constants.SYSTEM_ADMIN_USER, Constants.SYSTEM_ADMIN_PASSWORD);

        OrganizationalUnit createdOU = cc.create(organizationalUnit);
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
    public void testCreateOU07() throws Exception {

        OrganizationalUnit organizationalUnit = new OrganizationalUnit();
        Properties properties = new Properties();
        properties.setName("Organizational_Unit_Test_Name");
        organizationalUnit.setProperties(properties);

        MetadataRecords mdRecords = new MetadataRecords();
        MetadataRecord mdRecord = new MetadataRecord();
        mdRecord.setName("escidoc");

        String str =
            "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
                + "<ou:organization-details "
                + "xmlns:ou=\"http://escidoc.mpg.de/metadataprofile/schema/0.1/organization\">\n"
                + "<dc:title xmlns:dc=\"http://purl.org/dc/elements/1.1/\">"
                + "Generic Organizational Unit</dc:title>\n"
                + "</ou:organization-details>";
        InputStream in = new ByteArrayInputStream(str.getBytes());

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(in);
        mdRecord.setContent(doc.getDocumentElement());
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

    }

    /**
     * Test creating Organizational Unit with predecessors.
     * 
     * @throws Exception
     *             Thrown if no or wrong exception is caught from the framework.
     */
    @Test
    public void testOuPredecessor05() throws Exception {

        OrganizationalUnitHandlerClient cc =
            new OrganizationalUnitHandlerClient();
        cc.login(EscidocClientTestBase.DEFAULT_SERVICE_URL,
            Constants.SYSTEM_ADMIN_USER, Constants.SYSTEM_ADMIN_PASSWORD);

        // OU 1
        OrganizationalUnit ou1 = new OrganizationalUnit();
        Properties properties = new Properties();
        properties.setName("Organizational_Unit_Test_Name");
        ou1.setProperties(properties);

        MetadataRecords mdRecords = new MetadataRecords();
        MetadataRecord mdRecord = new MetadataRecord();
        mdRecord.setName("escidoc");
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.newDocument();
        Element element = doc.createElementNS(null, "myMdRecord");
        Element childElement =
            doc.createElementNS("http://purl.org/dc/elements/1.1/", "cd:title");
        childElement.setTextContent("name" + System.currentTimeMillis());

        element.appendChild(childElement);
        mdRecord.setContent(element);
        mdRecords.add(mdRecord);

        cc.create(ou1);

        // OU 2
        OrganizationalUnit ou2 = new OrganizationalUnit();
        Properties properties2 = new Properties();
        properties.setName("Organizational_Unit_Test_Name");
        ou2.setProperties(properties2);
        Predecessor predecessor = new Predecessor();
        predecessor.setObjid(ou1.getObjid());
        Predecessors predecessors = new Predecessors();
        predecessors.addPredecessorRef(predecessor);
        ou2.setPredecessors(predecessors);
        cc.create(ou2);

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
        properties.setName("name");
        organizationalUnit.setProperties(properties);
        MetadataRecords mdRecords = new MetadataRecords();
        MetadataRecord mdRecord = new MetadataRecord();
        mdRecord.setName("escidoc");
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.newDocument();
        Element element = doc.createElementNS(null, "myMdRecord");
        Element childElement =
            doc.createElementNS("http://purl.org/dc/elements/1.1/", "cd:title");
        childElement.setTextContent("name" + System.currentTimeMillis());

        // childElement.setParent(element);
        element.appendChild(childElement);
        mdRecord.setContent(element);
        mdRecords.add(mdRecord);

        MetadataRecord mdRecord2 = new MetadataRecord();
        mdRecord2.setName("md-record2");
        Document doc1 = builder.newDocument();
        Element element2 = doc.createElementNS(null, "myMdRecord");
        element2.setTextContent("222222222");

        mdRecord2.setContent(element2);

        mdRecords.add(mdRecord2);

        MetadataRecord mdRecord3 = new MetadataRecord();
        mdRecord3.setName("md-record3");
        Document doc3 = builder.newDocument();
        Element element3 = doc.createElementNS(null, "myMdRecord");
        Document doc4 = builder.newDocument();
        Element element4 = doc.createElementNS(null, "some-other-stuff");
        element2.setTextContent("33333333333333333333");
        element2.appendChild(element4);

        mdRecord3.setContent(element3);
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

    private static String xmlToString(Node node) {
        try {
            Source source = new DOMSource(node);
            StringWriter stringWriter = new StringWriter();
            Result result = new StreamResult(stringWriter);
            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer transformer = factory.newTransformer();
            transformer.transform(source, result);
            return stringWriter.getBuffer().toString();
        }
        catch (TransformerConfigurationException e) {
            e.printStackTrace();
        }
        catch (TransformerException e) {
            e.printStackTrace();
        }
        return null;
    }

}
