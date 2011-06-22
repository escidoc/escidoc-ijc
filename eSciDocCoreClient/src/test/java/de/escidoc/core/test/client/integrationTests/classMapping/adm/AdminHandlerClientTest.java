/**
 * 
 */
package de.escidoc.core.test.client.integrationTests.classMapping.adm;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.lang.Thread.UncaughtExceptionHandler;
import java.util.Collection;
import java.util.LinkedList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import de.escidoc.core.client.AdminHandlerClient;
import de.escidoc.core.client.Authentication;
import de.escidoc.core.client.ContainerHandlerClient;
import de.escidoc.core.client.ItemHandlerClient;
import de.escidoc.core.client.OrganizationalUnitHandlerClient;
import de.escidoc.core.client.exceptions.EscidocException;
import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.client.exceptions.TransportException;
import de.escidoc.core.client.interfaces.AdminHandlerClientInterface;
import de.escidoc.core.client.interfaces.ContainerHandlerClientInterface;
import de.escidoc.core.client.interfaces.ItemHandlerClientInterface;
import de.escidoc.core.client.interfaces.OrganizationalUnitHandlerClientInterface;
import de.escidoc.core.resources.adm.AdminStatus;
import de.escidoc.core.resources.adm.LoadExamplesResult.Entry;
import de.escidoc.core.resources.adm.MessagesStatus;
import de.escidoc.core.resources.adm.RepositoryInfo;
import de.escidoc.core.resources.common.MessagesResult;
import de.escidoc.core.resources.common.MetadataRecord;
import de.escidoc.core.resources.common.MetadataRecords;
import de.escidoc.core.resources.common.TaskParam;
import de.escidoc.core.resources.common.reference.ContentModelRef;
import de.escidoc.core.resources.common.reference.ContextRef;
import de.escidoc.core.resources.om.container.Container;
import de.escidoc.core.resources.om.container.ContainerProperties;
import de.escidoc.core.resources.om.item.Item;
import de.escidoc.core.resources.oum.OrganizationalUnit;
import de.escidoc.core.resources.oum.OrganizationalUnitProperties;
import de.escidoc.core.test.client.EscidocClientTestBase;
import de.escidoc.core.test.client.integrationTests.classMapping.om.ResourceUtility;

/**
 * 
 * @author MVO
 * 
 */
public class AdminHandlerClientTest {

    private static final Logger LOG = LoggerFactory.getLogger(AdminHandlerClientTest.class);

    private Authentication auth;

    private AdminHandlerClientInterface ahc;

    /**
     * 
     * @throws Exception
     */
    @Before
    public void init() throws Exception {
        auth =
            new Authentication(EscidocClientTestBase.getDefaultInfrastructureURL(),
                EscidocClientTestBase.SYSTEM_ADMIN_USER, EscidocClientTestBase.SYSTEM_ADMIN_PASSWORD);
        ahc = new AdminHandlerClient(auth.getServiceAddress());
        ahc.setHandle(auth.getHandle());
    }

    @After
    public void post() throws Exception {
        auth.logout();
    }

    /**
     * @throws Exception
     * 
     */
    @Test
    public void testLoadExamples() throws Exception {
        final MessagesResult<Entry> result = ahc.loadExamples();
        for (final Entry entry : result) {
            assertNotNull("Objid is null", entry.getObjid());
            assertNotNull("ResourceType is null", entry.getResourceType());
            assertNotNull("Message is null", entry.getMessage());
        }
    }

    /**
     * 
     * @throws Exception
     */
    @Test
    public void testPurgeStatus() throws Exception {
        final MessagesStatus status = ahc.getPurgeStatus();
        validateMessagesStatus(status);
    }

