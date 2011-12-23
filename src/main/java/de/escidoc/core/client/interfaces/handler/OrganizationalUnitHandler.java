package de.escidoc.core.client.interfaces.handler;

import gov.loc.www.zing.srw.ExplainRequestType;
import gov.loc.www.zing.srw.SearchRetrieveRequestType;

import java.rmi.Remote;

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
import de.escidoc.core.client.exceptions.application.notfound.ContextNotFoundException;
import de.escidoc.core.client.exceptions.application.notfound.FileNotFoundException;
import de.escidoc.core.client.exceptions.application.notfound.MdRecordNotFoundException;
import de.escidoc.core.client.exceptions.application.notfound.OrganizationalUnitNotFoundException;
import de.escidoc.core.client.exceptions.application.notfound.ReferencedResourceNotFoundException;
import de.escidoc.core.client.exceptions.application.notfound.RelationPredicateNotFoundException;
import de.escidoc.core.client.exceptions.application.notfound.XmlSchemaNotFoundException;
import de.escidoc.core.client.exceptions.application.security.AuthenticationException;
import de.escidoc.core.client.exceptions.application.security.AuthorizationException;
import de.escidoc.core.client.exceptions.application.violated.AlreadyExistsException;
import de.escidoc.core.client.exceptions.application.violated.AlreadyPublishedException;
import de.escidoc.core.client.exceptions.application.violated.LockingException;
import de.escidoc.core.client.exceptions.application.violated.NotPublishedException;
import de.escidoc.core.client.exceptions.application.violated.OptimisticLockingException;
import de.escidoc.core.client.exceptions.application.violated.OrganizationalUnitHierarchyViolationException;
import de.escidoc.core.client.exceptions.application.violated.ReadonlyAttributeViolationException;
import de.escidoc.core.client.exceptions.application.violated.ReadonlyElementViolationException;
import de.escidoc.core.client.exceptions.application.violated.ReadonlyVersionException;
import de.escidoc.core.client.exceptions.application.violated.ReadonlyViolationException;
import de.escidoc.core.client.exceptions.system.SystemException;

/**
 * Extend the OrganizationalUnitHandler with methods with additional parameter
 * types.
 * 
 * @author SWA
 * 
 */
public interface OrganizationalUnitHandler extends Remote {

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
    String retrieveOrganizationalUnits(final SearchRetrieveRequestType filter) throws EscidocException,
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
    String retrieveOrganizationalUnits(final ExplainRequestType filter) throws EscidocException,
        MissingMethodParameterException, AuthenticationException, AuthorizationException, InvalidXmlException;

    /**
     * @param ouId
     * @param taskParam
     * @return
     * 
     * @throws OptimisticLockingException
     * 
     * @throws OrganizationalUnitNotFoundException
     * @throws MissingMethodParameterException
     * @throws InvalidStatusException
     * @throws AuthenticationException
     * @throws AuthorizationException
     * @throws InvalidXmlException
     */
    String close(final String ouId, final String taskParam) throws EscidocException, OptimisticLockingException,
        SystemException, OrganizationalUnitNotFoundException, MissingMethodParameterException, InvalidStatusException,
        AuthenticationException, AuthorizationException, InvalidXmlException;

    /**
     * @param ouId
     * @param taskParam
     * @return
     * 
     * @throws OptimisticLockingException
     * 
     * @throws OrganizationalUnitNotFoundException
     * @throws MissingMethodParameterException
     * @throws InvalidStatusException
     * @throws AuthenticationException
     * @throws AuthorizationException
     * @throws InvalidXmlException
     */
    String open(final String ouId, final String taskParam) throws EscidocException, OptimisticLockingException,
        SystemException, OrganizationalUnitNotFoundException, MissingMethodParameterException, InvalidStatusException,
        AuthenticationException, AuthorizationException, InvalidXmlException;

    /**
     * @param ouId
     * @param xmlOfParents
     * @return
     * 
     * @throws OptimisticLockingException
     * 
     * @throws OrganizationalUnitNotFoundException
     * @throws MissingMethodParameterException
     * @throws OrganizationalUnitHierarchyViolationException
     * @throws InvalidStatusException
     * @throws AuthenticationException
     * @throws AuthorizationException
     * @throws InvalidXmlException
     * @throws MissingElementValueException
     */
    String updateParents(final String ouId, final String xmlOfParents) throws EscidocException,
        OptimisticLockingException, SystemException, OrganizationalUnitNotFoundException,
        MissingMethodParameterException, OrganizationalUnitHierarchyViolationException, InvalidStatusException,
        AuthenticationException, AuthorizationException, InvalidXmlException, MissingElementValueException;

