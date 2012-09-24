/**
 * 
 */
package de.escidoc.core.common.jibx.binding;

import java.io.IOException;

import org.jibx.runtime.IAliasable;
import org.jibx.runtime.IMarshaller;
import org.jibx.runtime.IMarshallingContext;
import org.jibx.runtime.IUnmarshaller;
import org.jibx.runtime.IUnmarshallingContext;
import org.jibx.runtime.JiBXException;
import org.jibx.runtime.impl.MarshallingContext;
import org.jibx.runtime.impl.UnmarshallingContext;

/**
 * @author MVO
 * 
 */
public class OptionalCDataMarshaller extends MarshallingBase implements IAliasable, IMarshaller, IUnmarshaller {

    /**
     * 
     * @param uri
     * @param index
     * @param name
     */
    public OptionalCDataMarshaller(final String uri, final int index, final String name) {
        super(uri, index, name);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.jibx.runtime.IUnmarshaller#isPresent(org.jibx.runtime.
     * IUnmarshallingContext)
     */
    @Override
    public boolean isPresent(final IUnmarshallingContext ictx) throws JiBXException {
        return ictx.isAt(getUri(), getName());
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.jibx.runtime.IUnmarshaller#unmarshal(java.lang.Object,
     * org.jibx.runtime.IUnmarshallingContext)
     */
    @Override
    public Object unmarshal(final Object obj, final IUnmarshallingContext ictx) throws JiBXException {

        if (!(ictx instanceof UnmarshallingContext))
            throw new IllegalArgumentException("UnmarshallingContext is not of expected type.");

        UnmarshallingContext ctx = (UnmarshallingContext) ictx;
        return ctx.parseElementText(getUri(), getName(), "");
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

    /*
     * (non-Javadoc)
     * 
     * @see org.jibx.runtime.IMarshaller#marshal(java.lang.Object,
     * org.jibx.runtime.IMarshallingContext)
     */
    @Override
    public void marshal(final Object obj, final IMarshallingContext ictx) throws JiBXException {

        if (!(ictx instanceof MarshallingContext))
            throw new IllegalArgumentException("MarshallingContext is not of expected type.");
        if (!(obj instanceof String))
            throw new IllegalArgumentException("Supplied object must be of type java.lang.String.");

        MarshallingContext ctx = (MarshallingContext) ictx;

        ctx.startTag(getIndex(), getName());

        /*
         * For some reason, JiBX does not handle this Exception within the
         * UnmarshallerContext class like done in the other methods.
         */
        try {
            ctx.writeCData((String) obj);
        }
        catch (IOException e) {
            throw new JiBXException("Error writing marshalled document", e);
        }

        ctx.endTag(getIndex(), getName());
    }
}
