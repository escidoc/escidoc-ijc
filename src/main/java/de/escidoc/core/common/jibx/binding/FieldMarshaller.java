/**
 * 
 */
package de.escidoc.core.common.jibx.binding;

import org.jibx.runtime.IAliasable;
import org.jibx.runtime.IMarshaller;
import org.jibx.runtime.IMarshallingContext;
import org.jibx.runtime.IUnmarshaller;
import org.jibx.runtime.IUnmarshallingContext;
import org.jibx.runtime.JiBXException;
import org.jibx.runtime.impl.MarshallingContext;
import org.jibx.runtime.impl.UnmarshallingContext;

import de.escidoc.core.resources.sm.ad.CountCumulationField;
import de.escidoc.core.resources.sm.ad.DifferenceCumulationField;
import de.escidoc.core.resources.sm.ad.Field;
import de.escidoc.core.resources.sm.ad.InfoField;
import de.escidoc.core.resources.sm.ad.InfoFieldType;
import de.escidoc.core.resources.sm.ad.TimeReductionField;
import de.escidoc.core.resources.sm.ad.TimeReductionFieldType;

/**
 * This Marshaller exists because of the wrong XML design for
 * aggregation-definition. It is not possible to define a mapping for the
 * current structure:<br/>
 * <br/>
 * &lt;xs:element name="field" type="aggregation-definition:FieldType"
 * maxOccurs="unbounded"&gt;<br/>
 * <br/>
 * &lt;xs:complexType name="FieldType"&gt;<br/>
 * &lt;xs:sequence&gt;<br/>
 * &lt;xs:choice&gt;<br/>
 * &lt;xs:element name="info-field"&gt; ... &lt;/xs:element&gt;<br/>
 * &lt;xs:element name="time-reduction-field"&gt;...&lt;/xs:element&gt;<br/>
 * &lt;xs:element name="count-cumulation-field"&gt;...&lt;/xs:element&gt;<br/>
 * &lt;xs:element name="difference-cumulation-field"&gt;...&lt;/xs:element&gt;<br/>
 * &lt;/xs:choice&gt;<br/>
 * &lt;/xs:sequence&gt;<br/>
 * &lt;/xs:complexType&gt;<br/>
 * <br/>
 * This should be changed to this:<br/>
 * <br/>
 * &lt;xs:element name="fields"&gt;<br/>
 * &lt;xs:sequence&gt;<br/>
 * &lt;xs:choice minOccurs="1" maxOccurs="unbounded"&gt;<br/>
 * &lt;xs:element name="info-field"&gt; ... &lt;/xs:element&gt;<br/>
 * &lt;xs:element name="time-reduction-field"&gt;...&lt;/xs:element&gt;<br/>
 * &lt;xs:element name="count-cumulation-field"&gt;...&lt;/xs:element&gt;<br/>
 * &lt;xs:element name="difference-cumulation-field"&gt;...&lt;/xs:element&gt;<br/>
 * &lt;/xs:choice&gt;<br/>
 * &lt;/xs:sequence&gt;<br/>
 * &lt;/xs:element&gt;
 * 
 * @author MVO
 * 
 */
