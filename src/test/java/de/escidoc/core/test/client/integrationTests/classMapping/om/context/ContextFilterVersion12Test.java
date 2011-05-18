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
package de.escidoc.core.test.client.integrationTests.classMapping.om.context;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import gov.loc.www.zing.srw.ExplainRequestType;
import gov.loc.www.zing.srw.SearchRetrieveRequestType;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.axis.types.NonNegativeInteger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import de.escidoc.core.client.Authentication;
import de.escidoc.core.client.ContextHandlerClient;
import de.escidoc.core.client.exceptions.EscidocException;
import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.client.exceptions.TransportException;
import de.escidoc.core.client.interfaces.ContextHandlerClientInterface;
import de.escidoc.core.resources.common.properties.PublicStatus;
import de.escidoc.core.resources.common.reference.OrganizationalUnitRef;
import de.escidoc.core.resources.om.context.AdminDescriptor;
import de.escidoc.core.resources.om.context.AdminDescriptors;
import de.escidoc.core.resources.om.context.Context;
import de.escidoc.core.resources.om.context.ContextProperties;
import de.escidoc.core.resources.om.context.OrganizationalUnitRefs;
import de.escidoc.core.resources.sb.explain.Explain;
import de.escidoc.core.resources.sb.explain.ExplainResponse;
import de.escidoc.core.resources.sb.search.SearchRetrieveResponse;
import de.escidoc.core.test.client.EscidocClientTestBase;

/**
 * Test the CQL Context Filter.
 * 
 * @author SWA
 * 
 */
public class ContextFilterVersion12Test {

    private Authentication auth;

    private ContextHandlerClientInterface cc;

    @Before
    public void init() throws Exception {
        auth =
            new Authentication(EscidocClientTestBase.getDefaultInfrastructureURL(),
                EscidocClientTestBase.SYSTEM_ADMIN_USER, EscidocClientTestBase.SYSTEM_ADMIN_PASSWORD);
        cc = new ContextHandlerClient(auth.getServiceAddress());
        cc.setHandle(auth.getHandle());
    }

    @After
    public void post() throws Exception {
        if (auth != null)
            auth.logout();
    }

    /**
     * Test retrieving Contexts through filter request (filter for version 1.2).
     * 
     * @throws Exception
     *             Thrown if anythings failed.
     */
    @Test
    public void testExplain() throws Exception {
        ExplainResponse response = cc.retrieveContexts(new ExplainRequestType());
        Explain explain = response.getRecord().getRecordData();

        assertEquals("Wrong version number", "1.1", response.getVersion());
        assertNotNull("No index definitions found", explain.getIndexInfo());
        assertTrue("No index definitions found", explain.getIndexInfo().getIndexes().size() > 0);
    }

    /**
     * Test retrieving Contexts through filter request (filter for version 1.2).
     * 
     * @throws Exception
     *             Thrown if anythings failed.
     */
    @Test
    public void testFilter01() throws Exception {
        Context createdContext = cc.create(createContext());

        // now check if at least this Context is in the list

        SearchRetrieveRequestType srwFilter = new SearchRetrieveRequestType();
        srwFilter.setQuery("\"/properties/creation-date\"=\"" + createdContext.getLastModificationDate() + "\"");
        srwFilter.setMaximumRecords(new NonNegativeInteger("1"));

        SearchRetrieveResponse contextList = cc.retrieveContexts(srwFilter);

        assertEquals("Wrong version number", "1.1", contextList.getVersion());
        assertTrue("Wrong number of matching records [query=" + srwFilter.getQuery() + "]",
            contextList.getNumberOfMatchingRecords() >= 1);
        assertTrue("Wrong number of resulting records [query=" + srwFilter.getQuery() + "]",
            contextList.getNumberOfResultingRecords() >= 1);
        assertEquals("Wrong record position [query=" + srwFilter.getQuery() + "]", 1, contextList
            .getRecords().iterator().next().getRecordPosition().intValue());
    }

    /**
     * 
     * @return
     * @throws ParserConfigurationException
     * @throws InternalClientException
     * @throws EscidocException
     * @throws TransportException
     */
    private Context createContext() throws ParserConfigurationException, TransportException, EscidocException,
        InternalClientException {
        Context context = new Context();
        ContextProperties properties = new ContextProperties();
        properties.setDescription("ContextDescription");
        properties.setName("ContextName" + System.currentTimeMillis());
        properties.setPublicStatus(PublicStatus.OPENED);
        properties.setPublicStatusComment("PublicStatusComment");

        OrganizationalUnitRefs organizationalUnitRefs = new OrganizationalUnitRefs();
        organizationalUnitRefs.add(new OrganizationalUnitRef(EscidocClientTestBase.getStaticOrganizationalUnitId()));

        properties.setOrganizationalUnitRefs(organizationalUnitRefs);
        properties.setType("type");
        context.setProperties(properties);

        AdminDescriptors adminDescriptors = new AdminDescriptors();
        AdminDescriptor adminDescriptor = new AdminDescriptor("AdminDescriptorDemoName");
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.newDocument();
        Element element = doc.createElementNS(null, "admin-descriptor");
        adminDescriptor.setContent(element);

        adminDescriptors.add(adminDescriptor);
        context.setAdminDescriptors(adminDescriptors);

        // create
        return context;
    }
}
