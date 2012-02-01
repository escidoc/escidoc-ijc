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
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
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
        cc.create(createContext());

        final ExplainResponse response = cc.retrieveContexts(new ExplainRequestType());
        final Explain explain = response.getRecord().getRecordData();

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
        // avoid LMD with 0z at the end
        while (createdContext.getLastModificationDate().withZone(DateTimeZone.UTC).toString().endsWith("0Z")) {
            createdContext = cc.create(createContext());
        }

        // now check if at least this Context is in the list

        final SearchRetrieveRequestType srwFilter = new SearchRetrieveRequestType();
        srwFilter.setQuery("\"/properties/creation-date\"=\""
            + createdContext.getLastModificationDate().toDateTime(DateTimeZone.UTC) + "\"");
        // srwFilter.setQuery("\"/properties/creation-date\"=\"2011-07-14T10:41:26.830Z\"");
        srwFilter.setMaximumRecords(new NonNegativeInteger("1"));

        final SearchRetrieveResponse contextList = cc.retrieveContexts(srwFilter);

        assertEquals("Wrong version number", "1.1", contextList.getVersion());
        assertTrue("Wrong number of matching records [query=" + srwFilter.getQuery() + "]", contextList
            .getNumberOfMatchingRecords() >= 1);
        assertTrue("Wrong number of resulting records [query=" + srwFilter.getQuery() + "]", contextList
            .getNumberOfResultingRecords() >= 1);
        assertEquals("Wrong record position [query=" + srwFilter.getQuery() + "]", 1, contextList
            .getRecords().iterator().next().getRecordPosition().intValue());
    }

    @Test
    @Ignore("Has to be fixed in infrastructure")
    public void testFilter02TryZeroMilliLMD() throws Exception {
        Context createdContext = cc.create(createContext());
        // force LMD to have 0Z at the end
        while (!createdContext.getLastModificationDate().withZone(DateTimeZone.UTC).toString().endsWith("0Z")) {
            createdContext = cc.create(createContext());
        }

        // now check if at least this Context is in the list

        final SearchRetrieveRequestType srwFilter = new SearchRetrieveRequestType();
        srwFilter.setQuery("\"/properties/creation-date\"=\""
            + createdContext.getLastModificationDate().toDateTime(DateTimeZone.UTC) + "\"");
        // srwFilter.setQuery("\"/properties/creation-date\"=\"2011-07-14T10:41:26.830Z\"");
        srwFilter.setMaximumRecords(new NonNegativeInteger("1"));

        final SearchRetrieveResponse contextList = cc.retrieveContexts(srwFilter);

        assertEquals("Wrong version number", "1.1", contextList.getVersion());
        assertTrue("Wrong number of matching records [query=" + srwFilter.getQuery() + "]", contextList
            .getNumberOfMatchingRecords() >= 1);
        assertTrue("Wrong number of resulting records [query=" + srwFilter.getQuery() + "]", contextList
            .getNumberOfResultingRecords() >= 1);
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
        final Context context = new Context();
        final ContextProperties properties = new ContextProperties();
        properties.setDescription("@" + getClass().getName());
        properties.setName("ContextName ("
            + new DateTime(System.currentTimeMillis()).withZone(DateTimeZone.UTC).toString() + ")");
        properties.setPublicStatus(PublicStatus.OPENED);
        properties.setPublicStatusComment("PublicStatusComment");

        final OrganizationalUnitRefs organizationalUnitRefs = new OrganizationalUnitRefs();
        organizationalUnitRefs.add(new OrganizationalUnitRef(EscidocClientTestBase.getStaticOrganizationalUnitId()));

        properties.setOrganizationalUnitRefs(organizationalUnitRefs);
        properties.setType("type");
        context.setProperties(properties);

        final AdminDescriptors adminDescriptors = new AdminDescriptors();
        final AdminDescriptor adminDescriptor = new AdminDescriptor("AdminDescriptorDemoName");
        final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        final DocumentBuilder builder = factory.newDocumentBuilder();
        final Document doc = builder.newDocument();
        final Element element = doc.createElementNS(null, "admin-descriptor");
        adminDescriptor.setContent(element);

        adminDescriptors.add(adminDescriptor);
        context.setAdminDescriptors(adminDescriptors);

        // create
        return context;
    }
}
