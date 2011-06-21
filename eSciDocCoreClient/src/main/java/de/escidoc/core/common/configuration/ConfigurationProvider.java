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
package de.escidoc.core.common.configuration;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;

import de.escidoc.core.client.TransportProtocol;
import de.escidoc.core.client.exceptions.InternalClientException;

/**
 * Configure and initialize the necessary resources for the client library. This
 * file tries to read and open the following files from the classpath.
 * <ul>
 * <li>client.default.properties</li>
 * <li>client.properties</li>
 * <li>{username}.properties</li>
 * </ul>
 * 
 * <p>
 * <b>client.default.properties</b> is mandatory. If this file is not present,
 * this class will throw an InternalClientException.
 * </p>
 * <p>
 * <b>client.properties</b> is optional
 * </p>
 * <p>
 * <b>{username}.properties</b> gets resolved to the user this class is being
 * executed under. If the username is "myname" then this property resolves to
 * myname.properties. This file is also optional.
 * </p>
 * 
 * 
 * @author MSC
 */
public final class ConfigurationProvider {

    private final Logger logger = Logger.getLogger(ConfigurationProvider.class.getName());

    /*
     * Namespace configuration
     */

    // IR
    public static final String NS_IR_ITEM = "ns.ir.item";

    public static final String NS_IR_CONTAINER = "ns.ir.container";

    public static final String NS_IR_CONTEXT = "ns.ir.context";

    public static final String NS_IR_CONTENT_RELATION = "ns.ir.content-relation";

    public static final String NS_IR_COMPONENTS = "ns.ir.components";

    // OUM
    public static final String NS_OUM_ORGANIZATIONAL_UNIT = "ns.oum.organizational-unit";

    // AA
    public static final String NS_AA_USER_ACCOUNT = "ns.aa.user-account";

    public static final String NS_AA_GRANT = "ns.aa.grant";

    public static final String NS_AA_ROLE = "ns.aa.role";

    public static final String NS_AA_USER_GROUP = "ns.aa.user-group";

    // CMM
    public static final String NS_CMM_CONTENT_MODEL = "ns.cmm.content-model";

    // STATISTIC
    public static final String NS_STATISTIC_SCOPE = "ns.statistic.scope";

    public static final String NS_STATISTIC_REPORT_DEF = "ns.statistic.report-definition";

    public static final String NS_STATISTIC_AGGREGATION_DEF = "ns.statistic.aggregation-definition";

    public static final String NS_STATISTIC_STATISTIC_DATA = "ns.statistic.statistic-data";

    // OAI
    public static final String NS_OAI_SET_DEFINITION = "ns.oai.set-definition";

    /*
     * Parameters for HttpClient
     */

    public static final String HTTP_PROTOCOL_VERSION = "http.protocol.version";

    public static final String HTTP_PROTOCOL_ELEMENT_CHARSET = "http.protocol.element-charset";

    public static final String HTTP_PROTOCOL_CONTENT_CHARSET = "http.protocol.content-charset";

    public static final String HTTP_USERAGENT = "http.useragent";

    public static final String HTTP_PROTOCOL_STRICT_TRANSFER_ENCODING = "http.protocol.strict-transfer-encoding";

    public static final String HTTP_PROTOCOL_EXPECT_CONTINUE = "http.protocol.expect-continue";

    public static final String HTTP_PROTOCOL_WAIT_FOR_CONTINUE = "http.protocol.wait-for-continue";

    public static final String HTTP_SOCKET_TIMEOUT = "http.socket.timeout";

    public static final String HTTP_TCP_NODELAY = "http.tcp.nodelay";

    public static final String HTTP_SOCKET_BUFFER_SIZE = "http.socket.buffer-size";

    public static final String HTTP_SOCKET_LINGER = "http.socket.linger";

    public static final String HTTP_CONNECTION_TIMEOUT = "http.socket.timeout";

    public static final String HTTP_CONNECTION_STALECHECK = "http.connection.stalecheck";

    public static final String HTTP_CONNECTION_MAX_LINE_LENGTH = "http.connection.max-line-length";

    public static final String HTTP_CONNECTION_MAX_HEADER_COUNT = "http.connection.max-header-count";

