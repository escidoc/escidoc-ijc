package de.escidoc.core.client.rest.serviceLocator;

import java.rmi.RemoteException;
import java.util.HashMap;

import de.escidoc.core.common.exceptions.remote.application.invalid.InvalidContentException;
import de.escidoc.core.common.exceptions.remote.application.invalid.InvalidContextException;
import de.escidoc.core.common.exceptions.remote.application.invalid.InvalidStatusException;
import de.escidoc.core.common.exceptions.remote.application.invalid.InvalidXmlException;
import de.escidoc.core.common.exceptions.remote.application.missing.MissingAttributeValueException;
import de.escidoc.core.common.exceptions.remote.application.missing.MissingContentException;
import de.escidoc.core.common.exceptions.remote.application.missing.MissingElementValueException;
import de.escidoc.core.common.exceptions.remote.application.missing.MissingLicenceException;
import de.escidoc.core.common.exceptions.remote.application.missing.MissingMdRecordException;
import de.escidoc.core.common.exceptions.remote.application.missing.MissingMethodParameterException;
import de.escidoc.core.common.exceptions.remote.application.notfound.AdminDescriptorNotFoundException;
import de.escidoc.core.common.exceptions.remote.application.notfound.ComponentNotFoundException;
import de.escidoc.core.common.exceptions.remote.application.notfound.ContentModelNotFoundException;
import de.escidoc.core.common.exceptions.remote.application.notfound.ContextNotFoundException;
import de.escidoc.core.common.exceptions.remote.application.notfound.FileNotFoundException;
import de.escidoc.core.common.exceptions.remote.application.notfound.ReferencedResourceNotFoundException;
import de.escidoc.core.common.exceptions.remote.application.notfound.RelationPredicateNotFoundException;
import de.escidoc.core.common.exceptions.remote.application.notfound.StreamNotFoundException;
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
import de.escidoc.core.om.ContextHandler;

/**
 * REST Service Connector.
 * 
 * @author SWA
 * 
 */
