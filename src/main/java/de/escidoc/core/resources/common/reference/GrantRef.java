/**
 * 
 */
package de.escidoc.core.resources.common.reference;

import de.escidoc.core.resources.ResourceType;

/**
 * @author MVO
 * 
 */
public class GrantRef extends Reference {

    public GrantRef() {
        this(null);
    }

    public GrantRef(String objid) {
        super(objid, ResourceType.Grant);
    }

    public GrantRef(String objid, String xLinkTitle) {
        super(objid, ResourceType.Grant);
    }
}