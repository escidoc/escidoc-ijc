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

    @Override
    public String close(final String ouId, final String taskParam)
        throws RemoteException, OptimisticLockingException, SystemException,
        OrganizationalUnitNotFoundException, MissingMethodParameterException,
        InvalidStatusException, AuthenticationException,
        AuthorizationException, InvalidXmlException {

        if (ouId == null)
            throw new IllegalArgumentException("ouId must not be null.");
        if (taskParam == null)
            throw new IllegalArgumentException("taskParam must not be null.");

        return post(PATH_OU + "/" + ouId + "/close", taskParam);
    }

    @Override
    public String open(final String ouId, final String taskParam)
        throws RemoteException, OptimisticLockingException, SystemException,
        OrganizationalUnitNotFoundException, MissingMethodParameterException,
        InvalidStatusException, AuthenticationException,
        AuthorizationException, InvalidXmlException {

        if (ouId == null)
            throw new IllegalArgumentException("ouId must not be null.");
        if (taskParam == null)
            throw new IllegalArgumentException("taskParam must not be null.");

        return post(PATH_OU + "/" + ouId + "/open", taskParam);
    }

    @Override
    public String updateParents(final String ouId, final String xmlOfParents)
        throws RemoteException, OptimisticLockingException, SystemException,
        OrganizationalUnitNotFoundException, MissingMethodParameterException,
        OrganizationalUnitHierarchyViolationException, InvalidStatusException,
        AuthenticationException, AuthorizationException, InvalidXmlException,
        OrganizationalUnitNameNotUniqueException, MissingElementValueException {

        if (ouId == null)
            throw new IllegalArgumentException("ouId must not be null.");
        if (xmlOfParents == null)
            throw new IllegalArgumentException("xmlOfParents must not be null.");

        return put(PATH_OU + "/" + ouId + "/parents", xmlOfParents);
    }

    @Override
    public String retrieveParents(final String ouId) throws RemoteException,
        SystemException, OrganizationalUnitNotFoundException,
        MissingMethodParameterException, AuthenticationException,
        AuthorizationException {

        if (ouId == null)
            throw new IllegalArgumentException("ouId must not be null.");

        return get(PATH_OU + "/" + ouId + "/parents");
    }

    @Override
    public String retrieveParentObjects(final String ouId)
        throws java.rmi.RemoteException, SystemException,
        OrganizationalUnitNotFoundException, MissingMethodParameterException,
        AuthenticationException, AuthorizationException {

        if (ouId == null)
            throw new IllegalArgumentException("ouId must not be null.");

        return get(PATH_OU + "/" + ouId + "/resources/parent-objects");
    }

    @Override
    public String retrieveChildObjects(final String ouId)
        throws java.rmi.RemoteException, SystemException,
        OrganizationalUnitNotFoundException, MissingMethodParameterException,
        AuthenticationException, AuthorizationException {

        if (ouId == null)
            throw new IllegalArgumentException("ouId must not be null.");

        return get(PATH_OU + "/" + ouId + "/resources/child-objects");
    }

    @Override
    public String retrievePathList(final String ouId) throws RemoteException,
        SystemException, OrganizationalUnitNotFoundException,
        MissingMethodParameterException, AuthenticationException,
        AuthorizationException {

        if (ouId == null)
            throw new IllegalArgumentException("ouId must not be null.");

        return get(PATH_OU + "/" + ouId + "/resources/path-list");
    }

    @Deprecated
    @Override
    public String retrieveOrganizationalUnits(final String filterXml)
        throws RemoteException, SystemException,
        MissingMethodParameterException, AuthenticationException,
        AuthorizationException, InvalidXmlException {

        if (filterXml == null)
            throw new IllegalArgumentException("filterXml must not be null.");

        return post("/oum/organizational-units/filter", filterXml);
    }

    @SuppressWarnings("rawtypes")
    @Override
    @Deprecated
    public String retrieveOrganizationalUnits(final HashMap filter)
        throws RemoteException, SystemException,
        MissingMethodParameterException, AuthenticationException,
        AuthorizationException, InvalidXmlException {

        if (filter == null)
            throw new IllegalArgumentException("filter must not be null.");

        return get(PATH_OU + "s", filter);
    }

    @Override
    public String retrieveOrganizationalUnits(
        final SearchRetrieveRequestType request) throws RemoteException,
        SystemException, MissingMethodParameterException,
        AuthenticationException, AuthorizationException, InvalidXmlException {

        if (request == null)
            throw new IllegalArgumentException("filter must not be null.");

        return get(PATH_OU + "s" + getEscidoc12Filter(request));
    }

    @Override
    public String retrieveOrganizationalUnits(final ExplainRequestType request)
        throws RemoteException, SystemException,
        MissingMethodParameterException, AuthenticationException,
        AuthorizationException, InvalidXmlException {

        if (request == null)
            throw new IllegalArgumentException("request must not be null.");

        return get(PATH_OU + "s" + getEscidoc12Filter(request));
    }

    @Override
    public void delete(final String ouId) throws RemoteException,
        SystemException, LockingException, MissingMethodParameterException,
        InvalidStatusException, AuthenticationException,
        OrganizationalUnitNotFoundException, AlreadyPublishedException,
        AuthorizationException {

        if (ouId == null)
            throw new IllegalArgumentException("ouId must not be null.");

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
    @Override
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

        if (organizationalUnitXml == null)
            throw new IllegalArgumentException(
                "organizationalUnitXml must not be null.");

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
    @Override
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

        if (organizationalUnitXml == null)
            throw new IllegalArgumentException(
                "organizationalUnitXml must not be null.");

        return put(PATH_OU + "/" + ouId, organizationalUnitXml);
    }

    /**
     * FIXME: No such method supported by the interface?
     */
    public String lock(final String ouId, final String userId)
        throws RemoteException, OptimisticLockingException, SystemException,
        LockingException, MissingMethodParameterException,
        AuthenticationException, OrganizationalUnitNotFoundException,
        AuthorizationException, InvalidContentException, InvalidXmlException {

        if (ouId == null)
            throw new IllegalArgumentException("ouId must not be null.");
        if (userId == null)
            throw new IllegalArgumentException("userId must not be null.");

        return post(PATH_OU + "/" + ouId + "/lock", userId);
    }

    /**
     * FIXME: No such method supported by the interface?
     */
    public String unlock(final String ouId, final String userId)
        throws RemoteException, OptimisticLockingException, SystemException,
        LockingException, MissingMethodParameterException,
        AuthenticationException, OrganizationalUnitNotFoundException,
        AuthorizationException, InvalidXmlException {

        if (ouId == null)
            throw new IllegalArgumentException("ouId must not be null.");
        if (userId == null)
            throw new IllegalArgumentException("userId must not be null.");

        return post(PATH_OU + "/" + ouId + "/unlock", userId);
    }

    /**
     * Retrieve XML representaion of Organizational Units.
     * 
     * @param ouId
     *            The id of the Organizational Unit.
     * @return XML representation of Organizational Unit
     */
    @Override
    public String retrieve(final String ouId) throws RemoteException,
        SystemException, MissingMethodParameterException,
        AuthenticationException, OrganizationalUnitNotFoundException,
        AuthorizationException {

        if (ouId == null)
            throw new IllegalArgumentException("ouId must not be null.");

        return get(PATH_OU + "/" + ouId);
    }

    /**
     * Retrieve Successors (virtual resource) of Organizational Units.
     * 
     * @param ouId
     *            The id of the Organizational Unit.
     * @return list of successors
     */
    @Override
    public String retrieveSuccessors(final String ouId) throws RemoteException,
        SystemException, OrganizationalUnitNotFoundException,
        MissingMethodParameterException, AuthenticationException,
        AuthorizationException {

        if (ouId == null)
            throw new IllegalArgumentException("ouId must not be null.");

        return get(PATH_OU + "/" + ouId + "/resources/successors");
    };

    public String createMdRecord(final String ouId, final String mdRecordXml)
        throws RemoteException, SystemException, LockingException,
        MissingAttributeValueException, MissingMethodParameterException,
        InvalidStatusException, AuthenticationException,
        XmlSchemaNotFoundException, OrganizationalUnitNotFoundException,
        AuthorizationException, InvalidXmlException {

        if (ouId == null)
            throw new IllegalArgumentException("ouId must not be null.");
        if (mdRecordXml == null)
            throw new IllegalArgumentException("mdRecordXml must not be null.");

        return put(PATH_OU + "/" + ouId + "/md-records/md-record", mdRecordXml);
    }

    @Override
    public String retrieveMdRecord(final String ouId, final String mdRecordId)
        throws RemoteException, SystemException,
        MissingMethodParameterException, AuthenticationException,
        OrganizationalUnitNotFoundException, AuthorizationException,
        MdRecordNotFoundException {

        if (ouId == null)
            throw new IllegalArgumentException("ouId must not be null.");
        if (mdRecordId == null)
            throw new IllegalArgumentException("mdRecordId must not be null.");

        return get(PATH_OU + "/" + ouId + "/md-records/md-record/" + mdRecordId);
    }

    /**
     * FIXME: Is there any need for this method?
     */
    @Override
    public String updateMdRecords(final String ouId, final String mdRecordsXml)
        throws RemoteException, OptimisticLockingException, SystemException,
        OrganizationalUnitNotFoundException, MissingMethodParameterException,
        AuthenticationException, AuthorizationException, InvalidXmlException,
        OrganizationalUnitNameNotUniqueException, MissingElementValueException {

        if (ouId == null)
            throw new IllegalArgumentException("ouId must not be null.");
        if (mdRecordsXml == null)
            throw new IllegalArgumentException("mdRecordsXml must not be null.");

        return put(PATH_OU + "/" + ouId + "/md-records", mdRecordsXml);
    }

    @Override
    public String retrieveMdRecords(final String ouId) throws RemoteException,
        SystemException, MissingMethodParameterException,
        AuthenticationException, OrganizationalUnitNotFoundException,
        AuthorizationException {

        if (ouId == null)
            throw new IllegalArgumentException("ouId must not be null.");

        return get(PATH_OU + "/" + ouId + "/md-records");
    }

    @Override
    public String retrieveProperties(final String ouId) throws RemoteException,
        SystemException, MissingMethodParameterException,
        AuthenticationException, OrganizationalUnitNotFoundException,
        AuthorizationException {

        if (ouId == null)
            throw new IllegalArgumentException("ouId must not be null.");

        return get(PATH_OU + "/" + ouId + "/properties");
    }

}