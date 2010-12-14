/**
 * 
 */
package de.escidoc.core.resources.common.structmap;

import de.escidoc.core.resources.common.reference.Reference;

/**
 * @author SWA, MVO
 * 
 */
public abstract class MemberRef extends Reference {

    public MemberRef(final String objid) {
        super(objid);
    }

    public MemberRef(final String xLinkHref, final String xLinkTitle) {
        super(xLinkHref, xLinkTitle);
    }
}