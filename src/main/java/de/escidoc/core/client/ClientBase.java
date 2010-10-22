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

import gov.loc.www.zing.srw.ExplainRequestType;
import gov.loc.www.zing.srw.SearchRetrieveRequestType;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.Remote;
import java.util.HashMap;

import org.apache.axis.EngineConfiguration;
import org.apache.axis.configuration.FileProvider;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;

import de.escidoc.core.client.exceptions.EscidocClientException;
import de.escidoc.core.client.exceptions.EscidocException;
import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.client.exceptions.TransportException;
import de.escidoc.core.common.configuration.ConfigurationProvider;

/**
 * Client Handler Base.
 * 
 * @author SWA
 * 
 */
public abstract class ClientBase {

    private static final String ENGINE_CONFIG_FILE =
        "clientlib_wss4j_config.wsdd";

    private final Logger logger = Logger.getLogger(ClientBase.class.getName());

    private final EngineConfiguration engineConfig = new FileProvider(
        ENGINE_CONFIG_FILE);

    private String serviceAddress;

    @Deprecated
    private Authentication auth = null;

    private String handle;

    /**
     * Create an instance of client base using the default constructor.
     * 
     * @throws InternalClientException
     *             Thrown in case of client internal errors.
     */
    public ClientBase() throws InternalClientException {
        setServiceAddress(null);
    }

    /**
     * Create ClientBase.
     * 
     * @param serviceAddress
     *            The service endpoint address.
     * @throws InternalClientException
     *             Thrown in case of client internal errors.
     */
    public ClientBase(final String serviceAddress)
        throws InternalClientException {
        setServiceAddress(serviceAddress);
    }

    /**
     * Get the address of the service endpoint.
     * 
     * @return address of service endpoint.
     */
    public String getServiceAddress() {
        return this.serviceAddress;
    }

    /**
     * Set the address of the service endpoint.
     * 
     * @param address
     *            Address of service endpoint.
     * @throws InternalClientException
     *             Thrown if address is not a valid URL.
     */
    private void setServiceAddress(final String address)
        throws InternalClientException {
        if (address == null) {
            this.serviceAddress =
                "http://"
                    + getConfiguration().getProperty(
                        ConfigurationProvider.PROP_SERVER_NAME)
                    + ":"
                    + getConfiguration().getProperty(
                        ConfigurationProvider.PROP_SERVER_PORT);
        }
        else {
            try {
                URL url = new URL(address);
                this.serviceAddress = url.toString();
            }
            catch (MalformedURLException e) {
                throw new InternalClientException(e);
            }
        }
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
            if (tmp.indexOf(":") != -1) {
                tmp = tmp.substring(tmp.indexOf(":") + tSize);
                result = "http://" + serviceUrl + tmp;
            }
        }
        if (logger.isDebugEnabled()) {
            logger.debug("Service address '" + result + "'");
        }
        return result;
    }

    /**
     * Get configuration.
     * 
     * @return ConfigurationProvider
     * @throws InternalClientException
     *             Thrown if loading of configuration failed.
     */
    protected ConfigurationProvider getConfiguration()
        throws InternalClientException {

        return ConfigurationProvider.getInstance();
    }

    /**
     * Get client.
     * 
     * @return Remote
     * @throws InternalClientException
     *             Thrown in case of client internal errors.
     */
    public abstract Remote getClient() throws InternalClientException;

    /**
     * Get the last modification date of the resource.
     * 
     * @param id
     *            object id
     * @return the last modification date of the resource since the latest
     *         request.
     * @throws EscidocException
     *             Thrown if an exception from framework is received.
     * @throws InternalClientException
     *             Thrown in case of client internal errors.
     * @throws TransportException
     *             Thrown if in case of failure on transport level.
     */
    public abstract DateTime getLastModificationDate(final String id)
        throws EscidocException, InternalClientException, TransportException;

    /**
     * Get Authentication Handle.
     * 
     * @return the handle to set
     */
    public String getHandle() {

        return this.handle;
    }

    /**
     * Set Authentication Handle.
     * 
     * @param handle
     *            the handle to set
     */
    public void setHandle(final String handle) {

        this.handle = handle;
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
     * @throws EscidocException
     *             Thrown if an exception from framework is received.
     * @throws InternalClientException
     *             Thrown in case of client internal errors.
     * @throws TransportException
     *             Thrown if in case of failure on transport level.
     */
    @Deprecated
    public String login(
        final String serviceUrl, final String username, final String password)
        throws EscidocException, InternalClientException, TransportException {

        setServiceAddress(serviceUrl);

        if (this.auth == null) {
            try {
                auth = new Authentication(serviceUrl, username, password);
            }
            catch (EscidocClientException e) {
                throw new InternalClientException("Login failed.", e);
            }
        }

        String handle = this.auth.getHandle();
        setHandle(handle);

        return handle;
    }

    /**
     * Converts a SRW SearchRetrieveRequest to the data structure for filter
     * requests for eSciDoc (version 1.2).
     * 
     * @param filter
     *            SRW SearchRetrieveRequest
     * @return data structure for filter requests for eSciDoc (version 1.2)
     */
    protected HashMap<String, String[]> getEscidoc12Filter(
        final SearchRetrieveRequestType filter) {

        HashMap<String, String[]> filter12 = new HashMap<String, String[]>();

        if (filter.getMaximumRecords() != null) {
            filter12.put("maximumRecords",
                new String[] { String.valueOf(filter.getMaximumRecords()) });
        }
        if (filter.getStartRecord() != null) {
            filter12.put("startRecord",
                new String[] { String.valueOf(filter.getStartRecord()) });
        }
        if (filter.getQuery() != null) {
            filter12.put("query", new String[] { filter.getQuery() });
        }
        if (filter.getVersion() != null) {
            filter12.put("version", new String[] { filter.getVersion() });
        }
        filter12.put("operation", new String[] { "searchRetrieve" });

        return filter12;
    }

    /**
     * Converts a SRW ExplainRequest to the data structure for filter requests
     * for eSciDoc (version 1.2).
     * 
     * @param filter
     *            SRW ExplainRequest
     * @return data structure for filter requests for eSciDoc (version 1.2)
     */
    protected HashMap<String, String[]> getEscidoc12Filter(
        final ExplainRequestType filter) {

        HashMap<String, String[]> filter12 = new HashMap<String, String[]>();

        if (filter.getVersion() != null) {
            filter12.put("version", new String[] { filter.getVersion() });
        }
        filter12.put("operation", new String[] { "explain" });

        return filter12;
    }

}
