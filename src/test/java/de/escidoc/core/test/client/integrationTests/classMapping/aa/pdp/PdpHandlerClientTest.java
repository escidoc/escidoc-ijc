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

import static org.junit.Assert.assertTrue;

import java.net.URI;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.sun.xacml.attr.StringAttribute;
import com.sun.xacml.ctx.Attribute;
import com.sun.xacml.ctx.RequestCtx;
import com.sun.xacml.ctx.Subject;

import de.escidoc.core.client.Authentication;
import de.escidoc.core.client.PolicyDecisionPointHandlerClient;
import de.escidoc.core.client.TransportProtocol;
import de.escidoc.core.client.interfaces.PolicyDecisionPointHandlerClientInterface;
import de.escidoc.core.common.jibx.Factory;
import de.escidoc.core.common.jibx.Marshaller;
import de.escidoc.core.resources.aa.pdp.Decision;
import de.escidoc.core.resources.aa.pdp.Requests;
import de.escidoc.core.resources.aa.pdp.Result;
import de.escidoc.core.resources.aa.pdp.Results;
import de.escidoc.core.test.client.AbstractParameterizedTestBase;
import de.escidoc.core.test.client.Constants;
import de.escidoc.core.test.client.EscidocClientTestBase;
import de.escidoc.core.test.client.util.Template;

/**
 * Test PDP Handler.
 * 
 * @author
 * 
 */
public class PdpHandlerClientTest extends AbstractParameterizedTestBase {

    private Authentication auth;

    private PolicyDecisionPointHandlerClientInterface pdpc;

    public PdpHandlerClientTest(TransportProtocol transport) {
        super(transport);
    }

    @Before
    public void init() throws Exception {
        auth =
            new Authentication(EscidocClientTestBase.DEFAULT_SERVICE_URL,
                Constants.SYSTEM_ADMIN_USER, Constants.SYSTEM_ADMIN_PASSWORD);
        pdpc = new PolicyDecisionPointHandlerClient(auth.getServiceAddress());
        pdpc.setHandle(auth.getHandle());
        pdpc.setTransport(transport);
    }

    @After
    public void post() throws Exception {
        auth.logout();
    }

    @Test
    public void testMarshalling() throws Exception {
        String xml =
            EscidocClientTestBase.getXmlFileAsString(Template.load(transport
                .name().toLowerCase() + "/aa/pdp/requests.xml"));
        Marshaller<Requests> m =
            Factory.getMarshallerFactory(transport).getPDPRequestsMarshaller();

        Requests requests = m.unmarshalDocument(xml);
        m.marshalDocument(requests);
    }

