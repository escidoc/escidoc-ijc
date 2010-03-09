package de.escidoc.core.client.rest.serviceLocator;

import java.rmi.RemoteException;

import de.escidoc.core.cmm.ContentModelHandler;
import de.escidoc.core.common.exceptions.remote.application.invalid.InvalidContentException;
import de.escidoc.core.common.exceptions.remote.application.invalid.InvalidStatusException;
import de.escidoc.core.common.exceptions.remote.application.invalid.InvalidXmlException;
import de.escidoc.core.common.exceptions.remote.application.missing.MissingAttributeValueException;
import de.escidoc.core.common.exceptions.remote.application.missing.MissingContentException;
import de.escidoc.core.common.exceptions.remote.application.missing.MissingElementValueException;
import de.escidoc.core.common.exceptions.remote.application.missing.MissingLicenceException;
import de.escidoc.core.common.exceptions.remote.application.missing.MissingMdRecordException;
import de.escidoc.core.common.exceptions.remote.application.missing.MissingMethodParameterException;
import de.escidoc.core.common.exceptions.remote.application.notfound.ComponentNotFoundException;
import de.escidoc.core.common.exceptions.remote.application.notfound.ContentModelNotFoundException;
import de.escidoc.core.common.exceptions.remote.application.notfound.ContentStreamNotFoundException;
import de.escidoc.core.common.exceptions.remote.application.notfound.FileNotFoundException;
import de.escidoc.core.common.exceptions.remote.application.notfound.ReferencedResourceNotFoundException;
import de.escidoc.core.common.exceptions.remote.application.notfound.RelationPredicateNotFoundException;
import de.escidoc.core.common.exceptions.remote.application.security.AuthenticationException;
import de.escidoc.core.common.exceptions.remote.application.security.AuthorizationException;
import de.escidoc.core.common.exceptions.remote.application.violated.AlreadyExistsException;
import de.escidoc.core.common.exceptions.remote.application.violated.AlreadyPublishedException;
import de.escidoc.core.common.exceptions.remote.application.violated.LockingException;
import de.escidoc.core.common.exceptions.remote.application.violated.NotPublishedException;
import de.escidoc.core.common.exceptions.remote.application.violated.OptimisticLockingException;
import de.escidoc.core.common.exceptions.remote.application.violated.ReadonlyAttributeViolationException;
import de.escidoc.core.common.exceptions.remote.application.violated.ReadonlyElementViolationException;
import de.escidoc.core.common.exceptions.remote.application.violated.ReadonlyVersionException;
import de.escidoc.core.common.exceptions.remote.application.violated.ReadonlyViolationException;
import de.escidoc.core.common.exceptions.remote.system.SystemException;

/**
 * REST Service Connector.
 * 
 * @author SWA
 * 
 */
public class ContentModelRestServiceLocator extends RestServiceMethod
    implements ContentModelHandler {

    private static final String PATH_CONTENT_MODEL = "/cmm/content-model";

    /**
     * See Interface for functional description.
     * 
     * @param contentModelId
     * @throws RemoteException
     * @throws SystemException
     * @throws LockingException
     * @throws MissingMethodParameterException
     * @throws InvalidStatusException
     * @throws AuthenticationException
     * @throws ContentModelNotFoundException
     * @throws AlreadyPublishedException
     * @throws AuthorizationException
     * @see de.escidoc.core.om.ContentModelHandler#delete(String)
     */
    public void delete(final String contentModelId) throws RemoteException,
        SystemException, LockingException, MissingMethodParameterException,
        InvalidStatusException, AuthenticationException,
        ContentModelNotFoundException, AlreadyPublishedException,
        AuthorizationException {

        del(PATH_CONTENT_MODEL + "/" + contentModelId);
    }

    /**
     * See Interface for functional description.
     * 
     * @param contentModelXml
     * @return
     * @throws RemoteException
     * @throws SystemException
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
     * @see de.escidoc.core.om.ContentModelHandler#create(String)
     */
    public String create(final String contentModelXml) throws RemoteException,
        SystemException, MissingAttributeValueException,
        MissingContentException, MissingMdRecordException,
        ReferencedResourceNotFoundException, AuthenticationException,
        AuthorizationException, ContentModelNotFoundException,
        InvalidContentException, RelationPredicateNotFoundException,
        ReadonlyAttributeViolationException, FileNotFoundException,
        MissingMethodParameterException, InvalidStatusException,
        ReadonlyElementViolationException, ContentModelNotFoundException,
        InvalidXmlException, MissingElementValueException {

        return put(PATH_CONTENT_MODEL, contentModelXml);
    }

    /**
     * See Interface for functional description.
     * 
     * @param contentModelId
     * @param contentModelXml
     * @return
     * @throws RemoteException
     * @throws SystemException
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
     * @see de.escidoc.core.om.ContentModelHandler#update(String, String)
     */
    public String update(
        final String contentModelId, final String contentModelXml)
        throws RemoteException, SystemException, MissingLicenceException,
        ReadonlyVersionException, LockingException, ComponentNotFoundException,
        MissingContentException, MissingAttributeValueException,
        AlreadyExistsException, InvalidContentException,
        MissingMdRecordException, ReferencedResourceNotFoundException,
        AuthenticationException, AuthorizationException,
        ContentModelNotFoundException, InvalidContentException,
        OptimisticLockingException, RelationPredicateNotFoundException,
        FileNotFoundException, MissingMethodParameterException,
        NotPublishedException, InvalidStatusException,
        ReadonlyViolationException, InvalidXmlException {

        return put(PATH_CONTENT_MODEL + "/" + contentModelId, contentModelXml);
    }

    /**
     * 
     * @param contentModelId
     * @return
     * @throws RemoteException
     * @throws SystemException
     * @throws MissingMethodParameterException
     * @throws AuthenticationException
     * @throws ContentModelNotFoundException
     * @throws AuthorizationException
     */
    public String retrieve(final String contentModelId) throws RemoteException,
        SystemException, MissingMethodParameterException,
        AuthenticationException, ContentModelNotFoundException,
        AuthorizationException {

        return get(PATH_CONTENT_MODEL + "/" + contentModelId);
    }

    public String retrieveContentStream(final String in0, final String in1)
        throws RemoteException, SystemException,
        ContentStreamNotFoundException, MissingMethodParameterException,
        AuthenticationException, ContentModelNotFoundException,
        AuthorizationException {

        throw new SystemException(500, "Method not yet supported", "");
    }

    public String retrieveContentStreams(final String in0)
        throws RemoteException, SystemException,
        MissingMethodParameterException, AuthenticationException,
        ContentModelNotFoundException, AuthorizationException {

        throw new SystemException(500, "Method not yet supported", "");
    }

    public String retrieveProperties(final String contentModelId)
        throws RemoteException, SystemException,
        MissingMethodParameterException, AuthenticationException,
        ContentModelNotFoundException, AuthorizationException {

        return get(PATH_CONTENT_MODEL + "/" + contentModelId + "/properties");
    }

    public java.lang.String retrieveResources(final String contentModelId)
        throws RemoteException, SystemException,
        MissingMethodParameterException, ContentModelNotFoundException,
        AuthenticationException, AuthorizationException {

        return get(PATH_CONTENT_MODEL + "/" + contentModelId + "/resources");
    }

    public String retrieveVersionHistory(final String contentModelId)
        throws RemoteException, SystemException,
        MissingMethodParameterException, AuthenticationException,
        ContentModelNotFoundException, AuthorizationException {

        return get(PATH_CONTENT_MODEL + "/" + contentModelId
            + "/version-history");
    }
}
