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
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.transform.TransformerException;

import org.apache.xpath.XPathAPI;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;

import de.escidoc.core.client.TransportProtocol;
import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.common.configuration.ConfigurationProvider;

/**
 * Utility methods for Tests.
 * 
 * @author SWA
 * 
 */
public class EscidocClientTestBase {

    public static final String DEFAULT_INFRASTRUCTURE_HOST = "localhost";

    public static final String DEFAULT_INFRASTRUCTURE_PORT = "8080";

    public static final String DEFAULT_INFRASTRUCTURE_PATH = "";

    public static final String DEFAULT_SERVICE_URL = "http://"
        + DEFAULT_INFRASTRUCTURE_HOST + ":" + DEFAULT_INFRASTRUCTURE_PORT
        + DEFAULT_INFRASTRUCTURE_PATH;

    private static final Pattern PATTERN_OBJID_ATTRIBUTE = Pattern
        .compile("objid=\"([^\"]*)\"");

    private static final Pattern PATTERN_XLINK_HREF_ATTRIBUTE = Pattern
        .compile("xlink:href=\"([^\"]*)\"");

    private static final Pattern PATTERN_LMD_ATTRIBUTE = Pattern
        .compile("last-modification-date=\"([^\"]*)\"");

    private static TransportProtocol defaultTransportProtocol;

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
    public static String getXmlFileAsString(final File file) throws IOException {

        // FIXME the fixed UTF-8 type let this method mark as garbage
        StringWriter writer = new StringWriter();
        BufferedReader reader =
            new BufferedReader(new InputStreamReader(new FileInputStream(file),
                "UTF-8"));
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
        if (defaultTransportProtocol == null) {
            defaultTransportProtocol =
                TransportProtocol.valueOf(ConfigurationProvider
                    .getInstance().getProperty(
                        ConfigurationProvider.PROP_SERVICE_PROTOCOL));
        }
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
        String xPathToElement, Document doc) throws DOMException,
        TransformerException {
        String objidOrHref =
            XPathAPI
                .selectSingleNode(doc,
                    xPathToElement + "/@objid|" + xPathToElement + "/@href")
                .getTextContent();
        return objidOrHref.substring(objidOrHref.lastIndexOf('/') + 1);
    }
}
