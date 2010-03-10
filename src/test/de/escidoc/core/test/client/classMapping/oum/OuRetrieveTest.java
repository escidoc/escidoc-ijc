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
package de.escidoc.core.test.client.classMapping.oum;

import java.io.StringWriter;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.junit.Test;
import org.w3c.dom.Node;

import de.escidoc.core.client.OrganizationalUnitHandlerClient;
import de.escidoc.core.resources.common.MetadataRecord;
import de.escidoc.core.resources.oum.OrganizationalUnit;
import de.escidoc.core.test.client.EscidocClientTestBase;

/**
 * Test retrieving OrganizationalUnit.
 * 
 * @author SWA
 * 
 */
public class OuRetrieveTest extends EscidocClientTestBase {

    /**
     * Test retrieving one OU of the example set.
     * 
     * @throws Exception
     *             Thrown if retrieving failed.
     */
    @Test
    public void testRetrieveExampleOu01() throws Exception {

        OrganizationalUnitHandlerClient cc =
            new OrganizationalUnitHandlerClient();
        cc.setHandle(EscidocClientTestBase.DEFAULT_HANDLE);

        OrganizationalUnit ou = cc.retrieve("escidoc:ex3");

        MetadataRecord mdRecord = ou.getMetadataRecords().get("escidoc");
        assertEquals("wrong name", "escidoc", mdRecord.getName());
        System.out.println(xmlToString(mdRecord.getContent()));
    }

    public static String xmlToString(Node node) {
        try {
            Source source = new DOMSource(node);
            StringWriter stringWriter = new StringWriter();
            Result result = new StreamResult(stringWriter);
            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer transformer = factory.newTransformer();
            transformer.transform(source, result);
            return stringWriter.getBuffer().toString();
        }
        catch (TransformerConfigurationException e) {
            e.printStackTrace();
        }
        catch (TransformerException e) {
            e.printStackTrace();
        }
        return null;
    }

}