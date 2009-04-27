package de.escidoc.core.common.jibx.binding;

import org.jibx.runtime.IAliasable;
import org.jibx.runtime.IMarshaller;
import org.jibx.runtime.IMarshallingContext;
import org.jibx.runtime.IUnmarshaller;
import org.jibx.runtime.IUnmarshallingContext;
import org.jibx.runtime.JiBXException;
import org.jibx.runtime.impl.MarshallingContext;
import org.jibx.runtime.impl.UnmarshallingContext;

/**
 * Content Stream Marshaller.
 * 
 * 
 */
public class ContentStream extends MarshallingBase
    implements IMarshaller, IUnmarshaller, IAliasable {
    private static final String STORAGE_ATTRIBUTE_NAME = "storage";

    private static final String MIME_TYPE_ATTRIBUTE_NAME = "mime-type";

    public ContentStream(String uri, int index, String name) {

        super(uri, index, name);
    }

    public boolean isExtension(int arg0) {
        return false;
    }

    public void marshal(Object obj, IMarshallingContext ictx)
        throws JiBXException {
        if (!(obj instanceof de.escidoc.core.resources.common.ContentStream)) {
            throw new JiBXException("Invalid object type for marshaller");
        }
        else if (!(ictx instanceof MarshallingContext)) {
            throw new JiBXException("Invalid object type for marshaller");
        }
        else {
            // TODO iterate all attributes and save them in a HashMap
            MarshallingContext ctx = (MarshallingContext) ictx;
            de.escidoc.core.resources.common.ContentStream contentStream =
                (de.escidoc.core.resources.common.ContentStream) obj;
            ctx.startTagAttributes(getIndex(), getName()).attribute(0,
                NAME_ATTRIBUTE_NAME, contentStream.getName());
            ctx
                .attribute(0, STORAGE_ATTRIBUTE_NAME, contentStream
                    .getStorage());
            ctx.attribute(0, MIME_TYPE_ATTRIBUTE_NAME, contentStream
                .getMimeType());

            if (contentStream.getXlinkType() != null) {
                ctx.attribute(1, XLINK_TYPE_ATTRIBUTE_NAME, contentStream
                    .getXlinkType().toString());
            }
            if (contentStream.getTitle() != null) {
                ctx.attribute(0, XLINK_TITLE_ATTRIBUTE_NAME, contentStream
                    .getTitle());
            }

            if (contentStream.getHref() != null) {
                ctx.attribute(0, XLINK_HREF_ATTRIBUTE_NAME, contentStream
                    .getHref());
            }
            ctx.closeStartContent();
            ctx.content(contentStream.getContent());
            ctx.endTag(getIndex(), getName());
        }

    }

    public boolean isPresent(IUnmarshallingContext ictx) throws JiBXException {
        return ictx.isAt(getUri(), getName());
    }

    public Object unmarshal(Object arg0, IUnmarshallingContext ictx)
        throws JiBXException {

        de.escidoc.core.resources.common.ContentStream result =
            (de.escidoc.core.resources.common.ContentStream) arg0;
        UnmarshallingContext ctx = (UnmarshallingContext) ictx;

        if (arg0 == null) {
            result = new de.escidoc.core.resources.common.ContentStream();
        }
        // TODO iterate all attributes and save them in a HashMap
        result.setName(ctx.attributeText(null,
            ContentStream.NAME_ATTRIBUTE_NAME));
        result.setStorage(ctx.attributeText(null,
            ContentStream.STORAGE_ATTRIBUTE_NAME, null));
        result.setMimeType(ctx.attributeText(null,
            ContentStream.MIME_TYPE_ATTRIBUTE_NAME, null));

        result.setMimeType(ctx.attributeText(XLINK_NAMESPACE,
            ContentStream.XLINK_HREF_ATTRIBUTE_NAME, null));
        result.setMimeType(ctx.attributeText(XLINK_NAMESPACE,
            ContentStream.XLINK_TITLE_ATTRIBUTE_NAME, null));
        result.setMimeType(ctx.attributeText(XLINK_NAMESPACE,
            ContentStream.XLINK_TYPE_ATTRIBUTE_NAME, null));

        ctx.parsePastStartTag(getUri(), getName());
        result.setContent(getContentOfElementAsXml(ctx, getName()));
        ctx.isEnd();
        ctx.parsePastEndTag(getUri(), getName());
        return result;
    }

}
