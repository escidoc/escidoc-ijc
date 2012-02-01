package de.escidoc.core.client.interfaces.handler;

import gov.loc.www.zing.srw.ExplainRequestType;
import gov.loc.www.zing.srw.SearchRetrieveRequestType;

import java.rmi.Remote;

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

/**
 * 
 * @author SWA, Marko Voß
 * 
 */
public interface ContainerHandler extends Remote {

    /**
     * @param filter
     * @return
     * 
     * 
     * @throws MissingMethodParameterException
     * @throws AuthenticationException
     * @throws AuthorizationException
     * @throws InvalidXmlException
     */
    String retrieveContainers(final SearchRetrieveRequestType filter) throws EscidocException,
        MissingMethodParameterException, AuthenticationException, AuthorizationException, InvalidXmlException;

    /**
     * @param filter
     * @return
     * 
     * 
     * @throws MissingMethodParameterException
     * @throws AuthenticationException
     * @throws AuthorizationException
     * @throws InvalidXmlException
     */
    String retrieveContainers(final ExplainRequestType filter) throws EscidocException,
        MissingMethodParameterException, AuthenticationException, AuthorizationException, InvalidXmlException;

    /**
     * @param contextId
     * @param filter
     * @return
     * 
     * 
     * @throws MissingMethodParameterException
     * @throws AuthenticationException
     * @throws AuthorizationException
     * @throws InvalidXmlException
     */
    String retrieveMembers(final String contextId, final SearchRetrieveRequestType filter) throws EscidocException,
        MissingMethodParameterException, AuthenticationException, AuthorizationException, InvalidXmlException;

    /**
     * @param contextId
     * @param filter
     * @return
     * 
     * 
     * @throws MissingMethodParameterException
     * @throws AuthenticationException
     * @throws AuthorizationException
     * @throws InvalidXmlException
     */
    String retrieveMembers(final String contextId, final ExplainRequestType filter) throws EscidocException,
        MissingMethodParameterException, AuthenticationException, AuthorizationException, InvalidXmlException;

    /**
     * @param containerId
     * @param taskParam
     * @return
     * 
     * @throws OptimisticLockingException
     * @throws LockingException
     * @throws MissingMethodParameterException
     * @throws MissingAttributeValueException
     * @throws ContainerNotFoundException
     * @throws InvalidContextException
     * @throws AuthenticationException
     * @throws AuthorizationException
     * @throws InvalidContentException
     * @throws EscidocException
     */
    String addTocs(final String containerId, final String taskParam) throws OptimisticLockingException,
        SystemException, LockingException, MissingMethodParameterException, MissingAttributeValueException,
        ContainerNotFoundException, InvalidContextException, AuthenticationException, AuthorizationException,
        InvalidContentException, EscidocException;

    /**
     * @param containerId
     * @param containerXml
     * @return
     * 
     * 
     * @throws LockingException
     * @throws MissingAttributeValueException
     * @throws ContainerNotFoundException
     * @throws InvalidContextException
     * @throws MissingMdRecordException
     * @throws ReferencedResourceNotFoundException
     * @throws AuthenticationException
     * @throws AuthorizationException
     * @throws ContextNotFoundException
     * @throws InvalidContentException
     * @throws RelationPredicateNotFoundException
     * @throws MissingMethodParameterException
     * @throws InvalidStatusException
     * @throws ContentModelNotFoundException
     * @throws MissingElementValueException
     * @throws InvalidXmlException
     */
    String createContainer(final String containerId, final String containerXml) throws EscidocException,
        LockingException, MissingAttributeValueException, ContainerNotFoundException, InvalidContextException,
        MissingMdRecordException, ReferencedResourceNotFoundException, AuthenticationException, AuthorizationException,
        ContextNotFoundException, InvalidContentException, RelationPredicateNotFoundException,
        MissingMethodParameterException, InvalidStatusException, ContentModelNotFoundException,
        MissingElementValueException, InvalidXmlException;

    /**
     * @param containerId
     * @param taskParam
     * @return
     * 
     * @throws OptimisticLockingException
     * @throws LockingException
     * @throws MissingMethodParameterException
     * @throws MissingAttributeValueException
     * @throws ContainerNotFoundException
     * @throws InvalidContextException
     * @throws AuthenticationException
     * @throws AuthorizationException
     * @throws InvalidContentException
     * @throws EscidocException
     */
    String addMembers(final String containerId, final String taskParam) throws OptimisticLockingException,
        SystemException, LockingException, MissingMethodParameterException, MissingAttributeValueException,
        ContainerNotFoundException, InvalidContextException, AuthenticationException, AuthorizationException,
        InvalidContentException, EscidocException;

