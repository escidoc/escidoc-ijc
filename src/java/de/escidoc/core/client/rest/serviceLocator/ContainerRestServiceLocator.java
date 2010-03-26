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

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;

import de.escidoc.core.common.exceptions.remote.application.invalid.InvalidContentException;
import de.escidoc.core.common.exceptions.remote.application.invalid.InvalidContextException;
import de.escidoc.core.common.exceptions.remote.application.invalid.InvalidContextStatusException;
import de.escidoc.core.common.exceptions.remote.application.invalid.InvalidItemStatusException;
import de.escidoc.core.common.exceptions.remote.application.invalid.InvalidStatusException;
import de.escidoc.core.common.exceptions.remote.application.invalid.InvalidXmlException;
import de.escidoc.core.common.exceptions.remote.application.invalid.XmlSchemaValidationException;
import de.escidoc.core.common.exceptions.remote.application.missing.MissingAttributeValueException;
import de.escidoc.core.common.exceptions.remote.application.missing.MissingContentException;
import de.escidoc.core.common.exceptions.remote.application.missing.MissingElementValueException;
import de.escidoc.core.common.exceptions.remote.application.missing.MissingLicenceException;
import de.escidoc.core.common.exceptions.remote.application.missing.MissingMdRecordException;
import de.escidoc.core.common.exceptions.remote.application.missing.MissingMethodParameterException;
import de.escidoc.core.common.exceptions.remote.application.notfound.ComponentNotFoundException;
import de.escidoc.core.common.exceptions.remote.application.notfound.ContainerNotFoundException;
import de.escidoc.core.common.exceptions.remote.application.notfound.ContentModelNotFoundException;
import de.escidoc.core.common.exceptions.remote.application.notfound.ContentRelationNotFoundException;
import de.escidoc.core.common.exceptions.remote.application.notfound.ContextNotFoundException;
import de.escidoc.core.common.exceptions.remote.application.notfound.FileNotFoundException;
import de.escidoc.core.common.exceptions.remote.application.notfound.ItemNotFoundException;
import de.escidoc.core.common.exceptions.remote.application.notfound.MdRecordNotFoundException;
import de.escidoc.core.common.exceptions.remote.application.notfound.ReferencedResourceNotFoundException;
import de.escidoc.core.common.exceptions.remote.application.notfound.RelationPredicateNotFoundException;
import de.escidoc.core.common.exceptions.remote.application.notfound.XmlSchemaNotFoundException;
import de.escidoc.core.common.exceptions.remote.application.security.AuthenticationException;
import de.escidoc.core.common.exceptions.remote.application.security.AuthorizationException;
import de.escidoc.core.common.exceptions.remote.application.violated.AdminDescriptorViolationException;
import de.escidoc.core.common.exceptions.remote.application.violated.AlreadyDeletedException;
import de.escidoc.core.common.exceptions.remote.application.violated.AlreadyExistsException;
import de.escidoc.core.common.exceptions.remote.application.violated.AlreadyPublishedException;
import de.escidoc.core.common.exceptions.remote.application.violated.AlreadyWithdrawnException;
import de.escidoc.core.common.exceptions.remote.application.violated.LockingException;
import de.escidoc.core.common.exceptions.remote.application.violated.NotPublishedException;
import de.escidoc.core.common.exceptions.remote.application.violated.OptimisticLockingException;
import de.escidoc.core.common.exceptions.remote.application.violated.ReadonlyAttributeViolationException;
import de.escidoc.core.common.exceptions.remote.application.violated.ReadonlyElementViolationException;
import de.escidoc.core.common.exceptions.remote.application.violated.ReadonlyVersionException;
import de.escidoc.core.common.exceptions.remote.application.violated.ReadonlyViolationException;
import de.escidoc.core.common.exceptions.remote.application.violated.WorkflowViolationException;
import de.escidoc.core.common.exceptions.remote.system.SystemException;
import de.escidoc.core.om.ContainerHandler;

/**
 * REST service for Container.
 * 
 * @author SWA
 * 
 */
