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
 * Copyright 2006-2010 Fachinformationszentrum Karlsruhe Gesellschaft
 * fuer wissenschaftlich-technische Information mbH and Max-Planck-
 * Gesellschaft zur Foerderung der Wissenschaft e.V.  
 * All rights reserved.  Use is subject to license terms.
 */
package de.escidoc.core.test.client;

import static org.junit.Assert.fail;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.transform.TransformerException;

import org.apache.xpath.XPathAPI;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;

import de.escidoc.core.client.AdminHandlerClient;
import de.escidoc.core.client.Authentication;
import de.escidoc.core.client.TransportProtocol;
import de.escidoc.core.client.UserAccountHandlerClient;
import de.escidoc.core.client.exceptions.EscidocException;
import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.client.exceptions.TransportException;
import de.escidoc.core.common.configuration.ConfigurationProvider;
import de.escidoc.core.resources.aa.useraccount.UserAccount;
import de.escidoc.core.resources.adm.LoadExamplesResult.Entry;
import de.escidoc.core.resources.common.MessagesResult;

/**
 * Utility methods for Tests.
 * 
 * @author SWA
 * 
 */
public final class EscidocClientTestBase {

    private static final Pattern PATTERN_OBJID_ATTRIBUTE = Pattern
        .compile("objid=\"([^\"]*)\"");

    private static final Pattern PATTERN_XLINK_HREF_ATTRIBUTE = Pattern
        .compile("xlink:href=\"([^\"]*)\"");

    private static final Pattern PATTERN_LMD_ATTRIBUTE = Pattern
        .compile("last-modification-date=\"([^\"]*)\"");

    private static final TransportProtocol defaultTransportProtocol =
        TransportProtocol.REST;

    /*
     * Static variables to store results of initial LoadExamples.
     */

    private static String exampleItemId;

    private static String exampleOrganizationalUnitId;

    private static String exampleContextId;

    private static String exampleContentModelId;

    private static String exampleContainerId;

    private static boolean loadedExamples = false;

    /*
     * Static user accounts.
     */

    private static String exampleAdminId;

    private static String exampleUserId;

    /*
     * Static connection
     */

    private static URL defaultInfrastructureURL;

    /*
     * Username/Password logins
     */
    public static final String SYSTEM_ADMIN_USER = "sysadmin";

    public static final String SYSTEM_ADMIN_PASSWORD = "eSciDoc";

    /**
     * This is definitive an invalid identifier (resource not found).
     */
    public static final String INVALID_RESOURCE_ID = "escidoc:-1";

    /**
     * No instances allowed.
     */
    private EscidocClientTestBase() {
    }

    /**
     * Asserts that the exception is of expected type<br>
     * This method compares the provided expected class with the class of the
     * provided exception.
     * 
     * @param message
     *            The message printed in case of failed assertion.
     * @param expectedClass
     *            The expected type.
     * @param e
     *            The exception to be asserted.
     */
    public static void assertExceptionType(
        final String message, final Class<?> expectedClass, final Exception e) {

        if (!e.getClass().equals(expectedClass)) {
            StringBuffer msg = new StringBuffer(message);
            msg.append(" expected:<");
            msg.append(expectedClass.getName());
            msg.append("> but was:<");
            msg.append(e.getClass().getName());
            msg.append(">");
            appendStackTrace(msg, e);
            fail(msg.toString());
        }
    }

    /**
     * Retrieve the stack trace from the provided exception and returns it in a
     * <code>String</code>.
     * 
     * @param e
     *            The exception to retrieve the stack trace from.
     * @return Returns the stack trace in a <code>String</code>.
     */
    public static String getStackTrace(final Exception e) {

        StringWriter writer = new StringWriter();
        PrintWriter printwriter = new PrintWriter(writer);
        e.printStackTrace(printwriter);
        return writer.toString();
    }

