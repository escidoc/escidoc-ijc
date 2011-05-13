/**
 * 
 */
package de.escidoc.core.client.interfaces.base;

import de.escidoc.core.client.exceptions.EscidocException;
import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.client.exceptions.TransportException;
import de.escidoc.core.resources.common.Relations;

/**
 * @author MVO
 * 
 * @param <T>
 */
public interface RelationsService {

    /**
     * 
     * @param id
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    Relations retrieveRelations(final String id) throws EscidocException, InternalClientException, TransportException;
}
