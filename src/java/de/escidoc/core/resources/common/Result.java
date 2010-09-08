package de.escidoc.core.resources.common;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import de.escidoc.core.client.exceptions.EscidocClientException;
import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.client.exceptions.TransportException;
import de.escidoc.core.common.XmlUtility;

/**
 * Result (result.xsd).
 * 
 * @author SWA
 * 
 */
public class Result {

    private static final Logger LOGGER = Logger.getLogger(Result.class);

    private DateTime lmd = null;

    private String param = null;

    private Element pidParam = null;

    /**
     * See Interface for functional description.
     * 
     * @return last modification date
     * @throws InternalClientException
     * @throws TransportException
     * @see de.escidoc.core.client.interfaces.ResultInterface#getLastModificationDate()
     */
    public DateTime getLastModificationDate() throws EscidocClientException,
        InternalClientException, TransportException {

        return this.lmd;
    }

    /**
     * Set the Last modification date of the result.
     * 
     * @param timestamp
     *            last modification date
     * @throws EscidocClientException
     * @throws InternalClientException
     * @throws TransportException
     */
    public void setLastModificationDate(final DateTime timestamp)
        throws EscidocClientException, InternalClientException,
        TransportException {

        this.lmd = timestamp;
    }

    /**
     * See Interface for functional description.
     * 
     * @return
     * @throws InternalClientException
     * @throws TransportException
     * @see de.escidoc.core.client.interfaces.ResultInterface#getParam()
     */
    public String getParam() throws EscidocClientException,
        InternalClientException, TransportException {

        return this.param;
    }

    /**
     * See Interface for functional description.
     * 
     * @param content
     *            The content of the result element.
     * @return
     * @throws InternalClientException
     * @throws TransportException
     * @see de.escidoc.core.client.interfaces.ResultInterface#getParam()
     */
    public void setParam(final String content) throws EscidocClientException,
        InternalClientException, TransportException {

        this.param = content;
    }

    /**
     * Get PID parameter.
     * 
     */
    public Element getPidParam() throws EscidocClientException,
        InternalClientException, TransportException {

        return this.pidParam;
    }

    /**
     * Set PID parameter.
     * 
     * @param pidParam
     * @throws EscidocClientException
     * @throws InternalClientException
     * @throws TransportException
     */
    public void setPidParam(final Element pidParam)
        throws EscidocClientException, InternalClientException,
        TransportException {

        this.pidParam = pidParam;
    }

    /**
     * Get PID parameter.
     * 
     */
    public String getPidParamAsString() throws EscidocClientException,
        InternalClientException, TransportException {

        String pidParam = null;
        
        try {
			pidParam = XmlUtility.xmlToString(this.pidParam, true);
		} catch (TransformerException e) {
			LOGGER.debug(e);
            throw new InternalClientException(e);
		}
        return pidParam;
    }

    /**
     * Set the content of result if element is "pid".
     * 
     * @param pidParam
     * @throws EscidocClientException
     * @throws InternalClientException
     * @throws TransportException
     */
    public void setPidParamAsString(final String pidParam)
        throws EscidocClientException, InternalClientException,
        TransportException {

        if (pidParam == null) {
            this.pidParam = null;
            return;
        }

        try {
            this.pidParam =
                XmlUtility.getDocument(pidParam).getDocumentElement();
        }
        catch (UnsupportedEncodingException e) {
            LOGGER.debug(e);
            throw new InternalClientException(e);
        }
        catch (ParserConfigurationException e) {
            LOGGER.debug(e);
            throw new InternalClientException(e);
        }
        catch (SAXException e) {
            LOGGER.debug(e);
            throw new InternalClientException(e);
        }
        catch (IOException e) {
            LOGGER.debug(e);
            throw new InternalClientException(e);
        }
    }

}
