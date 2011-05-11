/**
 * 
 */
package de.escidoc.core.test.client.unitTests.marshalling.sm.reportParameters;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import de.escidoc.core.client.TransportProtocol;
import de.escidoc.core.resources.sm.DateParameter;
import de.escidoc.core.resources.sm.ParameterType;
import de.escidoc.core.resources.sm.report.ReportParameters;
import de.escidoc.core.test.client.unitTests.marshalling.MarshallerTestBase;

/**
 * @author Marko Voß
 * 
 */
public class ReportParametersMarshallerTest
    extends MarshallerTestBase<ReportParameters> {

    private static final String BASE = "sm/reportParameter";

    private static final String XSD = "0.4";

    /**
     * @param transport
     * @throws IOException
     * @throws ParserConfigurationException
     * @throws SAXException
     */
    public ReportParametersMarshallerTest(final TransportProtocol transport)
        throws IOException, ParserConfigurationException, SAXException {
        super(ReportParameters.class, BASE, XSD,
            "report-parameters_complete.xml", transport);
    }

    @Override
    protected void validate(final ReportParameters obj) throws Exception {
        assertResource("/report:report-parameters/report:report-definition",
            obj.getReportDefinition());

        String path = "/report:report-parameters/report:parameter";

        assertNotNull(obj.getParameters());
        assertNotNull(obj.getParameters().get(0));
        assertTrue(obj
            .getParameters().get(0).getParameterType()
            .equals(ParameterType.DATE));
        assertXPath(path + "[1]/@name", obj.getParameters().get(0).getName());
        assertDateTime(path + "[1]/report:datevalue", ((DateParameter) obj
            .getParameters().get(0)).getValue());

        assertNotNull(obj.getParameters().get(1));
        assertTrue(obj
            .getParameters().get(1).getParameterType()
            .equals(ParameterType.STRING));
        assertXPath(path + "[2]/@name", obj.getParameters().get(1).getName());
        assertXPath(path + "[2]/report:stringvalue", obj
            .getParameters().get(1).getValue());

        assertNotNull(obj.getParameters().get(2));
        assertTrue(obj
            .getParameters().get(2).getParameterType()
            .equals(ParameterType.DECIMAL));
        assertXPath(path + "[3]/@name", obj.getParameters().get(2).getName());
        assertXPath(path + "[3]/report:decimalvalue", obj
            .getParameters().get(2).getValue());
    }

    @Override
    protected void testSubResources(final ReportParameters obj)
        throws Exception {
        // TODO Auto-generated method stub
    }

    @Override
    protected void testResourceWithoutSubResources(final ReportParameters obj)
        throws Exception {
        // TODO Auto-generated method stub
    }
}