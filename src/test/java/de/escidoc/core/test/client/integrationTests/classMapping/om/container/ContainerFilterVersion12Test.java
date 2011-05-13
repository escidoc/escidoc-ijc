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
package de.escidoc.core.test.client.integrationTests.classMapping.om.container;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import gov.loc.www.zing.srw.ExplainRequestType;
import gov.loc.www.zing.srw.SearchRetrieveRequestType;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.axis.types.NonNegativeInteger;
import org.joda.time.DateTimeZone;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import de.escidoc.core.client.Authentication;
import de.escidoc.core.client.ContainerHandlerClient;
import de.escidoc.core.client.exceptions.EscidocException;
import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.client.exceptions.TransportException;
import de.escidoc.core.client.interfaces.ContainerHandlerClientInterface;
import de.escidoc.core.resources.common.MetadataRecord;
import de.escidoc.core.resources.common.MetadataRecords;
import de.escidoc.core.resources.common.properties.ContentModelSpecific;
import de.escidoc.core.resources.common.reference.ContentModelRef;
import de.escidoc.core.resources.common.reference.ContextRef;
import de.escidoc.core.resources.om.container.Container;
import de.escidoc.core.resources.om.container.ContainerProperties;
import de.escidoc.core.resources.sb.explain.Explain;
import de.escidoc.core.resources.sb.explain.ExplainResponse;
import de.escidoc.core.resources.sb.search.SearchRetrieveResponse;
import de.escidoc.core.test.client.EscidocClientTestBase;

/**
 * Test the CQL Container Filter.
 * 
 * @author SWA
 * 
 */
public class ContainerFilterVersion12Test {

    private Authentication auth;

    private ContainerHandlerClientInterface cc;

    @Before
    public void init() throws Exception {
        auth =
            new Authentication(EscidocClientTestBase.getDefaultInfrastructureURL(),
                EscidocClientTestBase.SYSTEM_ADMIN_USER, EscidocClientTestBase.SYSTEM_ADMIN_PASSWORD);
        cc = new ContainerHandlerClient(auth.getServiceAddress());
        cc.setHandle(auth.getHandle());
    }

    @After
    public void post() throws Exception {
        if (auth != null)
            auth.logout();
    }

    /**
     * Test retrieving Containers through filter request (filter for version
     * 1.2).
     * 
     * @throws Exception
     *             Thrown if anythings failed.
     */
    @Test
    public void testExplain() throws Exception {
        cc.create(createContainer());

        ExplainResponse response = cc.retrieveContainers(new ExplainRequestType());
        Explain explain = response.getRecord().getRecordData();

        assertEquals("Wrong version number", "1.1", response.getVersion());
        assertTrue("No index definitions found", explain.getIndexInfo().getIndexes().size() > 0);
    }

    /**
     * Test retrieving Containers through filter request (filter for version
     * 1.2).
     * 
     * @throws Exception
     *             Thrown if anythings failed.
     */
    @Test
    public void testFilter01() throws Exception {
        Container createdContainer = cc.create(createContainer());

        // now check if at least this Container is in the list
        SearchRetrieveRequestType srwFilter = new SearchRetrieveRequestType();
        srwFilter.setQuery("\"/properties/creation-date\"=\""
            + createdContainer.getLastModificationDate().withZone(DateTimeZone.UTC) + "\"");
        srwFilter.setMaximumRecords(new NonNegativeInteger("1"));

        SearchRetrieveResponse containerList = cc.retrieveContainers(srwFilter);

        assertEquals("Wrong version number", "1.1", containerList.getVersion());
        assertTrue("Wrong number of matching records", containerList.getNumberOfMatchingRecords() >= 1);
        assertEquals("Wrong record position", 1, containerList
            .getRecords().iterator().next().getRecordPosition().intValue());

        // now check the convenience method
        Collection<Container> list = cc.retrieveContainersAsList(srwFilter);

        assertTrue("Wrong number of records", list.size() == containerList.getRecords().size());
    }

    /**
     * 
     * @return
     * @throws ParserConfigurationException
     * @throws InternalClientException
     * @throws EscidocException
     * @throws TransportException
     */
    private Container createContainer() throws ParserConfigurationException, TransportException, EscidocException,
        InternalClientException {
        Container container = new Container();

        // properties
        ContainerProperties properties = new ContainerProperties();
        properties.setContext(new ContextRef(EscidocClientTestBase.getStaticContextId()));
        properties.setContentModel(new ContentModelRef(EscidocClientTestBase.getStaticContentModelId()));

        // Content-model-specific
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.newDocument();
        Element contentModelSpecific = doc.createElementNS(null, "cms");

        List<Element> cmsContent = new LinkedList<Element>();
        cmsContent.add(contentModelSpecific);
        ContentModelSpecific cms = new ContentModelSpecific();

        cms.setContent(cmsContent);

        properties.setContentModelSpecific(cms);
        container.setProperties(properties);

        MetadataRecords mdRecords = new MetadataRecords();
        MetadataRecord mdRecord = new MetadataRecord("escidoc");

        Document docMdRecord = builder.newDocument();
        Element element = docMdRecord.createElementNS(null, "myMdRecord");
        mdRecord.setContent(element);

        mdRecords.add(mdRecord);
        container.setMetadataRecords(mdRecords);
        return container;
    }
}
