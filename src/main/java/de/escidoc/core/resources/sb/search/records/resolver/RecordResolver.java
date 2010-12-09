/**
 * 
 */
package de.escidoc.core.resources.sb.search.records.resolver;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import de.escidoc.core.resources.sb.Record;
import de.escidoc.core.resources.sb.search.records.RecordMetaData;

/**
 * @author MVO
 * 
 */
public abstract class RecordResolver<T extends Record<?>, MapValueType> {

    private final Map<TagEntry, MapValueType> resolvableRecordDefinitions =
        new HashMap<TagEntry, MapValueType>();

    private boolean ignoreNS = false;

    /**
     * @return the resolvableRecordDefinitions
     */
    protected final Map<TagEntry, MapValueType> getResolvableRecordDefinitions() {
        return resolvableRecordDefinitions;
    }

    /**
     * 
     * @return
     */
    public final Set<TagEntry> getTagEntries() {
        return resolvableRecordDefinitions.keySet();
    }

    /**
     * 
     * @param tagName
     * @param namespace
     * @return
     */
    public final T resolve(
        final String tagName, final URI namespace, final RecordMetaData data) {
        MapValueType value =
            resolvableRecordDefinitions.get(new TagEntry(tagName, namespace));

        if (value == null && ignoreNS) {
            value = resolvableRecordDefinitions.get(new TagEntry(tagName));
        }

        if (value == null)
            return null;

        return getRecordInstance(value, data);
    }

    /**
     * 
     * @param record
     * @param data
     * @return
     */
    public abstract T getRecordInstance(
        final MapValueType value, final RecordMetaData data);

    /**
     * @return the ignoreNS
     */
    public final boolean isIgnoreNS() {
        return ignoreNS;
    }

    /**
     * @param ignoreNS
     *            the ignoreNS to set
     */
    public final void setIgnoreNS(final boolean ignoreNS) {
        this.ignoreNS = ignoreNS;
    }

}