public class ContextRestServiceLocator extends RestServiceMethod
    implements ContextHandler {

    private static final String PATH_CONTEXT = "/ir/context";

    public String close(final String contextId, final String taskParam)
        throws RemoteException, OptimisticLockingException, SystemException,
        MissingMethodParameterException, LockingException,
        InvalidStatusException, AuthenticationException,
        StreamNotFoundException, AuthorizationException,
        ContextNotFoundException, InvalidXmlException {

        return post(PATH_CONTEXT + "/" + contextId + "/close", taskParam);
    }

    public String open(final String contextId, final String taskParam)
        throws java.rmi.RemoteException, OptimisticLockingException,
        SystemException, MissingMethodParameterException, LockingException,
        InvalidStatusException, AuthenticationException,
        StreamNotFoundException, AuthorizationException,
        ContextNotFoundException, InvalidXmlException {

        return post(PATH_CONTEXT + "/" + contextId + "/open", taskParam);
    }

    public String retrieveMembers(final String contextId, final String filter)
        throws RemoteException, SystemException,
        MissingMethodParameterException, AuthenticationException,
        AuthorizationException, ContextNotFoundException, InvalidXmlException {

        return post(PATH_CONTEXT + "/" + contextId
            + "/resources/members/filter", filter);
    }

    public String retrieveMembers(final String contextId, final HashMap filter)
        throws RemoteException, SystemException,
        MissingMethodParameterException, AuthenticationException,
        AuthorizationException, ContextNotFoundException, InvalidXmlException {

        return get(PATH_CONTEXT + "/" + contextId + "/resources/members",
            filter);
    }

    public String retrieveAdminDescriptor(
        final String contextId, final String admId) throws RemoteException,
        SystemException, AdminDescriptorNotFoundException,
        MissingMethodParameterException, AuthenticationException,
        AuthorizationException, ContextNotFoundException {

        return get(PATH_CONTEXT + "/" + contextId
            + "/admin-descriptors/admin-descriptor/" + admId);
    }

    public String retrieveAdminDescriptors(final String contextId)
        throws RemoteException, SystemException,
        MissingMethodParameterException, AuthenticationException,
        AuthorizationException, ContextNotFoundException {

        return get(PATH_CONTEXT + "/" + contextId + "/admin-descriptors");
    }

    public String updateAdminDescriptor(
        final String contextId, final String adminDescriptorId)
        throws RemoteException, OptimisticLockingException, SystemException,
        AdminDescriptorNotFoundException, MissingMethodParameterException,
        AuthenticationException, AuthorizationException,
        ContextNotFoundException, InvalidXmlException {

        return put(PATH_CONTEXT + "/" + contextId
            + "/admin-descriptors/admin-descriptor/" + adminDescriptorId, "");
    }

    /**
     * See Interface for functional description.
     * 
     * @param contextId
     * @throws RemoteException
     * @throws SystemException
     * @throws LockingException
     * @throws MissingMethodParameterException
     * @throws InvalidStatusException
     * @throws AuthenticationException
     * @throws ContextNotFoundException
     * @throws AlreadyPublishedException
     * @throws AuthorizationException
     * @see de.escidoc.core.om.ContextHandler#delete(String)
     */
    public void delete(final String contextId) throws RemoteException,
        SystemException, LockingException, MissingMethodParameterException,
        InvalidStatusException, AuthenticationException,
        ContextNotFoundException, AlreadyPublishedException,
        AuthorizationException {

        del(PATH_CONTEXT + "/" + contextId);
    }

    /**
     * See Interface for functional description.
     * 
     * @param contextXml
     * @return
     * @throws RemoteException
     * @throws SystemException
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
     * @see de.escidoc.core.om.ContextHandler#create(String)
     */
    public String create(final String contextXml) throws RemoteException,
        SystemException, MissingAttributeValueException,
        MissingContentException, MissingMdRecordException,
        ReferencedResourceNotFoundException, AuthenticationException,
        AuthorizationException, ContextNotFoundException,
        InvalidContentException, RelationPredicateNotFoundException,
        ReadonlyAttributeViolationException, FileNotFoundException,
        MissingMethodParameterException, InvalidStatusException,
        ReadonlyElementViolationException, ContentModelNotFoundException,
        InvalidXmlException, MissingElementValueException {

        return put(PATH_CONTEXT, contextXml);
    }

    /**
     * See Interface for functional description.
     * 
     * @param contextId
     * @param contextXml
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
     * @see de.escidoc.core.om.ContextHandler#update(String, String)
     */
    public String update(final String contextId, final String contextXml)
        throws RemoteException, SystemException, MissingLicenceException,
        ReadonlyVersionException, LockingException, ComponentNotFoundException,
        MissingContentException, MissingAttributeValueException,
        AlreadyExistsException, InvalidContextException,
        MissingMdRecordException, ReferencedResourceNotFoundException,
        AuthenticationException, AuthorizationException,
        ContextNotFoundException, InvalidContentException,
        OptimisticLockingException, RelationPredicateNotFoundException,
        FileNotFoundException, MissingMethodParameterException,
        NotPublishedException, InvalidStatusException,
        ReadonlyViolationException, InvalidXmlException {

        return put(PATH_CONTEXT + "/" + contextId, contextXml);
    }

    public String retrieve(final String contextId) throws RemoteException,
        SystemException, MissingMethodParameterException,
        AuthenticationException, ContextNotFoundException,
        AuthorizationException {

        return get(PATH_CONTEXT + "/" + contextId);
    }

    public String retrieveProperties(final String contextId)
        throws RemoteException, SystemException,
        MissingMethodParameterException, AuthenticationException,
        ContextNotFoundException, AuthorizationException {

        return get(PATH_CONTEXT + "/" + contextId + "/properties");
    }

    public String retrieveContexts(final String filter) throws RemoteException,
        SystemException, MissingMethodParameterException,
        AuthenticationException, AuthorizationException, InvalidXmlException {

        return post(PATH_CONTEXT + "s/filter", filter);
    }
}
