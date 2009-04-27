package de.escidoc.core.resources.aa.useraccount;

import de.escidoc.core.resources.ResourceRef;
import de.escidoc.core.resources.common.properties.Properties;

public class GrantProperties extends Properties {
    
    private String revocationDate;
    private ResourceRef revokedBy;
    private String grantRemark;
    private String revocationRemark;
    private ResourceRef role;
    private ResourceRef assignedOn;
    
    public GrantProperties() {
        
    }
    
    public String getRevocationDate() {
        return revocationDate;
    }
    
    public void setRevocationDate(String revocationDate) {
        this.revocationDate = revocationDate;
    }
    public ResourceRef getRevokedBy() {
        return revokedBy;
    }
    public void setRevokedBy(ResourceRef revokedBy) {
        this.revokedBy = revokedBy;
    }
    public String getGrantRemark() {
        return grantRemark;
    }
    /**
     * The method is optional on create of properties, 
     * but not-allowed on update of grant properties.
     * @param grantRemark
     */
    public void setGrantRemark(String grantRemark) {
        this.grantRemark = grantRemark;
    }
    public String getRevocationRemark() {
        return revocationRemark;
    }
    public void setRevocationRemark(String revocationRemark) {
        this.revocationRemark = revocationRemark;
    }
    
    public ResourceRef getRole() {
        return role;
    }
    /**
     * The method is mandatory on create of properties, 
     * but not-allowed on update of grant properties.
     * @param role
     */
    public void setRole(ResourceRef role) {
        this.role = role;
    }
    public ResourceRef getAssignedOn() {
        return assignedOn;
    }
    /**
     * The method is optional on create of properties, 
     * but not-allowed on update of grant properties.
     * @param assignedOn
     */
    public void setAssignedOn(ResourceRef assignedOn) {
        this.assignedOn = assignedOn;
    }
   
}
