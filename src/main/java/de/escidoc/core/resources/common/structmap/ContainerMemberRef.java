/**
 * 
 */
package de.escidoc.core.resources.common.structmap;

import de.escidoc.core.resources.ResourceType;

/**
 * @author SWA, MVO
 * 
 */
public class ContainerMemberRef extends MemberRef {

    public ContainerMemberRef() {
        this(null, null);
    }

    public ContainerMemberRef(String objid) {
        this(objid, null);
    }

    public ContainerMemberRef(String objid, String xLinkTitle) {
        super(objid, ResourceType.Container, xLinkTitle);
    }
}
