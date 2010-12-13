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

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Collections;

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
import de.escidoc.core.client.TransportProtocol;
import de.escidoc.core.client.exceptions.EscidocException;
import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.client.exceptions.TransportException;
import de.escidoc.core.client.interfaces.OrganizationalUnitHandlerClientInterface;
import de.escidoc.core.resources.common.Filter;
import de.escidoc.core.resources.common.MetadataRecord;
import de.escidoc.core.resources.common.MetadataRecords;
import de.escidoc.core.resources.common.TaskParam;
import de.escidoc.core.resources.oum.OrganizationalUnit;
import de.escidoc.core.resources.oum.OrganizationalUnitList;
import de.escidoc.core.resources.oum.Parents;
import de.escidoc.core.resources.oum.OrganizationalUnitProperties;
import de.escidoc.core.test.client.AbstractParameterizedTestBase;
import de.escidoc.core.test.client.Constants;
import de.escidoc.core.test.client.EscidocClientTestBase;

/**
 * Test filtering Top Level OrganizationalUnit.
 * 
 * @author CHH
 * 
 */
public class FilterTopLevelOrgUnitTest extends AbstractParameterizedTestBase {

    private static final String IS_TOP_LEVEL = "true";

    private static final String TOP_LEVEL_ORGANIZATIONAL_UNITS =
        "top-level-organizational-units";

    private Authentication auth;

    private OrganizationalUnitHandlerClientInterface orgUnitClient;

    public FilterTopLevelOrgUnitTest(final TransportProtocol transport) {
        super(transport);
    }

    @Before
    public void init() throws Exception {
        auth =
            new Authentication(EscidocClientTestBase.DEFAULT_SERVICE_URL,
                Constants.SYSTEM_ADMIN_USER, Constants.SYSTEM_ADMIN_PASSWORD);
        orgUnitClient =
            new OrganizationalUnitHandlerClient(auth.getServiceAddress());
        orgUnitClient.setHandle(auth.getHandle());
        orgUnitClient.setTransport(transport);
    }

    @After
    public void post() throws Exception {
        if (auth != null)
            auth.logout();
    }

    @Test
    public void shouldReturnOrgUnitWithNoParent() throws Exception {
        createOrgUnit();

        OrganizationalUnitList orgUnits = getTopLevelOrgUnits();

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
        OrganizationalUnitProperties properties = new OrganizationalUnitProperties();
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

        orgUnitClient.create(organizationalUnit);
    }

    /**
     * 
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    private void assertThatOrgUnitExist(
        final OrganizationalUnitList rootOrgUnitList) throws EscidocException,
        InternalClientException, TransportException {

        assertTrue("RootOrgUnitList is null.", rootOrgUnitList != null);

        Collection<OrganizationalUnit> rootOrgUnits = rootOrgUnitList;

        assertNotNull("rootOrgUnits is null.", rootOrgUnits);
        assertTrue("rootOrgUnits is empty.", rootOrgUnits.size() > 0);
    }

    /**
     * 
     */
    private void assertThatOrgUnitsDoNotHaveParent(
        final OrganizationalUnitList rootOrgUnitList) {
        for (final OrganizationalUnit organizationalUnit : rootOrgUnitList) {
            final Parents parents = organizationalUnit.getParents();
            assertNotNull("Parents object should exist.", parents);
            assertTrue("Root org unit can not have parents.",
                parents.size() == 0);
        }
    }

    private OrganizationalUnitList getTopLevelOrgUnits()
        throws EscidocException, InternalClientException, TransportException {
        return orgUnitClient
            .retrieveOrganizationalUnits(createTaskParamWithTopLevelFilter());
    }

    private TaskParam createTaskParamWithTopLevelFilter() {
        final TaskParam taskParam = new TaskParam();
        taskParam.getFilters().add(createTopLevelFilter());
        return taskParam;
    }

    private Filter createTopLevelFilter() {
        final Filter filter = new Filter();
        filter.setName(TOP_LEVEL_ORGANIZATIONAL_UNITS);
        filter.setValue(IS_TOP_LEVEL);
        filter.setIds(Collections.singletonList(""));
        return filter;
    }
}