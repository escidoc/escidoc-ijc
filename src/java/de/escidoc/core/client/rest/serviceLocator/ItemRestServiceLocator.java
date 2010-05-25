package de.escidoc.core.client.rest.serviceLocator;

import gov.loc.www.zing.srw.ExplainRequestType;
import gov.loc.www.zing.srw.SearchRetrieveRequestType;

import java.rmi.RemoteException;
import java.util.HashMap;

import de.escidoc.core.client.interfaces.ItemHandler;
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
import de.escidoc.core.common.exceptions.remote.application.notfound.ContentRelationNotFoundException;
import de.escidoc.core.common.exceptions.remote.application.notfound.ContentStreamNotFoundException;
import de.escidoc.core.common.exceptions.remote.application.notfound.ContextNotFoundException;
import de.escidoc.core.common.exceptions.remote.application.notfound.FileNotFoundException;
import de.escidoc.core.common.exceptions.remote.application.notfound.ItemNotFoundException;
import de.escidoc.core.common.exceptions.remote.application.notfound.MdRecordNotFoundException;
import de.escidoc.core.common.exceptions.remote.application.notfound.ReferencedResourceNotFoundException;
import de.escidoc.core.common.exceptions.remote.application.notfound.RelationPredicateNotFoundException;
import de.escidoc.core.common.exceptions.remote.application.notfound.XmlSchemaNotFoundException;
import de.escidoc.core.common.exceptions.remote.application.security.AuthenticationException;
import de.escidoc.core.common.exceptions.remote.application.security.AuthorizationException;
import de.escidoc.core.common.exceptions.remote.application.violated.AlreadyDeletedException;
import de.escidoc.core.common.exceptions.remote.application.violated.AlreadyExistsException;
import de.escidoc.core.common.exceptions.remote.application.violated.AlreadyPublishedException;
import de.escidoc.core.common.exceptions.remote.application.violated.AlreadyWithdrawnException;
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
public class ItemRestServiceLocator extends RestServiceMethod
    implements ItemHandler {

    private static final String PATH_ITEM = "/ir/item";

    /**
     * See Interface for functional description.
     * 
     * @param itemId
     * @throws RemoteException
     * @throws SystemException
     * @throws LockingException
     * @throws MissingMethodParameterException
     * @throws InvalidStatusException
     * @throws AuthenticationException
     * @throws ItemNotFoundException
     * @throws AlreadyPublishedException
     * @throws AuthorizationException
     * @see de.escidoc.core.om.ItemHandler#delete(java.lang.String)
     */
    public void delete(final String itemId) throws RemoteException,
        SystemException, LockingException, MissingMethodParameterException,
        InvalidStatusException, AuthenticationException, ItemNotFoundException,
        AlreadyPublishedException, AuthorizationException {

        del(PATH_ITEM + "/" + itemId);
    }

    /**
     * See Interface for functional description.
     * 
     * @param itemXml
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
     * @see de.escidoc.core.om.ItemHandler#create(java.lang.String)
     */
    public String create(final String itemXml) throws RemoteException,
        SystemException, MissingAttributeValueException,
        MissingContentException, MissingMdRecordException,
        ReferencedResourceNotFoundException, AuthenticationException,
        AuthorizationException, ContextNotFoundException,
        InvalidContentException, RelationPredicateNotFoundException,
        ReadonlyAttributeViolationException, FileNotFoundException,
        MissingMethodParameterException, InvalidStatusException,
        ReadonlyElementViolationException, ContentModelNotFoundException,
        InvalidXmlException, MissingElementValueException {

        return put(PATH_ITEM, itemXml);
    }

    /**
     * See Interface for functional description.
     * 
     * @param itemId
     * @param itemXml
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
     * @throws ItemNotFoundException
     * @throws InvalidContentException
     * @throws OptimisticLockingException
     * @throws RelationPredicateNotFoundException
     * @throws FileNotFoundException
     * @throws MissingMethodParameterException
     * @throws NotPublishedException
     * @throws InvalidStatusException
     * @throws ReadonlyViolationException
     * @throws InvalidXmlException
     * @see de.escidoc.core.om.ItemHandler#update(java.lang.String,
     *      java.lang.String)
     */
    public String update(final String itemId, final String itemXml)
        throws RemoteException, SystemException, MissingLicenceException,
        ReadonlyVersionException, LockingException, ComponentNotFoundException,
        MissingContentException, MissingAttributeValueException,
        AlreadyExistsException, InvalidContextException,
        MissingMdRecordException, ReferencedResourceNotFoundException,
        AuthenticationException, AuthorizationException, ItemNotFoundException,
        InvalidContentException, OptimisticLockingException,
        RelationPredicateNotFoundException, FileNotFoundException,
        MissingMethodParameterException, NotPublishedException,
        InvalidStatusException, ReadonlyViolationException, InvalidXmlException {

        return put(PATH_ITEM + "/" + itemId, itemXml);
    }

    public String lock(final String itemId, final String userId)
        throws RemoteException, OptimisticLockingException, SystemException,
        LockingException, MissingMethodParameterException,
        AuthenticationException, ItemNotFoundException, AuthorizationException,
        InvalidContentException, InvalidXmlException {

        return post(PATH_ITEM + "/" + itemId + "/lock", userId);
    }

    public String unlock(final String itemId, final String userId)
        throws RemoteException, OptimisticLockingException, SystemException,
        LockingException, MissingMethodParameterException,
        AuthenticationException, ItemNotFoundException, AuthorizationException,
        InvalidXmlException {

        return post(PATH_ITEM + "/" + itemId + "/unlock", userId);
    }

    public String release(final String itemId, final String param)
        throws RemoteException, OptimisticLockingException, SystemException,
        ReadonlyVersionException, LockingException,
        MissingMethodParameterException, InvalidStatusException,
        ReadonlyViolationException, AuthenticationException,
        ItemNotFoundException, AuthorizationException, InvalidXmlException {

        return post(PATH_ITEM + "/" + itemId + "/release", param);
    }

    public String retrieve(final String itemId) throws RemoteException,
        SystemException, MissingMethodParameterException,
        AuthenticationException, ItemNotFoundException, AuthorizationException {

        return get(PATH_ITEM + "/" + itemId);
    }

    public String submit(final String itemId, final String param)
        throws RemoteException, OptimisticLockingException, SystemException,
        ReadonlyVersionException, LockingException,
        MissingMethodParameterException, InvalidStatusException,
        ReadonlyViolationException, AuthenticationException,
        ItemNotFoundException, AuthorizationException, InvalidXmlException {

        return post(PATH_ITEM + "/" + itemId + "/submit", param);
    }

    public String createComponent(final String itemId, final String component)
        throws RemoteException, SystemException,
        MissingAttributeValueException, MissingContentException,
        LockingException, AuthenticationException, ItemNotFoundException,
        AuthorizationException, InvalidContentException,
        OptimisticLockingException, FileNotFoundException,
        MissingMethodParameterException, InvalidStatusException,
        ReadonlyViolationException, InvalidXmlException,
        MissingElementValueException {

        return put(PATH_ITEM + "/" + itemId + "/components/component",
            component);
    }

    public String retrieveComponent(
        final String itemId, final String componentId) throws RemoteException,
        SystemException, ComponentNotFoundException,
        MissingMethodParameterException, AuthenticationException,
        ItemNotFoundException, AuthorizationException {

        return get(PATH_ITEM + "/" + itemId + "/components/component"
            + componentId);
    }

    public String retrieveComponentMdRecords(
        final String itemId, final String componentId) throws RemoteException,
        SystemException, ComponentNotFoundException,
        MissingMethodParameterException, AuthenticationException,
        ItemNotFoundException, AuthorizationException {

        return get(PATH_ITEM + "/" + itemId + "/components/component/"
            + componentId + "/md-records");
    }

    public String retrieveComponentMdRecord(
        final String itemId, final String componentId, final String mdRecordId)
        throws RemoteException, SystemException, ComponentNotFoundException,
        MissingMethodParameterException, AuthenticationException,
        ItemNotFoundException, AuthorizationException,
        MdRecordNotFoundException {

        return get(PATH_ITEM + "/" + itemId + "/components/component/"
            + componentId + "/md-records/md-record/" + mdRecordId);
    }

    public String updateComponent(
        final String itemId, final String componentId, final String componentXml)
        throws RemoteException, SystemException, ReadonlyVersionException,
        MissingContentException, ComponentNotFoundException, LockingException,
        MissingAttributeValueException, AuthenticationException,
        ItemNotFoundException, AuthorizationException, InvalidContentException,
        OptimisticLockingException, FileNotFoundException,
        MissingMethodParameterException, InvalidStatusException,
        ReadonlyViolationException, InvalidXmlException {

        return put(PATH_ITEM + "/" + itemId + "/components/component/"
            + componentId, componentXml);
    }

    public String retrieveComponents(final String itemId)
        throws RemoteException, SystemException, ComponentNotFoundException,
        MissingMethodParameterException, AuthenticationException,
        ItemNotFoundException, AuthorizationException {

        return get(PATH_ITEM + "/" + itemId + "/components");
    }

    public String retrieveComponentProperties(
        final String itemId, final String componentId) throws RemoteException,
        SystemException, ComponentNotFoundException,
        MissingMethodParameterException, AuthenticationException,
        ItemNotFoundException, AuthorizationException {

        return get(PATH_ITEM + "/" + itemId + "/components/component/"
            + componentId + "/properties");
    }

    /**
     * Deprecated because of inconsistent naming. Use createMdRecord instead of.
     * 
     * @param itemId
     * @param mdRecordXml
     * @return
     * @throws RemoteException
     * @throws SystemException
     * @throws LockingException
     * @throws MissingAttributeValueException
     * @throws MissingMethodParameterException
     * @throws InvalidStatusException
     * @throws AuthenticationException
     * @throws XmlSchemaNotFoundException
     * @throws ItemNotFoundException
     * @throws AuthorizationException
     * @throws InvalidXmlException
     */
    @Deprecated
    public String createMetadataRecord(
        final String itemId, final String mdRecordXml) throws RemoteException,
        SystemException, LockingException, MissingAttributeValueException,
        MissingMethodParameterException, InvalidStatusException,
        AuthenticationException, XmlSchemaNotFoundException,
        ItemNotFoundException, AuthorizationException, InvalidXmlException {

        return createMdRecord(itemId, mdRecordXml);
    }

    public String createMdRecord(final String itemId, final String mdRecordXml)
        throws RemoteException, SystemException, LockingException,
        MissingAttributeValueException, MissingMethodParameterException,
        InvalidStatusException, AuthenticationException,
        XmlSchemaNotFoundException, ItemNotFoundException,
        AuthorizationException, InvalidXmlException {

        return put(PATH_ITEM + "/" + itemId + "/md-records/md-record",
            mdRecordXml);
    }

    public String retrieveMdRecord(final String itemId, final String mdRecordId)
        throws RemoteException, SystemException,
        MissingMethodParameterException, AuthenticationException,
        ItemNotFoundException, AuthorizationException,
        MdRecordNotFoundException {

        return get(PATH_ITEM + "/" + itemId + "/md-records/md-record/"
            + mdRecordId);
    }

    public String updateMdRecord(
        final String itemId, final String mdRecordId, final String mdRecordXml)
        throws RemoteException, SystemException, ReadonlyVersionException,
        LockingException, AuthenticationException, XmlSchemaNotFoundException,
        ItemNotFoundException, AuthorizationException, InvalidContentException,
        OptimisticLockingException, MissingMethodParameterException,
        InvalidStatusException, ReadonlyViolationException,
        MdRecordNotFoundException, InvalidXmlException {

        return post(PATH_ITEM + "/" + itemId + "/md-records/md-record/"
            + mdRecordId, mdRecordXml);
    }

    public String retrieveMdRecords(final String itemId)
        throws RemoteException, SystemException,
        MissingMethodParameterException, AuthenticationException,
        ItemNotFoundException, AuthorizationException {

        return get(PATH_ITEM + "/" + itemId + "/md-records");
    }

    public String retrieveContentStreams(final String itemId)
        throws RemoteException, SystemException,
        MissingMethodParameterException, AuthenticationException,
        ItemNotFoundException, AuthorizationException {

        return get(PATH_ITEM + "/" + itemId + "/content-streams");
    }

    public String retrieveContentStream(
        final String itemId, final String contentStreamId)
        throws RemoteException, SystemException,
        ContentStreamNotFoundException, MissingMethodParameterException,
        AuthenticationException, ItemNotFoundException, AuthorizationException {

        return get(PATH_ITEM + "/" + itemId
            + "/content-streams/content-stream/" + contentStreamId);
    }

    public String retrieveProperties(final String itemId)
        throws RemoteException, SystemException,
        MissingMethodParameterException, AuthenticationException,
        ItemNotFoundException, AuthorizationException {

        return get(PATH_ITEM + "/" + itemId + "/properties");
    }

    public String retrieveVersionHistory(final String itemId)
        throws RemoteException, SystemException,
        MissingMethodParameterException, AuthenticationException,
        ItemNotFoundException, AuthorizationException {

        return get(PATH_ITEM + "/" + itemId + "/version-history");
    }

    public String retrieveRelations(final String itemId)
        throws RemoteException, SystemException,
        MissingMethodParameterException, AuthenticationException,
        ItemNotFoundException, AuthorizationException {

        return get(PATH_ITEM + "/" + itemId + "/relations");
    }

    public String revise(final String itemId, final String taskParam)
        throws RemoteException, OptimisticLockingException, SystemException,
        ReadonlyVersionException, LockingException,
        MissingMethodParameterException, InvalidStatusException,
        ReadonlyViolationException, AuthenticationException,
        AuthorizationException, ItemNotFoundException, InvalidContentException,
        InvalidXmlException {

        return post(PATH_ITEM + "/" + itemId + "/revise", taskParam);
    }

    public String withdraw(final String itemId, final String taskParam)
        throws RemoteException, SystemException, ReadonlyVersionException,
        LockingException, AlreadyWithdrawnException, AuthenticationException,
        ItemNotFoundException, AuthorizationException,
        OptimisticLockingException, MissingMethodParameterException,
        NotPublishedException, InvalidStatusException,
        ReadonlyViolationException, InvalidXmlException {

        return post(PATH_ITEM + "/" + itemId + "/withdraw", taskParam);
    }

    public void deleteComponent(final String itemId, final String componentId)
        throws RemoteException, SystemException, ComponentNotFoundException,
        LockingException, MissingMethodParameterException,
        InvalidStatusException, AuthenticationException, ItemNotFoundException,
        AuthorizationException {

        del(PATH_ITEM + "/" + itemId + "/components/component/" + componentId);
    }

    public String moveToContext(final String in0, final String in1)
        throws RemoteException, SystemException, LockingException,
        MissingMethodParameterException, InvalidStatusException,
        AuthenticationException, ItemNotFoundException, AuthorizationException,
        ContextNotFoundException, InvalidContentException {

        throw new SystemException(500, "Method not yet supported", "");
    }

    @Deprecated
    public String retrieveItems(final String filter) throws RemoteException,
        SystemException, MissingMethodParameterException,
        AuthenticationException, AuthorizationException, InvalidXmlException {

        return post(PATH_ITEM + "s/filter", filter);
    }

    @Deprecated
    public String retrieveItems(final HashMap filter) throws RemoteException,
        SystemException, MissingMethodParameterException,
        AuthenticationException, AuthorizationException, InvalidXmlException {

        return get(PATH_ITEM + "s/", filter);
    }

    public String retrieveItems(final SearchRetrieveRequestType filter)
        throws RemoteException, SystemException,
        MissingMethodParameterException, AuthenticationException,
        AuthorizationException, InvalidXmlException {

        return get(PATH_ITEM + "s" + getEscidoc12Filter(filter));
    }

    public String retrieveItems(final ExplainRequestType filter)
        throws RemoteException, SystemException,
        MissingMethodParameterException, AuthenticationException,
        AuthorizationException, InvalidXmlException {

        return get(PATH_ITEM + "s" + getEscidoc12Filter(filter));
    }

    public String assignVersionPid(final String itemId, final String taskParam)
        throws RemoteException, OptimisticLockingException, SystemException,
        LockingException, MissingMethodParameterException,
        InvalidStatusException, AuthenticationException, ItemNotFoundException,
        AuthorizationException, InvalidXmlException {

        return post(PATH_ITEM + "/" + itemId + "/assign-version-pid", taskParam);
    }

    public String assignObjectPid(final String itemId, final String taskParam)
        throws RemoteException, OptimisticLockingException, SystemException,
        LockingException, MissingMethodParameterException,
        InvalidStatusException, AuthenticationException, ItemNotFoundException,
        AuthorizationException, InvalidXmlException {

        return post(PATH_ITEM + "/" + itemId + "/assign-object-pid", taskParam);
    }

    public String assignContentPid(
        final String itemId, final String componentId, final String taskParam)
        throws RemoteException, OptimisticLockingException, SystemException,
        LockingException, MissingMethodParameterException,
        ComponentNotFoundException, InvalidStatusException,
        AuthenticationException, ItemNotFoundException, AuthorizationException,
        InvalidXmlException {

        return post(PATH_ITEM + "/" + itemId + "/components/component/"
            + componentId + "/assign-content-pid", taskParam);
    }

    public String addContentRelations(
        final String itemId, final String taskParam) throws RemoteException,
        SystemException, ReadonlyVersionException, LockingException,
        AlreadyExistsException, AuthenticationException,
        ReferencedResourceNotFoundException, AuthorizationException,
        ItemNotFoundException, InvalidContentException,
        OptimisticLockingException, RelationPredicateNotFoundException,
        ReadonlyAttributeViolationException, MissingMethodParameterException,
        InvalidStatusException, ReadonlyViolationException,
        MissingElementValueException, InvalidXmlException {

        return post(PATH_ITEM + "/" + itemId + "/content-relations/add",
            taskParam);
    }

    public String removeContentRelations(
        final String itemId, final String taskParam) throws RemoteException,
        SystemException, ContentRelationNotFoundException,
        ReadonlyVersionException, LockingException, AuthenticationException,
        ItemNotFoundException, AuthorizationException, InvalidContentException,
        OptimisticLockingException, MissingMethodParameterException,
        InvalidStatusException, ReadonlyViolationException,
        AlreadyDeletedException, InvalidXmlException,
        MissingElementValueException {

        return (PATH_ITEM + "/" + itemId + "/content-relations/remove");
    }

}