    /**
     * 
     * @throws Exception
     */
    @Test
    public void testRepositoryInfo() throws Exception {
        final RepositoryInfo info = ahc.getRepositoryInfo();
        // evaluate result
        assertNotNull(RepositoryInfo.KEY_ESCIDOC_CORE_ADMIN_EMAIL + " is null.", info
            .get(RepositoryInfo.KEY_ESCIDOC_CORE_ADMIN_EMAIL));
        assertNotNull(RepositoryInfo.KEY_ESCIDOC_CORE_BASEURL + " is null.", info
            .get(RepositoryInfo.KEY_ESCIDOC_CORE_BASEURL));
        assertNotNull(RepositoryInfo.KEY_ESCIDOC_CORE_BUILD + " is null.", info
            .get(RepositoryInfo.KEY_ESCIDOC_CORE_BUILD));
        assertNotNull(RepositoryInfo.KEY_ESCIDOC_CORE_DATABASE_CONSISTENT + " is null.", info
            .get(RepositoryInfo.KEY_ESCIDOC_CORE_DATABASE_CONSISTENT));
        assertNotNull(RepositoryInfo.KEY_ESCIDOC_CORE_DATABASE_VERSION + " is null.", info
            .get(RepositoryInfo.KEY_ESCIDOC_CORE_DATABASE_VERSION));
        assertNotNull(RepositoryInfo.KEY_ESCIDOC_CORE_EARLIEST_DATE + " is null.", info
            .get(RepositoryInfo.KEY_ESCIDOC_CORE_EARLIEST_DATE));
        assertNotNull(RepositoryInfo.KEY_ESCIDOC_CORE_OM_CONTENT_CHECKSUM_ALGORITHM + " is null.", info
            .get(RepositoryInfo.KEY_ESCIDOC_CORE_OM_CONTENT_CHECKSUM_ALGORITHM));
        assertNotNull(RepositoryInfo.KEY_ESCIDOC_CORE_REPOSITORY_NAME + " is null.", info
            .get(RepositoryInfo.KEY_ESCIDOC_CORE_REPOSITORY_NAME));
        assertNotNull(RepositoryInfo.KEY_GSEARCH_URL + " is null.", info.get(RepositoryInfo.KEY_GSEARCH_URL));
        assertNotNull(RepositoryInfo.KEY_SCHEMA_CONTAINER + " is null.", info.get(RepositoryInfo.KEY_SCHEMA_CONTAINER));
        assertNotNull(RepositoryInfo.KEY_SCHEMA_CONTEXT + " is null.", info.get(RepositoryInfo.KEY_SCHEMA_CONTEXT));
        assertNotNull(RepositoryInfo.KEY_SCHEMA_ITEM + " is null.", info.get(RepositoryInfo.KEY_SCHEMA_ITEM));
        assertNotNull(RepositoryInfo.KEY_SCHEMA_ORGANIZATIONAL_UNIT + " is null.", info
            .get(RepositoryInfo.KEY_SCHEMA_ORGANIZATIONAL_UNIT));
        assertNotNull(RepositoryInfo.KEY_SCHEMA_USER_ACCOUNT + " is null.", info
            .get(RepositoryInfo.KEY_SCHEMA_USER_ACCOUNT));
    }

