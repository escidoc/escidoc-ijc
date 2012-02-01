/**
 * 
 */
package de.escidoc.core.resources.common;

import org.w3c.dom.Element;

/**
 * @author MVO
 * 
 */
public class SimpleMessagesResult extends MessagesResult<String> {

    private static final long serialVersionUID = -2691080562327027112L;

    public SimpleMessagesResult(final Result result) {
        super(result);
    }

    @Override
    protected String extractMessage(final Element messageNode, final int messageCount) {
        return messageNode.getTextContent();
    }

}
