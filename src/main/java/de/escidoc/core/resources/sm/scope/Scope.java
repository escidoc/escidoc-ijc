/**
 * 
 */
package de.escidoc.core.resources.sm.scope;

import de.escidoc.core.resources.Resource;
import de.escidoc.core.resources.common.reference.Referenceable;
import de.escidoc.core.resources.common.reference.ScopeRef;

/**
 * @author MVO
 * 
 */
public class Scope extends Resource implements Referenceable<ScopeRef> {

    private String name;

    private ScopeType scopeType;

    /**
     * Constructor for JiBX only.
     */
    @SuppressWarnings("unused")
    private Scope() {
    }

    /**
     * 
     * @param name
     * @param scopeType
     */
    public Scope(final String name, final ScopeType scopeType) {
        if (name == null)
            throw new IllegalArgumentException("name must not be null.");
        if (scopeType == null)
            throw new IllegalArgumentException("scopeType must not be null.");

        this.name = name;
        this.scopeType = scopeType;
    }

    /**
     * @return the name
     */
    public final String getName() {
        return name;
    }

    /**
     * @return the scopeType
     */
    public final ScopeType getScopeType() {
        return scopeType;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.resources.common.reference.Referenceable#getReference()
     */
    @Override
    public ScopeRef getReference() {
        return new ScopeRef(this.getObjid());
    }
}