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
import java.util.HashMap;

import de.escidoc.core.client.interfaces.ContentRelationHandler;
import de.escidoc.core.common.exceptions.remote.application.invalid.InvalidContentException;
import de.escidoc.core.common.exceptions.remote.application.invalid.InvalidStatusException;
import de.escidoc.core.common.exceptions.remote.application.invalid.InvalidXmlException;
import de.escidoc.core.common.exceptions.remote.application.missing.MissingAttributeValueException;
import de.escidoc.core.common.exceptions.remote.application.missing.MissingContentException;
import de.escidoc.core.common.exceptions.remote.application.missing.MissingElementValueException;
import de.escidoc.core.common.exceptions.remote.application.missing.MissingLicenceException;
import de.escidoc.core.common.exceptions.remote.application.missing.MissingMdRecordException;
import de.escidoc.core.common.exceptions.remote.application.missing.MissingMethodParameterException;
import de.escidoc.core.common.exceptions.remote.application.notfound.ComponentNotFoundException;
import de.escidoc.core.common.exceptions.remote.application.notfound.ContentModelNotFoundException;
import de.escidoc.core.common.exceptions.remote.application.notfound.ContentRelationNotFoundException;
import de.escidoc.core.common.exceptions.remote.application.notfound.FileNotFoundException;
import de.escidoc.core.common.exceptions.remote.application.notfound.MdRecordNotFoundException;
import de.escidoc.core.common.exceptions.remote.application.notfound.ReferencedResourceNotFoundException;
import de.escidoc.core.common.exceptions.remote.application.notfound.RelationPredicateNotFoundException;
import de.escidoc.core.common.exceptions.remote.application.security.AuthenticationException;
import de.escidoc.core.common.exceptions.remote.application.security.AuthorizationException;
import de.escidoc.core.common.exceptions.remote.application.violated.AlreadyExistsException;
import de.escidoc.core.common.exceptions.remote.application.violated.AlreadyPublishedException;
import de.escidoc.core.common.exceptions.remote.application.violated.LockingException;
import de.escidoc.core.common.exceptions.remote.application.violated.NotPublishedException;
import de.escidoc.core.common.exceptions.remote.application.violated.OptimisticLockingException;
import de.escidoc.core.common.exceptions.remote.application.violated.PidAlreadyAssignedException;
import de.escidoc.core.common.exceptions.remote.application.violated.ReadonlyAttributeViolationException;
import de.escidoc.core.common.exceptions.remote.application.violated.ReadonlyElementViolationException;
import de.escidoc.core.common.exceptions.remote.application.violated.ReadonlyVersionException;
import de.escidoc.core.common.exceptions.remote.application.violated.ReadonlyViolationException;
import de.escidoc.core.common.exceptions.remote.system.SystemException;

/**
 * REST Service Connector.
 * 
 * @author SWA
 * 
 */
public class ContentRelationRestServiceLocator extends RestServiceMethod implements ContentRelationHandler {

    public static final String PATH = "/ir/content-relation";

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.om.ContentRelationHandler#create(java.lang.String)
     */
    @Override
    public String create(final String contentRelationXml) throws RemoteException, SystemException,
        MissingAttributeValueException, MissingContentException, MissingMdRecordException,
        ReferencedResourceNotFoundException, AuthenticationException, AuthorizationException,
        ContentRelationNotFoundException, InvalidContentException, RelationPredicateNotFoundException,
        ReadonlyAttributeViolationException, FileNotFoundException, MissingMethodParameterException,
        InvalidStatusException, ReadonlyElementViolationException, ContentModelNotFoundException, InvalidXmlException,
        MissingElementValueException {

        checkNotNull(contentRelationXml);

        return put(PATH, contentRelationXml);
    }

