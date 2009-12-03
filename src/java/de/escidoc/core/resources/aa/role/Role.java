package de.escidoc.core.resources.aa.role;



import org.w3c.dom.Element;

import de.escidoc.core.resources.om.GenericResource;

public class Role extends GenericResource {

    
    private RoleProperties properties;
    private Scope scope;
    private Element policyOrPolicySet;

   
    public Role() {
    }
    

    public Element getPolicyOrPolicySet() {
        return policyOrPolicySet;
    }


    public void setPolicyOrPolicySet(Element policyOrPolicySet) {
        this.policyOrPolicySet = policyOrPolicySet;
    }


    public Scope getScope() {
        return scope;
    }

    public void setScope(Scope scope) {
        this.scope = scope;
    }

    public RoleProperties getRoleProperties() {
        return properties;
    }

    public void setRoleProperties(RoleProperties properties) {
        this.properties = properties;
    }
    public RoleProperties getProperties() {
        return properties;
    }
    public void setProperties(RoleProperties properties) {
        this.properties = properties;
    }
}
