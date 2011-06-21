package de.escidoc.core.test.client.unitTests.marshalling.sm.statisticData;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import de.escidoc.core.client.TransportProtocol;
import de.escidoc.core.resources.sm.DateParameter;
import de.escidoc.core.resources.sm.ParameterType;
import de.escidoc.core.resources.sm.sd.StatisticData;
import de.escidoc.core.test.client.unitTests.marshalling.MarshallerTestBase;

public class StatisticDataMarshallerTest extends MarshallerTestBase<StatisticData> {

    private static final String BASE = "/sm/statisticData";

    private static final String XSD = "0.3";

    /**
     * @param transport
     * @throws IOException
     * @throws ParserConfigurationException
     * @throws SAXException
     */
    public StatisticDataMarshallerTest(final TransportProtocol transport) throws IOException,
        ParserConfigurationException, SAXException {
        super(StatisticData.class, BASE, XSD, "statistic-data_complete.xml", transport);
    }

    @Override
    protected void validate(final StatisticData obj) throws Exception {
        assertResource("/sd:statistic-record/sd:scope", obj.getScope());

        String path = "/sd:statistic-record/sd:parameter";

        assertNotNull(obj.getParameters());
        assertNotNull(obj.getParameters().get(0));
        assertTrue(obj.getParameters().get(0).getParameterType().equals(ParameterType.DATE));
        assertXPath(path + "[1]/@name", obj.getParameters().get(0).getName());
        assertDateTime(path + "[1]/sd:datevalue", ((DateParameter) obj.getParameters().get(0)).getValue());

        assertNotNull(obj.getParameters().get(1));
        assertTrue(obj.getParameters().get(1).getParameterType().equals(ParameterType.STRING));
        assertXPath(path + "[2]/@name", obj.getParameters().get(1).getName());
        assertXPath(path + "[2]/sd:stringvalue", obj.getParameters().get(1).getValue());

        assertNotNull(obj.getParameters().get(2));
        assertTrue(obj.getParameters().get(2).getParameterType().equals(ParameterType.DECIMAL));
        assertXPath(path + "[3]/@name", obj.getParameters().get(2).getName());
        assertXPath(path + "[3]/sd:decimalvalue", obj.getParameters().get(2).getValue());
    }

    @Override
    protected void testSubResources(final StatisticData obj) throws Exception {
        // TODO Auto-generated method stub

    }

    @Override
    protected void testResourceWithoutSubResources(final StatisticData obj) throws Exception {
        // TODO Auto-generated method stub

    }

}