    /**
     * @param containerId
     * @param taskParam
     * @return
     * 
     * @throws InvalidItemStatusException
     * @throws LockingException
     * @throws AdminDescriptorViolationException
     * @throws ContainerNotFoundException
     * @throws AuthenticationException
     * @throws InvalidContextStatusException
     * @throws ItemNotFoundException
     * @throws AuthorizationException
     * @throws ContextNotFoundException
     * @throws InvalidContentException
     * @throws XmlSchemaValidationException
     * @throws EscidocException
     */
    String removeMembers(final String containerId, final String taskParam) throws EscidocException,
        InvalidItemStatusException, SystemException, LockingException, AdminDescriptorViolationException,
        ContainerNotFoundException, AuthenticationException, InvalidContextStatusException, ItemNotFoundException,
        AuthorizationException, ContextNotFoundException, InvalidContentException, XmlSchemaValidationException,
        EscidocException;

    /**
     * @param containerId
     * @return
     * 
     * 
     * @throws MissingMethodParameterException
     * @throws ContainerNotFoundException
     * @throws AuthenticationException
     * @throws AuthorizationException
     */
    String retrieveStructMap(final String containerId) throws EscidocException, MissingMethodParameterException,
        ContainerNotFoundException, AuthenticationException, AuthorizationException;

    /**
     * @param containerId
     * @param mdRecordId
     * @param mdRecordXml
     * @return
     * 
     * 
     * @throws XmlSchemaValidationException
     * @throws ReadonlyVersionException
     * @throws LockingException
     * @throws MissingMethodParameterException
     * @throws InvalidStatusException
     * @throws ContainerNotFoundException
     * @throws AuthenticationException
     * @throws XmlSchemaNotFoundException
     * @throws AuthorizationException
     * @throws MdRecordNotFoundException
     * @throws InvalidXmlException
     */
    String updateMetadataRecord(final String containerId, final String mdRecordId, final String mdRecordXml)
        throws EscidocException, XmlSchemaValidationException, ReadonlyVersionException, LockingException,
        MissingMethodParameterException, InvalidStatusException, ContainerNotFoundException, AuthenticationException,
        XmlSchemaNotFoundException, AuthorizationException, MdRecordNotFoundException, InvalidXmlException;

    /**
     * @param containerId
     * @param itemXml
     * @return
     * 
     * 
     * @throws MissingContentException
     * @throws MissingAttributeValueException
     * @throws LockingException
     * @throws InvalidContextException
     * @throws ContainerNotFoundException
     * @throws MissingMdRecordException
     * @throws AuthenticationException
     * @throws ReferencedResourceNotFoundException
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
     */
    String createItem(final String containerId, final String itemXml) throws EscidocException, MissingContentException,
        MissingAttributeValueException, LockingException, InvalidContextException, ContainerNotFoundException,
        MissingMdRecordException, AuthenticationException, ReferencedResourceNotFoundException, AuthorizationException,
        ContextNotFoundException, InvalidContentException, RelationPredicateNotFoundException,
        ReadonlyAttributeViolationException, FileNotFoundException, MissingMethodParameterException,
        InvalidStatusException, ReadonlyElementViolationException, ContentModelNotFoundException, InvalidXmlException,
        MissingElementValueException;

    /**
     * @param containerXml
     * @return
     * 
     * 
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
     */
    String create(final String containerXml) throws EscidocException, MissingAttributeValueException,
        MissingContentException, MissingMdRecordException, ReferencedResourceNotFoundException,
        AuthenticationException, AuthorizationException, ContextNotFoundException, InvalidContentException,
        RelationPredicateNotFoundException, ReadonlyAttributeViolationException, FileNotFoundException,
        MissingMethodParameterException, InvalidStatusException, ReadonlyElementViolationException,
        ContentModelNotFoundException, InvalidXmlException, MissingElementValueException;

    /**
     * @param containerId
     * @param containerXml
     * @return
     * 
     * 
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
     * @throws ContainerNotFoundException
     * @throws InvalidContentException
     * @throws OptimisticLockingException
     * @throws RelationPredicateNotFoundException
     * @throws FileNotFoundException
     * @throws MissingMethodParameterException
     * @throws NotPublishedException
     * @throws InvalidStatusException
     * @throws ReadonlyViolationException
     * @throws InvalidXmlException
     */
    String update(final String containerId, final String containerXml) throws EscidocException,
        MissingLicenceException, ReadonlyVersionException, LockingException, ComponentNotFoundException,
        MissingContentException, MissingAttributeValueException, AlreadyExistsException, InvalidContextException,
        MissingMdRecordException, ReferencedResourceNotFoundException, AuthenticationException, AuthorizationException,
        ContainerNotFoundException, InvalidContentException, OptimisticLockingException,
        RelationPredicateNotFoundException, FileNotFoundException, MissingMethodParameterException,
        NotPublishedException, InvalidStatusException, ReadonlyViolationException, InvalidXmlException;

