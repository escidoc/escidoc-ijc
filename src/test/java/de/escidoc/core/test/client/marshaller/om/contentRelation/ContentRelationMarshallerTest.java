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
package de.escidoc.core.test.client.marshaller.om.contentRelation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.joda.time.DateTime;
import org.junit.Test;

import de.escidoc.core.client.TransportProtocol;
import de.escidoc.core.common.jibx.Factory;
import de.escidoc.core.resources.om.contentRelation.ContentRelation;
import de.escidoc.core.test.client.AbstractParameterizedTestBase;
import de.escidoc.core.test.client.EscidocClientTestBase;
import de.escidoc.core.test.client.util.Template;

/**
 * Test marshalling/unmarshalling of ContentRelation with mockups.
 * 
 * @author SWA
 * 
 */
public class ContentRelationMarshallerTest
    extends AbstractParameterizedTestBase {

    public ContentRelationMarshallerTest(TransportProtocol transport) {
        super(transport);
    }

    /**
     * Test unmarshalling of Content Relation.
     * 
     * @throws Exception
     *             Thrown if no or wrong exception is caught from the framework.
     */
    @Test
    public void unmarshallingRetrieved() throws Exception {

        String crXml =
            EscidocClientTestBase.getXmlFileAsString(Template.load("/mockups/"
                + transport.name().toLowerCase()
                + "/om/content-relation/0.1/content-relation01.xml"));

        ContentRelation cr =
            Factory
                .getMarshallerFactory(transport).getContentRelationMarshaller()
                .unmarshalDocument(crXml);

        assertEquals("Wrong objid", "escidoc:157531", cr.getObjid());
        assertEquals("Wrong last modification date", new DateTime(
            "2010-06-08T18:25:25.141+02:00"), cr.getLastModificationDate());
        assertEquals("Wrong creation date", new DateTime(
            "2010-06-08T18:25:25.141+02:00"), cr
            .getProperties().getCreationDate());

        assertEquals("Wrong created-by", "escidoc:exuser1", cr
            .getProperties().getCreatedBy().getObjid());
        assertEquals("Wrong modified-by", "escidoc:exuser1", cr
            .getProperties().getModifiedBy().getObjid());
        assertEquals("Wrong property description",
            "Java Client Test Content Relation", cr
                .getProperties().getDescription());
        assertEquals("Wrong public-status", "pending", cr
            .getProperties().getPublicStatus());
        assertEquals("Wrong public-status comment", "Object created.", cr
            .getProperties().getPublicStatusComment());
        assertEquals("Wrong lock-status", "unlocked", cr
            .getProperties().getLockStatus());

        assertEquals(
            "Wrong type",
            "http://www.escidoc.de/ontologies/ontologies/content-relations#hasPart",
            cr.getType().toString());

        assertEquals("Wrong subject", "escidoc:ex1", cr.getSubject().getObjid());
        assertEquals("Wrong object", "escidoc:ex5:1", cr.getObject().getObjid());

        // md-records
        assertEquals("Wrong number of md-records", 3, cr
            .getMetadataRecords().size());

        assertEquals("Wrong name of md-record", "escidoc", cr
            .getMetadataRecords().get(0).getName());
        assertEquals("Wrong schema of md-record", "unknown", cr
            .getMetadataRecords().get(0).getSchema());

        assertEquals("Wrong name of md-record", "mdRecord2", cr
            .getMetadataRecords().get(1).getName());
        assertEquals("Wrong schema of md-record", "unknown", cr
            .getMetadataRecords().get(1).getSchema());

        assertEquals("Wrong name of md-record", "mdRecord3", cr
            .getMetadataRecords().get(2).getName());
        assertEquals("Wrong schema of md-record", "unknown", cr
            .getMetadataRecords().get(2).getSchema());
        // TODO validate md-record content

    }

    /**
     * Test unmarshalling of locked Content Relation.
     * 
     * @throws Exception
     *             Thrown if no or wrong exception is caught from the framework.
     */
    @Test
    public void unmarshallingRetrievLocked() throws Exception {

        String crXml =
            EscidocClientTestBase.getXmlFileAsString(Template.load("/mockups/"
                + transport.name().toLowerCase()
                + "/om/content-relation/0.1/content-relation-locked01.xml"));

        ContentRelation cr =
            Factory
                .getMarshallerFactory(transport).getContentRelationMarshaller()
                .unmarshalDocument(crXml);

        assertEquals("Wrong objid", "escidoc:157531", cr.getObjid());
        assertEquals("Wrong last modification date", new DateTime(
            "2010-06-08T18:25:25.141+02:00"), cr.getLastModificationDate());
        assertEquals("Wrong creation date", new DateTime(
            "2010-06-08T18:25:25.141+02:00"), cr
            .getProperties().getCreationDate());

        assertEquals("Wrong created-by", "escidoc:exuser1", cr
            .getProperties().getCreatedBy().getObjid());
        assertEquals("Wrong modified-by", "escidoc:exuser1", cr
            .getProperties().getModifiedBy().getObjid());
        assertEquals("Wrong property description",
            "Java Client Test Content Relation", cr
                .getProperties().getDescription());
        assertEquals("Wrong public-status", "pending", cr
            .getProperties().getPublicStatus());
        assertEquals("Wrong public-status comment", "Object created.", cr
            .getProperties().getPublicStatusComment());
        assertEquals("Wrong lock-status", "locked", cr
            .getProperties().getLockStatus());
        assertEquals("Wrong lock-date",
            new DateTime("2010-07-09T12:24:19.314Z"), cr
                .getProperties().getLockDate());
        assertEquals("Wrong lock-owner", "escidoc:10001", cr
            .getProperties().getLockOwner().getObjid());

        assertEquals("Wrong [object] pid", "hdl:someHandle/test/escidoc:40011",
            cr.getProperties().getPid());

        assertEquals(
            "Wrong type",
            "http://www.escidoc.de/ontologies/ontologies/content-relations#hasPart",
            cr.getType().toString());

        assertEquals("Wrong subject", "escidoc:ex1", cr.getSubject().getObjid());
        assertEquals("Wrong object", "escidoc:ex5:1", cr.getObject().getObjid());

        // md-records
        assertEquals("Wrong number of md-records", 3, cr
            .getMetadataRecords().size());

        assertEquals("Wrong name of md-record", "escidoc", cr
            .getMetadataRecords().get(0).getName());
        assertEquals("Wrong schema of md-record", "unknown", cr
            .getMetadataRecords().get(0).getSchema());

        assertEquals("Wrong name of md-record", "mdRecord2", cr
            .getMetadataRecords().get(1).getName());
        assertEquals("Wrong schema of md-record", "unknown", cr
            .getMetadataRecords().get(1).getSchema());

        assertEquals("Wrong name of md-record", "mdRecord3", cr
            .getMetadataRecords().get(2).getName());
        assertEquals("Wrong schema of md-record", "unknown", cr
            .getMetadataRecords().get(2).getSchema());
        // TODO validate md-record content

    }

    /**
     * Test unmarshalling of Content Relation.
     * 
     * @throws Exception
     *             Thrown if no or wrong exception is caught from the framework.
     */
    @Test
    public void unmarshallingBeforeCreate() throws Exception {

        String crXml =
            EscidocClientTestBase
                .getXmlFileAsString(Template
                    .load("/mockups/"
                        + transport.name().toLowerCase()
                        + "/om/content-relation/0.1/content-relation-before-create.xml"));

        ContentRelation cr =
            Factory
                .getMarshallerFactory(transport).getContentRelationMarshaller()
                .unmarshalDocument(crXml);

        assertNull("Wrong objid", cr.getObjid());
        assertNull("Wrong last modification date", cr.getLastModificationDate());
        assertNull("Wrong creation date", cr.getProperties().getCreationDate());

        assertNull("Wrong created-by", cr.getProperties().getCreatedBy());
        assertNull("Wrong modified-by", cr.getProperties().getModifiedBy());
        assertEquals("Wrong property description",
            "Demo content relation between Context and Item", cr
                .getProperties().getDescription());
        assertNull("Wrong public-status", cr.getProperties().getPublicStatus());
        assertNull("Wrong public-status comment", cr
            .getProperties().getPublicStatusComment());
        assertNull("Wrong lock-status", cr.getProperties().getLockStatus());

        assertEquals(
            "Wrong type",
            "http://www.escidoc.de/ontologies/ontologies/content-relations#hasPart",
            cr.getType().toString());

        assertEquals("Wrong subject", "escidoc:ex1", cr.getSubject().getObjid());
        assertEquals("Wrong object", "escidoc:ex5:1", cr.getObject().getObjid());

        // md-records
        assertEquals("Wrong number of md-records", 1, cr
            .getMetadataRecords().size());

        assertEquals("Wrong name of md-record", "escidoc", cr
            .getMetadataRecords().get(0).getName());
        assertNull("Wrong schema of md-record", cr
            .getMetadataRecords().get(0).getSchema());

        // TODO validate md-record content

    }

}
