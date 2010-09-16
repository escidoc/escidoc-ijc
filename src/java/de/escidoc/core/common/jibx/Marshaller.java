package de.escidoc.core.common.jibx;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;

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
@SuppressWarnings("rawtypes")
public class Marshaller<E> {

    private Class resourceClass;

    private String bindingName;

    /**
     * 
     * @param resourceClass
     * @throws InternalClientException
     */
    public Marshaller(final Class resourceClass) throws InternalClientException {
        this.resourceClass = resourceClass;
    }

    public Marshaller(final Class resourceClass, String bindingName)
        throws InternalClientException {
        this.resourceClass = resourceClass;
        this.bindingName = bindingName;
    }

    /**
     * Unmarshall XML document to Java class.
     * 
     * @param xmlDocument
     *            The XML document.
     * @return The corresponding java class.
     * @throws InternalClientException
     */
    @SuppressWarnings("unchecked")
    public E unmarshalDocument(final String xmlDocument)
        throws InternalClientException {

        E result = null;

        try {
            IBindingFactory bfact = getBindingFactory(resourceClass);
            IUnmarshallingContext uctx = bfact.createUnmarshallingContext();
            ByteArrayInputStream in =
                new ByteArrayInputStream(xmlDocument.getBytes("UTF-8"));
            result = (E) uctx.unmarshalDocument(in, null);
        }
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            throw new InternalClientException(
                "Unmarshalling from XML document to " + resourceClass.getName()
                    + " failed! Document is not 'UTF-8' encoded! ", e);
        }
        catch (JiBXException e) {
            e.printStackTrace();
            throw new InternalClientException(
                "Unmarshalling from XML document to " + resourceClass.getName()
                    + " failed! ", e);
        }
        return result;
    }

    /**
     * Marshall the Java class to XML representation.
     * 
     * @param resource
     *            The Java resource
     * @return The XML representation of the class.
     * @throws InternalClientException
     */
    public String marshalDocument(final E resource)
        throws InternalClientException {

        String result = null;

        try {
            IBindingFactory bfact = getBindingFactory(resource.getClass());
            IMarshallingContext mctx = bfact.createMarshallingContext();
            mctx.setIndent(2);
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            mctx.marshalDocument(resource, "UTF-8", null, out);
            result = new String(out.toByteArray(), "UTF-8");
        }
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            throw new InternalClientException("Marshalling from "
                + resourceClass.getName()
                + " to XML document failed due to wrong encoding ('UTF-8')!", e);
        }
        catch (JiBXException e) {
            e.printStackTrace();
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
     * 
     * @param resource
     * @return
     * @throws JiBXException
     */
    private IBindingFactory getBindingFactory(final Class resource)
        throws JiBXException {
        if (bindingName != null && !bindingName.isEmpty()) {
            return BindingDirectory.getFactory(bindingName, resource);
        }
        else {
            return BindingDirectory.getFactory(resource);
        }
    }
}
