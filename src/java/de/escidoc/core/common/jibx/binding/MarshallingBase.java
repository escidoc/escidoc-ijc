/**
 * 
 */
package de.escidoc.core.common.jibx.binding;

import java.io.IOException;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jibx.runtime.IUnmarshallingContext;
import org.jibx.runtime.JiBXException;
import org.jibx.runtime.impl.MarshallingContext;
import org.jibx.runtime.impl.UnmarshallingContext;

/**
 * @author msc
 * 
 */
public class MarshallingBase {

    private static final Pattern CDATA_PATTERN = Pattern
        .compile("<!\\[CDATA.*\\]\\]>");

    private static final Pattern XML_DECLARATION_PATTERN = Pattern
        .compile("<\\?xml.*\\?>");

    protected static final String SIZE_ATTRIBUTE_NAME = "size";

    protected static final String NAME_ATTRIBUTE_NAME = "name";

    protected static final String XLINK_TYPE_ATTRIBUTE_NAME = "type";

    protected static final String XLINK_TITLE_ATTRIBUTE_NAME = "title";

    protected static final String XLINK_HREF_ATTRIBUTE_NAME = "href";

    protected static final String XLINK_NAMESPACE =
        "http://www.w3.org/1999/xlink";

    private final String uri;

    private final int index;

    private final String name;

    /**
     * 
     * @param uri
     * @param index
     * @param name
     */
    public MarshallingBase(final String uri, final int index, final String name) {
        super();
        this.uri = uri;
        this.index = index;
        this.name = name;
    }

    /**
     * 
     * @param ictx
     * @param element
     * @return
     * @throws JiBXException
     */
    protected String getContentOfElementAsXml(
        IUnmarshallingContext ictx, String element) throws JiBXException {
        StringBuffer result = new StringBuffer();
        UnmarshallingContext ctx = (UnmarshallingContext) ictx;

        boolean finished = false;
        ctx.isStart();
        // String root = ctx.getElementName();
        while (!finished) {
            if (ctx.isStart()) {
                result = result.append(getStartElement(ictx));
                ctx.parsePastStartTag(ctx.getNamespace(), ctx.getElementName());
                result = result.append(getContent(ictx));
            }
            else if (ctx.isEnd()) {
                if (!element.equals(ctx.getElementName())) {
                    result = result.append(getEndElement(ictx));
                    ctx.parsePastEndTag(ctx.getNamespace(),
                        ctx.getElementName());
                }
                else {
                    finished = true;
                }
            }
        }
        return result.toString();
    }

    /**
     * Get XML start element.
     * 
     * @param ictx
     * @return
     * @throws JiBXException
     */
    private StringBuffer getStartElement(IUnmarshallingContext ictx)
        throws JiBXException {
        StringBuffer result = new StringBuffer("<");

        UnmarshallingContext ctx = (UnmarshallingContext) ictx;

        String prefix = ctx.getPrefix();
        if (prefix != null && prefix.length() != 0) {
            result.append(prefix).append(":");
            result.append(new StringBuffer(ctx.getElementName()));
        }
        else {
            result.append(new StringBuffer(ctx.getElementName()));
        }

        // namespaces
        StringBuffer namespaces = new StringBuffer();
        for (int i = 0; i < ctx.getNamespaceCount(); ++i) {
            namespaces.append(" xmlns");
            String currentNSPrefix = ctx.getNamespacePrefix(i);
            if (currentNSPrefix != null && currentNSPrefix.length() != 0) {
                namespaces.append(":").append(currentNSPrefix);
            }
            namespaces
                .append("=\"").append(ctx.getNamespaceUri(i)).append("\"");
        }
        result.append(namespaces);

        // attributes
        StringBuffer attributes = new StringBuffer();
        for (int i = 0; i < ctx.getAttributeCount(); ++i) {
            String attrPrefix = ctx.getAttributePrefix(i);
            attributes.append(" ");
            if (attrPrefix != null && attrPrefix.length() != 0) {
                attributes.append(attrPrefix).append(":");
            }
            attributes
                .append(ctx.getAttributeName(i)).append("=\"")
                .append(ctx.getAttributeValue(i)).append("\"");
        }
        result.append(attributes);

        return result.append(">");
    }

    /**
     * Get the content of XML element.
     * 
     * @param ictx
     *            Marshalling Context.
     * @return XML Element content as StringBuffer
     * @throws JiBXException
     *             Thrown if JiBX mapping failed.
     */
    private StringBuffer getContent(final IUnmarshallingContext ictx)
        throws JiBXException {
        StringBuffer result = new StringBuffer();
        UnmarshallingContext ctx = (UnmarshallingContext) ictx;
        return result.append(ctx.parseContentText());
    }

    /**
     * Get XML end element.
     * 
     * @param ictx
     * @return
     * @throws JiBXException
     */
    private StringBuffer getEndElement(IUnmarshallingContext ictx)
        throws JiBXException {
        StringBuffer result = new StringBuffer("</");
        UnmarshallingContext ctx = (UnmarshallingContext) ictx;

        String prefix = ctx.getPrefix();
        if (prefix != null && !"".equals(prefix)) {
            result = result.append(prefix).append(":");
            result = result.append(new StringBuffer(ctx.getElementName()));
        }
        else {
            result = result.append(new StringBuffer(ctx.getElementName()));
        }

        return result.append(">");
    }

    /**
     * 
     * @param ctx
     * @param content
     * @throws JiBXException
     * @throws IOException
     */
    public void setContentWhileMarshalling(
        final MarshallingContext ctx, final String content)
        throws JiBXException, IOException {
        String modifiedContentArray[] = null;
        String modifiedContent = null;
        Matcher xmlDeclaration = XML_DECLARATION_PATTERN.matcher(content);
        if (xmlDeclaration.find()) {
            modifiedContentArray = XML_DECLARATION_PATTERN.split(content);
            modifiedContent = modifiedContentArray[1];
        }
        else {
            modifiedContent = content;
        }

        Matcher m = CDATA_PATTERN.matcher(modifiedContent);
        Vector<String> cDataSections = new Vector<String>();
        while (m.find()) {
            String result = m.group();
            result = result.substring(9, result.length() - 3);
            cDataSections.add(result);
        }
        m.reset();
        String[] text = CDATA_PATTERN.split(modifiedContent);
        for (int i = 0; i < text.length; i++) {
            ctx.content(text[i]);
            if (i < text.length - 1) {
                ctx.writeCData(cDataSections.get(i));
            }

        }
        // HashMap<String, String> map= new HashMap<String, String>();

    }

    /**
     * @return the uri
     */
    public String getUri() {
        return uri;
    }

    /**
     * @return the index
     */
    public int getIndex() {
        return index;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Removes whitespace characters [ \t\n\x0B\f\r] at the beginning and end of
     * a text.
     * 
     * @param textContent
     * @return
     */
    protected String cleanup(String textContent) {
        return textContent.replaceAll("(^\\s+)|(\\s+$)", "");
    }
}
