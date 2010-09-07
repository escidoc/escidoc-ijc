/*
 * CDDL HEADER START
 *
 * The contents of this file are subject to the terms of the
 * Common Development and Distribution License, Version 1.0 only
 * (the "License").  You may not use this file except in compliance
 * with the License.
 *
 * You can obtain a copy of the license at license/ESCIDOC.LICENSE
 * or http://www.escidoc.de/license.
 * See the License for the specific language governing permissions
 * and limitations under the License.
 *
 * When distributing Covered Code, include this CDDL HEADER in each
 * file and include the License file at license/ESCIDOC.LICENSE.
 * If applicable, add the following below this CDDL HEADER, with the
 * fields enclosed by brackets "[]" replaced with your own identifying
 * information: Portions Copyright [yyyy] [name of copyright owner]
 *
 * CDDL HEADER END
 */

/*
 * Copyright 2006-2010 Fachinformationszentrum Karlsruhe Gesellschaft
 * fuer wissenschaftlich-technische Information mbH and Max-Planck-
 * Gesellschaft zur Foerderung der Wissenschaft e.V.  
 * All rights reserved.  Use is subject to license terms.
 */
package de.escidoc.core.resources;

import java.util.HashMap;
import java.util.Map;


/**
 * Object reference.
 * 
 * @author SWA
 * 
 */
public class ResourceRef {

    /**
     * XLink Types.
     * 
     */
    public static enum XLINK_TYPE {
        simple, extended, locator, arc, resource, title
    }

    /**
     * Types of eSciDoc resources.
     * 
     */
    public static enum RESOURCE_TYPE {
    	// root resources
        Context(true), 
        Item(true), 
        Container(true), 
        OrganizationalUnit(true),
        UserAccount(true), 
        ContentModel(true),
        // sub resources
        Component(false), 
        Toc(false), // ???
        UserAccountAttribute(false);
        
        private final boolean isRootResource;
        
        RESOURCE_TYPE(boolean isRootResource) {
        	this.isRootResource = isRootResource;
        }
        
        public boolean isRootResource() {
        	return isRootResource;
        }
    }
    
    /**
     * Types of eSciDoc stand-alone resources.
     * 
     */
    public static enum STANDALONE_RESOURCE_TYPE {
    	Context, Item, Container, OrganizationalUnit, 
        UserAccount, ContentModel
    }
    
    public static final Map<RESOURCE_TYPE, String> URL_TYPE = 
    	new HashMap<RESOURCE_TYPE, String>();
    {
    	URL_TYPE.put(RESOURCE_TYPE.Context, "/ir/context");
    	URL_TYPE.put(RESOURCE_TYPE.Item, "/ir/item");
    	URL_TYPE.put(RESOURCE_TYPE.Container, "/ir/container");
    	URL_TYPE.put(RESOURCE_TYPE.Component, "/components/component"); /* /ir/item/<iID>/components/component/<cID>/ */
    	URL_TYPE.put(RESOURCE_TYPE.Toc, "/tocs");
    	URL_TYPE.put(RESOURCE_TYPE.OrganizationalUnit, "/oum/organizational-unit");
    	URL_TYPE.put(RESOURCE_TYPE.UserAccount, "/aa/user-account");
    	URL_TYPE.put(RESOURCE_TYPE.UserAccountAttribute, "/resources/attributes/attribute"); /* /aa/user-account/<uID>/resources/attributes/attribute/<aID>/ */
    	URL_TYPE.put(RESOURCE_TYPE.ContentModel, "/cmm/content-model");
    }

    private String objid;

    private String xLinkHref;

    private String xLinkTitle;
    
    private XLINK_TYPE xLinkType;

    private RESOURCE_TYPE resourceType = null;

    /**
     * 
     */
    public ResourceRef() {

    }

    /**
     * 
     * @param objid
     *            The objid of the resource.
     */
    public ResourceRef(final String objid) {
    	setObjid(objid);
    }
    
    /**
     * 
     * @param objid
     * @param type
     */
    public ResourceRef(final String objid, final RESOURCE_TYPE type) {
    	setResourceType(type);
    	setObjid(objid);
    	setXLinkTitle(type+"Title");
    }
    
    /**
     * 
     * @param objid
     * @param type
     * @param title
     */
    public ResourceRef(final String objid, final RESOURCE_TYPE type, 
    		String title) {
    	setResourceType(type);
    	setObjid(objid);
    	setXLinkTitle(title);
    }

    /**
     * 
     * @param href
     *            The href of the resource (for XML Xlink href attribute)
     * @param title
     *            The title of the resource (for XML Xlink title attribute)
     */
    public ResourceRef(final String href, final String title) {
    	setXLinkHref(href);
    	setXLinkTitle(title);
    }

    /**
     * 
     * @param objid
     *            The objid of the resource.
     * @param href
     *            The href of the resource (for XML Xlink href attribute)
     * @param title
     *            The title of the resource (for XML Xlink title attribute)
     */
    public ResourceRef(final String objid, final String href, final String title) {
    	this.objid = objid;
        setXLinkHref(href);
        setXLinkTitle(title);
    }

    /**
     * 
     * @param objid
     *            The objid of the resource.
     * @param href
     *            The href of the resource (for XML Xlink href attribute)
     * @param title
     *            The title of the resource (for XML Xlink title attribute)
     * @param resourceType
     *            The type of the resource.
     */
    public ResourceRef(final String objid, final String href,
        final String title, final RESOURCE_TYPE resourceType) {
        this.objid = objid;
        setXLinkHref(href);
        setXLinkTitle(title);
        setResourceType(resourceType);
    }

    /**
     * Get the object id of the resource.
     * 
     * @return object id
     */
    public String getObjid() {
        return this.objid;
    }

    /**
     * Set object id.
     * 
     * Workaround needed because SRW returns search results as SOAP formatted
     * XML. Therefore we need to generate the Href in case we are using the 
     * REST protocol.
     * 
     * @param objid
     *            The object Id.
     */
    public void setObjid(final String objid) {
    	this.objid = objid;
    }

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
        	this.objid = href.substring(href.lastIndexOf('/')+1);
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

    /**
     * Set the type of the resource for the reference.
     * 
     * @param resourceType
     *            type of resource
     */
    public void setResourceType(final RESOURCE_TYPE resourceType) {

        this.resourceType = resourceType;
    }

    /**
     * Get the type of the resource.
     * 
     * @return Resource type
     */
    public RESOURCE_TYPE getResourceType() {

        return this.resourceType;
    }

    /**
     * 
     * @param xLinkHref
     * @return
     */
    public static final RESOURCE_TYPE getRootResourceTypeForHref(String xLinkHref) {
    	if(xLinkHref == null) return null;
    	
    	for (Map.Entry<RESOURCE_TYPE, String> entry : URL_TYPE.entrySet()) {
			if(entry.getKey().isRootResource() && 
					xLinkHref.startsWith(entry.getValue())) {
				return entry.getKey(); 
			}
		}
    	return null;
    }
    
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((objid == null) ? 0 : objid.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ResourceRef other = (ResourceRef) obj;
		if (objid == null) {
			if (other.objid != null)
				return false;
		} else if (!objid.equals(other.objid))
			return false;
		return true;
	}
}
