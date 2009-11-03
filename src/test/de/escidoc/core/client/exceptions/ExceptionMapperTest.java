package de.escidoc.core.client.exceptions;

import junit.framework.TestCase;

import org.apache.ws.security.WSSecurityException;

import de.escidoc.core.client.exceptions.application.invalid.ContextNotEmptyException;

public class ExceptionMapperTest extends TestCase {

    public void testMapInternalClientException() {
        InternalClientException ice = new InternalClientException();
        try {
            ExceptionMapper.map(ice);
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

    public void testMapEscidocException() {
        de.escidoc.core.common.exceptions.remote.application.invalid.ContextNotEmptyException cne =
            new de.escidoc.core.common.exceptions.remote.application.invalid.ContextNotEmptyException(
                123, "123 error", "The context is not empty");
        Exception cause = new IndexOutOfBoundsException("foo != bar");
        cne.detail = cause;
        try {
            ExceptionMapper.map(cne);
            fail("expected exception");
        }
        catch (EscidocException e) {
            assertEquals(ContextNotEmptyException.class, e.getClass());
            assertEquals("The context is not empty", e.getHttpStatusMsg());
            assertEquals("123 error", e.getHttpStatusLine());
            assertEquals(123, e.getHttpStatusCode());
            assertSame(cause, e.getCause().getCause());
            // e.printStackTrace();
        }
        catch (InternalClientException e) {
            fail("Threw wrong exception.");
        }
        catch (TransportException e) {
            fail("Threw wrong exception.");
        }
    }

    public void testMapEscidocException2() {
        de.escidoc.core.common.exceptions.remote.application.invalid.ContextNotEmptyException cne =
            null;
        try {
            ExceptionMapper.map(cne);
            fail("expected exception");
        }
        catch (EscidocException e) {
            fail("Didn't expect this exception.");
        }
        catch (InternalClientException e) {
            assertEquals(InternalClientException.class, e.getClass());
            // e.printStackTrace();
        }
        catch (TransportException e) {
            fail("Threw wrong exception.");
        }
    }

    // public void testMapEscidocException3() {
    // BrandNewException bne = new BrandNewException();
    // try {
    // ExceptionMapper.map(bne);
    // fail("expected exception");
    // }
    // catch (EscidocException e) {
    // fail("Didn't expect this exception.");
    // }
    // catch (InternalClientException e) {
    // assertEquals(InternalClientException.class, e.getClass());
    // }
    // catch (TransportException e) {
    // fail("Threw wrong exception.");
    // }
    // }
    //
    // private class BrandNewException extends
    // de.escidoc.core.common.exceptions.remote.application.invalid.ContextNotEmptyException
    // {
    // /**
    // *
    // */
    // private static final long serialVersionUID = -1437297204047524784L;
    // }

    public void testTransportException() {
        Exception cause = new IndexOutOfBoundsException("foo != bar");
        WSSecurityException wse = new WSSecurityException("foo message", cause);
        try {
            ExceptionMapper.map(wse);
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
            // e.printStackTrace();
        }
    }

    public void testOtherException() {
        Exception ex = new IndexOutOfBoundsException("foo != bar");
        try {
            ExceptionMapper.map(ex);
            fail("expected exception");
        }
        catch (EscidocException e) {
            fail("Threw wrong exception.");
        }
        catch (InternalClientException e) {
            assertEquals(InternalClientException.class, e.getClass());
            assertEquals(ex, e.getCause());
            // e.printStackTrace();
        }
        catch (TransportException e) {
            fail("Threw wrong exception.");
        }
    }
}
