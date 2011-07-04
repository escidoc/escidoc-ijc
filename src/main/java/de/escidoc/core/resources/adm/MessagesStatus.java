/**
 * 
 */
package de.escidoc.core.resources.adm;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.w3c.dom.Element;

import de.escidoc.core.common.XmlUtility;
import de.escidoc.core.resources.common.Result;

/**
 * @author MVO
 * 
 */
public class MessagesStatus extends AdminStatus {

    List<String> messages;

    /**
     * 
     * @param result
     */
    public MessagesStatus(final Result result) {
        messages = new LinkedList<String>();

        if (result.size() == 0) {
            statusMessage = null;
            statusCode = STATUS_INVALID_RESULT;
        }
        else if (result.size() == 1) {
            statusMessage = XmlUtility.getTextContent(result.getFirst());
            statusCode = STATUS_FINISHED;
        }
        else {
            final Iterator<Element> it = result.iterator();
            statusMessage = XmlUtility.getTextContent(it.next());
            while (it.hasNext()) {
                messages.add(XmlUtility.getTextContent(it.next()));
            }
            statusCode = STATUS_IN_PROGRESS;
        }
    }

    /**
     * 
     * @param result
     * @param initialStatus
     */
    public MessagesStatus(final Result result, final int initialStatus) {
        this(result);
        this.statusCode = initialStatus;
    }

    /**
     * @return the messages
     */
    public List<String> getMessages() {
        return messages;
    }
}
