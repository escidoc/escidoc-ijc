/**
 * 
 */
package de.escidoc.core.resources.aa.usergroup;

import java.util.Collection;
import java.util.LinkedList;

/**
 * @author MVO
 * 
 */
public class Selectors extends LinkedList<Selector> {

    /**
     * 
     */
    private static final long serialVersionUID = -6745643988431696869L;

    /**
     * 
     */
    public Selectors() {
    }

    /**
     * @param c
     */
    public Selectors(final Collection<? extends Selector> c) {
        super(c);
    }
}