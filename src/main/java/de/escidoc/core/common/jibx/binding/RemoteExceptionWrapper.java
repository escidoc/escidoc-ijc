/**
 * 
 */
package de.escidoc.core.common.jibx.binding;

import java.rmi.RemoteException;

import de.escidoc.core.annotations.JiBX;

/**
 * @author MVO
 * 
 */
@JiBX
public class RemoteExceptionWrapper {

    private final RemoteException remoteException;

    /**
     * @param remoteException
     */
    public RemoteExceptionWrapper(final RemoteException remoteException) {
        if (remoteException == null)
            throw new IllegalArgumentException("remoteException must not be null.");

        this.remoteException = remoteException;
    }

    /**
     * @return the remoteException
     */
    public final RemoteException getRemoteException() {
        return remoteException;
    }

}
