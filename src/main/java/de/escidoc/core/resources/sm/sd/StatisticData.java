package de.escidoc.core.resources.sm.sd;

import java.util.Collection;
import java.util.LinkedList;

import de.escidoc.core.resources.Resource;
import de.escidoc.core.resources.common.reference.ScopeRef;
import de.escidoc.core.resources.sm.Parameter;

/**
 * @author MRO
 * 
 */
public class StatisticData extends Resource {

    private ScopeRef scope;

    private Collection<Parameter<?>> parameters =
        new LinkedList<Parameter<?>>();

    /**
     * Constructor for JiBX only.
     */
    @SuppressWarnings("unused")
    private StatisticData() {

    }

    /**
     * 
     * @param scope
     */
    public StatisticData(final ScopeRef scope) {
        if (scope == null)
            throw new IllegalArgumentException("scope must not be null.");

        this.scope = scope;
    }

    /**
     * 
     * @return
     */
    public ScopeRef getScope() {
        return scope;
    }

    /**
     * @return the parameters
     */
    public final Collection<Parameter<?>> getParameters() {
        return parameters;
    }
}