/**
 * 
 */
package de.escidoc.core.common.jibx.binding;

import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.Logger;
import org.jibx.runtime.IAliasable;
import org.jibx.runtime.IMarshaller;
import org.jibx.runtime.IMarshallingContext;
import org.jibx.runtime.IUnmarshaller;
import org.jibx.runtime.IUnmarshallingContext;
import org.jibx.runtime.JiBXException;
import org.jibx.runtime.impl.UnmarshallingContext;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import de.escidoc.core.client.TransportProtocol;
import de.escidoc.core.common.XmlUtility;
import de.escidoc.core.resources.sb.Record.RecordPacking;
import de.escidoc.core.resources.sb.RecordMetaData;
import de.escidoc.core.resources.sb.search.SearchDescriptor;
import de.escidoc.core.resources.sb.search.records.DefaultRecord;
import de.escidoc.core.resources.sb.search.records.resolver.RecordResolver;

/**
 * @author MVO
 * 
 */
public class SearchRetrieveResponseRecordMarshaller extends MarshallingBase
    implements IMarshaller, IUnmarshaller, IAliasable {

    private static final Logger LOG = Logger
        .getLogger(SearchRetrieveResponseRecordMarshaller.class);

    /**
     * Pattern matches:<br/>
     * <ul>
     * <li>group 0: entire start tag</li>
     * <li>group 1: prefix name</li>
     * <li>group 2: tag name</li>
     * <li>group 3: everything after tag name until closing tag entity</li>
     * </ul>
     */
    private static final Pattern tagNameWithPrefix = Pattern
        .compile("<(?:([^>^:^\\s]*):)?([^>^\\s]*?)(?:\\s[^<]*)?>");

    /**
     * 
     * @param uri
     * @param index
     * @param name
     */
    public SearchRetrieveResponseRecordMarshaller(final String uri,
        final int index, final String name) {
        super(uri, index, name);
    }

    /**
     * @see org.jibx.runtime.IMarshaller#isExtension(java.lang.String)
     */
    @Override
    public boolean isExtension(final String arg0) {
        return false;
    }

    /**
     * (Java class > XML) See Interface for functional description.
     * 
     * @param obj
     *            Object (SearchResultRecord) to Marshall
     * @param ictx
     *            Marshalling Context
     * @throws JiBXException
     *             Thrown if mapping failed by JiBX
     * @see org.jibx.runtime.IMarshaller#marshal(java.lang.Object,
     *      org.jibx.runtime.IMarshallingContext)
     */
    @Override
    public void marshal(final Object obj, final IMarshallingContext ictx)
        throws JiBXException {
        throw new JiBXException("Marshalling not supported.");
        // if (!(obj instanceof
        // de.escidoc.core.resources.sb.search.SearchResultRecord)) {
        // throw new JiBXException("Invalid object type for marshaller");
        // }
        // else if (!(ictx instanceof MarshallingContext)) {
        // throw new JiBXException("Invalid object type for marshaller");
        // }
        // else {
        // // TODO iterate all attributes and save them in a HashMap
        // MarshallingContext ctx = (MarshallingContext) ictx;
        // de.escidoc.core.resources.sb.Record record =
        // (de.escidoc.core.resources.sb.Record) obj;
        // // int[] urisIndex = new int[1];
        // // urisIndex[0] = getIndex();
        // // String[] prefixIndex = new String[1];
        // // prefixIndex[0] = "search-result";
        //
        // ctx.startTag(getIndex(), getName());
        //
        // ctx.closeStartContent();
        //
        // ctx.element(getIndex(), "recordSchema", record.getRecordSchema());
        // ctx.element(getIndex(), "recordPacking", record.getRecordPacking());
        //
        // ctx.startTag(getIndex(), "recordData");
        //
        // if (record.getRecordData() instanceof IMarshallable) {
        // ((IMarshallable) record.getRecordData()).marshal(ctx);
        // }
        // else {
        // throw new JiBXException("Mapped value is not marshallable");
        // }
        //
        // ctx.endTag(getIndex(), "recordData");
        //
        // ctx.element(getIndex(), "recordPosition",
        // record.getRecordPosition());
        // ctx.endTag(getIndex(), getName());
        // }

    }

    /**
     * @see org.jibx.runtime.IUnmarshaller#isPresent(org.jibx.runtime.
     *      IUnmarshallingContext)
     */
    @Override
    public boolean isPresent(final IUnmarshallingContext ictx)
        throws JiBXException {
        return ictx.isAt(getUri(), getName());
    }

    /**
     * (XML -> Java class) See Interface for functional description.
     * 
     * @param arg0
     * @param ictx
     * @return
     * @throws JiBXException
     * @see org.jibx.runtime.IUnmarshaller#unmarshal(java.lang.Object,
     *      org.jibx.runtime.IUnmarshallingContext)
     */
    @Override
    public Object unmarshal(final Object arg0, final IUnmarshallingContext ictx)
        throws JiBXException {
        UnmarshallingContext ctx = (UnmarshallingContext) ictx;

        ctx.parsePastStartTag(getUri(), getName());

        String recordSchema = null, recordPosition = null, packing = null;
        String dataText = null;
        Element dataDOM = null;
        Object result = null;

        try {
            while (true) {
                if (ctx.isAt(getUri(), "recordSchema")) {
                    recordSchema =
                        ctx.parseElementText(getUri(), "recordSchema");
                }
                else if (ctx.isAt(getUri(), "recordPacking")) {
                    packing = ctx.parseElementText(getUri(), "recordPacking");
                    packing = packing.toLowerCase();
                }
                else if (ctx.isAt(getUri(), "recordData")) {

                    ctx.parsePastStartTag(getUri(), "recordData");

                    if (RecordPacking.xml.name().equals(packing)) {
                        /**
                         * Performance increase: Map content as string and not
                         * as DOM, because we have to pass a XML-string to the
                         * Marshaller for the recordData. Therefore we do not
                         * need to convert the DOM to string in this case.
                         * 
                         * Using DOMMapper:
                         * 
                         * RecordData > XML2DOM > DOM2String > Unmarshal
                         * 
                         * Using content as String:
                         * 
                         * RecordData > XML2String > Unmarshal
                         */
                        dataText = getContentOfElementAsXml(ctx, "recordData");

                    }
                    else if (RecordPacking.string.name().equals(packing)) {
                        dataText = ctx.parseContentText();
                    }

                    ctx.parsePastEndTag(getUri(), "recordData");
                }
                else if (ctx.isAt(getUri(), "recordPosition")) {
                    recordPosition =
                        ctx.parseElementText(getUri(), "recordPosition");
                }
                else {
                    break;
                }
            }

            RecordMetaData data =
                new RecordMetaData(recordSchema, packing, new Integer(
                    recordPosition).intValue(), dataDOM, dataText,
                    TransportProtocol
                        .valueOf(ctx.getFactory().getBindingName()));

            result = getRecord(data);

        }
        catch (Exception e) {
            throw new JiBXException(e.getMessage(), e);
        }
        ctx.parsePastEndTag(getUri(), getName());

        return result;
    }

    /**
     * This method is calling the registered RecordResolvers in order to return
     * an instance of the mapped object of the content of the recordData tag.
     * The RecordResolvers are handled as a LIFO as described in
     * {@link SearchDescriptor}. In case of any Exception no Exception will be
     * thrown and a {@link DefaultRecord} will be returned. If no resolver is
     * able to map the content to an object, a {@link DefaultRecord} will be
     * returned as well.
     * 
     * @param data
     * @return
     */
    private Object getRecord(final RecordMetaData data) {

        String tagname = null;
        String namespace = null;
        URI ns = null;

        if (data.getDataText() != null) {
            Matcher m = tagNameWithPrefix.matcher(data.getDataText());
            if (m.find()) {
                String startingXml = m.group(0).replaceAll(">$", "/>");
                DocumentBuilderFactory factory =
                    DocumentBuilderFactory.newInstance();

                try {
                    DocumentBuilder builder = factory.newDocumentBuilder();
                    Document doc =
                        builder.parse(new InputSource(new StringReader(
                            XmlUtility.XML_HEADER + startingXml)));

                    Element element = doc.getDocumentElement();
                    tagname = element.getLocalName();
                    namespace = element.getNamespaceURI();
                }
                catch (SAXException e) {
                    if (LOG.isDebugEnabled()) {
                        LOG.debug(
                            "Unable to parse start tag of recordData content.",
                            e);
                    }
                }
                catch (IOException e) {
                    if (LOG.isDebugEnabled()) {
                        LOG.debug(
                            "Unable to parse start tag of recordData content.",
                            e);
                    }
                }
                catch (ParserConfigurationException e) {
                    if (LOG.isDebugEnabled()) {
                        LOG.debug(
                            "Unable to parse start tag of recordData content.",
                            e);
                    }
                }
            }
        }
        else if (data.getDataDOM() != null) {
            /**
             * This case has become redundant since we are always mapping the
             * content of recordData to String.
             */
            tagname = data.getDataDOM().getLocalName();
            namespace = data.getDataDOM().getNamespaceURI();
        }

        if (tagname != null) {

            if (namespace != null) {
                try {
                    ns = new URI(namespace);
                }
                catch (URISyntaxException e) {
                    if (LOG.isDebugEnabled()) {
                        LOG.debug("Invalid namespace.", e);
                    }
                }
            }

            for (RecordResolver<?, ?> recordResolver : SearchDescriptor
                .getResolvers()) {

                Object result = recordResolver.resolve(tagname, ns, data);
                if (result != null)
                    return result;
            }
        }

        /**
         * If we are unable to resolve the type of the content, return a default
         * record. No Exception is thrown.
         */
        return new DefaultRecord(data);
    }
}