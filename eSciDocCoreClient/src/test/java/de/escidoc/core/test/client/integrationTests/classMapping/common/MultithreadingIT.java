/**
 * 
 */
package de.escidoc.core.test.client.integrationTests.classMapping.common;

import gov.loc.www.zing.srw.ExplainRequestType;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.Thread.UncaughtExceptionHandler;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.DefaultHttpClient;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import de.escidoc.core.client.Authentication;
import de.escidoc.core.client.ContainerHandlerClient;
import de.escidoc.core.client.ContentModelHandlerClient;
import de.escidoc.core.client.ItemHandlerClient;
import de.escidoc.core.client.OrganizationalUnitHandlerClient;
import de.escidoc.core.client.exceptions.EscidocException;
import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.client.exceptions.TransportException;
import de.escidoc.core.client.interfaces.ContainerHandlerClientInterface;
import de.escidoc.core.client.interfaces.ContentModelHandlerClientInterface;
import de.escidoc.core.client.interfaces.ItemHandlerClientInterface;
import de.escidoc.core.client.interfaces.OrganizationalUnitHandlerClientInterface;
import de.escidoc.core.client.rest.serviceLocator.HttpClientFactory;
import de.escidoc.core.common.configuration.ConfigurationProvider;
import de.escidoc.core.resources.common.MetadataRecord;
import de.escidoc.core.resources.common.MetadataRecords;
import de.escidoc.core.resources.common.properties.ContentModelSpecific;
import de.escidoc.core.resources.common.reference.ContentModelRef;
import de.escidoc.core.resources.common.reference.ContextRef;
import de.escidoc.core.resources.om.item.Item;
import de.escidoc.core.resources.oum.OrganizationalUnit;
import de.escidoc.core.resources.oum.OrganizationalUnitProperties;
import de.escidoc.core.test.client.EscidocClientTestBase;
import de.escidoc.core.test.client.integrationTests.classMapping.om.ResourceUtility;

/**
 * @author Marko Voß
 * 
 */
public class MultithreadingIT {

    private static final Logger LOG = LoggerFactory.getLogger(MultithreadingIT.class);

    private ItemHandlerClientInterface ihc;

    private OrganizationalUnitHandlerClientInterface ouhc;

    private ContainerHandlerClientInterface chc;

    private ContentModelHandlerClientInterface cmmhc;

    private Authentication auth;

    private static final String LOG_SEP = "\n------------------";

    @Before
    public void init() throws Exception {
        auth =
            new Authentication(EscidocClientTestBase.getDefaultInfrastructureURL(),
                EscidocClientTestBase.SYSTEM_ADMIN_USER, EscidocClientTestBase.SYSTEM_ADMIN_PASSWORD);

        ihc = new ItemHandlerClient(auth.getServiceAddress());
        ihc.setHandle(auth.getHandle());

        ouhc = new OrganizationalUnitHandlerClient(auth.getServiceAddress());
        ouhc.setHandle(auth.getHandle());

        chc = new ContainerHandlerClient(auth.getServiceAddress());
        chc.setHandle(auth.getHandle());

        cmmhc = new ContentModelHandlerClient(auth.getServiceAddress());
        cmmhc.setHandle(auth.getHandle());
    }

    @After
    public void post() throws Exception {
        if (auth != null)
            auth.logout();
    }

    private class ThrowableWrapper {

        private String throwableName;

        private Throwable throwable;

        /**
         * @return the additionalMessage
         */
        public String getThrowableName() {
            return throwableName;
        }

        /**
         * @param additionalMessage
         *            the additionalMessage to set
         */
        public void setThrowableName(final String throwableName) {
            this.throwableName = throwableName;
        }

        /**
         * @return the throwable
         */
        public Throwable getThrowable() {
            return throwable;
        }

        /**
         * @param throwable
         *            the throwable to set
         */
        public void setThrowable(final Throwable throwable) {
            this.throwable = throwable;
        }

        /**
         * @return
         */
        public boolean hasThrowable() {
            return this.throwable != null;
        }
    }

    /**
     * @author Marko Voß
     * 
     */
    private abstract class Runner implements Runnable {

        private final Random random = new Random(); // initialize seed

        private final String name;

        private boolean stop = false;

        /**
         * @param name
         */
        private Runner(final String name) {
            this.name = name;
        }

        @Override
        public final void run() {
            try {
                for (int i = 0; i < 100 && !stop; i++) {
                    // wait max. 100ms
                    final int wait = random.nextInt(100);
                    debug("WAITING: " + wait + "ms");
                    Thread.sleep(wait);
                    // execute request
                    debug("EXECUTING CALL");
                    executeCall();
                    debug("CALL FINISHED");
                }
                if (stop) {
                    debug("THREAD EXECUTION ABORTED");
                }
                else {
                    debug("THREAD EXECUTION FINISHED");
                }
            }
            catch (final Exception e) {
                LOG.error(e.getMessage(), e);
                throw new RuntimeException(e);
            }
        }

        /**
         * @param text
         */
        private final void debug(final String text) {
            LOG.debug(new StringBuilder("\n[").append(name).append("] ").append(text).append(LOG_SEP).toString());
        }

        /**
         * Stop the execution of this runner.
         */
        public final void stop() {
            this.stop = true;
        }

        /**
         * @throws Exception
         */
        public abstract void executeCall() throws Exception;
    }

