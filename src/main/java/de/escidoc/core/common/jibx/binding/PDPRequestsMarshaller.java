/**
 * 
 */
package de.escidoc.core.common.jibx.binding;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashSet;
import java.util.Set;

import org.jibx.runtime.IAliasable;
import org.jibx.runtime.IMarshaller;
import org.jibx.runtime.IMarshallingContext;
import org.jibx.runtime.IUnmarshaller;
import org.jibx.runtime.IUnmarshallingContext;
import org.jibx.runtime.JiBXException;
import org.jibx.runtime.impl.MarshallingContext;
import org.jibx.runtime.impl.UnmarshallingContext;

import com.sun.xacml.ParsingException;
import com.sun.xacml.UnknownIdentifierException;
import com.sun.xacml.attr.AttributeFactory;
import com.sun.xacml.ctx.Attribute;
import com.sun.xacml.ctx.RequestCtx;
import com.sun.xacml.ctx.Subject;

import de.escidoc.core.resources.aa.pdp.Requests;

/**
 * @author MVO
 * 
 */
public class PDPRequestsMarshaller extends MarshallingBase implements IMarshaller, IUnmarshaller, IAliasable {

    public static final String NS_XACML_PREFIX = "xacml-context";

    public static final String NS_XACML = "urn:oasis:names:tc:xacml:1.0:context";

    public static final String NS_REQUESTS_PREFIX = "requests";

    public static final String TAG_NAME_REQUEST = "Request";

    public static final String TAG_NAME_SUBJECT = "Subject";

    public static final String TAG_NAME_RESOURCE = "Resource";

    public static final String TAG_NAME_ACTION = "Action";

    public static final String TAG_NAME_ENVIRONMENT = "Environment";

    public static final String TAG_NAME_ATTRIBUTE = "Attribute";

    public static final String TAG_NAME_ATTRIBUTE_VALUE = "AttributeValue";

    public static final String ATTR_NAME_SUBJECT_CATEGORY = "SubjectCategory";

    public static final String ATTR_NAME_ATTRIBUTE_ID = "AttributeId";

    public static final String ATTR_NAME_DATA_TYPE = "DataType";

    /**
     * 
     * @param uri
     * @param index
     * @param name
     */
    public PDPRequestsMarshaller(String uri, int index, String name) {
        super(uri, index, name);
    }

    @Override
    public boolean isPresent(IUnmarshallingContext ictx) throws JiBXException {
        return ictx.isAt(getUri(), getName());
    }

    @Override
    public boolean isExtension(String arg0) {
        return false;
    }

    @Override
    public Object unmarshal(Object obj, IUnmarshallingContext ictx) throws JiBXException {

        if (!(ictx instanceof UnmarshallingContext)) {
            throw new JiBXException("Unexpected unmarshalling context type.");
        }

        Requests requests;

        if (obj instanceof Requests) {
            requests = (Requests) obj;
        }
        else {
            requests = new Requests();
        }

        UnmarshallingContext ctx = (UnmarshallingContext) ictx;

        ctx.parsePastStartTag(getUri(), getName());

        while (ctx.isAt(NS_XACML, TAG_NAME_REQUEST)) {
            ctx.parsePastStartTag(NS_XACML, TAG_NAME_REQUEST);

            Set<Subject> subjects = new HashSet<Subject>();
            Set<Attribute> resourceAttrs = new HashSet<Attribute>();
            Set<Attribute> actionAttrs = new HashSet<Attribute>();
            Set<Attribute> envAttrs = new HashSet<Attribute>();

            // get subjects
            while (ctx.isAt(NS_XACML, TAG_NAME_SUBJECT)) {
                String subjectId = ctx.attributeText(null, ATTR_NAME_SUBJECT_CATEGORY, null);

                ctx.parsePastStartTag(NS_XACML, TAG_NAME_SUBJECT);

                Set<Attribute> subjectAttrs = new HashSet<Attribute>();
                parseAttributes(subjectAttrs, ctx);

                if (subjectId == null) {
                    subjects.add(new Subject(subjectAttrs));
                }
                else {
                    try {
                        subjects.add(new Subject(new URI(subjectId), subjectAttrs));
                    }
                    catch (URISyntaxException e) {
                        throw new JiBXException("Unable to parse subject.", e);
                    }
                }
                ctx.parsePastEndTag(NS_XACML, TAG_NAME_SUBJECT);
            }
            if (ctx.isAt(NS_XACML, TAG_NAME_RESOURCE)) {
                ctx.parsePastStartTag(NS_XACML, TAG_NAME_RESOURCE);

                parseAttributes(resourceAttrs, ctx);

                ctx.parsePastEndTag(NS_XACML, TAG_NAME_RESOURCE);
            }
            if (ctx.isAt(NS_XACML, TAG_NAME_ACTION)) {
                ctx.parsePastStartTag(NS_XACML, TAG_NAME_ACTION);

                parseAttributes(actionAttrs, ctx);

                ctx.parsePastEndTag(NS_XACML, TAG_NAME_ACTION);
            }
            if (ctx.isAt(NS_XACML, TAG_NAME_ENVIRONMENT)) {
                ctx.parsePastStartTag(NS_XACML, TAG_NAME_ENVIRONMENT);

                parseAttributes(envAttrs, ctx);

                ctx.parsePastEndTag(NS_XACML, TAG_NAME_ENVIRONMENT);
            }

            requests.add(new RequestCtx(subjects, resourceAttrs, actionAttrs, envAttrs));

            ctx.parsePastEndTag(NS_XACML, TAG_NAME_REQUEST);
        }

        ctx.parsePastEndTag(getUri(), getName());

        return requests;
    }

