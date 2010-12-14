package de.escidoc.core.resources.common.reference;

import de.escidoc.core.annotations.JiBX;
import de.escidoc.core.resources.ResourceType;

/**
 * @author MRO
 * 
 */
@JiBX
public class ScopeRef extends Reference {

    @SuppressWarnings("unused")
    @JiBX
    private ScopeRef() {
        this(null);
    }

    public ScopeRef(final String objid) {
        super(objid);
    }

    public ScopeRef(final String xLinkHref, final String xLinkTitle) {
        super(xLinkHref, xLinkTitle);
    }

    @Override
    public ResourceType getResourceType() {
        return ResourceType.Scope;
    }
}