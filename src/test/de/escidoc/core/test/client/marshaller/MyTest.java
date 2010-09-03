package de.escidoc.core.test.client.marshaller;

import gov.loc.www.zing.srw.ExplainRequestType;
import gov.loc.www.zing.srw.ScanRequestType;
import gov.loc.www.zing.srw.SearchRetrieveRequestType;

import java.net.URLEncoder;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.jibx.extras.BindingSelector;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import de.escidoc.core.client.ItemHandlerClient;
import de.escidoc.core.client.SearchHandlerClient;
import de.escidoc.core.client.TransportProtocol;
import de.escidoc.core.common.jibx.Marshaller;
import de.escidoc.core.resources.om.item.Item;
import de.escidoc.core.resources.sb.Record;
import de.escidoc.core.resources.sb.Record.RecordPacking;
import de.escidoc.core.resources.sb.explain.ExplainData;
import de.escidoc.core.resources.sb.explain.ExplainResponse;
import de.escidoc.core.resources.sb.scan.ScanResponse;
import de.escidoc.core.resources.sb.search.SearchResultRecord;
import de.escidoc.core.resources.sb.search.SearchRetrieveRecord;
import de.escidoc.core.resources.sb.search.SearchRetrieveResponse;
import de.escidoc.core.resources.sb.srw.SearchRetrieveResponseType;
import de.escidoc.core.resources.sb.wrapper.search.SearchResponse;

@SuppressWarnings({ "rawtypes", "unused" })
@RunWith(Parameterized.class)
public class MyTest extends TestCase {

	private TransportProtocol protocol;
	private RecordPacking packing;
	
	private static final StringBuilder out = new StringBuilder(); 

	public MyTest(TransportProtocol protocol, RecordPacking packing) {
		this.protocol = protocol;
		this.packing = packing;
	}

