package de.escidoc.core.resources.common.reference;

import de.escidoc.core.resources.ResourceType;

/**
 * @author MRO
 * 
 */
public class ScopeRef extends Reference {

	public ScopeRef() {
		this(null, null);
	}

	public ScopeRef(String objid) {
		this(objid, null);
	}

	public ScopeRef(String objid, String xLinkTitle) {
		super(objid, ResourceType.Scope, xLinkTitle);
	}
}
