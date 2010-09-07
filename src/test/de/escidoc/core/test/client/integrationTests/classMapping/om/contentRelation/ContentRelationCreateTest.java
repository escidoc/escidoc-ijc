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
import static org.junit.Assert.assertNotNull;

import java.net.URI;
import java.net.URL;

import org.joda.time.DateTime;
import org.junit.Test;

import de.escidoc.core.client.Authentication;
import de.escidoc.core.client.ContentRelationHandlerClient;
import de.escidoc.core.client.exceptions.application.invalid.XmlSchemaValidationException;
import de.escidoc.core.resources.ResourceRef;
import de.escidoc.core.resources.ResourceRef.RESOURCE_TYPE;
import de.escidoc.core.resources.common.MetadataRecord;
import de.escidoc.core.resources.common.MetadataRecords;
import de.escidoc.core.resources.common.Result;
import de.escidoc.core.resources.common.TaskParam;
import de.escidoc.core.resources.om.contentRelation.ContentRelation;
import de.escidoc.core.resources.om.contentRelation.ContentRelationProperties;
import de.escidoc.core.test.client.Constants;
import de.escidoc.core.test.client.EscidocClientTestBase;
import de.escidoc.core.test.client.integrationTests.classMapping.om.ResourceUtility;

/**
 * Test create ContentRelation.
 * 
 * @author SWA
 * 
 */
public class ContentRelationCreateTest {

    /**
     * Test if the right exception is thrown if calling create with an
     * incomplete ContentRelation.
     * 
     * @throws Exception
     *             Thrown if no or wrong exception is caught from the framework.
     */
    @Test(expected = XmlSchemaValidationException.class)
    public void testCreateContentRelation01() throws Exception {

        Authentication auth =
            new Authentication(EscidocClientTestBase.DEFAULT_SERVICE_URL,
                Constants.SYSTEM_ADMIN_USER, Constants.SYSTEM_ADMIN_PASSWORD);

        ContentRelationHandlerClient cc = new ContentRelationHandlerClient();
        cc.setServiceAddress(EscidocClientTestBase.DEFAULT_SERVICE_URL);
        cc.setHandle(auth.getHandle());

        ContentRelation contentRelation = new ContentRelation();
        cc.create(contentRelation);
    }

    /**
     * Test if the right exception is thrown if calling create with an
     * incomplete ContentRelation.
     * 
     * A title is set (but shouldn't have influence).
     * 
     * @throws Exception
     *             Thrown if no or wrong exception is caught from the framework.
     */
    @Test(expected = XmlSchemaValidationException.class)
    public void testCreateContentRelation02() throws Exception {

        Authentication auth =
            new Authentication(EscidocClientTestBase.DEFAULT_SERVICE_URL,
                Constants.SYSTEM_ADMIN_USER, Constants.SYSTEM_ADMIN_PASSWORD);

        ContentRelationHandlerClient cc = new ContentRelationHandlerClient();
        cc.setServiceAddress(EscidocClientTestBase.DEFAULT_SERVICE_URL);
        cc.setHandle(auth.getHandle());

        ContentRelation contentRelation = new ContentRelation();
        contentRelation.setXLinkTitle("New title for test");
        cc.create(contentRelation);
    }

    /**
     * Test if the right exception is thrown if calling create with an
     * incomplete ContentRelation.
     * 
     * @throws Exception
     *             Thrown if no or wrong exception is caught from the framework.
     */
    @Test(expected = XmlSchemaValidationException.class)
    public void testCreateContentRelation03() throws Exception {

        Authentication auth =
            new Authentication(EscidocClientTestBase.DEFAULT_SERVICE_URL,
                Constants.SYSTEM_ADMIN_USER, Constants.SYSTEM_ADMIN_PASSWORD);

        ContentRelationHandlerClient cc = new ContentRelationHandlerClient();
        cc.setServiceAddress(EscidocClientTestBase.DEFAULT_SERVICE_URL);
        cc.setHandle(auth.getHandle());

        ContentRelation contentRelation = new ContentRelation();
        ContentRelationProperties properties = new ContentRelationProperties();
        contentRelation.setProperties(properties);
        cc.create(contentRelation);
    }