	@Parameters
	public static Collection data() {
		return Arrays.asList(new Object[][] {
				{ TransportProtocol.REST, RecordPacking.xml },
				{ TransportProtocol.REST, RecordPacking.string },
				{ TransportProtocol.SOAP, RecordPacking.xml },
				{ TransportProtocol.SOAP, RecordPacking.string } });
	}

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}
	
	@AfterClass
	public static void afterClass() throws Exception {
		System.out.println(out.toString());
	}

	/**
	 * 
	 * @throws Exception
	 */
	@Test
	public void testSRWExplain() throws Exception {

		SearchHandlerClient c = new SearchHandlerClient();
		c.setTransport(protocol);

		ExplainRequestType request = new ExplainRequestType();
		request.setRecordPacking(packing.name());
		request.setVersion("1.1");

		ExplainResponse response = c.explain2(request, null);
		Record<ExplainData> record = response.getRecord();
		ExplainData data = record.getRecordData();

		out.append("testSRWExplain: ");
		out.append("Protocol: "+protocol.name()+
				", RecordPacking: "+packing+"\n");
		data.toString(out);
		out.append("\n");
		
		Assert.assertNotNull(data);
	}
	
	/**
	 * 
	 * @throws Exception
	 */
	@Test
	public void testFilterExplain() throws Exception {

		ItemHandlerClient c = new ItemHandlerClient();
		c.setTransport(protocol);

		ExplainRequestType request = new ExplainRequestType();
		request.setVersion("1.1");
		request.setRecordPacking(packing.name());

		ExplainResponse response = c.retrieveItems(request);
		Record<ExplainData> record = response.getRecord();
		ExplainData data = record.getRecordData();

		out.append("testFilterExplain: ");
		out.append("Protocol: "+protocol.name()+
				", RecordPacking: "+packing+"\n");
		data.toString(out);
		out.append("\n");
		
		Assert.assertNotNull(data);
	}

	/**
	 * 
	 * @throws Exception
	 */
	@Test
	public void testSRWSearch() throws Exception {

		SearchHandlerClient c = new SearchHandlerClient();
		c.setTransport(protocol);
		String query = "escidoc.metadata=escidoc*";

		SearchRetrieveResponse response = c.search(URLEncoder.encode(
				query, "UTF-8"), null);
		
		out.append("testSRWSearch: query="+query);
		out.append(" [Protocol: "+protocol.name()+
				", RecordPacking: "+packing+"]\n");
		//data.toString(out);
		out.append("Results: "+response.getNumberOfRecords()+"\n");
		
		/*
		Collection<SearchRetrieveRecord> records = response.getRecords();
        for (Iterator<SearchRetrieveRecord> it = records.iterator(); it.hasNext();) {
			SearchRetrieveRecord searchRetrieveRecord = it.next();
			SearchResultRecord record = searchRetrieveRecord.getRecordData();
			
		}
		*/
	}
	
	/**
	 * 
	 * @throws Exception
	 */
	@Test
	public void testFilterSearch() throws Exception {

		ItemHandlerClient c = new ItemHandlerClient();
		c.setTransport(protocol);
		String query = "\"/properties/content-model/id\"=escidoc:ex4";

		SearchRetrieveRequestType request = new SearchRetrieveRequestType();
		request.setVersion("1.1");
//		request.setQuery("\"/id\"=escidoc:1004");
		request.setQuery(query);
		request.setRecordPacking(packing.name());

		SearchRetrieveResponse response = c.retrieveItems(request);
		for (Iterator it = response.getRecords().iterator(); it.hasNext();) {
			Record<Item> record = (Record<Item>) it.next();
			System.out.println(record.getRecordDataText());
		}
		
		out.append("testFilterSearch: query="+query);
		out.append(" [Protocol: "+protocol.name()+
				", RecordPacking: "+packing+"]\n");
		//data.toString(out);
		out.append("Results: "+response.getNumberOfRecords()+"\n");
	}
	
	/**
	 * 
	 * @throws Exception
	 */
	// @Test
	public void testSearchScanREST() throws Exception {

		SearchHandlerClient c = new SearchHandlerClient();
		c.setTransport(TransportProtocol.REST);

		ScanRequestType request = new ScanRequestType();
		request.setVersion("1.1");
		request.setScanClause("escidoc.metadata=escidoc");

		ScanResponse response = c.scan(request, null);
	}

	/**
	 * 
	 * @throws Exception
	 */
	// @Test
	public void testSearchScanSOAP() throws Exception {

		SearchHandlerClient c = new SearchHandlerClient();
		c.setTransport(TransportProtocol.SOAP);

		ScanRequestType request = new ScanRequestType();
		request.setVersion("1.1");
		request.setScanClause("escidoc.metadata=escidoc");

		ScanResponse response = c.scan(request, null);
	}

	/**
	 * ITEM
	 */

	/**
	 * 
	 * @throws Exception
	 */
	// @Test
	public void testItemFilterExplainREST() throws Exception {

		ItemHandlerClient c = new ItemHandlerClient();
		c.setTransport(TransportProtocol.REST);

		ExplainRequestType request = new ExplainRequestType();
		request.setVersion("1.1");

		ExplainResponse response = c.retrieveItems(request);
	}

	/**
	 * 
	 * @throws Exception
	 */
	// @Test
	public void testItemFilterExplainSOAP() throws Exception {

		ItemHandlerClient c = new ItemHandlerClient();
		c.setTransport(TransportProtocol.SOAP);

		ExplainRequestType request = new ExplainRequestType();
		request.setVersion("1.1");

		ExplainResponse response = c.retrieveItems(request);
	}

	/**
	 * 
	 * @throws Exception
	 */
	// @Test
	public void testItemFilterSearchSOAP() throws Exception {

		ItemHandlerClient c = new ItemHandlerClient();
		c.setTransport(TransportProtocol.SOAP);

		SearchRetrieveRequestType request = new SearchRetrieveRequestType();
		request.setVersion("1.1");
		request.setQuery("\"/id\"=escidoc:1004");

		//SearchRetrieveResponseType response = c.retrieveItems(request);

		BindingSelector selector = new BindingSelector(null, "version",
				new String[] {}, new String[] {});
	}
}