    /**
     * @param containerId
     * 
     * 
     * @throws LockingException
     * @throws MissingMethodParameterException
     * @throws InvalidStatusException
     * @throws AuthenticationException
     * @throws ContainerNotFoundException
     * @throws AlreadyPublishedException
     * @throws AuthorizationException
     */
    void delete(final String containerId) throws EscidocException, LockingException, MissingMethodParameterException,
        InvalidStatusException, AuthenticationException, ContainerNotFoundException, AlreadyPublishedException,
        AuthorizationException;

    /**
     * @param containerId
     * @param taskParam
     * @return
     * 
     * @throws OptimisticLockingException
     * 
     * @throws LockingException
     * @throws MissingMethodParameterException
     * @throws AuthenticationException
     * @throws ContainerNotFoundException
     * @throws AuthorizationException
     * @throws InvalidContentException
     * @throws InvalidXmlException
     */
    String lock(final String containerId, final String taskParam) throws EscidocException, OptimisticLockingException,
        SystemException, LockingException, MissingMethodParameterException, AuthenticationException,
        ContainerNotFoundException, AuthorizationException, InvalidContentException, InvalidXmlException;

    /**
     * @param containerId
     * @param taskParam
     * @return
     * 
     * @throws OptimisticLockingException
     * 
     * @throws LockingException
     * @throws MissingMethodParameterException
     * @throws AuthenticationException
     * @throws ContainerNotFoundException
     * @throws AuthorizationException
     * @throws InvalidXmlException
     */
    String unlock(final String containerId, final String taskParam) throws EscidocException,
        OptimisticLockingException, SystemException, LockingException, MissingMethodParameterException,
        AuthenticationException, ContainerNotFoundException, AuthorizationException, InvalidXmlException;

    /**
     * @param containerId
     * @param taskParam
     * @return
     * 
     * @throws OptimisticLockingException
     * 
     * @throws ReadonlyVersionException
     * @throws LockingException
     * @throws MissingMethodParameterException
     * @throws InvalidStatusException
     * @throws ReadonlyViolationException
     * @throws AuthenticationException
     * @throws ContainerNotFoundException
     * @throws AuthorizationException
     * @throws InvalidXmlException
     */
    String release(final String containerId, final String taskParam) throws EscidocException,
        OptimisticLockingException, SystemException, ReadonlyVersionException, LockingException,
        MissingMethodParameterException, InvalidStatusException, ReadonlyViolationException, AuthenticationException,
        ContainerNotFoundException, AuthorizationException, InvalidXmlException;

    /**
     * @param containerId
     * @return
     * 
     * 
     * @throws MissingMethodParameterException
     * @throws AuthenticationException
     * @throws ContainerNotFoundException
     * @throws AuthorizationException
     */
    String retrieve(final String containerId) throws EscidocException, MissingMethodParameterException,
        AuthenticationException, ContainerNotFoundException, AuthorizationException;

    /**
     * @param containerId
     * @param taskParam
     * @return
     * 
     * @throws OptimisticLockingException
     * 
     * @throws ReadonlyVersionException
     * @throws LockingException
     * @throws MissingMethodParameterException
     * @throws InvalidStatusException
     * @throws ReadonlyViolationException
     * @throws AuthenticationException
     * @throws ContainerNotFoundException
     * @throws AuthorizationException
     * @throws InvalidXmlException
     */
    String submit(final String containerId, final String taskParam) throws EscidocException,
        OptimisticLockingException, SystemException, ReadonlyVersionException, LockingException,
        MissingMethodParameterException, InvalidStatusException, ReadonlyViolationException, AuthenticationException,
        ContainerNotFoundException, AuthorizationException, InvalidXmlException;

    /**
     * @param containerId
     * @param xmlData
     * @return
     * 
     * 
     * @throws LockingException
     * @throws MissingAttributeValueException
     * @throws MissingMethodParameterException
     * @throws InvalidStatusException
     * @throws AuthenticationException
     * @throws XmlSchemaNotFoundException
     * @throws ContainerNotFoundException
     * @throws AuthorizationException
     * @throws InvalidXmlException
     */
    String createMdRecord(final String containerId, final String xmlData) throws EscidocException, LockingException,
        MissingAttributeValueException, MissingMethodParameterException, InvalidStatusException,
        AuthenticationException, XmlSchemaNotFoundException, ContainerNotFoundException, AuthorizationException,
        InvalidXmlException;

