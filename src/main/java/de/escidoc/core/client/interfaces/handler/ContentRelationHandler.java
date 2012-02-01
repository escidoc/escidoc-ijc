package de.escidoc.core.client.interfaces.handler;

import gov.loc.www.zing.srw.ExplainRequestType;
import gov.loc.www.zing.srw.SearchRetrieveRequestType;

import java.rmi.Remote;
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

/**
 * Extend the ContentRelationHandler with methods with additional parameter
 * types.
 * 
 * @author SWA
 * 
 */
public interface ContentRelationHandler extends Remote {

    /**
     * @param filter
     * @return
     
     
     * @throws MissingMethodParameterException
     * @throws AuthenticationException
     * @throws AuthorizationException
     * @throws InvalidXmlException
     */
    String retrieveContentRelations(final SearchRetrieveRequestType request) throws EscidocException,
        MissingMethodParameterException, AuthenticationException, AuthorizationException, InvalidXmlException;

    /**
     * @param filter
     * @return
     
     
     * @throws MissingMethodParameterException
     * @throws AuthenticationException
     * @throws AuthorizationException
     * @throws InvalidXmlException
     */
    String retrieveContentRelations(final ExplainRequestType request) throws EscidocException,
        MissingMethodParameterException, AuthenticationException, AuthorizationException, InvalidXmlException;

    /**
     * @param contentRelationXml
     * @return
     
     
     * @throws MissingAttributeValueException
     * @throws MissingContentException
     * @throws MissingMdRecordException
     * @throws ReferencedResourceNotFoundException
     * @throws AuthenticationException
     * @throws AuthorizationException
     * @throws ContentRelationNotFoundException
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
    String create(final String contentRelationXml) throws EscidocException, MissingAttributeValueException,
        MissingContentException, MissingMdRecordException, ReferencedResourceNotFoundException,
        AuthenticationException, AuthorizationException, ContentRelationNotFoundException, InvalidContentException,
        RelationPredicateNotFoundException, ReadonlyAttributeViolationException, FileNotFoundException,
        MissingMethodParameterException, InvalidStatusException, ReadonlyElementViolationException,
        ContentModelNotFoundException, InvalidXmlException, MissingElementValueException;

    /**
     * @param contentRelationId
     
     
     * @throws LockingException
     * @throws MissingMethodParameterException
     * @throws InvalidStatusException
     * @throws AuthenticationException
     * @throws ContentRelationNotFoundException
     * @throws AlreadyPublishedException
     * @throws AuthorizationException
     */
    void delete(final String contentRelationId) throws EscidocException, LockingException,
        MissingMethodParameterException, InvalidStatusException, AuthenticationException,
        ContentRelationNotFoundException, AlreadyPublishedException, AuthorizationException;

    /**
     * @param contentRelationId
     * @param contentRelationXml
     * @return
     
     
     * @throws MissingLicenceException
     * @throws ReadonlyVersionException
     * @throws LockingException
     * @throws ComponentNotFoundException
     * @throws MissingContentException
     * @throws MissingAttributeValueException
     * @throws AlreadyExistsException
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
     */
    String update(final String contentRelationId, final String contentRelationXml) throws EscidocException,
        MissingLicenceException, ReadonlyVersionException, LockingException, ComponentNotFoundException,
        MissingContentException, MissingAttributeValueException, AlreadyExistsException, MissingMdRecordException,
        ReferencedResourceNotFoundException, AuthenticationException, AuthorizationException,
        ContentRelationNotFoundException, InvalidContentException, OptimisticLockingException,
        RelationPredicateNotFoundException, FileNotFoundException, MissingMethodParameterException,
        NotPublishedException, InvalidStatusException, ReadonlyViolationException, InvalidXmlException;

    /**
     * @param contentRelationId
     * @return
     
     
     * @throws MissingMethodParameterException
     * @throws AuthenticationException
     * @throws ContentRelationNotFoundException
     * @throws AuthorizationException
     */
    String retrieve(final String contentRelationId) throws EscidocException, MissingMethodParameterException,
        AuthenticationException, ContentRelationNotFoundException, AuthorizationException;

    /**
     * @param contentRelationId
     * @return
     
     
     * @throws MissingMethodParameterException
     * @throws AuthenticationException
     * @throws ContentRelationNotFoundException
     * @throws AuthorizationException
     */
    String retrieveProperties(final String contentRelationId) throws EscidocException, MissingMethodParameterException,
        AuthenticationException, ContentRelationNotFoundException, AuthorizationException;

    /**
     * @param filter
     * @return
     
     
     * @throws MissingMethodParameterException
     * @throws AuthenticationException
     * @throws AuthorizationException
     * @throws InvalidXmlException
     */
    String retrieveContentRelations(final String filter) throws EscidocException, MissingMethodParameterException,
        AuthenticationException, AuthorizationException, InvalidXmlException;

    /**
     * @param contentRelationId
     * @param taskParam
     * @return
     
     * @throws OptimisticLockingException
     * @throws PidAlreadyAssignedException
     * @throws ContentRelationNotFoundException
     
     * @throws MissingMethodParameterException
     * @throws LockingException
     * @throws AuthenticationException
     * @throws AuthorizationException
     * @throws InvalidXmlException
     */
    String assignObjectPid(final String contentRelationId, final String taskParam) throws EscidocException,
        OptimisticLockingException, PidAlreadyAssignedException, ContentRelationNotFoundException, SystemException,
        MissingMethodParameterException, LockingException, AuthenticationException, AuthorizationException,
        InvalidXmlException;

