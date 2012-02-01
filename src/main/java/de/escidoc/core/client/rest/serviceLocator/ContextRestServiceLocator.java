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
import de.escidoc.core.client.exceptions.application.invalid.InvalidContextException;
import de.escidoc.core.client.exceptions.application.invalid.InvalidStatusException;
import de.escidoc.core.client.exceptions.application.invalid.InvalidXmlException;
import de.escidoc.core.client.exceptions.application.missing.MissingAttributeValueException;
import de.escidoc.core.client.exceptions.application.missing.MissingContentException;
import de.escidoc.core.client.exceptions.application.missing.MissingElementValueException;
import de.escidoc.core.client.exceptions.application.missing.MissingLicenceException;
import de.escidoc.core.client.exceptions.application.missing.MissingMdRecordException;
import de.escidoc.core.client.exceptions.application.missing.MissingMethodParameterException;
import de.escidoc.core.client.exceptions.application.notfound.AdminDescriptorNotFoundException;
import de.escidoc.core.client.exceptions.application.notfound.ComponentNotFoundException;
import de.escidoc.core.client.exceptions.application.notfound.ContentModelNotFoundException;
import de.escidoc.core.client.exceptions.application.notfound.ContextNotFoundException;
import de.escidoc.core.client.exceptions.application.notfound.FileNotFoundException;
import de.escidoc.core.client.exceptions.application.notfound.ReferencedResourceNotFoundException;
import de.escidoc.core.client.exceptions.application.notfound.RelationPredicateNotFoundException;
import de.escidoc.core.client.exceptions.application.notfound.StreamNotFoundException;
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
import de.escidoc.core.client.interfaces.handler.ContextHandler;

/**
 * REST Service Connector.
 * 
 * @author SWA
 * 
 */
public class ContextRestServiceLocator extends RestServiceMethod implements ContextHandler {

    public static final String PATH = "/ir/context";

    @Override
    public String close(final String contextId, final String taskParam) throws EscidocException,
        OptimisticLockingException, SystemException, MissingMethodParameterException, LockingException,
        InvalidStatusException, AuthenticationException, StreamNotFoundException, AuthorizationException,
        ContextNotFoundException, InvalidXmlException {

        checkNotNull(contextId);

        return post(PATH + "/" + contextId + "/close", taskParam);
    }

    @Override
    public String open(final String contextId, final String taskParam) throws EscidocException,
        OptimisticLockingException, SystemException, MissingMethodParameterException, LockingException,
        InvalidStatusException, AuthenticationException, StreamNotFoundException, AuthorizationException,
        ContextNotFoundException, InvalidXmlException {

        checkNotNull(contextId);

        return post(PATH + "/" + contextId + "/open", taskParam);
    }

    @Override
    public String retrieveMembers(final String contextId, final String filter) throws EscidocException,
        MissingMethodParameterException, AuthenticationException, AuthorizationException, ContextNotFoundException,
        InvalidXmlException {

        checkNotNull(contextId);

        return post(PATH + "/" + contextId + "/resources/members/filter", filter);
    }

    @Override
    public String retrieveMembers(final String contextId, final SearchRetrieveRequestType filter)
        throws EscidocException, MissingMethodParameterException, AuthenticationException, AuthorizationException,
        InvalidXmlException {

        checkNotNull(contextId);
        checkNotNull(filter);

        return get(PATH + "/" + contextId + "/resources/members" + getEscidoc12Filter(filter));
    }

    @Override
    public String retrieveMembers(final String contextId, final ExplainRequestType filter) throws EscidocException,
        MissingMethodParameterException, AuthenticationException, AuthorizationException, InvalidXmlException {

        checkNotNull(contextId);
        checkNotNull(filter);

        return get(PATH + "/" + contextId + "/resources/members" + getEscidoc12Filter(filter));
    }

    @Override
    public String retrieveAdminDescriptor(final String contextId, final String admId) throws EscidocException,
        AdminDescriptorNotFoundException, MissingMethodParameterException, AuthenticationException,
        AuthorizationException, ContextNotFoundException {

        checkNotNull(contextId);
        checkNotNull(admId);

        return get(PATH + "/" + contextId + "/admin-descriptors/admin-descriptor/" + admId);
    }

    @Override
    public String retrieveAdminDescriptors(final String contextId) throws EscidocException,
        MissingMethodParameterException, AuthenticationException, AuthorizationException, ContextNotFoundException {

        checkNotNull(contextId);

        return get(PATH + "/" + contextId + "/admin-descriptors");
    }

    @Override
    public String updateAdminDescriptor(final String id, final String xmlData) throws EscidocException,
        OptimisticLockingException, SystemException, AdminDescriptorNotFoundException, MissingMethodParameterException,
        AuthenticationException, AuthorizationException, ContextNotFoundException, InvalidXmlException {

        throw new UnsupportedOperationException("Method not yet implemented.");
    }

