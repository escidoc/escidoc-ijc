/**
 * 
 */
package de.escidoc.core.resources.aa.useraccount;

import de.escidoc.core.resources.ResourceType;
import de.escidoc.core.resources.XLinkList;

/**
 * @author Marko Voß
 * 
 */
public abstract class UserAccountElements<E> extends XLinkList<E> {

    /**
     * 
     */
    private static final long serialVersionUID = 7980374508642036272L;

    private String userObjid;

    /**
     * @param userObjid
     *            the userObjid to set
     */
    public void setUserObjid(final String userObjid) {
        this.userObjid = userObjid;
    }

    /**
     * @return the userObjid
     */
    public String getUserObjid() {
        return userObjid;
    }

    /**
     * Attributes are able to generate their own xlink:href depending on the
     * specified {@link Attributes#userObjid}.
     */
    public void generateXLinkHref() {
        if (userObjid != null && getXLinkHref() == null && getListXLinkPath() != null) {
            setXLinkHref(ResourceType.USERACCOUNT.getPath(userObjid) + getListXLinkPath());
        }
    }
}