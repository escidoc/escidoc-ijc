/**
 * 
 */
package de.escidoc.core.client.interfaces;

import de.escidoc.core.client.exceptions.EscidocException;
import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.client.exceptions.TransportException;
import de.escidoc.core.client.interfaces.base.HandlerService;
import de.escidoc.core.resources.sm.preprocess.PreprocessingInformation;

/**
 * @author MVO
 * 
 */
public interface PreprocessingHandlerClientInterface extends HandlerService {

    /**
     * 
     * @param aggregationDefinitionId
     * @param info
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    void preprocess(
        final String aggregationDefinitionId,
        final PreprocessingInformation info) throws EscidocException,
        InternalClientException, TransportException;
}
