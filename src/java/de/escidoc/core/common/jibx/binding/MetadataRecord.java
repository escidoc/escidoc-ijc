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
 * Metadataecord Marshaller.
 * 
 * @author
 * 
 */
//public class MetadataRecord extends MarshallingBase
//    implements IMarshaller, IUnmarshaller, IAliasable {
//    private static final String SCHEMA_ATTRIBUTE_NAME = "schema";
//
//    private static final String MD_TYPE_ATTRIBUTE_NAME = "md-type";
//
//    public MetadataRecord(String uri, int index, String name) {
//
//        super(uri, index, name);
//    }
//
//    public boolean isExtension(int arg0) {
//        return false;
//    }
//
//    /**
//     * (Java class > XML) See Interface for functional description.
//     * 
//     * @param obj
//     * @param ictx
//     * @throws JiBXException
//     * @see org.jibx.runtime.IMarshaller#marshal(java.lang.Object,
//     *      org.jibx.runtime.IMarshallingContext)
//     */
//    public void marshal(Object obj, IMarshallingContext ictx)
//        throws JiBXException {
//        if (!(obj instanceof de.escidoc.core.resources.common.MetadataRecord)) {
//            throw new JiBXException("Invalid object type for marshaller");
//        }
//        else if (!(ictx instanceof MarshallingContext)) {
//            throw new JiBXException("Invalid object type for marshaller");
//        }
//        else {
//            // TODO iterate all attributes and save them in a HashMap
//            MarshallingContext ctx = (MarshallingContext) ictx;
//            de.escidoc.core.resources.common.MetadataRecord mdRecord =
//                (de.escidoc.core.resources.common.MetadataRecord) obj;
//
//            ctx.startTagAttributes(getIndex(), getName());
//            if (mdRecord.getName() != null) {
//                ctx.attribute(0, NAME_ATTRIBUTE_NAME, mdRecord.getName());
//            }
//            if (mdRecord.getSchema() != null) {
//                ctx.attribute(0, SCHEMA_ATTRIBUTE_NAME, mdRecord.getSchema());
//            }
//            if (mdRecord.getMdType() != null) {
//                ctx.attribute(0, MD_TYPE_ATTRIBUTE_NAME, mdRecord.getMdType());
//            }
//            ctx.closeStartContent();
//            if (mdRecord.getContent() != null) {
//              //  ctx.content(mdRecord.getContent());
//                try {
//                    setContentWhileMarshalling(ctx, mdRecord.getContent());
//                }
//                catch (IOException e) {
//                   throw new JiBXException(e.getMessage());
//                }
//            
//            }
//            ctx.endTag(getIndex(), getName());
//        }
//
//    }
//
//    public boolean isPresent(IUnmarshallingContext ictx) throws JiBXException {
//        return ictx.isAt(getUri(), getName());
//    }
//
//    /**
//     * (XML -> Java class) See Interface for functional description.
//     * 
//     * @param arg0
//     * @param ictx
//     * @return
//     * @throws JiBXException
//     * @see org.jibx.runtime.IUnmarshaller#unmarshal(java.lang.Object,
//     *      org.jibx.runtime.IUnmarshallingContext)
//     */
////    public Object unmarshal(Object arg0, IUnmarshallingContext ictx)
////        throws JiBXException {
////
////        de.escidoc.core.resources.common.MetadataRecord result =
////            (de.escidoc.core.resources.common.MetadataRecord) arg0;
////        UnmarshallingContext ctx = (UnmarshallingContext) ictx;
////
////        if (arg0 == null) {
////            result = new de.escidoc.core.resources.common.MetadataRecord();
////        }
////        // TODO iterate all attributes and save them in a HashMap
////        result.setName(ctx.attributeText(null,
////            MetadataRecord.NAME_ATTRIBUTE_NAME));
////        result.setMdType(ctx.attributeText(null,
////            MetadataRecord.MD_TYPE_ATTRIBUTE_NAME, null));
////        result.setSchema(ctx.attributeText(null,
////            MetadataRecord.SCHEMA_ATTRIBUTE_NAME, null));
////
////        ctx.parsePastStartTag(getUri(), getName());
////        result.setContent(getContentOfElementAsXml(ctx, getName()));
////        ctx.isEnd();
////        ctx.parsePastEndTag(getUri(), getName());
////        return result;
////    }
//
//}
