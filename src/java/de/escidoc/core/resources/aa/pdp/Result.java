package de.escidoc.core.resources.aa.pdp;

import java.util.Collection;
import java.util.LinkedList;

import org.w3c.dom.Element;

public class Result {
    private final Collection<Element> responses = new LinkedList<Element>();

    private Decision decision;

    public Decision getDecision() {
        return decision;
    }

    public void setDecision(Decision decision) {
        this.decision = decision;
    }

    public Collection<Element> getResponses() {
        return responses;
    }
}
