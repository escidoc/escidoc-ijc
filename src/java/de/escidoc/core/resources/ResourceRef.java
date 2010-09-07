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

import java.util.Map;


/**
 * Object reference.
 * 
 * @author SWA
 * 
 */
public class ResourceRef extends XLinkResource {

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
        Grant(true),
        Role(true),
        ContentRelation(true),
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
    
    private String objid;

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

    @Override
    public void setXLinkHref(final String href) {
        super.setXLinkHref(href);
        if(href != null) {
        	this.objid = href.substring(href.lastIndexOf('/')+1);
        }
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
     * Returns the resource type of the specified xLinkHref. The xLinkHref is
     * not being validated.
     * 
     * @param xLinkHref
     * @return the resource type of the specified xLinkHref or null if and only
     * if the specified xLinkHref cannot be mapped to a root resource type.
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
    
    /**
     * Method used by ResourceRef implementations to ensure a fully valid
     * xLinkHref definition for all sub resources they may own. 
     * The validation methods calling this method may be called by JiBX as
     * post-set methods.
     * 
     * @param resource
     * @param type
     * @param prefix
     * @return the generated HREF
     */
    protected static final String genXLinkHref(final ResourceRef resource, 
    		final RESOURCE_TYPE type, final String prefix) {
    	
    	if(resource != null && resource.getXLinkHref() == null) {
    		
    		if(type != null) {
    			resource.setResourceType(type);
    		
	    		String URL = URL_TYPE.get(type);
	    		if(URL!=null) {
	    			String href = URL + "/" + resource.getObjid();
	    			if(prefix != null)
	    				href = prefix + href;
		    		resource.setXLinkHref(href);
		    		return href;
	    		}
    		} else if(prefix != null) {
    			resource.setXLinkHref(prefix);
	    		return prefix;
    		}
    	}
    	return "";
    }
    
    /**
     * Method used by ResourceRef implementations to ensure a fully valid
     * xLinkHref definition for all sub resources they may own. 
     * The validation methods calling this method may be called by JiBX as
     * post-set methods.
     */
    protected void genOwnXLinkHref() {
    	if(getXLinkHref() == null && getResourceType() != null) {
    		setXLinkHref(URL_TYPE.get(getResourceType()) + "/" + getObjid());
    	}
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
