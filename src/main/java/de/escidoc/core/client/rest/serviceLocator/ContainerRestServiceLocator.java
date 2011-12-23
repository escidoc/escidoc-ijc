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
import de.escidoc.core.client.exceptions.application.invalid.InvalidContextException;
import de.escidoc.core.client.exceptions.application.invalid.InvalidContextStatusException;
import de.escidoc.core.client.exceptions.application.invalid.InvalidItemStatusException;
import de.escidoc.core.client.exceptions.application.invalid.InvalidStatusException;
import de.escidoc.core.client.exceptions.application.invalid.InvalidXmlException;
import de.escidoc.core.client.exceptions.application.invalid.XmlSchemaValidationException;
import de.escidoc.core.client.exceptions.application.missing.MissingAttributeValueException;
import de.escidoc.core.client.exceptions.application.missing.MissingContentException;
import de.escidoc.core.client.exceptions.application.missing.MissingElementValueException;
import de.escidoc.core.client.exceptions.application.missing.MissingLicenceException;
import de.escidoc.core.client.exceptions.application.missing.MissingMdRecordException;
import de.escidoc.core.client.exceptions.application.missing.MissingMethodParameterException;
import de.escidoc.core.client.exceptions.application.notfound.ComponentNotFoundException;
import de.escidoc.core.client.exceptions.application.notfound.ContainerNotFoundException;
import de.escidoc.core.client.exceptions.application.notfound.ContentModelNotFoundException;
import de.escidoc.core.client.exceptions.application.notfound.ContentRelationNotFoundException;
import de.escidoc.core.client.exceptions.application.notfound.ContextNotFoundException;
import de.escidoc.core.client.exceptions.application.notfound.FileNotFoundException;
import de.escidoc.core.client.exceptions.application.notfound.ItemNotFoundException;
import de.escidoc.core.client.exceptions.application.notfound.MdRecordNotFoundException;
import de.escidoc.core.client.exceptions.application.notfound.ReferencedResourceNotFoundException;
import de.escidoc.core.client.exceptions.application.notfound.RelationPredicateNotFoundException;
import de.escidoc.core.client.exceptions.application.notfound.XmlSchemaNotFoundException;
import de.escidoc.core.client.exceptions.application.security.AuthenticationException;
import de.escidoc.core.client.exceptions.application.security.AuthorizationException;
import de.escidoc.core.client.exceptions.application.violated.AdminDescriptorViolationException;
import de.escidoc.core.client.exceptions.application.violated.AlreadyDeletedException;
import de.escidoc.core.client.exceptions.application.violated.AlreadyExistsException;
import de.escidoc.core.client.exceptions.application.violated.AlreadyPublishedException;
import de.escidoc.core.client.exceptions.application.violated.AlreadyWithdrawnException;
import de.escidoc.core.client.exceptions.application.violated.LockingException;
import de.escidoc.core.client.exceptions.application.violated.NotPublishedException;
import de.escidoc.core.client.exceptions.application.violated.OptimisticLockingException;
import de.escidoc.core.client.exceptions.application.violated.ReadonlyAttributeViolationException;
import de.escidoc.core.client.exceptions.application.violated.ReadonlyElementViolationException;
import de.escidoc.core.client.exceptions.application.violated.ReadonlyVersionException;
import de.escidoc.core.client.exceptions.application.violated.ReadonlyViolationException;
import de.escidoc.core.client.exceptions.system.SystemException;
import de.escidoc.core.client.interfaces.handler.ContainerHandler;

/**
 * REST service for Container.
 * 
 * @author SWA
 * 
 */
public class ContainerRestServiceLocator extends RestServiceMethod implements ContainerHandler {

    public static final String PATH = "/ir/container";

    @Override
    public String addTocs(final String containerId, final String taskParam) throws EscidocException,
        OptimisticLockingException, SystemException, LockingException, MissingMethodParameterException,
        MissingAttributeValueException, ContainerNotFoundException, InvalidContextException, AuthenticationException,
        AuthorizationException, InvalidContentException {

        checkNotNull(containerId);

        return post(PATH + "/" + containerId + "/tocs/add", taskParam);
    }

    @Override
    public String createContainer(final String containerId, final String containerXml) throws EscidocException,
        LockingException, MissingAttributeValueException, ContainerNotFoundException, InvalidContextException,
        MissingMdRecordException, ReferencedResourceNotFoundException, AuthenticationException, AuthorizationException,
        ContextNotFoundException, InvalidContentException, RelationPredicateNotFoundException,
        MissingMethodParameterException, InvalidStatusException, ContentModelNotFoundException,
        MissingElementValueException, InvalidXmlException {

        checkNotNull(containerId);
        checkNotNull(containerXml);

        return post(PATH + "/" + containerId + "/create-container", containerXml);
    }

