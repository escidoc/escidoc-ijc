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
package de.escidoc.core.client.rest.serviceLocator;

import static de.escidoc.core.common.Precondition.checkNotNull;
import gov.loc.www.zing.srw.ExplainRequestType;
import gov.loc.www.zing.srw.SearchRetrieveRequestType;

import java.rmi.RemoteException;

import de.escidoc.core.client.exceptions.EscidocException;
import de.escidoc.core.client.exceptions.application.invalid.InvalidContentException;
import de.escidoc.core.client.exceptions.application.invalid.InvalidStatusException;
import de.escidoc.core.client.exceptions.application.invalid.InvalidXmlException;
import de.escidoc.core.client.exceptions.application.missing.MissingAttributeValueException;
import de.escidoc.core.client.exceptions.application.missing.MissingContentException;
import de.escidoc.core.client.exceptions.application.missing.MissingElementValueException;
import de.escidoc.core.client.exceptions.application.missing.MissingLicenceException;
import de.escidoc.core.client.exceptions.application.missing.MissingMdRecordException;
import de.escidoc.core.client.exceptions.application.missing.MissingMethodParameterException;
import de.escidoc.core.client.exceptions.application.notfound.ComponentNotFoundException;
import de.escidoc.core.client.exceptions.application.notfound.ContentModelNotFoundException;
import de.escidoc.core.client.exceptions.application.notfound.ContentStreamNotFoundException;
import de.escidoc.core.client.exceptions.application.notfound.FileNotFoundException;
import de.escidoc.core.client.exceptions.application.notfound.ReferencedResourceNotFoundException;
import de.escidoc.core.client.exceptions.application.notfound.RelationPredicateNotFoundException;
import de.escidoc.core.client.exceptions.application.security.AuthenticationException;
import de.escidoc.core.client.exceptions.application.security.AuthorizationException;
import de.escidoc.core.client.exceptions.application.violated.AlreadyExistsException;
import de.escidoc.core.client.exceptions.application.violated.AlreadyPublishedException;
import de.escidoc.core.client.exceptions.application.violated.LockingException;
import de.escidoc.core.client.exceptions.application.violated.NotPublishedException;
import de.escidoc.core.client.exceptions.application.violated.OptimisticLockingException;
import de.escidoc.core.client.exceptions.application.violated.ReadonlyAttributeViolationException;
import de.escidoc.core.client.exceptions.application.violated.ReadonlyElementViolationException;
import de.escidoc.core.client.exceptions.application.violated.ReadonlyVersionException;
import de.escidoc.core.client.exceptions.application.violated.ReadonlyViolationException;
import de.escidoc.core.client.exceptions.system.SystemException;
import de.escidoc.core.client.interfaces.handler.ContentModelHandler;
import de.escidoc.core.resources.HttpInputStream;

/**
 * REST Service Connector.
 * 
 * @author SWA
 * 
 */
public class ContentModelRestServiceLocator extends RestServiceMethod implements ContentModelHandler {

    public static final String PATH = "/cmm/content-model";

    /**
     * See Interface for functional description.
     * 
     * @param contentModelId
     * @throws RemoteException
     * @throws SystemException
     * @throws LockingException
     * @throws MissingMethodParameterException
     * @throws InvalidStatusException
     * @throws AuthenticationException
     * @throws ContentModelNotFoundException
     * @throws AlreadyPublishedException
     * @throws AuthorizationException
     * @see de.escidoc.core.client.interfaces.handler.om.ContentModelHandler#delete(String)
     */
    @Override
    public void delete(final String contentModelId) throws EscidocException, LockingException,
        MissingMethodParameterException, InvalidStatusException, AuthenticationException,
        ContentModelNotFoundException, AlreadyPublishedException, AuthorizationException {

        checkNotNull(contentModelId);

        del(PATH + "/" + contentModelId);
    }

    /**
     * See Interface for functional description.
     * 
     * @param contentModelXml
     * @return
     * @throws RemoteException
     * @throws SystemException
     * @throws MissingAttributeValueException
     * @throws MissingContentException
     * @throws MissingMdRecordException
     * @throws ReferencedResourceNotFoundException
     * @throws AuthenticationException
     * @throws AuthorizationException
     * @throws ContentModelNotFoundException
     * @throws InvalidContentException
     * @throws RelationPredicateNotFoundException
     * @throws ReadonlyAttributeViolationException
     * @throws FileNotFoundException
     * @throws MissingMethodParameterException
     * @throws InvalidStatusException
     * @throws ReadonlyElementViolationException
     * @throws ContentModelNotFoundException
     * @throws InvalidXmlException
     * @throws MissingElementValueException
     * @see de.escidoc.core.client.interfaces.handler.om.ContentModelHandler#create(String)
     */
    @Override
    public String create(final String contentModelXml) throws EscidocException, MissingAttributeValueException,
        MissingContentException, MissingMdRecordException, ReferencedResourceNotFoundException,
        AuthenticationException, AuthorizationException, ContentModelNotFoundException, InvalidContentException,
        RelationPredicateNotFoundException, ReadonlyAttributeViolationException, FileNotFoundException,
        MissingMethodParameterException, InvalidStatusException, ReadonlyElementViolationException,
        ContentModelNotFoundException, InvalidXmlException, MissingElementValueException {

        checkNotNull(contentModelXml);

        return put(PATH, contentModelXml);
    }

