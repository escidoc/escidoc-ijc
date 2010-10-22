/**
 * 
 */
package de.escidoc.core.resources.adm;

import org.w3c.dom.Element;

import de.escidoc.core.resources.Resource.RESOURCE_TYPE;
import de.escidoc.core.resources.common.MessagesResult;
import de.escidoc.core.resources.common.Result;

/**
 * @author MVO
 * 
 */
public class LoadExamplesResult
    extends MessagesResult<LoadExamplesResult.Entry> {

    private static final long serialVersionUID = 556659402548830140L;

    /**
     * 
     * @param result
     */
    public LoadExamplesResult(final Result result) {
        super(result);
    }

    /**
     * 
     */
    @Override
    protected Entry extractMessage(
        final Element messageNode, final int messageCount) {
        final String message = messageNode.getTextContent();
        final String type = message.substring(8, message.indexOf(':'));
        final String objid = message.substring(message.indexOf(':') + 2);
        return new Entry(objid, RESOURCE_TYPE.valueByTagName(type), message);
    }

    /**
     * 
     * @author MVO
     * 
     */
    public class Entry {

        private final String message;

        private final String objid;

        private final RESOURCE_TYPE type;

        /**
         * 
         * @param objid
         * @param type
         * @param message
         */
        public Entry(final String objid, final RESOURCE_TYPE type,
            final String message) {
            this.objid = objid;
            this.type = type;
            this.message = message;
        }

        /**
         * 
         * @return
         */
        public String getMessage() {
            return message;
        }

        /**
         * 
         * @return
         */
        public String getObjid() {
            return objid;
        }

        /**
         * 
         * @return
         */
        public RESOURCE_TYPE getResourceType() {
            return type;
        }

        @Override
        public String toString() {
            return "Entry [message=" + message + ", objid=" + objid + ", type="
                + type + "]";
        }

    }
}
