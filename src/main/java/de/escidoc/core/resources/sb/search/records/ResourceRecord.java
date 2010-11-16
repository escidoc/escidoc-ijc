/**
 * 
 */
package de.escidoc.core.resources.sb.search.records;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.xml.transform.TransformerException;

import org.w3c.dom.Element;

import de.escidoc.core.client.TransportProtocol;
import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.common.jibx.Factory;
import de.escidoc.core.resources.Resource;
import de.escidoc.core.resources.ResourceType;
import de.escidoc.core.resources.sb.Record;

/**
 * @author MVO
 * 
 */
public class ResourceRecord<T extends Resource> extends Record<T> {

    private final Class<T> resource;

    /**
     * 
     * @param clazz
     * @param recordSchema
     * @param recordPacking
     * @param recordPosition
     * @param recordDataDOM
     * @param recordDataText
     * @param transport
     */
    private ResourceRecord(final Class<T> resource, final String recordSchema,
        final String recordPacking, final int recordPosition,
        final Element recordDataDOM, final String recordDataText,
        final TransportProtocol transport) {
        super(recordSchema, recordPacking, recordPosition, recordDataDOM,
            recordDataText, transport);

        if (resource == null)
            throw new IllegalArgumentException("resource must not be null.");
        this.resource = resource;
    }

    /**
     * 
     * @param <T>
     * @param clazz
     * @param recordSchema
     * @param recordPacking
     * @param recordPosition
     * @param recordDataDOM
     * @param recordDataText
     * @param transport
     * @return
     */
    public static final <T extends Resource> ResourceRecord<T> createResourceRecord(
        final Class<T> clazz, final String recordSchema,
        final String recordPacking, final int recordPosition,
        final Element recordDataDOM, final String recordDataText,
        final TransportProtocol transport) {

        return new ResourceRecord<T>(clazz, recordSchema, recordPacking,
            recordPosition, recordDataDOM, recordDataText, transport);
    }

    @Override
    protected T decodeFragmentXML() {
        try {
            return decodeXMLString(getRecordDataDOMAsString());
        }
        catch (TransformerException e) {
            if (LOG.isDebugEnabled()) {
                LOG.debug("Unable to unmarshal recordData.", e);
            }
        }
        return null;
    }

    @Override
    protected T decodeFragmentString() {
        return decodeXMLString(recordDataText);
    }

    /**
     * 
     * @param xml
     * @return
     */
    private T decodeXMLString(final String xml) {
        try {
            return Factory
                .getMarshallerFactory(transport).getMarshaller(resource)
                .unmarshalDocument(xmlHeader + xml);
        }
        catch (InternalClientException e) {
            if (LOG.isDebugEnabled()) {
                LOG.debug("Unable to initialize resource.", e);
            }
        }
        return null;
    }

    /**
     * Default instances
     */
    @Override
    protected T getDefaultInstance() {
        try {
            return this.resource.newInstance();
        }
        catch (InstantiationException e) {
            if (LOG.isDebugEnabled()) {
                LOG.debug("Unable to initialize resource.", e);
            }
        }
        catch (IllegalAccessException e) {
            if (LOG.isDebugEnabled()) {
                LOG.debug("Unable to initialize resource.", e);
            }
        }
        return null;
    }

    /**
     * 
     * @return
     */
    public ResourceType getDataType() {
        try {
            Method method = resource.getMethod("getResourceType");
            T instance = getDefaultInstance();
            return (ResourceType) method.invoke(instance);
        }
        catch (SecurityException e) {
            if (LOG.isDebugEnabled()) {
                LOG.debug("Unable to get ResourceType.", e);
            }
        }
        catch (NoSuchMethodException e) {
            if (LOG.isDebugEnabled()) {
                LOG.debug("Unable to get ResourceType.", e);
            }
        }
        catch (IllegalArgumentException e) {
            if (LOG.isDebugEnabled()) {
                LOG.debug("Unable to get ResourceType.", e);
            }
        }
        catch (IllegalAccessException e) {
            if (LOG.isDebugEnabled()) {
                LOG.debug("Unable to get ResourceType.", e);
            }
        }
        catch (InvocationTargetException e) {
            if (LOG.isDebugEnabled()) {
                LOG.debug("Unable to get ResourceType.", e);
            }
        }
        return null;
    }
}