    public static final String HTTP_CONNECTION_MAX_STATUS_LINE_GARBAGE = "http.connection.max-status-line-garbage";

    public static final String HTTP_ROUTE_DEFAULT_PROXY = "http.route.default-proxy";

    public static final String HTTP_ROUTE_LOCAL_ADDRESS = "http.route.local-address";

    public static final String HTTP_ROUTE_FORCED_ROUTE = "http.route.forced-route";

    public static final String HTTP_CONN_MANAGER_TIMEOUT = "http.conn-manager.timeout";

    public static final String HTTP_CONN_MANAGER_MAX_PER_ROUTE = "http.conn-manager.max-per-route";

    public static final String HTTP_CONN_MANAGER_MAX_TOTAL = "http.connmanager.max-total";

    public static final String HTTP_PROTOCOL_COOKIE_DATEPATTERNS = "http.cookie.date-patterns";

    public static final String HTTP_PROTOCOL_SINGLE_COOKIE_HEADER = "http.protocol.single-cookie-header";

    public static final String HTTP_PROTOCOL_COOKIE_POLICY = "http.protocol.cookie-policy";

    public static final String HTTP_PROTOCOL_HANDLE_AUTHENTICATION = "http.protocol.handle-authentication";

    public static final String HTTP_AUTH_CREDENTIAL_CHARSET = "http.auth.credential-charset";

    public static final String HTTP_PROTOCOL_HANDLE_REDIRECTS = "http.protocol.handle-redirects";

    public static final String HTTP_PROTOCOL_REJECT_RELATIVE_REDIRECT = "http.protocol.reject.relative.redirect";

    public static final String HTTP_PROTOCOL_MAX_REDIRECTS = "http.protocol.max-redirects";

    public static final String HTTP_PROTOCOL_ALLOW_CIRCULAR_REDIRECTS = "http.protocol.allow-circular-redirects";

    public static final String HTTP_CONNECTION_MANAGER_FACTORY_CLASS_NAME =
        "http.connection-manager.factory-class-name";

    public static final String HTTP_VIRTUAL_HOST = "http.virtual-host";

    public static final String HTTP_DEFAULT_HEADERS = "http.default-headers";

    public static final String HTTP_DEFAULT_HOST = "http.default-host";

    /**
     * @deprecated Configuration of the transport protocol is no longer
     *             supported. REST transport protocol will be used for the main
     *             handler clients.
     */
    @Deprecated
    public static final String PROP_SERVICE_PROTOCOL = "service_protocol";

    public static final TransportProtocol DEFAULT_TRANSPORT_PROTOCOL = TransportProtocol.REST;

    public static final String PROP_SEARCH_DATABASE = "search.database";

    private static ConfigurationProvider instance = null;

    private Properties properties = null;

    private final List<String> mandatoryFiles;

    private final List<String> optionalFiles;

    private final String[] paths = { "/" };

    /**
     * Create a new instance of this class. This constructor is private in order
     * to allow a safe initialization as a singleton.
     * 
     * @throws InternalClientException
     */
    private ConfigurationProvider() throws InternalClientException {

        this.mandatoryFiles = new LinkedList<String>();
        this.optionalFiles = new LinkedList<String>();
        addFile("client.default.properties", true);

        // to load any of the optional file the directory they reside must be
        // part of the classpath
        addFile("client.properties", false);
        final String currentUser = System.getProperties().getProperty("user.name");
        if (currentUser != null) {
            addFile(currentUser + ".properties", false);
        }
        init();
    }

    /**
     * Create if necessary, and return an instance of this class.
     * 
     * @return the ConfigurationProvider instance
     * 
     * @throws InternalClientException
     */
    public static synchronized ConfigurationProvider getInstance() throws InternalClientException {
        if (instance == null) {
            instance = new ConfigurationProvider();
        }
        return instance;
    }

    /**
     * Returns the property with the given name or null if property was not
     * found.
     * 
     * @param name
     *            The name of the Property.
     * @return Value of the given Property as String.
     * @common
     */
    public String getProperty(final String name) {

        return properties.getProperty(name);
    }