    /**
     * @param containerId
     * @param mdRecordId
     * @return
     * 
     * 
     * @throws MissingMethodParameterException
     * @throws AuthenticationException
     * @throws ContainerNotFoundException
     * @throws AuthorizationException
     * @throws MdRecordNotFoundException
     */
    String retrieveMdRecord(final String containerId, final String mdRecordId) throws EscidocException,
        MissingMethodParameterException, AuthenticationException, ContainerNotFoundException, AuthorizationException,
        MdRecordNotFoundException;

    /**
     * @param containerId
     * @return
     * 
     * 
     * @throws MissingMethodParameterException
     * @throws AuthenticationException
     * @throws ContainerNotFoundException
     * @throws AuthorizationException
     */
    String retrieveMdRecords(final String containerId) throws EscidocException, MissingMethodParameterException,
        AuthenticationException, ContainerNotFoundException, AuthorizationException;

    /**
     * @param containerId
     * @return
     * 
     * 
     * @throws MissingMethodParameterException
     * @throws AuthenticationException
     * @throws ContainerNotFoundException
     * @throws AuthorizationException
     */
    String retrieveProperties(final String containerId) throws EscidocException, MissingMethodParameterException,
        AuthenticationException, ContainerNotFoundException, AuthorizationException;

    /**
     * @param containerId
     * @return
     * 
     * 
     * @throws MissingMethodParameterException
     * @throws AuthenticationException
     * @throws ContainerNotFoundException
     * @throws AuthorizationException
     * @throws MdRecordNotFoundException
     */
    String retrieveVersionHistory(final String containerId) throws EscidocException, MissingMethodParameterException,
        AuthenticationException, ContainerNotFoundException, AuthorizationException, MdRecordNotFoundException;

    /**
     * @param containerId
     * @return
     * 
     * 
     * @throws MissingMethodParameterException
     * @throws AuthenticationException
     * @throws ContainerNotFoundException
     * @throws AuthorizationException
     */
    String retrieveRelations(final String containerId) throws EscidocException, MissingMethodParameterException,
        AuthenticationException, ContainerNotFoundException, AuthorizationException;

    /**
     * @param containerId
     * @param taskParam
     * @return
     * 
     * @throws OptimisticLockingException
     * 
     * @throws ReadonlyVersionException
     * @throws LockingException
     * @throws MissingMethodParameterException
     * @throws InvalidStatusException
     * @throws ReadonlyViolationException
     * @throws AuthenticationException
     * @throws AuthorizationException
     * @throws ContainerNotFoundException
     * @throws InvalidContentException
     * @throws InvalidXmlException
     */
    String revise(final String containerId, final String taskParam) throws EscidocException,
        OptimisticLockingException, SystemException, ReadonlyVersionException, LockingException,
        MissingMethodParameterException, InvalidStatusException, ReadonlyViolationException, AuthenticationException,
        AuthorizationException, ContainerNotFoundException, InvalidContentException, InvalidXmlException;

    /**
     * @param containerId
     * @param taskParam
     * @return
     * 
     * 
     * @throws ReadonlyVersionException
     * @throws LockingException
     * @throws AlreadyWithdrawnException
     * @throws AuthenticationException
     * @throws ContainerNotFoundException
     * @throws AuthorizationException
     * @throws OptimisticLockingException
     * @throws MissingMethodParameterException
     * @throws NotPublishedException
     * @throws InvalidStatusException
     * @throws ReadonlyViolationException
     * @throws InvalidXmlException
     */
    String withdraw(final String containerId, final String taskParam) throws EscidocException,
        ReadonlyVersionException, LockingException, AlreadyWithdrawnException, AuthenticationException,
        ContainerNotFoundException, AuthorizationException, OptimisticLockingException,
        MissingMethodParameterException, NotPublishedException, InvalidStatusException, ReadonlyViolationException,
        InvalidXmlException;

    /**
     * @param containerId
     * @param taskParam
     * @return
     * 
     * 
     * @throws LockingException
     * @throws MissingMethodParameterException
     * @throws InvalidStatusException
     * @throws AuthenticationException
     * @throws ContainerNotFoundException
     * @throws AuthorizationException
     * @throws ContextNotFoundException
     * @throws InvalidContentException
     */
    String moveToContext(final String containerId, final String taskParam) throws EscidocException, LockingException,
        MissingMethodParameterException, InvalidStatusException, AuthenticationException, ContainerNotFoundException,
        AuthorizationException, ContextNotFoundException, InvalidContentException;

