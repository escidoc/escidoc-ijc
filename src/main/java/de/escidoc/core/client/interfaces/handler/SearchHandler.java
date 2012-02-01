/**
 * 
 */
package de.escidoc.core.client.interfaces.handler;

import gov.loc.www.zing.srw.ExplainRequestType;
import gov.loc.www.zing.srw.ScanRequestType;
import gov.loc.www.zing.srw.SearchRetrieveRequestType;

import java.rmi.Remote;

import de.escidoc.core.client.exceptions.EscidocException;
import de.escidoc.core.client.exceptions.InternalClientException;

/**
 * @author MVO
 * 
 */
public interface SearchHandler extends Remote {

    /**
     * 
     * @param explainRequestType
     * @param database
     * @return
     */
    String explain(final ExplainRequestType explainRequestType) throws EscidocException;

    /**
     * 
     * @param searchRequestType
     * @param database
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     */
    String search(final SearchRetrieveRequestType searchRequestType) throws EscidocException, InternalClientException;

    /**
     * 
     * @param scanRequestType
     * @param database
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     */
    String scan(final ScanRequestType scanRequestType) throws EscidocException, InternalClientException;
}
