package de.escidoc.core.client.exceptions;

import java.lang.reflect.Constructor;
import java.rmi.RemoteException;

import org.apache.log4j.Logger;

/**
 * Maps exceptions to an acceptable exception type.
 *
 * @author SWA
 */
public class ExceptionMapper extends Exception {

    /**
     * Common denominator of package names for exceptions inheriting from AxisFault.
     */
    private static final String PREFIX_COMMON =
        "de.escidoc.core.common.exceptions.remote";

    /**
     * Common denominator of package names for exceptions inheriting from {@link EscidocClientException}.
     */
    private static final String PREFIX_CLIENT =
        "de.escidoc.core.client.exceptions";

    /**
     *
     */
    private static final long serialVersionUID = 263830560183559829L;

    private static final Logger LOGGER =
        Logger.getLogger(ExceptionMapper.class);

    /**
     * Maps exceptions to an acceptable exception type.
     * <ul>
     * <li> an {@link InternalClientException} is thrown as such.
     * <li> de.escidoc.core.common.exceptions.remote exeptions are mapped to
     * equivalent client.exeptions.
     * <li> a {@link RemoteException java.rmi.RemoteException} is nested in a
     * {@link TransportException}
     * <li> all other exceptions are nested in a {@link InternalClientException}
     * </ul>
     *
     * @param exception
     *            the exception to map
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
     */
    public static void map(Exception exception) throws EscidocException,
        InternalClientException, TransportException {
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

    private static void constructEscidocException(
        de.escidoc.core.common.exceptions.remote.EscidocException commonE)
        throws InternalClientException, EscidocException {
        EscidocException result = null;
        try {
            Class<?>[] parameterTypes = new Class[2];
            parameterTypes[0] = String.class;
            parameterTypes[1] = Throwable.class;
            Class<?> exClass =
                Class.forName(commonE.getClass().getName().replace(
                    PREFIX_COMMON, PREFIX_CLIENT));
            Constructor<?> constructor =
                exClass.getDeclaredConstructor(parameterTypes);
            result =
                (EscidocException) constructor.newInstance(
                    commonE.getMessage(), commonE);
        }
        catch (Exception e) {
            String msg = "Unable to map exception: " + commonE;
            LOGGER.debug(msg, e);
            throw new InternalClientException(msg, e);
        }
        throw result;
    }

}
