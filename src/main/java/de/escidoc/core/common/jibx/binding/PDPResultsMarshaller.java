/**
 * 
 */
package de.escidoc.core.common.jibx.binding;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.jibx.runtime.IAliasable;
import org.jibx.runtime.IMarshaller;
import org.jibx.runtime.IMarshallingContext;
import org.jibx.runtime.IUnmarshaller;
import org.jibx.runtime.IUnmarshallingContext;
import org.jibx.runtime.JiBXException;
import org.jibx.runtime.impl.UnmarshallingContext;

import com.sun.xacml.ctx.ResponseCtx;
import com.sun.xacml.ctx.Status;

import de.escidoc.core.resources.aa.pdp.Decision;
import de.escidoc.core.resources.aa.pdp.Result;
import de.escidoc.core.resources.aa.pdp.Results;

/**
 * @author MVO
 * 
 */
public class PDPResultsMarshaller extends MarshallingBase
    implements IMarshaller, IUnmarshaller, IAliasable {

    public static final String NS_XACML_PREFIX = "xacml-context";

    public static final String NS_XACML =
        "urn:oasis:names:tc:xacml:1.0:context";

    public static final String TAG_NAME_ESCIDOC_RESULT = "result";

    public static final String ATTR_NAME_ESCIDOC_DECISION = "decision";

    public static final String TAG_NAME_XACML_RESPONSE = "Response";

    public static final String TAG_NAME_XACML_RESULT = "Result";

    public static final String TAG_NAME_XACML_DECISION = "Decision";

    public static final String TAG_NAME_XACML_STATUS = "Status";

    public static final String TAG_NAME_XACML_STATUS_CODE = "StatusCode";

    public static final String TAG_NAME_XACML_STATUS_MESSAGE = "StatusMessage";

    public static final String ATTR_NAME_XACML_RESOURCE_ID = "ResourceId";

    public static final String ATTR_NAME_XACML_VALUE = "Value";

    // public static final String TAG_NAME_

    public PDPResultsMarshaller(final String uri, final int index,
        final String name) {
        super(uri, index, name);
    }

    @Override
    public boolean isPresent(final IUnmarshallingContext ictx)
        throws JiBXException {
        return ictx.isAt(getUri(), getName());
    }

    @Override
    public boolean isExtension(final String mapname) {
        return false;
    }

    @Override
    public Object unmarshal(final Object obj, final IUnmarshallingContext ictx)
        throws JiBXException {

        if (!(ictx instanceof UnmarshallingContext)) {
            throw new JiBXException("Unexpected unmarshalling context type.");
        }

        Results results;

        if (obj instanceof Results) {
            results = (Results) obj;
        }
        else {
            results = new Results();
        }

        UnmarshallingContext ctx = (UnmarshallingContext) ictx;

        ctx.parsePastStartTag(getUri(), getName());

        while (ctx.isAt(getUri(), TAG_NAME_ESCIDOC_RESULT)) {

            Decision iDecision = null;
            String tmp = ctx.attributeText(null, ATTR_NAME_ESCIDOC_DECISION);
            // simulate JiBX's enum-value-method
            for (Decision d : Decision.values()) {
                if (d.getXmlValue().equals(tmp))
                    iDecision = d;
            }

            ctx.parsePastStartTag(getUri(), TAG_NAME_ESCIDOC_RESULT);

            ResponseCtx responseCtx = parseXACMLContent(ctx);

            ctx.parsePastEndTag(getUri(), TAG_NAME_ESCIDOC_RESULT);

            Result result = new Result();
            result.setInterpretedDecision(iDecision);
            result.setResponseCtx(responseCtx);

            results.add(result);
        }

        ctx.parsePastEndTag(getUri(), getName());

        return results;
    }

    /**
     * Parses the XACML part of the XML.
     * 
     * <ul>
     * <li>Obligations are not yet supported. (TODO)</li>
     * <li>StatusDetail, which can contain any content is not yet supported.
     * (TODO)</li>
     * </ul>
     * 
     * @param ctx
     * @return
     * @throws JiBXException
     */
    private ResponseCtx parseXACMLContent(final UnmarshallingContext ctx)
        throws JiBXException {

        ctx.parsePastStartTag(NS_XACML, TAG_NAME_XACML_RESPONSE);

        Set<com.sun.xacml.ctx.Result> xacmlResults =
            new HashSet<com.sun.xacml.ctx.Result>();

        while (ctx.isAt(NS_XACML, TAG_NAME_XACML_RESULT)) {

            // optional
            String resourceId =
                ctx.attributeText(null, ATTR_NAME_XACML_RESOURCE_ID, null);

            ctx.parsePastStartTag(NS_XACML, TAG_NAME_XACML_RESULT);

            ctx.parsePastStartTag(NS_XACML, TAG_NAME_XACML_DECISION);
            String pdpDecision = ctx.parseContentText().trim();
            ctx.parsePastEndTag(NS_XACML, TAG_NAME_XACML_DECISION);

            // STATUS
            ctx.parsePastStartTag(NS_XACML, TAG_NAME_XACML_STATUS);

            String statusCode = null;
            if (ctx.isAt(NS_XACML, TAG_NAME_XACML_STATUS_CODE)) {

                statusCode = ctx.attributeText(null, ATTR_NAME_XACML_VALUE);
                // ctx.parsePastStartTag(NS_XACML, TAG_NAME_XACML_STATUS_CODE);
                ctx.parsePastEndTag(NS_XACML, TAG_NAME_XACML_STATUS_CODE);
            }
            else {
                throw new JiBXException("Missing required "
                    + TAG_NAME_XACML_STATUS_CODE + " tag.");
            }

            // optional
            String statusMessage = null;

            if (ctx.isAt(NS_XACML, TAG_NAME_XACML_STATUS_MESSAGE)) {
                ctx.parsePastStartTag(NS_XACML, TAG_NAME_XACML_STATUS_MESSAGE);

                statusMessage =
                    ctx.parseContentText(NS_XACML,
                        TAG_NAME_XACML_STATUS_MESSAGE);
            }

            ctx.parsePastEndTag(NS_XACML, TAG_NAME_XACML_STATUS);
            ctx.parsePastEndTag(NS_XACML, TAG_NAME_XACML_RESULT);

            addXACMLResult(xacmlResults, statusCode, statusMessage,
                pdpDecision, resourceId);
        }

        ctx.parsePastEndTag(NS_XACML, TAG_NAME_XACML_RESPONSE);

        return new ResponseCtx(xacmlResults);
    }

    /**
     * 
     * @param results
     * @param statusCode
     * @param statusMessage
     * @param decision
     * @param resourceId
     */
    private void addXACMLResult(
        final Set<com.sun.xacml.ctx.Result> results, final String statusCode,
        final String statusMessage, final String decision,
        final String resourceId) {

        // construct Status object
        List<String> codes = new LinkedList<String>();
        codes.add(statusCode);
        Status status = new Status(codes, statusMessage);

        // construct and add Result object
        int decisionCode = -1;
        for (int i = 0; i < com.sun.xacml.ctx.Result.DECISIONS.length; i++) {
            if (com.sun.xacml.ctx.Result.DECISIONS[i].equals(decision))
                decisionCode = i;
        }

        results.add(new com.sun.xacml.ctx.Result(decisionCode, status,
            resourceId, null));
    }

    @Override
    public void marshal(final Object obj, final IMarshallingContext ctx)
        throws JiBXException {
        throw new JiBXException("Method not yet supported.");
    }

}
