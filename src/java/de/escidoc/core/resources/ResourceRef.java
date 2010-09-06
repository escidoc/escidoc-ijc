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
    public static enum TYPE {
        simple, extended, locator, arc, resource, title
    }

    /**
     * Types of eSciDoc resources.
     * 
     */
    public static enum RESOURCE_TYPE {
        Context, Item, Container, Component, Toc, OrganizationalUnit, 
        UserAccount, UserAccountAttribute, ContentModel
    }
    
    public static final Map<RESOURCE_TYPE, String> URL_TYPE = new HashMap<RESOURCE_TYPE, String>();
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

    private String href;

    private String title;
    
    private TYPE xLinkType;

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

        this.objid = objid;
    }
    
    /**
     * 
     * @param objid
     * @param type
     */
    public ResourceRef(final String objid, final RESOURCE_TYPE type) {
    	this.setHref(URL_TYPE.get(type) + "/" + objid);
    	this.setTitle(type+"Title");
    	this.resourceType = type;
    }
    
    /**
     * 
     * @param objid
     * @param type
     * @param title
     */
    public ResourceRef(final String objid, final RESOURCE_TYPE type, String title) {
    	this.setHref(URL_TYPE.get(type) + "/" + objid);
    	this.title = title;
    	this.resourceType = type;
    }

    /**
     * 
     * @param href
     *            The href of the resource (for XML Xlink href attribute)
     * @param title
     *            The title of the resource (for XML Xlink title attribute)
     */
    public ResourceRef(final String href, final String title) {

        this.href = href;
        this.title = title;
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
        this.href = href;
        this.title = title;
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
        this.href = href;
        this.title = title;
        this.resourceType = resourceType;
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
    public String getHref() {
        return this.href;
    }

    /**
     * Set the href of the resource. (May depend on transport protocol.)
     * 
     * @param href
     *            The href of the resource
     */
    public void setHref(final String href) {
        this.href = href;
        if(href != null && href.length()>0) {
        	this.objid = href.substring(href.lastIndexOf('/')+1);
        }
    }
    
    /**
     * Get the title of the resource. (May depend on transport protocol.)
     * 
     * @return Resource title.
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * Set the resource title. (May depend on transport protocol or is
     * discarded.)
     * 
     * @param title
     *            The title of the resource.
     */
    public void setTitle(final String title) {
        this.title = title;
    }

    /**
     * Get Xlink type. (Default TYPE.simple)
     * 
     * @return xlink:type if and only if this.href has been set. 
     * (REST protocol uses xlink:href. SOAP needs this.objid only.)
     */
    public TYPE getXlinkType() {
    	if(this.xLinkType == null) {
    		return (this.href != null) ? TYPE.simple : null;
    	}
        return this.xLinkType;
    }

    /**
     * Set Xlink:type.
     * 
     * @param type
     *            Type of xlink.
     */
    public void setXlinkType(final TYPE type) {
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
