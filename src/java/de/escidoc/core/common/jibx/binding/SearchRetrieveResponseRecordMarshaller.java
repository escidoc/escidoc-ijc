/**
 * 
 */
package de.escidoc.core.common.jibx.binding;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jibx.runtime.IAliasable;
import org.jibx.runtime.IMarshaller;
import org.jibx.runtime.IMarshallingContext;
import org.jibx.runtime.IUnmarshaller;
import org.jibx.runtime.IUnmarshallingContext;
import org.jibx.runtime.JiBXException;
import org.jibx.runtime.impl.UnmarshallingContext;
import org.w3c.dom.Element;

import de.escidoc.core.client.TransportProtocol;
import de.escidoc.core.resources.sb.Record.RecordPacking;
import de.escidoc.core.resources.sb.search.records.ContainerRecord;
import de.escidoc.core.resources.sb.search.records.ContentRelationRecord;
import de.escidoc.core.resources.sb.search.records.ContextRecord;
import de.escidoc.core.resources.sb.search.records.DefaultRecord;
import de.escidoc.core.resources.sb.search.records.ItemRecord;
import de.escidoc.core.resources.sb.search.records.OrganizationalUnitRecord;
import de.escidoc.core.resources.sb.search.records.RoleRecord;
import de.escidoc.core.resources.sb.search.records.SearchResultRecordRecord;
import de.escidoc.core.resources.sb.search.records.UserAccountRecord;

/**
 * @author MVO
 *
 */
