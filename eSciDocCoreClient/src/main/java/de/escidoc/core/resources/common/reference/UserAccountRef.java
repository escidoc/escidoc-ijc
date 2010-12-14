/**
 * 
 */
package de.escidoc.core.resources.common.reference;

import de.escidoc.core.annotations.JiBX;
import de.escidoc.core.resources.ResourceType;

/**
 * @author MVO
 * 
 */
@JiBX
public class UserAccountRef extends Reference {

    @SuppressWarnings("unused")
    @JiBX
    private UserAccountRef() {
        this(null);
    }

    public UserAccountRef(final String objid) {
        super(objid);
    }

    public UserAccountRef(final String xLinkHref, final String xLinkTitle) {
        super(xLinkHref, xLinkTitle);
    }

    @Override
    public ResourceType getResourceType() {
        return ResourceType.UserAccount;
    }
}