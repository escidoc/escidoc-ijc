package de.escidoc.core.resources.sm.sd;

import static de.escidoc.core.common.Precondition.checkNotNull;

import java.util.LinkedList;
import java.util.List;

import de.escidoc.core.annotations.JiBX;
import de.escidoc.core.resources.common.reference.ScopeRef;
import de.escidoc.core.resources.sm.Parameter;

/**
 * @author MRO
 * 
 */
@JiBX
public class StatisticData {

    private ScopeRef scope;

    private List<Parameter<?>> parameters;

    /**
     * JiBX Constructor
     */
    @JiBX
    protected StatisticData() {
    }

    /**
     * 
     * @param scope
     */
    public StatisticData(final ScopeRef scope) {
        checkNotNull(scope);

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
    public List<Parameter<?>> getParameters() {
        if (parameters == null) {
            parameters = new LinkedList<Parameter<?>>();
        }
        return parameters;
    }
}