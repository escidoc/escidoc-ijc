package de.escidoc.core.test.client.classMapping.aa.pdp;

import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import de.escidoc.core.client.PolicyDecisionPointHandlerClient;
import de.escidoc.core.common.jibx.Factory;
import de.escidoc.core.common.jibx.Marshaller;
import de.escidoc.core.resources.aa.pdp.Requests;
import de.escidoc.core.resources.aa.pdp.RequestsResults;
import de.escidoc.core.test.client.EscidocClientTestBase;

public class PdpHandlerClientTest extends EscidocClientTestBase {

    private final Logger logger =
        Logger.getLogger(PdpHandlerClientTest.class.getName());

    /**
     * Test to create and retrieve user account.
     * 
     * @throws Exception
     *             Thrown if anythings failed.
     */
    public void testeEvaluateRequests() throws Exception {

        PolicyDecisionPointHandlerClient pdpc = new PolicyDecisionPointHandlerClient();
        pdpc.setHandle(EscidocClientTestBase.DEFAULT_HANDLE);
        Requests requests = createRequests();
        RequestsResults results = pdpc.evaluate(requests);
        

        String xml =
            Factory.getRequestsResultsMarshaller().marshalDocument(results
                );
        System.out.println(" results " + xml);

    }
    
    

    public Requests createRequests() throws Exception {
        Requests requests = new Requests();
        
        InputStream input = getClass().getResourceAsStream("request1.xml");
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(input);
        Element root1 = doc.getDocumentElement();
        requests.addRequest(root1);
        
        input = getClass().getResourceAsStream("request2.xml");
        Document doc2 = builder.parse(input);
        Element root2 = doc2.getDocumentElement();
        requests.addRequest(root2);
        
        input = getClass().getResourceAsStream("request3.xml");
        Document doc3 = builder.parse(input);
        Element root3 = doc3.getDocumentElement();
        requests.addRequest(root3);
        
        input = getClass().getResourceAsStream("request4.xml");
        Document doc4 = builder.parse(input);
        Element root4 = doc4.getDocumentElement();
        requests.addRequest(root4);
        
        input = getClass().getResourceAsStream("request5.xml");
        Document doc5 = builder.parse(input);
        Element root5 = doc5.getDocumentElement();
        requests.addRequest(root5);
        
        input = getClass().getResourceAsStream("request6.xml");
        Document doc6 = builder.parse(input);
        Element root6 = doc6.getDocumentElement();
        requests.addRequest(root6);
        
        Marshaller<Requests> m = new Marshaller<Requests>(requests.getClass());
        String xml = m.marshalDocument(requests);
        System.out.println(xml);

        Requests urequests = m.unmarshalDocument(xml);
        String requestsXml =
            Factory.getRequestsMarshaller().marshalDocument(
                urequests);
        System.out.println("requests" + requestsXml);
        return requests;

    }

   
}