    @Override
    public String addMembers(final String containerId, final String taskParam) throws EscidocException,
        OptimisticLockingException, SystemException, LockingException, MissingMethodParameterException,
        MissingAttributeValueException, ContainerNotFoundException, InvalidContextException, AuthenticationException,
        AuthorizationException, InvalidContentException {

        checkNotNull(containerId);

        return post(PATH + "/" + containerId + "/members/add", taskParam);
    }

    @Override
    public String removeMembers(final String containerId, final String taskParam) throws EscidocException,
        InvalidItemStatusException, SystemException, LockingException, AdminDescriptorViolationException,
        ContainerNotFoundException, AuthenticationException, InvalidContextStatusException, ItemNotFoundException,
        AuthorizationException, ContextNotFoundException, InvalidContentException, XmlSchemaValidationException {

        checkNotNull(containerId);

        return post(PATH + "/" + containerId + "/members/remove", taskParam);
    }

    @Override
    public String retrieveStructMap(final String containerId) throws EscidocException, MissingMethodParameterException,
        ContainerNotFoundException, AuthenticationException, AuthorizationException {

        checkNotNull(containerId);

        return get(PATH + "/" + containerId + "/struct-map");
    }

    @Override
    public String retrieveMembers(final String containerId, final SearchRetrieveRequestType filter)
        throws EscidocException, MissingMethodParameterException, AuthenticationException, AuthorizationException,
        InvalidXmlException {

        checkNotNull(containerId);
        checkNotNull(filter);

        return get(PATH + "/" + containerId + "/resources/members" + getEscidoc12Filter(filter));
    }

    @Override
    public String retrieveMembers(final String containerId, final ExplainRequestType filter) throws EscidocException,
        MissingMethodParameterException, AuthenticationException, AuthorizationException, InvalidXmlException {

        checkNotNull(containerId);
        checkNotNull(filter);

        return get(PATH + "/" + containerId + "/resources/members" + getEscidoc12Filter(filter));
    }

    @Override
    public String updateMetadataRecord(final String containerId, final String mdRecordId, final String mdRecordXml)
        throws EscidocException, XmlSchemaValidationException, ReadonlyVersionException, LockingException,
        MissingMethodParameterException, InvalidStatusException, ContainerNotFoundException, AuthenticationException,
        XmlSchemaNotFoundException, AuthorizationException, MdRecordNotFoundException, InvalidXmlException {

        checkNotNull(containerId);
        checkNotNull(mdRecordId);
        checkNotNull(mdRecordXml);

        return put(PATH + "/" + containerId + "/md-records/md-record/" + mdRecordId, mdRecordXml);
    }

    @Override
    public String createItem(final String containerId, final String itemXml) throws EscidocException,
        MissingContentException, MissingAttributeValueException, LockingException, InvalidContextException,
        ContainerNotFoundException, MissingMdRecordException, AuthenticationException,
        ReferencedResourceNotFoundException, AuthorizationException, ContextNotFoundException, InvalidContentException,
        RelationPredicateNotFoundException, ReadonlyAttributeViolationException, FileNotFoundException,
        MissingMethodParameterException, InvalidStatusException, ReadonlyElementViolationException,
        ContentModelNotFoundException, InvalidXmlException, MissingElementValueException {

        checkNotNull(containerId);
        checkNotNull(itemXml);

        return post(PATH + "/" + containerId + "/create-item", itemXml);
    }

    @Override
    public String create(final String containerXml) throws EscidocException, MissingAttributeValueException,
        MissingContentException, MissingMdRecordException, ReferencedResourceNotFoundException,
        AuthenticationException, AuthorizationException, ContextNotFoundException, InvalidContentException,
        RelationPredicateNotFoundException, ReadonlyAttributeViolationException, FileNotFoundException,
        MissingMethodParameterException, InvalidStatusException, ReadonlyElementViolationException,
        ContentModelNotFoundException, InvalidXmlException, MissingElementValueException {

        checkNotNull(containerXml);

        return put(PATH, containerXml);
    }

    @Override
    public String update(final String containerId, final String containerXml) throws EscidocException,
        MissingLicenceException, ReadonlyVersionException, LockingException, ComponentNotFoundException,
        MissingContentException, MissingAttributeValueException, AlreadyExistsException, InvalidContextException,
        MissingMdRecordException, ReferencedResourceNotFoundException, AuthenticationException, AuthorizationException,
        ContainerNotFoundException, InvalidContentException, OptimisticLockingException,
        RelationPredicateNotFoundException, FileNotFoundException, MissingMethodParameterException,
        NotPublishedException, InvalidStatusException, ReadonlyViolationException, InvalidXmlException {

        checkNotNull(containerId);
        checkNotNull(containerXml);

        return put(PATH + "/" + containerId, containerXml);
    }

