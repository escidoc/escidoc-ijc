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
import de.escidoc.core.common.jibx.MarshallerFactory;
import de.escidoc.core.resources.sm.report.Report;
import de.escidoc.core.resources.sm.report.ReportParameters;

/**
 * @author MVO
 * 
 */
public class ReportHandlerClient extends AbstractHandlerClient<RestReportHandlerClient>
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
     * @param serviceAddress
     * @deprecated Use {@link ReportHandlerClient#ReportHandlerClient(URL)}
     *             instead.
     */
    @Deprecated
    public ReportHandlerClient(final String serviceAddress) {
        super(serviceAddress);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.client.interfaces.ReportHandlerClientInterface#retrieve
     * (de.escidoc.core.resources.sm.report.ReportParameters)
     */
    @Override
    public Report retrieve(final ReportParameters reportParameters) throws EscidocException, InternalClientException,
        TransportException {

        String xml =
            MarshallerFactory.getInstance().getMarshaller(ReportParameters.class).marshalDocument(reportParameters);

        xml = getClient().retrieve(xml);

        return MarshallerFactory.getInstance().getMarshaller(Report.class).unmarshalDocument(xml);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.client.AbstractHandlerClient#getRestHandlerClientInstance
     * ()
     */
    @Override
    protected RestReportHandlerClient getRestHandlerClientInstance() throws InternalClientException {
        return new RestReportHandlerClient(getServiceAddress());
    }
}