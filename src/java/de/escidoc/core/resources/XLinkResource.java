/**
 * 
 */
package de.escidoc.core.resources;

import java.util.HashMap;
import java.util.Map;

import de.escidoc.core.resources.ResourceRef.RESOURCE_TYPE;

/**
 * @author MVO
 *
 */
public abstract class XLinkResource {

	/**
     * XLink Types.
     * 
     */
    public static enum XLINK_TYPE {
        simple, extended, locator, arc, resource, title
    }
    
    public static final Map<RESOURCE_TYPE, String> URL_TYPE = 
    	new HashMap<RESOURCE_TYPE, String>();
    {
    	URL_TYPE.put(RESOURCE_TYPE.Context, "/ir/context");
    	URL_TYPE.put(RESOURCE_TYPE.Item, "/ir/item");
    	URL_TYPE.put(RESOURCE_TYPE.Container, "/ir/container");
    	/* /ir/item/<iID>/components/component/<cID>/ */
    	URL_TYPE.put(RESOURCE_TYPE.Component, "/components/component");
    	// TODO: ???
    	URL_TYPE.put(RESOURCE_TYPE.Toc, "/tocs");
    	URL_TYPE.put(RESOURCE_TYPE.OrganizationalUnit, 
    			"/oum/organizational-unit");
    	URL_TYPE.put(RESOURCE_TYPE.UserAccount, "/aa/user-account");
    	/* /aa/user-account/<uID>/resources/attributes/attribute/<aID>/ */
    	URL_TYPE.put(RESOURCE_TYPE.UserAccountAttribute, 
    			"/resources/attributes/attribute");
    	URL_TYPE.put(RESOURCE_TYPE.ContentModel, "/cmm/content-model");
    	URL_TYPE.put(RESOURCE_TYPE.Grant, "/aa/grant");
    	URL_TYPE.put(RESOURCE_TYPE.Role, "/aa/role");
    	URL_TYPE.put(RESOURCE_TYPE.ContentRelation, "/ir/content-relation");
    }
    
    private String xLinkHref;

    private String xLinkTitle;
    
    private XLINK_TYPE xLinkType;
    
    /**
     * Get the href of the resource. (May depend on transport protocol.)
     * 
     * @return href of resource.
     */
    public String getXLinkHref() {
        return this.xLinkHref;
    }

    /**
     * Set the href of the resource. (May depend on transport protocol.)
     * 
     * @param href
     *            The href of the resource
     */
    public void setXLinkHref(final String href) {
        if(href != null && href.length()>0) {
        	this.xLinkHref = href;
        	this.xLinkType = XLINK_TYPE.simple;
        }
    }
    
    /**
     * Get the title of the resource. (May depend on transport protocol.)
     * 
     * @return Resource title.
     */
    public String getXLinkTitle() {
        return this.xLinkTitle;
    }

    /**
     * Set the resource title. (May depend on transport protocol or is
     * discarded.)
     * 
     * @param title
     *            The title of the resource.
     */
    public void setXLinkTitle(final String title) {
        this.xLinkTitle = title;
    }

    /**
     * Get XLink type. (Default XLINK_TYPE.simple)
     * 
     * @return xlink:type if and only if this.href has been set. 
     * (REST protocol uses xlink:href. SOAP needs this.objid only.)
     */
    public XLINK_TYPE getXLinkType() {
//    	if(this.xLinkType == null) {
//    		return (this.xLinkHref != null) ? XLINK_TYPE.simple : null;
//    	}
        return this.xLinkType;
    }

    /**
     * Set Xlink:type.
     * 
     * @param type
     *            Type of xlink.
     */
    public void setXLinkType(final XLINK_TYPE type) {
    	this.xLinkType = type;
    }
}
