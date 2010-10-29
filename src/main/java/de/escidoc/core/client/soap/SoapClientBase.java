/**
 * 
 */
package de.escidoc.core.client.soap;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.Remote;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;

import org.apache.axis.client.Stub;
import org.apache.ws.security.WSConstants;
import org.apache.ws.security.WSPasswordCallback;
import org.apache.ws.security.handler.WSHandlerConstants;

import de.escidoc.core.client.ClientBase;
import de.escidoc.core.client.exceptions.InternalClientException;

/**
 * @author MVO
 * 
 */
public abstract class SoapClientBase extends ClientBase
    implements CallbackHandler {

    public static final String ESCIDOC_USER = "eSciDocUser";

    /**
     * Create an instance of client base using the default constructor.
     * 
     * @throws InternalClientException
     *             Thrown in case of client internal errors.
     */
    public SoapClientBase() throws InternalClientException {
        super();
    }

    /**
     * Create ClientBase.
     * 
     * @param serviceAddress
     *            The service endpoint address.
     * @throws InternalClientException
     *             Thrown in case of client internal errors.
     */
    public SoapClientBase(final String serviceAddress)
        throws InternalClientException {
        super(serviceAddress);
    }

    /**
     * The handle method of the callback handler.
     * 
     * @param callbacks
     *            the WSPasswordCallback implementation
     * @throws IOException
     *             Exception
     * @throws UnsupportedCallbackException
     *             Exception
     * @see javax.security.auth.callback.CallbackHandler#handle
     *      (javax.security.auth.callback.Callback[])
     */
    public void handle(final Callback[] callbacks) throws IOException,
        UnsupportedCallbackException {

        String handle = getHandle();

        for (int i = 0; i < callbacks.length; i++) {
            if (callbacks[i] instanceof WSPasswordCallback) {
                WSPasswordCallback pc = (WSPasswordCallback) callbacks[i];
                if (ESCIDOC_USER.equals(pc.getIdentifier())) {
                    pc.setPassword((handle == null) ? "" : handle);
                }
            }
            else {
                throw new UnsupportedCallbackException(callbacks[i],
                    "Unrecognized Callback");
            }
        }
    }

    /**
     * 
     * 
     * @param handlerServiceAddress
     * @return
     * @throws InternalClientException
     */
    protected URL getHandlerServiceURL(final String handlerServiceAddress)
        throws InternalClientException {
        String address = handlerServiceAddress;
        URL url = null;
        try {
            url = new URL(address);
        }
        catch (MalformedURLException e) {
            throw new InternalClientException(e);
        }
        String path = url.getFile();
        address = getServiceAddress() + path;

        try {
            url = new URL(handlerServiceAddress);
        }
        catch (MalformedURLException e) {
            throw new InternalClientException(e);
        }
        return url;
    }

    /**
     * Registers this instance as the handler for the PWCallback process to pass
     * through the specified handle.
     * 
     * @param remoteService
     * @throws InternalClientException
     *             if the specified remoteService is not an instance of Stub.
     */
    protected void registerPWCallback(Remote remoteService)
        throws InternalClientException {
        if (remoteService instanceof Stub) {
            Stub stub = (Stub) remoteService;

            stub._setProperty(WSHandlerConstants.PW_CALLBACK_REF, this);
            stub._setProperty(WSHandlerConstants.ACTION,
                WSHandlerConstants.USERNAME_TOKEN);
            stub._setProperty(WSHandlerConstants.PASSWORD_TYPE,
                WSConstants.PW_TEXT);
            stub._setProperty(WSHandlerConstants.USER, ESCIDOC_USER);
            stub._setProperty("addUTElement", "Nonce Created");
        }
        else {
            throw new InternalClientException(
                "Specified remoteService is not an instance of Stub. Unable to register PWCallback.");
        }
    }
}
