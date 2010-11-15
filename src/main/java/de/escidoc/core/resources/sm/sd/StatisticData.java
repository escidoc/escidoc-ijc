package de.escidoc.core.resources.sm.sd;

import java.util.Collection;
import java.util.LinkedList;

import de.escidoc.core.annotations.JiBX;
import de.escidoc.core.resources.Resource;
import de.escidoc.core.resources.common.reference.ScopeRef;
import de.escidoc.core.resources.sm.Parameter;

/**
 * @author MRO
 * 
 */
@JiBX
public class StatisticData extends Resource {

    private ScopeRef scope;

    private Collection<Parameter<?>> parameters;

    @JiBX
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
    public Collection<Parameter<?>> getParameters() {
        if (parameters == null) {
            parameters = new LinkedList<Parameter<?>>();
        }
        return parameters;
    }
}