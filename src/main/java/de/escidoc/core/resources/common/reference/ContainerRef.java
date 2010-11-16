/**
 * 
 */
package de.escidoc.core.resources.common.reference;

import de.escidoc.core.resources.ResourceType;

/**
 * @author MVO
 * 
 */
public class ContainerRef extends Reference {

    public ContainerRef() {
        this(null, null);
    }

    public ContainerRef(String objid) {
        this(objid, null);
    }

    public ContainerRef(String objid, String xLinkTitle) {
        super(objid, ResourceType.Container, xLinkTitle);
    }
}
