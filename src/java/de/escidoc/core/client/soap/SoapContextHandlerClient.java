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

import javax.xml.rpc.ServiceException;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;

import de.escidoc.core.client.ClientBase;
import de.escidoc.core.client.exceptions.EscidocException;
import de.escidoc.core.client.exceptions.ExceptionMapper;
import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.client.exceptions.TransportException;
import de.escidoc.core.common.jibx.Factory;
import de.escidoc.core.om.ContextHandler;
import de.escidoc.core.om.ContextHandlerServiceLocator;

/**
 * 
 * @author SWA
 * 
 */
public class SoapContextHandlerClient extends ClientBase {

    private final Logger logger =
        Logger.getLogger(SoapContextHandlerClient.class.getName());

    private ContextHandler soapClient = null;

    public SoapContextHandlerClient() throws InternalClientException {

        super();
    }

    /**
     * 
     * @param context
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     * @see de.escidoc.core.om.service.interfaces.ContextHandlerInterface#create(java.lang.String)
     */
    public String create(final String context) throws EscidocException,
        InternalClientException, TransportException {

        String result = null;
        try {
            result = getClient().create(context);
        }
        catch (Exception e) {
            ExceptionMapper.map(e);
        }
        return result;
    }

    /**
     * 
     * @param id
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     * @see de.escidoc.core.om.service.interfaces.ContextHandlerInterface#delete(java.lang.String)
     */
    public void delete(final String id) throws EscidocException,
        InternalClientException, TransportException {

        try {
            getClient().delete(id);
        }
        catch (Exception e) {
            ExceptionMapper.map(e);
        }
    }

    /**
     * 
     * @param id
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     * @see de.escidoc.core.om.service.interfaces.ContextHandlerInterface#retrieve(java.lang.String)
     */
    public String retrieve(final String id) throws EscidocException,
        InternalClientException, TransportException {

        String result = null;
        try {
            result = getClient().retrieve(id);
        }
        catch (Exception e) {
            ExceptionMapper.map(e);
        }
        return result;
    }

    /**
     * 
     * @param id
     * @param context
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     * @see de.escidoc.core.om.service.interfaces.ContextHandlerInterface#update(java.lang.String,
     *      java.lang.String)
     */
    public String update(final String id, final String context)
        throws EscidocException, InternalClientException, TransportException {

        String result = null;
        try {
            result = getClient().update(id, context);
        }
        catch (Exception e) {
            ExceptionMapper.map(e);
        }
        return result;
    }

    /**
     * 
     * @param id
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     * @see de.escidoc.core.om.service.interfaces.ContextHandlerInterface#lock(java.lang.String)
     */
    public String open(final String id, final String taskParam)
        throws EscidocException, InternalClientException, TransportException {

        String result = null;
        try {
            result = getClient().open(id, taskParam);
        }
        catch (Exception e) {
            ExceptionMapper.map(e);
        }
        return result;
    }

    /**
     * 
     * @param id
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     * @see de.escidoc.core.om.service.interfaces.ContextHandlerInterface#unlock(java.lang.String)
     */
    public String close(final String id, final String taskParam)
        throws EscidocException, InternalClientException, TransportException {

        String result = null;
        try {
            result = getClient().close(id, taskParam);
        }
        catch (Exception e) {
            ExceptionMapper.map(e);
        }
        return result;
    }

    /**
     * 
     * @param id
     * @param name
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public String retrieveAdminDescriptor(final String id, final String name)
        throws EscidocException, InternalClientException, TransportException {

        String result = null;
        try {
            result = getClient().retrieveAdminDescriptor(id, name);
        }
        catch (Exception e) {
            ExceptionMapper.map(e);
        }
        return result;
    }

    /**
     * 
     * @param id
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public String retrieveAdminDescriptors(final String id)
        throws EscidocException, InternalClientException, TransportException {

        String result = null;
        try {
            result = getClient().retrieveAdminDescriptors(id);
        }
        catch (Exception e) {
            ExceptionMapper.map(e);
        }
        return result;
    }

    /**
     * 
     * @param filter
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public String retrieveContexts(final String filter)
        throws EscidocException, InternalClientException, TransportException {

        String result = null;
        try {
            result = getClient().retrieveContexts(filter);
        }
        catch (Exception e) {
            ExceptionMapper.map(e);
        }
        return result;
    }

    /**
     * 
     * @param id
     * @param filter
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public String retrieveMembers(final String id, final String filter)
        throws EscidocException, InternalClientException, TransportException {

        String result = null;
        try {
            result = getClient().retrieveMembers(id, filter);
        }
        catch (Exception e) {
            ExceptionMapper.map(e);
        }
        return result;
    }

    /**
     * Get the last-modification timestamp of the context.
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
        throws EscidocException, InternalClientException, TransportException {

        DateTime result = null;
        try {
            result =
                (Factory.getContextMarshaller().unmarshalDocument(getClient()
                    .retrieve(id))).getLastModificationDate();
        }
        catch (Exception e) {
            ExceptionMapper.map(e);
        }
        return result;
    }

    /**
     * @return Returns the soapClient.
     * @throws InternalClientException
     * @see de.escidoc.core.client.ClientBase#getClient()
     */
    @Override
    public ContextHandler getClient() throws InternalClientException {

        try {
            if (soapClient == null) {
                ContextHandlerServiceLocator serviceLocator =
                    new ContextHandlerServiceLocator(getEngineConfig());

                String adress =
                    serviceLocator.getContextHandlerServiceAddress();
                URL url = null;
                try {
                    url = new URL(adress);
                }
                catch (MalformedURLException e) {
                    throw new InternalClientException(e);
                }
                String path = url.getFile();
                adress = getServiceAddress() + path;

                try {
                    url = new URL(adress);
                }
                catch (MalformedURLException e) {
                    throw new ServiceException(e);
                }

                soapClient = serviceLocator.getContextHandlerService(url);
            }
        }
        catch (ServiceException e) {
            throw new InternalClientException(e.getMessage(), e);
        }
        return soapClient;
    }

}