public class ContainerRestServiceLocator extends RestServiceMethod
    implements ContainerHandler {

    private static final String PATH_CONTAINER = "/ir/container";

    public String retrieveTocs(final String containerId, final String filter)
        throws RemoteException, SystemException,
        MissingMethodParameterException, ContainerNotFoundException,
        AuthenticationException, AuthorizationException, InvalidXmlException {

        return post(PATH_CONTAINER + "/" + containerId + "/tocs/filter", filter);
    }

    public String retrieveTocs(final String containerId, final HashMap filter)
        throws RemoteException, SystemException,
        MissingMethodParameterException, ContainerNotFoundException,
        AuthenticationException, AuthorizationException, InvalidXmlException {

        return get(PATH_CONTAINER + "/" + containerId + "/tocs/filter", filter);
    }

    public String addTocs(final String containerId, final String taskParam)
        throws RemoteException, OptimisticLockingException, SystemException,
        LockingException, MissingMethodParameterException,
        MissingAttributeValueException, ContainerNotFoundException,
        InvalidContextException, AuthenticationException,
        AuthorizationException, InvalidContentException {

        return post(PATH_CONTAINER + "/" + containerId + "/tocs/add", taskParam);
    }

    public String createContainer(
        final String containerId, final String containerXml)
        throws RemoteException, SystemException, LockingException,
        MissingAttributeValueException, ContainerNotFoundException,
        InvalidContextException, MissingMdRecordException,
        ReferencedResourceNotFoundException, AuthenticationException,
        AuthorizationException, ContextNotFoundException,
        InvalidContentException, RelationPredicateNotFoundException,
        MissingMethodParameterException, InvalidStatusException,
        ContentModelNotFoundException, MissingElementValueException,
        InvalidXmlException {

        return post(PATH_CONTAINER + "/" + containerId + "/create-container",
            containerXml);
    }

    public String addMembers(final String containerId, final String taskParam)
        throws RemoteException, OptimisticLockingException, SystemException,
        LockingException, MissingMethodParameterException,
        MissingAttributeValueException, ContainerNotFoundException,
        InvalidContextException, AuthenticationException,
        AuthorizationException, InvalidContentException {

        return post(PATH_CONTAINER + "/" + containerId + "/members/add",
            taskParam);
    }

    public String removeMembers(final String containerId, final String taskParam)
        throws RemoteException, InvalidItemStatusException, SystemException,
        WorkflowViolationException, LockingException,
        AdminDescriptorViolationException, ContainerNotFoundException,
        AuthenticationException, InvalidContextStatusException,
        ItemNotFoundException, AuthorizationException,
        ContextNotFoundException, InvalidContentException,
        XmlSchemaValidationException {

        return post(PATH_CONTAINER + "/" + containerId + "/members/remove",
            taskParam);
    }

    public String retrieveStructMap(final String containerId)
        throws RemoteException, SystemException,
        MissingMethodParameterException, ContainerNotFoundException,
        AuthenticationException, AuthorizationException {

        return get(PATH_CONTAINER + "/" + containerId + "/struct-map");
    }

    public String retrieveMembers(final String containerId, final String filter)
        throws RemoteException, SystemException,
        MissingMethodParameterException, ContainerNotFoundException,
        AuthenticationException, AuthorizationException, InvalidXmlException {

        return post(PATH_CONTAINER + "/" + containerId + "/members/filter",
            filter);
    }

    public String retrieveMembers(final String containerId, final HashMap filter)
        throws RemoteException, SystemException,
        MissingMethodParameterException, ContainerNotFoundException,
        AuthenticationException, AuthorizationException, InvalidXmlException {

        return get(PATH_CONTAINER + "/" + containerId + "/members/filter",
            filter);
    }

    public String updateMetadataRecord(
        final String containerId, final String mdRecordId,
        final String mdRecordXml) throws RemoteException, SystemException,
        XmlSchemaValidationException, ReadonlyVersionException,
        LockingException, MissingMethodParameterException,
        InvalidStatusException, ContainerNotFoundException,
        AuthenticationException, XmlSchemaNotFoundException,
        AuthorizationException, MdRecordNotFoundException, InvalidXmlException {

        return post(PATH_CONTAINER + "/" + containerId
            + "/md-records/md-record/" + mdRecordId, mdRecordXml);
    }

    public String createItem(final String containerId, final String itemXml)
        throws RemoteException, SystemException, MissingContentException,
        MissingAttributeValueException, LockingException,
        InvalidContextException, ContainerNotFoundException,
        MissingMdRecordException, AuthenticationException,
        ReferencedResourceNotFoundException, AuthorizationException,
        ContextNotFoundException, InvalidContentException,
        RelationPredicateNotFoundException,
        ReadonlyAttributeViolationException, FileNotFoundException,
        MissingMethodParameterException, InvalidStatusException,
        ReadonlyElementViolationException, ContentModelNotFoundException,
        InvalidXmlException, MissingElementValueException {

        return post(PATH_CONTAINER + "/" + containerId + "/create-item",
            itemXml);
    }

    public String create(final String containerXml) throws RemoteException,
        SystemException, MissingAttributeValueException,
        MissingContentException, MissingMdRecordException,
        ReferencedResourceNotFoundException, AuthenticationException,
        AuthorizationException, ContextNotFoundException,
        InvalidContentException, RelationPredicateNotFoundException,
        ReadonlyAttributeViolationException, FileNotFoundException,
        MissingMethodParameterException, InvalidStatusException,
        ReadonlyElementViolationException, ContentModelNotFoundException,
        InvalidXmlException, MissingElementValueException {

        return put(PATH_CONTAINER, containerXml);
    }

    public String update(final String containerId, final String containerXml)
        throws RemoteException, SystemException, MissingLicenceException,
        ReadonlyVersionException, LockingException, ComponentNotFoundException,
        MissingContentException, MissingAttributeValueException,
        AlreadyExistsException, InvalidContextException,
        MissingMdRecordException, ReferencedResourceNotFoundException,
        AuthenticationException, AuthorizationException,
        ContainerNotFoundException, InvalidContentException,
        OptimisticLockingException, RelationPredicateNotFoundException,
        FileNotFoundException, MissingMethodParameterException,
        NotPublishedException, InvalidStatusException,
        ReadonlyViolationException, InvalidXmlException {

        return put(PATH_CONTAINER + "/" + containerId, containerXml);
    }

    public void delete(final String containerId) throws RemoteException,
        SystemException, LockingException, MissingMethodParameterException,
        InvalidStatusException, AuthenticationException,
        ContainerNotFoundException, AlreadyPublishedException,
        AuthorizationException {

        del(PATH_CONTAINER + "/" + containerId);
    }

    public String lock(final String containerId, final String userId)
        throws RemoteException, OptimisticLockingException, SystemException,
        LockingException, MissingMethodParameterException,
        AuthenticationException, ContainerNotFoundException,
        AuthorizationException, InvalidContentException, InvalidXmlException {

        return post(PATH_CONTAINER + "/" + containerId + "/lock", userId);
    }

    public String unlock(final String containerId, final String taskParam)
        throws RemoteException, OptimisticLockingException, SystemException,
        LockingException, MissingMethodParameterException,
        AuthenticationException, ContainerNotFoundException,
        AuthorizationException, InvalidXmlException {

        return post(PATH_CONTAINER + "/" + containerId + "/unlock", taskParam);
    }

    public String release(final String containerId, final String taskParam)
        throws RemoteException, OptimisticLockingException, SystemException,
        ReadonlyVersionException, LockingException,
        MissingMethodParameterException, InvalidStatusException,
        ReadonlyViolationException, AuthenticationException,
        ContainerNotFoundException, AuthorizationException, InvalidXmlException {

        return post(PATH_CONTAINER + "/" + containerId + "/release", taskParam);
    }

    public String retrieve(final String containerId) throws RemoteException,
        SystemException, MissingMethodParameterException,
        AuthenticationException, ContainerNotFoundException,
        AuthorizationException {

        return get(PATH_CONTAINER + "/" + containerId);
    }

    public String submit(final String containerId, final String taskParam)
        throws RemoteException, OptimisticLockingException, SystemException,
        ReadonlyVersionException, LockingException,
        MissingMethodParameterException, InvalidStatusException,
        ReadonlyViolationException, AuthenticationException,
        ContainerNotFoundException, AuthorizationException, InvalidXmlException {

        return post(PATH_CONTAINER + "/" + containerId + "/submit", taskParam);
    }

    public String createMetadataRecord(
        final String containerId, final String xmlData) throws RemoteException,
        SystemException, LockingException, MissingAttributeValueException,
        MissingMethodParameterException, InvalidStatusException,
        AuthenticationException, XmlSchemaNotFoundException,
        ContainerNotFoundException, AuthorizationException, InvalidXmlException {

        return put(
            PATH_CONTAINER + "/" + containerId + "/md-records/md-record",
            xmlData);
    }

    public String retrieveMdRecord(
        final String containerId, final String mdRecordId)
        throws RemoteException, SystemException,
        MissingMethodParameterException, AuthenticationException,
        ContainerNotFoundException, AuthorizationException,
        MdRecordNotFoundException {

        return get(PATH_CONTAINER + "/" + containerId
            + "/md-records/md-record/" + mdRecordId);
    }

    public String retrieveMdRecords(final String containerId)
        throws RemoteException, SystemException,
        MissingMethodParameterException, AuthenticationException,
        ContainerNotFoundException, AuthorizationException {

        return get(PATH_CONTAINER + "/" + containerId + "/md-records");
    }

    public String retrieveProperties(final String containerId)
        throws RemoteException, SystemException,
        MissingMethodParameterException, AuthenticationException,
        ContainerNotFoundException, AuthorizationException {

        return get(PATH_CONTAINER + "/" + containerId + "/properties");
    }

    public String retrieveVersionHistory(final String containerId)
        throws RemoteException, SystemException,
        MissingMethodParameterException, AuthenticationException,
        ContainerNotFoundException, AuthorizationException,
        MdRecordNotFoundException {

        return get(PATH_CONTAINER + "/" + containerId
            + "/resources/version-history");
    }

    public String retrieveRelations(final String containerId)
        throws RemoteException, SystemException,
        MissingMethodParameterException, AuthenticationException,
        ContainerNotFoundException, AuthorizationException {

        return get(PATH_CONTAINER + "/" + containerId + "/relations");
    }

    public String revise(final String containerId, final String taskParam)
        throws RemoteException, OptimisticLockingException, SystemException,
        ReadonlyVersionException, LockingException,
        MissingMethodParameterException, InvalidStatusException,
        ReadonlyViolationException, AuthenticationException,
        AuthorizationException, ContainerNotFoundException,
        InvalidContentException, InvalidXmlException {

        return post(PATH_CONTAINER + "/" + containerId + "/revise", taskParam);
    }

    public String withdraw(final String containerId, final String taskParam)
        throws RemoteException, SystemException, ReadonlyVersionException,
        LockingException, AlreadyWithdrawnException, AuthenticationException,
        ContainerNotFoundException, AuthorizationException,
        OptimisticLockingException, MissingMethodParameterException,
        NotPublishedException, InvalidStatusException,
        ReadonlyViolationException, InvalidXmlException {

        return post(PATH_CONTAINER + "/" + containerId + "/withdraw", taskParam);
    }

    public String moveToContext(final String containerId, final String taskParam)
        throws RemoteException, SystemException, LockingException,
        MissingMethodParameterException, InvalidStatusException,
        AuthenticationException, ContainerNotFoundException,
        AuthorizationException, ContextNotFoundException,
        InvalidContentException {

        return post(PATH_CONTAINER + "/" + containerId + "/move-to-context",
            taskParam);
    }

    public String retrieveContainers(final String filter)
        throws RemoteException, SystemException,
        MissingMethodParameterException, AuthenticationException,
        AuthorizationException, InvalidXmlException {

        return post(PATH_CONTAINER + "s/filter", filter);
    }

    public String retrieveContainers(final HashMap filter)
        throws RemoteException, SystemException,
        MissingMethodParameterException, AuthenticationException,
        AuthorizationException, InvalidXmlException {

        return get(PATH_CONTAINER + "s/filter", (Map<String, String[]>) filter);
    }

    public String assignVersionPid(
        final String containerId, final String taskParam)
        throws RemoteException, OptimisticLockingException, SystemException,
        LockingException, MissingMethodParameterException,
        InvalidStatusException, AuthenticationException,
        ContainerNotFoundException, AuthorizationException, InvalidXmlException {

        return post(PATH_CONTAINER + "/" + containerId + "/assign-version-pid",
            taskParam);
    }

    public String assignObjectPid(
        final String containerId, final String taskParam)
        throws RemoteException, OptimisticLockingException, SystemException,
        LockingException, MissingMethodParameterException,
        InvalidStatusException, AuthenticationException,
        ContainerNotFoundException, AuthorizationException, InvalidXmlException {

        return post(PATH_CONTAINER + "/" + containerId + "/assign-object-pid",
            taskParam);
    }

    public String addContentRelations(
        final String containerId, final String taskParam)
        throws RemoteException, SystemException, ReadonlyVersionException,
        LockingException, AlreadyExistsException, AuthenticationException,
        ReferencedResourceNotFoundException, AuthorizationException,
        ContainerNotFoundException, InvalidContentException,
        OptimisticLockingException, RelationPredicateNotFoundException,
        ReadonlyAttributeViolationException, MissingMethodParameterException,
        InvalidStatusException, ReadonlyViolationException,
        MissingElementValueException, InvalidXmlException {

        return post(PATH_CONTAINER + "/" + containerId
            + "/content-relations/add", taskParam);
    }

    public String removeContentRelations(
        final String containerId, final String taskParam)
        throws RemoteException, SystemException,
        ContentRelationNotFoundException, ReadonlyVersionException,
        LockingException, AuthenticationException, ContainerNotFoundException,
        AuthorizationException, InvalidContentException,
        OptimisticLockingException, MissingMethodParameterException,
        InvalidStatusException, ReadonlyViolationException,
        AlreadyDeletedException, InvalidXmlException,
        MissingElementValueException {

        return post(PATH_CONTAINER + "/" + containerId
            + "/content-relations/remove", taskParam);
    }
}
