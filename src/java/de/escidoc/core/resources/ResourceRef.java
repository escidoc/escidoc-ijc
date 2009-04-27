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
 * Copyright 2006-2008 Fachinformationszentrum Karlsruhe Gesellschaft
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
public class ResourceRef {

    /**
     * XLink Types.
     * 
     */
    public static enum TYPE {
        simple, extended, locator, arc, resource, title
    }

    /**
     * Types of Escidoc resources.
     * 
     */
    public static enum RESOURCE_TYPE {
        Context, Item, Container, Component, Toc, OrganizationalUnit
    }

    private String objid;

    private String href;

    private String title;

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
     * 
     * @return
     */
    public TYPE getXlinkType() {
        return TYPE.simple;
    }

    /**
     * 
     * @param type
     */
    public void setXlinkType(final TYPE type) {

    }

    /**
     * Set the type of the resource for the reference.
     * 
     * @param resourceType
     */
    public void setResourceType(final RESOURCE_TYPE resourceType) {

        this.resourceType = resourceType;
    }

    /**
     * Get the type of the resource.
     * 
     * @return
     */
    public RESOURCE_TYPE getResourceType() {

        return this.resourceType;
    }

    /**
     * Test the equality of this XLink with another object.
     * <p>
     * <b>WARNING:</b> This method does not guarantee that subclasses
     * inheriting this method are equal.
     * </p>
     * 
     * @param obj
     *            the object to be tested for equality
     * @return <code>true</code> if and only if the other object is an XLink
     *         of the same class and with the same attribute settings as this
     *         XLink
     */
    @Override
    public boolean equals(Object obj) {
        return false;
    }

    /**
     * Compute a hash for this XLink.
     * 
     * @return a hash for this XLink
     */
    @Override
    public int hashCode() {
        return 0;
    }

}
