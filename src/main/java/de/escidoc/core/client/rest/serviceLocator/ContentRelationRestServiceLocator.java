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
import de.escidoc.core.client.exceptions.application.notfound.ContentRelationNotFoundException;
import de.escidoc.core.client.exceptions.application.notfound.FileNotFoundException;
import de.escidoc.core.client.exceptions.application.notfound.MdRecordNotFoundException;
import de.escidoc.core.client.exceptions.application.notfound.ReferencedResourceNotFoundException;
import de.escidoc.core.client.exceptions.application.notfound.RelationPredicateNotFoundException;
import de.escidoc.core.client.exceptions.application.security.AuthenticationException;
import de.escidoc.core.client.exceptions.application.security.AuthorizationException;
import de.escidoc.core.client.exceptions.application.violated.AlreadyExistsException;
import de.escidoc.core.client.exceptions.application.violated.AlreadyPublishedException;
import de.escidoc.core.client.exceptions.application.violated.LockingException;
import de.escidoc.core.client.exceptions.application.violated.NotPublishedException;
import de.escidoc.core.client.exceptions.application.violated.OptimisticLockingException;
import de.escidoc.core.client.exceptions.application.violated.PidAlreadyAssignedException;
import de.escidoc.core.client.exceptions.application.violated.ReadonlyAttributeViolationException;
import de.escidoc.core.client.exceptions.application.violated.ReadonlyElementViolationException;
import de.escidoc.core.client.exceptions.application.violated.ReadonlyVersionException;
import de.escidoc.core.client.exceptions.application.violated.ReadonlyViolationException;
import de.escidoc.core.client.exceptions.system.SystemException;
import de.escidoc.core.client.interfaces.handler.ContentRelationHandler;

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
    public String create(final String contentRelationXml) throws EscidocException, MissingAttributeValueException,
        MissingContentException, MissingMdRecordException, ReferencedResourceNotFoundException,
        AuthenticationException, AuthorizationException, ContentRelationNotFoundException, InvalidContentException,
        RelationPredicateNotFoundException, ReadonlyAttributeViolationException, FileNotFoundException,
        MissingMethodParameterException, InvalidStatusException, ReadonlyElementViolationException,
        ContentModelNotFoundException, InvalidXmlException, MissingElementValueException {

        checkNotNull(contentRelationXml);

        return put(PATH, contentRelationXml);
    }

    @Override
    public void delete(final String contentRelationId) throws EscidocException, LockingException,
        MissingMethodParameterException, InvalidStatusException, AuthenticationException,
        ContentRelationNotFoundException, AlreadyPublishedException, AuthorizationException {

        checkNotNull(contentRelationId);

        del(PATH + "/" + contentRelationId);
    }

    @Override
    public String update(final String contentRelationId, final String contentRelationXml) throws EscidocException,
        MissingLicenceException, ReadonlyVersionException, LockingException, ComponentNotFoundException,
        MissingContentException, MissingAttributeValueException, AlreadyExistsException, MissingMdRecordException,
        ReferencedResourceNotFoundException, AuthenticationException, AuthorizationException,
        ContentRelationNotFoundException, InvalidContentException, OptimisticLockingException,
        RelationPredicateNotFoundException, FileNotFoundException, MissingMethodParameterException,
        NotPublishedException, InvalidStatusException, ReadonlyViolationException, InvalidXmlException {

        checkNotNull(contentRelationId);
        checkNotNull(contentRelationXml);

        return put(PATH + "/" + contentRelationId, contentRelationXml);
    }

    @Override
    public String retrieve(final String contentRelationId) throws EscidocException, MissingMethodParameterException,
        AuthenticationException, ContentRelationNotFoundException, AuthorizationException {

        checkNotNull(contentRelationId);

        return get(PATH + "/" + contentRelationId);
    }

    @Override
    public String retrieveProperties(final String contentRelationId) throws EscidocException,
        MissingMethodParameterException, AuthenticationException, ContentRelationNotFoundException,
        AuthorizationException {

        checkNotNull(contentRelationId);

        return get(PATH + "/" + contentRelationId + "/properties");
    }

    @Override
    public String retrieveContentRelations(final String filter) throws EscidocException,
        MissingMethodParameterException, AuthenticationException, AuthorizationException, InvalidXmlException {

        return post(PATH + "s/filter", filter);
    }

    @Override
    public String retrieveContentRelations(final SearchRetrieveRequestType filter) throws EscidocException,
        MissingMethodParameterException, AuthenticationException, AuthorizationException, InvalidXmlException {

        checkNotNull(filter);

        return get(PATH + "s" + getEscidoc12Filter(filter));
    }

    @Override
    public String retrieveContentRelations(final ExplainRequestType filter) throws EscidocException,
        MissingMethodParameterException, AuthenticationException, AuthorizationException, InvalidXmlException {

        checkNotNull(filter);

        return get(PATH + "s" + getEscidoc12Filter(filter));
    }

    @Override
    public String assignObjectPid(final String contentRelationId, final String taskParam) throws EscidocException,
        OptimisticLockingException, PidAlreadyAssignedException, ContentRelationNotFoundException, SystemException,
        MissingMethodParameterException, LockingException, AuthenticationException, AuthorizationException,
        InvalidXmlException {

        checkNotNull(contentRelationId);

        return post(PATH + "/" + contentRelationId + "/assign-object-pid", taskParam);
    }

    @Override
    public String lock(final String contentRelationId, final String taskParam) throws EscidocException,
        OptimisticLockingException, ContentRelationNotFoundException, SystemException, MissingMethodParameterException,
        LockingException, InvalidStatusException, AuthenticationException, AuthorizationException, InvalidXmlException,
        InvalidContentException {

        checkNotNull(contentRelationId);

        return post(PATH + "/" + contentRelationId + "/lock", taskParam);
    }

    @Override
    public String unlock(final String contentRelationId, final String taskParam) throws EscidocException,
        OptimisticLockingException, ContentRelationNotFoundException, SystemException, MissingMethodParameterException,
        LockingException, InvalidStatusException, AuthenticationException, AuthorizationException, InvalidXmlException,
        InvalidContentException {

        checkNotNull(contentRelationId);

        return post(PATH + "/" + contentRelationId + "/unlock", taskParam);
    }

    @Override
    public String submit(final String contentRelationId, final String taskParam) throws EscidocException,
        OptimisticLockingException, ContentRelationNotFoundException, SystemException, MissingMethodParameterException,
        LockingException, InvalidStatusException, AuthenticationException, AuthorizationException, InvalidXmlException,
        InvalidContentException {

        checkNotNull(contentRelationId);

        return post(PATH + "/" + contentRelationId + "/submit", taskParam);
    }

    @Override
    public String release(final String contentRelationId, final String taskParam) throws EscidocException,
        OptimisticLockingException, ContentRelationNotFoundException, SystemException, MissingMethodParameterException,
        LockingException, InvalidStatusException, AuthenticationException, AuthorizationException, InvalidXmlException,
        InvalidContentException {

        checkNotNull(contentRelationId);

        return post(PATH + "/" + contentRelationId + "/release", taskParam);
    }

    @Override
    public String retrieveMdRecord(final String contentRelationId, final String mdRecordId) throws EscidocException,
        ContentRelationNotFoundException, SystemException, AuthenticationException, AuthorizationException,
        MdRecordNotFoundException {

        checkNotNull(contentRelationId);
        checkNotNull(mdRecordId);

        return get(PATH + "/" + contentRelationId + "/md-records/md-record/" + mdRecordId);

    }

    @Override
    public String retrieveMdRecords(final String contentRelationId) throws EscidocException,
        ContentRelationNotFoundException, SystemException, AuthenticationException, AuthorizationException {

        checkNotNull(contentRelationId);

        return get(PATH + "/" + contentRelationId + "/md-records");
    }

    @Override
    public String revise(final String contentRelationId, final String taskParam) throws EscidocException,
        OptimisticLockingException, ContentRelationNotFoundException, SystemException, MissingMethodParameterException,
        LockingException, InvalidStatusException, AuthenticationException, AuthorizationException, InvalidXmlException,
        InvalidContentException {

        checkNotNull(contentRelationId);

        return post(PATH + "/" + contentRelationId + "/revise", taskParam);
    }

    @Override
    public String retrieveRegisteredPredicates() throws EscidocException, InvalidXmlException, InvalidContentException {

        return get(PATH + "s/retrieve-registered-predicates");

    }

    @Override
    public String retrieveResources(final String contentRelationId) throws EscidocException, AuthorizationException,
        AuthenticationException, ContentRelationNotFoundException, MissingMethodParameterException {

        checkNotNull(contentRelationId);

        return get(PATH + "/" + contentRelationId + "/resources");
    }
}