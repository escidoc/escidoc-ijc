package de.escidoc.core.common.jibx;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import org.apache.log4j.Logger;
import org.jibx.runtime.BindingDirectory;
import org.jibx.runtime.IBindingFactory;
import org.jibx.runtime.IMarshallingContext;
import org.jibx.runtime.IUnmarshallingContext;
import org.jibx.runtime.JiBXException;

import de.escidoc.core.client.exceptions.InternalClientException;

/**
 * 
 * @author
 * 
 * @param <E>
 */
public class Marshaller<E> {

    private static final Logger LOG = Logger.getLogger(Marshaller.class);

    private final Class<E> resourceClass;

    private String bindingName;

    private Object userContext;

    private static final String BINDING_IN_EXT = "_IN";

    private static final String BINDING_OUT_EXT = "_OUT";

    /**
     * 
     * @param resourceClass
     * @deprecated Use Marshaller.getMarshaller(Class<E> resourceClass) instead.
     */
    @Deprecated
    public Marshaller(final Class<E> resourceClass) {
        this(resourceClass, null);
    }

    /**
     * 
     * @param resourceClass
     * @param bindingName
     * @deprecated Use Marshaller.getMarshaller(Class<E> resourceClass, String
     *             bindingName) instead.
     */
    @Deprecated
    public Marshaller(final Class<E> resourceClass, final String bindingName) {
        if (resourceClass == null)
            throw new IllegalArgumentException(
                "resourceClass must not be null.");

        this.resourceClass = resourceClass;
        this.bindingName = bindingName;
    }

    /**
     * Unmarshall XML document to Java class.<br/>
     * <br/>
     * Thread-safety: The UnmarshallingContext itself is not thread-safe. This
     * method creates a new instance of the UnmarshallingContext for each call.
     * Therefore this class itself is thread-safe.
     * 
     * @param xmlDocument
     *            The XML document.
     * @return The corresponding java class.
     * @throws InternalClientException
     */
    @SuppressWarnings("unchecked")
    public E unmarshalDocument(final String xmlDocument)
        throws InternalClientException {

        if (xmlDocument == null)
            throw new IllegalArgumentException("xmlDocument must not be null.");

        E result = null;

        try {
            IBindingFactory bfact = getBindingFactory(BINDING_IN_EXT);
            IUnmarshallingContext uctx = bfact.createUnmarshallingContext();
            ByteArrayInputStream in =
                new ByteArrayInputStream(xmlDocument.getBytes("UTF-8"));

            uctx.setDocument(in, "UTF-8");
            if (userContext != null)
                uctx.setUserContext(userContext);

            result = (E) uctx.unmarshalElement();
        }
        catch (UnsupportedEncodingException e) {
            if (LOG.isDebugEnabled()) {
                LOG.debug(e.getMessage(), e);
            }
            throw new InternalClientException(
                "Unmarshalling from XML document to " + resourceClass.getName()
                    + " failed! Document is not 'UTF-8' encoded! ", e);
        }
        catch (JiBXException e) {
            if (LOG.isDebugEnabled()) {
                LOG.debug(e.getMessage(), e);
            }
            throw new InternalClientException(
                "Unmarshalling from XML document to " + resourceClass.getName()
                    + " failed! ", e);
        }
        return result;
    }

    /**
     * Unmarshall XML document to Java class.<br/>
     * <br/>
     * Thread-safety: The UnmarshallingContext itself is not thread-safe. This
     * method creates a new instance of the UnmarshallingContext for each call.
     * Therefore this class itself is thread-safe.
     * 
     * @param xmlStream
     *            The XML document as a stream.
     * @return The corresponding java class.
     * @throws InternalClientException
     */
    @SuppressWarnings("unchecked")
    public E unmarshalDocument(final InputStream xmlInputStream)
        throws InternalClientException {

        if (xmlInputStream == null)
            throw new IllegalArgumentException(
                "xmlInputStream must not be null.");

        E result = null;

        try {
            IBindingFactory bfact = getBindingFactory(BINDING_IN_EXT);
            IUnmarshallingContext uctx = bfact.createUnmarshallingContext();
            uctx.setDocument(xmlInputStream, "UTF-8");
            if (userContext != null)
                uctx.setUserContext(userContext);
            result = (E) uctx.unmarshalElement();
        }
        catch (JiBXException e) {
            if (LOG.isDebugEnabled()) {
                LOG.debug(e.getMessage(), e);
            }
            throw new InternalClientException(
                "Unmarshalling from XML document to " + resourceClass.getName()
                    + " failed! ", e);
        }
        return result;
    }

