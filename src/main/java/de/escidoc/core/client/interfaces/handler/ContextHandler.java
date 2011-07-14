package de.escidoc.core.client.interfaces.handler;

import gov.loc.www.zing.srw.ExplainRequestType;
import gov.loc.www.zing.srw.SearchRetrieveRequestType;

import java.rmi.Remote;
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

/**
 * Extend the ContextHandler with methods with additional parameter types.
 * 
 * @author SWA
 * 
 */
public interface ContextHandler extends Remote {

    /**
     * @param filter
     * @return
     
     
     * @throws MissingMethodParameterException
     * @throws AuthenticationException
     * @throws AuthorizationException
     * @throws InvalidXmlException
     */
    String retrieveContexts(final SearchRetrieveRequestType filter) throws EscidocException,
        MissingMethodParameterException, AuthenticationException, AuthorizationException, InvalidXmlException;

    /**
     * @param filter
     * @return
     
     
     * @throws MissingMethodParameterException
     * @throws AuthenticationException
     * @throws AuthorizationException
     * @throws InvalidXmlException
     */
    String retrieveContexts(final ExplainRequestType filter) throws EscidocException, MissingMethodParameterException,
        AuthenticationException, AuthorizationException, InvalidXmlException;

    /**
     * @param contextId
     * @param filter
     * @return
     
     
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
     
     
     * @throws MissingMethodParameterException
     * @throws AuthenticationException
     * @throws AuthorizationException
     * @throws InvalidXmlException
     */
    String retrieveMembers(final String contextId, final ExplainRequestType filter) throws EscidocException,
        MissingMethodParameterException, AuthenticationException, AuthorizationException, InvalidXmlException;

    /**
     * @param contextId
     * @param taskParam
     * @return
     
     * @throws OptimisticLockingException
     
     * @throws MissingMethodParameterException
     * @throws LockingException
     * @throws InvalidStatusException
     * @throws AuthenticationException
     * @throws StreamNotFoundException
     * @throws AuthorizationException
     * @throws ContextNotFoundException
     * @throws InvalidXmlException
     */
    String close(final String contextId, final String taskParam) throws EscidocException, OptimisticLockingException,
        SystemException, MissingMethodParameterException, LockingException, InvalidStatusException,
        AuthenticationException, StreamNotFoundException, AuthorizationException, ContextNotFoundException,
        InvalidXmlException;

    /**
     * @param contextId
     * @param taskParam
     * @return
     * @throws java.rmi.RemoteException
     * @throws OptimisticLockingException
     
     * @throws MissingMethodParameterException
     * @throws LockingException
     * @throws InvalidStatusException
     * @throws AuthenticationException
     * @throws StreamNotFoundException
     * @throws AuthorizationException
     * @throws ContextNotFoundException
     * @throws InvalidXmlException
     */
    String open(final String contextId, final String taskParam) throws EscidocException, OptimisticLockingException,
        SystemException, MissingMethodParameterException, LockingException, InvalidStatusException,
        AuthenticationException, StreamNotFoundException, AuthorizationException, ContextNotFoundException,
        InvalidXmlException;

    /**
     * @param contextId
     * @param filter
     * @return
     
     
     * @throws MissingMethodParameterException
     * @throws AuthenticationException
     * @throws AuthorizationException
     * @throws ContextNotFoundException
     * @throws InvalidXmlException
     */
    String retrieveMembers(final String contextId, final String filter) throws EscidocException,
        MissingMethodParameterException, AuthenticationException, AuthorizationException, ContextNotFoundException,
        InvalidXmlException;

    /**
     * @param contextId
     * @param admId
     * @return
     
     
     * @throws AdminDescriptorNotFoundException
     * @throws MissingMethodParameterException
     * @throws AuthenticationException
     * @throws AuthorizationException
     * @throws ContextNotFoundException
     */
    String retrieveAdminDescriptor(final String contextId, final String admId) throws EscidocException,
        AdminDescriptorNotFoundException, MissingMethodParameterException, AuthenticationException,
        AuthorizationException, ContextNotFoundException;

    /**
     * @param contextId
     * @return
     
     
     * @throws MissingMethodParameterException
     * @throws AuthenticationException
     * @throws AuthorizationException
     * @throws ContextNotFoundException
     */
    String retrieveAdminDescriptors(final String contextId) throws EscidocException, MissingMethodParameterException,
        AuthenticationException, AuthorizationException, ContextNotFoundException;

    /**
     * @param id
     * @param xmlData
     * @return
     
     * @throws OptimisticLockingException
     
     * @throws AdminDescriptorNotFoundException
     * @throws MissingMethodParameterException
     * @throws AuthenticationException
     * @throws AuthorizationException
     * @throws ContextNotFoundException
     * @throws InvalidXmlException
     */
    String updateAdminDescriptor(final String id, final String xmlData) throws EscidocException,
        OptimisticLockingException, SystemException, AdminDescriptorNotFoundException, MissingMethodParameterException,
        AuthenticationException, AuthorizationException, ContextNotFoundException, InvalidXmlException;

    /**
     * @param contextId
     
     
     * @throws LockingException
     * @throws MissingMethodParameterException
     * @throws InvalidStatusException
     * @throws AuthenticationException
     * @throws ContextNotFoundException
     * @throws AlreadyPublishedException
     * @throws AuthorizationException
     */
    void delete(final String contextId) throws EscidocException, LockingException, MissingMethodParameterException,
        InvalidStatusException, AuthenticationException, ContextNotFoundException, AlreadyPublishedException,
        AuthorizationException;

    /**
     * @param contextXml
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
    String create(final String contextXml) throws EscidocException, MissingAttributeValueException,
        MissingContentException, MissingMdRecordException, ReferencedResourceNotFoundException,
        AuthenticationException, AuthorizationException, ContextNotFoundException, InvalidContentException,
        RelationPredicateNotFoundException, ReadonlyAttributeViolationException, FileNotFoundException,
        MissingMethodParameterException, InvalidStatusException, ReadonlyElementViolationException,
        ContentModelNotFoundException, InvalidXmlException, MissingElementValueException;

    /**
     * @param contextId
     * @param contextXml
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
     */
    String update(final String contextId, final String contextXml) throws EscidocException, MissingLicenceException,
        ReadonlyVersionException, LockingException, ComponentNotFoundException, MissingContentException,
        MissingAttributeValueException, AlreadyExistsException, InvalidContextException, MissingMdRecordException,
        ReferencedResourceNotFoundException, AuthenticationException, AuthorizationException, ContextNotFoundException,
        InvalidContentException, OptimisticLockingException, RelationPredicateNotFoundException, FileNotFoundException,
        MissingMethodParameterException, NotPublishedException, InvalidStatusException, ReadonlyViolationException,
        InvalidXmlException;

    /**
     * @param contextId
     * @return
     
     
     * @throws MissingMethodParameterException
     * @throws AuthenticationException
     * @throws ContextNotFoundException
     * @throws AuthorizationException
     */
    String retrieve(final String contextId) throws EscidocException, MissingMethodParameterException,
        AuthenticationException, ContextNotFoundException, AuthorizationException;

    /**
     * @param contextId
     * @return
     
     
     * @throws MissingMethodParameterException
     * @throws AuthenticationException
     * @throws ContextNotFoundException
     * @throws AuthorizationException
     */
    String retrieveProperties(final String contextId) throws EscidocException, MissingMethodParameterException,
        AuthenticationException, ContextNotFoundException, AuthorizationException;
}