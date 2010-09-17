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
/**
 * @author MVO
 * 
 */
public class PDPRequestsMarshaller extends MarshallingBase
    implements IMarshaller, IUnmarshaller, IAliasable {

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
    public Object unmarshal(Object obj, IUnmarshallingContext ictx)
        throws JiBXException {

        Requests requests;

        if (obj instanceof Requests) {
            requests = (Requests) obj;
        }
        else {
            requests = new Requests();
        }

        UnmarshallingContext ctx = (UnmarshallingContext) ictx;

        ctx.parsePastStartTag(getUri(), getName());

        while (ctx.isAt(getUri(), "Request")) {
            ctx.parsePastStartTag(getUri(), "Request");

            Set<Subject> subjects = new HashSet<Subject>();
            Set<Attribute> resourceAttrs = null;
            Set<Attribute> actionAttrs = null;
            Set<Attribute> envAttrs = null;

            // get subjects
            while (ctx.isAt(getUri(), "Subject")) {
                String subjectId = ctx.attributeText(getUri(), "SubjectCategory", "");

                ctx.parsePastStartTag(getUri(), "Subject");

                if (subjectId == null) {
                    subjects.add(new Subject(parseAttributes(ctx)));
                }
                else {
                    try {
                        subjects.add(new Subject(new URI(subjectId),
                                parseAttributes(ctx)));
                    }
                    catch (URISyntaxException e) {
                        throw new JiBXException("Unable to parse subject.", e);
                    }
                }
                ctx.parsePastEndTag(getUri(), "Subject");
            }
            if (ctx.isAt(getUri(), "Resource")) {
                ctx.parsePastStartTag(getUri(), "Resource");

                resourceAttrs = parseAttributes(ctx);

                ctx.parsePastEndTag(getUri(), "Resource");
            }
            if (ctx.isAt(getUri(), "Action")) {
                ctx.parsePastStartTag(getUri(), "Action");

                actionAttrs = parseAttributes(ctx);

                ctx.parsePastEndTag(getUri(), "Action");
            }
            if (ctx.isAt(getUri(), "Environment")) {
                ctx.parsePastStartTag(getUri(), "Environment");

                envAttrs = parseAttributes(ctx);

                ctx.parsePastEndTag(getUri(), "Environment");
            }

            requests.add(new RequestCtx(subjects, resourceAttrs, actionAttrs,
                envAttrs));

            ctx.parsePastEndTag(getUri(), "Request");
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
    private Set<Attribute> parseAttributes(final UnmarshallingContext ctx)
        throws JiBXException {
        Set<Attribute> attributes = new HashSet<Attribute>();

        try {
            while (ctx.isAt(getUri(), "Attribute")) {
                URI id = new URI(ctx.attributeText(getUri(), "AttributeId"));

                URI dataType = new URI(ctx.attributeText(getUri(), "DataType"));
                String value = null;

                ctx.parsePastStartTag(getUri(), "Attribute");
                if (ctx.isAt(getUri(), "AttributeValue")) {
                    value = ctx.parseContentText();
                }
                else {
                    throw new JiBXException("{" + getUri()
                        + "}AttributeValue expected but found: {"
                        + ctx.getElementNamespace() + "}"
                        + ctx.getElementName());
                }
                attributes.add(new Attribute(id, null, null, AttributeFactory
                    .getInstance().createValue(dataType, value)));
                ctx.parsePastEndTag(getUri(), "Attribute");
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

        return attributes;
    }

    @Override
    public void marshal(Object obj, IMarshallingContext ictx)
        throws JiBXException {
        // TODO Auto-generated method stub

    }
}