    @Override
    public void delete(final String containerId) throws EscidocException, LockingException,
        MissingMethodParameterException, InvalidStatusException, AuthenticationException, ContainerNotFoundException,
        AlreadyPublishedException, AuthorizationException {

        checkNotNull(containerId);

        del(PATH + "/" + containerId);
    }

    @Override
    public String lock(final String containerId, final String taskParam) throws EscidocException,
        OptimisticLockingException, SystemException, LockingException, MissingMethodParameterException,
        AuthenticationException, ContainerNotFoundException, AuthorizationException, InvalidContentException,
        InvalidXmlException {

        checkNotNull(containerId);

        return post(PATH + "/" + containerId + "/lock", taskParam);
    }

    @Override
    public String unlock(final String containerId, final String taskParam) throws EscidocException,
        OptimisticLockingException, SystemException, LockingException, MissingMethodParameterException,
        AuthenticationException, ContainerNotFoundException, AuthorizationException, InvalidXmlException {

        checkNotNull(containerId);

        return post(PATH + "/" + containerId + "/unlock", taskParam);
    }

    @Override
    public String release(final String containerId, final String taskParam) throws EscidocException,
        OptimisticLockingException, SystemException, ReadonlyVersionException, LockingException,
        MissingMethodParameterException, InvalidStatusException, ReadonlyViolationException, AuthenticationException,
        ContainerNotFoundException, AuthorizationException, InvalidXmlException {

        checkNotNull(containerId);

        return post(PATH + "/" + containerId + "/release", taskParam);
    }

    @Override
    public String retrieve(final String containerId) throws EscidocException, MissingMethodParameterException,
        AuthenticationException, ContainerNotFoundException, AuthorizationException {

        checkNotNull(containerId);

        return get(PATH + "/" + containerId);
    }

    @Override
    public String submit(final String containerId, final String taskParam) throws EscidocException,
        OptimisticLockingException, SystemException, ReadonlyVersionException, LockingException,
        MissingMethodParameterException, InvalidStatusException, ReadonlyViolationException, AuthenticationException,
        ContainerNotFoundException, AuthorizationException, InvalidXmlException {

        checkNotNull(containerId);

        return post(PATH + "/" + containerId + "/submit", taskParam);
    }

    @Override
    public String createMdRecord(final String containerId, final String xmlData) throws EscidocException,
        LockingException, MissingAttributeValueException, MissingMethodParameterException, InvalidStatusException,
        AuthenticationException, XmlSchemaNotFoundException, ContainerNotFoundException, AuthorizationException,
        InvalidXmlException {

        checkNotNull(containerId);

        return put(PATH + "/" + containerId + "/md-records/md-record", xmlData);
    }

    @Override
    public String retrieveMdRecord(final String containerId, final String mdRecordId) throws EscidocException,
        MissingMethodParameterException, AuthenticationException, ContainerNotFoundException, AuthorizationException,
        MdRecordNotFoundException {

        checkNotNull(containerId);
        checkNotNull(mdRecordId);

        return get(PATH + "/" + containerId + "/md-records/md-record/" + mdRecordId);
    }

    @Override
    public String retrieveMdRecords(final String containerId) throws EscidocException, MissingMethodParameterException,
        AuthenticationException, ContainerNotFoundException, AuthorizationException {

        checkNotNull(containerId);

        return get(PATH + "/" + containerId + "/md-records");
    }

    @Override
    public String retrieveProperties(final String containerId) throws EscidocException,
        MissingMethodParameterException, AuthenticationException, ContainerNotFoundException, AuthorizationException {

        checkNotNull(containerId);

        return get(PATH + "/" + containerId + "/properties");
    }

    @Override
    public String retrieveVersionHistory(final String containerId) throws EscidocException,
        MissingMethodParameterException, AuthenticationException, ContainerNotFoundException, AuthorizationException,
        MdRecordNotFoundException {

        checkNotNull(containerId);

        return get(PATH + "/" + containerId + "/resources/version-history");
    }

    @Override
    public String retrieveRelations(final String containerId) throws EscidocException, MissingMethodParameterException,
        AuthenticationException, ContainerNotFoundException, AuthorizationException {

        checkNotNull(containerId);

        return get(PATH + "/" + containerId + "/relations");
    }

