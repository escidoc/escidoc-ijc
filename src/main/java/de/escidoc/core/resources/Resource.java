/**
 * 
 */
package de.escidoc.core.resources;

import static de.escidoc.core.common.Precondition.checkNotNull;
import de.escidoc.core.annotations.JiBX;

/**
 * @author MVO
 * 
 */
@JiBX
public abstract class Resource extends XLinkResource {

    /**
     * Only JiBX may initialize this object, because of the identifier will be
     * set by the eSciDoc Infrastructure.
     */
    protected Identifier identifier;

    /**
     * 
     */
    public Resource() {
    }

    /**
     * Protected constructor for objects, which require an objid for
     * initialization. Such objects are references to resources.
     * 
     * 
     * @param objid
     */
    protected Resource(final String objid) {
        identifier = new Identifier(objid);
    }

    /**
     * Protected constructor for objects, which require an objid for
     * initialization. Such objects are references to resources.
     * 
     * @param xLinkHref
     * @param xLinkTitle
     * @param xLinkType
     */
    protected Resource(final String xLinkHref, final String xLinkTitle, final XLinkType xLinkType) {
        identifier = new Identifier(xLinkHref, xLinkTitle, xLinkType);
    }

    /**
     * @return the objid
     */
    public String getObjid() {
        return identifier == null ? null : identifier.getObjid();
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.resources.XLinkResource#getXLinkHref()
     */
    @Override
    public String getXLinkHref() {
        return identifier == null ? null : identifier.getHref();
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.resources.XLinkResource#getXLinkTitle()
     */
    @Override
    public String getXLinkTitle() {
        return identifier == null ? null : identifier.getTitle();
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.escidoc.core.resources.XLinkResource#getXLinkType()
     */
    @Override
    public XLinkType getXLinkType() {
        return identifier == null ? null : identifier.getType();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.resources.XLinkResource#setXLinkHref(java.lang.String)
     */
    @Override
    protected void setXLinkHref(final String xLinkHref) {
        if (identifier == null)
            identifier = new Identifier(xLinkHref, null, null);
        else
            identifier.setHref(xLinkHref);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.resources.XLinkResource#setXLinkType(de.escidoc.core.
     * resources.XLinkType)
     */
    @Override
    protected void setXLinkType(final XLinkType type) {
        if (identifier != null)
            identifier.setType(type);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.escidoc.core.resources.XLinkResource#setXLinkTitle(java.lang.String)
     */
    @Override
    protected void setXLinkTitle(final String title) {
        if (identifier != null)
            identifier.setTitle(title);
    }

    /**
     * @return The {@link ResourceType} of the implementation of this abstract
     *         class.
     */
    public abstract ResourceType getResourceType();

    /**
     * Factory method for JiBX to create an instance of {@link Identifier}.
     * 
     * @param obj
     * @return
     */
    protected static final Identifier createIdentifier(final Object obj) {
        if (obj instanceof Resource) {
            return ((Resource) obj).getIdentifierInstance();
        }
        throw new IllegalArgumentException();
    }

    /**
     * This method returns an instance of the Identifier. All classes, which
     * extends this class in order to define new {@link Identifier}s must
     * override this method and return their own implementation instead of the
     * default one. <br/>
     * <br/>
     * See {@link VersionableResource} for an example of another Identifier
     * implementation.
     * 
     * @return an instance of the {@link Identifier}
     */
    protected Identifier getIdentifierInstance() {
        return new Identifier();
    }

    /**
     * 
     */
    protected void validateIdentifier() {
        if (identifier != null && identifier.objid == null && identifier.href == null) {
            identifier = null;
        }
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
    protected static final String genXLinkHref(final Resource resource, final ResourceType type, final String prefix) {

        if (resource != null && resource.getXLinkHref() == null) {

            if (type != null && resource.getObjid() != null) {

                String href = type.getPath(resource.getObjid());
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
     * @param resource
     * @param prefix
     * @return the generated HREF
     */
    protected static final String genXLinkHref(final Resource resource, final String prefix) {

        if (resource != null && resource.getXLinkHref() == null) {

            if (resource.getResourceType() != null && resource.getObjid() != null) {

                String href = resource.getResourceType().getPath(resource.getObjid());
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

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (identifier == null ? 0 : identifier.hashCode());
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
        if (identifier == null) {
            if (other.identifier != null)
                return false;
        }
        else if (!identifier.equals(other.identifier))
            return false;
        return true;
    }

    /**
     * @author MVO
     * 
     */
    @JiBX
    protected class Identifier {

        protected String objid;

        protected String href;

        protected XLinkType type = XLinkType.simple;

        protected String title;

        /**
         * @param id
         */
        Identifier() {
        }

        /**
         * @param objid
         */
        public Identifier(final String objid) {
            checkNotNull(objid);
            this.objid = objid;
        }

        /**
         * @param href
         * @param title
         * @param type
         */
        public Identifier(final String href, final String title, final XLinkType type) {
            checkNotNull(href);
            this.href = href;
            this.title = title;
            this.type = type == null ? XLinkType.simple : type;
        }

        /**
         * @param objid
         *            the objid to set
         */
        public void setObjid(final String objid) {
            this.objid = objid;
        }

        /**
         * @param href
         *            the href to set
         */
        public void setHref(final String href) {
            this.href = href;
        }

        /**
         * @param type
         *            the type to set
         */
        public void setType(final XLinkType type) {
            if (type != null)
                this.type = type;
        }

        /**
         * @param title
         *            the title to set
         */
        public void setTitle(final String title) {
            this.title = title;
        }

        /**
         * @return the objid
         */
        public String getObjid() {
            if (objid != null)
                return objid;
            if (href != null)
                setObjid(genObjid());
            return objid;
        }

        /**
         * @return the href
         */
        public String getHref() {
            if (href != null)
                return href;
            if (objid != null)
                setHref(genXLink());
            return href;
        }

        /**
         * @return
         */
        public boolean hasTitleForHref() {
            return getHref() != null && getTitle() != null;
        }

        /**
         * @return
         */
        public boolean hasTypeForHref() {
            return getHref() != null && getType() != null;
        }

        /**
         * @return the type
         */
        public XLinkType getType() {
            return type;
        }

        /**
         * @return the title
         */
        public String getTitle() {
            return title;
        }

        /**
         * @return
         */
        protected String genXLink() {
            if (href == null && getResourceType() != null && getResourceType().isRootResource())
                return new StringBuilder(getResourceType().getPath()).append('/').append(objid).toString();
            return null;
        }

        /**
         * @return
         */
        protected String genObjid() {
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
            result = prime * result + (this.getObjid() == null ? 0 : this.getObjid().hashCode());
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
}