    /**
     * @param ouId
     * @return
     * 
     * 
     * @throws OrganizationalUnitNotFoundException
     * @throws MissingMethodParameterException
     * @throws AuthenticationException
     * @throws AuthorizationException
     */
    String retrieveParents(final String ouId) throws EscidocException, OrganizationalUnitNotFoundException,
        MissingMethodParameterException, AuthenticationException, AuthorizationException;

    /**
     * @param ouId
     * @return
     * @throws java.rmi.RemoteException
     * 
     * @throws OrganizationalUnitNotFoundException
     * @throws MissingMethodParameterException
     * @throws AuthenticationException
     * @throws AuthorizationException
     */
    String retrieveParentObjects(final String ouId) throws EscidocException, SystemException,
        OrganizationalUnitNotFoundException, MissingMethodParameterException, AuthenticationException,
        AuthorizationException;

    /**
     * @param ouId
     * @return
     * @throws java.rmi.RemoteException
     * 
     * @throws OrganizationalUnitNotFoundException
     * @throws MissingMethodParameterException
     * @throws AuthenticationException
     * @throws AuthorizationException
     */
    String retrieveChildObjects(final String ouId) throws EscidocException, SystemException,
        OrganizationalUnitNotFoundException, MissingMethodParameterException, AuthenticationException,
        AuthorizationException;

    /**
     * @param ouId
     * @return
     * 
     * 
     * @throws OrganizationalUnitNotFoundException
     * @throws MissingMethodParameterException
     * @throws AuthenticationException
     * @throws AuthorizationException
     */
    String retrievePathList(final String ouId) throws EscidocException, OrganizationalUnitNotFoundException,
        MissingMethodParameterException, AuthenticationException, AuthorizationException;

    /**
     * @param ouId
     * 
     * 
     * @throws LockingException
     * @throws MissingMethodParameterException
     * @throws InvalidStatusException
     * @throws AuthenticationException
     * @throws OrganizationalUnitNotFoundException
     * @throws AlreadyPublishedException
     * @throws AuthorizationException
     */
    void delete(final String ouId) throws EscidocException, LockingException, MissingMethodParameterException,
        InvalidStatusException, AuthenticationException, OrganizationalUnitNotFoundException,
        AlreadyPublishedException, AuthorizationException;

    /**
     * @param organizationalUnitXml
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
    String create(final String organizationalUnitXml) throws EscidocException, MissingAttributeValueException,
        MissingContentException, MissingMdRecordException, ReferencedResourceNotFoundException,
        AuthenticationException, AuthorizationException, ContextNotFoundException, InvalidContentException,
        RelationPredicateNotFoundException, ReadonlyAttributeViolationException, FileNotFoundException,
        MissingMethodParameterException, InvalidStatusException, ReadonlyElementViolationException,
        ContentModelNotFoundException, InvalidXmlException, MissingElementValueException;

    /**
     * @param ouId
     * @param organizationalUnitXml
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
     * @throws OrganizationalUnitNotFoundException
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
    String update(final String ouId, final String organizationalUnitXml) throws EscidocException,
        MissingLicenceException, ReadonlyVersionException, LockingException, ComponentNotFoundException,
        MissingContentException, MissingAttributeValueException, AlreadyExistsException, InvalidContextException,
        MissingMdRecordException, ReferencedResourceNotFoundException, AuthenticationException, AuthorizationException,
        OrganizationalUnitNotFoundException, InvalidContentException, OptimisticLockingException,
        RelationPredicateNotFoundException, FileNotFoundException, MissingMethodParameterException,
        NotPublishedException, InvalidStatusException, ReadonlyViolationException, InvalidXmlException;

    /**
     * @param ouId
     * @param taskParam
     * @return
     * 
     * @throws OptimisticLockingException
     * 
     * @throws LockingException
     * @throws MissingMethodParameterException
     * @throws AuthenticationException
     * @throws OrganizationalUnitNotFoundException
     * @throws AuthorizationException
     * @throws InvalidContentException
     * @throws InvalidXmlException
     */
    String lock(final String ouId, final String taskParam) throws EscidocException, OptimisticLockingException,
        SystemException, LockingException, MissingMethodParameterException, AuthenticationException,
        OrganizationalUnitNotFoundException, AuthorizationException, InvalidContentException, InvalidXmlException;

