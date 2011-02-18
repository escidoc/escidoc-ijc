/**
 * 
 */
package de.escidoc.core.resources.sb.search;

import java.util.Deque;
import java.util.LinkedList;

import de.escidoc.core.resources.sb.Record;
import de.escidoc.core.resources.sb.search.resolver.ContentResolver;
import de.escidoc.core.resources.sb.search.resolver.ResourceResolver;

/**
 * @author MVO
 * 
 */
public final class SearchDescriptor {

    /**
     * LinkedList used as a Stack. {@link java.util.Stack} class has
     * synchronized methods and therefore won't be used here.
     */
    private static final Deque<ContentResolver<?>> RESOLVERS =
        new LinkedList<ContentResolver<?>>();

    static {
        registerResolver(new ResourceResolver());
    }

    private SearchDescriptor() {
        // no instance allowed.
    }

    /**
     * @return the resolvers
     */
    public static final Deque<ContentResolver<?>> getResolvers() {
        return RESOLVERS;
    }

    /**
     * Registers an implementation of {@link RecordResolver} to the LIFO (last
     * in first out) list. Therefore user implementations of
     * {@link RecordResolver} will be checked first, in order to resolve the
     * {@link Record} implementation for the content of the &lt;recordData&gt;
     * tag.
     * 
     * @param resolver
     *            The resolver to add to the LIFO list.
     */
    public static final void registerResolver(final ContentResolver<?> resolver) {

        if (resolver == null)
            throw new IllegalArgumentException("resolver must not be null.");
        RESOLVERS.push(resolver);
    }
}