    /**
     * Load content of file as String.
     * 
     * @param file
     *            File
     * @return Content of File as String
     * @throws IOException
     *             Thrown if access to file or reading failed.
     */
    public static String getXmlFileAsString(final InputStream in)
        throws IOException {

        // FIXME the fixed UTF-8 type let this method mark as garbage
        StringWriter writer = new StringWriter();
        BufferedReader reader =
            new BufferedReader(new InputStreamReader(in, "UTF-8"));
        String line = reader.readLine();
        while (line != null) {
            writer.append(line + "\n");
            line = reader.readLine();
        }
        reader.close();

        return writer.toString();
    }

    /**
     * Obtain objid and last-modification-date from eSciDoc XML.
     * 
     * @param xml
     *            XML representation of eSciDoc resource
     * @return String[objid, last-modification-date]
     */
    public static String[] obtainObjidAndLmd(final String xml) {

        String[] objidLmd = new String[2];

        // objid from href
        Matcher m = PATTERN_XLINK_HREF_ATTRIBUTE.matcher(xml);
        if (m.find()) {
            String href = m.group(1);
            int p = href.lastIndexOf("/");
            objidLmd[0] = href.substring(p + 1);
        }
        else {
            // fall back to objid (SOAP)
            m = PATTERN_OBJID_ATTRIBUTE.matcher(xml);
            if (m.find()) {
                objidLmd[0] = m.group(1);
            }
            else {
                objidLmd[0] = null;
            }
        }

        // lmd
        m = PATTERN_LMD_ATTRIBUTE.matcher(xml);
        if (m.find()) {
            objidLmd[1] = m.group(1);
        }
        else {
            objidLmd[1] = null;
        }

        return objidLmd;
    }

    /**
     * Adds the stack trace to the provided string buffer, if debug logging
     * level is enabled.
     * 
     * @param msg
     *            The StringBuffer to append the stack trace to.
     * @param e
     *            The exception for that the stack trace shall be appended.
     */
    private static void appendStackTrace(
        final StringBuffer msg, final Exception e) {

        msg.append("\n");
        msg.append(getStackTrace(e));
    }

    /**
     * 
     * @return
     * @throws InternalClientException
     */
    public static final TransportProtocol getDefaultTransportProtocol()
        throws InternalClientException {

        return defaultTransportProtocol;
    }

    /**
     * 
     * @param objidOrHref
     * @return
     * @throws TransformerException
     * @throws DOMException
     */
    public static final String obtainObjidByXPath(
        final String xPathToElement, final Document doc) throws DOMException,
        TransformerException {
        String objidOrHref =
            XPathAPI
                .selectSingleNode(doc,
                    xPathToElement + "/@objid|" + xPathToElement + "/@href")
                .getTextContent();
        return objidOrHref.substring(objidOrHref.lastIndexOf('/') + 1);
    }

    /**
     * @return
     * @throws TransportException
     * @throws EscidocException
     * @throws InternalClientException
     * @throws MalformedURLException
     */
    private static void loadStaticResources() throws TransportException,
        EscidocException, InternalClientException {

        Authentication auth =
            new Authentication(getDefaultInfrastructureURL(),
                SYSTEM_ADMIN_USER, SYSTEM_ADMIN_PASSWORD);
        AdminHandlerClient c = new AdminHandlerClient(auth.getServiceAddress());
        c.setHandle(auth.getHandle());
        MessagesResult<Entry> result = c.loadExamples();

        for (Entry entry : result) {
            switch (entry.getResourceType()) {
                case ITEM:
                    exampleItemId = entry.getObjid();
                    break;
                case ORGANIZATIONAL_UNIT:
                    exampleOrganizationalUnitId = entry.getObjid();
                    break;
                case CONTEXT:
                    exampleContextId = entry.getObjid();
                    break;
                case CONTENT_MODEL:
                    exampleContentModelId = entry.getObjid();
                    break;
                case CONTAINER:
                    exampleContainerId = entry.getObjid();
                    break;
            }
        }
        loadedExamples = true;
    }

    /**
     * @return
     * @throws TransportException
     * @throws EscidocException
     * @throws InternalClientException
     * @throws MalformedURLException
     */
    public static final synchronized String getStaticItemId()
        throws TransportException, EscidocException, InternalClientException {
        if (!loadedExamples)
            loadStaticResources();
        return exampleItemId;
    }

