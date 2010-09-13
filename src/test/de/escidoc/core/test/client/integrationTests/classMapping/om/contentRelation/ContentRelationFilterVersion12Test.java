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
package de.escidoc.core.test.client.integrationTests.classMapping.om.contentRelation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import gov.loc.www.zing.srw.ExplainRequestType;
import gov.loc.www.zing.srw.SearchRetrieveRequestType;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Collection;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import de.escidoc.core.client.Authentication;
import de.escidoc.core.client.ContentRelationHandlerClient;
import de.escidoc.core.client.TransportProtocol;
import de.escidoc.core.client.interfaces.ContentRelationHandlerClientInterface;
import de.escidoc.core.resources.ResourceRef;
import de.escidoc.core.resources.ResourceRef.RESOURCE_TYPE;
import de.escidoc.core.resources.common.MetadataRecord;
import de.escidoc.core.resources.common.MetadataRecords;
import de.escidoc.core.resources.common.Result;
import de.escidoc.core.resources.common.TaskParam;
import de.escidoc.core.resources.om.contentRelation.ContentRelation;
import de.escidoc.core.resources.om.contentRelation.ContentRelationProperties;
import de.escidoc.core.resources.sb.explain.ExplainData;
import de.escidoc.core.resources.sb.explain.ExplainResponse;
import de.escidoc.core.resources.sb.search.SearchRetrieveResponse;
import de.escidoc.core.test.client.Constants;
import de.escidoc.core.test.client.EscidocClientTestBase;
import de.escidoc.core.test.client.integrationTests.classMapping.om.ResourceUtility;

/**
 * Test the CQL Context Filter.
 * 
 * @author SWA
 * 
 */
@RunWith(Parameterized.class)
public class ContentRelationFilterVersion12Test {

    private TransportProtocol transport;

    public ContentRelationFilterVersion12Test(TransportProtocol transport) {
        this.transport = transport;
    }

    @SuppressWarnings("rawtypes")
    @Parameters
    public static Collection data() {
        return Arrays.asList(new Object[][] { { TransportProtocol.SOAP },
            { TransportProtocol.REST } });
    }

    /**
     * Test retrieving Contexts through filter request (filter for version 1.2).
     * 
     * @throws Exception
     *             Thrown if anythings failed.
     */
    @Test
    public void testExplain() throws Exception {

        Authentication auth =
            new Authentication(EscidocClientTestBase.DEFAULT_SERVICE_URL,
                Constants.SYSTEM_ADMIN_USER, Constants.SYSTEM_ADMIN_PASSWORD);

        ContentRelationHandlerClientInterface cc =
            new ContentRelationHandlerClient();
        cc.setServiceAddress(EscidocClientTestBase.DEFAULT_SERVICE_URL);
        cc.setHandle(auth.getHandle());
        cc.setTransport(transport);

        cc.create(createContentRelation());

        ExplainResponse response =
            cc.retrieveContentRelations(new ExplainRequestType());
        ExplainData explain = response.getRecord().getRecordData();

        assertEquals("Wrong version number", "1.1", response.getVersion());
        assertTrue("No index definitions found", explain
            .getIndexInfo().getIndexes().size() > 0);
    }

    /**
     * Test retrieving Contexts through filter request (filter for version 1.2).
     * 
     * @throws Exception
     *             Thrown if anythings failed.
     */
    @Test
    public void testFilter01() throws Exception {

        Authentication auth =
            new Authentication(EscidocClientTestBase.DEFAULT_SERVICE_URL,
                Constants.SYSTEM_ADMIN_USER, Constants.SYSTEM_ADMIN_PASSWORD);

        ContentRelationHandlerClientInterface cc =
            new ContentRelationHandlerClient();
        cc.setServiceAddress(EscidocClientTestBase.DEFAULT_SERVICE_URL);
        cc.setHandle(auth.getHandle());
        cc.setTransport(transport);

        ContentRelation contentRelation = cc.create(createContentRelation());

        // submit --------------------------------------------------------------
        TaskParam tp = new TaskParam();
        tp.setLastModificationDate(contentRelation.getLastModificationDate());
        tp.setComment("submitted as java client lib test");

        @SuppressWarnings("unused")
        Result result = cc.submit(contentRelation, tp);

        SearchRetrieveRequestType request = new SearchRetrieveRequestType();
        request.setQuery("\"/properties/public-status\"=\"pending\"");

        SearchRetrieveResponse res = cc.retrieveContentRelations(request);
        Collection<ContentRelation> results =
            cc.retrieveContentRelationsAsList(request);

        assertTrue("Wrong number of matching records",
            res.getNumberOfMatchingRecords() >= 1);
        assertTrue("Wrong number of resulting records for asList",
            results.size() >= 1);
        assertTrue("", res.getNumberOfResultingRecords() == results.size());
    }

    /**
     * 
     * @return
     * @throws URISyntaxException
     * @throws ParserConfigurationException
     */
    private ContentRelation createContentRelation() throws URISyntaxException,
        ParserConfigurationException {
        ContentRelation contentRelation = new ContentRelation();

        // properties
        ContentRelationProperties properties = new ContentRelationProperties();
        properties.setDescription("Java Client Test Content Relation");

        contentRelation.setProperties(properties);

        contentRelation
            .setType(new URI(
                "http://www.escidoc.de/ontologies/mpdl-ontologies/content-relations#hasPart"));

        contentRelation.setSubject(new ResourceRef("escidoc:ex1",
            RESOURCE_TYPE.Context));
        contentRelation.setObject(new ResourceRef("escidoc:ex5:1",
            RESOURCE_TYPE.Item));

        // md-record
        MetadataRecords mdRecords = new MetadataRecords();

        MetadataRecord mdrecord1 = ResourceUtility.getMdRecord("escidoc");
        mdRecords.add(mdrecord1);

        contentRelation.setMetadataRecords(mdRecords);
        return contentRelation;
    }
}
