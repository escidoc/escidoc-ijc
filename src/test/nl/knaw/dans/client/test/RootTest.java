package nl.knaw.dans.client.test;

import static org.junit.Assert.assertEquals;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.common.jibx.Marshaller;
import de.escidoc.core.test.client.EscidocClientTestBase;

public class RootTest {

    /**
     * 
     * @throws InternalClientException
     * @throws ParserConfigurationException
     */
    @Test
    public void testMandU() throws InternalClientException,
        ParserConfigurationException {
        Root root = new Root();
        root.setNormalElement("<foo>Why 1 < 2, and not 2 &lt; 1</foo>");
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.newDocument();
        Element element = doc.createElement("foo");
        element.setTextContent("Why 1 < 2, and not 2 &lt; 1");
        root.getMdRecord().setAnyElement(element);

        Marshaller<Root> m =
            new Marshaller<Root>(root.getClass(),
                EscidocClientTestBase.getDefaultTransportProtocol());
        String xml = m.marshalDocument(root);
        System.out.println(xml);

        Root uroot = m.unmarshalDocument(xml);
        assertEquals("<foo>Why 1 < 2, and not 2 &lt; 1</foo>",
            uroot.getNormalElement());
        assertEquals("Why 1 < 2, and not 2 &lt; 1", uroot
            .getMdRecord().getAnyElement().getTextContent());
    }

}
