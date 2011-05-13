/**
 * 
 */
package de.escidoc.core.client.interfaces;

import de.escidoc.core.client.exceptions.EscidocException;
import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.client.exceptions.TransportException;
import de.escidoc.core.client.interfaces.base.HandlerService;
import de.escidoc.core.resources.sm.report.Report;
import de.escidoc.core.resources.sm.report.ReportParameters;

/**
 * @author MVO
 * 
 */
public interface ReportHandlerClientInterface extends HandlerService {

    /**
     * 
     * @param reportParameters
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    Report retrieve(final ReportParameters reportParameters) throws EscidocException, InternalClientException,
        TransportException;
}