    /**
     * See Interface for functional description.
     * 
     * @param contextId
     * @throws RemoteException
     * @throws SystemException
     * @throws LockingException
     * @throws MissingMethodParameterException
     * @throws InvalidStatusException
     * @throws AuthenticationException
     * @throws ContextNotFoundException
     * @throws AlreadyPublishedException
     * @throws AuthorizationException
     * @see de.escidoc.core.client.interfaces.handler.om.ContextHandler#delete(String)
     */
    @Override
    public void delete(final String contextId) throws EscidocException, LockingException,
        MissingMethodParameterException, InvalidStatusException, AuthenticationException, ContextNotFoundException,
        AlreadyPublishedException, AuthorizationException {

        checkNotNull(contextId);

        del(PATH + "/" + contextId);
    }

    /**
     * See Interface for functional description.
     * 
     * @param contextXml
     * @return
     * @throws RemoteException
     * @throws SystemException
     * @throws MissingAttributeValueException
     * @throws MissingContentException
     * @throws MissingMdRecordException
     * @throws ReferencedResourceNotFoundException
     * @throws AuthenticationException
     * @throws AuthorizationException
     * @throws ContextNotFoundException
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
     * @see de.escidoc.core.client.interfaces.handler.om.ContextHandler#create(String)
     */
    @Override
    public String create(final String contextXml) throws EscidocException, MissingAttributeValueException,
        MissingContentException, MissingMdRecordException, ReferencedResourceNotFoundException,
        AuthenticationException, AuthorizationException, ContextNotFoundException, InvalidContentException,
        RelationPredicateNotFoundException, ReadonlyAttributeViolationException, FileNotFoundException,
        MissingMethodParameterException, InvalidStatusException, ReadonlyElementViolationException,
        ContentModelNotFoundException, InvalidXmlException, MissingElementValueException {

        checkNotNull(contextXml);

        return put(PATH, contextXml);
    }

    /**
     * See Interface for functional description.
     * 
     * @param contextId
     * @param contextXml
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
     * @throws InvalidContextException
     * @throws MissingMdRecordException
     * @throws ReferencedResourceNotFoundException
     * @throws AuthenticationException
     * @throws AuthorizationException
     * @throws ContextNotFoundException
     * @throws InvalidContentException
     * @throws OptimisticLockingException
     * @throws RelationPredicateNotFoundException
     * @throws FileNotFoundException
     * @throws MissingMethodParameterException
     * @throws NotPublishedException
     * @throws InvalidStatusException
     * @throws ReadonlyViolationException
     * @throws InvalidXmlException
     * @see de.escidoc.core.client.interfaces.handler.om.ContextHandler#update(String, String)
     */
    @Override
    public String update(final String contextId, final String contextXml) throws EscidocException,
        MissingLicenceException, ReadonlyVersionException, LockingException, ComponentNotFoundException,
        MissingContentException, MissingAttributeValueException, AlreadyExistsException, InvalidContextException,
        MissingMdRecordException, ReferencedResourceNotFoundException, AuthenticationException, AuthorizationException,
        ContextNotFoundException, InvalidContentException, OptimisticLockingException,
        RelationPredicateNotFoundException, FileNotFoundException, MissingMethodParameterException,
        NotPublishedException, InvalidStatusException, ReadonlyViolationException, InvalidXmlException {

        checkNotNull(contextId);
        checkNotNull(contextXml);

        return put(PATH + "/" + contextId, contextXml);
    }

    @Override
    public String retrieve(final String contextId) throws EscidocException, MissingMethodParameterException,
        AuthenticationException, ContextNotFoundException, AuthorizationException {

        checkNotNull(contextId);

        return get(PATH + "/" + contextId);
    }

    @Override
    public String retrieveProperties(final String contextId) throws EscidocException, MissingMethodParameterException,
        AuthenticationException, ContextNotFoundException, AuthorizationException {

        checkNotNull(contextId);

        return get(PATH + "/" + contextId + "/properties");
    }

    @Override
    public String retrieveContexts(final SearchRetrieveRequestType filter) throws EscidocException,
        MissingMethodParameterException, AuthenticationException, AuthorizationException, InvalidXmlException {

        checkNotNull(filter);

        return get(PATH + "s" + getEscidoc12Filter(filter));
    }

    @Override
    public String retrieveContexts(final ExplainRequestType filter) throws EscidocException,
        MissingMethodParameterException, AuthenticationException, AuthorizationException, InvalidXmlException {

        checkNotNull(filter);

        return get(PATH + "s" + getEscidoc12Filter(filter));
    }

}
