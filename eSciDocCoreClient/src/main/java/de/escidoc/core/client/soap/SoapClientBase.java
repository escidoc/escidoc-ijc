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

import org.apache.axis.EngineConfiguration;
import org.apache.axis.client.Stub;
import org.apache.axis.configuration.FileProvider;
import org.apache.log4j.Logger;
import org.apache.ws.security.WSConstants;
import org.apache.ws.security.WSPasswordCallback;
import org.apache.ws.security.handler.WSHandlerConstants;

import de.escidoc.core.client.ClientBase;
import de.escidoc.core.client.exceptions.InternalClientException;

/**
 * @author MVO
 * 
 */
public abstract class SoapClientBase extends ClientBase implements CallbackHandler {

    private static final String ESCIDOC_USER = "eSciDocUser";

    private static final String ENGINE_CONFIG_FILE = "clientlib_wss4j_config.wsdd";

    private static final EngineConfiguration engineConfig = new FileProvider(ENGINE_CONFIG_FILE);

    private final Logger LOG = Logger.getLogger(SoapClientBase.class);

    /**
     * Create ClientBase.
     * 
     * @param serviceAddress
     *            The service endpoint address.
     * @throws InternalClientException
     *             Thrown in case of client internal errors.
     */
    public SoapClientBase(final URL serviceAddress) throws InternalClientException {
        super(serviceAddress);
    }

    /**
     * 
     * @param serviceAddress
     * @throws InternalClientException
     * @deprecated Use {@link SoapClientBase#SoapClientBase(URL)} instead.
     */
    @Deprecated
    public SoapClientBase(final String serviceAddress) throws InternalClientException {
        super(serviceAddress);
    }

    /**
     * @return Returns the engineConfig.
     */
    public EngineConfiguration getEngineConfig() {
        return engineConfig;
    }

    /**
     * Check the given service address if it's base (server_host:port) is the
     * same as the constant <code>Constants.HOST_PORT</code>. If not
     * (server_host:port) is changed to the value configured in constant
     * <code>Constants.HOST_PORT</code>.
     * 
     * @param serviceUrl
     *            The original address.
     * @return The resulting address.
     */
    protected String checkSoapAddress(final String serviceUrl) {

        final int pSize = "http://".length();
        final int tSize = 5;

        String result = serviceUrl;
        if (!serviceUrl.startsWith("http://")) {
            String tmp = serviceUrl.substring(pSize);
            if (tmp.indexOf(':') != -1) {
                tmp = tmp.substring(tmp.indexOf(':') + tSize);
                result = "http://" + serviceUrl + tmp;
            }
        }
        if (LOG.isDebugEnabled()) {
            LOG.debug("Service address '" + result + "'");
        }
        return result;
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
    @Override
    public void handle(final Callback[] callbacks) throws IOException, UnsupportedCallbackException {

        final String handle = getHandle();

        for (final Callback callback : callbacks) {
            if (callback instanceof WSPasswordCallback) {
                final WSPasswordCallback pc = (WSPasswordCallback) callback;
                if (ESCIDOC_USER.equals(pc.getIdentifier())) {
                    pc.setPassword(handle == null ? "" : handle);
                }
            }
            else {
                throw new UnsupportedCallbackException(callback, "Unrecognized Callback");
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
    protected URL getHandlerServiceURL(final String handlerServiceAddress) throws InternalClientException {
        String address = handlerServiceAddress;
        URL url = null;
        try {
            url = new URL(address);
        }
        catch (final MalformedURLException e) {
            throw new InternalClientException(e);
        }
        final String path = url.getFile();
        address = getServiceAddress() + path;

        try {
            url = new URL(handlerServiceAddress);
        }
        catch (final MalformedURLException e) {
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
    protected void registerPWCallback(final Remote remoteService) throws InternalClientException {
        if (remoteService instanceof Stub) {
            final Stub stub = (Stub) remoteService;

            stub._setProperty(WSHandlerConstants.PW_CALLBACK_REF, this);
            stub._setProperty(WSHandlerConstants.ACTION, WSHandlerConstants.USERNAME_TOKEN);
            stub._setProperty(WSHandlerConstants.PASSWORD_TYPE, WSConstants.PW_TEXT);
            stub._setProperty(WSHandlerConstants.USER, ESCIDOC_USER);
            stub._setProperty("addUTElement", "Nonce Created");
        }
        else {
            throw new InternalClientException(
                "Specified remoteService is not an instance of Stub. Unable to register PWCallback.");
        }
    }
}
