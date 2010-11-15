package de.escidoc.core.resources.sm.ad;

import java.util.Collection;
import java.util.LinkedList;

import de.escidoc.core.annotations.JiBX;
import de.escidoc.core.resources.Resource;
import de.escidoc.core.resources.common.reference.AggregationDefinitionRef;
import de.escidoc.core.resources.common.reference.Referenceable;
import de.escidoc.core.resources.common.reference.ScopeRef;

/**
 * @author MRO
 * 
 */
@JiBX
public class AggregationDefinition extends Resource
    implements Referenceable<AggregationDefinitionRef> {

    private String name;

    private ScopeRef scope;

    private Collection<AggregationTable> aggregationTables;

    private StatisticData statisticData;

    @JiBX
    @SuppressWarnings("unused")
    private AggregationDefinition() {

    }

    /**
     * 
     * @param name
     * @param scope
     * @param aggregationTable
     * @param statisticData
     */
    public AggregationDefinition(final String name, final ScopeRef scope,
        final StatisticData statisticData) {

        if (name == null)
            throw new IllegalArgumentException("name must not be null.");
        if (scope == null)
            throw new IllegalArgumentException("scope must not be null.");
        if (statisticData == null)
            throw new IllegalArgumentException(
                "statisticData must not be null.");

        this.name = name;
        this.scope = scope;
        this.statisticData = statisticData;
    }

    /**
     * 
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * @return
     */
    public ScopeRef getScope() {
        return scope;
    }

    /**
     * @return
     */
    public Collection<AggregationTable> getAggregationTables() {
        if (aggregationTables == null) {
            aggregationTables = new LinkedList<AggregationTable>();
        }
        return aggregationTables;
    }

    /**
     * @return
     */
    public StatisticData getStatisticData() {
        return statisticData;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.resources.common.reference.Referenceable#getReference()
     */
    @Override
    public AggregationDefinitionRef getReference() {
        return new AggregationDefinitionRef(this.getObjid());
    }

}
