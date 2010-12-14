package de.escidoc.core.resources.common;

import java.util.Collection;
import java.util.LinkedList;

public class Filter {

    public Filter() {
    }

    public static final String DC_BASE =
        "http://escidoc.de/core/01/properties/";

    public static final String PROPERTIES_BASE =
        "http://escidoc.de/core/01/properties/";

    public static final String STRUCTURAL_RELATION_BASE =
        "http://escidoc.de/core/01/structural-relations/";

    private String name = null;

    private String value = null;

    private Collection<String> ids = null;

    public static LinkedList<String> idsFactory() {
        return new LinkedList<String>();
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * @return the value
     */
    public String getValue() {
        return value;
    }

    /**
     * @param value
     *            the value to set
     */
    public void setValue(final String value) {
        this.value = value;
    }

    /**
     * @return the ids
     */
    public Collection<String> getIds() {
        return ids;
    }

    /**
     * @param ids
     *            the ids to set
     */
    public void setIds(final Collection<String> ids) {
        this.ids = ids;
    }

}
