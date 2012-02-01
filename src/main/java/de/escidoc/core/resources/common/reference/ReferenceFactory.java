package de.escidoc.core.resources.common.reference;

import org.jibx.runtime.IUnmarshallingContext;
import org.jibx.runtime.JiBXException;
import org.jibx.runtime.impl.UnmarshallingContext;

import de.escidoc.core.annotations.JiBX;
import de.escidoc.core.resources.ResourceType;

@JiBX
public class ReferenceFactory {

    private static final String ATTR_NAME_XLINK_HREF = "href";

    private static final String ATTR_NS_XLINK = "http://www.w3.org/1999/xlink";

    /**
     * Automatically resolves the Reference implementation to use for a given
     * xlink:href attribute.
     * 
     * @param iuctx
     * @return the {@link Reference} implementation or <tt>null</tt> if unable
     *         to resolve the implementation.
     * @throws JiBXException
     */
    protected static final Reference createReference(final IUnmarshallingContext iuctx) throws JiBXException {

        if (!(iuctx instanceof UnmarshallingContext))
            throw new JiBXException("Unexpected UnmarshallingContext");

        final UnmarshallingContext uctx = (UnmarshallingContext) iuctx;
        String xLinkHref = null;

        for (int index = 0; index < uctx.getAttributeCount(); index++) {
            if (xLinkHref != null)
                break;
            if (ATTR_NS_XLINK.equals(uctx.getAttributeNamespace(index))) {
                if (ATTR_NAME_XLINK_HREF.equals(uctx.getAttributeName(index))) {
                    xLinkHref = uctx.getAttributeValue(index);
                }
            }
        }

        if (xLinkHref != null) {
            final String prefixPath = xLinkHref.substring(0, xLinkHref.lastIndexOf('/'));
            final ResourceType resourceType = ResourceType.getValue(prefixPath);

            switch (resourceType) {
                case AGGREGATION_DEFINITION:
                    return new AggregationDefinitionRef();
                case CONTAINER:
                    return new ContainerRef();
                case CONTENT_MODEL:
                    return new ContentModelRef();
                case CONTENT_RELATION:
                    return new ContentRelationRef();
                case CONTEXT:
                    return new ContextRef();
                case GRANT:
                    return new GrantRef();
                case ITEM:
                    return new ItemRef();
                case ORGANIZATIONAL_UNIT:
                    return new OrganizationalUnitRef();
                case REPORT_DEFINITION:
                    return new ReportDefinitionRef();
                case ROLE:
                    return new RoleRef();
                case SCOPE:
                    return new ScopeRef();
                case USERACCOUNT:
                    return new UserAccountRef();
                case USERGROUP:
                    return new UserGroupRef();
                case SET_DEFINITION:
                    return new SetDefinitionRef();
            }
        }

        throw new JiBXException("Unable to resolve Reference implementation type.");
    }

    // automatically detect the Reference implementation
    // if (resourceType != null) {
    // for (final Type type :
    // resourceType.getResourceClass().getGenericInterfaces()) {
    // if (type instanceof ParameterizedType) {
    // final ParameterizedType pType = (ParameterizedType) type;
    //
    // if (Referenceable.class.equals(pType.getRawType())) {
    // /*
    // * {@link Referenceable} should support only one
    // * type argument
    // */
    // if (pType.getActualTypeArguments().length != 1
    // && !Reference.class.equals(pType.getActualTypeArguments()[0])) {
    // throw new
    // JiBXException("The Referenceable class may have been changed.");
    // }
    // final Type refType = pType.getActualTypeArguments()[0];
    // if (refType instanceof Class) {
    // try {
    // return (Reference) ((Class<?>) refType).newInstance();
    // }
    // catch (final InstantiationException e) {
    // throw new JiBXException(
    // "Unable to create a new instance of the Reference implementation.");
    // }
    // catch (final IllegalAccessException e) {
    // throw new JiBXException(
    // "Unable to create a new instance of the Reference implementation.");
    // }
    // }
    //
    // }
    // }
    // }
    // }
}