    /**
     * 
     * @throws Exception
     */
    @Ignore("development")
    @Test
    public void testReindex() throws Exception {
        // ahc.reindex(true, "all");
        // ahc.reindex(true, "escidoc");
        // ahc.reindexAll(true);

        // start reindexing
        final MessagesStatus status = ahc.reindexAll(true);
        final ExceptionStatus exceptionStatus = new ExceptionStatus();

        // request status of recaching
        // do a request every 5 seconds
        final Thread thread = new Thread() {

            @Override
            public void run() {
                MessagesStatus myStatus = status;
                // log initial status
                log(myStatus);

                try {
                    for (; myStatus.getStatusCode() == MessagesStatus.STATUS_IN_PROGRESS;) {
                        // request current status
                        myStatus = ahc.getReindexStatus();
                        // log current status
                        log(myStatus);
                        // wait
                        sleep(5000);

                        /*
                         * uncomment this, if you want to see, that the
                         * exception delegation works correctly:
                         */
                        // throw new InternalClientException("TEST");
                    }
                }
                catch (final EscidocException e) {
                    throw new RuntimeException(e);
                }
                catch (final InternalClientException e) {
                    throw new RuntimeException(e);
                }
                catch (final TransportException e) {
                    throw new RuntimeException(e);
                }
                catch (final InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };
        /*
         * register exception handler to delegate the exception of the thread
         * above back to this test
         */
        thread.setUncaughtExceptionHandler(new UncaughtExceptionHandler() {

            @Override
            public void uncaughtException(final Thread t, final Throwable e) {
                exceptionStatus.setException((Exception) e.getCause());
            }

        });
        // now let's start the thread and wait for it to be finished
        thread.start();
        // wait for the thread
        for (; thread.isAlive();)
            ;
        // evaluate result
        if (exceptionStatus.hasException()) {
            throw exceptionStatus.getException();
        }
        else {
            // test if recache info status is set to finished
            final MessagesStatus newStatus = ahc.getReindexStatus();
            assertTrue(newStatus.getStatusCode() == MessagesStatus.STATUS_FINISHED);
        }
    }

    /**
     * 
     * @throws Exception
     */
    @Ignore("development")
    @Test
    public void testPurge() throws Exception {
        // create some data to delete
        // initialize handler clients
        final ItemHandlerClientInterface ih = new ItemHandlerClient(auth.getServiceAddress());
        ih.setHandle(auth.getHandle());

        final OrganizationalUnitHandlerClientInterface oh =
            new OrganizationalUnitHandlerClient(auth.getServiceAddress());
        oh.setHandle(auth.getHandle());

        final ContainerHandlerClientInterface ch = new ContainerHandlerClient(auth.getServiceAddress());
        ch.setHandle(auth.getHandle());

        final Collection<String> ids = new LinkedList<String>();

        for (int i = 0; i < 20; i++) {
            ids.add(createItem(ih));
            ids.add(createContainer(ch));
            ids.add(createOrganizationalUnit(oh));
        }

        // create task params
        final TaskParam param = new TaskParam();
        param.setKeepInSync(true);
        for (final String id : ids) {
            param.addResourceRef(id);
        }

        // start deletion
        final MessagesStatus status = ahc.deleteObjects(param);
        final ExceptionStatus exceptionStatus = new ExceptionStatus();

        // request status of deletion
        // do a request every 5 seconds
        final Thread thread = new Thread() {

            @Override
            public void run() {
                MessagesStatus myStatus = status;
                // log initial status
                log(myStatus);

                try {
                    for (; myStatus.getStatusCode() == MessagesStatus.STATUS_IN_PROGRESS;) {
                        // request current status
                        myStatus = ahc.getPurgeStatus();
                        // log current status
                        log(myStatus);
                        // wait
                        sleep(5000);

                        /*
                         * uncomment this, if you want to see, that the
                         * exception delegation works correctly:
                         */
                        // throw new InternalClientException("TEST");
                    }
                }
                catch (final EscidocException e) {
                    throw new RuntimeException(e);
                }
                catch (final InternalClientException e) {
                    throw new RuntimeException(e);
                }
                catch (final TransportException e) {
                    throw new RuntimeException(e);
                }
                catch (final InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };
        /*
         * register exception handler to delegate the exception of the thread
         * above back to this test
         */
        thread.setUncaughtExceptionHandler(new UncaughtExceptionHandler() {

            @Override
            public void uncaughtException(final Thread t, final Throwable e) {
                exceptionStatus.setException((Exception) e.getCause());
            }

        });
        // now let's start the thread and wait for it to be finished
        thread.start();
        // wait for the thread
        for (; thread.isAlive();)
            ;
        // evaluate result
        if (exceptionStatus.hasException()) {
            throw exceptionStatus.getException();
        }
        else {
            // test if recache info status is set to finished
            final MessagesStatus newStatus = ahc.getReindexStatus();
            assertTrue(newStatus.getStatusCode() == MessagesStatus.STATUS_FINISHED);
        }
    }

    /**
     * 
     * @param handler
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     * @throws ParserConfigurationException
     */
    private String createItem(final ItemHandlerClientInterface handler) throws EscidocException,
        InternalClientException, TransportException, ParserConfigurationException {
        final Item item = new Item();

        item.getProperties().setContext(new ContextRef(EscidocClientTestBase.getStaticContextId()));
        item.getProperties().setContentModel(new ContentModelRef(EscidocClientTestBase.getStaticContentModelId()));

        // Metadata Record(s)
        final MetadataRecords mdRecords = new MetadataRecords();
        final MetadataRecord mdRecord = ResourceUtility.getMdRecord("escidoc");
        mdRecords.add(mdRecord);
        item.setMetadataRecords(mdRecords);

        // create
        return handler.create(item).getObjid();
    }

    /**
     * 
     * @param handler
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     * @throws ParserConfigurationException
     */
    private String createContainer(final ContainerHandlerClientInterface handler) throws EscidocException,
        InternalClientException, TransportException, ParserConfigurationException {

        final Container container = new Container();

        // properties
        final ContainerProperties properties = new ContainerProperties();
        properties.setContext(new ContextRef(EscidocClientTestBase.getStaticContextId()));
        properties.setContentModel(new ContentModelRef(EscidocClientTestBase.getStaticContentModelId()));

        container.setProperties(properties);

        final MetadataRecords mdRecords = new MetadataRecords();
        final MetadataRecord mdRecord = ResourceUtility.getMdRecord("escidoc");

        mdRecords.add(mdRecord);
        container.setMetadataRecords(mdRecords);

        return handler.create(container).getObjid();
    }

    /**
     * 
     * @param handler
     * @return
     * @throws EscidocException
     * @throws InternalClientException
     * @throws TransportException
     * @throws ParserConfigurationException
     */
    private String createOrganizationalUnit(final OrganizationalUnitHandlerClientInterface handler)
        throws EscidocException, InternalClientException, TransportException, ParserConfigurationException {
        final String ouName = "Generic Organizational Unit " + System.currentTimeMillis();
        final String ouDescription = "Description of Organizational Unit.";

        final OrganizationalUnit organizationalUnit = new OrganizationalUnit();
        final OrganizationalUnitProperties properties = new OrganizationalUnitProperties();
        properties.setName("Organizational_Unit_Test_Name");
        organizationalUnit.setProperties(properties);

        final MetadataRecords mdRecords = new MetadataRecords();

        final MetadataRecord mdRecord = createMdRecordDC("escidoc", "organization-details", ouName, ouDescription);

        mdRecords.add(mdRecord);
        organizationalUnit.setMetadataRecords(mdRecords);

        return handler.create(organizationalUnit).getObjid();
    }

    /**
     * Creates an Metadata Record with DC content.
     * 
     * @param mdRecordName
     *            Name of the MdRecord
     * @param rootElementName
     *            Name of the root element of the MdRecord content
     * @param title
     *            The title which is to set in the DC metadata
     * @param description
     *            The description which is to set in the DC metadata
     * @return The MetadataRecord with the given values
     * @throws ParserConfigurationException
     *             If instantiation of DocumentBuilder fail
     */
    private MetadataRecord createMdRecordDC(
        final String mdRecordName, final String rootElementName, final String title, final String description)
        throws ParserConfigurationException {

        // md-record
        final MetadataRecord mdRecord = new MetadataRecord(mdRecordName);

        final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        factory.setCoalescing(true);
        factory.setValidating(true);
        final DocumentBuilder builder = factory.newDocumentBuilder();

        final Document doc = builder.newDocument();
        final Element mdRecordContent = doc.createElementNS(null, rootElementName);
        mdRecord.setContent(mdRecordContent);

        // title
        final Element titleElmt = doc.createElementNS("http://purl.org/dc/elements/1.1/", "title");
        titleElmt.setPrefix("dc");
        titleElmt.setTextContent(title);
        mdRecordContent.appendChild(titleElmt);

        // dc:description
        final Element descriptionElmt = doc.createElementNS("http://purl.org/dc/elements/1.1/", "description");
        descriptionElmt.setPrefix("dc");
        descriptionElmt.setTextContent(description);
        mdRecordContent.appendChild(descriptionElmt);
        mdRecord.setContent(mdRecordContent);

        return mdRecord;
    }

    /**
     * 
     * @param status
     */
    private void log(final MessagesStatus status) {
        final StringBuilder sb = new StringBuilder();
        sb.append("\n");
        sb.append(status.getStatusMessage().replaceAll("(^\\s+)|(\\s+$)", ""));
        for (final String message : status.getMessages()) {
            sb.append("\n");
            sb.append(message.replaceAll("(^\\s+)|(\\s+$)", ""));
        }
        LOG.debug(sb.toString());
    }

    /**
     * 
     * @param status
     */
    private void validateMessagesStatus(final MessagesStatus status) {
        assertTrue(status.getStatusCode() != AdminStatus.STATUS_INVALID_RESULT);

        if (status.getStatusCode() == AdminStatus.STATUS_FINISHED) {
            assertNotNull(status.getStatusMessage());
            assertTrue(status.getMessages().size() == 0);
        }
        else if (status.getStatusCode() == AdminStatus.STATUS_IN_PROGRESS) {
            assertNotNull(status.getStatusMessage());
            assertTrue(status.getMessages().size() > 0);
        }
        else
            fail("Invalid statusCode: " + status.getStatusCode());
    }

    /**
     * 
     * @author MVO
     * 
     */
    class ExceptionStatus {
        private Exception exception;

        public void setException(final Exception exception) {
            this.exception = exception;
        }

        public Exception getException() {
            return exception;
        }

        public boolean hasException() {
            return exception != null;
        }
    }
}
