package de.escidoc.core.client.rest.serviceLocator;

import static de.escidoc.core.common.Precondition.checkNotNull;
import gov.loc.www.zing.srw.ExplainRequestType;
import gov.loc.www.zing.srw.SearchRetrieveRequestType;

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
import de.escidoc.core.client.exceptions.application.notfound.ComponentNotFoundException;
import de.escidoc.core.client.exceptions.application.notfound.ContentModelNotFoundException;
import de.escidoc.core.client.exceptions.application.notfound.ContextNotFoundException;
import de.escidoc.core.client.exceptions.application.notfound.FileNotFoundException;
import de.escidoc.core.client.exceptions.application.notfound.MdRecordNotFoundException;
import de.escidoc.core.client.exceptions.application.notfound.OrganizationalUnitNotFoundException;
import de.escidoc.core.client.exceptions.application.notfound.ReferencedResourceNotFoundException;
import de.escidoc.core.client.exceptions.application.notfound.RelationPredicateNotFoundException;
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
import de.escidoc.core.client.interfaces.handler.OrganizationalUnitHandler;

/**
 * REST Service Connector.
 * 
 * @author SWA
 * 
 */
public class OrganizationalUnitRestServiceLocator extends RestServiceMethod implements OrganizationalUnitHandler {

    public static final String PATH = "/oum/organizational-unit";

    @Override
    public String close(final String ouId, final String taskParam) throws EscidocException, OptimisticLockingException,
        SystemException, OrganizationalUnitNotFoundException, MissingMethodParameterException, InvalidStatusException,
        AuthenticationException, AuthorizationException, InvalidXmlException {

        checkNotNull(ouId);

        return post(PATH + "/" + ouId + "/close", taskParam);
    }

    @Override
    public String open(final String ouId, final String taskParam) throws EscidocException, OptimisticLockingException,
        SystemException, OrganizationalUnitNotFoundException, MissingMethodParameterException, InvalidStatusException,
        AuthenticationException, AuthorizationException, InvalidXmlException {

        checkNotNull(ouId);

        return post(PATH + "/" + ouId + "/open", taskParam);
    }

    @Override
    public String updateParents(final String ouId, final String xmlOfParents) throws EscidocException,
        OptimisticLockingException, SystemException, OrganizationalUnitNotFoundException,
        MissingMethodParameterException, OrganizationalUnitHierarchyViolationException, InvalidStatusException,
        AuthenticationException, AuthorizationException, InvalidXmlException, MissingElementValueException {

        checkNotNull(ouId);
        checkNotNull(xmlOfParents);

        return put(PATH + "/" + ouId + "/parents", xmlOfParents);
    }

    @Override
    public String retrieveParents(final String ouId) throws EscidocException, OrganizationalUnitNotFoundException,
        MissingMethodParameterException, AuthenticationException, AuthorizationException {

        checkNotNull(ouId);

        return get(PATH + "/" + ouId + "/parents");
    }

    @Override
    public String retrieveParentObjects(final String ouId) throws EscidocException, SystemException,
        OrganizationalUnitNotFoundException, MissingMethodParameterException, AuthenticationException,
        AuthorizationException {

        checkNotNull(ouId);

        return get(PATH + "/" + ouId + "/resources/parent-objects");
    }

    @Override
    public String retrieveChildObjects(final String ouId) throws EscidocException, SystemException,
        OrganizationalUnitNotFoundException, MissingMethodParameterException, AuthenticationException,
        AuthorizationException {

        checkNotNull(ouId);

        return get(PATH + "/" + ouId + "/resources/child-objects");
    }

    @Override
    public String retrievePathList(final String ouId) throws EscidocException, OrganizationalUnitNotFoundException,
        MissingMethodParameterException, AuthenticationException, AuthorizationException {

        checkNotNull(ouId);

        return get(PATH + "/" + ouId + "/resources/path-list");
    }

    @Override
    public String retrieveOrganizationalUnits(final SearchRetrieveRequestType request) throws EscidocException,
        MissingMethodParameterException, AuthenticationException, AuthorizationException, InvalidXmlException {

        checkNotNull(request);

        return get(PATH + "s" + getEscidoc12Filter(request));
    }

    @Override
    public String retrieveOrganizationalUnits(final ExplainRequestType request) throws EscidocException,
        MissingMethodParameterException, AuthenticationException, AuthorizationException, InvalidXmlException {

        checkNotNull(request);

        return get(PATH + "s" + getEscidoc12Filter(request));
    }

