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
public class ContentStreamMarshaller extends MarshallingBase
    implements IMarshaller, IUnmarshaller, IAliasable {

    private static final String STORAGE_ATTRIBUTE_NAME = "storage";

    private static final String MIME_TYPE_ATTRIBUTE_NAME = "mime-type";

    /**
     * 
     * @param uri
     * @param index
     * @param name
     */
    public ContentStreamMarshaller(final String uri, final int index,
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

    public boolean isExtension(final int arg0) {
        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.jibx.runtime.IMarshaller#marshal(java.lang.Object,
     * org.jibx.runtime.IMarshallingContext)
     */
    @Override
    public void marshal(final Object obj, final IMarshallingContext ictx)
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
                .attribute(0, STORAGE_ATTRIBUTE_NAME,
                    contentStream.getStorage());
            ctx.attribute(0, MIME_TYPE_ATTRIBUTE_NAME,
                contentStream.getMimeType());

            if (contentStream.getXLinkType() != null) {
                ctx.attribute(1, XLINK_TYPE_ATTRIBUTE_NAME, contentStream
                    .getXLinkType().toString());
            }
            if (contentStream.getXLinkTitle() != null) {
                ctx.attribute(0, XLINK_TITLE_ATTRIBUTE_NAME,
                    contentStream.getXLinkTitle());
            }

            if (contentStream.getXLinkHref() != null) {
                ctx.attribute(0, XLINK_HREF_ATTRIBUTE_NAME,
                    contentStream.getXLinkHref());
            }
            ctx.closeStartContent();
            ctx.content(contentStream.getContent());
            ctx.endTag(getIndex(), getName());
        }

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

    /*
     * (non-Javadoc)
     * 
     * @see org.jibx.runtime.IUnmarshaller#unmarshal(java.lang.Object,
     * org.jibx.runtime.IUnmarshallingContext)
     */
    @Override
    public Object unmarshal(final Object arg0, final IUnmarshallingContext ictx)
        throws JiBXException {

        de.escidoc.core.resources.common.ContentStream result =
            (de.escidoc.core.resources.common.ContentStream) arg0;
        UnmarshallingContext ctx = (UnmarshallingContext) ictx;

        if (arg0 == null) {
            result = new de.escidoc.core.resources.common.ContentStream();
        }
        // TODO iterate all attributes and save them in a HashMap
        result.setName(ctx.attributeText(null,
            ContentStreamMarshaller.NAME_ATTRIBUTE_NAME));
        result.setStorage(ctx.attributeText(null,
            ContentStreamMarshaller.STORAGE_ATTRIBUTE_NAME, null));
        result.setMimeType(ctx.attributeText(null,
            ContentStreamMarshaller.MIME_TYPE_ATTRIBUTE_NAME, null));

        result.setMimeType(ctx.attributeText(XLINK_NAMESPACE,
            ContentStreamMarshaller.XLINK_HREF_ATTRIBUTE_NAME, null));
        result.setMimeType(ctx.attributeText(XLINK_NAMESPACE,
            ContentStreamMarshaller.XLINK_TITLE_ATTRIBUTE_NAME, null));
        result.setMimeType(ctx.attributeText(XLINK_NAMESPACE,
            ContentStreamMarshaller.XLINK_TYPE_ATTRIBUTE_NAME, null));

        ctx.parsePastStartTag(getUri(), getName());
        result.setContent(getContentOfElementAsXml(ctx, getName()));
        ctx.isEnd();
        ctx.parsePastEndTag(getUri(), getName());
        return result;
    }

}
