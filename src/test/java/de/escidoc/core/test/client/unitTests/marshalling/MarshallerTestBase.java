/**
 * 
 */
package de.escidoc.core.test.client.unitTests.marshalling;

import static de.escidoc.core.common.Precondition.checkNotEmpty;
import static de.escidoc.core.common.Precondition.checkNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collection;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.apache.xpath.XPathAPI;
import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import de.escidoc.core.client.TransportProtocol;
import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.common.DateTimeUtility;
import de.escidoc.core.common.XmlUtility;
import de.escidoc.core.common.jibx.Marshaller;
import de.escidoc.core.resources.NamedSubResource;
import de.escidoc.core.resources.Resource;
import de.escidoc.core.resources.XLinkList;
import de.escidoc.core.resources.XLinkResource;
import de.escidoc.core.resources.common.MetadataRecords;
import de.escidoc.core.resources.interfaces.XmlCompatibleEnum;
import de.escidoc.core.test.client.util.Template;

/**
 * @author Marko Voß
 * 
 */
@RunWith(Parameterized.class)
public abstract class MarshallerTestBase<T> {

    private final String xml;

    private final Document originDoc;

    private Document marshalledDoc;

    private boolean checkMarshalledDoc = false;

    private final Class<T> clazz;

    private static final DateTimeFormatter DATE_TIME_FORMATTER =
        new DateTimeFormatter();

    private static final EnumFormatter ENUM_FORMATTER = new EnumFormatter();

    private static final Collection<Object[]> PARAMETERS = Arrays
        .asList(new Object[][] { { TransportProtocol.REST },
            { TransportProtocol.SOAP } });

    private final TransportProtocol transport;

    /**
     * @param clazz
     * @param resourceFile
     * @throws IOException
     * @throws SAXException
     * @throws ParserConfigurationException
     */
    public MarshallerTestBase(final Class<T> clazz, final String basePath,
        final String xsdVersion, final String resourceFile,
        final TransportProtocol transport) throws IOException,
        ParserConfigurationException, SAXException {
        this.xml =
            loadResourceFile(transport, basePath, xsdVersion, resourceFile);
        this.originDoc = XmlUtility.getDocument(this.xml, true);
        this.clazz = clazz;
        this.transport = transport;
    }

    /**
     * @param resourceFile
     * @return
     * @throws IOException
     */
    private final String loadResourceFile(
        final TransportProtocol transport, final String basePath,
        final String xsdVersion, final String resourceFile) throws IOException {

        checkNotEmpty(resourceFile);

        InputStream in =
            Template.loadMockup(transport, basePath, xsdVersion, resourceFile);

        checkNotNull(in);

        StringBuilder sb = new StringBuilder();
        char[] buffer = new char[1024];
        int read = -1;

        try {
            BufferedReader reader =
                new BufferedReader(new InputStreamReader(in, "UTF-8"));

            while ((read = reader.read(buffer)) != -1) {
                sb.append(buffer, 0, read);
            }
        }
        finally {
            in.close();
        }

        return sb.toString();
    }

    /**
     * @return
     */
    @Parameters
    public static Collection<Object[]> data() {
        return PARAMETERS;
    }

    /**
     * @throws Exception
     */
    @Test
    public void testLifecycle() throws Exception {

        Marshaller<T> m = Marshaller.getMarshaller(clazz, transport.name());
        T obj = m.unmarshalDocument(xml);
        checkMarshalledDoc = false;
        validate(obj);

        marshalledDoc = XmlUtility.getDocument(m.marshalDocument(obj), true);
        checkMarshalledDoc = true;
        validate(obj);

        testSubResources(obj);

        testResourceWithoutSubResources(obj);
    }

    /**
     * @return
     */
    private final Document getDocument() {
        return checkMarshalledDoc ? marshalledDoc : originDoc;
    }

    /**
     * Validation method to check, that the unmarshalled object is valid for the
     * origin XML.
     * 
     * @param obj
     * @param xml
     * @throws Exception
     */
    protected abstract void validate(T obj) throws Exception;

    // /**
    // * Validation method to check, that both objects are equals.
    // *
    // * @param obj
    // * @param other
    // */
    // protected abstract void validate(T obj, T other) throws Exception;

    /**
     * @param obj
     */
    protected abstract void testSubResources(T obj) throws Exception;

    /**
     * @param obj
     */
    protected abstract void testResourceWithoutSubResources(T obj)
        throws Exception;

