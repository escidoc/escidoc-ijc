package de.escidoc.core.resources.sb.search;

import static de.escidoc.core.common.Precondition.checkNotEmpty;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.escidoc.core.resources.sb.search.resolver.ContentResolver;

/**
 * SearchResultRecord.
 * 
 * @author MVO
 * 
 */
public class SearchResult {

    private static final Logger LOG = LoggerFactory.getLogger(SearchResult.class);

    private Float score;

    private Highlight highlight;

    private Object content;

    private String contentTextFragment;

    private String base;

    private boolean isParsed = false;

    /**
     * Pattern matches:<br/>
     * <ul>
     * <li>group 0: entire start tag</li>
     * <li>group 1: prefix name</li>
     * <li>group 2: tag name</li>
     * <li>group 3: everything after tag name until closing tag entity</li>
     * <li>group 4: the namespace definition related to the prefix name</li>
     * <li>group 5: the quote used to define the value of the namespace (" or ')
     * </li>
     * <li>group 6: the namespace value</li>
     * </ul>
     */
    private static final Pattern tagNameWithPrefix =
        Pattern.compile("<(?:([^>^:^\\s]*):)?([^>^\\s]+?)(\\s(?:[^>]*?(xmlns[:]\\1[=]([\"']{1})(.*?)\\5))?[^>]*)?>");

    /**
     * @param score
     * @param highlight
     * @param contentTextFragment
     * @param base
     */
    public SearchResult(final Float score, final Highlight highlight, final String contentTextFragment,
        final String base) {

        checkNotEmpty(contentTextFragment);

        this.score = score;
        this.highlight = highlight;
        this.contentTextFragment = contentTextFragment;
        this.base = base;
    }

    public String getBase() {
        return base;
    }

    public void setBase(final String base) {
        this.base = base;
    }

    public Highlight getHighlight() {
        return highlight;
    }

    public void setHighlight(final Highlight highlight) {
        this.highlight = highlight;
    }

    /**
     * Returns the content of the search result. The content will get parsed,
     * only when this method is being called. This method is synchronized, in
     * order to avoid multiple parsing of the same content.
     * 
     * @return
     */
    public synchronized Object getContent() {
        if (content == null) {
            parseContent();
        }
        return content;
    }

    public void setContent(final Object content) {
        this.content = content;
    }

    public Float getScore() {
        return score;
    }

    public void setScore(final Float score) {
        this.score = score;
    }

    /**
     * 
     */
    private final void parseContent() {
        String tagname = null;
        String namespace = null;
        URI ns = null;

        Matcher m = tagNameWithPrefix.matcher(contentTextFragment);
        if (m.find()) {
            tagname = m.group(2);
            namespace = m.group(6);
        }

        if (tagname != null) {

            if (namespace != null) {
                try {
                    ns = new URI(namespace);
                }
                catch (URISyntaxException e) {
                    if (LOG.isDebugEnabled()) {
                        LOG.debug("Invalid namespace.", e);
                    }
                }
            }

            for (ContentResolver<?> contentResolver : SearchDescriptor.getResolvers()) {

                Object result = contentResolver.resolve(tagname, ns, contentTextFragment);
                if (result != null) {
                    content = result;
                    contentTextFragment = null;
                    isParsed = true;
                    return;
                }
            }
        }

        /**
         * If we are unable to resolve the type of the content, Use the XML
         * itself as the content. No Exception is thrown.
         */
        content = contentTextFragment;
        contentTextFragment = null;
    }

    /**
     * @return true if and only if the content of the search result got parsed
     *         and mapped to an Object. The mapping may fail most likely because
     *         the search index got changed core-side and therefore the XML
     *         within the search-result tag is different than expected by
     *         default. In this case, you can register your own
     *         {@link ContentResolver} to the {@link SearchDescriptor} once per
     *         application instance. All registered resolvers will be tried in
     *         order to map the content to an Object. If all of the fail, the
     *         content returned by {@link SearchResult#getContent()} will
     *         be the plain XML text.
     */
    public final boolean isParsed() {
        return isParsed;
    }
}
