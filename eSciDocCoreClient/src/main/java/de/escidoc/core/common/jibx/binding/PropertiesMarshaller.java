/**
 * 
 */
package de.escidoc.core.common.jibx.binding;

import java.util.Map;

import org.jibx.runtime.IAliasable;
import org.jibx.runtime.IMarshaller;
import org.jibx.runtime.IMarshallingContext;
import org.jibx.runtime.IUnmarshaller;
import org.jibx.runtime.IUnmarshallingContext;
import org.jibx.runtime.JiBXException;
import org.jibx.runtime.impl.MarshallingContext;
import org.jibx.runtime.impl.UnmarshallingContext;

import de.escidoc.core.resources.common.Properties;

/**
 * @author MVO
 * 
 */
public class PropertiesMarshaller extends MarshallingBase implements IMarshaller, IUnmarshaller, IAliasable {

    private static final String TAG_NAME_ENTRY = "entry";

    private static final String ATTR_NAME_KEY = "key";

    /**
     * 
     * @param uri
     * @param index
     * @param name
     */
    public PropertiesMarshaller(String uri, int index, String name) {
        super(uri, index, name);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.jibx.runtime.IUnmarshaller#isPresent(org.jibx.runtime.
     * IUnmarshallingContext)
     */
    public boolean isPresent(IUnmarshallingContext ictx) throws JiBXException {
        return ictx.isAt(getUri(), getName());
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.jibx.runtime.IMarshaller#isExtension(java.lang.String)
     */
    public boolean isExtension(String arg0) {
        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.jibx.runtime.IUnmarshaller#unmarshal(java.lang.Object,
     * org.jibx.runtime.IUnmarshallingContext)
     */
    public Object unmarshal(final Object obj, final IUnmarshallingContext ictx) throws JiBXException {

        if (!(ictx instanceof UnmarshallingContext)) {
            throw new JiBXException("Unexpected unmarshalling context type.");
        }

        Properties result = new Properties();
        UnmarshallingContext ctx = (UnmarshallingContext) ictx;

        ctx.parsePastStartTag(getUri(), getName());

        while (ctx.isAt(getUri(), TAG_NAME_ENTRY)) {
            String key = ctx.attributeText(getUri(), ATTR_NAME_KEY, null);
            ctx.parsePastStartTag(getUri(), TAG_NAME_ENTRY);
            String value = ctx.parseContentText(getUri(), TAG_NAME_ENTRY);

            result.put(key, value);
        }

        ctx.parsePastEndTag(getUri(), getName());

        return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.jibx.runtime.IMarshaller#marshal(java.lang.Object,
     * org.jibx.runtime.IMarshallingContext)
     */
    public void marshal(final Object obj, final IMarshallingContext ictx) throws JiBXException {

        if (!(obj instanceof Properties)) {
            throw new JiBXException("Invalid object type for marshaller");
        }
        if (!(ictx instanceof MarshallingContext)) {
            throw new JiBXException("Invalid marshalling context type for marshaller");
        }

        MarshallingContext ctx = (MarshallingContext) ictx;

        // TODO: implement startTagNamespaces
        ctx.startTag(getIndex(), getName());
        ctx.closeStartContent();

        for (Map.Entry<String, String> entry : ((Properties) obj).entrySet()) {
            ctx.startTagAttributes(getIndex(), TAG_NAME_ENTRY);
            ctx.attribute(getIndex(), ATTR_NAME_KEY, entry.getKey());
            ctx.closeStartContent();

            ctx.content(entry.getValue());

            ctx.endTag(getIndex(), TAG_NAME_ENTRY);
        }

        ctx.endTag(getIndex(), getName());
    }

}
