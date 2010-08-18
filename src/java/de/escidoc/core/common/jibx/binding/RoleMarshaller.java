package de.escidoc.core.common.jibx.binding;

import java.io.IOException;

import javax.swing.JButton;

import org.apache.axis.wsdl.toJava.JavaBindingWriter;
import org.jibx.runtime.IAliasable;
import org.jibx.runtime.IMarshallable;
import org.jibx.runtime.IMarshaller;
import org.jibx.runtime.IMarshallingContext;
import org.jibx.runtime.IUnmarshaller;
import org.jibx.runtime.IUnmarshallingContext;
import org.jibx.runtime.JiBXException;
import org.jibx.runtime.impl.MarshallingContext;
import org.jibx.runtime.impl.UnmarshallingContext;

import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.resources.aa.role.RoleProperties;
import de.escidoc.core.resources.aa.role.Scope;

/**
 * Role Marshaller.
 * 
 * @author
 * 
 */
//public class RoleMarshaller extends MarshallingBase
//    implements IMarshaller, IUnmarshaller, IAliasable {
//    
//
//    public RoleMarshaller(String uri, int index, String name) {
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
////    public void marshal(Object obj, IMarshallingContext ictx)
////        throws JiBXException {
////        if (!(obj instanceof de.escidoc.core.resources.aa.role.Role)) {
////            throw new JiBXException("Invalid object type for marshaller");
////        }
////        else if (!(ictx instanceof MarshallingContext)) {
////            throw new JiBXException("Invalid object type for marshaller");
////        }
////        else {
////            // TODO iterate all attributes and save them in a HashMap
////            MarshallingContext ctx = (MarshallingContext) ictx;
////            de.escidoc.core.resources.aa.role.Role role =
////                (de.escidoc.core.resources.aa.role.Role) obj;
////            int[] urisIndex = new int[1];
////            urisIndex[0] = getIndex();
////            String [] prefixIndex = new String [1];
////            prefixIndex[0] = "role";
////            ctx.startTagNamespaces(getIndex(), getName(), urisIndex, prefixIndex);
////            
////            if (role.getObjid() != null) {
////                ctx.attribute(0, "objid", role.getObjid());
////            }
////            if (role.getLastModificationDate() != null) {
////                ctx.attribute(0, "last-modification-date", role.getLastModificationDate());
////            }
////            
////            ctx.closeStartContent();
////           if (role.getProperties() instanceof IMarshallable) {
////           
////                ((IMarshallable)role.getProperties()).marshal(ctx);
////               
////            } else {
////                throw new JiBXException("Mapped value is not marshallable");
////            }
////                
////            if (role.getScope() instanceof IMarshallable) {
////                ((IMarshallable)role.getScope()).marshal(ctx);
////               
////            } else {
////                throw new JiBXException("Mapped value is not marshallable");
////            }
////
////            if (role.getPolicyOrPolicySet() != null) {
////                try {
////                    setContentWhileMarshalling(ctx, role.getPolicyOrPolicySet());
////                }
////                catch (IOException e) {
////                   throw new JiBXException(e.getMessage());
////                }
////            }
////            ctx.endTag(getIndex(), getName());
////        }
////
////    }
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
////        de.escidoc.core.resources.aa.role.Role result =
////            (de.escidoc.core.resources.aa.role.Role) arg0;
////        UnmarshallingContext ctx = (UnmarshallingContext) ictx;
////        if (!ctx.isAt("http://www.escidoc.de/schemas/role/0.4", "role")) {
////            ctx.throwStartTagNameError("http://www.escidoc.de/schemas/role/0.4", "role");
////        }
////
////        if (arg0 == null) {
////            result = new de.escidoc.core.resources.aa.role.Role();
////        }
////        // TODO iterate all attributes and save them in a HashMap
////        result.setObjid(ctx.attributeText(null,"objid", null));
////        result.setLastModificationDate(ctx.attributeText(null,
////            "last-modification-date", null));
////        
////
////        ctx.parsePastStartTag(getUri(), getName());
////        RoleProperties properties = (RoleProperties)ctx.unmarshalElement();
////        Scope scope = (Scope)ctx.unmarshalElement();
////        result.setProperties(properties);
////        result.setScope(scope);
////        
////
////        result.setPolicyOrPolicySet(getContentOfElementAsXml(ctx, getName()));
////        ctx.isEnd();
////        ctx.parsePastEndTag(getUri(), getName());
////        return result;
////    }
//
//}