    /**
     * @return
     * @throws TransportException
     * @throws EscidocException
     * @throws InternalClientException
     * @throws MalformedURLException
     */
    public static final synchronized String getStaticOrganizationalUnitId()
        throws TransportException, EscidocException, InternalClientException {
        if (!loadedExamples)
            loadStaticResources();
        return exampleOrganizationalUnitId;
    }

    /**
     * @return
     * @throws TransportException
     * @throws EscidocException
     * @throws InternalClientException
     * @throws MalformedURLException
     */
    public static final synchronized String getStaticContextId()
        throws TransportException, EscidocException, InternalClientException {
        if (!loadedExamples)
            loadStaticResources();
        return exampleContextId;
    }

    /**
     * @return
     * @throws TransportException
     * @throws EscidocException
     * @throws InternalClientException
     * @throws MalformedURLException
     */
    public static final synchronized String getStaticContentModelId()
        throws TransportException, EscidocException, InternalClientException {
        if (!loadedExamples)
            loadStaticResources();
        return exampleContentModelId;
    }

    /**
     * @return
     * @throws TransportException
     * @throws EscidocException
     * @throws InternalClientException
     * @throws MalformedURLException
     */
    public static final synchronized String getStaticContainerId()
        throws TransportException, EscidocException, InternalClientException {
        if (!loadedExamples)
            loadStaticResources();
        return exampleContainerId;
    }

    /**
     * @return
     * @throws TransportException
     * @throws EscidocException
     * @throws InternalClientException
     * @throws MalformedURLException
     */
    public static final synchronized String getStaticAdminUserId()
        throws TransportException, EscidocException, InternalClientException {
        if (exampleAdminId == null) {
            Authentication auth =
                new Authentication(getDefaultInfrastructureURL(),
                    SYSTEM_ADMIN_USER, SYSTEM_ADMIN_PASSWORD);
            UserAccountHandlerClient uhc =
                new UserAccountHandlerClient(auth.getServiceAddress());
            uhc.setHandle(auth.getHandle());
            exampleAdminId = uhc.retrieveCurrentUser().getObjid();
        }
        return exampleAdminId;
    }

    /**
     * @return
     * @throws TransportException
     * @throws EscidocException
     * @throws InternalClientException
     * @throws MalformedURLException
     */
    public static final synchronized String getStaticUserId()
        throws TransportException, EscidocException, InternalClientException {
        if (exampleUserId == null) {
            Authentication auth =
                new Authentication(getDefaultInfrastructureURL(),
                    SYSTEM_ADMIN_USER, SYSTEM_ADMIN_PASSWORD);
            UserAccountHandlerClient uhc =
                new UserAccountHandlerClient(auth.getServiceAddress());
            uhc.setHandle(auth.getHandle());

            UserAccount acc = new UserAccount();

            acc.getProperties().setActive(true);
            acc.getProperties().setLoginName("testuser");
            acc.getProperties().setName("testuser");

            exampleUserId = uhc.create(acc).getObjid();
        }
        return exampleUserId;
    }

    /**
     * @return
     * @throws InternalClientException
     */
    public static final String getDefaultInfrastructureHost()
        throws InternalClientException {
        return ConfigurationProvider.getInstance().getProperty(
            ConfigurationProvider.PROP_SERVER_NAME);
    }

    /**
     * @return
     * @throws InternalClientException
     */
    public static final String getDefaultInfrastructurePort()
        throws InternalClientException {
        return ConfigurationProvider.getInstance().getProperty(
            ConfigurationProvider.PROP_SERVER_PORT);
    }

    /**
     * @return
     * @throws InternalClientException
     */
    public static final synchronized URL getDefaultInfrastructureURL()
        throws InternalClientException {

        if (defaultInfrastructureURL == null) {
            String port = getDefaultInfrastructurePort();
            try {
                defaultInfrastructureURL =
                    new URL("http://" + getDefaultInfrastructureHost()
                        + (port == null ? "" : ":" + port));
            }
            catch (MalformedURLException e) {
                throw new InternalClientException(e.getMessage(), e);
            }
        }
        return defaultInfrastructureURL;
    }
}