    /**
     * Test if the right exception is thrown if calling create with an
     * incomplete ContentRelation.
     * 
     * @throws Exception
     *             Thrown if no or wrong exception is caught from the framework.
     */
    @Test(expected = XmlSchemaValidationException.class)
    public void testCreateContentRelation04() throws Exception {

        Authentication auth =
            new Authentication(EscidocClientTestBase.DEFAULT_SERVICE_URL,
                Constants.SYSTEM_ADMIN_USER, Constants.SYSTEM_ADMIN_PASSWORD);

        ContentRelationHandlerClient cc = new ContentRelationHandlerClient();
        cc.setServiceAddress(EscidocClientTestBase.DEFAULT_SERVICE_URL);
        cc.setHandle(auth.getHandle());

        ContentRelation contentRelation = new ContentRelation();
        ContentRelationProperties properties = new ContentRelationProperties();
        contentRelation.setProperties(properties);
        cc.create(contentRelation);
    }

    /**
     * Test if the right exception is thrown if calling create with an
     * incomplete ContentRelation.
     * 
     * @throws Exception
     *             Thrown if no or wrong exception is caught from the framework.
     */
    @Test(expected = XmlSchemaValidationException.class)
    public void testCreateContentRelation05() throws Exception {

        Authentication auth =
            new Authentication(EscidocClientTestBase.DEFAULT_SERVICE_URL,
                Constants.SYSTEM_ADMIN_USER, Constants.SYSTEM_ADMIN_PASSWORD);

        ContentRelationHandlerClient cc = new ContentRelationHandlerClient();
        cc.setServiceAddress(EscidocClientTestBase.DEFAULT_SERVICE_URL);
        cc.setHandle(auth.getHandle());

        ContentRelation contentRelation = new ContentRelation();
        ContentRelationProperties properties = new ContentRelationProperties();
        contentRelation.setProperties(properties);
        cc.create(contentRelation);
    }

    /**
     * Test if the right exception is thrown if calling create with an
     * incomplete ContentRelation.
     * 
     * One invalid MetadataRecord (no name, no content) is part of
     * ContentRelation.
     * 
     * @throws Exception
     *             Thrown if no or wrong exception is caught from the framework.
     */
    @Test(expected = XmlSchemaValidationException.class)
    public void testCreateContentRelation06() throws Exception {

        Authentication auth =
            new Authentication(EscidocClientTestBase.DEFAULT_SERVICE_URL,
                Constants.SYSTEM_ADMIN_USER, Constants.SYSTEM_ADMIN_PASSWORD);

        ContentRelationHandlerClient cc = new ContentRelationHandlerClient();
        cc.setServiceAddress(EscidocClientTestBase.DEFAULT_SERVICE_URL);
        cc.setHandle(auth.getHandle());

        ContentRelation contentRelation = new ContentRelation();
        ContentRelationProperties properties = new ContentRelationProperties();
        contentRelation.setProperties(properties);
        MetadataRecords mdRecords = new MetadataRecords();
        MetadataRecord mdRecord = new MetadataRecord();
        mdRecords.add(mdRecord);
        contentRelation.setMetadataRecords(mdRecords);

        cc.create(contentRelation);
    }

    /**
     * Test if the right exception is thrown if calling create with an
     * incomplete ContentRelation.
     * 
     * One invalid MetadataRecord (no content) is part of ContentRelation.
     * 
     * @throws Exception
     *             Thrown if no or wrong exception is caught from the framework.
     */
    @Test(expected = XmlSchemaValidationException.class)
    public void testCreateContentRelation07() throws Exception {

        Authentication auth =
            new Authentication(EscidocClientTestBase.DEFAULT_SERVICE_URL,
                Constants.SYSTEM_ADMIN_USER, Constants.SYSTEM_ADMIN_PASSWORD);

        ContentRelationHandlerClient cc = new ContentRelationHandlerClient();
        cc.setServiceAddress(EscidocClientTestBase.DEFAULT_SERVICE_URL);
        cc.setHandle(auth.getHandle());

        ContentRelation contentRelation = new ContentRelation();
        ContentRelationProperties properties = new ContentRelationProperties();
        contentRelation.setProperties(properties);
        MetadataRecords mdRecords = new MetadataRecords();
        MetadataRecord mdRecord = new MetadataRecord();
        mdRecord.setName("escidoc");
        mdRecords.add(mdRecord);
        contentRelation.setMetadataRecords(mdRecords);

        cc.create(contentRelation);
    }

