/**
 * 
 */
package de.escidoc.core.resources.adm;

import java.util.LinkedList;

import de.escidoc.core.resources.common.reference.Reference;

/**
 * @author MVO
 * 
 */
public class LoadExamplesResult extends LinkedList<LoadExamplesResult.Entry> {

    private static final long serialVersionUID = 556659402548830140L;

    /**
     * 
     * @author MVO
     * 
     */
    public class Entry extends Reference {

        private final String message;

        /**
         * 
         * @param objid
         * @param type
         * @param message
         */
        public Entry(String objid, RESOURCE_TYPE type, String message) {
            super(objid, type);
            this.message = message;
        }

        /**
         * 
         * @return
         */
        public String getMessage() {
            return message;
        }
    }
}
