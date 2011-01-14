package de.escidoc.core.client.interfaces.base;

import de.escidoc.core.client.exceptions.EscidocException;
import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.client.exceptions.TransportException;
import de.escidoc.core.resources.common.MetadataRecord;
import de.escidoc.core.resources.common.MetadataRecords;

/**
 * 
 * @author MVO
 * 
 * @param <T>
 */
public interface MdRecordService<T> {

    /**
     * 
     * @param id
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    MetadataRecords retrieveMdRecords(final String id) throws EscidocException,
        InternalClientException, TransportException;

    /**
     * 
     * @param resource
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    MetadataRecords retrieveMdRecords(final T resource)
        throws EscidocException, InternalClientException, TransportException;

    /**
     * 
     * @param id
     * @param mdRecordId
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    MetadataRecord retrieveMdRecord(final String id, final String mdRecordId)
        throws EscidocException, InternalClientException, TransportException;

    /**
     * 
     * @param resource
     * @param mdRecordId
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    MetadataRecord retrieveMdRecord(final T resource, final String mdRecordId)
        throws EscidocException, InternalClientException, TransportException;
}