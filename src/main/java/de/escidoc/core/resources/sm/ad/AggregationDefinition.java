package de.escidoc.core.resources.sm.ad;

import static de.escidoc.core.common.Precondition.checkNotNull;

import java.util.LinkedList;
import java.util.List;

import de.escidoc.core.annotations.JiBX;
import de.escidoc.core.resources.Resource;
import de.escidoc.core.resources.ResourceType;
import de.escidoc.core.resources.common.reference.AggregationDefinitionRef;
import de.escidoc.core.resources.common.reference.Referenceable;
import de.escidoc.core.resources.common.reference.ScopeRef;

/**
 * @author MRO
 * 
 */
@JiBX
public class AggregationDefinition extends Resource implements Referenceable<AggregationDefinitionRef> {

    private String name;

    private ScopeRef scope;

    private List<AggregationTable> aggregationTables;

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
    public AggregationDefinition(final String name, final ScopeRef scope, final StatisticData statisticData) {

        checkNotNull(name);
        checkNotNull(scope);
        checkNotNull(statisticData);

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
    public List<AggregationTable> getAggregationTables() {
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

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.resources.Resource#getResourceType()
     */
    @Override
    public ResourceType getResourceType() {
        return ResourceType.AGGREGATION_DEFINITION;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.resources.XLinkAutonomousX#generateXLinkHref(java.lang
     * .String)
     */
    @Override
    public void generateXLinkHref(final String parentPath) {
        // nothing to do
    }
}