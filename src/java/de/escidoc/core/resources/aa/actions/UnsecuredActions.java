package de.escidoc.core.resources.aa.actions;

import java.util.Collection;
import java.util.LinkedList;

public class UnsecuredActions {
    private final Collection<String> actions = new LinkedList<String>();

    public Collection<String> getActions() {
        return actions;
    }

    public Collection<String> addAction(String action) {
        this.actions.add(action);
        return this.actions;
    }
}
