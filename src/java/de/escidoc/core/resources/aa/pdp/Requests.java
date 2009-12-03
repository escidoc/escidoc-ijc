package de.escidoc.core.resources.aa.pdp;

import java.util.Collection;
import java.util.LinkedList;

import org.w3c.dom.Element;

public class Requests {
    private final Collection<Element> requests = new LinkedList<Element>();

    public Requests() {

    }

    public Collection<Element> getRequests() {
        return requests;
    }

    public Collection<Element> addRequest(Element request) {
        this.requests.add(request);
        return this.requests;
    }
}
