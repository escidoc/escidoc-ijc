/**
 * 
 */
package de.escidoc.core.resources.sb.explain;

import de.escidoc.core.resources.sb.Record;
import de.escidoc.core.resources.sb.Response;

/**
 * Read-only class.
 * 
 * This class is a representation of the response of an explain request.
 * 
 * This class may be initialized either by a SOAP response instance or by JiBX
 * if and only if the REST protocol is being used for the explain request.
 * 
 * @author MVO
 * 
 */
public class ExplainResponse extends Response {

    private ExplainRecord record;

    /**
     * Constructor for REST response.
     */
    protected ExplainResponse() {
        super();
    }

    /**
     * Convenience method to directly access data section of the record.
     * 
     * @return
     */
    public Record<Explain> getRecord() {
        return this.record;
    }
}
