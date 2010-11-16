/**
 * 
 */
package de.escidoc.core.common.jibx.binding;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jibx.runtime.IAliasable;
import org.jibx.runtime.IMarshaller;
import org.jibx.runtime.IMarshallingContext;
import org.jibx.runtime.IUnmarshaller;
import org.jibx.runtime.IUnmarshallingContext;
import org.jibx.runtime.JiBXException;
import org.jibx.runtime.impl.UnmarshallingContext;
import org.w3c.dom.Element;

import de.escidoc.core.client.TransportProtocol;
import de.escidoc.core.resources.ResourceType;
import de.escidoc.core.resources.aa.role.Role;
import de.escidoc.core.resources.aa.useraccount.UserAccount;
import de.escidoc.core.resources.om.container.Container;
import de.escidoc.core.resources.om.contentRelation.ContentRelation;
import de.escidoc.core.resources.om.context.Context;
import de.escidoc.core.resources.om.item.Item;
import de.escidoc.core.resources.oum.OrganizationalUnit;
import de.escidoc.core.resources.sb.Record.RecordPacking;
import de.escidoc.core.resources.sb.search.records.ResourceRecord;
import de.escidoc.core.resources.sb.search.records.DefaultRecord;
import de.escidoc.core.resources.sb.search.records.SearchResultRecordRecord;

/**
 * @author MVO
 * 
 */
public class SearchRetrieveResponseRecordMarshaller extends MarshallingBase
    implements IMarshaller, IUnmarshaller, IAliasable {

    /**
     * Pattern to match the tag name and prefix into group 1.
     */
    private static final Pattern tagNameNS = Pattern
        .compile("^<([^>\\s]+)[^>]*?>");

    private static final String TAG_SRW_RECORD = "search-result-record";

    // private static final String NS_SRW_RECORD = "";

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

            result =
                getRecord(recordSchema, new Integer(recordPosition).intValue(),
                    packing, dataText, dataDOM, TransportProtocol.valueOf(ctx
                        .getFactory().getBindingName()));

        }
        catch (Exception e) {
            throw new JiBXException(e.getMessage(), e);
        }
        ctx.parsePastEndTag(getUri(), getName());

        return result;
    }

    /**
     * TODO: Maybe also use namespace to recognize the current tag.
     * 
     * @param recordSchema
     * @param recordPosition
     * @param packing
     * @param dataText
     * @param dataDOM
     * @return
     */
    private Object getRecord(
        final String recordSchema, final int recordPosition,
        final String packing, final String dataText, final Element dataDOM,
        final TransportProtocol transport) {

        String tagName = null;

        if (dataText != null) {
            Matcher m = tagNameNS.matcher(dataText);
            if (m.find()) {
                tagName = m.group(1);
            }
        }
        else if (dataDOM != null) {
            /**
             * This case has become redundant since we are always mapping the
             * content of recordData to String.
             */
            tagName = dataDOM.getNodeName();
        }

        // remove NS prefix if exists
        tagName = tagName.substring(tagName.indexOf(':') + 1);

        // non-resources
        if (TAG_SRW_RECORD.equals(tagName))
            return new SearchResultRecordRecord(recordSchema, packing,
                recordPosition, dataDOM, dataText, transport);

        // resources
        else if (ResourceType.Item.getTagName().equals(tagName)) {
            return ResourceRecord.createResourceRecord(Item.class,
                recordSchema, packing, recordPosition, dataDOM, dataText,
                transport);
        }
        else if (ResourceType.Container.getTagName().equals(tagName)) {
            return ResourceRecord.createResourceRecord(Container.class,
                recordSchema, packing, recordPosition, dataDOM, dataText,
                transport);
        }
        else if (ResourceType.OrganizationalUnit.getTagName().equals(tagName)) {
            return ResourceRecord.createResourceRecord(
                OrganizationalUnit.class, recordSchema, packing,
                recordPosition, dataDOM, dataText, transport);
        }
        else if (ResourceType.Context.getTagName().equals(tagName)) {
            return ResourceRecord.createResourceRecord(Context.class,
                recordSchema, packing, recordPosition, dataDOM, dataText,
                transport);
        }
        else if (ResourceType.ContentRelation.getTagName().equals(tagName)) {
            return ResourceRecord.createResourceRecord(ContentRelation.class,
                recordSchema, packing, recordPosition, dataDOM, dataText,
                transport);
        }
        else if (ResourceType.Role.getTagName().equals(tagName)) {
            return ResourceRecord.createResourceRecord(Role.class,
                recordSchema, packing, recordPosition, dataDOM, dataText,
                transport);
        }
        else if (ResourceType.UserAccount.getTagName().equals(tagName)) {
            return ResourceRecord.createResourceRecord(UserAccount.class,
                recordSchema, packing, recordPosition, dataDOM, dataText,
                transport);
        }

        /**
         * If we are unable to guess the type of the content return a default
         * record.
         */
        return new DefaultRecord(recordSchema, packing, recordPosition,
            dataDOM, dataText, transport);
    }
}