    /**
     * @param ouId
     * @param taskParam
     * @return
     * 
     * @throws OptimisticLockingException
     * 
     * @throws LockingException
     * @throws MissingMethodParameterException
     * @throws AuthenticationException
     * @throws OrganizationalUnitNotFoundException
     * @throws AuthorizationException
     * @throws InvalidXmlException
     */
    String unlock(final String ouId, final String taskParam) throws EscidocException, OptimisticLockingException,
        SystemException, LockingException, MissingMethodParameterException, AuthenticationException,
        OrganizationalUnitNotFoundException, AuthorizationException, InvalidXmlException;

    /**
     * @param ouId
     * @return
     * 
     * 
     * @throws MissingMethodParameterException
     * @throws AuthenticationException
     * @throws OrganizationalUnitNotFoundException
     * @throws AuthorizationException
     */
    String retrieve(final String ouId) throws EscidocException, MissingMethodParameterException,
        AuthenticationException, OrganizationalUnitNotFoundException, AuthorizationException;

    /**
     * @param ouId
     * @return
     * 
     * 
     * @throws OrganizationalUnitNotFoundException
     * @throws MissingMethodParameterException
     * @throws AuthenticationException
     * @throws AuthorizationException
     */
    String retrieveSuccessors(final String ouId) throws EscidocException, OrganizationalUnitNotFoundException,
        MissingMethodParameterException, AuthenticationException, AuthorizationException;

    /**
     * @param ouId
     * @param mdRecordXml
     * @return
     * 
     * 
     * @throws LockingException
     * @throws MissingAttributeValueException
     * @throws MissingMethodParameterException
     * @throws InvalidStatusException
     * @throws AuthenticationException
     * @throws XmlSchemaNotFoundException
     * @throws OrganizationalUnitNotFoundException
     * @throws AuthorizationException
     * @throws InvalidXmlException
     */
    String createMdRecord(final String ouId, final String mdRecordXml) throws EscidocException, LockingException,
        MissingAttributeValueException, MissingMethodParameterException, InvalidStatusException,
        AuthenticationException, XmlSchemaNotFoundException, OrganizationalUnitNotFoundException,
        AuthorizationException, InvalidXmlException;

    /**
     * @param ouId
     * @param mdRecordId
     * @return
     * 
     * 
     * @throws MissingMethodParameterException
     * @throws AuthenticationException
     * @throws OrganizationalUnitNotFoundException
     * @throws AuthorizationException
     * @throws MdRecordNotFoundException
     */
    String retrieveMdRecord(final String ouId, final String mdRecordId) throws EscidocException,
        MissingMethodParameterException, AuthenticationException, OrganizationalUnitNotFoundException,
        AuthorizationException, MdRecordNotFoundException;

    /**
     * @param ouId
     * @param mdRecordsXml
     * @return
     * 
     * @throws OptimisticLockingException
     * 
     * @throws OrganizationalUnitNotFoundException
     * @throws MissingMethodParameterException
     * @throws AuthenticationException
     * @throws AuthorizationException
     * @throws InvalidXmlException
     * @throws MissingElementValueException
     */
    String updateMdRecords(final String ouId, final String mdRecordsXml) throws EscidocException,
        OptimisticLockingException, SystemException, OrganizationalUnitNotFoundException,
        MissingMethodParameterException, AuthenticationException, AuthorizationException, InvalidXmlException,
        MissingElementValueException;

    /**
     * @param ouId
     * @return
     * 
     * 
     * @throws MissingMethodParameterException
     * @throws AuthenticationException
     * @throws OrganizationalUnitNotFoundException
     * @throws AuthorizationException
     */
    String retrieveMdRecords(final String ouId) throws EscidocException, MissingMethodParameterException,
        AuthenticationException, OrganizationalUnitNotFoundException, AuthorizationException;

    /**
     * @param ouId
     * @return
     * 
     * 
     * @throws MissingMethodParameterException
     * @throws AuthenticationException
     * @throws OrganizationalUnitNotFoundException
     * @throws AuthorizationException
     */
    String retrieveProperties(final String ouId) throws EscidocException, MissingMethodParameterException,
        AuthenticationException, OrganizationalUnitNotFoundException, AuthorizationException;
}