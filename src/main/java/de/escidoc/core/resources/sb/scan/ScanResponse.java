/**
 * 
 */
package de.escidoc.core.resources.sb.scan;

import java.util.Collection;
import java.util.LinkedList;

import gov.loc.www.zing.srw.ScanResponseType;
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

        // FIXME compilation error
/*        
        if (type.getTerms() != null) {
            for (int i = 0; i < type.getTerms().length; i++) {
                terms.add(new Term(type.getTerms()[i]));
            }
        }
*/        
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