    /**
     * Test to create and retrieve user account.
     * 
     * @throws Exception
     *             Thrown if anythings failed.
     */
    @Test
    public void testEvaluateRequests() throws Exception {
        Requests requests = new Requests();

        // request 1
        Set<Attribute> attributes = new HashSet<Attribute>();
        attributes.add(new Attribute(new URI(
            "urn:oasis:names:tc:xacml:1.0:subject:subject-id"), null, null,
            new StringAttribute("escidoc:user1")));
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

        requests.add(new RequestCtx(subjects, resourceAttrs, actionAttrs,
            Requests.DEFAULT_ENVIRONMENT));

        // request 2 - invalid request
        attributes = new HashSet<Attribute>();
        attributes.add(new Attribute(new URI(
            "urn:oasis:names:tc:xacml:1.0:subject:subject-id"), null, null,
            new StringAttribute("escidoc:user42")));
        subjects = new HashSet<Subject>();
        subjects.add(new Subject(Subject.DEFAULT_CATEGORY, attributes));

        resourceAttrs = new HashSet<Attribute>();
        resourceAttrs.add(new Attribute(new URI(
            "info:escidoc/names:aa:1.0:resource:object-type-new"), null, null,
            new StringAttribute("item")));
        resourceAttrs.add(new Attribute(new URI(
            "info:escidoc/names:aa:1.0:resource:item:context-new"), null, null,
            new StringAttribute("escidoc:persistent3")));

        actionAttrs = new HashSet<Attribute>();
        actionAttrs
            .add(new Attribute(new URI(
                "urn:oasis:names:tc:xacml:1.0:action:action-id"), null, null,
                new StringAttribute(
                    "info:escidoc/names:aa:1.0:action:create-item")));

        requests.add(new RequestCtx(subjects, resourceAttrs, actionAttrs,
            Requests.DEFAULT_ENVIRONMENT));

        // Response
        Results results = pdpc.evaluate(requests);

        Iterator<?> itResult = results.iterator();
        Iterator<?> itRequest = requests.iterator();

        // both iterators should have the same count of entries
        while (itResult.hasNext() && itRequest.hasNext()) {

            Result result = (Result) itResult.next();
            RequestCtx requestCtx = (RequestCtx) itRequest.next();

            // there should be only one resultCtx inside most times
            for (Iterator<?> it2 =
                result.getResponseCtx().getResults().iterator(); it2.hasNext();) {
                com.sun.xacml.ctx.Result resultCtx =
                    (com.sun.xacml.ctx.Result) it2.next();

                if (result.getInterpretedDecision() == Decision.permit) {

                    assertTrue(resultCtx.getDecision() == com.sun.xacml.ctx.Result.DECISION_PERMIT);

                    @SuppressWarnings("unchecked")
                    Set<Attribute> resAttrs =
                        (Set<Attribute>) requestCtx.getResource();

                    for (Iterator<Attribute> itx = resAttrs.iterator(); itx
                        .hasNext();) {
                        Attribute attribute = itx.next();
                        if (attribute
                            .getId()
                            .toString()
                            .equals(
                                "urn:oasis:names:tc:xacml:1.0:resource:resource-id")) {

                            assertTrue(resultCtx.getResource().equals(
                                attribute.getValue().encode()));
                        }
                    }
                }
            }
        }
    }

    /**
     * Currently, the infrastructure does not support multiple subjects in one
     * RequestCtx. No exception is thrown. The exception is part of the
     * ResponseCtx. So you will get a decision deny instead of an Exception.
     * 
     * @throws Exception
     */
    @Test
    public void testMultipleSubjects() throws Exception {
        Requests requests = new Requests();

        Set<Subject> subjects = new HashSet<Subject>();

        // subject 1 - admin
        Set<Attribute> attrs01 = new HashSet<Attribute>();
        attrs01.add(new Attribute(new URI(
            "urn:oasis:names:tc:xacml:1.0:subject:subject-id"), null, null,
            new StringAttribute("escidoc:user1")));

        subjects.add(new Subject(Subject.DEFAULT_CATEGORY, attrs01));

        // subject 2 - test user without grants
        Set<Attribute> attrs02 = new HashSet<Attribute>();
        attrs02.add(new Attribute(new URI(
            "urn:oasis:names:tc:xacml:1.0:subject:subject-id"), null, null,
            new StringAttribute("escidoc:test")));

        subjects.add(new Subject(Subject.DEFAULT_CATEGORY, attrs02));

        // resource
        Set<Attribute> resourceAttrs = new HashSet<Attribute>();
        resourceAttrs.add(new Attribute(new URI(
            "urn:oasis:names:tc:xacml:1.0:resource:resource-id"), null, null,
            new StringAttribute("escidoc:persistent1")));

        // action
        Set<Attribute> actionAttrs = new HashSet<Attribute>();
        actionAttrs
            .add(new Attribute(
                new URI("urn:oasis:names:tc:xacml:1.0:action:action-id"),
                null,
                null,
                new StringAttribute(
                    "info:escidoc/names:aa:1.0:action:retrieve-organizational-unit")));

        // request with default environment
        requests.add(new RequestCtx(subjects, resourceAttrs, actionAttrs,
            Requests.DEFAULT_ENVIRONMENT));

        // Response
        Results results = pdpc.evaluate(requests);

        for (Iterator<Result> it = results.iterator(); it.hasNext();) {
            Result result = it.next();
            assertTrue(result.getInterpretedDecision() == Decision.deny);
        }
    }
}
