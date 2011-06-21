package de.escidoc.core.resources.sm.scope;

import de.escidoc.core.resources.ResourceList;

/**
 * @author MRO
 * 
 */
public class ScopeList extends ResourceList<Scope> {

    /**
     * 
     */
    private static final long serialVersionUID = -5681029846409773905L;

    private static final String SCOPES_PATH = "/scopes";

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.resources.XLinkResourceList#getListXLinkPath()
     */
    @Override
    protected String getListXLinkPath() {
        return SCOPES_PATH;
    }
}