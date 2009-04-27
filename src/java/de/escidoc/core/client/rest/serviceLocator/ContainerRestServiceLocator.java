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
import de.escidoc.core.common.exceptions.remote.application.notfound.ContentStreamNotFoundException;
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

    private static final String PATH_ITEM = "/ir/container";

    public String retrieveTocs(String in0, String in1) throws RemoteException,
        SystemException, MissingMethodParameterException,
        ContainerNotFoundException, AuthenticationException,
        AuthorizationException, InvalidXmlException {

        return null;
    }

    public String addTocs(String in0, String in1) throws RemoteException,
        OptimisticLockingException, SystemException, LockingException,
        MissingMethodParameterException, MissingAttributeValueException,
        ContainerNotFoundException, InvalidContextException,
        AuthenticationException, AuthorizationException,
        InvalidContentException {
        return null;
    }

    public String createContainer(String in0, String in1)
        throws RemoteException, SystemException, LockingException,
        MissingAttributeValueException, ContainerNotFoundException,
        InvalidContextException, MissingMdRecordException,
        ReferencedResourceNotFoundException, AuthenticationException,
        AuthorizationException, ContextNotFoundException,
        InvalidContentException, RelationPredicateNotFoundException,
        MissingMethodParameterException, InvalidStatusException,
        ContentModelNotFoundException, MissingElementValueException,
        InvalidXmlException {
        return null;
    }

    public String addMembers(String in0, String in1) throws RemoteException,
        OptimisticLockingException, SystemException, LockingException,
        MissingMethodParameterException, MissingAttributeValueException,
        ContainerNotFoundException, InvalidContextException,
        AuthenticationException, AuthorizationException,
        InvalidContentException {
        return null;
    }

    public String removeMembers(String in0, String in1) throws RemoteException,
        InvalidItemStatusException, SystemException,
        WorkflowViolationException, LockingException,
        AdminDescriptorViolationException, ContainerNotFoundException,
        AuthenticationException, InvalidContextStatusException,
        ItemNotFoundException, AuthorizationException,
        ContextNotFoundException, InvalidContentException,
        XmlSchemaValidationException {

        return null;
    }

    public String retrieveStructMap(String in0) throws RemoteException,
        SystemException, MissingMethodParameterException,
        ContainerNotFoundException, AuthenticationException,
        AuthorizationException {
        return null;

    }

    public String retrieveMembers(String in0, String in1)
        throws RemoteException, SystemException,
        MissingMethodParameterException, ContainerNotFoundException,
        AuthenticationException, AuthorizationException, InvalidXmlException {
        return null;
    }

    public String updateMetadataRecord(String in0, String in1, String in2)
        throws RemoteException, SystemException, XmlSchemaValidationException,
        ReadonlyVersionException, LockingException,
        MissingMethodParameterException, InvalidStatusException,
        ContainerNotFoundException, AuthenticationException,
        XmlSchemaNotFoundException, AuthorizationException,
        MdRecordNotFoundException, InvalidXmlException {

        return null;
    }

    public String createItem(final String in0, String in1)
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

        return null;
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

        return put(PATH_ITEM, containerXml);
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

        return put(PATH_ITEM + "/" + containerId, containerXml);
    }

    public void delete(String in0) throws RemoteException, SystemException,
        LockingException, MissingMethodParameterException,
        InvalidStatusException, AuthenticationException,
        ContainerNotFoundException, AlreadyPublishedException,
        AuthorizationException {

    }

    public String lock(final String id, final String userId)
        throws RemoteException, OptimisticLockingException, SystemException,
        LockingException, MissingMethodParameterException,
        AuthenticationException, ContainerNotFoundException,
        AuthorizationException, InvalidContentException, InvalidXmlException {

        return null;
    }

    public String unlock(String in0, String in1) throws RemoteException,
        OptimisticLockingException, SystemException, LockingException,
        MissingMethodParameterException, AuthenticationException,
        ContainerNotFoundException, AuthorizationException, InvalidXmlException {
        return null;
    }

    public String release(String in0, String in1) throws RemoteException,
        OptimisticLockingException, SystemException, ReadonlyVersionException,
        LockingException, MissingMethodParameterException,
        InvalidStatusException, ReadonlyViolationException,
        AuthenticationException, ContainerNotFoundException,
        AuthorizationException, InvalidXmlException {
        return null;
    }

    public String retrieve(final String itemId) throws RemoteException,
        SystemException, MissingMethodParameterException,
        AuthenticationException, ContainerNotFoundException,
        AuthorizationException {

        return get(PATH_ITEM + "/" + itemId);
    }

    public String submit(String in0, String in1) throws RemoteException,
        OptimisticLockingException, SystemException, ReadonlyVersionException,
        LockingException, MissingMethodParameterException,
        InvalidStatusException, ReadonlyViolationException,
        AuthenticationException, ContainerNotFoundException,
        AuthorizationException, InvalidXmlException {
        return null;
    }

    public String createComponent(String in0, String in1)
        throws RemoteException, SystemException,
        MissingAttributeValueException, MissingContentException,
        LockingException, AuthenticationException, ContainerNotFoundException,
        AuthorizationException, InvalidContentException,
        OptimisticLockingException, FileNotFoundException,
        MissingMethodParameterException, InvalidStatusException,
        ReadonlyViolationException, InvalidXmlException,
        MissingElementValueException {
        return null;
    }

    public String retrieveComponent(String in0, String in1)
        throws RemoteException, SystemException, ComponentNotFoundException,
        MissingMethodParameterException, AuthenticationException,
        ContainerNotFoundException, AuthorizationException {
        return null;
    }

    public String retrieveComponentMdRecords(String in0, String in1)
        throws RemoteException, SystemException, ComponentNotFoundException,
        MissingMethodParameterException, AuthenticationException,
        ContainerNotFoundException, AuthorizationException {
        return null;
    }

    public String retrieveComponentMdRecord(String in0, String in1, String in2)
        throws RemoteException, SystemException, ComponentNotFoundException,
        MissingMethodParameterException, AuthenticationException,
        ContainerNotFoundException, AuthorizationException,
        MdRecordNotFoundException {
        return null;
    }

    public String updateComponent(String in0, String in1, String in2)
        throws RemoteException, SystemException, ReadonlyVersionException,
        MissingContentException, ComponentNotFoundException, LockingException,
        MissingAttributeValueException, AuthenticationException,
        ContainerNotFoundException, AuthorizationException,
        InvalidContentException, OptimisticLockingException,
        FileNotFoundException, MissingMethodParameterException,
        InvalidStatusException, ReadonlyViolationException, InvalidXmlException {
        return null;
    }

    public String retrieveComponents(String in0) throws RemoteException,
        SystemException, ComponentNotFoundException,
        MissingMethodParameterException, AuthenticationException,
        ContainerNotFoundException, AuthorizationException {
        return null;
    }

    public String retrieveComponentProperties(String in0, String in1)
        throws RemoteException, SystemException, ComponentNotFoundException,
        MissingMethodParameterException, AuthenticationException,
        ContainerNotFoundException, AuthorizationException {
        return null;
    }

    public String createMetadataRecord(String in0, String in1)
        throws RemoteException, SystemException, LockingException,
        MissingAttributeValueException, MissingMethodParameterException,
        InvalidStatusException, AuthenticationException,
        XmlSchemaNotFoundException, ContainerNotFoundException,
        AuthorizationException, InvalidXmlException {
        return null;
    }

    public String retrieveMdRecord(String in0, String in1)
        throws RemoteException, SystemException,
        MissingMethodParameterException, AuthenticationException,
        ContainerNotFoundException, AuthorizationException,
        MdRecordNotFoundException {
        return null;
    }

    public String updateMdRecord(String in0, String in1, String in2)
        throws RemoteException, SystemException, ReadonlyVersionException,
        LockingException, AuthenticationException, XmlSchemaNotFoundException,
        ContainerNotFoundException, AuthorizationException,
        InvalidContentException, OptimisticLockingException,
        MissingMethodParameterException, InvalidStatusException,
        ReadonlyViolationException, MdRecordNotFoundException,
        InvalidXmlException {
        return null;
    }

    public String retrieveMdRecords(String in0) throws RemoteException,
        SystemException, MissingMethodParameterException,
        AuthenticationException, ContainerNotFoundException,
        AuthorizationException {
        return null;
    }

    public String retrieveContentStreams(String in0) throws RemoteException,
        SystemException, MissingMethodParameterException,
        AuthenticationException, ContainerNotFoundException,
        AuthorizationException {
        return null;
    }

    public String retrieveContentStream(String in0, String in1)
        throws RemoteException, SystemException,
        ContentStreamNotFoundException, MissingMethodParameterException,
        AuthenticationException, ContainerNotFoundException,
        AuthorizationException {
        return null;
    }

    public String retrieveProperties(String in0) throws RemoteException,
        SystemException, MissingMethodParameterException,
        AuthenticationException, ContainerNotFoundException,
        AuthorizationException {
        return null;
    }

    public String retrieveVersionHistory(String in0) throws RemoteException,
        SystemException, MissingMethodParameterException,
        AuthenticationException, ContainerNotFoundException,
        AuthorizationException {
        return null;
    }

    public String retrieveRelations(String in0) throws RemoteException,
        SystemException, MissingMethodParameterException,
        AuthenticationException, ContainerNotFoundException,
        AuthorizationException {
        return null;
    }

    public String revise(String in0, String in1) throws RemoteException,
        OptimisticLockingException, SystemException, ReadonlyVersionException,
        LockingException, MissingMethodParameterException,
        InvalidStatusException, ReadonlyViolationException,
        AuthenticationException, AuthorizationException,
        ContainerNotFoundException, InvalidContentException,
        InvalidXmlException {
        return null;
    }

    public String withdraw(String in0, String in1) throws RemoteException,
        SystemException, ReadonlyVersionException, LockingException,
        AlreadyWithdrawnException, AuthenticationException,
        ContainerNotFoundException, AuthorizationException,
        OptimisticLockingException, MissingMethodParameterException,
        NotPublishedException, InvalidStatusException,
        ReadonlyViolationException, InvalidXmlException {
        return null;
    }

    public void deleteComponent(String in0, String in1) throws RemoteException,
        SystemException, ComponentNotFoundException, LockingException,
        MissingMethodParameterException, InvalidStatusException,
        AuthenticationException, ContainerNotFoundException,
        AuthorizationException {
    }

    public String moveToContext(String in0, String in1) throws RemoteException,
        SystemException, LockingException, MissingMethodParameterException,
        InvalidStatusException, AuthenticationException,
        ContainerNotFoundException, AuthorizationException,
        ContextNotFoundException, InvalidContentException {
        return null;
    }

    public String retrieveContainers(String in0) throws RemoteException,
        SystemException, MissingMethodParameterException,
        AuthenticationException, AuthorizationException, InvalidXmlException {
        return null;
    }

    public String assignVersionPid(String in0, String in1)
        throws RemoteException, OptimisticLockingException, SystemException,
        LockingException, MissingMethodParameterException,
        InvalidStatusException, AuthenticationException,
        ContainerNotFoundException, AuthorizationException, InvalidXmlException {
        return null;
    }

    public String assignObjectPid(String in0, String in1)
        throws RemoteException, OptimisticLockingException, SystemException,
        LockingException, MissingMethodParameterException,
        InvalidStatusException, AuthenticationException,
        ContainerNotFoundException, AuthorizationException, InvalidXmlException {
        return null;
    }

    public String assignContentPid(String in0, String in1, String in2)
        throws RemoteException, OptimisticLockingException, SystemException,
        LockingException, MissingMethodParameterException,
        ComponentNotFoundException, InvalidStatusException,
        AuthenticationException, ContainerNotFoundException,
        AuthorizationException, InvalidXmlException {
        return null;
    }

    public String addContentRelations(String in0, String in1)
        throws RemoteException, SystemException, ReadonlyVersionException,
        LockingException, AlreadyExistsException, AuthenticationException,
        ReferencedResourceNotFoundException, AuthorizationException,
        ContainerNotFoundException, InvalidContentException,
        OptimisticLockingException, RelationPredicateNotFoundException,
        ReadonlyAttributeViolationException, MissingMethodParameterException,
        InvalidStatusException, ReadonlyViolationException,
        MissingElementValueException, InvalidXmlException {
        return null;
    }

    public String removeContentRelations(String in0, String in1)
        throws RemoteException, SystemException,
        ContentRelationNotFoundException, ReadonlyVersionException,
        LockingException, AuthenticationException, ContainerNotFoundException,
        AuthorizationException, InvalidContentException,
        OptimisticLockingException, MissingMethodParameterException,
        InvalidStatusException, ReadonlyViolationException,
        AlreadyDeletedException, InvalidXmlException,
        MissingElementValueException {
        return null;
    }

}
