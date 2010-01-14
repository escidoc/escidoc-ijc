package de.escidoc.core.client.rest.serviceLocator;

import java.rmi.RemoteException;

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

public class ContainerRestServiceLocator extends RestServiceMethod
    implements ContainerHandler {

    private static final String PATH_CONTAINER = "/ir/container";

    public String retrieveTocs(final String id, final String filter)
        throws RemoteException, SystemException,
        MissingMethodParameterException, ContainerNotFoundException,
        AuthenticationException, AuthorizationException, InvalidXmlException {

        return post(PATH_CONTAINER + "/" + id + "/tocs/filter", filter);
    }

    public String addTocs(final String id, final String taskParam)
        throws RemoteException, OptimisticLockingException, SystemException,
        LockingException, MissingMethodParameterException,
        MissingAttributeValueException, ContainerNotFoundException,
        InvalidContextException, AuthenticationException,
        AuthorizationException, InvalidContentException {

        return post(PATH_CONTAINER + "/" + id + "/tocs/add", taskParam);
    }

    public String createContainer(final String id, final String containerXml)
        throws RemoteException, SystemException, LockingException,
        MissingAttributeValueException, ContainerNotFoundException,
        InvalidContextException, MissingMdRecordException,
        ReferencedResourceNotFoundException, AuthenticationException,
        AuthorizationException, ContextNotFoundException,
        InvalidContentException, RelationPredicateNotFoundException,
        MissingMethodParameterException, InvalidStatusException,
        ContentModelNotFoundException, MissingElementValueException,
        InvalidXmlException {

        return post(PATH_CONTAINER + "/" + id + "/create-container",
            containerXml);
    }

    public String addMembers(final String id, final String taskParam)
        throws RemoteException, OptimisticLockingException, SystemException,
        LockingException, MissingMethodParameterException,
        MissingAttributeValueException, ContainerNotFoundException,
        InvalidContextException, AuthenticationException,
        AuthorizationException, InvalidContentException {

        return post(PATH_CONTAINER + "/" + id + "/members/add", taskParam);
    }

    public String removeMembers(final String id, final String taskParam)
        throws RemoteException, InvalidItemStatusException, SystemException,
        WorkflowViolationException, LockingException,
        AdminDescriptorViolationException, ContainerNotFoundException,
        AuthenticationException, InvalidContextStatusException,
        ItemNotFoundException, AuthorizationException,
        ContextNotFoundException, InvalidContentException,
        XmlSchemaValidationException {

        return post(PATH_CONTAINER + "/" + id + "/members/remove", taskParam);
    }

    public String retrieveStructMap(final String id) throws RemoteException,
        SystemException, MissingMethodParameterException,
        ContainerNotFoundException, AuthenticationException,
        AuthorizationException {

        return get(PATH_CONTAINER + "/" + id + "/struct-map");
    }

    public String retrieveMembers(final String id, final String filter)
        throws RemoteException, SystemException,
        MissingMethodParameterException, ContainerNotFoundException,
        AuthenticationException, AuthorizationException, InvalidXmlException {

        return post(PATH_CONTAINER + "/" + id + "/members/filter", filter);
    }

    public String updateMetadataRecord(
        final String in0, final String in1, final String in2)
        throws RemoteException, SystemException, XmlSchemaValidationException,
        ReadonlyVersionException, LockingException,
        MissingMethodParameterException, InvalidStatusException,
        ContainerNotFoundException, AuthenticationException,
        XmlSchemaNotFoundException, AuthorizationException,
        MdRecordNotFoundException, InvalidXmlException {

        return null;
    }

    public String createItem(final String id, final String itemXml)
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

        return post(PATH_CONTAINER + "/" + id + "/create-item", itemXml);
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

    public void delete(final String id) throws RemoteException,
        SystemException, LockingException, MissingMethodParameterException,
        InvalidStatusException, AuthenticationException,
        ContainerNotFoundException, AlreadyPublishedException,
        AuthorizationException {

        del(PATH_CONTAINER + "/" + id);
    }

    public String lock(final String id, final String userId)
        throws RemoteException, OptimisticLockingException, SystemException,
        LockingException, MissingMethodParameterException,
        AuthenticationException, ContainerNotFoundException,
        AuthorizationException, InvalidContentException, InvalidXmlException {

        return post(PATH_CONTAINER + "/" + id + "/lock", userId);
    }

    public String unlock(final String id, final String taskParam)
        throws RemoteException, OptimisticLockingException, SystemException,
        LockingException, MissingMethodParameterException,
        AuthenticationException, ContainerNotFoundException,
        AuthorizationException, InvalidXmlException {

        return post(PATH_CONTAINER + "/" + id + "/unlock", taskParam);
    }

    public String release(final String id, final String taskParam)
        throws RemoteException, OptimisticLockingException, SystemException,
        ReadonlyVersionException, LockingException,
        MissingMethodParameterException, InvalidStatusException,
        ReadonlyViolationException, AuthenticationException,
        ContainerNotFoundException, AuthorizationException, InvalidXmlException {

        return post(PATH_CONTAINER + "/" + id + "/release", taskParam);
    }

    public String retrieve(final String id) throws RemoteException,
        SystemException, MissingMethodParameterException,
        AuthenticationException, ContainerNotFoundException,
        AuthorizationException {

        return get(PATH_CONTAINER + "/" + id);
    }

    public String submit(final String id, final String taskParam)
        throws RemoteException, OptimisticLockingException, SystemException,
        ReadonlyVersionException, LockingException,
        MissingMethodParameterException, InvalidStatusException,
        ReadonlyViolationException, AuthenticationException,
        ContainerNotFoundException, AuthorizationException, InvalidXmlException {

        return post(PATH_CONTAINER + "/" + id + "/submit", taskParam);
    }

    public String createMetadataRecord(final String id, final String xmlData)
        throws RemoteException, SystemException, LockingException,
        MissingAttributeValueException, MissingMethodParameterException,
        InvalidStatusException, AuthenticationException,
        XmlSchemaNotFoundException, ContainerNotFoundException,
        AuthorizationException, InvalidXmlException {

        return put(PATH_CONTAINER + "/" + id + "/md-records/md-record", xmlData);
    }

    public String retrieveMdRecord(final String id, final String mdRecordId)
        throws RemoteException, SystemException,
        MissingMethodParameterException, AuthenticationException,
        ContainerNotFoundException, AuthorizationException,
        MdRecordNotFoundException {

        return get(PATH_CONTAINER + "/" + id + "/md-records/md-record/"
            + mdRecordId);
    }

    public String retrieveMdRecords(final String id) throws RemoteException,
        SystemException, MissingMethodParameterException,
        AuthenticationException, ContainerNotFoundException,
        AuthorizationException {

        return get(PATH_CONTAINER + "/" + id + "/md-records");
    }

    public String retrieveProperties(final String id) throws RemoteException,
        SystemException, MissingMethodParameterException,
        AuthenticationException, ContainerNotFoundException,
        AuthorizationException {

        return get(PATH_CONTAINER + "/" + id + "/properties");
    }

    public String retrieveVersionHistory(final String id)
        throws RemoteException, SystemException,
        MissingMethodParameterException, AuthenticationException,
        ContainerNotFoundException, AuthorizationException {

        return get(PATH_CONTAINER + "/" + id + "/resources/version-history");
    }

    public String retrieveRelations(final String id) throws RemoteException,
        SystemException, MissingMethodParameterException,
        AuthenticationException, ContainerNotFoundException,
        AuthorizationException {

        return get(PATH_CONTAINER + "/" + id + "/relations");
    }

    public String revise(final String id, final String taskParam)
        throws RemoteException, OptimisticLockingException, SystemException,
        ReadonlyVersionException, LockingException,
        MissingMethodParameterException, InvalidStatusException,
        ReadonlyViolationException, AuthenticationException,
        AuthorizationException, ContainerNotFoundException,
        InvalidContentException, InvalidXmlException {

        return post(PATH_CONTAINER + "/" + id + "/revise", taskParam);
    }

    public String withdraw(final String id, final String taskParam)
        throws RemoteException, SystemException, ReadonlyVersionException,
        LockingException, AlreadyWithdrawnException, AuthenticationException,
        ContainerNotFoundException, AuthorizationException,
        OptimisticLockingException, MissingMethodParameterException,
        NotPublishedException, InvalidStatusException,
        ReadonlyViolationException, InvalidXmlException {

        return post(PATH_CONTAINER + "/" + id + "/withdraw", taskParam);
    }

    public String moveToContext(final String id, final String taskParam)
        throws RemoteException, SystemException, LockingException,
        MissingMethodParameterException, InvalidStatusException,
        AuthenticationException, ContainerNotFoundException,
        AuthorizationException, ContextNotFoundException,
        InvalidContentException {

        return post(PATH_CONTAINER + "/" + id + "/move-to-context", taskParam);
    }

    public String retrieveContainers(final String filter)
        throws RemoteException, SystemException,
        MissingMethodParameterException, AuthenticationException,
        AuthorizationException, InvalidXmlException {

        return post(PATH_CONTAINER + "s/filter", filter);
    }

    public String assignVersionPid(final String id, final String taskParam)
        throws RemoteException, OptimisticLockingException, SystemException,
        LockingException, MissingMethodParameterException,
        InvalidStatusException, AuthenticationException,
        ContainerNotFoundException, AuthorizationException, InvalidXmlException {

        return post(PATH_CONTAINER + "/" + id + "/assign-version-pid",
            taskParam);
    }

    public String assignObjectPid(final String id, final String taskParam)
        throws RemoteException, OptimisticLockingException, SystemException,
        LockingException, MissingMethodParameterException,
        InvalidStatusException, AuthenticationException,
        ContainerNotFoundException, AuthorizationException, InvalidXmlException {

        return post(PATH_CONTAINER + "/" + id + "/assign-object-pid", taskParam);
    }

    public String addContentRelations(final String id, final String taskParam)
        throws RemoteException, SystemException, ReadonlyVersionException,
        LockingException, AlreadyExistsException, AuthenticationException,
        ReferencedResourceNotFoundException, AuthorizationException,
        ContainerNotFoundException, InvalidContentException,
        OptimisticLockingException, RelationPredicateNotFoundException,
        ReadonlyAttributeViolationException, MissingMethodParameterException,
        InvalidStatusException, ReadonlyViolationException,
        MissingElementValueException, InvalidXmlException {

        return post(PATH_CONTAINER + "/" + id + "/content-relations/add",
            taskParam);
    }

    public String removeContentRelations(final String id, final String taskParam)
        throws RemoteException, SystemException,
        ContentRelationNotFoundException, ReadonlyVersionException,
        LockingException, AuthenticationException, ContainerNotFoundException,
        AuthorizationException, InvalidContentException,
        OptimisticLockingException, MissingMethodParameterException,
        InvalidStatusException, ReadonlyViolationException,
        AlreadyDeletedException, InvalidXmlException,
        MissingElementValueException {

        return post(PATH_CONTAINER + "/" + id + "/content-relations/remove",
            taskParam);
    }

}
