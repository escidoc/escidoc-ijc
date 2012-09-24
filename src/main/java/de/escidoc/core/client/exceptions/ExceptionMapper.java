package de.escidoc.core.client.exceptions;

import static de.escidoc.core.common.Precondition.checkNotNull;

import java.lang.reflect.Constructor;
import java.rmi.RemoteException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.escidoc.core.client.TransportProtocol;
import de.escidoc.core.common.exceptions.remote.system.SystemException;
import de.escidoc.core.common.jibx.Marshaller;
import de.escidoc.core.common.jibx.MarshallerFactory;
import de.escidoc.core.common.jibx.binding.HttpStatusInfo;
import de.escidoc.core.common.jibx.binding.RemoteExceptionWrapper;

/**
 * Maps exceptions to an acceptable exception type.
 * 
 * @author SWA
 */
public class ExceptionMapper extends Exception {

    /**
     * Common denominator of package names for exceptions inheriting from
     * AxisFault.
     */
    private static final String PREFIX_REMOTE = "de.escidoc.core.common.exceptions.remote";

    /**
     * Common denominator of package names for exceptions inheriting from
     * {@link EscidocClientException}.
     */
    private static final String PREFIX_CLIENT = "de.escidoc.core.client.exceptions";

    /**
     *
     */
    private static final long serialVersionUID = 263830560183559829L;

    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionMapper.class);

    /**
     * Maps exceptions to an acceptable exception type.
     * <ul>
     * <li>an {@link InternalClientException} is thrown as such.
     * <li>de.escidoc.core.common.exceptions.remote exeptions are mapped to
     * equivalent client.exeptions.
     * <li>a {@link RemoteException java.rmi.RemoteException} is nested in a
     * {@link TransportException}
     * <li>all other exceptions are nested in a {@link InternalClientException}
     * </ul>
     * 
     * @param exception
     *            the exception to map (not null)
     * @param log
     *            the logger to use (not null)
     * @throws EscidocException
     *             if the given exception is a
     *             de.escidoc.core.common.exceptions.remote.EscidocException
     * @throws TransportException
     *             if the given exception is a java.rmi.RemoteException and not
     *             a de.escidoc.core.common.exceptions.remote.EscidocException
     * @throws InternalClientException
     *             if the given exception is an {@link InternalClientException}
     *             or none of the above; thrown if a
     *             de.escidoc.core.common.exceptions.remote.EscidocException
     *             couldn't be mapped
     * @throws IllegalArgumentException
     *             if any of the parameters <code>exception</code> or
     *             <code>log</code> is null.
     */
    public static void map(final Exception exception, final Logger log) throws EscidocException,
        InternalClientException, TransportException {

        checkNotNull(exception);
        checkNotNull(log);

        if (log.isDebugEnabled()) {
            log.debug(exception.getMessage(), exception);
        }

        if (exception instanceof InternalClientException) {
            throw (InternalClientException) exception;
        }
        else if (exception instanceof de.escidoc.core.common.exceptions.remote.EscidocException) {
            constructEscidocException((de.escidoc.core.common.exceptions.remote.EscidocException) exception);
        }
        else if (exception instanceof java.rmi.RemoteException) {
            throw new TransportException("Nested exception", exception);
        }
        else {
            throw new InternalClientException("Nested exception", exception);
        }

    }

    /**
     * Map Exception from Rest response.
     * 
     * @param statusCode
     * @param statusLine
     * @param statusText
     * @throws InternalClientException
     * @throws EscidocException
     */
    public static void constructEscidocException(
        final String exceptionXml, final int statusCode, final String redirectLocation) throws SystemException,
        RemoteException {

        RemoteExceptionWrapper result = null;

        try {
            final Marshaller<RemoteExceptionWrapper> m =
                MarshallerFactory.getInstance(TransportProtocol.REST).getMarshaller(RemoteExceptionWrapper.class);
            m.setUserContext(new HttpStatusInfo(statusCode, redirectLocation));
            result = m.unmarshalDocument(exceptionXml);
        }
        catch (final Exception e) {
            final String msg = "Unable to map exception:\n" + exceptionXml;
            LOGGER.debug(msg, e);
            throw new SystemException(500, msg, e.getMessage());
        }
        throw result.getRemoteException();
    }

    /**
     * 
     * @param commonE
     * @throws InternalClientException
     * @throws EscidocException
     */
    private static void constructEscidocException(
        final de.escidoc.core.common.exceptions.remote.EscidocException commonE) throws InternalClientException,
        EscidocException {

        EscidocException result = null;
        try {
            final Class<?>[] parameterTypes = new Class[] { String.class, Throwable.class };
            final Class<?> exClass = Class.forName(commonE.getClass().getName().replace(PREFIX_REMOTE, PREFIX_CLIENT));
            final Constructor<?> constructor = exClass.getDeclaredConstructor(parameterTypes);

            String msg = commonE.getMessage();
            if (msg == null) {
                msg = commonE.getHttpStatusLine();
            }
            if (msg == null) {
                msg = commonE.getHttpStatusMsg();
            }
            result = (EscidocException) constructor.newInstance(msg, commonE);
        }
        catch (final Exception e) {
            final String msg = "Unable to map exception: " + commonE;
            LOGGER.debug(msg, e);
            throw new InternalClientException(msg, e);
        }
        throw result;
    }
}