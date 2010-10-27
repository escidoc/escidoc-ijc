/**
 * 
 */
package de.escidoc.core.client.interfaces;

import gov.loc.www.zing.srw.ExplainRequestType;
import gov.loc.www.zing.srw.ScanRequestType;
import gov.loc.www.zing.srw.SearchRetrieveRequestType;

import java.io.UnsupportedEncodingException;
import java.rmi.RemoteException;

/**
 * @author MVO
 *
 */
public interface SearchHandler {
	
	/**
	 * 
	 * @param explainRequestType
	 * @param database
	 * @return
	 * @throws RemoteException
	 */
	String explain(final ExplainRequestType explainRequestType)
		throws RemoteException;
	
	/**
	 * 
	 * @param searchRequestType
	 * @param database
	 * @return
	 * @throws RemoteException
	 */
	String search(final SearchRetrieveRequestType searchRequestType)
		throws RemoteException, UnsupportedEncodingException;
	
	/**
	 * 
	 * @param scanRequestType
	 * @param database
	 * @return
	 * @throws RemoteException
	 */
	String scan(final ScanRequestType scanRequestType)
		throws RemoteException, UnsupportedEncodingException;
}