    @Override
    public String revise(final String containerId, final String taskParam) throws EscidocException,
        OptimisticLockingException, SystemException, ReadonlyVersionException, LockingException,
        MissingMethodParameterException, InvalidStatusException, ReadonlyViolationException, AuthenticationException,
        AuthorizationException, ContainerNotFoundException, InvalidContentException, InvalidXmlException {

        checkNotNull(containerId);

        return post(PATH + "/" + containerId + "/revise", taskParam);
    }

    @Override
    public String withdraw(final String containerId, final String taskParam) throws EscidocException,
        ReadonlyVersionException, LockingException, AlreadyWithdrawnException, AuthenticationException,
        ContainerNotFoundException, AuthorizationException, OptimisticLockingException,
        MissingMethodParameterException, NotPublishedException, InvalidStatusException, ReadonlyViolationException,
        InvalidXmlException {

        checkNotNull(containerId);

        return post(PATH + "/" + containerId + "/withdraw", taskParam);
    }

    @Override
    public String moveToContext(final String containerId, final String taskParam) throws EscidocException,
        LockingException, MissingMethodParameterException, InvalidStatusException, AuthenticationException,
        ContainerNotFoundException, AuthorizationException, ContextNotFoundException, InvalidContentException {

        checkNotNull(containerId);

        return post(PATH + "/" + containerId + "/move-to-context", taskParam);
    }

    @Override
    public String retrieveContainers(final SearchRetrieveRequestType filter) throws EscidocException,
        MissingMethodParameterException, AuthenticationException, AuthorizationException, InvalidXmlException {

        checkNotNull(filter);

        return get(PATH + "s" + getEscidoc12Filter(filter));
    }

    @Override
    public String retrieveContainers(final ExplainRequestType filter) throws EscidocException,
        MissingMethodParameterException, AuthenticationException, AuthorizationException, InvalidXmlException {

        checkNotNull(filter);

        return get(PATH + "s" + getEscidoc12Filter(filter));
    }

    @Override
    public String assignVersionPid(final String containerId, final String taskParam) throws EscidocException,
        OptimisticLockingException, SystemException, LockingException, MissingMethodParameterException,
        InvalidStatusException, AuthenticationException, ContainerNotFoundException, AuthorizationException,
        InvalidXmlException {

        checkNotNull(containerId);

        return post(PATH + "/" + containerId + "/assign-version-pid", taskParam);
    }

    @Override
    public String assignObjectPid(final String containerId, final String taskParam) throws EscidocException,
        OptimisticLockingException, SystemException, LockingException, MissingMethodParameterException,
        InvalidStatusException, AuthenticationException, ContainerNotFoundException, AuthorizationException,
        InvalidXmlException {

        checkNotNull(containerId);

        return post(PATH + "/" + containerId + "/assign-object-pid", taskParam);
    }

    @Override
    public String addContentRelations(final String containerId, final String taskParam) throws EscidocException,
        ReadonlyVersionException, LockingException, AlreadyExistsException, AuthenticationException,
        ReferencedResourceNotFoundException, AuthorizationException, ContainerNotFoundException,
        InvalidContentException, OptimisticLockingException, RelationPredicateNotFoundException,
        ReadonlyAttributeViolationException, MissingMethodParameterException, InvalidStatusException,
        ReadonlyViolationException, MissingElementValueException, InvalidXmlException {

        checkNotNull(containerId);

        return post(PATH + "/" + containerId + "/content-relations/add", taskParam);
    }

    @Override
    public String removeContentRelations(final String containerId, final String taskParam) throws EscidocException,
        ContentRelationNotFoundException, ReadonlyVersionException, LockingException, AuthenticationException,
        ContainerNotFoundException, AuthorizationException, InvalidContentException, OptimisticLockingException,
        MissingMethodParameterException, InvalidStatusException, ReadonlyViolationException, AlreadyDeletedException,
        InvalidXmlException, MissingElementValueException {

        checkNotNull(containerId);

        return post(PATH + "/" + containerId + "/content-relations/remove", taskParam);
    }

    @Override
    public String createMetadataRecord(final String id, final String xmlData) throws EscidocException,
        AuthorizationException, AuthenticationException, InvalidXmlException, LockingException,
        ContainerNotFoundException, MissingMethodParameterException {

        checkNotNull(id);
        checkNotNull(xmlData);

        // FIXME
        throw new UnsupportedOperationException("Not yet implemented.");
    }

    @Override
    public String retrieveParents(final String containerId) throws EscidocException, AuthorizationException,
        AuthenticationException, MissingMethodParameterException, ContainerNotFoundException {

        checkNotNull(containerId);

        return get(PATH + "/" + containerId + "/resources/parents");
    }
}
