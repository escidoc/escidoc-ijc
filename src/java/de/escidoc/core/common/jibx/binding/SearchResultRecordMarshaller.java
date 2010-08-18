package de.escidoc.core.common.jibx.binding;

import org.jibx.runtime.IAliasable;
import org.jibx.runtime.IMarshallable;
import org.jibx.runtime.IMarshaller;
import org.jibx.runtime.IMarshallingContext;
import org.jibx.runtime.IUnmarshaller;
import org.jibx.runtime.IUnmarshallingContext;
import org.jibx.runtime.JiBXException;
import org.jibx.runtime.impl.MarshallingContext;
import org.jibx.runtime.impl.UnmarshallingContext;

import de.escidoc.core.resources.om.container.Container;
import de.escidoc.core.resources.om.context.Context;
import de.escidoc.core.resources.om.item.Item;
import de.escidoc.core.resources.oum.OrganizationalUnit;
import de.escidoc.core.resources.sb.search.Highlight;

/**
 * SearchResultRecord Marshaller.
 * 
 * @author
 * 
 */
public class SearchResultRecordMarshaller extends MarshallingBase
    implements IMarshaller, IUnmarshaller, IAliasable {

    /**
     * SearchResultRecord Marshaller.
     * 
     * @param uri
     *            Schema URI of SearchResultRecord
     * @param index
     * @param name
     */
    public SearchResultRecordMarshaller(final String uri, final int index,
        final String name) {

        super(uri, index, name);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.jibx.runtime.IMarshaller#isExtension(java.lang.String)
     */
    public boolean isExtension(final String arg0) {
        return false;
    }

    public boolean isExtension(final int arg0) {
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
    public void marshal(final Object obj, final IMarshallingContext ictx)
        throws JiBXException {

        if (!(obj instanceof de.escidoc.core.resources.sb.search.SearchResultRecord)) {
            throw new JiBXException("Invalid object type for marshaller");
        }
        else if (!(ictx instanceof MarshallingContext)) {
            throw new JiBXException("Invalid object type for marshaller");
        }
        else {
            // TODO iterate all attributes and save them in a HashMap
            MarshallingContext ctx = (MarshallingContext) ictx;
            de.escidoc.core.resources.sb.search.SearchResultRecord record =
                (de.escidoc.core.resources.sb.search.SearchResultRecord) obj;
            int[] urisIndex = new int[1];
            urisIndex[0] = getIndex();
            String[] prefixIndex = new String[1];
            prefixIndex[0] = "search-result";

            ctx.startTagNamespaces(0, getName(), urisIndex, prefixIndex);

            if (record.getBase() != null) {
                ctx.attribute(1, "base", record.getBase());
            }

            ctx.closeStartContent();

            ctx.element(0, "score", record.getScore());

            if (record.getHighlight() instanceof IMarshallable) {

                ((IMarshallable) record.getHighlight()).marshal(ctx);

            }
            else {
                throw new JiBXException("Mapped value is not marshallable");
            }

            if (record.getContent() instanceof IMarshallable) {

                ((IMarshallable) record.getContent()).marshal(ctx);

            }
            else {
                throw new JiBXException("Mapped value is not marshallable");
            }

            ctx.endTag(getIndex(), getName());
        }

    }

    /*
     * (non-Javadoc)
     * 
     * @seeorg.jibx.runtime.IUnmarshaller#isPresent(org.jibx.runtime.
     * IUnmarshallingContext)
     */
    public boolean isPresent(IUnmarshallingContext ictx) throws JiBXException {
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
    public Object unmarshal(Object arg0, IUnmarshallingContext ictx)
        throws JiBXException {

        de.escidoc.core.resources.sb.search.SearchResultRecord result =
            (de.escidoc.core.resources.sb.search.SearchResultRecord) arg0;
        UnmarshallingContext ctx = (UnmarshallingContext) ictx;
        if (!ctx.isAt(getUri(), getName())) {
            ctx.throwStartTagNameError(getUri(), getName());
        }

        if (arg0 == null) {
            result =
                new de.escidoc.core.resources.sb.search.SearchResultRecord();
        }
        // TODO iterate all attributes and save them in a HashMap
        result.setBase(ctx.attributeText(
            "http://www.w3.org/XML/1998/namespace", "base", null));

        ctx.parsePastStartTag(getUri(), getName());

        // loop over all elements
        while (true) {
            if (ctx.isAt(getUri(), "score")) {
                result.setScore(ctx.parseElementText(getUri(), "score"));
            }
            else if (ctx.isAt(getUri(), "highlight")) {
                Highlight highlight = (Highlight) ctx.unmarshalElement();
                result.setHighlight(highlight);
            }
            else if (ctx.isAt("http://www.escidoc.de/schemas/item/0.9", "item")) {
                Item item = (Item) ctx.unmarshalElement();
                result.setContent(item);
            }
            else if (ctx.isAt("http://www.escidoc.de/schemas/container/0.8",
                "container")) {
                Container container = (Container) ctx.unmarshalElement();
                result.setContent(container);
            }
            else if (ctx.isAt(
                "http://www.escidoc.de/schemas/organizationalunit/0.8",
                "organizational-unit")) {
                OrganizationalUnit ou =
                    (OrganizationalUnit) ctx.unmarshalElement();
                result.setContent(ou);
            }
            else if (ctx.isAt("http://www.escidoc.de/schemas/context/0.7",
                "context")) {
                Context context = (Context) ctx.unmarshalElement();
                result.setContent(context);
            }
            else {
                break;
            }
        }
        ctx.parsePastEndTag(getUri(), getName());
        return result;
    }
}
