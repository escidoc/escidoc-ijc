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
 * Copyright 2006-2010 Fachinformationszentrum Karlsruhe Gesellschaft
 * fuer wissenschaftlich-technische Information mbH and Max-Planck-
 * Gesellschaft zur Foerderung der Wissenschaft e.V.  
 * All rights reserved.  Use is subject to license terms.
 */
package de.escidoc.core.test.client.integrationTests.classMapping.aa.pdp;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import de.escidoc.core.client.Authentication;
import de.escidoc.core.client.PolicyDecisionPointHandlerClient;
import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.common.jibx.Factory;
import de.escidoc.core.common.jibx.Marshaller;
import de.escidoc.core.resources.aa.pdp.Requests;
import de.escidoc.core.resources.aa.pdp.RequestsResults;
import de.escidoc.core.test.client.Constants;
import de.escidoc.core.test.client.EscidocClientTestBase;
import de.escidoc.core.test.client.util.Template;

/**
 * Test PDP Handler.
 * 
 * @author
 * 
 */
public class PdpHandlerClientTest {

    /**
     * Test to create and retrieve user account.
     * 
     * @throws Exception
     *             Thrown if anythings failed.
     */
    @Test
    public void testeEvaluateRequests() throws Exception {

        Authentication auth =
            new Authentication(EscidocClientTestBase.DEFAULT_SERVICE_URL,
                Constants.SYSTEM_ADMIN_USER, Constants.SYSTEM_ADMIN_PASSWORD);

        PolicyDecisionPointHandlerClient pdpc =
            new PolicyDecisionPointHandlerClient();
        pdpc.setServiceAddress(auth.getServiceAddress());
        pdpc.setHandle(auth.getHandle());


        Requests requests = createRequests();
        RequestsResults results = pdpc.evaluate(requests);

        Factory.getRequestsResultsMarshaller().marshalDocument(results);
    }

    /**
     * Create a PDP request message.
     * 
     * @return PDP Request message.
     * 
     * @throws ParserConfigurationException
     *             If parser configuration failed
     * @throws IOException
     *             If template access failed.
     * @throws SAXException
     *             If template parsing failed
     * @throws InternalClientException
     *             If internal client errors occur
     */
    private Requests createRequests() throws ParserConfigurationException,
        SAXException, IOException, InternalClientException {

        Requests requests = new Requests();

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        Document doc =
            builder.parse(Template.load("templates/soap/pdp/request1.xml"));
        Element root1 = doc.getDocumentElement();
        requests.addRequest(root1);

        Document doc2 =
            builder.parse(Template.load("templates/soap/pdp/request2.xml"));
        Element root2 = doc2.getDocumentElement();
        requests.addRequest(root2);

        Document doc3 =
            builder.parse(Template.load("templates/soap/pdp/request3.xml"));
        Element root3 = doc3.getDocumentElement();
        requests.addRequest(root3);

        Document doc4 =
            builder.parse(Template.load("templates/soap/pdp/request4.xml"));
        Element root4 = doc4.getDocumentElement();
        requests.addRequest(root4);

        Document doc5 =
            builder.parse(Template.load("templates/soap/pdp/request5.xml"));
        Element root5 = doc5.getDocumentElement();
        requests.addRequest(root5);

        Document doc6 =
            builder.parse(Template.load("templates/soap/pdp/request6.xml"));
        Element root6 = doc6.getDocumentElement();
        requests.addRequest(root6);

        Marshaller<Requests> m = new Marshaller<Requests>(requests.getClass());
        String xml = m.marshalDocument(requests);

        Requests urequests = m.unmarshalDocument(xml);
        Factory.getRequestsMarshaller().marshalDocument(urequests);

        return requests;
    }
}