    /**
     * Test if the right exception is thrown if calling create with an
     * incomplete ContentRelation.
     * 
     * MetadataRecord has name and content. ContentModel is at least missing.
     * 
     * @throws Exception
     *             Thrown if no or wrong exception is caught from the framework.
     */
    @Test
    public void testCreateContentRelation08() throws Exception {

        Authentication auth =
            new Authentication(EscidocClientTestBase.DEFAULT_SERVICE_URL,
                Constants.SYSTEM_ADMIN_USER, Constants.SYSTEM_ADMIN_PASSWORD);

        ContentRelationHandlerClient cc = new ContentRelationHandlerClient();
        cc.setServiceAddress(EscidocClientTestBase.DEFAULT_SERVICE_URL);
        cc.setHandle(auth.getHandle());

        ContentRelation contentRelation = new ContentRelation();

        // properties
        ContentRelationProperties properties = new ContentRelationProperties();
        properties.setDescription("Java Client Test Content Relation");

        contentRelation.setProperties(properties);

        contentRelation
            .setType(new URI(
                "http://www.escidoc.de/ontologies/mpdl-ontologies/content-relations#hasPart"));
        contentRelation.setSubject(new ResourceRef("escidoc:ex1", RESOURCE_TYPE.Context, "Context Example 1"));
        contentRelation.setObject(new ResourceRef("escidoc:ex5:1", RESOURCE_TYPE.Item, "eSciDoc Banner"));

        // md-record
        MetadataRecords mdRecords = new MetadataRecords();

        MetadataRecord mdrecord1 = ResourceUtility.getMdRecord("escidoc");
        mdRecords.add(mdrecord1);

        contentRelation.setMetadataRecords(mdRecords);

        ContentRelation createdCR = cc.create(contentRelation);

        assertNotNull("Object id is null", createdCR.getObjid());
        assertEquals("Relation type differ", contentRelation.getType(),
            createdCR.getType());
        assertEquals("Subjects differ", contentRelation.getSubject(),
            createdCR.getSubject());
        assertEquals("Objects differ", contentRelation.getObject(),
            createdCR.getObject());
    }

    /**
     * Test dealing with multiple MetadataRecords of ContentRelation.
     * 
     * @throws Exception
     *             Thrown if metadata records handling failed.
     */
    @Test
    public void testMultipleMetadataRecords01() throws Exception {

        Authentication auth =
            new Authentication(EscidocClientTestBase.DEFAULT_SERVICE_URL,
                Constants.SYSTEM_ADMIN_USER, Constants.SYSTEM_ADMIN_PASSWORD);

        ContentRelationHandlerClient cc = new ContentRelationHandlerClient();
        cc.setServiceAddress(EscidocClientTestBase.DEFAULT_SERVICE_URL);
        cc.setHandle(auth.getHandle());

        ContentRelation contentRelation = new ContentRelation();

        // properties
        ContentRelationProperties properties = new ContentRelationProperties();
        properties.setDescription("Java Client Test Content Relation");

        contentRelation.setProperties(properties);

        contentRelation
            .setType(new URI(
                "http://www.escidoc.de/ontologies/mpdl-ontologies/content-relations#hasPart"));
        contentRelation.setSubject(new ResourceRef("escidoc:ex1", RESOURCE_TYPE.Context));
        contentRelation.setObject(new ResourceRef("escidoc:ex5:1", RESOURCE_TYPE.Item));

        // md-record
        MetadataRecords mdRecords = new MetadataRecords();

        MetadataRecord mdrecord1 = ResourceUtility.getMdRecord("escidoc");
        mdRecords.add(mdrecord1);

        MetadataRecord mdrecord2 = ResourceUtility.getMdRecord("mdRecord2");
        mdRecords.add(mdrecord2);

        MetadataRecord mdrecord3 = ResourceUtility.getMdRecord("mdRecord3");
        mdRecords.add(mdrecord3);

        contentRelation.setMetadataRecords(mdRecords);

        ContentRelation createdCR = cc.create(contentRelation);

        assertNotNull("Object id is null", createdCR.getObjid());
        assertEquals("Relation type differ", contentRelation.getType(),
            createdCR.getType());
        assertEquals("Subjects differ", contentRelation.getSubject(),
            createdCR.getSubject());
        assertEquals("Objects differ", contentRelation.getObject(),
            createdCR.getObject());

        // compare the new created ContentRelation with the ContentRelation from
        // the request
        assertEquals("Number of md records differ", contentRelation
            .getMetadataRecords().size(), createdCR.getMetadataRecords().size());
    }