    /**
     * Returns the property with the given name or the second parameter as
     * default value if property was not found.
     * 
     * @param name
     *            The name of the Property.
     * @param defaultValue
     *            The default vaule if property isn't given.
     * @return Value of the given Property as String.
     * @common
     */
    public String getProperty(final String name, final String defaultValue) {

        return properties.getProperty(name, defaultValue);
    }

    /**
     * Initialize the instance upon creation
     * 
     * @throws InternalClientException
     */
    private void init() throws InternalClientException {

        final Properties result = new Properties();
        final Iterator<String> mandatoryFilesIter = mandatoryFiles.iterator();
        while (mandatoryFilesIter.hasNext()) {
            final String next = mandatoryFilesIter.next();
            result.putAll(loadProperties(next));
        }
        final Iterator<String> optionalFilesIter = optionalFiles.iterator();
        while (optionalFilesIter.hasNext()) {
            try {
                final String next = optionalFilesIter.next();
                result.putAll(loadProperties(next));
            }
            catch (final Exception e) {
                // ignore, it is no error if an optional properties file is not
                // available
            }
        }
        this.properties = result;
    }

    /**
     * Loads the Properties from the possible files. First loads properties from
     * the file escidoc-core.properties.default. Afterwards tries to load
     * specific properties from the file escidoc.properties and merges them with
     * the default properties. If any key is included in default and specific
     * properties, the value of the specific property will overwrite the default
     * property.
     * 
     * @param file
     *            The nameof the file.
     * @return The properties
     * @throws InternalClientException
     *             If the loading of the default properties (file
     *             escidoc-core.default.properties) fails.
     * 
     * @common
     */
    private Properties loadProperties(final String file) throws InternalClientException {
        final Properties result = new Properties();

        boolean failed = true;
        final int noOfPaths = paths.length;
        for (int i = 0; i < noOfPaths && failed; ++i) {
            try {
                final InputStream fis = getFileInputStreamFromResource(paths[i], file);
                result.load(fis);
                failed = false;
                if (logger.isDebugEnabled()) {
                    logger.debug("Load properties from file '" + file + "' with path '" + paths[i] + "'.");
                }
            }
            catch (final IOException e) {
                // ignore, try again
                if (logger.isDebugEnabled()) {
                    logger.debug("Failed to load properties from file '" + file + "' with path '" + paths[i] + "'.");
                }
            }
        }
        if (failed) {
            throw new InternalClientException("Error loading properties from file '" + file + "'!");
        }

        return result;
    }

    /**
     * Load resource from classpath using the current class loader.
     * 
     * @param path
     *            the String defining a path to the resource
     * @param filename
     *            the String defining the filename of the resource
     * @return the InputStream of the respective resource
     * @throws IOException
     */
    private InputStream getFileInputStreamFromResource(final String path, final String filename) throws IOException {
        InputStream result = null;

        final String search = concatenatePath(path, filename);
        // search = concatenatePath(search.replace(".", "/"), filename);
        result = getClass().getResourceAsStream(search);
        if (result == null) {
            throw new IOException("Resource not found [" + search + "]");
        }
        return result;
    }

    /**
     * Concatenates the two given path segments and returns a valid path, i.e.
     * the method takes care that there is only one path separator between the
     * path segments.
     * 
     * @param path
     *            The path.
     * @param appendix
     *            The path to append.
     * @return The concatenated path.
     * @st
     */
    private String concatenatePath(final String path, final String appendix) {
        String result = path;
        String append = appendix;
        result = result.replace("\\", "/");
        append = append.replace("\\", "/");
        if (!result.endsWith("/")) {
            if (!append.startsWith("/")) {
                result += "/" + append;
            }
            else {
                result += append;
            }
        }
        else {
            if (!append.startsWith("/")) {
                result += append;
            }
            else {
                result += append.substring(1);
            }
        }
        return result;
    }

    /**
     * Add configuration files.
     * 
     * @param name
     *            The file name.
     * @param mandatory
     *            Set true if mandatory, false for optional.
     */
    public void addFile(final String name, final boolean mandatory) {

        if (mandatory) {
            mandatoryFiles.add(name);
        }
        else {
            optionalFiles.add(name);
        }
    }

}