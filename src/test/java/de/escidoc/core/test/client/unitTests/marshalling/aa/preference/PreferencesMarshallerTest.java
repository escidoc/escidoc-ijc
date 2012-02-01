/**
 * 
 */
package de.escidoc.core.test.client.unitTests.marshalling.aa.preference;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.w3c.dom.DOMException;
import org.xml.sax.SAXException;

import de.escidoc.core.resources.aa.useraccount.Preferences;
import de.escidoc.core.test.client.unitTests.marshalling.MarshallerTestBase;

/**
 * @author Marko Voß
 * 
 */
public class PreferencesMarshallerTest extends MarshallerTestBase<Preferences> {

    private static final String BASE = "aa/preference";

    private static final String XSD = "0.1";

    /**
     * @param transport
     * @throws IOException
     * @throws ParserConfigurationException
     * @throws SAXException
     */
    public PreferencesMarshallerTest() throws IOException, ParserConfigurationException, SAXException {
        super(Preferences.class, BASE, XSD, "preferences_complete.xml");
    }

    @Override
    protected void validate(final Preferences obj) throws Exception {
        // Preferences
        assertXLinkList("/preferences:preferences", obj);
        assertXPath("/preferences:preferences/@user-objid", obj.getUserObjid());

        // Preference
        // TODO check if preferences can have xlink-attributes and objids
        // assertXLinkResource(
        // "/preferences:preferences/preferences:preference[1]", obj.get(0));

        validatePreference("/preferences:preferences", obj, 0);
        validatePreference("/preferences:preferences", obj, 1);
        validatePreference("/preferences:preferences", obj, 2);
    }

    /**
     * @param xPathContext
     * @param preferences
     * @param index
     * @throws TransformerException
     * @throws DOMException
     */
    private void validatePreference(final String xPathContext, final Preferences preferences, final int index)
        throws DOMException, TransformerException {

        final String path = xPathContext + "/preferences:preference[" + (index + 1) + "]";

        // Preferences within an attributes-tag may not have a LMD set.
        assertDateTime(path + "/@last-modification-date", preferences.get(index).getLastModificationDate(), false);

        assertXPath(path + "/@name", preferences.get(index).getName());
        assertXPath(path, preferences.get(index).getValue());
    }

    @Override
    protected void testSubResources(final Preferences obj) throws Exception {
        // TODO Auto-generated method stub
    }

    @Override
    protected void testResourceWithoutSubResources(final Preferences obj) throws Exception {
        // TODO Auto-generated method stub
    }
}