    /**
     * 
     * @param attributes
     * @param ctx
     * @throws JiBXException
     * @throws URISyntaxException
     */
    private void parseAttributes(final Set<Attribute> attributes, final UnmarshallingContext ctx) throws JiBXException {

        try {
            while (ctx.isAt(NS_XACML, TAG_NAME_ATTRIBUTE)) {
                URI id = new URI(ctx.attributeText(null, ATTR_NAME_ATTRIBUTE_ID));

                URI dataType = new URI(ctx.attributeText(null, ATTR_NAME_DATA_TYPE));
                String value = null;

                ctx.parsePastStartTag(NS_XACML, TAG_NAME_ATTRIBUTE);
                if (ctx.isAt(NS_XACML, TAG_NAME_ATTRIBUTE_VALUE)) {
                    value = cleanup(ctx.parseElementText(NS_XACML, TAG_NAME_ATTRIBUTE_VALUE));
                }
                else {
                    throw new JiBXException("{" + NS_XACML + "}AttributeValue expected but found: {"
                        + ctx.getElementNamespace() + "}" + ctx.getElementName());
                }
                attributes.add(new Attribute(id, null, null, AttributeFactory
                    .getInstance().createValue(dataType, value)));
                ctx.parsePastEndTag(NS_XACML, TAG_NAME_ATTRIBUTE);
            }
        }
        catch (URISyntaxException e) {
            throw new JiBXException("Unable to parse attribute.", e);
        }
        catch (UnknownIdentifierException e) {
            throw new JiBXException("Unable to parse attribute.", e);
        }
        catch (ParsingException e) {
            throw new JiBXException("Unable to parse attribute.", e);
        }
    }

    @Override
    public void marshal(Object obj, IMarshallingContext ictx) throws JiBXException {

        if (!(obj instanceof Requests)) {
            throw new JiBXException("Invalid object type for marshaller");
        }
        if (!(ictx instanceof MarshallingContext)) {
            throw new JiBXException("Invalid object type for marshaller");
        }

        MarshallingContext ctx = (MarshallingContext) ictx;
        Requests requests = (Requests) obj;

        int[] urisIndex = new int[] { findNamespace(ctx, getUri()), findNamespace(ctx, NS_XACML) };
        String[] prefixIndex = new String[] { NS_REQUESTS_PREFIX, NS_XACML_PREFIX };

        ctx.startTagNamespaces(urisIndex[0], getName(), urisIndex, prefixIndex);
        ctx.closeStartContent();

        for (RequestCtx request : requests) {
            ctx.startTag(urisIndex[1], TAG_NAME_REQUEST);

            for (Object subjectObj : request.getSubjects()) {
                Subject subject = (Subject) subjectObj;

                ctx.startTagAttributes(urisIndex[1], TAG_NAME_SUBJECT);
                ctx.attribute(0, ATTR_NAME_SUBJECT_CATEGORY, subject.getCategory().toString());
                ctx.closeStartContent();

                serializeAttributes(urisIndex[1], subject.getAttributes(), ctx);

                ctx.endTag(urisIndex[1], TAG_NAME_SUBJECT);
            }

            ctx.startTag(urisIndex[1], TAG_NAME_RESOURCE);
            serializeAttributes(urisIndex[1], request.getResource(), ctx);
            ctx.endTag(urisIndex[1], TAG_NAME_RESOURCE);

            ctx.startTag(urisIndex[1], TAG_NAME_ACTION);
            serializeAttributes(urisIndex[1], request.getAction(), ctx);
            ctx.endTag(urisIndex[1], TAG_NAME_ACTION);

            ctx.startTag(urisIndex[1], TAG_NAME_ENVIRONMENT);
            serializeAttributes(urisIndex[1], request.getEnvironmentAttributes(), ctx);
            ctx.endTag(urisIndex[1], TAG_NAME_ENVIRONMENT);

            ctx.endTag(urisIndex[1], TAG_NAME_REQUEST);
        }

        ctx.endTag(urisIndex[0], getName());
    }

    /**
     * 
     * @param ctx
     * @throws JiBXException
     */
    private void serializeAttributes(final int tagIndex, final Set<?> attributes, final MarshallingContext ctx)
        throws JiBXException {

        for (Object attrObj : attributes) {
            Attribute attribute = (Attribute) attrObj;

            ctx.startTagAttributes(tagIndex, TAG_NAME_ATTRIBUTE);
            ctx.attribute(0, ATTR_NAME_ATTRIBUTE_ID, attribute.getId().toString());
            ctx.attribute(0, ATTR_NAME_DATA_TYPE, attribute.getType().toString());

            ctx.startTag(tagIndex, TAG_NAME_ATTRIBUTE_VALUE);

            ctx.content(attribute.getValue().encode());

            ctx.endTag(tagIndex, TAG_NAME_ATTRIBUTE_VALUE);

            ctx.endTag(tagIndex, TAG_NAME_ATTRIBUTE);
        }
    }
}