    /**
     * @param contentRelationId
     * @param taskParam
     * @return
     
     * @throws OptimisticLockingException
     * @throws ContentRelationNotFoundException
     
     * @throws MissingMethodParameterException
     * @throws LockingException
     * @throws InvalidStatusException
     * @throws AuthenticationException
     * @throws AuthorizationException
     * @throws InvalidXmlException
     * @throws InvalidContentException
     */
    String lock(final String contentRelationId, final String taskParam) throws EscidocException,
        OptimisticLockingException, ContentRelationNotFoundException, SystemException, MissingMethodParameterException,
        LockingException, InvalidStatusException, AuthenticationException, AuthorizationException, InvalidXmlException,
        InvalidContentException;

    /**
     * @param contentRelationId
     * @param taskParam
     * @return
     * @throws java.rmi.RemoteException
     * @throws OptimisticLockingException
     * @throws ContentRelationNotFoundException
     
     * @throws MissingMethodParameterException
     * @throws LockingException
     * @throws InvalidStatusException
     * @throws AuthenticationException
     * @throws AuthorizationException
     * @throws InvalidXmlException
     * @throws InvalidContentException
     */
    String unlock(final String contentRelationId, final String taskParam) throws EscidocException,
        OptimisticLockingException, ContentRelationNotFoundException, SystemException, MissingMethodParameterException,
        LockingException, InvalidStatusException, AuthenticationException, AuthorizationException, InvalidXmlException,
        InvalidContentException;

    /**
     * @param contentRelationId
     * @param taskParam
     * @return
     
     * @throws OptimisticLockingException
     * @throws ContentRelationNotFoundException
     
     * @throws MissingMethodParameterException
     * @throws LockingException
     * @throws InvalidStatusException
     * @throws AuthenticationException
     * @throws AuthorizationException
     * @throws InvalidXmlException
     * @throws InvalidContentException
     */
    String submit(final String contentRelationId, final String taskParam) throws EscidocException,
        OptimisticLockingException, ContentRelationNotFoundException, SystemException, MissingMethodParameterException,
        LockingException, InvalidStatusException, AuthenticationException, AuthorizationException, InvalidXmlException,
        InvalidContentException;

    /**
     * @param contentRelationId
     * @param taskParam
     * @return
     
     * @throws OptimisticLockingException
     * @throws ContentRelationNotFoundException
     
     * @throws MissingMethodParameterException
     * @throws LockingException
     * @throws InvalidStatusException
     * @throws AuthenticationException
     * @throws AuthorizationException
     * @throws InvalidXmlException
     * @throws InvalidContentException
     */
    String release(final String contentRelationId, final String taskParam) throws EscidocException,
        OptimisticLockingException, ContentRelationNotFoundException, SystemException, MissingMethodParameterException,
        LockingException, InvalidStatusException, AuthenticationException, AuthorizationException, InvalidXmlException,
        InvalidContentException;

    /**
     * @param contentRelationId
     * @param mdRecordId
     * @return
     
     * @throws ContentRelationNotFoundException
     
     * @throws AuthenticationException
     * @throws AuthorizationException
     * @throws MdRecordNotFoundException
     */
    String retrieveMdRecord(final String contentRelationId, final String mdRecordId) throws EscidocException,
        ContentRelationNotFoundException, SystemException, AuthenticationException, AuthorizationException,
        MdRecordNotFoundException;

    /**
     * @param contentRelationId
     * @return
     
     * @throws ContentRelationNotFoundException
     
     * @throws AuthenticationException
     * @throws AuthorizationException
     */
    String retrieveMdRecords(final String contentRelationId) throws EscidocException, ContentRelationNotFoundException,
        SystemException, AuthenticationException, AuthorizationException;

    /**
     * @param contentRelationId
     * @param taskParam
     * @return
     
     * @throws OptimisticLockingException
     * @throws ContentRelationNotFoundException
     
     * @throws MissingMethodParameterException
     * @throws LockingException
     * @throws InvalidStatusException
     * @throws AuthenticationException
     * @throws AuthorizationException
     * @throws InvalidXmlException
     * @throws InvalidContentException
     */
    String revise(final String contentRelationId, final String taskParam) throws EscidocException,
        OptimisticLockingException, ContentRelationNotFoundException, SystemException, MissingMethodParameterException,
        LockingException, InvalidStatusException, AuthenticationException, AuthorizationException, InvalidXmlException,
        InvalidContentException;

    /**
     * @return
     
     
     * @throws InvalidXmlException
     * @throws InvalidContentException
     */
    String retrieveRegisteredPredicates() throws EscidocException, InvalidXmlException, InvalidContentException;

    /**
     * @param contentRelationId
     * @return
     
     
     * @throws AuthorizationException
     * @throws AuthenticationException
     * @throws ContentRelationNotFoundException
     * @throws MissingMethodParameterException
     */
    String retrieveResources(final String contentRelationId) throws EscidocException, AuthorizationException,
        AuthenticationException, ContentRelationNotFoundException, MissingMethodParameterException;
}