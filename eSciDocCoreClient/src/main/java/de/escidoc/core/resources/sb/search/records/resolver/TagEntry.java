package de.escidoc.core.resources.sb.search.records.resolver;

import java.net.URI;

/**
 * 
 * @author MVO
 * 
 */
public class TagEntry {

    private final URI namespace;

    private final String tagName;

    /**
     * 
     * @param tagName
     * @param ns
     */
    public TagEntry(final String tagName, final URI ns) {
        if (tagName == null)
            throw new IllegalArgumentException("tagName must not be null.");

        this.tagName = tagName;
        this.namespace = ns;
    }

    /**
     * 
     * @param tagName
     */
    public TagEntry(final String tagName) {
        this(tagName, null);
    }

    /**
     * @return the namespace
     */
    public final URI getNamespace() {
        return namespace;
    }

    /**
     * @return the tagName
     */
    public final String getTagName() {
        return tagName;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result =
            prime * result + (namespace == null ? 0 : namespace.hashCode());
        result = prime * result + (tagName == null ? 0 : tagName.hashCode());
        return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        TagEntry other = (TagEntry) obj;
        if (namespace == null) {
            if (other.namespace != null)
                return false;
        }
        else if (!namespace.equals(other.namespace))
            return false;
        if (tagName == null) {
            if (other.tagName != null)
                return false;
        }
        else if (!tagName.equals(other.tagName))
            return false;
        return true;
    }
}