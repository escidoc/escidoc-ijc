/**
 * 
 */
package de.escidoc.core.resources.common.reference;

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
        super(objid, RESOURCE_TYPE.UserAccount, xLinkTitle);
    }
}
