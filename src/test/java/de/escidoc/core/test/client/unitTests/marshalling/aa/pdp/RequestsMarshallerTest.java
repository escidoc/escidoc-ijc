/**
 * 
 */
package de.escidoc.core.test.client.unitTests.marshalling.aa.pdp;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.w3c.dom.DOMException;
import org.xml.sax.SAXException;

import com.sun.xacml.ctx.Attribute;
import com.sun.xacml.ctx.Subject;

import de.escidoc.core.client.TransportProtocol;
import de.escidoc.core.resources.aa.pdp.Requests;
import de.escidoc.core.test.client.unitTests.marshalling.MarshallerTestBase;

/**
 * @author Marko Voß
 * 
 */
public class RequestsMarshallerTest extends MarshallerTestBase<Requests> {

    private static final String BASE = "aa/pdp";

    private static final String XSD = "0.3";

    /**
     * @param transport
     * @throws IOException
     * @throws ParserConfigurationException
     * @throws SAXException
     */
    public RequestsMarshallerTest(final TransportProtocol transport)
        throws IOException, ParserConfigurationException, SAXException {
        super(Requests.class, BASE, XSD, "requests.xml", transport);
    }

    @Override
    protected void validate(final Requests obj) throws Exception {

        String path = "/requests:requests/xacml-context:Request";

        // RequestCtx
        assertNotNull(obj.get(0));

        // RequestCtx.Subject
        assertNotNull(obj.get(0).getSubjects());
        Iterator<?> it = obj.get(0).getSubjects().iterator();
        assertTrue(it.hasNext());
        Subject subject = (Subject) it.next();

        assertXPath(path + "/xacml-context:Subject/@SubjectCategory",
            subject.getCategory());

        // RequestCtx.Subject.Attribute
        validateAttribute(path + "/xacml-context:Subject",
            subject.getAttributes());

        // RequestCtx.Resource.Attribute
        validateAttribute(path + "/xacml-context:Resource", obj
            .get(0).getResource());

        validateAttribute(path + "/xacml-context:Action", obj
            .get(0).getAction());
    }

    /**
     * @param attributes
     * @param xPathContext
     * @throws DOMException
     * @throws TransformerException
     */
    private void validateAttribute(
        final String xPathContext, final Set<?> attributes)
        throws DOMException, TransformerException {

        assertNotNull(attributes);
        Iterator<?> it = attributes.iterator();
        assertTrue(it.hasNext());
        Attribute attr = (Attribute) it.next();

        assertXPath(xPathContext + "/xacml-context:Attribute/@AttributeId",
            attr.getId());
        assertXPath(xPathContext + "/xacml-context:Attribute/@DataType",
            attr.getType());
        assertXPath(xPathContext
            + "/xacml-context:Attribute/xacml-context:AttributeValue", attr
            .getValue().encode());
        // TODO test missing getters on RequestCtx.Subject.Attribute
    }

    @Override
    protected void testSubResources(final Requests obj) throws Exception {
        // nothing to do
    }

    @Override
    protected void testResourceWithoutSubResources(final Requests obj)
        throws Exception {
        // nothing to do
    }
}