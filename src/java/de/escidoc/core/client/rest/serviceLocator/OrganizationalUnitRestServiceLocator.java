package de.escidoc.core.client.rest.serviceLocator;

import gov.loc.www.zing.srw.ExplainRequestType;
import gov.loc.www.zing.srw.SearchRetrieveRequestType;

import java.rmi.RemoteException;
import java.util.HashMap;

import de.escidoc.core.client.interfaces.OrganizationalUnitHandler;
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

    @Deprecated
    public String retrieveOrganizationalUnits(final HashMap filter)
        throws RemoteException, SystemException,
        MissingMethodParameterException, AuthenticationException,
        AuthorizationException, InvalidXmlException {

        return get(PATH_OU + "s", filter);
    }

    public String retrieveOrganizationalUnits(
        final SearchRetrieveRequestType filter) throws RemoteException,
        SystemException, MissingMethodParameterException,
        AuthenticationException, AuthorizationException, InvalidXmlException {

        return get(PATH_OU + "s" + getEscidoc12Filter(filter));
    }

    public String retrieveOrganizationalUnits(final ExplainRequestType filter)
        throws RemoteException, SystemException,
        MissingMethodParameterException, AuthenticationException,
        AuthorizationException, InvalidXmlException {

        return get(PATH_OU + "s" + getEscidoc12Filter(filter));
    }

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
     * @return The XML representation of Organizational Unit.
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

    /**
     * Retrieve XML representaion of Organizational Units.
     * 
     * @param ouId
     *            The id of the Organizational Unit.
     * @return XML representation of Organizational Unit
     */
    public String retrieve(final String ouId) throws RemoteException,
        SystemException, MissingMethodParameterException,
        AuthenticationException, OrganizationalUnitNotFoundException,
        AuthorizationException {

        return get(PATH_OU + "/" + ouId);
    }

    /**
     * Retrieve Successors (virtual resource) of Organizational Units.
     * 
     * @param ouId
     *            The id of the Organizational Unit.
     * @return list of successors
     */
    public String retrieveSuccessors(final String ouId) throws RemoteException,
        SystemException, OrganizationalUnitNotFoundException,
        MissingMethodParameterException, AuthenticationException,
        AuthorizationException {

        return get(PATH_OU + "/" + ouId + "/resources/successors");
    };

    public String createMdRecord(final String ouId, final String mdRecordXml)
        throws RemoteException, SystemException, LockingException,
        MissingAttributeValueException, MissingMethodParameterException,
        InvalidStatusException, AuthenticationException,
        XmlSchemaNotFoundException, OrganizationalUnitNotFoundException,
        AuthorizationException, InvalidXmlException {

        return put(PATH_OU + "/" + ouId + "/md-records/md-record", mdRecordXml);
    }

    public String retrieveMdRecord(final String ouId, final String mdRecordId)
        throws RemoteException, SystemException,
        MissingMethodParameterException, AuthenticationException,
        OrganizationalUnitNotFoundException, AuthorizationException,
        MdRecordNotFoundException {

        return get(PATH_OU + "/" + ouId + "/md-records/md-record/" + mdRecordId);
    }

    public String updateMdRecords(final String ouId, final String mdRecordsXml)
        throws RemoteException, OptimisticLockingException, SystemException,
        OrganizationalUnitNotFoundException, MissingMethodParameterException,
        AuthenticationException, AuthorizationException, InvalidXmlException,
        OrganizationalUnitNameNotUniqueException, MissingElementValueException {

        return put(PATH_OU + "/" + ouId + "/md-records", mdRecordsXml);
    }

    public String retrieveMdRecords(String ouId) throws RemoteException,
        SystemException, MissingMethodParameterException,
        AuthenticationException, OrganizationalUnitNotFoundException,
        AuthorizationException {

        return get(PATH_OU + "/" + ouId + "/md-records");
    }

    public String retrieveProperties(final String ouId) throws RemoteException,
        SystemException, MissingMethodParameterException,
        AuthenticationException, OrganizationalUnitNotFoundException,
        AuthorizationException {

        return get(PATH_OU + "/" + ouId + "/properties");
    }

}
