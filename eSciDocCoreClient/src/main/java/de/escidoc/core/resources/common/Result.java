package de.escidoc.core.resources.common;

import java.util.LinkedList;

import org.joda.time.DateTime;
import org.w3c.dom.Element;

import de.escidoc.core.common.DateTimeUtility;

/**
 * Result (result.xsd).
 * 
 * @author SWA
 * 
 */
public class Result extends LinkedList<Element> {

    /**
     * 
     */
    private static final long serialVersionUID = 6147210897291035923L;

    // private static final Logger LOG = LoggerFactory.getLogger(Result.class);

    private DateTime lmd;

    /**
     * See Interface for functional description.
     * 
     * @see de.escidoc.core.client.interfaces.ResultInterface#getLastModificationDate()
     */
    public DateTime getLastModificationDate() {
        return this.lmd;
    }

    /**
     * Set the Last modification date of the result.
     * 
     * @param timestamp
     *            last modification date
     */
    public void setLastModificationDate(final DateTime timestamp) {
        this.lmd = DateTimeUtility.normalize(timestamp);
    }
}