    /**
     * See Interface for functional description.
     * 
     * @param contentModelId
     * @param contentModelXml
     * @return
     * @throws RemoteException
     * @throws SystemException
     * @throws MissingLicenceException
     * @throws ReadonlyVersionException
     * @throws LockingException
     * @throws ComponentNotFoundException
     * @throws MissingContentException
     * @throws MissingAttributeValueException
     * @throws AlreadyExistsException
     * @throws InvalidContentException
     * @throws MissingMdRecordException
     * @throws ReferencedResourceNotFoundException
     * @throws AuthenticationException
     * @throws AuthorizationException
     * @throws ContentModelNotFoundException
     * @throws InvalidContentException
     * @throws OptimisticLockingException
     * @throws RelationPredicateNotFoundException
     * @throws FileNotFoundException
     * @throws MissingMethodParameterException
     * @throws NotPublishedException
     * @throws InvalidStatusException
     * @throws ReadonlyViolationException
     * @throws InvalidXmlException
     * @see de.escidoc.core.client.interfaces.handler.om.ContentModelHandler#update(String,
     *      String)
     */
    @Override
    public String update(final String contentModelId, final String contentModelXml) throws EscidocException,
        MissingLicenceException, ReadonlyVersionException, LockingException, ComponentNotFoundException,
        MissingContentException, MissingAttributeValueException, AlreadyExistsException, InvalidContentException,
        MissingMdRecordException, ReferencedResourceNotFoundException, AuthenticationException, AuthorizationException,
        ContentModelNotFoundException, InvalidContentException, OptimisticLockingException,
        RelationPredicateNotFoundException, FileNotFoundException, MissingMethodParameterException,
        NotPublishedException, InvalidStatusException, ReadonlyViolationException, InvalidXmlException {

        checkNotNull(contentModelId);
        checkNotNull(contentModelXml);

        return put(PATH + "/" + contentModelId, contentModelXml);
    }

    /**
     * 
     * @param contentModelId
     * @return
     * @throws RemoteException
     * @throws SystemException
     * @throws MissingMethodParameterException
     * @throws AuthenticationException
     * @throws ContentModelNotFoundException
     * @throws AuthorizationException
     */
    @Override
    public String retrieve(final String contentModelId) throws EscidocException, MissingMethodParameterException,
        AuthenticationException, ContentModelNotFoundException, AuthorizationException {

        checkNotNull(contentModelId);

        return get(PATH + "/" + contentModelId);
    }

    @Override
    public String retrieveContentStream(final String contentModelId, final String contentStreamName)
        throws EscidocException, ContentStreamNotFoundException, MissingMethodParameterException,
        AuthenticationException, ContentModelNotFoundException, AuthorizationException {

        checkNotNull(contentModelId);
        checkNotNull(contentStreamName);

        return get(PATH + "/" + contentModelId + "/content-streams/content-stream/" + contentStreamName);
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.client.interfaces.handler.ContentModelHandler#
     * retrieveContentStreamContent(java.lang.String, java.lang.String)
     */
    @Override
    public HttpInputStream retrieveContentStreamContent(final String contentModelId, final String contentStreamName)
        throws EscidocException, ContentModelNotFoundException, SystemException, AuthenticationException,
        AuthorizationException, MissingMethodParameterException, ContentStreamNotFoundException, InvalidStatusException {

        checkNotNull(contentModelId);
        checkNotNull(contentStreamName);

        return getStream(PATH + "/" + contentModelId + "/content-streams/content-stream/" + contentStreamName
            + "/content");
    }

    @Override
    public String retrieveContentStreams(final String contentModelId) throws EscidocException,
        MissingMethodParameterException, AuthenticationException, ContentModelNotFoundException, AuthorizationException {

        checkNotNull(contentModelId);

        return get(PATH + "/" + contentModelId + "/content-streams");
    }

    @Override
    public String retrieveProperties(final String contentModelId) throws EscidocException,
        MissingMethodParameterException, AuthenticationException, ContentModelNotFoundException, AuthorizationException {

        checkNotNull(contentModelId);

        return get(PATH + "/" + contentModelId + "/properties");
    }

    @Override
    public String retrieveVersionHistory(final String contentModelId) throws EscidocException,
        MissingMethodParameterException, AuthenticationException, ContentModelNotFoundException, AuthorizationException {

        checkNotNull(contentModelId);

        return get(PATH + "/" + contentModelId + "/resources/version-history");
    }

    /**
     * 
     * @param request
     * @return
     * @throws SystemException
     * @throws RemoteException
     */
    @Override
    public String retrieveContentModels(final SearchRetrieveRequestType request) throws EscidocException,
        MissingMethodParameterException, AuthenticationException, AuthorizationException, InvalidXmlException {

        checkNotNull(request);
        return get(PATH + "s" + getEscidoc12Filter(request));
    }

    /**
     * 
     * @param request
     * @return
     * @throws SystemException
     * @throws RemoteException
     */
    @Override
    public String retrieveContentModels(final ExplainRequestType request) throws EscidocException,
        MissingMethodParameterException, AuthenticationException, AuthorizationException, InvalidXmlException {

        checkNotNull(request);
        return get(PATH + "s" + getEscidoc12Filter(request));
    }
}
