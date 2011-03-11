/**
 * 
 */
package de.escidoc.core.resources.sm.scope;

import static de.escidoc.core.common.Precondition.checkNotNull;
import de.escidoc.core.annotations.JiBX;
import de.escidoc.core.resources.GenericResource;
import de.escidoc.core.resources.ResourceType;
import de.escidoc.core.resources.common.reference.Referenceable;
import de.escidoc.core.resources.common.reference.ScopeRef;

/**
 * @author MVO
 * 
 */
@JiBX
public class Scope extends GenericResource implements Referenceable<ScopeRef> {

    private String name;

    private ScopeType scopeType;

    @JiBX
    @SuppressWarnings("unused")
    private Scope() {
    }

    /**
     * 
     * @param name
     * @param scopeType
     */
    public Scope(final String name, final ScopeType scopeType) {
        checkNotNull(name);
        checkNotNull(scopeType);

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

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.resources.Resource#getResourceType()
     */
    @Override
    public ResourceType getResourceType() {
        return ResourceType.Scope;
    }
}