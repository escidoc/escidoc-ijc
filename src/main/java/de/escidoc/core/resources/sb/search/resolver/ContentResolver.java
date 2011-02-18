/**
 * 
 */
package de.escidoc.core.resources.sb.search.resolver;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

/**
 * @author MVO
 * 
 */
public abstract class ContentResolver<T> {

    private final Map<TagEntry, Class<? extends T>> contentDefinitions =
        new HashMap<TagEntry, Class<? extends T>>();

    /**
     * 
     * @return
     */
    public final Map<TagEntry, Class<? extends T>> getTagEntries() {
        return contentDefinitions;
    }

    /**
     * 
     * @param tagName
     * @param namespace
     * @return
     */
    public final T resolve(
        final String tagName, final URI namespace, final String xmlTextFragment) {

        Class<? extends T> clazz =
            contentDefinitions.get(new TagEntry(tagName, namespace));

        if (clazz != null)
            return getContentInstance(clazz, xmlTextFragment);
        return null;
    }

    /**
     * 
     * @param record
     * @param data
     * @return
     */
    public abstract T getContentInstance(
        final Class<? extends T> clazz, final String xmlTextFragment);
}
