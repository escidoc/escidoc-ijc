package de.escidoc.core.client.exceptions;

import java.lang.reflect.Constructor;
import java.rmi.RemoteException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import de.escidoc.core.common.exceptions.remote.system.SystemException;

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
    private static final String PREFIX_REMOTE =
        "de.escidoc.core.common.exceptions.remote";

    private static final String PREFIX_COMMON =
        "de.escidoc.core.common.exceptions";

    /**
     * Common denominator of package names for exceptions inheriting from
     * {@link EscidocClientException}.
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
     * <li>an {@link InternalClientException} is thrown as such.
     * <li>de.escidoc.core.common.exceptions.remote exeptions are mapped to
     * equivalent client.exeptions.
     * <li>a {@link RemoteException java.rmi.RemoteException} is nested in a
     * {@link TransportException}
     * <li>all other exceptions are nested in a {@link InternalClientException}
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
    public static void map(final Exception exception) throws EscidocException,
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
        final int statusCode, final String statusLine, final String statusText)
        throws SystemException, RemoteException {

        RemoteException result = null;
        String exceptionClassName = null;
        String exceptionMessage = null;
        String exceptionCause = null;

        int pos = statusText.indexOf("</class>");
        String subString = statusText.substring(0, pos + 10);
        try {

            exceptionClassName = obtainClassName(subString);
            exceptionMessage = obtainExceptionMessage(subString);
            exceptionMessage = obtainExceptionCause(statusText);

            Class<?>[] parameterTypes =
                new Class[] { int.class, String.class, String.class };

            Class<?> exClass = Class.forName(exceptionClassName);
            Constructor<?> constructor =
                exClass.getDeclaredConstructor(parameterTypes);
            result =
                (RemoteException) constructor.newInstance(statusCode,
                    exceptionMessage, statusText);

        }
        catch (Exception e) {
            String msg = "Unable to map exception: " + statusLine;
            LOGGER.debug(msg, e);
            throw new SystemException(500, msg, e.getMessage());
        }
        throw result;
    }

    private static void constructEscidocException(
        final de.escidoc.core.common.exceptions.remote.EscidocException commonE)
        throws InternalClientException, EscidocException {

        EscidocException result = null;
        try {
            Class<?>[] parameterTypes =
                new Class[] { String.class, Throwable.class };
            Class<?> exClass =
                Class.forName(commonE.getClass().getName().replace(
                    PREFIX_REMOTE, PREFIX_CLIENT));
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

    private static String obtainClassName(final String subString) {

        String exceptionClassName = null;

        Pattern pClass =
            Pattern.compile("[^(<class>)].*<class><p>([^<]*)</p></class>.*",
                Pattern.DOTALL + Pattern.MULTILINE);

        Matcher m = pClass.matcher(subString);
        if (m.find()) {
            exceptionClassName = m.group(1);
            exceptionClassName =
                exceptionClassName.replace(PREFIX_COMMON, PREFIX_REMOTE);
        }

        return exceptionClassName;
    }

    private static String obtainExceptionMessage(final String subString) {

        String exceptionMessage = null;

        Pattern pMessage =
            Pattern.compile(".*<message><p>([^<]*)</p></message>.*",
                Pattern.DOTALL + Pattern.MULTILINE);

        Matcher m = pMessage.matcher(subString);
        if (m.find()) {
            exceptionMessage = m.group(1);
        }
        return exceptionMessage;
    }

    private static String obtainExceptionCause(final String text) {

        String beginTag = "<stack-trace><p><![CDATA[";
        String endTag = "]]></p></stack-trace>";
        int begin = text.indexOf(beginTag);
        int end = text.lastIndexOf(endTag);

        return text.substring(begin + beginTag.length(), end);
    }

}
