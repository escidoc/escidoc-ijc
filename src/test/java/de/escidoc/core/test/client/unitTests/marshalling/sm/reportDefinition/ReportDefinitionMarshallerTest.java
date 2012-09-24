package de.escidoc.core.test.client.unitTests.marshalling.sm.reportDefinition;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import de.escidoc.core.client.TransportProtocol;
import de.escidoc.core.resources.sm.report.ReportDefinition;
import de.escidoc.core.test.client.unitTests.marshalling.MarshallerTestBase;

public class ReportDefinitionMarshallerTest extends MarshallerTestBase<ReportDefinition> {

    private static final String BASE = "sm/reportDefinition";

    private static final String XSD = "0.4";

    /**
     * @param transport
     * @throws IOException
     * @throws ParserConfigurationException
     * @throws SAXException
     */
    public ReportDefinitionMarshallerTest(final TransportProtocol transport) throws IOException,
        ParserConfigurationException, SAXException {
        super(ReportDefinition.class, BASE, XSD, "report-definition_complete.xml", transport);
    }

    @Override
    protected void validate(final ReportDefinition obj) throws Exception {
        assertResource("/report-def:report-definition", obj);
        assertDateTime("/report-def:report-definition/@last-modification-date", obj.getLastModificationDate());
        assertXPath("/report-def:report-definition/report-def:name", obj.getName());
        assertResource("/report-def:report-definition/report-def:scope", obj.getScope());
        assertXPath("/report-def:report-definition/report-def:sql", obj.getSql());

        assertNotNull(obj.getAllowedRoles());
        assertNotNull(obj.getAllowedRoles().get(0));

        assertResource("/report-def:report-definition/report-def:allowed-roles/report-def:allowed-role[1]", obj
            .getAllowedRoles().get(0));
    }

    @Override
    protected void testSubResources(final ReportDefinition obj) throws Exception {
        // TODO Auto-generated method stub
    }

    @Override
    protected void testResourceWithoutSubResources(final ReportDefinition obj) throws Exception {
        // TODO Auto-generated method stub
    }
}