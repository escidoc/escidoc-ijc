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

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.HashSet;
import java.util.Set;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.Test;
import org.xml.sax.SAXException;

import com.sun.xacml.attr.StringAttribute;
import com.sun.xacml.ctx.Attribute;
import com.sun.xacml.ctx.RequestCtx;
import com.sun.xacml.ctx.Subject;

import de.escidoc.core.client.Authentication;
import de.escidoc.core.client.PolicyDecisionPointHandlerClient;
import de.escidoc.core.client.TransportProtocol;
import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.client.interfaces.PolicyDecisionPointHandlerClientInterface;
import de.escidoc.core.common.jibx.Factory;
import de.escidoc.core.common.jibx.Marshaller;
import de.escidoc.core.resources.aa.pdp.Requests;
import de.escidoc.core.resources.aa.pdp.RequestsResults;
import de.escidoc.core.test.client.AbstractParameterizedTestBase;
import de.escidoc.core.test.client.Constants;
import de.escidoc.core.test.client.EscidocClientTestBase;

/**
 * Test PDP Handler.
 * 
 * @author
 * 
 */
public class PdpHandlerClientTest extends AbstractParameterizedTestBase {

    public PdpHandlerClientTest(TransportProtocol transport) {
        super(transport);
    }

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

        PolicyDecisionPointHandlerClientInterface pdpc =
            new PolicyDecisionPointHandlerClient();
        pdpc.setServiceAddress(auth.getServiceAddress());
        pdpc.setHandle(auth.getHandle());
        pdpc.setTransport(transport);

        // Requests requests = createRequests();
        String xml =
            EscidocClientTestBase.getXmlFileAsString(new File(
                "templates/soap/pdp/requests.xml"));
        Marshaller<Requests> m =
            Factory.getMarshallerFactory(transport).getRequestsMarshaller();
        m.setBindingName(transport.name());

        Requests requests = m.unmarshalDocument(xml);

        System.out.println(m.marshalDocument(requests));

        RequestsResults results = pdpc.evaluate(requests);

        // String xml =
        // Factory
        // .getMarshallerFactory(transport).getRequestsResultsMarshaller()
        // .marshalDocument(results);
        // System.out.println(xml);

        /**
         * TEST IMPL OF PDP REQUEST/RESPONSE
         */

        /*
         * PDP
         */

        // PolicyFinder policyFinder = new PolicyFinder();
        // Set policyModules = new HashSet();
        // policyModules.add(new PolicyM);
        // policyFinder.setModules(policyModules);
        //
        // AttributeFinder attrFinder = new AttributeFinder();
        // List attrModules = new ArrayList();
        // attrModules.add(new Attr);
        // attrFinder.setModules(attrModules);

        /*
         * REQUEST
         */

        // SUBJECT
        Set<Attribute> attributes = new HashSet<Attribute>();
        attributes.add(new Attribute(new URI(
            "urn:oasis:names:tc:xacml:1.0:subject:subject-id"), null, null,
            new StringAttribute("escidoc:user1")));
        // bundle the attributes in a Subject with the default category
        Set<Subject> subjects = new HashSet<Subject>();
        subjects.add(new Subject(Subject.DEFAULT_CATEGORY, attributes));

        Set<Attribute> resourceAttrs = new HashSet<Attribute>();
        resourceAttrs.add(new Attribute(new URI(
            "urn:oasis:names:tc:xacml:1.0:resource:resource-id"), null, null,
            new StringAttribute("escidoc:persistent1")));

        Set<Attribute> actionAttrs = new HashSet<Attribute>();
        actionAttrs
            .add(new Attribute(
                new URI("urn:oasis:names:tc:xacml:1.0:action:action-id"),
                null,
                null,
                new StringAttribute(
                    "info:escidoc/names:aa:1.0:action:retrieve-organizational-unit")));

        Set<Attribute> envAttrs = new HashSet<Attribute>();

        RequestCtx request =
            new RequestCtx(subjects, resourceAttrs, actionAttrs, envAttrs);
        // ResponseCtx response = pdp.evaluate(request);
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

        // DocumentBuilderFactory factory =
        // DocumentBuilderFactory.newInstance();
        // DocumentBuilder builder = factory.newDocumentBuilder();
        //
        // Document doc =
        // builder.parse(Template.load("templates/soap/pdp/request1.xml"));
        // Element root1 = doc.getDocumentElement();
        // requests.addRequest(root1);
        //
        // Document doc2 =
        // builder.parse(Template.load("templates/soap/pdp/request2.xml"));
        // Element root2 = doc2.getDocumentElement();
        // requests.addRequest(root2);
        //
        // Document doc3 =
        // builder.parse(Template.load("templates/soap/pdp/request3.xml"));
        // Element root3 = doc3.getDocumentElement();
        // requests.addRequest(root3);
        //
        // Document doc4 =
        // builder.parse(Template.load("templates/soap/pdp/request4.xml"));
        // Element root4 = doc4.getDocumentElement();
        // requests.addRequest(root4);
        //
        // Document doc5 =
        // builder.parse(Template.load("templates/soap/pdp/request5.xml"));
        // Element root5 = doc5.getDocumentElement();
        // requests.addRequest(root5);
        //
        // Document doc6 =
        // builder.parse(Template.load("templates/soap/pdp/request6.xml"));
        // Element root6 = doc6.getDocumentElement();
        // requests.addRequest(root6);
        //
        // Marshaller<Requests> m =
        // new Marshaller<Requests>(requests.getClass(), transport.name());
        // String xml = m.marshalDocument(requests);
        //
        // Requests urequests = m.unmarshalDocument(xml);
        // Factory
        // .getMarshallerFactory(transport).getRequestsMarshaller()
        // .marshalDocument(urequests);

        return requests;
    }
}
