package de.escidoc.core.client.interfaces.handler;

import gov.loc.www.zing.srw.ExplainRequestType;
import gov.loc.www.zing.srw.SearchRetrieveRequestType;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Map;

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
import de.escidoc.core.client.exceptions.application.notfound.ComponentNotFoundException;
import de.escidoc.core.client.exceptions.application.notfound.ContentModelNotFoundException;
import de.escidoc.core.client.exceptions.application.notfound.ContentRelationNotFoundException;
import de.escidoc.core.client.exceptions.application.notfound.ContentStreamNotFoundException;
import de.escidoc.core.client.exceptions.application.notfound.ContextNotFoundException;
import de.escidoc.core.client.exceptions.application.notfound.FileNotFoundException;
import de.escidoc.core.client.exceptions.application.notfound.ItemNotFoundException;
import de.escidoc.core.client.exceptions.application.notfound.MdRecordNotFoundException;
import de.escidoc.core.client.exceptions.application.notfound.ReferencedResourceNotFoundException;
import de.escidoc.core.client.exceptions.application.notfound.RelationPredicateNotFoundException;
import de.escidoc.core.client.exceptions.application.notfound.XmlSchemaNotFoundException;
import de.escidoc.core.client.exceptions.application.security.AuthenticationException;
import de.escidoc.core.client.exceptions.application.security.AuthorizationException;
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
import de.escidoc.core.resources.HttpInputStream;

/**
 * Extend the ItemHandler with methods with additional parameter types.
 * 
 * @author SWA
 * 
 */
public interface ItemHandler extends Remote {

    /**
     * @param filter
     * @return
     
     
     * @throws MissingMethodParameterException
     * @throws AuthenticationException
     * @throws AuthorizationException
     * @throws InvalidXmlException
     */
    String retrieveItems(final SearchRetrieveRequestType filter) throws EscidocException,
        MissingMethodParameterException, AuthenticationException, AuthorizationException, InvalidXmlException;

    /**
     * @param filter
     * @return
     
     
     * @throws MissingMethodParameterException
     * @throws AuthenticationException
     * @throws AuthorizationException
     * @throws InvalidXmlException
     */
    String retrieveItems(final ExplainRequestType filter) throws EscidocException, MissingMethodParameterException,
        AuthenticationException, AuthorizationException, InvalidXmlException;

    /**
     * @param itemId
     * @param contentStreamId
     * @return
     
     
     * @throws ContentStreamNotFoundException
     * @throws MissingMethodParameterException
     * @throws AuthenticationException
     * @throws ItemNotFoundException
     * @throws AuthorizationException
     */
    HttpInputStream retrieveContentStreamContent(final String itemId, final String contentStreamId)
        throws EscidocException, ContentStreamNotFoundException, MissingMethodParameterException,
        AuthenticationException, ItemNotFoundException, AuthorizationException;

    /**
     * @param itemId
     * @param componentId
     * @return
     
     
     * @throws ComponentNotFoundException
     * @throws MissingMethodParameterException
     * @throws AuthenticationException
     * @throws ItemNotFoundException
     * @throws AuthorizationException
     */
    HttpInputStream retrieveContent(final String itemId, final String componentId) throws EscidocException,
        ComponentNotFoundException, MissingMethodParameterException, AuthenticationException, ItemNotFoundException,
        AuthorizationException;

    /**
     * @param itemId
     * @param componentId
     * @param transformer
     * @param transParams
     * @return
     
     
     * @throws ComponentNotFoundException
     * @throws MissingMethodParameterException
     * @throws AuthenticationException
     * @throws ItemNotFoundException
     * @throws AuthorizationException
     */
    HttpInputStream retrieveContent(
        final String itemId, final String componentId, final String transformer, final Map<String, String[]> transParams)
        throws EscidocException, ComponentNotFoundException, MissingMethodParameterException, AuthenticationException,
        ItemNotFoundException, AuthorizationException;

