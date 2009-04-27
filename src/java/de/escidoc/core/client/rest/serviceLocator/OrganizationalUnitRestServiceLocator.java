package de.escidoc.core.client.rest.serviceLocator;

import java.rmi.RemoteException;

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
import de.escidoc.core.common.exceptions.remote.application.notfound.ComponentNotFoundException;
import de.escidoc.core.common.exceptions.remote.application.notfound.ContentModelNotFoundException;
import de.escidoc.core.common.exceptions.remote.application.notfound.ContextNotFoundException;
import de.escidoc.core.common.exceptions.remote.application.notfound.FileNotFoundException;
import de.escidoc.core.common.exceptions.remote.application.notfound.MdRecordNotFoundException;
import de.escidoc.core.common.exceptions.remote.application.notfound.OrganizationalUnitNotFoundException;
import de.escidoc.core.common.exceptions.remote.application.notfound.ReferencedResourceNotFoundException;
import de.escidoc.core.common.exceptions.remote.application.notfound.RelationPredicateNotFoundException;
import de.escidoc.core.common.exceptions.remote.application.notfound.XmlSchemaNotFoundException;
import de.escidoc.core.common.exceptions.remote.application.security.AuthenticationException;
import de.escidoc.core.common.exceptions.remote.application.security.AuthorizationException;
import de.escidoc.core.common.exceptions.remote.application.violated.AlreadyExistsException;
import de.escidoc.core.common.exceptions.remote.application.violated.AlreadyPublishedException;
import de.escidoc.core.common.exceptions.remote.application.violated.LockingException;
import de.escidoc.core.common.exceptions.remote.application.violated.NotPublishedException;
import de.escidoc.core.common.exceptions.remote.application.violated.OptimisticLockingException;
import de.escidoc.core.common.exceptions.remote.application.violated.OrganizationalUnitHierarchyViolationException;
import de.escidoc.core.common.exceptions.remote.application.violated.OrganizationalUnitNameNotUniqueException;
import de.escidoc.core.common.exceptions.remote.application.violated.ReadonlyAttributeViolationException;
import de.escidoc.core.common.exceptions.remote.application.violated.ReadonlyElementViolationException;
import de.escidoc.core.common.exceptions.remote.application.violated.ReadonlyVersionException;
import de.escidoc.core.common.exceptions.remote.application.violated.ReadonlyViolationException;
import de.escidoc.core.common.exceptions.remote.system.SystemException;
import de.escidoc.core.oum.OrganizationalUnitHandler;

/**
 * REST Service Connector.
 * 
 * @author SWA
 * 
 */
