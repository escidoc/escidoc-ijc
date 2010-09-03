/**
 * 
 */
package de.escidoc.core.common.jibx.binding;

import java.util.regex.Pattern;

import org.jibx.extras.DomElementMapper;
import org.jibx.runtime.IAliasable;
import org.jibx.runtime.IMarshaller;
import org.jibx.runtime.IMarshallingContext;
import org.jibx.runtime.IUnmarshaller;
import org.jibx.runtime.IUnmarshallingContext;
import org.jibx.runtime.JiBXException;
import org.jibx.runtime.impl.UnmarshallingContext;
import org.w3c.dom.Element;

import de.escidoc.core.client.TransportProtocol;
import de.escidoc.core.resources.om.item.Item;
import de.escidoc.core.resources.sb.Record;
import de.escidoc.core.resources.sb.Record.RecordPacking;
import de.escidoc.core.resources.sb.search.SearchResultRecord;

/**
 * @author MVO
 *
 */
public class RecordDataMarshaller extends MarshallingBase
    implements IMarshaller, IUnmarshaller, IAliasable {

	private static final Pattern RD_SEARCH_RESULT_RECORD = Pattern.compile(
		"^<[^>]*?[:]?search-result-record[^>]+?>");
	private static final Pattern RD_ITEM = Pattern.compile(
	"^<[^>]*?[:]?item[^>]+?>");
	
    /**
     * 
     * @param uri
     * @param index
     * @param name
     */
    public RecordDataMarshaller(final String uri, final int index,
        final String name) {

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
    	
    	ctx.parsePastStartTag(getUri(), getName());
    	
    	String recordSchema = null, recordPosition = null, packing = null;
    	String dataText = null;
    	Element dataDOM = null;
    	Object result = null;
    	
    	try {
	        while (true) {
	            if (ctx.isAt(getUri(), "recordSchema")) {
	            	recordSchema = ctx.parseElementText(getUri(), "recordSchema");
	            }
	            else if (ctx.isAt(getUri(), "recordPacking")) {
	            	packing = ctx.parseElementText(getUri(), "recordPacking");
	            	packing = packing.toLowerCase();
	            }
	            else if (ctx.isAt(getUri(), "recordData")) {
	            	
	                ctx.parsePastStartTag(getUri(), "recordData");
	                
	                if(RecordPacking.xml.name().equals(packing)) {
	                	
	                	DomElementMapper mapper = new DomElementMapper();
	                	dataDOM = (Element) mapper.unmarshal(Element.class, ctx);
	                	
	                } else if (RecordPacking.string.name().equals(packing)) {
	                	
	                	dataText = ctx.parseContentText();
	                }
	                
	                ctx.parsePastEndTag(getUri(), "recordData");
	            }
	            else if (ctx.isAt(getUri(), "recordPosition")) {
	            	recordPosition = ctx.parseElementText(getUri(), "recordPosition");
	            } else {
	                break;
	            }
	        }
	        
	        result = getRecord(recordSchema, 
	        		new Integer(recordPosition).intValue(),
	        		packing, dataText, dataDOM);
	        
        } catch (Exception e) {
        	throw new JiBXException(e.getMessage(), e);
        }
        ctx.parsePastEndTag(getUri(), getName());
        
    	return result;
    }
    
    /**
     * 
     * @param recordSchema
     * @param recordPosition
     * @param packing
     * @param dataText
     * @param dataDOM
     * @return
     */
    private Object getRecord(String recordSchema, int recordPosition, 
    		String packing, String dataText, Element dataDOM) {
    	
    	if(dataText != null) {
	        
        	if(RD_SEARCH_RESULT_RECORD.matcher(dataText).find()) {
        		return createSearchResultRecordRD(recordSchema, packing, 
        				recordPosition, null, dataText, null);
        	} else if(RD_ITEM.matcher(dataText).find()) {
        		return createItemRD(recordSchema, packing, 
        				recordPosition, null, dataText, null);
        	} // TODO etc...
	        
        } else if(dataDOM != null) {
        	// TODO        	
        }
    	
    	/**
    	 * return default record, if we are unable to guess the type of the
    	 * content.
    	 */
    	return createObjectRD(recordSchema, packing, recordPosition, dataDOM, 
    			dataText, null);
    }
    
    /**
     * 
     * @param recordSchema
     * @param recordPacking
     * @param recordPosition
     * @param recordDataDOM
     * @param recordDataText
     * @param protocol
     * @return
     */
    private Record<Object> createObjectRD(final String recordSchema, final String recordPacking,
    		final int recordPosition, final Element recordDataDOM, 
    		final String recordDataText, final TransportProtocol protocol) {
    	
    	return new Record<Object>(recordSchema, recordPacking, recordPosition, 
    			recordDataDOM, recordDataText, protocol) {

					protected Object decodeFragmentXML() {
						return null;
					}

					@Override
					protected Object decodeFragmentString() {
						return null;
					}

					@Override
					protected Object getDefaultInstance() {
						return null;
					}
    	};
    }
    
    
    /**
     * 
     * @param recordSchema
     * @param recordPacking
     * @param recordPosition
     * @param recordDataDOM
     * @param recordDataText
     * @param protocol
     * @return
     */
    private Record<Item> createItemRD(final String recordSchema, 
    		final String recordPacking, final int recordPosition, 
    		final Element recordDataDOM, final String recordDataText, 
    		final TransportProtocol protocol) {
    	
    	return new Record<Item>(recordSchema, recordPacking, recordPosition, 
    			recordDataDOM, recordDataText, protocol) {

					protected Item decodeFragmentXML() {
						return null;
					}

					@Override
					protected Item decodeFragmentString() {
						return null;
					}

					@Override
					protected Item getDefaultInstance() {
						return null;
					}
    	};
	}

	/**
     * 
     * @param recordSchema
     * @param recordPacking
     * @param recordPosition
     * @param recordDataDOM
     * @param recordDataText
     * @param protocol
     * @return
     */
    private Record<SearchResultRecord> createSearchResultRecordRD(
    		final String recordSchema, final String recordPacking,
    		final int recordPosition, final Element recordDataDOM, 
    		final String recordDataText, final TransportProtocol protocol) {
    	
		return new Record<SearchResultRecord>(recordSchema, recordPacking, 
				recordPosition, recordDataDOM, recordDataText, 
	    		protocol) {

			@Override
			protected SearchResultRecord decodeFragmentXML() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			protected SearchResultRecord decodeFragmentString() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			protected SearchResultRecord getDefaultInstance() {
				// TODO Auto-generated method stub
				return null;
			}
		};
	}

	/**
     * Type mapping for unmarshaling sub content.
     * 
     * @param result
     * @param ctx
     * @throws JiBXException 
     */
    private void unmarshalRecordData(Record result, UnmarshallingContext ctx) throws JiBXException {
    	
//    	if (ctx.isAt("http://www.escidoc.de/schemas/item/0.9", "item")) {
//            Item item = (Item) ctx.unmarshalElement();
//            result.setRecordData(item);
//        }
//        else if (ctx.isAt("http://www.escidoc.de/schemas/container/0.8",
//            "container")) {
//            Container container = (Container) ctx.unmarshalElement();
//            result.setRecordData(container);
//        }
//        else if (ctx.isAt(
//            "http://www.escidoc.de/schemas/organizationalunit/0.8",
//            "organizational-unit")) {
//            OrganizationalUnit ou =
//                (OrganizationalUnit) ctx.unmarshalElement();
//            result.setRecordData(ou);
//        }
//        else if (ctx.isAt("http://www.escidoc.de/schemas/context/0.7",
//            "context")) {
//            Context context = (Context) ctx.unmarshalElement();
//            result.setRecordData(context);
//        }
    }
}