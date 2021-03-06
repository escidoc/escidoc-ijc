package de.escidoc.core.test.client.integrationTests.stresstests;

import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.escidoc.core.client.Authentication;
import de.escidoc.core.client.ContentModelHandlerClient;
import de.escidoc.core.client.interfaces.ContentModelHandlerClientInterface;
import de.escidoc.core.test.client.EscidocClientTestBase;

public class RetrieveST {

    private Authentication auth;

    private ContentModelHandlerClientInterface cc;

    private boolean failed;

    private int exceptionCount;

    private Exception firstException;

    private static final Logger LOG = LoggerFactory.getLogger(RetrieveST.class);

    @Before
    public void init() {
        try {
            auth =
                new Authentication(EscidocClientTestBase.getDefaultInfrastructureURL(),
                    EscidocClientTestBase.SYSTEM_ADMIN_USER, EscidocClientTestBase.SYSTEM_ADMIN_PASSWORD);

            cc = new ContentModelHandlerClient(auth.getServiceAddress());
            cc.setHandle(auth.getHandle());
            failed = false;
            exceptionCount = 0;
            firstException = null;
        }
        catch (Exception e) {
            failed = true;
            fail("Failed to initialize test case: " + e.getMessage());
        }
    }

    @After
    public void post() throws Exception {
        LOG.debug("Test result: "
            + (!failed ? "Test successful" : "Test failed!\n" + exceptionCount
                + " Exceptions thrown\nFirst Exception:\n"
                + (firstException != null ? firstException.getMessage() : "null")));
        auth.logout();
    }

    /**
     * 
     * @throws Exception
     */
    @Test
    public void testRetrieves1() throws Exception {
        for (int i = 0; i < 500; i++) {
            try {
                cc.retrieve(EscidocClientTestBase.getStaticContentModelId());
            }
            catch (Exception e) {
                if (!failed) {
                    failed = true;
                    firstException = e;
                }
                exceptionCount++;
            }
        }

        if (failed)
            throw firstException;
    }

    /**
     * 
     * @throws Exception
     */
    @Test
    public void testRetrieves2() throws Exception {
        for (int i = 0; i < 500; i++) {
            try {
                cc.retrieve(EscidocClientTestBase.getStaticContentModelId());
            }
            catch (Exception e) {
                if (!failed) {
                    failed = true;
                    firstException = e;
                }
                exceptionCount++;
            }
        }

        if (failed)
            throw firstException;
    }

    /**
     * 
     * @throws Exception
     */
    @Test
    public void testRetrieves3() throws Exception {
        for (int i = 0; i < 500; i++) {
            try {
                cc.retrieve(EscidocClientTestBase.getStaticContentModelId());
            }
            catch (Exception e) {
                if (!failed) {
                    failed = true;
                    firstException = e;
                }
                exceptionCount++;
            }
        }

        if (failed)
            throw firstException;
    }

    /**
     * 
     * @throws Exception
     */
    @Test
    public void testFoo4() throws Exception {
        for (int i = 0; i < 500; i++) {
            try {
                cc.retrieve(EscidocClientTestBase.getStaticContentModelId());
            }
            catch (Exception e) {
                if (!failed) {
                    failed = true;
                    firstException = e;
                }
                exceptionCount++;
            }
        }

        if (failed)
            throw firstException;
    }
}
