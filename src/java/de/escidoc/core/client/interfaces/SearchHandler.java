/**
 * 
 */
package de.escidoc.core.client.interfaces;

import gov.loc.www.zing.srw.ExplainRequestType;
import gov.loc.www.zing.srw.ScanRequestType;
import gov.loc.www.zing.srw.SearchRetrieveRequestType;

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
	public String explain(final ExplainRequestType explainRequestType)
		throws RemoteException;
	
	/**
	 * 
	 * @param searchRequestType
	 * @param database
	 * @return
	 * @throws RemoteException
	 */
	public String search(final SearchRetrieveRequestType searchRequestType)
		throws RemoteException;
	
	/**
	 * 
	 * @param scanRequestType
	 * @param database
	 * @return
	 * @throws RemoteException
	 */
	public String scan(final ScanRequestType scanRequestType)
		throws RemoteException;
}
