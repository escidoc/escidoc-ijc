/**
 * 
 */
package de.escidoc.core.resources;

import static de.escidoc.core.common.Precondition.checkNotNull;

/**
 * @author MVO
 * 
 */
public abstract class ResourceX extends XLinkResource {

    private final Identifier identifier;

    /**
     * 
     * @param objid
     *            The objid of the resource.
     */
    public ResourceX(final String objid) {
        this.identifier = new Identifier(objid);
    }

    /**
     * 
     * @param href
     *            The href of the resource (for XML Xlink href attribute)
     * @param title
     *            The title of the resource (for XML Xlink title attribute)
     */
    public ResourceX(final String href, final String title) {
        this.identifier = new Identifier(href, title);
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
    public ResourceX(final String objid, final String href, final String title) {
        this.identifier = new Identifier(objid, href, title);
    }

    /**
     * @param objid
     */
    public void setObjid(final String objid) {
        identifier.setObjid(objid);
    }

    /**
     * @return the objid
     */
    public String getObjid() {
        return identifier.getObjid();
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.resources.XLinkResource#getXLinkHref()
     */
    @Override
    public String getXLinkHref() {
        return identifier.getHref();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.resources.XLinkResource#setXLinkHref(java.lang.String)
     */
    @Override
    public void setXLinkHref(final String href) {
        identifier.setHref(href);
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.resources.XLinkResource#getXLinkTitle()
     */
    @Override
    public String getXLinkTitle() {
        return identifier.getTitle();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.resources.XLinkResource#setXLinkTitle(java.lang.String)
     */
    @Override
    public void setXLinkTitle(final String title) {
        identifier.setTitle(title);
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.resources.XLinkResource#getXLinkType()
     */
    @Override
    public XLinkType getXLinkType() {
        return identifier.getType();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.resources.XLinkResource#setXLinkType(de.escidoc.core.
     * resources.XLinkResource.XLINK_TYPE)
     */
    @Override
    public void setXLinkType(final XLinkType type) {
        identifier.setType(type);
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
        result = prime * result + identifier.hashCode();
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
        ResourceX other = (ResourceX) obj;
        if (!identifier.equals(other.identifier))
            return false;
        return true;
    }

    /**
     * @author MVO
     * 
     */
    public final class Identifier {

        private String objid;

        private String href;

        private XLinkType type = XLinkType.simple;

        private String title;

        /**
         * @param id
         */
        Identifier(final String id) {
            setObjid(id);
        }

        /**
         * @param href
         * @param title
         */
        Identifier(final String href, final String title) {
            setHref(href);
            setTitle(title);
        }

        /**
         * @param objid
         * @param href
         * @param title
         */
        Identifier(final String objid, final String href, final String title) {
            setObjid(objid);
            setHref(href);
            setTitle(title);
        }

        /**
         * @param objid
         *            the objid to set
         */
        void setObjid(final String objid) {
            checkNotNull(objid);
            this.objid = objid;
        }

        /**
         * @param href
         *            the href to set
         */
        void setHref(final String href) {
            checkNotNull(href);
            this.href = href;
        }

        /**
         * @param type
         *            the type to set
         */
        void setType(final XLinkType type) {
            if (type != null)
                this.type = type;
        }

        /**
         * @param title
         *            the title to set
         */
        void setTitle(final String title) {
            this.title = title;
        }

        /**
         * @return the objid
         */
        String getObjid() {
            if (objid != null)
                return objid;
            if (href != null)
                objid = genObjid();
            return objid;
        }

        /**
         * @return the href
         */
        String getHref() {
            if (href != null)
                return href;
            if (objid != null)
                href = genXLink();
            return href;
        }

        /**
         * @return the type
         */
        XLinkType getType() {
            return type;
        }

        /**
         * @return the title
         */
        String getTitle() {
            return title;
        }

        /**
         * @return
         */
        private final String genXLink() {
            if (href == null && getResourceType() != null)
                return new StringBuilder(getResourceType().getPath())
                    .append('/').append(objid).toString();
            return null;
        }

        /**
         * @return
         */
        private final String genObjid() {
            return href.substring(href.lastIndexOf('/') + 1);
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
            result =
                prime
                    * result
                    + (this.getObjid() == null ? 0 : this.getObjid().hashCode());
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
            Identifier other = (Identifier) obj;
            if (this.getObjid() == null) {
                if (other.getObjid() != null)
                    return false;
            }
            else if (!this.getObjid().equals(other.getObjid()))
                return false;
            return true;
        }
    }

    public abstract ResourceType getResourceType();
}