package de.escidoc.core.resources.sm.report;

import java.util.Collection;
import java.util.LinkedList;

import de.escidoc.core.annotations.JiBX;
import de.escidoc.core.resources.GenericResource;
import de.escidoc.core.resources.ResourceType;
import de.escidoc.core.resources.XLinkAutonomous;
import de.escidoc.core.resources.common.reference.Referenceable;
import de.escidoc.core.resources.common.reference.ReportDefinitionRef;
import de.escidoc.core.resources.common.reference.RoleRef;
import de.escidoc.core.resources.common.reference.ScopeRef;

/**
 * @author MRO
 * 
 */
@JiBX
public class ReportDefinition extends GenericResource
    implements Referenceable<ReportDefinitionRef>, XLinkAutonomous {

    private String name;

    private ScopeRef scope;

    private String sql;

    private Collection<RoleRef> allowedRoles;

    @JiBX
    @SuppressWarnings("unused")
    private ReportDefinition() {
    }

    /**
     * 
     * @param name
     * @param scope
     * @param sql
     * @param allowedRoles
     */
    public ReportDefinition(final String name, final ScopeRef scope,
        final String sql) {
        if (name == null)
            throw new IllegalArgumentException("name must not be null.");
        if (scope == null)
            throw new IllegalArgumentException("scope must not be null.");
        if (sql == null)
            throw new IllegalArgumentException("sql must not be null.");

        this.name = name;
        this.scope = scope;
        this.sql = sql;
    }

    /**
     * 
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * 
     * @return
     */
    public ScopeRef getScope() {
        return scope;
    }

    /**
     * 
     * @return
     */
    public String getSql() {
        return sql;
    }

    /**
     * 
     * @return
     */
    public Collection<RoleRef> getAllowedRoles() {
        if (allowedRoles == null) {
            allowedRoles = new LinkedList<RoleRef>();
        }
        return allowedRoles;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.resources.common.reference.Referenceable#getReference()
     */
    @Override
    public ReportDefinitionRef getReference() {
        return new ReportDefinitionRef(this.getObjid());
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.resources.Resource#getResourceType()
     */
    @Override
    public ResourceType getResourceType() {
        return ResourceType.ReportDefinition;
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.resources.Resource#genXLink()
     */
    @Override
    public void genXLink() {
        super.genXLink();
        if (scope != null)
            scope.genXLink();
    }
}