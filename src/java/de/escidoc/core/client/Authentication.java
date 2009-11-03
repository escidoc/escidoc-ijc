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
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.List;

/**
 * Authenticate against eSciDoc framework.
 * 
 * @author FRS, SWA
 * 
 */
public class Authentication {

    private String handle = null;

    private String serviceAddress = null;

    /**
     * Authentication.
     */
    public Authentication() {
    }

    /**
     * Authentication.
     * 
     * @param serviceAddress
     *            URL of framework (login).
     * @param username
     *            Username
     * @param password
     *            Password.
     * 
     * @throws IOException
     *             Thrown if Authentication failed.
     */
    public Authentication(final String serviceAddress, final String username,
        final String password) throws IOException {

        login(serviceAddress, username, password);
    }

    /**
     * Get the eSciDoc authentication handle.
     * 
     * @return handle (or null)
     */
    public String getAuthHandle() {
        return this.handle;
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
     * 
     * @throws IOException
     *             Thrown if Authentication failed.
     */
    public String login(
        final String serviceUrl, final String username, final String password)
        throws IOException {

        this.serviceAddress = unifyAddress(serviceUrl);

        URL loginUrl = new URL(this.serviceAddress + "aa/login");
        URL authURL =
            new URL(this.serviceAddress + "aa/j_spring_security_check");

        HttpURLConnection.setFollowRedirects(false);

        // 1) Make a login request in order to get the session cookie.
        HttpURLConnection restrictedConn =
            (HttpURLConnection) loginUrl.openConnection();
        restrictedConn.connect();

        // 2) Make a POST request sending the login credentials and the
        // previous session cookie in order to get the next session cookie.
        HttpURLConnection authConn =
            (HttpURLConnection) authURL.openConnection();
        authConn.setRequestMethod("POST");
        authConn.setDoOutput(true);

        authConn.setRequestProperty("Cookie", restrictedConn
            .getHeaderField("Set-Cookie"));
        String params = "j_username=" + username + "&j_password=" + password;

        OutputStreamWriter w =
            new OutputStreamWriter(authConn.getOutputStream());
        w.write(params);
        w.close();

        authConn.connect();
        List<String> cookieList = authConn.getHeaderFields().get("Set-Cookie");

        // 3) Make a login request with the previous session cookie in order
        // to get the auth cookie.
        HttpURLConnection redirectConn =
            (HttpURLConnection) loginUrl.openConnection();
        if (cookieList != null) {
            Iterator<String> cookieIt = cookieList.iterator();
            while (cookieIt.hasNext()) {
                redirectConn.addRequestProperty("Cookie", cookieIt.next());
            }
        }
        redirectConn.connect();
        cookieList = redirectConn.getHeaderFields().get("Set-Cookie");

        this.handle = getEsciDocCookie(cookieList);
        if (handle == null) {
            throw new IOException("Authorization failed.");
        }
        return handle;
    }

    /**
     * Logout from framework.
     * 
     * @throws IOException
     *             Thrown if logout failed.
     */
    public void logout() throws IOException {

        // FIXME
        // URL logoutUrl = new URL(this.serviceAddress + "aa/logout");

        this.handle = null;
        throw new IOException("Method not implemented.");
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

    /**
     * Unify URL (with trailing slash).
     * 
     * @param address
     *            The address (URL)
     * @return address with slash at the end.
     */
    private String unifyAddress(final String address) {

        String tmpServUrl = address;
        if (!tmpServUrl.endsWith("/")) {
            tmpServUrl += "/";
        }

        return tmpServUrl;
    }
}