    /**
     * @param itemId
     
     
     * @throws LockingException
     * @throws MissingMethodParameterException
     * @throws InvalidStatusException
     * @throws AuthenticationException
     * @throws ItemNotFoundException
     * @throws AlreadyPublishedException
     * @throws AuthorizationException
     */
    void delete(final String itemId) throws EscidocException, LockingException, MissingMethodParameterException,
        InvalidStatusException, AuthenticationException, ItemNotFoundException, AlreadyPublishedException,
        AuthorizationException;

    /**
     * @param itemXml
     * @return
     
     
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
    String create(final String itemXml) throws EscidocException, MissingAttributeValueException,
        MissingContentException, MissingMdRecordException, ReferencedResourceNotFoundException,
        AuthenticationException, AuthorizationException, ContextNotFoundException, InvalidContentException,
        RelationPredicateNotFoundException, ReadonlyAttributeViolationException, FileNotFoundException,
        MissingMethodParameterException, InvalidStatusException, ReadonlyElementViolationException,
        ContentModelNotFoundException, InvalidXmlException, MissingElementValueException;

    /**
     * @param itemId
     * @param itemXml
     * @return
     
     
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
     * @throws ItemNotFoundException
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
    String update(final String itemId, final String itemXml) throws EscidocException, MissingLicenceException,
        ReadonlyVersionException, LockingException, ComponentNotFoundException, MissingContentException,
        MissingAttributeValueException, AlreadyExistsException, InvalidContextException, MissingMdRecordException,
        ReferencedResourceNotFoundException, AuthenticationException, AuthorizationException, ItemNotFoundException,
        InvalidContentException, OptimisticLockingException, RelationPredicateNotFoundException, FileNotFoundException,
        MissingMethodParameterException, NotPublishedException, InvalidStatusException, ReadonlyViolationException,
        InvalidXmlException;

    /**
     * @param itemId
     * @param userId
     * @return
     
     * @throws OptimisticLockingException
     
     * @throws LockingException
     * @throws MissingMethodParameterException
     * @throws AuthenticationException
     * @throws ItemNotFoundException
     * @throws AuthorizationException
     * @throws InvalidContentException
     * @throws InvalidXmlException
     */
    String lock(final String itemId, final String userId) throws EscidocException, OptimisticLockingException,
        SystemException, LockingException, MissingMethodParameterException, AuthenticationException,
        ItemNotFoundException, AuthorizationException, InvalidContentException, InvalidXmlException;

    /**
     * @param itemId
     * @param userId
     * @return
     
     * @throws OptimisticLockingException
     
     * @throws LockingException
     * @throws MissingMethodParameterException
     * @throws AuthenticationException
     * @throws ItemNotFoundException
     * @throws AuthorizationException
     * @throws InvalidXmlException
     */
    String unlock(final String itemId, final String userId) throws EscidocException, OptimisticLockingException,
        SystemException, LockingException, MissingMethodParameterException, AuthenticationException,
        ItemNotFoundException, AuthorizationException, InvalidXmlException;

    /**
     * @param itemId
     * @param param
     * @return
     
     * @throws OptimisticLockingException
     
     * @throws ReadonlyVersionException
     * @throws LockingException
     * @throws MissingMethodParameterException
     * @throws InvalidStatusException
     * @throws ReadonlyViolationException
     * @throws AuthenticationException
     * @throws ItemNotFoundException
     * @throws AuthorizationException
     * @throws InvalidXmlException
     */
    String release(final String itemId, final String param) throws EscidocException, OptimisticLockingException,
        SystemException, ReadonlyVersionException, LockingException, MissingMethodParameterException,
        InvalidStatusException, ReadonlyViolationException, AuthenticationException, ItemNotFoundException,
        AuthorizationException, InvalidXmlException;

    /**
     * @param itemId
     * @return
     
     
     * @throws MissingMethodParameterException
     * @throws AuthenticationException
     * @throws ItemNotFoundException
     * @throws AuthorizationException
     */
    String retrieve(final String itemId) throws EscidocException, MissingMethodParameterException,
        AuthenticationException, ItemNotFoundException, AuthorizationException;

