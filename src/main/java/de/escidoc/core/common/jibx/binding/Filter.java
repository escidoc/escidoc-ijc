package de.escidoc.core.common.jibx.binding;

import java.util.Collection;
import java.util.LinkedList;

import org.jibx.runtime.IAliasable;
import org.jibx.runtime.IMarshaller;
import org.jibx.runtime.IMarshallingContext;
import org.jibx.runtime.IUnmarshaller;
import org.jibx.runtime.IUnmarshallingContext;
import org.jibx.runtime.JiBXException;
import org.jibx.runtime.impl.MarshallingContext;
import org.jibx.runtime.impl.UnmarshallingContext;

/**
 * Filter.
 * 
 * 
 */
public class Filter extends MarshallingBase
    implements IMarshaller, IUnmarshaller, IAliasable {

    private static final String ID_ELEMENT_NAME = "id";

    /**
     * Filter.
     * 
     * @param uri
     * @param index
     * @param name
     */
    public Filter(final String uri, final int index, final String name) {

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

    /*
     * (non-Javadoc)
     * 
     * @see org.jibx.runtime.IMarshaller#marshal(java.lang.Object,
     * org.jibx.runtime.IMarshallingContext)
     */
    public void marshal(Object obj, IMarshallingContext ictx)
        throws JiBXException {
        if (!(obj instanceof de.escidoc.core.resources.common.Filter)) {
            throw new JiBXException("Invalid object type for marshaller");
        }
        else if (!(ictx instanceof MarshallingContext)) {
            throw new JiBXException("Invalid object type for marshaller");
        }
        else {
            MarshallingContext ctx = (MarshallingContext) ictx;
            de.escidoc.core.resources.common.Filter filter =
                (de.escidoc.core.resources.common.Filter) obj;

            ctx.startTagAttributes(getIndex(), getName()).attribute(0,
                NAME_ATTRIBUTE_NAME, filter.getName());
            ctx.closeStartContent();

            Collection<String> ids = filter.getIds();
            if (ids != null && ids.size() > 0) {
                for (String id : ids) {
                    ctx.startTag(getIndex(), ID_ELEMENT_NAME);
                    ctx.content(id);
                    ctx.endTag(getIndex(), ID_ELEMENT_NAME);
                }
            }
            else {
                ctx.content(filter.getValue());

            }
            ctx.endTag(getIndex(), getName());

            // // TODO iterate all attributes and save them in a HashMap
            // MarshallingContext ctx = (MarshallingContext) ictx;
            // de.escidoc.core.resources.common.MdRecord mdRecord =
            // (de.escidoc.core.resources.common.MdRecord) obj;

            // ctx.startTagAttributes(getIndex(), getName()).attribute(0,
            // NAME_ATTRIBUTE_NAME, mdRecord.getName());
            // if (mdRecord.getSchema() != null) {
            // ctx.attribute(0, SCHEMA_ATTRIBUTE_NAME, mdRecord.getSchema());
            // }
            // if (mdRecord.getMdType() != null) {
            // ctx.attribute(0, MD_TYPE_ATTRIBUTE_NAME, mdRecord.getMdType());
            // }
            // ctx.closeStartContent();
            // ctx.content(mdRecord.getContent());
            // ctx.endTag(getIndex(), getName());
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

    /*
     * (non-Javadoc)
     * 
     * @see org.jibx.runtime.IUnmarshaller#unmarshal(java.lang.Object,
     * org.jibx.runtime.IUnmarshallingContext)
     */
    public Object unmarshal(Object arg0, IUnmarshallingContext ictx)
        throws JiBXException {

        de.escidoc.core.resources.common.Filter result =
            (de.escidoc.core.resources.common.Filter) arg0;
        UnmarshallingContext ctx = (UnmarshallingContext) ictx;

        if (arg0 == null) {
            result = new de.escidoc.core.resources.common.Filter();
        }

        result.setName(ctx.attributeText(null, NAME_ATTRIBUTE_NAME));

        ctx.parsePastStartTag(getUri(), getName());
        result.setValue(ctx.parseContentText());
        if (ctx.isStart() && Filter.ID_ELEMENT_NAME.equals(ctx.getName())) {
            Collection<String> ids = new LinkedList<String>();
            while (Filter.ID_ELEMENT_NAME.equals(ctx.getName())) {
                if (ctx.isStart()) {
                    ctx.parsePastStartTag(getUri(), ctx.getName());
                    ids.add(ctx.parseContentText());
                }
                if (ctx.isEnd()) {
                    ctx.parsePastEndTag(getUri(), ctx.getName());
                }

            }
            result.setIds(ids);
        }
        ctx.isEnd();
        ctx.parsePastEndTag(getUri(), getName());
        return result;
    }

}
