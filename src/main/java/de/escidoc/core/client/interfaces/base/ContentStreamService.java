package de.escidoc.core.client.interfaces.base;

import de.escidoc.core.client.exceptions.EscidocException;
import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.client.exceptions.TransportException;
import de.escidoc.core.resources.HttpInputStream;
import de.escidoc.core.resources.common.ContentStream;
import de.escidoc.core.resources.common.ContentStreams;

/**
 * 
 * @author MVO
 * 
 * @param <T>
 */
public interface ContentStreamService {

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
     * @param id
     * @param name
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    HttpInputStream retrieveContentStreamContent(
        final String id, final String name) throws EscidocException,
        InternalClientException, TransportException;
}