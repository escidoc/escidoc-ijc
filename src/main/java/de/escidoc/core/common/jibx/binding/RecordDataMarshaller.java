/**
 * 
 */
package de.escidoc.core.common.jibx.binding;

import static de.escidoc.core.common.Precondition.checkObject;

import org.jibx.runtime.IAliasable;
import org.jibx.runtime.IMarshaller;
import org.jibx.runtime.IMarshallingContext;
import org.jibx.runtime.IUnmarshaller;
import org.jibx.runtime.IUnmarshallingContext;
import org.jibx.runtime.JiBXException;
import org.jibx.runtime.impl.UnmarshallingContext;

import de.escidoc.core.client.TransportProtocol;
import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.common.jibx.MarshallerFactory;
import de.escidoc.core.resources.sb.Record;
import de.escidoc.core.resources.sb.Record.RecordPacking;

/**
 * 
 * Supports the unmarshalling of the recordData tag within the SRW/U response.
 * The unmarshalling has to happen separately, since the content of the
 * recordData tag can be an encoded XML string or the XML itself. This is
 * depending on the value of the recordPacking, which can be either
 * {@link RecordPacking#string} or {@link RecordPacking#xml}.<br/>
 * <br/>
 * <b>Important:</b><br/>
 * In case that the content is given as a string, a new Marshaller instance has
 * to be created to unmarshal this XML fragment. Therefore the current object
 * stack ({@link IUnmarshallingContext#getStackObject(int)}) will no longer be
 * available below this level of the XML. It will however be available, if and
 * only if the content of recordData is XML.<br/>
 * <br/>
 * Marshalling of the SRW/U response is not supported.
 * 
 * 
 * 
 * @author MVO
 * 
 */
public class RecordDataMarshaller extends MarshallingBase
    implements IMarshaller, IUnmarshaller, IAliasable {

    /**
     * @param uri
     * @param index
     * @param name
     */
    public RecordDataMarshaller(final String uri, final int index,
        final String name) {
        super(uri, index, name);
    }

    @Override
    public boolean isPresent(final IUnmarshallingContext ictx)
        throws JiBXException {
        return ictx.isAt(getUri(), getName());
    }

    @Override
    public Object unmarshal(final Object obj, final IUnmarshallingContext ictx)
        throws JiBXException {

        UnmarshallingContext ctx = checkUnmarshaller(ictx);
        Record<?> record = checkObject(Record.class, ictx.getStackTop());

        Object result = null;

        ctx.parsePastStartTag(getUri(), getName());

        if (record.getRecordPacking() == RecordPacking.xml) {
            /*
             * If the content of recordData is XML keep parsing it in the same
             * context.
             */
            result = ctx.unmarshalElement();
        }
        else {
            /*
             * If the content of recordData is a escaped String, get the String,
             * (decode it again) and use a new Marshaller instance to unmarshal
             * it.
             */
            String content = ctx.parseContentText();
            content = content.replaceAll("(^\\s*)|(\\s*$)", "");
            String bindingName = ctx.getFactory().getBindingName();
            if (bindingName == null) {
                throw new JiBXException(
                    "Unable to unmarshal SearchResultRecordRecord. "
                        + "No TransportProtocol defined for unmarshalling.");
            }

            TransportProtocol transport =
                TransportProtocol.valueOf(bindingName);
            try {
                result =
                    MarshallerFactory
                        .getInstance(transport)
                        .getMarshaller(record.getClass())
                        .unmarshalDocument(content);
            }
            catch (InternalClientException e) {
                throw new JiBXException(e.getMessage(), e);
            }
        }

        ctx.parsePastEndTag(getUri(), getName());

        return result;
    }

    @Override
    public boolean isExtension(final String arg0) {
        return false;
    }

    @Override
    public void marshal(final Object arg0, final IMarshallingContext arg1)
        throws JiBXException {
        throw new UnsupportedOperationException(EX_MARSH_NOT_SUPPORTED);
    }
}