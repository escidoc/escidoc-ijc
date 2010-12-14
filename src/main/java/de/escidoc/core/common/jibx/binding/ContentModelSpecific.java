package de.escidoc.core.common.jibx.binding;


//public class ContentModelSpecific extends MarshallingBase
//    implements IMarshaller, IUnmarshaller, IAliasable {
//    public ContentModelSpecific(String uri, int index, String name) {
//
//        super(uri, index, name);
//    }
//
//    public boolean isExtension(int arg0) {
//        return false;
//    }
//
//    public void marshal(Object obj, IMarshallingContext ictx)
//        throws JiBXException {
//        if (!(obj instanceof de.escidoc.core.resources.common.properties.ContentModelSpecific)) {
//            throw new JiBXException("Invalid object type for marshaller");
//        }
//        else if (!(ictx instanceof MarshallingContext)) {
//            throw new JiBXException("Invalid object type for marshaller");
//        }
//        else {
//            MarshallingContext ctx = (MarshallingContext) ictx;
//            de.escidoc.core.resources.common.properties.ContentModelSpecific cms =
//                (de.escidoc.core.resources.common.properties.ContentModelSpecific) obj;
//            ctx.startTagAttributes(getIndex(), getName());
//            ctx.closeStartContent();
//            ctx.content(cms.getContent());
//            ctx.endTag(getIndex(), getName());
//        }
//
//    }
//
//    public boolean isPresent(IUnmarshallingContext ictx) throws JiBXException {
//        return ictx.isAt(getUri(), getName());
//    }
//
//    public Object unmarshal(Object arg0, IUnmarshallingContext ictx)
//        throws JiBXException {
//
//        de.escidoc.core.resources.common.properties.ContentModelSpecific result =
//            (de.escidoc.core.resources.common.properties.ContentModelSpecific) arg0;
//        UnmarshallingContext ctx = (UnmarshallingContext) ictx;
//
//        if (arg0 == null) {
//            result = new de.escidoc.core.resources.common.properties.ContentModelSpecific();
//        }
//
//        ctx.parsePastStartTag(getUri(), getName());
//        result.setContent(getContentOfElementAsXml(ctx, getName()));
//        ctx.isEnd();
//        ctx.parsePastEndTag(getUri(), getName());
//        return result;
//    }
//
// }