    /**
     * @param itemId
     * @param param
     * @return
     
     * @throws OptimisticLockingException
     
     * @throws ReadonlyVersionException
     * @throws LockingException
     * @throws MissingMethodParameterException
     * @throws InvalidStatusException
     * @throws ReadonlyViolationException
     * @throws AuthenticationException
     * @throws ItemNotFoundException
     * @throws AuthorizationException
     * @throws InvalidXmlException
     */
    String submit(final String itemId, final String param) throws EscidocException, OptimisticLockingException,
        SystemException, ReadonlyVersionException, LockingException, MissingMethodParameterException,
        InvalidStatusException, ReadonlyViolationException, AuthenticationException, ItemNotFoundException,
        AuthorizationException, InvalidXmlException;

    /**
     * @param itemId
     * @param componentXml
     * @return
     
     
     * @throws MissingAttributeValueException
     * @throws MissingContentException
     * @throws LockingException
     * @throws AuthenticationException
     * @throws ItemNotFoundException
     * @throws AuthorizationException
     * @throws InvalidContentException
     * @throws OptimisticLockingException
     * @throws FileNotFoundException
     * @throws MissingMethodParameterException
     * @throws InvalidStatusException
     * @throws ReadonlyViolationException
     * @throws InvalidXmlException
     * @throws MissingElementValueException
     */
    String createComponent(final String itemId, final String componentXml) throws EscidocException,
        MissingAttributeValueException, MissingContentException, LockingException, AuthenticationException,
        ItemNotFoundException, AuthorizationException, InvalidContentException, OptimisticLockingException,
        FileNotFoundException, MissingMethodParameterException, InvalidStatusException, ReadonlyViolationException,
        InvalidXmlException, MissingElementValueException;

    /**
     * @param itemId
     * @param componentId
     * @return
     
     
     * @throws ComponentNotFoundException
     * @throws MissingMethodParameterException
     * @throws AuthenticationException
     * @throws ItemNotFoundException
     * @throws AuthorizationException
     */
    String retrieveComponent(final String itemId, final String componentId) throws EscidocException,
        ComponentNotFoundException, MissingMethodParameterException, AuthenticationException, ItemNotFoundException,
        AuthorizationException;

    /**
     * @param itemId
     * @param componentId
     * @return
     
     
     * @throws ComponentNotFoundException
     * @throws MissingMethodParameterException
     * @throws AuthenticationException
     * @throws ItemNotFoundException
     * @throws AuthorizationException
     */
    String retrieveComponentMdRecords(final String itemId, final String componentId) throws EscidocException,
        ComponentNotFoundException, MissingMethodParameterException, AuthenticationException, ItemNotFoundException,
        AuthorizationException;

    /**
     * @param itemId
     * @param componentId
     * @param mdRecordId
     * @return
     
     
     * @throws ComponentNotFoundException
     * @throws MissingMethodParameterException
     * @throws AuthenticationException
     * @throws ItemNotFoundException
     * @throws AuthorizationException
     * @throws MdRecordNotFoundException
     */
    String retrieveComponentMdRecord(final String itemId, final String componentId, final String mdRecordId)
        throws EscidocException, ComponentNotFoundException, MissingMethodParameterException, AuthenticationException,
        ItemNotFoundException, AuthorizationException, MdRecordNotFoundException;

    /**
     * @param itemId
     * @param componentId
     * @param componentXml
     * @return
     
     
     * @throws ReadonlyVersionException
     * @throws MissingContentException
     * @throws ComponentNotFoundException
     * @throws LockingException
     * @throws MissingAttributeValueException
     * @throws AuthenticationException
     * @throws ItemNotFoundException
     * @throws AuthorizationException
     * @throws InvalidContentException
     * @throws OptimisticLockingException
     * @throws FileNotFoundException
     * @throws MissingMethodParameterException
     * @throws InvalidStatusException
     * @throws ReadonlyViolationException
     * @throws InvalidXmlException
     */
    String updateComponent(final String itemId, final String componentId, final String componentXml)
        throws EscidocException, ReadonlyVersionException, MissingContentException, ComponentNotFoundException,
        LockingException, MissingAttributeValueException, AuthenticationException, ItemNotFoundException,
        AuthorizationException, InvalidContentException, OptimisticLockingException, FileNotFoundException,
        MissingMethodParameterException, InvalidStatusException, ReadonlyViolationException, InvalidXmlException;

