/**
 * 
 */
package de.escidoc.core.resources.common;

import java.util.LinkedList;

import org.w3c.dom.Element;

/**
 * @author MVO
 * 
 */
public abstract class MessagesResult<T> extends LinkedList<T> {

    /**
     * 
     */
    private static final long serialVersionUID = 6372913396135637338L;

    /**
     * 
     * @param result
     */
    public MessagesResult(Result result) {
        int i = 0;
        for (Element element : result) {
            add(extractMessage(element, i++));
        }
    }

    protected abstract T extractMessage(
        final Element messageNode, final int messageCount);
}
