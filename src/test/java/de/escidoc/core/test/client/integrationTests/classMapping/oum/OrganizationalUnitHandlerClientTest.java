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
import static org.junit.Assert.assertTrue;
import gov.loc.www.zing.srw.SearchRetrieveRequestType;

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
import de.escidoc.core.client.UserAccountHandlerClient;
import de.escidoc.core.client.exceptions.application.notfound.OrganizationalUnitNotFoundException;
import de.escidoc.core.client.interfaces.OrganizationalUnitHandlerClientInterface;
import de.escidoc.core.common.jibx.MarshallerFactory;
import de.escidoc.core.resources.aa.useraccount.UserAccount;
import de.escidoc.core.resources.common.MetadataRecord;
import de.escidoc.core.resources.common.MetadataRecords;
import de.escidoc.core.resources.oum.OrganizationalUnit;
import de.escidoc.core.resources.oum.OrganizationalUnitProperties;
import de.escidoc.core.test.client.EscidocClientTestBase;

/**
 * 
 * @author SWA
 * 
 */
public class OrganizationalUnitHandlerClientTest {

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
     * Test if the right exception is thrown if a non existing OrganizationUnit
     * is retrieved.
     * 
     * @throws Exception
     *             Thrown if not the right exception is caught.
     */
    @Test(expected = OrganizationalUnitNotFoundException.class)
    public void testRetrieveUnknown() throws Exception {
        ohc.retrieve(EscidocClientTestBase.INVALID_RESOURCE_ID);
    }

    /**
     * Test retrieving existing OrganizationUnit.
     * 
     * @throws Exception
     *             Thrown if retrieve and/or marshalling failed.
     */
    @Test
    public void testRetrieve01() throws Exception {
        OrganizationalUnit organizationUnit = ohc.retrieve(EscidocClientTestBase.getStaticOrganizationalUnitId());

        MarshallerFactory.getInstance(ohc.getTransport()).getMarshaller(OrganizationalUnit.class).marshalDocument(
            organizationUnit);
    }

    /**
     * Test retrieving existing OrganizationUnit.
     * 
     * TODO wichtig
     * 
     * @throws Exception
     *             Thrown if retrieve and/or marshalling failed.
     */
    @Ignore("INFR-1057")
    @Test
    public void testRetrieveUpdate() throws Exception {
        OrganizationalUnit ou = ohc.retrieve(EscidocClientTestBase.getStaticOrganizationalUnitId());

        OrganizationalUnit updatedOU = ohc.update(ou);

        assertEquals("", ou.getLastModificationDate(), updatedOU.getLastModificationDate());
    }

    /**
     * Test retrieving child organizational units.
     * 
     * @throws Exception
     *             Thrown if anythings failed.
     */
    @Test
    public void testRetrieveChildObjects() throws Exception {
        ohc.retrieveChildObjects(EscidocClientTestBase.getStaticOrganizationalUnitId());
    }

    /**
     * Test retrieving organizational units through filter request.
     * 
     * @throws Exception
     *             Thrown if anythings failed.
     */
    @Test
    public void testRetrieveOrganizationalUnits() throws Exception {

        final String ouName = "Generic Organizational Unit " + System.currentTimeMillis();
        final String ouDescription = "Description of Organizational Unit.";

        OrganizationalUnit organizationalUnit = new OrganizationalUnit();
        OrganizationalUnitProperties properties = new OrganizationalUnitProperties();
        properties.setName("Organizational_Unit_Test_Name");
        organizationalUnit.setProperties(properties);

        MetadataRecords mdRecords = new MetadataRecords();

        MetadataRecord mdRecord = createMdRecordDC("escidoc", "organization-details", ouName, ouDescription);

        mdRecords.add(mdRecord);
        organizationalUnit.setMetadataRecords(mdRecords);

        // create parent OU
        OrganizationalUnit ou = ohc.create(organizationalUnit);

        // just getting a valid objid of a user
        UserAccountHandlerClient uac = new UserAccountHandlerClient(auth.getServiceAddress());
        uac.setHandle(auth.getHandle());
        UserAccount me = uac.retrieveCurrentUser();

        OrganizationalUnitHandlerClientInterface ic = new OrganizationalUnitHandlerClient();

        SearchRetrieveRequestType request = new SearchRetrieveRequestType();
        request.setQuery("\"/properties/created-by/id\"=" + me.getObjid());

        List<OrganizationalUnit> ouList = ic.retrieveOrganizationalUnitsAsList(request);

        assertNotNull("No result list returned.", ouList);
        assertTrue("result list is empty, try another filter", ouList.size() != 0);
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
        MetadataRecord mdRecord = new MetadataRecord(mdRecordName);

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        factory.setCoalescing(true);
        factory.setValidating(true);
        DocumentBuilder builder = factory.newDocumentBuilder();

        Document doc = builder.newDocument();
        Element mdRecordContent = doc.createElementNS(null, rootElementName);
        mdRecord.setContent(mdRecordContent);

        // title
        Element titleElmt = doc.createElementNS("http://purl.org/dc/elements/1.1/", "title");
        titleElmt.setPrefix("dc");
        titleElmt.setTextContent(title);
        mdRecordContent.appendChild(titleElmt);

        // dc:description
        Element descriptionElmt = doc.createElementNS("http://purl.org/dc/elements/1.1/", "description");
        descriptionElmt.setPrefix("dc");
        descriptionElmt.setTextContent(description);
        mdRecordContent.appendChild(descriptionElmt);
        mdRecord.setContent(mdRecordContent);

        return mdRecord;
    }
}