    /**
     * @param itemId
     * @return
     
     
     * @throws ComponentNotFoundException
     * @throws MissingMethodParameterException
     * @throws AuthenticationException
     * @throws ItemNotFoundException
     * @throws AuthorizationException
     */
    String retrieveComponents(final String itemId) throws EscidocException, ComponentNotFoundException,
        MissingMethodParameterException, AuthenticationException, ItemNotFoundException, AuthorizationException;

    /**
     * @param itemId
     * @param componentId
     * @return
     
     
     * @throws ComponentNotFoundException
     * @throws MissingMethodParameterException
     * @throws AuthenticationException
     * @throws ItemNotFoundException
     * @throws AuthorizationException
     */
    String retrieveComponentProperties(final String itemId, final String componentId) throws EscidocException,
        ComponentNotFoundException, MissingMethodParameterException, AuthenticationException, ItemNotFoundException,
        AuthorizationException;

    /**
     * @param itemId
     * @param mdRecordXml
     * @return
     
     
     * @throws LockingException
     * @throws MissingAttributeValueException
     * @throws MissingMethodParameterException
     * @throws InvalidStatusException
     * @throws AuthenticationException
     * @throws XmlSchemaNotFoundException
     * @throws ItemNotFoundException
     * @throws AuthorizationException
     * @throws InvalidXmlException
     */
    String createMdRecord(final String itemId, final String mdRecordXml) throws EscidocException, LockingException,
        MissingAttributeValueException, MissingMethodParameterException, InvalidStatusException,
        AuthenticationException, XmlSchemaNotFoundException, ItemNotFoundException, AuthorizationException,
        InvalidXmlException;

    /**
     * @param itemId
     * @param mdRecordId
     * @return
     
     
     * @throws MissingMethodParameterException
     * @throws AuthenticationException
     * @throws ItemNotFoundException
     * @throws AuthorizationException
     * @throws MdRecordNotFoundException
     */
    String retrieveMdRecord(final String itemId, final String mdRecordId) throws EscidocException,
        MissingMethodParameterException, AuthenticationException, ItemNotFoundException, AuthorizationException,
        MdRecordNotFoundException;

    /**
     * @param itemId
     * @param mdRecordId
     * @param mdRecordXml
     * @return
     
     
     * @throws ReadonlyVersionException
     * @throws LockingException
     * @throws AuthenticationException
     * @throws XmlSchemaNotFoundException
     * @throws ItemNotFoundException
     * @throws AuthorizationException
     * @throws InvalidContentException
     * @throws OptimisticLockingException
     * @throws MissingMethodParameterException
     * @throws InvalidStatusException
     * @throws ReadonlyViolationException
     * @throws MdRecordNotFoundException
     * @throws InvalidXmlException
     */
    String updateMdRecord(final String itemId, final String mdRecordId, final String mdRecordXml)
        throws EscidocException, ReadonlyVersionException, LockingException, AuthenticationException,
        XmlSchemaNotFoundException, ItemNotFoundException, AuthorizationException, InvalidContentException,
        OptimisticLockingException, MissingMethodParameterException, InvalidStatusException,
        ReadonlyViolationException, MdRecordNotFoundException, InvalidXmlException;

    /**
     * @param itemId
     * @return
     
     
     * @throws MissingMethodParameterException
     * @throws AuthenticationException
     * @throws ItemNotFoundException
     * @throws AuthorizationException
     */
    String retrieveMdRecords(final String itemId) throws EscidocException, MissingMethodParameterException,
        AuthenticationException, ItemNotFoundException, AuthorizationException;

    /**
     * @param itemId
     * @return
     
     
     * @throws MissingMethodParameterException
     * @throws AuthenticationException
     * @throws ItemNotFoundException
     * @throws AuthorizationException
     */
    String retrieveContentStreams(final String itemId) throws EscidocException, MissingMethodParameterException,
        AuthenticationException, ItemNotFoundException, AuthorizationException;

