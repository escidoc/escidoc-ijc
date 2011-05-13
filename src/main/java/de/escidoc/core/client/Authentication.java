/*
 * CDDL HEADER START
 *
 * The contents of this file are subject to the terms of the
 * Common Development and Distribution License, Version 1.0 only
 * (the "License").  You may not use this file except in compliance
 * with the License.
 *
 * You can obtain a copy of the license at license/ESCIDOC.LICENSE
 * or http://www.escidoc.de/license.
 * See the License for the specific language governing permissions
 * and limitations under the License.
 *
 * When distributing Covered Code, include this CDDL HEADER in each
 * file and include the License file at license/ESCIDOC.LICENSE.
 * If applicable, add the following below this CDDL HEADER, with the
 * fields enclosed by brackets "[]" replaced with your own identifying
 * information: Portions Copyright [yyyy] [name of copyright owner]
 *
 * CDDL HEADER END
 */

/*
 * Copyright 2006-2008 Fachinformationszentrum Karlsruhe Gesellschaft
 * fuer wissenschaftlich-technische Information mbH and Max-Planck-
 * Gesellschaft zur Foerderung der Wissenschaft e.V.
 * All rights reserved.  Use is subject to license terms.
 */
package de.escidoc.core.client;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import de.escidoc.core.client.exceptions.EscidocClientException;
import de.escidoc.core.client.exceptions.EscidocException;
import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.client.exceptions.TransportException;
import de.escidoc.core.client.exceptions.application.security.AuthenticationException;
import de.escidoc.core.client.interfaces.UserManagementWrapperClientInterface;
import de.escidoc.core.common.URLUtility;
import de.escidoc.core.common.exceptions.remote.system.SystemException;

/**
 * Authenticate against eSciDoc framework.
 * 
 * @author FRS, SWA
 * 
 */
public class Authentication {

    private static final Logger LOG = Logger.getLogger(Authentication.class);

    private String handle;

    private URL serviceAddress;

    private String username;

    private UserManagementWrapperClientInterface userManagement;

    /**
     * Authentication.
     * 
     * @param serviceAddress
     *            URL of framework (login).
     * @param username
     *            Username
     * @param password
     *            Password.
     * @throws TransportException
     * @throws AuthenticationException
     * @throws InternalClientException
     * 
     * @throws IOException
     *             Thrown if Authentication failed.
     */
    public Authentication(final URL serviceAddress, final String username, final String password)
        throws AuthenticationException, TransportException {

        login(serviceAddress, username, password);
    }

    /**
     * @param serviceAddress
     * @param username
     * @param password
     * @throws AuthenticationException
     * @throws TransportException
     * @deprecated Use
     *             {@link Authentication#Authentication(URL, String, String)}
     *             instead.
     */
    @Deprecated
    public Authentication(final String serviceAddress, final String username, final String password)
        throws AuthenticationException, TransportException {

        URL url;
        try {
            url = new URL(serviceAddress);
        }
        catch (MalformedURLException e) {
            throw new TransportException(e);
        }

        login(url, username, password);
    }

    /**
     * Get the eSciDoc authentication handle.
     * 
     * @return handle (or null)
     */
    public String getHandle() {
        return this.handle;
    }

    /**
     * Get user name.
     * 
     * @return user name.
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * Get the address of the service.
     * 
     * @return Service Address
     */
    public URL getServiceAddress() {
        return this.serviceAddress;
    }

    /**
     * Get eSciDoc Authentication Handle.
     * 
     * @param serviceUrl
     *            URL of framework (login).
     * @param username
     *            Username
     * @param password
     *            Password.
     * @return eSciDoc Authentication handle
     * @throws TransportException
     * @throws AuthenticationException
     * 
     * @throws IOException
     *             Thrown if Authentication failed.
     */
    public String login(final URL serviceUrl, final String username, final String password) throws TransportException,
        AuthenticationException {

        this.serviceAddress = URLUtility.unifyAddress(serviceUrl);
        this.username = username;

        try {
            this.handle = getClient().getClient().getClient().login(username, password);
        }
        catch (SystemException e) {
            throw new TransportException(e.getMessage(), e);
        }
        catch (RemoteException e) {
            throw new AuthenticationException(e.getMessage(), e);
        }
        catch (InternalClientException e) {
            throw new TransportException(e.getMessage(), e);
        }

        return this.handle;
    }

    /**
     * @param serviceUrl
     * @param username
     * @param password
     * @return
     * @throws TransportException
     * @throws AuthenticationException
     * @deprecated Use {@link Authentication#login(URL, String, String)}
     *             instead.
     */
    @Deprecated
    public String login(final String serviceUrl, final String username, final String password)
        throws TransportException, AuthenticationException {

        URL url;
        try {
            url = new URL(serviceUrl);
        }
        catch (MalformedURLException e) {
            throw new TransportException(e);
        }

        return login(url, username, password);
    }

    /**
     * Logout from framework.
     * 
     * @throws EscidocClientException
     * @throws InternalClientException
     * @throws TransportException
     */
    public void logout() throws EscidocException, InternalClientException, TransportException {
        getClient().logout();
    }

    private UserManagementWrapperClient getClient() throws InternalClientException {
        if (userManagement == null) {
            userManagement = new UserManagementWrapperClient(serviceAddress);
            userManagement.setHandle(handle);
        }
        return (UserManagementWrapperClient) userManagement;
    }

    /**
     * Obtain handle from eSciDoc cookie.
     * 
     * @param cookieList
     *            List of all received cookies.
     * @return handle.
     */
    private String getEsciDocCookie(final List<String> cookieList) {

        String cHandle = null;
        // Get the user handle from the auth cookie.
        if (cookieList != null) {
            Iterator<String> cookieIterator = cookieList.iterator();
            while (cookieIterator.hasNext()) {
                String cookie = cookieIterator.next();
                if (cookie.toLowerCase().startsWith("escidoccookie=")) {
                    String[] parts = cookie.split(";");
                    cHandle = parts[0].split("=")[1];
                }
            }
        }
        return cHandle;
    }
}