public class SearchRetrieveResponseRecordMarshaller extends MarshallingBase
    implements IMarshaller, IUnmarshaller, IAliasable {

	/**
	 * Pattern to match the tag name and prefix into group 1.
	 */
	private static final Pattern tagNameNS = Pattern.compile(
			"^<([^>\\s]+)[^>]*?>");
	
	/**
	 * This {@link Enum} exists only because it is not possible to use special 
	 * characters in {@link Enum} values.
	 * 
	 * @author MVO
	 *
	 */
	private static enum RecordDataTag {
		
		SearchResultRecord("search-result-record"), 
		Item("item", "http://www.escidoc.de/schemas/item/0.9"), 
		Container("container", "http://www.escidoc.de/schemas/container/0.8"),
		OrganizationalUnit("organizational-unit", 
				"http://www.escidoc.de/schemas/organizationalunit/0.8"), 
		Context("context", "http://www.escidoc.de/schemas/context/0.7"), 
		ContentRelation("content-relation"), 
		Role("role"),
		UserAccount("user-account");
		
		private final String tagName;
		private final String namespace;
		
		RecordDataTag(String tagName) {
			this(tagName, null);
		}
		RecordDataTag(String tagName, String namespace) {
			this.tagName = tagName;
			this.namespace = namespace;
		}
		boolean equals(final String other) {
			return tagName.equals(other);
		}
		boolean equals(final String tagName, final String namespace) {
			if(this.tagName == null) return false;
			if(this.namespace == null)
				return this.tagName.equals(tagName);
			else
				return this.tagName.equals(tagName) 
					&& this.namespace.equals(namespace);
		}
	}
	
	/**
     * 
     * @param uri
     * @param index
     * @param name
     */
    public SearchRetrieveResponseRecordMarshaller(final String uri, 
    		final int index, final String name) {
        super(uri, index, name);
    }

    /**
     * @see org.jibx.runtime.IMarshaller#isExtension(java.lang.String)
     */
    public boolean isExtension(final String arg0) {
        return false;
    }

    /**
     * (Java class > XML) See Interface for functional description.
     * 
     * @param obj
     *            Object (SearchResultRecord) to Marshall
     * @param ictx
     *            Marshalling Context
     * @throws JiBXException
     *             Thrown if mapping failed by JiBX
     * @see org.jibx.runtime.IMarshaller#marshal(java.lang.Object,
     *      org.jibx.runtime.IMarshallingContext)
     */
    public void marshal(final Object obj, final IMarshallingContext ictx)
        throws JiBXException {
    	throw new JiBXException("Marshalling not supported.");
//        if (!(obj instanceof de.escidoc.core.resources.sb.search.SearchResultRecord)) {
//            throw new JiBXException("Invalid object type for marshaller");
//        }
//        else if (!(ictx instanceof MarshallingContext)) {
//            throw new JiBXException("Invalid object type for marshaller");
//        }
//        else {
//            // TODO iterate all attributes and save them in a HashMap
//            MarshallingContext ctx = (MarshallingContext) ictx;
//            de.escidoc.core.resources.sb.Record record =
//                (de.escidoc.core.resources.sb.Record) obj;
////            int[] urisIndex = new int[1];
////            urisIndex[0] = getIndex();
////            String[] prefixIndex = new String[1];
////            prefixIndex[0] = "search-result";
//
//            ctx.startTag(getIndex(), getName());
//
//            ctx.closeStartContent();
//
//            ctx.element(getIndex(), "recordSchema", record.getRecordSchema());
//            ctx.element(getIndex(), "recordPacking", record.getRecordPacking());
//            
//            ctx.startTag(getIndex(), "recordData");
//            
//            if (record.getRecordData() instanceof IMarshallable) {
//                ((IMarshallable) record.getRecordData()).marshal(ctx);
//            }
//            else {
//                throw new JiBXException("Mapped value is not marshallable");
//            }
//            
//            ctx.endTag(getIndex(), "recordData");
//            
//            ctx.element(getIndex(), "recordPosition", record.getRecordPosition());
//            ctx.endTag(getIndex(), getName());
//        }

    }

    /**
     * @see org.jibx.runtime.IUnmarshaller#isPresent(org.jibx.runtime.
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
    
    	ctx.parsePastStartTag(getUri(), getName());
    	
    	String recordSchema = null, recordPosition = null, packing = null;
    	String dataText = null;
    	Element dataDOM = null;
    	Object result = null;
    	
    	try {
	        while (true) {
	            if (ctx.isAt(getUri(), "recordSchema")) {
	            	recordSchema = ctx.parseElementText(
	            			getUri(), "recordSchema");
	            }
	            else if (ctx.isAt(getUri(), "recordPacking")) {
	            	packing = ctx.parseElementText(getUri(), "recordPacking");
	            	packing = packing.toLowerCase();
	            }
	            else if (ctx.isAt(getUri(), "recordData")) {
	            	
	                ctx.parsePastStartTag(getUri(), "recordData");
	                
	                if(RecordPacking.xml.name().equals(packing)) {
	                	/** 
	                	 * Performance increase: Map content as string and not 
	                	 * as DOM, because we have to pass a XML-string to the
	                	 * Marshaller for the recordData. Therefore we do not
	                	 * need to convert the DOM to string in this case.
	                	 * 
	                	 * Using DOMMapper:
	                	 * 
	                	 * RecordData > XML2DOM > DOM2String > Unmarshal
	                	 * 
	                	 * Using content as String:
	                	 * 
	                	 * RecordData > XML2String > Unmarshal
	                	 */
	                	dataText = getContentOfElementAsXml(ctx, "recordData");
	                	
	                } else if (RecordPacking.string.name().equals(packing)) {
	                	dataText = ctx.parseContentText();
	                }
	                
	                ctx.parsePastEndTag(getUri(), "recordData");
	            }
	            else if (ctx.isAt(getUri(), "recordPosition")) {
	            	recordPosition = ctx.parseElementText(
	            			getUri(), "recordPosition");
	            } else {
	                break;
	            }
	        }
	        
	        result = getRecord(recordSchema, 
	        		new Integer(recordPosition).intValue(),
	        		packing, dataText, dataDOM,
	        		TransportProtocol.valueOf(
	        				ctx.getFactory().getBindingName()));
	        
        } catch (Exception e) {
        	throw new JiBXException(e.getMessage(), e);
        }
        ctx.parsePastEndTag(getUri(), getName());
        
    	return result;
    }
    
    /**
     * TODO: Maybe also use namespace to recognize the current tag.
     * 
     * @param recordSchema
     * @param recordPosition
     * @param packing
     * @param dataText
     * @param dataDOM
     * @return
     */
    private Object getRecord(String recordSchema, int recordPosition, 
    		String packing, String dataText, Element dataDOM, 
    		TransportProtocol transport) {
    	
    	String tagName = null;
    	
    	if(dataText != null) {
    		Matcher m = tagNameNS.matcher(dataText);
    		if(m.find()) {
    			tagName = m.group(1);
    		}	        
        } else if(dataDOM != null) {
        	/**
        	 * This case has become redundant since we are always mapping the 
        	 * content of recordData to String.
        	 */
        	tagName = dataDOM.getNodeName(); 	
        }
    	
    	// remove NS prefix if exists
    	tagName = tagName.substring(tagName.indexOf(':')+1);
    	
    	if(RecordDataTag.SearchResultRecord.equals(tagName))
    		return new SearchResultRecordRecord(recordSchema, packing,
					recordPosition, dataDOM, dataText, transport);
		else if(RecordDataTag.Item.equals(tagName))
			return new ItemRecord(recordSchema, packing, 
    				recordPosition, dataDOM, dataText, transport);
		else if(RecordDataTag.Container.equals(tagName))
			return new ContainerRecord(recordSchema, packing, 
					recordPosition, dataDOM, dataText, transport);
		else if(RecordDataTag.OrganizationalUnit.equals(tagName))
			return new OrganizationalUnitRecord(recordSchema, packing, 
					recordPosition, dataDOM, dataText, transport);
		else if(RecordDataTag.Context.equals(tagName))
			return new ContextRecord(recordSchema, packing, 
					recordPosition, dataDOM, dataText, transport);
		else if(RecordDataTag.ContentRelation.equals(tagName))
			return new ContentRelationRecord(recordSchema, packing, 
					recordPosition, dataDOM, dataText, transport);
		else if(RecordDataTag.Role.equals(tagName))
			return new RoleRecord(recordSchema, packing, 
					recordPosition, dataDOM, dataText, transport);
		else if(RecordDataTag.UserAccount.equals(tagName))
			return new UserAccountRecord(recordSchema, packing, 
					recordPosition, dataDOM, dataText, transport);
		
    	/**
    	 * If we are unable to guess the type of the content return a default 
    	 * record.
    	 */
    	return new DefaultRecord(recordSchema, packing, recordPosition, dataDOM, 
    			dataText, transport);
    }
}