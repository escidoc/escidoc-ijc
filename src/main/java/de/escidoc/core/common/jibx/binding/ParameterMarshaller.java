/**
 * 
 */
package de.escidoc.core.common.jibx.binding;

import org.jibx.runtime.IAliasable;
import org.jibx.runtime.IMarshaller;
import org.jibx.runtime.IMarshallingContext;
import org.jibx.runtime.IUnmarshaller;
import org.jibx.runtime.IUnmarshallingContext;
import org.jibx.runtime.JiBXException;
import org.jibx.runtime.impl.MarshallingContext;
import org.jibx.runtime.impl.UnmarshallingContext;
import org.joda.time.DateTime;

import de.escidoc.core.resources.sm.DateParameter;
import de.escidoc.core.resources.sm.DecimalParameter;
import de.escidoc.core.resources.sm.Parameter;
import de.escidoc.core.resources.sm.StringParameter;

/**
 * @author MVO
 * 
 */
public class ParameterMarshaller extends MarshallingBase implements IMarshaller, IUnmarshaller, IAliasable {

    private static final String TAG_NAME_DATE = "datevalue";

    private static final String TAG_NAME_STRING = "stringvalue";

    private static final String TAG_NAME_DECIMAL = "decimalvalue";

    private static final String ATTR_NAME_NAME = "name";

    /**
     * 
     * @param uri
     * @param index
     * @param name
     */
    public ParameterMarshaller(final String uri, final int index, final String name) {
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
        Parameter<?> p = null;

        String name = ctx.attributeText(null, ATTR_NAME_NAME);
        ctx.parsePastStartTag(getUri(), getName());

        if (ctx.isAt(getUri(), TAG_NAME_STRING)) {
            String value = ctx.parseElementText(getUri(), TAG_NAME_STRING);
            p = new StringParameter(name, value);
        }
        else if (ctx.isAt(getUri(), TAG_NAME_DECIMAL)) {
            Float value = Float.valueOf(ctx.parseElementText(getUri(), TAG_NAME_DECIMAL));
            p = new DecimalParameter(name, value);
        }
        else if (ctx.isAt(getUri(), TAG_NAME_DATE)) {
            DateTime value = new DateTime(ctx.parseElementText(getUri(), TAG_NAME_DATE));
            p = new DateParameter(name, value);
        }
        else {
            throw new JiBXException("Unexpected element {" + ctx.getElementNamespace() + "}" + ctx.getElementName());
        }

        ctx.parsePastEndTag(getUri(), getName());

        return p;
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
        if (!(obj instanceof Parameter))
            throw new JiBXException("Invalid object type for marshaller");
        if (!(ictx instanceof MarshallingContext))
            throw new JiBXException("Invalid MarshallingContext type for marshaller");

        MarshallingContext ctx = (MarshallingContext) ictx;
        Parameter<?> p = (Parameter<?>) obj;

        ctx.startTagAttributes(getIndex(), getName()).attribute(0, ATTR_NAME_NAME, p.getName());
        ctx.closeStartContent();

        switch (p.getParameterType()) {
            case STRING: {
                ctx.startTag(getIndex(), TAG_NAME_STRING);
                ctx.content(((StringParameter) p).getValue());
                ctx.endTag(getIndex(), TAG_NAME_STRING);
                break;
            }
            case DECIMAL: {
                ctx.startTag(getIndex(), TAG_NAME_DECIMAL);
                ctx.content(((DecimalParameter) p).getValue().toString());
                ctx.endTag(getIndex(), TAG_NAME_DECIMAL);
                break;
            }
            case DATE: {
                ctx.startTag(getIndex(), TAG_NAME_DATE);
                ctx.content(((DateParameter) p).getValue().toString());
                ctx.endTag(getIndex(), TAG_NAME_DATE);
                break;
            }
        }

        ctx.endTag(getIndex(), getName());
    }
}
