package de.escidoc.core.test.client.integrationTests.classMapping.oum;

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

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import gov.loc.www.zing.srw.SearchRetrieveRequestType;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import de.escidoc.core.client.Authentication;
import de.escidoc.core.client.OrganizationalUnitHandlerClient;
import de.escidoc.core.client.exceptions.EscidocException;
import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.client.exceptions.TransportException;
import de.escidoc.core.client.interfaces.OrganizationalUnitHandlerClientInterface;
import de.escidoc.core.resources.common.MetadataRecord;
import de.escidoc.core.resources.common.MetadataRecords;
import de.escidoc.core.resources.oum.OrganizationalUnit;
import de.escidoc.core.resources.oum.OrganizationalUnitProperties;
import de.escidoc.core.resources.oum.Parents;
import de.escidoc.core.test.client.EscidocClientTestBase;

/**
 * Test filtering Top Level OrganizationalUnit.
 * 
 * @author CHH
 * 
 */
public class FilterTopLevelOrgUnitTest {

    private static final String IS_TOP_LEVEL = "true";

    private static final String TOP_LEVEL_ORGANIZATIONAL_UNITS =
        "top-level-organizational-units";

    private Authentication auth;

    private OrganizationalUnitHandlerClientInterface orgUnitClient;

    @Before
    public void init() throws Exception {
        auth =
            new Authentication(
                EscidocClientTestBase.getDefaultInfrastructureURL(),
                EscidocClientTestBase.SYSTEM_ADMIN_USER,
                EscidocClientTestBase.SYSTEM_ADMIN_PASSWORD);
        orgUnitClient =
            new OrganizationalUnitHandlerClient(auth.getServiceAddress());
        orgUnitClient.setHandle(auth.getHandle());
    }

    @After
    public void post() throws Exception {
        if (auth != null)
            auth.logout();
    }

    @Test
    public void shouldReturnOrgUnitWithNoParent() throws Exception {
        createOrgUnit();

        List<OrganizationalUnit> orgUnits = getTopLevelOrgUnits();

        assertThatOrgUnitExist(orgUnits);
        assertThatOrgUnitsDoNotHaveParent(orgUnits);
    }

    /**
     * 
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     * @throws TransportException
     * @throws InternalClientException
     * @throws EscidocException
     */
    private void createOrgUnit() throws ParserConfigurationException,
        SAXException, IOException, EscidocException, InternalClientException,
        TransportException {
        final String ouName = "Generic Organizational Unit";

        OrganizationalUnit organizationalUnit = new OrganizationalUnit();
        OrganizationalUnitProperties properties =
            new OrganizationalUnitProperties();
        organizationalUnit.setProperties(properties);

        MetadataRecords mdRecords = new MetadataRecords();
        MetadataRecord mdRecord = new MetadataRecord("escidoc");

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

        orgUnitClient.create(organizationalUnit);
    }

    /**
     * 
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    private void assertThatOrgUnitExist(
        final List<OrganizationalUnit> rootOrgUnitList)
        throws EscidocException, InternalClientException, TransportException {

        assertNotNull("rootOrgUnits is null.", rootOrgUnitList);
        assertTrue("rootOrgUnits is empty.", rootOrgUnitList.size() > 0);
    }

    /**
     * @param rootOrgUnitList
     */
    private void assertThatOrgUnitsDoNotHaveParent(
        final List<OrganizationalUnit> rootOrgUnitList) {

        for (final OrganizationalUnit organizationalUnit : rootOrgUnitList) {
            final Parents parents = organizationalUnit.getParents();
            assertNotNull("Parents object should exist.", parents);
            assertTrue("Root org unit can not have parents.",
                parents.size() == 0);
        }
    }

    private List<OrganizationalUnit> getTopLevelOrgUnits()
        throws EscidocException, InternalClientException, TransportException {

        SearchRetrieveRequestType request = new SearchRetrieveRequestType();
        request.setQuery(TOP_LEVEL_ORGANIZATIONAL_UNITS + "=" + IS_TOP_LEVEL);
        return orgUnitClient.retrieveOrganizationalUnitsAsList(request);
    }
}