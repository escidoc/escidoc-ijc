/**
 * 
 */
package de.escidoc.core.common.jibx.binding;

import java.lang.reflect.Constructor;
import java.rmi.RemoteException;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jibx.runtime.IAliasable;
import org.jibx.runtime.IMarshaller;
import org.jibx.runtime.IMarshallingContext;
import org.jibx.runtime.IUnmarshaller;
import org.jibx.runtime.IUnmarshallingContext;
import org.jibx.runtime.JiBXException;
import org.jibx.runtime.impl.UnmarshallingContext;

/**
 * @author MVO
 * 
 */
public class ExceptionMarshaller extends MarshallingBase implements IMarshaller, IUnmarshaller, IAliasable {

    private static final String TAG_TITLE = "title";

    private static final String TAG_MESSAGE = "message";

    private static final String TAG_P = "p";

    private static final String TAG_CLASS = "class";

    private static final String TAG_STACK_TRACE = "stack-trace";

    private static final String TAG_CAUSE = "cause";

    /**
     * <ul>
     * <li>group 1: declaring class</li>
     * <li>group 2: method separator</li>
     * <li>group 3: method name</li>
     * <li>group 4: filename</li>
     * <li>group 5: line number</li>
     * </ul>
     */
    private static final Pattern STACK_TRACE_ELEMENT_PATTERN =
        Pattern.compile("\\s*(.+)([$]|[.])([^$^.]+)[(]([^:]+):(\\d+)[)]\\s*");

    /**
     * Common denominator of package names for exceptions inheriting from
     * AxisFault.
     */
    private static final String PREFIX_REMOTE = "de.escidoc.core.common.exceptions.remote";

    private static final String PREFIX_COMMON = "de.escidoc.core.common.exceptions";

    /**
     * Non-aliased constructor
     */
    public ExceptionMarshaller() {
        super(null, 0, "exception");
    }

