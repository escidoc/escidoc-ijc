package de.escidoc.core.test.client.unitTests.marshalling.sm.preprocessing;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import de.escidoc.core.client.TransportProtocol;
import de.escidoc.core.resources.sm.preprocess.PreprocessingInformation;
import de.escidoc.core.test.client.unitTests.marshalling.MarshallerTestBase;

public class PreprocessingMarshallerTest
    extends MarshallerTestBase<PreprocessingInformation> {

    private static final String BASE = "sm/preprocessing";

    private static final String XSD = "0.3";

    /**
     * @param transport
     * @throws IOException
     * @throws ParserConfigurationException
     * @throws SAXException
     */
    public PreprocessingMarshallerTest(final TransportProtocol transport)
        throws IOException, ParserConfigurationException, SAXException {
        super(PreprocessingInformation.class, BASE, XSD,
            "preprocessing_complete.xml", transport);
    }

    @Override
    protected void validate(final PreprocessingInformation obj)
        throws Exception {
        assertXPath(
            "/preprocessing:preprocessing-information/preprocessing:start-date",
            PreprocessingInformation.serialize(obj.getStartDate()));
        assertXPath(
            "/preprocessing:preprocessing-information/preprocessing:end-date",
            PreprocessingInformation.serialize(obj.getEndDate()));
    }

    @Override
    protected void testSubResources(final PreprocessingInformation obj)
        throws Exception {
        // TODO Auto-generated method stub
    }

    @Override
    protected void testResourceWithoutSubResources(
        final PreprocessingInformation obj) throws Exception {
        // TODO Auto-generated method stub
    }
}