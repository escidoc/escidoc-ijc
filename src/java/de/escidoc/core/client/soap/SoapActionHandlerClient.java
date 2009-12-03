/*
 * CDDL HEADER START
 *
 * The contents of this file are subject to the terms of the
 * Common Development and Distribution License, Version 1.0 only
 * (the "License").  You may not use this file except in compliance
 * with the License.
 *
 * You can obtain a copy of the license at license/ESCIDOC.LICENSE
 * or http://www.escidoc.de/license.
 * See the License for the specific language governing permissions
 * and limitations under the License.
 *
 * When distributing Covered Code, include this CDDL HEADER in each
 * file and include the License file at license/ESCIDOC.LICENSE.
 * If applicable, add the following below this CDDL HEADER, with the
 * fields enclosed by brackets "[]" replaced with your own identifying
 * information: Portions Copyright [yyyy] [name of copyright owner]
 *
 * CDDL HEADER END
 */

/*
 * Copyright 2006-2008 Fachinformationszentrum Karlsruhe Gesellschaft
 * fuer wissenschaftlich-technische Information mbH and Max-Planck-
 * Gesellschaft zur Foerderung der Wissenschaft e.V.  
 * All rights reserved.  Use is subject to license terms.
 */
package de.escidoc.core.client.soap;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Logger;

import javax.xml.rpc.ServiceException;

import org.joda.time.DateTime;

import de.escidoc.core.aa.ActionHandler;
import de.escidoc.core.aa.ActionHandlerServiceLocator;
import de.escidoc.core.client.ClientBase;
import de.escidoc.core.client.exceptions.EscidocClientException;
import de.escidoc.core.client.exceptions.EscidocException;
import de.escidoc.core.client.exceptions.ExceptionMapper;
import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.client.exceptions.TransportException;

public class SoapActionHandlerClient extends ClientBase {

	private String serviceAddress = null;

	private final Logger logger = Logger
			.getLogger(SoapActionHandlerClient.class.getName());

	private ActionHandler soapClient = null;

	public SoapActionHandlerClient() throws InternalClientException {

		super();

	}

	/**
	 * 
	 * @param contextId
	 * @param actions
	 * @return
	 * @throws EscidocClientException
	 * @throws InternalClientException
	 * @throws TransportException
	 */
	public String createUnsecuredActions(final String contextId,
			final String actions) throws EscidocClientException,
			InternalClientException, TransportException {
		String result = null;
		try {
			result = getClient().createUnsecuredActions(contextId, actions);
		} catch (Exception e) {
			ExceptionMapper.map(e);
		}
		return result;
	}

	/**
	 * 
	 * @param contextId
	 * @return
	 * @throws EscidocClientException
	 * @throws InternalClientException
	 * @throws TransportException
	 */
	public String retrieveUnsecuredActions(final String contextId)
			throws EscidocClientException, InternalClientException,
			TransportException {
		String result = null;
		try {
			result = getClient().retrieveUnsecuredActions(contextId);
		} catch (Exception e) {
			ExceptionMapper.map(e);
		}
		return result;
	}

	/**
	 * 
	 * @param contextId
	 * @throws EscidocClientException
	 * @throws InternalClientException
	 * @throws TransportException
	 */
	public void deleteUnsecuredActions(final String contextId)
			throws EscidocClientException, InternalClientException,
			TransportException {
		try {
			getClient().deleteUnsecuredActions(contextId);
		} catch (Exception e) {
			ExceptionMapper.map(e);
		}
	}

	/**
	 * Place holder method.
	 * 
	 * @param id
	 *            The id of the context.
	 * @return The timestamp of the last modification of the context.
	 * @param id
	 * @return
	 * @throws EscidocException
	 * @throws InternalClientException
	 * @throws TransportException
	 * @see de.escidoc.core.client.ClientBase#getLastModificationDate(java.lang.String)
	 */
	@Override
	public DateTime getLastModificationDate(final String id)
			throws EscidocException, InternalClientException,
			TransportException {

		DateTime result = null;

		return result;
	}

	/**
	 * @return Returns the soapClient.
	 * @throws InternalClientException
	 * @see de.escidoc.core.client.ClientBase#getClient()
	 */
	@Override
	public ActionHandler getClient() throws InternalClientException {

		try {
			if (soapClient == null) {
				ActionHandlerServiceLocator serviceLocator = new ActionHandlerServiceLocator(
						getEngineConfig());
				String adress = serviceLocator.getActionHandlerServiceAddress();
				URL url = null;
				try {
					url = new URL(adress);
				} catch (MalformedURLException e) {
					throw new InternalClientException(e);
				}
				String path = url.getFile();
				adress = getServiceAddress() + path;

				try {
					url = new URL(adress);
				} catch (MalformedURLException e) {
					throw new ServiceException(e);
				}
				soapClient = serviceLocator.getActionHandlerService(url);
			}
		} catch (ServiceException e) {
			throw new InternalClientException(e.getMessage(), e);
		}
		return soapClient;
	}

}
