/**
 * 
 */
package de.escidoc.core.resources.sb.scan;

import gov.loc.www.zing.srw.ScanResponseType;

import java.util.Collection;
import java.util.LinkedList;

import de.escidoc.core.resources.sb.Response;

/**
 * @author MVO
 * 
 */
public class ScanResponse extends Response {

    private Collection<Term> terms = new LinkedList<Term>();

    /**
     * 
     */
    protected ScanResponse() {
        super();
    }

    /**
     * 
     * @param type
     */
    protected ScanResponse(ScanResponseType type) {
        super(type.getVersion());

        if (type.getTerms() != null && type.getTerms().getTerm() != null) {
            for (int i = 0; i < type.getTerms().getTerm().length; i++) {
                terms.add(new Term(type.getTerms().getTerm(i)));
            }
        }
    }

    /**
     * @return the terms
     */
    public Collection<Term> getTerms() {
        return terms;
    }

    /**
     * 
     * @param type
     * @return
     */
    public static final ScanResponse createScanResponse(ScanResponseType type) {
        return new ScanResponse(type);
    }
}
