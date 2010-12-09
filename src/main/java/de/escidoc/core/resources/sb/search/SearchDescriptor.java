/**
 * 
 */
package de.escidoc.core.resources.sb.search;

import java.util.Deque;
import java.util.LinkedList;

import de.escidoc.core.resources.sb.Record;
import de.escidoc.core.resources.sb.search.records.resolver.RecordResolver;
import de.escidoc.core.resources.sb.search.records.resolver.ResourceRecordResolver;
import de.escidoc.core.resources.sb.search.records.resolver.SearchResultRecordRecordResolver;

/**
 * @author MVO
 * 
 */
public final class SearchDescriptor {

    /**
     * LinkedList used as a Stack. {@link java.util.Stack} class has
     * synchronized methods and therefore won't be used here.
     */
    private static final Deque<RecordResolver<?, ?>> RESOLVERS =
        new LinkedList<RecordResolver<?, ?>>();

    private SearchDescriptor() {
        // no instance allowed.
    }

    /**
     * @return the resolvers
     */
    public static final Deque<RecordResolver<?, ?>> getResolvers() {
        if (RESOLVERS.size() == 0) {
            // register default clientLib resolvers
            registerResolver(new ResourceRecordResolver());
            registerResolver(new SearchResultRecordRecordResolver());
        }
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
    public static final void registerResolver(
        final RecordResolver<?, ?> resolver) {

        if (resolver == null)
            throw new IllegalArgumentException("resolver must not be null.");
        RESOLVERS.addLast(resolver);
    }
}