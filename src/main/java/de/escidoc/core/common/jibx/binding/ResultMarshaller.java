package de.escidoc.core.common.jibx.binding;

import org.jibx.runtime.IAliasable;
import org.jibx.runtime.IMarshaller;
import org.jibx.runtime.IMarshallingContext;
import org.jibx.runtime.IUnmarshaller;
import org.jibx.runtime.IUnmarshallingContext;
import org.jibx.runtime.JiBXException;
import org.jibx.runtime.impl.MarshallingContext;
import org.jibx.runtime.impl.UnmarshallingContext;

import de.escidoc.core.client.exceptions.EscidocClientException;
import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.client.exceptions.TransportException;

/**
 * 
 * @author SWA
 * 
 */
public class ResultMarshaller extends MarshallingBase
    implements IMarshaller, IUnmarshaller, IAliasable {

    /**
     * ResultMarshaller.
     * 
     * @param uri
     *            URI of XML Result XSD representation
     * @param index
     * @param name
     */
    public ResultMarshaller(final String uri, final int index, final String name) {

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
     * (Java class -> XML) See Interface for functional description.
     * 
     * @param obj
     * @param ictx
     * @throws JiBXException
     * @see org.jibx.runtime.IMarshaller#marshal(java.lang.Object,
     *      org.jibx.runtime.IMarshallingContext)
     */
    public void marshal(Object obj, IMarshallingContext ictx)
        throws JiBXException {
        if (!(obj instanceof de.escidoc.core.resources.common.Result)) {
            throw new JiBXException("Invalid object type for marshaller");
        }
        else if (!(ictx instanceof MarshallingContext)) {
            throw new JiBXException("Invalid object type for marshaller");
        }
        else {
            MarshallingContext ctx = (MarshallingContext) ictx;
            de.escidoc.core.resources.common.Result result =
                (de.escidoc.core.resources.common.Result) obj;

            try {
                ctx.closeStartContent();

                // ctx.startTag(getIndex(), ID_ELEMENT_NAME);
                ctx.content(result.getParam());
                // ctx.endTag(getIndex(), ID_ELEMENT_NAME);
                ctx.endTag(getIndex(), getName());
            }
            catch (InternalClientException e) {
                throw new JiBXException(e.toString());
            }
            catch (TransportException e) {
                throw new JiBXException(e.toString());
            }
            catch (EscidocClientException e) {
                throw new JiBXException(e.toString());
            }
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

        UnmarshallingContext ctx = (UnmarshallingContext) ictx;
        String result = "";
        String name = getName();
        String content = getContentOfElementAsXml(ctx, getName());

        result += content + "</" + name + ">";

        // For what does one need this?
        ctx.isEnd();
        ctx.parsePastEndTag(getUri(), getName());

        return result;
    }

}