public class FieldMarshaller extends MarshallingBase
    implements IMarshaller, IUnmarshaller, IAliasable {

    private static final String TAG_INFO_FIELD = "info-field";

    private static final String TAG_TIME_REDUCTION_FIELD =
        "time-reduction-field";

    private static final String TAG_COUNT_CUMULATION_FIELD =
        "count-cumulation-field";

    private static final String TAG_DIFF_CUMULATION_FIELD =
        "difference-cumulation-field";

    private static final String TAG_NAME = "name";

    private static final String TAG_XPATH = "xpath";

    private static final String TAG_TYPE = "type";

    private static final String TAG_REDUCE_TO = "reduce-to";

    private static final String ATTR_FEED = "feed";

    /**
     * 
     * @param uri
     * @param index
     * @param name
     */
    public FieldMarshaller(final String uri, final int index, final String name) {
        super(uri, index, name);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.jibx.runtime.IUnmarshaller#isPresent(org.jibx.runtime.
     * IUnmarshallingContext)
     */
    @Override
    public boolean isPresent(final IUnmarshallingContext ictx)
        throws JiBXException {
        return ictx.isAt(getUri(), getName());
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.jibx.runtime.IUnmarshaller#unmarshal(java.lang.Object,
     * org.jibx.runtime.IUnmarshallingContext)
     */
    @Override
    public Object unmarshal(final Object obj, final IUnmarshallingContext ictx)
        throws JiBXException {

        if (!(ictx instanceof UnmarshallingContext))
            throw new IllegalArgumentException(
                "UnmarshallingContext is not of expected type.");

        UnmarshallingContext ctx = (UnmarshallingContext) ictx;
        Field field = null;

        ctx.parsePastStartTag(getUri(), getName());

        if (ctx.isAt(getUri(), TAG_INFO_FIELD)) {
            String feed = ctx.attributeText(null, ATTR_FEED);

            ctx.parsePastStartTag(getUri(), TAG_INFO_FIELD);

            String name = ctx.parseElementText(getUri(), TAG_NAME);
            String type = ctx.parseElementText(getUri(), TAG_TYPE);
            String xPath = ctx.parseElementText(getUri(), TAG_XPATH);

            field =
                new InfoField(name, feed, getEnumValue(InfoFieldType.class,
                    type), xPath);

            ctx.parsePastEndTag(getUri(), TAG_INFO_FIELD);
        }
        else if (ctx.isAt(getUri(), TAG_TIME_REDUCTION_FIELD)) {

            String feed = ctx.attributeText(null, ATTR_FEED);

            ctx.parsePastStartTag(getUri(), TAG_TIME_REDUCTION_FIELD);

            String name = ctx.parseElementText(getUri(), TAG_NAME);
            String reduceTo = ctx.parseElementText(getUri(), TAG_REDUCE_TO);
            // xPath is optional here
            String xPath = ctx.parseElementText(getUri(), TAG_XPATH, null);

            field =
                new TimeReductionField(name, feed, getEnumValue(
                    TimeReductionFieldType.class, reduceTo), xPath);

            ctx.parsePastEndTag(getUri(), TAG_TIME_REDUCTION_FIELD);
        }
        else if (ctx.isAt(getUri(), TAG_COUNT_CUMULATION_FIELD)) {
            ctx.parsePastStartTag(getUri(), TAG_COUNT_CUMULATION_FIELD);

            String name = ctx.parseElementText(getUri(), TAG_NAME);

            field = new CountCumulationField(name);

            ctx.parsePastEndTag(getUri(), TAG_COUNT_CUMULATION_FIELD);
        }
        else if (ctx.isAt(getUri(), TAG_DIFF_CUMULATION_FIELD)) {

            String feed = ctx.attributeText(null, ATTR_FEED);

            ctx.parsePastStartTag(getUri(), TAG_DIFF_CUMULATION_FIELD);

            String name = ctx.parseElementText(getUri(), TAG_NAME);
            String xPath = ctx.parseElementText(getUri(), TAG_XPATH);

            field = new DifferenceCumulationField(name, feed, xPath);

            ctx.parsePastEndTag(getUri(), TAG_DIFF_CUMULATION_FIELD);
        }

        ctx.parsePastEndTag(getUri(), getName());

        return field;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.jibx.runtime.IMarshaller#isExtension(java.lang.String)
     */
    @Override
    public boolean isExtension(final String arg0) {
        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.jibx.runtime.IMarshaller#marshal(java.lang.Object,
     * org.jibx.runtime.IMarshallingContext)
     */
    @Override
    public void marshal(final Object obj, final IMarshallingContext ictx)
        throws JiBXException {

        if (!(obj instanceof Field))
            throw new JiBXException("Invalid object type for marshaller");
        if (!(ictx instanceof MarshallingContext))
            throw new JiBXException(
                "Invalid MarshallingContext type for marshaller");

        MarshallingContext ctx = (MarshallingContext) ictx;
        Field field = (Field) obj;

        ctx.startTag(getIndex(), getName());

        switch (field.getType()) {
            case INFO: {
                InfoField iField = (InfoField) field;
                ctx.startTagAttributes(getIndex(), TAG_INFO_FIELD).attribute(0,
                    ATTR_FEED, iField.getFeed());
                ctx.closeStartContent();

                ctx.startTag(getIndex(), TAG_NAME);
                ctx.content(iField.getName());
                ctx.endTag(getIndex(), TAG_NAME);

                ctx.startTag(getIndex(), TAG_TYPE);
                ctx.content(iField.getInfoFieldType().getXmlValue());
                ctx.endTag(getIndex(), TAG_TYPE);

                ctx.startTag(getIndex(), TAG_XPATH);
                ctx.content(iField.getXPath());
                ctx.endTag(getIndex(), TAG_XPATH);

                ctx.endTag(getIndex(), TAG_INFO_FIELD);
                break;
            }
            case TIME_REDUCTION: {
                TimeReductionField tField = (TimeReductionField) field;
                ctx.startTagAttributes(getIndex(), TAG_TIME_REDUCTION_FIELD)
                    .attribute(0, ATTR_FEED, tField.getFeed());
                ctx.closeStartContent();

                ctx.startTag(getIndex(), TAG_NAME);
                ctx.content(tField.getName());
                ctx.endTag(getIndex(), TAG_NAME);

                ctx.startTag(getIndex(), TAG_REDUCE_TO);
                ctx.content(tField.getReduceTo().getXmlValue());
                ctx.endTag(getIndex(), TAG_REDUCE_TO);

                // optional xpath
                if (tField.getXPath() != null) {
                    ctx.startTag(getIndex(), TAG_XPATH);
                    ctx.content(tField.getXPath());
                    ctx.endTag(getIndex(), TAG_XPATH);
                }

                ctx.endTag(getIndex(), TAG_TIME_REDUCTION_FIELD);
                break;
            }
            case COUNT_CUMULATION: {
                CountCumulationField cField = (CountCumulationField) field;
                ctx.startTag(getIndex(), TAG_COUNT_CUMULATION_FIELD);

                ctx.startTag(getIndex(), TAG_NAME);
                ctx.content(cField.getName());
                ctx.endTag(getIndex(), TAG_NAME);

                ctx.endTag(getIndex(), TAG_COUNT_CUMULATION_FIELD);
                break;
            }
            case DIFFERENCE_CUMULATION: {
                DifferenceCumulationField dField =
                    (DifferenceCumulationField) field;
                ctx.startTagAttributes(getIndex(), TAG_DIFF_CUMULATION_FIELD)
                    .attribute(0, ATTR_FEED, dField.getFeed());
                ctx.closeStartContent();

                ctx.startTag(getIndex(), TAG_NAME);
                ctx.content(dField.getName());
                ctx.endTag(getIndex(), TAG_NAME);

                ctx.startTag(getIndex(), TAG_XPATH);
                ctx.content(dField.getXPath());
                ctx.endTag(getIndex(), TAG_XPATH);

                ctx.endTag(getIndex(), TAG_DIFF_CUMULATION_FIELD);
                break;
            }
        }

        ctx.endTag(getIndex(), getName());
    }
}