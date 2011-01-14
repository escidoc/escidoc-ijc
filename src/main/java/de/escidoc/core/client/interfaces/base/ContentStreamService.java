package de.escidoc.core.client.interfaces.base;

import java.io.InputStream;

import de.escidoc.core.client.exceptions.EscidocException;
import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.client.exceptions.TransportException;
import de.escidoc.core.resources.common.ContentStream;
import de.escidoc.core.resources.common.ContentStreams;

/**
 * 
 * @author MVO
 * 
 * @param <T>
 */
public interface ContentStreamService<T> {

    /**
     * @param id
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    ContentStreams retrieveContentStreams(final String id)
        throws EscidocException, InternalClientException, TransportException;

    /**
     * 
     * @param resource
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    ContentStreams retrieveContentStreams(final T resource)
        throws EscidocException, InternalClientException, TransportException;

    /**
     * 
     * @param id
     * @param name
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    ContentStream retrieveContentStream(final String id, final String name)
        throws EscidocException, InternalClientException, TransportException;

    /**
     * 
     * @param resource
     * @param name
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    ContentStream retrieveContentStream(final T resource, final String name)
        throws EscidocException, InternalClientException, TransportException;

    /**
     * 
     * @param id
     * @param name
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    InputStream retrieveContentStreamContent(final String id, final String name)
        throws EscidocException, InternalClientException, TransportException;

    /**
     * 
     * @param resource
     * @param name
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    InputStream retrieveContentStreamContent(final T resource, final String name)
        throws EscidocException, InternalClientException, TransportException;
}
