/**
 * 
 */
package de.escidoc.core.client.interfaces;

import de.escidoc.core.client.exceptions.EscidocException;
import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.client.exceptions.TransportException;
import de.escidoc.core.client.interfaces.base.HandlerService;
import de.escidoc.core.resources.adm.LoadExamplesResult.Entry;
import de.escidoc.core.resources.adm.MessagesStatus;
import de.escidoc.core.resources.adm.RepositoryInfo;
import de.escidoc.core.resources.common.MessagesResult;
import de.escidoc.core.resources.common.TaskParam;

/**
 * @author MVO
 * 
 */
public interface AdminHandlerClientInterface extends HandlerService {

    public static final String EXAMPLE_SET_COMMON = "common";

    public MessagesStatus deleteObjects(final TaskParam taskParam)
        throws EscidocException, InternalClientException, TransportException;

    /**
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public MessagesStatus getPurgeStatus() throws EscidocException,
        InternalClientException, TransportException;

    /**
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public MessagesStatus getReindexStatus() throws EscidocException,
        InternalClientException, TransportException;

    /**
     * 
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public String getIndexConfiguration() throws EscidocException,
        InternalClientException, TransportException;

    /**
     * @param clearIndex
     * @param indexNamePrefix
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public MessagesStatus reindex(
        final boolean clearIndex, final String indexNamePrefix)
        throws EscidocException, InternalClientException, TransportException;

    /**
     * 
     * @param clearIndex
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public MessagesStatus reindexAll(final boolean clearIndex)
        throws EscidocException, InternalClientException, TransportException;

    /**
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public RepositoryInfo getRepositoryInfo() throws EscidocException,
        InternalClientException, TransportException;

    /**
     * @param exampleSet
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public MessagesResult<Entry> loadExamples(final String exampleSet)
        throws EscidocException, InternalClientException, TransportException;

    /**
     * Loads the common example set.
     * 
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     */
    public MessagesResult<Entry> loadExamples() throws EscidocException,
        InternalClientException, TransportException;
}
