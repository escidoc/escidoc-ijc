package de.escidoc.core.resources.sb.explain;

import gov.loc.www.zing.srw.RecordType;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.jibx.runtime.BindingDirectory;
import org.jibx.runtime.IBindingFactory;
import org.jibx.runtime.JiBXException;
import org.jibx.runtime.impl.UnmarshallingContext;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import de.escidoc.core.client.TransportProtocol;
import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.resources.sb.Record;

public class ExplainRecord extends Record<ExplainData> {

    private ExplainData resultData;

    /**
	 * 
	 */
    public ExplainRecord() {

    }

    /**
     * @param axisRecord
     * @param type
     * @throws InternalClientException
     */
    public ExplainRecord(final RecordType axisRecord)
        throws InternalClientException {
        super(axisRecord);
    }

    /**
     * <b>FIXME</b>: Fix this "bug" in infrastructure.
     * 
     * The response of a SOAP explain request differs between SOAP request as
     * XML and SOAP request as string. In the first case, we get an explain-tag
     * with prefix ns2.
     */
    @Override
    protected ExplainData parseFragmentDOM() {
        if (this.resultData != null)
            return this.resultData;
        else {
            try {
                StringWriter sw = new StringWriter();
                Transformer t =
                    TransformerFactory.newInstance().newTransformer();
                t.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
                // remove ns prefix if exists
                removePrefix(getRecordDataDOM(), getRecordDataDOM().getPrefix());
                t.transform(new DOMSource(getRecordDataDOM()),
                    new StreamResult(sw));

                return decodeFragment(sw.toString());

            }
            catch (TransformerException te) {
                return null;
            }
            catch (JiBXException e) {
                // ignore
                LOG.debug(e.getMessage(), e);
            }
        }
        return null;
    }

    /**
     * This methods removes the prefixes used in the explain response DOM.
     * 
     * @see decodeFragmentXML()
     * @param element
     */
    private void removePrefix(final Element element, final String prefix) {
        if (element == null || prefix == null || prefix.length() == 0)
            return;

        if (prefix.equals(element.getPrefix()))
            element.setPrefix("");

        for (int i = 0; i < element.getChildNodes().getLength(); i++) {
            Node node = element.getChildNodes().item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                removePrefix((Element) node, prefix);
            }
        }
    }

    @Override
    protected ExplainData parseFragmentText() {
        if (resultData != null)
            return resultData;
        else {
            try {
                return decodeFragment(getRecordDataText());
            }
            catch (JiBXException e) {
                // ignore
                LOG.debug(e.getMessage(), e);
            }
        }
        return null;
    }

    /**
     * <b>FIXME</b>: Fix this "bug" in infrastructure and use a normal
     * Marshaller here.
     * 
     * When putting up an explain request, we get the same explain response for
     * search and filter requests, containing the same recordData of the
     * explain-tag BUT using different namespaces.
     * 
     * JiBX cannot handle binding definitions of the same mapping name for the
     * same class using different namespaces. Therefore set the
     * UnmashallingContext to ignore the namespaces. The option of disabling
     * namespaces should be considered experimental and may not be supported in
     * the future by JiBX.
     * 
     * @param xml
     * @return
     * @throws JiBXException
     */
    private ExplainData decodeFragment(String xml) throws JiBXException {

        xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + xml;

        IBindingFactory bfact =
            BindingDirectory.getFactory(TransportProtocol.REST.name(),
                ExplainData.class);
        UnmarshallingContext uctx =
            (UnmarshallingContext) bfact.createUnmarshallingContext();
        uctx.setDocument(new StringReader(xml), "UTF-8", false);

        this.resultData = (ExplainData) uctx.unmarshalElement();
        return this.resultData;
    }

    /**
     * If mapping of recordData or recordDataText fails, do not return an empty
     * ExplainData instance. Return null instead to imply, that the content may
     * be usable by accessing the content of recordData or recordDataString
     * directly and evaluating this on your own.
     */
    @Override
    protected ExplainData getDefaultInstance() {
        return null;
    }

}
