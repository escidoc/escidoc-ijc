/**
 * 
 */
package de.escidoc.core.test.client.unitTests.marshalling.common.taskparam;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import de.escidoc.core.client.TransportProtocol;
import de.escidoc.core.resources.common.TaskParam;
import de.escidoc.core.test.client.unitTests.marshalling.MarshallerTestBase;

/**
 * @author Marko Voß
 * 
 */
public class TaskParamMarshallerTest extends MarshallerTestBase<TaskParam> {

    private static final String BASE = "common/taskParam";

    private static final String XSD = "0.1";

    /**
     * @param transport
     * @throws IOException
     * @throws ParserConfigurationException
     * @throws SAXException
     */
    public TaskParamMarshallerTest(final TransportProtocol transport) throws IOException, ParserConfigurationException,
        SAXException {
        super(TaskParam.class, BASE, XSD, "task-param_complete.xml", transport);
    }

    @Override
    protected void validate(final TaskParam obj) throws Exception {
        assertDateTime("/param/@last-modification-date", obj.getLastModificationDate());
        assertXPath("/param/comment", obj.getComment());
        assertXPath("/param/pid", obj.getPid());
        assertXPath("/param/password", obj.getPassword());
        assertXPath("/param/url", obj.getUrl());

        assertNotNull(obj.getResourceRefs());
        assertTrue(obj.getResourceRefs().size() >= 1);
        assertXPath("/param/id[1]", obj.getResourceRefs().get(0));

        assertTrue(obj.getResourceRefs().size() >= 2);
        assertXPath("/param/id[2]", obj.getResourceRefs().get(1));

        assertTrue(obj.getResourceRefs().size() >= 3);
        assertXPath("/param/id[3]", obj.getResourceRefs().get(2));

        assertTrue(obj.getResourceRefs().size() >= 4);
        assertXPath("/param/id[4]", obj.getResourceRefs().get(3));

        assertTrue(obj.getResourceRefs().size() >= 5);
        assertXPath("/param/id[5]", obj.getResourceRefs().get(4));

        assertNotNull(obj.getSelectors());
        assertTrue(obj.getSelectors().size() >= 1);
        assertXPath("/param/selector[1]/@name", obj.getSelectors().get(0).getName());
        assertEnum("/param/selector[1]/@type", obj.getSelectors().get(0).getType());
        assertXPath("/param/selector[1]", obj.getSelectors().get(0).getContent());

        assertTrue(obj.getSelectors().size() >= 2);
        assertXPath("/param/selector[2]/@name", obj.getSelectors().get(1).getName());
        assertEnum("/param/selector[2]/@type", obj.getSelectors().get(1).getType());
        assertXPath("/param/selector[2]", obj.getSelectors().get(1).getContent());

        assertTrue(obj.getSelectors().size() >= 3);
        assertXPath("/param/selector[3]/@name", obj.getSelectors().get(2).getName());
        assertEnum("/param/selector[3]/@type", obj.getSelectors().get(2).getType());
        assertXPath("/param/selector[3]", obj.getSelectors().get(2).getContent());

        assertXPath("/param/keepInSync", obj.isKeepInSync());
    }

    @Override
    protected void testSubResources(final TaskParam obj) throws Exception {
        // TODO Auto-generated method stub
    }

    @Override
    protected void testResourceWithoutSubResources(final TaskParam obj) throws Exception {
        // TODO Auto-generated method stub
    }
}