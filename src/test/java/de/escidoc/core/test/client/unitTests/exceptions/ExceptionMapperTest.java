package de.escidoc.core.test.client.unitTests.exceptions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.apache.ws.security.WSSecurityException;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.escidoc.core.client.exceptions.EscidocException;
import de.escidoc.core.client.exceptions.ExceptionMapper;
import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.client.exceptions.TransportException;
import de.escidoc.core.client.exceptions.application.invalid.ContextNotEmptyException;

/**
 * Test mapping of Exceptions.
 * 
 * 
 */
public class ExceptionMapperTest {

    private static final Logger LOG = LoggerFactory.getLogger(ExceptionMapperTest.class);

    /**
     * 
     */
    @Test
    public void testMapInternalClientException() {
        InternalClientException ice = new InternalClientException();
        try {
            ExceptionMapper.map(ice, LOG);
            fail("expected exception");
        }
        catch (EscidocException e) {
            fail("Threw wrong exception.");
        }
        catch (InternalClientException e) {
            assertSame(ice, e);
        }
        catch (TransportException e) {
            fail("Threw wrong exception.");
        }
    }

    /**
     * 
     */
    @Test
    public void testMapEscidocException() {
        de.escidoc.core.common.exceptions.remote.application.invalid.ContextNotEmptyException cne =
            new de.escidoc.core.common.exceptions.remote.application.invalid.ContextNotEmptyException(123, "123 error",
                "The context is not empty");
        Exception cause = new IndexOutOfBoundsException("foo != bar");
        cne.detail = cause;
        try {
            ExceptionMapper.map(cne, LOG);
            fail("expected exception");
        }
        catch (EscidocException e) {
            assertEquals(ContextNotEmptyException.class, e.getClass());
            assertEquals("The context is not empty", e.getHttpStatusMsg());
            assertEquals("123 error", e.getHttpStatusLine());
            assertEquals(123, e.getHttpStatusCode());
            assertSame(cause, e.getCause().getCause());
        }
        catch (InternalClientException e) {
            fail("Threw wrong exception.");
        }
        catch (TransportException e) {
            fail("Threw wrong exception.");
        }
    }

    /**
     * 
     * @
     */
    @Test(expected = IllegalArgumentException.class)
    public void testMapEscidocException2() {
        de.escidoc.core.common.exceptions.remote.application.invalid.ContextNotEmptyException cne = null;
        try {
            ExceptionMapper.map(cne, LOG);
            fail("expected exception");
        }
        catch (EscidocException e) {
            fail("Didn't expect this exception.");
        }
        catch (InternalClientException e) {
            assertEquals(InternalClientException.class, e.getClass());
        }
        catch (TransportException e) {
            fail("Threw wrong exception.");
        }
    }

    /**
     * 
     */
    @Test
    public void testTransportException() {

        Exception cause = new IndexOutOfBoundsException("foo != bar");
        WSSecurityException wse = new WSSecurityException("foo message", cause);
        try {
            ExceptionMapper.map(wse, LOG);
            fail("expected exception");
        }
        catch (EscidocException e) {
            fail("Threw wrong exception.");
        }
        catch (InternalClientException e) {
            fail("Threw wrong exception.");
        }
        catch (TransportException e) {
            assertEquals(TransportException.class, e.getClass());
            assertSame(wse, e.getCause());
            assertSame(cause, e.getCause().getCause());
            assertTrue(e.getCause().getMessage().startsWith("foo message"));
        }
    }

    /**
     * 
     */
    @Test
    public void testOtherException() {
        Exception ex = new IndexOutOfBoundsException("foo != bar");
        try {
            ExceptionMapper.map(ex, LOG);
            fail("expected exception");
        }
        catch (EscidocException e) {
            fail("Threw wrong exception.");
        }
        catch (InternalClientException e) {
            assertEquals(InternalClientException.class, e.getClass());
            assertEquals(ex, e.getCause());
        }
        catch (TransportException e) {
            fail("Threw wrong exception.");
        }
    }
}
