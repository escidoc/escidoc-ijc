package de.escidoc.core.resources.sm.report;

import static de.escidoc.core.common.Precondition.checkNotNull;

import java.util.LinkedList;
import java.util.List;

import de.escidoc.core.annotations.JiBX;
import de.escidoc.core.resources.GenericResource;
import de.escidoc.core.resources.ResourceType;
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
    implements Referenceable<ReportDefinitionRef> {

    private String name;

    private ScopeRef scope;

    private String sql;

    private List<RoleRef> allowedRoles;

    /**
     * JiBX Constructor
     */
    @JiBX
    protected ReportDefinition() {
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
        this.name = checkNotNull(name);
        this.scope = checkNotNull(scope);
        this.sql = checkNotNull(sql);
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
    public List<RoleRef> getAllowedRoles() {
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
        return ResourceType.REPORT_DEFINITION;
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