    /**
     * @param <U>
     * @param subClazz
     * @param subResource
     * @return
     * @throws InternalClientException
     */
    protected <U> U getTestSubResource(
        final Class<U> subClazz, final U subResource)
        throws InternalClientException {

        Marshaller<U> m = Marshaller.getMarshaller(subClazz);
        return m.unmarshalDocument(m.marshalDocument(subResource));
    }

    /**
     * @param xPath
     * @param valueToCheck
     * @param strict
     * @throws DOMException
     * @throws TransformerException
     */
    protected final void assertXPath(
        final String xPath, final Object valueToCheck) throws DOMException,
        TransformerException {

        assertXPath(xPath, valueToCheck, null);
    }

    /**
     * @param xPath
     * @param valueToCheck
     * @param strict
     * @throws DOMException
     * @throws TransformerException
     */
    protected final void assertXPath(
        final String xPath, final Object valueToCheck, final boolean strict)
        throws DOMException, TransformerException {

        assertXPath(xPath, valueToCheck, null, strict);
    }

    /**
     * @param xPath
     * @param valueToCheck
     * @param strict
     * @throws DOMException
     * @throws TransformerException
     */
    protected final <U> void assertXPath(
        final String xPath, final U valueToCheck, final Formatter<U> formatter)
        throws DOMException, TransformerException {

        assertXPath(xPath, valueToCheck, formatter, true);
    }

    /**
     * @param <U>
     * @param xPath
     * @param valueToCheck
     * @param formatter
     * @param strict
     * @throws DOMException
     * @throws TransformerException
     */
    protected final <U> void assertXPath(
        final String xPath, final U valueToCheck, final Formatter<U> formatter,
        final boolean strict) throws DOMException, TransformerException {

        checkNotNull(xPath);

        Node node =
            XPathAPI.selectSingleNode(getDocument(), xPath, getDocument()
                .getDocumentElement());

        if (strict) {
            assertNotNull("No node found for XPath: " + xPath, node);
        }
        String content = node == null ? null : node.getTextContent();

        if (content != null) {
            assertNotNull("Object value is null for XPath: " + xPath,
                valueToCheck);

            if (formatter != null) {
                content = formatter.format(content, valueToCheck);
            }

            assertEquals("Object value does not equal content for XPath: "
                + xPath, content, valueToCheck.toString());
        }
    }

    /**
     * @param xPathContext
     * @param res
     * @throws DOMException
     * @throws TransformerException
     */
    protected final void assertResource(
        final String xPathContext, final Resource res) throws DOMException,
        TransformerException {

        assertResource(xPathContext, res, true);
    }

    /**
     * @param xPathContext
     * @param res
     * @param checkXLink
     * @throws DOMException
     * @throws TransformerException
     */
    protected final void assertResource(
        final String xPathContext, final Resource res, final boolean checkXLink)
        throws DOMException, TransformerException {

        checkNotNull(xPathContext);
        checkNotNull(res);

        if (transport == TransportProtocol.SOAP) {
            String objid = res.getObjid();

            assertXPath(xPathContext + "/@objid", objid, true);
            // in some cases it is impossible to generate the XLink
            if (checkXLink) {
                assertNotNull("xlink:href is null.", res.getXLinkHref());
            }
        }
        else if (transport == TransportProtocol.REST) {
            assertXLinkResource(xPathContext, res);
            assertNotNull(res.getObjid());
        }
    }

    /**
     * @param xPathContext
     * @param res
     * @throws DOMException
     * @throws TransformerException
     */
    protected final void assertXLinkResource(
        final String xPathContext, final XLinkResource res)
        throws DOMException, TransformerException {

        assertXLinkResource(xPathContext, res, false);
    }

    /**
     * @param xPathContext
     * @param res
     * @param checkAlways
     * @throws DOMException
     * @throws TransformerException
     */
    protected final void assertXLinkResource(
        final String xPathContext, final XLinkResource res,
        final boolean checkAlways) throws DOMException, TransformerException {

        checkNotNull(xPathContext);
        checkNotNull(res);

        if (checkAlways || transport == TransportProtocol.REST) {
            assertXPath(xPathContext + "/@xlink:href", res.getXLinkHref(), true);
            assertXPath(xPathContext + "/@xlink:title", res.getXLinkTitle(),
                false);
            assertXPath(xPathContext + "/@xlink:type", res.getXLinkType(),
                false);
        }
    }

