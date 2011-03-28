package de.escidoc.core.common.jibx.binding;

import static de.escidoc.core.common.Precondition.checkObject;

import org.jibx.runtime.IAliasable;
import org.jibx.runtime.IMarshallable;
import org.jibx.runtime.IMarshaller;
import org.jibx.runtime.IMarshallingContext;
import org.jibx.runtime.IUnmarshaller;
import org.jibx.runtime.IUnmarshallingContext;
import org.jibx.runtime.JiBXException;
import org.jibx.runtime.impl.MarshallingContext;
import org.jibx.runtime.impl.UnmarshallingContext;

import de.escidoc.core.resources.sb.search.Highlight;
import de.escidoc.core.resources.sb.search.SearchResult;

/**
 * SearchResultRecord Marshaller.
 * 
 * @author
 * 
 */
public class SearchResultMarshaller extends MarshallingBase
    implements IMarshaller, IUnmarshaller, IAliasable {

    public static final String TAG_NAME_SCORE = "score";

    public static final String TAG_NAME_HIGHLIGHT = "highlight";

    // TODO there must be a way to get the prefix from the binding
    public static final String PREFIX_NAME = "search-result";

    public static final String ATTR_NAME_BASE = "base";

    /**
     * SearchResultRecord Marshaller.
     * 
     * @param uri
     *            Schema URI of SearchResultRecord
     * @param index
     * @param name
     */
    public SearchResultMarshaller(final String uri, final int index,
        final String name) {

        super(uri, index, name);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.jibx.runtime.IMarshaller#isExtension(java.lang.String)
     */
    @Override
    public boolean isExtension(final String arg0) {
        return false;
    }

    /**
     * (Java class > XML) See Interface for functional description.
     * 
     * TODO: marshalling should not be supported for search results.
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

        MarshallingContext ctx = checkMarshaller(ictx);
        SearchResult record = checkObject(SearchResult.class, obj);
        int[] urisIndex = new int[1];
        urisIndex[0] = getIndex();
        String[] prefixIndex = new String[1];
        prefixIndex[0] = PREFIX_NAME;

        ctx.startTagNamespaces(0, getName(), urisIndex, prefixIndex);

        if (record.getBase() != null) {
            ctx.attribute(1, ATTR_NAME_BASE, record.getBase());
        }

        ctx.closeStartContent();

        // optional
        if (record.getScore() != null) {
            ctx.element(0, TAG_NAME_SCORE, record.getScore().toString());
        }

        // optional
        if (record.getHighlight() != null) {
            if (record.getHighlight() instanceof IMarshallable) {

                ((IMarshallable) record.getHighlight()).marshal(ctx);

            }
            else {
                throw new JiBXException("Mapped value is not marshallable");
            }
        }

        // required
        if (record.getContent() instanceof IMarshallable) {

            ((IMarshallable) record.getContent()).marshal(ctx);

        }
        else {
            throw new JiBXException("Mapped value is not marshallable");
        }

        ctx.endTag(getIndex(), getName());

    }

    /*
     * (non-Javadoc)
     * 
     * @seeorg.jibx.runtime.IUnmarshaller#isPresent(org.jibx.runtime.
     * IUnmarshallingContext)
     */
    @Override
    public boolean isPresent(final IUnmarshallingContext ictx)
        throws JiBXException {
        return ictx.isAt(getUri(), getName());
    }

    /**
     * (XML -> Java class) See Interface for functional description.
     * 
     * @param obj
     * @param ictx
     * @return
     * @throws JiBXException
     * @see org.jibx.runtime.IUnmarshaller#unmarshal(java.lang.Object,
     *      org.jibx.runtime.IUnmarshallingContext)
     */
    @Override
    public Object unmarshal(final Object obj, final IUnmarshallingContext ictx)
        throws JiBXException {

        /*
         * ignore obj since the instance of SearchResultRecord will be created
         * at this point.
         */

        UnmarshallingContext ctx = checkUnmarshaller(ictx);

        SearchResult result = null;

        String base =
            ctx.attributeText("http://www.w3.org/XML/1998/namespace", "base",
                null);
        Float score = null;
        Highlight highlight = null;

        ctx.parsePastStartTag(getUri(), getName());

        // optional
        if (ctx.isAt(getUri(), TAG_NAME_SCORE)) {
            score =
                Float.valueOf(ctx.parseElementText(getUri(), TAG_NAME_SCORE));
        }
        // optional
        if (ctx.isAt(getUri(), TAG_NAME_HIGHLIGHT)) {
            highlight = (Highlight) ctx.unmarshalElement();
        }

        // required
        String contentXml = getElementAsXml(ctx);

        ctx.parsePastEndTag(getUri(), getName());

        result = new SearchResult(score, highlight, contentXml, base);

        return result;
    }
}