    /**
     * @param itemId
     * @param contentStreamId
     * @return
     
     
     * @throws ContentStreamNotFoundException
     * @throws MissingMethodParameterException
     * @throws AuthenticationException
     * @throws ItemNotFoundException
     * @throws AuthorizationException
     */
    String retrieveContentStream(final String itemId, final String contentStreamId) throws EscidocException,
        ContentStreamNotFoundException, MissingMethodParameterException, AuthenticationException,
        ItemNotFoundException, AuthorizationException;

    /**
     * @param itemId
     * @return
     
     
     * @throws MissingMethodParameterException
     * @throws AuthenticationException
     * @throws ItemNotFoundException
     * @throws AuthorizationException
     */
    String retrieveProperties(final String itemId) throws EscidocException, MissingMethodParameterException,
        AuthenticationException, ItemNotFoundException, AuthorizationException;

    /**
     * @param itemId
     * @return
     
     
     * @throws MissingMethodParameterException
     * @throws AuthenticationException
     * @throws ItemNotFoundException
     * @throws AuthorizationException
     */
    String retrieveVersionHistory(final String itemId) throws EscidocException, MissingMethodParameterException,
        AuthenticationException, ItemNotFoundException, AuthorizationException;

    /**
     * @param itemId
     * @return
     
     
     * @throws MissingMethodParameterException
     * @throws AuthenticationException
     * @throws ItemNotFoundException
     * @throws AuthorizationException
     */
    String retrieveRelations(final String itemId) throws EscidocException, MissingMethodParameterException,
        AuthenticationException, ItemNotFoundException, AuthorizationException;

    /**
     * @param itemId
     * @param taskParam
     * @return
     
     * @throws OptimisticLockingException
     
     * @throws ReadonlyVersionException
     * @throws LockingException
     * @throws MissingMethodParameterException
     * @throws InvalidStatusException
     * @throws ReadonlyViolationException
     * @throws AuthenticationException
     * @throws AuthorizationException
     * @throws ItemNotFoundException
     * @throws InvalidContentException
     * @throws InvalidXmlException
     */
    String revise(final String itemId, final String taskParam) throws EscidocException, OptimisticLockingException,
        SystemException, ReadonlyVersionException, LockingException, MissingMethodParameterException,
        InvalidStatusException, ReadonlyViolationException, AuthenticationException, AuthorizationException,
        ItemNotFoundException, InvalidContentException, InvalidXmlException;

    /**
     * @param itemId
     * @param taskParam
     * @return
     
     
     * @throws ReadonlyVersionException
     * @throws LockingException
     * @throws AlreadyWithdrawnException
     * @throws AuthenticationException
     * @throws ItemNotFoundException
     * @throws AuthorizationException
     * @throws OptimisticLockingException
     * @throws MissingMethodParameterException
     * @throws NotPublishedException
     * @throws InvalidStatusException
     * @throws ReadonlyViolationException
     * @throws InvalidXmlException
     */
    String withdraw(final String itemId, final String taskParam) throws EscidocException, ReadonlyVersionException,
        LockingException, AlreadyWithdrawnException, AuthenticationException, ItemNotFoundException,
        AuthorizationException, OptimisticLockingException, MissingMethodParameterException, NotPublishedException,
        InvalidStatusException, ReadonlyViolationException, InvalidXmlException;

    /**
     * @param itemId
     * @param componentId
     
     
     * @throws ComponentNotFoundException
     * @throws LockingException
     * @throws MissingMethodParameterException
     * @throws InvalidStatusException
     * @throws AuthenticationException
     * @throws ItemNotFoundException
     * @throws AuthorizationException
     */
    void deleteComponent(final String itemId, final String componentId) throws EscidocException,
        ComponentNotFoundException, LockingException, MissingMethodParameterException, InvalidStatusException,
        AuthenticationException, ItemNotFoundException, AuthorizationException;

    /**
     * @param id
     * @param taskParam
     * @return
     
     
     * @throws LockingException
     * @throws MissingMethodParameterException
     * @throws InvalidStatusException
     * @throws AuthenticationException
     * @throws ItemNotFoundException
     * @throws AuthorizationException
     * @throws ContextNotFoundException
     * @throws InvalidContentException
     */
    String moveToContext(final String id, final String taskParam) throws EscidocException, LockingException,
        MissingMethodParameterException, InvalidStatusException, AuthenticationException, ItemNotFoundException,
        AuthorizationException, ContextNotFoundException, InvalidContentException;