    /**
     * @param xPathContext
     * @param res
     * @throws DOMException
     * @throws TransformerException
     */
    protected final void assertXLinkList(
        final String xPathContext, final XLinkList<?> res) throws DOMException,
        TransformerException {

        checkNotNull(xPathContext);
        checkNotNull(res);

        if (transport == TransportProtocol.REST) {
            assertXPath(xPathContext + "/@xlink:href", res.getXLinkHref(), true);
            assertXPath(xPathContext + "/@xlink:title", res.getXLinkTitle(),
                false);
            assertXPath(xPathContext + "/@xlink:type", res.getXLinkType(),
                false);
        }
        else if (transport == TransportProtocol.SOAP) {
            assertNotNull(res.getXLinkHref());
        }

        assertDateTime(xPathContext + "/@last-modification-date",
            res.getLastModificationDate(), false);
    }

    /**
     * @param xPathContext
     * @param res
     * @throws DOMException
     * @throws TransformerException
     */
    protected final void assertNamedSubResource(
        final String xPathContext, final NamedSubResource res)
        throws DOMException, TransformerException {

        checkNotNull(xPathContext);
        checkNotNull(res);

        assertXPath(xPathContext + "/@name", res.getName());
        assertXLinkResource(xPathContext, res);
    }

    /**
     * @param xPathContext
     * @param e
     * @throws TransformerException
     * @throws DOMException
     */
    protected final void assertEnum(
        final String xPathContext, final XmlCompatibleEnum e)
        throws DOMException, TransformerException {

        assertXPath(xPathContext, e, ENUM_FORMATTER);
    }

    /**
     * @param xPathContext
     * @param date
     * @throws DOMException
     * @throws TransformerException
     */
    protected final void assertDateTime(
        final String xPathContext, final DateTime date) throws DOMException,
        TransformerException {

        assertDateTime(xPathContext, date, true);
    }

    /**
     * @param xPathContext
     * @param date
     * @param strict
     * @throws DOMException
     * @throws TransformerException
     */
    protected final void assertDateTime(
        final String xPathContext, final DateTime date, final boolean strict)
        throws DOMException, TransformerException {

        assertXPath(xPathContext, date, DATE_TIME_FORMATTER, strict);
    }

    /**
     * @param xPathContext
     * @param record
     * @throws DOMException
     * @throws TransformerException
     */
    protected final void assertMdRecord(
        final String xPathMdRecords, final MetadataRecords records,
        final int index) throws DOMException, TransformerException {

        checkNotNull(xPathMdRecords);
        checkNotNull(records);

        String xPathMdRecord =
            xPathMdRecords.substring(xPathMdRecords.lastIndexOf('/'),
                xPathMdRecords.length() - 1);
        xPathMdRecord =
            xPathMdRecords + xPathMdRecord + "[" + (index + 1) + "]";

        assertNamedSubResource(xPathMdRecord, records.get(index));

        // optional checks
        assertXPath(xPathMdRecord + "/@md-type",
            records.get(index).getMdType(), false);
        assertXPath(xPathMdRecord + "/@schema", records.get(index).getSchema(),
            false);

        // TODO: validate DOM content
    }

    /**
     * @return the transport
     */
    public TransportProtocol getTransport() {
        return transport;
    }

    /**
     * @author Marko Voß
     * 
     */
    abstract static class Formatter<T> {

        /**
         * @param <T>
         * @param value
         * @param class1
         * @return
         */
        abstract String format(String value, T object);
    }

    /**
     * @author Marko Voß
     * 
     */
    static class DateTimeFormatter extends Formatter<DateTime> {

        @Override
        String format(final String value, final DateTime obj) {
            if (value == null)
                return null;
            return DateTimeUtility.normalize(new DateTime(value)).toString();
        }
    }

    /**
     * @author Marko Voß
     * 
     */
    static class EnumFormatter extends Formatter<XmlCompatibleEnum> {

        @Override
        String format(final String value, final XmlCompatibleEnum e) {

            if (value == null)
                return null;

            if (e.getClass().isEnum()) {
                for (XmlCompatibleEnum t : e.getClass().getEnumConstants()) {
                    if (t.getXmlValue().equals(value)) {
                        return t.toString();
                    }
                }
            }

            throw new IllegalStateException("Unable to format XML value '"
                + value + "' for enumeration '" + e.getClass().getName() + "'");
        }
    }
}