    /**
     * @param containerId
     * @param taskParam
     * @return
     * 
     * @throws OptimisticLockingException
     * 
     * @throws LockingException
     * @throws MissingMethodParameterException
     * @throws InvalidStatusException
     * @throws AuthenticationException
     * @throws ContainerNotFoundException
     * @throws AuthorizationException
     * @throws InvalidXmlException
     */
    String assignVersionPid(final String containerId, final String taskParam) throws EscidocException,
        OptimisticLockingException, SystemException, LockingException, MissingMethodParameterException,
        InvalidStatusException, AuthenticationException, ContainerNotFoundException, AuthorizationException,
        InvalidXmlException;

    /**
     * @param containerId
     * @param taskParam
     * @return
     * 
     * @throws OptimisticLockingException
     * @throws LockingException
     * @throws MissingMethodParameterException
     * @throws InvalidStatusException
     * @throws AuthenticationException
     * @throws ContainerNotFoundException
     * @throws AuthorizationException
     * @throws InvalidXmlException
     * @throws EscidocException
     */
    String assignObjectPid(final String containerId, final String taskParam) throws OptimisticLockingException,
        SystemException, LockingException, MissingMethodParameterException, InvalidStatusException,
        AuthenticationException, ContainerNotFoundException, AuthorizationException, InvalidXmlException,
        EscidocException;

    /**
     * @param containerId
     * @param taskParam
     * @return
     * 
     * 
     * @throws ReadonlyVersionException
     * @throws LockingException
     * @throws AlreadyExistsException
     * @throws AuthenticationException
     * @throws ReferencedResourceNotFoundException
     * @throws AuthorizationException
     * @throws ContainerNotFoundException
     * @throws InvalidContentException
     * @throws OptimisticLockingException
     * @throws RelationPredicateNotFoundException
     * @throws ReadonlyAttributeViolationException
     * @throws MissingMethodParameterException
     * @throws InvalidStatusException
     * @throws ReadonlyViolationException
     * @throws MissingElementValueException
     * @throws InvalidXmlException
     */
    String addContentRelations(final String containerId, final String taskParam) throws EscidocException,
        ReadonlyVersionException, LockingException, AlreadyExistsException, AuthenticationException,
        ReferencedResourceNotFoundException, AuthorizationException, ContainerNotFoundException,
        InvalidContentException, OptimisticLockingException, RelationPredicateNotFoundException,
        ReadonlyAttributeViolationException, MissingMethodParameterException, InvalidStatusException,
        ReadonlyViolationException, MissingElementValueException, InvalidXmlException;

    /**
     * @param containerId
     * @param taskParam
     * @return
     * 
     * 
     * @throws ContentRelationNotFoundException
     * @throws ReadonlyVersionException
     * @throws LockingException
     * @throws AuthenticationException
     * @throws ContainerNotFoundException
     * @throws AuthorizationException
     * @throws InvalidContentException
     * @throws OptimisticLockingException
     * @throws MissingMethodParameterException
     * @throws InvalidStatusException
     * @throws ReadonlyViolationException
     * @throws AlreadyDeletedException
     * @throws InvalidXmlException
     * @throws MissingElementValueException
     */
    String removeContentRelations(final String containerId, final String taskParam) throws EscidocException,
        ContentRelationNotFoundException, ReadonlyVersionException, LockingException, AuthenticationException,
        ContainerNotFoundException, AuthorizationException, InvalidContentException, OptimisticLockingException,
        MissingMethodParameterException, InvalidStatusException, ReadonlyViolationException, AlreadyDeletedException,
        InvalidXmlException, MissingElementValueException;

    /**
     * @param id
     * @param xmlData
     * @return
     * 
     * 
     * @throws AuthorizationException
     * @throws AuthenticationException
     * @throws InvalidXmlException
     * @throws LockingException
     * @throws ContainerNotFoundException
     * @throws MissingMethodParameterException
     */
    String createMetadataRecord(final String id, final String xmlData) throws EscidocException, AuthorizationException,
        AuthenticationException, InvalidXmlException, LockingException, ContainerNotFoundException,
        MissingMethodParameterException;

    /**
     * @param containerId
     * @return
     * 
     * 
     * @throws AuthorizationException
     * @throws AuthenticationException
     * @throws MissingMethodParameterException
     * @throws ContainerNotFoundException
     */
    String retrieveParents(final String containerId) throws EscidocException, AuthorizationException,
        AuthenticationException, MissingMethodParameterException, ContainerNotFoundException;
}
