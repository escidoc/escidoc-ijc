/**
 * 
 */
package de.escidoc.core.test.client.marshaller.sm;

import org.junit.Test;

import de.escidoc.core.client.TransportProtocol;
import de.escidoc.core.common.jibx.MarshallerFactory;
import de.escidoc.core.resources.sm.ad.AggregationDefinition;
import de.escidoc.core.test.client.AbstractParameterizedTestBase;
import de.escidoc.core.test.client.EscidocClientTestBase;
import de.escidoc.core.test.client.util.Template;

/**
 * @author Marko Voß
 * 
 */
public class AggregationDefinitionMarshallerTest
    extends AbstractParameterizedTestBase {

    private static final String XSD_VERSION = "0.4";

    private static final String BASE = "/sm";

    /**
     * @param transport
     */
    public AggregationDefinitionMarshallerTest(final TransportProtocol transport) {
        super(transport);
    }

    /**
     * 
     * @throws Exception
     */
    @Test
    public void testMarshallingForCdata01() throws Exception {
        String xml =
            EscidocClientTestBase.getXmlFileAsString(Template.loadMockup(
                transport, BASE, XSD_VERSION, "ad-01.xml"));

        AggregationDefinition ad =
            MarshallerFactory
                .getInstance(transport)
                .getMarshaller(AggregationDefinition.class)
                .unmarshalDocument(xml);

        MarshallerFactory
            .getInstance(transport).getMarshaller(AggregationDefinition.class)
            .marshalDocument(ad);
    }
}