    @Override
    public void delete(final String ouId) throws EscidocException, LockingException, MissingMethodParameterException,
        InvalidStatusException, AuthenticationException, OrganizationalUnitNotFoundException,
        AlreadyPublishedException, AuthorizationException {

        checkNotNull(ouId);

        del(PATH + "/" + ouId);
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
     * @see de.escidoc.core.client.interfaces.handler.om.OrganizationalUnitHandler#create(String)
     */
    @Override
    public String create(final String organizationalUnitXml) throws EscidocException, MissingAttributeValueException,
        MissingContentException, MissingMdRecordException, ReferencedResourceNotFoundException,
        AuthenticationException, AuthorizationException, ContextNotFoundException, InvalidContentException,
        RelationPredicateNotFoundException, ReadonlyAttributeViolationException, FileNotFoundException,
        MissingMethodParameterException, InvalidStatusException, ReadonlyElementViolationException,
        ContentModelNotFoundException, InvalidXmlException, MissingElementValueException {

        checkNotNull(organizationalUnitXml);

        return put(PATH, organizationalUnitXml);
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
     * @see de.escidoc.core.client.interfaces.handler.om.OrganizationalUnitHandler#update(String,
     *      String)
     */
    @Override
    public String update(final String ouId, final String organizationalUnitXml) throws EscidocException,
        MissingLicenceException, ReadonlyVersionException, LockingException, ComponentNotFoundException,
        MissingContentException, MissingAttributeValueException, AlreadyExistsException, InvalidContextException,
        MissingMdRecordException, ReferencedResourceNotFoundException, AuthenticationException, AuthorizationException,
        OrganizationalUnitNotFoundException, InvalidContentException, OptimisticLockingException,
        RelationPredicateNotFoundException, FileNotFoundException, MissingMethodParameterException,
        NotPublishedException, InvalidStatusException, ReadonlyViolationException, InvalidXmlException {

        checkNotNull(ouId);
        checkNotNull(organizationalUnitXml);

        return put(PATH + "/" + ouId, organizationalUnitXml);
    }

    /**
     * Retrieve XML representaion of Organizational Units.
     * 
     * @param ouId
     *            The id of the Organizational Unit.
     * @return XML representation of Organizational Unit
     */
    @Override
    public String retrieve(final String ouId) throws EscidocException, MissingMethodParameterException,
        AuthenticationException, OrganizationalUnitNotFoundException, AuthorizationException {

        checkNotNull(ouId);

        return get(PATH + "/" + ouId);
    }

    /**
     * Retrieve Successors (virtual resource) of Organizational Units.
     * 
     * @param ouId
     *            The id of the Organizational Unit.
     * @return list of successors
     */
    @Override
    public String retrieveSuccessors(final String ouId) throws EscidocException, OrganizationalUnitNotFoundException,
        MissingMethodParameterException, AuthenticationException, AuthorizationException {

        checkNotNull(ouId);

        return get(PATH + "/" + ouId + "/resources/successors");
    };

    @Override
    public String retrieveMdRecord(final String ouId, final String mdRecordId) throws EscidocException,
        MissingMethodParameterException, AuthenticationException, OrganizationalUnitNotFoundException,
        AuthorizationException, MdRecordNotFoundException {

        checkNotNull(ouId);
        checkNotNull(mdRecordId);

        return get(PATH + "/" + ouId + "/md-records/md-record/" + mdRecordId);
    }

    /**
     * FIXME: Is there any need for this method?
     */
    @Override
    public String updateMdRecords(final String ouId, final String mdRecordsXml) throws EscidocException,
        OptimisticLockingException, SystemException, OrganizationalUnitNotFoundException,
        MissingMethodParameterException, AuthenticationException, AuthorizationException, InvalidXmlException,
        MissingElementValueException {

        checkNotNull(ouId);
        checkNotNull(mdRecordsXml);

        return put(PATH + "/" + ouId + "/md-records", mdRecordsXml);
    }

    @Override
    public String retrieveMdRecords(final String ouId) throws EscidocException, MissingMethodParameterException,
        AuthenticationException, OrganizationalUnitNotFoundException, AuthorizationException {

        checkNotNull(ouId);

        return get(PATH + "/" + ouId + "/md-records");
    }

    @Override
    public String retrieveProperties(final String ouId) throws EscidocException, MissingMethodParameterException,
        AuthenticationException, OrganizationalUnitNotFoundException, AuthorizationException {

        checkNotNull(ouId);

        return get(PATH + "/" + ouId + "/properties");
    }
}