    /**
     * Test lifecycle of ContentRelation.
     * 
     * @throws Exception
     *             Thrown if creation failed or non-volatile ContentRelation
     *             values differ.
     */
    @Test
    public void testLifecycleContentRelation01() throws Exception {

        Authentication auth =
            new Authentication(EscidocClientTestBase.DEFAULT_SERVICE_URL,
                Constants.SYSTEM_ADMIN_USER, Constants.SYSTEM_ADMIN_PASSWORD);

        ContentRelationHandlerClient cc = new ContentRelationHandlerClient();
        cc.setServiceAddress(EscidocClientTestBase.DEFAULT_SERVICE_URL);
        cc.setHandle(auth.getHandle());

        ContentRelation contentRelation = new ContentRelation();

        // properties
        ContentRelationProperties properties = new ContentRelationProperties();
        properties.setDescription("Java Client Test Content Relation");

        contentRelation.setProperties(properties);

        contentRelation
            .setType(new URI(
                "http://www.escidoc.de/ontologies/mpdl-ontologies/content-relations#hasPart"));
        contentRelation.setSubject(new ResourceRef("escidoc:ex1", RESOURCE_TYPE.Context));
        contentRelation.setObject(new ResourceRef("escidoc:ex5:1", RESOURCE_TYPE.Item));

        // md-record
        MetadataRecords mdRecords = new MetadataRecords();

        MetadataRecord mdrecord1 = ResourceUtility.getMdRecord("escidoc");
        mdRecords.add(mdrecord1);

        contentRelation.setMetadataRecords(mdRecords);

        contentRelation = cc.create(contentRelation);

        // submit --------------------------------------------------------------
        TaskParam tp = new TaskParam();
        tp.setLastModificationDate(contentRelation.getLastModificationDate());
        tp.setComment("submitted as java client lib test");

        Result result = cc.submit(contentRelation, tp);

        // check ContentRelation
        // compare timestamps
        DateTime lmdResult = result.getLastModificationDate();
        ContentRelation retrievedContentRelation = cc.retrieve(contentRelation);
        DateTime lmdRetrievedContentRelation =
            retrievedContentRelation.getLastModificationDate();

        assertEquals("Timestamps differ", lmdRetrievedContentRelation,
            lmdResult);

        // assign object PID ---------------------------------------------------
        TaskParam taskParam = new TaskParam();
        taskParam.setLastModificationDate(result.getLastModificationDate());
        taskParam.setUrl(new URL(
            "http://url.to.the.solution/path/for/this/resource/"
                + System.nanoTime()));
        taskParam.setComment("Object for test");

        result = cc.assignObjectPid(contentRelation, taskParam);

        // compare timestamps
        lmdResult = result.getLastModificationDate();
        retrievedContentRelation = cc.retrieve(contentRelation);
        lmdRetrievedContentRelation =
            retrievedContentRelation.getLastModificationDate();

        assertEquals("Timestamps differ", lmdRetrievedContentRelation,
            lmdResult);

        // assign version PID --------------------------------------------------
        // release -------------------------------------------------------------
        // update --------------------------------------------------------------

    }

}
