package de.escidoc.core.client.interfaces;

import de.escidoc.core.client.exceptions.EscidocClientException;
import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.client.exceptions.TransportException;

public interface UserManagementWrapperClientInterface {
void logout() throws EscidocClientException, InternalClientException,
TransportException;
}
