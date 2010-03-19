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

import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import de.escidoc.core.client.PolicyDecisionPointHandlerClient;
import de.escidoc.core.common.jibx.Factory;
import de.escidoc.core.common.jibx.Marshaller;
import de.escidoc.core.resources.aa.pdp.Requests;
import de.escidoc.core.resources.aa.pdp.RequestsResults;
import de.escidoc.core.test.client.Constants;

public class PdpHandlerClientTest {

    /**
     * Test to create and retrieve user account.
     * 
     * @throws Exception
     *             Thrown if anythings failed.
     */
    @Test
    public void testeEvaluateRequests() throws Exception {

        PolicyDecisionPointHandlerClient pdpc =
            new PolicyDecisionPointHandlerClient();
        pdpc.setHandle(Constants.DEFAULT_HANDLE);
        Requests requests = createRequests();
        RequestsResults results = pdpc.evaluate(requests);

        Factory.getRequestsResultsMarshaller().marshalDocument(results);

    }

    /**
     * 
     * @return
     * @throws Exception
     */
    private Requests createRequests() throws Exception {
        Requests requests = new Requests();

        InputStream input = getClass().getResourceAsStream("request1.xml");
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(input);
        Element root1 = doc.getDocumentElement();
        requests.addRequest(root1);

        input = getClass().getResourceAsStream("request2.xml");
        Document doc2 = builder.parse(input);
        Element root2 = doc2.getDocumentElement();
        requests.addRequest(root2);

        input = getClass().getResourceAsStream("request3.xml");
        Document doc3 = builder.parse(input);
        Element root3 = doc3.getDocumentElement();
        requests.addRequest(root3);

        input = getClass().getResourceAsStream("request4.xml");
        Document doc4 = builder.parse(input);
        Element root4 = doc4.getDocumentElement();
        requests.addRequest(root4);

        input = getClass().getResourceAsStream("request5.xml");
        Document doc5 = builder.parse(input);
        Element root5 = doc5.getDocumentElement();
        requests.addRequest(root5);

        input = getClass().getResourceAsStream("request6.xml");
        Document doc6 = builder.parse(input);
        Element root6 = doc6.getDocumentElement();
        requests.addRequest(root6);

        Marshaller<Requests> m = new Marshaller<Requests>(requests.getClass());
        String xml = m.marshalDocument(requests);

        Requests urequests = m.unmarshalDocument(xml);
        Factory.getRequestsMarshaller().marshalDocument(urequests);

        return requests;

    }

}
