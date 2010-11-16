package de.escidoc.core.resources.sb;

import gov.loc.www.zing.srw.RecordType;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.axis.utils.XMLUtils;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import de.escidoc.core.annotations.JiBX;
import de.escidoc.core.client.TransportProtocol;
import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.common.XmlUtility;

/**
 * 
 * 
 * @author SWA, MVO
 * 
 */
@JiBX
public abstract class Record<T> {

    protected static final Logger LOG = Logger.getLogger(Record.class);

    public static enum RecordPacking {
        string, xml
    };

    protected String recordSchema;

    protected String recordPacking = "";

    protected int recordPosition;

    protected Element recordDataDOM;

    protected String recordDataText;

    protected TransportProtocol transport;

    protected static final String xmlHeader =
        "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";

    @JiBX
    protected Record() {
        this.transport = TransportProtocol.REST;
    }

    /**
     * @param recordSchema
     * @param recordPacking
     * @param recordPosition
     * @param recordDataDOM
     * @param recordDataText
     * @param transport
     */
    protected Record(final String recordSchema, final String recordPacking,
        final int recordPosition, final Element recordDataDOM,
        final String recordDataText, final TransportProtocol transport) {

        if (transport == null)
            throw new IllegalArgumentException("protocol must not be null.");

        this.recordSchema = recordSchema;
        this.recordPacking = recordPacking;
        this.recordPosition = recordPosition;
        this.recordDataDOM = recordDataDOM;
        this.recordDataText = recordDataText;
        this.transport = transport;
    }

    /**
     * Constructor for SOAP response.
     * 
     * @throws InternalClientException
     */
    protected Record(final RecordType axisRecord) {

        this.recordSchema = axisRecord.getRecordSchema();
        if (axisRecord.getRecordPacking() != null)
            this.recordPacking = axisRecord.getRecordPacking();
        if (axisRecord.getRecordPosition() != null)
            this.recordPosition = axisRecord.getRecordPosition().intValue();

        try {

            if (RecordPacking.xml.name().equals(getRecordPacking())) {
                this.recordDataDOM =
                    axisRecord.getRecordData().get_any()[0].getAsDOM();

            }
            else if (RecordPacking.string.name().equals(getRecordPacking())) {
                this.recordDataText =
                    XmlUtility.unescapeForbiddenXmlCharacters(axisRecord
                        .getRecordData().get_any()[0].getAsString());
            }

        }
        catch (Exception e) {
            LOG.debug("Unable to get RecordData.", e);
        }
        this.transport = TransportProtocol.SOAP;
    }

    /**
     * @return the recordDataDOM
     */
    public Element getRecordDataDOM() {
        return recordDataDOM;
    }

    /**
     * 
     * @return
     */
    public boolean hasRecordDataDOM() {
        return recordDataDOM != null;
    }

    /**
     * @return the recordDataText
     */
    public String getRecordDataText() {
        return recordDataText;
    }

    /**
     * 
     * @param ignoreWhitespaceCharacters
     * @return
     */
    public boolean hasRecordDataText(final boolean ignoreWhitespaceCharacters) {
        if (ignoreWhitespaceCharacters) {
            return (recordDataText == null) ? false : (recordDataText
                .replaceAll("[\\s]", "").length() != 0);
        }
        return recordDataText != null;
    }

    /**
     * @return the protocol
     */
    public TransportProtocol getProtocol() {
        return transport;
    }

    /**
     * @return
     */
    public String getRecordSchema() {
        return recordSchema;
    }

    /**
     * @return
     */
    public String getRecordPacking() {
        return recordPacking.toLowerCase();
    }

    /**
     * @return
     */
    public int getRecordPosition() {
        return recordPosition;
    }

    /**
     * 
     * @return
     */
    public T getRecordData() {
        // XML structure exists
        if (hasRecordDataDOM()) {

            T t = decodeFragmentXML();
            return (t == null) ? getDefaultInstance() : t;
        }
        else if (hasRecordDataText(true)) {

            T t = decodeFragmentString();
            return (t == null) ? getDefaultInstance() : t;
        }
        return getDefaultInstance();
    }

    protected abstract T decodeFragmentXML();

    protected abstract T decodeFragmentString();

    protected abstract T getDefaultInstance();

    /**
     * Convenience method to create a DOM for the text representation of the
     * recordData. Check for hasRecordData() and hasRecordDataText(true) before
     * you might use this method.
     * 
     * @return
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     */
    public Element getRecordDataTextAsDom()
        throws ParserConfigurationException, SAXException, IOException {
        Document doc =
            XMLUtils.newDocument(new InputSource(new StringReader(
                getRecordDataText())));
        return doc.getDocumentElement();
    }

    /**
     * Convenience method to create a String out of the DOM representation of
     * the recordData. Check for hasRecordData() and hasRecordDataText(true)
     * before you might use this method.
     * 
     * @return
     * @throws TransformerException
     */
    public String getRecordDataDOMAsString() throws TransformerException {
        StringWriter sw = new StringWriter();
        Transformer t = TransformerFactory.newInstance().newTransformer();
        t.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
        t.transform(new DOMSource(getRecordDataDOM()), new StreamResult(sw));
        return sw.toString();
    }
}
