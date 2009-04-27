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
package de.escidoc.core.test.client.oum;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.log4j.Logger;

import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import de.escidoc.core.client.OrganizationalUnitHandlerClient;
import de.escidoc.core.client.exceptions.application.invalid.XmlSchemaValidationException;
import de.escidoc.core.client.exceptions.application.missing.MissingElementValueException;
import de.escidoc.core.resources.common.MetadataRecord;
import de.escidoc.core.resources.common.MetadataRecords;
import de.escidoc.core.resources.oum.OrganizationalUnit;
import de.escidoc.core.resources.oum.Properties;
import de.escidoc.core.test.client.EscidocClientTestBase;

/**
 * Test create OrganizationalUnit.
 * 
 * @author SWA
 * 
 */
public class OuCreateTest extends EscidocClientTestBase {

    private final Logger logger =
        Logger.getLogger(OuCreateTest.class.getName());

    /**
     * Set up the tests.
     * 
     * @throws Exception
     *             If anything fails.
     */
    @Override
    protected void setUp() throws Exception {

        super.setUp();

    }

    /**
     * Clean up after tests.
     * 
     * @throws Exception
     *             If anything fails.
     */
    @Override
    protected void tearDown() throws Exception {

        super.tearDown();
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
    @Test
    public void testCreateOrganizationalUnit01() throws Exception {

        OrganizationalUnitHandlerClient cc =
            new OrganizationalUnitHandlerClient();
        cc.setHandle(EscidocClientTestBase.DEFAULT_HANDLE);

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
        cc.setHandle(EscidocClientTestBase.DEFAULT_HANDLE);

        OrganizationalUnit organizationalUnit = new OrganizationalUnit();
        MetadataRecords mdRecords = new MetadataRecords();

        MetadataRecord mdRecord = new MetadataRecord();
        mdRecord.setName("escidoc");
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.newDocument();
        Element element = doc.createElementNS(
            null,
            "myMdRecord ");
       

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
        cc.setHandle(EscidocClientTestBase.DEFAULT_HANDLE);

        OrganizationalUnit organizationalUnit = new OrganizationalUnit();
        Properties properties = new Properties();
        organizationalUnit.setProperties(properties);

        MetadataRecords mdRecords = new MetadataRecords();

        MetadataRecord mdRecord = new MetadataRecord();
        mdRecord.setName("escidoc");
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.newDocument();
        Element element = doc.createElementNS(
            null,
            "myMdRecord ");

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
        cc.setHandle(EscidocClientTestBase.DEFAULT_HANDLE);

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
        Element element = doc.createElementNS(
            null,
            "myMdRecord ");

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
     * Test dealing with multiple MetadataRecords of OrganizationalUnit.
     * 
     * @throws Exception
     *             Thrown if metadata records handling failed.
     */
    @Test
    public void testMultipleMetadataRecords01() throws Exception {

        OrganizationalUnitHandlerClient cc =
            new OrganizationalUnitHandlerClient();
        cc.setHandle(EscidocClientTestBase.DEFAULT_HANDLE);

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
        Element element = doc.createElementNS(
            null,
            "myMdRecord ");
        Element childElement = doc.createElementNS(
            "http://purl.org/dc/elements/1.1/",
            "cd:title");
        childElement.setTextContent("name" + System.currentTimeMillis());
        
        // childElement.setParent(element);
        element.appendChild(childElement);
        mdRecord.setContent(element);
        mdRecords.add(mdRecord);

        MetadataRecord mdRecord2 = new MetadataRecord();
        mdRecord2.setName("md-record2");
        Document doc1 = builder.newDocument();
        Element element2 = doc.createElementNS(
            null,
            "myMdRecord ");
        element2.setTextContent("222222222");
        
        mdRecord2.setContent(element2);

        mdRecords.add(mdRecord2);

        MetadataRecord mdRecord3 = new MetadataRecord();
        mdRecord3.setName("md-record3");
        Document doc3 = builder.newDocument();
        Element element3 = doc.createElementNS(
            null, "myMdRecord");
        Document doc4 = builder.newDocument();
        Element element4= doc.createElementNS(
            null, "some-other-stuff");
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

        assertMdRecords(organizationalUnit.getMetadataRecords(),
            createdOrganizationalUnit.getMetadataRecords());

        // now delete some metadataRecords
        createdOrganizationalUnit.getMetadataRecords().del("md-record2");
        OrganizationalUnit updatedOrganizationalUnit1 =
            cc.update(createdOrganizationalUnit);

        assertNotNull("Object id is null", objId);

        assertMdRecords(createdOrganizationalUnit.getMetadataRecords(),
            updatedOrganizationalUnit1.getMetadataRecords());
    }

}
