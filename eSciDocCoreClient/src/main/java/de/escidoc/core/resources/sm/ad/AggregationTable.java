package de.escidoc.core.resources.sm.ad;

import java.util.Collection;
import java.util.LinkedList;

import de.escidoc.core.annotations.JiBX;

/**
 * @author MRO
 * 
 */
@JiBX
public class AggregationTable {

    private String name;

    private Collection<Field> fields;

    private Collection<Index> indexes;

    @JiBX
    @SuppressWarnings("unused")
    private AggregationTable() {

    }

    /**
     * 
     * @param name
     */
    public AggregationTable(final String name) {
        if (name == null)
            throw new IllegalArgumentException("name must not be null.");

        this.name = name;
    }

    /**
     * 
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * @return the fields
     */
    public Collection<Field> getFields() {
        if (fields == null) {
            fields = new LinkedList<Field>();
        }
        return fields;
    }

    /**
     * @return the indexes
     */
    public Collection<Index> getIndexes() {
        if (indexes == null) {
            indexes = new LinkedList<Index>();
        }
        return indexes;
    }
}
