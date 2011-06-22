/**
 * 
 */
package de.escidoc.core.resources.sb.search.resolver;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.escidoc.core.client.exceptions.InternalClientException;
import de.escidoc.core.common.jibx.MarshallerFactory;
import de.escidoc.core.resources.Resource;
import de.escidoc.core.resources.ResourceType;

/**
 * @author MVO
 * 
 */
public class ResourceResolver extends ContentResolver<Resource> {

    private static final Logger LOG = LoggerFactory.getLogger(ResourceResolver.class);

    /**
     * 
     */
    public ResourceResolver() {
        Map<TagEntry, Class<? extends Resource>> entries = getTagEntries();

        for (int i = 0; i < ResourceType.values().length; i++) {

            ResourceType type = ResourceType.values()[i];

            if (type.getNamespace() != null) {

                entries.put(new TagEntry(type.getXmlValue(), type.getNamespace()), type.getResourceClass());
            }
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.resources.sb.search.resolver.ContentResolver#
     * getContentInstance(java.lang.Class, java.lang.String)
     */
    @Override
    public Resource getContentInstance(final Class<? extends Resource> clazz, final String xmlTextFragment) {

        // TODO check if marshaller works that way.

        try {
            return MarshallerFactory.getInstance().getMarshaller(clazz).unmarshalDocument(xmlTextFragment);
        }
        catch (InternalClientException e) {
            if (LOG.isDebugEnabled())
                LOG.debug("Failed to unmarshal search-result content to resource: " + clazz.getName());
        }
        return null;
    }

}