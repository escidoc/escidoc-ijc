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

/**
 * Object reference.
 * 
 * @author SWA
 * 
 */
public abstract class Resource extends XLinkResource implements XLinkAutonomous {

    /**
     * The global unique id of the resource.
     */
    private String objid;

    /**
     * 
     */
    public Resource() {

    }

    /**
     * 
     * @param objid
     *            The objid of the resource.
     */
    public Resource(final String objid) {
        setObjid(objid);
    }

    /**
     * 
     * @param href
     *            The href of the resource (for XML Xlink href attribute)
     * @param title
     *            The title of the resource (for XML Xlink title attribute)
     */
    public Resource(final String href, final String title) {
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
    public Resource(final String objid, final String href, final String title) {
        setXLinkHref(href);
        setXLinkTitle(title);
        setObjid(objid);
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
     * XML. Therefore we need to generate the Href in case we are using the REST
     * protocol.
     * 
     * @param objid
     *            The object Id.
     */
    public void setObjid(final String objid) {
        this.objid = objid;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.resources.XLinkResource#setXLinkHref(java.lang.String)
     */
    @Override
    public void setXLinkHref(final String href) {
        super.setXLinkHref(href);
        if (href != null) {
            setObjid(href.substring(href.lastIndexOf('/') + 1));
        }
    }

    /**
     * Get the type of the resource.
     * 
     * @return Resource type
     */
    public abstract ResourceType getResourceType();

    /**
     * Method used by ResourceRef implementations to ensure a fully valid
     * xLinkHref definition for all sub resources they may own. The validation
     * methods calling this method may be called by JiBX as post-set methods.
     */
    protected void genOwnXLinkHref() {
        if (getXLinkHref() == null && getResourceType() != null
            && getResourceType().isRootResource() && getObjid() != null) {
            setXLinkHref(getResourceType().getPath() + "/" + getObjid());
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (objid == null ? 0 : objid.hashCode());
        return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Resource other = (Resource) obj;
        if (objid == null) {
            if (other.objid != null)
                return false;
        }
        else if (!objid.equals(other.objid))
            return false;
        return true;
    }

    /**
     * Method used by Resource implementations to ensure a fully valid xLinkHref
     * definition for all sub resources they may own. The validation methods
     * calling this method may be called by JiBX as post-set or pre-get methods.
     * 
     * @param resource
     * @param type
     * @param prefix
     * @return the generated HREF
     */
    protected static final String genXLinkHref(
        final Resource resource, final ResourceType type, final String prefix) {

        if (resource != null && resource.getXLinkHref() == null) {

            if (type != null && resource.getObjid() != null) {

                String href = type.getPath() + "/" + resource.getObjid();
                if (prefix != null)
                    href = prefix + href;
                resource.setXLinkHref(href);
                return href;
            }
            else if (prefix != null) {
                resource.setXLinkHref(prefix);
                return prefix;
            }
        }
        return "";
    }

    /**
     * Returns the resource type of the specified xLinkHref. The xLinkHref is
     * not being validated.
     * 
     * @param xLinkHref
     * @return the resource type of the specified xLinkHref or null if and only
     *         if the specified xLinkHref cannot be mapped to a root resource
     *         type.
     */
    public static final ResourceType getRootResourceTypeForHref(
        final String xLinkHref) {
        if (xLinkHref == null)
            return null;

        ResourceType type = ResourceType.getValue(xLinkHref);
        return type.isRootResource() ? type : null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.resources.XLinkAutonomous#genXLink()
     */
    @Override
    public void genXLink() {
        genOwnXLinkHref();
    }
}