/**
 * 
 */
package de.escidoc.core.client;

import java.net.URL;

import de.escidoc.core.client.exceptions.EscidocException;
import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.client.exceptions.TransportException;
import de.escidoc.core.client.interfaces.ReportHandlerClientInterface;
import de.escidoc.core.client.rest.RestReportHandlerClient;
import de.escidoc.core.client.soap.SoapReportHandlerClient;
import de.escidoc.core.common.jibx.MarshallerFactory;
import de.escidoc.core.resources.sm.report.Report;
import de.escidoc.core.resources.sm.report.ReportParameters;

/**
 * @author MVO
 * 
 */
public class ReportHandlerClient
    extends
    AbstractHandlerClient<SoapReportHandlerClient, RestReportHandlerClient>
    implements ReportHandlerClientInterface {

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
    public ReportHandlerClient(final URL serviceAddress) {
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
    @Override
    public Report retrieve(final ReportParameters reportParameters)
        throws EscidocException, InternalClientException, TransportException {

        String xml =
            MarshallerFactory
                .getInstance(getTransport())
                .getMarshaller(ReportParameters.class)
                .marshalDocument(reportParameters);

        if (getTransport() == TransportProtocol.SOAP) {
            xml = getSoapHandlerClient().retrieve(xml);
        }
        else {
            xml = getRestHandlerClient().retrieve(xml);
        }

        return MarshallerFactory
            .getInstance(getTransport()).getMarshaller(Report.class)
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
