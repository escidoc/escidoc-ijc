package de.escidoc.core.client.interfaces;

import de.escidoc.core.client.exceptions.EscidocException;
import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.client.exceptions.TransportException;
import de.escidoc.core.client.interfaces.base.HandlerService;

public interface UserManagementWrapperClientInterface extends HandlerService {

    /**
     * 
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    void logout() throws EscidocException, InternalClientException, TransportException;
}