    /**
     * @param uri
     * @param index
     * @param name
     */
    public ExceptionMarshaller(final String uri, final int index, final String name) {
        super(uri, index, name);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.jibx.runtime.IUnmarshaller#isPresent(org.jibx.runtime.
     * IUnmarshallingContext)
     */
    @Override
    public boolean isPresent(final IUnmarshallingContext ictx) throws JiBXException {
        return ictx.isAt(getUri(), getName());
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.jibx.runtime.IMarshaller#isExtension(java.lang.String)
     */
    @Override
    public boolean isExtension(final String arg0) {
        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.jibx.runtime.IUnmarshaller#unmarshal(java.lang.Object,
     * org.jibx.runtime.IUnmarshallingContext)
     */
    @Override
    public Object unmarshal(final Object obj, final IUnmarshallingContext ictx) throws JiBXException {

        if (!(ictx instanceof UnmarshallingContext))
            throw new IllegalArgumentException("UnmarshallingContext is not of expected type.");
        if (ictx.getUserContext() == null)
            throw new IllegalArgumentException("No user context object available.");
        if (!(ictx.getUserContext() instanceof HttpStatusInfo))
            throw new IllegalArgumentException(
                "Unexpected type of the supplied user context object. Expected HttpStatusInfo class.");

        UnmarshallingContext ctx = (UnmarshallingContext) ictx;
        HttpStatusInfo httpInfo = (HttpStatusInfo) ctx.getUserContext();

        // wrap the result into a wrapper-class since we are not able
        // to compile JiBX into java.rmi.RemoteException.
        return new RemoteExceptionWrapper((RemoteException) parseException(ctx, httpInfo, true));
    }

    /**
     * 
     * @param ctx
     * @param httpInfo
     * @param isRootException
     * @return
     * @throws JiBXException
     */
    private Exception parseException(
        final UnmarshallingContext ctx, final HttpStatusInfo httpInfo, final boolean isRootException)
        throws JiBXException {

        Exception result = null;
        String message = null;
        String className = null;
        String stackTrace = null;

        ctx.parsePastStartTag(getUri(), getName());

        // ignore title tag
        ctx.parsePastElement(getUri(), TAG_TITLE);

        // get message text (optional)
        ctx.parsePastStartTag(getUri(), TAG_MESSAGE);
        message = ctx.parseElementText(getUri(), TAG_P, null);
        ctx.parsePastEndTag(getUri(), TAG_MESSAGE);

        // get class (required)
        ctx.parsePastStartTag(getUri(), TAG_CLASS);
        className = ctx.parseElementText(getUri(), TAG_P);
        className = className.replace(PREFIX_COMMON, PREFIX_REMOTE);
        ctx.parsePastEndTag(getUri(), TAG_CLASS);

        // get stack trace (optional)
        if (ctx.isAt(getUri(), TAG_STACK_TRACE)) {
            ctx.parsePastStartTag(getUri(), TAG_STACK_TRACE);
            stackTrace = ctx.parseElementText(getUri(), TAG_P);
            ctx.parsePastEndTag(getUri(), TAG_STACK_TRACE);
        }

        // create instance
        result = createInstance(className, message, httpInfo, isRootException);
        if (stackTrace != null) {
            result.setStackTrace(getStackTraceElements(stackTrace));
        }

        /*
         * We do not parse the cause of the Exception and all sub-causes since
         * java.rmi.RemoteException does not allow a cause to be set.
         * RemoteException are not intended to have a cause-hierarchy. However,
         * it is still possible to do that, if the RemoteExceptionWrapper wraps
         * the RemoteException and stores the cause of this Exception as well.
         * In this case, RemoteExceptionWrapper has to extend
         * java.rmi.RemoteException in order to comply to the Axis interfaces
         * used in the HandlerClient-classes. This would be an ugly
         * implementation and because of that, the ClientLib will not support
         * the nested Exceptions, returned by the infrastructure.
         */

        /*
         * // get cause (optional) if (ctx.isAt(getUri(), TAG_CAUSE)) {
         * ctx.parsePastStartTag(getUri(), TAG_CAUSE); // set cause to current
         * Exception result.initCause(parseException(ctx, httpInfo, false));
         * ctx.parsePastEndTag(getUri(), TAG_CAUSE); }
         */

        if (ctx.isAt(getUri(), TAG_CAUSE)) {
            ctx.parsePastElement(getUri(), TAG_CAUSE);
        }

        ctx.parsePastEndTag(getUri(), getName());

        return result;
    }

    /**
     * 
     * @param className
     * @param message
     * @param httpInfo
     * @param isRootException
     * @return
     * @throws JiBXException
     */
    private Exception createInstance(
        final String className, final String message, final HttpStatusInfo httpInfo, final boolean isRootException)
        throws JiBXException {
        Exception result = null;
        try {
            Class<?> exClass = null;

            try {
                exClass = Class.forName(className);
            }
            catch (ClassNotFoundException e) {
                /*
                 * If the class was not found, the Exception is no
                 * RemoteException and therefore not available in the class-path
                 * of the ClientLib.
                 */
                if (isRootException) {
                    // the root Exception must be a RemoteException
                    throw new ClassNotFoundException(
                        "Root Exception must be a RemoteException or does not exists in the class-path.", e);
                }
                else {
                    // use a simple Exception class instead for non-root
                    // Exceptions
                    exClass = Exception.class;
                }
            }

            /**
             * SecurityExceptions do have a 4th parameter "redirectLocation",
             * which is not included in the XML response of the infrastructure.
             * In order to instantiate such exceptions, we have to pass null as
             * a 4th parameter.
             */
            if (de.escidoc.core.common.exceptions.remote.application.security.SecurityException.class
                .isAssignableFrom(exClass)) {
                Constructor<?> constructor =
                    exClass.getDeclaredConstructor(new Class[] { int.class, String.class, String.class, String.class });
                result =
                    (RemoteException) constructor.newInstance(httpInfo.getStatusCode(), null, message, httpInfo
                        .getRedirectLocation());
            }
            else if (RemoteException.class.isAssignableFrom(exClass)) {
                Constructor<?> constructor =
                    exClass.getDeclaredConstructor(new Class[] { int.class, String.class, String.class });
                result = (RemoteException) constructor.newInstance(httpInfo.getStatusCode(), null, message);
            }
            else {
                Constructor<?> constructor = exClass.getDeclaredConstructor(new Class[] { String.class });
                result = (Exception) constructor.newInstance(message);
            }
        }
        catch (Exception e) {
            throw new JiBXException("Unable to map Exception.", e);
        }
        return result;
    }

    /**
     * 
     * @param stackTrace
     * @return
     */
    private StackTraceElement[] getStackTraceElements(final String stackTrace) {
        List<StackTraceElement> list = new LinkedList<StackTraceElement>();

        String[] lines = stackTrace.split("[\\r\\n]+");

        for (String line : lines) {

            Matcher m = STACK_TRACE_ELEMENT_PATTERN.matcher(line);
            if (m.find()) {
                list.add(new StackTraceElement(m.group(1), m.group(3), m.group(4), new Integer(m.group(5)).intValue()));
            }

        }
        return list.toArray(new StackTraceElement[list.size()]);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.jibx.runtime.IMarshaller#marshal(java.lang.Object,
     * org.jibx.runtime.IMarshallingContext)
     */
    @Override
    public void marshal(final Object obj, final IMarshallingContext ictx) throws JiBXException {
        throw new UnsupportedOperationException("Marshalling of Exceptions not supported.");
    }

}