    /**
     * See Interface for functional description.
     * 
     * @param contentRelationId
     * @throws RemoteException
     * @throws SystemException
     * @throws LockingException
     * @throws MissingMethodParameterException
     * @throws InvalidStatusException
     * @throws AuthenticationException
     * @throws ContentRelationNotFoundException
     * @throws AlreadyPublishedException
     * @throws AuthorizationException
     * @see de.escidoc.core.om.ContentRelationHandler#delete(String)
     */
    @Override
    public void delete(final String contentRelationId) throws RemoteException, SystemException, LockingException,
        MissingMethodParameterException, InvalidStatusException, AuthenticationException,
        ContentRelationNotFoundException, AlreadyPublishedException, AuthorizationException {

        checkNotNull(contentRelationId);

        del(PATH + "/" + contentRelationId);
    }

    /**
     * See Interface for functional description.
     * 
     * @param contentRelationId
     * @param contentRelationXml
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
     * @throws InvalidContentRelationException
     * @throws MissingMdRecordException
     * @throws ReferencedResourceNotFoundException
     * @throws AuthenticationException
     * @throws AuthorizationException
     * @throws ContentRelationNotFoundException
     * @throws InvalidContentException
     * @throws OptimisticLockingException
     * @throws RelationPredicateNotFoundException
     * @throws FileNotFoundException
     * @throws MissingMethodParameterException
     * @throws NotPublishedException
     * @throws InvalidStatusException
     * @throws ReadonlyViolationException
     * @throws InvalidXmlException
     * @see de.escidoc.core.om.ContentRelationHandler#update(String, String)
     */
    @Override
    public String update(final String contentRelationId, final String contentRelationXml) throws RemoteException,
        SystemException, MissingLicenceException, ReadonlyVersionException, LockingException,
        ComponentNotFoundException, MissingContentException, MissingAttributeValueException, AlreadyExistsException,
        MissingMdRecordException, ReferencedResourceNotFoundException, AuthenticationException, AuthorizationException,
        ContentRelationNotFoundException, InvalidContentException, OptimisticLockingException,
        RelationPredicateNotFoundException, FileNotFoundException, MissingMethodParameterException,
        NotPublishedException, InvalidStatusException, ReadonlyViolationException, InvalidXmlException {

        checkNotNull(contentRelationId);
        checkNotNull(contentRelationXml);

        return put(PATH + "/" + contentRelationId, contentRelationXml);
    }

    @Override
    public String retrieve(final String contentRelationId) throws RemoteException, SystemException,
        MissingMethodParameterException, AuthenticationException, ContentRelationNotFoundException,
        AuthorizationException {

        checkNotNull(contentRelationId);

        return get(PATH + "/" + contentRelationId);
    }

    @Override
    public String retrieveProperties(final String contentRelationId) throws RemoteException, SystemException,
        MissingMethodParameterException, AuthenticationException, ContentRelationNotFoundException,
        AuthorizationException {

        checkNotNull(contentRelationId);

        return get(PATH + "/" + contentRelationId + "/properties");
    }

    public String retrieveContentRelations(final String filter) throws RemoteException, SystemException,
        MissingMethodParameterException, AuthenticationException, AuthorizationException, InvalidXmlException {

        return post(PATH + "s/filter", filter);
    }

    @SuppressWarnings( { "rawtypes", "unchecked" })
    @Override
    @Deprecated
    public String retrieveContentRelations(final HashMap filter) throws RemoteException, SystemException,
        MissingMethodParameterException, AuthenticationException, AuthorizationException, InvalidXmlException {

        return get(PATH + "s", filter);
    }

    @Override
    public String retrieveContentRelations(final SearchRetrieveRequestType filter) throws RemoteException,
        SystemException, MissingMethodParameterException, AuthenticationException, AuthorizationException,
        InvalidXmlException {

        checkNotNull(filter);

        return get(PATH + "s" + getEscidoc12Filter(filter));
    }

    @Override
    public String retrieveContentRelations(final ExplainRequestType filter) throws RemoteException, SystemException,
        MissingMethodParameterException, AuthenticationException, AuthorizationException, InvalidXmlException {

        checkNotNull(filter);

        return get(PATH + "s" + getEscidoc12Filter(filter));
    }