public class OrganizationalUnitRestServiceLocator extends RestServiceMethod
    implements OrganizationalUnitHandler {

    private static final String PATH_OU = "/oum/organizational-unit";

    public String close(final String ouId, final String taskParam)
        throws RemoteException, OptimisticLockingException, SystemException,
        OrganizationalUnitNotFoundException, MissingMethodParameterException,
        InvalidStatusException, AuthenticationException,
        AuthorizationException, InvalidXmlException {

        return post(PATH_OU + "/" + ouId + "/close", taskParam);
    }

    public String open(final String ouId, final String taskParam)
        throws RemoteException, OptimisticLockingException, SystemException,
        OrganizationalUnitNotFoundException, MissingMethodParameterException,
        InvalidStatusException, AuthenticationException,
        AuthorizationException, InvalidXmlException {

        return post(PATH_OU + "/" + ouId + "/open", taskParam);
    }

    public String updateParents(final String ouId, final String parents)
        throws RemoteException, OptimisticLockingException, SystemException,
        OrganizationalUnitNotFoundException, MissingMethodParameterException,
        OrganizationalUnitHierarchyViolationException, InvalidStatusException,
        AuthenticationException, AuthorizationException, InvalidXmlException,
        OrganizationalUnitNameNotUniqueException, MissingElementValueException {

        return put(PATH_OU + "/" + ouId + "/parents", parents);
    }

    public String retrieveParents(final String ouId) throws RemoteException,
        SystemException, OrganizationalUnitNotFoundException,
        MissingMethodParameterException, AuthenticationException,
        AuthorizationException {

        return get(PATH_OU + "/" + ouId + "/parents");
    }

    public String retrieveParentObjects(final String ouId)
        throws java.rmi.RemoteException, SystemException,
        OrganizationalUnitNotFoundException, MissingMethodParameterException,
        AuthenticationException, AuthorizationException {

        return get(PATH_OU + "/" + ouId + "/parents/resources/parent-objects");
    }

    public String retrieveChildObjects(final String ouId)
        throws java.rmi.RemoteException, SystemException,
        OrganizationalUnitNotFoundException, MissingMethodParameterException,
        AuthenticationException, AuthorizationException {

        return get(PATH_OU + "/" + ouId + "/resources/child-objects");
    }

    public String retrievePathList(final String ouId) throws RemoteException,
        SystemException, OrganizationalUnitNotFoundException,
        MissingMethodParameterException, AuthenticationException,
        AuthorizationException {

        return get(PATH_OU + "/" + ouId + "/resources/path-list");
    }

    public String retrieveOrganizationalUnits(final String filter)
        throws RemoteException, SystemException,
        MissingMethodParameterException, AuthenticationException,
        AuthorizationException, InvalidXmlException {

        return post("/oum/organizational-units/filter", filter);
    }

    /**
     * See Interface for functional description.
     * 
     * @param ouId
     * @throws RemoteException
     * @throws SystemException
     * @throws LockingException
     * @throws MissingMethodParameterException
     * @throws InvalidStatusException
     * @throws AuthenticationException
     * @throws OrganizationalUnitNotFoundException
     * @throws AlreadyPublishedException
     * @throws AuthorizationException
     * @see de.escidoc.core.om.OrganizationalUnitHandler#delete(String)
     */
    public void delete(final String ouId) throws RemoteException,
        SystemException, LockingException, MissingMethodParameterException,
        InvalidStatusException, AuthenticationException,
        OrganizationalUnitNotFoundException, AlreadyPublishedException,
        AuthorizationException {

        del(PATH_OU + "/" + ouId);
    }

    /**
     * See Interface for functional description.
     * 
     * @param organizationalUnitXml
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
     * @see de.escidoc.core.om.OrganizationalUnitHandler#create(String)
     */
    public String create(final String organizationalUnitXml)
        throws RemoteException, SystemException,
        MissingAttributeValueException, MissingContentException,
        MissingMdRecordException, ReferencedResourceNotFoundException,
        AuthenticationException, AuthorizationException,
        ContextNotFoundException, InvalidContentException,
        RelationPredicateNotFoundException,
        ReadonlyAttributeViolationException, FileNotFoundException,
        MissingMethodParameterException, InvalidStatusException,
        ReadonlyElementViolationException, ContentModelNotFoundException,
        InvalidXmlException, MissingElementValueException {

        return put(PATH_OU, organizationalUnitXml);
    }

    /**
     * See Interface for functional description.
     * 
     * @param ouId
     * @param organizationalUnitXml
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
     * @see de.escidoc.core.om.OrganizationalUnitHandler#update(String, String)
     */
    public String update(final String ouId, final String organizationalUnitXml)
        throws RemoteException, SystemException, MissingLicenceException,
        ReadonlyVersionException, LockingException, ComponentNotFoundException,
        MissingContentException, MissingAttributeValueException,
        AlreadyExistsException, InvalidContextException,
        MissingMdRecordException, ReferencedResourceNotFoundException,
        AuthenticationException, AuthorizationException,
        OrganizationalUnitNotFoundException, InvalidContentException,
        OptimisticLockingException, RelationPredicateNotFoundException,
        FileNotFoundException, MissingMethodParameterException,
        NotPublishedException, InvalidStatusException,
        ReadonlyViolationException, InvalidXmlException {

        return put(PATH_OU + "/" + ouId, organizationalUnitXml);
    }

    public String lock(final String ouId, final String userId)
        throws RemoteException, OptimisticLockingException, SystemException,
        LockingException, MissingMethodParameterException,
        AuthenticationException, OrganizationalUnitNotFoundException,
        AuthorizationException, InvalidContentException, InvalidXmlException {

        return post(PATH_OU + "/" + ouId + "/lock", userId);
    }

    public String unlock(final String ouId, final String userId)
        throws RemoteException, OptimisticLockingException, SystemException,
        LockingException, MissingMethodParameterException,
        AuthenticationException, OrganizationalUnitNotFoundException,
        AuthorizationException, InvalidXmlException {

        return post(PATH_OU + "/" + ouId + "/unlock", userId);
    }

    public String retrieve(final String ouId) throws RemoteException,
        SystemException, MissingMethodParameterException,
        AuthenticationException, OrganizationalUnitNotFoundException,
        AuthorizationException {

        return get(PATH_OU + "/" + ouId);
    }

    public String createMetadataRecord(String in0, String in1)
        throws RemoteException, SystemException, LockingException,
        MissingAttributeValueException, MissingMethodParameterException,
        InvalidStatusException, AuthenticationException,
        XmlSchemaNotFoundException, OrganizationalUnitNotFoundException,
        AuthorizationException, InvalidXmlException {
        throw new SystemException(500, "Method not yet supported", "");
    }

    public String retrieveMdRecord(String in0, String in1)
        throws RemoteException, SystemException,
        MissingMethodParameterException, AuthenticationException,
        OrganizationalUnitNotFoundException, AuthorizationException,
        MdRecordNotFoundException {
        throw new SystemException(500, "Method not yet supported", "");
    }

    public String updateMdRecords(String in0, String in1)
        throws RemoteException, OptimisticLockingException, SystemException,
        OrganizationalUnitNotFoundException, MissingMethodParameterException,
        AuthenticationException, AuthorizationException, InvalidXmlException,
        OrganizationalUnitNameNotUniqueException, MissingElementValueException {

        throw new SystemException(500, "Method not yet supported", "");
    }

    public String retrieveMdRecords(String in0) throws RemoteException,
        SystemException, MissingMethodParameterException,
        AuthenticationException, OrganizationalUnitNotFoundException,
        AuthorizationException {
        throw new SystemException(500, "Method not yet supported", "");
    }

    public String retrieveProperties(String in0) throws RemoteException,
        SystemException, MissingMethodParameterException,
        AuthenticationException, OrganizationalUnitNotFoundException,
        AuthorizationException {
        throw new SystemException(500, "Method not yet supported", "");
    }

}
