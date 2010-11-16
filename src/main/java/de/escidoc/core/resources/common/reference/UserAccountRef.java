/**
 * 
 */
package de.escidoc.core.resources.common.reference;

import de.escidoc.core.resources.ResourceType;

/**
 * @author MVO
 * 
 */
public class UserAccountRef extends Reference {

    public UserAccountRef() {
        this(null, null);
    }

    public UserAccountRef(String objid) {
        this(objid, null);
    }

    public UserAccountRef(String objid, String xLinkTitle) {
        super(objid, ResourceType.UserAccount, xLinkTitle);
    }
}
