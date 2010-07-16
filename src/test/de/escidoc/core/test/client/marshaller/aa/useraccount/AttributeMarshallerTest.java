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
package de.escidoc.core.test.client.marshaller.aa.useraccount;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.joda.time.DateTime;
import org.junit.Test;

import de.escidoc.core.common.jibx.Factory;
import de.escidoc.core.resources.aa.useraccount.Attribute;
import de.escidoc.core.test.client.EscidocClientTestBase;

/**
 * Test marshalling/unmarshalling of User Account attributes with mockups.
 * 
 * @author SWA
 * 
 */
public class AttributeMarshallerTest {

    /**
     * Test unmarshalling of attribute.
     * 
     * @throws Exception
     *             Thrown if no or wrong exception is caught from the framework.
     */
    @Test
    public void unmarshalling01() throws Exception {

        File templAttribute =
            new File(
                "./templates/mockups/soap/aa/useraccount/attribute_internal.xml");
        String attributeXml =
            EscidocClientTestBase.getXmlFileAsString(templAttribute);

        Attribute attribute =
            Factory.getAttributeMarshaller().unmarshalDocument(attributeXml);

        assertEquals("Wrong objid", "escidoc:209775", attribute.getObjid());
        assertEquals("Wrong last modification date", new DateTime(
            "2010-07-16T16:13:22.667Z"), attribute.getLastModificationDate());
        assertTrue("Wrong internal", attribute.isInternal());

        assertEquals("Wrong value", "AttributeValue", attribute.getValue());
    }

}
