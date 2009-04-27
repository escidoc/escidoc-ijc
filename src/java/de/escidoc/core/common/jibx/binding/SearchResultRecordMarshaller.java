package de.escidoc.core.common.jibx.binding;

import org.jibx.runtime.IAliasable;
import org.jibx.runtime.IMarshallable;
import org.jibx.runtime.IMarshaller;
import org.jibx.runtime.IMarshallingContext;
import org.jibx.runtime.IUnmarshaller;
import org.jibx.runtime.IUnmarshallingContext;
import org.jibx.runtime.JiBXException;
import org.jibx.runtime.impl.MarshallingContext;
import org.jibx.runtime.impl.UnmarshallingContext;

import de.escidoc.core.resources.om.container.Container;
import de.escidoc.core.resources.om.context.Context;
import de.escidoc.core.resources.om.item.Item;
import de.escidoc.core.resources.oum.OrganizationalUnit;
import de.escidoc.core.resources.sb.search.Highlight;

/**
 * Role Marshaller.
 * 
 * @author
 * 
 */
public class SearchResultRecordMarshaller extends MarshallingBase
    implements IMarshaller, IUnmarshaller, IAliasable {
    

    public SearchResultRecordMarshaller(String uri, int index, String name) {

        super(uri, index, name);
    }

    public boolean isExtension(int arg0) {
        return false;
    }

    /**
     * (Java class > XML) See Interface for functional description.
     * 
     * @param obj
     * @param ictx
     * @throws JiBXException
     * @see org.jibx.runtime.IMarshaller#marshal(java.lang.Object,
     *      org.jibx.runtime.IMarshallingContext)
     */
    public void marshal(Object obj, IMarshallingContext ictx)
        throws JiBXException {
        if (!(obj instanceof de.escidoc.core.resources.sb.search.SearchResultRecord)) {
            throw new JiBXException("Invalid object type for marshaller");
        }
        else if (!(ictx instanceof MarshallingContext)) {
            throw new JiBXException("Invalid object type for marshaller");
        }
        else {
            // TODO iterate all attributes and save them in a HashMap
            MarshallingContext ctx = (MarshallingContext) ictx;
            de.escidoc.core.resources.sb.search.SearchResultRecord record =
                (de.escidoc.core.resources.sb.search.SearchResultRecord) obj;
            int[] urisIndex = new int[1];
            urisIndex[0] = getIndex();
            String [] prefixIndex = new String [1];
            prefixIndex[0] = "search-result";
            
            ctx.startTagNamespaces(0, getName(), urisIndex, prefixIndex);
            
            if (record.getBase() != null) {
                ctx.attribute(1, "base", record.getBase());
            }
            
            
            ctx.closeStartContent();
           if (record.getHighlight() instanceof IMarshallable) {
           
                ((IMarshallable)record.getHighlight()).marshal(ctx);
               
            } else {
                throw new JiBXException("Mapped value is not marshallable");
            }
           if (record.getContent() instanceof IMarshallable) {
               
               ((IMarshallable)record.getContent()).marshal(ctx);
              
           } else {
               throw new JiBXException("Mapped value is not marshallable");
           }   
            
            
            ctx.endTag(getIndex(), getName());
        }

    }

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

        de.escidoc.core.resources.sb.search.SearchResultRecord result =
            (de.escidoc.core.resources.sb.search.SearchResultRecord) arg0;
        UnmarshallingContext ctx = (UnmarshallingContext) ictx;
        if (!ctx.isAt("http://www.escidoc.de/schemas/searchresult/0.7", "search-result-record")) {
            ctx.throwStartTagNameError("http://www.escidoc.de/schemas/searchresult/0.7", "search-result-record");
        }

        if (arg0 == null) {
            result = new de.escidoc.core.resources.sb.search.SearchResultRecord();
        }
        // TODO iterate all attributes and save them in a HashMap
        result.setBase(ctx.attributeText("http://www.w3.org/XML/1998/namespace","base", null));
        
        ctx.parsePastStartTag(getUri(), getName());
        Highlight highlight = (Highlight)ctx.unmarshalElement();
        
        result.setHighlight(highlight);
        
        if (ctx.isAt("http://www.escidoc.de/schemas/item/0.7", "item")) {
            Item item = (Item)ctx.unmarshalElement();
            result.setContent(item);
        } else if (ctx.isAt("http://www.escidoc.de/schemas/container/0.7", "container")) {
            Container container = (Container)ctx.unmarshalElement(); 
            result.setContent(container);
        } else if (ctx.isAt("http://www.escidoc.de/schemas/organizationalunit/0.6", "organizational-unit")) {
            OrganizationalUnit ou = (OrganizationalUnit)ctx.unmarshalElement(); 
            result.setContent(ou);
        } else if (ctx.isAt("http://www.escidoc.de/schemas/context/0.6", "context")) {
            Context context = (Context)ctx.unmarshalElement(); 
            result.setContent(context);
        }

        
        ctx.isEnd();
        ctx.parsePastEndTag(getUri(), getName());
        return result;
    }

}
