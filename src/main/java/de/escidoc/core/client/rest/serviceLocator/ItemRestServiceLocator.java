package de.escidoc.core.client.rest.serviceLocator;

import static de.escidoc.core.common.Precondition.checkNotNull;
import gov.loc.www.zing.srw.ExplainRequestType;
import gov.loc.www.zing.srw.SearchRetrieveRequestType;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;

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
import de.escidoc.core.resources.HttpInputStream;

/**
 * REST Service Connector.
 * 
 * @author SWA
 * 
 */
public class ItemRestServiceLocator extends RestServiceMethod implements ItemHandler {

    public static final String PATH = "/ir/item";

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
    @Override
    public void delete(final String itemId) throws RemoteException, SystemException, LockingException,
        MissingMethodParameterException, InvalidStatusException, AuthenticationException, ItemNotFoundException,
        AlreadyPublishedException, AuthorizationException {

        checkNotNull(itemId);

        del(PATH + "/" + itemId);
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
    @Override
    public String create(final String itemXml) throws RemoteException, SystemException, MissingAttributeValueException,
        MissingContentException, MissingMdRecordException, ReferencedResourceNotFoundException,
        AuthenticationException, AuthorizationException, ContextNotFoundException, InvalidContentException,
        RelationPredicateNotFoundException, ReadonlyAttributeViolationException, FileNotFoundException,
        MissingMethodParameterException, InvalidStatusException, ReadonlyElementViolationException,
        ContentModelNotFoundException, InvalidXmlException, MissingElementValueException {

        checkNotNull(itemXml);

        return put(PATH, itemXml);
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
    @Override
    public String update(final String itemId, final String itemXml) throws RemoteException, SystemException,
        MissingLicenceException, ReadonlyVersionException, LockingException, ComponentNotFoundException,
        MissingContentException, MissingAttributeValueException, AlreadyExistsException, InvalidContextException,
        MissingMdRecordException, ReferencedResourceNotFoundException, AuthenticationException, AuthorizationException,
        ItemNotFoundException, InvalidContentException, OptimisticLockingException, RelationPredicateNotFoundException,
        FileNotFoundException, MissingMethodParameterException, NotPublishedException, InvalidStatusException,
        ReadonlyViolationException, InvalidXmlException {

        checkNotNull(itemId);
        checkNotNull(itemXml);

        return put(PATH + "/" + itemId, itemXml);
    }

    @Override
    public String lock(final String itemId, final String userId) throws RemoteException, OptimisticLockingException,
        SystemException, LockingException, MissingMethodParameterException, AuthenticationException,
        ItemNotFoundException, AuthorizationException, InvalidContentException, InvalidXmlException {

        checkNotNull(itemId);

        return post(PATH + "/" + itemId + "/lock", userId);
    }

    @Override
    public String unlock(final String itemId, final String userId) throws RemoteException, OptimisticLockingException,
        SystemException, LockingException, MissingMethodParameterException, AuthenticationException,
        ItemNotFoundException, AuthorizationException, InvalidXmlException {

        checkNotNull(itemId);
        checkNotNull(userId);

        return post(PATH + "/" + itemId + "/unlock", userId);
    }

    @Override
    public String release(final String itemId, final String param) throws RemoteException, OptimisticLockingException,
        SystemException, ReadonlyVersionException, LockingException, MissingMethodParameterException,
        InvalidStatusException, ReadonlyViolationException, AuthenticationException, ItemNotFoundException,
        AuthorizationException, InvalidXmlException {

        checkNotNull(itemId);

        return post(PATH + "/" + itemId + "/release", param);
    }

    @Override
    public String retrieve(final String itemId) throws RemoteException, SystemException,
        MissingMethodParameterException, AuthenticationException, ItemNotFoundException, AuthorizationException {

        checkNotNull(itemId);

        return get(PATH + "/" + itemId);
    }

    @Override
    public String submit(final String itemId, final String param) throws RemoteException, OptimisticLockingException,
        SystemException, ReadonlyVersionException, LockingException, MissingMethodParameterException,
        InvalidStatusException, ReadonlyViolationException, AuthenticationException, ItemNotFoundException,
        AuthorizationException, InvalidXmlException {

        checkNotNull(itemId);

        return post(PATH + "/" + itemId + "/submit", param);
    }

    @Override
    public String createComponent(final String itemId, final String componentXml) throws RemoteException,
        SystemException, MissingAttributeValueException, MissingContentException, LockingException,
        AuthenticationException, ItemNotFoundException, AuthorizationException, InvalidContentException,
        OptimisticLockingException, FileNotFoundException, MissingMethodParameterException, InvalidStatusException,
        ReadonlyViolationException, InvalidXmlException, MissingElementValueException {

        checkNotNull(itemId);
        checkNotNull(componentXml);

        return put(PATH + "/" + itemId + "/components/component", componentXml);
    }

    @Override
    public String retrieveComponent(final String itemId, final String componentId) throws RemoteException,
        SystemException, ComponentNotFoundException, MissingMethodParameterException, AuthenticationException,
        ItemNotFoundException, AuthorizationException {

        checkNotNull(itemId);
        checkNotNull(componentId);

        return get(PATH + "/" + itemId + "/components/component/" + componentId);
    }

    @Override
    public String retrieveComponentMdRecords(final String itemId, final String componentId) throws RemoteException,
        SystemException, ComponentNotFoundException, MissingMethodParameterException, AuthenticationException,
        ItemNotFoundException, AuthorizationException {

        checkNotNull(itemId);
        checkNotNull(componentId);

        return get(PATH + "/" + itemId + "/components/component/" + componentId + "/md-records");
    }

    @Override
    public String retrieveComponentMdRecord(final String itemId, final String componentId, final String mdRecordId)
        throws RemoteException, SystemException, ComponentNotFoundException, MissingMethodParameterException,
        AuthenticationException, ItemNotFoundException, AuthorizationException, MdRecordNotFoundException {

        checkNotNull(itemId);
        checkNotNull(componentId);
        checkNotNull(mdRecordId);

        return get(PATH + "/" + itemId + "/components/component/" + componentId + "/md-records/md-record/" + mdRecordId);
    }

    @Override
    public String updateComponent(final String itemId, final String componentId, final String componentXml)
        throws RemoteException, SystemException, ReadonlyVersionException, MissingContentException,
        ComponentNotFoundException, LockingException, MissingAttributeValueException, AuthenticationException,
        ItemNotFoundException, AuthorizationException, InvalidContentException, OptimisticLockingException,
        FileNotFoundException, MissingMethodParameterException, InvalidStatusException, ReadonlyViolationException,
        InvalidXmlException {

        checkNotNull(itemId);
        checkNotNull(componentId);
        checkNotNull(componentXml);

        return put(PATH + "/" + itemId + "/components/component/" + componentId, componentXml);
    }

    @Override
    public String retrieveComponents(final String itemId) throws RemoteException, SystemException,
        ComponentNotFoundException, MissingMethodParameterException, AuthenticationException, ItemNotFoundException,
        AuthorizationException {

        checkNotNull(itemId);

        return get(PATH + "/" + itemId + "/components");
    }

    @Override
    public String retrieveComponentProperties(final String itemId, final String componentId) throws RemoteException,
        SystemException, ComponentNotFoundException, MissingMethodParameterException, AuthenticationException,
        ItemNotFoundException, AuthorizationException {

        checkNotNull(itemId);
        checkNotNull(componentId);

        return get(PATH + "/" + itemId + "/components/component/" + componentId + "/properties");
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
    @Override
    @Deprecated
    public String createMetadataRecord(final String itemId, final String mdRecordXml) throws RemoteException,
        SystemException, LockingException, MissingAttributeValueException, MissingMethodParameterException,
        InvalidStatusException, AuthenticationException, XmlSchemaNotFoundException, ItemNotFoundException,
        AuthorizationException, InvalidXmlException {

        checkNotNull(itemId);
        checkNotNull(mdRecordXml);

        return createMdRecord(itemId, mdRecordXml);
    }

    @Override
    public String createMdRecord(final String itemId, final String mdRecordXml) throws RemoteException,
        SystemException, LockingException, MissingAttributeValueException, MissingMethodParameterException,
        InvalidStatusException, AuthenticationException, XmlSchemaNotFoundException, ItemNotFoundException,
        AuthorizationException, InvalidXmlException {

        checkNotNull(itemId);

        return put(PATH + "/" + itemId + "/md-records/md-record", mdRecordXml);
    }

    @Override
    public String retrieveMdRecord(final String itemId, final String mdRecordId) throws RemoteException,
        SystemException, MissingMethodParameterException, AuthenticationException, ItemNotFoundException,
        AuthorizationException, MdRecordNotFoundException {

        checkNotNull(itemId);
        checkNotNull(mdRecordId);

        return get(PATH + "/" + itemId + "/md-records/md-record/" + mdRecordId);
    }

    @Override
    public String updateMdRecord(final String itemId, final String mdRecordId, final String mdRecordXml)
        throws RemoteException, SystemException, ReadonlyVersionException, LockingException, AuthenticationException,
        XmlSchemaNotFoundException, ItemNotFoundException, AuthorizationException, InvalidContentException,
        OptimisticLockingException, MissingMethodParameterException, InvalidStatusException,
        ReadonlyViolationException, MdRecordNotFoundException, InvalidXmlException {

        checkNotNull(itemId);
        checkNotNull(mdRecordId);
        checkNotNull(mdRecordXml);

        return post(PATH + "/" + itemId + "/md-records/md-record/" + mdRecordId, mdRecordXml);
    }

    @Override
    public String retrieveMdRecords(final String itemId) throws RemoteException, SystemException,
        MissingMethodParameterException, AuthenticationException, ItemNotFoundException, AuthorizationException {

        checkNotNull(itemId);

        return get(PATH + "/" + itemId + "/md-records");
    }

    @Override
    public String retrieveContentStreams(final String itemId) throws RemoteException, SystemException,
        MissingMethodParameterException, AuthenticationException, ItemNotFoundException, AuthorizationException {

        checkNotNull(itemId);

        return get(PATH + "/" + itemId + "/content-streams");
    }

    @Override
    public String retrieveContentStream(final String itemId, final String contentStreamId) throws RemoteException,
        SystemException, ContentStreamNotFoundException, MissingMethodParameterException, AuthenticationException,
        ItemNotFoundException, AuthorizationException {

        checkNotNull(itemId);
        checkNotNull(contentStreamId);

        return get(PATH + "/" + itemId + "/content-streams/content-stream/" + contentStreamId);
    }

    @Override
    public HttpInputStream retrieveContentStreamContent(final String itemId, final String contentStreamId)
        throws RemoteException, SystemException, ContentStreamNotFoundException, MissingMethodParameterException,
        AuthenticationException, ItemNotFoundException, AuthorizationException {

        checkNotNull(itemId);
        checkNotNull(contentStreamId);

        return getStream(PATH + "/" + itemId + "/content-streams/content-stream/" + contentStreamId + "/content");
    }

    @Override
    public HttpInputStream retrieveContent(final String itemId, final String componentId) throws RemoteException,
        SystemException, ComponentNotFoundException, MissingMethodParameterException, AuthenticationException,
        ItemNotFoundException, AuthorizationException {

        checkNotNull(itemId);
        checkNotNull(componentId);

        return getStream(PATH + "/" + itemId + "/components/component/" + componentId + "/content");
    }

    @Override
    public HttpInputStream retrieveContent(
        final String itemId, final String componentId, final String transformer, final Map<String, String[]> transParams)
        throws RemoteException, SystemException, ComponentNotFoundException, MissingMethodParameterException,
        AuthenticationException, ItemNotFoundException, AuthorizationException {

        checkNotNull(itemId);
        checkNotNull(componentId);
        checkNotNull(transformer);

        String params = getGetParams(transParams);
        params = params == null ? "" : "?" + params;

        return getStream(PATH + "/" + itemId + "/components/component/" + componentId + "/content/" + transformer
            + params);
    }

    @Override
    public String retrieveProperties(final String itemId) throws RemoteException, SystemException,
        MissingMethodParameterException, AuthenticationException, ItemNotFoundException, AuthorizationException {

        checkNotNull(itemId);

        return get(PATH + "/" + itemId + "/properties");
    }

    @Override
    public String retrieveVersionHistory(final String itemId) throws RemoteException, SystemException,
        MissingMethodParameterException, AuthenticationException, ItemNotFoundException, AuthorizationException {

        checkNotNull(itemId);

        return get(PATH + "/" + itemId + "/resources/version-history");
    }

    @Override
    public String retrieveRelations(final String itemId) throws RemoteException, SystemException,
        MissingMethodParameterException, AuthenticationException, ItemNotFoundException, AuthorizationException {

        checkNotNull(itemId);

        return get(PATH + "/" + itemId + "/relations");
    }

    @Override
    public String revise(final String itemId, final String taskParam) throws RemoteException,
        OptimisticLockingException, SystemException, ReadonlyVersionException, LockingException,
        MissingMethodParameterException, InvalidStatusException, ReadonlyViolationException, AuthenticationException,
        AuthorizationException, ItemNotFoundException, InvalidContentException, InvalidXmlException {

        checkNotNull(itemId);

        return post(PATH + "/" + itemId + "/revise", taskParam);
    }

    @Override
    public String withdraw(final String itemId, final String taskParam) throws RemoteException, SystemException,
        ReadonlyVersionException, LockingException, AlreadyWithdrawnException, AuthenticationException,
        ItemNotFoundException, AuthorizationException, OptimisticLockingException, MissingMethodParameterException,
        NotPublishedException, InvalidStatusException, ReadonlyViolationException, InvalidXmlException {

        checkNotNull(itemId);

        return post(PATH + "/" + itemId + "/withdraw", taskParam);
    }

    @Override
    public void deleteComponent(final String itemId, final String componentId) throws RemoteException, SystemException,
        ComponentNotFoundException, LockingException, MissingMethodParameterException, InvalidStatusException,
        AuthenticationException, ItemNotFoundException, AuthorizationException {

        checkNotNull(itemId);
        checkNotNull(componentId);

        del(PATH + "/" + itemId + "/components/component/" + componentId);
    }

    @Override
    public String moveToContext(final String id, final String taskParam) throws RemoteException, SystemException,
        LockingException, MissingMethodParameterException, InvalidStatusException, AuthenticationException,
        ItemNotFoundException, AuthorizationException, ContextNotFoundException, InvalidContentException {

        checkNotNull(id);
        checkNotNull(taskParam);

        return post(PATH + "/" + id + "/move-to-context", taskParam);
    }

    @SuppressWarnings( { "rawtypes", "unchecked" })
    @Override
    @Deprecated
    public String retrieveItems(final HashMap filter) throws RemoteException, SystemException,
        MissingMethodParameterException, AuthenticationException, AuthorizationException, InvalidXmlException {

        return get(PATH + "s", filter);
    }

    @Override
    public String retrieveItems(final SearchRetrieveRequestType filter) throws RemoteException, SystemException,
        MissingMethodParameterException, AuthenticationException, AuthorizationException, InvalidXmlException {

        checkNotNull(filter);

        return get(PATH + "s" + getEscidoc12Filter(filter));
    }

    @Override
    public String retrieveItems(final ExplainRequestType filter) throws RemoteException, SystemException,
        MissingMethodParameterException, AuthenticationException, AuthorizationException, InvalidXmlException {

        checkNotNull(filter);

        return get(PATH + "s" + getEscidoc12Filter(filter));
    }

    @Override
    public String assignVersionPid(final String itemId, final String taskParam) throws RemoteException,
        OptimisticLockingException, SystemException, LockingException, MissingMethodParameterException,
        InvalidStatusException, AuthenticationException, ItemNotFoundException, AuthorizationException,
        InvalidXmlException {

        checkNotNull(itemId);

        return post(PATH + "/" + itemId + "/assign-version-pid", taskParam);
    }

    @Override
    public String assignObjectPid(final String itemId, final String taskParam) throws RemoteException,
        OptimisticLockingException, SystemException, LockingException, MissingMethodParameterException,
        InvalidStatusException, AuthenticationException, ItemNotFoundException, AuthorizationException,
        InvalidXmlException {

        checkNotNull(itemId);

        return post(PATH + "/" + itemId + "/assign-object-pid", taskParam);
    }

    @Override
    public String assignContentPid(final String itemId, final String componentId, final String taskParam)
        throws RemoteException, OptimisticLockingException, SystemException, LockingException,
        MissingMethodParameterException, ComponentNotFoundException, InvalidStatusException, AuthenticationException,
        ItemNotFoundException, AuthorizationException, InvalidXmlException {

        checkNotNull(itemId);
        checkNotNull(componentId);

        return post(PATH + "/" + itemId + "/components/component/" + componentId + "/assign-content-pid", taskParam);
    }

    @Override
    public String addContentRelations(final String itemId, final String taskParam) throws RemoteException,
        SystemException, ReadonlyVersionException, LockingException, AlreadyExistsException, AuthenticationException,
        ReferencedResourceNotFoundException, AuthorizationException, ItemNotFoundException, InvalidContentException,
        OptimisticLockingException, RelationPredicateNotFoundException, ReadonlyAttributeViolationException,
        MissingMethodParameterException, InvalidStatusException, ReadonlyViolationException,
        MissingElementValueException, InvalidXmlException {

        checkNotNull(itemId);

        return post(PATH + "/" + itemId + "/content-relations/add", taskParam);
    }

    @Override
    public String removeContentRelations(final String itemId, final String taskParam) throws RemoteException,
        SystemException, ContentRelationNotFoundException, ReadonlyVersionException, LockingException,
        AuthenticationException, ItemNotFoundException, AuthorizationException, InvalidContentException,
        OptimisticLockingException, MissingMethodParameterException, InvalidStatusException,
        ReadonlyViolationException, AlreadyDeletedException, InvalidXmlException, MissingElementValueException {

        checkNotNull(itemId);

        return post(PATH + "/" + itemId + "/content-relations/remove", taskParam);
    }

    @Override
    public String retrieveParents(final String itemId) throws RemoteException, SystemException, AuthorizationException,
        AuthenticationException, ItemNotFoundException, MissingMethodParameterException {

        checkNotNull(itemId);

        return get(PATH + "/" + itemId + "/resources/parents");
    }
}