    /**
     * Marshall the Java class to XML representation.<br/>
     * <br/>
     * Thread-safety: The MarshallingContext itself is not thread-safe. This
     * method creates a new instance of the MarshallingContext for each call.
     * Therefore this class itself is thread-safe.
     * 
     * @param resource
     *            The Java resource
     * @return The XML representation of the class.
     * @throws InternalClientException
     */
    public String marshalDocument(final E resource)
        throws InternalClientException {

        if (resource == null)
            throw new IllegalArgumentException("resource must not be null.");

        String result = null;

        try {
            IBindingFactory bfact = getBindingFactory(BINDING_OUT_EXT);
            IMarshallingContext mctx = bfact.createMarshallingContext();
            mctx.setIndent(2);
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            mctx.marshalDocument(resource, "UTF-8", null, out);
            result = new String(out.toByteArray(), "UTF-8");
        }
        catch (UnsupportedEncodingException e) {
            if (LOG.isDebugEnabled()) {
                LOG.debug(e.getMessage(), e);
            }
            throw new InternalClientException("Marshalling from "
                + resourceClass.getName()
                + " to XML document failed due to wrong encoding ('UTF-8')!", e);
        }
        catch (JiBXException e) {
            if (LOG.isDebugEnabled()) {
                LOG.debug(e.getMessage(), e);
            }
            throw new InternalClientException("Marshalling from "
                + resourceClass.getName() + " to XML document failed! ", e);
        }

        return result;
    }

    /**
     * 
     * @param bindingName
     */
    public void setBindingName(final String bindingName) {
        this.bindingName = bindingName;
    }

    /**
     * 
     * @return
     */
    public String getBindingName() {
        return this.bindingName;
    }

    /**
     * @return the userContext
     */
    public final Object getUserContext() {
        return userContext;
    }

    /**
     * @param userContext
     *            the userContext to set
     */
    public final void setUserContext(final Object userContext) {
        this.userContext = userContext;
    }

    /**
     * 
     * @param resource
     * @return
     * @throws JiBXException
     */
    private IBindingFactory getBindingFactory(final String bindingNameExtension)
        throws JiBXException {
        if (bindingName != null && !bindingName.isEmpty()) {
            String name = bindingName;
            if (bindingNameExtension != null) {
                name += bindingNameExtension;
            }
            /*
             * try to get the factory with the binding name extension or without
             * the extension if the first try fails.
             */
            try {
                return BindingDirectory.getFactory(name, resourceClass);
            }
            catch (JiBXException e) {
                return BindingDirectory.getFactory(bindingName, resourceClass);
            }
        }
        else {
            return BindingDirectory.getFactory(resourceClass);
        }
    }

    /**
     * Create a Marshaller for type T.
     * 
     * @param <T>
     * @param clazz
     * @return
     */
    @SuppressWarnings("deprecation")
    public static final <T> Marshaller<T> getMarshaller(final Class<T> clazz) {
        return new Marshaller<T>(clazz);
    }

    /**
     * Create a Marshaller for type T.
     * 
     * @param <T>
     * @param clazz
     * @param bindingName
     * @return
     */
    @SuppressWarnings("deprecation")
    public static final <T> Marshaller<T> getMarshaller(
        final Class<T> clazz, final String bindingName) {
        return new Marshaller<T>(clazz, bindingName);
    }
}