    /**
     * @throws Exception
     */
    @Test
    public void test01() throws Exception {

        /*
         * The first few request will be executed using the default settings
         * anyhow, so the changes to the configuration done here will take
         * effect after a few requests first.
         */

        // overwrite timeouts
        HttpClientFactory.getHttpClient().getParams().setParameter(ConfigurationProvider.HTTP_SOCKET_TIMEOUT, null);
        HttpClientFactory.getHttpClient().getParams().setParameter(ConfigurationProvider.HTTP_CONNECTION_TIMEOUT, null);
        HttpClientFactory
            .getHttpClient().getParams().setParameter(ConfigurationProvider.HTTP_CONN_MANAGER_TIMEOUT, null);
        // overwrite and use default keep-alive-strategy (replace 30000ms with
        // infinite)
        ((DefaultHttpClient) HttpClientFactory.getHttpClient())
            .setKeepAliveStrategy(new DefaultConnectionKeepAliveStrategy());

        final ThreadGroup group = new ThreadGroup("TestCase");
        final List<Runner> runners = new ArrayList<Runner>();

        runners.add(new Runner(
            "T-01") {

            @Override
            public void executeCall() throws Exception {
                ihc.retrieveItems(new ExplainRequestType());
            }
        });

        runners.add(new Runner(
            "T-02") {

            @Override
            public void executeCall() throws Exception {
                ouhc.retrieveOrganizationalUnits(new ExplainRequestType());
            }
        });
        runners.add(new Runner(
            "T-03") {

            @Override
            public void executeCall() throws Exception {
                chc.retrieveContainers(new ExplainRequestType());

            }
        });
        runners.add(new Runner(
            "T-04") {

            @Override
            public void executeCall() throws Exception {
                cmmhc.retrieveContentModels(new ExplainRequestType());
            }
        });
        runners.add(new Runner(
            "T-05") {

            @Override
            public void executeCall() throws Exception {
                ihc.create(getBasicItem());
            }
        });
        runners.add(new Runner(
            "T-06") {

            @Override
            public void executeCall() throws Exception {
                ouhc.create(getBasicOU());
            }
        });

        // uncomment this to test the Exception handling of this test

        // runners.add(new Runner(
        // "T-ABORT") {
        //
        // @Override
        // public void executeCall() throws Exception {
        // throw new Exception("Test Abort.");
        // }
        // });

        final ThrowableWrapper wrapper = new ThrowableWrapper();

        Thread.setDefaultUncaughtExceptionHandler(new UncaughtExceptionHandler() {

            @Override
            public void uncaughtException(final Thread t, final Throwable e) {
                wrapper.setThrowableName(t.getName());
                wrapper.setThrowable(e);
                for (final Runner r : runners) {
                    r.stop();
                }
            }
        });

        LOG.debug("\nSTARTING EXECUTION" + LOG_SEP);
        for (final Runner r : runners) {
            new Thread(group, r, r.name).start();
        }

        // wait till threads have been destroyed or an Exception occurred
        while (group.activeCount() != 0)
            ;

        // evaluate exception
        if (wrapper.hasThrowable()) {
            final String msg =
                "Thread " + wrapper.getThrowableName() + " threw Exception: " + wrapper.getThrowable().getMessage();
            LOG.debug("\nTEST FAILED: " + msg, wrapper.getThrowable());
            throw new IllegalStateException(msg, wrapper.getThrowable());
        }

        LOG.debug("\nTEST FINISHED SUCCESSFULLY");
    }

    /**
     * @return
     * @throws TransportException
     * @throws EscidocException
     * @throws InternalClientException
     * @throws ParserConfigurationException
     */
    @SuppressWarnings("deprecation")
    private final Item getBasicItem() throws ParserConfigurationException, TransportException, EscidocException,
        InternalClientException {
        final Item item = new Item();

        item.getProperties().setContext(new ContextRef(EscidocClientTestBase.getStaticContextId()));
        item.getProperties().setContentModel(new ContentModelRef(EscidocClientTestBase.getStaticContentModelId()));

        // Content-model
        final ContentModelSpecific cms = ResourceUtility.getContentModelSpecific();
        item.getProperties().setContentModelSpecific(cms);

        // Metadata Record(s)
        final MetadataRecords mdRecords = new MetadataRecords();
        final MetadataRecord mdrecord = ResourceUtility.getMdRecord("escidoc");
        mdRecords.add(mdrecord);
        item.setMetadataRecords(mdRecords);

        return item;
    }

    /**
     * @return
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     */
    private final OrganizationalUnit getBasicOU() throws ParserConfigurationException, SAXException, IOException {
        final String ouName = "Generic Organizational Unit";

        final OrganizationalUnit organizationalUnit = new OrganizationalUnit();
        final OrganizationalUnitProperties properties = new OrganizationalUnitProperties();
        organizationalUnit.setProperties(properties);

        final MetadataRecords mdRecords = new MetadataRecords();
        final MetadataRecord mdRecord = new MetadataRecord("escidoc");

        final String str =
            "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + "<ou:organization-details "
                + "xmlns:ou=\"http://escidoc.mpg.de/metadataprofile/schema/0.1/organization\">\n"
                + "<dc:title xmlns:dc=\"http://purl.org/dc/elements/1.1/\">" + ouName + "</dc:title>\n"
                + "</ou:organization-details>";
        final InputStream in = new ByteArrayInputStream(str.getBytes());

        final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        final DocumentBuilder builder = factory.newDocumentBuilder();
        final Document doc = builder.parse(in);
        mdRecord.setContent(doc.getDocumentElement());
        mdRecords.add(mdRecord);

        organizationalUnit.setMetadataRecords(mdRecords);

        return organizationalUnit;
    }
}
