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
package de.escidoc.core.test.client.integrationTests.classMapping.oum;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.junit.Test;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import de.escidoc.core.client.OrganizationalUnitHandlerClient;
import de.escidoc.core.client.interfaces.OrganizationalUnitHandlerClientInterface;
import de.escidoc.core.resources.common.MetadataRecord;
import de.escidoc.core.resources.common.MetadataRecords;
import de.escidoc.core.resources.oum.OrganizationalUnit;
import de.escidoc.core.resources.oum.Properties;
import de.escidoc.core.test.client.Constants;
import de.escidoc.core.test.client.EscidocClientTestBase;

/**
 * Test deletion of OrganizationalUnit.
 * 
 * @author SWA
 * 
 */
public class OuDeleteTest {

    /**
     * Test delete of Organizational Unit.
     * 
     * @throws Exception
     *             Thrown if deletion failed.
     */
    @Test
    public void testDeleteOu01() throws Exception {

        // create OU
    	OrganizationalUnitHandlerClientInterface cc =
            new OrganizationalUnitHandlerClient();
        cc.login(EscidocClientTestBase.DEFAULT_SERVICE_URL,
            Constants.SYSTEM_ADMIN_USER, Constants.SYSTEM_ADMIN_PASSWORD);

        OrganizationalUnit createdOU = cc.create(createOu());

        // delete createdOU
        cc.delete(createdOU.getObjid());

    }

    /**
     * Create value object Organizational Unit.
     * 
     * @return Organizational Unit which is not created in the infrastructure.
     * 
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     */
    private OrganizationalUnit createOu() throws ParserConfigurationException,
        SAXException, IOException {

        // create OU
        OrganizationalUnit organizationalUnit = new OrganizationalUnit();
        Properties properties = new Properties();
        properties.setName("Organizational_Unit_Test_Name");
        organizationalUnit.setProperties(properties);

        MetadataRecords mdRecords = new MetadataRecords();
        MetadataRecord mdRecord = new MetadataRecord();
        mdRecord.setName("escidoc");

        String str =
            "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
                + "<ou:organization-details "
                + "xmlns:ou=\"http://escidoc.mpg.de/"
                + "metadataprofile/schema/0.1/organization\">\n"
                + "<dc:title xmlns:dc=\"http://purl.org/dc/elements/1.1/\">"
                + "Generic Organizational Unit</dc:title>\n"
                + "</ou:organization-details>";
        InputStream in = new ByteArrayInputStream(str.getBytes());

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(in);
        mdRecord.setContent(doc.getDocumentElement());
        mdRecords.add(mdRecord);

        organizationalUnit.setMetadataRecords(mdRecords);

        return organizationalUnit;
    }

}
