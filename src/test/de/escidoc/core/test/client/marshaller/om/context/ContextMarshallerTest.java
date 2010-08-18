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
package de.escidoc.core.test.client.marshaller.om.context;

import static org.junit.Assert.assertEquals;

import java.io.File;

import org.joda.time.DateTime;
import org.junit.Test;

import de.escidoc.core.common.jibx.Factory;
import de.escidoc.core.resources.om.context.Context;
import de.escidoc.core.test.client.EscidocClientTestBase;

/**
 * Test marshalling/unmarshalling of Context with mockups.
 * 
 * @author SWA
 * 
 */
public class ContextMarshallerTest {

    /**
     * Test unmarshalling of Context.
     * 
     * @throws Exception
     *             Thrown if no or wrong exception is caught from the framework.
     */
    @Test
    public void unmarshalling01() throws Exception {

        File templContext =
            new File("./templates/mockups/soap/om/context/0.7/context01.xml");
        String contextXml =
            EscidocClientTestBase.getXmlFileAsString(templContext);

        Context context =
            Factory.getContextMarshaller().unmarshalDocument(contextXml);

        assertEquals("Wrong objid", "escidoc:157546", context.getObjid());
        assertEquals("Wrong last modification date", new DateTime(
            "2010-06-08T16:25:46.663Z"), context.getLastModificationDate());
        assertEquals("Wrong creation date", new DateTime(
            "2010-06-08T16:25:46.663Z"), context
            .getProperties().getCreationDate());

        assertEquals("Wrong created-by", "escidoc:exuser1", context
            .getProperties().getCreatedBy().getObjid());
        assertEquals("Wrong modified-by", "escidoc:exuser1", context
            .getProperties().getModifiedBy().getObjid());
        assertEquals("Wrong public-status", "created", context
            .getProperties().getPublicStatus());
        assertEquals("Wrong public-status comment",
            "Object escidoc:157546 created.", context
                .getProperties().getPublicStatusComment());
        assertEquals("Wrong name", "ContextName1276014346603", context
            .getProperties().getName());

        assertEquals("Wrong description", "ContextDescription", context
            .getProperties().getDescription());
        assertEquals("Wrong type", "type", context
            .getProperties().getType());

        // TODO
//        assertEquals("Wrong number of admin-descriptors", 1, context
//            .getProperties().getOrganizationalUnitRefs().);

//      assertEquals("Wrong name of admin-descriptors", 1, context
//      .getAdminDescriptors().getAdminDescriptors().);

    }

    /**
     * Test unmarshalling of Context.
     * 
     * @throws Exception
     *             Thrown if no or wrong exception is caught from the framework.
     */
    @Test
    public void unmarshalling02() throws Exception {

        File templContext =
            new File("./templates/mockups/soap/om/context/0.7/context02.xml");
        String contextXml =
            EscidocClientTestBase.getXmlFileAsString(templContext);

        Context context =
            Factory.getContextMarshaller().unmarshalDocument(contextXml);

    }


}