    /**
     * @param itemId
     * @param taskParam
     * @return
     
     * @throws OptimisticLockingException
     
     * @throws LockingException
     * @throws MissingMethodParameterException
     * @throws InvalidStatusException
     * @throws AuthenticationException
     * @throws ItemNotFoundException
     * @throws AuthorizationException
     * @throws InvalidXmlException
     */
    String assignVersionPid(final String itemId, final String taskParam) throws EscidocException,
        OptimisticLockingException, SystemException, LockingException, MissingMethodParameterException,
        InvalidStatusException, AuthenticationException, ItemNotFoundException, AuthorizationException,
        InvalidXmlException;

    /**
     * @param itemId
     * @param taskParam
     * @return
     
     * @throws OptimisticLockingException
     
     * @throws LockingException
     * @throws MissingMethodParameterException
     * @throws InvalidStatusException
     * @throws AuthenticationException
     * @throws ItemNotFoundException
     * @throws AuthorizationException
     * @throws InvalidXmlException
     */
    String assignObjectPid(final String itemId, final String taskParam) throws EscidocException,
        OptimisticLockingException, SystemException, LockingException, MissingMethodParameterException,
        InvalidStatusException, AuthenticationException, ItemNotFoundException, AuthorizationException,
        InvalidXmlException;

    /**
     * @param itemId
     * @param componentId
     * @param taskParam
     * @return
     
     * @throws OptimisticLockingException
     
     * @throws LockingException
     * @throws MissingMethodParameterException
     * @throws ComponentNotFoundException
     * @throws InvalidStatusException
     * @throws AuthenticationException
     * @throws ItemNotFoundException
     * @throws AuthorizationException
     * @throws InvalidXmlException
     */
    String assignContentPid(final String itemId, final String componentId, final String taskParam)
        throws EscidocException, OptimisticLockingException, SystemException, LockingException,
        MissingMethodParameterException, ComponentNotFoundException, InvalidStatusException, AuthenticationException,
        ItemNotFoundException, AuthorizationException, InvalidXmlException;

    /**
     * @param itemId
     * @param taskParam
     * @return
     
     
     * @throws ReadonlyVersionException
     * @throws LockingException
     * @throws AlreadyExistsException
     * @throws AuthenticationException
     * @throws ReferencedResourceNotFoundException
     * @throws AuthorizationException
     * @throws ItemNotFoundException
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
    String addContentRelations(final String itemId, final String taskParam) throws EscidocException,
        ReadonlyVersionException, LockingException, AlreadyExistsException, AuthenticationException,
        ReferencedResourceNotFoundException, AuthorizationException, ItemNotFoundException, InvalidContentException,
        OptimisticLockingException, RelationPredicateNotFoundException, ReadonlyAttributeViolationException,
        MissingMethodParameterException, InvalidStatusException, ReadonlyViolationException,
        MissingElementValueException, InvalidXmlException;

    /**
     * @param itemId
     * @param taskParam
     * @return
     
     
     * @throws ContentRelationNotFoundException
     * @throws ReadonlyVersionException
     * @throws LockingException
     * @throws AuthenticationException
     * @throws ItemNotFoundException
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
    String removeContentRelations(final String itemId, final String taskParam) throws EscidocException,
        ContentRelationNotFoundException, ReadonlyVersionException, LockingException, AuthenticationException,
        ItemNotFoundException, AuthorizationException, InvalidContentException, OptimisticLockingException,
        MissingMethodParameterException, InvalidStatusException, ReadonlyViolationException, AlreadyDeletedException,
        InvalidXmlException, MissingElementValueException;

    /**
     * @param itemId
     * @return
     
     
     * @throws AuthorizationException
     * @throws AuthenticationException
     * @throws ItemNotFoundException
     * @throws MissingMethodParameterException
     */
    String retrieveParents(final String itemId) throws EscidocException, AuthorizationException,
        AuthenticationException, ItemNotFoundException, MissingMethodParameterException;
}