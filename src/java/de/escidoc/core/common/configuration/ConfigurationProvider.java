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

    private final Logger logger =
        Logger.getLogger(ConfigurationProvider.class.getName());

    public static final String PROP_SERVER_NAME = "server.name";

    public static final String PROP_SERVER_PORT = "server.port";

    public static final String SERVICE_PROTOCOL = "service_protocol";

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
        String currentUser = System.getProperties().getProperty("user.name");
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
    public static synchronized ConfigurationProvider getInstance()
        throws InternalClientException {
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

        Properties result = new Properties();
        Iterator<String> mandatoryFilesIter = mandatoryFiles.iterator();
        while (mandatoryFilesIter.hasNext()) {
            String next = mandatoryFilesIter.next();
            result.putAll(loadProperties(next));
        }
        Iterator<String> optionalFilesIter = optionalFiles.iterator();
        while (optionalFilesIter.hasNext()) {
            try {
                String next = optionalFilesIter.next();
                result.putAll(loadProperties(next));
            }
            catch (Exception e) {
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
    private Properties loadProperties(final String file)
        throws InternalClientException {
        Properties result = new Properties();

        boolean failed = true;
        int noOfPaths = paths.length;
        for (int i = 0; i < noOfPaths && failed; ++i) {
            try {
                InputStream fis =
                    getFileInputStreamFromResource(paths[i], file);
                result.load(fis);
                failed = false;
                if (logger.isDebugEnabled()) {
                    logger.debug("Load properties from file '" + file
                        + "' with path '" + paths[i] + "'.");
                }
            }
            catch (IOException e) {
                // ignore, try again
                if (logger.isDebugEnabled()) {
                    logger.debug("Failed to load properties from file '" + file
                        + "' with path '" + paths[i] + "'.");
                }
            }
        }
        if (failed) {
            throw new InternalClientException(
                "Error loading properties from file '" + file + "'!");
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
    private InputStream getFileInputStreamFromResource(
        final String path, final String filename) throws IOException {
        InputStream result = null;

        String search = concatenatePath(path, filename);
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
