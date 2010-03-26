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
import de.escidoc.core.oum.OrganizationalUnitHandler;
import de.escidoc.core.oum.OrganizationalUnitHandlerServiceLocator;

/**
 * SOAP Handler for Organizational Unit.
 * 
 * @author SWA
 * 
 */
public class SoapOrganizationalUnitHandlerClient extends ClientBase {

    private final Logger logger =
        Logger.getLogger(SoapOrganizationalUnitHandlerClient.class.getName());

    private OrganizationalUnitHandler soapClient = null;

    public SoapOrganizationalUnitHandlerClient() throws InternalClientException {

        super();
    }

    /**
     * 
     * @param organizationalUnit
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     * @see de.escidoc.core.om.service.interfaces.OrganizationalUnitHandlerInterface#create(java.lang.String)
     */
    public String create(final String organizationalUnit)
        throws EscidocException, InternalClientException, TransportException {

        String result = null;
        try {
            result = getClient().create(organizationalUnit);
        }
        catch (Exception e) {
            logger.debug(e);
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
     * @see de.escidoc.core.om.service.interfaces.OrganizationalUnitHandlerInterface#delete(java.lang.String)
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
     * @see de.escidoc.core.om.service.interfaces.OrganizationalUnitHandlerInterface#retrieve(java.lang.String)
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
     * @param organizationalUnit
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     * @see de.escidoc.core.om.service.interfaces.OrganizationalUnitHandlerInterface#update(java.lang.String,
     *      java.lang.String)
     */
    public String update(final String id, final String organizationalUnit)
        throws EscidocException, InternalClientException, TransportException {

        String result = null;
        try {
            result = getClient().update(id, organizationalUnit);
        }
        catch (Exception e) {
            ExceptionMapper.map(e);
        }
        return result;
    }

    /**
     * Retrieve the OrganizationalUnits that are subordinated to this
     * OrganizationalUnit.
     * 
     * @param id
     *            The identifier of the Organizational Unit.
     * 
     * @return The XML representation of the list of child Organizational Units
     *         corresponding to XML-schema "organizational-unit-list.xsd".
     * 
     * @throws EscidocException
     *             e
     * @throws InternalClientException
     *             e
     * @throws TransportException
     *             e
     * @see de.escidoc.core.om.service.interfaces.OrganizationalUnitHandlerInterface#retrieveChildObjects(java.lang.String)
     */
    public String retrieveChildObjects(final String id)
        throws EscidocException, InternalClientException, TransportException {

        String result = null;
        try {
            result = getClient().retrieveChildObjects(id);
        }
        catch (Exception e) {
            ExceptionMapper.map(e);
        }
        return result;
    }

    /**
     * 
     * @param taskParam
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     * @see de.escidoc.core.om.service.interfaces.OrganizationalUnitHandlerInterface#retrieveOrganizationalUnits(java.lang.String)
     */
    public String retrieveOrganizationalUnits(final String taskParam)
        throws EscidocException, InternalClientException, TransportException {

        String result = null;
        try {
            result = getClient().retrieveOrganizationalUnits(taskParam);
        }
        catch (Exception e) {
            ExceptionMapper.map(e);
        }
        return result;
    }

    /**
     * Retrieve all Organizational Units objects to that this OrganizationalUnit
     * is subordinated.
     * 
     * @param id
     *            The identifier of the Organizational Unit.
     * 
     * @return The XML representation of the list of parent Organizational Units
     *         corresponding to XML-schema "organizational-unit-list.xsd".
     * 
     * @throws EscidocException
     *             e
     * @throws InternalClientException
     *             e
     * @throws TransportException
     *             e
     * @see de.escidoc.core.om.service.interfaces.OrganizationalUnitHandlerInterface#retrieveParentObjects(java.lang.String)
     */
    public String retrieveParentObjects(final String id)
        throws EscidocException, InternalClientException, TransportException {

        String result = null;
        try {
            result = getClient().retrieveParentObjects(id);
        }
        catch (Exception e) {
            ExceptionMapper.map(e);
        }
        return result;
    }

    /**
     * Retrieve the pathList of an OrganizationalUnit. This is a list of all
     * paths from a given organizational unit to all its top level
     * organizational units. Each path contains references to all organizational
     * units of that path.
     * 
     * @param id
     *            The identifier of the Organizational Unit.
     * 
     * @return The XML representation of the path list of that Organizational
     *         Unit corresponding to XMLschema
     *         "organizational-unit-path-list.xsd"
     * 
     * @throws EscidocException
     *             e
     * @throws InternalClientException
     *             e
     * @throws TransportException
     *             e
     * @see de.escidoc.core.om.service.interfaces.OrganizationalUnitHandlerInterface#retrievePathList(java.lang.String)
     */
    public String retrievePathList(final String id) throws EscidocException,
        InternalClientException, TransportException {

        String result = null;
        try {
            result = getClient().retrievePathList(id);
        }
        catch (Exception e) {
            ExceptionMapper.map(e);
        }
        return result;
    }

    /**
     * Retrieve a list with references to all successors of this Organizational
     * Units (see schema for data structure).
     * 
     * @param id
     *            The identifier of the Organizational Unit.
     * 
     * @return The XML representation of successors of the Organizational Unit
     *         corresponding the organizational-unit-successors schema.
     * 
     * @throws EscidocException
     *             e
     * @throws InternalClientException
     *             e
     * @throws TransportException
     *             e
     * @see de.escidoc.core.om.service.interfaces.OrganizationalUnitHandlerInterface#retrieveSuccessors(java.lang.String)
     */
    public String retrieveSuccessors(final String id) throws EscidocException,
        InternalClientException, TransportException {

        String result = null;
        try {
            result = getClient().retrieveSuccessors(id);
        }
        catch (Exception e) {
            ExceptionMapper.map(e);
        }
        return result;
    }

    /**
     * 
     * @param id
     * @param taskParam
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
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
     * @param taskParam
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
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
     * Get the last-modification timestamp of the organizationalUnit.
     * 
     * @param id
     *            The id of the organizationalUnit.
     * @return The timestamp of the last modification of the organizationalUnit.
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
                (Factory.getOrganizationalUnitMarshaller()
                    .unmarshalDocument(getClient().retrieve(id)))
                    .getLastModificationDate();
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
    public OrganizationalUnitHandler getClient() throws InternalClientException {

        try {
            if (soapClient == null) {
                OrganizationalUnitHandlerServiceLocator serviceLocator =
                    new OrganizationalUnitHandlerServiceLocator(
                        getEngineConfig());
                String adress =
                    serviceLocator.getOrganizationalUnitHandlerServiceAddress();
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

                soapClient =
                    serviceLocator.getOrganizationalUnitHandlerService(url);
            }
        }
        catch (ServiceException e) {
            throw new InternalClientException(e.getMessage(), e);
        }
        return soapClient;
    }

}