    @Override
    public String assignObjectPid(final String contentRelationId, final String taskParam) throws RemoteException,
        OptimisticLockingException, PidAlreadyAssignedException, ContentRelationNotFoundException, SystemException,
        MissingMethodParameterException, LockingException, AuthenticationException, AuthorizationException,
        InvalidXmlException {

        checkNotNull(contentRelationId);

        return post(PATH + "/" + contentRelationId + "/assign-object-pid", taskParam);
    }

    @Override
    public String lock(final String contentRelationId, final String taskParam) throws RemoteException,
        OptimisticLockingException, ContentRelationNotFoundException, SystemException, MissingMethodParameterException,
        LockingException, InvalidStatusException, AuthenticationException, AuthorizationException, InvalidXmlException,
        InvalidContentException {

        checkNotNull(contentRelationId);

        return post(PATH + "/" + contentRelationId + "/lock", taskParam);
    }

    @Override
    public String unlock(final String contentRelationId, final String taskParam) throws java.rmi.RemoteException,
        OptimisticLockingException, ContentRelationNotFoundException, SystemException, MissingMethodParameterException,
        LockingException, InvalidStatusException, AuthenticationException, AuthorizationException, InvalidXmlException,
        InvalidContentException {

        checkNotNull(contentRelationId);

        return post(PATH + "/" + contentRelationId + "/unlock", taskParam);
    }

    @Override
    public String submit(final String contentRelationId, final String taskParam) throws RemoteException,
        OptimisticLockingException, ContentRelationNotFoundException, SystemException, MissingMethodParameterException,
        LockingException, InvalidStatusException, AuthenticationException, AuthorizationException, InvalidXmlException,
        InvalidContentException {

        checkNotNull(contentRelationId);

        return post(PATH + "/" + contentRelationId + "/submit", taskParam);
    }

    @Override
    public String release(final String contentRelationId, final String taskParam) throws RemoteException,
        OptimisticLockingException, ContentRelationNotFoundException, SystemException, MissingMethodParameterException,
        LockingException, InvalidStatusException, AuthenticationException, AuthorizationException, InvalidXmlException,
        InvalidContentException {

        checkNotNull(contentRelationId);

        return post(PATH + "/" + contentRelationId + "/release", taskParam);
    }

    @Override
    public String retrieveMdRecord(final String contentRelationId, final String mdRecordId) throws RemoteException,
        ContentRelationNotFoundException, SystemException, AuthenticationException, AuthorizationException,
        MdRecordNotFoundException {

        checkNotNull(contentRelationId);
        checkNotNull(mdRecordId);

        return get(PATH + "/" + contentRelationId + "/md-records/md-record/" + mdRecordId);

    }

    @Override
    public String retrieveMdRecords(final String contentRelationId) throws RemoteException,
        ContentRelationNotFoundException, SystemException, AuthenticationException, AuthorizationException {

        checkNotNull(contentRelationId);

        return get(PATH + "/" + contentRelationId + "/md-records");
    }

    @Override
    public String revise(final String contentRelationId, final String taskParam) throws RemoteException,
        OptimisticLockingException, ContentRelationNotFoundException, SystemException, MissingMethodParameterException,
        LockingException, InvalidStatusException, AuthenticationException, AuthorizationException, InvalidXmlException,
        InvalidContentException {

        checkNotNull(contentRelationId);

        return post(PATH + "/" + contentRelationId + "/revise", taskParam);
    }

    @Override
    public String retrieveRegisteredPredicates() throws RemoteException, SystemException, InvalidXmlException,
        InvalidContentException {

        return get(PATH + "s/retrieve-registered-predicates");

    }

    @Override
    public String retrieveResources(final String contentRelationId) throws RemoteException, SystemException,
        AuthorizationException, AuthenticationException, ContentRelationNotFoundException,
        MissingMethodParameterException {

        checkNotNull(contentRelationId);

        return get(PATH + "/" + contentRelationId + "/resources");
    }
}