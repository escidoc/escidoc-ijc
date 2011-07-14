/**
 * 
 */
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
import de.escidoc.core.client.exceptions.application.notfound.ContentStreamNotFoundException;
import de.escidoc.core.client.exceptions.application.notfound.FileNotFoundException;
import de.escidoc.core.client.exceptions.application.notfound.ReferencedResourceNotFoundException;
import de.escidoc.core.client.exceptions.application.notfound.RelationPredicateNotFoundException;
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
import de.escidoc.core.resources.HttpInputStream;

/**
 * @author MVO
 * 
 */
public interface ContentModelHandler extends Remote {

    /**
     * @param filter
     * @return
     
     
     * @throws MissingMethodParameterException
     * @throws AuthenticationException
     * @throws AuthorizationException
     * @throws InvalidXmlException
     */
    String retrieveContentModels(final SearchRetrieveRequestType filter) throws EscidocException,
        MissingMethodParameterException, AuthenticationException, AuthorizationException, InvalidXmlException;

    /**
     * @param filter
     * @return
     
     
     * @throws MissingMethodParameterException
     * @throws AuthenticationException
     * @throws AuthorizationException
     * @throws InvalidXmlException
     */
    String retrieveContentModels(final ExplainRequestType filter) throws EscidocException,
        MissingMethodParameterException, AuthenticationException, AuthorizationException, InvalidXmlException;

    /**
     * @param contentModelId
     * @param contentStreamName
     * @return
     
     * @throws ContentModelNotFoundException
     
     * @throws AuthenticationException
     * @throws AuthorizationException
     * @throws MissingMethodParameterException
     * @throws ContentStreamNotFoundException
     * @throws InvalidStatusException
     */
    HttpInputStream retrieveContentStreamContent(String contentModelId, String contentStreamName)
        throws EscidocException, ContentModelNotFoundException, SystemException, AuthenticationException,
        AuthorizationException, MissingMethodParameterException, ContentStreamNotFoundException, InvalidStatusException;

    /**
     * @param contentModelId
     
     
     * @throws LockingException
     * @throws MissingMethodParameterException
     * @throws InvalidStatusException
     * @throws AuthenticationException
     * @throws ContentModelNotFoundException
     * @throws AlreadyPublishedException
     * @throws AuthorizationException
     */
    void delete(final String contentModelId) throws EscidocException, LockingException,
        MissingMethodParameterException, InvalidStatusException, AuthenticationException,
        ContentModelNotFoundException, AlreadyPublishedException, AuthorizationException;

    /**
     * @param contentModelXml
     * @return
     
     
     * @throws MissingAttributeValueException
     * @throws MissingContentException
     * @throws MissingMdRecordException
     * @throws ReferencedResourceNotFoundException
     * @throws AuthenticationException
     * @throws AuthorizationException
     * @throws ContentModelNotFoundException
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
    String create(final String contentModelXml) throws EscidocException, MissingAttributeValueException,
        MissingContentException, MissingMdRecordException, ReferencedResourceNotFoundException,
        AuthenticationException, AuthorizationException, ContentModelNotFoundException, InvalidContentException,
        RelationPredicateNotFoundException, ReadonlyAttributeViolationException, FileNotFoundException,
        MissingMethodParameterException, InvalidStatusException, ReadonlyElementViolationException,
        ContentModelNotFoundException, InvalidXmlException, MissingElementValueException;

    /**
     * @param contentModelId
     * @param contentModelXml
     * @return
     
     
     * @throws MissingLicenceException
     * @throws ReadonlyVersionException
     * @throws LockingException
     * @throws ComponentNotFoundException
     * @throws MissingContentException
     * @throws MissingAttributeValueException
     * @throws AlreadyExistsException
     * @throws InvalidContentException
     * @throws MissingMdRecordException
     * @throws ReferencedResourceNotFoundException
     * @throws AuthenticationException
     * @throws AuthorizationException
     * @throws ContentModelNotFoundException
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
    String update(final String contentModelId, final String contentModelXml) throws EscidocException,
        MissingLicenceException, ReadonlyVersionException, LockingException, ComponentNotFoundException,
        MissingContentException, MissingAttributeValueException, AlreadyExistsException, InvalidContentException,
        MissingMdRecordException, ReferencedResourceNotFoundException, AuthenticationException, AuthorizationException,
        ContentModelNotFoundException, InvalidContentException, OptimisticLockingException,
        RelationPredicateNotFoundException, FileNotFoundException, MissingMethodParameterException,
        NotPublishedException, InvalidStatusException, ReadonlyViolationException, InvalidXmlException;

    /**
     * @param contentModelId
     * @return
     
     
     * @throws MissingMethodParameterException
     * @throws AuthenticationException
     * @throws ContentModelNotFoundException
     * @throws AuthorizationException
     */
    String retrieve(final String contentModelId) throws EscidocException, MissingMethodParameterException,
        AuthenticationException, ContentModelNotFoundException, AuthorizationException;

    /**
     * @param contentModelId
     * @param contentStreamName
     * @return
     
     
     * @throws ContentStreamNotFoundException
     * @throws MissingMethodParameterException
     * @throws AuthenticationException
     * @throws ContentModelNotFoundException
     * @throws AuthorizationException
     */
    String retrieveContentStream(final String contentModelId, final String contentStreamName) throws EscidocException,
        ContentStreamNotFoundException, MissingMethodParameterException, AuthenticationException,
        ContentModelNotFoundException, AuthorizationException;

    /**
     * @param contentModelId
     * @return
     
     
     * @throws MissingMethodParameterException
     * @throws AuthenticationException
     * @throws ContentModelNotFoundException
     * @throws AuthorizationException
     */
    String retrieveContentStreams(final String contentModelId) throws EscidocException,
        MissingMethodParameterException, AuthenticationException, ContentModelNotFoundException, AuthorizationException;

    /**
     * @param contentModelId
     * @return
     
     
     * @throws MissingMethodParameterException
     * @throws AuthenticationException
     * @throws ContentModelNotFoundException
     * @throws AuthorizationException
     */
    String retrieveProperties(final String contentModelId) throws EscidocException, MissingMethodParameterException,
        AuthenticationException, ContentModelNotFoundException, AuthorizationException;

    /**
     * @param contentModelId
     * @return
     
     
     * @throws MissingMethodParameterException
     * @throws AuthenticationException
     * @throws ContentModelNotFoundException
     * @throws AuthorizationException
     */
    String retrieveVersionHistory(final String contentModelId) throws EscidocException,
        MissingMethodParameterException, AuthenticationException, ContentModelNotFoundException, AuthorizationException;

}
