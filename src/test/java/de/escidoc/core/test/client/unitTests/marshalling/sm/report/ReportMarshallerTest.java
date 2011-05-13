package de.escidoc.core.test.client.unitTests.marshalling.sm.report;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import de.escidoc.core.client.TransportProtocol;
import de.escidoc.core.resources.sm.DateParameter;
import de.escidoc.core.resources.sm.ParameterType;
import de.escidoc.core.resources.sm.report.Report;
import de.escidoc.core.test.client.unitTests.marshalling.MarshallerTestBase;

public class ReportMarshallerTest extends MarshallerTestBase<Report> {

    private static final String BASE = "sm/report";

    private static final String XSD = "0.4";

    /**
     * @param transport
     * @throws IOException
     * @throws ParserConfigurationException
     * @throws SAXException
     */
    public ReportMarshallerTest(final TransportProtocol transport) throws IOException, ParserConfigurationException,
        SAXException {
        super(Report.class, BASE, XSD, "report_complete.xml", transport);
    }

    @Override
    protected void validate(final Report obj) throws Exception {
        assertResource("/report:report/report:report-definition", obj.getReportDefinition());

        String path = "/report:report/report:report-record/report:parameter";

        assertNotNull(obj.getReportRecord());
        assertNotNull(obj.getReportRecord().getParameters());
        assertNotNull(obj.getReportRecord().getParameters().get(0));
        assertTrue(obj.getReportRecord().getParameters().get(0).getParameterType().equals(ParameterType.DATE));
        assertXPath(path + "[1]/@name", obj.getReportRecord().getParameters().get(0).getName());
        assertDateTime(path + "[1]/report:datevalue", ((DateParameter) obj.getReportRecord().getParameters().get(0))
            .getValue());

        assertNotNull(obj.getReportRecord().getParameters().get(1));
        assertTrue(obj.getReportRecord().getParameters().get(1).getParameterType().equals(ParameterType.STRING));
        assertXPath(path + "[2]/@name", obj.getReportRecord().getParameters().get(1).getName());
        assertXPath(path + "[2]/report:stringvalue", obj.getReportRecord().getParameters().get(1).getValue());

        assertNotNull(obj.getReportRecord().getParameters().get(2));
        assertTrue(obj.getReportRecord().getParameters().get(2).getParameterType().equals(ParameterType.DECIMAL));
        assertXPath(path + "[3]/@name", obj.getReportRecord().getParameters().get(2).getName());
        assertXPath(path + "[3]/report:decimalvalue", obj.getReportRecord().getParameters().get(2).getValue());
    }

    @Override
    protected void testSubResources(final Report obj) throws Exception {
        // TODO Auto-generated method stub
    }

    @Override
    protected void testResourceWithoutSubResources(final Report obj) throws Exception {
        // TODO Auto-generated method stub
    }
}