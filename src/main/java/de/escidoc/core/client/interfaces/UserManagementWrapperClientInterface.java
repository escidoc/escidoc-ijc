package de.escidoc.core.client.interfaces;

import de.escidoc.core.client.exceptions.EscidocException;
import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.client.exceptions.TransportException;

public interface UserManagementWrapperClientInterface
    extends HandlerServiceInterface {
    void logout() throws EscidocException, InternalClientException,
        TransportException;
}
