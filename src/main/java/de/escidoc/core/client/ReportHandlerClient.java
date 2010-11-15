/**
 * 
 */
package de.escidoc.core.client;

import de.escidoc.core.client.exceptions.EscidocException;
import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.client.exceptions.TransportException;
import de.escidoc.core.client.rest.RestReportHandlerClient;
import de.escidoc.core.client.soap.SoapReportHandlerClient;
import de.escidoc.core.common.jibx.Factory;
import de.escidoc.core.resources.sm.report.Report;
import de.escidoc.core.resources.sm.report.ReportParameters;

/**
 * @author MVO
 * 
 */
public class ReportHandlerClient
    extends
    AbstractHandlerClient<SoapReportHandlerClient, RestReportHandlerClient> {

    /**
     * 
     */
    public ReportHandlerClient() {
        super();
    }

    /**
     * 
     * @param serviceAddress
     */
    public ReportHandlerClient(final String serviceAddress) {
        super(serviceAddress);
    }

    /**
     * 
     * @param xml
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public Report retrieve(final ReportParameters reportParameters)
        throws EscidocException, InternalClientException, TransportException {

        String xml =
            Factory
                .getMarshallerFactory(getTransport())
                .getMarshaller(ReportParameters.class)
                .marshalDocument(reportParameters);

        if (getTransport() == TransportProtocol.SOAP) {
            xml = getSoapHandlerClient().retrieve(xml);
        }
        else {
            xml = getRestHandlerClient().retrieve(xml);
        }

        return Factory
            .getMarshallerFactory(getTransport()).getMarshaller(Report.class)
            .unmarshalDocument(xml);
    }

    @Override
    protected SoapReportHandlerClient getSoapHandlerClientInstance()
        throws InternalClientException {
        return new SoapReportHandlerClient(getServiceAddress());
    }

    @Override
    protected RestReportHandlerClient getRestHandlerClientInstance()
        throws InternalClientException {
        return new RestReportHandlerClient(getServiceAddress());
    }

}
